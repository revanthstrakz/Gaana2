package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzev<T> implements zzff<T> {
    private final zzeq zzmj;
    private final boolean zzmk;
    private final zzfx<?, ?> zzmt;
    private final zzcz<?> zzmu;

    private zzev(zzfx<?, ?> zzfx, zzcz<?> zzcz, zzeq zzeq) {
        this.zzmt = zzfx;
        this.zzmk = zzcz.zze(zzeq);
        this.zzmu = zzcz;
        this.zzmj = zzeq;
    }

    static <T> zzev<T> zza(zzfx<?, ?> zzfx, zzcz<?> zzcz, zzeq zzeq) {
        return new zzev(zzfx, zzcz, zzeq);
    }

    public final boolean equals(T t, T t2) {
        if (this.zzmt.zzo(t).equals(this.zzmt.zzo(t2))) {
            return this.zzmk ? this.zzmu.zzc(t).equals(this.zzmu.zzc(t2)) : true;
        } else {
            return false;
        }
    }

    public final int hashCode(T t) {
        int hashCode = this.zzmt.zzo(t).hashCode();
        return this.zzmk ? (hashCode * 53) + this.zzmu.zzc(t).hashCode() : hashCode;
    }

    public final void zzc(T t, T t2) {
        zzfh.zza(this.zzmt, (Object) t, (Object) t2);
        if (this.zzmk) {
            zzfh.zza(this.zzmu, (Object) t, (Object) t2);
        }
    }

    public final void zza(T t, zzgr zzgr) throws IOException {
        Iterator it = this.zzmu.zzc(t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzde zzde = (zzde) entry.getKey();
            if (zzde.zzbm() != zzgq.MESSAGE || zzde.zzbn() || zzde.zzbo()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzdw) {
                zzgr.zza(zzde.zzbk(), ((zzdw) entry).zzcf().zzaf());
            } else {
                zzgr.zza(zzde.zzbk(), entry.getValue());
            }
        }
        zzfx zzfx = this.zzmt;
        zzfx.zzc(zzfx.zzo(t), zzgr);
    }

    public final void zze(T t) {
        this.zzmt.zze(t);
        this.zzmu.zze((Object) t);
    }

    public final boolean zzm(T t) {
        return this.zzmu.zzc(t).isInitialized();
    }

    public final int zzl(T t) {
        zzfx zzfx = this.zzmt;
        int zzp = 0 + zzfx.zzp(zzfx.zzo(t));
        return this.zzmk ? zzp + this.zzmu.zzc(t).zzbj() : zzp;
    }
}
