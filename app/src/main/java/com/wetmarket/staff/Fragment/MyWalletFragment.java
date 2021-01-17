package com.wetmarket.staff.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wetmarket.staff.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWalletFragment extends BaseFragment {

    public MyWalletFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_wallet, container, false);
    }

    @Override
    public void initComponents(View rootView) {

    }
}
