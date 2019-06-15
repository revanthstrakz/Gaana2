package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

final class zzacj {
    private String mId;
    private final WeakReference<zzbgg> zzdcm;

    public zzacj(zzbgg zzbgg) {
        this.zzdcm = new WeakReference(zzbgg);
    }

    public final void zza(zzaqp zzaqp) {
        zzaqp.zza("/loadHtml", new zzack(this, zzaqp));
        zzaqp.zza("/showOverlay", new zzacm(this, zzaqp));
        zzaqp.zza("/hideOverlay", new zzacn(this, zzaqp));
        zzbgg zzbgg = (zzbgg) this.zzdcm.get();
        if (zzbgg != null) {
            zzbgg.zza("/sendMessageToSdk", new zzaco(this, zzaqp));
        }
    }
}
