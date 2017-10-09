package com.wap.smartstay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ihyeon-yeong on 2017. 8. 18..
 */



public class SmartKeyCallingListViewAdapter extends BaseAdapter {

    private ArrayList<SmartKeyCallingListViewItem> smartKeyCallingListViewItemList = new ArrayList<SmartKeyCallingListViewItem>();
    public SmartKeyCallingListViewAdapter() {

    }

    @Override
    public int getCount() {
        return smartKeyCallingListViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.smartkey_list_pop_item, parent, false);
        }

        TextView smartkeyRoomInfoTextView = (TextView) convertView.findViewById(R.id.smartkeyRoomInfo);

        SmartKeyCallingListViewItem smartkeyCallingListViewItem = smartKeyCallingListViewItemList.get(position);

        smartkeyRoomInfoTextView.setText(smartkeyCallingListViewItem.getSmartCallingRoomInfo());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SmartKeyCallingListViewItem getItem(int position) {
        return smartKeyCallingListViewItemList.get(position);
    }

    public void addItem(String roomInfo, String officeCode) {
        SmartKeyCallingListViewItem roomInfoItem = new SmartKeyCallingListViewItem();

        roomInfoItem.setSmartCallingRoomInfo(roomInfo);
        roomInfoItem.setSmartCallingOfficeCode(officeCode);
        smartKeyCallingListViewItemList.add(roomInfoItem);
    }


}
