package com.wetmarket.staff.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.ActivityEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {


private ActivityEditProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);

        binding.ivBack.setOnClickListener(this);
        binding.rlEdit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ivBack:
                finish();
                break;

            case R.id.rlEdit:
                Intent intent1 = new Intent(EditProfileActivity.this,ProfileDetailActivity.class);
                startActivity(intent1);
                finish();
                break;


        }
    }
}