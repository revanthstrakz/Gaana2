package com.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import com.constants.c;
import com.gaana.R;

public class CustomViewPager extends ViewPager {
    private Boolean a = Boolean.valueOf(true);
    private Context b;
    private PagerTabStrip c = null;
    private TypedValue d = new TypedValue();
    private TypedValue e = new TypedValue();
    private TypedValue f = new TypedValue();
    private Drawable g;

    public CustomViewPager(Context context) {
        super(context);
        this.b = context;
        context.getTheme().resolveAttribute(R.attr.grid_text_bar_bgcolor, this.d, true);
        context.getTheme().resolveAttribute(R.attr.first_line_color, this.e, true);
        context.getTheme().resolveAttribute(R.attr.pager_strip_color, this.f, true);
        TypedArray obtainStyledAttributes = this.b.obtainStyledAttributes(new int[]{R.attr.layer_pager_strip});
        this.g = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        b();
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        if (this.c != null) {
            removeView(this.c);
        }
    }

    public void b() {
        if (c.b.booleanValue()) {
            this.c = new PagerTabStrip(this.b);
            this.c.setTextColor(this.e.data);
            this.c.setTabIndicatorColor(getResources().getColor(R.color.pager_strip_color));
            this.c.setBackgroundDrawable(this.g);
            this.c.setBackgroundColor(this.d.data);
            LayoutParams layoutParams = new LayoutParams();
            layoutParams.height = -2;
            layoutParams.width = -1;
            layoutParams.gravity = 48;
            this.c.setPadding(0, 10, 0, 10);
            addView(this.c, layoutParams);
        }
    }

    public void setSwipeEnabled(Boolean bool) {
        this.a = bool;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.a.booleanValue() ? super.onTouchEvent(motionEvent) : false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.a.booleanValue() ? super.onInterceptTouchEvent(motionEvent) : false;
    }
}
