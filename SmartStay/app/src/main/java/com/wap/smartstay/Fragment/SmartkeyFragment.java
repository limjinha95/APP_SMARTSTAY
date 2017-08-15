package com.wap.smartstay.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wap.smartstay.AddGroup;
import com.wap.smartstay.ClientThread;
import com.wap.smartstay.Login;
import com.wap.smartstay.Manifest;
import com.wap.smartstay.Manual;
import com.wap.smartstay.R;

import java.io.IOException;
import java.net.Socket;

public class SmartkeyFragment extends Fragment {
    Socket client;
    String ip = "192.168.43.179";
    int port = 4040;
    Thread thread;
    ClientThread clientThread;
    Handler handler;

    public static String phoneNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        connect();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.smartkey_fragment, container, false);

        /** 매뉴얼 이미지 버튼을 눌렀을 때 이벤트*/
        ImageButton manualBtn = (ImageButton) view.findViewById(R.id.manualBtn);
        manualBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent manual = new Intent(getContext(), Manual.class);
                        startActivity(manual);
                    }
                }
        );

        /** 매뉴얼 배경 눌렀을때도 넘어가게 하기위해서 이벤트 적용*/
        LinearLayout manualBackground = (LinearLayout) view.findViewById(R.id.manualBackground);
        manualBackground.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manual = new Intent(getContext(), Manual.class);
                startActivity(manual);
            }
        });
        /** 동숙객 추가 버튼을 눌렀을 때 이벤트*/
        ImageButton addgroupBtn = (ImageButton) view.findViewById(R.id.addgroupBtn);
        addgroupBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent addgroup = new Intent(getContext(), AddGroup.class);
                        startActivity(addgroup);
                    }
                }
        );


        /** 전화 버튼 눌렀을 때 이벤트  */
        ImageButton callBtn = (ImageButton) view.findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBtnEventDialog();
            }
        });
        return view;
    }

    public void callBtnEventDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("Call")
                .setMessage("전화를 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whichButton) {
                        calling();
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int whichButton) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = alertDialogBuilder.create(); //알림 창 객체 생성
        dialog.show();
    }

    public void calling() {
        clientThread.send("SearchOfficePnum");
        String phone = "tel:" + phoneNumber;

        Uri number;
        Intent intent;
        number = Uri.parse(phone); // 번호 수정해주시면 됩니다.
        intent = new Intent(Intent.ACTION_DIAL, number); // ACTION_CALL : 바로걸기
        startActivity(intent);
    }

    public void connect() {
        thread = new Thread() {
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client, handler, SmartkeyFragment.class);
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

}