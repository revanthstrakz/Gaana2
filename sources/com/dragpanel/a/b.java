package com.dragpanel.a;

import android.view.View;
import com.collapsible_header.i;

class b extends c {
    b(View view, View view2) {
        super(view, view2);
    }

    public void a(float f) {
        i.d(k(), 1.0f - (f / g()));
        i.e(k(), 1.0f - (f / h()));
    }

    public void b(float f) {
        i.b(k(), (float) (k().getWidth() - i()));
        i.c(k(), (float) (k().getHeight() - j()));
    }

    public boolean a() {
        return k().getRight() == l().getWidth();
    }

    public boolean b() {
        return k().getBottom() == l().getHeight();
    }

    public boolean d() {
        return ((double) (k().getRight() - i())) < ((double) l().getWidth()) * 0.6d;
    }

    public boolean c() {
        return ((double) (k().getRight() - i())) > ((double) l().getWidth()) * 1.25d;
    }

    public int e() {
        return k().getHeight();
    }

    public int f() {
        return n();
    }
}
