package com.wetmarket.staff.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.StringUtils;
import com.wetmarket.staff.base.BasePresenterInterface;
import com.wetmarket.staff.base.BaseViewInterface;
import com.wetmarket.staff.base.Presenter;
import com.wetmarket.staff.retrofit.ApiCallInterface;
import com.wetmarket.staff.retrofit.model.LableModel;
import com.wetmarket.staff.retrofit.model.OrderStatusModel;

import java.util.HashMap;

public class SplashActivity extends AppCompatActivity implements BaseViewInterface {

    private int SPLASH_TIME_OUT = 1000;
    private boolean isLogin = false;
    BasePresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenterInterface = new Presenter(this);
        HashMap<String, String> map = new HashMap<>();
        map.put("status_for", "2");
        presenterInterface.sendRequest(SplashActivity.this, "", map, ApiCallInterface.ORDER_STATUS_LIST, false);

    }

    @Override
    public void retry(int pos) {

    }

    @Override
    public void onError(String errorMsg, int requestCode, int resultCode) {

    }

    @Override
    public void onSuccess(Object success, int requestCode, int resultCode) {
        if (requestCode == ApiCallInterface.ORDER_STATUS_LIST) {
            OrderStatusModel userModel = (OrderStatusModel) success;
            if (userModel.getResult() != null) {
                new MyPref(SplashActivity.this).setOrderStatus(userModel);
                getLable();
            }
        }
        if (requestCode == ApiCallInterface.GET_LABEL) {
            LableModel lableModel = (LableModel) success;

            new MyPref(SplashActivity.this).setLable(lableModel.getResult());
            new SplashTask().execute();
        }
    }

    private class SplashTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(SPLASH_TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (new MyPref(SplashActivity.this).getData(MyPref.Keys.token).equalsIgnoreCase("")) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

            }
            finishAffinity();

        }
    }

    private void getLable() {
        HashMap<String, String> map = new HashMap<>();

        if (StringUtils.isNotEmpty(new MyPref(SplashActivity.this).getData(MyPref.Keys.language))) {
            map.put("language", new MyPref(SplashActivity.this).getData(MyPref.Keys.language));
        } else {
            map.put("language", "EN");
        }

        presenterInterface.sendRequest(SplashActivity.this, "", map, ApiCallInterface.GET_LABEL, false);
    }
}
