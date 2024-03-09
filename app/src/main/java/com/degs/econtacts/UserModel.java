package com.degs.econtacts;

public class UserModel {
    String user_name,email,mobile,user_image;

    public UserModel(String user_name, String email, String mobile, String user_image) {
        this.user_name = user_name;
        this.email = email;
        this.mobile = mobile;
        this.user_image = user_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }
}
