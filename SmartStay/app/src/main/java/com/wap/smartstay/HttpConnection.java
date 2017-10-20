package com.wap.smartstay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ihyeon-yeong on 2017. 10. 20..
 */

public class HttpConnection {
    String serverUrl;
    URL url;
    HttpsURLConnection connection;

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public HttpConnection() {
        try {
            String serverUrl = "";
            url = new URL(serverUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpsURLConnection getConnection() {
        return connection;
    }

    public void sendObject(String sendData) {
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bufferedWriter.write(sendData);
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String receiveObject() {
        String receiveMessage="";
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            receiveMessage = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiveMessage;
    }
}
