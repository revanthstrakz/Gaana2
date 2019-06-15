package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.view.component.i;
import com.facebook.ads.internal.view.g.b.z;
import java.util.HashMap;

public class a extends LinearLayout {
    private static final int a = ((int) (12.0f * y.b));
    private static final int b = ((int) (16.0f * y.b));
    private final i c;
    private final ImageView d;
    private final RelativeLayout e;
    private final com.facebook.ads.internal.view.component.a f;
    private final int g;

    public a(Context context, int i, d dVar, c cVar, com.facebook.ads.internal.view.a.a aVar, boolean z, boolean z2, com.facebook.ads.internal.t.a aVar2, w wVar) {
        Context context2 = context;
        int i2 = i;
        super(context);
        this.g = i2;
        this.d = new f(context2);
        y.a(this.d, 0);
        y.a(this.d);
        LayoutParams layoutParams = new LayoutParams(i2, i2);
        layoutParams.addRule(15);
        layoutParams.addRule(9);
        layoutParams.setMargins(0, 0, a, 0);
        if (z2) {
            this.d.setVisibility(8);
        }
        this.c = new i(context2, dVar, true, z, true);
        this.c.setAlignment(8388611);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(1, this.d.getId());
        layoutParams2.addRule(15);
        com.facebook.ads.internal.view.component.a aVar3 = r1;
        com.facebook.ads.internal.view.component.a aVar4 = new com.facebook.ads.internal.view.component.a(context2, true, false, z.REWARDED_VIDEO_AD_CLICK.a(), dVar, cVar, aVar, aVar2, wVar);
        this.f = aVar3;
        this.f.setVisibility(8);
        this.e = new RelativeLayout(context2);
        y.a(this.e);
        this.e.addView(this.d, layoutParams);
        this.e.addView(this.c, layoutParams2);
        addView(this.e, new LinearLayout.LayoutParams(-2, -2));
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        y.a(this, gradientDrawable);
    }

    public void a() {
        this.f.setVisibility(0);
    }

    public void a(int i) {
        y.b(this.f);
        int i2 = 1;
        if (i != 1) {
            i2 = 0;
        }
        setOrientation(i2);
        int i3 = -1;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2 != 0 ? -1 : 0, -2);
        layoutParams.weight = 1.0f;
        if (i2 == 0) {
            i3 = -2;
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i3, -2);
        layoutParams2.setMargins(i2 != 0 ? 0 : b, i2 != 0 ? b : 0, 0, 0);
        layoutParams2.gravity = 80;
        this.e.setLayoutParams(layoutParams);
        addView(this.f, layoutParams2);
    }

    public void setInfo(k kVar) {
        this.c.a(kVar.b().a(), kVar.b().b(), false, false);
        this.f.a(kVar.c(), kVar.g(), new HashMap());
        new com.facebook.ads.internal.view.c.d(this.d).a(this.g, this.g).a(kVar.a().b());
    }
}
