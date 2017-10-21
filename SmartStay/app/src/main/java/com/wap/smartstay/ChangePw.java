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
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class ChangePw extends AppCompatActivity {
    EditText Opw, Npw, Cpw;

    Handler handler;

    HttpConnection httpConnectionClient;

    int check = 0;
    Button changeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepw);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitle("비밀번호 변경");

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Opw = (EditText) findViewById(R.id.changpwOriginalPw);
        Npw = (EditText) findViewById(R.id.changpwNewPw);
        Cpw = (EditText) findViewById(R.id.changpwCheckNewPw);
        changeBtn = (Button) findViewById(R.id.changpwSaveBtn);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
            }
        };

        changePwEvent();

    }

    public void changePwEvent() {
        changeBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Npw.getText().toString().equals(Cpw.getText().toString())) {
                    JSONObject object = new JSONObject();
                    try {
                        object.put("head", "ChangePwd");
                        object.put("ID", Login.Id);
                        object.put("PWD", Npw.getText().toString());
                        String data = object.toString();
                        httpConnectionClient = new HttpConnection();
                        httpConnectionClient.sendObject(data);
                        String receiveMsg = httpConnectionClient.receiveObject();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(ChangePw.this, "비밀번호 변경에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                    check = 0;

                    Intent i = new Intent(ChangePw.this, MyInfo.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(ChangePw.this, "비밀번호를 다시 확인해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
