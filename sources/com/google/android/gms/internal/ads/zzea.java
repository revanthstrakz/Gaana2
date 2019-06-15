package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ExecutionException;

public final class zzea extends zzeu {
    private static final zzev<zzbv> zzuk = new zzev();
    private final Context zzuj;
    private zzbi zzul = null;

    public zzea(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2, Context context, zzbi zzbi) {
        super(zzdl, str, str2, zzbl, i, 27);
        this.zzuj = context;
        this.zzul = zzbi;
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039  */
    public final void zzas() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
        r9 = this;
        r0 = zzuk;
        r1 = r9.zzuj;
        r1 = r1.getPackageName();
        r0 = r0.zzp(r1);
        monitor-enter(r0);
        r1 = r0.get();	 Catch:{ all -> 0x0110 }
        r1 = (com.google.android.gms.internal.ads.zzbv) r1;	 Catch:{ all -> 0x0110 }
        r2 = 1;
        r3 = 0;
        if (r1 == 0) goto L_0x0036;
    L_0x0017:
        r4 = r1.zzdq;	 Catch:{ all -> 0x0110 }
        r4 = com.google.android.gms.internal.ads.zzds.zzo(r4);	 Catch:{ all -> 0x0110 }
        if (r4 != 0) goto L_0x0036;
    L_0x001f:
        r4 = r1.zzdq;	 Catch:{ all -> 0x0110 }
        r5 = "E";
        r4 = r4.equals(r5);	 Catch:{ all -> 0x0110 }
        if (r4 != 0) goto L_0x0036;
    L_0x0029:
        r1 = r1.zzdq;	 Catch:{ all -> 0x0110 }
        r4 = "0000000000000000000000000000000000000000000000000000000000000000";
        r1 = r1.equals(r4);	 Catch:{ all -> 0x0110 }
        if (r1 == 0) goto L_0x0034;
    L_0x0033:
        goto L_0x0036;
    L_0x0034:
        r1 = r3;
        goto L_0x0037;
    L_0x0036:
        r1 = r2;
    L_0x0037:
        if (r1 == 0) goto L_0x00db;
    L_0x0039:
        r1 = r9.zzul;	 Catch:{ all -> 0x0110 }
        r1 = 0;
        r4 = com.google.android.gms.internal.ads.zzds.zzo(r1);	 Catch:{ all -> 0x0110 }
        r5 = 3;
        r6 = 2;
        if (r4 != 0) goto L_0x0046;
    L_0x0044:
        r4 = 4;
        goto L_0x0089;
    L_0x0046:
        r4 = r9.zzul;	 Catch:{ all -> 0x0110 }
        com.google.android.gms.internal.ads.zzds.zzo(r1);	 Catch:{ all -> 0x0110 }
        r4 = java.lang.Boolean.valueOf(r3);	 Catch:{ all -> 0x0110 }
        r4 = r4.booleanValue();	 Catch:{ all -> 0x0110 }
        if (r4 == 0) goto L_0x0088;
    L_0x0055:
        r4 = r9.zzqo;	 Catch:{ all -> 0x0110 }
        r4 = r4.zzai();	 Catch:{ all -> 0x0110 }
        if (r4 == 0) goto L_0x0083;
    L_0x005d:
        r4 = com.google.android.gms.internal.ads.zzaan.zzctt;	 Catch:{ all -> 0x0110 }
        r7 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x0110 }
        r4 = r7.zzd(r4);	 Catch:{ all -> 0x0110 }
        r4 = (java.lang.Boolean) r4;	 Catch:{ all -> 0x0110 }
        r4 = r4.booleanValue();	 Catch:{ all -> 0x0110 }
        if (r4 == 0) goto L_0x0083;
    L_0x006f:
        r4 = com.google.android.gms.internal.ads.zzaan.zzctu;	 Catch:{ all -> 0x0110 }
        r7 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x0110 }
        r4 = r7.zzd(r4);	 Catch:{ all -> 0x0110 }
        r4 = (java.lang.Boolean) r4;	 Catch:{ all -> 0x0110 }
        r4 = r4.booleanValue();	 Catch:{ all -> 0x0110 }
        if (r4 == 0) goto L_0x0083;
    L_0x0081:
        r4 = r2;
        goto L_0x0084;
    L_0x0083:
        r4 = r3;
    L_0x0084:
        if (r4 == 0) goto L_0x0088;
    L_0x0086:
        r4 = r5;
        goto L_0x0089;
    L_0x0088:
        r4 = r6;
    L_0x0089:
        r7 = r9.zzuw;	 Catch:{ all -> 0x0110 }
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0110 }
        r8 = r9.zzuj;	 Catch:{ all -> 0x0110 }
        r5[r3] = r8;	 Catch:{ all -> 0x0110 }
        if (r4 != r6) goto L_0x0094;
    L_0x0093:
        r3 = r2;
    L_0x0094:
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ all -> 0x0110 }
        r5[r2] = r3;	 Catch:{ all -> 0x0110 }
        r2 = com.google.android.gms.internal.ads.zzaan.zzctn;	 Catch:{ all -> 0x0110 }
        r3 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x0110 }
        r2 = r3.zzd(r2);	 Catch:{ all -> 0x0110 }
        r5[r6] = r2;	 Catch:{ all -> 0x0110 }
        r2 = r7.invoke(r1, r5);	 Catch:{ all -> 0x0110 }
        r2 = (java.lang.String) r2;	 Catch:{ all -> 0x0110 }
        r3 = new com.google.android.gms.internal.ads.zzbv;	 Catch:{ all -> 0x0110 }
        r3.<init>(r2);	 Catch:{ all -> 0x0110 }
        r2 = r3.zzdq;	 Catch:{ all -> 0x0110 }
        r2 = com.google.android.gms.internal.ads.zzds.zzo(r2);	 Catch:{ all -> 0x0110 }
        if (r2 != 0) goto L_0x00c3;
    L_0x00b9:
        r2 = r3.zzdq;	 Catch:{ all -> 0x0110 }
        r5 = "E";
        r2 = r2.equals(r5);	 Catch:{ all -> 0x0110 }
        if (r2 == 0) goto L_0x00d8;
    L_0x00c3:
        switch(r4) {
            case 3: goto L_0x00cc;
            case 4: goto L_0x00c7;
            default: goto L_0x00c6;
        };	 Catch:{ all -> 0x0110 }
    L_0x00c6:
        goto L_0x00d8;
    L_0x00c7:
        r1 = r1.zzdq;	 Catch:{ all -> 0x0110 }
        r3.zzdq = r1;	 Catch:{ all -> 0x0110 }
        goto L_0x00d8;
    L_0x00cc:
        r1 = r9.zzat();	 Catch:{ all -> 0x0110 }
        r2 = com.google.android.gms.internal.ads.zzds.zzo(r1);	 Catch:{ all -> 0x0110 }
        if (r2 != 0) goto L_0x00d8;
    L_0x00d6:
        r3.zzdq = r1;	 Catch:{ all -> 0x0110 }
    L_0x00d8:
        r0.set(r3);	 Catch:{ all -> 0x0110 }
    L_0x00db:
        r1 = r0.get();	 Catch:{ all -> 0x0110 }
        r1 = (com.google.android.gms.internal.ads.zzbv) r1;	 Catch:{ all -> 0x0110 }
        monitor-exit(r0);	 Catch:{ all -> 0x0110 }
        r2 = r9.zzun;
        monitor-enter(r2);
        if (r1 == 0) goto L_0x010c;
    L_0x00e7:
        r0 = r9.zzun;	 Catch:{ all -> 0x010a }
        r3 = r1.zzdq;	 Catch:{ all -> 0x010a }
        r0.zzdq = r3;	 Catch:{ all -> 0x010a }
        r0 = r9.zzun;	 Catch:{ all -> 0x010a }
        r3 = r1.zzit;	 Catch:{ all -> 0x010a }
        r3 = java.lang.Long.valueOf(r3);	 Catch:{ all -> 0x010a }
        r0.zzev = r3;	 Catch:{ all -> 0x010a }
        r0 = r9.zzun;	 Catch:{ all -> 0x010a }
        r3 = r1.zzds;	 Catch:{ all -> 0x010a }
        r0.zzds = r3;	 Catch:{ all -> 0x010a }
        r0 = r9.zzun;	 Catch:{ all -> 0x010a }
        r3 = r1.zzdt;	 Catch:{ all -> 0x010a }
        r0.zzdt = r3;	 Catch:{ all -> 0x010a }
        r0 = r9.zzun;	 Catch:{ all -> 0x010a }
        r1 = r1.zzdu;	 Catch:{ all -> 0x010a }
        r0.zzdu = r1;	 Catch:{ all -> 0x010a }
        goto L_0x010c;
    L_0x010a:
        r0 = move-exception;
        goto L_0x010e;
    L_0x010c:
        monitor-exit(r2);	 Catch:{ all -> 0x010a }
        return;
    L_0x010e:
        monitor-exit(r2);	 Catch:{ all -> 0x010a }
        throw r0;
    L_0x0110:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0110 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzea.zzas():void");
    }

    private final String zzat() {
        try {
            if (this.zzqo.zzal() != null) {
                this.zzqo.zzal().get();
            }
            zzbl zzak = this.zzqo.zzak();
            if (!(zzak == null || zzak.zzdq == null)) {
                return zzak.zzdq;
            }
        } catch (InterruptedException | ExecutionException unused) {
        }
        return null;
    }
}
