package com.wap.smartstay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends AppCompatActivity {
    Socket client;
    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;

    Thread thread;
    ClientThread clientThread;
    Handler handler;

    TextView tvInformation, tvDate, tvLocation;
    TextView tvStayPrice, tvCouponPrice,tvTotalPrice;

    public static String start, end, rNum, officeCode, location;
    public static int couponPrice;

    String N_start = "", N_end = "";
    Date St, Ed;
    Long diffDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payscreen);

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //
        tvInformation = (TextView) findViewById(R.id.tvInformation);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvLocation = (TextView) findViewById(R.id.tvLocation);

        tvStayPrice = (TextView) findViewById(R.id.tvStayPrice);
        tvCouponPrice = (TextView) findViewById(R.id.tvCouponPrice);
        tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);

        connect();

        JSONObject jo = new JSONObject();

        try{
            jo.put("head","Reserve");
            jo.put("ID",Login.Id);
        }catch (Exception e){

        }

        String data = jo.toString();

        clientThread.send(data);
        Log.i("test","대기");

        tvInformation.setText(officeCode);
        tvLocation.setText(location);
        tvDate.setText(start + "~" + end);
        tvCouponPrice.setText(couponPrice);

        String[] startDate = start.split("-");
        String[] endDate = end.split("-");

        N_start = startDate[1] + "/" + startDate[3] + "/" + startDate[0];
        N_end = endDate[1] + "/" + endDate[3] + "/" + endDate[0];

        try{
            SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");

            St = date.parse(N_start);
            Ed = date.parse(N_end);

            long diff = Math.abs((int)(St.getTime()) - (int)(Ed.getTime()) );
            diffDate = diff / (24 * 60 * 60 * 1000);

        }catch (Exception e){
            e.printStackTrace();
        }

        tvStayPrice.setText((50000 * diffDate) + "원");

        Button btnCoupon = (Button) findViewById(R.id.couponBtn);

        btnCoupon.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(Payment.this,SelectCouponList.class);
                startActivity(Intent);

                tvCouponPrice.setText(couponPrice + "원");
                tvTotalPrice.setText( (50000 * diffDate ) - couponPrice + "원");
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
        toolbar.setTitle("결제 페이지");
        setSupportActionBar(toolbar);

        Spinner Spinner = (Spinner) findViewById(R.id.Spinner);
        ArrayAdapter Adapter = ArrayAdapter.createFromResource(this, R.array.payment,
                android.R.layout.simple_spinner_item);
        Spinner.setAdapter(Adapter);
    }


    public void connect(){
        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client,handler,Login.class);
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ClientThread.setRunningState(false);
        thread.interrupt();
    }
}