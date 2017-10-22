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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class Payment extends AppCompatActivity {
    public HttpConnection httpConnectionClient;
    TextView pay1, pay2, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payscreen);

        final Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1,1);
        myAnim.setInterpolator(interpolator);

        Button btnCoupon = (Button) findViewById(R.id.couponBtn);

        btnCoupon.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(myAnim);
                Intent Intent = new Intent(Payment.this, CouponList.class);
                startActivity(Intent);
            }
        });
        Intent intent = getIntent();
        String roomPrice = intent.getExtras().getString("roomPrice");
        final String roomNumber = intent.getExtras().getString("roomNumber");
        final String officeCode = intent.getExtras().getString("officeCode");

        final String startDate = intent.getExtras().getString("startDate");
        final String endDate = intent.getExtras().getString("endDate");

        pay1 = (TextView) findViewById(R.id.pay1);
        pay2 = (TextView) findViewById(R.id.pay2);
        date = (TextView) findViewById(R.id.date_info);

        pay1.setText(roomPrice);
        pay2.setText(roomPrice);
        date.setText(startDate + "~" + endDate);
        Button btnPayment = (Button) findViewById(R.id.paymentBtn);
        btnPayment.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(myAnim);
                try {
                    JSONObject object = new JSONObject();
                    object.put("head", "Reservation");
                    object.put("office_no", officeCode);
                    object.put("room_no", roomNumber);
                    object.put("ID", Login.Id);
                    object.put("StartDate", startDate);
                    object.put("EndDate", endDate);

                    String sendData = object.toString();

                    httpConnectionClient = new HttpConnection();
                    httpConnectionClient.sendObject(sendData);
                    String receiveData = httpConnectionClient.receiveObject();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "예약되었습니다.", Toast.LENGTH_SHORT).show();
                Intent Intent = new Intent(Payment.this, PayCheck.class);
                startActivity(Intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);

        Spinner Spinner = (Spinner) findViewById(R.id.Spinner);
        ArrayAdapter Adapter = ArrayAdapter.createFromResource(this, R.array.payment, android.R.layout.simple_spinner_item);
        Spinner.setAdapter(Adapter);


    }

}
