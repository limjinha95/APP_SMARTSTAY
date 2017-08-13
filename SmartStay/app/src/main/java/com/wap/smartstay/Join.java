package com.wap.smartstay;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Socket;

public class Join extends AppCompatActivity {
    EditText Eid;
    EditText Ename;
    EditText Epwd;
    EditText EpwdCheck;
    EditText Epnum;
    static int check=0;
    static int check2=0;
    Context cont;
    Socket client;
    String ip = "192.168.43.179";
    int port = 4040;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.join);
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
                // TODO : click event
                Log.i("test",check+"");
                clientThread.send("ID/"+Eid.getText().toString());
                while(check==0)
                    Log.i("test",check+"");
                if(check==1)
                {
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
                Log.i("test",Epwd.getText()+" "+EpwdCheck.getText());
                if(Epwd.getText().toString().equals(EpwdCheck.getText().toString())) {
                    clientThread.send("R/" + Eid.getText().toString() + "-" + Ename.getText().toString() + "-" + Epwd.getText().toString() + "-" + Epnum.getText().toString());
                    while(check2==0);
                    if(check2==1) {
                        Toast.makeText(Join.this, "가입 축하 드립니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else if(check2==2) {
                        Toast.makeText(Join.this, "가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        check2=0;
                    }
                }
                else
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_tool, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                // 해당 버튼을 눌렀을 때 적절한 액션을 넣는다.
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
