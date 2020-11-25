package com.example.androidtask.util;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.example.androidtask.R;

import es.dmoral.toasty.Toasty;


public final class ViewUtil {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

    public static void configToasty(Context context) {
        Toasty.Config.getInstance()
                .setErrorColor(context.getResources().getColor(R.color.colorAccent))
                .setSuccessColor(context.getResources().getColor(R.color.colorAccent))
                .setWarningColor(context.getResources().getColor(R.color.colorAccent))
                .setTextColor(context.getResources().getColor(R.color.colorAccent))
                .setTextSize(13)
                .apply();
    }

    public static boolean canConnect(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showToast( Context context,String message) {
        Toasty.warning( context,message, Toast.LENGTH_SHORT, true).show();

    }
    public static void hideKeyBoard(Context context,View view) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
