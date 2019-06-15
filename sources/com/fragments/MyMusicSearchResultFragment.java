package com.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.actionbar.MyMusicSearchResultActionBar;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;

public class MyMusicSearchResultFragment extends BaseGaanaFragment {
    private ViewPager a;
    private a b;
    private TabLayout c;
    private ListingFragment[] d;
    private ListingComponents e;
    private View f = null;
    private String g = "";
    private boolean h = false;
    private OnPageChangeListener i = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            MyMusicSearchResultFragment.this.a(i);
        }
    };

    class a extends FragmentStatePagerAdapter {
        private FragmentManager b;

        public a(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.b = fragmentManager;
        }

        public Fragment getItem(int i) {
            ListingFragment listingFragment = new ListingFragment();
            ListingParams listingParams = new ListingParams();
            listingParams.e(false);
            listingParams.b(i);
            listingParams.f(true);
            listingParams.h(true);
            listingParams.d(false);
            listingParams.i(false);
            listingParams.a(false);
            listingParams.a((ListingButton) MyMusicSearchResultFragment.this.e.c().get(i));
            listingFragment.a(listingParams);
            MyMusicSearchResultFragment.this.d[i] = listingFragment;
            return listingFragment;
        }

        public int getCount() {
            return MyMusicSearchResultFragment.this.e.c().size();
        }

        public CharSequence getPageTitle(int i) {
            return ((ListingButton) MyMusicSearchResultFragment.this.e.c().get(i)).d();
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
                            MyMusicSearchResultFragment.this.d[parseInt] = (ListingFragment) fragment;
                        }
                    }
                }
            }
        }
    }

    public void setGAScreenName(String str, String str2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f == null) {
            this.g = "";
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.f = setContentView(R.layout.fragment_tab_layout, viewGroup);
            this.mAppState = (GaanaApplication) getActivity().getApplicationContext();
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.h = arguments.getBoolean("searchInDownloads");
                this.g = arguments.getString("searchText");
            }
            this.e = Constants.b(this.g, this.h);
            this.mAppState.setListingComponents(this.e);
            this.d = new ListingFragment[this.e.c().size()];
            c();
            String str = "in Local Files";
            if (this.h) {
                str = "in My Music";
            }
            setActionBar(this.f, new MyMusicSearchResultActionBar(this.mContext, this.g, str));
        }
        if (((GaanaActivity) this.mContext).getRefreshData()) {
            ((GaanaActivity) this.mContext).setRefreshData(false);
            a();
        }
        ((BaseActivity) this.mContext).resetLoginStatus();
        updateView();
        return this.f;
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mAppState.setListingComponents(Constants.b(this.g, this.h));
    }

    public void onStop() {
        super.onStop();
    }

    public void refreshListView() {
        if (this.d != null) {
            for (ListingFragment listingFragment : this.d) {
                if (listingFragment != null) {
                    listingFragment.refreshListView();
                }
            }
        }
        if (this.a != null) {
            a(this.a.getCurrentItem());
        }
    }

    public void a(int i, String str) {
        if (this.c != null && this.c.getTabCount() > i) {
            Tab tabAt = this.c.getTabAt(i);
            if (tabAt != null) {
                tabAt.setText((CharSequence) str);
            }
        }
        if (this.a != null) {
            a(this.a.getCurrentItem());
        }
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

    private void c() {
        this.a = (ViewPager) this.f.findViewById(R.id.viewpager);
        this.b = new a(getChildFragmentManager());
        this.a.setAdapter(this.b);
        this.a.setOnPageChangeListener(this.i);
        this.c = (TabLayout) this.f.findViewById(R.id.sliding_tabs);
        this.c.setupWithViewPager(this.a);
        this.c.setSelectedTabIndicatorColor(0);
        this.c.setTabMode(0);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.gaana_background});
        this.c.setBackgroundColor(obtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK));
        obtainStyledAttributes.recycle();
        this.c.setTabTextColors(-1, SupportMenu.CATEGORY_MASK);
        a(0);
    }

    private void a(int i) {
        for (int i2 = 0; i2 < this.c.getTabCount(); i2++) {
            Tab tabAt = this.c.getTabAt(i2);
            if (tabAt.getCustomView() == null) {
                tabAt.setCustomView((int) R.layout.tab_view);
            }
            TextView textView = (TextView) tabAt.getCustomView().findViewById(R.id.tab_text);
            textView.setText(((ListingButton) this.e.c().get(i2)).d());
            if (i == i2) {
                textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.f17gaana.red));
                if (Constants.l) {
                    textView.setBackgroundResource(R.drawable.rounded_corner_tab_red_white);
                } else {
                    textView.setBackgroundResource(R.drawable.rounded_corner_tab_red);
                }
            } else if (Constants.l) {
                textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.first_line_color_white));
                textView.setBackgroundResource(R.drawable.rounded_corner_tab_white);
            } else {
                textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.first_line_color));
                textView.setBackgroundResource(R.drawable.rounded_corner_tab_black);
            }
        }
    }

    public void onDestroyView() {
        if (this.f.getParent() != null) {
            ((ViewGroup) this.f.getParent()).removeView(this.f);
        }
        super.onDestroyView();
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name();
    }

    public boolean b() {
        return this.h;
    }
}
