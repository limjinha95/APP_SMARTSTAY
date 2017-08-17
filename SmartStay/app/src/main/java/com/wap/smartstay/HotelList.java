package com.wap.smartstay;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wap.smartstay.HotelListViewAdapter;

import java.net.Socket;
import java.util.ArrayList;

public class HotelList extends ListFragment {
    Socket client;
    String ip = ServerInformation.serverIP;
    int port = ServerInformation.port;

    Thread thread;
    ClientThread clientThread;
    Handler handler;

    public static ArrayList<HotelListViewItem> HotelList = new ArrayList<HotelListViewItem>();
    public static String HotelName, HotelLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.hotel_list, container, false);

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) View.findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));

        HotelListViewAdapter adapter;

        adapter = new HotelListViewAdapter();

        ListView ListHotel = (ListView)View.findViewById(R.id.ListHotel);
        ListHotel.setAdapter(adapter);

        for (int i = 0; i < HotelList.size(); i++) {
            HotelName = HotelList.get(i).getName();
            HotelLocation = HotelList.get(i).getLocation();

            adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.one),
                    HotelName, HotelLocation) ;
        }

        return super.onCreateView(inflater, container, savedInstanceState);

    }


    public void connect(){
        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);
                    clientThread = new ClientThread(client,handler,Login.class);
                    clientThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        ClientThread.setRunningState(false);
        thread.interrupt();
    }
}