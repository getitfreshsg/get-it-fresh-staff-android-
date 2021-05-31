package com.wetmarket.staff.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.wetmarket.staff.Adapter.ViewPagerAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Fragment.CollectionFragment;
import com.wetmarket.staff.Fragment.HomeFragment;
import com.wetmarket.staff.Fragment.JobFragment;
import com.wetmarket.staff.Fragment.OrderFragment;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.ImageUtils;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.base.BaseActivity;
import com.wetmarket.staff.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;


    public static String search_text = "";
    public static String search_date = "";
    public static String search_status = "";

    private JobFragment jobFragment = new JobFragment();
    private CollectionFragment collectionFragment = new CollectionFragment();
    private OrderFragment orderFragment = new OrderFragment();



    private int[] tabIcons = {
            R.drawable.menu_purchase,
            R.drawable.menu_collection,
            R.drawable.menu_delivery,
    };
    ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        ApplicationClass.getmInstance().setActivity(MainActivity.this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        lableModel = new MyPref(MainActivity.this).getLableData();
        binding.setLable(lableModel);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(jobFragment);
        viewPagerAdapter.addFragment(collectionFragment);
        viewPagerAdapter.addFragment(orderFragment);

        binding.viewpager.setOffscreenPageLimit(3);
        binding.viewpager.setAdapter(viewPagerAdapter);


        setTabText();
        setonclickLisnter();


        binding.fragmentMainTlHome.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                binding.viewpager.setCurrentItem(tab.getPosition());



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setTabText() {

        binding.fragmentMainTlHome.addTab(binding.fragmentMainTlHome.newTab().setIcon(tabIcons[0]).setText(lableModel.getStaffTabToBePurchase()));
        binding.fragmentMainTlHome.addTab(binding.fragmentMainTlHome.newTab().setIcon(tabIcons[1]).setText(lableModel.getStaffTabAtCollectionCenter()));
        binding.fragmentMainTlHome.addTab(binding.fragmentMainTlHome.newTab().setIcon(tabIcons[2]).setText(lableModel.getStaffTabOutForDelivery()));


    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageUtils.loadImage(MainActivity.this, new MyPref(MainActivity.this).getUserData().getProfile_pic(), R.drawable.ic_user_placeholder_new, binding.ivProfile);
    }

    public void setonclickLisnter() {


        binding.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });
        binding.llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, SearchandFilterActivity.class);
                startActivity(intent);

            }
        });
        binding.ivNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
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




    public void setUpToolBar(String title, boolean isSearch, boolean isBack) {

        binding.tvTitle.setText(title);
        binding.tvTitle.setVisibility(isBack ? View.VISIBLE : View.GONE);
        binding.ivBack.setVisibility(isBack ? View.VISIBLE : View.GONE);
        binding.llProfile.setVisibility(isBack ? View.GONE : View.VISIBLE);
        binding.llSearch.setVisibility(isBack ? View.GONE : View.VISIBLE);

    }


}
