package com.wetmarket.staff.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.DataBindingUtil;

import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.ImageUtils;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.StringUtils;
import com.wetmarket.staff.base.BaseActivity;
import com.wetmarket.staff.base.BasePresenterInterface;
import com.wetmarket.staff.base.BaseViewInterface;
import com.wetmarket.staff.base.Presenter;
import com.wetmarket.staff.databinding.ActivityProfileBinding;
import com.wetmarket.staff.dialog.CustomAlertDialog;
import com.wetmarket.staff.retrofit.ApiCallInterface;
import com.wetmarket.staff.retrofit.model.LableModel;
import com.wetmarket.staff.retrofit.model.LanguageModel;
import com.wetmarket.staff.retrofit.model.UserModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ProfileActivity extends BaseActivity implements View.OnClickListener, BaseViewInterface {

    private ActivityProfileBinding binding;
    BasePresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setLable(lableModel);
        presenterInterface = new Presenter(this);
        binding.rlProfileDetail.setOnClickListener(this);
        binding.rlEditProfileNext.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
        binding.rlLogout.setOnClickListener(this);
        binding.rlLanguage.setOnClickListener(this);


        binding.spLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    LanguageModel languageModel = (LanguageModel) binding.spLanguage.getSelectedItem();
                    binding.tvSelectLanguage.setText(languageModel.getLanguage_name());
                    new MyPref(ProfileActivity.this).setData(MyPref.Keys.language, languageModel.getLanguage_code());

                    HashMap<String, String> map = new HashMap<>();


                    map.put("language", languageModel.getLanguage_code());
                    presenterInterface.sendRequest(ProfileActivity.this, "", map, ApiCallInterface.GET_LABEL, false);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        presenterInterface.sendRequest(ProfileActivity.this, "", null, ApiCallInterface.GET_LANGUAGE, false);


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterInterface.sendRequest(ProfileActivity.this, "", null, ApiCallInterface.GET_PROFILE, true);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.rl_language:
                binding.spLanguage.performClick();
                break;
            case R.id.rlEditProfileNext:
                Intent intent1 = new Intent(ProfileActivity.this, ProfileDetailActivity.class);
                startActivity(intent1);

                break;

            case R.id.rlProfileDetail:
                Intent intent2 = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent2);
                break;
            case R.id.rlLogout:
                new CustomAlertDialog(ProfileActivity.this, lableModel.getTxtAreYouSure(), "", "Yes", "No", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == R.id.tv_ok) {
                            new MyPref(ProfileActivity.this).setUserData(null);
                            new MyPref(ProfileActivity.this).setData(MyPref.Keys.token,"");
                            Intent logout = new Intent(ProfileActivity.this, LoginActivity.class);
                            startActivity(logout);
                            finishAffinity();
                        }

                    }
                }).show();


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
        if (requestCode == ApiCallInterface.GET_LABEL) {
            LableModel lableModel = (LableModel) success;
            new MyPref(ProfileActivity.this).setLable(lableModel.getResult());

            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            finishAffinity();

        }

        if (requestCode == ApiCallInterface.GET_PROFILE) {
            UserModel userModel = (UserModel) success;
            if (userModel.getResult() != null) {

                new MyPref(ProfileActivity.this).setUserData(userModel.getResult());
                binding.tvStaffName.setText(userModel.getResult().getFull_name());
                binding.tvStaffEmailId.setText(userModel.getResult().getEmail());
                if (StringUtils.isNotEmpty(userModel.getResult().getProfile_pic())) {
                    ImageUtils.loadImage(ProfileActivity.this, userModel.getResult().getProfile_pic(), R.drawable.ic_user_placeholder_new, binding.ivProfile);
                }
            }
        }
        if (requestCode == ApiCallInterface.GET_LANGUAGE) {
            LanguageModel languageModel = (LanguageModel) success;
            if (languageModel.getResult() != null) {
                Collections.reverse(languageModel.getResult());
                setSpinner(binding.spLanguage, languageModel.getResult());

                if (StringUtils.isNotEmpty(new MyPref(ProfileActivity.this).getData(MyPref.Keys.language))) {
                    for (int i = 0; i < languageModel.getResult().size(); i++) {
                        if (new MyPref(ProfileActivity.this).getData(MyPref.Keys.language).equalsIgnoreCase(languageModel.getResult().get(i).getLanguage_code())) {
                            binding.tvSelectLanguage.setText(languageModel.getResult().get(i).getLanguage_name());
                            return;
                        }
                    }
                } else {
                    binding.tvSelectLanguage.setText(getResources().getString(R.string.select_language));
                }
            }
        }
    }

    private void setSpinner(AppCompatSpinner spinner, List<LanguageModel> list) {

        LanguageModel languageModel = new LanguageModel();
        languageModel.set_id("0");
        languageModel.setLanguage_code("0");
        languageModel.setLanguage_name("Select");
        list.add(0, languageModel);
        ArrayAdapter<LanguageModel> arrayAdapter = new ArrayAdapter<LanguageModel>(ProfileActivity.this, R.layout.spinner_row, list) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View spinnerView = super.getDropDownView(position, convertView, parent);
                TextView spinnerTextV = (TextView) spinnerView;
                spinnerTextV.setTextColor(Color.parseColor("#b2000000"));
                if (position == 0) {
                    spinnerTextV.setTextColor(Color.parseColor("#a7a7a6"));
                } else {
                    spinnerTextV.setTextColor(Color.parseColor("#b2000000"));
                }
                return spinnerView;
            }
        };


        arrayAdapter.setDropDownViewResource(R.layout.spinner_drop_down);

        spinner.setAdapter(arrayAdapter);

    }
}