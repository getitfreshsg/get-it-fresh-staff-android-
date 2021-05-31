package com.wetmarket.staff.Fragment;

import android.content.Intent;
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
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.OnItemClickListener;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.base.BasePresenterInterface;
import com.wetmarket.staff.base.BaseViewInterface;
import com.wetmarket.staff.base.Presenter;
import com.wetmarket.staff.databinding.FragmentOrderBinding;
import com.wetmarket.staff.retrofit.ApiCallInterface;
import com.wetmarket.staff.retrofit.ParametersInterface;
import com.wetmarket.staff.retrofit.model.OrderModel;
import com.wetmarket.staff.retrofit.model.OrderStatusModel;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderFragment extends BaseFragment implements View.OnClickListener, BaseViewInterface {

    private FragmentOrderBinding binding;
    private AppCompatActivity activity;
    private View rootView;

    private ArrayList<ItemModel> itemModelArrayList = new ArrayList<>();
    private OrderListAdapter orderListAdapter;
    private BasePresenterInterface presenterInterface;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        activity = ApplicationClass.getmInstance().getActivity();
        presenterInterface = new Presenter(this);
        rootView = binding.getRoot();
        initComponents(rootView);
        return rootView;

    }

    @Override
    public void initComponents(View rootView) {

        binding.tvViewCompletedOrders.setOnClickListener(this);

        for (int i = 0; i <= 5; i++) {
            itemModelArrayList.add(new ItemModel());
        }
        binding.rvList.setLayoutManager(new LinearLayoutManager(activity));
        orderListAdapter = new OrderListAdapter(activity);
        binding.rvList.setAdapter(orderListAdapter);
        HashMap<String, String> map = new HashMap<>();
        map.put(ParametersInterface.ORDER_LIST.wetmarket_id.name(), new MyPref(getActivity()).getUserData().getWetmarket_id().get_id());
        map.put(ParametersInterface.ORDER_LIST.page.name(), "1");
        map.put(ParametersInterface.ORDER_LIST.perPage.name(), "20");
        presenterInterface.sendRequest(getActivity(), "", map, ApiCallInterface.OUT_DELIVERY_ORDER, true);
        orderListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, int which, Object object) {

                //Utils.addNextFragment(activity, new CompletedOrdersFragment(), OrderFragment.this, false);
            }
        });
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.tvViewCompletedOrders:

                startActivity(new Intent(getActivity(), CompletedOrdersFragment.class));

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

    public void setOnItemClick(ItemModel item, int position) {
        Utils.addNextFragment(activity, new DeliveryDetailsFragment(), OrderFragment.this, false);

    }


    @Override
    public void retry(int pos) {

    }

    @Override
    public void onError(String errorMsg, int requestCode, int resultCode) {

    }

    @Override
    public void onSuccess(Object success, int requestCode, int resultCode) {
        if (requestCode == ApiCallInterface.OUT_DELIVERY_ORDER) {
            OrderModel orderModel = (OrderModel) success;
            if (orderModel.getResult() != null) {
                orderListAdapter.setList(orderModel.getResult().getDocs());
            }
        }

    }
}
