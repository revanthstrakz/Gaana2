package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;

@zzark
@TargetApi(16)
public final class zzbfw extends zzbfk implements zzbez {
    private String url;
    private boolean zzexf;
    private zzbes zzexl;
    private Exception zzexm;
    private boolean zzexn;

    public zzbfw(zzbdz zzbdz, zzbdy zzbdy) {
        super(zzbdz);
        this.zzexl = new zzbes(zzbdz.getContext(), zzbdy);
        this.zzexl.zza((zzbez) this);
    }

    public final void zzdd(int i) {
    }

    public final void zzp(int i, int i2) {
    }

    public final void zzb(boolean z, long j) {
        zzbdz zzbdz = (zzbdz) this.zzewo.get();
        if (zzbdz != null) {
            zzbcg.zzepo.execute(new zzbfx(zzbdz, z, j));
        }
    }

    public final void zza(String str, Exception exception) {
        this.zzexm = exception;
        zzbbd.zzc("Precache error", exception);
        zzfa(str);
    }

    public final void zzda(int i) {
        this.zzexl.zzacz().zzdg(i);
    }

    public final void zzcz(int i) {
        this.zzexl.zzacz().zzdf(i);
    }

    public final void zzdb(int i) {
        this.zzexl.zzacz().zzdb(i);
    }

    public final void zzdc(int i) {
        this.zzexl.zzacz().zzdc(i);
    }

    public final void release() {
        if (this.zzexl != null) {
            this.zzexl.zza(null);
            this.zzexl.release();
        }
        super.release();
    }

    /* Access modifiers changed, original: protected|final */
    public final String zzey(String str) {
        String valueOf = String.valueOf("cache:");
        str = String.valueOf(super.zzey(str));
        return str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
    }

    /* JADX WARNING: Missing block: B:26:0x009c, code skipped:
            if (r11.zzexm == null) goto L_0x00a3;
     */
    /* JADX WARNING: Missing block: B:27:0x009e, code skipped:
            r1 = "badUrl";
     */
    /* JADX WARNING: Missing block: B:30:0x00a2, code skipped:
            throw r11.zzexm;
     */
    /* JADX WARNING: Missing block: B:32:?, code skipped:
            r1 = "externalAbort";
     */
    /* JADX WARNING: Missing block: B:35:0x00ac, code skipped:
            throw new java.io.IOException("Abort requested before buffering finished. ");
     */
    public final boolean zzex(java.lang.String r34) {
        /*
        r33 = this;
        r11 = r33;
        r12 = r34;
        r11.url = r12;
        r13 = r33.zzey(r34);
        r14 = "error";
        r1 = android.net.Uri.parse(r34);	 Catch:{ Exception -> 0x015b }
        r2 = r11.zzexl;	 Catch:{ Exception -> 0x015b }
        r3 = r11.zzeiz;	 Catch:{ Exception -> 0x015b }
        r2.zza(r1, r3);	 Catch:{ Exception -> 0x015b }
        r1 = r11.zzewo;	 Catch:{ Exception -> 0x015b }
        r1 = r1.get();	 Catch:{ Exception -> 0x015b }
        r1 = (com.google.android.gms.internal.ads.zzbdz) r1;	 Catch:{ Exception -> 0x015b }
        if (r1 == 0) goto L_0x0028;
    L_0x0021:
        r1.zza(r13, r11);	 Catch:{ Exception -> 0x0025 }
        goto L_0x0028;
    L_0x0025:
        r0 = move-exception;
        goto L_0x015e;
    L_0x0028:
        r10 = com.google.android.gms.ads.internal.zzbv.zzlm();	 Catch:{ Exception -> 0x015b }
        r16 = r10.currentTimeMillis();	 Catch:{ Exception -> 0x015b }
        r1 = com.google.android.gms.internal.ads.zzaan.zzcox;	 Catch:{ Exception -> 0x015b }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ Exception -> 0x015b }
        r1 = r2.zzd(r1);	 Catch:{ Exception -> 0x015b }
        r1 = (java.lang.Long) r1;	 Catch:{ Exception -> 0x015b }
        r8 = r1.longValue();	 Catch:{ Exception -> 0x015b }
        r1 = com.google.android.gms.internal.ads.zzaan.zzcow;	 Catch:{ Exception -> 0x015b }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ Exception -> 0x015b }
        r1 = r2.zzd(r1);	 Catch:{ Exception -> 0x015b }
        r1 = (java.lang.Long) r1;	 Catch:{ Exception -> 0x015b }
        r1 = r1.longValue();	 Catch:{ Exception -> 0x015b }
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r6 = r1 * r3;
        r1 = com.google.android.gms.internal.ads.zzaan.zzcov;	 Catch:{ Exception -> 0x015b }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ Exception -> 0x015b }
        r1 = r2.zzd(r1);	 Catch:{ Exception -> 0x015b }
        r1 = (java.lang.Integer) r1;	 Catch:{ Exception -> 0x015b }
        r1 = r1.intValue();	 Catch:{ Exception -> 0x015b }
        r4 = (long) r1;	 Catch:{ Exception -> 0x015b }
        r1 = -1;
    L_0x0067:
        monitor-enter(r33);	 Catch:{ Exception -> 0x015b }
        r18 = r10.currentTimeMillis();	 Catch:{ all -> 0x0153 }
        r20 = r18 - r16;
        r3 = (r20 > r6 ? 1 : (r20 == r6 ? 0 : -1));
        if (r3 <= 0) goto L_0x0096;
    L_0x0072:
        r1 = "downloadTimeout";
        r2 = new java.io.IOException;	 Catch:{ all -> 0x0092 }
        r3 = 47;
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0092 }
        r4.<init>(r3);	 Catch:{ all -> 0x0092 }
        r3 = "Timeout reached. Limit: ";
        r4.append(r3);	 Catch:{ all -> 0x0092 }
        r4.append(r6);	 Catch:{ all -> 0x0092 }
        r3 = " ms";
        r4.append(r3);	 Catch:{ all -> 0x0092 }
        r3 = r4.toString();	 Catch:{ all -> 0x0092 }
        r2.<init>(r3);	 Catch:{ all -> 0x0092 }
        throw r2;	 Catch:{ all -> 0x0092 }
    L_0x0092:
        r0 = move-exception;
        r14 = r1;
        goto L_0x0156;
    L_0x0096:
        r3 = r11.zzexf;	 Catch:{ all -> 0x0153 }
        if (r3 == 0) goto L_0x00ad;
    L_0x009a:
        r1 = r11.zzexm;	 Catch:{ all -> 0x0159 }
        if (r1 == 0) goto L_0x00a3;
    L_0x009e:
        r1 = "badUrl";
        r2 = r11.zzexm;	 Catch:{ all -> 0x0092 }
        throw r2;	 Catch:{ all -> 0x0092 }
    L_0x00a3:
        r1 = "externalAbort";
        r2 = new java.io.IOException;	 Catch:{ all -> 0x0092 }
        r3 = "Abort requested before buffering finished. ";
        r2.<init>(r3);	 Catch:{ all -> 0x0092 }
        throw r2;	 Catch:{ all -> 0x0092 }
    L_0x00ad:
        r3 = r11.zzexn;	 Catch:{ all -> 0x0153 }
        r18 = 1;
        if (r3 == 0) goto L_0x00b6;
    L_0x00b3:
        monitor-exit(r33);	 Catch:{ all -> 0x0159 }
        goto L_0x0127;
    L_0x00b6:
        r3 = r11.zzexl;	 Catch:{ all -> 0x0153 }
        r3 = r3.zzacw();	 Catch:{ all -> 0x0153 }
        if (r3 != 0) goto L_0x00c8;
    L_0x00be:
        r1 = "exoPlayerReleased";
        r2 = new java.io.IOException;	 Catch:{ all -> 0x0092 }
        r3 = "ExoPlayer was released during preloading.";
        r2.<init>(r3);	 Catch:{ all -> 0x0092 }
        throw r2;	 Catch:{ all -> 0x0092 }
    L_0x00c8:
        r22 = r14;
        r14 = r3.getDuration();	 Catch:{ all -> 0x014e }
        r19 = 0;
        r21 = (r14 > r19 ? 1 : (r14 == r19 ? 0 : -1));
        if (r21 <= 0) goto L_0x012c;
    L_0x00d4:
        r23 = r3.getBufferedPosition();	 Catch:{ all -> 0x014e }
        r3 = (r23 > r1 ? 1 : (r23 == r1 ? 0 : -1));
        if (r3 == 0) goto L_0x0107;
    L_0x00dc:
        r1 = (r23 > r19 ? 1 : (r23 == r19 ? 0 : -1));
        if (r1 <= 0) goto L_0x00e3;
    L_0x00e0:
        r21 = r18;
        goto L_0x00e5;
    L_0x00e3:
        r21 = 0;
    L_0x00e5:
        r25 = com.google.android.gms.internal.ads.zzbes.zzacx();	 Catch:{ all -> 0x014e }
        r26 = com.google.android.gms.internal.ads.zzbes.zzacy();	 Catch:{ all -> 0x014e }
        r1 = r11;
        r2 = r12;
        r3 = r13;
        r27 = r4;
        r4 = r23;
        r29 = r6;
        r6 = r14;
        r31 = r8;
        r8 = r21;
        r9 = r25;
        r21 = r10;
        r10 = r26;
        r1.zza(r2, r3, r4, r6, r8, r9, r10);	 Catch:{ all -> 0x014e }
        r1 = r23;
        goto L_0x010f;
    L_0x0107:
        r27 = r4;
        r29 = r6;
        r31 = r8;
        r21 = r10;
    L_0x010f:
        r3 = (r23 > r14 ? 1 : (r23 == r14 ? 0 : -1));
        if (r3 < 0) goto L_0x0118;
    L_0x0113:
        r11.zzc(r12, r13, r14);	 Catch:{ all -> 0x014e }
        monitor-exit(r33);	 Catch:{ all -> 0x014e }
        goto L_0x0127;
    L_0x0118:
        r3 = r11.zzexl;	 Catch:{ all -> 0x014e }
        r3 = r3.getBytesTransferred();	 Catch:{ all -> 0x014e }
        r5 = (r3 > r27 ? 1 : (r3 == r27 ? 0 : -1));
        if (r5 < 0) goto L_0x0128;
    L_0x0122:
        r3 = (r23 > r19 ? 1 : (r23 == r19 ? 0 : -1));
        if (r3 <= 0) goto L_0x0128;
    L_0x0126:
        monitor-exit(r33);	 Catch:{ all -> 0x014e }
    L_0x0127:
        return r18;
    L_0x0128:
        r3 = r1;
        r1 = r31;
        goto L_0x0134;
    L_0x012c:
        r27 = r4;
        r29 = r6;
        r21 = r10;
        r3 = r1;
        r1 = r8;
    L_0x0134:
        r11.wait(r1);	 Catch:{ InterruptedException -> 0x0144 }
        monitor-exit(r33);	 Catch:{ all -> 0x014e }
        r8 = r1;
        r1 = r3;
        r10 = r21;
        r14 = r22;
        r4 = r27;
        r6 = r29;
        goto L_0x0067;
    L_0x0144:
        r1 = "interrupted";
        r2 = new java.io.IOException;	 Catch:{ all -> 0x0092 }
        r3 = "Wait interrupted.";
        r2.<init>(r3);	 Catch:{ all -> 0x0092 }
        throw r2;	 Catch:{ all -> 0x0092 }
    L_0x014e:
        r0 = move-exception;
        r1 = r0;
        r14 = r22;
        goto L_0x0157;
    L_0x0153:
        r0 = move-exception;
        r22 = r14;
    L_0x0156:
        r1 = r0;
    L_0x0157:
        monitor-exit(r33);	 Catch:{ all -> 0x0159 }
        throw r1;	 Catch:{ Exception -> 0x0025 }
    L_0x0159:
        r0 = move-exception;
        goto L_0x0156;
    L_0x015b:
        r0 = move-exception;
        r22 = r14;
    L_0x015e:
        r1 = r0;
        r2 = r1.getMessage();
        r3 = 34;
        r4 = java.lang.String.valueOf(r34);
        r4 = r4.length();
        r3 = r3 + r4;
        r4 = java.lang.String.valueOf(r2);
        r4 = r4.length();
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;
        r4.<init>(r3);
        r3 = "Failed to preload url ";
        r4.append(r3);
        r4.append(r12);
        r3 = " Exception: ";
        r4.append(r3);
        r4.append(r2);
        r2 = r4.toString();
        com.google.android.gms.internal.ads.zzbbd.zzeo(r2);
        r33.release();
        r1 = zzb(r14, r1);
        r11.zza(r12, r13, r14, r1);
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbfw.zzex(java.lang.String):boolean");
    }

    public final void abort() {
        zzfa(null);
    }

    private final void zzfa(String str) {
        synchronized (this) {
            this.zzexf = true;
            notify();
            release();
        }
        if (this.url != null) {
            String zzey = zzey(this.url);
            if (this.zzexm != null) {
                zza(this.url, zzey, "badUrl", zzb(str, this.zzexm));
                return;
            }
            zza(this.url, zzey, "externalAbort", "Programmatic precache abort.");
        }
    }

    public final zzbes zzadd() {
        synchronized (this) {
            this.zzexn = true;
            notify();
        }
        this.zzexl.zza(null);
        zzbes zzbes = this.zzexl;
        this.zzexl = null;
        return zzbes;
    }

    private static String zzb(String str, Exception exception) {
        String canonicalName = exception.getClass().getCanonicalName();
        String message = exception.getMessage();
        StringBuilder stringBuilder = new StringBuilder(((2 + String.valueOf(str).length()) + String.valueOf(canonicalName).length()) + String.valueOf(message).length());
        stringBuilder.append(str);
        stringBuilder.append("/");
        stringBuilder.append(canonicalName);
        stringBuilder.append(":");
        stringBuilder.append(message);
        return stringBuilder.toString();
    }
}
