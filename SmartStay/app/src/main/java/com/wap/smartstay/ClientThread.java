package com.wap.smartstay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.wap.smartstay.Fragment.SmartkeyFragment;

import org.json.JSONObject;

public class ClientThread extends Thread {
    Class clas;
    BufferedReader bufferR;
    BufferedWriter bufferW;
    Socket client;
    Handler handler;
    static private boolean isRunning;
    public ClientThread(Socket client, Handler handler, Class clas) {
        this.handler = handler;
        this.clas=clas;
        isRunning=true;
        try {
            this.client = client;
            bufferR = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF8"));
            bufferW = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void send(String data) {
        try {
            bufferW.write(data);
            bufferW.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String listen() {
        String msg = null;
        try {
            while (isRunning) {
                msg = bufferR.readLine();
                Log.i("testmsg", msg);
                if(clas.getName().equals("com.wap.smartstay.Login")) {
                    if(msg.toString().equals("-")) {
                        Login.Islogin=2;
                    }
                    else {
                        try {
                            JSONObject jo = new JSONObject(msg);
                            Login.Id = jo.getString("ID");
                            Login.Name = jo.getString("NAME");
                            Login.Pnum = jo.getString("Pnum");
                            Login.Islogin = 1;
                        }catch (Exception e)
                        {

                        }
                    }
                }
                else if(clas.getName().equals("com.wap.smartstay.Join")) {
                    String msg2="";
                    try {
                        JSONObject jo = new JSONObject(msg);
                        msg2 = jo.getString("Unique");
                    }catch (Exception e)
                    {

                    }
                    if(msg2.equals("Y"))
                        Join.check = 1;
                    else if(msg2.equals("N"))
                        Join.check=2;
                }
                else if(clas.getName().equals("com.wap.smartstay.Fragment.SmartkeyFragment")){
                    SmartkeyFragment.phoneNumber = msg;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return msg;
    }
    static public void setRunningState(boolean state){
        isRunning = state;
    }
    public void run() {
        super.run();
        listen();
    }
}

