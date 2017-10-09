package com.wap.smartstay;

import android.graphics.drawable.Drawable;

public class HotelListViewItem {
    private String nameStr;
    private String locationStr;
    private Drawable picDrawable;

    public void setPic(Drawable pic){
        picDrawable = pic;
    }
    public void setName(String name){
        nameStr = name;
    }
    public void setLocation(String location){
        locationStr = location;
    }

    public String getName(){
        return this.nameStr;
    }
    public String getLocation(){ return this.locationStr; }
    public Drawable getPic(){
        return this.picDrawable;
    }
}