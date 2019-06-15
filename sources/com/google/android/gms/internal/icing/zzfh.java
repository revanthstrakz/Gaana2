package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.List;

final class zzfh {
    private static final Class<?> zznc = zzdf();
    private static final zzfx<?, ?> zznd = zzj(false);
    private static final zzfx<?, ?> zzne = zzj(true);
    private static final zzfx<?, ?> zznf = new zzfz();

    public static void zzf(Class<?> cls) {
        if (!zzdj.class.isAssignableFrom(cls) && zznc != null && !zznc.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zza(i, (List) list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzb(i, (List) list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzgr zzgr, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzgr zzgr) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zza(i, (List) list);
        }
    }

    public static void zzb(int i, List<zzce> list, zzgr zzgr) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzb(i, (List) list);
        }
    }

    public static void zza(int i, List<?> list, zzgr zzgr, zzff zzff) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zza(i, (List) list, zzff);
        }
    }

    public static void zzb(int i, List<?> list, zzgr zzgr, zzff zzff) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgr.zzb(i, (List) list, zzff);
        }
    }

    static int zza(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzef) {
            zzef zzef = (zzef) list;
            i2 = 0;
            while (i < size) {
                i2 += zzct.zze(zzef.getLong(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzct.zze(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return i2;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzct.zzs(i));
    }

    static int zzb(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzef) {
            zzef zzef = (zzef) list;
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzf(zzef.getLong(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzf(((Long) list.get(i)).longValue());
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
        return zzb(list) + (size * zzct.zzs(i));
    }

    static int zzc(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzef) {
            zzef zzef = (zzef) list;
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzg(zzef.getLong(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzg(((Long) list.get(i)).longValue());
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
        return zzc(list) + (size * zzct.zzs(i));
    }

    static int zzd(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzdk) {
            zzdk zzdk = (zzdk) list;
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzy(zzdk.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzy(((Integer) list.get(i)).intValue());
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
        return zzd(list) + (size * zzct.zzs(i));
    }

    static int zze(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzdk) {
            zzdk zzdk = (zzdk) list;
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzt(zzdk.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzt(((Integer) list.get(i)).intValue());
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
        return zze(list) + (size * zzct.zzs(i));
    }

    static int zzf(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzdk) {
            zzdk zzdk = (zzdk) list;
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzu(zzdk.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzu(((Integer) list.get(i)).intValue());
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
        return zzf((List) list) + (size * zzct.zzs(i));
    }

    static int zzg(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int i2;
        if (list instanceof zzdk) {
            zzdk zzdk = (zzdk) list;
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzv(zzdk.getInt(i));
                i++;
            }
        } else {
            i2 = 0;
            while (i < size) {
                i2 += zzct.zzv(((Integer) list.get(i)).intValue());
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
        return zzg(list) + (size * zzct.zzs(i));
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzct.zzj(i, 0);
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzct.zzg(i, 0);
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzct.zzb(i, true);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        i = zzct.zzs(i) * size;
        Object zzad;
        int zzb;
        if (list instanceof zzea) {
            zzea zzea = (zzea) list;
            while (i2 < size) {
                zzad = zzea.zzad(i2);
                if (zzad instanceof zzce) {
                    zzb = zzct.zzb((zzce) zzad);
                } else {
                    zzb = zzct.zzv((String) zzad);
                }
                i += zzb;
                i2++;
            }
        } else {
            while (i2 < size) {
                zzad = list.get(i2);
                if (zzad instanceof zzce) {
                    zzb = zzct.zzb((zzce) zzad);
                } else {
                    zzb = zzct.zzv((String) zzad);
                }
                i += zzb;
                i2++;
            }
        }
        return i;
    }

    static int zzc(int i, Object obj, zzff zzff) {
        if (obj instanceof zzdy) {
            return zzct.zza(i, (zzdy) obj);
        }
        return zzct.zzb(i, (zzeq) obj, zzff);
    }

    static int zzc(int i, List<?> list, zzff zzff) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        i = zzct.zzs(i) * size;
        while (i2 < size) {
            int zza;
            Object obj = list.get(i2);
            if (obj instanceof zzdy) {
                zza = zzct.zza((zzdy) obj);
            } else {
                zza = zzct.zza((zzeq) obj, zzff);
            }
            i += zza;
            i2++;
        }
        return i;
    }

    static int zzd(int i, List<zzce> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        size *= zzct.zzs(i);
        while (i2 < list.size()) {
            size += zzct.zzb((zzce) list.get(i2));
            i2++;
        }
        return size;
    }

    static int zzd(int i, List<zzeq> list, zzff zzff) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int i3 = 0;
        while (i2 < size) {
            i3 += zzct.zzc(i, (zzeq) list.get(i2), zzff);
            i2++;
        }
        return i3;
    }

    public static zzfx<?, ?> zzdc() {
        return zznd;
    }

    public static zzfx<?, ?> zzdd() {
        return zzne;
    }

    public static zzfx<?, ?> zzde() {
        return zznf;
    }

    private static zzfx<?, ?> zzj(boolean z) {
        try {
            Class zzdg = zzdg();
            if (zzdg == null) {
                return null;
            }
            return (zzfx) zzdg.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdf() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdg() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static <T> void zza(zzel zzel, T t, T t2, long j) {
        zzgd.zza((Object) t, j, zzel.zzb(zzgd.zzo(t, j), zzgd.zzo(t2, j)));
    }

    static <T, FT extends zzde<FT>> void zza(zzcz<FT> zzcz, T t, T t2) {
        zzdc zzc = zzcz.zzc(t2);
        if (!zzc.isEmpty()) {
            zzcz.zzd(t).zza(zzc);
        }
    }

    static <T, UT, UB> void zza(zzfx<UT, UB> zzfx, T t, T t2) {
        zzfx.zze(t, zzfx.zzf(zzfx.zzo(t), zzfx.zzo(t2)));
    }
}
