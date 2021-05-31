package com.wetmarket.staff.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.wetmarket.staff.databinding.LoadingDialogBinding;


public class LoadingDialog extends AppCompatDialog implements View.OnClickListener {
    private final Context context;


    LoadingDialogBinding mBinding;

    public LoadingDialog(Context context) {
        super(context);
        this.context = context;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mBinding = LoadingDialogBinding.inflate(LayoutInflater.from(context), null, false);
        setContentView(mBinding.getRoot());
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(this.getWindow().getAttributes());
        lp.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.92);
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        this.getWindow().setAttributes(lp);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);


       // AnimatedVectorDrawable vectorDrawable= (AnimatedVectorDrawable) mBinding.recommandedProgress.getBackground();
    }

    @Override
    public void onClick(View v) {

    }
}
