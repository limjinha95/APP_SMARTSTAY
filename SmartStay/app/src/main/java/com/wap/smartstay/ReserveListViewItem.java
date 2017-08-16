package com.wap.smartstay;

import android.graphics.drawable.Drawable;

/**
 * Created by limjinha on 2017. 8. 13..
 */

public class ReserveListViewItem {
    private Drawable accomodationImg;
    private String accomodationOfficecode;
    private String accomodationRoomnumber;
    private String reservationDuty;
    private String accomodationInfo;

    public void setAccomodationImg(Drawable img) {
        accomodationImg = img;
    }

    public void setAccomodationOfficeCode(String officecode) {
        accomodationOfficecode = officecode;
    }

    public void setAccomodationRoomNumber(String roomnumber) {
        accomodationRoomnumber = roomnumber;
    }

    public void setReservationDuty(String duty) {
        reservationDuty = duty;
    }

    public void setAccomodationInfo(String info) {
        accomodationInfo = info;
    }

    public Drawable getAccomodationImg() {
        return this.accomodationImg;
    }

    public String getAccomodationOfficeCode() {
        return this.accomodationOfficecode;
    }

    public String getAccomodationRoomnumber() {
        return this.accomodationRoomnumber;
    }

    public String getReservationDuty() {
        return this.reservationDuty;
    }

    public String getAccomodationInfo() {
        return this.accomodationInfo;
    }
}
