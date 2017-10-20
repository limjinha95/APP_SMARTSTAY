package com.wap.smartstay.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wap.smartstay.AddGroup;
import com.wap.smartstay.Manual;
import com.wap.smartstay.R;
import com.wap.smartstay.SmartKeyCallingList;
import com.wap.smartstay.SmartkeyPopupList;

import java.util.ArrayList;
import java.util.List;

public class SmartkeyFragment extends Fragment {
    private RecyclerView recyclerView;
    private String[] hotelNames;
    private String[] roomNames;
    private TypedArray roomPics;
    private List<SmartkeyList> SmartkeyList;
    private static Context context = null;

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

        context=getActivity();

        hotelNames = getResources().getStringArray(R.array.hotelNames);
        roomNames = getResources().getStringArray(R.array.roomNames);
        roomPics = getResources().obtainTypedArray(R.array.roomPics);

        SmartkeyList = new ArrayList<SmartkeyList>();
        for(int i=0; i<roomNames.length; i++){
            SmartkeyList smartkeyList = new SmartkeyList(hotelNames[i], roomNames[i], roomPics.getResourceId(i,-1));
            SmartkeyList.add(smartkeyList);
        }

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SmartkeyFragment.context);
        recyclerView.setLayoutManager(layoutManager);
        SmartkeyFragmentAdapter adapter = new SmartkeyFragmentAdapter(SmartkeyList,SmartkeyFragment.context);
        recyclerView.setAdapter(adapter);

        LinearLayout manualBackground = (LinearLayout) view.findViewById(R.id.manualBackground);
        manualBackground.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manual = new Intent(getContext(), Manual.class);
                startActivity(manual);
            }
        });

//        ImageButton smartkeyBtn = (ImageButton) view.findViewById(R.id.smartkeyBtn);
//        smartkeyBtn.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                    }
//                }
//        );
//
//        ImageButton addgroupBtn = (ImageButton) view.findViewById(R.id.addgroupBtn);
//        addgroupBtn.setOnClickListener(
//                new Button.OnClickListener() {
//                    public void onClick(View v) {
//                        Intent addgroup = new Intent(getContext(), AddGroup.class);
//                        startActivity(addgroup);
//                    }
//                }
//        );
//
//        ImageButton callBtn = (ImageButton) view.findViewById(R.id.callBtn);
//        callBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
        return view;
    }

}