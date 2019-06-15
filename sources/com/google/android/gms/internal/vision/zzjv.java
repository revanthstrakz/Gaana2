package com.google.android.gms.internal.vision;

import java.util.Arrays;

final class zzjv {
    final int tag;
    final byte[] zzse;

    zzjv(int i, byte[] bArr) {
        this.tag = i;
        this.zzse = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzjv)) {
            return false;
        }
        zzjv zzjv = (zzjv) obj;
        return this.tag == zzjv.tag && Arrays.equals(this.zzse, zzjv.zzse);
    }

    public final int hashCode() {
        return ((527 + this.tag) * 31) + Arrays.hashCode(this.zzse);
    }
}
