package com.wap.smartstay;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;

public class UsageList extends AppCompatActivity {
    Context cont;
    Socket client;
    String ip = "13.124.213.57";
    int port = 9010;
    Thread thread;
    ClientThread clientThread;
    Handler handler;
    boolean check=false;
    TextView accomodationName;
    TextView accomodationDuty;
    TextView accomodationInfo;

    public static ArrayList<ReserveListViewItem> reserveList = new ArrayList<ReserveListViewItem>() ;
    public static boolean check2=false;
    String officeCode, roomNumber, reservationDuty, roomInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_list);

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        connect();
        Log.i("1","1");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("이용 내역");
        Log.i("2","1");
        accomodationName = (TextView) findViewById(R.id.reserveAccommodationName);
        accomodationDuty = (TextView) findViewById(R.id.reserveAccommodationDuty);
        accomodationInfo = (TextView) findViewById(R.id.reserveAccommodationInfo);
        Log.i("3","1");
        JSONObject jo = new JSONObject();
        Log.i("4","1");
        try {
            jo.put("head","ReservationCheck");
            jo.put("ID", Login.Id);
        } catch (Exception e) {}
        Log.i("5","1");
        String data = jo.toString();
        while(check==false);
        clientThread.send(data);
        Log.i("test","대기");
        while(check2==false);
        ListView listview ;
        ReserveListViewAdapter adapter;
        Log.i("6","1");
        // Adapter 생성
        adapter = new ReserveListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_reserve);
        listview.setAdapter(adapter);

        Drawable img = ContextCompat.getDrawable(this, R.drawable.one);

        for (int i = 0; i < reserveList.size(); i++) {
            officeCode = reserveList.get(i).getAccomodationOfficeCode();
            roomNumber = reserveList.get(i).getAccomodationRoomnumber();
            reservationDuty = reserveList.get(i).getReservationDuty();
            roomInfo = reserveList.get(i).getAccomodationInfo();

            adapter.addItem(img, officeCode,roomNumber, reservationDuty, roomInfo);
        }
    }

    public void connect(){

        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client,handler,UsageList.class);
                    clientThread.start();
                    check=true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }


}