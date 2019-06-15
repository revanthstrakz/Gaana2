package com.gaana.FastScrollRecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.gaana.R;

public class FastScroller {
    private static final int DEFAULT_AUTO_HIDE_DELAY = 1500;
    boolean mAnimatingShow;
    private Animator mAutoHideAnimator;
    private int mAutoHideDelay = 1500;
    private boolean mAutoHideEnabled = true;
    private final Runnable mHideRunnable;
    private Rect mInvalidateRect = new Rect();
    private Rect mInvalidateTmpRect = new Rect();
    private boolean mIsDragging;
    public Point mOffset = new Point(0, 0);
    private FastScrollPopup mPopup;
    private FastScrollRecyclerView mRecyclerView;
    private Paint mThumb;
    private int mThumbHeight;
    public Point mThumbPosition = new Point(-1, -1);
    private Rect mTmpRect = new Rect();
    private int mTouchInset;
    private int mTouchOffset;
    private Paint mTrack;
    private int mWidth;
    private boolean showPopup;

    public FastScroller(Context context, FastScrollRecyclerView fastScrollRecyclerView, AttributeSet attributeSet) {
        Resources resources = context.getResources();
        this.mRecyclerView = fastScrollRecyclerView;
        this.mPopup = new FastScrollPopup(resources, fastScrollRecyclerView);
        this.mThumbHeight = Utils.toPixels(resources, 48.0f);
        this.mWidth = Utils.toPixels(resources, 6.0f);
        this.mTouchInset = Utils.toPixels(resources, -24.0f);
        this.mThumb = new Paint(1);
        this.mTrack = new Paint(1);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FastScrollRecyclerView, 0, 0);
        try {
            this.mAutoHideEnabled = obtainStyledAttributes.getBoolean(0, true);
            this.mAutoHideDelay = obtainStyledAttributes.getInteger(1, 1500);
            int color = obtainStyledAttributes.getColor(5, 520093696);
            int color2 = obtainStyledAttributes.getColor(4, ViewCompat.MEASURED_STATE_MASK);
            int color3 = obtainStyledAttributes.getColor(2, ViewCompat.MEASURED_STATE_MASK);
            int color4 = obtainStyledAttributes.getColor(3, -1);
            this.mTrack.setColor(color);
            this.mThumb.setColor(color2);
            this.mPopup.setBgColor(color3);
            this.mPopup.setTextColor(color4);
            this.mHideRunnable = new Runnable() {
                public void run() {
                    if (!FastScroller.this.mIsDragging) {
                        if (FastScroller.this.mAutoHideAnimator != null) {
                            FastScroller.this.mAutoHideAnimator.cancel();
                        }
                        FastScroller fastScroller = FastScroller.this;
                        FastScroller fastScroller2 = FastScroller.this;
                        String str = "offsetX";
                        int i = 1;
                        int[] iArr = new int[1];
                        if (Utils.isRtl(FastScroller.this.mRecyclerView.getResources())) {
                            i = -1;
                        }
                        iArr[0] = i * FastScroller.this.mWidth;
                        fastScroller.mAutoHideAnimator = ObjectAnimator.ofInt(fastScroller2, str, iArr);
                        FastScroller.this.mAutoHideAnimator.setInterpolator(new FastOutLinearInInterpolator());
                        FastScroller.this.mAutoHideAnimator.setDuration(200);
                        FastScroller.this.mAutoHideAnimator.start();
                    }
                }
            };
            this.mRecyclerView.addOnScrollListener(new OnScrollListener() {
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    FastScroller.this.show();
                }
            });
            if (this.mAutoHideEnabled) {
                postAutoHideDelayed();
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public int getThumbHeight() {
        return this.mThumbHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isDragging() {
        return this.mIsDragging;
    }

    public void handleTouchEvent(MotionEvent motionEvent, int i, int i2, int i3) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this.mRecyclerView.getContext());
        int action = motionEvent.getAction();
        int y = (int) motionEvent.getY();
        switch (action) {
            case 0:
                if (isNearPoint(i, i2)) {
                    this.mTouchOffset = i2 - this.mThumbPosition.y;
                    return;
                }
                return;
            case 1:
            case 3:
                this.mTouchOffset = 0;
                if (this.mIsDragging) {
                    this.mIsDragging = false;
                    if (this.showPopup) {
                        this.mPopup.animateVisibility(false);
                        return;
                    }
                    return;
                }
                return;
            case 2:
                if (!this.mIsDragging && isNearPoint(i, i2) && Math.abs(y - i2) > viewConfiguration.getScaledTouchSlop()) {
                    this.mRecyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                    this.mIsDragging = true;
                    this.mTouchOffset += i3 - i2;
                    if (this.showPopup) {
                        this.mPopup.animateVisibility(true);
                    }
                }
                if (this.mIsDragging) {
                    i = this.mRecyclerView.getHeight() - this.mThumbHeight;
                    String scrollToPositionAtProgress = this.mRecyclerView.scrollToPositionAtProgress((((float) Math.max(0, Math.min(i, y - this.mTouchOffset))) - ((float) null)) / ((float) (i - 0)));
                    if (this.showPopup) {
                        this.mPopup.setSectionName(scrollToPositionAtProgress);
                        this.mPopup.animateVisibility(scrollToPositionAtProgress.isEmpty() ^ 1);
                        this.mRecyclerView.invalidate(this.mPopup.updateFastScrollerBounds(this.mRecyclerView, this.mThumbPosition.y));
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void draw(Canvas canvas) {
        if (this.mThumbPosition.x >= 0 && this.mThumbPosition.y >= 0) {
            canvas.drawRect((float) (this.mThumbPosition.x + this.mOffset.x), (float) ((this.mThumbHeight / 2) + this.mOffset.y), (float) ((this.mThumbPosition.x + this.mOffset.x) + this.mWidth), (float) ((this.mRecyclerView.getHeight() + this.mOffset.y) - (this.mThumbHeight / 2)), this.mTrack);
            canvas.drawRect((float) (this.mThumbPosition.x + this.mOffset.x), (float) (this.mThumbPosition.y + this.mOffset.y), (float) ((this.mThumbPosition.x + this.mOffset.x) + this.mWidth), (float) ((this.mThumbPosition.y + this.mOffset.y) + this.mThumbHeight), this.mThumb);
            if (this.showPopup) {
                this.mPopup.draw(canvas);
            }
        }
    }

    private boolean isNearPoint(int i, int i2) {
        this.mTmpRect.set(this.mThumbPosition.x, this.mThumbPosition.y, this.mThumbPosition.x + this.mWidth, this.mThumbPosition.y + this.mThumbHeight);
        this.mTmpRect.inset(this.mTouchInset, this.mTouchInset);
        return this.mTmpRect.contains(i, i2);
    }

    public void setThumbPosition(int i, int i2) {
        if (this.mThumbPosition.x != i || this.mThumbPosition.y != i2) {
            this.mInvalidateRect.set(this.mThumbPosition.x + this.mOffset.x, this.mOffset.y, (this.mThumbPosition.x + this.mOffset.x) + this.mWidth, this.mRecyclerView.getHeight() + this.mOffset.y);
            this.mThumbPosition.set(i, i2);
            this.mInvalidateTmpRect.set(this.mThumbPosition.x + this.mOffset.x, this.mOffset.y, (this.mThumbPosition.x + this.mOffset.x) + this.mWidth, this.mRecyclerView.getHeight() + this.mOffset.y);
            this.mInvalidateRect.union(this.mInvalidateTmpRect);
            this.mRecyclerView.invalidate(this.mInvalidateRect);
        }
    }

    public void setOffset(int i, int i2) {
        if (this.mOffset.x != i || this.mOffset.y != i2) {
            this.mInvalidateRect.set(this.mThumbPosition.x + this.mOffset.x, this.mOffset.y, (this.mThumbPosition.x + this.mOffset.x) + this.mWidth, this.mRecyclerView.getHeight() + this.mOffset.y);
            this.mOffset.set(i, i2);
            this.mInvalidateTmpRect.set(this.mThumbPosition.x + this.mOffset.x, this.mOffset.y, (this.mThumbPosition.x + this.mOffset.x) + this.mWidth, this.mRecyclerView.getHeight() + this.mOffset.y);
            this.mInvalidateRect.union(this.mInvalidateTmpRect);
            this.mRecyclerView.invalidate(this.mInvalidateRect);
        }
    }

    public void setOffsetX(int i) {
        setOffset(i, this.mOffset.y);
    }

    public int getOffsetX() {
        return this.mOffset.x;
    }

    public void show() {
        if (!this.mAnimatingShow) {
            if (this.mAutoHideAnimator != null) {
                this.mAutoHideAnimator.cancel();
            }
            this.mAutoHideAnimator = ObjectAnimator.ofInt(this, "offsetX", new int[]{0});
            this.mAutoHideAnimator.setInterpolator(new LinearOutSlowInInterpolator());
            this.mAutoHideAnimator.setDuration(150);
            this.mAutoHideAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                    FastScroller.this.mAnimatingShow = false;
                }

                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    FastScroller.this.mAnimatingShow = false;
                }
            });
            this.mAnimatingShow = true;
            this.mAutoHideAnimator.start();
        }
        if (this.mAutoHideEnabled) {
            postAutoHideDelayed();
        } else {
            cancelAutoHide();
        }
    }

    /* Access modifiers changed, original: protected */
    public void postAutoHideDelayed() {
        if (this.mRecyclerView != null) {
            cancelAutoHide();
            this.mRecyclerView.postDelayed(this.mHideRunnable, (long) this.mAutoHideDelay);
        }
    }

    /* Access modifiers changed, original: protected */
    public void cancelAutoHide() {
        if (this.mRecyclerView != null) {
            this.mRecyclerView.removeCallbacks(this.mHideRunnable);
        }
    }

    public void setThumbColor(@ColorInt int i) {
        this.mThumb.setColor(i);
        this.mRecyclerView.invalidate(this.mInvalidateRect);
    }

    public void setTrackColor(@ColorInt int i) {
        this.mTrack.setColor(i);
        this.mRecyclerView.invalidate(this.mInvalidateRect);
    }

    public void setPopupBgColor(@ColorInt int i) {
        this.mPopup.setBgColor(i);
    }

    public void setPopupTextColor(@ColorInt int i) {
        this.mPopup.setTextColor(i);
    }

    public void setAutoHideDelay(int i) {
        this.mAutoHideDelay = i;
        if (this.mAutoHideEnabled) {
            postAutoHideDelayed();
        }
    }

    public void setAutoHideEnabled(boolean z) {
        this.mAutoHideEnabled = z;
        if (z) {
            postAutoHideDelayed();
        } else {
            cancelAutoHide();
        }
    }

    public void showHidePopup(boolean z) {
        this.showPopup = z;
    }
}
