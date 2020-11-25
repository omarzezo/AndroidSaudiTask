package com.example.androidtask.ui.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


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
