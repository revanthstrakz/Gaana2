package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzdt extends zzbt<Integer, Long> {
    public Long zzhf;
    public Long zzhg;
    public Long zztt;

    public zzdt(String str) {
        zzj(str);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzj(String str) {
        HashMap zzk = zzbt.zzk(str);
        if (zzk != null) {
            this.zztt = (Long) zzk.get(Integer.valueOf(0));
            this.zzhf = (Long) zzk.get(Integer.valueOf(1));
            this.zzhg = (Long) zzk.get(Integer.valueOf(2));
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final HashMap<Integer, Long> zzv() {
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zztt);
        hashMap.put(Integer.valueOf(1), this.zzhf);
        hashMap.put(Integer.valueOf(2), this.zzhg);
        return hashMap;
    }
}
