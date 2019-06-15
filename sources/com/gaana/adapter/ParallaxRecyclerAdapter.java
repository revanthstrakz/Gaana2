package com.gaana.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.gaana.view.item.BaseItemView.ItemNormalViewHolder;

public class ParallaxRecyclerAdapter extends ListAdapter {
    private CustomRelativeWrapper mHeader;
    private OnClickEvent mOnClickEvent;
    private OnParallaxScroll mParallaxScroll;
    private RecyclerView mRecyclerView;
    private float mScrollMultiplier = 0.5f;
    private boolean mShouldClipView = true;

    static class CustomRelativeWrapper extends RelativeLayout {
        private int mOffset;
        private boolean mShouldClip;

        public CustomRelativeWrapper(Context context, boolean z) {
            super(context);
            this.mShouldClip = z;
        }

        /* Access modifiers changed, original: protected */
        public void dispatchDraw(Canvas canvas) {
            if (this.mShouldClip) {
                canvas.clipRect(new Rect(getLeft(), getTop(), getRight(), getBottom() + this.mOffset));
            }
            super.dispatchDraw(canvas);
        }

        public void setClipY(int i) {
            this.mOffset = i;
            invalidate();
        }
    }

    public interface OnClickEvent {
        void onClick(View view, int i);
    }

    public interface OnParallaxScroll {
        void onParallaxScroll(float f, float f2, View view);
    }

    public static class VIEW_TYPES {
        public static final int FIRST_VIEW = 3;
        public static final int HEADER = 2;
        public static final int NORMAL = 1;
    }

    public ParallaxRecyclerAdapter(Context context) {
        super(context);
    }

    public void translateHeader(float f) {
        float f2 = this.mScrollMultiplier * f;
        if (VERSION.SDK_INT >= 11 && f < ((float) this.mHeader.getHeight())) {
            this.mHeader.setTranslationY(f2);
        } else if (f < ((float) this.mHeader.getHeight())) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, f2, f2);
            translateAnimation.setFillAfter(true);
            translateAnimation.setDuration(0);
            this.mHeader.startAnimation(translateAnimation);
        }
        this.mHeader.setClipY(Math.round(f2));
        if (this.mParallaxScroll != null) {
            float f3 = 1.0f;
            if (this.mRecyclerView.findViewHolderForAdapterPosition(0) != null) {
                f3 = Math.min(1.0f, f2 / (((float) this.mHeader.getHeight()) * this.mScrollMultiplier));
            }
            this.mParallaxScroll.onParallaxScroll(f3, f, this.mHeader);
        }
    }

    public void setParallaxHeader(View view, RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        this.mHeader = new CustomRelativeWrapper(view.getContext(), this.mShouldClipView);
        this.mHeader.setLayoutParams(new LayoutParams(-2, -2));
        this.mHeader.addView(view, new RelativeLayout.LayoutParams(-1, -1));
        recyclerView.setOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (ParallaxRecyclerAdapter.this.mHeader != null) {
                    ParallaxRecyclerAdapter.this.translateHeader((float) (ParallaxRecyclerAdapter.this.mRecyclerView.getLayoutManager().getChildAt(0) == ParallaxRecyclerAdapter.this.mHeader ? ParallaxRecyclerAdapter.this.mRecyclerView.computeVerticalScrollOffset() : ParallaxRecyclerAdapter.this.mHeader.getHeight()));
                }
            }
        });
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.mHeader == null) {
            super.onBindViewHolder(viewHolder, i);
        } else if (i != 0) {
            super.onBindViewHolder(viewHolder, i - 1);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2 && this.mHeader != null) {
            return new ItemNormalViewHolder(this.mHeader);
        }
        if (!(i != 3 || this.mHeader == null || this.mRecyclerView == null)) {
            ViewHolder findViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(0);
            if (findViewHolderForAdapterPosition != null) {
                translateHeader((float) (-findViewHolderForAdapterPosition.itemView.getTop()));
            }
        }
        final ViewHolder onCreateViewHolder = super.onCreateViewHolder(viewGroup, i);
        if (this.mOnClickEvent != null) {
            onCreateViewHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ParallaxRecyclerAdapter.this.mOnClickEvent.onClick(view, onCreateViewHolder.getAdapterPosition() - (ParallaxRecyclerAdapter.this.mHeader == null ? 0 : 1));
                }
            });
        }
        return onCreateViewHolder;
    }

    public boolean hasHeader() {
        return this.mHeader != null;
    }

    public void setOnClickEvent(OnClickEvent onClickEvent) {
        this.mOnClickEvent = onClickEvent;
    }

    public boolean isShouldClipView() {
        return this.mShouldClipView;
    }

    public void setShouldClipView(boolean z) {
        this.mShouldClipView = z;
    }

    public void setOnParallaxScroll(OnParallaxScroll onParallaxScroll) {
        this.mParallaxScroll = onParallaxScroll;
        this.mParallaxScroll.onParallaxScroll(0.0f, 0.0f, this.mHeader);
    }

    public int getItemCount() {
        return super.getItemCount() + (this.mHeader == null ? 0 : 1);
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return this.mHeader != null ? 2 : super.getItemViewType(0);
        } else if (i == 1) {
            return 3;
        } else {
            return super.getItemViewType(i);
        }
    }

    public void setScrollMultiplier(float f) {
        this.mScrollMultiplier = f;
    }

    public float getScrollMultiplier() {
        return this.mScrollMultiplier;
    }
}
