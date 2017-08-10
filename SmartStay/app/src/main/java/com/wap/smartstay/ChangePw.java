package com.wap.smartstay;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Socket;

public class ChangePw extends AppCompatActivity {
    EditText Opw,Npw,Cpw;
    Context cont;
    Socket client;
    String ip = "192.168.43.179";
    int port = 4040;
    public static int check=0;
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

        setContentView(R.layout.changepw);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("비밀번호 변경");

        Opw = (EditText)findViewById(R.id.changpwOriginalPw);
        Npw = (EditText)findViewById(R.id.changpwNewPw);
        Cpw = (EditText)findViewById(R.id.changpwCheckNewPw);

        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
            }
        };

        connect();
        Button changeBtn = (Button) findViewById(R.id.changpwSaveBtn) ;
        changeBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
                if(Npw.getText().toString().equals(Cpw.getText().toString())) {
                    clientThread.send("ChagnePwd/" +Login.Id + "-" + Npw.getText().toString());
                    while (check == 0)
                        Log.i("test",logined+"");
                    Log.i("test","대기끝");

                    if (check == 1) {
                        Toast.makeText(ChangePw.this, "비밀번호 변경에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        check = 0;
                        Intent i = new Intent(ChangePw.this,MyInfo.class);
                        startActivity(i);
                        finish();
                    } else if (check == 2) {
                        Toast.makeText(ChangePw.this, "비밀번호 변경에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        check = 0;
                        Intent i = new Intent(ChangePw.this,MyInfo.class);
                        startActivity(i);
                        finish();
                    }
                }
                else {
                    Toast.makeText(ChangePw.this,"비밀번호를 다시 확인해 주세요.",Toast.LENGTH_SHORT).show();
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
