package com.wetmarket.staff.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.DemoListAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.CompletedOrdersModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.base.BaseActivity;
import com.wetmarket.staff.databinding.FragmentCompletedOrdersBinding;

import java.util.ArrayList;

public class CompletedOrdersFragment extends BaseActivity implements DemoListAdapter.OnItemClickListener, View.OnClickListener {

    private FragmentCompletedOrdersBinding binding;
    private AppCompatActivity activity;
    private View rootView;

    private ArrayList<CompletedOrdersModel> itemModelArrayList = new ArrayList<>();

    private DemoListAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_completed_orders);
        binding.setLable(lableModel);
        initComponents();
    }


    public void initComponents() {
        binding.ivBack.setOnClickListener(this);
        for (int i = 0; i < 3; i++) {
            itemModelArrayList.add(new CompletedOrdersModel());

        }
        binding.rvCompletedOrderList.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new DemoListAdapter(activity, itemModelArrayList);
        adapter.setOnItemClickListener(this);
        binding.rvCompletedOrderList.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, CompletedOrdersModel viewModel) {


        DeliveryDetailsFragment deliveryDetailsFragment = new DeliveryDetailsFragment();
        Bundle args = new Bundle();
        args.putBoolean("isCompleteOrder", true);
        deliveryDetailsFragment.setArguments(args);

        //  Utils.addNextFragment(activity, deliveryDetailsFragment,CompletedOrdersFragment.this,false);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivBack) {
            onBackPressed();
        }
    }
}
