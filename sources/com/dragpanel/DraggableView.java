package com.dragpanel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.collapsible_header.i;
import com.dragpanel.a.c;
import com.dragpanel.a.d;
import com.gaana.R;

public class DraggableView extends RelativeLayout {
    private int a = -1;
    private float b;
    private View c;
    private View d;
    private FragmentManager e;
    private ViewDragHelper f;
    private c g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private a m;
    private int n;
    private float o;
    private float p;
    private int q;
    private int r;
    private int s;
    private int t;

    public DraggableView(Context context) {
        super(context);
    }

    public DraggableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public DraggableView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    public boolean a() {
        return this.j;
    }

    public void setClickToMaximizeEnabled(boolean z) {
        this.j = z;
    }

    public boolean b() {
        return this.k;
    }

    public void setClickToMinimizeEnabled(boolean z) {
        this.k = z;
    }

    public void setTouchEnabled(boolean z) {
        this.l = z;
    }

    public void setXTopViewScaleFactor(float f) {
        this.g.c(f);
    }

    public void setYTopViewScaleFactor(float f) {
        this.g.d(f);
    }

    public void setTopViewMarginRight(int i) {
        this.g.a(i);
    }

    public void setTopViewMarginBottom(int i) {
        this.g.b(i);
    }

    public void setTopViewHeight(int i) {
        this.g.c(i);
    }

    public void setTopViewWidth(int i) {
        this.g.d(i);
    }

    public void setHorizontalAlphaEffectEnabled(boolean z) {
        this.h = z;
    }

    public void setDraggableListener(a aVar) {
        this.m = aVar;
    }

    public void setTopViewResize(boolean z) {
        this.i = z;
        B();
    }

    public void computeScroll() {
        if (!isInEditMode() && this.f.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void c() {
        a(0.0f);
        C();
    }

    public void d() {
        a(1.0f);
        D();
    }

    public void e() {
        if (this.f.smoothSlideViewTo(this.c, this.g.n(), getHeight() - this.g.e())) {
            ViewCompat.postInvalidateOnAnimation(this);
            E();
        }
    }

    public void f() {
        if (this.f.smoothSlideViewTo(this.c, -this.g.n(), getHeight() - this.g.e())) {
            ViewCompat.postInvalidateOnAnimation(this);
            F();
        }
    }

    public boolean g() {
        return x() && w();
    }

    public boolean h() {
        return v();
    }

    public boolean i() {
        return this.c.getLeft() >= getWidth();
    }

    public boolean j() {
        return this.c.getRight() <= 0;
    }

    public boolean k() {
        return j() || i();
    }

    /* JADX WARNING: Missing block: B:9:0x0025, code skipped:
            r0 = r5.f.isViewUnder(r5.c, (int) r6.getX(), (int) r6.getY());
     */
    /* JADX WARNING: Missing block: B:10:0x003d, code skipped:
            if (r5.f.shouldInterceptTouchEvent(r6) != false) goto L_0x0041;
     */
    /* JADX WARNING: Missing block: B:11:0x003f, code skipped:
            if (r0 == false) goto L_0x0042;
     */
    /* JADX WARNING: Missing block: B:12:0x0041, code skipped:
            r1 = true;
     */
    /* JADX WARNING: Missing block: B:13:0x0042, code skipped:
            return r1;
     */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
        r5 = this;
        r0 = r5.isEnabled();
        r1 = 0;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = android.support.v4.view.MotionEventCompat.getActionMasked(r6);
        r0 = r0 & 255;
        r2 = 3;
        if (r0 == r2) goto L_0x0043;
    L_0x0011:
        switch(r0) {
            case 0: goto L_0x0015;
            case 1: goto L_0x0043;
            default: goto L_0x0014;
        };
    L_0x0014:
        goto L_0x0025;
    L_0x0015:
        r0 = android.support.v4.view.MotionEventCompat.getActionIndex(r6);
        r0 = android.support.v4.view.MotionEventCompat.getPointerId(r6, r0);
        r5.a = r0;
        r0 = r5.a;
        r2 = -1;
        if (r0 != r2) goto L_0x0025;
    L_0x0024:
        return r1;
    L_0x0025:
        r0 = r5.f;
        r2 = r5.c;
        r3 = r6.getX();
        r3 = (int) r3;
        r4 = r6.getY();
        r4 = (int) r4;
        r0 = r0.isViewUnder(r2, r3, r4);
        r2 = r5.f;
        r6 = r2.shouldInterceptTouchEvent(r6);
        if (r6 != 0) goto L_0x0041;
    L_0x003f:
        if (r0 == 0) goto L_0x0042;
    L_0x0041:
        r1 = 1;
    L_0x0042:
        return r1;
    L_0x0043:
        r6 = r5.f;
        r6.cancel();
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dragpanel.DraggableView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if ((actionMasked & 255) == 0) {
            this.a = MotionEventCompat.getPointerId(motionEvent, actionMasked);
        }
        boolean z = false;
        if (this.a == -1) {
            return false;
        }
        this.f.processTouchEvent(motionEvent);
        if (k()) {
            return false;
        }
        boolean a = a(this.c, (int) motionEvent.getX(), (int) motionEvent.getY());
        boolean a2 = a(this.d, (int) motionEvent.getX(), (int) motionEvent.getY());
        a(motionEvent, a);
        if (h()) {
            this.c.dispatchTouchEvent(motionEvent);
        } else {
            this.c.dispatchTouchEvent(a(motionEvent, 3));
        }
        if (a || a2) {
            z = true;
        }
        return z;
    }

    private void a(MotionEvent motionEvent, boolean z) {
        switch (motionEvent.getAction()) {
            case 0:
                this.b = motionEvent.getX();
                return;
            case 1:
                if (!a(motionEvent, motionEvent.getX() - this.b, z)) {
                    return;
                }
                if (g() && a()) {
                    c();
                    return;
                } else if (h() && b()) {
                    d();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public boolean a(MotionEvent motionEvent, float f, boolean z) {
        return Math.abs(f) < 10.0f && motionEvent.getAction() != 2 && z;
    }

    private MotionEvent a(MotionEvent motionEvent, int i) {
        return MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), i, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState());
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (isInEditMode()) {
            super.onLayout(z, i, i2, i3, i4);
        } else if (v()) {
            this.c.layout(i, i2, i3, this.g.m());
            this.d.layout(i, this.g.m(), i3, i4);
            i.h(this.c, (float) i2);
            i.h(this.d, (float) this.g.m());
        } else {
            this.d.layout(i, this.g.m(), i3, i4);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            z();
            B();
            A();
        }
    }

    private void z() {
        this.c = findViewById(this.s);
        this.d = findViewById(this.t);
    }

    /* Access modifiers changed, original: 0000 */
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.e = fragmentManager;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Fragment fragment) {
        a((int) R.id.drag_view, fragment);
    }

    /* Access modifiers changed, original: 0000 */
    public void b(Fragment fragment) {
        a((int) R.id.second_view, fragment);
    }

    /* Access modifiers changed, original: 0000 */
    public void l() {
        this.g.b(getVerticalDragOffset());
    }

    /* Access modifiers changed, original: 0000 */
    public void m() {
        i.h(this.d, (float) this.c.getBottom());
    }

    /* Access modifiers changed, original: 0000 */
    public void n() {
        this.g.a(getVerticalDragOffset());
    }

    /* Access modifiers changed, original: 0000 */
    public void o() {
        Drawable background = getBackground();
        if (background != null) {
            background.setAlpha((int) (100.0f * (1.0f - getVerticalDragOffset())));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void p() {
        i.a(this.d, 1.0f - getVerticalDragOffset());
    }

    /* Access modifiers changed, original: 0000 */
    public void q() {
        if (this.h) {
            float horizontalDragOffset = 1.0f - getHorizontalDragOffset();
            if (horizontalDragOffset == 0.0f) {
                horizontalDragOffset = 1.0f;
            }
            i.a(this.c, horizontalDragOffset);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void r() {
        if (this.h && i.a(this.c) < 1.0f) {
            i.a(this.c, 1.0f);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean s() {
        return this.g.p();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean t() {
        return this.g.d();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean u() {
        return this.g.c();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean v() {
        return this.g.o();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean w() {
        return this.g.a();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean x() {
        return this.g.b();
    }

    private boolean a(View view, int i, int i2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr2);
        int i3 = iArr2[0] + i;
        int i4 = iArr2[1] + i2;
        if (i3 < iArr[0] || i3 >= iArr[0] + view.getWidth() || i4 < iArr[1] || i4 >= iArr[1] + view.getHeight()) {
            return false;
        }
        return true;
    }

    private void a(int i, Fragment fragment) {
        this.e.beginTransaction().replace(i, fragment).commit();
    }

    private void A() {
        this.f = ViewDragHelper.create(this, 1.0f, new b(this, this.c));
    }

    private void B() {
        this.g = new d().a(this.i, this.c, this);
        this.g.c(this.n);
        this.g.c(this.o);
        this.g.d(this.p);
        this.g.a(this.r);
        this.g.b(this.q);
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.draggable_view);
        this.h = obtainStyledAttributes.getBoolean(3, true);
        this.j = obtainStyledAttributes.getBoolean(1, false);
        this.k = obtainStyledAttributes.getBoolean(2, false);
        this.i = obtainStyledAttributes.getBoolean(8, false);
        this.n = obtainStyledAttributes.getDimensionPixelSize(4, -1);
        this.o = obtainStyledAttributes.getFloat(9, 2.0f);
        this.p = obtainStyledAttributes.getFloat(10, 2.0f);
        this.q = obtainStyledAttributes.getDimensionPixelSize(6, 30);
        this.r = obtainStyledAttributes.getDimensionPixelSize(7, 30);
        this.s = obtainStyledAttributes.getResourceId(5, R.id.drag_view);
        this.t = obtainStyledAttributes.getResourceId(0, R.id.second_view);
        obtainStyledAttributes.recycle();
    }

    public boolean y() {
        int paddingTop = (int) (((float) getPaddingTop()) + (1.0f * getVerticalDragRange()));
        this.c.setX((float) ((int) (((float) (getWidth() - this.g.f())) * 1.0f)));
        this.c.setY((float) paddingTop);
        return true;
    }

    private boolean a(float f) {
        if (!this.f.smoothSlideViewTo(this.c, (int) (((float) (getWidth() - this.g.f())) * f), (int) (((float) getPaddingTop()) + (f * getVerticalDragRange())))) {
            return false;
        }
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    private int getDragViewMarginRight() {
        return this.g.i();
    }

    private int getDragViewMarginBottom() {
        return this.g.j();
    }

    private float getHorizontalDragOffset() {
        return ((float) Math.abs(this.c.getLeft())) / ((float) getWidth());
    }

    private float getVerticalDragOffset() {
        return ((float) this.c.getTop()) / getVerticalDragRange();
    }

    private float getVerticalDragRange() {
        return (float) (getHeight() - this.g.e());
    }

    private void C() {
        if (this.m != null) {
            this.m.onMaximized();
        }
    }

    private void D() {
        if (this.m != null) {
            this.m.onMinimized();
        }
    }

    private void E() {
        if (this.m != null) {
            this.m.onClosedToRight();
        }
    }

    private void F() {
        if (this.m != null) {
            this.m.onClosedToLeft();
        }
    }

    public int getDraggedViewHeightPlusMarginTop() {
        return this.g.e();
    }
}
