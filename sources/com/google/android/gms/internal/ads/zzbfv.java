package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

@zzark
public final class zzbfv extends zzbfk implements zzpn<zzov> {
    private String url;
    private ByteBuffer zzaep;
    private final zzbdy zzeuo;
    private boolean zzexf;
    private final zzbfu zzexg = new zzbfu();
    private final zzbfc zzexh = new zzbfc();
    private boolean zzexi;
    private final Object zzexj = new Object();
    private boolean zzexk;

    public zzbfv(zzbdz zzbdz, zzbdy zzbdy) {
        super(zzbdz);
        this.zzeuo = zzbdy;
    }

    public final /* bridge */ /* synthetic */ void zzc(Object obj, int i) {
    }

    public final /* bridge */ /* synthetic */ void zze(Object obj) {
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean zzadc() {
        return this.zzexk;
    }

    /* Access modifiers changed, original: protected|final */
    public final String zzey(String str) {
        String valueOf = String.valueOf("cache:");
        str = String.valueOf(super.zzey(str));
        return str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
    }

    private final void zzabm() {
        int zzl = (int) this.zzexh.zzl(this.zzaep);
        int round = Math.round(((float) zzl) * (((float) this.zzaep.position()) / ((float) ((int) this.zzexg.zzadb()))));
        zza(this.url, zzey(this.url), (long) round, (long) zzl, round > 0, zzbes.zzacx(), zzbes.zzacy());
    }

    /* JADX WARNING: Missing block: B:20:?, code skipped:
            r9.zzexk = true;
            zzc(r10, r11, (long) ((int) r9.zzexh.zzl(r9.zzaep)));
     */
    public final boolean zzex(java.lang.String r23) {
        /*
        r22 = this;
        r9 = r22;
        r10 = r23;
        r9.url = r10;
        r11 = r22.zzey(r23);
        r12 = "error";
        r15 = new com.google.android.gms.internal.ads.zzpb;	 Catch:{ Exception -> 0x013d }
        r2 = r9.zzeiz;	 Catch:{ Exception -> 0x013d }
        r3 = 0;
        r1 = r9.zzeuo;	 Catch:{ Exception -> 0x013d }
        r5 = r1.zzetn;	 Catch:{ Exception -> 0x013d }
        r1 = r9.zzeuo;	 Catch:{ Exception -> 0x013d }
        r6 = r1.zzetp;	 Catch:{ Exception -> 0x013d }
        r7 = 1;
        r8 = 0;
        r1 = r15;
        r4 = r9;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x013d }
        r1 = r9.zzeuo;	 Catch:{ Exception -> 0x013d }
        r1 = r1.zzets;	 Catch:{ Exception -> 0x013d }
        if (r1 == 0) goto L_0x0033;
    L_0x0026:
        r1 = new com.google.android.gms.internal.ads.zzbep;	 Catch:{ Exception -> 0x0030 }
        r2 = r9.mContext;	 Catch:{ Exception -> 0x0030 }
        r3 = 0;
        r1.<init>(r2, r15, r3, r3);	 Catch:{ Exception -> 0x0030 }
        r15 = r1;
        goto L_0x0033;
    L_0x0030:
        r0 = move-exception;
        goto L_0x0140;
    L_0x0033:
        r1 = android.net.Uri.parse(r23);	 Catch:{ Exception -> 0x013d }
        r2 = new com.google.android.gms.internal.ads.zzoz;	 Catch:{ Exception -> 0x013d }
        r2.<init>(r1);	 Catch:{ Exception -> 0x013d }
        r15.zza(r2);	 Catch:{ Exception -> 0x013d }
        r1 = r9.zzewo;	 Catch:{ Exception -> 0x013d }
        r1 = r1.get();	 Catch:{ Exception -> 0x013d }
        r1 = (com.google.android.gms.internal.ads.zzbdz) r1;	 Catch:{ Exception -> 0x013d }
        if (r1 == 0) goto L_0x004c;
    L_0x0049:
        r1.zza(r11, r9);	 Catch:{ Exception -> 0x0030 }
    L_0x004c:
        r1 = com.google.android.gms.ads.internal.zzbv.zzlm();	 Catch:{ Exception -> 0x013d }
        r2 = r1.currentTimeMillis();	 Catch:{ Exception -> 0x013d }
        r4 = com.google.android.gms.internal.ads.zzaan.zzcox;	 Catch:{ Exception -> 0x013d }
        r5 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ Exception -> 0x013d }
        r4 = r5.zzd(r4);	 Catch:{ Exception -> 0x013d }
        r4 = (java.lang.Long) r4;	 Catch:{ Exception -> 0x013d }
        r4 = r4.longValue();	 Catch:{ Exception -> 0x013d }
        r6 = com.google.android.gms.internal.ads.zzaan.zzcow;	 Catch:{ Exception -> 0x013d }
        r7 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ Exception -> 0x013d }
        r6 = r7.zzd(r6);	 Catch:{ Exception -> 0x013d }
        r6 = (java.lang.Long) r6;	 Catch:{ Exception -> 0x013d }
        r6 = r6.longValue();	 Catch:{ Exception -> 0x013d }
        r8 = r9.zzeuo;	 Catch:{ Exception -> 0x013d }
        r8 = r8.zzetm;	 Catch:{ Exception -> 0x013d }
        r8 = java.nio.ByteBuffer.allocate(r8);	 Catch:{ Exception -> 0x013d }
        r9.zzaep = r8;	 Catch:{ Exception -> 0x013d }
        r8 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r13 = new byte[r8];	 Catch:{ Exception -> 0x013d }
        r16 = r2;
    L_0x0084:
        r14 = r9.zzaep;	 Catch:{ Exception -> 0x013d }
        r14 = r14.remaining();	 Catch:{ Exception -> 0x013d }
        r14 = java.lang.Math.min(r14, r8);	 Catch:{ Exception -> 0x013d }
        r8 = 0;
        r14 = r15.read(r13, r8, r14);	 Catch:{ Exception -> 0x013d }
        r8 = -1;
        if (r14 != r8) goto L_0x00a8;
    L_0x0096:
        r8 = 1;
        r9.zzexk = r8;	 Catch:{ Exception -> 0x0030 }
        r1 = r9.zzexh;	 Catch:{ Exception -> 0x0030 }
        r2 = r9.zzaep;	 Catch:{ Exception -> 0x0030 }
        r1 = r1.zzl(r2);	 Catch:{ Exception -> 0x0030 }
        r1 = (int) r1;	 Catch:{ Exception -> 0x0030 }
        r1 = (long) r1;	 Catch:{ Exception -> 0x0030 }
        r9.zzc(r10, r11, r1);	 Catch:{ Exception -> 0x0030 }
    L_0x00a6:
        r1 = 1;
        goto L_0x00c9;
    L_0x00a8:
        r8 = r9.zzexj;	 Catch:{ Exception -> 0x013d }
        monitor-enter(r8);	 Catch:{ Exception -> 0x013d }
        r18 = r12;
        r12 = r9.zzexf;	 Catch:{ all -> 0x0134 }
        if (r12 != 0) goto L_0x00ba;
    L_0x00b1:
        r12 = r9.zzaep;	 Catch:{ all -> 0x0134 }
        r19 = r15;
        r15 = 0;
        r12.put(r13, r15, r14);	 Catch:{ all -> 0x0134 }
        goto L_0x00bc;
    L_0x00ba:
        r19 = r15;
    L_0x00bc:
        monitor-exit(r8);	 Catch:{ all -> 0x0134 }
        r8 = r9.zzaep;	 Catch:{ Exception -> 0x0138 }
        r8 = r8.remaining();	 Catch:{ Exception -> 0x0138 }
        if (r8 > 0) goto L_0x00ca;
    L_0x00c5:
        r22.zzabm();	 Catch:{ Exception -> 0x0138 }
        goto L_0x00a6;
    L_0x00c9:
        return r1;
    L_0x00ca:
        r8 = r9.zzexf;	 Catch:{ Exception -> 0x0138 }
        if (r8 == 0) goto L_0x00f4;
    L_0x00ce:
        r12 = "externalAbort";
        r1 = new java.io.IOException;	 Catch:{ Exception -> 0x0030 }
        r2 = r9.zzaep;	 Catch:{ Exception -> 0x0030 }
        r2 = r2.limit();	 Catch:{ Exception -> 0x0030 }
        r3 = 35;
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0030 }
        r4.<init>(r3);	 Catch:{ Exception -> 0x0030 }
        r3 = "Precache abort at ";
        r4.append(r3);	 Catch:{ Exception -> 0x0030 }
        r4.append(r2);	 Catch:{ Exception -> 0x0030 }
        r2 = " bytes";
        r4.append(r2);	 Catch:{ Exception -> 0x0030 }
        r2 = r4.toString();	 Catch:{ Exception -> 0x0030 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0030 }
        throw r1;	 Catch:{ Exception -> 0x0030 }
    L_0x00f4:
        r14 = r1.currentTimeMillis();	 Catch:{ Exception -> 0x0138 }
        r20 = r14 - r16;
        r8 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1));
        if (r8 < 0) goto L_0x0103;
    L_0x00fe:
        r22.zzabm();	 Catch:{ Exception -> 0x0138 }
        r16 = r14;
    L_0x0103:
        r20 = r14 - r2;
        r14 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r14 = r14 * r6;
        r8 = (r20 > r14 ? 1 : (r20 == r14 ? 0 : -1));
        if (r8 <= 0) goto L_0x012c;
    L_0x010c:
        r12 = "downloadTimeout";
        r1 = 49;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0030 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0030 }
        r1 = "Timeout exceeded. Limit: ";
        r2.append(r1);	 Catch:{ Exception -> 0x0030 }
        r2.append(r6);	 Catch:{ Exception -> 0x0030 }
        r1 = " sec";
        r2.append(r1);	 Catch:{ Exception -> 0x0030 }
        r1 = r2.toString();	 Catch:{ Exception -> 0x0030 }
        r2 = new java.io.IOException;	 Catch:{ Exception -> 0x0030 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0030 }
        throw r2;	 Catch:{ Exception -> 0x0030 }
    L_0x012c:
        r12 = r18;
        r15 = r19;
        r8 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        goto L_0x0084;
    L_0x0134:
        r0 = move-exception;
        r1 = r0;
        monitor-exit(r8);	 Catch:{ all -> 0x0134 }
        throw r1;	 Catch:{ Exception -> 0x0138 }
    L_0x0138:
        r0 = move-exception;
        r1 = r0;
        r12 = r18;
        goto L_0x0141;
    L_0x013d:
        r0 = move-exception;
        r18 = r12;
    L_0x0140:
        r1 = r0;
    L_0x0141:
        r2 = r1.getClass();
        r2 = r2.getCanonicalName();
        r1 = r1.getMessage();
        r3 = java.lang.String.valueOf(r2);
        r3 = r3.length();
        r4 = 1;
        r13 = r4 + r3;
        r3 = java.lang.String.valueOf(r1);
        r3 = r3.length();
        r13 = r13 + r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r13);
        r3.append(r2);
        r2 = ":";
        r3.append(r2);
        r3.append(r1);
        r1 = r3.toString();
        r2 = 34;
        r3 = java.lang.String.valueOf(r23);
        r3 = r3.length();
        r2 = r2 + r3;
        r3 = java.lang.String.valueOf(r1);
        r3 = r3.length();
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Failed to preload url ";
        r3.append(r2);
        r3.append(r10);
        r2 = " Exception: ";
        r3.append(r2);
        r3.append(r1);
        r2 = r3.toString();
        com.google.android.gms.internal.ads.zzbbd.zzeo(r2);
        r9.zza(r10, r11, r12, r1);
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbfv.zzex(java.lang.String):boolean");
    }

    public final void abort() {
        this.zzexf = true;
    }

    public final ByteBuffer getByteBuffer() {
        synchronized (this.zzexj) {
            if (!(this.zzaep == null || this.zzexi)) {
                this.zzaep.flip();
                this.zzexi = true;
            }
            this.zzexf = true;
        }
        return this.zzaep;
    }

    public final /* synthetic */ void zza(Object obj, zzoz zzoz) {
        zzov zzov = (zzov) obj;
        if (zzov instanceof zzpb) {
            this.zzexg.zza((zzpb) zzov);
        }
    }
}
