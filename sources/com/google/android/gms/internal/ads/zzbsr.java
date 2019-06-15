package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzbsr<T> implements zzbtc<T> {
    private final zzbsl zzfsm;
    private final boolean zzfsn;
    private final zzbtu<?, ?> zzfsw;
    private final zzbqr<?> zzfsx;

    private zzbsr(zzbtu<?, ?> zzbtu, zzbqr<?> zzbqr, zzbsl zzbsl) {
        this.zzfsw = zzbtu;
        this.zzfsn = zzbqr.zzh(zzbsl);
        this.zzfsx = zzbqr;
        this.zzfsm = zzbsl;
    }

    static <T> zzbsr<T> zza(zzbtu<?, ?> zzbtu, zzbqr<?> zzbqr, zzbsl zzbsl) {
        return new zzbsr(zzbtu, zzbqr, zzbsl);
    }

    public final T newInstance() {
        return this.zzfsm.zzamu().zzamz();
    }

    public final boolean equals(T t, T t2) {
        if (this.zzfsw.zzag(t).equals(this.zzfsw.zzag(t2))) {
            return this.zzfsn ? this.zzfsx.zzq(t).equals(this.zzfsx.zzq(t2)) : true;
        } else {
            return false;
        }
    }

    public final int hashCode(T t) {
        int hashCode = this.zzfsw.zzag(t).hashCode();
        return this.zzfsn ? (hashCode * 53) + this.zzfsx.zzq(t).hashCode() : hashCode;
    }

    public final void zzd(T t, T t2) {
        zzbte.zza(this.zzfsw, (Object) t, (Object) t2);
        if (this.zzfsn) {
            zzbte.zza(this.zzfsx, (Object) t, (Object) t2);
        }
    }

    public final void zza(T t, zzbup zzbup) throws IOException {
        Iterator it = this.zzfsx.zzq(t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzbqw zzbqw = (zzbqw) entry.getKey();
            if (zzbqw.zzamm() != zzbuo.MESSAGE || zzbqw.zzamn() || zzbqw.zzamo()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzbrq) {
                zzbup.zzb(zzbqw.zzom(), ((zzbrq) entry).zzann().zzakf());
            } else {
                zzbup.zzb(zzbqw.zzom(), entry.getValue());
            }
        }
        zzbtu zzbtu = this.zzfsw;
        zzbtu.zzc(zzbtu.zzag(t), zzbup);
    }

    public final void zza(T r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.ads.zzbpr r11) throws java.io.IOException {
        /*
        r6 = this;
        r7 = (com.google.android.gms.internal.ads.zzbrd) r7;
        r0 = r7.zzfpu;
        r1 = com.google.android.gms.internal.ads.zzbtv.zzaoz();
        if (r0 != r1) goto L_0x0010;
    L_0x000a:
        r0 = com.google.android.gms.internal.ads.zzbtv.zzapa();
        r7.zzfpu = r0;
    L_0x0010:
        r7 = r0;
    L_0x0011:
        if (r9 >= r10) goto L_0x0069;
    L_0x0013:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r8, r9, r11);
        r0 = r11.zzfld;
        r9 = 11;
        r1 = 2;
        if (r0 == r9) goto L_0x0030;
    L_0x001e:
        r9 = r0 & 7;
        if (r9 != r1) goto L_0x002b;
    L_0x0022:
        r1 = r8;
        r3 = r10;
        r4 = r7;
        r5 = r11;
        r9 = com.google.android.gms.internal.ads.zzbpq.zza(r0, r1, r2, r3, r4, r5);
        goto L_0x0011;
    L_0x002b:
        r9 = com.google.android.gms.internal.ads.zzbpq.zza(r0, r8, r2, r10, r11);
        goto L_0x0011;
    L_0x0030:
        r9 = 0;
        r0 = 0;
    L_0x0032:
        if (r2 >= r10) goto L_0x005f;
    L_0x0034:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r8, r2, r11);
        r3 = r11.zzfld;
        r4 = r3 >>> 3;
        r5 = r3 & 7;
        switch(r4) {
            case 2: goto L_0x004d;
            case 3: goto L_0x0042;
            default: goto L_0x0041;
        };
    L_0x0041:
        goto L_0x0056;
    L_0x0042:
        if (r5 != r1) goto L_0x0056;
    L_0x0044:
        r2 = com.google.android.gms.internal.ads.zzbpq.zze(r8, r2, r11);
        r0 = r11.zzflf;
        r0 = (com.google.android.gms.internal.ads.zzbpu) r0;
        goto L_0x0032;
    L_0x004d:
        if (r5 != 0) goto L_0x0056;
    L_0x004f:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r8, r2, r11);
        r9 = r11.zzfld;
        goto L_0x0032;
    L_0x0056:
        r4 = 12;
        if (r3 == r4) goto L_0x005f;
    L_0x005a:
        r2 = com.google.android.gms.internal.ads.zzbpq.zza(r3, r8, r2, r10, r11);
        goto L_0x0032;
    L_0x005f:
        if (r0 == 0) goto L_0x0067;
    L_0x0061:
        r9 = r9 << 3;
        r9 = r9 | r1;
        r7.zzc(r9, r0);
    L_0x0067:
        r9 = r2;
        goto L_0x0011;
    L_0x0069:
        if (r9 == r10) goto L_0x0070;
    L_0x006b:
        r7 = com.google.android.gms.internal.ads.zzbrl.zzanj();
        throw r7;
    L_0x0070:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsr.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.zzbpr):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x008b A:{SYNTHETIC} */
    public final void zza(T r11, com.google.android.gms.internal.ads.zzbtb r12, com.google.android.gms.internal.ads.zzbqq r13) throws java.io.IOException {
        /*
        r10 = this;
        r0 = r10.zzfsw;
        r1 = r10.zzfsx;
        r2 = r0.zzah(r11);
        r3 = r1.zzr(r11);
    L_0x000c:
        r4 = r12.zzals();	 Catch:{ all -> 0x008f }
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
        r5 = r10.zzfsm;	 Catch:{ all -> 0x008f }
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
        r4 = r12.zzalt();	 Catch:{ all -> 0x008f }
        goto L_0x0089;
    L_0x003e:
        r4 = 0;
        r6 = 0;
        r7 = r4;
        r4 = r6;
    L_0x0042:
        r8 = r12.zzals();	 Catch:{ all -> 0x008f }
        if (r8 == r5) goto L_0x0070;
    L_0x0048:
        r8 = r12.getTag();	 Catch:{ all -> 0x008f }
        r9 = 16;
        if (r8 != r9) goto L_0x005b;
    L_0x0050:
        r7 = r12.zzald();	 Catch:{ all -> 0x008f }
        r6 = r10.zzfsm;	 Catch:{ all -> 0x008f }
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
        r4 = r12.zzalc();	 Catch:{ all -> 0x008f }
        goto L_0x0042;
    L_0x006a:
        r8 = r12.zzalt();	 Catch:{ all -> 0x008f }
        if (r8 != 0) goto L_0x0042;
    L_0x0070:
        r5 = r12.getTag();	 Catch:{ all -> 0x008f }
        r8 = 12;
        if (r5 == r8) goto L_0x007d;
    L_0x0078:
        r12 = com.google.android.gms.internal.ads.zzbrl.zzang();	 Catch:{ all -> 0x008f }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbsr.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbtb, com.google.android.gms.internal.ads.zzbqq):void");
    }

    public final void zzs(T t) {
        this.zzfsw.zzs(t);
        this.zzfsx.zzs(t);
    }

    public final boolean zzae(T t) {
        return this.zzfsx.zzq(t).isInitialized();
    }

    public final int zzac(T t) {
        zzbtu zzbtu = this.zzfsw;
        int zzai = 0 + zzbtu.zzai(zzbtu.zzag(t));
        return this.zzfsn ? zzai + this.zzfsx.zzq(t).zzamk() : zzai;
    }
}
