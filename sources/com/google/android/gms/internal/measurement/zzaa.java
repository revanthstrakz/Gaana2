package com.google.android.gms.internal.measurement;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@ShowFirstParty
public final class zzaa extends zzi<zzaa> {
    private Map<Integer, Double> zzue = new HashMap(4);

    public final String toString() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzue.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            StringBuilder stringBuilder = new StringBuilder(6 + String.valueOf(valueOf).length());
            stringBuilder.append("metric");
            stringBuilder.append(valueOf);
            hashMap.put(stringBuilder.toString(), entry.getValue());
        }
        return zzi.zza((Object) hashMap);
    }

    public final Map<Integer, Double> zzav() {
        return Collections.unmodifiableMap(this.zzue);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        ((zzaa) zzi).zzue.putAll(this.zzue);
    }
}
