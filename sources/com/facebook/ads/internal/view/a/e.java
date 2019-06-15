package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.s.a.y;

public class e extends LinearLayout {
    public static final LayoutParams a = new LayoutParams(-1, -2);
    private static final int b = ((int) (8.0f * y.b));
    private static final int c = ((int) (14.5d * ((double) y.b)));
    private static final int d = ((int) (20.0f * y.b));
    private final LinearLayout e;
    private final ImageView f;
    private final TextView g;

    public e(Context context) {
        super(context);
        this.f = new ImageView(context);
        this.f.setColorFilter(-10459280);
        LayoutParams layoutParams = new LayoutParams(d, d);
        layoutParams.gravity = 16;
        this.f.setLayoutParams(layoutParams);
        this.e = new LinearLayout(context);
        this.e.setOrientation(1);
        this.e.setPadding(b * 2, 0, 0, 0);
        this.e.setLayoutParams(a);
        this.g = new TextView(context);
        y.a(this.g, true, 16);
        this.g.setTextColor(-14934495);
        this.e.addView(this.g, a);
        setOrientation(0);
        addView(this.f);
        addView(this.e);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:6:0x0040 in {2, 4, 5} preds:[]
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:242)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:42)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    public void a(com.facebook.ads.internal.s.b.b r2, java.lang.String r3, java.lang.String r4) {
        /*
        r1 = this;
        r0 = r1.f;
        r2 = com.facebook.ads.internal.s.b.c.a(r2);
        r0.setImageBitmap(r2);
        r2 = r1.g;
        r2.setText(r3);
        r2 = android.text.TextUtils.isEmpty(r4);
        r3 = 0;
        if (r2 != 0) goto L_0x003b;
        r2 = new android.widget.TextView;
        r0 = r1.getContext();
        r2.<init>(r0);
        r0 = 14;
        com.facebook.ads.internal.s.a.y.a(r2, r3, r0);
        r0 = -10459280; // 0xffffffffff606770 float:-2.9828415E38 double:NaN;
        r2.setTextColor(r0);
        r2.setText(r4);
        r4 = r1.e;
        r0 = a;
        r4.addView(r2, r0);
        r2 = b;
        r4 = b;
        r1.setPadding(r3, r2, r3, r4);
        return;
        r2 = c;
        r4 = c;
        goto L_0x0037;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.a.e.a(com.facebook.ads.internal.s.b.b, java.lang.String, java.lang.String):void");
    }
}
