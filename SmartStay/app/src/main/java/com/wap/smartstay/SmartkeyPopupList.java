package com.wap.smartstay;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("스마트키 방 목록");

        connect();
        JSONObject jo = new JSONObject();
        try {
            jo.put("head","MyKey");
            jo.put("ID", Login.Id);
            String data = jo.toString();
            clientThread.send(data);
        } catch (Exception e) {
            e.printStackTrace();
        }




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7), (int)(height*.3));

        ListView listview ;
        final SmartkeyPopupListViewAdapter adapter;

        adapter = new SmartkeyPopupListViewAdapter() ;

        listview = (ListView) findViewById(R.id.listview_smartkey);
        for (int i = 0; i < smartkeyRoomList.size(); i++) {
            smartKeyRoomInfo = smartkeyRoomList.get(i).getSmartkeyRoomInfo();
            adapter.addItem(smartKeyRoomInfo);
        }
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String json = adapter.getItem(position).toString();
                String [] datas = json.split(" ");
                JSONObject jo2 = new JSONObject();

                try {
                    jo2.put("head","Search_Room_Ip");
                    jo2.put("OfficeCode", datas[0]);
                    jo2.put("RoomNumber", datas[1]);
                } catch (Exception e) {}
                String data2 = jo2.toString();
                clientThread.send(data2);
            }
        });
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
        //ClientThread.setRunningState(false);
        thread.interrupt();
    }
}