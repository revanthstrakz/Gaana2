package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@zzark
public final class zzacr extends zzadg implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener, zzacw {
    @VisibleForTesting
    private static final String[] zzdcy = new String[]{NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    @Nullable
    @VisibleForTesting
    private FrameLayout zzbld;
    @Nullable
    @VisibleForTesting
    private zzacd zzdbj;
    private final FrameLayout zzdcz;
    private View zzdda;
    @VisibleForTesting
    private Map<String, WeakReference<View>> zzddb = Collections.synchronizedMap(new HashMap());
    @Nullable
    @VisibleForTesting
    private View zzddc;
    @VisibleForTesting
    private boolean zzddd = false;
    @VisibleForTesting
    private Point zzdde = new Point();
    @VisibleForTesting
    private Point zzddf = new Point();
    @VisibleForTesting
    private WeakReference<zzsc> zzddg = new WeakReference(null);

    @TargetApi(21)
    public zzacr(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzdcz = frameLayout;
        this.zzbld = frameLayout2;
        zzbv.zzme();
        zzbct.zza(this.zzdcz, (OnGlobalLayoutListener) this);
        zzbv.zzme();
        zzbct.zza(this.zzdcz, (OnScrollChangedListener) this);
        this.zzdcz.setOnTouchListener(this);
        this.zzdcz.setOnClickListener(this);
        if (frameLayout2 != null && PlatformVersion.isAtLeastLollipop()) {
            frameLayout2.setElevation(Float.MAX_VALUE);
        }
        zzaan.initialize(this.zzdcz.getContext());
    }

    /* JADX WARNING: Missing block: B:16:0x003d, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:18:0x003f, code skipped:
            return;
     */
    public final void zzb(java.lang.String r4, com.google.android.gms.dynamic.IObjectWrapper r5) {
        /*
        r3 = this;
        r5 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r5);
        r5 = (android.view.View) r5;
        r0 = r3.mLock;
        monitor-enter(r0);
        r1 = r3.zzddb;	 Catch:{ all -> 0x0040 }
        if (r1 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r0);	 Catch:{ all -> 0x0040 }
        return;
    L_0x000f:
        if (r5 != 0) goto L_0x0017;
    L_0x0011:
        r5 = r3.zzddb;	 Catch:{ all -> 0x0040 }
        r5.remove(r4);	 Catch:{ all -> 0x0040 }
        goto L_0x003c;
    L_0x0017:
        r1 = r3.zzddb;	 Catch:{ all -> 0x0040 }
        r2 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x0040 }
        r2.<init>(r5);	 Catch:{ all -> 0x0040 }
        r1.put(r4, r2);	 Catch:{ all -> 0x0040 }
        r1 = "1098";
        r1 = r1.equals(r4);	 Catch:{ all -> 0x0040 }
        if (r1 != 0) goto L_0x003e;
    L_0x0029:
        r1 = "3011";
        r4 = r1.equals(r4);	 Catch:{ all -> 0x0040 }
        if (r4 == 0) goto L_0x0032;
    L_0x0031:
        goto L_0x003e;
    L_0x0032:
        r5.setOnTouchListener(r3);	 Catch:{ all -> 0x0040 }
        r4 = 1;
        r5.setClickable(r4);	 Catch:{ all -> 0x0040 }
        r5.setOnClickListener(r3);	 Catch:{ all -> 0x0040 }
    L_0x003c:
        monitor-exit(r0);	 Catch:{ all -> 0x0040 }
        return;
    L_0x003e:
        monitor-exit(r0);	 Catch:{ all -> 0x0040 }
        return;
    L_0x0040:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0040 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacr.zzb(java.lang.String, com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    public final IObjectWrapper zzbm(String str) {
        synchronized (this.mLock) {
            Object obj = null;
            if (this.zzddb == null) {
                return null;
            }
            WeakReference weakReference = (WeakReference) this.zzddb.get(str);
            if (weakReference != null) {
                obj = (View) weakReference.get();
            }
            IObjectWrapper wrap = ObjectWrapper.wrap(obj);
            return wrap;
        }
    }

    private final void zzm(@Nullable View view) {
        if (this.zzdbj != null) {
            zzacd zzso;
            if (this.zzdbj instanceof zzacc) {
                zzso = ((zzacc) this.zzdbj).zzso();
            } else {
                zzso = this.zzdbj;
            }
            if (zzso != null) {
                zzso.zzm(view);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x00df A:{Catch:{ Exception -> 0x0157 }} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x012a A:{Catch:{ Exception -> 0x0157 }} */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x014b A:{Catch:{ Exception -> 0x0157 }} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x017f A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x01d4 A:{Catch:{ Exception -> 0x0157 }} */
    /* JADX WARNING: Missing block: B:121:0x0216, code skipped:
            return;
     */
    public final void zza(com.google.android.gms.dynamic.IObjectWrapper r12) {
        /*
        r11 = this;
        r0 = r11.mLock;
        monitor-enter(r0);
        r1 = 0;
        r11.zzm(r1);	 Catch:{ all -> 0x021a }
        r12 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r12);	 Catch:{ all -> 0x021a }
        r2 = r12 instanceof com.google.android.gms.internal.ads.zzach;	 Catch:{ all -> 0x021a }
        if (r2 != 0) goto L_0x0016;
    L_0x000f:
        r12 = "Not an instance of native engine. This is most likely a transient error";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r12);	 Catch:{ all -> 0x021a }
        monitor-exit(r0);	 Catch:{ all -> 0x021a }
        return;
    L_0x0016:
        r2 = 1;
        r11.zzddd = r2;	 Catch:{ all -> 0x021a }
        r12 = (com.google.android.gms.internal.ads.zzach) r12;	 Catch:{ all -> 0x021a }
        r3 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x0028;
    L_0x001f:
        r3 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        r4 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r5 = r11.zzddb;	 Catch:{ all -> 0x021a }
        r3.zzb(r4, r5);	 Catch:{ all -> 0x021a }
    L_0x0028:
        r3 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        r3 = r3 instanceof com.google.android.gms.internal.ads.zzach;	 Catch:{ all -> 0x021a }
        r9 = 0;
        if (r3 == 0) goto L_0x0063;
    L_0x002f:
        r3 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        r3 = (com.google.android.gms.internal.ads.zzach) r3;	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x0063;
    L_0x0035:
        r4 = r3.getContext();	 Catch:{ all -> 0x021a }
        if (r4 == 0) goto L_0x0063;
    L_0x003b:
        r4 = com.google.android.gms.ads.internal.zzbv.zzmf();	 Catch:{ all -> 0x021a }
        r5 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r5 = r5.getContext();	 Catch:{ all -> 0x021a }
        r4 = r4.zzv(r5);	 Catch:{ all -> 0x021a }
        if (r4 == 0) goto L_0x0063;
    L_0x004b:
        r3 = r3.zzsu();	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x0054;
    L_0x0051:
        r3.zzai(r9);	 Catch:{ all -> 0x021a }
    L_0x0054:
        r4 = r11.zzddg;	 Catch:{ all -> 0x021a }
        r4 = r4.get();	 Catch:{ all -> 0x021a }
        r4 = (com.google.android.gms.internal.ads.zzsc) r4;	 Catch:{ all -> 0x021a }
        if (r4 == 0) goto L_0x0063;
    L_0x005e:
        if (r3 == 0) goto L_0x0063;
    L_0x0060:
        r4.zzb(r3);	 Catch:{ all -> 0x021a }
    L_0x0063:
        r3 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        r3 = r3 instanceof com.google.android.gms.internal.ads.zzacc;	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x007b;
    L_0x0069:
        r3 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        r3 = (com.google.android.gms.internal.ads.zzacc) r3;	 Catch:{ all -> 0x021a }
        r3 = r3.zzsn();	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x007b;
    L_0x0073:
        r3 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        r3 = (com.google.android.gms.internal.ads.zzacc) r3;	 Catch:{ all -> 0x021a }
        r3.zzc(r12);	 Catch:{ all -> 0x021a }
        goto L_0x0087;
    L_0x007b:
        r11.zzdbj = r12;	 Catch:{ all -> 0x021a }
        r3 = r12 instanceof com.google.android.gms.internal.ads.zzacc;	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x0087;
    L_0x0081:
        r3 = r12;
        r3 = (com.google.android.gms.internal.ads.zzacc) r3;	 Catch:{ all -> 0x021a }
        r3.zzc(r1);	 Catch:{ all -> 0x021a }
    L_0x0087:
        r3 = r11.zzbld;	 Catch:{ all -> 0x021a }
        if (r3 != 0) goto L_0x008d;
    L_0x008b:
        monitor-exit(r0);	 Catch:{ all -> 0x021a }
        return;
    L_0x008d:
        r3 = r11.zzbld;	 Catch:{ all -> 0x021a }
        r3.setClickable(r9);	 Catch:{ all -> 0x021a }
        r3 = r11.zzbld;	 Catch:{ all -> 0x021a }
        r3.removeAllViews();	 Catch:{ all -> 0x021a }
        r3 = r12.zzsj();	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x00cd;
    L_0x009d:
        r4 = r11.zzddb;	 Catch:{ all -> 0x021a }
        if (r4 == 0) goto L_0x00c5;
    L_0x00a1:
        r4 = 2;
        r5 = new java.lang.String[r4];	 Catch:{ all -> 0x021a }
        r6 = "1098";
        r5[r9] = r6;	 Catch:{ all -> 0x021a }
        r6 = "3011";
        r5[r2] = r6;	 Catch:{ all -> 0x021a }
        r6 = r9;
    L_0x00ad:
        if (r6 >= r4) goto L_0x00c5;
    L_0x00af:
        r7 = r5[r6];	 Catch:{ all -> 0x021a }
        r8 = r11.zzddb;	 Catch:{ all -> 0x021a }
        r7 = r8.get(r7);	 Catch:{ all -> 0x021a }
        r7 = (java.lang.ref.WeakReference) r7;	 Catch:{ all -> 0x021a }
        if (r7 == 0) goto L_0x00c2;
    L_0x00bb:
        r4 = r7.get();	 Catch:{ all -> 0x021a }
        r4 = (android.view.View) r4;	 Catch:{ all -> 0x021a }
        goto L_0x00c6;
    L_0x00c2:
        r6 = r6 + 1;
        goto L_0x00ad;
    L_0x00c5:
        r4 = r1;
    L_0x00c6:
        r5 = r4 instanceof android.view.ViewGroup;	 Catch:{ all -> 0x021a }
        if (r5 == 0) goto L_0x00cd;
    L_0x00ca:
        r4 = (android.view.ViewGroup) r4;	 Catch:{ all -> 0x021a }
        goto L_0x00ce;
    L_0x00cd:
        r4 = r1;
    L_0x00ce:
        if (r3 == 0) goto L_0x00d3;
    L_0x00d0:
        if (r4 == 0) goto L_0x00d3;
    L_0x00d2:
        goto L_0x00d4;
    L_0x00d3:
        r2 = r9;
    L_0x00d4:
        r3 = r12.zza(r11, r2);	 Catch:{ all -> 0x021a }
        r11.zzddc = r3;	 Catch:{ all -> 0x021a }
        r3 = r11.zzddc;	 Catch:{ all -> 0x021a }
        r10 = -1;
        if (r3 == 0) goto L_0x011b;
    L_0x00df:
        r3 = r11.zzddb;	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x00f1;
    L_0x00e3:
        r3 = r11.zzddb;	 Catch:{ all -> 0x021a }
        r5 = "1007";
        r6 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x021a }
        r7 = r11.zzddc;	 Catch:{ all -> 0x021a }
        r6.<init>(r7);	 Catch:{ all -> 0x021a }
        r3.put(r5, r6);	 Catch:{ all -> 0x021a }
    L_0x00f1:
        if (r2 == 0) goto L_0x00fc;
    L_0x00f3:
        r4.removeAllViews();	 Catch:{ all -> 0x021a }
        r2 = r11.zzddc;	 Catch:{ all -> 0x021a }
        r4.addView(r2);	 Catch:{ all -> 0x021a }
        goto L_0x011b;
    L_0x00fc:
        r2 = r12.getContext();	 Catch:{ all -> 0x021a }
        r3 = new com.google.android.gms.ads.formats.AdChoicesView;	 Catch:{ all -> 0x021a }
        r3.<init>(r2);	 Catch:{ all -> 0x021a }
        r2 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x021a }
        r2.<init>(r10, r10);	 Catch:{ all -> 0x021a }
        r3.setLayoutParams(r2);	 Catch:{ all -> 0x021a }
        r2 = r11.zzddc;	 Catch:{ all -> 0x021a }
        r3.addView(r2);	 Catch:{ all -> 0x021a }
        r2 = r11.zzbld;	 Catch:{ all -> 0x021a }
        if (r2 == 0) goto L_0x011b;
    L_0x0116:
        r2 = r11.zzbld;	 Catch:{ all -> 0x021a }
        r2.addView(r3);	 Catch:{ all -> 0x021a }
    L_0x011b:
        r4 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r5 = r11.zzddb;	 Catch:{ all -> 0x021a }
        r6 = 0;
        r3 = r12;
        r7 = r11;
        r8 = r11;
        r3.zza(r4, r5, r6, r7, r8);	 Catch:{ all -> 0x021a }
        r2 = r11.zzdda;	 Catch:{ all -> 0x021a }
        if (r2 != 0) goto L_0x0141;
    L_0x012a:
        r2 = new android.view.View;	 Catch:{ all -> 0x021a }
        r3 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r3 = r3.getContext();	 Catch:{ all -> 0x021a }
        r2.<init>(r3);	 Catch:{ all -> 0x021a }
        r11.zzdda = r2;	 Catch:{ all -> 0x021a }
        r2 = r11.zzdda;	 Catch:{ all -> 0x021a }
        r3 = new android.widget.FrameLayout$LayoutParams;	 Catch:{ all -> 0x021a }
        r3.<init>(r10, r9);	 Catch:{ all -> 0x021a }
        r2.setLayoutParams(r3);	 Catch:{ all -> 0x021a }
    L_0x0141:
        r2 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r3 = r11.zzdda;	 Catch:{ all -> 0x021a }
        r3 = r3.getParent();	 Catch:{ all -> 0x021a }
        if (r2 == r3) goto L_0x0152;
    L_0x014b:
        r2 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r3 = r11.zzdda;	 Catch:{ all -> 0x021a }
        r2.addView(r3);	 Catch:{ all -> 0x021a }
    L_0x0152:
        r2 = r12.zzsp();	 Catch:{ Exception -> 0x0157 }
        goto L_0x016d;
    L_0x0157:
        r2 = move-exception;
        com.google.android.gms.ads.internal.zzbv.zzlh();	 Catch:{ all -> 0x021a }
        r3 = com.google.android.gms.internal.ads.zzayp.zzaaa();	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x0167;
    L_0x0161:
        r2 = "Privileged processes cannot create HTML overlays.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r2);	 Catch:{ all -> 0x021a }
        goto L_0x016c;
    L_0x0167:
        r3 = "Error obtaining overlay.";
        com.google.android.gms.internal.ads.zzbbd.zzb(r3, r2);	 Catch:{ all -> 0x021a }
    L_0x016c:
        r2 = r1;
    L_0x016d:
        if (r2 == 0) goto L_0x017c;
    L_0x016f:
        r3 = r11.zzbld;	 Catch:{ all -> 0x021a }
        if (r3 == 0) goto L_0x017c;
    L_0x0173:
        r3 = r11.zzbld;	 Catch:{ all -> 0x021a }
        r2 = r2.getView();	 Catch:{ all -> 0x021a }
        r3.addView(r2);	 Catch:{ all -> 0x021a }
    L_0x017c:
        r2 = r11.mLock;	 Catch:{ all -> 0x021a }
        monitor-enter(r2);	 Catch:{ all -> 0x021a }
        r3 = r11.zzddb;	 Catch:{ all -> 0x0217 }
        r12.zzf(r3);	 Catch:{ all -> 0x0217 }
        r3 = r11.zzddb;	 Catch:{ all -> 0x0217 }
        if (r3 == 0) goto L_0x01a3;
    L_0x0188:
        r3 = zzdcy;	 Catch:{ all -> 0x0217 }
        r4 = r3.length;	 Catch:{ all -> 0x0217 }
    L_0x018b:
        if (r9 >= r4) goto L_0x01a3;
    L_0x018d:
        r5 = r3[r9];	 Catch:{ all -> 0x0217 }
        r6 = r11.zzddb;	 Catch:{ all -> 0x0217 }
        r5 = r6.get(r5);	 Catch:{ all -> 0x0217 }
        r5 = (java.lang.ref.WeakReference) r5;	 Catch:{ all -> 0x0217 }
        if (r5 == 0) goto L_0x01a0;
    L_0x0199:
        r1 = r5.get();	 Catch:{ all -> 0x0217 }
        r1 = (android.view.View) r1;	 Catch:{ all -> 0x0217 }
        goto L_0x01a3;
    L_0x01a0:
        r9 = r9 + 1;
        goto L_0x018b;
    L_0x01a3:
        r3 = r1 instanceof android.widget.FrameLayout;	 Catch:{ all -> 0x0217 }
        if (r3 != 0) goto L_0x01a9;
    L_0x01a7:
        monitor-exit(r2);	 Catch:{ all -> 0x0217 }
        goto L_0x01ba;
    L_0x01a9:
        r3 = new com.google.android.gms.internal.ads.zzacs;	 Catch:{ all -> 0x0217 }
        r3.<init>(r11, r1);	 Catch:{ all -> 0x0217 }
        r4 = r12 instanceof com.google.android.gms.internal.ads.zzacc;	 Catch:{ all -> 0x0217 }
        if (r4 == 0) goto L_0x01b6;
    L_0x01b2:
        r12.zzb(r1, r3);	 Catch:{ all -> 0x0217 }
        goto L_0x01b9;
    L_0x01b6:
        r12.zza(r1, r3);	 Catch:{ all -> 0x0217 }
    L_0x01b9:
        monitor-exit(r2);	 Catch:{ all -> 0x0217 }
    L_0x01ba:
        r12.zza(r11);	 Catch:{ all -> 0x021a }
        r1 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r12.zzi(r1);	 Catch:{ all -> 0x021a }
        r12 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r11.zzm(r12);	 Catch:{ all -> 0x021a }
        r12 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        r1 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r12.zzj(r1);	 Catch:{ all -> 0x021a }
        r12 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        r12 = r12 instanceof com.google.android.gms.internal.ads.zzach;	 Catch:{ all -> 0x021a }
        if (r12 == 0) goto L_0x0215;
    L_0x01d4:
        r12 = r11.zzdbj;	 Catch:{ all -> 0x021a }
        r12 = (com.google.android.gms.internal.ads.zzach) r12;	 Catch:{ all -> 0x021a }
        if (r12 == 0) goto L_0x0215;
    L_0x01da:
        r1 = r12.getContext();	 Catch:{ all -> 0x021a }
        if (r1 == 0) goto L_0x0215;
    L_0x01e0:
        r1 = com.google.android.gms.ads.internal.zzbv.zzmf();	 Catch:{ all -> 0x021a }
        r2 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r2 = r2.getContext();	 Catch:{ all -> 0x021a }
        r1 = r1.zzv(r2);	 Catch:{ all -> 0x021a }
        if (r1 == 0) goto L_0x0215;
    L_0x01f0:
        r1 = r11.zzddg;	 Catch:{ all -> 0x021a }
        r1 = r1.get();	 Catch:{ all -> 0x021a }
        r1 = (com.google.android.gms.internal.ads.zzsc) r1;	 Catch:{ all -> 0x021a }
        if (r1 != 0) goto L_0x020e;
    L_0x01fa:
        r1 = new com.google.android.gms.internal.ads.zzsc;	 Catch:{ all -> 0x021a }
        r2 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r2 = r2.getContext();	 Catch:{ all -> 0x021a }
        r3 = r11.zzdcz;	 Catch:{ all -> 0x021a }
        r1.<init>(r2, r3);	 Catch:{ all -> 0x021a }
        r2 = new java.lang.ref.WeakReference;	 Catch:{ all -> 0x021a }
        r2.<init>(r1);	 Catch:{ all -> 0x021a }
        r11.zzddg = r2;	 Catch:{ all -> 0x021a }
    L_0x020e:
        r12 = r12.zzsu();	 Catch:{ all -> 0x021a }
        r1.zza(r12);	 Catch:{ all -> 0x021a }
    L_0x0215:
        monitor-exit(r0);	 Catch:{ all -> 0x021a }
        return;
    L_0x0217:
        r12 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0217 }
        throw r12;	 Catch:{ all -> 0x021a }
    L_0x021a:
        r12 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x021a }
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacr.zza(com.google.android.gms.dynamic.IObjectWrapper):void");
    }

    /* JADX WARNING: Missing block: B:19:0x0093, code skipped:
            return;
     */
    public final void onClick(android.view.View r10) {
        /*
        r9 = this;
        r0 = r9.mLock;
        monitor-enter(r0);
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0094 }
        if (r1 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r0);	 Catch:{ all -> 0x0094 }
        return;
    L_0x0009:
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0094 }
        r1.cancelUnconfirmedClick();	 Catch:{ all -> 0x0094 }
        r5 = new android.os.Bundle;	 Catch:{ all -> 0x0094 }
        r5.<init>();	 Catch:{ all -> 0x0094 }
        r1 = "x";
        r2 = r9.zzdde;	 Catch:{ all -> 0x0094 }
        r2 = r2.x;	 Catch:{ all -> 0x0094 }
        r2 = r9.zzck(r2);	 Catch:{ all -> 0x0094 }
        r2 = (float) r2;	 Catch:{ all -> 0x0094 }
        r5.putFloat(r1, r2);	 Catch:{ all -> 0x0094 }
        r1 = "y";
        r2 = r9.zzdde;	 Catch:{ all -> 0x0094 }
        r2 = r2.y;	 Catch:{ all -> 0x0094 }
        r2 = r9.zzck(r2);	 Catch:{ all -> 0x0094 }
        r2 = (float) r2;	 Catch:{ all -> 0x0094 }
        r5.putFloat(r1, r2);	 Catch:{ all -> 0x0094 }
        r1 = "start_x";
        r2 = r9.zzddf;	 Catch:{ all -> 0x0094 }
        r2 = r2.x;	 Catch:{ all -> 0x0094 }
        r2 = r9.zzck(r2);	 Catch:{ all -> 0x0094 }
        r2 = (float) r2;	 Catch:{ all -> 0x0094 }
        r5.putFloat(r1, r2);	 Catch:{ all -> 0x0094 }
        r1 = "start_y";
        r2 = r9.zzddf;	 Catch:{ all -> 0x0094 }
        r2 = r2.y;	 Catch:{ all -> 0x0094 }
        r2 = r9.zzck(r2);	 Catch:{ all -> 0x0094 }
        r2 = (float) r2;	 Catch:{ all -> 0x0094 }
        r5.putFloat(r1, r2);	 Catch:{ all -> 0x0094 }
        r1 = r9.zzddc;	 Catch:{ all -> 0x0094 }
        if (r1 == 0) goto L_0x0089;
    L_0x004f:
        r1 = r9.zzddc;	 Catch:{ all -> 0x0094 }
        r1 = r1.equals(r10);	 Catch:{ all -> 0x0094 }
        if (r1 == 0) goto L_0x0089;
    L_0x0057:
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0094 }
        r1 = r1 instanceof com.google.android.gms.internal.ads.zzacc;	 Catch:{ all -> 0x0094 }
        if (r1 == 0) goto L_0x007b;
    L_0x005d:
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0094 }
        r1 = (com.google.android.gms.internal.ads.zzacc) r1;	 Catch:{ all -> 0x0094 }
        r1 = r1.zzso();	 Catch:{ all -> 0x0094 }
        if (r1 == 0) goto L_0x0092;
    L_0x0067:
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0094 }
        r1 = (com.google.android.gms.internal.ads.zzacc) r1;	 Catch:{ all -> 0x0094 }
        r2 = r1.zzso();	 Catch:{ all -> 0x0094 }
        r4 = "1007";
        r6 = r9.zzddb;	 Catch:{ all -> 0x0094 }
        r7 = r9.zzdcz;	 Catch:{ all -> 0x0094 }
        r8 = 0;
        r3 = r10;
        r2.zza(r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0094 }
        goto L_0x0092;
    L_0x007b:
        r2 = r9.zzdbj;	 Catch:{ all -> 0x0094 }
        r4 = "1007";
        r6 = r9.zzddb;	 Catch:{ all -> 0x0094 }
        r7 = r9.zzdcz;	 Catch:{ all -> 0x0094 }
        r8 = 0;
        r3 = r10;
        r2.zza(r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0094 }
        goto L_0x0092;
    L_0x0089:
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0094 }
        r2 = r9.zzddb;	 Catch:{ all -> 0x0094 }
        r3 = r9.zzdcz;	 Catch:{ all -> 0x0094 }
        r1.zza(r10, r2, r5, r3);	 Catch:{ all -> 0x0094 }
    L_0x0092:
        monitor-exit(r0);	 Catch:{ all -> 0x0094 }
        return;
    L_0x0094:
        r10 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0094 }
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacr.onClick(android.view.View):void");
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                this.zzdbj.zzc(this.zzdcz, this.zzddb);
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                this.zzdbj.zzc(this.zzdcz, this.zzddb);
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzdbj == null) {
                return false;
            }
            int[] iArr = new int[2];
            this.zzdcz.getLocationOnScreen(iArr);
            Point point = new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
            this.zzdde = point;
            if (motionEvent.getAction() == 0) {
                this.zzddf = point;
            }
            motionEvent = MotionEvent.obtain(motionEvent);
            motionEvent.setLocation((float) point.x, (float) point.y);
            this.zzdbj.zzd(motionEvent);
            motionEvent.recycle();
            return false;
        }
    }

    public final void destroy() {
        synchronized (this.mLock) {
            if (this.zzbld != null) {
                this.zzbld.removeAllViews();
            }
            this.zzbld = null;
            this.zzddb = null;
            this.zzddc = null;
            this.zzdbj = null;
            this.zzdde = null;
            this.zzddf = null;
            this.zzddg = null;
            this.zzdda = null;
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, int i) {
        if (zzbv.zzmf().zzv(this.zzdcz.getContext()) && this.zzddg != null) {
            zzsc zzsc = (zzsc) this.zzddg.get();
            if (zzsc != null) {
                zzsc.zznh();
            }
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        this.zzdbj.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final synchronized Map<String, WeakReference<View>> zzsv() {
        return this.zzddb;
    }

    public final View zzsw() {
        return this.zzdcz;
    }

    @VisibleForTesting
    private final int zzck(int i) {
        zzwu.zzpv();
        return zzbat.zzb(this.zzdbj.getContext(), i);
    }
}
