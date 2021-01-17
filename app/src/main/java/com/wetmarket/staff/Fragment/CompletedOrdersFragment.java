package com.wetmarket.staff.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.DemoListAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.CompletedOrdersModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.databinding.FragmentCompletedOrdersBinding;

import java.util.ArrayList;

public class CompletedOrdersFragment extends BaseFragment implements DemoListAdapter.OnItemClickListener {

    private FragmentCompletedOrdersBinding binding;
    private AppCompatActivity activity;
    private View rootView;

    private ArrayList<CompletedOrdersModel> itemModelArrayList = new ArrayList<>();

    private DemoListAdapter adapter ;


    public CompletedOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_orders, container, false);
        activity = ApplicationClass.getmInstance().getActivity();
        rootView = binding.getRoot();
        initComponents(rootView);
        setUpToolBar();
        return rootView;
    }

    private void setUpToolBar()
    {
        ((MainActivity)activity).setUpToolBar(getResources().getString(R.string.completed_orders),false,true);
    }

    @Override
    public void initComponents(View rootView) {

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


        DeliveryDetailsFragment deliveryDetailsFragment = new DeliveryDetailsFragment ();
        Bundle args = new Bundle();
        args.putBoolean("isCompleteOrder", true);
        deliveryDetailsFragment.setArguments(args);

        Utils.addNextFragment(activity, deliveryDetailsFragment,CompletedOrdersFragment.this,false);



    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            setUpToolBar();
        }
    }
}
