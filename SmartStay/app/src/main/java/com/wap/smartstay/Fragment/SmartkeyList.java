package com.wap.smartstay.Fragment;

public class SmartkeyList {
    String HotelName;
    String RoomName;
    int RoomPic;

    SmartkeyList(String HotelName, String RoomName, int RoomPic) {
        this.HotelName = HotelName;
        this.RoomName = RoomName;
        this.RoomPic = RoomPic;
    }

    public String getHotelName() {
        return HotelName;
    }

    public String getRoomName() {
        return RoomName;
    }

    public int getRoomPic() {
        return RoomPic;
    }

    public void setHotelName(String HotelName) {
        this.HotelName = HotelName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public void setRoomPic(int RoomPic) {
        this.RoomPic = RoomPic;
    }
}