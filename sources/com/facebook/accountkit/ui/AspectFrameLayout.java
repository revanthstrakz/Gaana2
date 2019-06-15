package com.facebook.accountkit.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.facebook.accountkit.R;

public final class AspectFrameLayout extends FrameLayout {
    private int aspectHeight;
    private int aspectWidth;
    private Point displaySize;

    public AspectFrameLayout(Context context) {
        super(context);
    }

    public AspectFrameLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public AspectFrameLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    @TargetApi(21)
    public AspectFrameLayout(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    private void init(Context context, @Nullable AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AspectFrameLayout);
        try {
            this.aspectWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AspectFrameLayout_com_accountkit_aspect_width, 0);
            this.aspectHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AspectFrameLayout_com_accountkit_aspect_height, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public float getAspectHeight() {
        return (float) this.aspectHeight;
    }

    public void setAspectHeight(int i) {
        if (this.aspectHeight != i) {
            this.aspectHeight = i;
            requestLayout();
        }
    }

    public float getAspectWidth() {
        return (float) this.aspectWidth;
    }

    public void setAspectWidth(int i) {
        if (this.aspectWidth != i) {
            this.aspectWidth = i;
            requestLayout();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Point point = new Point();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
        this.displaySize = point;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        if (this.aspectWidth == 0 || this.aspectHeight == 0 || this.displaySize == null) {
            super.onMeasure(i, i2);
            return;
        }
        i = (this.displaySize.x * this.aspectHeight) / this.aspectWidth;
        if (i > this.displaySize.y) {
            i2 = this.displaySize.x;
        } else {
            i2 = (this.displaySize.y * this.aspectWidth) / this.aspectHeight;
            i = this.displaySize.y;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(i2, 1073741824), MeasureSpec.makeMeasureSpec(i, 1073741824));
    }
}
