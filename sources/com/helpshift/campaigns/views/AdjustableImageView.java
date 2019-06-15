package com.helpshift.campaigns.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

@SuppressLint({"AppCompatCustomView"})
public class AdjustableImageView extends ImageView {
    boolean a;

    public AdjustableImageView(Context context) {
        super(context);
    }

    public AdjustableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AdjustableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAdjustViewBounds(boolean z) {
        this.a = z;
        super.setAdjustViewBounds(z);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onMeasure(i, i2);
            return;
        }
        if (this.a) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int size = MeasureSpec.getSize(i2);
            int size2 = MeasureSpec.getSize(i);
            int mode = MeasureSpec.getMode(i2);
            int mode2 = MeasureSpec.getMode(i);
            if (mode == 1073741824 && mode2 != 1073741824) {
                intrinsicWidth = (intrinsicWidth * size) / intrinsicHeight;
                if (a()) {
                    setMeasuredDimension(intrinsicWidth, size);
                } else {
                    setMeasuredDimension(Math.min(intrinsicWidth, size2), Math.min(size, size));
                }
            } else if (mode2 != 1073741824 || mode == 1073741824) {
                super.onMeasure(i, i2);
            } else {
                intrinsicHeight = (intrinsicHeight * size2) / intrinsicWidth;
                if (a()) {
                    setMeasuredDimension(size2, intrinsicHeight);
                } else {
                    setMeasuredDimension(Math.min(size2, size2), Math.min(intrinsicHeight, size));
                }
            }
        } else {
            super.onMeasure(i, i2);
        }
    }

    private boolean a() {
        ViewParent parent = getParent();
        while (parent != null && (parent instanceof ViewGroup)) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }
}
