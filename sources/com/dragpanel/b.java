package com.dragpanel;

import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.View;

class b extends Callback {
    private DraggableView a;
    private View b;

    public b(DraggableView draggableView, View view) {
        this.a = draggableView;
        this.b = view;
    }

    public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
        if (this.a.x()) {
            this.a.q();
            return;
        }
        this.a.r();
        this.a.n();
        this.a.l();
        this.a.p();
        this.a.m();
        this.a.o();
    }

    public void onViewReleased(View view, float f, float f2) {
        super.onViewReleased(view, f, f2);
        if (!this.a.x() || this.a.w()) {
            a(f2);
        } else {
            b(f);
        }
    }

    public boolean tryCaptureView(View view, int i) {
        return view.equals(this.b);
    }

    public int clampViewPositionHorizontal(View view, int i, int i2) {
        int left = this.b.getLeft();
        return ((!this.a.g() || Math.abs(i2) <= 5) && (!this.a.x() || this.a.w())) ? left : i;
    }

    public int clampViewPositionVertical(View view, int i, int i2) {
        int height = this.a.getHeight() - this.a.getDraggedViewHeightPlusMarginTop();
        if ((!this.a.g() || Math.abs(i2) < 15) && (this.a.g() || this.a.x())) {
            return height;
        }
        return Math.min(Math.max(i, this.a.getPaddingTop()), (this.a.getHeight() - this.a.getDraggedViewHeightPlusMarginTop()) - this.b.getPaddingBottom());
    }

    private void a(float f) {
        if (f < 0.0f && f <= -1000.0f) {
            this.a.c();
        } else if (f > 0.0f && f >= 1000.0f) {
            this.a.d();
        } else if (this.a.s()) {
            this.a.c();
        } else {
            this.a.d();
        }
    }

    private void b(float f) {
        if (f < 0.0f && f <= -1500.0f) {
            this.a.f();
        } else if (f > 0.0f && f >= 1500.0f) {
            this.a.e();
        } else if (this.a.t()) {
            this.a.f();
        } else if (this.a.u()) {
            this.a.e();
        } else {
            this.a.d();
        }
    }
}
