package com.example.androidtask.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.collection.LongSparseArray;
import android.view.View;


import com.example.androidtask.data.Application;
import com.example.androidtask.injection.component.ActivityComponent;
import com.example.androidtask.injection.component.ConfigPersistentComponent;
import com.example.androidtask.injection.component.DaggerConfigPersistentComponent;
import com.example.androidtask.injection.module.ActivityModule;
import com.example.androidtask.util.ViewUtil;

import java.util.concurrent.atomic.AtomicLong;

import timber.log.Timber;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public class BaseFragment extends Fragment {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent>
            sComponentsMap = new LongSparseArray<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;
    public Context context;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

        if (configPersistentComponent == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mActivityId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(Application.get(getActivity()).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(getActivity()));

    }

    public void showLoaderDialog() {
        Intents.showProgressDialog(DTDialog.newInstance(), "callback", this);
    }


    @Override
    public void onStop() {
        ViewUtil.hideKeyboard((Activity) context);
        super.onStop();
    }

    public void hideLoaderDialog() {
        Intents.hideProgressDialog("callback", this);
    }



    public boolean isNetworkConnected() {
        return ViewUtil.canConnect(getActivity());
    }
    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

}
