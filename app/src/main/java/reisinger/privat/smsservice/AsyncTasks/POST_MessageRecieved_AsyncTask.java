package reisinger.privat.smsservice.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import reisinger.privat.smsservice.SMSDTO;

public class POST_MessageRecieved_AsyncTask extends AsyncTask<SMSDTO, Integer, Void> {

    private final String apiKey = "ab4025400c6d76d54c5ec920b3c8cdf74d541a4b6872f768733e96cdc26e6edb";
    private final String url = "http://localhost:8080";

    @Override
    protected Void doInBackground(SMSDTO... smsdtos) {
        SMSDTO smsdto = smsdtos[0];
        Gson gson = new Gson();
        String jsonString = gson.toJson(smsdto);
        try {
            HttpURLConnection httpURLConnection = getPostConnection(jsonString, url);
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.i("SMS", "Message sent to Server");
            } else {
                Log.e("SMS", "Error while sending Message to Server");
            }
            httpURLConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection getPostConnection(String data, String url) throws IOException {
        HttpURLConnection connection =
                (HttpURLConnection) new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content=Type", "application/json");
        connection.setRequestProperty("ApiKey", apiKey);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.close();
        return connection;
    }

}
