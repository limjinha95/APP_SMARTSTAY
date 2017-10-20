package com.wap.smartstay;

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

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

public class Join extends AppCompatActivity {
    EditText Eid;
    EditText Ename;
    EditText Epwd;
    EditText EpwdCheck;
    EditText Epnum;
    int check = 0;
    Button joinCheckIdBtn, joinBtn;

    HttpConnection httpConnectionClient;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("회원가입");

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Toast.makeText(Join.this, bundle.getString("msg"), Toast.LENGTH_SHORT).show();
            }
        };


        Eid = (EditText) findViewById(R.id.joinIdEdit);
        Ename = (EditText) findViewById(R.id.joinNameEdit);
        Epwd = (EditText) findViewById(R.id.joinPwEdit);
        EpwdCheck = (EditText) findViewById(R.id.joinCheckPwEdit);
        Epnum = (EditText) findViewById(R.id.joinPhoneEdit);

        joinCheckIdBtn = (Button) findViewById(R.id.joinCheckIdBtn);
        joinBtn = (Button) findViewById(R.id.joinSaveBtn);
        joinBtn.setEnabled(false);

        joinEvent();

    }

    public void joinEvent() {

        joinCheckIdBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();

                try {
                    object.put("head", "ID");
                    object.put("ID", Eid.getText().toString());
                    String sendData = object.toString();

                    httpConnectionClient = new HttpConnection();
                    httpConnectionClient.sendObject(sendData);
                    String receiveMsg = httpConnectionClient.receiveObject();
                    object = new JSONObject(receiveMsg);
                    String data = object.get("Unique").toString();

                    if (data.equals("Y"))
                        check = 1;
                    else
                        check = 2;


                } catch (Exception e) {
                    e.printStackTrace();
                }

                while (check == 0)
                    Log.i("test", check + "");

                if (check == 1) {
                    Toast.makeText(Join.this, "사용이 가능한 ID입니다.", Toast.LENGTH_SHORT).show();
                    joinBtn.setEnabled(true);
                } else if (check == 2) {
                    Toast.makeText(Join.this, "중복된 ID가 존재합니다.", Toast.LENGTH_SHORT).show();
                    check = 0;
                }
            }
        });

        joinBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Epwd.getText().toString().equals(EpwdCheck.getText().toString())) {
                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                    JSONObject object = new JSONObject();

                    try {
                        object.put("head", "Register");
                        object.put("ID", Eid.getText().toString());
                        object.put("PWD", Epwd.getText().toString());
                        object.put("Name", Ename.getText().toString());
                        object.put("Pnum", Epnum.getText().toString());
                        object.put("Token", refreshedToken);
                        String data = object.toString();

                        httpConnectionClient = new HttpConnection();
                        httpConnectionClient.sendObject(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Join.this, "가입 축하 드립니다.", Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(Join.this, "비밀번호를 다시 확인해 주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
