package com.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.actionbar.DownloadDetailsActionbar;
import com.collapsible_header.SlidingTabLayout;
import com.constants.Constants;
import com.constants.Constants.SortOrder;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.BusinessObject;
import com.gaana.view.CustomListView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.al;
import com.managers.e;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.utilities.Util;

public class FavoritesFragment extends BaseGaanaFragment implements com.actionbar.DownloadDetailsActionbar.a, com.fragments.ListingFragment.a {
    private ViewPager a;
    private a b;
    private SlidingTabLayout c;
    private ListingFragment[] d = new ListingFragment[6];
    private ListingComponents e;
    private View f = null;
    private String g = null;
    private boolean h = true;
    private DownloadDetailsActionbar i;
    private OnPageChangeListener j = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
            FavoritesFragment.this.i.hideContextMenu(false);
            FavoritesFragment.this.i.setPagerPosition(i);
        }

        public void onPageSelected(int i) {
            if (!(FavoritesFragment.this.d == null || i >= FavoritesFragment.this.d.length || FavoritesFragment.this.d[i] == null)) {
                FavoritesFragment.this.d[i].c(true);
            }
            FavoritesFragment.this.e();
        }
    };
    private int k = 0;

    class a extends FragmentStatePagerAdapter {
        private FragmentManager b;

        public a(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.b = fragmentManager;
        }

        public Fragment getItem(int i) {
            ListingFragment listingFragment = new ListingFragment();
            listingFragment.b(FavoritesFragment.this.g);
            ListingParams listingParams = new ListingParams();
            listingParams.e(true);
            listingParams.b(i);
            listingParams.b(true);
            listingParams.f(true);
            listingParams.i(true);
            listingParams.h(false);
            listingParams.k(false);
            listingParams.j(true);
            if (i == 0) {
                listingParams.a(SortOrder.TrackName);
                listingParams.d((int) R.menu.filter_menu_fav_tracks);
            } else {
                listingParams.a(SortOrder.TrackName);
            }
            listingParams.b(PLAYOUT_SECTION_TYPE.FAVORITES.name());
            listingParams.a((ListingButton) FavoritesFragment.this.e.c().get(i));
            listingParams.c(Util.a(listingParams.j().c()));
            listingParams.a(String.valueOf(e.k));
            listingFragment.a(a(((ListingButton) FavoritesFragment.this.e.c().get(i)).d()));
            listingFragment.a(listingParams);
            listingFragment.a(FavoritesFragment.this);
            listingFragment.a(FavoritesFragment.this);
            FavoritesFragment.this.d[i] = listingFragment;
            return listingFragment;
        }

        private java.lang.String a(java.lang.String r2) {
            /*
            r1 = this;
            r0 = r2.hashCode();
            switch(r0) {
                case -1885249576: goto L_0x003a;
                case -14379540: goto L_0x0030;
                case 79082974: goto L_0x0026;
                case 920766657: goto L_0x001c;
                case 1022198348: goto L_0x0012;
                case 1933132772: goto L_0x0008;
                default: goto L_0x0007;
            };
        L_0x0007:
            goto L_0x0044;
        L_0x0008:
            r0 = "ALBUMS";
            r2 = r2.equals(r0);
            if (r2 == 0) goto L_0x0044;
        L_0x0010:
            r2 = 1;
            goto L_0x0045;
        L_0x0012:
            r0 = "OCCASIONS";
            r2 = r2.equals(r0);
            if (r2 == 0) goto L_0x0044;
        L_0x001a:
            r2 = 5;
            goto L_0x0045;
        L_0x001c:
            r0 = "PLAYLISTS";
            r2 = r2.equals(r0);
            if (r2 == 0) goto L_0x0044;
        L_0x0024:
            r2 = 2;
            goto L_0x0045;
        L_0x0026:
            r0 = "SONGS";
            r2 = r2.equals(r0);
            if (r2 == 0) goto L_0x0044;
        L_0x002e:
            r2 = 0;
            goto L_0x0045;
        L_0x0030:
            r0 = "ARTISTS";
            r2 = r2.equals(r0);
            if (r2 == 0) goto L_0x0044;
        L_0x0038:
            r2 = 4;
            goto L_0x0045;
        L_0x003a:
            r0 = "RADIOS";
            r2 = r2.equals(r0);
            if (r2 == 0) goto L_0x0044;
        L_0x0042:
            r2 = 3;
            goto L_0x0045;
        L_0x0044:
            r2 = -1;
        L_0x0045:
            switch(r2) {
                case 0: goto L_0x005a;
                case 1: goto L_0x0057;
                case 2: goto L_0x0054;
                case 3: goto L_0x0051;
                case 4: goto L_0x004e;
                case 5: goto L_0x004b;
                default: goto L_0x0048;
            };
        L_0x0048:
            r2 = "";
            return r2;
        L_0x004b:
            r2 = "FAV_OC_BOTTOM_BANNER";
            return r2;
        L_0x004e:
            r2 = "FAV_AR_BOTTOM_BANNER";
            return r2;
        L_0x0051:
            r2 = "FAV_RD_BOTTOM_BANNER";
            return r2;
        L_0x0054:
            r2 = "FAV_PL_BOTTOM_BANNER";
            return r2;
        L_0x0057:
            r2 = "FAV_AL_BOTTOM_BANNER";
            return r2;
        L_0x005a:
            r2 = "FAV_TR_BOTTOM_BANNER";
            return r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fragments.FavoritesFragment$a.a(java.lang.String):java.lang.String");
        }

        public int getCount() {
            return FavoritesFragment.this.e.c().size();
        }

        public CharSequence getPageTitle(int i) {
            return ((ListingButton) FavoritesFragment.this.e.c().get(i)).d();
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
                            listingFragment.a(FavoritesFragment.this);
                            listingFragment.a(FavoritesFragment.this);
                            FavoritesFragment.this.d[parseInt] = listingFragment;
                        }
                    }
                }
            }
        }
    }

    public void a() {
    }

    public boolean a(int i) {
        return false;
    }

    public void b() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f == null) {
            this.g = "";
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.f = setContentView(R.layout.fragment_favorites, viewGroup);
            this.mAppState = (GaanaApplication) getActivity().getApplicationContext();
            this.e = Constants.a();
            this.mAppState.setListingComponents(this.e);
            h();
            this.i = new DownloadDetailsActionbar(this.mContext, false, this.mContext.getString(R.string.favorites_txt));
            this.i.setDownloadActionbarClickListener(this);
            this.i.showContextMenu(false);
            this.i.c(true);
            setActionBar(this.f, this.i);
        }
        ((BaseActivity) this.mContext).resetLoginStatus();
        updateView();
        if (((GaanaActivity) this.mContext).getRefreshData() || PlaylistSyncManager.refreshFragment) {
            ((GaanaActivity) this.mContext).setRefreshData(false);
            PlaylistSyncManager.refreshFragment = false;
            c();
        }
        setGAScreenName("MyMusic-Favorites", "MyMusic-Favorites");
        this.mAppState.setSidebarActiveBtn(R.id.LeftMenuMyMusic);
        ((BaseActivity) this.mContext).refreshSidebar();
        ((GaanaActivity) this.mContext).title = "favorites";
        return this.f;
    }

    public void onResume() {
        super.onResume();
        this.mAppState.setListingComponents(Constants.a());
    }

    public void onStop() {
        super.onStop();
        if (al.a) {
            e();
        }
    }

    public void onDestroyView() {
        if (this.f.getParent() != null) {
            ((ViewGroup) this.f.getParent()).removeView(this.f);
        }
        super.onDestroyView();
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

    public void c() {
        if (this.d != null) {
            for (ListingFragment listingFragment : this.d) {
                if (listingFragment != null) {
                    listingFragment.i();
                }
            }
        }
    }

    public void a(BusinessObject businessObject, boolean z) {
        this.d[this.a.getCurrentItem()].b(businessObject, z);
    }

    public void d() {
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
            d();
        }
    }

    private void h() {
        this.a = (ViewPager) this.f.findViewById(R.id.viewpager);
        this.a = (ViewPager) this.f.findViewById(R.id.viewpager);
        this.b = new a(getChildFragmentManager());
        this.a.setAdapter(this.b);
        this.a.addOnPageChangeListener(this.j);
        this.c = (SlidingTabLayout) this.f.findViewById(R.id.sliding_tabs);
        this.c.setCustomTabView(R.layout.generic_tab_indicator, 16908308);
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
        int i = typedValue.data;
        this.c.setSelectedIndicatorColors(i);
        this.c.setSelectedIndicatorColors(this.mContext.getResources().getColor(R.color.f17gaana.red));
        this.c.setViewPager(this.a);
    }

    public void a(SortOrder sortOrder, int i) {
        if (this.d != null && this.d[i] != null && this.d[i].h() != null) {
            this.d[i].a(sortOrder);
            this.d[i].h().sortList(sortOrder, false);
        }
    }

    public void a(boolean z, int i, SortOrder sortOrder) {
        this.i.setCustomMenuId(i);
        this.i.setSortOrder(sortOrder);
        this.i.a(z);
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        if (this.d != null) {
            for (ListingFragment listingFragment : this.d) {
                if (listingFragment != null) {
                    listingFragment.refreshListView(businessObject, z);
                }
            }
        }
    }

    public void a(View view, int i, CustomListView customListView) {
        this.k = i;
        this.i.setParams(this, customListView.getmBusinessObject());
        this.i.showContextMenu(true);
        al.a().a(true);
        al.a().a((BusinessObject) view.getTag(), true);
        ((CheckBox) view.findViewById(R.id.f35download.item.checkbox)).setChecked(true);
        f();
    }

    public void e() {
        if (this.k != 0) {
            this.k = 0;
            this.i.showContextMenu(false);
            al.a().a(false);
            al.a().c();
            refreshListView();
        }
    }

    public void f() {
        this.i.updateSelectedCountinContextMode(this.k);
    }

    public void g() {
        if (al.a().e()) {
            al.a().c();
        } else {
            al.a().a(this.d[0].h().getListingButton().g());
        }
        refreshListView();
        f();
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.FAVORITES.name();
    }
}
