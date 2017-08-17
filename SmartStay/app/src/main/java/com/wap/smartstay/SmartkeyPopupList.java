package com.wap.smartstay;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;

public class SmartkeyPopupList extends AppCompatActivity {
    Socket client;
    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    boolean check = false;
    public static boolean check2 = false;

    public static ArrayList<SmartkeyPopupListViewItem> smartkeyRoomList = new java.util.ArrayList<SmartkeyPopupListViewItem>();
    public static String smartKeyRoomInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartkey_list_pop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("스마트키 목록");

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        connect();
        JSONObject jo = new JSONObject();

        try {
            jo.put("head", "MyKey");
            jo.put("ID", Login.Id);
            String data = jo.toString();

            while (check == false) ;

            clientThread.send(data);

            while (check2 == false) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * .3));

        ListView listview;
        final SmartkeyPopupListViewAdapter adapter;
        adapter = new SmartkeyPopupListViewAdapter();

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
                String[] datas = json.split(" ");
                JSONObject jo2 = new JSONObject();

                try {
                    jo2.put("head", "Search_Room_Ip");
                    jo2.put("OfficeCode", datas[0]);
                    jo2.put("RoomNumber", datas[1]);
                } catch (Exception e) {
                }
                String data2 = jo2.toString();
                clientThread.send(data2);
            }
        });
    }

    public void connect() {
        thread = new Thread() {
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client, handler, SmartkeyPopupList.class);
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