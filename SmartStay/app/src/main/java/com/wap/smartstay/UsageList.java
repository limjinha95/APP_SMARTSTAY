package com.wap.smartstay;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class UsageList extends AppCompatActivity {
    TextView accomodationName;
    TextView reservationDuty;
    TextView accomodationInfo;

    public static ArrayList<ReserveListViewItem> reserveList = new ArrayList<ReserveListViewItem>();
    String name, duty, info;

    HttpConnection httpConnectionClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitle("이용 내역");

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        accomodationName = (TextView) findViewById(R.id.reserveAccommodationName);
        reservationDuty = (TextView) findViewById(R.id.reserveAccommodationDuty);
        accomodationInfo = (TextView) findViewById(R.id.reserveAccommodationInfo);


        try {
            JSONObject object = new JSONObject();
            object.put("head", "ReservationCheck");
            object.put("ID", Login.Id);
            String data = object.toString();
            httpConnectionClient = new HttpConnection();
            httpConnectionClient.sendObject(data);
            String receiveMsg = httpConnectionClient.receiveObject();

            JSONArray jsonArray = new JSONArray(receiveMsg);
//            ReserveListViewItem item;

            Log.e("UsageList", receiveMsg);
            Log.e("UsageList", jsonArray.length() + "");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dataJsonObject = (JSONObject) jsonArray.getJSONObject(i);
                Log.e("aa","aa");
                Log.e("aa",dataJsonObject.toString());
                String roomName = dataJsonObject.getString("office_no") + " " + dataJsonObject.getString("room_no") + " " + dataJsonObject.getString("room_name") + " " + dataJsonObject.getString("office_name");
                Log.e("aa",dataJsonObject.getString("office_no") + " " + dataJsonObject.getString("room_no") + " " + dataJsonObject.getString("room_name") + " " + dataJsonObject.getString("office_name"));
                String reservationDuty = dataJsonObject.getString("start_date") + "~" + dataJsonObject.getString("end_date");
                String roomInfo = "기준 " + dataJsonObject.getString("standard_num") + "인 / 최대 " + dataJsonObject.getString("maximum_num") + "인";
                ReserveListViewItem item = new ReserveListViewItem();

                item.setAccomodationName(roomName);
                item.setReservationDuty(reservationDuty);
                item.setAccomodationInfo(roomInfo);
                reserveList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListView listview;
        ReserveListViewAdapter adapter;
        adapter = new ReserveListViewAdapter();

        listview = (ListView) findViewById(R.id.listview_reserve);
        listview.setAdapter(adapter);

        Drawable img = ContextCompat.getDrawable(this, R.drawable.one);

        for (int i = 0; i < reserveList.size(); i++) {
            name = reserveList.get(i).getAccomodationName();
            duty = reserveList.get(i).getReservationDuty();
            info = reserveList.get(i).getAccomodationInfo();

            adapter.addItem(img, name, duty, info);
        }
    }
}