package com.wap.smartstay;

/**
 * Created by limjinha on 2017. 8. 13..
 */

public class AddGroupListViewItem {
    private String addGroupId;
    private String addGroupName;
    private String addGroupPnum;

    public void setAddGroupId(String id) {
        addGroupId = id;
    }
    public void setAddGroupName(String name) {
        addGroupName = name;
    }
    public void setAddGroupPnum(String pnum) {
        addGroupPnum = pnum;
    }

    public String getAddGroupId() {
        return this.addGroupId;
    }

    public String getAddGroupName() {
        return this.addGroupName;
    }

    public String getAddGroupPnum() {
        return this.addGroupPnum;
    }
}
