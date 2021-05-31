package com.wetmarket.staff.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    private AppCompatActivity activity;
    private View rootView;

    private FragmentManager fragmentManager;

    private JobFragment jobFragment = new JobFragment();
    private CollectionFragment collectionFragment = new CollectionFragment();
    private OrderFragment orderFragment = new OrderFragment();
    private Fragment active = jobFragment;


    private int[] tabIcons = {
            R.drawable.menu_purchase,
            R.drawable.menu_collection,
            R.drawable.menu_delivery,
    };


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        activity = ApplicationClass.getmInstance().getActivity();
        rootView = binding.getRoot();
        initComponents(rootView);
        return rootView;
    }

    @Override
    public void initComponents(View rootView) {
        lableModel = new MyPref(getActivity()).getLableData();

        fragmentManager = activity.getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.fameLayout, jobFragment, "1").commitAllowingStateLoss();
        fragmentManager.beginTransaction().add(R.id.fameLayout, collectionFragment, "2").hide(collectionFragment).commitAllowingStateLoss();
        fragmentManager.beginTransaction().add(R.id.fameLayout, orderFragment, "3").hide(orderFragment).commitAllowingStateLoss();

        setTabText();

        binding.fragmentMainTlHome.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in, R.animator.slide_fragment_horizontal_left_out, R.animator.slide_fragment_horizontal_left_in,
                            R.animator.slide_fragment_horizontal_right_out);
                    fragmentTransaction.hide(active).show(jobFragment).commit();
                    active = jobFragment;
                    ((MainActivity) activity).setUpToolBar("", true, false);

                } else if (tab.getPosition() == 1) {

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in, R.animator.slide_fragment_horizontal_left_out, R.animator.slide_fragment_horizontal_left_in,
                            R.animator.slide_fragment_horizontal_right_out);
                    fragmentTransaction.hide(active).show(collectionFragment).commit();
                    active = collectionFragment;
                    ((MainActivity) activity).setUpToolBar("", true, false);

                } else if (tab.getPosition() == 2) {

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in, R.animator.slide_fragment_horizontal_left_out, R.animator.slide_fragment_horizontal_left_in,
                            R.animator.slide_fragment_horizontal_right_out);
                    fragmentTransaction.hide(active).show(orderFragment).commit();
                    active = orderFragment;
                    ((MainActivity) activity).setUpToolBar("", true, false);


                }
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

    public void replaceFragment(Fragment fragment) {

        final FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in, R.animator.slide_fragment_horizontal_left_out, R.animator.slide_fragment_horizontal_left_in,
                R.animator.slide_fragment_horizontal_right_out);
        transaction.replace(R.id.fameLayout, fragment);
        transaction.commit();
    }

}
