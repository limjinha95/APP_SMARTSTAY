package com.wap.smartstay;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inquire extends AppCompatActivity {
    private EditText identify = null;
    private EditText subject = null;
    private EditText content = null;
    private Button Send = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("1:1 문의");

        identify = (EditText) findViewById(R.id.identify);
        subject = (EditText) findViewById(R.id.subject);
        content = (EditText) findViewById(R.id.content);

        Send = (Button) findViewById(R.id.Send);

        Send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"전송완료",Toast.LENGTH_SHORT).show();

                MailSender sender = new MailSender("n2wstar@gmail.com","13mino's");

                // 연결확인
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                        .permitDiskReads()
                        .permitDiskWrites()
                        .permitNetwork().build());

                try {
                    sender.sendMail(
                            identify.getText().toString(),
                            subject.getText().toString(),
                            content.getText().toString()
                    );
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        });
    }
}
