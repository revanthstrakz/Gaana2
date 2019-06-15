package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzem implements zzel {
    zzem() {
    }

    public final zzej<?, ?> zzk(Object obj) {
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzi(Object obj) {
        return (zzek) obj;
    }

    public final Object zzj(Object obj) {
        ((zzek) obj).zzaj();
        return obj;
    }

    public final Object zzb(Object obj, Object obj2) {
        obj = (zzek) obj;
        zzek zzek = (zzek) obj2;
        if (!zzek.isEmpty()) {
            if (!obj.isMutable()) {
                obj = obj.zzcm();
            }
            obj.zza(zzek);
        }
        return obj;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzek zzek = (zzek) obj;
        if (zzek.isEmpty()) {
            return 0;
        }
        Iterator it = zzek.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Entry entry = (Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
