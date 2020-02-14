package com.example.androidtask.ui.HomeDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtask.R;

import com.example.androidtask.data.adapter.AttractionsAdapter;
import com.example.androidtask.data.adapter.EventsAdapter;
import com.example.androidtask.data.adapter.HotspotsAdapter;
import com.example.androidtask.data.model.HomeResponseModel;
import com.example.androidtask.ui.base.BaseActivity;
import com.example.androidtask.util.Constants;
import com.example.androidtask.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeDetailsActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.home_details_rv)
    RecyclerView home_details_rv;

    @BindView(R.id.title_txt)
    TextView title_txt;

    HotspotsAdapter hotspotsAdapter;
    EventsAdapter eventsAdapter;
    AttractionsAdapter attractionsAdapter;

    List<HomeResponseModel.DataBean.HotSpotsBean> hotSpotsBeanList=new ArrayList<>();
    List<HomeResponseModel.DataBean.EventsBean> eventsBeanList=new ArrayList<>();
    List<HomeResponseModel.DataBean.AttractionsBean> attractionsBeanList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.HOTSPOTS)){
            title_txt.setText("Hotspots");
            Log.e("ContentTyOe",1+"");
            hotSpotsBeanList= (List<HomeResponseModel.DataBean.HotSpotsBean>) intent.getSerializableExtra(Constants.HOTSPOTS);
            initHotspotsRecyclerView(hotSpotsBeanList);
        }else if (intent.hasExtra(Constants.EVENTS)){
            title_txt.setText("Events");
            Log.e("ContentTyOe",2+"");
            eventsBeanList= (List<HomeResponseModel.DataBean.EventsBean>) intent.getSerializableExtra(Constants.EVENTS);
            initEventsRecyclerView(eventsBeanList);
        }else if (intent.hasExtra(Constants.ATTRACTIONS)){
            title_txt.setText("Attractions");
            Log.e("ContentTyOe",3+"");
            attractionsBeanList= (List<HomeResponseModel.DataBean.AttractionsBean>) intent.getSerializableExtra(Constants.ATTRACTIONS);
            initAttractionsRecyclerView(attractionsBeanList);
        }
    }


    private void initHotspotsRecyclerView(List<HomeResponseModel.DataBean.HotSpotsBean> hotSpotsBeanList) {
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        home_details_rv.setLayoutManager(manager);
        home_details_rv.setItemAnimator(new DefaultItemAnimator());
        home_details_rv.setNestedScrollingEnabled(false);
        home_details_rv.setHasFixedSize(true);
        home_details_rv.scrollToPosition(0);
        hotspotsAdapter = new HotspotsAdapter(this, hotSpotsBeanList);
        home_details_rv.setAdapter(hotspotsAdapter);
    }

    private void initEventsRecyclerView(List<HomeResponseModel.DataBean.EventsBean> eventsBeanList) {
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        home_details_rv.setLayoutManager(manager);
        home_details_rv.setItemAnimator(new DefaultItemAnimator());
        home_details_rv.setNestedScrollingEnabled(false);
        home_details_rv.setHasFixedSize(true);
        home_details_rv.scrollToPosition(0);
        eventsAdapter = new EventsAdapter(this, eventsBeanList);
        home_details_rv.setAdapter(eventsAdapter);
    }

    private void initAttractionsRecyclerView(List<HomeResponseModel.DataBean.AttractionsBean> attractionsBeanList) {
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        home_details_rv.setLayoutManager(manager);
        home_details_rv.setItemAnimator(new DefaultItemAnimator());
        home_details_rv.setNestedScrollingEnabled(false);
        home_details_rv.setHasFixedSize(true);
        home_details_rv.scrollToPosition(0);
        attractionsAdapter = new AttractionsAdapter(this, attractionsBeanList );
        home_details_rv.setAdapter(attractionsAdapter);
    }

    @OnClick(R.id.img_back)
    void backOnClick() {
       onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
