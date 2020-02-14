package com.example.androidtask.data;


import com.example.androidtask.data.local.DatabaseHelper;
import com.example.androidtask.data.local.PreferencesHelper;
import com.example.androidtask.data.model.HomeResponseModel;
import com.example.androidtask.data.remote.RibotsService;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Observable;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }


    public Observable<HomeResponseModel> getHomeData() {
        return mRibotsService.getHomeData().distinct();
    }


}
