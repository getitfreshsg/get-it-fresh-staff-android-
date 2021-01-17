package com.wetmarket.staff.Util;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.wetmarket.staff.R;

public class Utils {
    public static Fragment curFragment;

    public static void addNextFragment(AppCompatActivity mActivity, Fragment targetedFragment, Fragment shooterFragment, boolean isDownToUp) {
        final FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();


        if (isDownToUp) {
            transaction.setCustomAnimations(R.animator.slide_fragment_vertical_right_in, R.animator.slide_fragment_vertical_left_out, R.animator.slide_fragment_vertical_left_in,
                    R.animator.slide_fragment_vertical_right_out);

            //FragmentTransactionExtended fragmentTransactionExtended = new FragmentTransactionExtended(mActivity, transaction, shooterFragment, targetedFragment, R.id.activity_menubar_containers);
            //fragmentTransactionExtended.addTransition(FragmentTransactionExtended.SLIDE_VERTICAL);

        } else {
//            transaction.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in, R.animator.slide_fragment_horizontal_left_out, R.animator.slide_fragment_horizontal_left_in,
//                    R.animator.slide_fragment_horizontal_right_out);

            transaction.setCustomAnimations(R.animator.slide_fragment_horizontal_right_in, R.animator.slide_fragment_horizontal_left_out, R.animator.slide_fragment_horizontal_left_in,
                    R.animator.slide_fragment_horizontal_right_out);

            //FragmentTransactionExtended fragmentTransactionExtended = new FragmentTransactionExtended(mActivity, transaction, shooterFragment, targetedFragment, R.id.activity_menubar_containers);
            //fragmentTransactionExtended.addTransition(FragmentTransactionExtended.SLIDE_HORIZONTAL);
        }


        transaction.add(R.id.frame_mainLayout,targetedFragment,targetedFragment.getClass().getSimpleName());
        //curFragment = targetedFragment;
        transaction.hide(shooterFragment);
        transaction.addToBackStack(targetedFragment.getClass().getSimpleName());
        transaction.commit();
    }



}
