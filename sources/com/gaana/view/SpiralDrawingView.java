package com.gaana.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class SpiralDrawingView extends View {
    boolean cancelCheck = false;
    Paint drawPaint;
    float fixXcordinate = ((float) this.size);
    float fixYcordinate;
    public int height;
    private Paint mPaint;
    private Path mPath;
    RectF oval;
    int size;
    public int width;

    public SpiralDrawingView(Context context, float f, int i) {
        super(context);
        this.fixYcordinate = f;
        this.size = i;
        this.oval = new RectF((float) (this.size / 2), (float) (this.size / 2), (this.fixYcordinate * 2.0f) + ((float) this.size), (this.fixYcordinate * 2.0f) + ((float) this.size));
        this.mPath = new Path();
        this.mPath.setFillType(FillType.WINDING);
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setColor(Color.parseColor("#ee4820"));
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeJoin(Join.ROUND);
        this.mPaint.setStrokeCap(Cap.ROUND);
        this.mPaint.setStrokeWidth((float) this.size);
        int i2 = (int) (2.0f * f);
        this.width = (this.size * 2) + i2;
        this.height = i2 + (2 * this.size);
        setLayoutParams(new LayoutParams(this.width, this.height));
        this.drawPaint = new Paint();
        this.drawPaint.setColor(-7829368);
        this.drawPaint.setStyle(Style.STROKE);
    }

    public void ResetSpiral() {
        this.mPath.reset();
        this.mPath.moveTo(this.fixXcordinate, this.fixYcordinate);
        invalidate();
    }

    public void onResume(float f) {
        this.mPath.arcTo(this.oval, 180.0f, (float) ((int) ((57.29577951308232d * ((double) f)) / ((double) this.fixYcordinate))), true);
        invalidate();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawPath(this.mPath, this.mPaint);
    }
}
