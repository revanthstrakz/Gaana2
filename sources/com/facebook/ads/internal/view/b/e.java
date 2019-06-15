package com.facebook.ads.internal.view.b;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils.TruncateAt;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.s.b.b;
import com.facebook.ads.internal.s.b.c;

@TargetApi(19)
public class e extends LinearLayout {
    private TextView a;
    private TextView b;
    private Drawable c;

    public e(Context context) {
        super(context);
        a();
    }

    private void a() {
        float f = getResources().getDisplayMetrics().density;
        setOrientation(1);
        this.a = new TextView(getContext());
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        this.a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.a.setTextSize(2, 20.0f);
        this.a.setEllipsize(TruncateAt.END);
        this.a.setSingleLine(true);
        this.a.setVisibility(8);
        addView(this.a, layoutParams);
        this.b = new TextView(getContext());
        layoutParams = new LayoutParams(-1, -2);
        this.b.setAlpha(0.5f);
        this.b.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.b.setTextSize(2, 15.0f);
        this.b.setCompoundDrawablePadding((int) (5.0f * f));
        this.b.setEllipsize(TruncateAt.END);
        this.b.setSingleLine(true);
        this.b.setVisibility(8);
        addView(this.b, layoutParams);
    }

    private Drawable getPadlockDrawable() {
        if (this.c == null) {
            this.c = c.a(getContext(), b.BROWSER_PADLOCK);
        }
        return this.c;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:10:0x003c in {2, 4, 7, 8, 9} preds:[]
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
    public void setSubtitle(java.lang.String r4) {
        /*
        r3 = this;
        r0 = android.text.TextUtils.isEmpty(r4);
        r1 = 0;
        if (r0 == 0) goto L_0x0014;
        r4 = r3.b;
        r4.setText(r1);
        r4 = r3.b;
        r0 = 8;
        r4.setVisibility(r0);
        return;
        r4 = android.net.Uri.parse(r4);
        r0 = r3.b;
        r2 = r4.getHost();
        r0.setText(r2);
        r0 = r3.b;
        r2 = "https";
        r4 = r4.getScheme();
        r4 = r2.equals(r4);
        if (r4 == 0) goto L_0x0034;
        r4 = r3.getPadlockDrawable();
        goto L_0x0035;
        r4 = r1;
        r0.setCompoundDrawablesRelativeWithIntrinsicBounds(r4, r1, r1, r1);
        r4 = r3.b;
        r0 = 0;
        goto L_0x0010;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.b.e.setSubtitle(java.lang.String):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:6:0x001d in {2, 4, 5} preds:[]
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
    public void setTitle(java.lang.String r2) {
        /*
        r1 = this;
        r0 = android.text.TextUtils.isEmpty(r2);
        if (r0 == 0) goto L_0x0014;
        r2 = r1.a;
        r0 = 0;
        r2.setText(r0);
        r2 = r1.a;
        r0 = 8;
        r2.setVisibility(r0);
        return;
        r0 = r1.a;
        r0.setText(r2);
        r2 = r1.a;
        r0 = 0;
        goto L_0x0010;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.b.e.setTitle(java.lang.String):void");
    }
}
