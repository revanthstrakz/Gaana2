package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class zzvr implements zzvq {
    zzvr() {
    }

    public final Map<?, ?> zzac(Object obj) {
        return (zzvp) obj;
    }

    public final zzvo<?, ?> zzah(Object obj) {
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzad(Object obj) {
        return (zzvp) obj;
    }

    public final boolean zzae(Object obj) {
        return !((zzvp) obj).isMutable();
    }

    public final Object zzaf(Object obj) {
        ((zzvp) obj).zzsw();
        return obj;
    }

    public final Object zzag(Object obj) {
        return zzvp.zzxg().zzxh();
    }

    public final Object zzc(Object obj, Object obj2) {
        obj = (zzvp) obj;
        zzvp zzvp = (zzvp) obj2;
        if (!zzvp.isEmpty()) {
            if (!obj.isMutable()) {
                obj = obj.zzxh();
            }
            obj.zza(zzvp);
        }
        return obj;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzvp zzvp = (zzvp) obj;
        if (zzvp.isEmpty()) {
            return 0;
        }
        Iterator it = zzvp.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Entry entry = (Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
