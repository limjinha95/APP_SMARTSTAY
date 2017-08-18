package com.wap.smartstay;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Manual extends AppCompatActivity {
    Intent manualMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("Smart Stay 메뉴얼");

        manualMain = new Intent(this, ManualMain.class);

        Button startBtn = (Button) findViewById(R.id.manualStartBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(manualMain);
            }
        });
    }
}
