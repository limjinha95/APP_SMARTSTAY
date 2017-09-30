package com.wap.smartstay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity{
    TextInputEditText Eid, Epwd;
    TextView goJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

        Eid = (TextInputEditText) findViewById(R.id.loginIdEdit);
        Epwd = (TextInputEditText) findViewById(R.id.loginPwEdit);

        goJoin = (TextView) findViewById(R.id.goJoin);

        goJoin.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Join.class);
                startActivity(intent);
            }
        });

    }
}