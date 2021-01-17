package com.wetmarket.staff.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wetmarket.staff.Model.CompletedOrdersModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.RowCompletedOrdersBinding;

import java.util.List;


public class DemoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


        private List<CompletedOrdersModel> purchaseListModelList;
        private OnItemClickListener onItemClickListener;
        private Context mContext;
        private RowCompletedOrdersBinding binding;


        public DemoListAdapter(Context context, List<CompletedOrdersModel> items) {
            this.purchaseListModelList = items;
            this.mContext = context;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_completed_orders, parent, false);
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
                        onItemClickListener.onItemClick(v, (CompletedOrdersModel) v.getTag());
                    }
                }, 200);
            }
        }


        public interface OnItemClickListener {

            void onItemClick(View view, CompletedOrdersModel viewModel);

        }

        protected class ViewHolderData extends RecyclerView.ViewHolder {

            private RowCompletedOrdersBinding binding;


            public ViewHolderData(RowCompletedOrdersBinding binding) {
                super(binding.getRoot());
                this.binding = binding;


            }

            public void bindData(final CompletedOrdersModel item, final int position) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.onItemClick(v, item);

                }
            });

            }
        }


    }


