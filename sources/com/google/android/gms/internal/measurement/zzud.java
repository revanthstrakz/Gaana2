package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzuo.zzc;
import java.io.IOException;
import java.util.Map.Entry;

final class zzud extends zzuc<Object> {
    zzud() {
    }

    /* Access modifiers changed, original: final */
    public final boolean zze(zzvv zzvv) {
        return zzvv instanceof zzc;
    }

    /* Access modifiers changed, original: final */
    public final zzuf<Object> zzw(Object obj) {
        return ((zzc) obj).zzbyj;
    }

    /* Access modifiers changed, original: final */
    public final zzuf<Object> zzx(Object obj) {
        zzc zzc = (zzc) obj;
        if (zzc.zzbyj.isImmutable()) {
            zzc.zzbyj = (zzuf) zzc.zzbyj.clone();
        }
        return zzc.zzbyj;
    }

    /* Access modifiers changed, original: final */
    public final void zzy(Object obj) {
        zzw(obj).zzsw();
    }

    /* Access modifiers changed, original: final */
    public final <UT, UB> UB zza(zzwk zzwk, Object obj, zzub zzub, zzuf<Object> zzuf, UB ub, zzxd<UT, UB> zzxd) throws IOException {
        throw new NoSuchMethodError();
    }

    /* Access modifiers changed, original: final */
    public final int zzb(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzxy zzxy, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* Access modifiers changed, original: final */
    public final Object zza(zzub zzub, zzvv zzvv, int i) {
        return zzub.zza(zzvv, i);
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzwk zzwk, Object obj, zzub zzub, zzuf<Object> zzuf) throws IOException {
        throw new NoSuchMethodError();
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzte zzte, Object obj, zzub zzub, zzuf<Object> zzuf) throws IOException {
        throw new NoSuchMethodError();
    }
}
