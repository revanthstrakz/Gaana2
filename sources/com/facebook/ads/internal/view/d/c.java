package com.facebook.ads.internal.view.d;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.i;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.c.d;
import com.facebook.ads.internal.view.component.f;

public class c extends LinearLayout {
    private f a;
    private TextView b;
    private TextView c;

    public c(Context context) {
        super(context);
        a(context);
    }

    public void a(int i, int i2) {
        this.b.setTextColor(i);
        this.c.setTextColor(i2);
    }

    public void a(Context context) {
        int i = (int) (32.0f * y.b);
        setGravity(16);
        this.a = new f(context);
        this.a.setFullCircleCorners(true);
        LayoutParams layoutParams = new LayoutParams(i, i);
        layoutParams.setMargins(0, 0, (int) (8.0f * y.b), 0);
        addView(this.a, layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.b = new TextView(context);
        layoutParams = new LayoutParams(-1, -2);
        y.a(this.b, true, 16);
        this.b.setEllipsize(TruncateAt.END);
        this.b.setSingleLine(true);
        this.c = new TextView(context);
        y.a(this.c, false, 14);
        linearLayout.addView(this.b);
        linearLayout.addView(this.c);
        addView(linearLayout, layoutParams);
    }

    public void setPageDetails(i iVar) {
        d dVar = new d(this.a);
        dVar.a((int) (y.b * 32.0f), (int) (32.0f * y.b));
        dVar.a(iVar.b());
        this.b.setText(iVar.a());
        this.c.setText(iVar.d());
    }
}
