package com.facebook.ads.internal.p;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AdIconView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.component.d;
import com.facebook.ads.internal.view.component.h;
import com.facebook.ads.internal.view.r;
import java.util.ArrayList;

public class a {
    private static final float a = y.b;
    private final j b;
    private final e c;
    private final RelativeLayout d;
    private ArrayList<View> e = new ArrayList();

    public a(Context context, e eVar, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, @Nullable MediaView mediaView, AdIconView adIconView, k kVar, j jVar) {
        f mediaView2;
        this.d = relativeLayout;
        y.a(this.d, jVar.b());
        this.b = jVar;
        this.c = eVar;
        this.d.setLayoutParams(new LayoutParams(-1, Math.round(((float) kVar.b()) * a)));
        r rVar = new r(context);
        rVar.setMinWidth(Math.round(280.0f * a));
        rVar.setMaxWidth(Math.round(375.0f * a));
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13, -1);
        rVar.setLayoutParams(layoutParams);
        this.d.addView(rVar);
        ViewGroup linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        rVar.addView(linearLayout);
        switch (kVar) {
            case HEIGHT_400:
                a(linearLayout);
                break;
            case HEIGHT_300:
                break;
        }
        a(linearLayout, mediaView2);
        a(linearLayout, kVar, adIconView);
        View view = this.d;
        if (mediaView2 == null) {
            mediaView2 = adIconView;
        }
        eVar.a(view, mediaView2, this.e);
        LayoutParams layoutParams2 = (LayoutParams) relativeLayout2.getLayoutParams();
        layoutParams2.addRule(11);
        layoutParams2.setMargins(Math.round(a * 4.0f), Math.round(a * 4.0f), Math.round(a * 4.0f), Math.round(4.0f * a));
        rVar.addView(relativeLayout2);
    }

    private void a(ViewGroup viewGroup) {
        h hVar = new h(this.d.getContext(), this.c, this.b);
        hVar.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(110.0f * a)));
        viewGroup.addView(hVar);
    }

    private void a(ViewGroup viewGroup, RelativeLayout relativeLayout) {
        View relativeLayout2 = new RelativeLayout(this.d.getContext());
        relativeLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(a * 180.0f)));
        y.a(relativeLayout2, this.b.b());
        relativeLayout2.addView(relativeLayout);
        LayoutParams layoutParams = new LayoutParams(-1, (int) (180.0f * a));
        layoutParams.addRule(13, -1);
        relativeLayout.setLayoutParams(layoutParams);
        viewGroup.addView(relativeLayout2);
        this.e.add(relativeLayout);
    }

    private void a(ViewGroup viewGroup, k kVar, AdIconView adIconView) {
        d dVar = new d(this.d.getContext(), this.c, this.b, adIconView, a(kVar), b(kVar));
        dVar.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(((float) b(kVar)) * a)));
        viewGroup.addView(dVar);
        this.e.add(dVar.getIconView());
        this.e.add(dVar.getCallToActionView());
    }

    private boolean a(k kVar) {
        return kVar == k.HEIGHT_300 || kVar == k.HEIGHT_120;
    }

    private int b(k kVar) {
        switch (kVar) {
            case HEIGHT_400:
                return (kVar.b() - 180) / 2;
            case HEIGHT_300:
                return kVar.b() - 180;
            case HEIGHT_100:
            case HEIGHT_120:
                return kVar.b();
            default:
                return 0;
        }
    }

    public void a() {
        this.c.I();
    }
}
