package com.wap.smartstay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ReserveSpinnerAdapter extends BaseAdapter {
    Context context;
    int pic[];
    String[] roomType;
    LayoutInflater inflter;

    public ReserveSpinnerAdapter(Context applicationContext, int[] pic, String[] roomType) {
        this.context = applicationContext;
        this.pic = pic;
        this.roomType = roomType;

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return pic.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.reserve_spinner_item, null);
        ImageView icon = (ImageView) view.findViewById(R.id.pic);
        TextView information = (TextView) view.findViewById(R.id.information);

        icon.setImageResource(pic[i]);
        information.setText(roomType[i]);
        return view;
    }
}