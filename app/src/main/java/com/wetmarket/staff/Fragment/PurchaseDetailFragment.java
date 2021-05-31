package com.wetmarket.staff.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wetmarket.staff.Activity.EditProfileActivity;
import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.ItemListAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.ImageUtils;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.StringUtils;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.base.BaseActivity;
import com.wetmarket.staff.base.BasePresenterInterface;
import com.wetmarket.staff.base.BaseViewInterface;
import com.wetmarket.staff.base.Presenter;
import com.wetmarket.staff.databinding.FragmentPurchaseDetailBinding;
import com.wetmarket.staff.retrofit.ApiCallInterface;
import com.wetmarket.staff.retrofit.ParametersInterface;
import com.wetmarket.staff.retrofit.model.OrderModel;
import com.wetmarket.staff.retrofit.model.ProductModel;

import java.util.ArrayList;
import java.util.HashMap;

public class PurchaseDetailFragment extends BaseActivity implements ItemListAdapter.OnItemClickListener, View.OnClickListener, BaseViewInterface {
    private FragmentPurchaseDetailBinding binding;
    private ItemListAdapter adapter;
    private OrderModel orderModel;
    private BasePresenterInterface presenterInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_purchase_detail);
        lableModel = new MyPref(this).getLableData();
        binding.setLable(lableModel);
        presenterInterface = new Presenter(this);
        initComponents();
    }

    public void initComponents() {
        if (getIntent() != null && getIntent().hasExtra("order")) {
            orderModel = (OrderModel) getIntent().getSerializableExtra("order");
        }
        binding.btnAccept.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
        binding.rvPurchaseList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemListAdapter(this, orderModel.getOrderproduct());
        adapter.setOnItemClickListener(this);
        binding.rvPurchaseList.setAdapter(adapter);
        binding.tvOrderID.setText(orderModel.getOrder_no());
        binding.tvStaffName.setText(orderModel.getUser_name());
        binding.tvStaffNumber.setText(orderModel.getUser_phone_number());
        binding.tvTotalAmount.setText("$" + orderModel.getMain_total());
        binding.tvPrice.setText("$" + orderModel.getMain_total());
        binding.tvWetMarketName.setText(orderModel.getWetmarket().getName());
        if (StringUtils.isNotEmpty(orderModel.getUser_pic())) {
            ImageUtils.loadImage(this, orderModel.getUser_pic(), R.drawable.ic_user_placeholder_new, binding.ivProfile);
        }
        if (orderModel.getPurchaser_flag().equalsIgnoreCase("1")) {
            binding.btnAccept.setText(lableModel.getTxtAccept());
        } else if (orderModel.getPurchaser_flag().equalsIgnoreCase("2")) {
            binding.btnAccept.setText(lableModel.getTxtHandoverItem());
            adapter.setCheck(true);
        }

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnAccept:
                if (orderModel.getPurchaser_flag().equalsIgnoreCase("1")) {
                    acceptOrder();
                } else if (orderModel.getPurchaser_flag().equalsIgnoreCase("2")) {
                    binding.btnAccept.setText(lableModel.getTxtHandoverItem());
                }
                break;
            case R.id.ivBack:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onItemClick(View view, ProductModel viewModel) {

    }

    @Override
    public void retry(int pos) {

    }

    @Override
    public void onError(String errorMsg, int requestCode, int resultCode) {

    }

    @Override
    public void onSuccess(Object success, int requestCode, int resultCode) {

        if (requestCode == ApiCallInterface.ORDER_ACCEPT) {
            orderModel.setPurchaser_flag("2");
            binding.btnAccept.setText(lableModel.getTxtHandoverItem());
            adapter.setCheck(true);
        }

    }

    private void acceptOrder() {
        HashMap<String, String> map = new HashMap<>();
        map.put(ParametersInterface.ORDER_LIST.order_id.name(), orderModel.get_id());
        presenterInterface.sendRequest(context, "", map, ApiCallInterface.ORDER_ACCEPT, true);
    }
}