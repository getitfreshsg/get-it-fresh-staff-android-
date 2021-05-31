package com.wetmarket.staff.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.StringUtils;
import com.wetmarket.staff.Util.Utility;
import com.wetmarket.staff.base.BaseActivity;
import com.wetmarket.staff.base.BasePresenterInterface;
import com.wetmarket.staff.base.BaseViewInterface;
import com.wetmarket.staff.base.Presenter;
import com.wetmarket.staff.databinding.ActivityLoginBinding;
import com.wetmarket.staff.retrofit.ApiCallInterface;
import com.wetmarket.staff.retrofit.ParametersInterface;
import com.wetmarket.staff.retrofit.model.UserModel;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener, BaseViewInterface {

    private ActivityLoginBinding binding;
    BasePresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLable(lableModel);
        presenterInterface = new Presenter(this);
        binding.btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.btnLogin:
                if (isValidate()) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(ParametersInterface.LOGIN.email.name(), binding.edtEmail.getText().toString().trim());
                    map.put(ParametersInterface.LOGIN.password.name(), binding.edtPassword.getText().toString().trim());
                    map.put(ParametersInterface.LOGIN.deviceId.name(), Utility.getDeviceIdWithoutPermission(LoginActivity.this));
                    map.put(ParametersInterface.LOGIN.FCMId.name(), "123");
                    presenterInterface.sendRequest(LoginActivity.this, "", map, ApiCallInterface.LOGIN, true);
                }

                break;
        }
    }

    @Override
    public void retry(int pos) {

    }

    @Override
    public void onError(String errorMsg, int requestCode, int resultCode) {
        Utility.showErrorMessage(binding.getRoot(), errorMsg);
    }

    @Override
    public void onSuccess(Object success, int requestCode, int resultCode) {
        UserModel userModel = (UserModel) success;
        if (userModel.getResult() != null) {
            new MyPref(LoginActivity.this).setData(MyPref.Keys.token, userModel.getToken());
            new MyPref(LoginActivity.this).setUserData(userModel.getResult());
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finishAffinity();
        }

    }

    private boolean isValidate() {

        if (!StringUtils.isNotEmpty(binding.edtEmail.getText().toString().trim())) {
            Utility.showErrorMessage(binding.getRoot(), lableModel.getAlertEnterEmailAddress());
            return false;
        } else if (!StringUtils.isValidEmail(binding.edtEmail.getText().toString().trim())) {
            Utility.showErrorMessage(binding.getRoot(), lableModel.getAlertValidEmail());
            return false;
        } else if (!StringUtils.isNotEmpty(binding.edtPassword.getText().toString().trim())) {
            Utility.showErrorMessage(binding.getRoot(), lableModel.getAlertEnterPassword());
            return false;
        }

        return true;
    }
}
