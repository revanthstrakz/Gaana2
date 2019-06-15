package com.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.gaana.view.CustomListView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.URLManager.BusinessObjectType;
import com.managers.al;
import com.models.ListingComponents;
import com.models.ListingParams;
import java.util.ArrayList;
import java.util.Iterator;

public class MyMusicItemFragment extends BaseGaanaFragment implements com.actionbar.DownloadDetailsActionbar.a, com.fragments.ListingFragment.a {
    private ViewPager a;
    private a b;
    private SlidingTabLayout c;
    private ListingFragment[] d;
    private ListingComponents e;
    private ArrayList<ListingParams> f;
    private View g = null;
    private String h = null;
    private boolean i = true;
    private BusinessObjectType j;
    private String k = "";
    private String l = "-1";
    private DownloadDetailsActionbar m;
    private OnPageChangeListener n = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
            MyMusicItemFragment.this.m.hideContextMenu(false);
            if (MyMusicItemFragment.this.d != null && MyMusicItemFragment.this.d[i] != null && MyMusicItemFragment.this.d[i].h() != null) {
                MyMusicItemFragment.this.m.setPagerPosition(i);
            }
        }

        public void onPageSelected(int i) {
            if (!(MyMusicItemFragment.this.d == null || i >= MyMusicItemFragment.this.d.length || MyMusicItemFragment.this.d[i] == null)) {
                MyMusicItemFragment.this.d[i].c(true);
            }
            if (MyMusicItemFragment.this.j == BusinessObjectType.Tracks) {
                MyMusicItemFragment.this.f();
            }
        }
    };
    private int o = 0;

    class a extends FragmentStatePagerAdapter {
        private FragmentManager b;

        public a(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.b = fragmentManager;
        }

        public Fragment getItem(int i) {
            ListingFragment listingFragment = new ListingFragment();
            listingFragment.b(MyMusicItemFragment.this.h);
            listingFragment.a((ListingParams) MyMusicItemFragment.this.f.get(i));
            listingFragment.a(MyMusicItemFragment.this);
            listingFragment.a(MyMusicItemFragment.this);
            boolean z = true;
            if (i != MyMusicItemFragment.this.f.size() - 1) {
                z = false;
            }
            listingFragment.setIsDownloadFragment(z);
            MyMusicItemFragment.this.d[i] = listingFragment;
            return listingFragment;
        }

        public int getCount() {
            return MyMusicItemFragment.this.f.size();
        }

        public CharSequence getPageTitle(int i) {
            return ((ListingParams) MyMusicItemFragment.this.f.get(i)).j().d();
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
                            listingFragment.a(MyMusicItemFragment.this);
                            listingFragment.a(MyMusicItemFragment.this);
                            MyMusicItemFragment.this.d[parseInt] = listingFragment;
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

    public ViewPager c() {
        return this.a;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.g == null) {
            this.h = "";
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.g = setContentView(R.layout.fragment_favorites, viewGroup);
            if (getArguments() != null) {
                this.l = getArguments().getString("DEEPLINKING_SCREEN_EXTRA_PARAM", "-1");
            }
            this.mAppState = (GaanaApplication) getActivity().getApplicationContext();
            a((BusinessObjectType) getArguments().getSerializable("obj_type"));
            this.d = new ListingFragment[this.f.size()];
            h();
            i();
            this.m = new DownloadDetailsActionbar(this.mContext, false, this.k);
            this.m.setDownloadActionbarClickListener(this);
            this.m.showContextMenu(false);
            this.m.c(true);
            setActionBar(this.g, this.m);
        }
        ((BaseActivity) this.mContext).resetLoginStatus();
        updateView();
        if (((GaanaActivity) this.mContext).getRefreshData() || PlaylistSyncManager.refreshFragment) {
            ((GaanaActivity) this.mContext).setRefreshData(false);
            PlaylistSyncManager.refreshFragment = false;
            d();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MyMusic-");
        stringBuilder.append(this.k);
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("MyMusic-");
        stringBuilder3.append(this.k);
        setGAScreenName(stringBuilder2, stringBuilder3.toString());
        this.mAppState.setSidebarActiveBtn(R.id.LeftMenuMyMusic);
        ((BaseActivity) this.mContext).refreshSidebar();
        return this.g;
    }

    private void a(BusinessObjectType businessObjectType) {
        if (businessObjectType == BusinessObjectType.Tracks) {
            this.k = this.mContext.getString(R.string.songs);
            this.f = Constants.p();
        } else if (businessObjectType == BusinessObjectType.Playlists) {
            this.k = this.mContext.getString(R.string.playlists);
            this.f = Constants.q();
        } else if (businessObjectType == BusinessObjectType.Albums) {
            this.k = this.mContext.getString(R.string.albums_camel);
            this.f = Constants.r();
        }
        this.j = businessObjectType;
    }

    public void onResume() {
        super.onResume();
        h();
    }

    private void h() {
        if (this.f != null) {
            this.e = new ListingComponents();
            ArrayList arrayList = new ArrayList();
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                arrayList.add(((ListingParams) it.next()).j());
            }
            this.e.a(arrayList);
            this.mAppState.setListingComponents(this.e);
        }
    }

    public void onStop() {
        super.onStop();
        if (al.a) {
            f();
        }
    }

    public void onDestroyView() {
        if (this.g.getParent() != null) {
            ((ViewGroup) this.g.getParent()).removeView(this.g);
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

    public void d() {
        if (this.d != null) {
            for (ListingFragment listingFragment : this.d) {
                if (listingFragment != null) {
                    listingFragment.i();
                }
            }
        }
    }

    public void a(BusinessObject businessObject, boolean z) {
        int i = 1;
        if (this.d[0] != null) {
            boolean z2;
            if (businessObject instanceof Track) {
                z2 = false;
                this.d[0].a(businessObject, z2);
            } else {
                z2 = false;
                this.d[0].a(businessObject, z2);
            }
            z2 = true;
            this.d[0].a(businessObject, z2);
        }
        if (this.j == BusinessObjectType.Playlists) {
            i = 2;
        }
        if (this.d[i] != null) {
            this.d[i].b(businessObject, z);
        }
    }

    private void i() {
        this.a = (ViewPager) this.g.findViewById(R.id.viewpager);
        this.a = (ViewPager) this.g.findViewById(R.id.viewpager);
        this.b = new a(getChildFragmentManager());
        this.a.setAdapter(this.b);
        this.a.addOnPageChangeListener(this.n);
        this.c = (SlidingTabLayout) this.g.findViewById(R.id.sliding_tabs);
        this.c.setCustomTabView(R.layout.generic_tab_indicator, 16908308);
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
        int i = typedValue.data;
        this.c.setSelectedIndicatorColors(i);
        this.c.setSelectedIndicatorColors(this.mContext.getResources().getColor(R.color.f17gaana.red));
        this.c.setViewPager(this.a);
        if (!this.l.equals("-1")) {
            this.a.setCurrentItem(Integer.valueOf(this.l).intValue(), true);
        } else if (this.j == BusinessObjectType.Playlists) {
            this.a.setCurrentItem(1, true);
        }
    }

    public void a(SortOrder sortOrder, int i) {
        if (this.d != null && this.d[i] != null && this.d[i].h() != null) {
            this.d[i].a(sortOrder);
            this.d[i].h().sortList(sortOrder, ((ListingParams) this.f.get(i)).j().b() ^ 1);
        }
    }

    public void a(boolean z, int i, SortOrder sortOrder) {
        this.m.setCustomMenuId(i);
        this.m.setSortOrder(sortOrder);
        this.m.a(z);
    }

    public void e() {
        if (this.j == BusinessObjectType.Tracks && this.a.getCurrentItem() == 0) {
            this.d[0].o();
        }
    }

    public void a(int i, boolean z) {
        if (this.d != null) {
            BusinessObject businessObject = null;
            if (this.j == BusinessObjectType.Tracks) {
                businessObject = new Track();
            } else if (this.j == BusinessObjectType.Playlists) {
                businessObject = new Playlist();
            } else if (this.j == BusinessObjectType.Albums) {
                businessObject = new Album();
            }
            if (businessObject != null) {
                businessObject.setBusinessObjType(this.j);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(i);
                businessObject.setBusinessObjId(stringBuilder.toString());
                if (this.d[0] != null) {
                    z = businessObject.isFavorite().booleanValue() ^ 1;
                    this.d[0].a(businessObject, z);
                }
                if (this.d[this.f.size() - 1] != null) {
                    this.d[this.f.size() - 1].a(businessObject, z);
                }
            }
        }
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
        this.o = i;
        this.m.setParams(this, customListView.getmBusinessObject());
        this.m.showContextMenu(true);
        al.a().a(true);
        al.a().a((BusinessObject) view.getTag(), true);
        ((CheckBox) view.findViewById(R.id.f35download.item.checkbox)).setChecked(true);
        g();
    }

    public void f() {
        if (this.o != 0) {
            this.o = 0;
            this.m.showContextMenu(false);
            al.a().a(false);
            al.a().c();
            refreshListView();
        }
    }

    public void g() {
        this.m.updateSelectedCountinContextMode(this.o);
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.FAVORITES.name();
    }
}
