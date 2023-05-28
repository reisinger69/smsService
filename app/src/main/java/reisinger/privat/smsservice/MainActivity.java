package reisinger.privat.smsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import reisinger.privat.smsservice.AsyncTasks.GET_SendMessage_AsyncTask;

public class MainActivity extends AppCompatActivity {

    String phone = "067762951641";
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usingCountDownTimer();
    }


    public void usingCountDownTimer() {
        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 20000) {

            // This is called after every 10 sec interval.
            public void onTick(long millisUntilFinished) {
                GET_SendMessage_AsyncTask sendmessage = new GET_SendMessage_AsyncTask(new SendCallback() {
                    @Override
                    public void callBack(SMSDTO smsdto) {
                        if (smsdto != null) {
                            smsSendMessage(smsdto);
                        }
                    }
                });
                sendmessage.execute();
            }

            public void onFinish() {
                start();
            }
        }.start();
    }

    public void smsSendMessage(SMSDTO smsdto) {
        Intent sentIntent = new Intent("SMS_SENT");
        PendingIntent sentPendingIntent = PendingIntent.getBroadcast(this, 0, sentIntent, PendingIntent.FLAG_IMMUTABLE);
        registerReceiver(new SmsSentReceiver(smsdto), new IntentFilter("SMS_SENT"));

        Intent deliveryIntent = new Intent("SMS_DELIVERED");
        PendingIntent deliveryPendingIntent = PendingIntent.getBroadcast(this, 0, deliveryIntent, PendingIntent.FLAG_IMMUTABLE);
        registerReceiver(new SmsDeliveredReceiver(smsdto), new IntentFilter("SMS_DELIVERED"));

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(smsdto.getPhoneNumber(), null, smsdto.getMessage(), sentPendingIntent, deliveryPendingIntent);
    }
}