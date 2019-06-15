package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class NativeContainerLayout extends ViewGroup {
    private static final String a = "NativeContainerLayout";

    public static class a extends LayoutParams {
        public int a;
        public int b;

        public a(int i, int i2) {
            super(i, i2);
        }

        public a(LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public NativeContainerLayout(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = i4;
        while (i3 < childCount) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                int measuredWidth = aVar.a + childAt.getMeasuredWidth();
                int measuredHeight = aVar.b + childAt.getMeasuredHeight();
                i5 = Math.max(i5, measuredWidth);
                i4 = Math.max(i4, measuredHeight);
            }
            i3++;
        }
        setMeasuredDimension(resolveSize(Math.max(i5, getSuggestedMinimumWidth()), i), resolveSize(Math.max(i4, getSuggestedMinimumHeight()), i2));
    }

    /* Access modifiers changed, original: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                childAt.layout(aVar.a, aVar.b, aVar.a + childAt.getMeasuredWidth(), aVar.b + childAt.getMeasuredHeight());
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    /* Access modifiers changed, original: protected */
    public LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new a(layoutParams);
    }
}
