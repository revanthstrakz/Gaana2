package com.google.android.gms.internal.cast;

final class zzdy implements zzec {
    private final /* synthetic */ zzec zzaap;
    private final /* synthetic */ zzdx zzaaq;

    zzdy(zzdx zzdx, zzec zzec) {
        this.zzaaq = zzdx;
        this.zzaap = zzec;
    }

    public final void zza(long j, int i, Object obj) {
        this.zzaaq.zzzu = null;
        if (this.zzaap != null) {
            this.zzaap.zza(j, i, obj);
        }
    }

    public final void zzd(long j) {
        if (this.zzaap != null) {
            this.zzaap.zzd(j);
        }
    }
}
