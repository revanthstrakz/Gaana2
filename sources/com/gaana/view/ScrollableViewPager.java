package com.gaana.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class ScrollableViewPager extends ViewPager {
    private boolean isHorizontallScrollEnabled = true;

    public ScrollableViewPager(@NonNull Context context) {
        super(context);
    }

    public ScrollableViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setHorizontalScrollEnabled(boolean z) {
        this.isHorizontallScrollEnabled = z;
    }

    public void onMeasure(int i, int i2) {
        try {
            int childCount = getChildCount();
            int i3 = 0;
            int i4 = i3;
            while (i3 < childCount) {
                View childAt = getChildAt(i3);
                if (childAt != null) {
                    childAt.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (measuredHeight > i4) {
                        i4 = measuredHeight;
                    }
                }
                i3++;
            }
            i2 = MeasureSpec.makeMeasureSpec(i4, 1073741824);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        super.onMeasure(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.isHorizontallScrollEnabled && super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.isHorizontallScrollEnabled && super.onInterceptTouchEvent(motionEvent);
    }
}
