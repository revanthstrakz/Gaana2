package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.MuteThisAdReason;

@zzark
public final class zzyo implements MuteThisAdReason {
    private final String zzclw;
    private zzyl zzclx;

    public zzyo(zzyl zzyl) {
        String description;
        this.zzclx = zzyl;
        try {
            description = zzyl.getDescription();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            description = null;
        }
        this.zzclw = description;
    }

    public final String getDescription() {
        return this.zzclw;
    }

    public final zzyl zzqe() {
        return this.zzclx;
    }
}
