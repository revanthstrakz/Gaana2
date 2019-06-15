package com.google.android.gms.internal.ads;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzarf implements UncaughtExceptionHandler {
    private final /* synthetic */ UncaughtExceptionHandler zzdvk;
    private final /* synthetic */ zzare zzdvl;

    zzarf(zzare zzare, UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzdvl = zzare;
        this.zzdvk = uncaughtExceptionHandler;
    }

    /* JADX WARNING: Failed to extract finally block: empty outs */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0011 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:8:?, code skipped:
            com.google.android.gms.internal.ads.zzbbd.e("AdMob exception reporter failed reporting the exception.");
     */
    /* JADX WARNING: Missing block: B:10:0x0018, code skipped:
            if (r2.zzdvk != null) goto L_0x001a;
     */
    /* JADX WARNING: Missing block: B:11:0x001a, code skipped:
            r2.zzdvk.uncaughtException(r3, r4);
     */
    /* JADX WARNING: Missing block: B:12:0x001f, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:15:0x0023, code skipped:
            if (r2.zzdvk != null) goto L_0x0025;
     */
    /* JADX WARNING: Missing block: B:16:0x0025, code skipped:
            r2.zzdvk.uncaughtException(r3, r4);
     */
    public final void uncaughtException(java.lang.Thread r3, java.lang.Throwable r4) {
        /*
        r2 = this;
        r0 = r2.zzdvl;	 Catch:{ Throwable -> 0x0011 }
        r0.zza(r3, r4);	 Catch:{ Throwable -> 0x0011 }
        r0 = r2.zzdvk;
        if (r0 == 0) goto L_0x0020;
    L_0x0009:
        r0 = r2.zzdvk;
        r0.uncaughtException(r3, r4);
        return;
    L_0x000f:
        r0 = move-exception;
        goto L_0x0021;
    L_0x0011:
        r0 = "AdMob exception reporter failed reporting the exception.";
        com.google.android.gms.internal.ads.zzbbd.e(r0);	 Catch:{ all -> 0x000f }
        r0 = r2.zzdvk;
        if (r0 == 0) goto L_0x0020;
    L_0x001a:
        r0 = r2.zzdvk;
        r0.uncaughtException(r3, r4);
        return;
    L_0x0020:
        return;
    L_0x0021:
        r1 = r2.zzdvk;
        if (r1 == 0) goto L_0x002a;
    L_0x0025:
        r1 = r2.zzdvk;
        r1.uncaughtException(r3, r4);
    L_0x002a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzarf.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}
