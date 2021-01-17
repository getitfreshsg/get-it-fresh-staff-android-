package com.wetmarket.staff.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wetmarket.staff.Activity.MainActivity;

import com.wetmarket.staff.Adapter.CollectionItemListCheckAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.FragmentCollctionPurchaseDetailBinding;

import java.util.ArrayList;

public class CollectionPurchaseDetailFragment extends Fragment  implements CollectionItemListCheckAdapter.OnItemClickListener,View.OnClickListener{

    private FragmentCollctionPurchaseDetailBinding binding;
    private AppCompatActivity activity;
    private View rootView;
    private ArrayList<ItemModel> itemModelArrayList = new ArrayList<>();
    private CollectionItemListCheckAdapter adapter;

    private Dialog dialog;
    private Button btnOk;

    public CollectionPurchaseDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_collction_purchase_detail, container, false);
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

        for(int i=0;i<3;i++){
            itemModelArrayList.add(new ItemModel());
        }




        binding.rvPurchaseList.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new CollectionItemListCheckAdapter(activity,itemModelArrayList);
        adapter.setOnItemClickListener(this);
        binding.rvPurchaseList.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id) {
            case R.id.ivBack:
                break;
            case R.id.btnCollecItems:
                break;

        }

    }

    @Override
    public void onItemClick(View view, ItemModel viewModel) {

    }








}