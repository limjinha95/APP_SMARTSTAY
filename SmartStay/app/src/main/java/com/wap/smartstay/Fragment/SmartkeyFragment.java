package com.wap.smartstay.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.wap.smartstay.AddGroup;
import com.wap.smartstay.HttpConnection;
import com.wap.smartstay.Login;
import com.wap.smartstay.Manual;
import com.wap.smartstay.R;
import com.wap.smartstay.SmartKeyCallingList;
import com.wap.smartstay.SmartkeyPopupList;
import com.wap.smartstay.SmartkeyRoomAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SmartkeyFragment extends Fragment {
    public HttpConnection httpConnectionClient;


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
        SmartkeyRoomAdapter adapter;
        Spinner spinner = (Spinner) view.findViewById(R.id.reserveListSpinner);
        List<String> data = new ArrayList<>();
        String[] roomNames = {"1호", "2호", "3호"};
        for (String str : roomNames) {
            data.add(str);
        }


        adapter = new SmartkeyRoomAdapter(view.getContext(), data);
        spinner.setAdapter(adapter);

        try {
            JSONObject object = new JSONObject();
            object.put("head", "MyKey");
            object.put("ID", Login.Id);
            String sendData = object.toString();
            httpConnectionClient = new HttpConnection();
            httpConnectionClient.sendObject(sendData);


        } catch (Exception e) {
            e.printStackTrace();
        }


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
            public void onClick(View v) {
                Intent smartkey = new Intent(getContext(), SmartKeyCallingList.class);
                startActivity(smartkey);

            }
        });
        return view;
    }

}