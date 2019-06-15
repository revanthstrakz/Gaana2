package com.moengage.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RatingBar;
import com.moengage.inapp.R;

public class MoERatingBar extends RatingBar {
    private int color;
    private int colorFillPressedOff;
    private Bitmap colorsJoined;
    private final float dp;
    private float interiorAngleModifier;
    private final Paint paintInside;
    private final Paint paintOutline;
    private Path path;
    private int polygonVertices;
    private final RectF rectangle;
    private float starSize;
    private int strokeWidth;

    public MoERatingBar(Context context) {
        this(context, null);
    }

    public MoERatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.color = Color.rgb(97, 97, 97);
        this.colorFillPressedOff = 0;
        this.polygonVertices = 5;
        this.strokeWidth = -1;
        this.paintInside = new Paint();
        this.paintOutline = new Paint();
        this.path = new Path();
        this.rectangle = new RectF();
        this.interiorAngleModifier = 2.2f;
        this.dp = getResources().getDisplayMetrics().density;
        getXmlAttrs(context, attributeSet);
        init();
    }

    public MoERatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.color = Color.rgb(97, 97, 97);
        this.colorFillPressedOff = 0;
        this.polygonVertices = 5;
        this.strokeWidth = -1;
        this.paintInside = new Paint();
        this.paintOutline = new Paint();
        this.path = new Path();
        this.rectangle = new RectF();
        this.interiorAngleModifier = 2.2f;
        this.dp = getResources().getDisplayMetrics().density;
        getXmlAttrs(context, attributeSet);
        init();
    }

    private void init() {
        this.paintInside.setAntiAlias(true);
        this.paintOutline.setStrokeWidth((float) this.strokeWidth);
        this.paintOutline.setStyle(Style.STROKE);
        this.paintOutline.setStrokeJoin(Join.ROUND);
        this.paintOutline.setAntiAlias(true);
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized void onMeasure(int i, int i2) {
        int numStars = (int) ((40.0f * this.dp) * ((float) getNumStars()));
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        i2 = MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            numStars = i;
        } else if (mode == Integer.MIN_VALUE) {
            numStars = Math.min(numStars, i);
        }
        if (mode2 != 1073741824) {
            if (mode2 == Integer.MIN_VALUE) {
                i2 = Math.min(i2, numStars / getNumStars());
            } else {
                i2 = numStars / getNumStars();
            }
        }
        this.starSize = (float) Math.min(i2, numStars / getNumStars());
        if (this.strokeWidth < 0) {
            this.strokeWidth = (int) (this.starSize / 15.0f);
        }
        this.starSize -= (float) this.strokeWidth;
        setMeasuredDimension(numStars, i2);
    }

    private Path createStarBySize(float f, int i) {
        float f2 = f;
        int i2 = i;
        if (i2 == 0) {
            this.path.addOval(new RectF(0.0f, 0.0f, f2, f2), Direction.CW);
            this.path.close();
            return this.path;
        }
        f2 /= 2.0f;
        float f3 = f2 / this.interiorAngleModifier;
        float toRadians = (float) Math.toRadians((double) (360.0f / ((float) i2)));
        float f4 = toRadians / 2.0f;
        this.path.setFillType(FillType.EVEN_ODD);
        this.path.moveTo(f2, 0.0f);
        double d = 0.0d;
        for (float f5 = 6.2831855f; d < ((double) f5); f5 = 6.2831855f) {
            double d2 = (double) f2;
            this.path.lineTo((float) (d2 - (Math.sin(d) * d2)), (float) (d2 - (Math.cos(d) * d2)));
            double d3 = (double) f3;
            double d4 = ((double) f4) + d;
            double d5 = d;
            this.path.lineTo((float) (d2 - (Math.sin(d4) * d3)), (float) (d2 - (d3 * Math.cos(d4))));
            d = d5 + ((double) toRadians);
        }
        this.path.close();
        return this.path;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.paintInside.setShader(updateShader(this.color, this.colorFillPressedOff));
        this.path.rewind();
        this.path = createStarBySize(this.starSize, this.polygonVertices);
        for (int i = 0; i < getNumStars(); i++) {
            this.paintOutline.setColor(this.color);
            this.path.computeBounds(this.rectangle, true);
            this.path.offset((((((float) i) + 0.5f) * ((float) getWidth())) / ((float) getNumStars())) - this.rectangle.centerX(), ((float) (getHeight() / 2)) - this.rectangle.centerY());
            canvas.drawPath(this.path, this.paintInside);
            canvas.drawPath(this.path, this.paintOutline);
        }
    }

    private BitmapShader updateShader(int i, int i2) {
        int rating = (int) ((getRating() * ((float) getWidth())) / ((float) getNumStars()));
        Bitmap bitmap;
        if (rating <= 0 || getWidth() - rating <= 0) {
            this.colorsJoined = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
            bitmap = this.colorsJoined;
            if (rating <= 0) {
                i = i2;
            }
            bitmap.eraseColor(i);
        } else {
            bitmap = Bitmap.createBitmap(rating, getHeight(), Config.ARGB_8888);
            bitmap.eraseColor(i);
            Bitmap createBitmap = Bitmap.createBitmap(getWidth() - rating, getHeight(), Config.ARGB_8888);
            createBitmap.eraseColor(i2);
            this.colorsJoined = combineBitmaps(bitmap, createBitmap);
        }
        return new BitmapShader(this.colorsJoined, TileMode.CLAMP, TileMode.CLAMP);
    }

    private Bitmap combineBitmaps(Bitmap bitmap, Bitmap bitmap2) {
        this.colorsJoined = Bitmap.createBitmap(bitmap.getWidth() + bitmap2.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(this.colorsJoined);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
        canvas.drawBitmap(bitmap2, (float) bitmap.getWidth(), 0.0f, null);
        return this.colorsJoined;
    }

    private void getXmlAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.MoERatingBar, 0, 0);
        try {
            this.color = obtainStyledAttributes.getInteger(R.styleable.MoERatingBar_starColor, Color.rgb(97, 97, 97));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setColor(int i) {
        this.color = i;
    }
}
