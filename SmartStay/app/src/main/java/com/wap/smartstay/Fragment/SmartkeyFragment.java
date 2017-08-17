package com.wap.smartstay.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.wap.smartstay.AddGroup;
import com.wap.smartstay.Manual;
import com.wap.smartstay.R;
import com.wap.smartstay.SmartKeyCallingList;
import com.wap.smartstay.SmartkeyPopupList;

public class SmartkeyFragment extends Fragment {
    public static String phoneNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.smartkey_fragment, container, false);

        ImageButton manualBtn = (ImageButton) view.findViewById(R.id.manualBtn);
        manualBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent manual = new Intent(getContext(), Manual.class);
                        startActivity(manual);
                    }
                }
        );

        LinearLayout manualBackground = (LinearLayout) view.findViewById(R.id.manualBackground);
        manualBackground.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manual = new Intent(getContext(), Manual.class);
                startActivity(manual);
            }
        });

        ImageButton smartkeyBtn = (ImageButton) view.findViewById(R.id.smartkeyBtn);
        smartkeyBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent smartkey = new Intent(getContext(), SmartkeyPopupList.class);
                        startActivity(smartkey);
                    }
                }
        );

        ImageButton addgroupBtn = (ImageButton) view.findViewById(R.id.addgroupBtn);
        addgroupBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent addgroup = new Intent(getContext(), AddGroup.class);
                        startActivity(addgroup);
                    }
                }
        );

        ImageButton callBtn = (ImageButton) view.findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loomInfo = new Intent(getContext(), SmartKeyCallingList.class);
                startActivity(loomInfo);
            }
        });
        return view;
    }

}