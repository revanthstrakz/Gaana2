package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Map.Entry;

abstract class zzuc<T extends zzuh<T>> {
    zzuc() {
    }

    public abstract Object zza(zzub zzub, zzvv zzvv, int i);

    public abstract <UT, UB> UB zza(zzwk zzwk, Object obj, zzub zzub, zzuf<T> zzuf, UB ub, zzxd<UT, UB> zzxd) throws IOException;

    public abstract void zza(zzte zzte, Object obj, zzub zzub, zzuf<T> zzuf) throws IOException;

    public abstract void zza(zzwk zzwk, Object obj, zzub zzub, zzuf<T> zzuf) throws IOException;

    public abstract void zza(zzxy zzxy, Entry<?, ?> entry) throws IOException;

    public abstract int zzb(Entry<?, ?> entry);

    public abstract boolean zze(zzvv zzvv);

    public abstract zzuf<T> zzw(Object obj);

    public abstract zzuf<T> zzx(Object obj);

    public abstract void zzy(Object obj);
}
