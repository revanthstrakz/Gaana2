package com.gaana.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

public class PopUpMenuListView extends ListView {
    public PopUpMenuListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int bottom = getChildAt(getChildCount() - 1).getBottom() - (getHeight() + getScrollY());
        if (motionEvent.getAction() == 0) {
            if (bottom == 0) {
                return false;
            }
            if (getFirstVisiblePosition() != 0 && getChildAt(0).getTop() != 1) {
                onTouchEvent(motionEvent);
            } else if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() != 1) {
                onTouchEvent(motionEvent);
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (canScrollVertically(this)) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean canScrollVertically(AbsListView absListView) {
        if (absListView != null && absListView.getChildCount() > 0) {
            int i = (absListView.getFirstVisiblePosition() == 0 && absListView.getChildAt(0).getTop() == 0) ? 0 : true;
            boolean z = i != 0 && absListView.getLastVisiblePosition() == absListView.getChildCount();
            if (i != 0 || z) {
                return true;
            }
        }
        return false;
    }
}
