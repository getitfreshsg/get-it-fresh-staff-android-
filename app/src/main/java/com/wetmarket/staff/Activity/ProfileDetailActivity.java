package com.wetmarket.staff.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.ImagePickerUtils;
import com.wetmarket.staff.Util.ImageUtils;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.StringUtils;
import com.wetmarket.staff.Util.Utility;
import com.wetmarket.staff.base.BaseActivity;
import com.wetmarket.staff.base.BasePresenterInterface;
import com.wetmarket.staff.base.BaseViewInterface;
import com.wetmarket.staff.base.Presenter;
import com.wetmarket.staff.databinding.ActivityProfileDetailBinding;
import com.wetmarket.staff.dialog.CustomAlertDialog;
import com.wetmarket.staff.retrofit.ApiCallInterface;
import com.wetmarket.staff.retrofit.model.BaseModel;
import com.wetmarket.staff.retrofit.model.UserModel;

import java.util.HashMap;

public class ProfileDetailActivity extends BaseActivity implements View.OnClickListener, BaseViewInterface {

    private ActivityProfileDetailBinding binding;
    UserModel userModel;
    BasePresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_detail);
        binding.setLable(lableModel);
        presenterInterface = new Presenter(this);
        binding.ivBack.setOnClickListener(this);
        binding.btnSave.setOnClickListener(this);
        binding.ivCamera.setOnClickListener(this);
        userModel = new MyPref(ProfileDetailActivity.this).getUserData();
        binding.tvName.setText(userModel.getFull_name());
        if (StringUtils.isNotEmpty(userModel.getProfile_pic())) {
            ImageUtils.loadImage(ProfileDetailActivity.this, userModel.getProfile_pic(), R.drawable.ic_user_placeholder_new, binding.ivProfile);
        }

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivCamera:
                new ImagePickerUtils(ProfileDetailActivity.this, false, true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;

            case R.id.btnSave:
                if (isValidate()) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("full_name", binding.tvName.getText().toString());
                    presenterInterface.sendRequest(ProfileDetailActivity.this, map, ApiCallInterface.UPDATE_PROFILE, true, binding.ivProfile.getTag() != null ? binding.ivProfile.getTag().toString() : "");
                }
                break;

        }
    }

    @Override
    public void retry(int pos) {

    }

    @Override
    public void onError(String errorMsg, int requestCode, int resultCode) {

    }

    @Override
    public void onSuccess(Object success, int requestCode, int resultCode) {
        BaseModel baseModel = (BaseModel) success;
        new CustomAlertDialog(ProfileDetailActivity.this, baseModel.getMessage(), "", "OK", "", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        }).show();

    }

    private boolean isValidate() {

        if (!StringUtils.isNotEmpty(binding.tvName.getText().toString().trim())) {
            Utility.showErrorMessage(binding.getRoot(), "Please enter name");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1310 && resultCode == Activity.RESULT_OK) {
            assert data != null;
            if (data.hasExtra("path")) {
                Uri uri = data.getParcelableExtra("path");
                if (uri != null) {
                    binding.ivProfile.setTag(uri.getPath());
                    binding.ivProfile.setImageURI(uri);
                }
            }


        }
    }
}


