package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzhl<T> implements zzhw<T> {
    private final zzhf zzzh;
    private final boolean zzzi;
    private final zzio<?, ?> zzzr;
    private final zzfl<?> zzzs;

    private zzhl(zzio<?, ?> zzio, zzfl<?> zzfl, zzhf zzhf) {
        this.zzzr = zzio;
        this.zzzi = zzfl.zze(zzhf);
        this.zzzs = zzfl;
        this.zzzh = zzhf;
    }

    static <T> zzhl<T> zza(zzio<?, ?> zzio, zzfl<?> zzfl, zzhf zzhf) {
        return new zzhl(zzio, zzfl, zzhf);
    }

    public final T newInstance() {
        return this.zzzh.zzfa().zzff();
    }

    public final boolean equals(T t, T t2) {
        if (this.zzzr.zzt(t).equals(this.zzzr.zzt(t2))) {
            return this.zzzi ? this.zzzs.zzc(t).equals(this.zzzs.zzc(t2)) : true;
        } else {
            return false;
        }
    }

    public final int hashCode(T t) {
        int hashCode = this.zzzr.zzt(t).hashCode();
        return this.zzzi ? (hashCode * 53) + this.zzzs.zzc(t).hashCode() : hashCode;
    }

    public final void zzc(T t, T t2) {
        zzhy.zza(this.zzzr, (Object) t, (Object) t2);
        if (this.zzzi) {
            zzhy.zza(this.zzzs, (Object) t, (Object) t2);
        }
    }

    public final void zza(T t, zzjj zzjj) throws IOException {
        Iterator it = this.zzzs.zzc(t).iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            zzfr zzfr = (zzfr) entry.getKey();
            if (zzfr.zzet() != zzji.MESSAGE || zzfr.zzeu() || zzfr.zzev()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzgk) {
                zzjj.zza(zzfr.zzr(), ((zzgk) entry).zzfs().zzce());
            } else {
                zzjj.zza(zzfr.zzr(), entry.getValue());
            }
        }
        zzio zzio = this.zzzr;
        zzio.zzc(zzio.zzt(t), zzjj);
    }

    public final void zza(T r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.vision.zzei r11) throws java.io.IOException {
        /*
        r6 = this;
        r7 = (com.google.android.gms.internal.vision.zzfy) r7;
        r0 = r7.zzwj;
        r1 = com.google.android.gms.internal.vision.zzip.zzhe();
        if (r0 != r1) goto L_0x0010;
    L_0x000a:
        r0 = com.google.android.gms.internal.vision.zzip.zzhf();
        r7.zzwj = r0;
    L_0x0010:
        r7 = r0;
    L_0x0011:
        if (r9 >= r10) goto L_0x0069;
    L_0x0013:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r8, r9, r11);
        r0 = r11.zzro;
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
        r9 = com.google.android.gms.internal.vision.zzeh.zza(r0, r1, r2, r3, r4, r5);
        goto L_0x0011;
    L_0x002b:
        r9 = com.google.android.gms.internal.vision.zzeh.zza(r0, r8, r2, r10, r11);
        goto L_0x0011;
    L_0x0030:
        r9 = 0;
        r0 = 0;
    L_0x0032:
        if (r2 >= r10) goto L_0x005f;
    L_0x0034:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r8, r2, r11);
        r3 = r11.zzro;
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
        r2 = com.google.android.gms.internal.vision.zzeh.zze(r8, r2, r11);
        r0 = r11.zzrq;
        r0 = (com.google.android.gms.internal.vision.zzeo) r0;
        goto L_0x0032;
    L_0x004d:
        if (r5 != 0) goto L_0x0056;
    L_0x004f:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r8, r2, r11);
        r9 = r11.zzro;
        goto L_0x0032;
    L_0x0056:
        r4 = 12;
        if (r3 == r4) goto L_0x005f;
    L_0x005a:
        r2 = com.google.android.gms.internal.vision.zzeh.zza(r3, r8, r2, r10, r11);
        goto L_0x0032;
    L_0x005f:
        if (r0 == 0) goto L_0x0067;
    L_0x0061:
        r9 = r9 << 3;
        r9 = r9 | r1;
        r7.zzb(r9, r0);
    L_0x0067:
        r9 = r2;
        goto L_0x0011;
    L_0x0069:
        if (r9 == r10) goto L_0x0070;
    L_0x006b:
        r7 = com.google.android.gms.internal.vision.zzgf.zzfo();
        throw r7;
    L_0x0070:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhl.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzei):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x008b A:{SYNTHETIC} */
    public final void zza(T r11, com.google.android.gms.internal.vision.zzhv r12, com.google.android.gms.internal.vision.zzfk r13) throws java.io.IOException {
        /*
        r10 = this;
        r0 = r10.zzzr;
        r1 = r10.zzzs;
        r2 = r0.zzu(r11);
        r3 = r1.zzd(r11);
    L_0x000c:
        r4 = r12.zzcn();	 Catch:{ all -> 0x008f }
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r4 != r5) goto L_0x0019;
    L_0x0015:
        r0.zzf(r11, r2);
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
        r5 = r10.zzzh;	 Catch:{ all -> 0x008f }
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
        r4 = r12.zzco();	 Catch:{ all -> 0x008f }
        goto L_0x0089;
    L_0x003e:
        r4 = 0;
        r6 = 0;
        r7 = r4;
        r4 = r6;
    L_0x0042:
        r8 = r12.zzcn();	 Catch:{ all -> 0x008f }
        if (r8 == r5) goto L_0x0070;
    L_0x0048:
        r8 = r12.getTag();	 Catch:{ all -> 0x008f }
        r9 = 16;
        if (r8 != r9) goto L_0x005b;
    L_0x0050:
        r7 = r12.zzcx();	 Catch:{ all -> 0x008f }
        r6 = r10.zzzh;	 Catch:{ all -> 0x008f }
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
        r4 = r12.zzcw();	 Catch:{ all -> 0x008f }
        goto L_0x0042;
    L_0x006a:
        r8 = r12.zzco();	 Catch:{ all -> 0x008f }
        if (r8 != 0) goto L_0x0042;
    L_0x0070:
        r5 = r12.getTag();	 Catch:{ all -> 0x008f }
        r8 = 12;
        if (r5 == r8) goto L_0x007d;
    L_0x0078:
        r12 = com.google.android.gms.internal.vision.zzgf.zzfl();	 Catch:{ all -> 0x008f }
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
        r0.zzf(r11, r2);
        return;
    L_0x008f:
        r12 = move-exception;
        r0.zzf(r11, r2);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhl.zza(java.lang.Object, com.google.android.gms.internal.vision.zzhv, com.google.android.gms.internal.vision.zzfk):void");
    }

    public final void zze(T t) {
        this.zzzr.zze(t);
        this.zzzs.zze((Object) t);
    }

    public final boolean zzr(T t) {
        return this.zzzs.zzc(t).isInitialized();
    }

    public final int zzp(T t) {
        zzio zzio = this.zzzr;
        int zzv = 0 + zzio.zzv(zzio.zzt(t));
        return this.zzzi ? zzv + this.zzzs.zzc(t).zzer() : zzv;
    }
}
