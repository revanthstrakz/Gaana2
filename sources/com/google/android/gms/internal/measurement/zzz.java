package com.google.android.gms.internal.measurement;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@ShowFirstParty
public final class zzz extends zzi<zzz> {
    private Map<Integer, String> zzud = new HashMap(4);

    public final String toString() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzud.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            StringBuilder stringBuilder = new StringBuilder(9 + String.valueOf(valueOf).length());
            stringBuilder.append("dimension");
            stringBuilder.append(valueOf);
            hashMap.put(stringBuilder.toString(), entry.getValue());
        }
        return zzi.zza((Object) hashMap);
    }

    public final Map<Integer, String> zzau() {
        return Collections.unmodifiableMap(this.zzud);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        ((zzz) zzi).zzud.putAll(this.zzud);
    }
}
