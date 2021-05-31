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
import com.wetmarket.staff.Adapter.JobsListAdapter;
import com.wetmarket.staff.Adapter.PurchaseListCheckAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.PurchaseListModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.OnItemClickListener;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.base.BasePresenterInterface;
import com.wetmarket.staff.base.BaseViewInterface;
import com.wetmarket.staff.base.Presenter;
import com.wetmarket.staff.databinding.FragmentJobBinding;
import com.wetmarket.staff.retrofit.ApiCallInterface;
import com.wetmarket.staff.retrofit.ParametersInterface;
import com.wetmarket.staff.retrofit.model.OrderModel;

import java.util.ArrayList;
import java.util.HashMap;


public class JobFragment extends BaseFragment implements BaseViewInterface {

    private FragmentJobBinding binding;
    private JobsListAdapter adapter;
    private AppCompatActivity activity;
    private View rootView;
    private BasePresenterInterface presenterInterface;
    private ArrayList<PurchaseListModel> purchaseListModelArrayList = new ArrayList<>();


    public JobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_job, container, false);
        activity = ApplicationClass.getmInstance().getActivity();
        rootView = binding.getRoot();
        presenterInterface = new Presenter(this);
        initComponents(rootView);
        return rootView;
    }

    @Override
    public void initComponents(View rootView) {


        binding.rvPurchaseList.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new JobsListAdapter(activity);
        binding.rvPurchaseList.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, int which, Object object) {
                Intent intent = new Intent(getActivity(), PurchaseDetailFragment.class);
                intent.putExtra("order", (OrderModel) object);
                startActivity(intent);


            }
        });
        getOrder();
    }

    @Override
    public void onResume() {
        super.onResume();
        getOrder();
    }

    private void getOrder() {
        HashMap<String, String> map = new HashMap<>();
        map.put(ParametersInterface.ORDER_LIST.wetmarket_id.name(), new MyPref(getActivity()).getUserData().getWetmarket_id().get_id());
        map.put(ParametersInterface.ORDER_LIST.page.name(), "1");
        map.put(ParametersInterface.ORDER_LIST.perPage.name(), "20");
        map.put(ParametersInterface.ORDER_LIST.search_text.name(), MainActivity.search_text);
        map.put(ParametersInterface.ORDER_LIST.order_status.name(), MainActivity.search_status);
        map.put(ParametersInterface.ORDER_LIST.date.name(), MainActivity.search_date);
        presenterInterface.sendRequest(getActivity(), "", map, ApiCallInterface.ORDER_LIST, true);
    }

    public void toolBar() {

        ((MainActivity) activity).setUpToolBar("", true, false);


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            toolBar();
            getOrder();
        }
    }


    @Override
    public void retry(int pos) {

    }

    @Override
    public void onError(String errorMsg, int requestCode, int resultCode) {

    }

    @Override
    public void onSuccess(Object success, int requestCode, int resultCode) {
        if (requestCode == ApiCallInterface.ORDER_LIST) {
            OrderModel orderModel = (OrderModel) success;
            if (orderModel.getResult() != null) {
                adapter.setList(orderModel.getResult().getDocs());
            }
        }
    }
}
