package com.wap.smartstay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wap.smartstay.HotelListViewAdapter;

public class HotelList extends ListFragment {
    HotelListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.hotel_list, container, false);

        ListView ListHotel = (ListView)View.findViewById(R.id.ListHotel);
        ListHotel.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) View.findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));

        adapter = new HotelListViewAdapter();

        setListAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.one),
                "부경 대연 호텔", "부산광역시 대연3동") ;
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.three),
                "부경 용당 호텔", "부산광역시") ;
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.one),
                "부경 호텔", "부산광역시 대연동") ;
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.three),
                "부경 대연 호텔", "부산광역시 대연3동") ;
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}