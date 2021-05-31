package com.wetmarket.staff.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wetmarket.staff.Model.ItemModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.RowItemBinding;
import com.wetmarket.staff.retrofit.model.ProductModel;

import java.util.List;


public class ItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<ProductModel> notificationList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private RowItemBinding binding;

    private boolean isCheck = false;


    public ItemListAdapter(Context context, List<ProductModel> items) {
        this.notificationList = items;
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setCheck(boolean check) {
        isCheck = check;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_item, parent, false);
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
                    onItemClickListener.onItemClick(v, (ProductModel) v.getTag());
                }
            }, 200);
        }
    }


    public interface OnItemClickListener {

        void onItemClick(View view, ProductModel viewModel);

    }

    protected class ViewHolderData extends RecyclerView.ViewHolder {


        private RowItemBinding binding;


        public ViewHolderData(RowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

        public void bindData(final ProductModel item, final int position) {
            binding.tvItemName.setText(item.getProduct_name() + "(" + item.getWeight() + "g)");
            Float subTotal = Float.parseFloat(item.getPrice()) * Integer.parseInt(item.getQuantity());
            binding.tvDetail1.setText("$" + subTotal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.onItemClick(v, item);


                }
            });

            if (isCheck) {
                binding.cbItem.setVisibility(View.VISIBLE);
                binding.cbItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        notificationList.get(position).setSelect(!notificationList.get(position).isSelect());
                    }
                });

            } else {
                binding.cbItem.setVisibility(View.GONE);
            }

        }
    }


}


