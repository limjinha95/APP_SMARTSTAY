package com.wap.smartstay;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;

public class UsageList extends AppCompatActivity {
    Socket client;

    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    boolean check = false;

    TextView accomodationName;
    TextView reservationDuty;
    TextView accomodationInfo;

    public static ArrayList<ReserveListViewItem> reserveList = new ArrayList<ReserveListViewItem>() ;
    public static boolean check2=false;
    String name, duty, info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("이용 내역");

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        connect();

        accomodationName = (TextView) findViewById(R.id.reserveAccommodationName);
        reservationDuty = (TextView) findViewById(R.id.reserveAccommodationDuty);
        accomodationInfo = (TextView) findViewById(R.id.reserveAccommodationInfo);

        JSONObject jo = new JSONObject();

        try {
            jo.put("head","ReservationCheck");
            jo.put("ID", Login.Id);
        } catch (Exception e) {}

        String data = jo.toString();
        while(check == false);
        clientThread.send(data);
        while(check2 == false);

        ListView listview ;
        ReserveListViewAdapter adapter;
        adapter = new ReserveListViewAdapter() ;

        listview = (ListView) findViewById(R.id.listview_reserve);
        listview.setAdapter(adapter);

        Drawable img = ContextCompat.getDrawable(this, R.drawable.one);

        for (int i = 0; i < reserveList.size(); i++) {
            name = reserveList.get(i).getAccomodationName();
            duty = reserveList.get(i).getReservationDuty();
            info = reserveList.get(i).getAccomodationInfo();

            adapter.addItem(img, name, duty, info);
        }
    }

    public void connect(){
        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client,handler, UsageList.class);
                    clientThread.start();
                    check = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClientThread.setRunningState(false);
        thread.interrupt();
    }

}