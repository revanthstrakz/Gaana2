package com.a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;
import com.gaana.R;

public class b extends Drawable {
    private final int a;
    private final int b;
    private final Paint c = new Paint(1);
    private final RectF d = new RectF();
    private final RectF e = new RectF();
    private final Interpolator f;
    private final Interpolator g;
    private final Interpolator h;
    private final Interpolator i;

    public void setAlpha(int i) {
    }

    public b(int i, int i2) {
        this.a = i;
        this.b = i2;
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        Path path2 = path;
        path2.cubicTo(0.0375f, 0.0f, 0.12876461f, 0.0895381f, 0.25f, 0.21855351f);
        path2.cubicTo(0.32241032f, 0.2956106f, 0.43666667f, 0.41759142f, 0.48333332f, 0.48982617f);
        path2.cubicTo(0.69f, 0.80972296f, 0.79333335f, 0.95001614f, 1.0f, 1.0f);
        this.f = PathInterpolatorCompat.create(path);
        path = new Path();
        path.moveTo(0.0f, 0.0f);
        Path path3 = path;
        path3.cubicTo(0.06834272f, 0.019925667f, 0.19220331f, 0.15855429f, 0.33333334f, 0.3492616f);
        path3.cubicTo(0.38410434f, 0.41477913f, 0.5494579f, 0.6813603f, 0.6666667f, 0.68279964f);
        path3.cubicTo(0.75258625f, 0.6817962f, 0.73725396f, 0.8788962f, 1.0f, 1.0f);
        this.g = PathInterpolatorCompat.create(path);
        path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(0.2f, 0.0f);
        path3 = path;
        path3.cubicTo(0.39583334f, 0.0f, 0.47484508f, 0.20679761f, 0.59166664f, 0.41708294f);
        path3.cubicTo(0.715161f, 0.6393796f, 0.81625f, 0.9745569f, 1.0f, 1.0f);
        this.h = PathInterpolatorCompat.create(path);
        path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(0.3665f, 0.0f);
        path3 = path;
        path3.cubicTo(0.4725262f, 0.06240991f, 0.6154161f, 0.5f, 0.68325f, 0.5f);
        path3.cubicTo(0.7547506f, 0.5f, 0.7572583f, 0.8145101f, 1.0f, 1.0f);
        this.i = PathInterpolatorCompat.create(path);
    }

    public void draw(Canvas canvas) {
        float level = ((float) getLevel()) / 10000.0f;
        canvas.drawColor(this.a);
        canvas.save();
        canvas.translate(((float) canvas.getWidth()) / 2.0f, 0.0f);
        canvas.scale(((float) canvas.getWidth()) / 360.0f, 1.0f);
        this.c.setColor(this.b);
        int save = canvas.save();
        canvas.translate(-197.6f + (this.f.getInterpolation(level) * 620.2f), 0.0f);
        float f = (1.6199005f * (-Math.abs(this.g.getInterpolation(level) - 0.5f))) + 0.90995026f;
        this.d.set(-144.0f * f, 0.0f, f * 144.0f, (float) canvas.getHeight());
        canvas.drawRect(this.d, this.c);
        canvas.restoreToCount(save);
        save = canvas.save();
        canvas.translate(-522.6f + (this.h.getInterpolation(level) * 722.2f), 0.0f);
        float f2 = (1.4536984f * (-Math.abs(this.i.getInterpolation(level) - 0.5f))) + 0.8268492f;
        this.e.set(-144.0f * f2, 0.0f, 144.0f * f2, (float) canvas.getHeight());
        canvas.drawRect(this.e, this.c);
        canvas.restoreToCount(save);
        canvas.restore();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.c.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        int i = this.a >>> 24;
        int i2 = this.b >>> 24;
        if (i == 255 && i2 == 255) {
            return -1;
        }
        return (i == 0 && i2 == 0) ? -2 : -3;
    }

    public static b a(@NonNull Context context) {
        return new b(ContextCompat.getColor(context, R.color.black_alfa_20), ContextCompat.getColor(context, R.color.f17gaana.red));
    }
}
