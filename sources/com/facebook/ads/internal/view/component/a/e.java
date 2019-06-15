package com.facebook.ads.internal.view.component.a;

import android.content.res.Resources;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.h;

public class e extends b {
    private static final int c = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final f d;

    public e(d dVar, boolean z, d dVar2) {
        super(dVar, dVar2, z);
        this.d = new f(dVar.a(), dVar.d());
        this.d.a(dVar.h(), dVar.i(), getTitleDescContainer(), z);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12);
        layoutParams.setMargins(a, a, a, a);
        getCtaButton().setLayoutParams(layoutParams);
        FrameLayout frameLayout = new FrameLayout(dVar.a());
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        layoutParams2.addRule(2, getCtaButton().getId());
        frameLayout.setLayoutParams(layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams3.gravity = 17;
        layoutParams3.setMargins(a, 0, a, 0);
        frameLayout.addView(this.d, layoutParams3);
        addView(frameLayout);
        addView(getCtaButton());
    }

    public void a(h hVar, String str, double d) {
        super.a(hVar, str, d);
        if (d > 0.0d) {
            this.d.a((int) (((double) (c - (a * 2))) / d));
        }
    }

    public boolean a() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean c() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean e() {
        return false;
    }
}
