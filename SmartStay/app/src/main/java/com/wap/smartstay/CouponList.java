package com.wap.smartstay;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CouponList extends AppCompatActivity {
    Handler handler;
    public static ArrayList<CouponListViewItem> couponList = new ArrayList<CouponListViewItem>();
    public static String couponName, couponInfo, couponDuty;
    boolean check2 = false;
    HttpConnection httpConnectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("내 쿠폰함");

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //쿠폰데이터 가져오기
        JSONObject object = new JSONObject();

        try {
            object.put("head", "MyCoupon");
            object.put("ID", Login.Id);

            String sendData = object.toString();
            httpConnectionClient = new HttpConnection();
            httpConnectionClient.sendObject(sendData);

            String receiveMsg = httpConnectionClient.receiveObject();
            JSONArray jsonArray = new JSONArray(receiveMsg);
            CouponListViewItem item;
            check2 = false;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dataJsonObject = (JSONObject) jsonArray.getJSONObject(i);

                String couponName = dataJsonObject.getString("NAME");
                String couponInfo = dataJsonObject.getString("INFO");
                String couponDuty = dataJsonObject.getString("STARTDATE") + "~" + dataJsonObject.getString("ENDDATE");

                item = new CouponListViewItem();

                item.setCouponName(couponName);
                item.setCouponInfo(couponInfo);
                item.setCouponDuty(couponDuty);

                couponList.add(item);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



        ListView listview;
        CouponListViewAdapter adapter;
        adapter = new CouponListViewAdapter();

        listview = (ListView) findViewById(R.id.listview_coupon);
        listview.setAdapter(adapter);

        for (int i = 0; i < couponList.size(); i++) {
            couponName = couponList.get(i).getCouponName();
            couponInfo = couponList.get(i).getCouponInfo();
            couponDuty = couponList.get(i).getCouponDuty();

            adapter.addItem(couponName, couponInfo, couponDuty);
        }

    }

}