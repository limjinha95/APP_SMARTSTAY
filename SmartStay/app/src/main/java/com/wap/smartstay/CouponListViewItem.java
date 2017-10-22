package com.wap.smartstay;


public class CouponListViewItem {
    private String couponName;
    private String couponInfo;
    private String couponDuty;
    private String couponCost;

    public void setCouponName(String name) {
        couponName = name;
    }

    public void setCouponInfo(String info) {
        couponInfo = info;
    }

    public void setCouponDuty(String duty) {
        couponDuty = duty;
    }

    public void setCouponCost(String cost) {
        couponCost = cost;
    }

    public String getCouponName() {
        return this.couponName;
    }

    public String getCouponInfo() {
        return this.couponInfo;
    }

    public String getCouponDuty() {
        return this.couponDuty;
    }

    public String getCouponCost() {
        return this.couponCost;
    }
}
