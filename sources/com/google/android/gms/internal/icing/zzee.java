package com.google.android.gms.internal.icing;

final class zzee extends zzeb {
    private zzee() {
        super();
    }

    /* Access modifiers changed, original: final */
    public final void zza(Object obj, long j) {
        zzc(obj, j).zzaj();
    }

    /* Access modifiers changed, original: final */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzdq zzc = zzc(obj, j);
        obj2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = obj2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzai()) {
                zzc = zzc.zzj(size2 + size);
            }
            zzc.addAll(obj2);
        }
        if (size > 0) {
            obj2 = zzc;
        }
        zzgd.zza(obj, j, obj2);
    }

    private static <E> zzdq<E> zzc(Object obj, long j) {
        return (zzdq) zzgd.zzo(obj, j);
    }
}
