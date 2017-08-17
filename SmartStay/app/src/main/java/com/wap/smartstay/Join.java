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

import java.net.Socket;

public class Join extends AppCompatActivity {
    EditText Eid;
    EditText Ename;
    EditText Epwd;
    EditText EpwdCheck;
    EditText Epnum;
    static int check=0;

    Socket client;
    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("회원가입");

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Toast.makeText (Join.this, bundle.getString("msg"),Toast.LENGTH_SHORT).show();
            }
        };

        connect();

        Eid = (EditText)findViewById(R.id.joinIdEdit);
        Ename = (EditText)findViewById(R.id.joinNameEdit);
        Epwd = (EditText)findViewById(R.id.joinPwEdit);
        EpwdCheck = (EditText)findViewById(R.id.joinCheckPwEdit);
        Epnum = (EditText)findViewById(R.id.joinPhoneEdit);

        Button joinCheckIdBtn = (Button) findViewById(R.id.joinCheckIdBtn) ;
        final Button joinBtn = (Button) findViewById(R.id.joinSaveBtn) ;
        joinBtn.setEnabled(false);

        joinCheckIdBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jo = new JSONObject();

                try{
                    jo.put("head", "ID");
                    jo.put("ID", Eid.getText().toString());
                } catch (Exception e) {

                }

                String data = jo.toString();
                clientThread.send(data);
                while(check==0)
                    Log.i("test",check+"");

                if(check==1) {
                    Toast.makeText(Join.this,"사용이 가능한 ID입니다.",Toast.LENGTH_SHORT).show();
                    joinBtn.setEnabled(true);
                }
                else if(check==2) {
                    Toast.makeText(Join.this, "중복된 ID가 존재합니다.", Toast.LENGTH_SHORT).show();
                    check=0;
                }
            }
        });

        joinBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Epwd.getText().toString().equals(EpwdCheck.getText().toString())) {
                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                    JSONObject jo = new JSONObject();

                    try {
                        jo.put("head","Register");
                        jo.put("ID",Eid.getText().toString());
                        jo.put("PWD",Epwd.getText().toString());
                        jo.put("Name",Ename.getText().toString());
                        jo.put("Pnum",Epnum.getText().toString());
                        jo.put("Token",refreshedToken);
                    } catch(Exception e) {

                    }

                    String data = jo.toString();
                    clientThread.send(data);
                    Toast.makeText(Join.this, "가입 축하 드립니다.", Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(Join.this,"비밀번호를 다시 확인해 주세요.",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void connect(){
        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client,handler,Join.class);
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

    @Override
    protected void onRestart(){
        super.onRestart();
        connect();
    }
}
