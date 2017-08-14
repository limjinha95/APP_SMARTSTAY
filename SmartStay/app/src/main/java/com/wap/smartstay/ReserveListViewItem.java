package com.wap.smartstay;
import android.graphics.drawable.Drawable;

/**
 * Created by limjinha on 2017. 8. 13..
 */

public class ReserveListViewItem {
    private Drawable accomodationImg;
    private String accomodationName;
    private String reservationDuty;
    private String accomodationInfo;

    public void setAccomodationImg(Drawable img) {
        accomodationImg = img ;
    }
    public void setAccomodationName(String name) {
        accomodationName = name ;
    }
    public void setReservationDuty(String duty) {
        reservationDuty = duty ;
    }
    public void setAccomodationInfo(String info) {
        accomodationInfo = info ;
    }

    public Drawable getAccomodationImg() {
        return this.accomodationImg ;
    }
    public String getAccomodationName() {
        return this.accomodationName ;
    }
    public String getReservationDuty() {
        return this.reservationDuty ;
    }
    public String getAccomodationInfo() {
        return this.accomodationInfo ;
    }
}
