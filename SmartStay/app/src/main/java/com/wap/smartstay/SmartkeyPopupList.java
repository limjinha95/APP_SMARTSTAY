package com.wap.smartstay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.ListView;

public class SmartkeyPopupList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartkey_list_pop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setTitle("스마트키 방 목록");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7), (int)(height*.3));

        ListView listview ;
        SmartkeyPopupListViewAdapter adapter;

        // Adapter 생성
        adapter = new SmartkeyPopupListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview_smartkey);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem("지니하우스 101호") ;
        adapter.addItem("지니하우스 102호") ;
        adapter.addItem("지니하우스 201호") ;
        adapter.addItem("지니하우스 201호") ;
        adapter.addItem("지니하우스 201호") ;

    }


}