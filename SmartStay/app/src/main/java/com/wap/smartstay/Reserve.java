package com.wap.smartstay;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reserve extends AppCompatActivity implements View.OnClickListener {
    Button btnFromPicker, btnToPicker, btnCoupon, btnPayment;
    TextView textFrom, textTo, textPeriod;
    TextView textStayPrice, textCouponPrice, textTotalPrice;
    private int from_Year, from_Month, from_Day;
    private int to_Year, to_Month, to_Day;

    String str_startDate, str_endDate;
    Long diffDate;
    String period;

    private int stayPrice, couponPrice, totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve);

        btnFromPicker = (Button) findViewById(R.id.setFromBtn);
        btnToPicker = (Button) findViewById(R.id.setToBtn);
        btnCoupon = (Button) findViewById(R.id.couponBtn);
        btnPayment = (Button) findViewById(R.id.paymentBtn);

        textFrom = (TextView) findViewById(R.id.textFrom);
        textTo = (TextView) findViewById(R.id.textTo);
        textPeriod = (TextView) findViewById(R.id.textPeriod);

        textStayPrice = (TextView) findViewById(R.id.textStayPrice);
        textCouponPrice = (TextView) findViewById(R.id.textCouponPrice);
        textTotalPrice = (TextView) findViewById(R.id.textTotalPrice);

        btnFromPicker.setOnClickListener(this);
        btnToPicker.setOnClickListener(this);
        btnCoupon.setOnClickListener(this);
        btnPayment.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);

        Spinner Spinner = (Spinner) findViewById(R.id.Spinner);
        ArrayAdapter Adapter = ArrayAdapter.createFromResource(this, R.array.payment,
                android.R.layout.simple_spinner_item);
        Spinner.setAdapter(Adapter);
    }

    @Override
    public void onClick(View view) {

        if (view == btnFromPicker) { // startDate

            final Calendar startCal = Calendar.getInstance();

            from_Year = startCal.get(Calendar.YEAR);
            from_Month = startCal.get(Calendar.MONTH);
            from_Day = startCal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dp1 = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            textFrom.setText(year + "." + (monthOfYear + 1) + "." + dayOfMonth + " ~"); //N월 N일 ~ N월 N일
                        }
                    }, from_Year, from_Month, from_Day);
            dp1.getDatePicker().setMinDate(startCal.getTimeInMillis());
            dp1.show();

            StringBuilder startDate = new StringBuilder();

            if(from_Month < 10){
                startDate.append("0" + from_Month + "/");
                if(from_Day < 10){
                    startDate.append("0" + from_Day + "/" + from_Year);
                }
            }else {
                startDate.append(from_Month + "/");
                if(from_Day < 10){
                    startDate.append("0" + from_Day + "/" + from_Year);
                }
            }

            str_startDate = startDate.toString();

        }
        if (view == btnToPicker) {  // endDate

            final Calendar endCal = Calendar.getInstance();
            to_Year = endCal.get(Calendar.YEAR);
            to_Month = endCal.get(Calendar.MONTH);
            to_Day = endCal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dp2 = new DatePickerDialog(this,

                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            textTo.setText(year + "."+ (monthOfYear + 1) + "." + dayOfMonth ); //N월 N일 ~ N월 N일

                        }
                    }, to_Year, to_Month, to_Day);

            dp2.getDatePicker().setMinDate(endCal.getTimeInMillis());
            dp2.show();

            StringBuilder endDate = new StringBuilder();

            if(to_Month < 10){
                endDate.append("0" + to_Month + "/");
                if(to_Day < 10){
                    endDate.append("0" + to_Day + "/" + to_Year);
                }
            }else {
                endDate.append(to_Month + "/");
                if(to_Day < 10){
                    endDate.append("0" + to_Day + "/" + to_Year);
                }
            }

            str_endDate = endDate.toString();

            // get date period
            try{
                Date start;
                Date end;

                SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");

                start = date.parse(str_startDate);
                end = date.parse(str_endDate);

                long diff = Math.abs((int)(start.getTime()) - (int)(end.getTime()) );
                diffDate = diff / (24 * 60 * 60 * 1000);

                period = Long.toString(diffDate);

                if(diffDate == 0){
                    textPeriod.setText("1박");
                }else if(diffDate > 0){
                    textPeriod.setText(diffDate + "박" + (diffDate - 1) + "일");
                }else{
                    Toast.makeText(getApplicationContext(),"잘못된 날짜입니다",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (view == btnCoupon) {
            Intent couponlist = new Intent(view.getContext(), CouponList.class);
            startActivity(couponlist);

            textTotalPrice.setText((stayPrice - couponPrice) + "원");
            textCouponPrice.setText(couponPrice + "원");
        }
        if (view == btnPayment) {
            Toast.makeText(getApplicationContext(), "예약완료", Toast.LENGTH_SHORT).show();
//            Intent Intent = new Intent() // 이동
        }
    }
}