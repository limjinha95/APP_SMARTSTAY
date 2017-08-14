package com.wap.smartstay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class CouponList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("내 쿠폰함");

        ListView listview;
        CouponListViewAdapter adapter;

        // Adapter 생성
        adapter = new CouponListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_coupon);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("신규가입회원 축하쿠폰", "Account Box Black 36dp","Account Box Black 36dp") ;

    }
}