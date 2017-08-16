package com.wap.smartstay;

/**
 * Created by limjinha on 2017. 8. 13..
 */

public class SmartkeyPopupListViewItem {
    private String smartkeyRoomInfo;
    private String smartkeyOfficeCode;

    public void setSmartkeyRoomInfo(String roomInfo) {
        smartkeyRoomInfo = roomInfo;
    }
    public void setSmartkeyOfficeCode(String officeCode) { smartkeyOfficeCode = officeCode; }

    public String getSmartkeyRoomInfo() {
        return this.smartkeyRoomInfo ;
    }
    public String getSmartkeyOfficeCode() { return  this.smartkeyOfficeCode; }
}
