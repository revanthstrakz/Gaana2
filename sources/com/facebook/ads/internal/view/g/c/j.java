package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.facebook.ads.internal.view.g.a.b;
import com.facebook.ads.internal.view.g.b.c;
import com.facebook.ads.internal.view.g.b.l;
import com.facebook.ads.internal.view.g.b.m;
import com.facebook.ads.internal.view.g.b.n;
import com.facebook.ads.internal.view.g.b.o;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class j extends View implements b {
    private final Paint a;
    private final Paint b;
    private final Paint c;
    private a d = a.CLOSE_BUTTON_MODE;
    private final Paint e;
    private final RectF f;
    @Nullable
    private com.facebook.ads.internal.view.g.a g;
    private int h;
    private final AtomicInteger i = new AtomicInteger(0);
    private final AtomicBoolean j = new AtomicBoolean(false);
    private final m k = new m() {
        public void a(l lVar) {
            j.this.j.set(true);
        }
    };
    private final o l = new o() {
        public void a(n nVar) {
            if (j.this.g != null) {
                int c = j.this.h;
                int duration = j.this.g.getDuration();
                if (c <= 0) {
                    j.this.i.set(0);
                } else {
                    c = Math.min(duration, c * 1000);
                    if (c != 0) {
                        j.this.i.set(((c - j.this.g.getCurrentPositionInMillis()) * 100) / c);
                    } else {
                        return;
                    }
                }
                j.this.postInvalidate();
            }
        }
    };
    private final c m = new c() {
        public void a(com.facebook.ads.internal.view.g.b.b bVar) {
            j.this.h = 0;
            j.this.i.set(0);
            j.this.postInvalidate();
        }
    };

    public enum a {
        CLOSE_BUTTON_MODE,
        SKIP_BUTTON_MODE
    }

    public j(Context context, int i, int i2) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        this.h = i;
        this.b = new Paint();
        this.b.setStyle(Style.FILL);
        this.b.setColor(i2);
        this.c = new Paint();
        this.c.setColor(-1);
        this.c.setAlpha(230);
        this.c.setStyle(Style.FILL);
        this.c.setStrokeWidth(1.0f * f);
        this.c.setAntiAlias(true);
        this.a = new Paint();
        this.a.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.a.setStyle(Style.STROKE);
        this.a.setAlpha(102);
        this.a.setStrokeWidth(1.5f * f);
        this.a.setAntiAlias(true);
        setLayerType(1, null);
        this.a.setMaskFilter(new BlurMaskFilter(6.0f, Blur.NORMAL));
        this.e = new Paint();
        this.e.setColor(-10066330);
        this.e.setStyle(Style.STROKE);
        this.e.setStrokeWidth(2.0f * f);
        this.e.setAntiAlias(true);
        this.f = new RectF();
    }

    public void a(com.facebook.ads.internal.view.g.a aVar) {
        this.g = aVar;
        this.g.getEventBus().a(this.k, this.l, this.m);
    }

    public boolean a() {
        return this.g != null && (this.h <= 0 || this.i.get() < 0);
    }

    public void b(com.facebook.ads.internal.view.g.a aVar) {
        this.g.getEventBus().b(this.m, this.l, this.k);
        this.g = null;
    }

    public int getSkipSeconds() {
        return this.h;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        if (this.j.get()) {
            int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
            int i = min / 2;
            float f = (float) i;
            canvas.drawCircle((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i), f, this.a);
            canvas.drawCircle((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i), f, this.c);
            if (this.i.get() > 0) {
                this.f.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()));
                canvas.drawArc(this.f, -90.0f, ((float) (-(this.i.get() * 360))) / 100.0f, true, this.b);
            } else if (this.d == a.SKIP_BUTTON_MODE) {
                int i2 = min / 4;
                min /= 3;
                Path path = new Path();
                path.moveTo((float) (getPaddingLeft() + i2), (float) (getPaddingTop() + min));
                path.lineTo((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i));
                int i3 = min * 2;
                path.lineTo((float) (getPaddingLeft() + i2), (float) (getPaddingTop() + i3));
                canvas.drawPath(path, this.e);
                path = new Path();
                path.moveTo((float) (getPaddingLeft() + i), (float) (min + getPaddingTop()));
                path.lineTo((float) ((i2 * 3) + getPaddingLeft()), (float) (getPaddingTop() + i));
                path.lineTo((float) (i + getPaddingLeft()), (float) (i3 + getPaddingTop()));
                canvas.drawPath(path, this.e);
            } else {
                min /= 3;
                i = min * 2;
                Canvas canvas2 = canvas;
                canvas2.drawLine((float) (getPaddingLeft() + min), (float) (getPaddingTop() + min), (float) (getPaddingLeft() + i), (float) (getPaddingTop() + i), this.e);
                canvas.drawLine((float) (getPaddingLeft() + i), (float) (getPaddingTop() + min), (float) (min + getPaddingLeft()), (float) (i + getPaddingTop()), this.e);
            }
            super.onDraw(canvas);
            return;
        }
        super.onDraw(canvas);
    }

    public void setButtonMode(a aVar) {
        this.d = aVar;
    }
}
