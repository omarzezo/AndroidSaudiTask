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

public class AttractionsAdapter extends RecyclerView.Adapter<AttractionsAdapter.MyViewHolder> {

    protected  ItemListenerOfItems itemListener;
    Context mContext;
    private List<HomeResponseModel.DataBean.AttractionsBean> attractionsBeanList;

    public AttractionsAdapter(Context context, List<HomeResponseModel.DataBean.AttractionsBean> attractionsBeanList){

        mContext=context;
        this.attractionsBeanList = attractionsBeanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.home_items_row, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final HomeResponseModel.DataBean.AttractionsBean singleModel = attractionsBeanList.get(position);

        if (singleModel.getName()!=null) {
            holder.title_txt.setText(singleModel.getName());
        }
        if (singleModel.getDescription()!=null) {
            holder.description2_txt.setText(singleModel.getDescription());
        }
//        if (singleModel.getProfile_photo()!=null) {
//            Glide.with(mContext)
//                    .load(singleModel.getProfile_photo())
//                    .apply(new RequestOptions()
//                            .placeholder(R.drawable.big_header).error(R.drawable.big_header)
//                    )
//                    .into(holder.img_banner);
//        }
    }

    @Override
    public int getItemCount() {
        return attractionsBeanList.size();
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

    public interface ItemListenerOfItems {
        void onItemsClickFromAdapter(int position);
    }

    public void updateList(List<HomeResponseModel.DataBean.AttractionsBean> list){
        attractionsBeanList = list;
        notifyDataSetChanged();
    }
}

