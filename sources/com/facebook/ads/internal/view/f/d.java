package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.component.g;

class d extends g {
    private final ImageView a;

    public d(Context context) {
        super(context);
        this.a = new ImageView(context);
        this.a.setAdjustViewBounds(true);
        addView(this.a, new LayoutParams(-2, -1));
    }

    public void a(String str) {
        com.facebook.ads.internal.view.c.d dVar = new com.facebook.ads.internal.view.c.d(this.a);
        dVar.a();
        dVar.a(str);
    }
}
