package com.wap.smartstay;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReserveListViewAdapter extends BaseAdapter {
    private ArrayList<ReserveListViewItem> reserveListViewItemList = new ArrayList<ReserveListViewItem>();

    public ReserveListViewAdapter() {

    }

    @Override
    public int getCount() {
        return reserveListViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reserve_list_item, parent, false);
        }

        ImageView accomodationImageView = (ImageView) convertView.findViewById(R.id.reserveAccommodationImg);
        TextView accomodationNameTextView = (TextView) convertView.findViewById(R.id.reserveAccommodationName);
        TextView reservationDutyTextView = (TextView) convertView.findViewById(R.id.reserveAccommodationDuty);
        TextView accomodationInfoTextView = (TextView) convertView.findViewById(R.id.reserveAccommodationInfo);

        ReserveListViewItem reserveListViewItem = reserveListViewItemList.get(position);

        accomodationImageView.setImageDrawable(reserveListViewItem.getAccomodationImg());
        accomodationNameTextView.setText(reserveListViewItem.getAccomodationName());
        reservationDutyTextView.setText(reserveListViewItem.getReservationDuty());
        accomodationInfoTextView.setText(reserveListViewItem.getAccomodationInfo());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return reserveListViewItemList.get(position);
    }

    public void addItem(Drawable img, String name, String duty, String info) {
        ReserveListViewItem reserveItem = new ReserveListViewItem();

        reserveItem.setAccomodationImg(img);
        reserveItem.setAccomodationName(name);
        reserveItem.setReservationDuty(duty);
        reserveItem.setAccomodationInfo(info);

        reserveListViewItemList.add(reserveItem);
    }

}
