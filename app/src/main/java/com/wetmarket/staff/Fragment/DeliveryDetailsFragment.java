package com.wetmarket.staff.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.OrderDeatilsListAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.databinding.FragmentDeliveryDetailsBinding;

import java.util.ArrayList;

public class DeliveryDetailsFragment extends BaseFragment implements  OrderDeatilsListAdapter.OnItemClickListener, View.OnClickListener  {

    private FragmentDeliveryDetailsBinding binding;
    private AppCompatActivity activity;
    private View rootView;

    private ArrayList<ItemModel> itemModelArrayList = new ArrayList<>();
    private OrderDeatilsListAdapter orderDeatilsListAdapter;

    public boolean isCollection=false;
    public boolean isCompleteOrder=false;


    public DeliveryDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!= null)
        {
            isCollection = getArguments().getBoolean("isCollection");
            isCompleteOrder = getArguments().getBoolean("isCompleteOrder");

        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery_details, container, false);
        activity = ApplicationClass.getmInstance().getActivity();
        rootView = binding.getRoot();
        initComponents(rootView);
        setUpToolBar();
        return rootView;
    }

    private void setUpToolBar()
    {
        if(isCompleteOrder)
        {
            ((MainActivity)activity).setUpToolBar(getResources().getString(R.string.order_details),false,true);


        }else{
            ((MainActivity)activity).setUpToolBar(getResources().getString(R.string.delivery_details),false,true);

        }
    }

    @Override
    public void initComponents(View rootView) {

        binding.btnDeliver.setOnClickListener(this);

        if(isCollection){

            binding.btnDeliver.setText(activity.getResources().getString(R.string.handover_item));
            binding.tvDate.setVisibility(View.GONE);
            binding.llDeliveryBy.setVisibility(View.VISIBLE);

        }
        if(isCompleteOrder)
        {
            binding.btnDeliver.setVisibility(View.GONE);
            binding.tvDate.setVisibility(View.VISIBLE);
            binding.llDeliveryBy.setVisibility(View.GONE);

        }

        for (int i = 0; i < 3; i++) {
            itemModelArrayList.add(new ItemModel());

        }
        binding.rvList.setLayoutManager(new LinearLayoutManager(activity));
        orderDeatilsListAdapter = new OrderDeatilsListAdapter(activity, itemModelArrayList);
        orderDeatilsListAdapter.setOnItemClickListener(this);
        binding.rvList.setAdapter(orderDeatilsListAdapter);

    }


    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch (id){
            case R.id.btnDeliver:

                Utils.addNextFragment(activity,new CompletedOrdersFragment(),DeliveryDetailsFragment.this,false);

                break;
        }
    }

    @Override
    public void onItemClick(View view, ItemModel viewModel) {


    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            setUpToolBar();
        }
    }



}
