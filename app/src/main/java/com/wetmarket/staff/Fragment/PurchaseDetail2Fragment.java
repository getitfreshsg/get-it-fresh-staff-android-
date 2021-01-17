package com.wetmarket.staff.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.ItemListCheckAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.FragmentPurchaseDetail2Binding;

import java.util.ArrayList;

public class PurchaseDetail2Fragment extends Fragment  implements ItemListCheckAdapter.OnItemClickListener,View.OnClickListener{

    private FragmentPurchaseDetail2Binding binding;
    private AppCompatActivity activity;
    private View rootView;
    private ArrayList<ItemModel> itemModelArrayList = new ArrayList<>();
    private ItemListCheckAdapter adapter;

    private Dialog dialog;
    private Button btnOk;

    boolean isPurchase=false;
    public PurchaseDetail2Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!= null)
        {
            isPurchase = getArguments().getBoolean("isPurchase");
        }

        Log.d("Job_purchase","Job_purchase"+isPurchase);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_purchase_detail2, container, false);
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


        if(isPurchase){
            binding.llPurchaser.setVisibility(View.GONE);
            binding.tvPurchaserReciept.setVisibility(View.GONE);
        }else{
            binding.llPurchaser.setVisibility(View.VISIBLE);
            binding.tvPurchaserReciept.setVisibility(View.VISIBLE);

        }





        for(int i=0;i<3;i++){
            itemModelArrayList.add(new ItemModel());
        }


        binding.rvPurchaseList.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new ItemListCheckAdapter(activity,itemModelArrayList);
        adapter.setOnItemClickListener(this);
        binding.rvPurchaseList.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id) {
            case R.id.ivBack:
                break;
            case R.id.btnHandoverItems:
                if(!isPurchase)
                {
                    showDialog();
                }else{


                    FragmentManager fm = activity.getSupportFragmentManager();
                    for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                        fm.popBackStack();
                    }

                }

                break;

        }

    }

    @Override
    public void onItemClick(View view, ItemModel viewModel) {

    }



    private void showDialog() {

        dialog = new Dialog(getActivity(), R.style.picture_dialog_style);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_handover);
        final WindowManager.LayoutParams wlmp = dialog.getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER;
        wlmp.width = WindowManager.LayoutParams.FILL_PARENT;
        wlmp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(wlmp);
        dialog.show();

        btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent i = new Intent(getActivity(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

    }





}