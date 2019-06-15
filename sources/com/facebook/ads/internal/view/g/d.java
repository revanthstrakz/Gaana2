package com.facebook.ads.internal.view.g;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.g.c.g;
import com.facebook.ads.internal.view.g.d.c;
import java.lang.ref.WeakReference;

public class d extends RelativeLayout {
    private final c a;
    @Nullable
    private g b;
    private WeakReference<a> c;

    public interface a {
        void a();
    }

    public d(Context context, c cVar) {
        super(context);
        this.a = cVar;
        y.b((View) this.a);
        addView(this.a.getView(), new LayoutParams(-1, -1));
    }

    public void a(com.facebook.ads.internal.view.g.a.c cVar) {
        addView(cVar, new LayoutParams(-1, -1));
        this.b = (g) cVar;
    }

    public void b(com.facebook.ads.internal.view.g.a.c cVar) {
        y.b(cVar);
        this.b = null;
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ((View) this.a).layout(0, 0, getWidth(), getHeight());
        if (this.b != null) {
            this.b.layout(0, 0, getWidth(), getHeight());
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:15:0x0046, code skipped:
            if (r3 > r10) goto L_0x006f;
     */
    public void onMeasure(int r9, int r10) {
        /*
        r8 = this;
        r0 = r8.a;
        r0 = r0.getVideoWidth();
        r1 = r8.a;
        r1 = r1.getVideoHeight();
        r2 = getDefaultSize(r0, r9);
        r3 = getDefaultSize(r1, r10);
        if (r0 <= 0) goto L_0x006a;
    L_0x0016:
        if (r1 <= 0) goto L_0x006a;
    L_0x0018:
        r2 = 1;
        r3 = android.view.View.MeasureSpec.getMode(r9);
        r9 = android.view.View.MeasureSpec.getSize(r9);
        r4 = android.view.View.MeasureSpec.getMode(r10);
        r10 = android.view.View.MeasureSpec.getSize(r10);
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r3 != r5) goto L_0x003d;
    L_0x002d:
        if (r4 != r5) goto L_0x003d;
    L_0x002f:
        r3 = r0 * r10;
        r4 = r9 * r1;
        if (r3 >= r4) goto L_0x0038;
    L_0x0035:
        r9 = r3 / r1;
        goto L_0x006f;
    L_0x0038:
        if (r3 <= r4) goto L_0x006f;
    L_0x003a:
        r3 = r4 / r0;
        goto L_0x0049;
    L_0x003d:
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r3 != r5) goto L_0x004b;
    L_0x0041:
        r1 = r1 * r9;
        r3 = r1 / r0;
        if (r4 != r6) goto L_0x0049;
    L_0x0046:
        if (r3 <= r10) goto L_0x0049;
    L_0x0048:
        goto L_0x006f;
    L_0x0049:
        r10 = r3;
        goto L_0x006f;
    L_0x004b:
        if (r4 != r5) goto L_0x0056;
    L_0x004d:
        r0 = r0 * r10;
        r0 = r0 / r1;
        if (r3 != r6) goto L_0x0054;
    L_0x0051:
        if (r0 <= r9) goto L_0x0054;
    L_0x0053:
        goto L_0x006f;
    L_0x0054:
        r9 = r0;
        goto L_0x006f;
    L_0x0056:
        if (r4 != r6) goto L_0x005e;
    L_0x0058:
        if (r1 <= r10) goto L_0x005e;
    L_0x005a:
        r4 = r10 * r0;
        r4 = r4 / r1;
        goto L_0x0060;
    L_0x005e:
        r4 = r0;
        r10 = r1;
    L_0x0060:
        if (r3 != r6) goto L_0x0068;
    L_0x0062:
        if (r4 <= r9) goto L_0x0068;
    L_0x0064:
        r1 = r1 * r9;
        r3 = r1 / r0;
        goto L_0x0049;
    L_0x0068:
        r9 = r4;
        goto L_0x006f;
    L_0x006a:
        r9 = 0;
        r10 = r3;
        r7 = r2;
        r2 = r9;
        r9 = r7;
    L_0x006f:
        r8.setMeasuredDimension(r9, r10);
        if (r2 == 0) goto L_0x008b;
    L_0x0074:
        r9 = r8.c;
        if (r9 == 0) goto L_0x008b;
    L_0x0078:
        r9 = r8.c;
        r9 = r9.get();
        if (r9 == 0) goto L_0x008b;
    L_0x0080:
        r9 = r8.c;
        r9 = r9.get();
        r9 = (com.facebook.ads.internal.view.g.d.a) r9;
        r9.a();
    L_0x008b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.g.d.onMeasure(int, int):void");
    }

    public void setViewImplInflationListener(a aVar) {
        this.c = new WeakReference(aVar);
    }
}
