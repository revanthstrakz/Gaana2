package com.inmobi.ads;

import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;

class bp extends NativeScrollableContainer implements OnPageChangeListener {
    private static final String b = "bp";
    @Nullable
    a a;
    private ViewPager c;
    private Point d = new Point();
    private Point e = new Point();
    private boolean f;
    private boolean g = false;

    public bp(Context context) {
        super(context, 0);
        setClipChildren(false);
        setLayerType(1, null);
        this.c = new ViewPager(getContext());
        this.c.addOnPageChangeListener(this);
        addView(this.c);
    }

    public final void a(@NonNull am amVar, @NonNull ax axVar, int i, int i2, @NonNull a aVar) {
        LayoutParams layoutParams = (LayoutParams) NativeViewFactory.a(amVar.a(0), (ViewGroup) this);
        if (VERSION.SDK_INT >= 17) {
            layoutParams.setMarginStart(20);
            layoutParams.setMarginEnd(20);
        } else {
            layoutParams.setMargins(20, 0, 20, 0);
        }
        layoutParams.gravity = i2;
        this.c.setLayoutParams(layoutParams);
        this.c.setAdapter((ap) axVar);
        this.c.setOffscreenPageLimit(2);
        this.c.setPageMargin(16);
        this.c.setCurrentItem(i);
        this.a = aVar;
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.d.x = i / 2;
        this.d.y = i2 / 2;
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.e.x = (int) motionEvent.getX();
                this.e.y = (int) motionEvent.getY();
                break;
            case 1:
                float f = (float) this.e.x;
                float x = motionEvent.getX();
                int currentItem = this.c.getCurrentItem();
                int count = this.c.getAdapter().getCount();
                int width = this.c.getWidth();
                int width2 = getWidth();
                int i = 0;
                float f2;
                if (currentItem == 0 || count - 1 == currentItem) {
                    width2 -= width;
                    if (currentItem == 0) {
                        f2 = (float) width2;
                        if (f > f2 && x > f2) {
                            i = (int) Math.ceil((double) ((x - f2) / ((float) width)));
                        }
                    } else {
                        f2 = (float) width2;
                        if (f < f2 && x < f2) {
                            i = -((int) Math.ceil((double) ((f2 - x) / ((float) width))));
                        }
                    }
                } else {
                    f2 = (float) ((width2 - width) / 2);
                    if (f >= f2 || x >= f2) {
                        f2 = (float) ((width2 + width) / 2);
                        if (f > f2 && x > f2) {
                            i = (int) Math.ceil((double) ((x - f2) / ((float) width)));
                        }
                    } else {
                        i = -((int) Math.ceil((double) ((f2 - x) / ((float) width))));
                    }
                }
                if (i != 0) {
                    motionEvent.setAction(3);
                    this.c.setCurrentItem(this.c.getCurrentItem() + i);
                }
                motionEvent.offsetLocation((float) (this.d.x - this.e.x), (float) (this.d.y - this.e.y));
                break;
        }
        motionEvent.offsetLocation((float) (this.d.x - this.e.x), (float) (this.d.y - this.e.y));
        return this.c.dispatchTouchEvent(motionEvent);
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.f) {
            invalidate();
        }
    }

    public void onPageSelected(int i) {
        LayoutParams layoutParams = (LayoutParams) this.c.getLayoutParams();
        if (this.a != null) {
            layoutParams.gravity = this.a.a(i);
            this.c.requestLayout();
        }
    }

    public void onPageScrollStateChanged(int i) {
        this.f = i != 0;
    }
}
