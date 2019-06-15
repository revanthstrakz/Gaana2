package com.gaana.view.item;

import android.content.Context;
import android.util.AttributeSet;
import com.library.controls.CrossFadeImageView;

public class SquareImageViewByHeight extends CrossFadeImageView {
    public SquareImageViewByHeight(Context context) {
        super(context);
    }

    public SquareImageViewByHeight(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
