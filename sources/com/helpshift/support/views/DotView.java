package com.helpshift.support.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DotView extends View implements AnimatorUpdateListener {
    private int a;
    private Paint b;
    private float c;
    private float d;
    private float e;
    private RectF f;

    public DotView(Context context, int i) {
        super(context);
        this.c = -1.0f;
        this.d = -1.0f;
        this.a = i;
        b();
    }

    public DotView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DotView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = -1.0f;
        this.d = -1.0f;
    }

    public void setDotColor(int i) {
        this.a = i;
        invalidate();
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.c = (float) (getWidth() / 2);
        this.d = (float) (getHeight() / 2);
        this.e = Math.min(this.c, this.d);
        a();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawOval(this.f, this.b);
    }

    private void a() {
        this.f.left = this.c - this.e;
        this.f.right = this.c + this.e;
        this.f.top = this.d - this.e;
        this.f.bottom = this.d + this.e;
    }

    private void b() {
        this.f = new RectF();
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setColor(this.a);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a = Color.argb(((Integer) valueAnimator.getAnimatedValue()).intValue(), Color.red(this.a), Color.green(this.a), Color.blue(this.a));
        this.b.setColor(this.a);
        invalidate();
    }
}
