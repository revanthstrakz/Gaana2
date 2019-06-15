package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzvi extends zzvf {
    private zzvi() {
        super();
    }

    /* Access modifiers changed, original: final */
    public final <L> List<L> zza(Object obj, long j) {
        List<L> zzd = zzd(obj, j);
        if (zzd.zztz()) {
            return zzd;
        }
        int size = zzd.size();
        Object zzal = zzd.zzal(size == 0 ? 10 : size << 1);
        zzxj.zza(obj, j, zzal);
        return zzal;
    }

    /* Access modifiers changed, original: final */
    public final void zzb(Object obj, long j) {
        zzd(obj, j).zzsw();
    }

    /* Access modifiers changed, original: final */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzuu zzd = zzd(obj, j);
        obj2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = obj2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zztz()) {
                zzd = zzd.zzal(size2 + size);
            }
            zzd.addAll(obj2);
        }
        if (size > 0) {
            obj2 = zzd;
        }
        zzxj.zza(obj, j, obj2);
    }

    private static <E> zzuu<E> zzd(Object obj, long j) {
        return (zzuu) zzxj.zzp(obj, j);
    }
}
