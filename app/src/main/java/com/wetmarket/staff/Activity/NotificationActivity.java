package com.wetmarket.staff.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wetmarket.staff.Adapter.NotificationListAdapter;
import com.wetmarket.staff.Model.NotificationModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.ActivityNotificationBinding;

import java.util.ArrayList;

public class NotificationActivity extends Activity implements NotificationListAdapter.OnItemClickListener , View.OnClickListener{

    private ActivityNotificationBinding binding;
    private Activity mActivity;


    private ArrayList<NotificationModel> notificationArrayList = new ArrayList<>();
    private NotificationListAdapter  notificationListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification);

        initComponet();
    }

    private void initComponet() {

        binding.ivBack.setOnClickListener(this);

        for(int i=0;i<5;i++){
            notificationArrayList.add(new NotificationModel());
        }

        binding.rvNotification.setLayoutManager(new LinearLayoutManager(mActivity));
        notificationListAdapter=new NotificationListAdapter(mActivity,notificationArrayList);
        notificationListAdapter.setOnItemClickListener(this);

        binding.rvNotification.setAdapter(notificationListAdapter);
    }




    @Override
    public void onItemClick(View view, NotificationModel viewModel) {

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.ivBack:
                finish();
                break;
        }

    }
    }





