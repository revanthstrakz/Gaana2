package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.component.a.d;

public class a extends b {
    private static final int c = ((int) (12.0f * y.b));

    a(d dVar, com.facebook.ads.internal.adapters.a.d dVar2, String str, com.facebook.ads.internal.view.d.a.a aVar) {
        super(dVar, dVar2, true, str, aVar);
    }

    /* Access modifiers changed, original: protected */
    public void a(Context context) {
        View titleDescContainer = getTitleDescContainer();
        titleDescContainer.setAlignment(3);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(8, getMediaContainer().getId());
        titleDescContainer.setLayoutParams(layoutParams);
        titleDescContainer.setPadding(c, c, c, c);
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        y.a(titleDescContainer, gradientDrawable);
        layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(3, getMediaContainer().getId());
        getCtaButton().setLayoutParams(layoutParams);
        addView(getMediaContainer());
        addView(titleDescContainer);
        addView(getCtaButton());
    }

    /* Access modifiers changed, original: protected */
    public boolean d() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean e() {
        return false;
    }
}
