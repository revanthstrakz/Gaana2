package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils.TruncateAt;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.s.a.y;

public class i extends LinearLayout {
    private static final float a = Resources.getSystem().getDisplayMetrics().density;
    private static final int b = ((int) (6.0f * a));
    private static final int c = ((int) (8.0f * a));
    private final TextView d;
    private final TextView e;

    public i(Context context, d dVar, boolean z, int i, int i2, int i3) {
        super(context);
        setOrientation(1);
        this.d = new TextView(context);
        y.a(this.d, true, i);
        this.d.setTextColor(dVar.c(z));
        this.d.setEllipsize(TruncateAt.END);
        this.d.setLineSpacing((float) b, 1.0f);
        this.e = new TextView(context);
        y.a(this.e, false, i2);
        this.e.setTextColor(dVar.b(z));
        this.e.setEllipsize(TruncateAt.END);
        this.e.setLineSpacing((float) b, 1.0f);
        addView(this.d, new LayoutParams(-1, -2));
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, i3, 0, 0);
        addView(this.e, layoutParams);
    }

    public i(Context context, d dVar, boolean z, boolean z2, boolean z3) {
        this(context, dVar, z, z2 ? 18 : 22, z2 ? 14 : 16, z3 ? c / 2 : c);
    }

    /* JADX WARNING: Missing block: B:16:0x0035, code skipped:
            if (r8 != false) goto L_0x0041;
     */
    public void a(java.lang.String r5, java.lang.String r6, boolean r7, boolean r8) {
        /*
        r4 = this;
        r0 = android.text.TextUtils.isEmpty(r5);
        r1 = 1;
        r0 = r0 ^ r1;
        r2 = android.text.TextUtils.isEmpty(r6);
        r2 = r2 ^ r1;
        r3 = r4.d;
        if (r0 == 0) goto L_0x0010;
    L_0x000f:
        goto L_0x0011;
    L_0x0010:
        r5 = r6;
    L_0x0011:
        r3.setText(r5);
        r5 = r4.e;
        if (r0 == 0) goto L_0x0019;
    L_0x0018:
        goto L_0x001b;
    L_0x0019:
        r6 = "";
    L_0x001b:
        r5.setText(r6);
        r5 = 3;
        r6 = 2;
        if (r0 == 0) goto L_0x0038;
    L_0x0022:
        if (r2 != 0) goto L_0x0025;
    L_0x0024:
        goto L_0x0038;
    L_0x0025:
        r0 = r4.d;
        if (r7 == 0) goto L_0x002b;
    L_0x0029:
        r2 = r1;
        goto L_0x002c;
    L_0x002b:
        r2 = r6;
    L_0x002c:
        r0.setMaxLines(r2);
        r0 = r4.e;
        if (r7 == 0) goto L_0x0035;
    L_0x0033:
        r5 = r1;
        goto L_0x0041;
    L_0x0035:
        if (r8 == 0) goto L_0x003c;
    L_0x0037:
        goto L_0x0041;
    L_0x0038:
        r0 = r4.d;
        if (r7 == 0) goto L_0x003e;
    L_0x003c:
        r5 = r6;
        goto L_0x0041;
    L_0x003e:
        if (r8 == 0) goto L_0x0041;
    L_0x0040:
        r5 = 4;
    L_0x0041:
        r0.setMaxLines(r5);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.component.i.a(java.lang.String, java.lang.String, boolean, boolean):void");
    }

    public TextView getDescriptionTextView() {
        return this.e;
    }

    public TextView getTitleTextView() {
        return this.d;
    }

    public void setAlignment(int i) {
        this.d.setGravity(i);
        this.e.setGravity(i);
    }
}
