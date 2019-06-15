package com.inmobi.ads;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.inmobi.commons.core.utilities.b.c;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class NativeTimerView extends View {
    long a;
    long b;
    ValueAnimator c;
    private Bitmap d;
    private Canvas e;
    private RectF f;
    private RectF g;
    private Rect h;
    private Paint i;
    private Paint j;
    private Paint k;
    private Paint l;
    private Paint m;
    private float n;
    private b o;

    public static class a implements AnimatorUpdateListener {
        public WeakReference<NativeTimerView> a;

        public a(NativeTimerView nativeTimerView) {
            this.a = new WeakReference(nativeTimerView);
        }

        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            NativeTimerView nativeTimerView = (NativeTimerView) this.a.get();
            if (nativeTimerView != null) {
                int visibility = nativeTimerView.getVisibility();
                if (visibility != 4 && visibility != 8) {
                    nativeTimerView.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                } else if (((double) ((Float) valueAnimator.getAnimatedValue()).floatValue()) >= 1.0d) {
                    nativeTimerView.b();
                }
            }
        }
    }

    interface b {
        void a();
    }

    public NativeTimerView(Context context) {
        this(context, null);
    }

    public NativeTimerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NativeTimerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0;
        this.i = new Paint();
        this.i.setAntiAlias(true);
        this.i.setColor(-723724);
        this.m = new Paint();
        this.m.setAntiAlias(true);
        this.m.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.m.setTextAlign(Align.CENTER);
        this.m.setAntiAlias(true);
        this.h = new Rect();
        this.j = new Paint();
        this.j.setAntiAlias(true);
        this.j.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setColor(0);
        this.k.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.l = new Paint();
        this.l.setStyle(Style.STROKE);
        this.l.setAntiAlias(true);
        this.l.setColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public void setTimerEventsListener(b bVar) {
        this.o = bVar;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!(i == i3 && i2 == i4)) {
            this.d = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            this.d.eraseColor(0);
            this.e = new Canvas(this.d);
        }
        super.onSizeChanged(i, i2, i3, i4);
        float a = (float) c.a((int) ((4.0f * ((float) getWidth())) * 0.007f));
        float a2 = (float) c.a((int) ((14.0f * ((float) getWidth())) * 0.007f));
        float a3 = (float) c.a((int) ((5.0f * ((float) getWidth())) * 0.007f));
        float a4 = (float) c.a((int) ((1.5f * ((float) getWidth())) * 0.007f));
        this.f = new RectF(a3, a3, ((float) getWidth()) - a3, ((float) getHeight()) - a3);
        this.g = new RectF(this.f.left + a, this.f.top + a, this.f.right - a, this.f.bottom - a);
        this.l.setStrokeWidth(a4);
        this.m.setTextSize(a2);
        invalidate();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.e.drawColor(0, Mode.CLEAR);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        int min = Math.min(width, height);
        int a = c.a((int) ((7.0f * ((float) getWidth())) * 0.007f));
        float f = (float) width;
        float f2 = (float) height;
        canvas.drawCircle(f, f2, (float) min, this.i);
        canvas.drawCircle(f, f2, (float) (min - a), this.l);
        if (this.c != null) {
            width = (int) (this.a - (this.c.getCurrentPlayTime() / 1000));
            if (((double) ((Float) this.c.getAnimatedValue()).floatValue()) >= 1.0d) {
                width = 0;
            }
            Paint paint = this.m;
            Rect rect = this.h;
            String valueOf = String.valueOf(width);
            paint.getTextBounds(valueOf, 0, valueOf.length(), rect);
            canvas.drawText(valueOf, (float) (getWidth() / 2), ((float) (getHeight() / 2)) + (((paint.descent() - paint.ascent()) / 2.0f) - paint.descent()), paint);
            if (((double) ((Float) this.c.getAnimatedValue()).floatValue()) >= 1.0d) {
                b();
            }
        }
        if (this.n > 0.0f) {
            this.e.drawArc(this.f, 270.0f, this.n, true, this.j);
            this.e.drawOval(this.g, this.k);
        }
        canvas.drawBitmap(this.d, 0.0f, 0.0f, null);
    }

    public void setTimerValue(long j) {
        this.a = j;
    }

    public final void a() {
        this.c = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.c.setDuration(TimeUnit.SECONDS.toMillis(this.a));
        this.c.setInterpolator(new LinearInterpolator());
        this.c.addUpdateListener(new a(this));
        this.c.start();
    }

    /* Access modifiers changed, original: final */
    public final void a(float f) {
        this.n = 360.0f * f;
        invalidate();
    }

    private void b() {
        if (this.o != null) {
            this.o.a();
            this.c.cancel();
            this.c = null;
        }
    }
}
