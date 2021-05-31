package com.wetmarket.staff.retrofit.model;

import java.io.Serializable;

public class UserModel extends BaseModel implements Serializable {
    private String token, full_name, email, profile_pic, language, active_flag, _id, phone_number;
    private UserModel result;
    private WetMarketModel wetmarket_id;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public String getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(String active_flag) {
        this.active_flag = active_flag;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public UserModel getResult() {
        return result;
    }

    public void setResult(UserModel result) {
        this.result = result;
    }

    public WetMarketModel getWetmarket_id() {
        return wetmarket_id;
    }

    public void setWetmarket_id(WetMarketModel wetmarket_id) {
        this.wetmarket_id = wetmarket_id;
    }
}
