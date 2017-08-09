package com.wap.smartstay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Socket;

import static android.R.attr.port;

public class Login extends AppCompatActivity {
    EditText Eid,Epwd;
    Context cont;
    Socket client;
    String ip = "192.168.43.179";
    int port = 4040;
    public static String Id,Pnum,Name;
    public static int logined=0;
    Thread thread;
    ClientThread clientThread;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cont = this;
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.login);
        Eid = (EditText)findViewById(R.id.loginIdEdit);
        Epwd = (EditText)findViewById(R.id.loginPwEdit);
        Eid.setText("");
        Epwd.setText("");

        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Toast.makeText (Login.this, bundle.getString("msg"),Toast.LENGTH_SHORT).show();

            }
        };
        connect();
        Button startJoinBtn = (Button) findViewById(R.id.joinStartBtn) ;
        startJoinBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
                Intent i = new Intent(Login.this,Join.class);
                startActivity(i);
            }
        });

        Button loginStartBtn = (Button) findViewById(R.id.loginStartBtn) ;
        loginStartBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO : click event
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
}
