package reisinger.privat.smsservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import reisinger.privat.smsservice.AsyncTasks.POST_SMSDelivered_AsyncTask;
import reisinger.privat.smsservice.AsyncTasks.POST_SMSSent_AsyncTask;

public class SmsSentReceiver extends BroadcastReceiver {

    private SMSDTO smsdto;

    public SmsSentReceiver(SMSDTO smsdto){
        this.smsdto = smsdto;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                POST_SMSSent_AsyncTask smsDelivered_asyncTask_OK = new POST_SMSSent_AsyncTask();
                smsdto.setAction("SENT");
                smsDelivered_asyncTask_OK.execute(smsdto);
                break;
            case Activity.RESULT_CANCELED:
                POST_SMSSent_AsyncTask smsDelivered_asyncTask_Error = new POST_SMSSent_AsyncTask();
                smsdto.setAction("ERROR");
                smsDelivered_asyncTask_Error.execute(smsdto);
                break;
        }
    }
}
