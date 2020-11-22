package com.example.androidtask.data.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidtask.R;
import com.example.androidtask.data.model.UsersPojoResponse;

import java.util.List;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.MyViewHolder> {

    protected  ItemListenerOfItems itemListener;
    Context mContext;
    private List<UsersPojoResponse.DataBean.UsersBean> usersDataBeans;
    UsersDetailsItemsAdapter usersDetailsItemsAdapter;

    public AllUsersAdapter(Context context, List<UsersPojoResponse.DataBean.UsersBean> usersDataBeans){

        mContext=context;
        this.usersDataBeans = usersDataBeans;
//        this.itemListener = itemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.users_items_row, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final UsersPojoResponse.DataBean.UsersBean singleModel = usersDataBeans.get(position);

        holder.user_name_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, holder.getAdapterPosition()+1+"", Toast.LENGTH_SHORT).show();
            }
        });

        if (singleModel.getName()!=null) {
            holder.user_name_txt.setText(singleModel.getName());
        }

        if (singleModel.getImage()!=null) {
            Glide.with(mContext)
                    .load(singleModel.getImage())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.no_image).error(R.drawable.no_image)
                    )
                    .into(holder.user_img);
        }
        if (singleModel.getItems()!=null){
            initRecyclerView(mContext,singleModel.getItems(),holder.users_items_rv);
        }

    }

    @Override
    public int getItemCount() {
        return usersDataBeans.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView user_img;
        TextView user_name_txt;
        RecyclerView users_items_rv;

        public MyViewHolder(View itemView) {
            super(itemView);

            user_img =  itemView.findViewById(R.id.user_img);
            user_name_txt =  itemView.findViewById(R.id.user_name_txt);
            users_items_rv =  itemView.findViewById(R.id.users_items_rv);
        }

    }


    private void initRecyclerView(Context context ,List<String> imgList,RecyclerView recyclerView) {
        GridLayoutManager linearLayoutManager = null;
        
        if (imgList!=null){
            if (imgList.size()%2==0){
                linearLayoutManager = new GridLayoutManager(context, 2);
            }else {
                 linearLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);

                linearLayoutManager.setSpanSizeLookup(
                        new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                // 2 column size for first row
                                return (position == 0 ? 2 : 1);
                            }
                        });
//                linearLayoutManager = new LinearLayoutManager(context);
//                recyclerView.setLayoutManager(linearLayoutManager);
            }
        }
//        linearLayoutManager = new GridLayoutManager(context, 2);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.scrollToPosition(0);
//        usersDetailsItemsAdapter = new UsersDetailsItemsAdapter(context, imgList);
//        recyclerView.setAdapter(usersDetailsItemsAdapter);
        
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.scrollToPosition(0);
        usersDetailsItemsAdapter = new UsersDetailsItemsAdapter(context, imgList);
        recyclerView.setAdapter(usersDetailsItemsAdapter);
    }


    public void updateList(List<UsersPojoResponse.DataBean.UsersBean> list){
        usersDataBeans = list;
        notifyDataSetChanged();
    }

    public interface ItemListenerOfItems {
        void onItemsClickFromAdapter(int position);
    }
}

