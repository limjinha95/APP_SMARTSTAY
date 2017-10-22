package com.wap.smartstay;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ihyeon-yeong on 2017. 10. 20..
 */

public class HttpConnection {
    String serverUrl;
    URL url;
    HttpURLConnection connection;

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public HttpConnection() {
        try {
            serverUrl = "http://13.125.39.91:8080/SmartStay/first";
            //serverUrl = "http://52.79.135.132:8080/SmartStay/first";

            url = new URL(serverUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(20000);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendObject(String sendData) {
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            bufferedWriter.write("data=" + sendData);
            Log.e("Send", "data=" + sendData);
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String receiveObject() {
        String receiveMessage = null;
        try {
            Log.e("receive", "r1");
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            Log.e("receive", "r2");
            receiveMessage = bufferedReader.readLine();
            Log.e("receive", "r3" + receiveMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("receive", receiveMessage);
        return receiveMessage;
    }
}
