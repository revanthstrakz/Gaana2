package com.google.android.gms.internal.ads;

public final class zzaji extends zzbcr<zzajr> {
    private final Object mLock = new Object();
    private final zzajm zzdjp;
    private boolean zzdjq;

    public zzaji(zzajm zzajm) {
        this.zzdjp = zzajm;
    }

    public final void release() {
        synchronized (this.mLock) {
            if (this.zzdjq) {
                return;
            }
            this.zzdjq = true;
            zza(new zzajj(this), new zzbcp());
            zza(new zzajk(this), new zzajl(this));
        }
    }
}
