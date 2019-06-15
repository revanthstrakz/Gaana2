package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.ColorUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.s.a.y;
import java.util.ArrayList;
import java.util.List;

public class e extends LinearLayout {
    private final int a;
    private final int b;
    private final int c;
    private int d = -1;
    private List<GradientDrawable> e;

    public e(Context context, d dVar, int i) {
        super(context);
        setOrientation(0);
        setGravity(17);
        float f = y.b;
        int i2 = (int) (8.0f * f);
        int i3 = (int) (6.0f * f);
        this.c = (int) (1.0f * f);
        this.a = dVar.a(false);
        this.b = ColorUtils.setAlphaComponent(this.a, 128);
        this.e = new ArrayList();
        for (int i4 = 0; i4 < i; i4++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setSize(i2, i2);
            gradientDrawable.setStroke(this.c, 0);
            ImageView imageView = new ImageView(context);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, i3, 0);
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
            imageView.setImageDrawable(gradientDrawable);
            this.e.add(gradientDrawable);
            addView(imageView);
        }
        a(0);
    }

    public void a(int i) {
        if (this.d != i) {
            this.d = i;
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                int i3;
                int i4;
                if (i2 == i) {
                    i3 = this.a;
                    i4 = this.a;
                } else {
                    i3 = this.b;
                    i4 = 0;
                }
                ((GradientDrawable) this.e.get(i2)).setStroke(this.c, i4);
                ((GradientDrawable) this.e.get(i2)).setColor(i3);
                ((GradientDrawable) this.e.get(i2)).invalidateSelf();
            }
        }
    }
}
