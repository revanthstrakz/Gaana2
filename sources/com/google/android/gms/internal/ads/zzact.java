package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@zzark
public final class zzact extends zzadl implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener, zzacw {
    @VisibleForTesting
    static final String[] zzdcy = new String[]{NativeAppInstallAd.ASSET_MEDIA_VIDEO, NativeContentAd.ASSET_MEDIA_VIDEO, UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO};
    private final Object mLock = new Object();
    @Nullable
    @VisibleForTesting
    private zzacd zzdbj;
    @Nullable
    @VisibleForTesting
    private View zzddc;
    @VisibleForTesting
    private Point zzdde = new Point();
    @VisibleForTesting
    private Point zzddf = new Point();
    @Nullable
    @VisibleForTesting
    private WeakReference<zzsc> zzddg = new WeakReference(null);
    private final WeakReference<View> zzddj;
    private final Map<String, WeakReference<View>> zzddk = new HashMap();
    private final Map<String, WeakReference<View>> zzddl = new HashMap();
    private final Map<String, WeakReference<View>> zzddm = new HashMap();

    public zzact(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        View view2;
        zzbv.zzme();
        zzbct.zza(view, (OnGlobalLayoutListener) this);
        zzbv.zzme();
        zzbct.zza(view, (OnScrollChangedListener) this);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        this.zzddj = new WeakReference(view);
        for (Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            view2 = (View) entry.getValue();
            if (view2 != null) {
                this.zzddk.put(str, new WeakReference(view2));
                if (!(NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str) || UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW.equals(str))) {
                    view2.setOnTouchListener(this);
                    view2.setClickable(true);
                    view2.setOnClickListener(this);
                }
            }
        }
        this.zzddm.putAll(this.zzddk);
        for (Entry entry2 : hashMap2.entrySet()) {
            view2 = (View) entry2.getValue();
            if (view2 != null) {
                this.zzddl.put((String) entry2.getKey(), new WeakReference(view2));
                view2.setOnTouchListener(this);
            }
        }
        this.zzddm.putAll(this.zzddl);
        zzaan.initialize(view.getContext());
    }

    public final void unregisterNativeAd() {
        synchronized (this.mLock) {
            this.zzddc = null;
            this.zzdbj = null;
            this.zzdde = null;
            this.zzddf = null;
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            ViewGroup viewGroup = null;
            zzm(null);
            Object unwrap = ObjectWrapper.unwrap(iObjectWrapper);
            if (unwrap instanceof zzach) {
                zzach zzach = (zzach) unwrap;
                if (zzach.zzsl()) {
                    int i;
                    View view;
                    View view2 = (View) this.zzddj.get();
                    if (!(this.zzdbj == null || view2 == null)) {
                        this.zzdbj.zzb(view2, this.zzddm);
                    }
                    synchronized (this.mLock) {
                        i = 0;
                        if (this.zzdbj instanceof zzach) {
                            zzach zzach2 = (zzach) this.zzdbj;
                            View view3 = (View) this.zzddj.get();
                            if (!(zzach2 == null || zzach2.getContext() == null || view3 == null || !zzbv.zzmf().zzv(view3.getContext()))) {
                                zzawv zzsu = zzach2.zzsu();
                                if (zzsu != null) {
                                    zzsu.zzai(false);
                                }
                                zzsc zzsc = (zzsc) this.zzddg.get();
                                if (!(zzsc == null || zzsu == null)) {
                                    zzsc.zzb(zzsu);
                                }
                            }
                        }
                    }
                    if ((this.zzdbj instanceof zzacc) && ((zzacc) this.zzdbj).zzsn()) {
                        ((zzacc) this.zzdbj).zzc(zzach);
                    } else {
                        this.zzdbj = zzach;
                        if (zzach instanceof zzacc) {
                            ((zzacc) zzach).zzc(null);
                        }
                    }
                    String[] strArr = new String[]{NativeAd.ASSET_ADCHOICES_CONTAINER_VIEW, UnifiedNativeAdAssetNames.ASSET_ADCHOICES_CONTAINER_VIEW};
                    while (i < 2) {
                        WeakReference weakReference = (WeakReference) this.zzddm.get(strArr[i]);
                        if (weakReference != null) {
                            view = (View) weakReference.get();
                            break;
                        }
                        i++;
                    }
                    view = null;
                    if (view == null) {
                        zzbbd.zzeo("Ad choices asset view is not provided.");
                    } else {
                        if (view instanceof ViewGroup) {
                            viewGroup = (ViewGroup) view;
                        }
                        if (viewGroup != null) {
                            this.zzddc = zzach.zza((OnClickListener) this, true);
                            if (this.zzddc != null) {
                                this.zzddm.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzddc));
                                this.zzddk.put(NativeContentAd.ASSET_ATTRIBUTION_ICON_IMAGE, new WeakReference(this.zzddc));
                                viewGroup.removeAllViews();
                                viewGroup.addView(this.zzddc);
                            }
                        }
                    }
                    zzach.zza(view2, this.zzddk, this.zzddl, (OnTouchListener) this, (OnClickListener) this);
                    zzayh.zzelc.post(new zzacu(this, zzach));
                    zzm(view2);
                    this.zzdbj.zzj(view2);
                    synchronized (this.mLock) {
                        if (this.zzdbj instanceof zzach) {
                            zzach zzach3 = (zzach) this.zzdbj;
                            view = (View) this.zzddj.get();
                            if (!(zzach3 == null || zzach3.getContext() == null || view == null || !zzbv.zzmf().zzv(view.getContext()))) {
                                zzsc zzsc2 = (zzsc) this.zzddg.get();
                                if (zzsc2 == null) {
                                    zzsc2 = new zzsc(view.getContext(), view);
                                    this.zzddg = new WeakReference(zzsc2);
                                }
                                zzsc2.zza(zzach3.zzsu());
                            }
                        }
                    }
                    return;
                }
                zzbbd.e("Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account.");
                return;
            }
            zzbbd.zzeo("Not an instance of native engine. This is most likely a transient error");
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzdbj.setClickConfirmingView((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    private final boolean zza(String[] strArr) {
        for (Object obj : strArr) {
            if (this.zzddk.get(obj) != null) {
                return true;
            }
        }
        for (Object obj2 : strArr) {
            if (this.zzddl.get(obj2) != null) {
                return false;
            }
        }
        return false;
    }

    /* JADX WARNING: Missing block: B:20:0x003a, code skipped:
            return;
     */
    private final void zza(com.google.android.gms.internal.ads.zzach r7) {
        /*
        r6 = this;
        r0 = r6.mLock;
        monitor-enter(r0);
        r1 = zzdcy;	 Catch:{ all -> 0x003b }
        r2 = r1.length;	 Catch:{ all -> 0x003b }
        r3 = 0;
    L_0x0007:
        if (r3 >= r2) goto L_0x001f;
    L_0x0009:
        r4 = r1[r3];	 Catch:{ all -> 0x003b }
        r5 = r6.zzddm;	 Catch:{ all -> 0x003b }
        r4 = r5.get(r4);	 Catch:{ all -> 0x003b }
        r4 = (java.lang.ref.WeakReference) r4;	 Catch:{ all -> 0x003b }
        if (r4 == 0) goto L_0x001c;
    L_0x0015:
        r1 = r4.get();	 Catch:{ all -> 0x003b }
        r1 = (android.view.View) r1;	 Catch:{ all -> 0x003b }
        goto L_0x0020;
    L_0x001c:
        r3 = r3 + 1;
        goto L_0x0007;
    L_0x001f:
        r1 = 0;
    L_0x0020:
        r2 = r1 instanceof android.widget.FrameLayout;	 Catch:{ all -> 0x003b }
        if (r2 != 0) goto L_0x0029;
    L_0x0024:
        r7.zzsr();	 Catch:{ all -> 0x003b }
        monitor-exit(r0);	 Catch:{ all -> 0x003b }
        return;
    L_0x0029:
        r2 = new com.google.android.gms.internal.ads.zzacv;	 Catch:{ all -> 0x003b }
        r2.<init>(r6, r1);	 Catch:{ all -> 0x003b }
        r3 = r7 instanceof com.google.android.gms.internal.ads.zzacc;	 Catch:{ all -> 0x003b }
        if (r3 == 0) goto L_0x0036;
    L_0x0032:
        r7.zzb(r1, r2);	 Catch:{ all -> 0x003b }
        goto L_0x0039;
    L_0x0036:
        r7.zza(r1, r2);	 Catch:{ all -> 0x003b }
    L_0x0039:
        monitor-exit(r0);	 Catch:{ all -> 0x003b }
        return;
    L_0x003b:
        r7 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x003b }
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzact.zza(com.google.android.gms.internal.ads.zzach):void");
    }

    private final void zzm(@Nullable View view) {
        synchronized (this.mLock) {
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
    }

    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                View view = (View) this.zzddj.get();
                if (view != null) {
                    this.zzdbj.zzc(view, this.zzddm);
                }
            }
        }
    }

    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                View view = (View) this.zzddj.get();
                if (view != null) {
                    this.zzdbj.zzc(view, this.zzddm);
                }
            }
        }
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzdbj == null) {
                return false;
            }
            View view2 = (View) this.zzddj.get();
            if (view2 == null) {
                return false;
            }
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
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

    public final synchronized Map<String, WeakReference<View>> zzsv() {
        return this.zzddm;
    }

    @Nullable
    public final View zzsw() {
        return (View) this.zzddj.get();
    }

    /* JADX WARNING: Missing block: B:23:0x0095, code skipped:
            return;
     */
    public final void onClick(android.view.View r10) {
        /*
        r9 = this;
        r0 = r9.mLock;
        monitor-enter(r0);
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0096 }
        if (r1 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        return;
    L_0x0009:
        r1 = r9.zzddj;	 Catch:{ all -> 0x0096 }
        r1 = r1.get();	 Catch:{ all -> 0x0096 }
        r7 = r1;
        r7 = (android.view.View) r7;	 Catch:{ all -> 0x0096 }
        if (r7 != 0) goto L_0x0016;
    L_0x0014:
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        return;
    L_0x0016:
        r5 = new android.os.Bundle;	 Catch:{ all -> 0x0096 }
        r5.<init>();	 Catch:{ all -> 0x0096 }
        r1 = "x";
        r2 = r9.zzdde;	 Catch:{ all -> 0x0096 }
        r2 = r2.x;	 Catch:{ all -> 0x0096 }
        r2 = r9.zzck(r2);	 Catch:{ all -> 0x0096 }
        r2 = (float) r2;	 Catch:{ all -> 0x0096 }
        r5.putFloat(r1, r2);	 Catch:{ all -> 0x0096 }
        r1 = "y";
        r2 = r9.zzdde;	 Catch:{ all -> 0x0096 }
        r2 = r2.y;	 Catch:{ all -> 0x0096 }
        r2 = r9.zzck(r2);	 Catch:{ all -> 0x0096 }
        r2 = (float) r2;	 Catch:{ all -> 0x0096 }
        r5.putFloat(r1, r2);	 Catch:{ all -> 0x0096 }
        r1 = "start_x";
        r2 = r9.zzddf;	 Catch:{ all -> 0x0096 }
        r2 = r2.x;	 Catch:{ all -> 0x0096 }
        r2 = r9.zzck(r2);	 Catch:{ all -> 0x0096 }
        r2 = (float) r2;	 Catch:{ all -> 0x0096 }
        r5.putFloat(r1, r2);	 Catch:{ all -> 0x0096 }
        r1 = "start_y";
        r2 = r9.zzddf;	 Catch:{ all -> 0x0096 }
        r2 = r2.y;	 Catch:{ all -> 0x0096 }
        r2 = r9.zzck(r2);	 Catch:{ all -> 0x0096 }
        r2 = (float) r2;	 Catch:{ all -> 0x0096 }
        r5.putFloat(r1, r2);	 Catch:{ all -> 0x0096 }
        r1 = r9.zzddc;	 Catch:{ all -> 0x0096 }
        if (r1 == 0) goto L_0x008d;
    L_0x0057:
        r1 = r9.zzddc;	 Catch:{ all -> 0x0096 }
        r1 = r1.equals(r10);	 Catch:{ all -> 0x0096 }
        if (r1 == 0) goto L_0x008d;
    L_0x005f:
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0096 }
        r1 = r1 instanceof com.google.android.gms.internal.ads.zzacc;	 Catch:{ all -> 0x0096 }
        if (r1 == 0) goto L_0x0081;
    L_0x0065:
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0096 }
        r1 = (com.google.android.gms.internal.ads.zzacc) r1;	 Catch:{ all -> 0x0096 }
        r1 = r1.zzso();	 Catch:{ all -> 0x0096 }
        if (r1 == 0) goto L_0x0094;
    L_0x006f:
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0096 }
        r1 = (com.google.android.gms.internal.ads.zzacc) r1;	 Catch:{ all -> 0x0096 }
        r2 = r1.zzso();	 Catch:{ all -> 0x0096 }
        r4 = "1007";
        r6 = r9.zzddm;	 Catch:{ all -> 0x0096 }
        r8 = 0;
        r3 = r10;
        r2.zza(r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0096 }
        goto L_0x0094;
    L_0x0081:
        r2 = r9.zzdbj;	 Catch:{ all -> 0x0096 }
        r4 = "1007";
        r6 = r9.zzddm;	 Catch:{ all -> 0x0096 }
        r8 = 0;
        r3 = r10;
        r2.zza(r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0096 }
        goto L_0x0094;
    L_0x008d:
        r1 = r9.zzdbj;	 Catch:{ all -> 0x0096 }
        r2 = r9.zzddm;	 Catch:{ all -> 0x0096 }
        r1.zza(r10, r2, r5, r7);	 Catch:{ all -> 0x0096 }
    L_0x0094:
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        return;
    L_0x0096:
        r10 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzact.onClick(android.view.View):void");
    }

    @VisibleForTesting
    private final int zzck(int i) {
        synchronized (this.mLock) {
            zzwu.zzpv();
            i = zzbat.zzb(this.zzdbj.getContext(), i);
        }
        return i;
    }
}
