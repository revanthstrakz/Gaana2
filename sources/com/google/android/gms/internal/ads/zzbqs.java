package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbrd.zzc;
import java.io.IOException;
import java.util.Map.Entry;

final class zzbqs extends zzbqr<Object> {
    zzbqs() {
    }

    /* Access modifiers changed, original: final */
    public final boolean zzh(zzbsl zzbsl) {
        return zzbsl instanceof zzc;
    }

    /* Access modifiers changed, original: final */
    public final zzbqu<Object> zzq(Object obj) {
        return ((zzc) obj).zzfqa;
    }

    /* Access modifiers changed, original: final */
    public final void zza(Object obj, zzbqu<Object> zzbqu) {
        ((zzc) obj).zzfqa = zzbqu;
    }

    /* Access modifiers changed, original: final */
    public final zzbqu<Object> zzr(Object obj) {
        zzbqu<Object> zzq = zzq(obj);
        if (!zzq.isImmutable()) {
            return zzq;
        }
        zzbqu zzbqu = (zzbqu) zzq.clone();
        zza(obj, zzbqu);
        return zzbqu;
    }

    /* Access modifiers changed, original: final */
    public final void zzs(Object obj) {
        zzq(obj).zzakj();
    }

    /* Access modifiers changed, original: final */
    public final <UT, UB> UB zza(zzbtb zzbtb, Object obj, zzbqq zzbqq, zzbqu<Object> zzbqu, UB ub, zzbtu<UT, UB> zzbtu) throws IOException {
        throw new NoSuchMethodError();
    }

    /* Access modifiers changed, original: final */
    public final int zza(Entry<?, ?> entry) {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzbup zzbup, Entry<?, ?> entry) throws IOException {
        entry.getKey();
        throw new NoSuchMethodError();
    }

    /* Access modifiers changed, original: final */
    public final Object zza(zzbqq zzbqq, zzbsl zzbsl, int i) {
        return zzbqq.zza(zzbsl, i);
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzbtb zzbtb, Object obj, zzbqq zzbqq, zzbqu<Object> zzbqu) throws IOException {
        throw new NoSuchMethodError();
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzbpu zzbpu, Object obj, zzbqq zzbqq, zzbqu<Object> zzbqu) throws IOException {
        throw new NoSuchMethodError();
    }
}
