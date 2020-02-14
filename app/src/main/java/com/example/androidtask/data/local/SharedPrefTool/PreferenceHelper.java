package com.example.androidtask.data.local.SharedPrefTool;

import android.content.Context;

public class PreferenceHelper {

    public static String TAG_OF_IMAGE_KIND = "image_kind";

    public static int getImageKind(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_OF_IMAGE_KIND);
    }

    public static void setImageKind(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_OF_IMAGE_KIND, value);
    }

}
