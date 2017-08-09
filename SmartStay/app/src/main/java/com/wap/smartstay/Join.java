package com.wap.smartstay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;


public class Join extends AppCompatActivity {
<<<<<<< HEAD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));

        toolbar.setTitle("회원가입");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ↓툴바의 홈버튼의 이미지를 변경(기본 이미지는 뒤로가기 화살표)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_search_black_24dp);
=======
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
>>>>>>> parent of ae014c3... add changePhoneNumber UI



        Button joinCheckIdBtn = (Button) findViewById(R.id.joinCheckIdBtn) ;
        joinCheckIdBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
            }
        });

        Button joinBtn = (Button) findViewById(R.id.joinSaveBtn) ;
        joinBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
            }
        });
    }
<<<<<<< HEAD

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_tool, menu);
        return true;
    }
=======
    public void connect(){
>>>>>>> parent of ae014c3... add changePhoneNumber UI

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
            {
                // 해당 버튼을 눌렀을 때 적절한 액션을 넣는다.
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
<<<<<<< HEAD

}
=======
}
>>>>>>> parent of ae014c3... add changePhoneNumber UI
