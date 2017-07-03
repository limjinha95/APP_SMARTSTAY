package com.wap.smartstay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button startJoinBtn = (Button) findViewById(R.id.startJoinBtn) ;
        startJoinBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
            }
        });

        Button loginBtn = (Button) findViewById(R.id.loginBtn) ;
        loginBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO : click event
            }
        });

    }
}
