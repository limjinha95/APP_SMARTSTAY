package com.wap.smartstay;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.util.AsyncListUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by limjinha on 2017. 8. 13..
 */

public class SmartkeyPopupListViewAdapter extends BaseAdapter {
    private ArrayList<SmartkeyPopupListViewItem> smartkeyPopupListViewItemList = new ArrayList<SmartkeyPopupListViewItem>() ;

    public SmartkeyPopupListViewAdapter() {

    }

    @Override
    public int getCount() {
        return smartkeyPopupListViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.smartkey_list_pop_item, parent, false);
        }

        TextView smartkeyRoomInfoTextView = (TextView) convertView.findViewById(R.id.smartkeyRoomInfo) ;

        SmartkeyPopupListViewItem smartkeyPopupListViewItem = smartkeyPopupListViewItemList.get(position);

        smartkeyRoomInfoTextView.setText(smartkeyPopupListViewItem.getSmartkeyRoomInfo());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return smartkeyPopupListViewItemList.get(position);
    }

    public void addItem(String roomInfo) {
        SmartkeyPopupListViewItem roomInfoItem = new SmartkeyPopupListViewItem();
        roomInfoItem.setSmartkeyRoomInfo(roomInfo);
        smartkeyPopupListViewItemList.add(roomInfoItem);
    }



}
