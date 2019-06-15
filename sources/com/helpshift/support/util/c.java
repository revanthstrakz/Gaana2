package com.helpshift.support.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.helpshift.e.a;
import com.helpshift.k.b;
import com.helpshift.support.compositions.FaqFragment;
import com.helpshift.support.fragments.FaqFlowFragment;
import com.helpshift.support.fragments.ScreenshotPreviewFragment;
import com.helpshift.support.fragments.SearchFragment;
import com.helpshift.support.fragments.SingleQuestionFragment;
import com.helpshift.support.fragments.SupportFragment;
import java.util.List;

public class c {
    public static void a(FragmentManager fragmentManager, int i, Fragment fragment, String str, String str2, boolean z, boolean z2) {
        b(fragmentManager, i, fragment, str, str2, z, z2);
    }

    public static void a(FragmentManager fragmentManager, int i, Fragment fragment, String str, boolean z) {
        b(fragmentManager, i, fragment, str, fragment.getClass().getName(), z, false);
    }

    public static void b(FragmentManager fragmentManager, int i, Fragment fragment, String str, boolean z) {
        b(fragmentManager, i, fragment, str, null, z, false);
    }

    public static void a(FragmentManager fragmentManager, String str) {
        fragmentManager.popBackStack(str, 1);
    }

    public static void b(FragmentManager fragmentManager, String str) {
        fragmentManager.popBackStackImmediate(str, 1);
    }

    public static void a(FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
    }

    public static SingleQuestionFragment a(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && (fragment instanceof SingleQuestionFragment)) {
                    return (SingleQuestionFragment) fragment;
                }
            }
        }
        return null;
    }

    public static ScreenshotPreviewFragment b(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && (fragment instanceof ScreenshotPreviewFragment)) {
                    return (ScreenshotPreviewFragment) fragment;
                }
            }
        }
        return null;
    }

    public static FaqFlowFragment c(FragmentManager fragmentManager) {
        List fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (int size = fragments.size() - 1; size >= 0; size--) {
                Fragment fragment = (Fragment) fragments.get(size);
                if (fragment != null && (fragment instanceof FaqFlowFragment)) {
                    return (FaqFlowFragment) fragment;
                }
            }
        }
        return null;
    }

    public static FaqFragment d(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && (fragment instanceof FaqFragment)) {
                    return (FaqFragment) fragment;
                }
            }
        }
        return null;
    }

    public static SearchFragment e(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && (fragment instanceof SearchFragment)) {
                    return (SearchFragment) fragment;
                }
            }
        }
        return null;
    }

    public static SupportFragment a(Fragment fragment) {
        if (fragment instanceof SupportFragment) {
            return (SupportFragment) fragment;
        }
        fragment = fragment.getParentFragment();
        if (fragment == null) {
            return null;
        }
        if (fragment instanceof SupportFragment) {
            return (SupportFragment) fragment;
        }
        return a(fragment);
    }

    private static void b(FragmentManager fragmentManager, int i, Fragment fragment, String str, String str2, boolean z, boolean z2) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        Fragment findFragmentById = fragmentManager.findFragmentById(i);
        if (!b.a().a.j.booleanValue()) {
            if (findFragmentById == null || z2) {
                beginTransaction.setCustomAnimations(0, 0, 0, 0);
            } else {
                beginTransaction.setCustomAnimations(a.hs__slide_in_from_right, a.hs__slide_out_to_left, a.hs__slide_in_from_left, a.hs__slide_out_to_right);
            }
        }
        beginTransaction.replace(i, fragment, str);
        if (!TextUtils.isEmpty(str2)) {
            beginTransaction.addToBackStack(str2);
        }
        beginTransaction.commitAllowingStateLoss();
        if (z) {
            fragmentManager.executePendingTransactions();
        }
    }
}
