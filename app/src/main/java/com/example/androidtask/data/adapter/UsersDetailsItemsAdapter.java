package com.example.androidtask.data.adapter;

import android.app.Activity;
import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidtask.R;


import java.util.List;

public class UsersDetailsItemsAdapter extends RecyclerView.Adapter<UsersDetailsItemsAdapter.MyViewHolder> {

    protected  ItemListenerOfItems itemListener;
    Context mContext;
    private List<String> imgList;
    DisplayMetrics displaymetrics ;



    public UsersDetailsItemsAdapter(Context context, List<String> imgList){

        mContext=context;
        this.imgList = imgList;
//        this.itemListener = itemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.users_details_items_row, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final String singleModel = imgList.get(position);

//        holder.user_parent_details_img.setVisibility(View.VISIBLE);

        holder.user_details_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, holder.getAdapterPosition()+1+"", Toast.LENGTH_SHORT).show();
            }
        });

        CircularProgressDrawable circularProgressDrawable =  new CircularProgressDrawable(mContext);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        if (singleModel!=null) {
            Glide.with(mContext)
                    .load(singleModel)
                    .apply(new RequestOptions()
//                            .placeholder(R.raw.loading_img).error(R.drawable.no_image)
                            .placeholder(circularProgressDrawable).error(R.drawable.no_image)
                    )
                    .into(holder.user_details_img);
        }

        if (imgList!=null) {
            if (imgList.size() % 2 != 0) {
                displaymetrics = new DisplayMetrics();
                ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int devicewidth = displaymetrics.widthPixels;
                int deviceheight = displaymetrics.heightPixels / 2;
                if (position == 0) {
                    holder.user_details_img.getLayoutParams().height=deviceheight+60;
//                    holder.user_details_img.getLayoutParams().width=holder.user_details_img.getLayoutParams().width*2;
//                    holder.user_details_img.getLayoutParams().width = devicewidth;
//                    holder.user_details_img.getLayoutParams().height = deviceheight;
//                    Log.e("deviceheight",deviceheight+"");
//                    Log.e("devicewidth",devicewidth+"");

                }else {
//                    holder.user_details_img.getLayoutParams().width = (devicewidth/2);
//                    holder.user_details_img.getLayoutParams().height = (deviceheight/2)-30;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView user_details_img ;
        ConstraintLayout constraint_layout;


        public MyViewHolder(View itemView) {
            super(itemView);

            user_details_img =  itemView.findViewById(R.id.user_details_img);
        }

    }



    public void updateList(List<String> list){
        imgList = list;
        notifyDataSetChanged();
    }

    public interface ItemListenerOfItems {
        void onItemsClickFromAdapter(int position);
    }
}


