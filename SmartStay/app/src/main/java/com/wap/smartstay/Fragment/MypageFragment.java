package com.wap.smartstay.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wap.smartstay.AlarmSet;
import com.wap.smartstay.CouponList;
import com.wap.smartstay.Inquire;
import com.wap.smartstay.Login;
import com.wap.smartstay.MyInfo;
import com.wap.smartstay.R;
import com.wap.smartstay.UsageList;

public class MypageFragment extends Fragment {
    TextView name, id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mypage_fragment, container, false);
        id = (TextView) view.findViewById(R.id.mypageUserId);
        name = (TextView) view.findViewById(R.id.mypageUserName);
        ImageButton mypageMyInfoBtn = (ImageButton) view.findViewById(R.id.mypageMyInfoBtn);
        mypageMyInfoBtn.setEnabled(false);
        if (Login.Islogin == 1) {
            id.setText(Login.Id);
            name.setText(Login.Name);
            mypageMyInfoBtn.setEnabled(true);
        }
        // TODO: 2017. 7. 31. 마이페이지 버튼 이벤트들 여기다가 추가

        mypageMyInfoBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent mypageMyInfo = new Intent(getContext(), MyInfo.class);
                        startActivity(mypageMyInfo);
                    }
                }
        );

        Button usagelistBtn = (Button) view.findViewById(R.id.usagelistBtn);
        usagelistBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent usagelist = new Intent(getContext(), UsageList.class);
                        startActivity(usagelist);
                    }
                }
        );

        Button mycouponBtn = (Button) view.findViewById(R.id.mycouponBtn);
        mycouponBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent mycoupon = new Intent(getContext(), CouponList.class);
                        startActivity(mycoupon);
                    }
                }
        );

        Button alarmsetBtn = (Button) view.findViewById(R.id.alarmsetBtn);
        alarmsetBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent alarmset = new Intent(getContext(), AlarmSet.class);
                        startActivity(alarmset);
                    }
                }
        );

        Button inquireBtn = (Button) view.findViewById(R.id.inquireBtn);
        inquireBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent inquire = new Intent(getContext(), Inquire.class);
                        startActivity(inquire);
                    }
                }
        );

        return view;
    }

}