package com.wetmarket.staff.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wetmarket.staff.Fragment.CollectionFragment;
import com.wetmarket.staff.Model.CollectionListModel;
import com.wetmarket.staff.R;
import com.wetmarket.staff.databinding.RowCollectionBinding;

import java.util.List;


public class CollectionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    private List<CollectionListModel> notificationList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private RowCollectionBinding binding;
    private CollectionFragment fragment;


    public CollectionListAdapter(Context context, List<CollectionListModel> items,CollectionFragment fragment) {
        this.notificationList = items;
        this.mContext = context;
        this.fragment = fragment;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_collection, parent, false);
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
                    onItemClickListener.onItemClick(v, (CollectionListModel) v.getTag());
                }
            }, 200);
        }
    }


    public interface OnItemClickListener {

        void onItemClick(View view, CollectionListModel viewModel);

    }

    protected class ViewHolderData extends RecyclerView.ViewHolder {


        private RowCollectionBinding binding;


        public ViewHolderData(RowCollectionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

        public void bindData(final CollectionListModel item, final int position) {

            //for textView color

/*            if (item.getType().equalsIgnoreCase("Delivery Accepted")) {
                binding.tvOrderStatus.setTextColor(mContext.getResources().getColor(R.color.blue_color_dark));
                binding.tvOrderStatusSub.setVisibility(View.GONE);
                binding.ivOrderImage.setBackgroundResource((R.drawable.ic_purchase_accepted_order));

            } else if (item.getType().equalsIgnoreCase("Purchased")) {

                //complete
                binding.tvOrderStatus.setTextColor(mContext.getResources().getColor(R.color.blue_color_dark));
                binding.tvOrderStatusSub.setVisibility(View.GONE);


                //incomplete
                binding.tvOrderStatusSub.setVisibility(View.VISIBLE);
                binding.tvOrderStatusSub.setTextColor(mContext.getResources().getColor(R.color.orange_color));
            }*/

            itemView.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){

            onItemClickListener.onItemClick(v, item);


        }
        });

    }
}



}


