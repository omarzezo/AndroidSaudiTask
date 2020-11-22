package com.example.androidtask.ui.home;

import com.example.androidtask.data.model.UsersPojoResponse;
import com.example.androidtask.ui.base.MvpView;

public interface HomeMvpView extends MvpView {
    void returnHomeData(UsersPojoResponse usersPojoResponse);
}
