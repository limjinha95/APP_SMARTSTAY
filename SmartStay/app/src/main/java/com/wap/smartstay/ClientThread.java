package com.wap.smartstay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.os.Handler;
import android.util.Log;

import com.wap.smartstay.Fragment.SmartkeyFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.wap.smartstay.CouponList.couponList;
import static com.wap.smartstay.SmartkeyPopupList.smartkeyRoomList;
import static com.wap.smartstay.UsageList.reserveList;

public class ClientThread extends Thread {
    Class clas;
    BufferedReader bufferR;
    BufferedWriter bufferW;
    Socket client;
    Handler handler;
    static private boolean isRunning;

    public ClientThread(Socket client, Handler handler, Class clas) {
        this.handler = handler;
        this.clas = clas;
        isRunning = true;
        try {
            this.client = client;
            bufferR = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferW = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void send(String data) {
        try {
            bufferW.write(data);
            bufferW.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String listen() {
        String msg = null;
        try {
            while (isRunning) {
                msg = bufferR.readLine();
                Log.i("test", msg);
                if (clas.getName().equals("com.wap.smartstay.Login")) {
                    if (msg.toString().equals("-")) {
                        Login.Islogin = 2;
                    } else {
                        try {
                            JSONObject jo = new JSONObject(msg);
                            Login.Id = jo.getString("ID");
                            Login.Name = jo.getString("NAME");
                            Login.Pnum = jo.getString("Pnum");
                            Login.Islogin = 1;
                        } catch (Exception e) {

                        }
                    }
                } else if (clas.getName().equals("com.wap.smartstay.Join")) {
                    try{
                        JSONObject jo = new JSONObject(msg);
                        String data = jo.get("Unique").toString();
                        if(data.equals("Y"))
                            Join.check=1;
                        else
                            Join.check=2;
                    }catch(Exception e)
                    {

                    }
                } else if (clas.getName().equals("com.wap.smartstay.SmartkeyCallingList")) {
                    if(SmartKeyCallingList.number == 1) {
                        try {
                            JSONObject wrapObject = new JSONObject(msg);
                            JSONArray ja = new JSONArray(wrapObject);

                            SmartkeyPopupListViewItem item = new SmartkeyPopupListViewItem();
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(i);
                                String smartKeyRoomInfo = dataJsonObject.getString("NAME") + " " + dataJsonObject.getString("RNUM");
                                String smartKeyOfficeCode = dataJsonObject.getString("OfficeCode");
                                item.setSmartkeyRoomInfo(smartKeyRoomInfo);
                                item.setSmartkeyOfficeCode(smartKeyOfficeCode);
                                smartkeyRoomList.add(item);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }else if(SmartKeyCallingList.number == 2){
                        try {
                            JSONObject wrapObject = new JSONObject(msg);
                            JSONArray ja = new JSONArray(wrapObject);
                            JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(0);
                            SmartKeyCallingList.phoneNumber = dataJsonObject.getString("OfficePnum");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                } else if (clas.getName().equals("com.wap.smartstay.UsageList")) {
                    try {
                        JSONArray ja = new JSONArray(msg);
                        ReserveListViewItem item;
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(i);
                            String roomNumber = dataJsonObject.getString("RNUM");
                            String officeCode = dataJsonObject.getString("OFFICECODE");
                            String reservationDuty = dataJsonObject.getString("STARTDATE") + "~" + dataJsonObject.getString("ENDDATE");

                            String roomInfo = "기준 " + dataJsonObject.getString("MINNUM") + "원 / 최대 " + dataJsonObject.getString("MAXNUM");

                            item = new ReserveListViewItem();

                            item.setAccomodationOfficeCode(officeCode);
                            item.setAccomodationRoomNumber(roomNumber);
                            item.setReservationDuty(reservationDuty);
                            item.setAccomodationInfo(roomInfo);

                            reserveList.add(item);

                        }
                        UsageList.check2=true;
                    } catch (JSONException e) {
                    }

                } else if (clas.getName().equals("com.wap.smartstay.CouponList")) {
                    try {
                        JSONObject wrapObject = new JSONObject(msg);
                        JSONArray ja = new JSONArray(wrapObject);

                        CouponListViewItem item = new CouponListViewItem();

                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(i);

                            String couponName = dataJsonObject.getString("NAME");
                            String couponInfo = dataJsonObject.getString("INFO");
                            String couponDuty = dataJsonObject.getString("STARTDATE") + "~" + dataJsonObject.getString("ENDDATE");

                            item.setCouponName(couponName);
                            item.setCouponInfo(couponInfo);
                            item.setCouponDuty(couponDuty);

                            couponList.add(item);
                        }
                    } catch (JSONException e) {
                    }
                } else if(clas.getName().equals("com.wap.smartstay.SmartKeyPopupList")){
                    try {
                        JSONObject wrapObject = new JSONObject(msg);
                        JSONArray ja = new JSONArray(wrapObject);

                        SmartkeyPopupListViewItem item = new SmartkeyPopupListViewItem();

                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(i);

                            String smartKeyRoomInfo = dataJsonObject.getString("NAME") + " " + dataJsonObject.getString("RNUM");

                            item.setSmartkeyRoomInfo(smartKeyRoomInfo);

                            smartkeyRoomList.add(item);
                        }


                    } catch (JSONException e) {
                    }
                }
                else if (clas.getName().equals("com.wap.smartstay.AddGroup")) {
                    if (msg.toString().equals("-")) {
                        AddGroup.idCheck = 1;
                    } else {
                        try {
                            JSONObject jo = new JSONObject(msg);
                            AddGroup.groupName = jo.getString("NAME");
                            AddGroup.groupId = jo.getString("ID");
                            AddGroup.groupPnum = jo.getString("Pnum");
                            AddGroup.idCheck = 2;
                        } catch (Exception e) {

                        }
                    }

                }
            }
        } catch (
                IOException e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return msg;
    }

    static public void setRunningState(boolean state) {
        isRunning = state;
    }

    public void run() {
        super.run();
        listen();
    }
}

