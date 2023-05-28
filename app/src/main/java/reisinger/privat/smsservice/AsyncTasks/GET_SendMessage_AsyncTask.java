package reisinger.privat.smsservice.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import reisinger.privat.smsservice.SendCallback;
import reisinger.privat.smsservice.SMSDTO;

public class GET_SendMessage_AsyncTask extends AsyncTask<Void, Integer, SMSDTO> {

    private final String apiKey = "ab4025400c6d76d54c5ec920b3c8cdf74d541a4b6872f768733e96cdc26e6edb";
    private final String url = "https://api.rtws.at/smshub/v1/new-message";

    private SendCallback sendCallback;

    public GET_SendMessage_AsyncTask(SendCallback callback){
        this.sendCallback = callback;
    }

    @Override
    protected void onPostExecute(SMSDTO smsdto) {
        sendCallback.callBack(smsdto);
    }

    @Override
    protected SMSDTO doInBackground(Void... voids) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("ApiKey", apiKey);
            int responseCode = connection.getResponseCode();
            Log.i("SMS", "ResponseCode: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = br.readLine();
                br.close();
                if (line.isEmpty()){
                    Log.i("SMS", "New Message");
                    return null;
                }
                Gson gson = new Gson();
                Log.i("SMS", "GOT Message");
                return gson.fromJson(line, SMSDTO.class);

            } else if(responseCode == 204){
                Log.i("SMS", "No Message");
            } else {
                Log.e("SMS", "Error while getting Message from Server");
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
