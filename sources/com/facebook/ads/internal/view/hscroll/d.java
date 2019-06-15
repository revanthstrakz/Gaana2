package com.facebook.ads.internal.view.hscroll;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class d extends RecyclerView implements OnTouchListener {
    protected final int a = a();
    protected int b = 0;
    private int c = 0;
    private boolean d = true;
    private boolean e = false;
    private LinearLayoutManager f;
    private a g;

    public interface a {
        int a(int i);
    }

    public d(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnTouchListener(this);
    }

    public d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnTouchListener(this);
    }

    private int a() {
        return ((int) getContext().getResources().getDisplayMetrics().density) * 10;
    }

    private int a(int i) {
        int i2 = this.c - i;
        i = this.g.a(i2);
        return i2 > this.a ? a(this.b, i) : i2 < (-this.a) ? b(this.b, i) : this.b;
    }

    private int a(int i, int i2) {
        return Math.min(i + i2, getItemCount() - 1);
    }

    private int b(int i, int i2) {
        return Math.max(i - i2, 0);
    }

    private int getItemCount() {
        return getAdapter() == null ? 0 : getAdapter().getItemCount();
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, boolean z) {
        if (getAdapter() != null) {
            this.b = i;
            if (z) {
                smoothScrollToPosition(i);
            } else {
                scrollToPosition(i);
            }
        }
    }

    public int getCurrentPosition() {
        return this.b;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 6 || actionMasked == 3 || actionMasked == 4) {
            if (this.e) {
                a(a(rawX), true);
            }
            this.d = true;
            this.e = false;
            return true;
        }
        if (actionMasked == 0 || actionMasked == 5 || (this.d && actionMasked == 2)) {
            this.c = rawX;
            if (this.d) {
                this.d = false;
            }
            this.e = true;
        }
        return false;
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            super.setLayoutManager(layoutManager);
            this.f = (LinearLayoutManager) layoutManager;
            return;
        }
        throw new IllegalArgumentException("SnapRecyclerView only supports LinearLayoutManager");
    }

    public void setSnapDelegate(a aVar) {
        this.g = aVar;
    }
}
