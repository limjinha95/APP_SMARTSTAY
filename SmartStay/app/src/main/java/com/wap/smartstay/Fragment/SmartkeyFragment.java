package com.wap.smartstay.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.wap.smartstay.AddGroup;
import com.wap.smartstay.HttpConnection;
import com.wap.smartstay.Login;
import com.wap.smartstay.Manual;
import com.wap.smartstay.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SmartkeyFragment extends Fragment {
    public HttpConnection httpConnectionClient;
    ImageButton manualBtn;
    LinearLayout manualBackground;
    ImageButton smartkeyBtn, smartLightBtn, addgroupBtn, callBtn;
    Spinner spinner;
    List<String> data = new ArrayList<>();
    String[] roomNames = {"1호", "2호", "3호"};
    String[] officeCode = {};
    String[] roomNumber = {};
    public static int roomChoice = 0;
    public static String phoneNumber = "";

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
        spinner = (Spinner) view.findViewById(R.id.reserveListSpinner);
        manualBtn = (ImageButton) view.findViewById(R.id.manualBtn);
        manualBackground = (LinearLayout) view.findViewById(R.id.manualBackground);
        smartkeyBtn = (ImageButton) view.findViewById(R.id.smartkeyBtn);
        smartLightBtn = (ImageButton) view.findViewById(R.id.smartlightBtn);
        addgroupBtn = (ImageButton) view.findViewById(R.id.addgroupBtn);
        callBtn = (ImageButton) view.findViewById(R.id.callBtn);
        try {
            JSONObject object = new JSONObject();
            object.put("head", "MyKey");
            object.put("ID", Login.Id);
            String sendData = object.toString();
            httpConnectionClient = new HttpConnection();
            httpConnectionClient.sendObject(sendData);
            String receiveMsg = httpConnectionClient.receiveObject();
            JSONArray jsonArray = new JSONArray(receiveMsg);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dataJsonObject = (JSONObject) jsonArray.getJSONObject(i);
                String smartKeyOfficeCode = dataJsonObject.getString("OFFICECODE");

                String roomName = dataJsonObject.getString("NAME");
                String roomNum = dataJsonObject.getString("RNUM");
                String smartKeyRoomInfo = roomName + roomNum;

//                String smartKeyRoomInfo = dataJsonObject.getString("NAME") + " " + dataJsonObject.getString("RNUM");

                roomNames[i] = smartKeyRoomInfo;
                officeCode[i] = smartKeyOfficeCode;
                roomNumber[i] = roomNum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String str : roomNames) {
            data.add(str);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,data);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                roomChoice = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        manualBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent manual = new Intent(getContext(), Manual.class);
                        startActivity(manual);
                    }
                }
        );

        manualBackground.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manual = new Intent(getContext(), Manual.class);
                startActivity(manual);
            }
        });


        smartkeyBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        SmartkeyOpenKey();
                    }
                }
        );

        smartLightBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "현재 준비 중인 서비스입니다.", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        addgroupBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent addgroup = new Intent(getContext(), AddGroup.class);
                        startActivity(addgroup);
                    }
                }
        );

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject object = new JSONObject();
                try {
                    object.put("head", "SearchOfficePnum");
                    object.put("OfficeCode", officeCode[roomChoice]);

                    String sendData = object.toString();
                    httpConnectionClient = new HttpConnection();
                    httpConnectionClient.sendObject(sendData);

                    String receiveMsg = httpConnectionClient.receiveObject();
                    JSONObject object2 = new JSONObject(receiveMsg);

                    String phone = object2.get("OfficePnum").toString();
                    phoneNumber = phone;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                callBtnEventDialog(roomNames[roomChoice]);
            }
        });


        return view;
    }

    public void SmartkeyOpenKey() {

        JSONObject object = new JSONObject();

        try {
            object.put("head", "Search_Room_Ip");
            object.put("OfficeCode", officeCode[roomChoice]);
            object.put("RoomNumber", roomNumber);

            String data = object.toString();
            httpConnectionClient = new HttpConnection();
            httpConnectionClient.sendObject(data);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void callBtnEventDialog(final String roomName) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("*" + roomName + "* Call")
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
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }

    public void calling() {
        String phone = "tel:" + phoneNumber;
        Uri number;
        Intent intent;
        number = Uri.parse(phone);
        phoneNumber = null;
        intent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(intent);

    }

}