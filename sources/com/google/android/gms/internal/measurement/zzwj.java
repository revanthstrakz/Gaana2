package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuo.zze;

final class zzwj implements zzvt {
    private final int flags;
    private final String info;
    private final Object[] zzcar;
    private final zzvv zzcau;

    zzwj(zzvv zzvv, String str, Object[] objArr) {
        this.zzcau = zzvv;
        this.info = str;
        this.zzcar = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 >= 55296) {
                i |= (charAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            } else {
                this.flags = i | (charAt2 << i2);
                return;
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final String zzxv() {
        return this.info;
    }

    /* Access modifiers changed, original: final */
    public final Object[] zzxw() {
        return this.zzcar;
    }

    public final zzvv zzxo() {
        return this.zzcau;
    }

    public final int zzxm() {
        return (this.flags & 1) == 1 ? zze.zzbys : zze.zzbyt;
    }

    public final boolean zzxn() {
        return (this.flags & 2) == 2;
    }
}
