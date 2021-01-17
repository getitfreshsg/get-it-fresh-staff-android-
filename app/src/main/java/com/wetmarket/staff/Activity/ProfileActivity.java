package com.wetmarket.staff.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity  implements View.OnClickListener{

    private ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            binding= DataBindingUtil.setContentView(this, R.layout.activity_profile);

        binding.rlProfileDetail.setOnClickListener(this);

        binding.rlEditProfileNext.setOnClickListener(this);

        binding.ivBack.setOnClickListener(this);
        binding.rlLogout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.ivBack:
                finish();
                break;
            case R.id.rlEditProfileNext:
                Intent intent1 = new Intent(ProfileActivity.this,ProfileDetailActivity.class);
                startActivity(intent1);

                break;

            case R.id.rlProfileDetail:
                Intent intent2 = new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(intent2);
                break;
            case R.id.rlLogout:

                Intent intent3 = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent3);
                finish();
                break;
        }

    }
}