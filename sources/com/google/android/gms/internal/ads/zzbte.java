package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzbte {
    private static final Class<?> zzftf = zzaon();
    private static final zzbtu<?, ?> zzftg = zzbg(false);
    private static final zzbtu<?, ?> zzfth = zzbg(true);
    private static final zzbtu<?, ?> zzfti = new zzbtw();

    public static void zzg(Class<?> cls) {
        if (!zzbrd.class.isAssignableFrom(cls) && zzftf != null && !zzftf.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zza(i, (List) list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzb(i, (List) list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzbup zzbup, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzbup zzbup) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zza(i, (List) list);
        }
    }

    public static void zzb(int i, List<zzbpu> list, zzbup zzbup) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzb(i, (List) list);
        }
    }

    public static void zza(int i, List<?> list, zzbup zzbup, zzbtc zzbtc) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zza(i, (List) list, zzbtc);
        }
    }

    public static void zzb(int i, List<?> list, zzbup zzbup, zzbtc zzbtc) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbup.zzb(i, (List) list, zzbtc);
        }
    }

    static int zzaf(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzbb(zzbrz.getLong(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzbb(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return i2;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzaf(list) + (list.size() * zzbqk.zzfd(i));
    }

    static int zzag(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzbc(zzbrz.getLong(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzbc(((Long) list.get(i)).longValue());
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
        return zzag(list) + (size * zzbqk.zzfd(i));
    }

    static int zzah(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbrz) {
            zzbrz zzbrz = (zzbrz) list;
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzbd(zzbrz.getLong(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzbd(((Long) list.get(i)).longValue());
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
        return zzah(list) + (size * zzbqk.zzfd(i));
    }

    static int zzai(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzfj(zzbre.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzfj(((Integer) list.get(i)).intValue());
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
        return zzai(list) + (size * zzbqk.zzfd(i));
    }

    static int zzaj(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzfe(zzbre.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzfe(((Integer) list.get(i)).intValue());
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
        return zzaj(list) + (size * zzbqk.zzfd(i));
    }

    static int zzak(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzff(zzbre.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzff(((Integer) list.get(i)).intValue());
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
        return zzak(list) + (size * zzbqk.zzfd(i));
    }

    static int zzal(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzbre) {
            zzbre zzbre = (zzbre) list;
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzfg(zzbre.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzbqk.zzfg(((Integer) list.get(i)).intValue());
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
        return zzal(list) + (size * zzbqk.zzfd(i));
    }

    static int zzam(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbqk.zzac(i, 0);
    }

    static int zzan(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbqk.zzp(i, 0);
    }

    static int zzao(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbqk.zzk(i, true);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        i = zzbqk.zzfd(i) * size;
        Object zzfp;
        int zzao;
        if (list instanceof zzbru) {
            zzbru zzbru = (zzbru) list;
            while (i2 < size) {
                zzfp = zzbru.zzfp(i2);
                if (zzfp instanceof zzbpu) {
                    zzao = zzbqk.zzao((zzbpu) zzfp);
                } else {
                    zzao = zzbqk.zzfy((String) zzfp);
                }
                i += zzao;
                i2++;
            }
        } else {
            while (i2 < size) {
                zzfp = list.get(i2);
                if (zzfp instanceof zzbpu) {
                    zzao = zzbqk.zzao((zzbpu) zzfp);
                } else {
                    zzao = zzbqk.zzfy((String) zzfp);
                }
                i += zzao;
                i2++;
            }
        }
        return i;
    }

    static int zzc(int i, Object obj, zzbtc zzbtc) {
        if (obj instanceof zzbrs) {
            return zzbqk.zza(i, (zzbrs) obj);
        }
        return zzbqk.zzb(i, (zzbsl) obj, zzbtc);
    }

    static int zzc(int i, List<?> list, zzbtc zzbtc) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        i = zzbqk.zzfd(i) * size;
        while (i2 < size) {
            int zza;
            Object obj = list.get(i2);
            if (obj instanceof zzbrs) {
                zza = zzbqk.zza((zzbrs) obj);
            } else {
                zza = zzbqk.zzb((zzbsl) obj, zzbtc);
            }
            i += zza;
            i2++;
        }
        return i;
    }

    static int zzd(int i, List<zzbpu> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        size *= zzbqk.zzfd(i);
        while (i2 < list.size()) {
            size += zzbqk.zzao((zzbpu) list.get(i2));
            i2++;
        }
        return size;
    }

    static int zzd(int i, List<zzbsl> list, zzbtc zzbtc) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int i3 = 0;
        while (i2 < size) {
            i3 += zzbqk.zzc(i, (zzbsl) list.get(i2), zzbtc);
            i2++;
        }
        return i3;
    }

    public static zzbtu<?, ?> zzaok() {
        return zzftg;
    }

    public static zzbtu<?, ?> zzaol() {
        return zzfth;
    }

    public static zzbtu<?, ?> zzaom() {
        return zzfti;
    }

    private static zzbtu<?, ?> zzbg(boolean z) {
        try {
            Class zzaoo = zzaoo();
            if (zzaoo == null) {
                return null;
            }
            return (zzbtu) zzaoo.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzaon() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzaoo() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zza(zzbsg zzbsg, T t, T t2, long j) {
        zzbua.zza((Object) t, j, zzbsg.zzc(zzbua.zzp(t, j), zzbua.zzp(t2, j)));
    }

    static <T, FT extends zzbqw<FT>> void zza(zzbqr<FT> zzbqr, T t, T t2) {
        zzbqu zzq = zzbqr.zzq(t2);
        if (!zzq.isEmpty()) {
            zzbqr.zzr(t).zza(zzq);
        }
    }

    static <T, UT, UB> void zza(zzbtu<UT, UB> zzbtu, T t, T t2) {
        zzbtu.zzf(t, zzbtu.zzh(zzbtu.zzag(t), zzbtu.zzag(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzbri zzbri, UB ub, zzbtu<UT, UB> zzbtu) {
        if (zzbri == null) {
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
                    if (!zzbri.zzcb(intValue)) {
                        ub = zza(i, intValue, (Object) ub2, (zzbtu) zzbtu);
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
            if (zzbri.zzcb(intValue2)) {
                if (i2 != intValue) {
                    list.set(intValue, Integer.valueOf(intValue2));
                }
                intValue++;
            } else {
                ub2 = zza(i, intValue2, (Object) ub2, (zzbtu) zzbtu);
            }
            i2++;
        }
        if (intValue != size) {
            list.subList(intValue, size).clear();
        }
        return ub2;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzbtu<UT, UB> zzbtu) {
        Object ub2;
        if (ub2 == null) {
            ub2 = zzbtu.zzaoy();
        }
        zzbtu.zza(ub2, i, (long) i2);
        return ub2;
    }
}
