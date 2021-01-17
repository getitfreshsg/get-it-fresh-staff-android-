package com.wetmarket.staff.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wetmarket.staff.Fragment.OrderFragment;
import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.RowOrderListBinding;

import java.util.List;


public class OrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<ItemModel> notificationList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private RowOrderListBinding binding;
    private OrderFragment orderFragment;


    public OrderListAdapter(OrderFragment orderFragment,Context context, List<ItemModel> items) {
        this.notificationList = items;
        this.mContext = context;
        this.orderFragment=orderFragment;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_order_list, parent, false);
        View v = binding.getRoot();
        v.setOnClickListener(this);
        return new ViewHolderData(binding);

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ((ViewHolderData) holder).bindData(notificationList.get(position), position);

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    @Override
    public void onClick(final View v) {
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (ItemModel) v.getTag());
                }
            }, 200);
        }
    }


    public interface OnItemClickListener {

        void onItemClick(View view, ItemModel viewModel);

    }

    protected class ViewHolderData extends RecyclerView.ViewHolder {


        private RowOrderListBinding binding;


        public ViewHolderData(RowOrderListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

        public void bindData(final ItemModel item, final int position) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   orderFragment.setOnItemClick(item,position);

                }
            });

        }
    }

}


