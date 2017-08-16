package com.wap.smartstay;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.net.Socket;

public class UsageList extends AppCompatActivity {
    Context cont;
    Socket client;
    String ip = "13.124.213.57";
    int port = 9010;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    TextView accomodationName;
    TextView accomodationDuty;
    TextView accomodationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("이용 내역");

        accomodationName = (TextView) findViewById(R.id.reserveAccommodationName);
        accomodationDuty = (TextView) findViewById(R.id.reserveAccommodationDuty);
        accomodationInfo = (TextView) findViewById(R.id.reserveAccommodationInfo);

        ListView listview ;
        ReserveListViewAdapter adapter;

        // Adapter 생성
        adapter = new ReserveListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_reserve);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.one),
                "Box", "Account Box Black 36dp","Account Box Black 36dp") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.two),
                "Box", "Account Box Black 36dp","Account Box Black 36dp") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.two),
                "Box", "Account Box Black 36dp","Account Box Black 36dp") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.two),
                "Box", "Account Box Black 36dp","Account Box Black 36dp") ;

    }


}