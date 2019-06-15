package com.gaana.revampeddetail.manager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class LockableViewPager extends ViewPager {
    private boolean swipeLocked;

    public LockableViewPager(Context context) {
        super(context);
    }

    public LockableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean getSwipeLocked() {
        return this.swipeLocked;
    }

    public void setSwipeLocked(boolean z) {
        this.swipeLocked = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.swipeLocked && super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.swipeLocked && super.onInterceptTouchEvent(motionEvent);
    }

    public boolean canScrollHorizontally(int i) {
        return !this.swipeLocked && super.canScrollHorizontally(i);
    }
}
