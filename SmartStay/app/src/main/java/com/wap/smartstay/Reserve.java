package com.wap.smartstay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Reserve extends AppCompatActivity {
    Calendar dateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve);

        Spinner Spinner = (Spinner) findViewById(R.id.Spinner);
        ArrayAdapter Adapter = ArrayAdapter.createFromResource(this, R.array.payment,
                android.R.layout.simple_spinner_item);
        Spinner.setAdapter(Adapter);

        Button dateBtn = (Button) findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  updateDate();
            }
        });

        Button couponBtn = (Button) findViewById(R.id.couponBtn);
        couponBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent couponlist = new Intent(view.getContext(), CouponList.class);
                startActivity(couponlist);
            }
        });

        Button paymentBtn = (Button) findViewById(R.id.paymentBtn);
        paymentBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "예약완료", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
