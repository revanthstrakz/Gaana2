package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.gaana.R;

public class RoundedSquareImageView extends SquareImageView {
    private int cornerRadius;
    private boolean hasGradient;
    private Bitmap image;
    private Paint paint;
    private RectF rectF;
    private int shaderLength;

    public RoundedSquareImageView(Context context) {
        super(context);
    }

    public RoundedSquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.rectF = new RectF();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedCornerImageView, 0, 0);
        this.cornerRadius = obtainStyledAttributes.getInteger(0, getContext().getResources().getDimensionPixelSize(R.dimen.dimen_4dp));
        this.hasGradient = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
    }

    public void setHasGradient(boolean z) {
        this.hasGradient = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b5  */
    public void onDraw(android.graphics.Canvas r13) {
        /*
        r12 = this;
        r0 = r12.getDrawable();
        r0 = r0 instanceof android.graphics.drawable.TransitionDrawable;
        r1 = 0;
        if (r0 == 0) goto L_0x001f;
    L_0x0009:
        r0 = r12.getDrawable();
        r0 = (android.graphics.drawable.TransitionDrawable) r0;
        r2 = 1;
        r3 = r0.getDrawable(r2);
        r3 = r3 instanceof android.graphics.drawable.BitmapDrawable;
        if (r3 == 0) goto L_0x002e;
    L_0x0018:
        r0 = r0.getDrawable(r2);
        r0 = (android.graphics.drawable.BitmapDrawable) r0;
        goto L_0x002f;
    L_0x001f:
        r0 = r12.getDrawable();
        r0 = r0 instanceof android.graphics.drawable.BitmapDrawable;
        if (r0 == 0) goto L_0x002e;
    L_0x0027:
        r0 = r12.getDrawable();
        r0 = (android.graphics.drawable.BitmapDrawable) r0;
        goto L_0x002f;
    L_0x002e:
        r0 = r1;
    L_0x002f:
        if (r0 == 0) goto L_0x0035;
    L_0x0031:
        r1 = r0.getBitmap();
    L_0x0035:
        if (r1 == 0) goto L_0x00b5;
    L_0x0037:
        r0 = r12.getWidth();
        r2 = r12.getHeight();
        r3 = r12.image;
        if (r1 != r3) goto L_0x004b;
    L_0x0043:
        r3 = r12.shaderLength;
        if (r3 != r0) goto L_0x004b;
    L_0x0047:
        r3 = r12.shaderLength;
        if (r3 == r2) goto L_0x009f;
    L_0x004b:
        r12.image = r1;
        r12.shaderLength = r0;
        r1 = r12.image;
        r1 = r1.getHeight();
        r3 = r12.shaderLength;
        if (r1 != r3) goto L_0x0066;
    L_0x0059:
        r1 = r12.image;
        r1 = r1.getWidth();
        r3 = r12.shaderLength;
        if (r1 != r3) goto L_0x0066;
    L_0x0063:
        r1 = r12.image;
        goto L_0x0071;
    L_0x0066:
        r1 = r12.image;
        r3 = r12.shaderLength;
        r4 = r12.shaderLength;
        r5 = 0;
        r1 = android.graphics.Bitmap.createScaledBitmap(r1, r3, r4, r5);
    L_0x0071:
        r3 = new android.graphics.BitmapShader;
        r4 = android.graphics.Shader.TileMode.CLAMP;
        r5 = android.graphics.Shader.TileMode.CLAMP;
        r3.<init>(r1, r4, r5);
        r1 = r12.hasGradient;
        if (r1 == 0) goto L_0x009a;
    L_0x007e:
        r1 = new android.graphics.LinearGradient;
        r5 = 0;
        r4 = 2;
        r4 = r4 * r2;
        r4 = r4 / 3;
        r6 = (float) r4;
        r7 = 0;
        r8 = (float) r2;
        r9 = 436207616; // 0x1a000000 float:2.646978E-23 double:2.155151975E-315;
        r10 = -1728053248; // 0xffffffff99000000 float:-6.617445E-24 double:NaN;
        r11 = android.graphics.Shader.TileMode.CLAMP;
        r4 = r1;
        r4.<init>(r5, r6, r7, r8, r9, r10, r11);
        r4 = new android.graphics.ComposeShader;
        r5 = android.graphics.PorterDuff.Mode.SRC_ATOP;
        r4.<init>(r3, r1, r5);
        r3 = r4;
    L_0x009a:
        r1 = r12.paint;
        r1.setShader(r3);
    L_0x009f:
        r1 = r12.rectF;
        r0 = (float) r0;
        r2 = (float) r2;
        r3 = 0;
        r1.set(r3, r3, r0, r2);
        r0 = r12.rectF;
        r1 = r12.cornerRadius;
        r1 = (float) r1;
        r2 = r12.cornerRadius;
        r2 = (float) r2;
        r3 = r12.paint;
        r13.drawRoundRect(r0, r1, r2, r3);
        goto L_0x00b8;
    L_0x00b5:
        super.onDraw(r13);
    L_0x00b8:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.RoundedSquareImageView.onDraw(android.graphics.Canvas):void");
    }
}
