package com.wetmarket.staff.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.RowOrderDetailsListBinding;

import java.util.List;

public class OrderDeatilsListAdapter extends RecyclerView.Adapter<OrderDeatilsListAdapter.MyViewHolder> {
    private Context context;
    private List<ItemModel> list;
    private OnItemClickListener onItemClickListener;
    private int lastPostiopn = -1;

    //private EventDetailsFragmentNew eventDetailsFragmentNew;


    public OrderDeatilsListAdapter(Context context, List<ItemModel> list) {
        this.context = context;
        this.list = list;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowOrderDetailsListBinding mBinder = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.row_order_details_list, parent, false);
        View v = mBinder.getRoot();

        return new MyViewHolder(mBinder);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ((MyViewHolder) holder).bindData(list.get(position), position);


    }

    public void addData(List<ItemModel> commonModels) {
        list.addAll(commonModels);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface OnItemClickListener {

        void onItemClick(View view, ItemModel viewModel);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RowOrderDetailsListBinding mBinder;


        private MyViewHolder(RowOrderDetailsListBinding mBinder) {
            super(mBinder.getRoot());
            this.mBinder = mBinder;


        }

        public void bindData(ItemModel item, int position) {

        }
    }


}