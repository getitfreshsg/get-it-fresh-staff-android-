package com.wetmarket.staff.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wetmarket.staff.R;
import com.wetmarket.staff.Util.MyPref;
import com.wetmarket.staff.Util.OnItemClickListener;
import com.wetmarket.staff.Util.Utility;
import com.wetmarket.staff.databinding.RowOrderListBinding;
import com.wetmarket.staff.databinding.RowPurchaseCheckBinding;
import com.wetmarket.staff.retrofit.model.LableModel;
import com.wetmarket.staff.retrofit.model.OrderModel;

import java.util.ArrayList;
import java.util.List;


public class JobsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderModel> list;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private RowPurchaseCheckBinding binding;


    public JobsListAdapter(Context context) {
        this.list = new ArrayList<>();
        this.mContext = context;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_purchase_check, parent, false);
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


        private RowPurchaseCheckBinding binding;


        public ViewHolderData(RowPurchaseCheckBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

        public void bindData(final OrderModel item, final int position) {
            LableModel lableModel = new MyPref(mContext).getLableData();
            binding.tvOrderNumber.setText("Order " + item.getOrder_no());
            //binding.tvDeliveryBy.setText("Delivery by " + item.getUser_name());
            binding.tvDate.setText("" + item.getDelivery_date());


            if (item.getPurchaser_flag().equalsIgnoreCase("1")) {
                binding.tvOrderStatus.setText(lableModel.getTxtNew());
                binding.tvDeliveryBy.setText(lableModel.getTxtWaitingForPurchase());
                binding.tvOrderStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                binding.ivOrderImage.setImageResource(R.drawable.ic_new_order);
            } else if (item.getPurchaser_flag().equalsIgnoreCase("2")) {
                binding.tvOrderStatus.setText(lableModel.getTxtPurchaseAccepted());
                binding.tvDeliveryBy.setText(lableModel.getTxtAcceptedBy() + " " + item.getPurchased_by().getFull_name());
                binding.ivOrderImage.setImageResource(R.drawable.ic_purchase_accepted_order);
                binding.tvOrderStatus.setTextColor(mContext.getResources().getColor(R.color.blue_color_dark));
            } /*else if (item.getClient_order_status().equalsIgnoreCase("3")) {
                binding.tvOrderStatus.setText(lableModel.getTxtReadyForDelivery());
                binding.tvOrderStatus.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else if (item.getClient_order_status().equalsIgnoreCase("4")) {
                binding.tvOrderStatus.setText(lableModel.getTxtDelivered());
                binding.tvOrderStatus.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else if (item.getClient_order_status().equalsIgnoreCase("5")) {
                binding.tvOrderStatus.setText(lableModel.getTxtReadyToCollect());
                binding.tvOrderStatus.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            } else if (item.getClient_order_status().equalsIgnoreCase("6")) {
                binding.tvOrderStatus.setText(lableModel.getTxtCollected());
                binding.tvOrderStatus.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            }
*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        if (item.getPurchaser_flag().equalsIgnoreCase("1")) {
                            onItemClickListener.onItemClick(position, 1, item);
                        }
                        if (item.getPurchaser_flag().equalsIgnoreCase("2")) {
                            onItemClickListener.onItemClick(position, 2, item);
                        }

                    }

                }
            });

        }
    }

}


