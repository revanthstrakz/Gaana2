package com.helpshift.campaigns.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

@SuppressLint({"AppCompatCustomView"})
public class CircleImageView extends ImageView {
    private static final ScaleType a = ScaleType.CENTER_CROP;
    private static final Config b = Config.ARGB_8888;
    private final RectF c;
    private final RectF d;
    private final Matrix e;
    private final Paint f;
    private Bitmap g;
    private BitmapShader h;
    private int i;
    private int j;
    private float k;
    private ColorFilter l;
    private boolean m;
    private boolean n;

    public CircleImageView(Context context) {
        super(context);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        a();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        a();
    }

    private void a() {
        super.setScaleType(a);
        this.m = true;
        if (this.n) {
            b();
            this.n = false;
        }
    }

    public ScaleType getScaleType() {
        return a;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }

    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        if (this.g != null) {
            canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, this.k, this.f);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        b();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.g = bitmap;
        b();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.g = a(drawable);
        b();
    }

    public void setImageResource(@DrawableRes int i) {
        super.setImageResource(i);
        this.g = a(getDrawable());
        b();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.g = uri != null ? a(getDrawable()) : null;
        b();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.l) {
            this.l = colorFilter;
            this.f.setColorFilter(this.l);
            invalidate();
        }
    }

    private Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap;
            if (drawable instanceof ColorDrawable) {
                createBitmap = Bitmap.createBitmap(2, 2, b);
            } else {
                createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), b);
            }
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    private void b() {
        if (!this.m) {
            this.n = true;
        } else if (getWidth() != 0 || getHeight() != 0) {
            if (this.g == null) {
                invalidate();
                return;
            }
            this.h = new BitmapShader(this.g, TileMode.CLAMP, TileMode.CLAMP);
            this.f.setAntiAlias(true);
            this.f.setShader(this.h);
            this.j = this.g.getHeight();
            this.i = this.g.getWidth();
            this.d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.c.set(this.d);
            this.k = Math.min(this.c.height() / 2.0f, this.c.width() / 2.0f);
            c();
            invalidate();
        }
    }

    private void c() {
        float height;
        float f;
        this.e.set(null);
        float f2 = 0.0f;
        if (((float) this.i) * this.c.height() > this.c.width() * ((float) this.j)) {
            height = this.c.height() / ((float) this.j);
            f = 0.0f;
            f2 = (this.c.width() - (((float) this.i) * height)) * 0.5f;
        } else {
            height = this.c.width() / ((float) this.i);
            f = (this.c.height() - (((float) this.j) * height)) * 0.5f;
        }
        this.e.setScale(height, height);
        this.e.postTranslate(((float) ((int) (f2 + 0.5f))) + this.c.left, ((float) ((int) (f + 0.5f))) + this.c.top);
        this.h.setLocalMatrix(this.e);
    }
}
