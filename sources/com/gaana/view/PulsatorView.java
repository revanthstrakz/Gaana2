package com.gaana.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.constants.Constants;
import com.gaana.R;
import java.util.ArrayList;
import java.util.List;

public class PulsatorView extends RelativeLayout {
    private static final int DEFAULT_COLOR = Color.rgb(0, 116, 193);
    private static final int DEFAULT_COUNT = 4;
    private static final int DEFAULT_DURATION = 7000;
    private static final int DEFAULT_INTERPOLATOR = 0;
    private static final int DEFAULT_REPEAT = 0;
    private static final boolean DEFAULT_START_FROM_SCRATCH = true;
    public static final int INFINITE = 0;
    public static final int INTERP_ACCELERATE = 1;
    public static final int INTERP_ACCELERATE_DECELERATE = 3;
    public static final int INTERP_DECELERATE = 2;
    public static final int INTERP_LINEAR = 0;
    private static boolean isCompletedCycle = false;
    private final AnimatorListener mAnimatorListener;
    private AnimatorSet mAnimatorSet;
    private float mCenterX;
    private float mCenterY;
    private int mColor;
    private int mCount;
    private int mDuration;
    private int mInterpolator;
    private boolean mIsStarted;
    private Paint mPaint;
    private float mRadius;
    private int mRepeat;
    private boolean mStartFromScratch;
    private final List<View> mViews;

    private class PulseView extends View {
        public PulseView(Context context) {
            super(context);
        }

        /* Access modifiers changed, original: protected */
        public void onDraw(Canvas canvas) {
            canvas.drawCircle(PulsatorView.this.mCenterX, PulsatorView.this.mCenterY, PulsatorView.this.mRadius, PulsatorView.this.mPaint);
        }
    }

    public PulsatorView(Context context) {
        this(context, null, 0);
    }

    public PulsatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PulsatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mViews = new ArrayList();
        this.mAnimatorListener = new AnimatorListener() {
            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                PulsatorView.this.mIsStarted = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (PulsatorView.this.mIsStarted) {
                    PulsatorView.isCompletedCycle = true;
                }
                PulsatorView.this.mIsStarted = false;
            }

            public void onAnimationCancel(Animator animator) {
                PulsatorView.this.mIsStarted = false;
            }
        };
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.Pulsator4Droid, 0, 0);
        this.mCount = 4;
        this.mDuration = DEFAULT_DURATION;
        this.mRepeat = 0;
        this.mStartFromScratch = true;
        this.mColor = DEFAULT_COLOR;
        this.mInterpolator = 0;
        try {
            this.mCount = obtainStyledAttributes.getInteger(1, 4);
            this.mDuration = obtainStyledAttributes.getInteger(2, DEFAULT_DURATION);
            this.mRepeat = Constants.f;
            this.mStartFromScratch = obtainStyledAttributes.getBoolean(6, true);
            this.mColor = obtainStyledAttributes.getColor(0, DEFAULT_COLOR);
            this.mInterpolator = obtainStyledAttributes.getInteger(3, 0);
            this.mPaint = new Paint();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Style.FILL);
            this.mPaint.setColor(this.mColor);
            build();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX WARNING: Missing block: B:15:0x003e, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:17:0x0040, code skipped:
            return;
     */
    public synchronized void start() {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = r8.mAnimatorSet;	 Catch:{ all -> 0x0041 }
        if (r0 == 0) goto L_0x003f;
    L_0x0005:
        r0 = r8.mIsStarted;	 Catch:{ all -> 0x0041 }
        if (r0 == 0) goto L_0x000a;
    L_0x0009:
        goto L_0x003f;
    L_0x000a:
        r0 = r8.mAnimatorSet;	 Catch:{ all -> 0x0041 }
        r0.start();	 Catch:{ all -> 0x0041 }
        r0 = r8.mStartFromScratch;	 Catch:{ all -> 0x0041 }
        if (r0 != 0) goto L_0x003d;
    L_0x0013:
        r0 = r8.mAnimatorSet;	 Catch:{ all -> 0x0041 }
        r0 = r0.getChildAnimations();	 Catch:{ all -> 0x0041 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0041 }
    L_0x001d:
        r1 = r0.hasNext();	 Catch:{ all -> 0x0041 }
        if (r1 == 0) goto L_0x003d;
    L_0x0023:
        r1 = r0.next();	 Catch:{ all -> 0x0041 }
        r1 = (android.animation.Animator) r1;	 Catch:{ all -> 0x0041 }
        r1 = (android.animation.ObjectAnimator) r1;	 Catch:{ all -> 0x0041 }
        r2 = r1.getStartDelay();	 Catch:{ all -> 0x0041 }
        r4 = 0;
        r1.setStartDelay(r4);	 Catch:{ all -> 0x0041 }
        r4 = r8.mDuration;	 Catch:{ all -> 0x0041 }
        r4 = (long) r4;	 Catch:{ all -> 0x0041 }
        r6 = r4 - r2;
        r1.setCurrentPlayTime(r6);	 Catch:{ all -> 0x0041 }
        goto L_0x001d;
    L_0x003d:
        monitor-exit(r8);
        return;
    L_0x003f:
        monitor-exit(r8);
        return;
    L_0x0041:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.PulsatorView.start():void");
    }

    /* JADX WARNING: Missing block: B:11:0x0012, code skipped:
            return;
     */
    public synchronized void stop() {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = r1.mAnimatorSet;	 Catch:{ all -> 0x0013 }
        if (r0 == 0) goto L_0x0011;
    L_0x0005:
        r0 = r1.mIsStarted;	 Catch:{ all -> 0x0013 }
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0011;
    L_0x000a:
        r0 = r1.mAnimatorSet;	 Catch:{ all -> 0x0013 }
        r0.end();	 Catch:{ all -> 0x0013 }
        monitor-exit(r1);
        return;
    L_0x0011:
        monitor-exit(r1);
        return;
    L_0x0013:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.PulsatorView.stop():void");
    }

    public synchronized boolean isStarted() {
        boolean z;
        z = this.mAnimatorSet != null && this.mIsStarted;
        return z;
    }

    public int getCount() {
        return this.mCount;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setCount(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        } else if (i != this.mCount) {
            this.mCount = i;
            reset();
            invalidate();
        }
    }

    public void setDuration(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        } else if (i != this.mDuration) {
            this.mDuration = i;
            reset();
            invalidate();
        }
    }

    public int getColor() {
        return this.mColor;
    }

    public void setColor(int i) {
        if (i != this.mColor) {
            this.mColor = i;
            if (this.mPaint != null) {
                this.mPaint.setColor(i);
            }
        }
    }

    public int getInterpolator() {
        return this.mInterpolator;
    }

    public void setInterpolator(int i) {
        if (i != this.mInterpolator) {
            this.mInterpolator = i;
            reset();
            invalidate();
        }
    }

    public void onMeasure(int i, int i2) {
        int size = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = (MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        this.mCenterX = ((float) size) * 0.5f;
        this.mCenterY = ((float) size2) * 0.5f;
        this.mRadius = ((float) Math.min(size, size2)) * 0.5f;
        super.onMeasure(i, i2);
    }

    public void clear() {
        stop();
        for (View removeView : this.mViews) {
            removeView(removeView);
        }
        this.mViews.clear();
    }

    public void build() {
        int i = -1;
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        if (this.mRepeat != 0) {
            i = this.mRepeat;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mCount; i2++) {
            PulseView pulseView = new PulseView(getContext());
            pulseView.setScaleX(0.0f);
            pulseView.setScaleY(0.0f);
            pulseView.setAlpha(1.0f);
            addView(pulseView, i2, layoutParams);
            this.mViews.add(pulseView);
            long j = (long) ((this.mDuration * i2) / this.mCount);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(pulseView, "ScaleX", new float[]{0.0f, 1.0f});
            ofFloat.setRepeatCount(i);
            ofFloat.setRepeatMode(1);
            ofFloat.setStartDelay(j);
            arrayList.add(ofFloat);
            ofFloat = ObjectAnimator.ofFloat(pulseView, "ScaleY", new float[]{0.0f, 1.0f});
            ofFloat.setRepeatCount(i);
            ofFloat.setRepeatMode(1);
            ofFloat.setStartDelay(j);
            arrayList.add(ofFloat);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(pulseView, "Alpha", new float[]{1.0f, 0.0f});
            ofFloat2.setRepeatCount(i);
            ofFloat2.setRepeatMode(1);
            ofFloat2.setStartDelay(j);
            arrayList.add(ofFloat2);
        }
        this.mAnimatorSet = new AnimatorSet();
        this.mAnimatorSet.playTogether(arrayList);
        this.mAnimatorSet.setInterpolator(createInterpolator(this.mInterpolator));
        this.mAnimatorSet.setDuration((long) this.mDuration);
        this.mAnimatorSet.addListener(this.mAnimatorListener);
    }

    private void reset() {
        boolean isStarted = isStarted();
        clear();
        build();
        if (isStarted) {
            start();
        }
    }

    private static Interpolator createInterpolator(int i) {
        switch (i) {
            case 1:
                return new AccelerateInterpolator();
            case 2:
                return new DecelerateInterpolator();
            case 3:
                return new AccelerateDecelerateInterpolator();
            default:
                return new LinearInterpolator();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.cancel();
            this.mAnimatorSet = null;
        }
    }

    public boolean getCycleCompleted() {
        return isCompletedCycle;
    }

    public static void resetPulsatorFlag() {
        isCompletedCycle = false;
    }
}
