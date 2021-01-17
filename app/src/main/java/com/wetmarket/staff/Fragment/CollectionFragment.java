package com.wetmarket.staff.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wetmarket.staff.Activity.MainActivity;
import com.wetmarket.staff.Adapter.CollectionListAdapter;
import com.wetmarket.staff.ApplicationClass;
import com.wetmarket.staff.Model.CollectionListModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.Utils;
import com.wetmarket.staff.databinding.FragmentColletionBinding;

import java.util.ArrayList;


public class CollectionFragment extends BaseFragment implements CollectionListAdapter.OnItemClickListener, View.OnClickListener {

    private FragmentColletionBinding binding;
    private CollectionListAdapter adapter;
    private AppCompatActivity activity;
    private View rootView;


    private ArrayList<CollectionListModel> collectionListModelsList = new ArrayList<>();


    public CollectionFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_colletion, container, false);
        activity = ApplicationClass.getmInstance().getActivity();
        rootView = binding.getRoot();
        initComponents(rootView);
        return rootView;


    }


    public void toolBar() {
        ((MainActivity) activity).setUpToolBar("", true, false);

    }

    @Override
    public void initComponents(View rootView) {


        for (int i = 0; i < 5; i++) {
            collectionListModelsList.add(new CollectionListModel());

        }


        binding.rvCollectionList.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new CollectionListAdapter(activity, collectionListModelsList, this);
        adapter.setOnItemClickListener(this);
        binding.rvCollectionList.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        switch (id) {
            case R.id.btnAccept:


                break;

        }
    }

    @Override
    public void onItemClick(View view, CollectionListModel viewModel) {

/*        if(viewModel.getType().equalsIgnoreCase("Purchased"))
        {

            Utils.addNextFragment(activity,new CollectionPurchaseDetailFragment(),CollectionFragment.this,false);

        //InComplete
            if("Incomplete"){
                Utils.addNextFragment(activity,new PurchaseDetail2IncompleteFragment(),CollectionFragment.this,false);

            }

        }
        else if (item.getType().equalsIgnoreCase("Delivery Accepted") ){

        DeliveryDetailsFragment deliveryDetailsFragment = new DeliveryDetailsFragment ();
        Bundle args = new Bundle();
        args.putBoolean("isCollection", true);
        deliveryDetailsFragment.setArguments(args);

        Utils.addNextFragment(activity,deliveryDetailsFragment,CollectionFragment.this,false);

        }*/


        //temp
        Utils.addNextFragment(activity,new CollectionPurchaseDetailFragment(),CollectionFragment.this,false);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            toolBar();
        }
    }
}