package com.wap.smartstay;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by ihyeon-yeong on 2017. 8. 16..
 */


public class SmartKeyCallingList extends AppCompatActivity {
    Context cont;
    Socket client;
    String ip = "13.124.213.57";
    int port = 9010;
    Thread thread;
    ClientThread clientThread;
    android.os.Handler handler;

    public static String phoneNumber;
    public static String officeCode;
    public static int number = 0;
    public static ArrayList<SmartkeyPopupListViewItem> smartkeyRoomList = new java.util.ArrayList<SmartkeyPopupListViewItem>();
    public static ArrayList<String> pnumList = new java.util.ArrayList<String>();
    public static String smartKeyRoomInfo, officePnum;
    public ArrayList<String> officeCodeList = new java.util.ArrayList<String>();

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
        number = 1;
        try {
            jo.put("head", "ReservationCheck");
            jo.put("ID", Login.Id);
            String data = jo.toString();
            clientThread.send(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("test", "대기");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * .3));

        final ListView listview;
        final SmartkeyPopupListViewAdapter adapter;

        // Adapter 생성
        adapter = new SmartkeyPopupListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_smartkey);
        listview.setAdapter(adapter);


//        smartKeyRoomInfo = "1호";
//        adapter.addItem(smartKeyRoomInfo);
//
//        smartKeyRoomInfo = "2호";
//        adapter.addItem(smartKeyRoomInfo);

        for (int i = 0; i < smartkeyRoomList.size(); i++) {
            smartKeyRoomInfo = smartkeyRoomList.get(i).getSmartkeyRoomInfo();
            officeCode = smartkeyRoomList.get(i).getSmartkeyOfficeCode();
            adapter.addItem(smartKeyRoomInfo);
            officeCodeList.add(officeCode);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String office = officeCodeList.get(position);
                callBtnEventDialog(office);
            }
        });


    }


    public void callBtnEventDialog(final String office1) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Call")
                .setMessage("전화를 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whichButton) {
                        calling(office1);
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int whichButton) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = alertDialogBuilder.create(); //알림 창 객체 생성
        dialog.show();
    }

    public void calling(String officeCode) {
        number = 2;
        JSONObject jo = new JSONObject();

        try {
            jo.put("head", "SearchOfficePnum");
            jo.put("OfficeName", officeCode);
            String data = jo.toString();
            clientThread.send(data);
        } catch (Exception e) {
            e.printStackTrace();
        }


        String phone = "tel:" + phoneNumber;
        Log.e("error : ", phone);

        Uri number;
        Intent intent;
        number = Uri.parse(phone); // 번호 수정해주시면 됩니다.
        intent = new Intent(Intent.ACTION_DIAL, number); // ACTION_CALL : 바로걸기
        startActivity(intent);
    }


    public void connect() {

        thread = new Thread() {
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client, handler, SmartKeyCallingList.class);
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

}