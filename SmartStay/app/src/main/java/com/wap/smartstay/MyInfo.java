package com.wap.smartstay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MyInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("내 정보");

        Button ChangeUserPwBtn = (Button) findViewById(R.id.ChangeUserPwBtn) ;
        ChangeUserPwBtn.setOnClickListener(new Button.OnClickListener() {
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



    }


}
