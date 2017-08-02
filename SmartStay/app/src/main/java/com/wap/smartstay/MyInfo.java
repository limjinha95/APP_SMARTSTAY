package com.wap.smartstay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;

public class MyInfo extends AppCompatActivity {
    Button Logout, Delete, ChangePwd, ChangePnum;
    TextView Id, Name, Pnum;
    Context cont;
    Socket client;
    String ip = "192.168.43.179";
    int port = 4040;
    static int delete=0;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("내 정보");

        Logout = (Button)findViewById(R.id.logoutBtn);
        Delete = (Button)findViewById(R.id.deleteUser);
        ChangePnum = (Button)findViewById(R.id.ChangeUserPhoneNumberBtn);
        ChangePwd = (Button)findViewById(R.id.ChangeUserPwBtn);
        Id = (TextView)findViewById(R.id.userId);
        Name = (TextView)findViewById(R.id.userName);
        Pnum = (TextView)findViewById(R.id.userPhoneNumber);

        Id.setText(Login.Id);
        Name.setText(Login.Name);
        Pnum.setText(Login.Pnum);

        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Toast.makeText (MyInfo.this, bundle.getString("msg"),Toast.LENGTH_SHORT).show();

            }
        };
        ChangePnum.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
                Intent i = new Intent(MyInfo.this,ChangePhone.class);
                startActivity(i);
            }
        });
        ChangePwd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
                Intent i = new Intent(MyInfo.this,ChangePw.class);
                startActivity(i);
            }
        });
        connect();
        Logout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
                Login.logined=0;
                Login.Name="";
                Login.Id="";
                Login.Pnum="";
                Toast.makeText(MyInfo.this,"로그아웃 하였습니다.",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MyInfo.this,Main.class);
                startActivity(i);
            }
        });
        Delete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
                clientThread.send("Delete/" + Login.Id);
                Login.logined=0;
                Login.Name="";
                Login.Id="";
                Login.Pnum="";
                while (delete == 0) ;
                if(delete==1) {
                    delete=0;
                    Toast.makeText(MyInfo.this, "탈퇴 성공 하였습니다.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MyInfo.this, Main.class);
                    startActivity(i);
                }
                else if(delete==2)
                {
                    delete=0;
                    Toast.makeText(MyInfo.this, "탈퇴 실패 하였습니다.", Toast.LENGTH_SHORT).show();
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
                    clientThread = new ClientThread(client,handler,MyInfo.class);
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }


}
