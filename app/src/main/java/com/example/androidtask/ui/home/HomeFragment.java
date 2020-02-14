package com.example.androidtask.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akshay.library.CurveBottomBar;
import com.example.androidtask.R;
import com.example.androidtask.data.adapter.AttractionsAdapter;
import com.example.androidtask.data.adapter.EventsAdapter;
import com.example.androidtask.data.adapter.HotspotsAdapter;
import com.example.androidtask.data.model.HomeResponseModel;
import com.example.androidtask.ui.HomeDetails.HomeDetailsActivity;
import com.example.androidtask.ui.base.BaseFragment;
import com.example.androidtask.util.Constants;
import com.example.androidtask.util.NetworkUtil;
import com.example.androidtask.util.ViewUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;

public class HomeFragment extends BaseFragment implements HomeMvpView {

    View mView;
    @BindView(R.id.hotspots_data_rv)
    RecyclerView hotspots_data_rv;

    @BindView(R.id.events_data_rv)
    RecyclerView events_data_rv;

    @BindView(R.id.attractions_data_rv)
    RecyclerView attractions_data_rv;

     @BindView(R.id.hotsposts_view_all_txt)
     TextView hotsposts_view_all_txt;

    @BindView(R.id.events_view_all_txt)
    TextView events_view_all_txt;

    @BindView(R.id.attractions_view_all_txt)
    TextView attractions_view_all_txt;


   public static HotspotsAdapter hotspotsAdapter;
    public static EventsAdapter eventsAdapter;
    public static AttractionsAdapter attractionsAdapter;

    @Inject
    HomePresenter homePresenter;

    public static List<HomeResponseModel.DataBean.HotSpotsBean> hotSpotsBeanList=new ArrayList<>();
    public static List<HomeResponseModel.DataBean.EventsBean> eventsBeanList=new ArrayList<>();
    public static List<HomeResponseModel.DataBean.AttractionsBean> attractionsBeanList=new ArrayList<>();

    OnDataPass dataPasser;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activityComponent().inject(this);
        homePresenter.attachView(this);

        if (savedInstanceState != null)
            return;

        ButterKnife.bind(this, view);
        if (getActivity()!=null)
        ViewUtil.hideKeyboard(getActivity());
        homePresenter.getHomeData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            // Inflate the layout for this fragment
            mView = inflater.inflate(R.layout.fragment_home, container, false);
            // Find and setup subviews
        } else {

        }
        return mView;
    }

    private void initHotspotsRecyclerView(List<HomeResponseModel.DataBean.HotSpotsBean> hotSpotsBeanList) {
        hotspots_data_rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        hotspots_data_rv.setItemAnimator(new DefaultItemAnimator());
        hotspots_data_rv.setNestedScrollingEnabled(false);
        hotspots_data_rv.setHasFixedSize(true);
        hotspots_data_rv.scrollToPosition(0);
        hotspotsAdapter = new HotspotsAdapter(getActivity(), hotSpotsBeanList);
        hotspots_data_rv.setAdapter(hotspotsAdapter);
    }

    private void initEventsRecyclerView(List<HomeResponseModel.DataBean.EventsBean> eventsBeanList) {
        events_data_rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        events_data_rv.setItemAnimator(new DefaultItemAnimator());
        events_data_rv.setNestedScrollingEnabled(false);
        events_data_rv.setHasFixedSize(true);
        events_data_rv.scrollToPosition(0);
        eventsAdapter = new EventsAdapter(getActivity(), eventsBeanList);
        events_data_rv.setAdapter(eventsAdapter);
    }

    private void initAttractionsRecyclerView(List<HomeResponseModel.DataBean.AttractionsBean> attractionsBeanList) {
        attractions_data_rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        attractions_data_rv.setItemAnimator(new DefaultItemAnimator());
        attractions_data_rv.setNestedScrollingEnabled(false);
        attractions_data_rv.setHasFixedSize(true);
        attractions_data_rv.scrollToPosition(0);
        attractionsAdapter = new AttractionsAdapter(getActivity(), attractionsBeanList );
        attractions_data_rv.setAdapter(attractionsAdapter);
    }


    @Override
    public void returnHomeData(HomeResponseModel homeResponseModel) {
        if (homeResponseModel.getData()!=null){
        if (homeResponseModel.getData().getHot_spots()!=null) {
            initHotspotsRecyclerView(homeResponseModel.getData().getHot_spots());
            hotSpotsBeanList=homeResponseModel.getData().getHot_spots();
            Log.e("DataForHotspots"+">>","DataForHotspots1");
        }
            if (homeResponseModel.getData().getEvents()!=null) {
                initEventsRecyclerView(homeResponseModel.getData().getEvents());
                eventsBeanList=homeResponseModel.getData().getEvents();
                Log.e("DataForEvents"+">>","DataForEvents2");
            }
            if (homeResponseModel.getData().getAttractions()!=null) {
                initAttractionsRecyclerView(homeResponseModel.getData().getAttractions());
                attractionsBeanList=homeResponseModel.getData().getAttractions();
                Log.e("DataForAttractions"+">>","DataForAttractions3");
            }
        }

        passData();
    }


    @OnClick(R.id.hotsposts_view_all_txt)
    void hotspotsDetailsOnClick() {
        if (getActivity()!=null)
        if (NetworkUtil.isNetworkAvailable(getActivity())) {
            if (hotSpotsBeanList!=null) {
                openActivity(Constants.HOTSPOTS, Constants.HOTSPOTS);
            }
        }
    }

    @OnClick(R.id.hotsposts_view_all_relative)
    void hotspotsDetailsRelOnClick() {
        if (getActivity()!=null)
        if (NetworkUtil.isNetworkAvailable(getActivity())) {
            if (hotSpotsBeanList!=null) {
                openActivity(Constants.HOTSPOTS, Constants.HOTSPOTS);
            }
        }
    }

    @OnClick(R.id.events_view_all_txt)
    void eventsDetailsOnClick() {
        if (getActivity()!=null)
        if (NetworkUtil.isNetworkAvailable(getActivity())) {
            if (eventsBeanList!=null) {
                openActivity(Constants.EVENTS, Constants.EVENTS);
            }
        }
    }

    @OnClick(R.id.view_all_relative_events)
    void eventsDetailsRelOnClick() {
        if (getActivity()!=null)
        if (NetworkUtil.isNetworkAvailable(getActivity())) {
            if (eventsBeanList!=null) {
                openActivity(Constants.EVENTS, Constants.EVENTS);
            }
        }
    }

    @OnClick(R.id.attractions_view_all_txt)
    void attractionsDetailsOnClick() {
        if (getActivity()!=null)
        if (NetworkUtil.isNetworkAvailable(getActivity())) {
            if (attractionsBeanList!=null) {
                openActivity(Constants.ATTRACTIONS, Constants.ATTRACTIONS);
            }
        }
    }

    @OnClick(R.id.view_all_relative_attractions)
    void attractionsDetailsRelOnClick() {
        if (getActivity()!=null)
        if (NetworkUtil.isNetworkAvailable(getActivity())) {
            if (attractionsBeanList!=null) {
                openActivity(Constants.ATTRACTIONS, Constants.ATTRACTIONS);
            }
        }
    }

    void openActivity(String key,String type){
        Intent intent =new Intent(getActivity(), HomeDetailsActivity.class);
        if (type.equals(Constants.HOTSPOTS)) {
            if (hotSpotsBeanList!=null) {
                intent.putExtra(key, (Serializable) hotSpotsBeanList);
            }
        }else if (type.equals(Constants.EVENTS)) {
            if (eventsBeanList!=null) {
                intent.putExtra(key, (Serializable) eventsBeanList);
            }
        }else if (type.equals(Constants.ATTRACTIONS)) {
            if (attractionsBeanList!=null) {
                intent.putExtra(key, (Serializable) attractionsBeanList);
            }
        }
        startActivity(intent);
    }

    public interface OnDataPass {
        void onDataPass(List<HomeResponseModel.DataBean.HotSpotsBean> hotSpotsBean
                ,List<HomeResponseModel.DataBean.EventsBean> eventsBean,List<HomeResponseModel.DataBean.AttractionsBean> attractionsBean);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
    public void passData() {
        if (dataPasser!=null) {
            if (hotSpotsBeanList!=null && eventsBeanList!=null && attractionsBeanList!=null) {
                dataPasser.onDataPass(hotSpotsBeanList, eventsBeanList, attractionsBeanList);
            }
        }
    }


   public static void filter(String text){
        List<HomeResponseModel.DataBean.HotSpotsBean> temp = new ArrayList();
        for(HomeResponseModel.DataBean.HotSpotsBean d: hotSpotsBeanList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getName().contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
        hotspotsAdapter.updateList(temp);
    }

    public static void filter2(String text){
        List<HomeResponseModel.DataBean.EventsBean> temp2 = new ArrayList();
        for(HomeResponseModel.DataBean.EventsBean d: eventsBeanList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getName().contains(text)){
                temp2.add(d);
            }
        }

        //update recyclerview
        eventsAdapter.updateList(temp2);
    }

    public static void filter3(String text){

        List<HomeResponseModel.DataBean.AttractionsBean> temp3 = new ArrayList();

        for(HomeResponseModel.DataBean.AttractionsBean d: attractionsBeanList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getName().contains(text)){
                temp3.add(d);
            }
        }
        attractionsAdapter.updateList(temp3);
    }
    @Override
    public void showEmpty() {
        hideLoader();
    }

    @Override
    public void showError() {
        hideLoader();
    }

    @Override
    public void showLoader() {
        showLoaderDialog();
    }

    @Override
    public void hideLoader() {
        hideLoaderDialog();
    }
}
