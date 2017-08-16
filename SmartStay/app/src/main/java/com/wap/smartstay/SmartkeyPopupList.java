package com.wap.smartstay;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;

public class SmartkeyPopupList extends AppCompatActivity {
    Context cont;
    Socket client;
    String ip = "13.124.213.57";
    int port = 9010;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    public static ArrayList<SmartkeyPopupListViewItem> smartkeyRoomList = new java.util.ArrayList<SmartkeyPopupListViewItem>() ;
    public static String smartKeyRoomInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartkey_list_pop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("스마트키 방 목록");

        connect();

        JSONObject jo = new JSONObject();

        try {
            jo.put("head","ReservationCheck");
            jo.put("ID", Login.Id);
        } catch (Exception e) {}

        String data = jo.toString();
        clientThread.send(data);
        Log.i("test","대기");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7), (int)(height*.3));

        ListView listview ;
        SmartkeyPopupListViewAdapter adapter;

        // Adapter 생성
        adapter = new SmartkeyPopupListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_smartkey);
        listview.setAdapter(adapter);

        for (int i = 0; i < smartkeyRoomList.size(); i++) {
            smartKeyRoomInfo = smartkeyRoomList.get(i).getSmartkeyRoomInfo();
            adapter.addItem(smartKeyRoomInfo);
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