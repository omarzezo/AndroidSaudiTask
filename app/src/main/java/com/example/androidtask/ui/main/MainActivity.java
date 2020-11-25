package com.example.androidtask.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.example.androidtask.R;
import com.example.androidtask.ui.base.BaseActivity;
import com.example.androidtask.ui.home.HomeFragmentTask;


import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @BindView(R.id.am_frame_container)
    FrameLayout am_frame_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityComponent().inject(this);
        ButterKnife.bind(this);

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        openFragment(HomeFragmentTask.class,null);

    }

    public void openFragment(Class fragmentClass, Object myObject) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.am_frame_container, fragment).commit();
    }
}
