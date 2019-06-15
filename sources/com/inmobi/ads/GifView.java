package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import com.inmobi.ads.t.a;

public class GifView extends View implements a {
    private t a;
    private float b;
    private float c;
    private float d;
    private int e;
    private int f;
    private boolean g;

    public GifView(Context context) {
        this(context, null);
    }

    public GifView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = true;
        setLayerType(1, null);
    }

    public GifView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = true;
        setLayerType(1, null);
    }

    public void setPaused(boolean z) {
        this.a.a(z);
    }

    public void setGif(t tVar) {
        this.a = tVar;
        if (this.a != null) {
            this.a.a((a) this);
            this.a.a();
        }
        requestLayout();
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a  */
    public void onMeasure(int r5, int r6) {
        /*
        r4 = this;
        r0 = r4.a;
        if (r0 == 0) goto L_0x0053;
    L_0x0004:
        r0 = r4.a;
        r0 = r0.b();
        r1 = r4.a;
        r1 = r1.c();
        r2 = android.view.View.MeasureSpec.getMode(r5);
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r2 == 0) goto L_0x0023;
    L_0x0018:
        r5 = android.view.View.MeasureSpec.getSize(r5);
        if (r0 <= r5) goto L_0x0023;
    L_0x001e:
        r2 = (float) r0;
        r5 = (float) r5;
        r5 = r2 / r5;
        goto L_0x0024;
    L_0x0023:
        r5 = r3;
    L_0x0024:
        r2 = android.view.View.MeasureSpec.getMode(r6);
        if (r2 == 0) goto L_0x0035;
    L_0x002a:
        r6 = android.view.View.MeasureSpec.getSize(r6);
        if (r1 <= r6) goto L_0x0035;
    L_0x0030:
        r2 = (float) r1;
        r6 = (float) r6;
        r6 = r2 / r6;
        goto L_0x0036;
    L_0x0035:
        r6 = r3;
    L_0x0036:
        r5 = java.lang.Math.max(r5, r6);
        r3 = r3 / r5;
        r4.d = r3;
        r5 = (float) r0;
        r6 = r4.d;
        r5 = r5 * r6;
        r5 = (int) r5;
        r4.e = r5;
        r5 = (float) r1;
        r6 = r4.d;
        r5 = r5 * r6;
        r5 = (int) r5;
        r4.f = r5;
        r5 = r4.e;
        r6 = r4.f;
        r4.setMeasuredDimension(r5, r6);
        return;
    L_0x0053:
        r5 = r4.getSuggestedMinimumWidth();
        r6 = r4.getSuggestedMinimumHeight();
        r4.setMeasuredDimension(r5, r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.GifView.onMeasure(int, int):void");
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.b = ((float) (getWidth() - this.e)) / 2.0f;
        this.c = ((float) (getHeight() - this.f)) / 2.0f;
        this.g = getVisibility() == 0;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        if (this.a != null) {
            if (this.a.d()) {
                this.a.e();
                a(canvas);
                b();
                return;
            }
            a(canvas);
        }
    }

    private void a(Canvas canvas) {
        canvas.save();
        canvas.scale(this.d, this.d);
        this.a.a(canvas, this.b / this.d, this.c / this.d);
        canvas.restore();
    }

    private void b() {
        if (this.g) {
            if (VERSION.SDK_INT >= 16) {
                postInvalidateOnAnimation();
                return;
            }
            invalidate();
        }
    }

    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        this.g = z;
        b();
    }

    /* Access modifiers changed, original: protected */
    public void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        this.g = i == 0;
        b();
    }

    /* Access modifiers changed, original: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.g = i == 0;
        b();
    }

    public final void a() {
        invalidate();
    }
}
