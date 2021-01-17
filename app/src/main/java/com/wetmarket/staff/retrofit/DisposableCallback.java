package com.wetmarket.staff.retrofit;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;
import com.wetmarket.staff.base.BaseViewInterface;
import com.wetmarket.staff.retrofit.model.BaseModel;

import java.io.IOException;
import java.net.ConnectException;

import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public class DisposableCallback<T> extends DisposableObserver<T> {
    private Context context;
    private int reqCode;
    private BaseViewInterface onApiCallListerner;
    private boolean isProgress;
    private ProgressDialog progressDialog;


    public DisposableCallback(Context context, int reqCode, BaseViewInterface onApiCallListerner, boolean isProgress) {
        this.context = context;
        this.reqCode = reqCode;
        this.onApiCallListerner = onApiCallListerner;
        this.isProgress = isProgress;
        if (this.isProgress)
            showProgrssDialog();


    }

    public DisposableCallback(Context context, int reqCode, BaseViewInterface onApiCallListerner) {
        this(context, reqCode, onApiCallListerner, false);


    }

    @Override
    public void onNext(Object t) {
        dismissDialog();
        if (((AppCompatActivity) context).isFinishing()) {
            return;
        }

        onApiCallListerner.onSuccess(t, reqCode, 200);
    }

    @Override
    public void onError(Throwable e) {
        dismissDialog();
        if (((AppCompatActivity) context).isFinishing()) {
            return;
        }

        //hideProgress();
        if (e instanceof UndeliverableException) {
            onApiCallListerner.onError(e.getMessage(), reqCode, e.hashCode());
        } else if (e instanceof ConnectException) {
            //  AppClass.networkConnectivity.errorMessage(context, onApiCallListerner, reqCode);
        } else if (e instanceof HttpException) {

            if (((HttpException) e).code() == 401) {
                HttpException httpException = (HttpException) e;
                try {
                    String errorBody = httpException.response().errorBody().string();
                    BaseModel baseModel = new Gson().fromJson(errorBody, BaseModel.class);
                    onApiCallListerner.onError(baseModel.getMessage(), reqCode, ((HttpException) e).code());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                /*context.startActivity(new Intent(context, LoginActivity.class));
                ((AppCompatActivity) context).finishAffinity();*/
                return;
            }
        }


    }

    @Override
    public void onComplete() {
        dismissDialog();
    }

    private void showProgrssDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.show();
    }

    private void dismissDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
}
