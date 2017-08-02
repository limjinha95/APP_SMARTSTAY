package com.wap.smartstay;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
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

        Button loginBtn = (Button) findViewById(R.id.loginStartBtn) ;
        loginBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(logined==1)
                {
                    Toast.makeText(Login.this,"이미 로그인 하였습니다.",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    clientThread.send("L/" + Eid.getText().toString() + "-" + Epwd.getText().toString());
                    Eid.setText("");
                    Epwd.setText("");
                    Log.i("test","대기");

                    while (logined == 0)
                        Log.i("test",logined+"");
                    Log.i("test","대기끝");

                    if (logined == 1) {
                        Toast.makeText(Login.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this,Main.class);
                        startActivity(i);
                    } else if (logined == 2) {
                        Toast.makeText(Login.this, "잘못된 ID 혹은 PWD 입니다.", Toast.LENGTH_SHORT).show();
                        logined = 0;
                    }
                }
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
