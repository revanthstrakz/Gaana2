package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzwn {
    private static final Class<?> zzcbn = zzya();
    private static final zzxd<?, ?> zzcbo = zzv(false);
    private static final zzxd<?, ?> zzcbp = zzv(true);
    private static final zzxd<?, ?> zzcbq = new zzxf();

    public static void zzj(Class<?> cls) {
        if (!zzuo.class.isAssignableFrom(cls) && zzcbn != null && !zzcbn.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zza(i, (List) list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzb(i, (List) list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzxy zzxy, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzxy zzxy) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zza(i, (List) list);
        }
    }

    public static void zzb(int i, List<zzte> list, zzxy zzxy) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzb(i, (List) list);
        }
    }

    public static void zza(int i, List<?> list, zzxy zzxy, zzwl zzwl) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zza(i, (List) list, zzwl);
        }
    }

    public static void zzb(int i, List<?> list, zzxy zzxy, zzwl zzwl) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzxy.zzb(i, (List) list, zzwl);
        }
    }

    static int zzy(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzaw(zzvj.getLong(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzaw(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return i2;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzy(list) + (list.size() * zztv.zzbd(i));
    }

    static int zzz(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzax(zzvj.getLong(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzax(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return i2;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzz(list) + (size * zztv.zzbd(i));
    }

    static int zzaa(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzvj) {
            zzvj zzvj = (zzvj) list;
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzay(zzvj.getLong(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzay(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return i2;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaa(list) + (size * zztv.zzbd(i));
    }

    static int zzab(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzbj(zzup.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzbj(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return i2;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzab(list) + (size * zztv.zzbd(i));
    }

    static int zzac(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzbe(zzup.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzbe(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return i2;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzac(list) + (size * zztv.zzbd(i));
    }

    static int zzad(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzbf(zzup.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzbf(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return i2;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzad(list) + (size * zztv.zzbd(i));
    }

    static int zzae(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzup) {
            zzup zzup = (zzup) list;
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzbg(zzup.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zztv.zzbg(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return i2;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzae(list) + (size * zztv.zzbd(i));
    }

    static int zzaf(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zztv.zzk(i, 0);
    }

    static int zzag(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zztv.zzg(i, 0);
    }

    static int zzah(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zztv.zzc(i, true);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        i = zztv.zzbd(i) * size;
        Object zzbp;
        int zzb;
        if (list instanceof zzve) {
            zzve zzve = (zzve) list;
            while (i2 < size) {
                zzbp = zzve.zzbp(i2);
                if (zzbp instanceof zzte) {
                    zzb = zztv.zzb((zzte) zzbp);
                } else {
                    zzb = zztv.zzgc((String) zzbp);
                }
                i += zzb;
                i2++;
            }
        } else {
            while (i2 < size) {
                zzbp = list.get(i2);
                if (zzbp instanceof zzte) {
                    zzb = zztv.zzb((zzte) zzbp);
                } else {
                    zzb = zztv.zzgc((String) zzbp);
                }
                i += zzb;
                i2++;
            }
        }
        return i;
    }

    static int zzc(int i, Object obj, zzwl zzwl) {
        if (obj instanceof zzvc) {
            return zztv.zza(i, (zzvc) obj);
        }
        return zztv.zzb(i, (zzvv) obj, zzwl);
    }

    static int zzc(int i, List<?> list, zzwl zzwl) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        i = zztv.zzbd(i) * size;
        while (i2 < size) {
            int zza;
            Object obj = list.get(i2);
            if (obj instanceof zzvc) {
                zza = zztv.zza((zzvc) obj);
            } else {
                zza = zztv.zzb((zzvv) obj, zzwl);
            }
            i += zza;
            i2++;
        }
        return i;
    }

    static int zzd(int i, List<zzte> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        size *= zztv.zzbd(i);
        while (i2 < list.size()) {
            size += zztv.zzb((zzte) list.get(i2));
            i2++;
        }
        return size;
    }

    static int zzd(int i, List<zzvv> list, zzwl zzwl) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int i3 = 0;
        while (i2 < size) {
            i3 += zztv.zzc(i, (zzvv) list.get(i2), zzwl);
            i2++;
        }
        return i3;
    }

    public static zzxd<?, ?> zzxx() {
        return zzcbo;
    }

    public static zzxd<?, ?> zzxy() {
        return zzcbp;
    }

    public static zzxd<?, ?> zzxz() {
        return zzcbq;
    }

    private static zzxd<?, ?> zzv(boolean z) {
        try {
            Class zzyb = zzyb();
            if (zzyb == null) {
                return null;
            }
            return (zzxd) zzyb.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzya() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzyb() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zza(zzvq zzvq, T t, T t2, long j) {
        zzxj.zza((Object) t, j, zzvq.zzc(zzxj.zzp(t, j), zzxj.zzp(t2, j)));
    }

    static <T, FT extends zzuh<FT>> void zza(zzuc<FT> zzuc, T t, T t2) {
        zzuf zzw = zzuc.zzw(t2);
        if (!zzw.isEmpty()) {
            zzuc.zzx(t).zza(zzw);
        }
    }

    static <T, UT, UB> void zza(zzxd<UT, UB> zzxd, T t, T t2) {
        zzxd.zzf(t, zzxd.zzh(zzxd.zzal(t), zzxd.zzal(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzut zzut, UB ub, zzxd<UT, UB> zzxd) {
        if (zzut == null) {
            return ub;
        }
        UB ub2;
        int intValue;
        if (!(list instanceof RandomAccess)) {
            Iterator it = list.iterator();
            loop1:
            while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    intValue = ((Integer) it.next()).intValue();
                    if (!zzut.zzb(intValue)) {
                        ub = zza(i, intValue, (Object) ub2, (zzxd) zzxd);
                        it.remove();
                    }
                }
                break loop1;
            }
        }
        int size = list.size();
        int i2 = 0;
        ub2 = ub;
        intValue = 0;
        while (i2 < size) {
            int intValue2 = ((Integer) list.get(i2)).intValue();
            if (zzut.zzb(intValue2)) {
                if (i2 != intValue) {
                    list.set(intValue, Integer.valueOf(intValue2));
                }
                intValue++;
            } else {
                ub2 = zza(i, intValue2, (Object) ub2, (zzxd) zzxd);
            }
            i2++;
        }
        if (intValue != size) {
            list.subList(intValue, size).clear();
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzxd<UT, UB> zzxd) {
        Object ub2;
        if (ub2 == null) {
            ub2 = zzxd.zzyk();
        }
        zzxd.zza(ub2, i, (long) i2);
        return ub2;
    }
}
