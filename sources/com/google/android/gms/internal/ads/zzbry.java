package com.google.android.gms.internal.ads;

import java.util.List;

final class zzbry extends zzbrv {
    private zzbry() {
        super();
    }

    /* Access modifiers changed, original: final */
    public final <L> List<L> zza(Object obj, long j) {
        List<L> zzd = zzd(obj, j);
        if (zzd.zzaki()) {
            return zzd;
        }
        int size = zzd.size();
        Object zzel = zzd.zzel(size == 0 ? 10 : size << 1);
        zzbua.zza(obj, j, zzel);
        return zzel;
    }

    /* Access modifiers changed, original: final */
    public final void zzb(Object obj, long j) {
        zzd(obj, j).zzakj();
    }

    /* Access modifiers changed, original: final */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzbrk zzd = zzd(obj, j);
        obj2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = obj2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zzaki()) {
                zzd = zzd.zzel(size2 + size);
            }
            zzd.addAll(obj2);
        }
        if (size > 0) {
            obj2 = zzd;
        }
        zzbua.zza(obj, j, obj2);
    }

    private static <E> zzbrk<E> zzd(Object obj, long j) {
        return (zzbrk) zzbua.zzp(obj, j);
    }
}
