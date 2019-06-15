package com.gaana.view.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class CustomCardView extends RelativeLayout {
    public CustomCardView(Context context) {
        super(context);
    }

    public CustomCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i2, i2);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
    }
}
