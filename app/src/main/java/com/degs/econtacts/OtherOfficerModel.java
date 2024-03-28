package com.degs.econtacts;

public class OtherOfficerModel {
    String role,name,mobile;

    public OtherOfficerModel(String role, String name, String mobile) {
        this.role = role;
        this.name = name;
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
