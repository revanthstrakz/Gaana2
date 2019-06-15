package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zze;

final class zzbta implements zzbsj {
    private final int flags;
    private final String info;
    private final Object[] zzfsj;
    private final zzbsl zzfsm;

    zzbta(zzbsl zzbsl, String str, Object[] objArr) {
        this.zzfsm = zzbsl;
        this.info = str;
        this.zzfsj = objArr;
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
    public final String zzaoi() {
        return this.info;
    }

    /* Access modifiers changed, original: final */
    public final Object[] zzaoj() {
        return this.zzfsj;
    }

    public final zzbsl zzaob() {
        return this.zzfsm;
    }

    public final int zzanz() {
        return (this.flags & 1) == 1 ? zze.zzfqj : zze.zzfqk;
    }

    public final boolean zzaoa() {
        return (this.flags & 2) == 2;
    }
}
