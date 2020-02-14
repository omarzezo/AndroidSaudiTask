package com.example.androidtask.data.local.SharedPrefTool;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidtask.data.Application;


public class SharedPreferencesTool {

    public static final String fileName = Application.getAppContext().getPackageName();

    public static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        return editor;
    }

    /**
     * Reference from shared prefernce to read from it
     *
     * @param context app context
     */

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getInt(key, -1);
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(key, value);
        editor.apply();
    }

}
