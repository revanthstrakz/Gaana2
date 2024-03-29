package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzdj extends zzbt<Integer, Object> {
    public Long zzsk;
    public Boolean zzsl;
    public Boolean zzsm;

    public zzdj(String str) {
        zzj(str);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzj(String str) {
        HashMap zzk = zzbt.zzk(str);
        if (zzk != null) {
            this.zzsk = (Long) zzk.get(Integer.valueOf(0));
            this.zzsl = (Boolean) zzk.get(Integer.valueOf(1));
            this.zzsm = (Boolean) zzk.get(Integer.valueOf(2));
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final HashMap<Integer, Object> zzv() {
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzsk);
        hashMap.put(Integer.valueOf(1), this.zzsl);
        hashMap.put(Integer.valueOf(2), this.zzsm);
        return hashMap;
    }
}
