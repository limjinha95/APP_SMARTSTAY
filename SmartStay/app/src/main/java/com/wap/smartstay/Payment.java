package com.wap.smartstay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payscreen);

        Button btnCoupon = (Button) findViewById(R.id.couponBtn);

        btnCoupon.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(Payment.this,CouponList.class);
                startActivity(Intent);
            }
        });

        Button btnPayment = (Button) findViewById(R.id.paymentBtn);
        btnPayment.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(Payment.this,Join.class);
                startActivity(Intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);

        Spinner Spinner = (Spinner) findViewById(R.id.Spinner);
        ArrayAdapter Adapter = ArrayAdapter.createFromResource(this, R.array.payment,
                android.R.layout.simple_spinner_item);
        Spinner.setAdapter(Adapter);


    }

}
