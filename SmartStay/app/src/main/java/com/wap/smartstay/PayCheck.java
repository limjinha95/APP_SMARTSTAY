package com.wap.smartstay;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.net.Socket;


public class PayCheck extends AppCompatActivity {
    Socket client;
    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;

    Thread thread;
    ClientThread clientThread;
    Handler handler;

    Button paycheckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payscreen);

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        connect();

        JSONObject jo = new JSONObject();

        try{
            jo.put("head","Reserve");
            jo.put("ID", Login.Id);
        }catch (Exception e){

        }

        Button paycheckBtn = (Button) findViewById(R.id.paycheckBtn);
        paycheckBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(PayCheck.this,HotelList.class);
                startActivity(Intent);
            }
        });

    }


    public void connect(){
        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client,handler,Login.class);
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ClientThread.setRunningState(false);
        thread.interrupt();
    }
}