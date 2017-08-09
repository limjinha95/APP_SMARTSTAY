package com.wap.smartstay;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Reserve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve);

        Spinner Spinner = (Spinner)findViewById(R.id.Spinner);
        ArrayAdapter Adapter = ArrayAdapter.createFromResource(this, R.array.payment,
                android.R.layout.simple_spinner_item);
        Spinner.setAdapter(Adapter);
/*
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
        int dayOfMonth) {
            // TODO Auto-generated method stub
            // getCalender();
            int Year = year;
            int Month = monthOfYear;
            int Day = dayOfMonth;
            v.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(Month + 1).append("/").append(Day).append("/")
                    .append(Year).append(" "));
            System.out.println(v.getText().toString());
        }
        Button dateBtn = (Button) findViewById(R.id.dateBtn) ;
        dateBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar date = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(Reserve.this,
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH)
                );

            }
        });*/

        Button couponBtn = (Button) findViewById(R.id.couponBtn);
        couponBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent couponlist = new Intent(view.getContext(), CouponList.class);
                startActivity(couponlist);            }
        });

        Button paymentBtn = (Button) findViewById(R.id.paymentBtn) ;
        paymentBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"예약완료",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
