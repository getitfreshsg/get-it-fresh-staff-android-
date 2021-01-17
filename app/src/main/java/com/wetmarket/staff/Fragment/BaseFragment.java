package com.wetmarket.staff.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;


import com.wetmarket.staff.R;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private static int MAX_CLICK_INTERVAL = 3000;
    private long mLastClickTime = 0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        return rootView;
    }


    public abstract void initComponents(View rootView);


    @Override
    public void onClick(View v) {
        /**
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - mLastClickTime < MAX_CLICK_INTERVAL) {

            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
    }



    public void handleDoubleClick() {
        /**
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - mLastClickTime < MAX_CLICK_INTERVAL) {

            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
    }


}
