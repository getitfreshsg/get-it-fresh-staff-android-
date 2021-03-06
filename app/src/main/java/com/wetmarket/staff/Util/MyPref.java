package com.wetmarket.staff.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


import com.google.gson.Gson;
import com.wetmarket.staff.retrofit.model.LableModel;
import com.wetmarket.staff.retrofit.model.OrderStatusModel;
import com.wetmarket.staff.retrofit.model.UserModel;

import static android.content.Context.MODE_PRIVATE;

public class MyPref {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public MyPref(Context context) {
        preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setUserData(UserModel userData) {
        editor.putString(Keys.UserData.name(), new Gson().toJson(userData));
        editor.commit();
    }

    public void setLable(LableModel userData) {
        editor.putString(Keys.lable.name(), new Gson().toJson(userData));
        editor.commit();
    }

    public LableModel getLableData() {
        String userData = preferences.getString(Keys.lable.name(), "");
        if (TextUtils.isEmpty(userData))
            return new LableModel();
        LableModel user = new Gson().fromJson(userData, LableModel.class);
        if (user == null) return new LableModel();
        return user;
    }

    public void setOrderStatus(OrderStatusModel userData) {
        editor.putString(Keys.orderstatus.name(), new Gson().toJson(userData));
        editor.commit();
    }

    public UserModel getUserData() {
        String userData = preferences.getString(Keys.UserData.name(), "");
        if (TextUtils.isEmpty(userData))
            return new UserModel();
        UserModel user = new Gson().fromJson(userData, UserModel.class);
        if (user == null) return new UserModel();
        return user;
    }


    public void setData(Keys keys, boolean isData) {
        editor.putBoolean(keys.name(), isData);
        editor.commit();
    }

    public void setData(Keys keys, String isData) {
        editor.putString(keys.name(), isData);
        editor.commit();
    }

    public OrderStatusModel getOrderStatus() {
        String userData = preferences.getString(Keys.orderstatus.name(), "");
        if (TextUtils.isEmpty(userData))
            return new OrderStatusModel();
        OrderStatusModel user = new Gson().fromJson(userData, OrderStatusModel.class);
        if (user == null) return new OrderStatusModel();
        return user;
    }

    public String getData(Keys keys) {
        return preferences.getString(keys.name(), "");
    }

    public boolean getData(Keys keys, boolean defaults) {
        return preferences.getBoolean(keys.name(), defaults);
    }

    public void clearPrefs() {
        editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public enum Keys {
        UserData, token, orderstatus, language, lable;
    }
}
