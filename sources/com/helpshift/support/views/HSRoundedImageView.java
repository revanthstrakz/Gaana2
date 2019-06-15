package com.helpshift.support.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import com.helpshift.e.l;
import com.helpshift.support.util.a;

public class HSRoundedImageView extends AppCompatImageView {
    private final Matrix a;
    private ScaleType b;
    private Bitmap c;
    private int d;
    private int e;
    private RectF f;
    private RectF g;
    private Paint h;
    private Paint i;
    private BitmapShader j;
    private float k;
    private int l;
    private float m;
    private boolean[] n;
    private float o;
    private String p;

    public HSRoundedImageView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HSRoundedImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Matrix();
        this.b = ScaleType.CENTER_CROP;
        this.f = new RectF();
        this.g = new RectF();
        this.n = new boolean[4];
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, l.HSRoundedImageView, 0, 0);
        this.l = obtainStyledAttributes.getColor(l.HSRoundedImageView_hs__borderColor, -1);
        this.k = obtainStyledAttributes.getDimension(l.HSRoundedImageView_hs__borderWidth, 0.0f);
        if (this.k < 0.0f) {
            this.k = 0.0f;
        }
        this.m = obtainStyledAttributes.getDimension(l.HSRoundedImageView_hs__cornerRadius, 0.0f);
        this.n[0] = obtainStyledAttributes.getBoolean(l.HSRoundedImageView_hs__roundedTopLeft, true);
        this.n[1] = obtainStyledAttributes.getBoolean(l.HSRoundedImageView_hs__roundedTopRight, true);
        this.n[2] = obtainStyledAttributes.getBoolean(l.HSRoundedImageView_hs__roundedBottomLeft, true);
        this.n[3] = obtainStyledAttributes.getBoolean(l.HSRoundedImageView_hs__roundedBottomRight, true);
        obtainStyledAttributes.recycle();
        this.h = new Paint();
        this.h.setStyle(Style.FILL);
        this.h.setAntiAlias(true);
        this.i = new Paint();
        this.i.setStyle(Style.STROKE);
        this.i.setAntiAlias(true);
        this.i.setColor(this.l);
        this.i.setStrokeWidth(this.k);
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.h.setShader(this.j);
        if (this.k > 0.0f) {
            canvas.drawRoundRect(this.f, this.m - this.k, this.m - this.k, this.h);
            canvas.drawRoundRect(this.g, this.m, this.m, this.i);
            a(canvas);
            b(canvas);
            return;
        }
        canvas.drawRoundRect(this.f, this.m, this.m, this.h);
        a(canvas);
    }

    private void a() {
        b();
        if (this.c == null) {
            invalidate();
            return;
        }
        if (getWidth() > 0 && getHeight() > 0) {
            this.e = this.c.getWidth();
            this.d = this.c.getHeight();
            this.g.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.f.set(this.g);
            this.g.inset(this.k / 2.0f, this.k / 2.0f);
            this.f.inset(this.k, this.k);
            this.j = new BitmapShader(this.c, TileMode.CLAMP, TileMode.CLAMP);
            c();
            invalidate();
        }
    }

    private void b() {
        if (TextUtils.isEmpty(this.p) || getWidth() <= 0) {
            this.c = null;
        } else {
            this.c = a.a(this.p, getWidth());
        }
    }

    public void a(String str) {
        if (str != null) {
            str = str.trim();
            if (!str.equals(this.p)) {
                this.p = str;
                a();
                return;
            } else if (this.c == null) {
                a();
                return;
            } else {
                return;
            }
        }
        this.p = null;
        a();
    }

    public ScaleType getScaleType() {
        return this.b;
    }

    private void a(Canvas canvas) {
        if (this.m > 0.0f) {
            float f = this.f.left;
            float f2 = this.f.top;
            float width = this.f.width() + f;
            float height = this.f.height() + f2;
            float f3 = this.m;
            RectF rectF = new RectF();
            if (!this.n[0]) {
                rectF.set(f, f2, f + f3, f2 + f3);
                canvas.drawRect(rectF, this.h);
            }
            if (!this.n[1]) {
                rectF.set(width - f3, f2, width, f2 + f3);
                canvas.drawRect(rectF, this.h);
            }
            if (!this.n[2]) {
                rectF.set(f, height - f3, f + f3, height);
                canvas.drawRect(rectF, this.h);
            }
            if (!this.n[3]) {
                rectF.set(width - f3, height - f3, width, height);
                canvas.drawRect(rectF, this.h);
            }
        }
    }

    private void b(Canvas canvas) {
        if (this.m > 0.0f) {
            Canvas canvas2;
            float f = this.g.left;
            float f2 = this.g.top;
            float width = f + this.g.width();
            float height = f2 + this.g.height();
            float f3 = this.m;
            float f4 = this.k;
            if (!this.n[0]) {
                canvas.drawLine(f - f4, f2, f + f3, f2, this.i);
                canvas.drawLine(f, f2 - f4, f, f2 + f3, this.i);
            }
            if (!this.n[1]) {
                canvas2 = canvas;
                float f5 = width;
                canvas2.drawLine((width - f3) - f4, f2, f5, f2, this.i);
                canvas2.drawLine(width, f2 - f4, f5, f2 + f3, this.i);
            }
            if (!this.n[3]) {
                canvas2 = canvas;
                float f6 = height;
                canvas2.drawLine((width - f3) - f4, height, width + f4, f6, this.i);
                canvas2.drawLine(width, height - f3, width, f6, this.i);
            }
            if (!this.n[2]) {
                canvas.drawLine(f - f4, height, f + f3, height, this.i);
                canvas.drawLine(f, height - f3, f, height, this.i);
            }
        }
    }

    private void c() {
        if (this.c != null && getWidth() > 0 && getHeight() > 0) {
            float width;
            float f = 0.0f;
            if (this.e > this.d) {
                this.o = this.f.height() / ((float) this.d);
                width = (this.f.width() - (((float) this.e) * this.o)) * 0.5f;
            } else {
                this.o = this.f.width() / ((float) this.e);
                f = (this.f.height() - (((float) this.d) * this.o)) * 0.5f;
                width = 0.0f;
            }
            this.a.setScale(this.o, this.o);
            this.a.postTranslate(((float) ((int) (width + 0.5f))) + this.k, ((float) ((int) (f + 0.5f))) + this.k);
            this.j.setLocalMatrix(this.a);
        }
    }
}
