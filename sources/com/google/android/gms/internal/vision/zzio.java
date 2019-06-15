package com.google.android.gms.internal.vision;

import java.io.IOException;

abstract class zzio<T, B> {
    zzio() {
    }

    public abstract void zza(B b, int i, long j);

    public abstract void zza(B b, int i, zzeo zzeo);

    public abstract void zza(B b, int i, T t);

    public abstract void zza(T t, zzjj zzjj) throws IOException;

    public abstract boolean zza(zzhv zzhv);

    public abstract void zzb(B b, int i, long j);

    public abstract void zzc(B b, int i, int i2);

    public abstract void zzc(T t, zzjj zzjj) throws IOException;

    public abstract void zze(Object obj);

    public abstract void zze(Object obj, T t);

    public abstract void zzf(Object obj, B b);

    public abstract T zzg(T t, T t2);

    public abstract B zzhd();

    public abstract T zzm(B b);

    public abstract int zzp(T t);

    public abstract T zzt(Object obj);

    public abstract B zzu(Object obj);

    public abstract int zzv(T t);

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    public final boolean zza(B r7, com.google.android.gms.internal.vision.zzhv r8) throws java.io.IOException {
        /*
        r6 = this;
        r0 = r8.getTag();
        r1 = r0 >>> 3;
        r0 = r0 & 7;
        r2 = 1;
        switch(r0) {
            case 0: goto L_0x0055;
            case 1: goto L_0x004d;
            case 2: goto L_0x0045;
            case 3: goto L_0x001b;
            case 4: goto L_0x0019;
            case 5: goto L_0x0011;
            default: goto L_0x000c;
        };
    L_0x000c:
        r7 = com.google.android.gms.internal.vision.zzgf.zzfm();
        throw r7;
    L_0x0011:
        r8 = r8.zzct();
        r6.zzc(r7, r1, r8);
        return r2;
    L_0x0019:
        r7 = 0;
        return r7;
    L_0x001b:
        r0 = r6.zzhd();
        r3 = r1 << 3;
        r3 = r3 | 4;
    L_0x0023:
        r4 = r8.zzcn();
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r4 == r5) goto L_0x0032;
    L_0x002c:
        r4 = r6.zza(r0, r8);
        if (r4 != 0) goto L_0x0023;
    L_0x0032:
        r8 = r8.getTag();
        if (r3 == r8) goto L_0x003d;
    L_0x0038:
        r7 = com.google.android.gms.internal.vision.zzgf.zzfl();
        throw r7;
    L_0x003d:
        r8 = r6.zzm(r0);
        r6.zza(r7, r1, r8);
        return r2;
    L_0x0045:
        r8 = r8.zzcw();
        r6.zza(r7, r1, r8);
        return r2;
    L_0x004d:
        r3 = r8.zzcs();
        r6.zzb(r7, r1, r3);
        return r2;
    L_0x0055:
        r3 = r8.zzcq();
        r6.zza(r7, r1, r3);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzio.zza(java.lang.Object, com.google.android.gms.internal.vision.zzhv):boolean");
    }
}
