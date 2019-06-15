package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzfy.zzg;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzfg implements zzjj {
    private final zzfe zzsd;

    public static zzfg zza(zzfe zzfe) {
        if (zzfe.zzss != null) {
            return zzfe.zzss;
        }
        return new zzfg(zzfe);
    }

    private zzfg(zzfe zzfe) {
        this.zzsd = (zzfe) zzga.zza((Object) zzfe, "output");
    }

    public final int zzed() {
        return zzg.zzxi;
    }

    public final void zzo(int i, int i2) throws IOException {
        this.zzsd.zzh(i, i2);
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzsd.zza(i, j);
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzsd.zzc(i, j);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzsd.zza(i, f);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzsd.zza(i, d);
    }

    public final void zzp(int i, int i2) throws IOException {
        this.zzsd.zze(i, i2);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzsd.zza(i, j);
    }

    public final void zze(int i, int i2) throws IOException {
        this.zzsd.zze(i, i2);
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzsd.zzc(i, j);
    }

    public final void zzh(int i, int i2) throws IOException {
        this.zzsd.zzh(i, i2);
    }

    public final void zzb(int i, boolean z) throws IOException {
        this.zzsd.zzb(i, z);
    }

    public final void zza(int i, String str) throws IOException {
        this.zzsd.zza(i, str);
    }

    public final void zza(int i, zzeo zzeo) throws IOException {
        this.zzsd.zza(i, zzeo);
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zzsd.zzf(i, i2);
    }

    public final void zzg(int i, int i2) throws IOException {
        this.zzsd.zzg(i, i2);
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzsd.zzb(i, j);
    }

    public final void zza(int i, Object obj, zzhw zzhw) throws IOException {
        this.zzsd.zza(i, (zzhf) obj, zzhw);
    }

    public final void zzb(int i, Object obj, zzhw zzhw) throws IOException {
        zzfe zzfe = this.zzsd;
        zzhf zzhf = (zzhf) obj;
        zzfe.zzd(i, 3);
        zzhw.zza(zzhf, zzfe.zzss);
        zzfe.zzd(i, 4);
    }

    public final void zzbe(int i) throws IOException {
        this.zzsd.zzd(i, 3);
    }

    public final void zzbf(int i) throws IOException {
        this.zzsd.zzd(i, 4);
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzeo) {
            this.zzsd.zzb(i, (zzeo) obj);
        } else {
            this.zzsd.zzb(i, (zzhf) obj);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzaw(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzar(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zze(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzaz(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzau(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zzh(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzh(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zze(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzi(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zze(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzk(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzg(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzg(((Float) list.get(i)).floatValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzf(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zza(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzb(((Double) list.get(i)).doubleValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zza(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zza(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzbb(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzar(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zze(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzi(((Boolean) list.get(i)).booleanValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzh(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zzb(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzgo) {
            zzgo zzgo = (zzgo) list;
            while (i2 < list.size()) {
                Object raw = zzgo.getRaw(i2);
                if (raw instanceof String) {
                    this.zzsd.zza(i, (String) raw);
                } else {
                    this.zzsd.zza(i, (zzeo) raw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zza(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zzb(int i, List<zzeo> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzsd.zza(i, (zzeo) list.get(i2));
        }
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzax(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzas(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzba(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzau(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zzh(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzl(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzg(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzay(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzat(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zzg(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzsd.zzd(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzfe.zzj(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzsd.zzas(i3);
            while (i2 < list.size()) {
                this.zzsd.zzf(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzsd.zzb(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzhw zzhw) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzhw);
        }
    }

    public final void zzb(int i, List<?> list, zzhw zzhw) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzhw);
        }
    }

    public final <K, V> void zza(int i, zzgy<K, V> zzgy, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.zzsd.zzd(i, 2);
            this.zzsd.zzas(zzgx.zza(zzgy, entry.getKey(), entry.getValue()));
            zzgx.zza(this.zzsd, zzgy, entry.getKey(), entry.getValue());
        }
    }
}
