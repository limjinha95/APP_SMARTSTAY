package com.wap.smartstay;

import android.content.DialogInterface;
import android.content.Intent;
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

import org.json.JSONObject;

import java.net.Socket;
import java.util.ArrayList;

public class SmartKeyCallingList extends AppCompatActivity {
    Socket client;

    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;
    Thread thread;
    ClientThread clientThread;
    android.os.Handler handler;

    public static String phoneNumber;
    public static String officeCode;
    public static int number = 0;

    public static ArrayList<SmartkeyPopupListViewItem> smartkeyRoomList = new java.util.ArrayList<SmartkeyPopupListViewItem>();
    public static String smartKeyRoomInfo;
    public ArrayList<String> officeCodeList = new java.util.ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartkey_list_pop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("연락처 목록");

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        connect();

        JSONObject jo = new JSONObject();
        number = 1;

        try {
            jo.put("head", "MyKey");
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

        getWindow().setLayout((int) (width * .7), (int) (height * .3));

        final ListView listview;
        final SmartkeyPopupListViewAdapter adapter;

        adapter = new SmartkeyPopupListViewAdapter();

        listview = (ListView) findViewById(R.id.listview_smartkey);
        listview.setAdapter(adapter);

        for (int i = 0; i < smartkeyRoomList.size(); i++) {
            smartKeyRoomInfo = smartkeyRoomList.get(i).getSmartkeyRoomInfo();
            officeCode = smartkeyRoomList.get(i).getSmartkeyOfficeCode();

            adapter.addItem(smartKeyRoomInfo,officeCode);
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
        AlertDialog dialog = alertDialogBuilder.create();
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
        number = Uri.parse(phone);
        intent = new Intent(Intent.ACTION_DIAL, number);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClientThread.setRunningState(false);
        thread.interrupt();
    }

}