package com.wetmarket.staff.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.ActivityProfileDetailBinding;

public class ProfileDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityProfileDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_detail);

        binding.ivBack.setOnClickListener(this);
        binding.btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.ivBack:
                finish();
                break;

            case R.id.btnSave:

                Intent intent = new Intent(ProfileDetailActivity.this, EditProfileActivity.class);
                startActivity(intent);
                break;

        }
    }
}


