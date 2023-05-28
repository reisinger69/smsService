package reisinger.privat.smsservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import reisinger.privat.smsservice.AsyncTasks.POST_SMSDelivered_AsyncTask;

public class SmsDeliveredReceiver extends BroadcastReceiver {

    private SMSDTO smsdto;
    public SmsDeliveredReceiver(SMSDTO smsdto){
        this.smsdto = smsdto;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                POST_SMSDelivered_AsyncTask smsDelivered_asyncTask_OK = new POST_SMSDelivered_AsyncTask();
                smsdto.setAction("DELIVERED");
                smsDelivered_asyncTask_OK.execute(smsdto);
                break;
            case Activity.RESULT_CANCELED:
                POST_SMSDelivered_AsyncTask smsDelivered_asyncTask_Error = new POST_SMSDelivered_AsyncTask();
                smsdto.setAction("ERROR");
                smsDelivered_asyncTask_Error.execute(smsdto);
                break;
            // Handle other possible results
        }
    }
}
