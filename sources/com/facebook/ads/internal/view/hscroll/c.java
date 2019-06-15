package com.facebook.ads.internal.view.hscroll;

import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;
import android.view.ViewGroup;

public class c {
    public int[] a(View view, int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i, view.getPaddingLeft() + view.getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i2, view.getPaddingTop() + view.getPaddingBottom(), layoutParams.height));
        return new int[]{(view.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin, (view.getMeasuredHeight() + layoutParams.bottomMargin) + layoutParams.topMargin};
    }
}
