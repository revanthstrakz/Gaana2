package com.google.android.gms.internal.ads;

public class zzae extends Exception {
    private long zzad;
    private final zzp zzbj;

    public zzae() {
        this.zzbj = null;
    }

    public zzae(zzp zzp) {
        this.zzbj = zzp;
    }

    public zzae(String str) {
        super(str);
        this.zzbj = null;
    }

    public zzae(Throwable th) {
        super(th);
        this.zzbj = null;
    }

    /* Access modifiers changed, original: final */
    public final void zza(long j) {
        this.zzad = j;
    }
}
