package com.dragpanel;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.gaana.R;

public class DraggablePanel extends FrameLayout {
    private DraggableView a;
    private a b;
    private FragmentManager c;
    private Fragment d;
    private Fragment e;
    private int f;
    private int g;
    private int h;
    private float i;
    private float j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;

    public DraggablePanel(Context context) {
        super(context);
    }

    public DraggablePanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public DraggablePanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.c = fragmentManager;
    }

    public void setTopFragment(Fragment fragment) {
        this.d = fragment;
    }

    public void setBottomFragment(Fragment fragment) {
        this.e = fragment;
    }

    public void setTopViewHeight(int i) {
        this.f = i;
    }

    public void setClickToMaximizeEnabled(boolean z) {
        this.l = z;
    }

    public void setClickToMinimizeEnabled(boolean z) {
        this.m = z;
    }

    public void setXScaleFactor(float f) {
        this.i = f;
    }

    public void setYScaleFactor(float f) {
        this.j = f;
    }

    public void setTopFragmentMarginRight(int i) {
        this.g = i;
    }

    public void setTopFragmentMarginBottom(int i) {
        this.h = i;
    }

    public void setDraggableListener(a aVar) {
        this.b = aVar;
    }

    public void setEnableHorizontalAlphaEffect(boolean z) {
        this.k = z;
    }

    public void setTopFragmentResize(boolean z) {
        this.a.setTopViewResize(z);
    }

    public void a() {
        this.a.e();
    }

    public void b() {
        this.a.y();
    }

    public void c() {
        this.a.c();
    }

    public void d() {
        this.a.d();
    }

    public void e() {
        h();
        g();
        inflate(getContext(), R.layout.draggable_panel, this);
        this.a = (DraggableView) findViewById(R.id.draggable_view);
        this.a.setTopViewHeight(this.f);
        this.a.setFragmentManager(this.c);
        this.a.a(this.d);
        this.a.setXTopViewScaleFactor(this.i);
        this.a.setYTopViewScaleFactor(this.j);
        this.a.setTopViewMarginRight(this.g);
        this.a.setTopViewMarginBottom(this.h);
        this.a.b(this.e);
        this.a.setDraggableListener(this.b);
        this.a.setHorizontalAlphaEffectEnabled(this.k);
        this.a.setClickToMaximizeEnabled(this.l);
        this.a.setClickToMinimizeEnabled(this.m);
        this.a.setTouchEnabled(this.n);
    }

    public void a(int i) {
        this.a.setTopViewHeight(i);
    }

    public boolean f() {
        return this.a.h();
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.draggable_panel);
        this.f = obtainStyledAttributes.getDimensionPixelSize(4, 200);
        this.i = obtainStyledAttributes.getFloat(7, 2.0f);
        this.j = obtainStyledAttributes.getFloat(8, 2.0f);
        this.g = obtainStyledAttributes.getDimensionPixelSize(6, 0);
        this.h = obtainStyledAttributes.getDimensionPixelSize(5, 0);
        this.k = obtainStyledAttributes.getBoolean(2, true);
        this.l = obtainStyledAttributes.getBoolean(0, false);
        this.m = obtainStyledAttributes.getBoolean(1, false);
        this.n = obtainStyledAttributes.getBoolean(3, true);
        obtainStyledAttributes.recycle();
    }

    private void g() {
        if (this.c == null) {
            throw new IllegalStateException("You have to set the support FragmentManager before initialize DraggablePanel");
        }
    }

    private void h() {
        if (this.d == null || this.e == null) {
            throw new IllegalStateException("You have to set top and bottom fragment before initialize DraggablePanel");
        }
    }
}
