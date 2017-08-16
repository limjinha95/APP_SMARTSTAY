package com.wap.smartstay;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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


public class SmartKeyCallingList extends AppCompatActivity{
    Context cont;
    Socket client;
    String ip = "13.124.213.57";
    int port = 9010;
    Thread thread;
    ClientThread clientThread;
    android.os.Handler handler;

    public static String phoneNumber;
    public static String officeCode;

    public static ArrayList<SmartkeyPopupListViewItem> smartkeyRoomList = new java.util.ArrayList<SmartkeyPopupListViewItem>() ;
    public static String smartKeyRoomInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartkey_list_pop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("스마트키 방 목록");

//        connect();

//        JSONObject jo = new JSONObject();
//
//        try {
//            jo.put("head","ReservationCheck");
//            jo.put("ID", Login.Id);
//        } catch (Exception e) {}
//
//        String data = jo.toString();
//        clientThread.send(data);
//        Log.i("test","대기");
//
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7), (int)(height*.3));

        final ListView listview ;
        final SmartkeyPopupListViewAdapter adapter;

        // Adapter 생성
        adapter = new SmartkeyPopupListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_smartkey);
        listview.setAdapter(adapter);
        smartKeyRoomInfo = "1호";
        adapter.addItem(smartKeyRoomInfo);

        smartKeyRoomInfo = "2호";
        adapter.addItem(smartKeyRoomInfo);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ListView listView = (ListView) adapterView;
                SmartkeyPopupListViewItem item = (SmartkeyPopupListViewItem) listView.getItemAtPosition(position);
                String roomNum = item.getSmartkeyRoomInfo();
                callBtnEventDialog(roomNum);
            }
        });


//        for (int i = 0; i < smartkeyRoomList.size(); i++) {
//            smartKeyRoomInfo = smartkeyRoomList.get(i).getSmartkeyRoomInfo();
//            adapter.addItem(smartKeyRoomInfo);
//        }

    }


    public void callBtnEventDialog(final String roomInformation) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Call")
                .setMessage(roomInformation + " 전화를 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whichButton) {
                        calling(roomInformation);
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

    public void calling(String roomName) {
        JSONObject jo = new JSONObject();

        try{
            jo.put("head","SearchOfficePnum");
            jo.put("OfficeCode", roomName);

        }catch (Exception e) {
            e.printStackTrace();
        }
        String data = jo.toString();

        clientThread.send(data);


        String phone = "tel:" + phoneNumber;
        Log.e("error", phone);


        Uri number;
        Intent intent;
        number = Uri.parse(phone); // 번호 수정해주시면 됩니다.
        intent = new Intent(Intent.ACTION_DIAL, number); // ACTION_CALL : 바로걸기
        startActivity(intent);
    }

}