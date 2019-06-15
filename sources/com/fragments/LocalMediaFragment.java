package com.fragments;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.actionbar.DownloadDetailsActionbar;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.BusinessObject;
import com.gaana.view.CustomListView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.al;
import com.managers.e;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.utilities.Util;

public class LocalMediaFragment extends BaseGaanaFragment {
    private ViewPager a;
    private a b;
    private TabLayout c;
    private ListingFragment[] d = new ListingFragment[4];
    private ListingComponents e;
    private View f = null;
    private String g = null;
    private DownloadDetailsActionbar h;
    private OnPageChangeListener i = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageSelected(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (i == 0) {
                LocalMediaFragment.this.h.hideContextMenu(false);
            } else {
                LocalMediaFragment.this.h.hideContextMenu(true);
            }
        }
    };
    private int j = 0;

    class a extends FragmentStatePagerAdapter {
        private FragmentManager b;

        public a(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.b = fragmentManager;
        }

        public Fragment getItem(int i) {
            ListingFragment listingFragment = new ListingFragment();
            listingFragment.b(LocalMediaFragment.this.g);
            ListingParams listingParams = new ListingParams();
            listingParams.e(true);
            listingParams.b(i);
            listingParams.f(true);
            listingParams.h(true);
            listingParams.b(true);
            listingParams.k(false);
            listingParams.o(Constants.cY ^ 1);
            listingParams.a((ListingButton) LocalMediaFragment.this.e.c().get(i));
            listingParams.c(Util.a(listingParams.j().c()));
            listingParams.a(String.valueOf(e.k));
            listingParams.g(true);
            listingFragment.a(listingParams);
            if (i == 0) {
                listingFragment.a(LocalMediaFragment.this);
                listingParams.m(true);
            } else {
                listingParams.m(false);
            }
            LocalMediaFragment.this.d[i] = listingFragment;
            return listingFragment;
        }

        public int getCount() {
            return LocalMediaFragment.this.e.c().size();
        }

        public CharSequence getPageTitle(int i) {
            return ((ListingButton) LocalMediaFragment.this.e.c().get(i)).d();
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
                            fragment.setMenuVisibility(false);
                            ListingFragment listingFragment = (ListingFragment) fragment;
                            listingFragment.a(LocalMediaFragment.this);
                            LocalMediaFragment.this.d[parseInt] = listingFragment;
                        }
                    }
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f == null) {
            this.g = "";
            super.onCreateView(layoutInflater, viewGroup, bundle);
            setAnimateFragmentElements(true);
            this.f = setContentView(R.layout.fragment_tab_layout, viewGroup);
            this.mAppState = (GaanaApplication) getActivity().getApplicationContext();
            this.e = Constants.l();
            this.mAppState.setListingComponents(this.e);
            g();
            f();
            this.h = new DownloadDetailsActionbar(this.mContext, false, this.mContext.getString(R.string.local_music));
            this.h.showContextMenu(false);
            this.h.a(false);
            this.h.c(true);
            setActionBar(this.f, this.h);
        }
        if (((GaanaActivity) this.mContext).getRefreshData()) {
            ((GaanaActivity) this.mContext).setRefreshData(false);
            a();
        }
        ((BaseActivity) this.mContext).resetLoginStatus();
        updateView();
        setGAScreenName("MyMusic-MusicOnMyPhone", "MyMusic-MusicOnMyPhone");
        this.mAppState.setSidebarActiveBtn(R.id.LeftMenuMyMusic);
        ((BaseActivity) this.mContext).refreshSidebar();
        ((GaanaActivity) this.mContext).title = this.mContext.getString(R.string.music_on_my_phone);
        return this.f;
    }

    private void f() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MYMUSIC_IMPORTED_FIRST_TIME", 0);
        if (sharedPreferences.getBoolean("MYMUSIC_IMPORTED_FIRST_TIME", true) && LocalMediaManager.getInstance(this.mContext).getLocalTrackCounts() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(LocalMediaManager.getInstance(this.mContext).getLocalTrackCounts());
            stringBuilder.append(" Tracks");
            ((BaseActivity) this.mContext).sendGAEvent("Local Music", "Import", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append(LocalMediaManager.getInstance(this.mContext).getLocalPlaylistCounts());
            stringBuilder.append(" Playlists");
            ((BaseActivity) this.mContext).sendGAEvent("Local Music", "Import", stringBuilder.toString());
            Editor edit = sharedPreferences.edit();
            edit.putBoolean("MYMUSIC_IMPORTED_FIRST_TIME", false);
            edit.apply();
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mAppState.setListingComponents(Constants.l());
    }

    public void onStop() {
        super.onStop();
        if (al.a) {
            c();
        }
    }

    public void refreshListView() {
        if (this.d != null) {
            for (ListingFragment listingFragment : this.d) {
                if (listingFragment != null) {
                    listingFragment.refreshListView();
                }
            }
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void a() {
        if (this.d != null) {
            for (ListingFragment listingFragment : this.d) {
                if (listingFragment != null) {
                    listingFragment.i();
                }
            }
        }
    }

    public void b() {
        if (this.d != null) {
            for (ListingFragment listingFragment : this.d) {
                if (listingFragment != null) {
                    listingFragment.c(this.g);
                }
            }
        }
    }

    public void a(String str) {
        if (this.mAppState == null) {
            this.mAppState = (GaanaApplication) GaanaApplication.getContext();
        }
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(this.g)) {
            this.g = str;
            b();
        }
    }

    private void g() {
        this.a = (ViewPager) this.f.findViewById(R.id.viewpager);
        this.b = new a(getChildFragmentManager());
        this.a.setAdapter(this.b);
        this.a.setOnPageChangeListener(this.i);
        this.c = (TabLayout) this.f.findViewById(R.id.sliding_tabs);
        this.c.setupWithViewPager(this.a);
    }

    public void a(View view, int i, CustomListView customListView) {
        this.j = i;
        this.h.setParams(this, customListView.getmBusinessObject());
        this.h.showContextMenu(true);
        al.a().a(true);
        al.a().a((BusinessObject) view.getTag(), true);
        ((CheckBox) view.findViewById(R.id.f35download.item.checkbox)).setChecked(true);
        d();
    }

    public void c() {
        this.j = 0;
        this.h.showContextMenu(false);
        al.a().a(false);
        al.a().c();
        refreshListView();
    }

    public void d() {
        this.h.updateSelectedCountinContextMode(this.j);
    }

    public void e() {
        if (al.a().e()) {
            al.a().c();
        } else {
            al.a().a(this.d[0].h().getListingButton().g());
        }
        refreshListView();
        d();
    }

    public void onDestroyView() {
        if (this.f.getParent() != null) {
            ((ViewGroup) this.f.getParent()).removeView(this.f);
        }
        super.onDestroyView();
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.LOCAL.name();
    }
}
