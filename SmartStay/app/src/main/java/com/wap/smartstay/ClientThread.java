package com.wap.smartstay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.os.Handler;
import android.util.Log;

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
            e.printStackTrace();
        }
    }

    public String listen() {
        String msg = null;

        try {
            while (isRunning) {
                msg = bufferR.readLine();

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
                    try {
                        JSONObject jo = new JSONObject(msg);
                        String data = jo.get("Unique").toString();

                        if (data.equals("Y"))
                            Join.check = 1;
                        else
                            Join.check = 2;
                    } catch (Exception e) {

                    }
                } else if (clas.getName().equals("com.wap.smartstay.SmartkeyCallingList")) {
                    if (SmartkeyCallingList.number == 1) {
                        try {
                            JSONArray ja = new JSONArray(msg);
                            SmartkeyPopupListViewItem item;

                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(i);

                                String smartKeyRoomInfo = dataJsonObject.getString("NAME") + " (" + dataJsonObject.getString("RNUM") + ")";
                                String smartKeyOfficeCode = dataJsonObject.getString("OfficeCode");

                                item = new SmartkeyPopupListViewItem();

                                item.setSmartkeyRoomInfo(smartKeyRoomInfo);
                                item.setSmartkeyOfficeCode(smartKeyOfficeCode);

                                smartkeyRoomList.add(item);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else if (SmartkeyCallingList.number == 2) {
                        try {
                            JSONArray ja = new JSONArray(msg);
                            JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(0);

                            SmartkeyCallingList.phoneNumber = dataJsonObject.getString("OfficePnum");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else if (clas.getName().equals("com.wap.smartstay.UsageList")) {
                        try {
                            JSONArray ja = new JSONArray(msg);
                            ReserveListViewItem item;

                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(i);

                                String accomodationName = dataJsonObject.getString("NAME") + " (" + dataJsonObject.getString("RNUM") + ")";
                                String reservationDuty = dataJsonObject.getString("STARTDATE") + " ~ " + dataJsonObject.getString("ENDDATE");
                                String accomodationInfo = "기준 " + dataJsonObject.getString("MINNUM") + "인 / 최대 " + dataJsonObject.getString("MAXNUM") + "인";

                                item = new ReserveListViewItem();

                                item.setAccomodationName(accomodationName);
                                item.setReservationDuty(reservationDuty);
                                item.setAccomodationInfo(accomodationInfo);

                                reserveList.add(item);
                            }
                            UsageList.check2 = true;
                        } catch (JSONException e) {
                        }
                    } else if (clas.getName().equals("com.wap.smartstay.CouponList")) {
                        try {
                            JSONArray ja = new JSONArray(msg);
                            CouponListViewItem item;

                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(i);

                                String couponName = dataJsonObject.getString("NAME");
                                String couponInfo = dataJsonObject.getString("INFO");
                                String couponDuty = dataJsonObject.getString("STARTDATE") + " ~ " + dataJsonObject.getString("ENDDATE");

                                item = new CouponListViewItem();

                                item.setCouponName(couponName);
                                item.setCouponInfo(couponInfo);
                                item.setCouponDuty(couponDuty);

                                couponList.add(item);
                            }
                            CouponList.check2 = true;
                        } catch (JSONException e) {
                        }
                    } else if (clas.getName().equals("com.wap.smartstay.SmartkeyPopupList")) {
                        try {
                            JSONArray ja = new JSONArray(msg);
                            SmartkeyPopupListViewItem item;
                            SmartkeyPopupList.check2 = false;

                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject dataJsonObject = (JSONObject) ja.getJSONObject(i);

                                String smartKeyRoomInfo = dataJsonObject.getString("NAME") + " (" + dataJsonObject.getString("RNUM") + ")";

                                item = new SmartkeyPopupListViewItem();
                                item.setSmartkeyRoomInfo(smartKeyRoomInfo);

                                smartkeyRoomList.add(item);
                            }
                            SmartkeyPopupList.check2 = true;
                        } catch (JSONException e) {
                        }
                    } else if (clas.getName().equals("com.wap.smartstay.AddGroup")) {
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
            }
        } catch (IOException e) {
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