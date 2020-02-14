package com.example.androidtask.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

import com.example.androidtask.data.DataManager;
import com.example.androidtask.data.SyncService;
import com.example.androidtask.data.local.DatabaseHelper;
import com.example.androidtask.data.local.PreferencesHelper;
import com.example.androidtask.data.remote.RibotsService;
import com.example.androidtask.injection.ApplicationContext;
import com.example.androidtask.injection.module.ApplicationModule;
import com.example.androidtask.util.RxEventBus;



@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();
    Application application();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
