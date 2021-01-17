package com.wetmarket.staff.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.OrderListAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.databinding.FragmentOrderBinding;

import java.util.ArrayList;

public class OrderFragment extends BaseFragment implements View.OnClickListener {

    private FragmentOrderBinding binding;
    private AppCompatActivity activity;
    private View rootView;

    private ArrayList<ItemModel> itemModelArrayList=new ArrayList<>();
    private OrderListAdapter orderListAdapter;


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        activity = ApplicationClass.getmInstance().getActivity();
        rootView = binding.getRoot();
        initComponents(rootView);
        return rootView;

    }

    @Override
    public void initComponents(View rootView) {

        binding.tvViewCompletedOrders.setOnClickListener(this);

        for (int i=0;i<=5;i++){
            itemModelArrayList.add(new ItemModel());
        }
        binding.rvList.setLayoutManager(new LinearLayoutManager(activity));
        orderListAdapter=new OrderListAdapter(this,activity,itemModelArrayList);
        binding.rvList.setAdapter(orderListAdapter);

    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch (id){
            case R.id.tvViewCompletedOrders:

                Utils.addNextFragment(activity,new CompletedOrdersFragment(),OrderFragment.this,false);

                break;
        }
    }
    public void setUpToolBar() {
        ((MainActivity) activity).setUpToolBar("", true, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            setUpToolBar();
        }
    }

    public void setOnItemClick(ItemModel item, int position)
    {
       Utils.addNextFragment(activity,new DeliveryDetailsFragment(),OrderFragment.this,false);

    }


}
