package com.wap.smartstay;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;

public class CouponList extends AppCompatActivity {
    Context cont;
    Socket client;
    String ip = "13.124.213.57";
    int port = 9010;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    public static ArrayList<CouponListViewItem> couponList = new ArrayList<CouponListViewItem>();
    public static String couponName, couponInfo, couponDuty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("내 쿠폰함");

        connect();

        JSONObject jo = new JSONObject();

        try {
            jo.put("head","ReservationCheck");
            jo.put("ID", Login.Id);
        } catch (Exception e) {}

        String data = jo.toString();
        clientThread.send(data);
        Log.i("test","대기");

        ListView listview;
        CouponListViewAdapter adapter;

        // Adapter 생성
        adapter = new CouponListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
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
                    clientThread = new ClientThread(client,handler,Login.class);
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

}