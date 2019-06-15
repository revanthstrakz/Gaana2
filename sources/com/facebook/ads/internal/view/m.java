package com.facebook.ads.internal.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p.e;
import com.facebook.ads.internal.p.j;

public abstract class m {
    public static LinearLayout a(Context context, e eVar, j jVar) {
        LinearLayout linearLayout = new LinearLayout(context);
        q qVar = new q(context);
        qVar.setText(eVar.q());
        b(qVar, jVar);
        linearLayout.addView(qVar);
        return linearLayout;
    }

    public static void a(TextView textView, j jVar) {
        textView.setTextColor(jVar.c());
        textView.setTextSize((float) jVar.h());
        textView.setTypeface(jVar.a(), 1);
    }

    public static void b(TextView textView, j jVar) {
        textView.setTextColor(jVar.d());
        textView.setTextSize((float) jVar.i());
        textView.setTypeface(jVar.a());
    }
}
