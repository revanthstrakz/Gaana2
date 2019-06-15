package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.facebook.ads.internal.s.a.y;

public class g extends ViewGroup {
    private static final int a = ((int) (8.0f * y.b));
    private int b;

    public g(Context context) {
        super(context);
        setMotionEventSplittingEnabled(false);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        i3 -= i;
        int paddingLeft = getPaddingLeft();
        i = getPaddingTop();
        for (i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (paddingLeft + measuredWidth > i3) {
                paddingLeft = getPaddingLeft();
                i += this.b;
            }
            childAt.layout(paddingLeft, i, paddingLeft + measuredWidth, measuredHeight + i);
            paddingLeft += measuredWidth + a;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        i = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        i2 = (MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE);
        int i3 = 0;
        int i4 = paddingTop;
        paddingTop = paddingLeft;
        paddingLeft = 0;
        while (i3 < getChildCount()) {
            View childAt = getChildAt(i3);
            childAt.measure(MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = childAt.getMeasuredWidth();
            paddingLeft = Math.max(paddingLeft, childAt.getMeasuredHeight() + a);
            if (paddingTop + measuredWidth > i) {
                paddingTop = getPaddingLeft();
                i4 += paddingLeft;
            }
            paddingTop += measuredWidth + a;
            i3++;
        }
        this.b = paddingLeft;
        paddingLeft += i4;
        if (paddingLeft < i2) {
            i2 = paddingLeft;
        }
        setMeasuredDimension(i, i2 + a);
    }
}
