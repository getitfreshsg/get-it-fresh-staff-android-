package com.wetmarket.staff.base;

import android.content.Context;

import java.util.HashMap;

public interface BasePresenterInterface {
    void sendRequest(Context context, String body, HashMap<String, String> param, int reqCode,boolean isProgress);
    void sendRequest(Context context, HashMap<String, String> param, int reqCode, boolean isProgress, String... photos);
}
