package com.wetmarket.staff.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wetmarket.staff.Model.PurchaseListModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.RowPurchaseCheckBinding;

import java.util.List;


public class PurchaseListCheckAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    private List<PurchaseListModel> purchaseListModelList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private RowPurchaseCheckBinding binding;


    public PurchaseListCheckAdapter(Context context, List<PurchaseListModel> items) {
        this.purchaseListModelList = items;
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_purchase_check, parent, false);
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


        ((ViewHolderData) holder).bindData(purchaseListModelList.get(position), position);

    }

    @Override
    public int getItemCount() {
        return purchaseListModelList.size();
    }

    @Override
    public void onClick(final View v) {
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (PurchaseListModel) v.getTag());
                }
            }, 200);
        }
    }


    public interface OnItemClickListener {

        void onItemClick(View view, PurchaseListModel viewModel);

    }

    protected class ViewHolderData extends RecyclerView.ViewHolder {


        private RowPurchaseCheckBinding binding;


        public ViewHolderData(RowPurchaseCheckBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

        public void bindData(final PurchaseListModel item, final int position) {

/*
            //for textView color

            if (item.getType().equalsIgnoreCase("Purchase Accepted")) {

                binding.tvOrderStatus.setTextColor(mContext.getResources().getColor(R.color.blue_color_dark));
                binding.ivOrderImage.setBackgroundResource((R.drawable.ic_purchase_accepted_order));

            }*/
            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    onItemClickListener.onItemClick(v, item);

                }
            });

        }
    }


}


