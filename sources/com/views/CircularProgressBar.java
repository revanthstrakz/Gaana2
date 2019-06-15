package com.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircularProgressBar extends View {
    private int a = 100;
    private int b = 1;
    private Paint c = new Paint();
    private RectF d = new RectF();
    private int e = -3355444;
    private int f = Color.parseColor("#f63d03");
    private float g = 8.0f;
    private a h;

    public interface a {
        void a(int i, int i2, float f);
    }

    public void setOnProgressChangeListener(a aVar) {
        this.h = aVar;
    }

    public CircularProgressBar(Context context) {
        super(context);
    }

    public CircularProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setMax(int i) {
        if (i < 0) {
            i = 0;
        }
        this.a = i;
    }

    public int getMax() {
        return this.a;
    }

    public void setProgress(int i) {
        if (i > this.a) {
            i = this.a;
        }
        this.b = i;
        if (this.h != null) {
            this.h.a(this.a, i, getRateOfProgress());
        }
        invalidate();
    }

    public int getProgress() {
        return this.b;
    }

    public void setBackgroundColor(int i) {
        this.e = i;
    }

    public void setPrimaryColor(int i) {
        this.f = i;
    }

    public void setCircleWidth(float f) {
        this.g = f;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        int i = width < height ? width : height;
        float f = this.g / 2.0f;
        this.c.setColor(this.e);
        this.c.setDither(true);
        this.c.setFlags(1);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(this.g);
        this.c.setStyle(Style.STROKE);
        canvas.drawCircle((float) width, (float) height, ((float) i) - f, this.c);
        this.c.setColor(this.f);
        this.d.top = ((float) (height - i)) + f;
        this.d.bottom = ((float) (height + i)) - f;
        this.d.left = ((float) (width - i)) + f;
        this.d.right = ((float) (width + i)) - f;
        canvas.drawArc(this.d, -90.0f, getRateOfProgress() * 360.0f, false, this.c);
        canvas.save();
    }

    public void setStrokeWidth(float f) {
        this.g = f;
    }

    private float getRateOfProgress() {
        return ((float) this.b) / ((float) this.a);
    }
}
