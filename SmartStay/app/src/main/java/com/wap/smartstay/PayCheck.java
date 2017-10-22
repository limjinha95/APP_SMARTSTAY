package com.wap.smartstay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PayCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paycheck);

        final Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1,1);
        myAnim.setInterpolator(interpolator);

        Button payCheckBtn = (Button) findViewById(R.id.paycheckBtn);
        payCheckBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        v.startAnimation(myAnim);
                        Intent i = new Intent(PayCheck.this, Main.class);
                        startActivity(i);
                        finish();
                    }
                }
        );



    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent i = new Intent(PayCheck.this, Main.class);
        startActivity(i);
        finish();
    }

}
