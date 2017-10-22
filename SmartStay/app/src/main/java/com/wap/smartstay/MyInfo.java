package com.wap.smartstay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class MyInfo extends AppCompatActivity {
    Button Logout, Delete, ChangePwd, ChangeUserPhoneNumberBtn;
    TextView Id, Name, Pnum;
    HttpConnection httpConnectionClient;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitle("내 정보");

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Logout = (Button) findViewById(R.id.logoutBtn);
        Delete = (Button) findViewById(R.id.deleteUser);
        ChangeUserPhoneNumberBtn = (Button) findViewById(R.id.ChangeUserPhoneNumberBtn);
        ChangePwd = (Button) findViewById(R.id.ChangeUserPwBtn);
        Id = (TextView) findViewById(R.id.userId);
        Name = (TextView) findViewById(R.id.userName);
        Pnum = (TextView) findViewById(R.id.userPhoneNumber);

        Id.setText(Login.Id);
        Name.setText(Login.Name);
        Pnum.setText(Login.Pnum);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Toast.makeText(MyInfo.this, bundle.getString("msg"), Toast.LENGTH_SHORT).show();
            }
        };
        MyInfoEvent();
    }

    public void MyInfoEvent() {
        ChangeUserPhoneNumberBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyInfo.this, ChangePhone.class);
                startActivity(i);
            }
        });

        ChangePwd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyInfo.this, ChangePw.class);
                startActivity(i);
            }
        });


        Logout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.Islogin = 0;
                Login.Name = "";
                Login.Id = "";
                Login.Pnum = "";

                Toast.makeText(MyInfo.this, "로그아웃 하였습니다.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MyInfo.this, Login.class);
                startActivity(i);
            }
        });

        Delete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();

                try {
                    object.put("head", "Delete");
                    object.put("ID", Login.Id);
                    String data = object.toString();

                    httpConnectionClient = new HttpConnection();
                    httpConnectionClient.sendObject(data);
                    String receive = httpConnectionClient.receiveObject();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Login.Islogin = 0;
                Login.Name = "";
                Login.Id = "";
                Login.Pnum = "";
                Toast.makeText(MyInfo.this, "탈퇴 성공 하였습니다.", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MyInfo.this, Login.class);
                startActivity(i);
            }
        });
    }
}