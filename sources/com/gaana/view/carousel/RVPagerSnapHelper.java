package com.gaana.view.carousel;

import android.os.Build.VERSION;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import kotlin.TypeCastException;
import kotlin.jvm.internal.c;

public class RVPagerSnapHelper extends PagerSnapHelper implements OnGlobalLayoutListener {
    private final OnPageChangeListener externalListener;
    private int lastPage = -1;
    private final RecyclerView recyclerView;

    public final OnPageChangeListener getExternalListener() {
        return this.externalListener;
    }

    /* Access modifiers changed, original: protected|final */
    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public RVPagerSnapHelper(RecyclerView recyclerView, OnPageChangeListener onPageChangeListener) {
        c.b(recyclerView, "recyclerView");
        c.b(onPageChangeListener, "externalListener");
        this.recyclerView = recyclerView;
        this.externalListener = onPageChangeListener;
        this.recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public final int getLastPage() {
        return this.lastPage;
    }

    public final void setLastPage(int i) {
        this.lastPage = i;
    }

    public void onGlobalLayout() {
        LayoutManager layoutManager = this.recyclerView.getLayoutManager();
        if (layoutManager == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.LinearLayoutManager");
        }
        int findFirstCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        if (findFirstCompletelyVisibleItemPosition != -1) {
            notifyNewPageIfNeeded(findFirstCompletelyVisibleItemPosition);
            if (VERSION.SDK_INT >= 16) {
                this.recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }

    public View findSnapView(LayoutManager layoutManager) {
        View findSnapView = super.findSnapView(layoutManager);
        notifyNewPageIfNeeded(this.recyclerView.getChildAdapterPosition(findSnapView));
        return findSnapView;
    }

    public int findTargetSnapPosition(LayoutManager layoutManager, int i, int i2) {
        int findTargetSnapPosition = super.findTargetSnapPosition(layoutManager, i, i2);
        if (findTargetSnapPosition > -1) {
            Object adapter = this.recyclerView.getAdapter();
            c.a(adapter, "recyclerView.adapter");
            if (findTargetSnapPosition < adapter.getItemCount()) {
                notifyNewPageIfNeeded(findTargetSnapPosition);
            }
        }
        return findTargetSnapPosition;
    }

    /* Access modifiers changed, original: protected|final */
    public final void notifyNewPageIfNeeded(int i) {
        if (i != this.lastPage) {
            this.externalListener.onPageSelected(i);
            this.lastPage = i;
        }
    }
}
