package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzvh extends zzvf {
    private static final Class<?> zzcac = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzvh() {
        super();
    }

    /* Access modifiers changed, original: final */
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* Access modifiers changed, original: final */
    public final void zzb(Object obj, long j) {
        Object zzxc;
        List list = (List) zzxj.zzp(obj, j);
        if (list instanceof zzve) {
            zzxc = ((zzve) list).zzxc();
        } else if (!zzcac.isAssignableFrom(list.getClass())) {
            if ((list instanceof zzwg) && (list instanceof zzuu)) {
                zzuu zzuu = (zzuu) list;
                if (zzuu.zztz()) {
                    zzuu.zzsw();
                }
                return;
            }
            zzxc = Collections.unmodifiableList(list);
        } else {
            return;
        }
        zzxj.zza(obj, j, zzxc);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> zzc = zzc(obj, j);
        Object zzvd;
        if (zzc.isEmpty()) {
            if (zzc instanceof zzve) {
                zzvd = new zzvd(i);
            } else if ((zzc instanceof zzwg) && (zzc instanceof zzuu)) {
                zzvd = ((zzuu) zzc).zzal(i);
            } else {
                zzvd = new ArrayList(i);
            }
            zzxj.zza(obj, j, zzvd);
            return zzvd;
        }
        ArrayList arrayList;
        if (zzcac.isAssignableFrom(zzc.getClass())) {
            arrayList = new ArrayList(zzc.size() + i);
            arrayList.addAll(zzc);
            zzxj.zza(obj, j, (Object) arrayList);
        } else if (zzc instanceof zzxg) {
            Object arrayList2 = new zzvd(zzc.size() + i);
            arrayList2.addAll((zzxg) zzc);
            zzxj.zza(obj, j, arrayList2);
        } else if (!(zzc instanceof zzwg) || !(zzc instanceof zzuu)) {
            return zzc;
        } else {
            zzuu zzuu = (zzuu) zzc;
            if (zzuu.zztz()) {
                return zzc;
            }
            zzvd = zzuu.zzal(zzc.size() + i);
            zzxj.zza(obj, j, zzvd);
            return zzvd;
        }
        return arrayList2;
    }

    /* Access modifiers changed, original: final */
    public final <E> void zza(Object obj, Object obj2, long j) {
        obj2 = zzc(obj2, j);
        List zza = zza(obj, j, obj2.size());
        int size = zza.size();
        int size2 = obj2.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(obj2);
        }
        if (size > 0) {
            obj2 = zza;
        }
        zzxj.zza(obj, j, obj2);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzxj.zzp(obj, j);
    }
}
