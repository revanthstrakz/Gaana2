package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

abstract class zzfz extends zzef {
    public zzfz(String str) {
        super(str);
    }

    public abstract boolean zza(String str, String str2, Map<String, zzp> map);

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zzp zzp, zzp zzp2, Map<String, zzp> map) {
        String zzc = zzgj.zzc(zzp);
        String zzc2 = zzgj.zzc(zzp2);
        return (zzc == zzgj.zzqp() || zzc2 == zzgj.zzqp()) ? false : zza(zzc, zzc2, (Map) map);
    }
}
