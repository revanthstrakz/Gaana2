package com.facebook.ads.internal.view;

import android.content.Context;
import android.widget.RelativeLayout;

public class r extends RelativeLayout {
    private int a = 0;
    private int b = 0;

    public r(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.b > 0 && getMeasuredWidth() > this.b) {
            i = this.b;
        } else if (getMeasuredWidth() < this.a) {
            i = this.a;
        } else {
            return;
        }
        setMeasuredDimension(i, getMeasuredHeight());
    }

    public void setMaxWidth(int i) {
        this.b = i;
    }

    public void setMinWidth(int i) {
        this.a = i;
    }
}
