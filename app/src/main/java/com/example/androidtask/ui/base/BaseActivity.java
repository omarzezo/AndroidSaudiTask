package com.example.androidtask.ui.base;

import android.os.Bundle;
import androidx.collection.LongSparseArray;
import androidx.appcompat.app.AppCompatActivity;

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
public class BaseActivity extends AppCompatActivity {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent>
            sComponentsMap = new LongSparseArray<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

        if (configPersistentComponent == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mActivityId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(Application.get(this).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
        ViewUtil.configToasty(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", mActivityId);
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
    }

    @Override
    public void onStop() {
        ViewUtil.hideKeyboard(this);
        super.onStop();
    }

//    @Override
    public boolean isNetworkConnected() {
        return ViewUtil.canConnect(this);
    }
    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

    public void showLoaderDialog(){
        Intents.showProgressDialog(DTDialog.newInstance(), "callback", this);
    }

    public void hideLoaderDialog(){
        Intents.hideProgressDialog("callback", this);
    }
}
