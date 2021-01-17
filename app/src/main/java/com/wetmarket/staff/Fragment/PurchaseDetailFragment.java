package com.wetmarket.staff.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.ItemListAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.databinding.FragmentPurchaseDetailBinding;

import java.util.ArrayList;

public class PurchaseDetailFragment extends BaseFragment  implements ItemListAdapter.OnItemClickListener ,View.OnClickListener{

    private FragmentPurchaseDetailBinding binding;
    private ItemListAdapter adapter;
    private AppCompatActivity activity;
    private View rootView;


    private ArrayList<ItemModel> purchaseListModelArrayList = new ArrayList<>();



    public PurchaseDetailFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_purchase_detail, container, false);
        activity = ApplicationClass.getmInstance().getActivity();
        rootView = binding.getRoot();
        initComponents(rootView);
        return rootView;


    }


    public void toolBar(){
        ((MainActivity)activity).setUpToolBar(getResources().getString(R.string.purchase_detail),false,true);

    }

    @Override
    public void initComponents(View rootView) {

        toolBar();

        binding.btnAccept.setOnClickListener(this);


        for(int i=0;i<5;i++){
            purchaseListModelArrayList.add(new ItemModel());

        }


        binding.rvPurchaseList.setLayoutManager(new LinearLayoutManager(activity));
        adapter=new ItemListAdapter(activity,purchaseListModelArrayList);
        adapter.setOnItemClickListener(this);
        binding.rvPurchaseList.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id=v.getId();
        switch (id){
            case R.id.btnAccept:
                Utils.addNextFragment(activity, new PurchaseDetail2Fragment(),PurchaseDetailFragment.this, false);
                break;

        }
    }

    @Override
    public void onItemClick(View view, ItemModel viewModel) {

    }
}