package com.dragpanel.a;

import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.collapsible_header.i;

public abstract class c {
    private final View a;
    private final View b;
    private int c;
    private int d;
    private float e;
    private float f;
    private int g;
    private int h;

    public abstract void a(float f);

    public abstract boolean a();

    public abstract void b(float f);

    public abstract boolean b();

    public abstract boolean c();

    public abstract boolean d();

    public abstract int e();

    public abstract int f();

    public c(View view, View view2) {
        this.a = view;
        this.b = view2;
    }

    public float g() {
        return this.e;
    }

    public void c(float f) {
        this.e = f;
    }

    public float h() {
        return this.f;
    }

    public void d(float f) {
        this.f = f;
    }

    public int i() {
        return this.c;
    }

    public void a(int i) {
        this.c = Math.round((float) i);
    }

    public int j() {
        return this.d;
    }

    public void b(int i) {
        this.d = Math.round((float) i);
    }

    public void c(int i) {
        if (i > 0) {
            this.g = i;
            LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
            layoutParams.height = i;
            this.a.setLayoutParams(layoutParams);
        }
    }

    public void d(int i) {
        if (i > 0) {
            this.h = i;
            LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
            layoutParams.width = i;
            this.a.setLayoutParams(layoutParams);
        }
    }

    /* Access modifiers changed, original: protected */
    public View k() {
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public View l() {
        return this.b;
    }

    public int m() {
        if (this.g == 0) {
            this.g = this.a.getMeasuredHeight();
        }
        return this.g;
    }

    public int n() {
        if (this.h == 0) {
            this.h = this.a.getMeasuredWidth();
        }
        return this.h;
    }

    public boolean o() {
        return this.a.getTop() == 0;
    }

    public boolean p() {
        return ((double) (i.b(this.a) + (((float) this.a.getHeight()) * 0.5f))) < ((double) this.b.getHeight()) * 0.5d;
    }
}
