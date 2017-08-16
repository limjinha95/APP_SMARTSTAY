package com.wap.smartstay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by limjinha on 2017. 8. 13..
 */

public class AddGroupListViewAdapter extends BaseAdapter {
    private ArrayList<AddGroupListViewItem> addGroupListViewItemList = new ArrayList<AddGroupListViewItem>() ;

    public AddGroupListViewAdapter() {

    }

    @Override
    public int getCount() {
        return addGroupListViewItemList.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.addgroup_list_item, parent, false);
        }

        TextView addGroupIdTextView = (TextView) convertView.findViewById(R.id.addgroupId);
        TextView addGroupNameTextView = (TextView) convertView.findViewById(R.id.addgroupName);
        TextView addGroupPnumTextView = (TextView) convertView.findViewById(R.id.addGroupPnum) ;

        AddGroupListViewItem addGroupListViewItem = addGroupListViewItemList.get(position);

        addGroupIdTextView.setText(addGroupListViewItem.getAddGroupId());
        addGroupNameTextView.setText(addGroupListViewItem.getAddGroupName());
        addGroupPnumTextView.setText(addGroupListViewItem.getAddGroupPnum());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return addGroupListViewItemList.get(position) ;
    }

    public void addItem(String name, String id, String pnum) {
        AddGroupListViewItem addgroupItem = new AddGroupListViewItem();

        addgroupItem.setAddGroupName(name);
        addgroupItem.setAddGroupId(id);
        addgroupItem.setAddGroupPnum(pnum);

        addGroupListViewItemList.add(addgroupItem);
    }

}
