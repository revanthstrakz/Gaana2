package com.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.gaana.R;
import com.gaana.login.GooglePlusLogin;
import com.utilities.Util;

public class ExtraHeightSquareLayout extends FrameLayout {
    private int a = 0;
    private int b = 0;
    private float c = 1.0f;

    public ExtraHeightSquareLayout(@NonNull Context context) {
        super(context);
        a(context, null, 0, 0);
    }

    public ExtraHeightSquareLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0, 0);
    }

    public ExtraHeightSquareLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public ExtraHeightSquareLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, 0);
    }

    public void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExtraHeightSquareLayout, i, i2);
        this.a = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        this.c = obtainStyledAttributes.getFloat(2, 1.0f);
        this.b = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (size <= 480) {
            if (size > size2) {
                this.a = Util.b((int) GooglePlusLogin.RC_CREDENTIALS_READ);
            } else {
                this.a = Util.b(180);
            }
        } else if (size <= 720) {
            this.a = Util.b(270);
        } else if (size <= 1080) {
            if (size > size2) {
                this.a = Util.b(220);
            } else {
                this.a = Util.b(280);
            }
        }
        if (size == 0 && size2 == 0) {
            super.onMeasure(i, i2);
            i = Math.min(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(i, i);
            return;
        }
        i = (int) (((float) size) * this.c);
        i2 = ((int) (((float) size2) * this.c)) - this.b;
        if (this.a != 0) {
            i = Math.min(i, this.a);
        }
        if (i == 0 || i2 == 0) {
            i = Math.max(i, i2);
        } else {
            i = Math.min(i, i2);
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(i, 1073741824), MeasureSpec.makeMeasureSpec(this.b + i, 1073741824));
    }
}
