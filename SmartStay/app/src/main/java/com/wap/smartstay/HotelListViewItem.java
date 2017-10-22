package com.wap.smartstay;

import android.graphics.drawable.Drawable;

public class HotelListViewItem {
    private String roomName;
    private String roomAddress;
    private String officeName;
    private Drawable picDrawable;
    private String roomPrice;
    private String roomType;
    private String officeCode;
    private String roomNum;

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public void setroomNum(String roomNum){
        this.roomNum = roomNum;
    }
    public void setPic(Drawable pic) {
        picDrawable = pic;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public void setName(String name) {
        roomName = name;
    }

    public void setLocation(String location) {
        roomAddress = location;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getName() {
        return this.roomName;
    }

    public String getLocation() {
        return this.roomAddress;
    }

    public String getOfficeCode() {
        return this.officeCode;
    }

    public String getRoomNum(){
        return this.roomNum;
    }
    public Drawable getPic() {
        return this.picDrawable;
    }

    public String getOfficeName() {
        return officeName;
    }

    public String getRoomPrice() {
        return this.roomPrice;
    }

    public String getRoomType() {
        return this.roomType;
    }
}