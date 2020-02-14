package com.example.androidtask.data;


import android.annotation.SuppressLint;
import android.content.Context;

import io.paperdb.Paper;
import timber.log.Timber;


import com.example.androidtask.BuildConfig;
import com.example.androidtask.R;
import com.example.androidtask.injection.component.ApplicationComponent;
import com.example.androidtask.injection.component.DaggerApplicationComponent;
import com.example.androidtask.injection.module.ApplicationModule;
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.tripl3dev.prettystates.StatesConfigFactory;


public class Application extends android.app.Application {

    ApplicationComponent mApplicationComponent;
    @SuppressLint("StaticFieldLeak")
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        Paper.init(context);
        RxPaparazzo.register(this);
        StatesConfigFactory.Companion.intialize().initDefaultViews();
        StatesConfigFactory.Companion.get().setDefaultLoadingLayout(R.layout.fragment_loader);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }

    public static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
    public static Context getAppContext() {
        return context;
    }

}
