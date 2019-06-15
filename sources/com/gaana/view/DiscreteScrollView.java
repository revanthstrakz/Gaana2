package com.gaana.view;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.View;
import com.gaana.view.transform.DiscreteScrollItemTransformer;
import java.util.ArrayList;
import java.util.List;

public class DiscreteScrollView extends RecyclerView {
    private static final int DEFAULT_ORIENTATION = 0;
    public static final int NO_POSITION = -1;
    private DiscreteScrollLayoutManager layoutManager;
    private List<OnItemChangedListener> onItemChangedListeners;
    private List<ScrollStateChangeListener> scrollStateChangeListeners;

    public interface OnItemChangedListener<T extends ViewHolder> {
        void onCurrentItemChanged(@Nullable T t, int i);
    }

    public interface ScrollListener<T extends ViewHolder> {
        void onScroll(float f, int i, int i2, @Nullable T t, @Nullable T t2);
    }

    public interface ScrollStateChangeListener<T extends ViewHolder> {
        void onScroll(float f, int i, int i2, @Nullable T t, @Nullable T t2);

        void onScrollEnd(@NonNull T t, int i);

        void onScrollStart(@NonNull T t, int i);
    }

    private class ScrollStateListener implements com.gaana.view.DiscreteScrollLayoutManager.ScrollStateListener {
        private ScrollStateListener() {
        }

        public void onIsBoundReachedFlagChange(boolean z) {
            DiscreteScrollView.this.setOverScrollMode(z ? 0 : 2);
        }

        public void onScrollStart() {
            if (!DiscreteScrollView.this.scrollStateChangeListeners.isEmpty()) {
                int currentPosition = DiscreteScrollView.this.layoutManager.getCurrentPosition();
                ViewHolder viewHolder = DiscreteScrollView.this.getViewHolder(currentPosition);
                if (viewHolder != null) {
                    DiscreteScrollView.this.notifyScrollStart(viewHolder, currentPosition);
                }
            }
        }

        public void onScrollEnd() {
            if (!DiscreteScrollView.this.onItemChangedListeners.isEmpty() || !DiscreteScrollView.this.scrollStateChangeListeners.isEmpty()) {
                int currentPosition = DiscreteScrollView.this.layoutManager.getCurrentPosition();
                ViewHolder viewHolder = DiscreteScrollView.this.getViewHolder(currentPosition);
                if (viewHolder != null) {
                    DiscreteScrollView.this.notifyScrollEnd(viewHolder, currentPosition);
                    DiscreteScrollView.this.notifyCurrentItemChanged(viewHolder, currentPosition);
                }
            }
        }

        public void onScroll(float f) {
            if (!DiscreteScrollView.this.scrollStateChangeListeners.isEmpty()) {
                int currentItem = DiscreteScrollView.this.getCurrentItem();
                int nextPosition = DiscreteScrollView.this.layoutManager.getNextPosition();
                if (currentItem != nextPosition) {
                    DiscreteScrollView.this.notifyScroll(f, currentItem, nextPosition, DiscreteScrollView.this.getViewHolder(currentItem), DiscreteScrollView.this.getViewHolder(nextPosition));
                }
            }
        }

        public void onCurrentViewFirstLayout() {
            DiscreteScrollView.this.post(new Runnable() {
                public void run() {
                    DiscreteScrollView.this.notifyCurrentItemChanged();
                }
            });
        }

        public void onDataSetChangeChangedPosition() {
            DiscreteScrollView.this.notifyCurrentItemChanged();
        }
    }

    public void addScrollListener(@NonNull ScrollListener<?> scrollListener) {
    }

    public void removeScrollListener(@NonNull ScrollListener<?> scrollListener) {
    }

    public DiscreteScrollView(Context context) {
        super(context);
        init(null);
    }

    public DiscreteScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public DiscreteScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        this.scrollStateChangeListeners = new ArrayList();
        this.onItemChangedListeners = new ArrayList();
        this.layoutManager = new DiscreteScrollLayoutManager(getContext(), new ScrollStateListener(), new HorizontalHelper());
        setLayoutManager(this.layoutManager);
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof DiscreteScrollLayoutManager) {
            super.setLayoutManager(layoutManager);
            return;
        }
        throw new IllegalArgumentException("LayoutManager should be instance of DiscreteScrollLayoutManager");
    }

    public DiscreteScrollLayoutManager getLayoutManager() {
        return this.layoutManager;
    }

    public boolean fling(int i, int i2) {
        boolean fling = super.fling(i, i2);
        if (fling) {
            this.layoutManager.onFling(i, i2);
        } else {
            this.layoutManager.returnToCurrentPosition();
        }
        return fling;
    }

    @Nullable
    public ViewHolder getViewHolder(int i) {
        View findViewByPosition = this.layoutManager.findViewByPosition(i);
        return findViewByPosition != null ? getChildViewHolder(findViewByPosition) : null;
    }

    public void returnToCurrentItem() {
        this.layoutManager.returnToCurrentPosition();
    }

    public int getCurrentItem() {
        return this.layoutManager.getCurrentPosition();
    }

    public void setItemTransformer(DiscreteScrollItemTransformer discreteScrollItemTransformer) {
        this.layoutManager.setItemTransformer(discreteScrollItemTransformer);
    }

    public void setItemTransitionTimeMillis(@IntRange(from = 10) int i) {
        this.layoutManager.setTimeForItemSettle(i);
    }

    public void setSlideOnFling(boolean z) {
        this.layoutManager.setShouldSlideOnFling(z);
    }

    public void setSlideOnFlingThreshold(int i) {
        this.layoutManager.setSlideOnFlingThreshold(i);
    }

    public void setOffscreenItems(int i) {
        this.layoutManager.setOffscreenItems(i);
    }

    public void addScrollStateChangeListener(@NonNull ScrollStateChangeListener<?> scrollStateChangeListener) {
        this.scrollStateChangeListeners.add(scrollStateChangeListener);
    }

    public void addOnItemChangedListener(@NonNull OnItemChangedListener<?> onItemChangedListener) {
        this.onItemChangedListeners.add(onItemChangedListener);
    }

    public void removeScrollStateChangeListener(@NonNull ScrollStateChangeListener<?> scrollStateChangeListener) {
        this.scrollStateChangeListeners.remove(scrollStateChangeListener);
    }

    public void removeItemChangedListener(@NonNull OnItemChangedListener<?> onItemChangedListener) {
        this.onItemChangedListeners.remove(onItemChangedListener);
    }

    private void notifyScrollStart(ViewHolder viewHolder, int i) {
        for (ScrollStateChangeListener onScrollStart : this.scrollStateChangeListeners) {
            onScrollStart.onScrollStart(viewHolder, i);
        }
    }

    private void notifyScrollEnd(ViewHolder viewHolder, int i) {
        for (ScrollStateChangeListener onScrollEnd : this.scrollStateChangeListeners) {
            onScrollEnd.onScrollEnd(viewHolder, i);
        }
    }

    private void notifyScroll(float f, int i, int i2, ViewHolder viewHolder, ViewHolder viewHolder2) {
        for (ScrollStateChangeListener onScroll : this.scrollStateChangeListeners) {
            onScroll.onScroll(f, i, i2, viewHolder, viewHolder2);
        }
    }

    private void notifyCurrentItemChanged(ViewHolder viewHolder, int i) {
        for (OnItemChangedListener onCurrentItemChanged : this.onItemChangedListeners) {
            onCurrentItemChanged.onCurrentItemChanged(viewHolder, i);
        }
    }

    private void notifyCurrentItemChanged() {
        if (!this.onItemChangedListeners.isEmpty()) {
            int currentPosition = this.layoutManager.getCurrentPosition();
            notifyCurrentItemChanged(getViewHolder(currentPosition), currentPosition);
        }
    }
}
