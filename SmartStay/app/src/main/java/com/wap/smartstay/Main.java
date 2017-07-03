package com.wap.smartstay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.wap.smartstay.Fragment.HomeFragment;
import com.wap.smartstay.Fragment.MypageFragment;
import com.wap.smartstay.Fragment.SmartkeyFragment;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_screen_lock_portrait_black_24dp,
            R.drawable.ic_person_outline_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);

        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(

                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.Home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.Key:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.Mypage:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 한 줄 코드
        getMenuInflater().inflate(R.menu.item_tool, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.local_main:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                return true;
            case R.id.login_main:
                Intent intent2 = new Intent(this, Login.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new SmartkeyFragment(), "SmartKey");
        adapter.addFragment(new MypageFragment(), "Mypage");
        viewPager.setAdapter(adapter);
    }

}