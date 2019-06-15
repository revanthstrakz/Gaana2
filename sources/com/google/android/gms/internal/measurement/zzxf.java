package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zzxf extends zzxd<zzxe, zzxe> {
    zzxf() {
    }

    /* Access modifiers changed, original: final */
    public final boolean zza(zzwk zzwk) {
        return false;
    }

    private static void zza(Object obj, zzxe zzxe) {
        ((zzuo) obj).zzbyd = zzxe;
    }

    /* Access modifiers changed, original: final */
    public final void zzy(Object obj) {
        ((zzuo) obj).zzbyd.zzsw();
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ int zzai(Object obj) {
        return ((zzxe) obj).zzvx();
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ int zzan(Object obj) {
        return ((zzxe) obj).zzyn();
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ Object zzh(Object obj, Object obj2) {
        zzxe zzxe = (zzxe) obj;
        zzxe zzxe2 = (zzxe) obj2;
        if (zzxe2.equals(zzxe.zzyl())) {
            return zzxe;
        }
        return zzxe.zza(zzxe, zzxe2);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzc(Object obj, zzxy zzxy) throws IOException {
        ((zzxe) obj).zza(zzxy);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzg(Object obj, Object obj2) {
        zza(obj, (zzxe) obj2);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ Object zzam(Object obj) {
        zzxe zzxe = ((zzuo) obj).zzbyd;
        if (zzxe != zzxe.zzyl()) {
            return zzxe;
        }
        zzxe = zzxe.zzym();
        zza(obj, zzxe);
        return zzxe;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ Object zzal(Object obj) {
        return ((zzuo) obj).zzbyd;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzf(Object obj, Object obj2) {
        zza(obj, (zzxe) obj2);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ Object zzaf(Object obj) {
        zzxe zzxe = (zzxe) obj;
        zzxe.zzsw();
        return zzxe;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ Object zzyk() {
        return zzxe.zzym();
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzxe) obj).zzb((i << 3) | 1, Long.valueOf(j));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzc(Object obj, int i, int i2) {
        ((zzxe) obj).zzb((i << 3) | 5, Integer.valueOf(i2));
    }
}
