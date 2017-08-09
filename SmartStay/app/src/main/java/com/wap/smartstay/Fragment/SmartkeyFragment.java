package com.wap.smartstay.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.wap.smartstay.R;

public class SmartkeyFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.smartkey_fragment, container, false);

        // TODO: 2017. 7. 31. 스마트키 페이지 버튼 이벤트들 여기다가 추가
        LinearLayout manual = (LinearLayout) view.findViewById(R.id.manualLayout);
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manual = new Intent(getContext(), Manual.class);
                startActivity(manual);
            }
        });
        ImageButton manualBtn = (ImageButton) view.findViewById(R.id.manualBtn);
        manualBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent manual = new Intent(getContext(), Manual.class);
                        startActivity(manual);
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

        return view;
    }
}