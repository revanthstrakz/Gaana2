package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzcy extends zzbt<Integer, Long> {
    public long zzse;
    public long zzsf;

    public zzcy() {
        this.zzse = -1;
        this.zzsf = -1;
    }

    public zzcy(String str) {
        this();
        zzj(str);
    }

    /* Access modifiers changed, original: protected|final */
    public final HashMap<Integer, Long> zzv() {
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), Long.valueOf(this.zzse));
        hashMap.put(Integer.valueOf(1), Long.valueOf(this.zzsf));
        return hashMap;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzj(String str) {
        HashMap zzk = zzbt.zzk(str);
        if (zzk != null) {
            this.zzse = ((Long) zzk.get(Integer.valueOf(0))).longValue();
            this.zzsf = ((Long) zzk.get(Integer.valueOf(1))).longValue();
        }
    }
}
