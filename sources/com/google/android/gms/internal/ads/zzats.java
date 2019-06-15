package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;

final class zzats implements Runnable {
    private final /* synthetic */ zzasi zzeax;
    private final /* synthetic */ zzast zzeay;
    private final /* synthetic */ zzatq zzeaz;

    zzats(zzatq zzatq, zzasi zzasi, zzast zzast) {
        this.zzeaz = zzatq;
        this.zzeax = zzasi;
        this.zzeay = zzast;
    }

    public final void run() {
        zzasm zzb;
        try {
            zzb = this.zzeaz.zzb(this.zzeax);
        } catch (Exception e) {
            zzbv.zzlj().zza(e, "AdRequestServiceImpl.loadAdAsync");
            zzbbd.zzc("Could not fetch ad response due to an Exception.", e);
            zzb = null;
        }
        if (zzb == null) {
            zzb = new zzasm(0);
        }
        try {
            this.zzeay.zza(zzb);
        } catch (RemoteException e2) {
            zzbbd.zzc("Fail to forward ad response.", e2);
        }
    }
}
