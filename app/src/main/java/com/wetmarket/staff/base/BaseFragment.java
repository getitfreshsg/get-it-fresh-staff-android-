package com.wetmarket.staff.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;


public class BaseFragment extends Fragment {

//    MyPref myPref;
    public Context context;
    ProgressDialog progressDialog;



   /* public MyPref getMyPref() {
        if (myPref == null) {
            myPref = new MyPref(this);
        }
        return myPref;
    }*/

    public void errorMessage(View view, String error) {
        Snackbar snackbar = Snackbar
                .make(view, error, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }



    public void showDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    public void hideDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
