package com.library.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.gaana.R;

public class CircularImageView extends CrossFadeImageView {
    private int borderWidth;
    private int canvasSize;
    private Bitmap image;
    private Paint paint;
    private Paint paintBorder;
    private int shaderRadius;

    /* Access modifiers changed, original: protected */
    public void setDefaultDrawable(@NonNull Drawable drawable) {
    }

    public CircularImageView(Context context) {
        this(context, null);
    }

    public CircularImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.circularImageViewStyle);
    }

    public CircularImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.shaderRadius = 0;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paintBorder = new Paint();
        this.paintBorder.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularImageView, i, 0);
        if (obtainStyledAttributes.getBoolean(0, false)) {
            setBorderWidth(obtainStyledAttributes.getColor(2, 4));
            setBorderColor(obtainStyledAttributes.getInt(1, -1));
        }
        obtainStyledAttributes.recycle();
    }

    public void setBorderWidth(int i) {
        this.borderWidth = i;
        requestLayout();
        invalidate();
    }

    public void setBorderColor(int i) {
        if (this.paintBorder != null) {
            this.paintBorder.setColor(i);
        }
        invalidate();
    }

    public void addShadow() {
        super.setLayerType(1, this.paintBorder);
        this.paintBorder.setShadowLayer(4.0f, 0.0f, 2.0f, ViewCompat.MEASURED_STATE_MASK);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00d5  */
    public void onDraw(android.graphics.Canvas r7) {
        /*
        r6 = this;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 11;
        if (r0 >= r1) goto L_0x000b;
    L_0x0006:
        super.onDraw(r7);
        goto L_0x00d8;
    L_0x000b:
        r0 = r6.getDrawable();
        r0 = r0 instanceof android.graphics.drawable.TransitionDrawable;
        r1 = 0;
        if (r0 == 0) goto L_0x002a;
    L_0x0014:
        r0 = r6.getDrawable();
        r0 = (android.graphics.drawable.TransitionDrawable) r0;
        r2 = 1;
        r3 = r0.getDrawable(r2);
        r3 = r3 instanceof android.graphics.drawable.BitmapDrawable;
        if (r3 == 0) goto L_0x0039;
    L_0x0023:
        r0 = r0.getDrawable(r2);
        r0 = (android.graphics.drawable.BitmapDrawable) r0;
        goto L_0x003a;
    L_0x002a:
        r0 = r6.getDrawable();
        r0 = r0 instanceof android.graphics.drawable.BitmapDrawable;
        if (r0 == 0) goto L_0x0039;
    L_0x0032:
        r0 = r6.getDrawable();
        r0 = (android.graphics.drawable.BitmapDrawable) r0;
        goto L_0x003a;
    L_0x0039:
        r0 = r1;
    L_0x003a:
        if (r0 == 0) goto L_0x0040;
    L_0x003c:
        r1 = r0.getBitmap();
    L_0x0040:
        if (r1 == 0) goto L_0x00d5;
    L_0x0042:
        r0 = r6.getWidth();
        r6.canvasSize = r0;
        r0 = r6.getHeight();
        r2 = r6.canvasSize;
        if (r0 >= r2) goto L_0x0056;
    L_0x0050:
        r0 = r6.getHeight();
        r6.canvasSize = r0;
    L_0x0056:
        r0 = r6.image;
        if (r1 != r0) goto L_0x0060;
    L_0x005a:
        r0 = r6.shaderRadius;
        r2 = r6.canvasSize;
        if (r0 == r2) goto L_0x0096;
    L_0x0060:
        r6.image = r1;
        r0 = r6.canvasSize;
        r6.shaderRadius = r0;
        r0 = r6.image;
        r0 = r0.getHeight();
        r1 = r6.shaderRadius;
        if (r0 != r1) goto L_0x007d;
    L_0x0070:
        r0 = r6.image;
        r0 = r0.getWidth();
        r1 = r6.shaderRadius;
        if (r0 != r1) goto L_0x007d;
    L_0x007a:
        r0 = r6.image;
        goto L_0x0088;
    L_0x007d:
        r0 = r6.image;
        r1 = r6.shaderRadius;
        r2 = r6.shaderRadius;
        r3 = 0;
        r0 = android.graphics.Bitmap.createScaledBitmap(r0, r1, r2, r3);
    L_0x0088:
        r1 = new android.graphics.BitmapShader;
        r2 = android.graphics.Shader.TileMode.CLAMP;
        r3 = android.graphics.Shader.TileMode.CLAMP;
        r1.<init>(r0, r2, r3);
        r0 = r6.paint;
        r0.setShader(r1);
    L_0x0096:
        r0 = r6.canvasSize;
        r1 = r6.borderWidth;
        r1 = r1 * 2;
        r0 = r0 - r1;
        r0 = r0 / 2;
        r1 = r6.borderWidth;
        r1 = r1 + r0;
        r1 = (float) r1;
        r2 = r6.borderWidth;
        r2 = r2 + r0;
        r2 = (float) r2;
        r3 = r6.canvasSize;
        r4 = r6.borderWidth;
        r4 = r4 * 2;
        r3 = r3 - r4;
        r3 = r3 / 2;
        r4 = r6.borderWidth;
        r3 = r3 + r4;
        r3 = (float) r3;
        r4 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r3 = r3 - r4;
        r5 = r6.paintBorder;
        r7.drawCircle(r1, r2, r3, r5);
        r1 = r6.borderWidth;
        r1 = r1 + r0;
        r1 = (float) r1;
        r2 = r6.borderWidth;
        r0 = r0 + r2;
        r0 = (float) r0;
        r2 = r6.canvasSize;
        r3 = r6.borderWidth;
        r3 = r3 * 2;
        r2 = r2 - r3;
        r2 = r2 / 2;
        r2 = (float) r2;
        r2 = r2 - r4;
        r3 = r6.paint;
        r7.drawCircle(r1, r0, r2, r3);
        goto L_0x00d8;
    L_0x00d5:
        super.onDraw(r7);
    L_0x00d8:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.library.controls.CircularImageView.onDraw(android.graphics.Canvas):void");
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(measureWidth(i), measureHeight(i2));
    }

    private int measureWidth(int i) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        return (mode == 1073741824 || mode == Integer.MIN_VALUE) ? i : this.canvasSize;
    }

    private int measureHeight(int i) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        return (mode == 1073741824 || mode == Integer.MIN_VALUE) ? i : this.canvasSize;
    }
}
