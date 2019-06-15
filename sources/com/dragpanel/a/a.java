package com.dragpanel.a;

import android.view.View;
import android.widget.RelativeLayout.LayoutParams;

class a extends c {
    private final LayoutParams a;

    a(View view, View view2) {
        super(view, view2);
        this.a = (LayoutParams) view.getLayoutParams();
    }

    public void a(float f) {
        this.a.width = (int) (((float) n()) * (1.0f - (f / g())));
        this.a.height = (int) (((float) m()) * (1.0f - (f / h())));
        k().setLayoutParams(this.a);
    }

    public void b(float f) {
        int e = e(f);
        int i = e - this.a.width;
        int top = k().getTop();
        k().layout(i, top, e, this.a.height + top);
    }

    public boolean a() {
        return k().getRight() + i() == l().getWidth();
    }

    public boolean b() {
        return k().getBottom() + j() == l().getHeight();
    }

    public boolean c() {
        return ((double) (k().getLeft() - i())) > ((double) l().getWidth()) * 0.75d;
    }

    public boolean d() {
        return ((double) (k().getLeft() - i())) < ((double) l().getWidth()) * 0.05d;
    }

    public int e() {
        return (int) ((((float) m()) * (1.0f - (1.0f / h()))) + ((float) j()));
    }

    public int f() {
        return (int) ((((float) n()) * (1.0f - (1.0f / g()))) + ((float) i()));
    }

    private int e(float f) {
        return (int) (((float) n()) - (((float) i()) * f));
    }
}
