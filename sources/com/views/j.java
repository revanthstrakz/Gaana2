package com.views;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.widget.ScrollerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import java.util.Arrays;

public class j {
    private static final Interpolator v = new Interpolator() {
        public float getInterpolation(float f) {
            f -= 1.0f;
            return ((((f * f) * f) * f) * f) + 1.0f;
        }
    };
    private int a;
    private int b;
    private int c = -1;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private ScrollerCompat q;
    private final a r;
    private View s;
    private boolean t;
    private final ViewGroup u;
    private final Runnable w = new Runnable() {
        public void run() {
            j.this.a(0);
        }
    };

    public static abstract class a {
        public int a(View view) {
            return 0;
        }

        public int a(View view, int i, int i2) {
            return 0;
        }

        public void a(int i) {
        }

        public void a(int i, int i2) {
        }

        public void a(View view, float f, float f2) {
        }

        public void a(View view, int i, int i2, int i3, int i4) {
        }

        public abstract boolean a(View view, int i);

        public int b(View view) {
            return 0;
        }

        public int b(View view, int i, int i2) {
            return 0;
        }

        public void b(int i, int i2) {
        }

        public void b(View view, int i) {
        }

        public boolean b(int i) {
            return false;
        }

        public int c(int i) {
            return i;
        }
    }

    public static j a(ViewGroup viewGroup, Interpolator interpolator, a aVar) {
        return new j(viewGroup.getContext(), viewGroup, interpolator, aVar);
    }

    public static j a(ViewGroup viewGroup, float f, Interpolator interpolator, a aVar) {
        j a = a(viewGroup, interpolator, aVar);
        a.b = (int) (((float) a.b) * (1.0f / f));
        return a;
    }

    private j(Context context, ViewGroup viewGroup, Interpolator interpolator, a aVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (aVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.u = viewGroup;
            this.r = aVar;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.o = (int) ((20.0f * context.getResources().getDisplayMetrics().density) + 0.5f);
            this.b = viewConfiguration.getScaledTouchSlop();
            this.m = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.n = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            if (interpolator == null) {
                interpolator = v;
            }
            this.q = ScrollerCompat.create(context, interpolator);
        }
    }

    public void a(float f) {
        this.n = f;
    }

    public int a() {
        return this.a;
    }

    public void a(View view, int i) {
        if (view.getParent() != this.u) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (");
            stringBuilder.append(this.u);
            stringBuilder.append(")");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.s = view;
        this.c = i;
        this.r.b(view, i);
        a(1);
    }

    public int b() {
        return this.b;
    }

    public void c() {
        this.c = -1;
        e();
        if (this.l != null) {
            this.l.recycle();
            this.l = null;
        }
    }

    public void d() {
        c();
        if (this.a == 2) {
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            this.q.abortAnimation();
            int currX2 = this.q.getCurrX();
            int currY2 = this.q.getCurrY();
            this.r.a(this.s, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        a(0);
    }

    public boolean a(View view, int i, int i2) {
        this.s = view;
        this.c = -1;
        return a(i, i2, 0, 0);
    }

    public boolean a(int i, int i2) {
        if (this.t) {
            return a(i, i2, (int) VelocityTrackerCompat.getXVelocity(this.l, this.c), (int) VelocityTrackerCompat.getYVelocity(this.l, this.c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean a(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        i -= left;
        i2 -= top;
        if (i == 0 && i2 == 0) {
            this.q.abortAnimation();
            a(0);
            return false;
        }
        this.q.startScroll(left, top, i, i2, a(this.s, i, i2, i3, i4));
        a(2);
        return true;
    }

    private int a(View view, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        i3 = b(i3, (int) this.n, (int) this.m);
        i4 = b(i4, (int) this.n, (int) this.m);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(i3);
        int abs4 = Math.abs(i4);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        if (i3 != 0) {
            f = (float) abs3;
            f2 = (float) i5;
        } else {
            f = (float) abs;
            f2 = (float) i6;
        }
        f /= f2;
        if (i4 != 0) {
            f3 = (float) abs4;
            f2 = (float) i5;
        } else {
            f3 = (float) abs2;
            f2 = (float) i6;
        }
        f3 /= f2;
        return (int) ((((float) a(i, i3, this.r.b(view))) * f) + (((float) a(i2, i4, this.r.a(view))) * f3));
    }

    private int a(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        float f = (float) (width / 2);
        f += b(Math.min(1.0f, ((float) Math.abs(i)) / ((float) width))) * f;
        i2 = Math.abs(i2);
        if (i2 > 0) {
            i = 4 * Math.round(1000.0f * Math.abs(f / ((float) i2)));
        } else {
            i = (int) (((((float) Math.abs(i)) / ((float) i3)) + 1.0f) * 256.0f);
        }
        return Math.min(i, 600);
    }

    private int b(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs <= i3) {
            return i;
        }
        if (i <= 0) {
            i3 = -i3;
        }
        return i3;
    }

    private float a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs <= f3) {
            return f;
        }
        if (f <= 0.0f) {
            f3 = -f3;
        }
        return f3;
    }

    private float b(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    public boolean a(boolean z) {
        boolean z2 = false;
        if (this.s == null) {
            return false;
        }
        if (this.a == 2) {
            boolean computeScrollOffset = this.q.computeScrollOffset();
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            int left = currX - this.s.getLeft();
            int top = currY - this.s.getTop();
            if (computeScrollOffset || top == 0) {
                if (left != 0) {
                    this.s.offsetLeftAndRight(left);
                }
                if (top != 0) {
                    this.s.offsetTopAndBottom(top);
                }
                if (!(left == 0 && top == 0)) {
                    this.r.a(this.s, currX, currY, left, top);
                }
                if (computeScrollOffset && currX == this.q.getFinalX() && currY == this.q.getFinalY()) {
                    this.q.abortAnimation();
                    computeScrollOffset = this.q.isFinished();
                }
                if (!computeScrollOffset) {
                    if (z) {
                        this.u.post(this.w);
                    } else {
                        a(0);
                    }
                }
            } else {
                this.s.setTop(0);
                return true;
            }
        }
        if (this.a == 2) {
            z2 = true;
        }
        return z2;
    }

    private void a(float f, float f2) {
        this.t = true;
        this.r.a(this.s, f, f2);
        this.t = false;
        if (this.a == 1) {
            a(0);
        }
    }

    private void e() {
        if (this.d != null) {
            Arrays.fill(this.d, 0.0f);
            Arrays.fill(this.e, 0.0f);
            Arrays.fill(this.f, 0.0f);
            Arrays.fill(this.g, 0.0f);
            Arrays.fill(this.h, 0);
            Arrays.fill(this.i, 0);
            Arrays.fill(this.j, 0);
            this.k = 0;
        }
    }

    private void b(int i) {
        if (this.d != null && this.d.length > i) {
            this.d[i] = 0.0f;
            this.e[i] = 0.0f;
            this.f[i] = 0.0f;
            this.g[i] = 0.0f;
            this.h[i] = 0;
            this.i[i] = 0;
            this.j[i] = 0;
            this.k = ((1 << i) ^ -1) & this.k;
        }
    }

    private void c(int i) {
        if (this.d == null || this.d.length <= i) {
            i++;
            float[] fArr = new float[i];
            float[] fArr2 = new float[i];
            float[] fArr3 = new float[i];
            float[] fArr4 = new float[i];
            int[] iArr = new int[i];
            int[] iArr2 = new int[i];
            int[] iArr3 = new int[i];
            if (this.d != null) {
                System.arraycopy(this.d, 0, fArr, 0, this.d.length);
                System.arraycopy(this.e, 0, fArr2, 0, this.e.length);
                System.arraycopy(this.f, 0, fArr3, 0, this.f.length);
                System.arraycopy(this.g, 0, fArr4, 0, this.g.length);
                System.arraycopy(this.h, 0, iArr, 0, this.h.length);
                System.arraycopy(this.i, 0, iArr2, 0, this.i.length);
                System.arraycopy(this.j, 0, iArr3, 0, this.j.length);
            }
            this.d = fArr;
            this.e = fArr2;
            this.f = fArr3;
            this.g = fArr4;
            this.h = iArr;
            this.i = iArr2;
            this.j = iArr3;
        }
    }

    private void a(float f, float f2, int i) {
        c(i);
        float[] fArr = this.d;
        this.f[i] = f;
        fArr[i] = f;
        fArr = this.e;
        this.g[i] = f2;
        fArr[i] = f2;
        this.h[i] = c((int) f, (int) f2);
        this.k |= 1 << i;
    }

    private void c(MotionEvent motionEvent) {
        int pointerCount = MotionEventCompat.getPointerCount(motionEvent);
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = MotionEventCompat.getPointerId(motionEvent, i);
            float x = MotionEventCompat.getX(motionEvent, i);
            float y = MotionEventCompat.getY(motionEvent, i);
            if (this.f != null && this.g != null && this.f.length > pointerId && this.g.length > pointerId) {
                this.f[pointerId] = x;
                this.g[pointerId] = y;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i) {
        if (this.a != i) {
            this.a = i;
            this.r.a(i);
            if (this.a == 0) {
                this.s = null;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b(View view, int i) {
        if (view == this.s && this.c == i) {
            return true;
        }
        if (view == null || !this.r.a(view, i)) {
            return false;
        }
        this.c = i;
        a(view, i);
        return true;
    }

    public boolean a(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (actionMasked == 0) {
            c();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int pointerId;
        float x;
        switch (actionMasked) {
            case 0:
                float x2 = motionEvent.getX();
                float y = motionEvent.getY();
                pointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                a(x2, y, pointerId);
                View b = b((int) x2, (int) y);
                if (b == this.s && this.a == 2) {
                    b(b, pointerId);
                }
                actionMasked = this.h[pointerId];
                if ((this.p & actionMasked) != 0) {
                    this.r.a(actionMasked & this.p, pointerId);
                    break;
                }
                break;
            case 1:
            case 3:
                c();
                break;
            case 2:
                actionMasked = MotionEventCompat.getPointerCount(motionEvent);
                for (actionIndex = 0; actionIndex < actionMasked && this.d != null && this.e != null; actionIndex++) {
                    int pointerId2 = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    if (pointerId2 < this.d.length && pointerId2 < this.e.length) {
                        x = MotionEventCompat.getX(motionEvent, actionIndex);
                        x -= this.d[pointerId2];
                        float y2 = MotionEventCompat.getY(motionEvent, actionIndex) - this.e[pointerId2];
                        b(x, y2, pointerId2);
                        if (this.a != 1) {
                            View b2 = b((int) this.d[pointerId2], (int) this.e[pointerId2]);
                            if (b2 != null && a(b2, x, y2) && b(b2, pointerId2)) {
                            }
                        }
                    }
                }
                c(motionEvent);
                break;
            case 5:
                actionMasked = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                x = MotionEventCompat.getX(motionEvent, actionIndex);
                float y3 = MotionEventCompat.getY(motionEvent, actionIndex);
                a(x, y3, actionMasked);
                if (this.a != 0) {
                    if (this.a == 2) {
                        View b3 = b((int) x, (int) y3);
                        if (b3 == this.s) {
                            b(b3, actionMasked);
                            break;
                        }
                    }
                }
                pointerId = this.h[actionMasked];
                if ((this.p & pointerId) != 0) {
                    this.r.a(pointerId & this.p, actionMasked);
                    break;
                }
                break;
            case 6:
                b(MotionEventCompat.getPointerId(motionEvent, actionIndex));
                break;
        }
        if (this.a == 1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062  */
    public void b(android.view.MotionEvent r9) {
        /*
        r8 = this;
        r0 = android.support.v4.view.MotionEventCompat.getActionMasked(r9);
        r1 = android.support.v4.view.MotionEventCompat.getActionIndex(r9);
        if (r0 != 0) goto L_0x000d;
    L_0x000a:
        r8.c();
    L_0x000d:
        r2 = r8.l;
        if (r2 != 0) goto L_0x0017;
    L_0x0011:
        r2 = android.view.VelocityTracker.obtain();
        r8.l = r2;
    L_0x0017:
        r2 = r8.l;
        r2.addMovement(r9);
        r2 = 0;
        r3 = 1;
        switch(r0) {
            case 0: goto L_0x00fe;
            case 1: goto L_0x00f4;
            case 2: goto L_0x0077;
            case 3: goto L_0x006a;
            case 4: goto L_0x0021;
            case 5: goto L_0x00fe;
            case 6: goto L_0x0023;
            default: goto L_0x0021;
        };
    L_0x0021:
        goto L_0x00fe;
    L_0x0023:
        r0 = android.support.v4.view.MotionEventCompat.getPointerId(r9, r1);
        r1 = r8.a;
        if (r1 != r3) goto L_0x0065;
    L_0x002b:
        r1 = r8.c;
        if (r0 != r1) goto L_0x0065;
    L_0x002f:
        r1 = android.support.v4.view.MotionEventCompat.getPointerCount(r9);
    L_0x0033:
        r3 = -1;
        if (r2 >= r1) goto L_0x005f;
    L_0x0036:
        r4 = android.support.v4.view.MotionEventCompat.getPointerId(r9, r2);
        r5 = r8.c;
        if (r4 != r5) goto L_0x003f;
    L_0x003e:
        goto L_0x005c;
    L_0x003f:
        r5 = android.support.v4.view.MotionEventCompat.getX(r9, r2);
        r6 = android.support.v4.view.MotionEventCompat.getY(r9, r2);
        r5 = (int) r5;
        r6 = (int) r6;
        r5 = r8.b(r5, r6);
        r6 = r8.s;
        if (r5 != r6) goto L_0x005c;
    L_0x0051:
        r5 = r8.s;
        r4 = r8.b(r5, r4);
        if (r4 == 0) goto L_0x005c;
    L_0x0059:
        r9 = r8.c;
        goto L_0x0060;
    L_0x005c:
        r2 = r2 + 1;
        goto L_0x0033;
    L_0x005f:
        r9 = r3;
    L_0x0060:
        if (r9 != r3) goto L_0x0065;
    L_0x0062:
        r8.f();
    L_0x0065:
        r8.b(r0);
        goto L_0x00fe;
    L_0x006a:
        r9 = r8.a;
        if (r9 != r3) goto L_0x0072;
    L_0x006e:
        r9 = 0;
        r8.a(r9, r9);
    L_0x0072:
        r8.c();
        goto L_0x00fe;
    L_0x0077:
        r0 = r8.a;
        if (r0 != r3) goto L_0x00ae;
    L_0x007b:
        r0 = r8.c;
        r0 = android.support.v4.view.MotionEventCompat.findPointerIndex(r9, r0);
        r1 = android.support.v4.view.MotionEventCompat.getX(r9, r0);
        r0 = android.support.v4.view.MotionEventCompat.getY(r9, r0);
        r2 = r8.f;
        r3 = r8.c;
        r2 = r2[r3];
        r1 = r1 - r2;
        r1 = (int) r1;
        r2 = r8.g;
        r3 = r8.c;
        r2 = r2[r3];
        r0 = r0 - r2;
        r0 = (int) r0;
        r2 = r8.s;
        r2 = r2.getLeft();
        r2 = r2 + r1;
        r3 = r8.s;
        r3 = r3.getTop();
        r3 = r3 + r0;
        r8.b(r2, r3, r1, r0);
        r8.c(r9);
        goto L_0x00fe;
    L_0x00ae:
        r0 = android.support.v4.view.MotionEventCompat.getPointerCount(r9);
    L_0x00b2:
        if (r2 >= r0) goto L_0x00f0;
    L_0x00b4:
        r1 = android.support.v4.view.MotionEventCompat.getPointerId(r9, r2);
        r4 = android.support.v4.view.MotionEventCompat.getX(r9, r2);
        r5 = android.support.v4.view.MotionEventCompat.getY(r9, r2);
        r6 = r8.d;
        r6 = r6[r1];
        r4 = r4 - r6;
        r6 = r8.e;
        r6 = r6[r1];
        r5 = r5 - r6;
        r8.b(r4, r5, r1);
        r6 = r8.a;
        if (r6 != r3) goto L_0x00d2;
    L_0x00d1:
        goto L_0x00f0;
    L_0x00d2:
        r6 = r8.d;
        r6 = r6[r1];
        r6 = (int) r6;
        r7 = r8.e;
        r7 = r7[r1];
        r7 = (int) r7;
        r6 = r8.b(r6, r7);
        r4 = r8.a(r6, r4, r5);
        if (r4 == 0) goto L_0x00ed;
    L_0x00e6:
        r1 = r8.b(r6, r1);
        if (r1 == 0) goto L_0x00ed;
    L_0x00ec:
        goto L_0x00f0;
    L_0x00ed:
        r2 = r2 + 1;
        goto L_0x00b2;
    L_0x00f0:
        r8.c(r9);
        goto L_0x00fe;
    L_0x00f4:
        r9 = r8.a;
        if (r9 != r3) goto L_0x00fb;
    L_0x00f8:
        r8.f();
    L_0x00fb:
        r8.c();
    L_0x00fe:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.views.j.b(android.view.MotionEvent):void");
    }

    private void b(float f, float f2, int i) {
        int i2 = 1;
        if (!a(f, f2, i, 1)) {
            i2 = 0;
        }
        if (a(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (a(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (a(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.i;
            iArr[i] = iArr[i] | i2;
            this.r.b(i2, i);
        }
    }

    private boolean a(float f, float f2, int i, int i2) {
        f = Math.abs(f);
        f2 = Math.abs(f2);
        boolean z = false;
        if ((this.h[i] & i2) != i2 || (this.p & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2 || (f <= ((float) this.b) && f2 <= ((float) this.b))) {
            return false;
        }
        if (f >= f2 * 0.5f || !this.r.b(i2)) {
            if ((this.i[i] & i2) == 0 && f > ((float) this.b)) {
                z = true;
            }
            return z;
        }
        int[] iArr = this.j;
        iArr[i] = iArr[i] | i2;
        return false;
    }

    private boolean a(View view, float f, float f2) {
        boolean z = false;
        if (view == null) {
            return false;
        }
        boolean z2 = this.r.b(view) > 0;
        boolean z3 = this.r.a(view) > 0;
        if (z2 && z3) {
            if ((f * f) + (f2 * f2) > ((float) (this.b * this.b))) {
                z = true;
            }
            return z;
        } else if (z2) {
            if (Math.abs(f) > ((float) this.b)) {
                z = true;
            }
            return z;
        } else if (!z3) {
            return false;
        } else {
            if (Math.abs(f2) > ((float) this.b)) {
                z = true;
            }
            return z;
        }
    }

    private void f() {
        this.l.computeCurrentVelocity(1000, this.m);
        a(a(VelocityTrackerCompat.getXVelocity(this.l, this.c), this.n, this.m), a(VelocityTrackerCompat.getYVelocity(this.l, this.c), this.n, this.m));
    }

    private void b(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        if (i3 != 0) {
            i = this.r.b(this.s, i, i3);
            this.s.offsetLeftAndRight(i - left);
        }
        int i5 = i;
        if (i4 != 0) {
            i2 = this.r.a(this.s, i2, i4);
            this.s.offsetTopAndBottom(i2 - top);
        }
        int i6 = i2;
        if (i3 != 0 || i4 != 0) {
            this.r.a(this.s, i5, i6, i5 - left, i6 - top);
        }
    }

    public View b(int i, int i2) {
        for (int childCount = this.u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.u.getChildAt(this.r.c(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private int c(int i, int i2) {
        int i3 = i < this.u.getLeft() + this.o ? 1 : 0;
        if (i2 < this.u.getTop() + this.o) {
            i3 |= 4;
        }
        if (i > this.u.getRight() - this.o) {
            i3 |= 2;
        }
        return i2 > this.u.getBottom() - this.o ? i3 | 8 : i3;
    }
}
