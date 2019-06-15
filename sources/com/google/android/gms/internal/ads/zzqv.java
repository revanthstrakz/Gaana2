package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.view.Surface;

public final class zzqv {
    private final Handler handler;
    private final zzqu zzbko;

    public zzqv(Handler handler, zzqu zzqu) {
        this.handler = zzqu != null ? (Handler) zzpo.checkNotNull(handler) : null;
        this.zzbko = zzqu;
    }

    public final void zzc(zzhn zzhn) {
        if (this.zzbko != null) {
            this.handler.post(new zzqw(this, zzhn));
        }
    }

    public final void zzb(String str, long j, long j2) {
        if (this.zzbko != null) {
            this.handler.post(new zzqx(this, str, j, j2));
        }
    }

    public final void zzd(zzfs zzfs) {
        if (this.zzbko != null) {
            this.handler.post(new zzqy(this, zzfs));
        }
    }

    public final void zzi(int i, long j) {
        if (this.zzbko != null) {
            this.handler.post(new zzqz(this, i, j));
        }
    }

    public final void zzb(int i, int i2, int i3, float f) {
        if (this.zzbko != null) {
            this.handler.post(new zzra(this, i, i2, i3, f));
        }
    }

    public final void zzb(Surface surface) {
        if (this.zzbko != null) {
            this.handler.post(new zzrb(this, surface));
        }
    }

    public final void zzd(zzhn zzhn) {
        if (this.zzbko != null) {
            this.handler.post(new zzrc(this, zzhn));
        }
    }
}
