package com.facebook.ads.internal.view.component.a;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.s.a.y;

public class a extends b {
    public a(d dVar, d dVar2, boolean z) {
        super(dVar, dVar2, true);
        View relativeLayout = new RelativeLayout(dVar.a());
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12);
        Drawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
        gradientDrawable.setCornerRadius(0.0f);
        gradientDrawable.setGradientType(0);
        y.a(relativeLayout, gradientDrawable);
        View linearLayout = new LinearLayout(dVar.a());
        linearLayout.setOrientation(z ^ 1);
        linearLayout.setGravity(80);
        y.a(linearLayout);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.setMargins(a, 0, a, dVar.h() == null ? a : a / 2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(z ? -2 : -1, -2);
        layoutParams3.setMargins(z ? a : 0, z ? 0 : a, 0, 0);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(z ? 0 : -1, -2);
        layoutParams4.setMargins(0, 0, 0, 0);
        layoutParams4.weight = 1.0f;
        linearLayout.addView(getTitleDescContainer(), layoutParams4);
        linearLayout.addView(getCtaButton(), layoutParams3);
        relativeLayout.addView(linearLayout, layoutParams2);
        if (dVar.h() != null) {
            LayoutParams layoutParams5 = new LayoutParams(-1, -2);
            layoutParams5.setMargins(0, 0, 0, 0);
            layoutParams5.addRule(3, linearLayout.getId());
            relativeLayout.addView(dVar.h(), layoutParams5);
        }
        addView(dVar.d(), new LayoutParams(-1, -1));
        addView(relativeLayout, layoutParams);
        if (dVar.i() != null) {
            LayoutParams layoutParams6 = new LayoutParams(b, b);
            layoutParams6.addRule(10);
            layoutParams6.addRule(11);
            layoutParams6.setMargins(a, a + dVar.j(), a, a);
            addView(dVar.i(), layoutParams6);
        }
    }

    public boolean a() {
        return true;
    }
}
