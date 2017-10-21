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
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SmartkeyPopupList extends AppCompatActivity {
    Handler handler;
    Button cancel;
    ListView listview;
    SmartkeyPopupListViewAdapter adapter;

    boolean check = false;
    public static boolean check2 = false;

    public static ArrayList<SmartkeyPopupListViewItem> smartkeyRoomList = new ArrayList<SmartkeyPopupListViewItem>();
    public static String smartKeyRoomInfo;
    public static String smartKeyOfficeCode;
    HttpConnection httpConnectionClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartkey_list_pop);


        cancel = (Button) findViewById(R.id.cancel_action);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitle("스마트키 목록");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * .3));

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            JSONObject object = new JSONObject();
            object.put("head", "MyKey");
            object.put("ID", Login.Id);
            String data = object.toString();
            httpConnectionClient = new HttpConnection();
            httpConnectionClient.sendObject(data);
            String receiveMsg = httpConnectionClient.receiveObject();

            JSONArray jsonArray = new JSONArray(receiveMsg);
            SmartkeyPopupListViewItem item;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dataJsonObject = (JSONObject) jsonArray.getJSONObject(i);

                String smartKeyOfficeCode = dataJsonObject.getString("OFFICECODE");
                String smartKeyRoomInfo = dataJsonObject.getString("NAME") + " " + dataJsonObject.getString("RNUM");

                item = new SmartkeyPopupListViewItem();
                item.setSmartkeyRoomInfo(smartKeyRoomInfo);
                item.setSmartkeyOfficeCode(smartKeyOfficeCode);

                smartkeyRoomList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        adapter = new SmartkeyPopupListViewAdapter();

        listview = (ListView) findViewById(R.id.listview_smartkey);

        for (int i = 0; i < smartkeyRoomList.size(); i++) {
            smartKeyRoomInfo = smartkeyRoomList.get(i).getSmartkeyRoomInfo();
            smartKeyOfficeCode = smartkeyRoomList.get(i).getSmartkeyOfficeCode();
            adapter.addItem(smartKeyRoomInfo, smartKeyOfficeCode);
        }
        listview.setAdapter(adapter);
        SmartkeyPopupEvent();

    }

    public void SmartkeyPopupEvent() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SmartkeyPopupListViewItem obj = adapter.getItem(position);
                String[] datas = obj.getSmartkeyRoomInfo().split(" ");
                JSONObject object = new JSONObject();

                try {
                    object.put("head", "Search_Room_Ip");
                    object.put("OfficeCode", obj.getSmartkeyOfficeCode());
                    object.put("RoomNumber", datas[1]);
                } catch (Exception e) {
                }
                String data = object.toString();
                httpConnectionClient = new HttpConnection();
                httpConnectionClient.sendObject(data);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}