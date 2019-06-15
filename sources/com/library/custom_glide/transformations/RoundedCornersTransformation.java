package com.library.custom_glide.transformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import com.bumptech.glide.load.engine.a.e;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.resource.bitmap.d;
import java.security.MessageDigest;

public class RoundedCornersTransformation implements i<Bitmap> {
    private e mBitmapPool;
    private CornerType mCornerType;
    private int mDiameter;
    private int mMargin;
    private int mRadius;

    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public q<Bitmap> transform(Context context, q<Bitmap> qVar, int i, int i2) {
        return transform(qVar, i, i2);
    }

    public RoundedCornersTransformation(Context context, int i, int i2) {
        this(context, i, i2, CornerType.ALL);
    }

    public RoundedCornersTransformation(e eVar, int i, int i2) {
        this(eVar, i, i2, CornerType.ALL);
    }

    public RoundedCornersTransformation(Context context, int i, int i2, CornerType cornerType) {
        this(com.bumptech.glide.e.b(context).b(), i, i2, cornerType);
    }

    public RoundedCornersTransformation(e eVar, int i, int i2, CornerType cornerType) {
        this.mBitmapPool = eVar;
        this.mRadius = i;
        this.mDiameter = this.mRadius * 2;
        this.mMargin = i2;
        this.mCornerType = cornerType;
    }

    public q<Bitmap> transform(q<Bitmap> qVar, int i, int i2) {
        Bitmap bitmap = (Bitmap) qVar.c();
        i = bitmap.getWidth();
        i2 = bitmap.getHeight();
        Bitmap a = this.mBitmapPool.a(i, i2, Config.ARGB_4444);
        if (a == null) {
            a = Bitmap.createBitmap(i, i2, Config.ARGB_4444);
        }
        Canvas canvas = new Canvas(a);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
        drawRoundRect(canvas, paint, (float) i, (float) i2);
        return d.a(a, this.mBitmapPool);
    }

    private void drawRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        f -= (float) this.mMargin;
        f2 -= (float) this.mMargin;
        switch (this.mCornerType) {
            case ALL:
                canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, f, f2), (float) this.mRadius, (float) this.mRadius, paint);
                return;
            case TOP_LEFT:
                drawTopLeftRoundRect(canvas, paint, f, f2);
                return;
            case TOP_RIGHT:
                drawTopRightRoundRect(canvas, paint, f, f2);
                return;
            case BOTTOM_LEFT:
                drawBottomLeftRoundRect(canvas, paint, f, f2);
                return;
            case BOTTOM_RIGHT:
                drawBottomRightRoundRect(canvas, paint, f, f2);
                return;
            case TOP:
                drawTopRoundRect(canvas, paint, f, f2);
                return;
            case BOTTOM:
                drawBottomRoundRect(canvas, paint, f, f2);
                return;
            case LEFT:
                drawLeftRoundRect(canvas, paint, f, f2);
                return;
            case RIGHT:
                drawRightRoundRect(canvas, paint, f, f2);
                return;
            case OTHER_TOP_LEFT:
                drawOtherTopLeftRoundRect(canvas, paint, f, f2);
                return;
            case OTHER_TOP_RIGHT:
                drawOtherTopRightRoundRect(canvas, paint, f, f2);
                return;
            case OTHER_BOTTOM_LEFT:
                drawOtherBottomLeftRoundRect(canvas, paint, f, f2);
                return;
            case OTHER_BOTTOM_RIGHT:
                drawOtherBottomRightRoundRect(canvas, paint, f, f2);
                return;
            case DIAGONAL_FROM_TOP_LEFT:
                drawDiagonalFromTopLeftRoundRect(canvas, paint, f, f2);
                return;
            case DIAGONAL_FROM_TOP_RIGHT:
                drawDiagonalFromTopRightRoundRect(canvas, paint, f, f2);
                return;
            default:
                canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, f, f2), (float) this.mRadius, (float) this.mRadius, paint);
                return;
        }
    }

    private void drawTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, (float) (this.mMargin + this.mDiameter), (float) (this.mMargin + this.mDiameter)), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) (this.mMargin + this.mRadius), (float) (this.mMargin + this.mRadius), f2), paint);
        canvas.drawRect(new RectF((float) (this.mMargin + this.mRadius), (float) this.mMargin, f, f2), paint);
    }

    private void drawTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.mDiameter), (float) this.mMargin, f, (float) (this.mMargin + this.mDiameter)), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) this.mMargin, f - ((float) this.mRadius), f2), paint);
        canvas.drawRect(new RectF(f - ((float) this.mRadius), (float) (this.mMargin + this.mRadius), f, f2), paint);
    }

    private void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, f2 - ((float) this.mDiameter), (float) (this.mMargin + this.mDiameter), f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) this.mMargin, (float) (this.mMargin + this.mDiameter), f2 - ((float) this.mRadius)), paint);
        canvas.drawRect(new RectF((float) (this.mMargin + this.mRadius), (float) this.mMargin, f, f2), paint);
    }

    private void drawBottomRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.mDiameter), f2 - ((float) this.mDiameter), f, f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) this.mMargin, f - ((float) this.mRadius), f2), paint);
        canvas.drawRect(new RectF(f - ((float) this.mRadius), (float) this.mMargin, f, f2 - ((float) this.mRadius)), paint);
    }

    private void drawTopRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, f, (float) (this.mMargin + this.mDiameter)), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) (this.mMargin + this.mRadius), f, f2), paint);
    }

    private void drawBottomRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, f2 - ((float) this.mDiameter), f, f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) this.mMargin, f, f2 - ((float) this.mRadius)), paint);
    }

    private void drawLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, (float) (this.mMargin + this.mDiameter), f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) (this.mMargin + this.mRadius), (float) this.mMargin, f, f2), paint);
    }

    private void drawRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.mDiameter), (float) this.mMargin, f, f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) this.mMargin, f - ((float) this.mRadius), f2), paint);
    }

    private void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, f2 - ((float) this.mDiameter), f, f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRoundRect(new RectF(f - ((float) this.mDiameter), (float) this.mMargin, f, f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) this.mMargin, f - ((float) this.mRadius), f2 - ((float) this.mRadius)), paint);
    }

    private void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, (float) (this.mMargin + this.mDiameter), f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRoundRect(new RectF((float) this.mMargin, f2 - ((float) this.mDiameter), f, f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) (this.mMargin + this.mRadius), (float) this.mMargin, f, f2 - ((float) this.mRadius)), paint);
    }

    private void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, f, (float) (this.mMargin + this.mDiameter)), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRoundRect(new RectF(f - ((float) this.mDiameter), (float) this.mMargin, f, f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) (this.mMargin + this.mRadius), f - ((float) this.mRadius), f2), paint);
    }

    private void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, f, (float) (this.mMargin + this.mDiameter)), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, (float) (this.mMargin + this.mDiameter), f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) (this.mMargin + this.mRadius), (float) (this.mMargin + this.mRadius), f, f2), paint);
    }

    private void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF((float) this.mMargin, (float) this.mMargin, (float) (this.mMargin + this.mDiameter), (float) (this.mMargin + this.mDiameter)), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRoundRect(new RectF(f - ((float) this.mDiameter), f2 - ((float) this.mDiameter), f, f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) (this.mMargin + this.mRadius), f - ((float) this.mDiameter), f2), paint);
        canvas.drawRect(new RectF((float) (this.mMargin + this.mDiameter), (float) this.mMargin, f, f2 - ((float) this.mRadius)), paint);
    }

    private void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - ((float) this.mDiameter), (float) this.mMargin, f, (float) (this.mMargin + this.mDiameter)), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRoundRect(new RectF((float) this.mMargin, f2 - ((float) this.mDiameter), (float) (this.mMargin + this.mDiameter), f2), (float) this.mRadius, (float) this.mRadius, paint);
        canvas.drawRect(new RectF((float) this.mMargin, (float) this.mMargin, f - ((float) this.mRadius), f2 - ((float) this.mRadius)), paint);
        canvas.drawRect(new RectF((float) (this.mMargin + this.mRadius), (float) (this.mMargin + this.mRadius), f, f2), paint);
    }

    public String getId() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RoundedTransformation(radius=");
        stringBuilder.append(this.mRadius);
        stringBuilder.append(", margin=");
        stringBuilder.append(this.mMargin);
        stringBuilder.append(", diameter=");
        stringBuilder.append(this.mDiameter);
        stringBuilder.append(", cornerType=");
        stringBuilder.append(this.mCornerType.name());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
