package com.gaana.view.item;

import android.content.Context;
import android.util.AttributeSet;
import com.library.controls.CrossFadeImageView;

public class SquareImageByHeight extends CrossFadeImageView {
    public SquareImageByHeight(Context context) {
        super(context);
    }

    public SquareImageByHeight(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i2, i2);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
    }
}
