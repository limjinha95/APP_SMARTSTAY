package com.wap.smartstay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("로그인");

        Button joinStartBtn = (Button) findViewById(R.id.joinStartBtn);
        joinStartBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent joinStart = new Intent(getBaseContext(), Join.class);
                        startActivity(joinStart);
                    }
                }
        );

        Button loginStartBtn = (Button) findViewById(R.id.loginStartBtn) ;
        loginStartBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO : click event
            }
        });

    }
}
