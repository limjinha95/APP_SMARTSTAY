package com.wap.smartstay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
=======
>>>>>>> parent of ae014c3... add changePhoneNumber UI
import android.view.View;
import android.widget.Button;

public class MyInfo extends AppCompatActivity {
<<<<<<< HEAD

=======
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
>>>>>>> parent of ae014c3... add changePhoneNumber UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);

<<<<<<< HEAD
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("내 정보");

        Button ChangeUserPwBtn = (Button) findViewById(R.id.ChangeUserPwBtn) ;
        ChangeUserPwBtn.setOnClickListener(new Button.OnClickListener() {
=======
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
                Intent i = new Intent(MyInfo.this,ChangePnum.class);
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
>>>>>>> parent of ae014c3... add changePhoneNumber UI
            @Override
            public void onClick(View view) {
                // TODO: 2017. 7. 31. 비밀번호 변경 구현
                Intent intent = new Intent(getBaseContext(), ChangePw.class);
                startActivity(intent);
            }
        });

        Button ChangeUserPhoneNumberBtn = (Button) findViewById(R.id.ChangeUserPhoneNumberBtn) ;
        ChangeUserPhoneNumberBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2017. 7. 31. 전화번호 변경 구현
            }
        });
<<<<<<< HEAD


=======
    }
    public void connect(){
>>>>>>> parent of ae014c3... add changePhoneNumber UI

    }
}
