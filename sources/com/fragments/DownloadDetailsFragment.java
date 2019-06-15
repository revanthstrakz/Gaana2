package com.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout.LayoutParams;
import com.actionbar.DownloadDetailsActionbar;
import com.collapsible_header.SlidingTabLayout;
import com.constants.Constants;
import com.constants.Constants.SortOrder;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.view.CustomListView;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.DownloadProgressBar;
import com.gaana.view.item.FailedDownloadView;
import com.gaana.view.item.GaanaMiniPackSetup;
import com.gaana.view.item.GaanaPlusExpiredRenewMessageBox;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.library.managers.TaskManager.TaskListner;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.DownloadManager;
import com.managers.aj;
import com.managers.al;
import com.managers.ap;
import com.managers.i;
import com.managers.k;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.services.h;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class DownloadDetailsFragment extends BaseGaanaFragment implements com.actionbar.DownloadDetailsActionbar.a, com.fragments.ListingFragment.a {
    String a;
    boolean b = false;
    boolean c = false;
    boolean d = false;
    private ViewGroup e;
    private DownloadProgressBar f;
    private ViewPager g;
    private a h;
    private SlidingTabLayout i;
    private ArrayList<ListingFragment> j = null;
    private ListingComponents k;
    private CheckBox l;
    private View m = null;
    private String n = null;
    private DownloadDetailsActionbar o;
    private OnPageChangeListener p = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
            DownloadDetailsFragment.this.o.hideContextMenu(false);
            if (DownloadDetailsFragment.this.j != null && DownloadDetailsFragment.this.j.size() > i && DownloadDetailsFragment.this.j.get(i) != null && ((ListingFragment) DownloadDetailsFragment.this.j.get(i)).h() != null) {
                DownloadDetailsFragment.this.o.setPagerPosition(i);
            }
        }

        public void onPageSelected(int i) {
            if (!(DownloadDetailsFragment.this.j == null || i >= DownloadDetailsFragment.this.j.size() || DownloadDetailsFragment.this.j.get(i) == null)) {
                ((ListingFragment) DownloadDetailsFragment.this.j.get(i)).c(true);
            }
            if (i == 0) {
                DownloadDetailsFragment.this.o.b(true);
            } else {
                DownloadDetailsFragment.this.o.b(false);
            }
            DownloadDetailsFragment.this.g();
        }
    };
    private int q = 0;

    class a extends FragmentStatePagerAdapter {
        private FragmentManager b = null;

        public a(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.b = fragmentManager;
        }

        public Fragment getItem(int i) {
            if (DownloadDetailsFragment.this.j.size() <= i || DownloadDetailsFragment.this.j.get(i) == null) {
                Fragment listingFragment = new ListingFragment();
                listingFragment.b(DownloadDetailsFragment.this.n);
                ListingParams listingParams = new ListingParams();
                listingParams.e(true);
                listingParams.j(true);
                listingFragment.a(SortOrder.TrackName);
                listingParams.b(i);
                listingParams.f(true);
                listingParams.b(true);
                listingParams.h(true);
                listingParams.k(false);
                listingParams.a((ListingButton) DownloadDetailsFragment.this.k.c().get(i));
                listingParams.c(Util.a(listingParams.j().c()));
                if (i == 0) {
                    listingParams.c(true);
                    listingParams.a(SortOrder.TrackName);
                    listingFragment.a(DownloadDetailsFragment.this);
                } else {
                    listingParams.a(SortOrder.TrackName);
                }
                listingFragment.a(listingParams);
                listingFragment.a(DownloadDetailsFragment.this);
                DownloadDetailsFragment.this.j.set(i, listingFragment);
                return listingFragment;
            }
            ListingFragment listingFragment2 = (ListingFragment) DownloadDetailsFragment.this.j.get(i);
            listingFragment2.b(DownloadDetailsFragment.this.n);
            return listingFragment2;
        }

        public int getCount() {
            return DownloadDetailsFragment.this.k.c().size();
        }

        public CharSequence getPageTitle(int i) {
            return ((ListingButton) DownloadDetailsFragment.this.k.c().get(i)).d();
        }

        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
            super.restoreState(parcelable, classLoader);
            if (parcelable != null) {
                Bundle bundle = (Bundle) parcelable;
                for (String str : bundle.keySet()) {
                    if (str.startsWith("f")) {
                        int parseInt = Integer.parseInt(str.substring(1));
                        Fragment fragment = this.b.getFragment(bundle, str);
                        if (fragment != null) {
                            while (DownloadDetailsFragment.this.j.size() <= parseInt) {
                                DownloadDetailsFragment.this.j.add(null);
                            }
                            fragment.setMenuVisibility(false);
                            ListingFragment listingFragment = (ListingFragment) fragment;
                            listingFragment.a(DownloadDetailsFragment.this);
                            listingFragment.a(DownloadDetailsFragment.this);
                            DownloadDetailsFragment.this.j.set(parseInt, listingFragment);
                        }
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void f() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = -1;
        if (this.m == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            setAnimateFragmentElements(true);
            this.m = setContentView(R.layout.activity_download_details, viewGroup);
            this.mAppState = (GaanaApplication) getActivity().getApplicationContext();
            this.k = Constants.k();
            this.mAppState.setListingComponents(this.k);
            this.e = (ViewGroup) this.m.findViewById(R.id.llParentHeaderOfList);
            this.e.setVisibility(0);
            this.f = new DownloadProgressBar(getActivity(), this);
            o();
            this.o = new DownloadDetailsActionbar(this.mContext, true);
            this.o.setDownloadActionbarClickListener(this);
            this.o.showContextMenu(false);
            this.o.c(true);
            setActionBar(this.m, this.o);
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.a = arguments.getString("EXTRA_PARAM_FILTER");
                String string = arguments.getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
                if (!TextUtils.isEmpty(string)) {
                    switch (string.hashCode()) {
                        case 48:
                            if (string.equals("0")) {
                                i = 0;
                                break;
                            }
                            break;
                        case 49:
                            if (string.equals("1")) {
                                i = 1;
                                break;
                            }
                            break;
                        case 50:
                            if (string.equals(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                                i = 2;
                                break;
                            }
                            break;
                    }
                    switch (i) {
                        case 0:
                            this.g.setCurrentItem(0, true);
                            break;
                        case 1:
                            this.g.setCurrentItem(1, true);
                            break;
                        case 2:
                            this.g.setCurrentItem(2, true);
                            break;
                    }
                }
                if (arguments.getBoolean("DOWNLOAD_EDIT_PARAM", false)) {
                    m();
                }
            }
            updateView();
        } else {
            updateView();
            a(true, -1, SortOrder.Default);
            a(SortOrder.DownloadTime, 0);
        }
        l();
        ((BaseActivity) this.mContext).resetLoginStatus();
        setGAScreenName("Downloads Details", "MyMusic-Downloads");
        this.mAppState.setSidebarActiveBtn(R.id.MyMusicMenuDownloads);
        ((BaseActivity) this.mContext).refreshSidebar();
        ((GaanaActivity) this.mContext).title = this.mContext.getString(R.string.mymusic_downloads);
        this.c = DownloadManager.c().G();
        this.d = DownloadManager.c().E();
        return this.m;
    }

    public void onPause() {
        super.onPause();
        if (this.b) {
            j();
        }
    }

    private void j() {
        DownloadManager.c().h(false);
        DownloadManager.c().e(this.c);
        DownloadManager.c().g(this.d);
    }

    private void k() {
        if (!TextUtils.isEmpty(this.a) && this.a == "smart_download") {
            DownloadManager.c().h(true);
            DownloadManager.c().e(false);
            DownloadManager.c().g(false);
            this.b = true;
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void l() {
        if (!ap.a().j() && ap.a().k()) {
            this.e.addView(new GaanaPlusExpiredRenewMessageBox(this.mContext, this).getView((Activity) this.mContext, false));
        } else if (this.mAppState.getCurrentUser().getDeviceLinkLimitReached()) {
            this.e.addView(new GaanaPlusExpiredRenewMessageBox(this.mContext, this).getView((Activity) this.mContext, true));
        } else if (!ap.a().h()) {
            this.e.addView(new GaanaMiniPackSetup(this.mContext, this).getView((Activity) this.mContext, true));
        }
    }

    public void onResume() {
        super.onResume();
        k();
        this.e.removeAllViews();
        c();
        this.mAppState.setListingComponents(Constants.k());
        ((GaanaActivity) this.mContext).resetOriginalDownloadsCount();
        boolean b = this.mDeviceResManager.b("SHOW_SNACKBAR_DATA_FREE_DOWNLOADS", true, true);
        if (ap.a().j() && b && DownloadManager.c().p() > 0) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.snackbar_gotit), this.mContext.getString(R.string.snackbar_txt_play_your_downloads), new com.managers.aj.a() {
                public void onSet() {
                    DownloadDetailsFragment.this.mDeviceResManager.a("SHOW_SNACKBAR_DATA_FREE_DOWNLOADS", false, true);
                }

                public void onDismiss() {
                    DownloadDetailsFragment.this.mDeviceResManager.a("SHOW_SNACKBAR_DATA_FREE_DOWNLOADS", false, true);
                }
            });
        }
    }

    public void onStop() {
        super.onStop();
        if (al.a) {
            g();
        }
        ((GaanaActivity) this.mContext).resetOriginalDownloadsCount();
    }

    public void c() {
        if (!DownloadManager.c().v() || DownloadManager.c().k() != -1) {
            this.e.addView(this.f.getView(null));
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 8.0f, getResources().getDisplayMetrics()));
            this.e.setLayoutParams(layoutParams);
        } else if (this.mAppState.getCurrentUser().getDeviceLinkLimitReached()) {
            this.e.addView(new GaanaPlusExpiredRenewMessageBox(this.mContext, this).getView((Activity) this.mContext, true));
        } else if (!ap.a().j() && ap.a().k()) {
            this.e.addView(new GaanaPlusExpiredRenewMessageBox(this.mContext, this).getView((Activity) this.mContext, false));
        } else if (!ap.a().h()) {
            this.e.removeAllViews();
            this.e.addView(new GaanaMiniPackSetup(this.mContext, this).getView((Activity) this.mContext, true));
        } else if (DownloadManager.c().r() > 0) {
            this.f.checkForFailedDownloadView(this.e);
        } else {
            this.e.removeAllViews();
        }
    }

    public void refreshListView() {
        if (this.j != null) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                ListingFragment listingFragment = (ListingFragment) it.next();
                if (listingFragment != null) {
                    listingFragment.refreshListView();
                }
            }
        }
        if (this.f != null) {
            if (this.e != null && this.e.getChildCount() == 0) {
                c();
            } else if (this.e != null && this.e.getChildCount() == 1 && (this.e.getChildAt(0) instanceof FailedDownloadView)) {
                this.e.removeView(this.e.getChildAt(0));
                c();
            }
            this.f.refreshProgressBar();
        }
    }

    public void d() {
        if (this.j != null) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                ListingFragment listingFragment = (ListingFragment) it.next();
                if (listingFragment != null) {
                    listingFragment.i();
                }
            }
        }
    }

    public void e() {
        if (this.j != null) {
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                ListingFragment listingFragment = (ListingFragment) it.next();
                if (listingFragment != null) {
                    listingFragment.c(this.n);
                }
            }
        }
    }

    public void a(String str) {
        if (this.mAppState == null) {
            this.mAppState = (GaanaApplication) GaanaApplication.getContext();
        }
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(this.n)) {
            this.n = str;
            e();
        }
    }

    public boolean a(boolean z) {
        if (i.a().f()) {
            b(z);
        } else {
            m();
        }
        refreshListView();
        return true;
    }

    private void b(boolean z) {
        if (!z) {
            i.a().b(false);
            i.a().a(false);
            this.m.findViewById(R.id.ll_selector).setVisibility(8);
            this.e.removeAllViews();
            c();
            i.a().e();
        } else if (i.a().h()) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.no_item_selected));
            this.m.findViewById(R.id.ll_selector).setVisibility(0);
        } else {
            i.a().a(false);
            new CustomDialogView(this.mContext, this.mContext.getString(R.string.delete_download_items_msg), new OnButtonClickListener() {
                public void onPositiveButtonClick() {
                    DownloadDetailsFragment.this.n();
                }

                public void onNegativeButtonClick() {
                    i.a().b(false);
                    i.a().a(false);
                    DownloadDetailsFragment.this.m.findViewById(R.id.ll_selector).setVisibility(8);
                    DownloadDetailsFragment.this.e.removeAllViews();
                    DownloadDetailsFragment.this.c();
                    i.a().e();
                }
            }).show();
        }
    }

    private void m() {
        i.a().e();
        i.a().a(true);
        this.e.removeAllViews();
        this.m.findViewById(R.id.ll_selector).setVisibility(0);
        this.m.findViewById(R.id.f31download.delete.items).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadDetailsFragment.this.a((int) R.id.deleteActionBar);
            }
        });
        this.l = (CheckBox) this.m.findViewById(R.id.f30download.checkbox.selectall);
        if (i.a().g()) {
            this.l.setChecked(true);
        } else {
            this.l.setChecked(false);
        }
        this.l.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (i.a().g()) {
                    DownloadDetailsFragment.this.l.setChecked(false);
                    i.a().e();
                } else {
                    DownloadDetailsFragment.this.l.setChecked(true);
                    i.a().d();
                }
                DownloadDetailsFragment.this.refreshListView();
            }
        });
        this.m.findViewById(R.id.f49download.tv.delete).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (i.a().g()) {
                    DownloadDetailsFragment.this.l.setChecked(false);
                    i.a().e();
                } else {
                    DownloadDetailsFragment.this.l.setChecked(true);
                    i.a().d();
                }
                DownloadDetailsFragment.this.refreshListView();
            }
        });
        refreshListView();
    }

    private void n() {
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(false), this.mContext.getString(R.string.deleting));
        h.a().a(new TaskListner() {
            public void doBackGroundTask() {
                Process.setThreadPriority(10);
                i.a().c();
            }

            public void onBackGroundTaskCompleted() {
                i.a().a(false);
                if (DownloadDetailsFragment.this.m == null) {
                    ((BaseActivity) DownloadDetailsFragment.this.mContext).hideProgressDialog();
                    return;
                }
                DownloadDetailsFragment.this.m.findViewById(R.id.ll_selector).setVisibility(8);
                DownloadDetailsFragment.this.f();
                DownloadDetailsFragment.this.e.removeAllViews();
                DownloadDetailsFragment.this.c();
                DownloadDetailsFragment.this.d();
                ((BaseActivity) DownloadDetailsFragment.this.mContext).hideProgressDialog();
                DownloadDetailsFragment.this.o.d(true);
            }
        }, -1);
    }

    public void a() {
        if (i.a().f()) {
            this.o.d(true);
            a(false);
            return;
        }
        ((GaanaActivity) this.mContext).onBackPressedHandling();
    }

    public boolean a(int i) {
        boolean f = i.a().f();
        if (i == R.id.deleteActionBar) {
            if (f) {
                b(true);
            } else {
                m();
            }
            refreshListView();
        } else if (i == R.id.editActionbar) {
            if (f) {
                b(true);
            } else {
                m();
            }
            refreshListView();
        }
        return false;
    }

    public void b() {
        d();
    }

    public void a(SortOrder sortOrder, int i) {
        if (i != -1 && this.j != null && this.j.get(i) != null && ((ListingFragment) this.j.get(i)).h() != null) {
            ((ListingFragment) this.j.get(i)).a(sortOrder);
            ((ListingFragment) this.j.get(i)).h().setSortOrder(sortOrder);
            ((ListingFragment) this.j.get(i)).h().sortList(sortOrder, true);
        }
    }

    private void o() {
        this.j = new ArrayList();
        int size = this.k.c().size();
        for (int i = 0; i < size; i++) {
            this.j.add(null);
        }
        this.g = (ViewPager) this.m.findViewById(R.id.viewpager);
        this.h = new a(getChildFragmentManager());
        this.g.setAdapter(this.h);
        this.g.addOnPageChangeListener(this.p);
        this.i = (SlidingTabLayout) this.m.findViewById(R.id.sliding_tabs);
        this.i.setCustomTabView(R.layout.generic_tab_indicator, 16908308);
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
        size = typedValue.data;
        this.i.setSelectedIndicatorColors(size);
        this.i.setViewPager(this.g);
    }

    public void a(boolean z, int i, SortOrder sortOrder) {
        this.o.setCustomMenuId(i);
        this.o.setSortOrder(sortOrder);
        this.o.a(z);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            this.g = null;
        }
        this.j = null;
        if (this.e != null) {
            this.e.removeAllViews();
            this.e = null;
        }
        this.m = null;
    }

    public void a(View view, int i, CustomListView customListView) {
        this.q = i;
        this.o.setParams(this, customListView.getmBusinessObject());
        this.o.showContextMenu(true);
        al.a().a(true);
        al.a().a((BusinessObject) view.getTag(), true);
        ((CheckBox) view.findViewById(R.id.f35download.item.checkbox)).setChecked(true);
        h();
    }

    public void g() {
        if (this.q != 0) {
            this.q = 0;
            this.o.showContextMenu(false);
            al.a().a(false);
            al.a().c();
            refreshListView();
        }
    }

    public void h() {
        this.o.updateSelectedCountinContextMode(this.q);
    }

    public void i() {
        if (al.a().e() || this.j.get(0) == null) {
            al.a().c();
        } else {
            ArrayList g = ((ListingFragment) this.j.get(0)).h().getListingButton().g();
            if (g.size() >= 100) {
                g = new ArrayList(g.subList(0, 100));
            }
            al.a().a(g);
        }
        refreshListView();
        h();
    }

    public void onDestroyView() {
        i.a().a(false);
        i.a().e();
        k.a().c();
        if (this.m.getParent() != null) {
            ((ViewGroup) this.m.getParent()).removeView(this.m);
        }
        if (!(this.j == null || this.j.size() <= 0 || this.j.get(0) == null || ((ListingFragment) this.j.get(0)).h() == null)) {
            ((ListingFragment) this.j.get(0)).h().sortList(SortOrder.TrackName, true);
        }
        super.onDestroyView();
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.DOWNLOADS.name();
    }
}
