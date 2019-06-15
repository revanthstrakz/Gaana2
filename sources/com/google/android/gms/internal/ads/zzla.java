package com.google.android.gms.internal.ads;

public final class zzla implements zzlw {
    private final zzlw[] zzawm;

    public zzla(zzlw[] zzlwArr) {
        this.zzawm = zzlwArr;
    }

    public final long zzeu() {
        long j = Long.MAX_VALUE;
        for (zzlw zzeu : this.zzawm) {
            long zzeu2 = zzeu.zzeu();
            if (zzeu2 != Long.MIN_VALUE) {
                j = Math.min(j, zzeu2);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public final boolean zzy(long j) {
        boolean z = false;
        int i;
        do {
            long zzeu = zzeu();
            if (zzeu == Long.MIN_VALUE) {
                break;
            }
            zzlw[] zzlwArr = this.zzawm;
            int length = zzlwArr.length;
            int i2 = 0;
            i = i2;
            while (i2 < length) {
                zzlw zzlw = zzlwArr[i2];
                if (zzlw.zzeu() == zzeu) {
                    i |= zzlw.zzy(j);
                }
                i2++;
            }
            z |= i;
        } while (i != 0);
        return z;
    }
}
