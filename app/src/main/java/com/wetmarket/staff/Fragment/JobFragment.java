package com.wetmarket.staff.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.PurchaseListCheckAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.PurchaseListModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.databinding.FragmentJobBinding;

import java.util.ArrayList;


public class JobFragment extends BaseFragment implements PurchaseListCheckAdapter.OnItemClickListener {

    private FragmentJobBinding binding;
    private PurchaseListCheckAdapter adapter;
    private AppCompatActivity activity;
    private View rootView;

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
        initComponents(rootView);
        return rootView;
    }

    @Override
    public void initComponents(View rootView) {


        for (int i = 0; i < 5; i++) {
            purchaseListModelArrayList.add(new PurchaseListModel());
        }


        binding.rvPurchaseList.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new PurchaseListCheckAdapter(activity, purchaseListModelArrayList);
        adapter.setOnItemClickListener(this);
        binding.rvPurchaseList.setAdapter(adapter);

    }


    public void toolBar() {

        ((MainActivity) activity).setUpToolBar("", true, false);


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            toolBar();
        }
    }

    @Override
    public void onItemClick(View view, PurchaseListModel viewModel) {


/*
        if (viewModel.getType().equalsIgnoreCase("Purchase Accepted")) {

        PurchaseDetail2Fragment purchaseDetail2Fragment = new PurchaseDetail2Fragment ();
        Bundle args = new Bundle();
        args.putBoolean("isPurchase", true);
        purchaseDetail2Fragment.setArguments(args);
        Utils.addNextFragment(activity,purchaseDetail2Fragment, JobFragment.this, false);


        } else if (item.getType().equalsIgnoreCase("New")) {

            Utils.addNextFragment(activity, new PurchaseDetailFragment(), JobFragment.this, false);
        }

        */
        //temp
        Utils.addNextFragment(activity, new PurchaseDetailFragment(), JobFragment.this, false);


    }
}
