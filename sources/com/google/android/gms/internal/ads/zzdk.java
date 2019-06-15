package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzdk extends zzbt<Integer, Long> {
    public Long zzsn;
    public Long zzso;

    public zzdk(String str) {
        zzj(str);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzj(String str) {
        HashMap zzk = zzbt.zzk(str);
        if (zzk != null) {
            this.zzsn = (Long) zzk.get(Integer.valueOf(0));
            this.zzso = (Long) zzk.get(Integer.valueOf(1));
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final HashMap<Integer, Long> zzv() {
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzsn);
        hashMap.put(Integer.valueOf(1), this.zzso);
        return hashMap;
    }
}
