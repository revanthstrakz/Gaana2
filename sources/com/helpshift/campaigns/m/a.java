package com.helpshift.campaigns.m;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.helpshift.campaigns.fragments.InboxFragment;
import com.helpshift.k.b;

public class a {
    public static void a(FragmentManager fragmentManager, int i, Fragment fragment, String str, String str2, boolean z) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        Fragment findFragmentById = fragmentManager.findFragmentById(i);
        if (!b.a().a.j.booleanValue()) {
            if (findFragmentById == null) {
                beginTransaction.setCustomAnimations(0, 0, 0, 0);
            } else {
                beginTransaction.setCustomAnimations(com.helpshift.e.a.hs__slide_in_from_right, com.helpshift.e.a.hs__slide_out_to_left, com.helpshift.e.a.hs__slide_in_from_left, com.helpshift.e.a.hs__slide_out_to_right);
            }
        }
        beginTransaction.replace(i, fragment, str);
        if (!TextUtils.isEmpty(str2)) {
            beginTransaction.addToBackStack(str2);
        }
        beginTransaction.commit();
        if (z) {
            fragmentManager.executePendingTransactions();
        }
    }

    public static void a(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.remove(fragment);
        beginTransaction.commit();
    }

    public static InboxFragment a(Fragment fragment) {
        fragment = fragment.getParentFragment();
        if (fragment == null) {
            return null;
        }
        if (fragment instanceof InboxFragment) {
            return (InboxFragment) fragment;
        }
        return a(fragment);
    }
}
