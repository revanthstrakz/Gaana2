package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzwa<T> implements zzwl<T> {
    private final zzvv zzcau;
    private final boolean zzcav;
    private final zzxd<?, ?> zzcbe;
    private final zzuc<?> zzcbf;

    private zzwa(zzxd<?, ?> zzxd, zzuc<?> zzuc, zzvv zzvv) {
        this.zzcbe = zzxd;
        this.zzcav = zzuc.zze(zzvv);
        this.zzcbf = zzuc;
        this.zzcau = zzvv;
    }

    static <T> zzwa<T> zza(zzxd<?, ?> zzxd, zzuc<?> zzuc, zzvv zzvv) {
        return new zzwa(zzxd, zzuc, zzvv);
    }

    public final T newInstance() {
        return this.zzcau.zzwi().zzwn();
    }

    public final boolean equals(T t, T t2) {
        if (this.zzcbe.zzal(t).equals(this.zzcbe.zzal(t2))) {
            return this.zzcav ? this.zzcbf.zzw(t).equals(this.zzcbf.zzw(t2)) : true;
        } else {
            return false;
        }
    }

    public final int hashCode(T t) {
        int hashCode = this.zzcbe.zzal(t).hashCode();
        return this.zzcav ? (hashCode * 53) + this.zzcbf.zzw(t).hashCode() : hashCode;
    }

    public final void zzd(T t, T t2) {
        zzwn.zza(this.zzcbe, (Object) t, (Object) t2);
        if (this.zzcav) {
            zzwn.zza(this.zzcbf, (Object) t, (Object) t2);
        }
    }

    public final void zza(T t, zzxy zzxy) throws IOException {
        Iterator it = this.zzcbf.zzw(t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzuh zzuh = (zzuh) entry.getKey();
            if (zzuh.zzwa() != zzxx.MESSAGE || zzuh.zzwb() || zzuh.zzwc()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzva) {
                zzxy.zza(zzuh.zzc(), ((zzva) entry).zzxa().zztw());
            } else {
                zzxy.zza(zzuh.zzc(), entry.getValue());
            }
        }
        zzxd zzxd = this.zzcbe;
        zzxd.zzc(zzxd.zzal(t), zzxy);
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x008b A:{SYNTHETIC} */
    public final void zza(T r11, com.google.android.gms.internal.measurement.zzwk r12, com.google.android.gms.internal.measurement.zzub r13) throws java.io.IOException {
        /*
        r10 = this;
        r0 = r10.zzcbe;
        r1 = r10.zzcbf;
        r2 = r0.zzam(r11);
        r3 = r1.zzx(r11);
    L_0x000c:
        r4 = r12.zzvh();	 Catch:{ all -> 0x008f }
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r4 != r5) goto L_0x0019;
    L_0x0015:
        r0.zzg(r11, r2);
        return;
    L_0x0019:
        r4 = r12.getTag();	 Catch:{ all -> 0x008f }
        r6 = 11;
        if (r4 == r6) goto L_0x003e;
    L_0x0021:
        r5 = r4 & 7;
        r6 = 2;
        if (r5 != r6) goto L_0x0039;
    L_0x0026:
        r5 = r10.zzcau;	 Catch:{ all -> 0x008f }
        r4 = r4 >>> 3;
        r4 = r1.zza(r13, r5, r4);	 Catch:{ all -> 0x008f }
        if (r4 == 0) goto L_0x0034;
    L_0x0030:
        r1.zza(r12, r4, r13, r3);	 Catch:{ all -> 0x008f }
        goto L_0x0088;
    L_0x0034:
        r4 = r0.zza(r2, r12);	 Catch:{ all -> 0x008f }
        goto L_0x0089;
    L_0x0039:
        r4 = r12.zzvi();	 Catch:{ all -> 0x008f }
        goto L_0x0089;
    L_0x003e:
        r4 = 0;
        r6 = 0;
        r7 = r4;
        r4 = r6;
    L_0x0042:
        r8 = r12.zzvh();	 Catch:{ all -> 0x008f }
        if (r8 == r5) goto L_0x0070;
    L_0x0048:
        r8 = r12.getTag();	 Catch:{ all -> 0x008f }
        r9 = 16;
        if (r8 != r9) goto L_0x005b;
    L_0x0050:
        r7 = r12.zzus();	 Catch:{ all -> 0x008f }
        r6 = r10.zzcau;	 Catch:{ all -> 0x008f }
        r6 = r1.zza(r13, r6, r7);	 Catch:{ all -> 0x008f }
        goto L_0x0042;
    L_0x005b:
        r9 = 26;
        if (r8 != r9) goto L_0x006a;
    L_0x005f:
        if (r6 == 0) goto L_0x0065;
    L_0x0061:
        r1.zza(r12, r6, r13, r3);	 Catch:{ all -> 0x008f }
        goto L_0x0042;
    L_0x0065:
        r4 = r12.zzur();	 Catch:{ all -> 0x008f }
        goto L_0x0042;
    L_0x006a:
        r8 = r12.zzvi();	 Catch:{ all -> 0x008f }
        if (r8 != 0) goto L_0x0042;
    L_0x0070:
        r5 = r12.getTag();	 Catch:{ all -> 0x008f }
        r8 = 12;
        if (r5 == r8) goto L_0x007d;
    L_0x0078:
        r12 = com.google.android.gms.internal.measurement.zzuv.zzwt();	 Catch:{ all -> 0x008f }
        throw r12;	 Catch:{ all -> 0x008f }
    L_0x007d:
        if (r4 == 0) goto L_0x0088;
    L_0x007f:
        if (r6 == 0) goto L_0x0085;
    L_0x0081:
        r1.zza(r4, r6, r13, r3);	 Catch:{ all -> 0x008f }
        goto L_0x0088;
    L_0x0085:
        r0.zza(r2, r7, r4);	 Catch:{ all -> 0x008f }
    L_0x0088:
        r4 = 1;
    L_0x0089:
        if (r4 != 0) goto L_0x000c;
    L_0x008b:
        r0.zzg(r11, r2);
        return;
    L_0x008f:
        r12 = move-exception;
        r0.zzg(r11, r2);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwa.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzwk, com.google.android.gms.internal.measurement.zzub):void");
    }

    public final void zzy(T t) {
        this.zzcbe.zzy(t);
        this.zzcbf.zzy(t);
    }

    public final boolean zzaj(T t) {
        return this.zzcbf.zzw(t).isInitialized();
    }

    public final int zzai(T t) {
        zzxd zzxd = this.zzcbe;
        int zzan = 0 + zzxd.zzan(zzxd.zzal(t));
        return this.zzcav ? zzan + this.zzcbf.zzw(t).zzvy() : zzan;
    }
}
