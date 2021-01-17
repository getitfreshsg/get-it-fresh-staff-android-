package com.wetmarket.staff.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Fragment.HomeFragment;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private HomeFragment mainfragment=new HomeFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        ApplicationClass.getmInstance().setActivity(MainActivity.this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        OpenFragment();
        setonclickLisnter();
    }

    public void setonclickLisnter(){




        binding.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "demo" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);

            }
        });
        binding.ivNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().popBackStack();
            }
        });


    }


    public void OpenFragment() {
        final FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_mainLayout,mainfragment);
        transaction.commit();
    }

    public void setUpToolBar(String title,boolean isSearch,boolean isBack){

        binding.tvTitle.setText(title);
        binding.tvTitle.setVisibility(isBack ? View.VISIBLE : View.GONE);
        binding.ivBack.setVisibility(isBack ? View.VISIBLE : View.GONE);
        binding.llProfile.setVisibility(isBack ? View.GONE : View.VISIBLE);
        binding.llSearch.setVisibility(isBack ? View.GONE : View.VISIBLE);

    }



}
