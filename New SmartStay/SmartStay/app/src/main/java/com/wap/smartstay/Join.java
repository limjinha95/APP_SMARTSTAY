package com.wap.smartstay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Join extends AppCompatActivity{
    TextInputEditText Eid;
    TextInputEditText Ename;
    TextInputEditText Epwd;
    TextInputEditText EpwdCheck;
    TextInputEditText Epnum;
    TextView goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        getSupportActionBar().hide();


        Eid = (TextInputEditText) findViewById(R.id.joinIdEdit);
        Ename = (TextInputEditText) findViewById(R.id.joinNameEdit);
        Epwd = (TextInputEditText) findViewById(R.id.joinPwEdit);
        EpwdCheck = (TextInputEditText) findViewById(R.id.joinCheckPwEdit);
        Epnum = (TextInputEditText) findViewById(R.id.joinPhoneEdit);

        goback = (TextView) findViewById(R.id.goback);

        Button joinCheckIdBtn = (Button) findViewById(R.id.joinCheckIdBtn);
        Button joinBtn = (Button) findViewById(R.id.joinSaveBtn);

        goback.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Join.this, Login.class); // 두번째 액티비티를 실행하기 위한 인텐
                startActivity(intent);
            }
        });

        joinBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Eid.getText().toString().length() == 0){
                    Toast.makeText(Join.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    Eid.requestFocus();
                    return;
                }

                if(Ename.getText().toString().length() == 0){
                    Toast.makeText(Join.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                    Ename.requestFocus();
                    return;
                }

                if(Epwd.getText().toString().length() == 0){
                    Toast.makeText(Join.this, "패스워드를 입력하세요.", Toast.LENGTH_SHORT).show();
                    Epwd.requestFocus();
                    return;
                }

                if( Epwd.getText().toString().equals(EpwdCheck.getText().toString()) ) {
                    Toast.makeText(Join.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    EpwdCheck.requestFocus();
                    return;
                }

                if(Epnum.getText().toString().length() == 0){
                    Toast.makeText(Join.this, "핸드폰 번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    Epnum.requestFocus();
                    return;
                }

                Toast.makeText(Join.this, "가입 축하 드립니다.", Toast.LENGTH_SHORT).show();

            }

        });
    }

    }
