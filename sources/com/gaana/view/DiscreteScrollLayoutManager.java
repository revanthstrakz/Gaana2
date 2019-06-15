package com.gaana.view;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.fragments.PlayerFragmentV4;
import com.gaana.GaanaActivity;
import com.gaana.fragments.BaseFragment;
import com.gaana.view.transform.DiscreteScrollItemTransformer;

public class DiscreteScrollLayoutManager extends LayoutManager {
    private static final int DEFAULT_FLING_THRESHOLD = 2100;
    private static final int DEFAULT_TIME_FOR_ITEM_SETTLE = 300;
    private static final String EXTRA_POSITION = "extra_position";
    static final int NO_POSITION = -1;
    private int childHalfHeight;
    private int childHalfWidth;
    private Context context;
    private int currentPosition = -1;
    private int currentScrollState;
    private Point currentViewCenter = new Point();
    private boolean dataSetChangeShiftedPosition;
    private SparseArray<View> detachedCache = new SparseArray();
    private int extraLayoutSpace;
    private int flingThreshold = 2100;
    private boolean isFirstOrEmptyLayout;
    private DiscreteScrollItemTransformer itemTransformer;
    private int offscreenItems;
    private HorizontalHelper orientationHelper;
    private int pendingPosition = -1;
    private int pendingScroll;
    private BaseFragment playerFragment;
    private Point recyclerCenter = new Point();
    @NonNull
    private final ScrollStateListener scrollStateListener;
    private int scrollToChangeCurrent;
    private int scrolled;
    private boolean shouldSlideOnFling = false;
    private int timeForItemSettle = 300;
    private Point viewCenterIterator = new Point();

    protected static class HorizontalHelper {
        public boolean canScrollHorizontally() {
            return true;
        }

        public boolean canScrollVertically() {
            return false;
        }

        public int getDistanceToChangeCurrent(int i, int i2) {
            return i;
        }

        public int getFlingVelocity(int i, int i2) {
            return i;
        }

        public int getPendingDx(int i) {
            return i;
        }

        public int getPendingDy(int i) {
            return 0;
        }

        public int getViewEnd(int i, int i2) {
            return i;
        }

        protected HorizontalHelper() {
        }

        public void setCurrentViewCenter(Point point, int i, Point point2) {
            point2.set(point.x - i, point.y);
        }

        public void shiftViewCenter(Direction direction, int i, Point point) {
            point.set(point.x + direction.applyTo(i), point.y);
        }

        public boolean isViewVisible(Point point, int i, int i2, int i3, int i4) {
            return point.x - i < i3 + i4 && point.x + i > (-i4);
        }

        public boolean hasNewBecomeVisible(DiscreteScrollLayoutManager discreteScrollLayoutManager) {
            View firstChild = discreteScrollLayoutManager.getFirstChild();
            View lastChild = discreteScrollLayoutManager.getLastChild();
            int width = discreteScrollLayoutManager.getWidth() + discreteScrollLayoutManager.getExtraLayoutSpace();
            int i = (discreteScrollLayoutManager.getDecoratedLeft(firstChild) <= (-discreteScrollLayoutManager.getExtraLayoutSpace()) || discreteScrollLayoutManager.getPosition(firstChild) <= 0) ? false : 1;
            int i2 = (discreteScrollLayoutManager.getDecoratedRight(lastChild) >= width || discreteScrollLayoutManager.getPosition(lastChild) >= discreteScrollLayoutManager.getItemCount() - 1) ? false : 1;
            if (i == 0 && i2 == 0) {
                return false;
            }
            return true;
        }

        public void offsetChildren(int i, LayoutManager layoutManager) {
            layoutManager.offsetChildrenHorizontal(i);
        }

        public float getDistanceFromCenter(Point point, int i, int i2) {
            return (float) (i - point.x);
        }
    }

    public interface ScrollStateListener {
        void onCurrentViewFirstLayout();

        void onDataSetChangeChangedPosition();

        void onIsBoundReachedFlagChange(boolean z);

        void onScroll(float f);

        void onScrollEnd();

        void onScrollStart();
    }

    private class DiscreteLinearSmoothScroller extends LinearSmoothScroller {
        public DiscreteLinearSmoothScroller(Context context) {
            super(context);
        }

        public int calculateDxToMakeVisible(View view, int i) {
            return DiscreteScrollLayoutManager.this.orientationHelper.getPendingDx(-DiscreteScrollLayoutManager.this.pendingScroll);
        }

        public int calculateDyToMakeVisible(View view, int i) {
            return DiscreteScrollLayoutManager.this.orientationHelper.getPendingDy(-DiscreteScrollLayoutManager.this.pendingScroll);
        }

        /* Access modifiers changed, original: protected */
        public int calculateTimeForScrolling(int i) {
            return (int) (Math.max(0.01f, ((float) Math.min(Math.abs(i), DiscreteScrollLayoutManager.this.scrollToChangeCurrent)) / ((float) DiscreteScrollLayoutManager.this.scrollToChangeCurrent)) * ((float) DiscreteScrollLayoutManager.this.timeForItemSettle));
        }

        @Nullable
        public PointF computeScrollVectorForPosition(int i) {
            return new PointF((float) DiscreteScrollLayoutManager.this.orientationHelper.getPendingDx(DiscreteScrollLayoutManager.this.pendingScroll), (float) DiscreteScrollLayoutManager.this.orientationHelper.getPendingDy(DiscreteScrollLayoutManager.this.pendingScroll));
        }
    }

    public boolean canScrollHorizontally() {
        return true;
    }

    public boolean canScrollVertically() {
        return false;
    }

    public DiscreteScrollLayoutManager(@NonNull Context context, @NonNull ScrollStateListener scrollStateListener, @NonNull HorizontalHelper horizontalHelper) {
        this.context = context;
        this.scrollStateListener = scrollStateListener;
        this.orientationHelper = horizontalHelper;
        setAutoMeasureEnabled(true);
        if (this.context instanceof GaanaActivity) {
            this.playerFragment = ((GaanaActivity) this.context).getmCurrentPlayerFragment();
        }
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        boolean z = false;
        if (state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            this.pendingPosition = -1;
            this.currentPosition = -1;
            this.pendingScroll = 0;
            this.scrolled = 0;
            return;
        }
        if (this.currentPosition <= -1 || this.currentPosition >= getItemCount()) {
            this.currentPosition = 0;
        }
        if (!this.isFirstOrEmptyLayout) {
            if (getChildCount() == 0) {
                z = true;
            }
            this.isFirstOrEmptyLayout = z;
            if (this.isFirstOrEmptyLayout) {
                initChildDimensions(recycler);
            }
        }
        updateRecyclerDimensions();
        detachAndScrapAttachedViews(recycler);
        fill(recycler);
        applyItemTransformToChildren();
    }

    public void onLayoutCompleted(State state) {
        if (this.isFirstOrEmptyLayout) {
            this.scrollStateListener.onCurrentViewFirstLayout();
            this.isFirstOrEmptyLayout = false;
        } else if (this.dataSetChangeShiftedPosition) {
            this.scrollStateListener.onDataSetChangeChangedPosition();
            this.dataSetChangeShiftedPosition = false;
        }
    }

    private void initChildDimensions(Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(0);
        addView(viewForPosition);
        measureChildWithMargins(viewForPosition, 0, 0);
        int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
        int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
        this.childHalfWidth = decoratedMeasuredWidth / 2;
        this.childHalfHeight = decoratedMeasuredHeight / 2;
        this.scrollToChangeCurrent = this.orientationHelper.getDistanceToChangeCurrent(decoratedMeasuredWidth, decoratedMeasuredHeight);
        this.extraLayoutSpace = this.scrollToChangeCurrent * this.offscreenItems;
        detachAndScrapView(viewForPosition, recycler);
    }

    private void updateRecyclerDimensions() {
        this.recyclerCenter.set(getWidth() / 2, getHeight() / 2);
    }

    private void fill(Recycler recycler) {
        cacheAndDetachAttachedViews();
        this.orientationHelper.setCurrentViewCenter(this.recyclerCenter, this.scrolled, this.currentViewCenter);
        int viewEnd = this.orientationHelper.getViewEnd(getWidth(), getHeight());
        if (isViewVisible(this.currentViewCenter, viewEnd)) {
            layoutView(recycler, this.currentPosition, this.currentViewCenter);
        }
        layoutViews(recycler, Direction.START, viewEnd);
        layoutViews(recycler, Direction.END, viewEnd);
        recycleViewsAndClearCache(recycler);
    }

    private void layoutViews(Recycler recycler, Direction direction, int i) {
        int applyTo = direction.applyTo(1);
        int i2 = (this.pendingPosition == -1 || !direction.sameAs(this.pendingPosition - this.currentPosition)) ? 1 : 0;
        this.viewCenterIterator.set(this.currentViewCenter.x, this.currentViewCenter.y);
        int i3 = this.currentPosition;
        while (true) {
            i3 += applyTo;
            if (isInBounds(i3)) {
                if (i3 == this.pendingPosition) {
                    i2 = 1;
                }
                this.orientationHelper.shiftViewCenter(direction, this.scrollToChangeCurrent, this.viewCenterIterator);
                if (isViewVisible(this.viewCenterIterator, i)) {
                    layoutView(recycler, i3, this.viewCenterIterator);
                } else if (i2 != 0) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private void layoutView(Recycler recycler, int i, Point point) {
        if (i >= 0 && i < getItemCount()) {
            View view = (View) this.detachedCache.get(i);
            if (view == null) {
                View viewForPosition = recycler.getViewForPosition(i);
                addView(viewForPosition);
                measureChildWithMargins(viewForPosition, 0, 0);
                if (this.playerFragment == null || !(this.playerFragment instanceof PlayerFragmentV4)) {
                    layoutDecoratedWithMargins(viewForPosition, point.x - this.childHalfWidth, point.y - this.childHalfHeight, point.x + this.childHalfWidth, point.y + this.childHalfHeight);
                } else {
                    layoutDecoratedWithMargins(viewForPosition, point.x - this.childHalfWidth, 0, point.x + this.childHalfWidth, point.y + this.childHalfHeight);
                }
            } else {
                attachView(view);
                this.detachedCache.remove(i);
            }
        }
    }

    private void cacheAndDetachAttachedViews() {
        this.detachedCache.clear();
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            this.detachedCache.put(getPosition(childAt), childAt);
        }
        while (i < this.detachedCache.size()) {
            detachView((View) this.detachedCache.valueAt(i));
            i++;
        }
    }

    private void recycleViewsAndClearCache(Recycler recycler) {
        for (int i = 0; i < this.detachedCache.size(); i++) {
            recycler.recycleView((View) this.detachedCache.valueAt(i));
        }
        this.detachedCache.clear();
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        int i3 = this.currentPosition;
        if (this.currentPosition == -1) {
            i3 = 0;
        } else if (this.currentPosition >= i) {
            i3 = Math.min(this.currentPosition + i2, getItemCount() - 1);
        }
        onNewPosition(i3);
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        int i3 = this.currentPosition;
        if (getItemCount() == 0) {
            i3 = -1;
        } else if (this.currentPosition >= i) {
            if (this.currentPosition < i + i2) {
                this.currentPosition = -1;
            }
            i3 = Math.max(0, this.currentPosition - i2);
        }
        onNewPosition(i3);
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.currentPosition = Math.min(Math.max(0, this.currentPosition), getItemCount() - 1);
        this.dataSetChangeShiftedPosition = true;
    }

    private void onNewPosition(int i) {
        if (this.currentPosition != i) {
            this.currentPosition = i;
            this.dataSetChangeShiftedPosition = true;
        }
    }

    public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
        return scrollBy(i, recycler);
    }

    public int scrollVerticallyBy(int i, Recycler recycler, State state) {
        return scrollBy(i, recycler);
    }

    private int scrollBy(int i, Recycler recycler) {
        if (getChildCount() == 0) {
            return 0;
        }
        Direction fromDelta = Direction.fromDelta(i);
        int calculateAllowedScrollIn = calculateAllowedScrollIn(fromDelta);
        if (calculateAllowedScrollIn <= 0) {
            return 0;
        }
        i = fromDelta.applyTo(Math.min(calculateAllowedScrollIn, Math.abs(i)));
        this.scrolled += i;
        if (this.pendingScroll != 0) {
            this.pendingScroll -= i;
        }
        this.orientationHelper.offsetChildren(-i, this);
        if (this.orientationHelper.hasNewBecomeVisible(this)) {
            fill(recycler);
        }
        notifyScroll();
        applyItemTransformToChildren();
        return i;
    }

    public void applyItemTransformToChildren() {
        if (this.itemTransformer != null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                this.itemTransformer.transformItem(childAt, getCenterRelativePositionOf(childAt));
            }
        }
    }

    public void scrollToPosition(int i) {
        if (this.currentPosition != i) {
            this.currentPosition = i;
            requestLayout();
        }
    }

    /* JADX WARNING: Missing block: B:11:0x001b, code skipped:
            return;
     */
    public void smoothScrollToPosition(android.support.v7.widget.RecyclerView r2, android.support.v7.widget.RecyclerView.State r3, int r4) {
        /*
        r1 = this;
        r2 = r1.currentPosition;
        if (r2 == r4) goto L_0x001b;
    L_0x0004:
        r2 = r1.pendingPosition;
        r0 = -1;
        if (r2 == r0) goto L_0x000a;
    L_0x0009:
        goto L_0x001b;
    L_0x000a:
        r2 = r1.isValidTargetPosition(r3, r4);
        if (r2 == 0) goto L_0x001a;
    L_0x0010:
        r2 = r1.currentPosition;
        if (r2 != r0) goto L_0x0017;
    L_0x0014:
        r1.currentPosition = r4;
        goto L_0x001a;
    L_0x0017:
        r1.startSmoothPendingScroll(r4);
    L_0x001a:
        return;
    L_0x001b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.DiscreteScrollLayoutManager.smoothScrollToPosition(android.support.v7.widget.RecyclerView, android.support.v7.widget.RecyclerView$State, int):void");
    }

    private boolean isValidTargetPosition(State state, int i) {
        return i >= 0 && i < state.getItemCount();
    }

    public void onScrollStateChanged(int i) {
        if (this.currentScrollState == 0 && this.currentScrollState != i) {
            this.scrollStateListener.onScrollStart();
        }
        if (i == 0) {
            if (onScrollEnd()) {
                this.scrollStateListener.onScrollEnd();
            } else {
                return;
            }
        } else if (i == 1) {
            onDragStart();
        }
        this.currentScrollState = i;
    }

    private boolean onScrollEnd() {
        if (this.pendingPosition != -1) {
            this.currentPosition = this.pendingPosition;
            this.pendingPosition = -1;
            this.scrolled = 0;
        }
        Direction fromDelta = Direction.fromDelta(this.scrolled);
        if (Math.abs(this.scrolled) == this.scrollToChangeCurrent) {
            this.currentPosition += fromDelta.applyTo(1);
            this.scrolled = 0;
        }
        if (isAnotherItemCloserThanCurrent()) {
            this.pendingScroll = getHowMuchIsLeftToScroll(this.scrolled);
        } else {
            this.pendingScroll = -this.scrolled;
        }
        if (this.pendingScroll == 0) {
            return true;
        }
        startSmoothPendingScroll();
        return false;
    }

    private void onDragStart() {
        if ((Math.abs(this.scrolled) > this.scrollToChangeCurrent ? 1 : 0) != 0) {
            int i = this.scrolled / this.scrollToChangeCurrent;
            this.currentPosition += i;
            this.scrolled -= i * this.scrollToChangeCurrent;
        }
        if (isAnotherItemCloserThanCurrent()) {
            this.currentPosition += Direction.fromDelta(this.scrolled).applyTo(1);
            this.scrolled = -getHowMuchIsLeftToScroll(this.scrolled);
        }
        this.pendingPosition = -1;
        this.pendingScroll = 0;
    }

    public void onFling(int i, int i2) {
        i = this.orientationHelper.getFlingVelocity(i, i2);
        Object obj = 1;
        i2 = checkNewOnFlingPositionIsInBounds(this.currentPosition + Direction.fromDelta(i).applyTo(this.shouldSlideOnFling ? Math.abs(i / this.flingThreshold) : 1));
        if ((i * this.scrolled >= 0 ? 1 : null) == null || !isInBounds(i2)) {
            obj = null;
        }
        if (obj != null) {
            startSmoothPendingScroll(i2);
        } else {
            returnToCurrentPosition();
        }
    }

    public void returnToCurrentPosition() {
        this.pendingScroll = -this.scrolled;
        if (this.pendingScroll != 0) {
            startSmoothPendingScroll();
        }
    }

    private int calculateAllowedScrollIn(Direction direction) {
        if (this.pendingScroll != 0) {
            return Math.abs(this.pendingScroll);
        }
        boolean z = true;
        int i = 0;
        boolean z2 = direction.applyTo(this.scrolled) > 0;
        if (direction == Direction.START && this.currentPosition == 0) {
            if (this.scrolled != 0) {
                z = false;
            }
            if (!z) {
                i = Math.abs(this.scrolled);
            }
        } else if (direction == Direction.END && this.currentPosition == getItemCount() - 1) {
            if (this.scrolled != 0) {
                z = false;
            }
            if (!z) {
                i = Math.abs(this.scrolled);
            }
        } else {
            int abs;
            if (z2) {
                abs = this.scrollToChangeCurrent - Math.abs(this.scrolled);
            } else {
                abs = this.scrollToChangeCurrent + Math.abs(this.scrolled);
            }
            z = false;
            i = abs;
        }
        this.scrollStateListener.onIsBoundReachedFlagChange(z);
        return i;
    }

    private void startSmoothPendingScroll() {
        DiscreteLinearSmoothScroller discreteLinearSmoothScroller = new DiscreteLinearSmoothScroller(this.context);
        discreteLinearSmoothScroller.setTargetPosition(this.currentPosition);
        startSmoothScroll(discreteLinearSmoothScroller);
    }

    private void startSmoothPendingScroll(int i) {
        if (this.currentPosition != i) {
            this.pendingScroll = -this.scrolled;
            this.pendingScroll += Direction.fromDelta(i - this.currentPosition).applyTo(Math.abs(i - this.currentPosition) * this.scrollToChangeCurrent);
            this.pendingPosition = i;
            startSmoothPendingScroll();
        }
    }

    public void onAdapterChanged(Adapter adapter, Adapter adapter2) {
        this.pendingPosition = -1;
        this.pendingScroll = 0;
        this.scrolled = 0;
        this.currentPosition = 0;
        removeAllViews();
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (this.pendingPosition != -1) {
            this.currentPosition = this.pendingPosition;
        }
        bundle.putInt(EXTRA_POSITION, this.currentPosition);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        this.currentPosition = ((Bundle) parcelable).getInt(EXTRA_POSITION);
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public int getNextPosition() {
        if (this.scrolled == 0) {
            return this.currentPosition;
        }
        if (this.pendingPosition != -1) {
            return this.pendingPosition;
        }
        return this.currentPosition + Direction.fromDelta(this.scrolled).applyTo(1);
    }

    public void setItemTransformer(DiscreteScrollItemTransformer discreteScrollItemTransformer) {
        this.itemTransformer = discreteScrollItemTransformer;
    }

    public void setTimeForItemSettle(int i) {
        this.timeForItemSettle = i;
    }

    public void setOffscreenItems(int i) {
        this.offscreenItems = i;
        this.extraLayoutSpace = this.scrollToChangeCurrent * i;
        requestLayout();
    }

    public void setOrientation() {
        this.orientationHelper = new HorizontalHelper();
        removeAllViews();
        requestLayout();
    }

    public void setShouldSlideOnFling(boolean z) {
        this.shouldSlideOnFling = z;
    }

    public void setSlideOnFlingThreshold(int i) {
        this.flingThreshold = i;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            AccessibilityRecordCompat asRecord = AccessibilityEventCompat.asRecord(accessibilityEvent);
            asRecord.setFromIndex(getPosition(getFirstChild()));
            asRecord.setToIndex(getPosition(getLastChild()));
        }
    }

    private float getCenterRelativePositionOf(View view) {
        return Math.min(Math.max(-1.0f, this.orientationHelper.getDistanceFromCenter(this.recyclerCenter, getDecoratedLeft(view) + this.childHalfWidth, getDecoratedTop(view) + this.childHalfHeight) / ((float) this.scrollToChangeCurrent)), 1.0f);
    }

    private int checkNewOnFlingPositionIsInBounds(int i) {
        if (this.currentPosition == 0 || i >= 0) {
            return (this.currentPosition == getItemCount() + -1 || i < getItemCount()) ? i : getItemCount() - 1;
        } else {
            return 0;
        }
    }

    private int getHowMuchIsLeftToScroll(int i) {
        return Direction.fromDelta(i).applyTo(this.scrollToChangeCurrent - Math.abs(this.scrolled));
    }

    private boolean isAnotherItemCloserThanCurrent() {
        return ((float) Math.abs(this.scrolled)) >= ((float) this.scrollToChangeCurrent) * 0.6f;
    }

    public View getFirstChild() {
        return getChildAt(0);
    }

    public View getLastChild() {
        return getChildAt(getChildCount() - 1);
    }

    public int getExtraLayoutSpace() {
        return this.extraLayoutSpace;
    }

    private void notifyScroll() {
        this.scrollStateListener.onScroll(-Math.min(Math.max(-1.0f, ((float) this.scrolled) / ((float) (this.pendingPosition != -1 ? Math.abs(this.scrolled + this.pendingScroll) : this.scrollToChangeCurrent))), 1.0f));
    }

    private boolean isInBounds(int i) {
        return i >= 0 && i < getItemCount();
    }

    private boolean isViewVisible(Point point, int i) {
        return this.orientationHelper.isViewVisible(point, this.childHalfWidth, this.childHalfHeight, i, this.extraLayoutSpace);
    }
}
