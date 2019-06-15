package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzed extends zzeb {
    private static final Class<?> zzls = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzed() {
        super();
    }

    /* Access modifiers changed, original: final */
    public final void zza(Object obj, long j) {
        Object zzch;
        List list = (List) zzgd.zzo(obj, j);
        if (list instanceof zzea) {
            zzch = ((zzea) list).zzch();
        } else if (!zzls.isAssignableFrom(list.getClass())) {
            if ((list instanceof zzfb) && (list instanceof zzdq)) {
                zzdq zzdq = (zzdq) list;
                if (zzdq.zzai()) {
                    zzdq.zzaj();
                }
                return;
            }
            zzch = Collections.unmodifiableList(list);
        } else {
            return;
        }
        zzgd.zza(obj, j, zzch);
    }

    /* Access modifiers changed, original: final */
    public final <E> void zza(Object obj, Object obj2, long j) {
        obj2 = zzb(obj2, j);
        int size = obj2.size();
        List zzb = zzb(obj, j);
        if (zzb.isEmpty()) {
            if (zzb instanceof zzea) {
                zzb = new zzdz(size);
            } else if ((zzb instanceof zzfb) && (zzb instanceof zzdq)) {
                zzb = ((zzdq) zzb).zzj(size);
            } else {
                zzb = new ArrayList(size);
            }
            zzgd.zza(obj, j, (Object) zzb);
        } else {
            ArrayList arrayList;
            if (zzls.isAssignableFrom(zzb.getClass())) {
                arrayList = new ArrayList(zzb.size() + size);
                arrayList.addAll(zzb);
                zzgd.zza(obj, j, (Object) arrayList);
            } else if (zzb instanceof zzga) {
                Object arrayList2 = new zzdz(zzb.size() + size);
                arrayList2.addAll((zzga) zzb);
                zzgd.zza(obj, j, arrayList2);
            } else if ((zzb instanceof zzfb) && (zzb instanceof zzdq)) {
                zzdq zzdq = (zzdq) zzb;
                if (!zzdq.zzai()) {
                    List zzj = zzdq.zzj(zzb.size() + size);
                    zzgd.zza(obj, j, (Object) zzj);
                    zzb = zzj;
                }
            }
            zzb = arrayList2;
        }
        size = zzb.size();
        int size2 = obj2.size();
        if (size > 0 && size2 > 0) {
            zzb.addAll(obj2);
        }
        if (size > 0) {
            obj2 = zzb;
        }
        zzgd.zza(obj, j, obj2);
    }

    private static <E> List<E> zzb(Object obj, long j) {
        return (List) zzgd.zzo(obj, j);
    }
}
