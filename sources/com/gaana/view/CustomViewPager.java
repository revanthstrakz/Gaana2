package com.gaana.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewPager extends ViewPager {
    Boolean disableParentScroll = Boolean.valueOf(false);
    private float fullScreenWidthAspectRatio = -1.0f;
    private Boolean isInsideScrollView = Boolean.valueOf(false);
    private Boolean isSwipeEnabled = Boolean.valueOf(true);
    private long mAutoScrollTime = -1;
    private Handler mHandlerPageChanger;
    private int mPageCount = -1;
    private CustomViewPagerAdapter mPagerAdpter;
    float oldX;
    float oldY;
    private OnGetTitleCalledListner onGetTitleCalledListner;
    private OnGetViewCalledListner onGetViewCalledListner;
    Runnable pageChangerRunnable = new Runnable() {
        public void run() {
            if (CustomViewPager.this != null) {
                int currentItem = CustomViewPager.this.getCurrentItem() + 1;
                if (currentItem == CustomViewPager.this.mPageCount) {
                    currentItem = 0;
                }
                CustomViewPager.this.setCurrentItem(currentItem, true);
            }
            CustomViewPager.this.mHandlerPageChanger.postDelayed(CustomViewPager.this.pageChangerRunnable, CustomViewPager.this.mAutoScrollTime);
        }
    };

    public interface OnGetTitleCalledListner {
        String onGetTitleCalled(int i);
    }

    public interface OnGetViewCalledListner {
        View onGetViewCalled(int i, ViewGroup viewGroup);
    }

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setSwipeEnabled(Boolean bool) {
        this.isSwipeEnabled = bool;
    }

    public void setInsideVerticalScrollView(Boolean bool) {
        this.isInsideScrollView = bool;
    }

    public void setAutoScrollEnabled(long j) {
        if (getAdapter() == null) {
            throw new NullPointerException("Please use setAutoScrollEnabled after @Link:setAdapterParams.");
        }
        this.mAutoScrollTime = j;
        this.mHandlerPageChanger = new Handler();
        this.mHandlerPageChanger.postDelayed(this.pageChangerRunnable, this.mAutoScrollTime);
    }

    public float getFullScreenWidthAspectRatio() {
        return this.fullScreenWidthAspectRatio;
    }

    public void setFullScreenWidthAspectRatio(float f) {
        this.fullScreenWidthAspectRatio = f;
    }

    public void setAdapterParams(int i, OnGetViewCalledListner onGetViewCalledListner) {
        this.mPageCount = i;
        this.onGetViewCalledListner = onGetViewCalledListner;
        this.mPagerAdpter = new CustomViewPagerAdapter(this.mPageCount, this.onGetViewCalledListner);
        if (this.onGetTitleCalledListner != null) {
            this.mPagerAdpter.setOnGetTitleCalledListner(this.onGetTitleCalledListner);
        }
        this.mPagerAdpter.setViewPager(this);
        setAdapter(this.mPagerAdpter);
    }

    public void setTitleChangeListner(OnGetTitleCalledListner onGetTitleCalledListner) {
        if (this.onGetViewCalledListner == null) {
            this.onGetTitleCalledListner = onGetTitleCalledListner;
        }
    }

    public CustomViewPagerAdapter getPagerAdapter() {
        return this.mPagerAdpter;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isInsideScrollView.booleanValue()) {
            getParent().requestDisallowInterceptTouchEvent(this.disableParentScroll.booleanValue());
        }
        return this.isSwipeEnabled.booleanValue() ? super.onTouchEvent(motionEvent) : false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isInsideScrollView.booleanValue()) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.oldX = motionEvent.getX();
                this.oldY = motionEvent.getY();
            } else if (actionMasked == 2) {
                float x = motionEvent.getX() - this.oldX;
                if (Math.abs(x) <= Math.abs(motionEvent.getY() - this.oldY) || x == 0.0f) {
                    this.disableParentScroll = Boolean.valueOf(false);
                } else {
                    this.disableParentScroll = Boolean.valueOf(true);
                }
            }
            getParent().requestDisallowInterceptTouchEvent(this.disableParentScroll.booleanValue());
        }
        if (this.isSwipeEnabled.booleanValue()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }
}
