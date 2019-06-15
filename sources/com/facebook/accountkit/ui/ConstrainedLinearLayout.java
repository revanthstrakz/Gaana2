package com.facebook.accountkit.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import com.facebook.accountkit.R;

public final class ConstrainedLinearLayout extends LinearLayout {
    private int maxHeight = -1;
    private int maxWidth = -1;
    private int minHeight = -1;

    public ConstrainedLinearLayout(Context context) {
        super(context);
    }

    public ConstrainedLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public ConstrainedLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    @TargetApi(21)
    public ConstrainedLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null && !isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstrainedLinearLayout);
            try {
                this.maxHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ConstrainedLinearLayout_com_accountkit_max_height, this.maxHeight);
                this.maxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ConstrainedLinearLayout_com_accountkit_max_width, this.maxWidth);
                this.minHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ConstrainedLinearLayout_com_accountkit_min_height, this.minHeight);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public int getMinHeight() {
        return this.minHeight;
    }

    public void setMinHeight(int i) {
        this.minHeight = i;
        requestLayout();
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        Object obj;
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (this.maxWidth < 0 || measuredWidth <= this.maxWidth) {
            obj = null;
        } else {
            i = MeasureSpec.makeMeasureSpec(this.maxWidth, 1073741824);
            obj = 1;
        }
        if (this.maxHeight >= 0 && measuredHeight > this.maxHeight) {
            i2 = MeasureSpec.makeMeasureSpec(this.maxHeight, 1073741824);
            obj = 1;
        }
        if (this.minHeight >= 0 && measuredHeight < this.minHeight) {
            i2 = MeasureSpec.makeMeasureSpec(this.minHeight, 1073741824);
            obj = 1;
        }
        if (obj != null) {
            super.onMeasure(i, i2);
        }
    }
}
