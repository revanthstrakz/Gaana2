package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzk extends zzyc<zzk> {
    private static volatile zzk[] zzor;
    public int key;
    public int value;

    public static zzk[] zzh() {
        if (zzor == null) {
            synchronized (zzyg.zzcfc) {
                if (zzor == null) {
                    zzor = new zzk[0];
                }
            }
        }
        return zzor;
    }

    public zzk() {
        this.key = 0;
        this.value = 0;
        this.zzcet = null;
        this.zzcfd = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzk)) {
            return false;
        }
        zzk zzk = (zzk) obj;
        if (this.key != zzk.key || this.value != zzk.value) {
            return false;
        }
        if (this.zzcet == null || this.zzcet.isEmpty()) {
            return zzk.zzcet == null || zzk.zzcet.isEmpty();
        } else {
            return this.zzcet.equals(zzk.zzcet);
        }
    }

    public final int hashCode() {
        int hashCode = (((((527 + getClass().getName().hashCode()) * 31) + this.key) * 31) + this.value) * 31;
        int hashCode2 = (this.zzcet == null || this.zzcet.isEmpty()) ? 0 : this.zzcet.hashCode();
        return hashCode + hashCode2;
    }

    public final void zza(zzya zzya) throws IOException {
        zzya.zzd(1, this.key);
        zzya.zzd(2, this.value);
        super.zza(zzya);
    }

    /* Access modifiers changed, original: protected|final */
    public final int zzf() {
        return (super.zzf() + zzya.zzh(1, this.key)) + zzya.zzh(2, this.value);
    }
}
