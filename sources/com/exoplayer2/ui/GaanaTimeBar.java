package com.exoplayer2.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
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
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.R;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.ui.TimeBar.OnScrubListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Formatter;
import java.util.Locale;

public class GaanaTimeBar extends View implements TimeBar {
    private Point A;
    private boolean B;
    private long C;
    private long D;
    private long E;
    private long F;
    private int G;
    private long[] H;
    private final Rect a = new Rect();
    private final Rect b = new Rect();
    private final Rect c = new Rect();
    private final Rect d = new Rect();
    private final Paint e = new Paint();
    private final Paint f = new Paint();
    private final Paint g = new Paint();
    private final Paint h = new Paint();
    private final Paint i = new Paint();
    private final int j;
    private final int k;
    private final int l;
    private final int m;
    private final int n;
    private final int o;
    private final int p;
    private final int q;
    private final StringBuilder r;
    private final Formatter s;
    private final Runnable t;
    private int u;
    private OnScrubListener v;
    private int w;
    private long x;
    private int y;
    private int[] z;

    private static int a(int i) {
        return i | 16728576;
    }

    private static int b(int i) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | 855638016;
    }

    private static int c(int i) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | -872415232;
    }

    public GaanaTimeBar(Context context, AttributeSet attributeSet) {
        AttributeSet attributeSet2 = attributeSet;
        super(context, attributeSet);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.q = a(displayMetrics, -50);
        int a = a(displayMetrics, 4);
        int a2 = a(displayMetrics, 26);
        int a3 = a(displayMetrics, 4);
        int a4 = a(displayMetrics, 12);
        int a5 = a(displayMetrics, 0);
        int a6 = a(displayMetrics, 16);
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.DefaultTimeBar, 0, 0);
            try {
                this.j = obtainStyledAttributes.getDimensionPixelSize(2, a);
                this.k = obtainStyledAttributes.getDimensionPixelSize(11, a2);
                this.l = obtainStyledAttributes.getDimensionPixelSize(1, a3);
                this.m = obtainStyledAttributes.getDimensionPixelSize(10, a4);
                this.n = obtainStyledAttributes.getDimensionPixelSize(7, a5);
                this.o = obtainStyledAttributes.getDimensionPixelSize(8, a6);
                a6 = obtainStyledAttributes.getInt(5, -1);
                a3 = obtainStyledAttributes.getInt(6, a(a6));
                a = obtainStyledAttributes.getInt(3, c(a6));
                a2 = obtainStyledAttributes.getInt(12, b(a6));
                int i = obtainStyledAttributes.getInt(0, DefaultTimeBar.DEFAULT_AD_MARKER_COLOR);
                this.e.setColor(a6);
                this.f.setColor(a3);
                this.g.setColor(a);
                this.h.setColor(a2);
                this.i.setColor(i);
                obtainStyledAttributes.recycle();
            } catch (Throwable th) {
                Throwable th2 = th;
                obtainStyledAttributes.recycle();
            }
        } else {
            this.j = a;
            this.k = a2;
            this.l = a3;
            this.m = a4;
            this.n = a5;
            this.o = a6;
            this.e.setColor(-1);
            this.f.setColor(a(-1));
            this.g.setColor(c(-1));
            this.h.setColor(b(-1));
            this.i.setColor(DefaultTimeBar.DEFAULT_AD_MARKER_COLOR);
        }
        this.r = new StringBuilder();
        this.s = new Formatter(this.r, Locale.getDefault());
        this.t = new Runnable() {
            public void run() {
                GaanaTimeBar.this.a(false);
            }
        };
        this.u = this.m;
        this.p = (Math.max(this.n, Math.max(this.m, this.o)) + 1) / 2;
        this.D = C.TIME_UNSET;
        this.x = C.TIME_UNSET;
        this.w = 20;
        setFocusable(true);
        if (Util.SDK_INT >= 16) {
            a();
        }
    }

    public void setKeyTimeIncrement(long j) {
        Assertions.checkArgument(j > 0);
        this.w = -1;
        this.x = j;
    }

    public void setKeyCountIncrement(int i) {
        Assertions.checkArgument(i > 0);
        this.w = i;
        this.x = C.TIME_UNSET;
    }

    public void setPosition(long j) {
        this.E = j;
        setContentDescription(getProgressText());
    }

    public void setBufferedPosition(long j) {
        this.F = j;
    }

    public void setDuration(long j) {
        this.D = j;
        if (this.B && j == C.TIME_UNSET) {
            a(true);
        } else {
            c();
        }
    }

    public void setAdGroupTimesMs(@Nullable long[] jArr, @Nullable boolean[] zArr, int i) {
        boolean z = this.G == 0 || this.H != null;
        Assertions.checkArgument(z);
        this.G = this.G;
        this.H = this.H;
    }

    public void addListener(OnScrubListener onScrubListener) {
        this.v = onScrubListener;
    }

    public void removeListener(OnScrubListener onScrubListener) {
        this.v = onScrubListener;
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        c();
        if (this.B && !z) {
            a(true);
        }
    }

    public void onDraw(Canvas canvas) {
        canvas.save();
        a(canvas);
        b(canvas);
        canvas.restore();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!isEnabled() || this.D <= 0) {
            return false;
        }
        Point a = a(motionEvent);
        int i = a.x;
        int i2 = a.y;
        switch (motionEvent.getAction()) {
            case 0:
                float f = (float) i;
                if (a(f, (float) i2)) {
                    b();
                    a(f);
                    this.C = getScrubberPosition();
                    d();
                    invalidate();
                    return true;
                }
                break;
            case 1:
            case 3:
                if (this.B) {
                    if (motionEvent.getAction() == 3) {
                        z = true;
                    }
                    a(z);
                    return true;
                }
                break;
            case 2:
                if (this.B) {
                    if (i2 < this.q) {
                        a((float) (this.y + ((i - this.y) / 3)));
                    } else {
                        this.y = i;
                        a((float) i);
                    }
                    this.C = getScrubberPosition();
                    if (this.v != null) {
                        this.v.onScrubMove(this, this.C);
                    }
                    d();
                    invalidate();
                    return true;
                }
                break;
        }
        return false;
    }

    /* JADX WARNING: Missing block: B:7:0x0018, code skipped:
            if (a(r0) == false) goto L_0x0036;
     */
    /* JADX WARNING: Missing block: B:8:0x001a, code skipped:
            removeCallbacks(r4.t);
            postDelayed(r4.t, 1000);
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
        r0 = r4.a(r0);
        if (r0 == 0) goto L_0x0036;
    L_0x001a:
        r5 = r4.t;
        r4.removeCallbacks(r5);
        r5 = r4.t;
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4.postDelayed(r5, r0);
        return r3;
    L_0x0027:
        r0 = r4.B;
        if (r0 == 0) goto L_0x0036;
    L_0x002b:
        r5 = r4.t;
        r4.removeCallbacks(r5);
        r5 = r4.t;
        r5.run();
        return r3;
    L_0x0036:
        r5 = super.onKeyDown(r5, r6);
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.exoplayer2.ui.GaanaTimeBar.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        i2 = MeasureSpec.getSize(i2);
        if (mode == 0) {
            i2 = this.k;
        } else if (mode != 1073741824) {
            i2 = Math.min(this.k, i2);
        }
        setMeasuredDimension(MeasureSpec.getSize(i), i2);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        i3 -= i;
        i4 = ((i4 - i2) - this.k) / 2;
        i = ((this.k - this.j) / 2) + i4;
        this.a.set(getPaddingLeft(), i4, i3 - getPaddingRight(), this.k + i4);
        this.b.set(this.a.left + this.p, i, this.a.right - this.p, this.j + i);
        d();
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    @TargetApi(14)
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (accessibilityEvent.getEventType() == 4) {
            accessibilityEvent.getText().add(getProgressText());
        }
        accessibilityEvent.setClassName(DefaultTimeBar.class.getName());
    }

    @TargetApi(21)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DefaultTimeBar.class.getCanonicalName());
        accessibilityNodeInfo.setContentDescription(getProgressText());
        if (this.D > 0) {
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
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (super.performAccessibilityAction(i, bundle)) {
            return true;
        }
        if (this.D <= 0) {
            return false;
        }
        if (i == 8192) {
            if (a(-getPositionIncrement())) {
                a(false);
            }
        } else if (i != 4096) {
            return false;
        } else {
            if (a(getPositionIncrement())) {
                a(false);
            }
        }
        sendAccessibilityEvent(4);
        return true;
    }

    @TargetApi(16)
    private void a() {
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private void b() {
        this.B = true;
        c();
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        if (this.v != null) {
            this.v.onScrubStart(this, this.E);
        }
    }

    private void a(boolean z) {
        this.B = false;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        c();
        invalidate();
        if (this.v != null) {
            this.v.onScrubStop(this, getScrubberPosition(), z);
        }
    }

    private void c() {
        int i = this.B ? this.o : (!isEnabled() || this.D < 0) ? this.n : this.m;
        this.u = i;
    }

    private void d() {
        this.c.set(this.b);
        this.d.set(this.b);
        long j = this.B ? this.C : this.E;
        if (this.D > 0) {
            this.c.right = Math.min(this.b.left + ((int) ((((long) this.b.width()) * this.F) / this.D)), this.b.right);
            this.d.right = Math.min(this.b.left + ((int) ((((long) this.b.width()) * j) / this.D)), this.b.right);
        } else {
            this.c.right = this.b.left;
            this.d.right = this.b.left;
        }
        invalidate(this.a);
    }

    private void a(float f) {
        this.d.right = Util.constrainValue((int) f, this.b.left, this.b.right);
    }

    private Point a(MotionEvent motionEvent) {
        if (this.z == null) {
            this.z = new int[2];
            this.A = new Point();
        }
        getLocationOnScreen(this.z);
        this.A.set(((int) motionEvent.getRawX()) - this.z[0], ((int) motionEvent.getRawY()) - this.z[1]);
        return this.A;
    }

    private long getScrubberPosition() {
        return (this.b.width() <= 0 || this.D == C.TIME_UNSET) ? 0 : (((long) this.d.width()) * this.D) / ((long) this.b.width());
    }

    private boolean a(float f, float f2) {
        return this.a.contains((int) f, (int) f2);
    }

    private void a(Canvas canvas) {
        int height = this.b.height();
        int centerY = this.b.centerY() - (height / 2);
        height += centerY;
        if (this.D <= 0) {
            canvas.drawRect((float) this.b.left, (float) centerY, (float) this.b.right, (float) height, this.h);
            return;
        }
        int i = this.c.left;
        int i2 = this.c.right;
        int max = Math.max(Math.max(this.b.left, i2), this.d.right);
        if (max < this.b.right) {
            canvas.drawRect((float) max, (float) centerY, (float) this.b.right, (float) height, this.h);
        }
        i = Math.max(i, this.d.right);
        if (i2 > i) {
            canvas.drawRect((float) i, (float) centerY, (float) i2, (float) height, this.g);
        }
        if (this.d.width() > 0) {
            canvas.drawRect((float) this.d.left, (float) centerY, (float) this.d.right, (float) height, this.e);
        }
        i = this.l / 2;
        for (max = 0; max < this.G; max++) {
            int min = this.b.left + Math.min(this.b.width() - this.l, Math.max(0, ((int) ((((long) this.b.width()) * Util.constrainValue(this.H[max], 0, this.D)) / this.D)) - i));
            canvas.drawRect((float) min, (float) centerY, (float) (min + this.l), (float) height, this.i);
        }
    }

    private void b(Canvas canvas) {
        if (this.D > 0) {
            canvas.drawCircle((float) Util.constrainValue(this.d.right, this.d.left, this.b.right), (float) this.d.centerY(), (float) (this.u / 2), this.f);
        }
    }

    private String getProgressText() {
        return Util.getStringForTime(this.r, this.s, this.E);
    }

    private long getPositionIncrement() {
        if (this.x == C.TIME_UNSET) {
            return this.D == C.TIME_UNSET ? 0 : this.D / ((long) this.w);
        } else {
            return this.x;
        }
    }

    private boolean a(long j) {
        if (this.D <= 0) {
            return false;
        }
        long scrubberPosition = getScrubberPosition();
        this.C = Util.constrainValue(scrubberPosition + j, 0, this.D);
        if (this.C == scrubberPosition) {
            return false;
        }
        if (!this.B) {
            b();
        }
        if (this.v != null) {
            this.v.onScrubMove(this, this.C);
        }
        d();
        return true;
    }

    private static int a(DisplayMetrics displayMetrics, int i) {
        return (int) ((((float) i) * displayMetrics.density) + 0.5f);
    }
}
