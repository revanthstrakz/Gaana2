package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg.zzg;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbp implements zzfr {
    private final zzbn zzfo;

    private zzbp(zzbn zzbn) {
        this.zzfo = (zzbn) zzci.zza((Object) zzbn, "output");
    }

    public static zzbp zza(zzbn zzbn) {
        return zzbn.zzfz != null ? zzbn.zzfz : new zzbp(zzbn);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzfo.zza(i, d);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzfo.zza(i, f);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzfo.zza(i, j);
    }

    public final void zza(int i, zzbb zzbb) throws IOException {
        this.zzfo.zza(i, zzbb);
    }

    public final <K, V> void zza(int i, zzdh<K, V> zzdh, Map<K, V> map) throws IOException {
        for (Entry entry : map.entrySet()) {
            this.zzfo.zzb(i, 2);
            this.zzfo.zzo(zzdg.zza(zzdh, entry.getKey(), entry.getValue()));
            zzdg.zza(this.zzfo, zzdh, entry.getKey(), entry.getValue());
        }
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzbb) {
            this.zzfo.zzb(i, (zzbb) obj);
        } else {
            this.zzfo.zzb(i, (zzdo) obj);
        }
    }

    public final void zza(int i, Object obj, zzef zzef) throws IOException {
        this.zzfo.zza(i, (zzdo) obj, zzef);
    }

    public final void zza(int i, String str) throws IOException {
        this.zzfo.zza(i, str);
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzcx) {
            zzcx zzcx = (zzcx) list;
            while (i2 < list.size()) {
                Object raw = zzcx.getRaw(i2);
                if (raw instanceof String) {
                    this.zzfo.zza(i, (String) raw);
                } else {
                    this.zzfo.zza(i, (zzbb) raw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zza(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzef zzef) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzef);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzs(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzn(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zzc(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzaa(int i) throws IOException {
        this.zzfo.zzb(i, 3);
    }

    public final void zzab(int i) throws IOException {
        this.zzfo.zzb(i, 4);
    }

    public final int zzaj() {
        return zzg.zzko;
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzfo.zzb(i, j);
    }

    public final void zzb(int i, Object obj, zzef zzef) throws IOException {
        zzbn zzbn = this.zzfo;
        zzdo zzdo = (zzdo) obj;
        zzbn.zzb(i, 3);
        zzef.zza(zzdo, zzbn.zzfz);
        zzbn.zzb(i, 4);
    }

    public final void zzb(int i, List<zzbb> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzfo.zza(i, (zzbb) list.get(i2));
        }
    }

    public final void zzb(int i, List<?> list, zzef zzef) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzef);
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzv(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzq(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzb(int i, boolean z) throws IOException {
        this.zzfo.zzb(i, z);
    }

    public final void zzc(int i, int i2) throws IOException {
        this.zzfo.zzc(i, i2);
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzfo.zzc(i, j);
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zze(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzb(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzd(int i, int i2) throws IOException {
        this.zzfo.zzd(i, i2);
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzf(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzb(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zza(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zze(int i, int i2) throws IOException {
        this.zzfo.zze(i, i2);
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzh(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzd(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zzfo.zzf(i, i2);
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzb(((Float) list.get(i)).floatValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zza(((Float) list.get(i2)).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zza(i, ((Float) list.get(i2)).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzb(((Double) list.get(i)).doubleValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zza(((Double) list.get(i2)).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zza(i, ((Double) list.get(i2)).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzx(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzn(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zzc(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzfo.zza(i, j);
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzb(((Boolean) list.get(i)).booleanValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zza(((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zzb(i, ((Boolean) list.get(i2)).booleanValue());
            i2++;
        }
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzfo.zzc(i, j);
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzt(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzo(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zzd(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzw(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzq(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zzf(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzi(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzd(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zzc(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public final void zzm(int i, int i2) throws IOException {
        this.zzfo.zzf(i, i2);
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzu(((Integer) list.get(i)).intValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzp(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zze(i, ((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public final void zzn(int i, int i2) throws IOException {
        this.zzfo.zzc(i, i2);
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzfo.zzb(i, 2);
            i = 0;
            int i3 = i;
            while (i < list.size()) {
                i3 += zzbn.zzg(((Long) list.get(i)).longValue());
                i++;
            }
            this.zzfo.zzo(i3);
            while (i2 < list.size()) {
                this.zzfo.zzc(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzfo.zzb(i, ((Long) list.get(i2)).longValue());
            i2++;
        }
    }
}
