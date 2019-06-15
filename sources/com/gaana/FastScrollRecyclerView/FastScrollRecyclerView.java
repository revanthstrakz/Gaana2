package com.gaana.FastScrollRecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.collapsible_header.ObservableRecyclerView;

public class FastScrollRecyclerView extends ObservableRecyclerView implements OnItemTouchListener {
    private int mDownX;
    private int mDownY;
    private int mLastY;
    private ScrollPositionState mScrollPosState;
    private FastScroller mScrollbar;

    public static class ScrollPositionState {
        public int rowHeight;
        public int rowIndex;
        public int rowTopOffset;
    }

    public interface SectionedAdapter {
        @NonNull
        String getSectionName(int i);
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    public FastScrollRecyclerView(Context context) {
        this(context, null);
    }

    public FastScrollRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FastScrollRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScrollPosState = new ScrollPositionState();
        this.mScrollbar = new FastScroller(context, this, attributeSet);
    }

    public int getScrollBarWidth() {
        return this.mScrollbar.getWidth();
    }

    public int getScrollBarThumbHeight() {
        return this.mScrollbar.getThumbHeight();
    }

    /* Access modifiers changed, original: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        addOnItemTouchListener(this);
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        return handleTouchEvent(motionEvent);
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        handleTouchEvent(motionEvent);
    }

    private boolean handleTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.mDownX = x;
                this.mLastY = y;
                this.mDownY = y;
                this.mScrollbar.handleTouchEvent(motionEvent, this.mDownX, this.mDownY, this.mLastY);
                break;
            case 1:
            case 3:
                this.mScrollbar.handleTouchEvent(motionEvent, this.mDownX, this.mDownY, this.mLastY);
                break;
            case 2:
                this.mLastY = y;
                this.mScrollbar.handleTouchEvent(motionEvent, this.mDownX, this.mDownY, this.mLastY);
                break;
        }
        return this.mScrollbar.isDragging();
    }

    /* Access modifiers changed, original: protected */
    public int getAvailableScrollHeight(int i, int i2, int i3) {
        return (((getPaddingTop() + i3) + (i * i2)) + getPaddingBottom()) - getHeight();
    }

    /* Access modifiers changed, original: protected */
    public int getAvailableScrollBarHeight() {
        return getHeight() - this.mScrollbar.getThumbHeight();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        onUpdateScrollbar();
        this.mScrollbar.draw(canvas);
    }

    /* Access modifiers changed, original: protected */
    public void synchronizeScrollBarThumbOffsetToViewScroll(ScrollPositionState scrollPositionState, int i, int i2) {
        i = getAvailableScrollHeight(i, scrollPositionState.rowHeight, i2);
        int availableScrollBarHeight = getAvailableScrollBarHeight();
        if (i <= 0) {
            this.mScrollbar.setThumbPosition(-1, -1);
            return;
        }
        int paddingTop = (int) ((((float) (((getPaddingTop() + i2) + (scrollPositionState.rowIndex * scrollPositionState.rowHeight)) - scrollPositionState.rowTopOffset)) / ((float) i)) * ((float) availableScrollBarHeight));
        if (Utils.isRtl(getResources())) {
            i = 0;
        } else {
            i = getWidth() - this.mScrollbar.getWidth();
        }
        this.mScrollbar.setThumbPosition(i, paddingTop);
    }

    public String scrollToPositionAtProgress(float f) {
        if (getAdapter() == null) {
            return "";
        }
        int itemCount = getAdapter().getItemCount();
        if (itemCount == 0) {
            return "";
        }
        int spanCount;
        int ceil;
        if (getLayoutManager() instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) getLayoutManager()).getSpanCount();
            ceil = (int) Math.ceil(((double) itemCount) / ((double) spanCount));
        } else {
            ceil = itemCount;
            spanCount = 1;
        }
        stopScroll();
        getCurScrollState(this.mScrollPosState);
        float f2 = ((float) itemCount) * f;
        ceil = (int) (((float) getAvailableScrollHeight(ceil, this.mScrollPosState.rowHeight, 0)) * f);
        if (this.mScrollPosState.rowHeight == 0) {
            this.mScrollPosState.rowHeight = 1;
        }
        ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset((spanCount * ceil) / this.mScrollPosState.rowHeight, -(ceil % this.mScrollPosState.rowHeight));
        if (!(getAdapter() instanceof SectionedAdapter)) {
            return "";
        }
        if (f == 1.0f) {
            f2 -= 1.0f;
        }
        return ((SectionedAdapter) getAdapter()).getSectionName((int) f2);
    }

    public void onUpdateScrollbar() {
        if (getAdapter() != null) {
            int itemCount = getAdapter().getItemCount();
            if (getLayoutManager() instanceof GridLayoutManager) {
                itemCount = (int) Math.ceil(((double) itemCount) / ((double) ((GridLayoutManager) getLayoutManager()).getSpanCount()));
            }
            if (itemCount == 0) {
                this.mScrollbar.setThumbPosition(-1, -1);
                return;
            }
            getCurScrollState(this.mScrollPosState);
            if (this.mScrollPosState.rowIndex < 0) {
                this.mScrollbar.setThumbPosition(-1, -1);
            } else {
                synchronizeScrollBarThumbOffsetToViewScroll(this.mScrollPosState, itemCount, 0);
            }
        }
    }

    private void getCurScrollState(ScrollPositionState scrollPositionState) {
        scrollPositionState.rowIndex = -1;
        scrollPositionState.rowTopOffset = -1;
        scrollPositionState.rowHeight = -1;
        if (getAdapter().getItemCount() != 0 && getChildCount() != 0) {
            View childAt = getChildAt(0);
            scrollPositionState.rowIndex = getChildAdapterPosition(childAt);
            if (getLayoutManager() instanceof GridLayoutManager) {
                scrollPositionState.rowIndex /= ((GridLayoutManager) getLayoutManager()).getSpanCount();
            }
            scrollPositionState.rowTopOffset = getLayoutManager().getDecoratedTop(childAt);
            scrollPositionState.rowHeight = childAt.getHeight();
        }
    }

    public void setThumbColor(@ColorInt int i) {
        this.mScrollbar.setThumbColor(i);
    }

    public void setTrackColor(@ColorInt int i) {
        this.mScrollbar.setTrackColor(i);
    }

    public void setPopupBgColor(@ColorInt int i) {
        this.mScrollbar.setPopupBgColor(i);
    }

    public void setPopupTextColor(@ColorInt int i) {
        this.mScrollbar.setPopupTextColor(i);
    }

    public void setAutoHideDelay(int i) {
        this.mScrollbar.setAutoHideDelay(i);
    }

    public void setAutoHideEnabled(boolean z) {
        this.mScrollbar.setAutoHideEnabled(z);
    }

    public void showHidePopup(boolean z) {
        if (this.mScrollbar != null) {
            this.mScrollbar.showHidePopup(z);
        }
    }
}
