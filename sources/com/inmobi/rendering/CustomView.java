package com.inmobi.rendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.view.View;

public class CustomView extends View {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private int f;
    private int g;
    private Paint h;
    private Path i;
    private RectF j;

    private CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, float f, int i) {
        this(context);
        this.f = i;
        this.a = f;
        this.g = 15;
        this.h = new Paint(1);
        this.j = new RectF();
        this.i = new Path();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        this.h.reset();
        Canvas canvas3;
        float f;
        float f2;
        switch (this.f) {
            case 0:
                float f3 = (50.0f * this.a) / 2.0f;
                float f4 = (30.0f * this.a) / 2.0f;
                float f5 = f4 / 3.0f;
                float f6 = f3 - f5;
                float f7 = f3 + f5;
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawCircle(f3, f3, f4, this.h);
                this.h.setColor(-1);
                this.h.setStyle(Style.STROKE);
                canvas3 = canvas2;
                f = f6;
                float f8 = f7;
                canvas3.drawLine(f, f6, f8, f7, this.h);
                canvas3.drawLine(f, f7, f8, f6, this.h);
                canvas2.drawCircle(f3, f3, f4, this.h);
                return;
            case 1:
                f2 = (50.0f * this.a) / 2.0f;
                this.h.setAntiAlias(true);
                this.h.setColor(0);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawCircle(f2, f2, f2, this.h);
                return;
            case 2:
                this.h.setAntiAlias(true);
                this.h.setColor(-1);
                this.h.setStrokeWidth(5.0f);
                this.h.setStyle(Style.STROKE);
                canvas3 = canvas2;
                canvas3.drawLine(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((((float) this.g) * this.a) / 2.0f) + ((float) (getWidth() / 2)), ((((float) this.g) * this.a) / 2.0f) + ((float) (getHeight() / 2)), this.h);
                canvas3 = canvas2;
                canvas3.drawLine(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((((float) this.g) * this.a) / 2.0f) + ((float) (getHeight() / 2)), ((((float) this.g) * this.a) / 2.0f) + ((float) (getWidth() / 2)), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f), this.h);
                break;
            case 3:
                f2 = (50.0f * this.a) / 2.0f;
                f = (30.0f * this.a) / 2.0f;
                this.i.reset();
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawCircle(f2, f2, f, this.h);
                this.h.setColor(-1);
                this.h.setStyle(Style.STROKE);
                canvas2.drawCircle(f2, f2, f, this.h);
                this.j.set(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                canvas2.drawArc(this.j, 0.0f, 270.0f, false, this.h);
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - (this.a * 2.0f));
                this.i.lineTo((((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f)) - (this.a * 2.0f), (float) (getHeight() / 2));
                this.i.lineTo((((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f)) + (this.a * 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - (2.0f * this.a));
                this.i.close();
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawPath(this.i, this.h);
                return;
            case 4:
                this.i.reset();
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawPath(this.i, this.h);
                return;
            case 5:
                this.i.reset();
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawPath(this.i, this.h);
                return;
            case 6:
                this.i.reset();
                this.i.setFillType(FillType.EVEN_ODD);
                this.i.moveTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) + ((((float) this.g) * this.a) / 2.0f), (float) (getHeight() / 2));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) + ((((float) this.g) * this.a) / 2.0f));
                this.i.lineTo(((float) (getWidth() / 2)) - ((((float) this.g) * this.a) / 2.0f), ((float) (getHeight() / 2)) - ((((float) this.g) * this.a) / 2.0f));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(-12303292);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Style.FILL_AND_STROKE);
                canvas2.drawPath(this.i, this.h);
                return;
            case 7:
                b(canvas);
                this.b = this.e / 3.0f;
                this.c = this.e / 3.0f;
                this.h.setStyle(Style.FILL);
                this.i.moveTo(this.d + this.b, this.d);
                this.i.lineTo(this.d - this.b, this.d - this.c);
                this.i.lineTo(this.d - this.b, this.d + this.c);
                this.i.lineTo(this.d + this.b, this.d);
                canvas2.drawPath(this.i, this.h);
                return;
            case 8:
                b(canvas);
                this.b = this.e / 4.0f;
                this.c = this.e / 3.0f;
                canvas3 = canvas2;
                canvas3.drawLine(this.d - this.b, this.d - this.c, this.d - this.b, this.c + this.d, this.h);
                canvas3 = canvas2;
                canvas3.drawLine(this.b + this.d, this.d - this.c, this.b + this.d, this.c + this.d, this.h);
                return;
            case 9:
                a(canvas);
                RectF rectF = new RectF(this.d - (this.a * 10.0f), (this.d - this.c) - (this.a * 2.0f), this.d + (14.0f * this.a), (this.d + this.c) + (2.0f * this.a));
                RectF rectF2 = new RectF(this.d - (10.0f * this.a), (this.d - this.c) - (this.a * 4.0f), this.d + (18.0f * this.a), (this.d + this.c) + (this.a * 4.0f));
                this.h.setColor(-1);
                this.h.setStrokeWidth(4.0f);
                this.h.setStyle(Style.STROKE);
                canvas3 = canvas2;
                canvas3.drawArc(rectF, -45.0f, 90.0f, false, this.h);
                canvas3.drawArc(rectF2, -45.0f, 90.0f, false, this.h);
                canvas2.drawPath(this.i, this.h);
                canvas2.drawPath(this.i, this.h);
                return;
            case 11:
                a(canvas);
                this.h.setColor(-1);
                this.h.setStrokeWidth(4.0f);
                this.h.setStyle(Style.STROKE);
                this.i.moveTo(this.d + (this.a * 10.0f), this.d - this.c);
                this.i.lineTo(this.d + (this.a * 18.0f), this.d + this.c);
                this.i.moveTo(this.d + (18.0f * this.a), this.d - this.c);
                this.i.lineTo(this.d + (10.0f * this.a), this.d + this.c);
                canvas2.drawPath(this.i, this.h);
                return;
            case 12:
                this.d = (50.0f * this.a) / 2.0f;
                this.b = this.a * 3.0f;
                this.c = 3.0f * this.a;
                this.h.setStyle(Style.STROKE);
                this.h.setStrokeWidth(4.0f);
                this.h.setColor(-1);
                this.i.moveTo(this.d - this.b, (this.d - this.c) - (this.a * 5.0f));
                this.i.lineTo(this.d - this.b, this.d - this.c);
                this.i.lineTo((this.d - this.b) - (this.a * 5.0f), this.d - this.c);
                this.i.moveTo(this.d + this.b, (this.d - this.c) - (this.a * 5.0f));
                this.i.lineTo(this.d + this.b, this.d - this.c);
                this.i.lineTo((this.d + this.b) + (this.a * 5.0f), this.d - this.c);
                this.i.moveTo(this.d - this.b, (this.d + this.c) + (this.a * 5.0f));
                this.i.lineTo(this.d - this.b, this.d + this.c);
                this.i.lineTo((this.d - this.b) - (this.a * 5.0f), this.d + this.c);
                this.i.moveTo(this.d + this.b, (this.d + this.c) + (this.a * 5.0f));
                this.i.lineTo(this.d + this.b, this.d + this.c);
                this.i.lineTo((this.d + this.b) + (5.0f * this.a), this.d + this.c);
                canvas2.drawPath(this.i, this.h);
                return;
        }
    }

    private void a(Canvas canvas) {
        this.d = ((30.0f * this.a) / 2.0f) - (this.a * 5.0f);
        this.b = this.a * 5.0f;
        this.c = 5.0f * this.a;
        this.h.setStyle(Style.FILL);
        this.h.setColor(-1);
        this.h.setStrokeWidth(4.0f);
        this.h.setAntiAlias(true);
        this.i.moveTo(this.d - this.b, this.d - this.c);
        this.i.lineTo(this.d, this.d - this.c);
        this.i.lineTo(this.d + (this.a * 6.0f), (this.d - this.c) - (this.a * 4.0f));
        this.i.lineTo(this.d + (6.0f * this.a), (this.d + this.c) + (4.0f * this.a));
        this.i.lineTo(this.d, this.d + this.c);
        this.i.lineTo(this.d - this.b, this.d + this.c);
        this.i.lineTo(this.d - this.b, this.d - this.c);
        canvas.drawPath(this.i, this.h);
    }

    private void b(Canvas canvas) {
        this.e = 25.0f * this.a;
        this.d = 30.0f * this.a;
        this.h.setAntiAlias(true);
        this.h.setColor(-1);
        this.h.setStrokeWidth(7.0f);
        this.h.setStyle(Style.STROKE);
        canvas.drawCircle(this.d, this.d, this.e, this.h);
    }

    public void setSwitchInt(int i) {
        this.f = i;
    }
}
