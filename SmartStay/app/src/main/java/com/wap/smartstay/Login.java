package com.wap.smartstay;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

public class Login extends AppCompatActivity {
    EditText Eid, Epwd;
    Handler handler;
    Button loginBtn;
    TextView startJoinBtn;
    public static String Id, Pnum, Name;
    public static int Islogin = 0;
    HttpConnection httpConnectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Eid = (EditText) findViewById(R.id.loginIdEdit);
        Epwd = (EditText) findViewById(R.id.loginPwEdit);
        loginBtn = (Button) findViewById(R.id.loginStartBtn);
        startJoinBtn = (TextView) findViewById(R.id.joinStartBtn);


        Eid.setText("");
        Epwd.setText("");

        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Toast.makeText(Login.this, bundle.getString("msg"), Toast.LENGTH_SHORT).show();

            }
        };
        LoginEvent();
    }

    public void LoginEvent() {
        startJoinBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Join.class);
                startActivity(i);
            }
        });
        loginBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Islogin == 1) {
                    Toast.makeText(Login.this, "이미 로그인 하였습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    JSONObject object = new JSONObject();
                    try {
                        object.put("head", "Login");
                        object.put("ID", Eid.getText().toString());
                        object.put("PWD", Epwd.getText().toString());
                        String myToken = FirebaseInstanceId.getInstance().getToken();
                        object.put("Token", myToken);
                        String data = object.toString();
                        httpConnectionClient = new HttpConnection();
                        httpConnectionClient.sendObject(data);
                        String receiveMsg = httpConnectionClient.receiveObject();
                        Log.e("dd", receiveMsg.toString());
                        if (receiveMsg.equals("-")) {
                            Islogin = 2;
                            Log.e("dd", "d11");
                        } else {
                            Log.e("dd", "d2");
                            JSONObject jo = new JSONObject(receiveMsg);
                            Log.e("dd", "d3");
                            Id = jo.getString("user_id");
                            Log.e("dd", "d4");
                            Name = jo.getString("user_name");
                            Log.e("dd", "d5");
                            Pnum = jo.getString("user_mobile");
                            Log.e("dd", "d6");
                            Islogin = 1;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.e("dd", Islogin + "");

                    Eid.setText("");
                    Epwd.setText("");

                    if (Islogin == 1) {
                        Log.e("dd", "logind1");
                        Toast.makeText(Login.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, Main.class);
                        startActivity(i);
                        finish();
                    } else if (Islogin == 2) {
                        Log.e("dd", "logind2");
                        Toast.makeText(Login.this, "잘못된 ID 혹은 PWD 입니다.", Toast.LENGTH_SHORT).show();
                        Islogin = 0;
                    }
                }
            }
        });

    }


}