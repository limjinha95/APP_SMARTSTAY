package com.wap.smartstay;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HotelListViewAdapter extends BaseAdapter {
    MyBounceInterpolator interpolator;

    private ArrayList<HotelListViewItem> hotelViewItemList = new ArrayList<HotelListViewItem>() ;
    public HotelListViewAdapter() {

    }

    @Override
    public int getCount() {
        return hotelViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        final Animation myAnim = AnimationUtils.loadAnimation(context,R.anim.bounce);
        interpolator = new MyBounceInterpolator(0.1,1);
        myAnim.setInterpolator(interpolator);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.hotel_list_item, null);
        }

        ImageView pic = (ImageView) convertView.findViewById(R.id.pic);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView location = (TextView) convertView.findViewById(R.id.location);

        final HotelListViewItem HotelListItem = hotelViewItemList.get(position);

        pic.setImageDrawable(HotelListItem.getPic());
        location.setText(HotelListItem.getLocation());
        name.setText(HotelListItem.getName());

        Button reserveBtn = (Button) convertView.findViewById(R.id.reserveBtn);
        reserveBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        v.startAnimation(myAnim);
                        Intent Intent = new Intent(context, Reserve.class);
                        Intent.putExtra("roomName", HotelListItem.getName());
                        Intent.putExtra("roomNumber", HotelListItem.getRoomNum());
                        Intent.putExtra("officeCode", HotelListItem.getOfficeCode());
                        Intent.putExtra("roomType", HotelListItem.getRoomType());
                        Intent.putExtra("roomPrice", HotelListItem.getRoomPrice());
                        Log.e("roomPrice", HotelListItem.getRoomPrice());
                        context.startActivity(Intent);
                    }
                }
        );
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return hotelViewItemList.get(position);
    }

    public void addItem(Drawable pic, String room_name, String office_name, String address, String roomPrice, String roomType, String office_code, String room_no) {
        HotelListViewItem item = new HotelListViewItem();

        item.setPic(pic);
        item.setOfficeName(office_name);
        item.setName(room_name);
        item.setLocation(address);
        item.setRoomPrice(roomPrice);
        item.setRoomType(roomType);
        item.setroomNum(room_no);
        item.setOfficeCode(office_code);
        hotelViewItemList.add(item);
    }
}