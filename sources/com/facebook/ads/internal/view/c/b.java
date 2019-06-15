package com.facebook.ads.internal.view.c;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.internal.s.a.j;
import com.facebook.ads.internal.s.a.y;

public class b extends FrameLayout {
    private final ImageView a;
    private int b;
    private int c;

    public b(Context context) {
        super(context);
        this.a = new ImageView(context);
        a();
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ImageView(context, attributeSet);
        a();
    }

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new ImageView(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public b(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = new ImageView(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        this.a.setScaleType(ScaleType.FIT_XY);
        addView(this.a, new LayoutParams(-2, -2));
        j.a(this.a, j.INTERNAL_AD_MEDIA);
    }

    public void a(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap2 != null) {
            y.a((View) this, new BitmapDrawable(getContext().getResources(), bitmap2));
        } else {
            y.a((View) this, 0);
        }
        if (bitmap != null) {
            this.b = bitmap.getWidth();
            this.c = bitmap.getHeight();
            this.a.setImageBitmap(Bitmap.createBitmap(bitmap));
            return;
        }
        this.a.setImageDrawable(null);
    }

    public ImageView getBodyImageView() {
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (this.b <= 0 || this.c <= 0) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        float min = Math.min(((float) i5) / ((float) this.b), ((float) i6) / ((float) this.c));
        i3 = (int) (((float) this.b) * min);
        i += i5 / 2;
        i2 += i6 / 2;
        i3 /= 2;
        int i7 = ((int) (min * ((float) this.c))) / 2;
        this.a.layout(i - i3, i2 - i7, i + i3, i2 + i7);
    }
}
