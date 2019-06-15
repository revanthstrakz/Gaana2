package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CollectionUtils;
import com.payu.custombrowser.util.CBConstant;
import java.util.Set;

@zzark
public final class zzaoa extends zzaok {
    private static final Set<String> zzdpf = CollectionUtils.setOf("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    private final Object mLock = new Object();
    private zzaol zzdgd;
    private final zzbgg zzdin;
    private final Activity zzdow;
    private String zzdpg = "top-right";
    private boolean zzdph = true;
    private int zzdpi = 0;
    private int zzdpj = 0;
    private int zzdpk = 0;
    private int zzdpl = 0;
    private zzbht zzdpm;
    private ImageView zzdpn;
    private LinearLayout zzdpo;
    private PopupWindow zzdpp;
    private RelativeLayout zzdpq;
    private ViewGroup zzdpr;
    private int zzvt = -1;
    private int zzvu = -1;

    public zzaoa(zzbgg zzbgg, zzaol zzaol) {
        super(zzbgg, "resize");
        this.zzdin = zzbgg;
        this.zzdow = zzbgg.zzabw();
        this.zzdgd = zzaol;
    }

    public final void zzk(java.util.Map<java.lang.String, java.lang.String> r14) {
        /*
        r13 = this;
        r0 = r13.mLock;
        monitor-enter(r0);
        r1 = r13.zzdow;	 Catch:{ all -> 0x0320 }
        if (r1 != 0) goto L_0x000e;
    L_0x0007:
        r14 = "Not an activity context. Cannot resize.";
        r13.zzda(r14);	 Catch:{ all -> 0x0320 }
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x000e:
        r1 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r1 = r1.zzadj();	 Catch:{ all -> 0x0320 }
        if (r1 != 0) goto L_0x001d;
    L_0x0016:
        r14 = "Webview is not yet available, size is not set.";
        r13.zzda(r14);	 Catch:{ all -> 0x0320 }
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x001d:
        r1 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r1 = r1.zzadj();	 Catch:{ all -> 0x0320 }
        r1 = r1.zzafb();	 Catch:{ all -> 0x0320 }
        if (r1 == 0) goto L_0x0030;
    L_0x0029:
        r14 = "Is interstitial. Cannot resize an interstitial.";
        r13.zzda(r14);	 Catch:{ all -> 0x0320 }
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x0030:
        r1 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r1 = r1.zzadq();	 Catch:{ all -> 0x0320 }
        if (r1 == 0) goto L_0x003f;
    L_0x0038:
        r14 = "Cannot resize an expanded banner.";
        r13.zzda(r14);	 Catch:{ all -> 0x0320 }
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x003f:
        r1 = "width";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0320 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0320 }
        if (r1 != 0) goto L_0x005e;
    L_0x004d:
        com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ all -> 0x0320 }
        r1 = "width";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0320 }
        r1 = com.google.android.gms.internal.ads.zzayh.zzdy(r1);	 Catch:{ all -> 0x0320 }
        r13.zzvt = r1;	 Catch:{ all -> 0x0320 }
    L_0x005e:
        r1 = "height";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0320 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0320 }
        if (r1 != 0) goto L_0x007d;
    L_0x006c:
        com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ all -> 0x0320 }
        r1 = "height";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0320 }
        r1 = com.google.android.gms.internal.ads.zzayh.zzdy(r1);	 Catch:{ all -> 0x0320 }
        r13.zzvu = r1;	 Catch:{ all -> 0x0320 }
    L_0x007d:
        r1 = "offsetX";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0320 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0320 }
        if (r1 != 0) goto L_0x009c;
    L_0x008b:
        com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ all -> 0x0320 }
        r1 = "offsetX";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0320 }
        r1 = com.google.android.gms.internal.ads.zzayh.zzdy(r1);	 Catch:{ all -> 0x0320 }
        r13.zzdpk = r1;	 Catch:{ all -> 0x0320 }
    L_0x009c:
        r1 = "offsetY";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0320 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0320 }
        if (r1 != 0) goto L_0x00bb;
    L_0x00aa:
        com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ all -> 0x0320 }
        r1 = "offsetY";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0320 }
        r1 = com.google.android.gms.internal.ads.zzayh.zzdy(r1);	 Catch:{ all -> 0x0320 }
        r13.zzdpl = r1;	 Catch:{ all -> 0x0320 }
    L_0x00bb:
        r1 = "allowOffscreen";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0320 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0320 }
        if (r1 != 0) goto L_0x00d7;
    L_0x00c9:
        r1 = "allowOffscreen";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0320 }
        r1 = java.lang.Boolean.parseBoolean(r1);	 Catch:{ all -> 0x0320 }
        r13.zzdph = r1;	 Catch:{ all -> 0x0320 }
    L_0x00d7:
        r1 = "customClosePosition";
        r14 = r14.get(r1);	 Catch:{ all -> 0x0320 }
        r14 = (java.lang.String) r14;	 Catch:{ all -> 0x0320 }
        r1 = android.text.TextUtils.isEmpty(r14);	 Catch:{ all -> 0x0320 }
        if (r1 != 0) goto L_0x00e7;
    L_0x00e5:
        r13.zzdpg = r14;	 Catch:{ all -> 0x0320 }
    L_0x00e7:
        r14 = r13.zzvt;	 Catch:{ all -> 0x0320 }
        r1 = 0;
        r2 = 1;
        if (r14 < 0) goto L_0x00f3;
    L_0x00ed:
        r14 = r13.zzvu;	 Catch:{ all -> 0x0320 }
        if (r14 < 0) goto L_0x00f3;
    L_0x00f1:
        r14 = r2;
        goto L_0x00f4;
    L_0x00f3:
        r14 = r1;
    L_0x00f4:
        if (r14 != 0) goto L_0x00fd;
    L_0x00f6:
        r14 = "Invalid width and height options. Cannot resize.";
        r13.zzda(r14);	 Catch:{ all -> 0x0320 }
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x00fd:
        r14 = r13.zzdow;	 Catch:{ all -> 0x0320 }
        r14 = r14.getWindow();	 Catch:{ all -> 0x0320 }
        if (r14 == 0) goto L_0x0319;
    L_0x0105:
        r3 = r14.getDecorView();	 Catch:{ all -> 0x0320 }
        if (r3 != 0) goto L_0x010d;
    L_0x010b:
        goto L_0x0319;
    L_0x010d:
        r3 = r13.zzvl();	 Catch:{ all -> 0x0320 }
        if (r3 != 0) goto L_0x011a;
    L_0x0113:
        r14 = "Resize location out of screen or close button is not visible.";
        r13.zzda(r14);	 Catch:{ all -> 0x0320 }
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x011a:
        com.google.android.gms.internal.ads.zzwu.zzpv();	 Catch:{ all -> 0x0320 }
        r4 = r13.zzdow;	 Catch:{ all -> 0x0320 }
        r5 = r13.zzvt;	 Catch:{ all -> 0x0320 }
        r4 = com.google.android.gms.internal.ads.zzbat.zza(r4, r5);	 Catch:{ all -> 0x0320 }
        com.google.android.gms.internal.ads.zzwu.zzpv();	 Catch:{ all -> 0x0320 }
        r5 = r13.zzdow;	 Catch:{ all -> 0x0320 }
        r6 = r13.zzvu;	 Catch:{ all -> 0x0320 }
        r5 = com.google.android.gms.internal.ads.zzbat.zza(r5, r6);	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r6 = r6.getView();	 Catch:{ all -> 0x0320 }
        r6 = r6.getParent();	 Catch:{ all -> 0x0320 }
        if (r6 == 0) goto L_0x0312;
    L_0x013c:
        r7 = r6 instanceof android.view.ViewGroup;	 Catch:{ all -> 0x0320 }
        if (r7 == 0) goto L_0x0312;
    L_0x0140:
        r7 = r6;
        r7 = (android.view.ViewGroup) r7;	 Catch:{ all -> 0x0320 }
        r8 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r8 = r8.getView();	 Catch:{ all -> 0x0320 }
        r7.removeView(r8);	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdpp;	 Catch:{ all -> 0x0320 }
        if (r7 != 0) goto L_0x017f;
    L_0x0150:
        r6 = (android.view.ViewGroup) r6;	 Catch:{ all -> 0x0320 }
        r13.zzdpr = r6;	 Catch:{ all -> 0x0320 }
        com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r6 = r6.getView();	 Catch:{ all -> 0x0320 }
        r6 = com.google.android.gms.internal.ads.zzayh.zzt(r6);	 Catch:{ all -> 0x0320 }
        r7 = new android.widget.ImageView;	 Catch:{ all -> 0x0320 }
        r8 = r13.zzdow;	 Catch:{ all -> 0x0320 }
        r7.<init>(r8);	 Catch:{ all -> 0x0320 }
        r13.zzdpn = r7;	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdpn;	 Catch:{ all -> 0x0320 }
        r7.setImageBitmap(r6);	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r6 = r6.zzadj();	 Catch:{ all -> 0x0320 }
        r13.zzdpm = r6;	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdpr;	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdpn;	 Catch:{ all -> 0x0320 }
        r6.addView(r7);	 Catch:{ all -> 0x0320 }
        goto L_0x0184;
    L_0x017f:
        r6 = r13.zzdpp;	 Catch:{ all -> 0x0320 }
        r6.dismiss();	 Catch:{ all -> 0x0320 }
    L_0x0184:
        r6 = new android.widget.RelativeLayout;	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdow;	 Catch:{ all -> 0x0320 }
        r6.<init>(r7);	 Catch:{ all -> 0x0320 }
        r13.zzdpq = r6;	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdpq;	 Catch:{ all -> 0x0320 }
        r6.setBackgroundColor(r1);	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdpq;	 Catch:{ all -> 0x0320 }
        r7 = new android.view.ViewGroup$LayoutParams;	 Catch:{ all -> 0x0320 }
        r7.<init>(r4, r5);	 Catch:{ all -> 0x0320 }
        r6.setLayoutParams(r7);	 Catch:{ all -> 0x0320 }
        com.google.android.gms.ads.internal.zzbv.zzlf();	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdpq;	 Catch:{ all -> 0x0320 }
        r6 = com.google.android.gms.internal.ads.zzayh.zza(r6, r4, r5, r1);	 Catch:{ all -> 0x0320 }
        r13.zzdpp = r6;	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdpp;	 Catch:{ all -> 0x0320 }
        r6.setOutsideTouchable(r2);	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdpp;	 Catch:{ all -> 0x0320 }
        r6.setTouchable(r2);	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdpp;	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdph;	 Catch:{ all -> 0x0320 }
        r7 = r7 ^ r2;
        r6.setClippingEnabled(r7);	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdpq;	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r7 = r7.getView();	 Catch:{ all -> 0x0320 }
        r8 = -1;
        r6.addView(r7, r8, r8);	 Catch:{ all -> 0x0320 }
        r6 = new android.widget.LinearLayout;	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdow;	 Catch:{ all -> 0x0320 }
        r6.<init>(r7);	 Catch:{ all -> 0x0320 }
        r13.zzdpo = r6;	 Catch:{ all -> 0x0320 }
        r6 = new android.widget.RelativeLayout$LayoutParams;	 Catch:{ all -> 0x0320 }
        com.google.android.gms.internal.ads.zzwu.zzpv();	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdow;	 Catch:{ all -> 0x0320 }
        r9 = 50;
        r7 = com.google.android.gms.internal.ads.zzbat.zza(r7, r9);	 Catch:{ all -> 0x0320 }
        com.google.android.gms.internal.ads.zzwu.zzpv();	 Catch:{ all -> 0x0320 }
        r10 = r13.zzdow;	 Catch:{ all -> 0x0320 }
        r9 = com.google.android.gms.internal.ads.zzbat.zza(r10, r9);	 Catch:{ all -> 0x0320 }
        r6.<init>(r7, r9);	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdpg;	 Catch:{ all -> 0x0320 }
        r9 = r7.hashCode();	 Catch:{ all -> 0x0320 }
        switch(r9) {
            case -1364013995: goto L_0x0223;
            case -1012429441: goto L_0x0219;
            case -655373719: goto L_0x020f;
            case 1163912186: goto L_0x0205;
            case 1288627767: goto L_0x01fb;
            case 1755462605: goto L_0x01f1;
            default: goto L_0x01f0;
        };	 Catch:{ all -> 0x0320 }
    L_0x01f0:
        goto L_0x022d;
    L_0x01f1:
        r9 = "top-center";
        r7 = r7.equals(r9);	 Catch:{ all -> 0x0320 }
        if (r7 == 0) goto L_0x022d;
    L_0x01f9:
        r7 = r2;
        goto L_0x022e;
    L_0x01fb:
        r9 = "bottom-center";
        r7 = r7.equals(r9);	 Catch:{ all -> 0x0320 }
        if (r7 == 0) goto L_0x022d;
    L_0x0203:
        r7 = 4;
        goto L_0x022e;
    L_0x0205:
        r9 = "bottom-right";
        r7 = r7.equals(r9);	 Catch:{ all -> 0x0320 }
        if (r7 == 0) goto L_0x022d;
    L_0x020d:
        r7 = 5;
        goto L_0x022e;
    L_0x020f:
        r9 = "bottom-left";
        r7 = r7.equals(r9);	 Catch:{ all -> 0x0320 }
        if (r7 == 0) goto L_0x022d;
    L_0x0217:
        r7 = 3;
        goto L_0x022e;
    L_0x0219:
        r9 = "top-left";
        r7 = r7.equals(r9);	 Catch:{ all -> 0x0320 }
        if (r7 == 0) goto L_0x022d;
    L_0x0221:
        r7 = r1;
        goto L_0x022e;
    L_0x0223:
        r9 = "center";
        r7 = r7.equals(r9);	 Catch:{ all -> 0x0320 }
        if (r7 == 0) goto L_0x022d;
    L_0x022b:
        r7 = 2;
        goto L_0x022e;
    L_0x022d:
        r7 = r8;
    L_0x022e:
        r8 = 9;
        r9 = 14;
        r10 = 11;
        r11 = 12;
        r12 = 10;
        switch(r7) {
            case 0: goto L_0x0261;
            case 1: goto L_0x025a;
            case 2: goto L_0x0254;
            case 3: goto L_0x024d;
            case 4: goto L_0x0246;
            case 5: goto L_0x023f;
            default: goto L_0x023b;
        };	 Catch:{ all -> 0x0320 }
    L_0x023b:
        r6.addRule(r12);	 Catch:{ all -> 0x0320 }
        goto L_0x0268;
    L_0x023f:
        r6.addRule(r11);	 Catch:{ all -> 0x0320 }
        r6.addRule(r10);	 Catch:{ all -> 0x0320 }
        goto L_0x026b;
    L_0x0246:
        r6.addRule(r11);	 Catch:{ all -> 0x0320 }
        r6.addRule(r9);	 Catch:{ all -> 0x0320 }
        goto L_0x026b;
    L_0x024d:
        r6.addRule(r11);	 Catch:{ all -> 0x0320 }
        r6.addRule(r8);	 Catch:{ all -> 0x0320 }
        goto L_0x026b;
    L_0x0254:
        r7 = 13;
        r6.addRule(r7);	 Catch:{ all -> 0x0320 }
        goto L_0x026b;
    L_0x025a:
        r6.addRule(r12);	 Catch:{ all -> 0x0320 }
        r6.addRule(r9);	 Catch:{ all -> 0x0320 }
        goto L_0x026b;
    L_0x0261:
        r6.addRule(r12);	 Catch:{ all -> 0x0320 }
        r6.addRule(r8);	 Catch:{ all -> 0x0320 }
        goto L_0x026b;
    L_0x0268:
        r6.addRule(r10);	 Catch:{ all -> 0x0320 }
    L_0x026b:
        r7 = r13.zzdpo;	 Catch:{ all -> 0x0320 }
        r8 = new com.google.android.gms.internal.ads.zzaob;	 Catch:{ all -> 0x0320 }
        r8.<init>(r13);	 Catch:{ all -> 0x0320 }
        r7.setOnClickListener(r8);	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdpo;	 Catch:{ all -> 0x0320 }
        r8 = "Close button";
        r7.setContentDescription(r8);	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdpq;	 Catch:{ all -> 0x0320 }
        r8 = r13.zzdpo;	 Catch:{ all -> 0x0320 }
        r7.addView(r8, r6);	 Catch:{ all -> 0x0320 }
        r6 = r13.zzdpp;	 Catch:{ RuntimeException -> 0x02ca }
        r14 = r14.getDecorView();	 Catch:{ RuntimeException -> 0x02ca }
        com.google.android.gms.internal.ads.zzwu.zzpv();	 Catch:{ RuntimeException -> 0x02ca }
        r7 = r13.zzdow;	 Catch:{ RuntimeException -> 0x02ca }
        r8 = r3[r1];	 Catch:{ RuntimeException -> 0x02ca }
        r7 = com.google.android.gms.internal.ads.zzbat.zza(r7, r8);	 Catch:{ RuntimeException -> 0x02ca }
        com.google.android.gms.internal.ads.zzwu.zzpv();	 Catch:{ RuntimeException -> 0x02ca }
        r8 = r13.zzdow;	 Catch:{ RuntimeException -> 0x02ca }
        r9 = r3[r2];	 Catch:{ RuntimeException -> 0x02ca }
        r8 = com.google.android.gms.internal.ads.zzbat.zza(r8, r9);	 Catch:{ RuntimeException -> 0x02ca }
        r6.showAtLocation(r14, r1, r7, r8);	 Catch:{ RuntimeException -> 0x02ca }
        r14 = r3[r1];	 Catch:{ all -> 0x0320 }
        r6 = r3[r2];	 Catch:{ all -> 0x0320 }
        r7 = r13.zzdgd;	 Catch:{ all -> 0x0320 }
        if (r7 == 0) goto L_0x02b3;
    L_0x02aa:
        r7 = r13.zzdgd;	 Catch:{ all -> 0x0320 }
        r8 = r13.zzvt;	 Catch:{ all -> 0x0320 }
        r9 = r13.zzvu;	 Catch:{ all -> 0x0320 }
        r7.zza(r14, r6, r8, r9);	 Catch:{ all -> 0x0320 }
    L_0x02b3:
        r14 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r4 = com.google.android.gms.internal.ads.zzbht.zzr(r4, r5);	 Catch:{ all -> 0x0320 }
        r14.zza(r4);	 Catch:{ all -> 0x0320 }
        r14 = r3[r1];	 Catch:{ all -> 0x0320 }
        r1 = r3[r2];	 Catch:{ all -> 0x0320 }
        r13.zzh(r14, r1);	 Catch:{ all -> 0x0320 }
        r14 = "resized";
        r13.zzdc(r14);	 Catch:{ all -> 0x0320 }
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x02ca:
        r14 = move-exception;
        r1 = "Cannot show popup window: ";
        r14 = r14.getMessage();	 Catch:{ all -> 0x0320 }
        r14 = java.lang.String.valueOf(r14);	 Catch:{ all -> 0x0320 }
        r2 = r14.length();	 Catch:{ all -> 0x0320 }
        if (r2 == 0) goto L_0x02e0;
    L_0x02db:
        r14 = r1.concat(r14);	 Catch:{ all -> 0x0320 }
        goto L_0x02e5;
    L_0x02e0:
        r14 = new java.lang.String;	 Catch:{ all -> 0x0320 }
        r14.<init>(r1);	 Catch:{ all -> 0x0320 }
    L_0x02e5:
        r13.zzda(r14);	 Catch:{ all -> 0x0320 }
        r14 = r13.zzdpq;	 Catch:{ all -> 0x0320 }
        r1 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r1 = r1.getView();	 Catch:{ all -> 0x0320 }
        r14.removeView(r1);	 Catch:{ all -> 0x0320 }
        r14 = r13.zzdpr;	 Catch:{ all -> 0x0320 }
        if (r14 == 0) goto L_0x0310;
    L_0x02f7:
        r14 = r13.zzdpr;	 Catch:{ all -> 0x0320 }
        r1 = r13.zzdpn;	 Catch:{ all -> 0x0320 }
        r14.removeView(r1);	 Catch:{ all -> 0x0320 }
        r14 = r13.zzdpr;	 Catch:{ all -> 0x0320 }
        r1 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r1 = r1.getView();	 Catch:{ all -> 0x0320 }
        r14.addView(r1);	 Catch:{ all -> 0x0320 }
        r14 = r13.zzdin;	 Catch:{ all -> 0x0320 }
        r1 = r13.zzdpm;	 Catch:{ all -> 0x0320 }
        r14.zza(r1);	 Catch:{ all -> 0x0320 }
    L_0x0310:
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x0312:
        r14 = "Webview is detached, probably in the middle of a resize or expand.";
        r13.zzda(r14);	 Catch:{ all -> 0x0320 }
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x0319:
        r14 = "Activity context is not ready, cannot get window or decor view.";
        r13.zzda(r14);	 Catch:{ all -> 0x0320 }
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        return;
    L_0x0320:
        r14 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0320 }
        throw r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaoa.zzk(java.util.Map):void");
    }

    public final void zzx(boolean z) {
        synchronized (this.mLock) {
            if (this.zzdpp != null) {
                this.zzdpp.dismiss();
                this.zzdpq.removeView(this.zzdin.getView());
                if (this.zzdpr != null) {
                    this.zzdpr.removeView(this.zzdpn);
                    this.zzdpr.addView(this.zzdin.getView());
                    this.zzdin.zza(this.zzdpm);
                }
                if (z) {
                    zzdc(CBConstant.DEFAULT_VALUE);
                    if (this.zzdgd != null) {
                        this.zzdgd.zzjk();
                    }
                }
                this.zzdpp = null;
                this.zzdpq = null;
                this.zzdpr = null;
                this.zzdpo = null;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0126  */
    /* JADX WARNING: Missing block: B:50:0x0113, code skipped:
            if ((r5 + 50) <= r1[1]) goto L_0x0116;
     */
    private final int[] zzvl() {
        /*
        r9 = this;
        r0 = com.google.android.gms.ads.internal.zzbv.zzlf();
        r1 = r9.zzdow;
        r0 = r0.zzh(r1);
        r1 = com.google.android.gms.ads.internal.zzbv.zzlf();
        r2 = r9.zzdow;
        r1 = r1.zzi(r2);
        r2 = 0;
        r3 = r0[r2];
        r4 = 1;
        r0 = r0[r4];
        r5 = r9.zzvt;
        r6 = 2;
        r7 = 50;
        if (r5 < r7) goto L_0x011e;
    L_0x0021:
        r5 = r9.zzvt;
        if (r5 <= r3) goto L_0x0027;
    L_0x0025:
        goto L_0x011e;
    L_0x0027:
        r5 = r9.zzvu;
        if (r5 < r7) goto L_0x0118;
    L_0x002b:
        r5 = r9.zzvu;
        if (r5 <= r0) goto L_0x0031;
    L_0x002f:
        goto L_0x0118;
    L_0x0031:
        r5 = r9.zzvu;
        if (r5 != r0) goto L_0x0040;
    L_0x0035:
        r0 = r9.zzvt;
        if (r0 != r3) goto L_0x0040;
    L_0x0039:
        r0 = "Cannot resize to a full-screen ad.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r0);
        goto L_0x0123;
    L_0x0040:
        r0 = r9.zzdph;
        if (r0 == 0) goto L_0x0116;
    L_0x0044:
        r0 = r9.zzdpg;
        r5 = -1;
        r8 = r0.hashCode();
        switch(r8) {
            case -1364013995: goto L_0x0081;
            case -1012429441: goto L_0x0077;
            case -655373719: goto L_0x006d;
            case 1163912186: goto L_0x0063;
            case 1288627767: goto L_0x0059;
            case 1755462605: goto L_0x004f;
            default: goto L_0x004e;
        };
    L_0x004e:
        goto L_0x008b;
    L_0x004f:
        r8 = "top-center";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x008b;
    L_0x0057:
        r0 = r4;
        goto L_0x008c;
    L_0x0059:
        r8 = "bottom-center";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x008b;
    L_0x0061:
        r0 = 4;
        goto L_0x008c;
    L_0x0063:
        r8 = "bottom-right";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x008b;
    L_0x006b:
        r0 = 5;
        goto L_0x008c;
    L_0x006d:
        r8 = "bottom-left";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x008b;
    L_0x0075:
        r0 = 3;
        goto L_0x008c;
    L_0x0077:
        r8 = "top-left";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x008b;
    L_0x007f:
        r0 = r2;
        goto L_0x008c;
    L_0x0081:
        r8 = "center";
        r0 = r0.equals(r8);
        if (r0 == 0) goto L_0x008b;
    L_0x0089:
        r0 = r6;
        goto L_0x008c;
    L_0x008b:
        r0 = r5;
    L_0x008c:
        switch(r0) {
            case 0: goto L_0x00fd;
            case 1: goto L_0x00ec;
            case 2: goto L_0x00d5;
            case 3: goto L_0x00c6;
            case 4: goto L_0x00b1;
            case 5: goto L_0x009e;
            default: goto L_0x008f;
        };
    L_0x008f:
        r0 = r9.zzdpi;
        r5 = r9.zzdpk;
        r0 = r0 + r5;
        r5 = r9.zzvt;
        r0 = r0 + r5;
        r0 = r0 - r7;
        r5 = r9.zzdpj;
        r8 = r9.zzdpl;
        r5 = r5 + r8;
        goto L_0x0107;
    L_0x009e:
        r0 = r9.zzdpi;
        r5 = r9.zzdpk;
        r0 = r0 + r5;
        r5 = r9.zzvt;
        r0 = r0 + r5;
        r0 = r0 - r7;
        r5 = r9.zzdpj;
        r8 = r9.zzdpl;
        r5 = r5 + r8;
        r8 = r9.zzvu;
        r5 = r5 + r8;
        r5 = r5 - r7;
        goto L_0x0107;
    L_0x00b1:
        r0 = r9.zzdpi;
        r5 = r9.zzdpk;
        r0 = r0 + r5;
        r5 = r9.zzvt;
        r5 = r5 / r6;
        r0 = r0 + r5;
        r0 = r0 + -25;
        r5 = r9.zzdpj;
        r8 = r9.zzdpl;
        r5 = r5 + r8;
        r8 = r9.zzvu;
        r5 = r5 + r8;
        r5 = r5 - r7;
        goto L_0x0107;
    L_0x00c6:
        r0 = r9.zzdpi;
        r5 = r9.zzdpk;
        r0 = r0 + r5;
        r5 = r9.zzdpj;
        r8 = r9.zzdpl;
        r5 = r5 + r8;
        r8 = r9.zzvu;
        r5 = r5 + r8;
        r5 = r5 - r7;
        goto L_0x0107;
    L_0x00d5:
        r0 = r9.zzdpi;
        r5 = r9.zzdpk;
        r0 = r0 + r5;
        r5 = r9.zzvt;
        r5 = r5 / r6;
        r0 = r0 + r5;
        r0 = r0 + -25;
        r5 = r9.zzdpj;
        r8 = r9.zzdpl;
        r5 = r5 + r8;
        r8 = r9.zzvu;
        r8 = r8 / r6;
        r5 = r5 + r8;
        r5 = r5 + -25;
        goto L_0x0107;
    L_0x00ec:
        r0 = r9.zzdpi;
        r5 = r9.zzdpk;
        r0 = r0 + r5;
        r5 = r9.zzvt;
        r5 = r5 / r6;
        r0 = r0 + r5;
        r0 = r0 + -25;
        r5 = r9.zzdpj;
        r8 = r9.zzdpl;
        r5 = r5 + r8;
        goto L_0x0107;
    L_0x00fd:
        r0 = r9.zzdpi;
        r5 = r9.zzdpk;
        r0 = r0 + r5;
        r5 = r9.zzdpj;
        r8 = r9.zzdpl;
        r5 = r5 + r8;
    L_0x0107:
        if (r0 < 0) goto L_0x0123;
    L_0x0109:
        r0 = r0 + r7;
        if (r0 > r3) goto L_0x0123;
    L_0x010c:
        r0 = r1[r2];
        if (r5 < r0) goto L_0x0123;
    L_0x0110:
        r5 = r5 + r7;
        r0 = r1[r4];
        if (r5 <= r0) goto L_0x0116;
    L_0x0115:
        goto L_0x0123;
    L_0x0116:
        r0 = r4;
        goto L_0x0124;
    L_0x0118:
        r0 = "Height is too small or too large.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r0);
        goto L_0x0123;
    L_0x011e:
        r0 = "Width is too small or too large.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r0);
    L_0x0123:
        r0 = r2;
    L_0x0124:
        if (r0 != 0) goto L_0x0128;
    L_0x0126:
        r0 = 0;
        return r0;
    L_0x0128:
        r0 = r9.zzdph;
        if (r0 == 0) goto L_0x013d;
    L_0x012c:
        r0 = new int[r6];
        r1 = r9.zzdpi;
        r3 = r9.zzdpk;
        r1 = r1 + r3;
        r0[r2] = r1;
        r1 = r9.zzdpj;
        r2 = r9.zzdpl;
        r1 = r1 + r2;
        r0[r4] = r1;
        return r0;
    L_0x013d:
        r0 = com.google.android.gms.ads.internal.zzbv.zzlf();
        r1 = r9.zzdow;
        r0 = r0.zzh(r1);
        r1 = com.google.android.gms.ads.internal.zzbv.zzlf();
        r3 = r9.zzdow;
        r1 = r1.zzi(r3);
        r0 = r0[r2];
        r3 = r9.zzdpi;
        r5 = r9.zzdpk;
        r3 = r3 + r5;
        r5 = r9.zzdpj;
        r7 = r9.zzdpl;
        r5 = r5 + r7;
        if (r3 >= 0) goto L_0x0161;
    L_0x015f:
        r0 = r2;
        goto L_0x016b;
    L_0x0161:
        r7 = r9.zzvt;
        r7 = r7 + r3;
        if (r7 <= r0) goto L_0x016a;
    L_0x0166:
        r3 = r9.zzvt;
        r0 = r0 - r3;
        goto L_0x016b;
    L_0x016a:
        r0 = r3;
    L_0x016b:
        r3 = r1[r2];
        if (r5 >= r3) goto L_0x0172;
    L_0x016f:
        r5 = r1[r2];
        goto L_0x017f;
    L_0x0172:
        r3 = r9.zzvu;
        r3 = r3 + r5;
        r7 = r1[r4];
        if (r3 <= r7) goto L_0x017f;
    L_0x0179:
        r1 = r1[r4];
        r3 = r9.zzvu;
        r5 = r1 - r3;
    L_0x017f:
        r1 = new int[r6];
        r1[r2] = r0;
        r1[r4] = r5;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaoa.zzvl():int[]");
    }

    public final void zza(int i, int i2, boolean z) {
        synchronized (this.mLock) {
            this.zzdpi = i;
            this.zzdpj = i2;
            if (this.zzdpp != null && z) {
                int[] zzvl = zzvl();
                if (zzvl != null) {
                    PopupWindow popupWindow = this.zzdpp;
                    zzwu.zzpv();
                    int zza = zzbat.zza(this.zzdow, zzvl[0]);
                    zzwu.zzpv();
                    popupWindow.update(zza, zzbat.zza(this.zzdow, zzvl[1]), this.zzdpp.getWidth(), this.zzdpp.getHeight());
                    zzh(zzvl[0], zzvl[1]);
                } else {
                    zzx(true);
                }
            }
        }
    }

    private final void zzh(int i, int i2) {
        zzb(i, i2 - zzbv.zzlf().zzi(this.zzdow)[0], this.zzvt, this.zzvu);
    }

    public final boolean zzvm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdpp != null;
        }
        return z;
    }

    public final void zzi(int i, int i2) {
        this.zzdpi = i;
        this.zzdpj = i2;
    }
}
