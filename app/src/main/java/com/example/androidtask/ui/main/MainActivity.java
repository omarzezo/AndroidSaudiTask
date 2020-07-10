package com.example.androidtask.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.akshay.library.CurveBottomBar;
import com.example.androidtask.R;
import com.example.androidtask.data.model.HomeResponseModel;
import com.example.androidtask.ui.Component.CustomFrameLayout;
import com.example.androidtask.ui.HomeDetails.HomeDetailsActivity;
import com.example.androidtask.ui.base.BaseActivity;
import com.example.androidtask.ui.home.HomeFragment;
import com.example.androidtask.util.Constants;
import com.example.androidtask.util.FloatingActionButton;
import com.example.androidtask.util.ViewUtil;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.androidtask.ui.home.HomeFragment.filter;
import static com.example.androidtask.ui.home.HomeFragment.filter2;
import static com.example.androidtask.ui.home.HomeFragment.filter3;

public class MainActivity extends BaseActivity implements CurveBottomBar.OnNavigationItemSelectedListener, HomeFragment.OnDataPass {

    @BindView(R.id.search_txt)
    EditText search_txt;

    @BindView(R.id.notification_img)
    ImageView notification_img;

    @BindView(R.id.am_frame_container)
    FrameLayout am_frame_container;

    @BindView(R.id.navigation)
    CurveBottomBar navigation;

//    @BindView(R.id.float_btn)
//    FloatingActionButton float_btn;

    CustomFrameLayout events ;
    CustomFrameLayout attractions ;
    CustomFrameLayout hotspots ;
    CustomFrameLayout map ;


    List<HomeResponseModel.DataBean.HotSpotsBean> hotSpotsBeanList=new ArrayList<>();
    List<HomeResponseModel.DataBean.EventsBean> eventsBeanList=new ArrayList<>();
    List<HomeResponseModel.DataBean.AttractionsBean> attractionsBeanList=new ArrayList<>();
    FloatingActionMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityComponent().inject(this);
        ButterKnife.bind(this);

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        navigation.setOnNavigationItemSelectedListener(this);
        navigation.getMenu().findItem(R.id.nav_home).setChecked(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

//        ViewUtil.hideKeyBoard(this,search_txt);

        events = new CustomFrameLayout(this,"Events",R.drawable.events_icon);
        attractions = new CustomFrameLayout(this,"Attractions",R.drawable.attarctions_icon);
        hotspots = new CustomFrameLayout(this,"Hotspots",R.drawable.hotspot_icon);
        map = new CustomFrameLayout(this,"Map",R.drawable.small_grey_location_pin);
        prepareFloatBtnLib();
        enlargeIconNav();
        openFragment(HomeFragment.class,null);

        actinCustomMenu();

        search_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // filter your list from your input
                filter(s.toString());
                filter2(s.toString());
                filter3(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });
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




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            ViewUtil.hideKeyboard(this);

//        setMenu(item.getItemId());
        switch (item.getItemId()) {


            case R.id.nav_home:
                closeDrawer();
                openFragment(HomeFragment.class,null);
                return true;


            case R.id.nav_search:
                closeDrawer();
                return true;


            case R.id.nav_cart:

                closeDrawer();
                return true;

            case R.id.nav_profile:
                closeDrawer();
                return true;

        }

        return false;

    }


    void enlargeIconNav(){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }

    void prepareFloatBtnLib(){
        ImageView not=new ImageView(this);
        not.setImageResource(R.drawable.notification_bottom_icon);
        FloatingActionButton fab = new FloatingActionButton.Builder(this)
                .setContentView(not).setPosition(FloatingActionButton.POSITION_BOTTOM_CENTER).setBackgroundDrawable(R.drawable.fab_background)
                .build();

        FrameLayout.LayoutParams tvParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        events.setLayoutParams(tvParams);
        attractions.setLayoutParams(tvParams);
        hotspots.setLayoutParams(tvParams);
        map.setLayoutParams(tvParams);
        circleMenu = new FloatingActionMenu.Builder(this)
                .setStartAngle(0) // A whole circle!
                .setEndAngle(-180)
//                .setRadius(getResources().getDimensionPixelSize(R.dimen.radius_large))
                .addSubActionView(map)
                .addSubActionView(attractions)
                .addSubActionView(events)
                .addSubActionView(hotspots)
                .attachTo(fab)
                .build();
    }
    void actinCustomMenu(){
       hotspots.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (hotSpotsBeanList!=null) {
                   circleMenu.close(true);
                   openActivity(Constants.HOTSPOTS, Constants.HOTSPOTS);
               }
           }
       });
       events.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (eventsBeanList!=null) {
                   circleMenu.close(true);
                   openActivity(Constants.EVENTS, Constants.EVENTS);
               }
           }
       });

        attractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (attractionsBeanList!=null) {
                    circleMenu.close(true);
                    openActivity(Constants.ATTRACTIONS, Constants.ATTRACTIONS);
                }
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hotSpotsBeanList!=null) {
                    circleMenu.close(true);
                    Toast.makeText(MainActivity.this, "Map", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void closeDrawer(){
        if (circleMenu.isOpen()){
            circleMenu.close(true);
        }
    }

    @Override
    public void onDataPass(List<HomeResponseModel.DataBean.HotSpotsBean> hotSpotsBean, List<HomeResponseModel.DataBean.EventsBean> eventsBean, List<HomeResponseModel.DataBean.AttractionsBean> attractionsBean) {
        hotSpotsBeanList=hotSpotsBean;
        eventsBeanList=eventsBean;
        attractionsBeanList=attractionsBean;
    }

    void openActivity(String key,String type){
        Intent intent =new Intent(this, HomeDetailsActivity.class);
        if (type.equals(Constants.HOTSPOTS)) {
            if (hotSpotsBeanList!=null) {
                intent.putExtra(key, (Serializable) hotSpotsBeanList);
            }
        }else if (type.equals(Constants.EVENTS)) {
            if (eventsBeanList!=null) {
                intent.putExtra(key, (Serializable) eventsBeanList);
            }
        }else if (type.equals(Constants.ATTRACTIONS)) {
            if (attractionsBeanList!=null) {
                intent.putExtra(key, (Serializable) attractionsBeanList);
            }
        }
        startActivity(intent);
    }
}
