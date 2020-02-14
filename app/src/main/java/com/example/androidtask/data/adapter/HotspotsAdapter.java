package com.example.androidtask.data.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidtask.R;
import com.example.androidtask.data.model.HomeResponseModel;

import java.util.List;

public class HotspotsAdapter extends RecyclerView.Adapter<HotspotsAdapter.MyViewHolder> {

    protected  ItemListenerOfItems itemListener;
    Context mContext;
    private List<HomeResponseModel.DataBean.HotSpotsBean> hotSpotsBeanList;

    public HotspotsAdapter(Context context, List<HomeResponseModel.DataBean.HotSpotsBean> hotSpotsBeanList){

        mContext=context;
        this.hotSpotsBeanList = hotSpotsBeanList;
//        this.itemListener = itemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.home_items_row, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final HomeResponseModel.DataBean.HotSpotsBean singleModel = hotSpotsBeanList.get(position);


        if (singleModel.getName()!=null) {
            holder.title_txt.setText(singleModel.getName());
        }
        if (singleModel.getAddress()!=null) {
            holder.description2_txt.setText(singleModel.getAddress());
        }
//        if (singleModel.getProfile_photo()!=null) {
//            Glide.with(mContext)
//                    .load(singleModel.getProfile_photo())
//                    .apply(new RequestOptions()
//                            .placeholder(R.drawable.no_image).error(R.drawable.no_image)
//                    )
//                    .into(holder.img_banner);
//        }


    }

    @Override
    public int getItemCount() {
        return hotSpotsBeanList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img_banner;
        TextView title_txt,description2_txt;

        public MyViewHolder(View itemView) {
            super(itemView);

            img_banner =  itemView.findViewById(R.id.img_banner);
            title_txt =  itemView.findViewById(R.id.title_txt);
            description2_txt =  itemView.findViewById(R.id.description2_txt);
        }

    }

    public void updateList(List<HomeResponseModel.DataBean.HotSpotsBean> list){
        hotSpotsBeanList = list;
        notifyDataSetChanged();
    }

    public interface ItemListenerOfItems {
        void onItemsClickFromAdapter(int position);
    }
}

