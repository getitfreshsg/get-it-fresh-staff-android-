package com.wetmarket.staff.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.ImageUtils;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.StringUtils;
import com.wetmarket.staff.base.BaseActivity;
import com.wetmarket.staff.base.BasePresenterInterface;
import com.wetmarket.staff.base.BaseViewInterface;
import com.wetmarket.staff.base.Presenter;
import com.wetmarket.staff.databinding.ActivityEditProfileBinding;
import com.wetmarket.staff.retrofit.ApiCallInterface;
import com.wetmarket.staff.retrofit.model.UserModel;

public class EditProfileActivity extends BaseActivity implements View.OnClickListener, BaseViewInterface {


    private ActivityEditProfileBinding binding;
    UserModel userModel;
    BasePresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        binding.setLable(lableModel);
        presenterInterface = new Presenter(this);
        binding.ivBack.setOnClickListener(this);
        binding.rlEdit.setOnClickListener(this);
        userModel = new MyPref(EditProfileActivity.this).getUserData();


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterInterface.sendRequest(EditProfileActivity.this, "", null, ApiCallInterface.GET_PROFILE, true);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ivBack:
                finish();
                break;

            case R.id.rlEdit:
                Intent intent1 = new Intent(EditProfileActivity.this, ProfileDetailActivity.class);
                startActivity(intent1);
                finish();
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

        UserModel userModel = (UserModel) success;
        if (userModel.getResult() != null) {

            new MyPref(EditProfileActivity.this).setUserData(userModel.getResult());
            binding.tvStaffName.setText(userModel.getResult().getFull_name());
            binding.tvName.setText(userModel.getResult().getFull_name());
            binding.tvEmail.setText(userModel.getResult().getEmail());
            binding.tvMobile.setText(userModel.getResult().getPhone_number());
            if (StringUtils.isNotEmpty(userModel.getResult().getProfile_pic())) {
                ImageUtils.loadImage(EditProfileActivity.this, userModel.getResult().getProfile_pic(), R.drawable.ic_user_placeholder_new, binding.ivProfile);
            }
        }


    }
}