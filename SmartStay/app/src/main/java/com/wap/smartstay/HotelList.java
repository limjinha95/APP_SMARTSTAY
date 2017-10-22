package com.wap.smartstay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class HotelList extends ListFragment {
    HotelListViewAdapter adapter;
    HttpConnection httpConnectionClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.hotel_list, container, false);

        ListView ListHotel = (ListView) View.findViewById(R.id.ListHotel);
        ListHotel.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) View.findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        adapter = new HotelListViewAdapter();
        try {
            JSONObject object = new JSONObject();
            object.put("head", "RoomList");
            String sendData = object.toString();
            httpConnectionClient = new HttpConnection();
            httpConnectionClient.sendObject(sendData);

            String receiveMsg = httpConnectionClient.receiveObject();
            JSONArray jsonArray = new JSONArray(receiveMsg);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dataJsonObject = (JSONObject) jsonArray.getJSONObject(i);
                String room_name = dataJsonObject.getString("room_name");
                String office_name = dataJsonObject.getString("office_name");
                String office_address = dataJsonObject.getString("office_address");
                String room_price = dataJsonObject.getString("cost");
                String room_type = dataJsonObject.getString("room_type");
                String office_code = dataJsonObject.getString("office_no");
                String room_number = dataJsonObject.getString("room_no");

                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.one)
                        , room_name
                        , office_name
                        , office_address
                        , room_price
                        , room_type
                        , office_code
                        ,room_number
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}