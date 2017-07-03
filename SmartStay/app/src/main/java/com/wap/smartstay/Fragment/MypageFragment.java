package com.wap.smartstay.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wap.smartstay.Join;
import com.wap.smartstay.R;

public class MypageFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.usagelistBtn:
                        //Intent intent = new Intent(this, Join.class);
                        //startActivity(intent);
                        break;
                    case R.id.mycouponBtn:
                        //
                        break;
                    case R.id.alarmsetBtn:
                        //
                        break;
                    case R.id.inquireBtn:
                        //
                        break;
                }
            }
        };
//        Button usageListBtn = (Button) findViewById(R.id.usagelistBtn);
//        usageListBtn.setOnClickListener(onClickListener);
//        Button myCouponBtn = (Button) findViewById(R.id.mycouponBtn);
//        myCouponBtn.setOnClickListener(onClickListener);
//        Button alarmSetBtn = (Button) findViewById(R.id.alarmsetBtn);
//        alarmSetBtn.setOnClickListener(onClickListener);
//        Button inquireBtn = (Button) findViewById(R.id.inquireBtn);
//        inquireBtn.setOnClickListener(onClickListener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mypage_fragment, container, false);
    }

}
