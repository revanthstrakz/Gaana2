package com.google.android.gms.internal.ads;

public abstract class zzop {
    private zzoq zzbfj;

    public abstract zzor zza(zzga[] zzgaArr, zzma zzma) throws zzff;

    public abstract void zzd(Object obj);

    public final void zza(zzoq zzoq) {
        this.zzbfj = zzoq;
    }

    /* Access modifiers changed, original: protected|final */
    public final void invalidate() {
        if (this.zzbfj != null) {
            this.zzbfj.zzbu();
        }
    }
}
