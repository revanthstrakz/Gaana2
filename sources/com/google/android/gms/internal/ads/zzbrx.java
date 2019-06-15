package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzbrx extends zzbrv {
    private static final Class<?> zzfrt = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzbrx() {
        super();
    }

    /* Access modifiers changed, original: final */
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* Access modifiers changed, original: final */
    public final void zzb(Object obj, long j) {
        Object zzanp;
        List list = (List) zzbua.zzp(obj, j);
        if (list instanceof zzbru) {
            zzanp = ((zzbru) list).zzanp();
        } else if (!zzfrt.isAssignableFrom(list.getClass())) {
            if ((list instanceof zzbsx) && (list instanceof zzbrk)) {
                zzbrk zzbrk = (zzbrk) list;
                if (zzbrk.zzaki()) {
                    zzbrk.zzakj();
                }
                return;
            }
            zzanp = Collections.unmodifiableList(list);
        } else {
            return;
        }
        zzbua.zza(obj, j, zzanp);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> zzc = zzc(obj, j);
        Object zzbrt;
        if (zzc.isEmpty()) {
            if (zzc instanceof zzbru) {
                zzbrt = new zzbrt(i);
            } else if ((zzc instanceof zzbsx) && (zzc instanceof zzbrk)) {
                zzbrt = ((zzbrk) zzc).zzel(i);
            } else {
                zzbrt = new ArrayList(i);
            }
            zzbua.zza(obj, j, zzbrt);
            return zzbrt;
        }
        ArrayList arrayList;
        if (zzfrt.isAssignableFrom(zzc.getClass())) {
            arrayList = new ArrayList(zzc.size() + i);
            arrayList.addAll(zzc);
            zzbua.zza(obj, j, (Object) arrayList);
        } else if (zzc instanceof zzbtx) {
            Object arrayList2 = new zzbrt(zzc.size() + i);
            arrayList2.addAll((zzbtx) zzc);
            zzbua.zza(obj, j, arrayList2);
        } else if (!(zzc instanceof zzbsx) || !(zzc instanceof zzbrk)) {
            return zzc;
        } else {
            zzbrk zzbrk = (zzbrk) zzc;
            if (zzbrk.zzaki()) {
                return zzc;
            }
            zzbrt = zzbrk.zzel(zzc.size() + i);
            zzbua.zza(obj, j, zzbrt);
            return zzbrt;
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
        zzbua.zza(obj, j, obj2);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzbua.zzp(obj, j);
    }
}
