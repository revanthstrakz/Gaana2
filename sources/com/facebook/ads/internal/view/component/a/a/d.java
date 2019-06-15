package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.component.i;
import com.facebook.ads.internal.view.d.a.a;

public class d extends b {
    private static final int c = ((int) (20.0f * y.b));
    private static final int d = ((int) (16.0f * y.b));

    d(com.facebook.ads.internal.view.component.a.d dVar, com.facebook.ads.internal.adapters.a.d dVar2, String str, a aVar) {
        super(dVar, dVar2, false, str, aVar);
    }

    /* Access modifiers changed, original: protected */
    public void a(Context context) {
        i titleDescContainer = getTitleDescContainer();
        titleDescContainer.setAlignment(3);
        titleDescContainer.setLayoutParams(new LayoutParams(-1, -2));
        titleDescContainer.setPadding(0, 0, 0, c);
        getCtaButton().setLayoutParams(new LayoutParams(-1, -2));
        View linearLayout = new LinearLayout(context);
        y.a(linearLayout, new ColorDrawable(-1));
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(3, getMediaContainer().getId());
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(d, d, d, d);
        linearLayout.addView(titleDescContainer);
        linearLayout.addView(getCtaButton());
        addView(getMediaContainer());
        addView(linearLayout);
    }

    /* Access modifiers changed, original: protected */
    public boolean b() {
        return false;
    }
}
