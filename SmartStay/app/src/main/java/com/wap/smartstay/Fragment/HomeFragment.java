package com.wap.smartstay.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.wap.smartstay.Manual;
import com.wap.smartstay.R;
import com.wap.smartstay.Reserve;

public class HomeFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        Button reserveBtn1 = (Button) view.findViewById(R.id.reserveBtn1);
        reserveBtn1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent manual = new Intent(getContext(), Reserve.class);
                        startActivity(manual);
                    }
                }
        );

        Button reserveBtn2 = (Button) view.findViewById(R.id.reserveBtn2);
        reserveBtn2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent manual = new Intent(getContext(), Reserve.class);
                        startActivity(manual);
                    }
                }
        );

        Button reserveBtn3 = (Button) view.findViewById(R.id.reserveBtn3);
        reserveBtn3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent manual = new Intent(getContext(), Reserve.class);
                        startActivity(manual);
                    }
                }
        );

        return view;
    }
}

