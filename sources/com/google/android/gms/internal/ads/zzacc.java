package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@zzark
public final class zzacc extends zzach {
    private final Object mLock;
    @Nullable
    private zzals zzdbv;
    @Nullable
    private zzalv zzdbw;
    @Nullable
    private zzaly zzdbx;
    private final zzace zzdby;
    @Nullable
    private zzacd zzdbz;
    private boolean zzdca;
    private boolean zzdcb;

    public zzacc(Context context, zzace zzace, zzcu zzcu, zzals zzals, zzacf zzacf) {
        this(context, zzace, zzcu, zzacf);
        this.zzdbv = zzals;
    }

    @Nullable
    public final zzbgg zzsp() {
        return null;
    }

    public final void zzsq() {
    }

    public final void zzsr() {
    }

    public zzacc(Context context, zzace zzace, zzcu zzcu, zzalv zzalv, zzacf zzacf) {
        this(context, zzace, zzcu, zzacf);
        this.zzdbw = zzalv;
    }

    public zzacc(Context context, zzace zzace, zzcu zzcu, zzaly zzaly, zzacf zzacf) {
        this(context, zzace, zzcu, zzacf);
        this.zzdbx = zzaly;
    }

    private zzacc(Context context, zzace zzace, zzcu zzcu, zzacf zzacf) {
        super(context, zzace, null, zzcu, null, zzacf, null, null);
        this.zzdca = false;
        this.zzdcb = false;
        this.mLock = new Object();
        this.zzdby = zzace;
    }

    @Nullable
    public final View zza(OnClickListener onClickListener, boolean z) {
        synchronized (this.mLock) {
            View zza;
            if (this.zzdbz != null) {
                zza = this.zzdbz.zza(onClickListener, z);
                return zza;
            }
            IObjectWrapper zzvb;
            try {
                if (this.zzdbx != null) {
                    zzvb = this.zzdbx.zzvb();
                } else if (this.zzdbv != null) {
                    zzvb = this.zzdbv.zzvb();
                } else {
                    if (this.zzdbw != null) {
                        zzvb = this.zzdbw.zzvb();
                    }
                    zzvb = null;
                }
            } catch (RemoteException e) {
                zzbbd.zzc("Failed to call getAdChoicesContent", e);
            }
            if (zzvb != null) {
                zza = (View) ObjectWrapper.unwrap(zzvb);
                return zza;
            }
            return null;
        }
    }

    public final boolean zzsj() {
        synchronized (this.mLock) {
            boolean zzsj;
            if (this.zzdbz != null) {
                zzsj = this.zzdbz.zzsj();
                return zzsj;
            }
            zzsj = this.zzdby.zzjo();
            return zzsj;
        }
    }

    public final boolean zzsk() {
        synchronized (this.mLock) {
            boolean zzsk;
            if (this.zzdbz != null) {
                zzsk = this.zzdbz.zzsk();
                return zzsk;
            }
            zzsk = this.zzdby.zzjq();
            return zzsk;
        }
    }

    public final boolean zzsl() {
        synchronized (this.mLock) {
            boolean zzsl;
            if (this.zzdbz != null) {
                zzsl = this.zzdbz.zzsl();
                return zzsl;
            }
            zzsl = this.zzdby.zzjp();
            return zzsl;
        }
    }

    public final void zza(zzaet zzaet) {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                this.zzdbz.zza(zzaet);
            }
        }
    }

    public final void cancelUnconfirmedClick() {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                this.zzdbz.cancelUnconfirmedClick();
            }
        }
    }

    public final void setClickConfirmingView(View view) {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                this.zzdbz.setClickConfirmingView(view);
            }
        }
    }

    public final void zza(View view, Map<String, WeakReference<View>> map) {
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        synchronized (this.mLock) {
            this.zzdcg = true;
            if (this.zzdbz != null) {
                this.zzdbz.zza(view, (Map) map);
                this.zzdby.recordImpression();
            } else {
                try {
                    if (this.zzdbx != null && !this.zzdbx.getOverrideImpressionRecording()) {
                        this.zzdbx.recordImpression();
                        this.zzdby.recordImpression();
                    } else if (this.zzdbv != null && !this.zzdbv.getOverrideImpressionRecording()) {
                        this.zzdbv.recordImpression();
                        this.zzdby.recordImpression();
                    } else if (!(this.zzdbw == null || this.zzdbw.getOverrideImpressionRecording())) {
                        this.zzdbw.recordImpression();
                        this.zzdby.recordImpression();
                    }
                } catch (RemoteException e) {
                    zzbbd.zzc("Failed to call recordImpression", e);
                }
            }
        }
    }

    public final void zzsm() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on main UI thread.");
        synchronized (this.mLock) {
            this.zzdch = true;
            if (this.zzdbz != null) {
                this.zzdbz.zzsm();
            }
        }
    }

    public final void zzd(MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (this.zzdbz != null) {
                this.zzdbz.zzd(motionEvent);
            }
        }
    }

    /* JADX WARNING: Missing block: B:14:0x0027, code skipped:
            return;
     */
    public final void zza(android.view.View r3, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r4, android.os.Bundle r5, android.view.View r6) {
        /*
        r2 = this;
        r0 = "performClick must be called on the main UI thread.";
        com.google.android.gms.common.internal.Preconditions.checkMainThread(r0);
        r0 = r2.mLock;
        monitor-enter(r0);
        r1 = r2.zzdcb;	 Catch:{ all -> 0x0028 }
        if (r1 == 0) goto L_0x0014;
    L_0x000c:
        r1 = r2.zzsk();	 Catch:{ all -> 0x0028 }
        if (r1 == 0) goto L_0x0014;
    L_0x0012:
        monitor-exit(r0);	 Catch:{ all -> 0x0028 }
        return;
    L_0x0014:
        r1 = r2.zzdbz;	 Catch:{ all -> 0x0028 }
        if (r1 == 0) goto L_0x0023;
    L_0x0018:
        r1 = r2.zzdbz;	 Catch:{ all -> 0x0028 }
        r1.zza(r3, r4, r5, r6);	 Catch:{ all -> 0x0028 }
        r3 = r2.zzdby;	 Catch:{ all -> 0x0028 }
        r3.onAdClicked();	 Catch:{ all -> 0x0028 }
        goto L_0x0026;
    L_0x0023:
        r2.zzl(r3);	 Catch:{ all -> 0x0028 }
    L_0x0026:
        monitor-exit(r0);	 Catch:{ all -> 0x0028 }
        return;
    L_0x0028:
        r3 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0028 }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacc.zza(android.view.View, java.util.Map, android.os.Bundle, android.view.View):void");
    }

    public final void zzsi() {
        this.zzdcb = true;
        if (this.zzdbz != null) {
            this.zzdbz.zzsi();
        }
    }

    /* JADX WARNING: Missing block: B:23:0x004a, code skipped:
            return;
     */
    public final void recordCustomClickGesture() {
        /*
        r2 = this;
        r0 = "recordCustomClickGesture must be called on the main UI thread.";
        com.google.android.gms.common.internal.Preconditions.checkMainThread(r0);
        r0 = r2.mLock;
        monitor-enter(r0);
        r1 = r2.zzdbz;	 Catch:{ all -> 0x004b }
        if (r1 == 0) goto L_0x0020;
    L_0x000c:
        r1 = r2.zzdcb;	 Catch:{ all -> 0x004b }
        if (r1 == 0) goto L_0x0015;
    L_0x0010:
        r1 = r2.zzdbz;	 Catch:{ all -> 0x004b }
        r1.zzsi();	 Catch:{ all -> 0x004b }
    L_0x0015:
        r1 = r2.zzdbz;	 Catch:{ all -> 0x004b }
        r1.recordCustomClickGesture();	 Catch:{ all -> 0x004b }
        r1 = r2.zzdby;	 Catch:{ all -> 0x004b }
        r1.onAdClicked();	 Catch:{ all -> 0x004b }
        goto L_0x0049;
    L_0x0020:
        r1 = r2.zzdcb;	 Catch:{ all -> 0x004b }
        if (r1 != 0) goto L_0x002b;
    L_0x0024:
        r1 = "Custom click reporting for 3p ads failed. enableCustomClickGesture is not set.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);	 Catch:{ all -> 0x004b }
        monitor-exit(r0);	 Catch:{ all -> 0x004b }
        return;
    L_0x002b:
        r1 = r2.zzsk();	 Catch:{ all -> 0x004b }
        if (r1 != 0) goto L_0x0038;
    L_0x0031:
        r1 = "Custom click reporting for 3p ads failed. Ad unit id not whitelisted.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r1);	 Catch:{ all -> 0x004b }
        monitor-exit(r0);	 Catch:{ all -> 0x004b }
        return;
    L_0x0038:
        r1 = r2.zzst();	 Catch:{ all -> 0x004b }
        if (r1 == 0) goto L_0x0049;
    L_0x003e:
        r1 = r2.zzst();	 Catch:{ all -> 0x004b }
        r1 = r1.zzsw();	 Catch:{ all -> 0x004b }
        r2.zzl(r1);	 Catch:{ all -> 0x004b }
    L_0x0049:
        monitor-exit(r0);	 Catch:{ all -> 0x004b }
        return;
    L_0x004b:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x004b }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzacc.recordCustomClickGesture():void");
    }

    private final void zzl(View view) {
        try {
            if (this.zzdbx != null && !this.zzdbx.getOverrideClickHandling()) {
                this.zzdbx.zzk(ObjectWrapper.wrap(view));
                this.zzdby.onAdClicked();
            } else if (this.zzdbv == null || this.zzdbv.getOverrideClickHandling()) {
                if (!(this.zzdbw == null || this.zzdbw.getOverrideClickHandling())) {
                    this.zzdbw.zzk(ObjectWrapper.wrap(view));
                    this.zzdby.onAdClicked();
                }
            } else {
                this.zzdbv.zzk(ObjectWrapper.wrap(view));
                this.zzdby.onAdClicked();
            }
        } catch (RemoteException e) {
            zzbbd.zzc("Failed to call performClick", e);
        }
    }

    public final void zza(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        synchronized (this.mLock) {
            this.zzdca = true;
            HashMap zzb = zzb(map);
            HashMap zzb2 = zzb(map2);
            try {
                if (this.zzdbx != null) {
                    this.zzdbx.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                } else if (this.zzdbv != null) {
                    this.zzdbv.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzdbv.zzl(ObjectWrapper.wrap(view));
                } else if (this.zzdbw != null) {
                    this.zzdbw.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(zzb), ObjectWrapper.wrap(zzb2));
                    this.zzdbw.zzl(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e) {
                zzbbd.zzc("Failed to call prepareAd", e);
            }
            this.zzdca = false;
        }
    }

    private static HashMap<String, View> zzb(Map<String, WeakReference<View>> map) {
        HashMap hashMap = new HashMap();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Entry entry : map.entrySet()) {
                View view = (View) ((WeakReference) entry.getValue()).get();
                if (view != null) {
                    hashMap.put((String) entry.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    public final void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            try {
                if (this.zzdbx != null) {
                    this.zzdbx.zzm(ObjectWrapper.wrap(view));
                } else if (this.zzdbv != null) {
                    this.zzdbv.zzm(ObjectWrapper.wrap(view));
                } else if (this.zzdbw != null) {
                    this.zzdbw.zzm(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e) {
                zzbbd.zzc("Failed to call untrackView", e);
            }
        }
    }

    public final boolean zzsn() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdca;
        }
        return z;
    }

    public final void zzc(@Nullable zzacd zzacd) {
        synchronized (this.mLock) {
            this.zzdbz = zzacd;
        }
    }

    public final zzacd zzso() {
        zzacd zzacd;
        synchronized (this.mLock) {
            zzacd = this.zzdbz;
        }
        return zzacd;
    }

    public final void zzjl() {
        if (this.zzdbz != null) {
            this.zzdbz.zzjl();
        }
    }

    public final void zzjm() {
        if (this.zzdbz != null) {
            this.zzdbz.zzjm();
        }
    }
}
