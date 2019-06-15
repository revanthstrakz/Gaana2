package com.payu.custombrowser.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.payu.custombrowser.d.i;
import java.util.Timer;
import java.util.TimerTask;

public class SnoozeLoaderView extends View {
    Activity a;
    private Paint b;
    private Paint c;
    private Rect d;
    private Rect e;
    private Rect f;
    private Paint g;
    private Paint h;
    private Paint i;
    private int j = 40;
    private int k = 120;
    private int l = 70;
    private int m = (this.k / 3);
    private boolean n = false;
    private int o = Color.parseColor("#00adf2");
    private int p = Color.parseColor("#b0eafc");
    private int q = 200;
    private int r = 0;
    private Timer s;

    public SnoozeLoaderView(Context context) {
        super(context);
        this.a = (Activity) context;
        c();
    }

    public SnoozeLoaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = (Activity) context;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, i.SnoozeLoaderView, 0, 0);
        try {
            this.n = obtainStyledAttributes.getBoolean(i.SnoozeLoaderView_startAnimate, this.n);
            this.o = obtainStyledAttributes.getColor(i.SnoozeLoaderView_activeBarColor, this.o);
            this.p = obtainStyledAttributes.getColor(i.SnoozeLoaderView_inActiveBarColor, this.p);
            this.j = obtainStyledAttributes.getDimensionPixelSize(i.SnoozeLoaderView_barWidth, this.j);
            this.k = obtainStyledAttributes.getDimensionPixelSize(i.SnoozeLoaderView_barHeight, this.k);
            this.m = this.k / 3;
            this.l = obtainStyledAttributes.getDimensionPixelSize(i.SnoozeLoaderView_barSpace, this.l);
            this.q = obtainStyledAttributes.getInt(i.SnoozeLoaderView_animationSpeed, this.q);
            c();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public SnoozeLoaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = (Activity) context;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, i.SnoozeLoaderView, 0, 0);
        try {
            this.n = obtainStyledAttributes.getBoolean(i.SnoozeLoaderView_startAnimate, this.n);
            this.o = obtainStyledAttributes.getColor(i.SnoozeLoaderView_activeBarColor, this.o);
            this.p = obtainStyledAttributes.getColor(i.SnoozeLoaderView_inActiveBarColor, this.p);
            this.j = obtainStyledAttributes.getDimensionPixelSize(i.SnoozeLoaderView_barWidth, this.j);
            this.k = obtainStyledAttributes.getDimensionPixelSize(i.SnoozeLoaderView_barHeight, this.k);
            this.m = this.k / 3;
            this.l = obtainStyledAttributes.getDimensionPixelSize(i.SnoozeLoaderView_barSpace, this.l);
            c();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(this.d, this.g);
        canvas.drawRect(this.e, this.h);
        canvas.drawRect(this.f, this.i);
    }

    public void a(int i) {
        switch (i) {
            case 0:
                this.g = this.c;
                this.h = this.c;
                this.i = this.c;
                break;
            case 1:
                this.g = this.b;
                this.h = this.c;
                this.i = this.c;
                break;
            case 2:
                this.g = this.b;
                this.h = this.b;
                this.i = this.c;
                break;
            case 3:
                this.g = this.b;
                this.h = this.b;
                this.i = this.b;
                break;
            default:
                this.g = this.c;
                this.h = this.c;
                this.i = this.c;
                break;
        }
        if (this.a != null && !this.a.isFinishing()) {
            this.a.runOnUiThread(new Runnable() {
                public void run() {
                    SnoozeLoaderView.this.invalidate();
                }
            });
        }
    }

    private void c() {
        this.b = new Paint();
        this.b.setColor(this.o);
        this.b.setStyle(Style.FILL);
        this.c = new Paint();
        this.c.setColor(this.p);
        this.c.setStyle(Style.FILL);
        this.g = this.c;
        this.h = this.c;
        this.i = this.c;
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        i /= 2;
        i2 /= 2;
        i3 = i - (this.j / 2);
        i4 = i2 - (this.k / 2);
        int i5 = ((i - this.j) - this.l) - (this.j / 2);
        int i6 = (i2 - (this.k / 2)) - this.m;
        int i7 = this.j + i5;
        int i8 = ((this.k + i6) + this.m) + this.m;
        i = (i + (this.j / 2)) + this.l;
        i2 = (i2 - (this.k / 2)) + this.m;
        int i9 = this.j + i;
        int i10 = ((this.k + i2) - this.m) - this.m;
        this.e = new Rect(i3, i4, this.j + i3, this.k + i4);
        this.d = new Rect(i5, i6, i7, i8);
        this.f = new Rect(i, i2, i9, i10);
        if (this.n) {
            a();
        }
    }

    public void a() {
        this.n = true;
        this.s = new Timer();
        this.s.schedule(new TimerTask() {
            public void run() {
                if (SnoozeLoaderView.this.r == 4) {
                    SnoozeLoaderView.this.r = 0;
                } else {
                    SnoozeLoaderView.this.r = SnoozeLoaderView.this.r + 1;
                }
                if (SnoozeLoaderView.this.n) {
                    SnoozeLoaderView.this.a(SnoozeLoaderView.this.r);
                } else {
                    cancel();
                }
            }
        }, 0, (long) this.q);
    }

    public void b() {
        this.n = false;
        if (this.s != null) {
            this.s.cancel();
            this.s.purge();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension((((3 * this.j) + (this.l * 2)) + getPaddingLeft()) + getPaddingRight(), ((this.k + (2 * this.m)) + getPaddingTop()) + getPaddingBottom());
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.n = false;
        b();
    }
}
