package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzp;
import java.util.Map;

abstract class zzdy extends zzef {
    public zzdy(String str) {
        super(str);
    }

    public abstract boolean zza(zzgi zzgi, zzgi zzgi2, Map<String, zzp> map);

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zzp zzp, zzp zzp2, Map<String, zzp> map) {
        zzgi zzd = zzgj.zzd(zzp);
        zzgi zzd2 = zzgj.zzd(zzp2);
        return (zzd == zzgj.zzqo() || zzd2 == zzgj.zzqo()) ? false : zza(zzd, zzd2, (Map) map);
    }
}
