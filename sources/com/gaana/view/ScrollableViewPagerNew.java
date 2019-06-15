package com.gaana.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.gaana.R;
import com.services.d;

public class ScrollableViewPagerNew extends ViewPager {
    private boolean isHorizontallScrollEnabled = true;
    private Context mContext;
    private View mCurrentView;

    public ScrollableViewPagerNew(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public ScrollableViewPagerNew(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void setHorizontalScrollEnabled(boolean z) {
        this.isHorizontallScrollEnabled = z;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mCurrentView != null) {
            this.mCurrentView.measure(i, MeasureSpec.makeMeasureSpec(0, 0));
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(Math.max(Math.max(0, this.mCurrentView.getMeasuredHeight()), d.a().c() - this.mContext.getResources().getDimensionPixelSize(R.dimen.dp320)), 1073741824));
        }
    }

    public void measureCurrentView(View view) {
        this.mCurrentView = view;
        requestLayout();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.isHorizontallScrollEnabled && super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.isHorizontallScrollEnabled && super.onInterceptTouchEvent(motionEvent);
    }
}
