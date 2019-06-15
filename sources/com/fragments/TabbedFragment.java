package com.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.constants.a;
import com.gaana.GaanaActivity;
import com.gaana.R;
import java.util.ArrayList;
import java.util.List;

public class TabbedFragment extends Fragment {
    private Context a;
    private List<Fragment> b = new ArrayList();

    public void onAttach(Context context) {
        super.onAttach(context);
        this.a = context;
    }

    public static Fragment a(String str) {
        TabbedFragment tabbedFragment = new TabbedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tag", str);
        tabbedFragment.setArguments(bundle);
        return tabbedFragment;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_tabbed, viewGroup, false);
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        this.b.clear();
        super.onSaveInstanceState(bundle);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        c(a());
        if (this.b.size() > 0) {
            for (int i = 0; i < this.b.size(); i++) {
                b((Fragment) this.b.get(i));
            }
        }
        if (this.b.size() == 0 && getChildFragmentManager().getBackStackEntryCount() == 0) {
            b(a.a(a()));
        }
        this.b.clear();
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
    }

    public void a(Fragment fragment) {
        this.b.add(fragment);
    }

    public String a() {
        return getArguments() != null ? getArguments().getString("tag", "home") : "";
    }

    public void b(Fragment fragment) {
        if (isAdded() && fragment != null) {
            String valueOf = String.valueOf(getChildFragmentManager().getBackStackEntryCount());
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.frame_container, fragment, valueOf);
            beginTransaction.addToBackStack(valueOf);
            try {
                if (fragment instanceof BaseGaanaFragment) {
                    ((GaanaActivity) this.a).setFragment((BaseGaanaFragment) fragment);
                }
                beginTransaction.commitAllowingStateLoss();
                fragment.setHasOptionsMenu(true);
            } catch (IllegalStateException unused) {
            }
        } else if (fragment != null) {
            this.b.add(fragment);
        }
    }

    public void onDetach() {
        super.onDetach();
        this.a = null;
    }

    public int b() {
        return isAdded() ? getChildFragmentManager().getBackStackEntryCount() : 0;
    }

    public Fragment c() {
        if (!isAdded() || getChildFragmentManager().getBackStackEntryCount() <= 0) {
            return null;
        }
        return getChildFragmentManager().findFragmentByTag(getChildFragmentManager().getBackStackEntryAt(getChildFragmentManager().getBackStackEntryCount() - 1).getName());
    }

    public boolean d() {
        return a(String.valueOf(0), 0);
    }

    public boolean e() {
        boolean z = false;
        try {
            if (getChildFragmentManager().getBackStackEntryCount() > 1 && getChildFragmentManager().popBackStackImmediate()) {
                z = true;
            }
            return z;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    public boolean a(String str, int i) {
        try {
            return getChildFragmentManager().popBackStackImmediate(str, i);
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A:{SYNTHETIC, RETURN} */
    private void c(java.lang.String r5) {
        /*
        r4 = this;
        r0 = com.logging.GaanaLogger.PAGE_SORCE_NAME.OTHER;
        r0 = r0.name();
        r1 = r5.hashCode();
        r2 = -906336856; // 0xffffffffc9fa65a8 float:-2051253.0 double:NaN;
        r3 = -1;
        if (r1 == r2) goto L_0x003e;
    L_0x0010:
        r2 = 3208415; // 0x30f4df float:4.495947E-39 double:1.5851676E-317;
        if (r1 == r2) goto L_0x0034;
    L_0x0015:
        r2 = 108270587; // 0x67413fb float:4.590598E-35 double:5.34927775E-316;
        if (r1 == r2) goto L_0x002a;
    L_0x001a:
        r2 = 1522043897; // 0x5ab88bf9 float:2.59726486E16 double:7.51989601E-315;
        if (r1 == r2) goto L_0x0020;
    L_0x001f:
        goto L_0x0048;
    L_0x0020:
        r1 = "mymusic";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x0048;
    L_0x0028:
        r5 = 3;
        goto L_0x0049;
    L_0x002a:
        r1 = "radio";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x0048;
    L_0x0032:
        r5 = 1;
        goto L_0x0049;
    L_0x0034:
        r1 = "home";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x0048;
    L_0x003c:
        r5 = 0;
        goto L_0x0049;
    L_0x003e:
        r1 = "search";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x0048;
    L_0x0046:
        r5 = 2;
        goto L_0x0049;
    L_0x0048:
        r5 = r3;
    L_0x0049:
        switch(r5) {
            case 0: goto L_0x0069;
            case 1: goto L_0x0060;
            case 2: goto L_0x0057;
            case 3: goto L_0x004e;
            default: goto L_0x004c;
        };
    L_0x004c:
        r5 = r3;
        goto L_0x0071;
    L_0x004e:
        r5 = com.gaana.GaanaActivity.SHOW_TAB_MYMUSIC;
        r0 = com.logging.GaanaLogger.PAGE_SORCE_NAME.MYMUSIC;
        r0 = r0.name();
        goto L_0x0071;
    L_0x0057:
        r5 = com.gaana.GaanaActivity.SHOW_TAB_SEARCH;
        r0 = com.logging.GaanaLogger.PAGE_SORCE_NAME.SEARCH;
        r0 = r0.name();
        goto L_0x0071;
    L_0x0060:
        r5 = com.gaana.GaanaActivity.SHOW_TAB_RADIO;
        r0 = com.logging.GaanaLogger.PAGE_SORCE_NAME.RADIO;
        r0 = r0.name();
        goto L_0x0071;
    L_0x0069:
        r5 = com.gaana.GaanaActivity.SHOW_TAB_HOME;
        r0 = com.logging.GaanaLogger.PAGE_SORCE_NAME.HOME;
        r0 = r0.name();
    L_0x0071:
        r1 = com.gaana.application.GaanaApplication.getInstance();
        r1.setCurrentPageName(r0);
        if (r5 == r3) goto L_0x00a5;
    L_0x007a:
        r0 = r4.a;
        r0 = (com.gaana.GaanaActivity) r0;
        r0 = r0.getBottomNavigationView();
        if (r0 != 0) goto L_0x0093;
    L_0x0084:
        r0 = r4.a;
        r0 = (com.gaana.GaanaActivity) r0;
        r0.initBottomNavigationBar();
        r0 = r4.a;
        r0 = (com.gaana.GaanaActivity) r0;
        r0 = r0.getBottomNavigationView();
    L_0x0093:
        if (r0 == 0) goto L_0x00a5;
    L_0x0095:
        r1 = r4.a;	 Catch:{ Exception -> 0x00a1 }
        r1 = (com.gaana.GaanaActivity) r1;	 Catch:{ Exception -> 0x00a1 }
        r1 = r1.getBottomNavigationBarHelper();	 Catch:{ Exception -> 0x00a1 }
        r1.a(r0, r5);	 Catch:{ Exception -> 0x00a1 }
        goto L_0x00a5;
    L_0x00a1:
        r5 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);
    L_0x00a5:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.TabbedFragment.c(java.lang.String):void");
    }

    public boolean b(String str) {
        return getChildFragmentManager().findFragmentByTag(str) != null;
    }
}
