package com.facebook.ads.internal.view.component.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.component.g;
import com.facebook.ads.internal.view.component.i;

final class f extends RelativeLayout {
    private final View a;
    private final g b;

    public f(Context context, View view) {
        super(context);
        this.a = view;
        this.b = new g(context);
        y.a(this.b);
    }

    public void a(int i) {
        this.a.setLayoutParams(new LayoutParams(-1, i));
    }

    public void a(@Nullable View view, @Nullable View view2, @Nullable i iVar, boolean z) {
        LayoutParams layoutParams;
        this.b.addView(this.a, new LayoutParams(-1, -2));
        if (view2 != null) {
            layoutParams = new LayoutParams(b.b, b.b);
            layoutParams.addRule(6, this.a.getId());
            layoutParams.addRule(7, this.a.getId());
            layoutParams.setMargins(b.a, b.a, b.a, b.a);
            this.b.addView(view2, layoutParams);
        }
        view2 = new LinearLayout(getContext());
        view2.setOrientation(1);
        layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(8, this.a.getId());
        if (iVar != null) {
            if (z) {
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                iVar.setAlignment(3);
                layoutParams2.setMargins(b.a / 2, b.a / 2, b.a / 2, b.a / 2);
                view2.addView(iVar, layoutParams2);
                Drawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
                gradientDrawable.setCornerRadius(0.0f);
                gradientDrawable.setGradientType(0);
                y.a(view2, gradientDrawable);
            } else {
                LayoutParams layoutParams3 = new LayoutParams(-1, -2);
                layoutParams3.addRule(3, this.b.getId());
                layoutParams3.setMargins(0, b.a, 0, 0);
                iVar.setAlignment(17);
                addView(iVar, layoutParams3);
            }
        }
        if (view != null) {
            view2.addView(view, new LayoutParams(-1, -2));
        }
        this.b.addView(view2, layoutParams);
        addView(this.b, new LayoutParams(-1, -2));
    }
}
