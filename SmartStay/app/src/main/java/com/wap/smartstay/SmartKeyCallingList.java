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
import android.widget.Button;
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

    public static int number = 1;
    boolean check = false;
    public static boolean check2 = false;

    public static ArrayList<SmartKeyCallingListViewItem> smartCallingRoomList = new ArrayList<SmartKeyCallingListViewItem>();
    public static String smartKeyRoomInfo;
    public ArrayList<String> officeCodeList = new ArrayList<String>();

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

        final ListView listview;
        final SmartKeyCallingListViewAdapter adapter;

        adapter = new SmartKeyCallingListViewAdapter();

        listview = (ListView) findViewById(R.id.listview_smartkey);
        listview.setAdapter(adapter);

        for (int i = 0; i < smartCallingRoomList.size(); i++) {
            smartKeyRoomInfo = smartCallingRoomList.get(i).getSmartCallingRoomInfo();
            officeCode = smartCallingRoomList.get(i).getSmartCallingOfficeCode();
            adapter.addItem(smartKeyRoomInfo,officeCode);
            officeCodeList.add(officeCode);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                number = 2;
                callBtnEventDialog();
            }
        });

    }

    public void callBtnEventDialog() {
                SmartKeyCallingListViewItem obj = adapter.getItem(position);
                String[] datas = obj.getSmartCallingRoomInfo().split(" ");

                JSONObject jo2 = new JSONObject();
                try {
                    jo2.put("head", "SearchOfficePnum");
                    jo2.put("OfficeCode", obj.getSmartCallingOfficeCode());
                } catch (Exception e) {
                }
                String data2 = jo2.toString();
                clientThread.send(data2);
                callBtnEventDialog(datas[0]);
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void callBtnEventDialog(final String roomName) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("*" +roomName + "* Call")
                .setMessage("전화를 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whichButton) {
                        calling();
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

    public void calling() {
        String phone = "tel:" + phoneNumber;
        Uri number;
        Intent intent;
        number = Uri.parse(phone);
        phoneNumber = null;
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