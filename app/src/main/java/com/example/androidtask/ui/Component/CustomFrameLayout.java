package com.example.androidtask.ui.Component;

import android.content.Context;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtask.R;

public class CustomFrameLayout extends FrameLayout {

    public ImageView events_img;
    public TextView events_txt;
    String text;
    int img;
    public CustomFrameLayout(@NonNull Context context ,String text ,int img) {
        this(context, null);
        initView(context);
        this.text=text;
        this.img=img;
        init();
    }

    public CustomFrameLayout(@NonNull Context context) {
        this(context, null);
    }
    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init(context);
    }


    public void initView(Context context){
        inflate(context, R.layout.custom_popup_layout, this);
        events_img = findViewById(R.id.events_img);
        events_txt = findViewById(R.id.events_txt);
    }

    private void init() {
        events_txt.setText(text);
        events_img.setImageResource(img);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
