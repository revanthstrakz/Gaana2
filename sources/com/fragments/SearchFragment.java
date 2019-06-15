package com.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.view.CustomListView.OnDataLoadedListener;
import com.gaana.view.item.DownloadSongListingView;
import com.gaana.view.item.DownloadSongsItemView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.GaanaSearchManager;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.utilities.Util;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class SearchFragment extends BaseGaanaFragment implements OnPageChangeListener, OnClickListener, OnDataLoadedListener {
    public static boolean a = false;
    private long b;
    private int c = 0;
    private String d = "Track";
    private boolean e = true;
    private String f = null;
    private HashMap<String, Boolean> g = new HashMap();
    private HashMap<String, Boolean> h = new HashMap();
    private boolean i = false;
    private boolean j = false;
    private ViewPager k;
    private a l;
    private TabLayout m;
    private ListingFragment[] n = new ListingFragment[4];
    private ListingComponents o;
    private View p = null;
    private TextView q;
    private TextView r;
    private TypedValue s = new TypedValue();

    class a extends FragmentStatePagerAdapter {
        private FragmentManager b;

        public a(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.b = fragmentManager;
        }

        public Fragment getItem(int i) {
            ListingFragment listingFragment = new ListingFragment();
            ListingParams listingParams = new ListingParams();
            listingParams.b(i);
            listingParams.f(false);
            listingParams.e(false);
            listingParams.h(SearchFragment.this.j);
            listingFragment.a(SearchFragment.this);
            ListingButton listingButton = (ListingButton) SearchFragment.this.o.c().get(i);
            URLManager c = listingButton.c();
            c.b(SearchFragment.this.f);
            c.d(SearchFragment.this.j);
            c.e(SearchFragment.this.j);
            c.h(SearchFragment.this.j);
            c.g(false);
            listingParams.a(listingButton);
            listingFragment.a(listingParams);
            SearchFragment.this.n[i] = listingFragment;
            return listingFragment;
        }

        public int getCount() {
            return SearchFragment.this.o.c().size();
        }

        public CharSequence getPageTitle(int i) {
            return ((ListingButton) SearchFragment.this.o.c().get(i)).d();
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
                            SearchFragment.this.n[parseInt] = (ListingFragment) fragment;
                        }
                    }
                }
            }
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.p == null) {
            this.b = Calendar.getInstance().getTimeInMillis();
            getActivity().getTheme().resolveAttribute(R.attr.first_line_color, this.s, true);
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.p = setContentView(R.layout.view_search_details, viewGroup);
            this.q = (TextView) this.p.findViewById(R.id.tab_gaana_search);
            this.r = (TextView) this.p.findViewById(R.id.tab_mymusic_search);
            this.g.put(BusinessObjectType.Tracks.name(), Boolean.valueOf(false));
            this.g.put(BusinessObjectType.Albums.name(), Boolean.valueOf(false));
            this.g.put(BusinessObjectType.Playlists.name(), Boolean.valueOf(false));
            this.g.put(BusinessObjectType.Artists.name(), Boolean.valueOf(false));
            HashMap hashMap = this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Local ");
            stringBuilder.append(BusinessObjectType.Tracks.name());
            hashMap.put(stringBuilder.toString(), Boolean.valueOf(false));
            hashMap = this.g;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Local ");
            stringBuilder.append(BusinessObjectType.Albums.name());
            hashMap.put(stringBuilder.toString(), Boolean.valueOf(false));
            hashMap = this.g;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Local ");
            stringBuilder.append(BusinessObjectType.Playlists.name());
            hashMap.put(stringBuilder.toString(), Boolean.valueOf(false));
            hashMap = this.g;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Local ");
            stringBuilder.append(BusinessObjectType.Artists.name());
            hashMap.put(stringBuilder.toString(), Boolean.valueOf(false));
            this.h.put(BusinessObjectType.Tracks.name(), Boolean.valueOf(false));
            this.h.put(BusinessObjectType.Albums.name(), Boolean.valueOf(false));
            this.h.put(BusinessObjectType.Playlists.name(), Boolean.valueOf(false));
            this.h.put(BusinessObjectType.Artists.name(), Boolean.valueOf(false));
            hashMap = this.h;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Local ");
            stringBuilder.append(BusinessObjectType.Tracks.name());
            hashMap.put(stringBuilder.toString(), Boolean.valueOf(false));
            hashMap = this.h;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Local ");
            stringBuilder.append(BusinessObjectType.Albums.name());
            hashMap.put(stringBuilder.toString(), Boolean.valueOf(false));
            hashMap = this.h;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Local ");
            stringBuilder.append(BusinessObjectType.Playlists.name());
            hashMap.put(stringBuilder.toString(), Boolean.valueOf(false));
            hashMap = this.h;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Local ");
            stringBuilder.append(BusinessObjectType.Artists.name());
            hashMap.put(stringBuilder.toString(), Boolean.valueOf(false));
            if (getArguments() != null) {
                this.d = getArguments().getString("default_tab");
                this.f = getArguments().getString("search_string");
                this.e = getArguments().getBoolean("save_search_query", true);
                this.j = getArguments().getBoolean("search_my_music", false);
                a(this.f);
                if (this.d == null) {
                    this.c = 0;
                    this.d = "Track";
                } else if (this.d.equalsIgnoreCase("Artist")) {
                    this.c = 2;
                } else if (this.d.equalsIgnoreCase("Track")) {
                    this.c = 0;
                } else if (this.d.equalsIgnoreCase("Album")) {
                    this.c = 1;
                } else if (this.d.equalsIgnoreCase("Playlist")) {
                    this.c = 3;
                }
            }
            if (GaanaSearchManager.a().h() == SearchType.Generic) {
                this.q.setOnClickListener(this);
                this.r.setOnClickListener(this);
            } else {
                this.r.setVisibility(8);
                this.q.setVisibility(8);
            }
            updateView();
            b();
            a();
        }
        if (GaanaSearchManager.a().h() == SearchType.Radio) {
            setGAScreenName("RadioSearchResultScreen", "RadioSearchResultScreen");
        } else if (this.j) {
            setGAScreenName("MyMusic-SearchResultScreen", "MyMusic-SearchResultScreen");
        } else {
            setGAScreenName("GaanaMusic-SearchResultScreen", "GaanaMusic-SearchResultScreen");
        }
        MoEngage.getInstance().reportSectionViewedEvent("Search");
        return this.p;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void a(String str) {
        this.o = Constants.j();
        if (GaanaSearchManager.a().h() == SearchType.Generic) {
            this.o = Constants.j();
            boolean z = this.e;
        } else if (GaanaSearchManager.a().h() == SearchType.Radio) {
            this.o = Constants.o();
        }
        Iterator it = this.o.c().iterator();
        while (it.hasNext()) {
            ListingButton listingButton = (ListingButton) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(listingButton.c().k());
            stringBuilder.append(str);
            listingButton.c().a(stringBuilder.toString());
        }
        this.mAppState.setListingComponents(this.o);
    }

    public void onResume() {
        super.onResume();
        this.mAppState.setListingComponents(this.o);
        if (!(this.o == null || ((ListingButton) this.o.c().get(0)).g() == null)) {
            this.mAppState.setCurrentBusObjInListView(((ListingButton) this.o.c().get(0)).g());
        }
        setActionBar(this.p, new GenericBackActionBar(this.mContext, getString(R.string.search)));
    }

    public void onPause() {
        super.onPause();
        a = false;
    }

    public void onPageSelected(int i) {
        Object stringBuilder;
        String str = "";
        if (i == 0) {
            str = BusinessObjectType.Tracks.name();
        } else if (i == 1) {
            str = BusinessObjectType.Albums.name();
        } else if (i == 2) {
            str = BusinessObjectType.Artists.name();
        } else if (i == 3) {
            str = BusinessObjectType.Playlists.name();
        }
        if (this.j) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Local ");
            stringBuilder2.append(str);
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder = str;
        }
        if (((Boolean) this.g.get(stringBuilder)).booleanValue() && !((Boolean) this.h.get(stringBuilder)).booleanValue()) {
            try {
                StringBuilder stringBuilder3;
                i = this.n[i].c();
                String str2 = this.f;
                String str3 = "swipe";
                String str4 = "GaanaMusic-SRP";
                if (this.j) {
                    str4 = "MyMusic-SRP";
                }
                if (i == 0) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(this.f);
                    stringBuilder3.append("|No result");
                    str2 = stringBuilder3.toString();
                }
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str3);
                stringBuilder3.append("/");
                stringBuilder3.append(str);
                stringBuilder3.append("/");
                stringBuilder3.append(this.f);
                ((BaseActivity) this.mContext).sendGAEvent(str4, stringBuilder3.toString(), str2);
                this.h.put(stringBuilder, Boolean.valueOf(true));
            } catch (Exception unused) {
            }
        }
    }

    public void refreshListView() {
        if (this.n != null) {
            for (ListingFragment listingFragment : this.n) {
                if (listingFragment != null) {
                    listingFragment.refreshListView();
                }
            }
            super.refreshListView();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008d  */
    public void onDataLoaded(com.gaana.models.BusinessObject r9, com.managers.URLManager.BusinessObjectType r10) {
        /*
        r8 = this;
        r0 = java.util.Calendar.getInstance();
        r0 = r0.getTimeInMillis();
        r2 = r8.i;
        r3 = 1;
        if (r2 != 0) goto L_0x0024;
    L_0x000d:
        r4 = r8.b;
        r6 = 0;
        r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x0024;
    L_0x0015:
        r2 = "Full results";
        r4 = r8.b;
        r6 = r0 - r4;
        r0 = "Load";
        r1 = "Search";
        com.constants.Constants.a(r0, r6, r1, r2);
        r8.i = r3;
    L_0x0024:
        r0 = r10.name();
        r1 = com.managers.URLManager.BusinessObjectType.Tracks;
        r1 = r1.name();
        r0 = r0.equalsIgnoreCase(r1);
        r1 = 0;
        if (r0 == 0) goto L_0x0037;
    L_0x0035:
        r0 = r1;
        goto L_0x006c;
    L_0x0037:
        r0 = r10.name();
        r2 = com.managers.URLManager.BusinessObjectType.Albums;
        r2 = r2.name();
        r0 = r0.equalsIgnoreCase(r2);
        if (r0 == 0) goto L_0x0049;
    L_0x0047:
        r0 = r3;
        goto L_0x006c;
    L_0x0049:
        r0 = r10.name();
        r2 = com.managers.URLManager.BusinessObjectType.Artists;
        r2 = r2.name();
        r0 = r0.equalsIgnoreCase(r2);
        if (r0 == 0) goto L_0x005b;
    L_0x0059:
        r0 = 2;
        goto L_0x006c;
    L_0x005b:
        r0 = r10.name();
        r2 = com.managers.URLManager.BusinessObjectType.Playlists;
        r2 = r2.name();
        r0 = r0.equalsIgnoreCase(r2);
        if (r0 == 0) goto L_0x0035;
    L_0x006b:
        r0 = 3;
    L_0x006c:
        r2 = r10.name();
        r4 = r8.j;
        if (r4 == 0) goto L_0x0085;
    L_0x0074:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Local ";
        r4.append(r5);
        r4.append(r2);
        r2 = r4.toString();
    L_0x0085:
        r4 = r8.k;
        r4 = r4.getCurrentItem();
        if (r0 != r4) goto L_0x0108;
    L_0x008d:
        r0 = r8.f;
        r1 = "search";
        r4 = "GaanaMusic-SRP";
        r5 = r8.j;
        if (r5 == 0) goto L_0x0099;
    L_0x0097:
        r4 = "MyMusic-SRP";
    L_0x0099:
        if (r9 == 0) goto L_0x00ab;
    L_0x009b:
        r5 = r9.getArrListBusinessObj();
        if (r5 == 0) goto L_0x00ab;
    L_0x00a1:
        r9 = r9.getArrListBusinessObj();
        r9 = r9.size();
        if (r9 != 0) goto L_0x00be;
    L_0x00ab:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r0 = r8.f;
        r9.append(r0);
        r0 = "|No result";
        r9.append(r0);
        r0 = r9.toString();
    L_0x00be:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r9.append(r1);
        r1 = "/";
        r9.append(r1);
        r10 = r10.name();
        r9.append(r10);
        r10 = "/";
        r9.append(r10);
        r10 = r8.f;
        r9.append(r10);
        r9 = r9.toString();
        r10 = r8.g;
        r10 = r10.get(r2);
        r10 = (java.lang.Boolean) r10;
        r10 = r10.booleanValue();
        if (r10 != 0) goto L_0x0128;
    L_0x00ee:
        r10 = r8.mContext;
        r10 = (com.gaana.BaseActivity) r10;
        r10.sendGAEvent(r4, r9, r0);
        r9 = r8.g;
        r10 = java.lang.Boolean.valueOf(r3);
        r9.put(r2, r10);
        r9 = r8.h;
        r10 = java.lang.Boolean.valueOf(r3);
        r9.put(r2, r10);
        goto L_0x0128;
    L_0x0108:
        r9 = r8.g;
        r9 = r9.get(r2);
        r9 = (java.lang.Boolean) r9;
        r9 = r9.booleanValue();
        if (r9 != 0) goto L_0x0128;
    L_0x0116:
        r9 = r8.g;
        r10 = java.lang.Boolean.valueOf(r3);
        r9.put(r2, r10);
        r9 = r8.h;
        r10 = java.lang.Boolean.valueOf(r1);
        r9.put(r2, r10);
    L_0x0128:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.SearchFragment.onDataLoaded(com.gaana.models.BusinessObject, com.managers.URLManager$BusinessObjectType):void");
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        if (this.p.getParent() != null) {
            ((ViewGroup) this.p.getParent()).removeView(this.p);
        }
        super.onDestroyView();
    }

    private void a() {
        this.k = (ViewPager) this.p.findViewById(R.id.viewpager);
        this.l = new a(getChildFragmentManager());
        this.k.setAdapter(this.l);
        this.k.setCurrentItem(this.c);
        this.k.setOnPageChangeListener(this);
        this.m = (TabLayout) this.p.findViewById(R.id.sliding_tabs);
        this.m.setupWithViewPager(this.k);
    }

    private void b() {
        if (this.j) {
            this.q.setTextColor(this.s.data);
            this.q.setBackgroundResource(R.drawable.search_tab_notselected);
            this.r.setTextColor(this.mContext.getResources().getColor(R.color.f17gaana.red));
            this.r.setBackgroundResource(R.drawable.search_tab_selected);
            ((ListingButton) this.o.c().get(0)).c(DownloadSongListingView.class.getName());
            return;
        }
        this.r.setTextColor(this.s.data);
        this.r.setBackgroundResource(R.drawable.search_tab_notselected);
        this.q.setTextColor(this.mContext.getResources().getColor(R.color.f17gaana.red));
        this.q.setBackgroundResource(R.drawable.search_tab_selected);
        ((ListingButton) this.o.c().get(0)).c(DownloadSongsItemView.class.getName());
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tab_gaana_search) {
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
                return;
            } else if (Util.j(this.mContext)) {
                this.j = false;
                setGAScreenName("GaanaMusic-SearchResultScreen", "GaanaMusic-SearchResultScreen");
            } else {
                ap.a().f(this.mContext);
                return;
            }
        } else if (id == R.id.tab_mymusic_search) {
            this.j = true;
            setGAScreenName("MyMusic-SearchResultScreen", "MyMusic-SearchResultScreen");
        }
        b();
        c();
    }

    private void c() {
        if (this.n != null) {
            for (ListingFragment listingFragment : this.n) {
                if (listingFragment != null) {
                    ListingParams f = listingFragment.f();
                    f.h(this.j);
                    URLManager c = f.j().c();
                    c.b(this.f);
                    c.d(this.j);
                    c.h(this.j);
                    c.g(false);
                    listingFragment.g();
                }
            }
        }
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.SEARCH_FULL.name();
    }
}
