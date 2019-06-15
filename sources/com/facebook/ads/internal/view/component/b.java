package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.p.e;
import com.facebook.ads.internal.p.j;
import com.facebook.ads.internal.view.m;
import com.facebook.ads.internal.view.p;

public class b extends LinearLayout {
    private p a = new p(getContext(), 2);
    private int b;

    public b(Context context, e eVar, j jVar) {
        super(context);
        setOrientation(1);
        setVerticalGravity(16);
        this.a.setMinTextSize((float) (jVar.h() - 2));
        this.a.setText(eVar.l());
        m.a(this.a, jVar);
        this.a.setLayoutParams(new LayoutParams(-2, -2));
        addView(this.a);
        int i = 21;
        if (eVar.l() != null) {
            i = Math.min(eVar.l().length(), 21);
        }
        this.b = i;
        addView(m.a(context, eVar, jVar));
    }

    public int getMinVisibleTitleCharacters() {
        return this.b;
    }

    public TextView getTitleTextView() {
        return this.a;
    }
}
