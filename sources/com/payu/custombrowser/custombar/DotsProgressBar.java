package com.payu.custombrowser.custombar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.payu.custombrowser.d.b;
import com.payu.custombrowser.d.c;

public class DotsProgressBar extends View {
    private final Paint a = new Paint(1);
    private final Paint b = new Paint(1);
    private final Handler c = new Handler();
    private final int d = 10;
    private float e;
    private float f;
    private int g = 0;
    private int h;
    private int i;
    private int j = 5;
    private boolean k;
    private int l = 1;
    private Runnable m;

    public DotsProgressBar(Context context) {
        super(context);
        a(context);
    }

    public DotsProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DotsProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.e = context.getResources().getDimension(c.cb_circle_indicator_radius);
        this.f = context.getResources().getDimension(c.cb_circle_indicator_outer_radius);
        this.a.setStyle(Style.FILL);
        this.a.setColor(context.getResources().getColor(b.cb_payu_blue));
        this.b.setStyle(Style.FILL);
        this.b.setColor(855638016);
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.m = new Runnable() {
            public void run() {
                DotsProgressBar.this.g = DotsProgressBar.this.g + DotsProgressBar.this.l;
                if (DotsProgressBar.this.g < 0) {
                    DotsProgressBar.this.g = 1;
                    DotsProgressBar.this.l = 1;
                } else if (DotsProgressBar.this.g > DotsProgressBar.this.j - 1) {
                    DotsProgressBar.this.g = 0;
                    DotsProgressBar.this.l = 1;
                }
                if (!DotsProgressBar.this.k) {
                    DotsProgressBar.this.invalidate();
                    DotsProgressBar.this.c.postDelayed(DotsProgressBar.this.m, 400);
                }
            }
        };
        a();
    }

    public void setDotsCount(int i) {
        this.j = i;
    }

    public void a() {
        this.g = -1;
        this.k = false;
        this.c.removeCallbacks(this.m);
        this.c.post(this.m);
    }

    public void b() {
        if (this.c != null && this.m != null) {
            this.k = true;
            this.c.removeCallbacks(this.m);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.h = (int) (((((this.e * 2.0f) * ((float) this.j)) + ((float) (10 * this.j))) + 10.0f) + (this.f - this.e));
        this.i = ((((int) this.e) * 2) + getPaddingBottom()) + getPaddingTop();
        setMeasuredDimension(this.h, this.i);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = ((((float) this.h) - ((((float) this.j) * this.e) * 2.0f)) - ((float) ((this.j - 1) * 10))) / 2.0f;
        float f2 = (float) (this.i / 2);
        for (int i = 0; i < this.j; i++) {
            if (i == this.g) {
                canvas.drawCircle(f, f2, this.f, this.a);
            } else {
                canvas.drawCircle(f, f2, this.e, this.b);
            }
            f += (this.e * 2.0f) + 10.0f;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.c != null && this.m != null) {
            this.c.removeCallbacks(this.m);
            this.m = null;
        }
    }
}
