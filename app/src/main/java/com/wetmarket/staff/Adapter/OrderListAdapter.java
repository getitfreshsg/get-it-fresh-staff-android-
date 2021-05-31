package com.wetmarket.staff.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.OnItemClickListener;
import com.wetmarket.staff.Util.Utility;
import com.wetmarket.staff.databinding.RowOrderListBinding;
import com.wetmarket.staff.retrofit.model.OrderModel;

import java.util.ArrayList;
import java.util.List;


public class OrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderModel> list;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private RowOrderListBinding binding;


    public OrderListAdapter(Context context) {
        this.list = new ArrayList<>();
        this.mContext = context;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_order_list, parent, false);
        View v = binding.getRoot();
        return new ViewHolderData(binding);

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);


    }

    public void setList(List<OrderModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        ((ViewHolderData) holder).bindData(list.get(position), position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    protected class ViewHolderData extends RecyclerView.ViewHolder {


        private RowOrderListBinding binding;


        public ViewHolderData(RowOrderListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

        public void bindData(final OrderModel item, final int position) {
            binding.tvOrderNumber.setText("Order " + item.getOrder_no());
            binding.tvDeliveryBy.setText("Delivery by " + item.getUser_name());
            binding.tvDate.setText("" + item.getDelivery_date());
            Utility.getOrderStatus(mContext, item.getEmployee_order_status());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, 1, item);
                    }

                }
            });

        }
    }

}


