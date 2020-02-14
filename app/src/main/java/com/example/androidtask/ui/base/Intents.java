package com.example.androidtask.ui.base;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class Intents {


    public static void showProgressDialog(DialogFragment currentDialog, String tagName,
                                          Object context) {

        addFragment(context, currentDialog, tagName);
    }

    private static <T extends Fragment> void addFragment(@NonNull final Object context,
                                                         @NonNull T fragment, String tagName) {
        FragmentManager fragmentManager = getFragmentManager(context);
        if (fragmentManager == null)
            return;

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(fragment, tagName);
        transaction.commitAllowingStateLoss();
    }

    public static void hideProgressDialog(String tagName, Object context) {
        if (null == context)
            return;
        FragmentManager fragmentManager = getFragmentManager(context);
        if (fragmentManager == null)
            return;

        DialogFragment transparentDialog = (DialogFragment) fragmentManager.findFragmentByTag(tagName);
        if (transparentDialog == null || !transparentDialog.isAdded()) {
            return;
        }

        transparentDialog.dismissAllowingStateLoss();
    }


    private static FragmentManager getFragmentManager(Object context) {
        if (context instanceof FragmentActivity)
            return ((FragmentActivity) context).getSupportFragmentManager();
        else if (((Fragment) context).isAdded())
            return ((Fragment) context).getChildFragmentManager();

        return null;
    }
}
