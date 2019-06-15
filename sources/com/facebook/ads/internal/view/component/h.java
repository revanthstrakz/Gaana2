package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.p.e;
import com.facebook.ads.internal.p.j;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.m;

public class h extends LinearLayout {
    public h(Context context, e eVar, j jVar) {
        super(context);
        float f = y.b;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setVerticalGravity(16);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        float f2 = 15.0f * f;
        layoutParams.setMargins(Math.round(f2), Math.round(f2), Math.round(f2), Math.round(f2));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        CharSequence m = eVar.m();
        TextView textView = new TextView(getContext());
        if (TextUtils.isEmpty(m)) {
            m = eVar.l();
        }
        textView.setText(m);
        m.a(textView, jVar);
        textView.setEllipsize(TruncateAt.END);
        textView.setSingleLine(true);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(getContext());
        textView2.setText(eVar.n());
        m.b(textView2, jVar);
        textView2.setEllipsize(TruncateAt.END);
        textView2.setMaxLines(2);
        linearLayout.addView(textView2);
    }
}
