package com.example.androidtask.ui.home;

import com.example.androidtask.data.model.HomeResponseModel;
import com.example.androidtask.ui.base.MvpView;

public interface HomeMvpView extends MvpView {
    void returnHomeData(HomeResponseModel homeResponseModel);
}
