package com.spearheads.nchoudhry.rentpayle;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by N.Choudhry on 8/16/2015.
 */
public class SearchItem extends AsyncTask<String,Void,String> {
    private TextView resultBox;

    public SearchItem(TextView logArea) {
        this.resultBox = logArea;
    }

    @Override
    protected String doInBackground(String... params) {
        String item = params[0];
        String link = "http://192.168.44.172:8000/api/time";
        StringBuilder chained = new StringBuilder("");
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());

            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = rd.readLine()) != null) {
                chained.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chained.toString();
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        try {
            JSONArray resultJSON = new JSONArray(result);
            resultBox.setText("Call Completed " + resultJSON.get(1).toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
