package com.gaana.view.item;

import android.content.Context;
import android.util.AttributeSet;
import com.library.controls.CrossFadeImageView;

public class SquareImageView extends CrossFadeImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
