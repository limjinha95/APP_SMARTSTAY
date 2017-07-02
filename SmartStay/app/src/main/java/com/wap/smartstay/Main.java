package com.wap.smartstay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(Color.parseColor("#000000")); //제목의
        toolbar.setNavigationIcon(R.drawable.ic_app_logo); //제목앞에 아이콘 넣기
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new SmartkeyFragment(), "SmartKey");
        adapter.addFragment(new MypageFragment(), "Mypage");
        viewPager.setAdapter(adapter);
    }
}