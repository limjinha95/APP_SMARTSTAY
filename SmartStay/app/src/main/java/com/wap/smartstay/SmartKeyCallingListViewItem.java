package com.wap.smartstay;

/**
 * Created by ihyeon-yeong on 2017. 8. 18..
 */



public class SmartKeyCallingListViewItem {
    private String smartkeyRoomInfo;
    private String smartkeyOfficeCode;

    public void setSmartCallingRoomInfo(String roomInfo) {
        this.smartkeyRoomInfo = roomInfo;
    }

    public void setSmartCallingOfficeCode(String officeCode) {
        this.smartkeyOfficeCode = officeCode;
    }

    public String getSmartCallingRoomInfo() {
        return this.smartkeyRoomInfo;
    }

    public String getSmartCallingOfficeCode() {
        return this.smartkeyOfficeCode;
    }
}
