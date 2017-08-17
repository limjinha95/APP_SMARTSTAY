package com.wap.smartstay;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;

public class CouponList extends AppCompatActivity {
    Socket client;
    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;

    Thread thread;
    ClientThread clientThread;
    Handler handler;
    boolean check=false;

    public static ArrayList<CouponListViewItem> couponList = new ArrayList<CouponListViewItem>();
    public static String couponName, couponInfo, couponDuty;
    public static boolean check2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("내 쿠폰함");

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        connect();

        JSONObject jo = new JSONObject();

        try {
            jo.put("head","MyCoupon");
            jo.put("ID", Login.Id);
        } catch (Exception e) {}

        String data = jo.toString();
        while(check==false);
        clientThread.send(data);
        while(check2==false);

        ListView listview;
        CouponListViewAdapter adapter;
        adapter = new CouponListViewAdapter();

        listview = (ListView) findViewById(R.id.listview_coupon);
        listview.setAdapter(adapter);

        for (int i = 0; i < couponList.size(); i++) {
            couponName = couponList.get(i).getCouponName();
            couponInfo = couponList.get(i).getCouponInfo();
            couponDuty = couponList.get(i).getCouponDuty();

            adapter.addItem(couponName, couponInfo, couponDuty);
        }
    }

    public void connect(){
        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client,handler,CouponList.class);
                    clientThread.start();
                    check=true;
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