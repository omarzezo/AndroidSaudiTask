package com.example.androidtask.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidtask.R;
import com.example.androidtask.data.adapter.AllUsersAdapter;
import com.example.androidtask.data.model.UsersPojoResponse;
import com.example.androidtask.ui.base.BaseFragment;
import com.example.androidtask.util.EndlessRecyclerViewScrollListener;
import com.example.androidtask.util.ViewUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragmentTask extends BaseFragment implements HomeMvpView {

    View mView;

    @BindView(R.id.all_users_rv)
    RecyclerView all_users_rv;


    public static AllUsersAdapter allUsersAdapter;
    List<UsersPojoResponse.DataBean.UsersBean> usersBeans = new ArrayList<>();
    boolean noDataToShow = false;
    int pageNumber = 0;

    @Inject
    HomePresenter homePresenter;

    public static List<UsersPojoResponse.DataBean.UsersBean> usersBeanList=new ArrayList<>();


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

        initRecyclerView(usersBeans);
        homePresenter.getHomeData(pageNumber,10);

        all_users_rv.addOnScrollListener(new EndlessRecyclerViewScrollListener(all_users_rv.getLayoutManager()) {
            @Override
            public void onLoadMore(final int page, final int totalItemsCount) {
                Log.e("void onLoadMore", "vvoid onLoadMore");
                if (noDataToShow)
                    return;
                homePresenter.getHomeData(pageNumber,10);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            // Inflate the layout for this fragment
            mView = inflater.inflate(R.layout.fragment_home_task, container, false);
            // Find and setup subviews
        } else {

        }
        return mView;
    }

    private void initRecyclerView(List<UsersPojoResponse.DataBean.UsersBean> usersBeanList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        all_users_rv.setLayoutManager(linearLayoutManager);
        all_users_rv.setItemAnimator(new DefaultItemAnimator());
        all_users_rv.setNestedScrollingEnabled(false);
        all_users_rv.setHasFixedSize(true);
        all_users_rv.scrollToPosition(0);
        allUsersAdapter = new AllUsersAdapter(getActivity(), usersBeanList);
        all_users_rv.setAdapter(allUsersAdapter);

    }

    @Override
    public void returnHomeData(UsersPojoResponse usersPojoResponse) {
//        if (usersPojoResponse.getData()!=null){
//            if (usersPojoResponse.getData().getUsers()!=null) {
//                initRecyclerView(usersPojoResponse.getData().getUsers());
//                usersBeanList=usersPojoResponse.getData().getUsers();
//                Log.e("DataForHotspots"+">>","DataForHotspots1");
//            }
//        }

        if (usersPojoResponse!=null) {
            if (usersPojoResponse.getData() != null) {
                if (usersPojoResponse.getData().getUsers().isEmpty()) {
                    noDataToShow = true;
                    return;
                }

                pageNumber++;
                usersBeans.addAll(usersPojoResponse.getData().getUsers());
                allUsersAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        dataPasser = (OnDataPass) context;
    }

    @Override
    public void showEmpty() {
        hideLoader();
//        if (getActivity()!=null)
//        Toast.makeText(getActivity(), "Threre is no enough Data", Toast.LENGTH_SHORT).show();
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
