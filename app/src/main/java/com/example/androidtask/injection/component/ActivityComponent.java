package com.example.androidtask.injection.component;

import com.example.androidtask.injection.PerActivity;
import com.example.androidtask.injection.module.ActivityModule;
import com.example.androidtask.ui.home.HomeFragmentTask;
import com.example.androidtask.ui.main.MainActivity;


import dagger.Subcomponent;


/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(HomeFragmentTask homeFragmentTask);
}
