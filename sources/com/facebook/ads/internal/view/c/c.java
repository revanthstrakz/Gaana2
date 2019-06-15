package com.facebook.ads.internal.view.c;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.facebook.ads.internal.t.a;
import com.facebook.internal.AnalyticsEvents;
import com.gaana.login.GooglePlusLogin;
import java.lang.ref.WeakReference;

public class c extends Drawable {
    private final Paint a = new Paint();
    private final Paint b = new Paint();
    private final Path c = new Path();
    private final TextPaint d = new TextPaint();
    private final Paint e = new Paint();
    private int f;
    private int g;
    private String h;
    private int i;
    private boolean j;
    @Nullable
    private String k;
    private String l;
    private long m;
    private final Handler n = new Handler();
    @Nullable
    private WeakReference<a> o;
    private final Runnable p = new Runnable() {
        public void run() {
            c.this.c();
            if (c.this.j) {
                c.this.n.postDelayed(c.this.p, 250);
            }
        }
    };

    public c() {
        this.a.setColor(Color.argb(127, 36, 36, 36));
        this.a.setStyle(Style.FILL_AND_STROKE);
        this.b.setAntiAlias(true);
        this.b.setColor(Color.argb(191, 0, 255, 0));
        this.b.setStrokeWidth(20.0f);
        this.b.setStyle(Style.STROKE);
        this.d.setAntiAlias(true);
        this.d.setColor(-1);
        this.d.setStyle(Style.FILL_AND_STROKE);
        this.d.setTextSize(30.0f);
        this.e.setColor(Color.argb(GooglePlusLogin.RC_HINT, 0, 0, 0));
        this.e.setStyle(Style.FILL_AND_STROKE);
        b();
    }

    private void c() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f <= 0) {
            if (!TextUtils.isEmpty(this.k)) {
                stringBuilder.append(this.k);
                stringBuilder.append("\n");
            }
            if (!TextUtils.isEmpty(this.l)) {
                stringBuilder.append(this.l);
                stringBuilder.append("\n");
            }
            stringBuilder.append("Sdk ");
            stringBuilder.append("5.0.0");
            stringBuilder.append(", Loaded ");
            if (this.m > 0) {
                long max = Math.max(0, System.currentTimeMillis() - this.m);
                int i = (int) (max / 3600000);
                max %= 3600000;
                int i2 = (int) (max / 60000);
                int i3 = (int) ((max % 60000) / 1000);
                if (i > 0) {
                    stringBuilder.append(i);
                    stringBuilder.append("h ");
                }
                if (i > 0 || i2 > 0) {
                    stringBuilder.append(i2);
                    stringBuilder.append("m ");
                }
                stringBuilder.append(i3);
                str = "s ago";
            } else {
                str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            stringBuilder.append(str);
        } else {
            stringBuilder.append("Card ");
            stringBuilder.append(this.g + 1);
            stringBuilder.append(" of ");
            stringBuilder.append(this.f);
        }
        stringBuilder.append("\nView: ");
        str = (this.o == null || this.o.get() == null) ? "Viewability Checker not set" : ((a) this.o.get()).d();
        stringBuilder.append(str);
        this.h = stringBuilder.toString();
        float f = -2.14748365E9f;
        for (String str2 : this.h.split("\n")) {
            f = Math.max(f, this.d.measureText(str2, 0, str2.length()));
        }
        this.i = (int) (f + 0.5f);
        invalidateSelf();
    }

    public void a(int i, int i2) {
        this.f = i;
        this.g = i2;
        c();
    }

    public void a(long j) {
        this.m = j;
        c();
    }

    public void a(a aVar) {
        this.o = new WeakReference(aVar);
        c();
    }

    public void a(String str) {
        this.k = str;
        c();
    }

    public void a(boolean z) {
        this.j = z;
        if (this.j) {
            this.n.post(this.p);
        } else {
            this.n.removeCallbacks(this.p);
        }
        invalidateSelf();
    }

    public boolean a() {
        return this.j;
    }

    public void b() {
        this.f = 0;
        this.g = -1;
        this.h = "Initializing...";
        this.i = 100;
        this.k = null;
        this.m = -1;
        this.o = null;
        a(false);
    }

    public void b(String str) {
        this.l = str;
        c();
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        if (this.j) {
            float width = (float) canvas.getWidth();
            float height = (float) canvas.getHeight();
            canvas2.drawRect(0.0f, 0.0f, width, height, this.a);
            StaticLayout staticLayout = new StaticLayout(this.h, this.d, this.i, Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            float f = width / 2.0f;
            float f2 = height / 2.0f;
            float width2 = ((float) staticLayout.getWidth()) / 2.0f;
            float height2 = ((float) staticLayout.getHeight()) / 2.0f;
            float f3 = f - width2;
            float f4 = f2 - height2;
            StaticLayout staticLayout2 = staticLayout;
            canvas2.drawRect(f3 - 40.0f, f4 - 40.0f, (f + width2) + 40.0f, (f2 + height2) + 40.0f, this.e);
            canvas.save();
            canvas2.translate(f3, f4);
            staticLayout2.draw(canvas2);
            canvas.restore();
            this.c.reset();
            this.c.moveTo(0.0f, 0.0f);
            this.c.lineTo(width, 0.0f);
            this.c.lineTo(width, height);
            this.c.lineTo(0.0f, height);
            this.c.lineTo(0.0f, 0.0f);
            canvas2.drawPath(this.c, this.b);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }
}
