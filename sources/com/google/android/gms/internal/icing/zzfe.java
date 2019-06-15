package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdj.zzd;

final class zzfe implements zzeo {
    private final int flags;
    private final String info;
    private final Object[] zzmg;
    private final zzeq zzmj;

    zzfe(zzeq zzeq, String str, Object[] objArr) {
        this.zzmj = zzeq;
        this.info = str;
        this.zzmg = objArr;
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
    public final String zzda() {
        return this.info;
    }

    /* Access modifiers changed, original: final */
    public final Object[] zzdb() {
        return this.zzmg;
    }

    public final zzeq zzct() {
        return this.zzmj;
    }

    public final int zzcr() {
        return (this.flags & 1) == 1 ? zzd.zzki : zzd.zzkj;
    }

    public final boolean zzcs() {
        return (this.flags & 2) == 2;
    }
}
