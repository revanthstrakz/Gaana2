package com.google.android.exoplayer2.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ui.TimeBar.OnScrubListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;

public class DefaultTimeBar extends View implements TimeBar {
    private static final String ACCESSIBILITY_CLASS_NAME = "android.widget.SeekBar";
    public static final int DEFAULT_AD_MARKER_COLOR = -1291845888;
    public static final int DEFAULT_AD_MARKER_WIDTH_DP = 4;
    public static final int DEFAULT_BAR_HEIGHT_DP = 4;
    private static final int DEFAULT_INCREMENT_COUNT = 20;
    public static final int DEFAULT_PLAYED_COLOR = -1;
    public static final int DEFAULT_SCRUBBER_DISABLED_SIZE_DP = 0;
    public static final int DEFAULT_SCRUBBER_DRAGGED_SIZE_DP = 16;
    public static final int DEFAULT_SCRUBBER_ENABLED_SIZE_DP = 12;
    public static final int DEFAULT_TOUCH_TARGET_HEIGHT_DP = 26;
    private static final int FINE_SCRUB_RATIO = 3;
    private static final int FINE_SCRUB_Y_THRESHOLD_DP = -50;
    private static final long STOP_SCRUBBING_TIMEOUT_MS = 1000;
    private int adGroupCount;
    @Nullable
    private long[] adGroupTimesMs;
    private final Paint adMarkerPaint = new Paint();
    private final int adMarkerWidth;
    private final int barHeight;
    private final Rect bufferedBar = new Rect();
    private final Paint bufferedPaint = new Paint();
    private long bufferedPosition;
    private long duration;
    private final int fineScrubYThreshold;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private int keyCountIncrement;
    private long keyTimeIncrement;
    private int lastCoarseScrubXPosition;
    private final CopyOnWriteArraySet<OnScrubListener> listeners;
    private final int[] locationOnScreen;
    @Nullable
    private boolean[] playedAdGroups;
    private final Paint playedAdMarkerPaint = new Paint();
    private final Paint playedPaint = new Paint();
    private long position;
    private final Rect progressBar = new Rect();
    private long scrubPosition;
    private final Rect scrubberBar = new Rect();
    private final int scrubberDisabledSize;
    private final int scrubberDraggedSize;
    @Nullable
    private final Drawable scrubberDrawable;
    private final int scrubberEnabledSize;
    private final int scrubberPadding;
    private final Paint scrubberPaint = new Paint();
    private boolean scrubbing;
    private final Rect seekBounds = new Rect();
    private final Runnable stopScrubbingRunnable;
    private final Point touchPosition;
    private final int touchTargetHeight;
    private final Paint unplayedPaint = new Paint();

    public static int getDefaultBufferedColor(int i) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | -872415232;
    }

    public static int getDefaultPlayedAdMarkerColor(int i) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | 855638016;
    }

    public static int getDefaultScrubberColor(int i) {
        return i | ViewCompat.MEASURED_STATE_MASK;
    }

    public static int getDefaultUnplayedColor(int i) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | 855638016;
    }

    public DefaultTimeBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scrubberPaint.setAntiAlias(true);
        this.listeners = new CopyOnWriteArraySet();
        this.locationOnScreen = new int[2];
        this.touchPosition = new Point();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.fineScrubYThreshold = dpToPx(displayMetrics, FINE_SCRUB_Y_THRESHOLD_DP);
        int dpToPx = dpToPx(displayMetrics, 4);
        int dpToPx2 = dpToPx(displayMetrics, 26);
        int dpToPx3 = dpToPx(displayMetrics, 4);
        int dpToPx4 = dpToPx(displayMetrics, 12);
        int dpToPx5 = dpToPx(displayMetrics, 0);
        int dpToPx6 = dpToPx(displayMetrics, 16);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.DefaultTimeBar, 0, 0);
            try {
                this.scrubberDrawable = obtainStyledAttributes.getDrawable(R.styleable.DefaultTimeBar_scrubber_drawable);
                if (this.scrubberDrawable != null) {
                    setDrawableLayoutDirection(this.scrubberDrawable);
                    dpToPx2 = Math.max(this.scrubberDrawable.getMinimumHeight(), dpToPx2);
                }
                this.barHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_bar_height, dpToPx);
                this.touchTargetHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_touch_target_height, dpToPx2);
                this.adMarkerWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_ad_marker_width, dpToPx3);
                this.scrubberEnabledSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_scrubber_enabled_size, dpToPx4);
                this.scrubberDisabledSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_scrubber_disabled_size, dpToPx5);
                this.scrubberDraggedSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DefaultTimeBar_scrubber_dragged_size, dpToPx6);
                int i = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_played_color, -1);
                dpToPx6 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_scrubber_color, getDefaultScrubberColor(i));
                dpToPx3 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_buffered_color, getDefaultBufferedColor(i));
                dpToPx = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_unplayed_color, getDefaultUnplayedColor(i));
                dpToPx2 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_ad_marker_color, DEFAULT_AD_MARKER_COLOR);
                dpToPx4 = obtainStyledAttributes.getInt(R.styleable.DefaultTimeBar_played_ad_marker_color, getDefaultPlayedAdMarkerColor(dpToPx2));
                this.playedPaint.setColor(i);
                this.scrubberPaint.setColor(dpToPx6);
                this.bufferedPaint.setColor(dpToPx3);
                this.unplayedPaint.setColor(dpToPx);
                this.adMarkerPaint.setColor(dpToPx2);
                this.playedAdMarkerPaint.setColor(dpToPx4);
            } finally {
                obtainStyledAttributes.recycle();
            }
        } else {
            this.barHeight = dpToPx;
            this.touchTargetHeight = dpToPx2;
            this.adMarkerWidth = dpToPx3;
            this.scrubberEnabledSize = dpToPx4;
            this.scrubberDisabledSize = dpToPx5;
            this.scrubberDraggedSize = dpToPx6;
            this.playedPaint.setColor(-1);
            this.scrubberPaint.setColor(getDefaultScrubberColor(-1));
            this.bufferedPaint.setColor(getDefaultBufferedColor(-1));
            this.unplayedPaint.setColor(getDefaultUnplayedColor(-1));
            this.adMarkerPaint.setColor(DEFAULT_AD_MARKER_COLOR);
            this.scrubberDrawable = null;
        }
        this.formatBuilder = new StringBuilder();
        this.formatter = new Formatter(this.formatBuilder, Locale.getDefault());
        this.stopScrubbingRunnable = new DefaultTimeBar$$Lambda$0(this);
        if (this.scrubberDrawable != null) {
            this.scrubberPadding = (this.scrubberDrawable.getMinimumWidth() + 1) / 2;
        } else {
            this.scrubberPadding = (Math.max(this.scrubberDisabledSize, Math.max(this.scrubberEnabledSize, this.scrubberDraggedSize)) + 1) / 2;
        }
        this.duration = C.TIME_UNSET;
        this.keyTimeIncrement = C.TIME_UNSET;
        this.keyCountIncrement = 20;
        setFocusable(true);
        if (Util.SDK_INT >= 16) {
            maybeSetImportantForAccessibilityV16();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$new$0$DefaultTimeBar() {
        stopScrubbing(false);
    }

    public void setPlayedColor(@ColorInt int i) {
        this.playedPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setScrubberColor(@ColorInt int i) {
        this.scrubberPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setBufferedColor(@ColorInt int i) {
        this.bufferedPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setUnplayedColor(@ColorInt int i) {
        this.unplayedPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setAdMarkerColor(@ColorInt int i) {
        this.adMarkerPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void setPlayedAdMarkerColor(@ColorInt int i) {
        this.playedAdMarkerPaint.setColor(i);
        invalidate(this.seekBounds);
    }

    public void addListener(OnScrubListener onScrubListener) {
        this.listeners.add(onScrubListener);
    }

    public void removeListener(OnScrubListener onScrubListener) {
        this.listeners.remove(onScrubListener);
    }

    public void setKeyTimeIncrement(long j) {
        Assertions.checkArgument(j > 0);
        this.keyCountIncrement = -1;
        this.keyTimeIncrement = j;
    }

    public void setKeyCountIncrement(int i) {
        Assertions.checkArgument(i > 0);
        this.keyCountIncrement = i;
        this.keyTimeIncrement = C.TIME_UNSET;
    }

    public void setPosition(long j) {
        this.position = j;
        setContentDescription(getProgressText());
        update();
    }

    public void setBufferedPosition(long j) {
        this.bufferedPosition = j;
        update();
    }

    public void setDuration(long j) {
        this.duration = j;
        if (this.scrubbing && j == C.TIME_UNSET) {
            stopScrubbing(true);
        }
        update();
    }

    public void setAdGroupTimesMs(@Nullable long[] jArr, @Nullable boolean[] zArr, int i) {
        boolean z = i == 0 || !(jArr == null || zArr == null);
        Assertions.checkArgument(z);
        this.adGroupCount = i;
        this.adGroupTimesMs = jArr;
        this.playedAdGroups = zArr;
        update();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.scrubbing && !z) {
            stopScrubbing(true);
        }
    }

    public void onDraw(Canvas canvas) {
        canvas.save();
        drawTimeBar(canvas);
        drawPlayhead(canvas);
        canvas.restore();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!isEnabled() || this.duration <= 0) {
            return false;
        }
        Point resolveRelativeTouchPosition = resolveRelativeTouchPosition(motionEvent);
        int i = resolveRelativeTouchPosition.x;
        int i2 = resolveRelativeTouchPosition.y;
        switch (motionEvent.getAction()) {
            case 0:
                float f = (float) i;
                if (isInSeekBar(f, (float) i2)) {
                    positionScrubber(f);
                    startScrubbing();
                    this.scrubPosition = getScrubberPosition();
                    update();
                    invalidate();
                    return true;
                }
                break;
            case 1:
            case 3:
                if (this.scrubbing) {
                    if (motionEvent.getAction() == 3) {
                        z = true;
                    }
                    stopScrubbing(z);
                    return true;
                }
                break;
            case 2:
                if (this.scrubbing) {
                    if (i2 < this.fineScrubYThreshold) {
                        positionScrubber((float) (this.lastCoarseScrubXPosition + ((i - this.lastCoarseScrubXPosition) / 3)));
                    } else {
                        this.lastCoarseScrubXPosition = i;
                        positionScrubber((float) i);
                    }
                    this.scrubPosition = getScrubberPosition();
                    Iterator it = this.listeners.iterator();
                    while (it.hasNext()) {
                        ((OnScrubListener) it.next()).onScrubMove(this, this.scrubPosition);
                    }
                    update();
                    invalidate();
                    return true;
                }
                break;
        }
        return false;
    }

    /* JADX WARNING: Missing block: B:7:0x0018, code skipped:
            if (scrubIncrementally(r0) == false) goto L_0x0036;
     */
    /* JADX WARNING: Missing block: B:8:0x001a, code skipped:
            removeCallbacks(r4.stopScrubbingRunnable);
            postDelayed(r4.stopScrubbingRunnable, 1000);
     */
    /* JADX WARNING: Missing block: B:9:0x0026, code skipped:
            return true;
     */
    public boolean onKeyDown(int r5, android.view.KeyEvent r6) {
        /*
        r4 = this;
        r0 = r4.isEnabled();
        if (r0 == 0) goto L_0x0036;
    L_0x0006:
        r0 = r4.getPositionIncrement();
        r2 = 66;
        r3 = 1;
        if (r5 == r2) goto L_0x0027;
    L_0x000f:
        switch(r5) {
            case 21: goto L_0x0013;
            case 22: goto L_0x0014;
            case 23: goto L_0x0027;
            default: goto L_0x0012;
        };
    L_0x0012:
        goto L_0x0036;
    L_0x0013:
        r0 = -r0;
    L_0x0014:
        r0 = r4.scrubIncrementally(r0);
        if (r0 == 0) goto L_0x0036;
    L_0x001a:
        r5 = r4.stopScrubbingRunnable;
        r4.removeCallbacks(r5);
        r5 = r4.stopScrubbingRunnable;
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4.postDelayed(r5, r0);
        return r3;
    L_0x0027:
        r0 = r4.scrubbing;
        if (r0 == 0) goto L_0x0036;
    L_0x002b:
        r5 = r4.stopScrubbingRunnable;
        r4.removeCallbacks(r5);
        r5 = r4.stopScrubbingRunnable;
        r5.run();
        return r3;
    L_0x0036:
        r5 = super.onKeyDown(r5, r6);
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.DefaultTimeBar.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    /* Access modifiers changed, original: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.scrubberDrawable != null) {
            this.scrubberDrawable.jumpToCurrentState();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        i2 = MeasureSpec.getSize(i2);
        if (mode == 0) {
            i2 = this.touchTargetHeight;
        } else if (mode != 1073741824) {
            i2 = Math.min(this.touchTargetHeight, i2);
        }
        setMeasuredDimension(MeasureSpec.getSize(i), i2);
        updateDrawableState();
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        i3 -= i;
        i4 = ((i4 - i2) - this.touchTargetHeight) / 2;
        i = ((this.touchTargetHeight - this.barHeight) / 2) + i4;
        this.seekBounds.set(getPaddingLeft(), i4, i3 - getPaddingRight(), this.touchTargetHeight + i4);
        this.progressBar.set(this.seekBounds.left + this.scrubberPadding, i, this.seekBounds.right - this.scrubberPadding, this.barHeight + i);
        update();
    }

    public void onRtlPropertiesChanged(int i) {
        if (this.scrubberDrawable != null && setDrawableLayoutDirection(this.scrubberDrawable, i)) {
            invalidate();
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (accessibilityEvent.getEventType() == 4) {
            accessibilityEvent.getText().add(getProgressText());
        }
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @TargetApi(21)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
        accessibilityNodeInfo.setContentDescription(getProgressText());
        if (this.duration > 0) {
            if (Util.SDK_INT >= 21) {
                accessibilityNodeInfo.addAction(AccessibilityAction.ACTION_SCROLL_FORWARD);
                accessibilityNodeInfo.addAction(AccessibilityAction.ACTION_SCROLL_BACKWARD);
            } else if (Util.SDK_INT >= 16) {
                accessibilityNodeInfo.addAction(4096);
                accessibilityNodeInfo.addAction(8192);
            }
        }
    }

    @TargetApi(16)
    public boolean performAccessibilityAction(int i, @Nullable Bundle bundle) {
        if (super.performAccessibilityAction(i, bundle)) {
            return true;
        }
        if (this.duration <= 0) {
            return false;
        }
        if (i == 8192) {
            if (scrubIncrementally(-getPositionIncrement())) {
                stopScrubbing(false);
            }
        } else if (i != 4096) {
            return false;
        } else {
            if (scrubIncrementally(getPositionIncrement())) {
                stopScrubbing(false);
            }
        }
        sendAccessibilityEvent(4);
        return true;
    }

    @TargetApi(16)
    private void maybeSetImportantForAccessibilityV16() {
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private void startScrubbing() {
        this.scrubbing = true;
        setPressed(true);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((OnScrubListener) it.next()).onScrubStart(this, getScrubberPosition());
        }
    }

    private void stopScrubbing(boolean z) {
        this.scrubbing = false;
        setPressed(false);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        invalidate();
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((OnScrubListener) it.next()).onScrubStop(this, getScrubberPosition(), z);
        }
    }

    private void update() {
        this.bufferedBar.set(this.progressBar);
        this.scrubberBar.set(this.progressBar);
        long j = this.scrubbing ? this.scrubPosition : this.position;
        if (this.duration > 0) {
            this.bufferedBar.right = Math.min(this.progressBar.left + ((int) ((((long) this.progressBar.width()) * this.bufferedPosition) / this.duration)), this.progressBar.right);
            this.scrubberBar.right = Math.min(this.progressBar.left + ((int) ((((long) this.progressBar.width()) * j) / this.duration)), this.progressBar.right);
        } else {
            this.bufferedBar.right = this.progressBar.left;
            this.scrubberBar.right = this.progressBar.left;
        }
        invalidate(this.seekBounds);
    }

    private void positionScrubber(float f) {
        this.scrubberBar.right = Util.constrainValue((int) f, this.progressBar.left, this.progressBar.right);
    }

    private Point resolveRelativeTouchPosition(MotionEvent motionEvent) {
        getLocationOnScreen(this.locationOnScreen);
        this.touchPosition.set(((int) motionEvent.getRawX()) - this.locationOnScreen[0], ((int) motionEvent.getRawY()) - this.locationOnScreen[1]);
        return this.touchPosition;
    }

    private long getScrubberPosition() {
        return (this.progressBar.width() <= 0 || this.duration == C.TIME_UNSET) ? 0 : (((long) this.scrubberBar.width()) * this.duration) / ((long) this.progressBar.width());
    }

    private boolean isInSeekBar(float f, float f2) {
        return this.seekBounds.contains((int) f, (int) f2);
    }

    private void drawTimeBar(Canvas canvas) {
        int height = this.progressBar.height();
        int centerY = this.progressBar.centerY() - (height / 2);
        height += centerY;
        if (this.duration <= 0) {
            canvas.drawRect((float) this.progressBar.left, (float) centerY, (float) this.progressBar.right, (float) height, this.unplayedPaint);
            return;
        }
        int i = this.bufferedBar.left;
        int i2 = this.bufferedBar.right;
        int max = Math.max(Math.max(this.progressBar.left, i2), this.scrubberBar.right);
        if (max < this.progressBar.right) {
            canvas.drawRect((float) max, (float) centerY, (float) this.progressBar.right, (float) height, this.unplayedPaint);
        }
        i = Math.max(i, this.scrubberBar.right);
        if (i2 > i) {
            canvas.drawRect((float) i, (float) centerY, (float) i2, (float) height, this.bufferedPaint);
        }
        if (this.scrubberBar.width() > 0) {
            canvas.drawRect((float) this.scrubberBar.left, (float) centerY, (float) this.scrubberBar.right, (float) height, this.playedPaint);
        }
        if (this.adGroupCount != 0) {
            long[] jArr = (long[]) Assertions.checkNotNull(this.adGroupTimesMs);
            boolean[] zArr = (boolean[]) Assertions.checkNotNull(this.playedAdGroups);
            max = this.adMarkerWidth / 2;
            for (int i3 = 0; i3 < this.adGroupCount; i3++) {
                int min = this.progressBar.left + Math.min(this.progressBar.width() - this.adMarkerWidth, Math.max(0, ((int) ((((long) this.progressBar.width()) * Util.constrainValue(jArr[i3], 0, this.duration)) / this.duration)) - max));
                canvas.drawRect((float) min, (float) centerY, (float) (min + this.adMarkerWidth), (float) height, zArr[i3] ? this.playedAdMarkerPaint : this.adMarkerPaint);
            }
        }
    }

    private void drawPlayhead(Canvas canvas) {
        if (this.duration > 0) {
            int constrainValue = Util.constrainValue(this.scrubberBar.right, this.scrubberBar.left, this.progressBar.right);
            int centerY = this.scrubberBar.centerY();
            int i;
            if (this.scrubberDrawable == null) {
                i = (this.scrubbing || isFocused()) ? this.scrubberDraggedSize : isEnabled() ? this.scrubberEnabledSize : this.scrubberDisabledSize;
                canvas.drawCircle((float) constrainValue, (float) centerY, (float) (i / 2), this.scrubberPaint);
            } else {
                i = this.scrubberDrawable.getIntrinsicWidth() / 2;
                int intrinsicHeight = this.scrubberDrawable.getIntrinsicHeight() / 2;
                this.scrubberDrawable.setBounds(constrainValue - i, centerY - intrinsicHeight, constrainValue + i, centerY + intrinsicHeight);
                this.scrubberDrawable.draw(canvas);
            }
        }
    }

    private void updateDrawableState() {
        if (this.scrubberDrawable != null && this.scrubberDrawable.isStateful() && this.scrubberDrawable.setState(getDrawableState())) {
            invalidate();
        }
    }

    private String getProgressText() {
        return Util.getStringForTime(this.formatBuilder, this.formatter, this.position);
    }

    private long getPositionIncrement() {
        if (this.keyTimeIncrement == C.TIME_UNSET) {
            return this.duration == C.TIME_UNSET ? 0 : this.duration / ((long) this.keyCountIncrement);
        } else {
            return this.keyTimeIncrement;
        }
    }

    private boolean scrubIncrementally(long j) {
        if (this.duration <= 0) {
            return false;
        }
        long scrubberPosition = getScrubberPosition();
        this.scrubPosition = Util.constrainValue(scrubberPosition + j, 0, this.duration);
        if (this.scrubPosition == scrubberPosition) {
            return false;
        }
        if (!this.scrubbing) {
            startScrubbing();
        }
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((OnScrubListener) it.next()).onScrubMove(this, this.scrubPosition);
        }
        update();
        return true;
    }

    private boolean setDrawableLayoutDirection(Drawable drawable) {
        return Util.SDK_INT >= 23 && setDrawableLayoutDirection(drawable, getLayoutDirection());
    }

    private static boolean setDrawableLayoutDirection(Drawable drawable, int i) {
        return Util.SDK_INT >= 23 && drawable.setLayoutDirection(i);
    }

    private static int dpToPx(DisplayMetrics displayMetrics, int i) {
        return (int) ((((float) i) * displayMetrics.density) + 0.5f);
    }
}
