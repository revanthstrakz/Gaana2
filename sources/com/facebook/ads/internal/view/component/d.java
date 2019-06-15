package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AdIconView;
import com.facebook.ads.internal.p.e;
import com.facebook.ads.internal.p.j;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.m;
import com.facebook.ads.internal.view.q;

public class d extends LinearLayout {
    private AdIconView a;
    private b b;
    private TextView c;
    private LinearLayout d = new LinearLayout(getContext());

    public d(Context context, e eVar, j jVar, AdIconView adIconView, boolean z, int i) {
        j jVar2 = jVar;
        boolean z2 = z;
        super(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setVerticalGravity(16);
        setOrientation(1);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(displayMetrics.density * 15.0f), Math.round(displayMetrics.density * 15.0f), Math.round(displayMetrics.density * 15.0f), Math.round(displayMetrics.density * 15.0f));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        layoutParams = new LayoutParams(-1, 0);
        this.d.setOrientation(0);
        this.d.setGravity(16);
        layoutParams.weight = 3.0f;
        this.d.setLayoutParams(layoutParams);
        linearLayout.addView(this.d);
        this.a = adIconView;
        float a = (float) a(z2, i);
        LayoutParams layoutParams2 = new LayoutParams(Math.round(displayMetrics.density * a), Math.round(a * displayMetrics.density));
        layoutParams2.setMargins(0, 0, Math.round(displayMetrics.density * 15.0f), 0);
        this.a.setLayoutParams(layoutParams2);
        this.d.addView(this.a);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        this.d.addView(linearLayout2);
        this.b = new b(getContext(), eVar, jVar2);
        LayoutParams layoutParams3 = new LayoutParams(-2, -1);
        layoutParams3.setMargins(0, 0, Math.round(15.0f * displayMetrics.density), 0);
        layoutParams3.weight = 0.5f;
        this.b.setLayoutParams(layoutParams3);
        linearLayout2.addView(this.b);
        this.c = new TextView(getContext());
        this.c.setPadding(Math.round(displayMetrics.density * 6.0f), Math.round(displayMetrics.density * 6.0f), Math.round(displayMetrics.density * 6.0f), Math.round(6.0f * displayMetrics.density));
        this.c.setText(eVar.p());
        this.c.setTextColor(jVar.f());
        this.c.setTextSize(14.0f);
        this.c.setTypeface(jVar.a(), 1);
        this.c.setMaxLines(2);
        this.c.setEllipsize(TruncateAt.END);
        this.c.setGravity(17);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(jVar.e());
        gradientDrawable.setCornerRadius(5.0f * displayMetrics.density);
        gradientDrawable.setStroke(1, jVar.g());
        y.a(this.c, gradientDrawable);
        LayoutParams layoutParams4 = new LayoutParams(-2, -2);
        layoutParams4.weight = 0.25f;
        this.c.setLayoutParams(layoutParams4);
        if (!eVar.h()) {
            this.c.setVisibility(4);
        }
        linearLayout2.addView(this.c);
        if (z2) {
            q qVar = new q(getContext());
            qVar.setText(eVar.n());
            m.b(qVar, jVar2);
            qVar.setMinTextSize((float) (jVar.i() - 1));
            LayoutParams layoutParams5 = new LayoutParams(-1, 0);
            layoutParams5.weight = 1.0f;
            qVar.setLayoutParams(layoutParams5);
            qVar.setGravity(80);
            linearLayout.addView(qVar);
        }
    }

    private int a(boolean z, int i) {
        return (int) (((double) (i - 30)) * (3.0d / ((double) (3 + z))));
    }

    public TextView getCallToActionView() {
        return this.c;
    }

    public AdIconView getIconView() {
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        TextView titleTextView = this.b.getTitleTextView();
        if (titleTextView.getLayout().getLineEnd(titleTextView.getLineCount() - 1) < this.b.getMinVisibleTitleCharacters()) {
            this.d.removeView(this.a);
            super.onMeasure(i, i2);
        }
    }
}
