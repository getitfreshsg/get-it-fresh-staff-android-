package com.wetmarket.staff.base;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.retrofit.DisposableCallback;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;


public class Presenter implements BasePresenterInterface {

    BaseViewInterface mvi;
    private String TAG = "UserPresenter";

    public Presenter(BaseViewInterface mvi) {
        this.mvi = mvi;
    }


    @Override
    public void sendRequest(Context context, String body, HashMap<String, String> param, int reqCode, boolean isProgress) {
        try {
            ((ApplicationClass) ((AppCompatActivity) context).getApplication()).getApiTask().sendRequest(body, param, reqCode, new DisposableCallback(context, reqCode, mvi, isProgress));
        } catch (CertificateException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendRequest(Context context, HashMap<String, String> param, int reqCode, boolean isProgress, String... photos) {
        try {
            ((ApplicationClass) ((AppCompatActivity) context).getApplication()).getApiTask().sendRequest(param, reqCode, new DisposableCallback(context, reqCode, mvi, isProgress),photos);
        } catch (CertificateException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
    }
}
