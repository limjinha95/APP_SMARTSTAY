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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SmartKeyCallingList extends AppCompatActivity {
    android.os.Handler handler;

    String phoneNumber, officeCode;
    Button cancel;
    ListView listview;
    SmartKeyCallingListViewAdapter adapter;

    public static ArrayList<SmartKeyCallingListViewItem> smartCallingRoomList = new ArrayList<SmartKeyCallingListViewItem>();
    public static String smartKeyRoomInfo;
    public ArrayList<String> officeCodeList = new ArrayList<String>();
    HttpConnection httpConnectionClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.smartkey_list_pop);

        listview = (ListView) findViewById(R.id.listview_smartkey);
        cancel = (Button) findViewById(R.id.cancel_action);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitle("연락처 목록");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * .3));


        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        JSONObject jo = new JSONObject();
        try {
            jo.put("head", "MyKey");
            jo.put("ID", Login.Id);
            String data = jo.toString();
            httpConnectionClient = new HttpConnection();
            httpConnectionClient.sendObject(data);
            String receiveMsg = httpConnectionClient.receiveObject();

            JSONArray jsonArray = new JSONArray(receiveMsg);

            SmartKeyCallingListViewItem item;
            for (int i = 0; i < jsonArray.length(); i++) {
                item = new SmartKeyCallingListViewItem();
                JSONObject dataJsonObject = (JSONObject) jsonArray.getJSONObject(i);
                String smartKeyRoomInfo = dataJsonObject.getString("NAME") + " " + dataJsonObject.getString("RNUM");
                String smartKeyOfficeCode = dataJsonObject.getString("OFFICECODE");
                item.setSmartCallingRoomInfo(smartKeyRoomInfo);
                item.setSmartCallingOfficeCode(smartKeyOfficeCode);
                smartCallingRoomList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        adapter = new SmartKeyCallingListViewAdapter();
        listview.setAdapter(adapter);

        for (int i = 0; i < smartCallingRoomList.size(); i++) {
            smartKeyRoomInfo = smartCallingRoomList.get(i).getSmartCallingRoomInfo();
            officeCode = smartCallingRoomList.get(i).getSmartCallingOfficeCode();
            adapter.addItem(smartKeyRoomInfo, officeCode);
            officeCodeList.add(officeCode);
        }


        SmartKeyCallingEvent();
    }

    public void SmartKeyCallingEvent() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SmartKeyCallingListViewItem obj = adapter.getItem(position);
                String[] datas = obj.getSmartCallingRoomInfo().split(" ");
                JSONObject object = new JSONObject();
                try {
                    object.put("head", "SearchOfficePnum");
                    object.put("OfficeCode", obj.getSmartCallingOfficeCode());

                    String sendData = object.toString();
                    httpConnectionClient = new HttpConnection();
                    httpConnectionClient.sendObject(sendData);

                    String receiveMsg = httpConnectionClient.receiveObject();
                    object = new JSONObject(receiveMsg);

                    String phone = object.get("OfficePnum").toString();
                    phoneNumber = phone;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                callBtnEventDialog(datas[0]);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void callBtnEventDialog(final String roomName) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("*" + roomName + "* Call")
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
}