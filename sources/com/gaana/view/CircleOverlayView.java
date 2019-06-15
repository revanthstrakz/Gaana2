package com.gaana.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.constants.Constants;
import com.gaana.R;

public class CircleOverlayView extends LinearLayout {
    private Bitmap bitmap;
    private int circleMarginRight;
    private int circleMarginTop;

    public boolean isInEditMode() {
        return true;
    }

    public CircleOverlayView(Context context) {
        this(context, null);
    }

    public CircleOverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleOverlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.circleMarginRight = 0;
        this.circleMarginTop = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleOverlayView);
        if (Constants.cc) {
            this.circleMarginRight = context.getResources().getDimensionPixelSize(obtainStyledAttributes.getResourceId(1, R.dimen.activity_horizontal_margin));
        } else if (Constants.cb) {
            this.circleMarginRight = context.getResources().getDimensionPixelSize(obtainStyledAttributes.getResourceId(0, R.dimen.activity_horizontal_margin));
        }
        this.circleMarginTop = context.getResources().getDimensionPixelSize(obtainStyledAttributes.getResourceId(2, R.dimen.circle_margin_top));
        if (Constants.ce == 1) {
            this.circleMarginRight = context.getResources().getDimensionPixelSize(obtainStyledAttributes.getResourceId(4, R.dimen.coachmark_chrome_pos1_margin));
            obtainStyledAttributes.recycle();
        } else if (Constants.ce == 2) {
            this.circleMarginRight = context.getResources().getDimensionPixelSize(obtainStyledAttributes.getResourceId(5, R.dimen.coachmark_chrome_pos2_margin));
            obtainStyledAttributes.recycle();
        } else if (Constants.ce == 3) {
            this.circleMarginRight = context.getResources().getDimensionPixelSize(obtainStyledAttributes.getResourceId(6, R.dimen.coachmark_chrome_pos3_margin));
            obtainStyledAttributes.recycle();
        } else if (Constants.ce == 4) {
            this.circleMarginRight = context.getResources().getDimensionPixelSize(obtainStyledAttributes.getResourceId(7, R.dimen.coachmark_chrome_pos4_margin));
            obtainStyledAttributes.recycle();
        }
    }

    /* Access modifiers changed, original: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.bitmap == null) {
            createWindowFrame();
        }
        canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, null);
    }

    /* Access modifiers changed, original: protected */
    public void createWindowFrame() {
        this.bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_4444);
        Canvas canvas = new Canvas(this.bitmap);
        RectF rectF = new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        Paint paint = new Paint(1);
        if (Constants.l) {
            paint.setColor(getResources().getColor(R.color.cast_white_color));
        } else {
            paint.setColor(getResources().getColor(R.color.cast_black_color));
        }
        canvas.drawRect(rectF, paint);
        paint.setColor(0);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OUT));
        float width = (float) (getWidth() - this.circleMarginRight);
        float f = (float) this.circleMarginTop;
        float dimensionPixelSize = (float) getResources().getDimensionPixelSize(R.dimen.coachmark_cast_circle_radius);
        canvas.drawCircle(width, f, dimensionPixelSize, paint);
        paint = new Paint();
        if (Constants.l) {
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            paint.setColor(-1);
        }
        paint.setStrokeWidth(2.0f);
        paint.setStyle(Style.STROKE);
        canvas.drawCircle(width, f, (float) (((double) (25.0f + dimensionPixelSize)) / 1.4d), paint);
        paint = new Paint();
        if (Constants.l) {
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            paint.setColor(-1);
        }
        paint.setStrokeWidth(5.0f);
        paint.setStyle(Style.STROKE);
        canvas.drawCircle(width, f, (float) (((double) dimensionPixelSize) / 1.4d), paint);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.bitmap = null;
    }
}
