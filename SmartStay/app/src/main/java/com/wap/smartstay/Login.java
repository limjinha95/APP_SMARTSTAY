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

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.Socket;

public class Login extends AppCompatActivity {
    EditText Eid, Epwd;
    Context cont;

    Socket client;

    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    public static String Id, Pnum, Name;
    public static int Islogin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cont = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        JSONArray ja = new JSONArray();

        Eid = (EditText) findViewById(R.id.loginIdEdit);
        Epwd = (EditText) findViewById(R.id.loginPwEdit);
        Eid.setText("");
        Epwd.setText("");

        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Toast.makeText(Login.this, bundle.getString("msg"), Toast.LENGTH_SHORT).show();

            }
        };

        connect();

        Button startJoinBtn = (Button) findViewById(R.id.joinStartBtn);
        startJoinBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Join.class);
                startActivity(i);
            }
        });

        Button loginBtn = (Button) findViewById(R.id.loginStartBtn);
        loginBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Islogin == 1) {
                    Toast.makeText(Login.this, "이미 로그인 하였습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    JSONObject jo = new JSONObject();

                    try {
                        jo.put("head", "Login");
                        jo.put("ID", Eid.getText().toString());
                        jo.put("PWD", Epwd.getText().toString());

                        String myToken = FirebaseInstanceId.getInstance().getToken();
                        jo.put("Token", myToken);
                    } catch (Exception e) {

                    }

                    String data = jo.toString();
                    clientThread.send(data);

                    Eid.setText("");
                    Epwd.setText("");

                    while (Islogin == 0)
                        Log.i("test", Islogin + "");
                    Log.i("test", "대기끝");

                    if (Islogin == 1) {
                        Toast.makeText(Login.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, Main.class);
                        startActivity(i);
                        finish();
                    } else if (Islogin == 2) {
                        Toast.makeText(Login.this, "잘못된 ID 혹은 PWD 입니다.", Toast.LENGTH_SHORT).show();
                        Islogin = 0;
                    }
                }
            }
        });

    }

    public void connect() {

        thread = new Thread() {
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client, handler, Login.class);
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClientThread.setRunningState(false);
        thread.interrupt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        connect();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        connect();
    }
}