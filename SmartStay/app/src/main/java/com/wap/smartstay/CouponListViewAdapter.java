package com.wap.smartstay;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CouponListViewAdapter extends BaseAdapter {
    private ArrayList<CouponListViewItem> couponListViewItemList = new ArrayList<CouponListViewItem>() ;

    public CouponListViewAdapter() {

    }

    @Override
    public int getCount() {
        return couponListViewItemList.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.coupon_list_item, parent, false);
        }

        TextView couponNameTextView = (TextView) convertView.findViewById(R.id.couponName);
        TextView couponInfoTextView = (TextView) convertView.findViewById(R.id.couponInfo);
        TextView couponDutyTextView = (TextView) convertView.findViewById(R.id.couponDuty) ;

        CouponListViewItem couponListViewItem = couponListViewItemList.get(position);

        couponNameTextView.setText(couponListViewItem.getCouponName());
        couponInfoTextView.setText(couponListViewItem.getCouponInfo());
        couponDutyTextView.setText(couponListViewItem.getCouponDuty());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return couponListViewItemList.get(position);
    }

    public void addItem(String name, String info, String duty, String cost) {
        CouponListViewItem couponItem = new CouponListViewItem();

        couponItem.setCouponName(name);
        couponItem.setCouponInfo(info);
        couponItem.setCouponDuty(duty);
        couponItem.setCouponCost(cost);

        couponListViewItemList.add(couponItem);
    }

}
