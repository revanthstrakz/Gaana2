package com.gaana.view;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.gaana.view.CustomViewPager.OnGetTitleCalledListner;
import com.gaana.view.CustomViewPager.OnGetViewCalledListner;

public class CustomViewPagerAdapter extends PagerAdapter {
    private Boolean hasLayoutDimenSet = Boolean.valueOf(false);
    private int mPageCount = 0;
    private CustomViewPager mPager;
    private OnGetTitleCalledListner onGetTitleCalledListner;
    private OnGetViewCalledListner onGetViewCalledListner;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public CustomViewPagerAdapter(int i, OnGetViewCalledListner onGetViewCalledListner) {
        this.mPageCount = i;
        this.onGetViewCalledListner = onGetViewCalledListner;
    }

    public void setOnGetTitleCalledListner(OnGetTitleCalledListner onGetTitleCalledListner) {
        this.onGetTitleCalledListner = onGetTitleCalledListner;
    }

    public void setViewPager(CustomViewPager customViewPager) {
        this.mPager = customViewPager;
    }

    public int getCount() {
        return this.mPageCount;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View onGetViewCalled = this.onGetViewCalledListner.onGetViewCalled(i, viewGroup);
        viewGroup.addView(onGetViewCalled, 0);
        return onGetViewCalled;
    }

    public CharSequence getPageTitle(int i) {
        return this.onGetTitleCalledListner != null ? this.onGetTitleCalledListner.onGetTitleCalled(i) : "";
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
