package com.wetmarket.staff.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.ItemListAdapter;
import com.wetmarket.staff.Adapter.ItemListCheckAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.FragmentPurchaseDetail2IncompleteBinding;

import java.util.ArrayList;

public class PurchaseDetail2IncompleteFragment extends Fragment  implements View.OnClickListener{

    private FragmentPurchaseDetail2IncompleteBinding binding;
    private AppCompatActivity activity;
    private View rootView;
    private ArrayList<ItemModel> itemModelArrayList = new ArrayList<>();
    private ArrayList<ItemModel> itemModelArrayListCheck = new ArrayList<>();

    private ItemListCheckAdapter itemListCheckAdapter;
    private ItemListAdapter adapter;


    boolean isPurchase=false;
    public PurchaseDetail2IncompleteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!= null)
        {
            isPurchase = getArguments().getBoolean("isPurchase");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_purchase_detail2_incomplete, container, false);
        activity = ApplicationClass.getmInstance().getActivity();
        rootView = binding.getRoot();
        initComponents(rootView);
        return rootView;
    }



    public void toolBar(){
        ((MainActivity)activity).setUpToolBar(getResources().getString(R.string.purchase_detail),false,true);

    }

    private void initComponents(View rootView) {

        toolBar();
        binding.btnHandoverItems.setOnClickListener(this);


        for(int i=0;i<3;i++){
            itemModelArrayList.add(new ItemModel());
        }

        for(int i=0;i<3;i++){
            itemModelArrayListCheck.add(new ItemModel());
        }

        binding.rvPurchaseList.setLayoutManager(new LinearLayoutManager(activity));
       // adapter = new ItemListAdapter(activity,itemModelArrayList);
        binding.rvPurchaseList.setAdapter(adapter);


        //checkedList
        binding.rvPurchaseListCheck.setLayoutManager(new LinearLayoutManager(activity));
        itemListCheckAdapter = new ItemListCheckAdapter(activity,itemModelArrayListCheck);
        binding.rvPurchaseListCheck.setAdapter(itemListCheckAdapter);


    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id) {
            case R.id.ivBack:
                break;

        }

    }










}