package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;
import com.facebook.ads.internal.s.a.y;

public class d extends TextView {
    private static final int a = ((int) (16.0f * y.b));
    private static final int b = ((int) (12.0f * y.b));
    private boolean c = false;

    public d(Context context) {
        super(context);
        b();
        setPadding(a, b, a, b);
    }

    private void b() {
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(this.c ? -13272859 : -1315344);
        gradientDrawable.setCornerRadius(50.0f);
        y.a((View) this, gradientDrawable);
        setTextColor(this.c ? -1 : -10459280);
    }

    public void a() {
        this.c ^= 1;
        b();
    }
}
