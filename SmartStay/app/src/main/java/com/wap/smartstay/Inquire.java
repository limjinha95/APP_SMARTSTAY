package com.wap.smartstay;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Inquire extends AppCompatActivity {
    private EditText identify = null;
    private EditText subject = null;
    private EditText content = null;
    private Button Send = null;

    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("1:1 문의");

        identify = (EditText) findViewById(R.id.identify);
        subject = (EditText) findViewById(R.id.subject);
        content = (EditText) findViewById(R.id.content);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        Send = (Button) findViewById(R.id.Send);

        Send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MailSender sender = new MailSender("test51949@gmail.com","gummybear51949");

                try {
                    sender.sendMail(
                            subject.getText().toString(),
                            content.getText().toString(),
                            "test51949@gmail.com"
                    );
                    Toast.makeText(getApplicationContext(),"전송되었습니다",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        };

    }

    public void hideKeyboard(View view){
        imm.hideSoftInputFromWindow(identify.getWindowToken(),0);
        imm.hideSoftInputFromWindow(content.getWindowToken(),0);
        imm.hideSoftInputFromWindow(subject.getWindowToken(),0);
    }

}
