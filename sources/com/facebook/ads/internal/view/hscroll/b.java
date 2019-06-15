package com.facebook.ads.internal.view.hscroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

public class b extends d implements com.facebook.ads.internal.view.hscroll.d.a {
    private final HScrollLinearLayoutManager c;
    private a d;
    private int e = -1;
    private int f = -1;
    private int g = 0;
    private int h = 0;

    public interface a {
        void a(int i, int i2);
    }

    public b(Context context) {
        super(context);
        this.c = new HScrollLinearLayoutManager(context, new c(), new a());
        a();
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new HScrollLinearLayoutManager(context, new c(), new a());
        a();
    }

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new HScrollLinearLayoutManager(context, new c(), new a());
        a();
    }

    private void a() {
        this.c.setOrientation(0);
        setLayoutManager(this.c);
        setSaveEnabled(false);
        setSnapDelegate(this);
    }

    private void a(int i, int i2) {
        if (i != this.e || i2 != this.f) {
            this.e = i;
            this.f = i2;
            if (this.d != null) {
                this.d.a(this.e, this.f);
            }
        }
    }

    private int b(int i) {
        int i2 = this.h * 2;
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - i2;
        int itemCount = getAdapter().getItemCount();
        int i3 = 0;
        int i4 = Integer.MAX_VALUE;
        while (i4 > i) {
            i3++;
            if (i3 >= itemCount) {
                return i;
            }
            i4 = (int) (((float) (measuredWidth - (i3 * i2))) / (((float) i3) + 0.333f));
        }
        return i4;
    }

    public int a(int i) {
        i = Math.abs(i);
        return i <= this.a ? 0 : this.g == 0 ? 1 : 1 + (i / this.g);
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, boolean z) {
        super.a(i, z);
        a(i, 0);
    }

    public int getChildSpacing() {
        return this.h;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        i = Math.round(((float) getMeasuredWidth()) / 1.91f);
        int mode = MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            i = Math.min(MeasureSpec.getSize(i2), i);
        } else if (mode == 1073741824) {
            i = MeasureSpec.getSize(i2);
        }
        i2 = getPaddingTop() + getPaddingBottom();
        i = b(i - i2);
        setMeasuredDimension(getMeasuredWidth(), i2 + i);
        setChildWidth(i + (this.h * 2));
    }

    public void setAdapter(@Nullable Adapter adapter) {
        this.c.a(adapter == null ? -1 : adapter.hashCode());
        super.setAdapter(adapter);
    }

    public void setChildSpacing(int i) {
        this.h = i;
    }

    public void setChildWidth(int i) {
        this.g = i;
        i = getMeasuredWidth();
        this.c.b((((i - getPaddingLeft()) - getPaddingRight()) - this.g) / 2);
        this.c.a(((double) this.g) / ((double) i));
    }

    public void setCurrentPosition(int i) {
        a(i, false);
    }

    public void setOnPageChangedListener(a aVar) {
        this.d = aVar;
    }
}
