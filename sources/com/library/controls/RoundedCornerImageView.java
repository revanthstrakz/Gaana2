package com.library.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.gaana.R;

public class RoundedCornerImageView extends CrossFadeImageView {
    private int cornerRadius;
    private boolean hasGradient;
    private int height;
    private Paint paint;
    private RectF rectF;
    private int shaderHeight;
    private Bitmap shaderImage;
    private int shaderWidth;
    private int width;

    /* Access modifiers changed, original: protected */
    public void setDefaultDrawable(@NonNull Drawable drawable) {
    }

    public RoundedCornerImageView(Context context) {
        this(context, null);
    }

    public RoundedCornerImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedCornerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.rectF = new RectF();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedCornerImageView, 0, 0);
        this.cornerRadius = obtainStyledAttributes.getInteger(0, getContext().getResources().getDimensionPixelSize(R.dimen.dimen_4dp));
        this.hasGradient = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dd  */
    public void onDraw(android.graphics.Canvas r11) {
        /*
        r10 = this;
        r0 = r10.getDrawable();
        r0 = r0 instanceof android.graphics.drawable.TransitionDrawable;
        r1 = 0;
        if (r0 == 0) goto L_0x001f;
    L_0x0009:
        r0 = r10.getDrawable();
        r0 = (android.graphics.drawable.TransitionDrawable) r0;
        r2 = 1;
        r3 = r0.getDrawable(r2);
        r3 = r3 instanceof android.graphics.drawable.BitmapDrawable;
        if (r3 == 0) goto L_0x0042;
    L_0x0018:
        r0 = r0.getDrawable(r2);
        r0 = (android.graphics.drawable.BitmapDrawable) r0;
        goto L_0x0043;
    L_0x001f:
        r0 = r10.getDrawable();
        r0 = r0 instanceof android.graphics.drawable.BitmapDrawable;
        if (r0 == 0) goto L_0x002e;
    L_0x0027:
        r0 = r10.getDrawable();
        r0 = (android.graphics.drawable.BitmapDrawable) r0;
        goto L_0x0043;
    L_0x002e:
        r0 = r10.getDrawable();
        r0 = r0 instanceof android.graphics.drawable.GradientDrawable;
        if (r0 == 0) goto L_0x0042;
    L_0x0036:
        r0 = r10.getDrawable();
        r0 = (android.graphics.drawable.GradientDrawable) r0;
        r2 = r10.cornerRadius;
        r2 = (float) r2;
        r0.setCornerRadius(r2);
    L_0x0042:
        r0 = r1;
    L_0x0043:
        if (r0 == 0) goto L_0x0049;
    L_0x0045:
        r1 = r0.getBitmap();
    L_0x0049:
        if (r1 == 0) goto L_0x00dd;
    L_0x004b:
        r0 = r10.getWidth();
        r10.width = r0;
        r0 = r10.getHeight();
        r10.height = r0;
        r0 = r10.shaderImage;
        if (r0 != r1) goto L_0x0067;
    L_0x005b:
        r0 = r10.width;
        r2 = r10.shaderWidth;
        if (r0 != r2) goto L_0x0067;
    L_0x0061:
        r0 = r10.height;
        r2 = r10.shaderHeight;
        if (r0 == r2) goto L_0x00c3;
    L_0x0067:
        r0 = r10.height;
        r10.shaderHeight = r0;
        r0 = r10.width;
        r10.shaderWidth = r0;
        r10.shaderImage = r1;
        r0 = r10.shaderImage;
        r0 = r0.getHeight();
        r2 = r10.shaderHeight;
        if (r0 != r2) goto L_0x0088;
    L_0x007b:
        r0 = r10.shaderImage;
        r0 = r0.getWidth();
        r2 = r10.shaderWidth;
        if (r0 != r2) goto L_0x0088;
    L_0x0085:
        r0 = r10.shaderImage;
        goto L_0x0091;
    L_0x0088:
        r0 = r10.width;
        r2 = r10.height;
        r3 = 0;
        r0 = android.graphics.Bitmap.createScaledBitmap(r1, r0, r2, r3);
    L_0x0091:
        r1 = new android.graphics.BitmapShader;
        r2 = android.graphics.Shader.TileMode.CLAMP;
        r3 = android.graphics.Shader.TileMode.CLAMP;
        r1.<init>(r0, r2, r3);
        r0 = r10.hasGradient;
        if (r0 == 0) goto L_0x00be;
    L_0x009e:
        r0 = new android.graphics.LinearGradient;
        r3 = 0;
        r2 = 2;
        r4 = r10.height;
        r2 = r2 * r4;
        r2 = r2 / 3;
        r4 = (float) r2;
        r5 = 0;
        r2 = r10.height;
        r6 = (float) r2;
        r7 = 436207616; // 0x1a000000 float:2.646978E-23 double:2.155151975E-315;
        r8 = -1728053248; // 0xffffffff99000000 float:-6.617445E-24 double:NaN;
        r9 = android.graphics.Shader.TileMode.CLAMP;
        r2 = r0;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9);
        r2 = new android.graphics.ComposeShader;
        r3 = android.graphics.PorterDuff.Mode.SRC_ATOP;
        r2.<init>(r1, r0, r3);
        r1 = r2;
    L_0x00be:
        r0 = r10.paint;
        r0.setShader(r1);
    L_0x00c3:
        r0 = r10.rectF;
        r1 = r10.width;
        r1 = (float) r1;
        r2 = r10.height;
        r2 = (float) r2;
        r3 = 0;
        r0.set(r3, r3, r1, r2);
        r0 = r10.rectF;
        r1 = r10.cornerRadius;
        r1 = (float) r1;
        r2 = r10.cornerRadius;
        r2 = (float) r2;
        r3 = r10.paint;
        r11.drawRoundRect(r0, r1, r2, r3);
        goto L_0x00e0;
    L_0x00dd:
        super.onDraw(r11);
    L_0x00e0:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.library.controls.RoundedCornerImageView.onDraw(android.graphics.Canvas):void");
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(measureWidth(i), measureHeight(i2));
    }

    private int measureWidth(int i) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        return (mode == 1073741824 || mode == Integer.MIN_VALUE) ? i : this.width;
    }

    public void setHasGradient(boolean z) {
        this.hasGradient = z;
    }

    private int measureHeight(int i) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        return (mode == 1073741824 || mode == Integer.MIN_VALUE) ? i : this.height;
    }
}
