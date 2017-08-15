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

public class ClientThread extends Thread {
    Class clas;
    BufferedReader bufferR;
    BufferedWriter bufferW;
    Socket client;
    Handler handler;

    public ClientThread(Socket client, Handler handler, Class clas) {
        this.handler = handler;
        this.clas=clas;
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
            while (true) {
                msg = bufferR.readLine();
                Log.i("test", msg);
                if(clas.getName().equals("com.wap.smartstay.Login")) {
                    if(msg.toString().equals("-")) {
                        Login.Islogin=2;
                    }
                    else {
                        String[] loginData = new String[3];
                        loginData = msg.toString().split("/");
                        Login.Id = loginData[0];
                        Login.Name = loginData[1];
                        Login.Pnum = loginData[2];
                        Login.Islogin=1;
                    }
                }
                else if(clas.getName().equals("com.wap.smartstay.Join")) {
                    if(msg.equals("Y"))
                        Join.check = 1;
                    else if(msg.equals("N"))
                        Join.check=2;
                    else if(msg.equals("success"))
                        Join.check2=1;
                    else if(msg.equals("failed"))
                        Join.check2=2;
                }
                else if(clas.getName().equals("com.wap.smartstay.MyInfo")) {
                    if(msg.equals("success"))
                        MyInfo.delete=1;
                    else if(msg.equals("failed"))
                        MyInfo.delete=2;
                }
                else if(clas.getName().equals("com.wap.smartstay.ChagnePw")) {
                    if(msg.equals("success"))
                        ChangePw.check=1;
                    else if(msg.equals("failed"))
                        ChangePw.check=2;
                }
                else if(clas.getName().equals("com.wap.smartstay.ChangePnum")) {
//                    if(msg.equals("success"))
//                        ChangePhone.check=1;
//                    else if(msg.equals("failed"))
//                        ChangePhone.check=2;
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
    public void run() {
        super.run();
        listen();
    }
}

