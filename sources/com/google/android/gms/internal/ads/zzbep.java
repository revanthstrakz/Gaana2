package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;

@zzark
public final class zzbep implements zzov {
    private boolean isOpen;
    private Uri uri;
    private InputStream zzevf;
    private final zzov zzevg;
    @Nullable
    private final zzpn<zzov> zzevh;
    private final zzbeq zzevi;
    private final Context zzsp;

    public zzbep(Context context, zzov zzov, zzpn<zzov> zzpn, zzbeq zzbeq) {
        this.zzsp = context;
        this.zzevg = zzov;
        this.zzevh = zzpn;
        this.zzevi = zzbeq;
    }

    public final void close() throws IOException {
        if (this.isOpen) {
            this.isOpen = false;
            this.uri = null;
            if (this.zzevf != null) {
                IOUtils.closeQuietly(this.zzevf);
                this.zzevf = null;
            } else {
                this.zzevg.close();
            }
            if (this.zzevh != null) {
                this.zzevh.zze(this);
                return;
            }
            return;
        }
        throw new IOException("Attempt to close an already closed CacheDataSource.");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x00ab=Splitter:B:21:0x00ab, B:24:0x00de=Splitter:B:24:0x00de} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00de */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00ab */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:21:0x00ab, B:24:0x00de] */
    /* JADX WARNING: Missing block: B:19:0x00a8, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:20:0x00a9, code skipped:
            r2 = r0;
     */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
            r5.cancel(true);
            java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Missing block: B:23:0x00b5, code skipped:
            r7 = com.google.android.gms.ads.internal.zzbv.zzlm().elapsedRealtime() - r10;
            r1.zzevi.zzb(false, r7);
            r3 = new java.lang.StringBuilder(44);
            r3.append("Cache connection took ");
            r3.append(r7);
            r3.append("ms");
            com.google.android.gms.internal.ads.zzaxz.v(r3.toString());
     */
    /* JADX WARNING: Missing block: B:25:?, code skipped:
            r5.cancel(true);
     */
    /* JADX WARNING: Missing block: B:26:0x00e1, code skipped:
            r7 = com.google.android.gms.ads.internal.zzbv.zzlm().elapsedRealtime() - r10;
            r1.zzevi.zzb(false, r7);
            r3 = new java.lang.StringBuilder(44);
            r3.append("Cache connection took ");
            r3.append(r7);
            r3.append("ms");
            com.google.android.gms.internal.ads.zzaxz.v(r3.toString());
     */
    /* JADX WARNING: Missing block: B:27:0x010a, code skipped:
            r5 = com.google.android.gms.ads.internal.zzbv.zzlm().elapsedRealtime() - r10;
            r1.zzevi.zzb(false, r5);
            r3 = new java.lang.StringBuilder(44);
            r3.append("Cache connection took ");
            r3.append(r5);
            r3.append("ms");
            com.google.android.gms.internal.ads.zzaxz.v(r3.toString());
     */
    public final long zza(com.google.android.gms.internal.ads.zzoz r20) throws java.io.IOException {
        /*
        r19 = this;
        r1 = r19;
        r2 = r20;
        r3 = r1.isOpen;
        if (r3 == 0) goto L_0x0010;
    L_0x0008:
        r2 = new java.io.IOException;
        r3 = "Attempt to open an already open CacheDataSource.";
        r2.<init>(r3);
        throw r2;
    L_0x0010:
        r3 = 1;
        r1.isOpen = r3;
        r4 = r2.uri;
        r1.uri = r4;
        r4 = r1.zzevh;
        if (r4 == 0) goto L_0x0020;
    L_0x001b:
        r4 = r1.zzevh;
        r4.zza(r1, r2);
    L_0x0020:
        r4 = r2.uri;
        r4 = com.google.android.gms.internal.ads.zzty.zzd(r4);
        r5 = com.google.android.gms.internal.ads.zzaan.zzcvv;
        r6 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r5 = r6.zzd(r5);
        r5 = (java.lang.Boolean) r5;
        r5 = r5.booleanValue();
        r6 = -1;
        if (r5 == 0) goto L_0x0133;
    L_0x003a:
        if (r4 == 0) goto L_0x0151;
    L_0x003c:
        r8 = r2.zzaha;
        r4.zzcab = r8;
        r5 = r4.zzcaa;
        if (r5 == 0) goto L_0x0051;
    L_0x0044:
        r5 = com.google.android.gms.internal.ads.zzaan.zzcvx;
        r8 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r5 = r8.zzd(r5);
        r5 = (java.lang.Long) r5;
        goto L_0x005d;
    L_0x0051:
        r5 = com.google.android.gms.internal.ads.zzaan.zzcvw;
        r8 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r5 = r8.zzd(r5);
        r5 = (java.lang.Long) r5;
    L_0x005d:
        r8 = r5.longValue();
        r5 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r10 = r5.elapsedRealtime();
        com.google.android.gms.ads.internal.zzbv.zzmb();
        r5 = r1.zzsp;
        r5 = com.google.android.gms.internal.ads.zzul.zza(r5, r4);
        r12 = 0;
        r13 = 44;
        r14 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ ExecutionException | TimeoutException -> 0x00de, ExecutionException | TimeoutException -> 0x00de, InterruptedException -> 0x00ab }
        r8 = r5.get(r8, r14);	 Catch:{ ExecutionException | TimeoutException -> 0x00de, ExecutionException | TimeoutException -> 0x00de, InterruptedException -> 0x00ab }
        r8 = (java.io.InputStream) r8;	 Catch:{ ExecutionException | TimeoutException -> 0x00de, ExecutionException | TimeoutException -> 0x00de, InterruptedException -> 0x00ab }
        r1.zzevf = r8;	 Catch:{ ExecutionException | TimeoutException -> 0x00de, ExecutionException | TimeoutException -> 0x00de, InterruptedException -> 0x00ab }
        r2 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r4 = r2.elapsedRealtime();
        r8 = r4 - r10;
        r2 = r1.zzevi;
        r2.zzb(r3, r8);
        r2 = new java.lang.StringBuilder;
        r2.<init>(r13);
        r3 = "Cache connection took ";
        r2.append(r3);
        r2.append(r8);
        r3 = "ms";
        r2.append(r3);
        r2 = r2.toString();
        com.google.android.gms.internal.ads.zzaxz.v(r2);
        return r6;
    L_0x00a8:
        r0 = move-exception;
        r2 = r0;
        goto L_0x010a;
    L_0x00ab:
        r5.cancel(r3);	 Catch:{ all -> 0x00a8 }
        r3 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x00a8 }
        r3.interrupt();	 Catch:{ all -> 0x00a8 }
        r3 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r5 = r3.elapsedRealtime();
        r7 = r5 - r10;
        r3 = r1.zzevi;
        r3.zzb(r12, r7);
        r3 = new java.lang.StringBuilder;
        r3.<init>(r13);
        r5 = "Cache connection took ";
        r3.append(r5);
        r3.append(r7);
        r5 = "ms";
        r3.append(r5);
        r3 = r3.toString();
        com.google.android.gms.internal.ads.zzaxz.v(r3);
        goto L_0x0151;
    L_0x00de:
        r5.cancel(r3);	 Catch:{ all -> 0x00a8 }
        r3 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r5 = r3.elapsedRealtime();
        r7 = r5 - r10;
        r3 = r1.zzevi;
        r3.zzb(r12, r7);
        r3 = new java.lang.StringBuilder;
        r3.<init>(r13);
        r5 = "Cache connection took ";
        r3.append(r5);
        r3.append(r7);
        r5 = "ms";
        r3.append(r5);
        r3 = r3.toString();
        com.google.android.gms.internal.ads.zzaxz.v(r3);
        goto L_0x0151;
    L_0x010a:
        r3 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r3 = r3.elapsedRealtime();
        r5 = r3 - r10;
        r3 = r1.zzevi;
        r3.zzb(r12, r5);
        r3 = new java.lang.StringBuilder;
        r3.<init>(r13);
        r4 = "Cache connection took ";
        r3.append(r4);
        r3.append(r5);
        r4 = "ms";
        r3.append(r4);
        r3 = r3.toString();
        com.google.android.gms.internal.ads.zzaxz.v(r3);
        throw r2;
    L_0x0133:
        r3 = 0;
        if (r4 == 0) goto L_0x0142;
    L_0x0136:
        r8 = r2.zzaha;
        r4.zzcab = r8;
        r3 = com.google.android.gms.ads.internal.zzbv.zzll();
        r3 = r3.zza(r4);
    L_0x0142:
        if (r3 == 0) goto L_0x0151;
    L_0x0144:
        r5 = r3.zzoe();
        if (r5 == 0) goto L_0x0151;
    L_0x014a:
        r2 = r3.zzof();
        r1.zzevf = r2;
        return r6;
    L_0x0151:
        if (r4 == 0) goto L_0x0171;
    L_0x0153:
        r3 = new com.google.android.gms.internal.ads.zzoz;
        r4 = r4.url;
        r9 = android.net.Uri.parse(r4);
        r10 = r2.zzbft;
        r11 = r2.zzbfu;
        r13 = r2.zzaha;
        r4 = r2.zzcc;
        r6 = r2.zzcb;
        r2 = r2.flags;
        r8 = r3;
        r15 = r4;
        r17 = r6;
        r18 = r2;
        r8.<init>(r9, r10, r11, r13, r15, r17, r18);
        r2 = r3;
    L_0x0171:
        r3 = r1.zzevg;
        r2 = r3.zza(r2);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbep.zza(com.google.android.gms.internal.ads.zzoz):long");
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.isOpen) {
            int read;
            if (this.zzevf != null) {
                read = this.zzevf.read(bArr, i, i2);
            } else {
                read = this.zzevg.read(bArr, i, i2);
            }
            if (this.zzevh != null) {
                this.zzevh.zzc(this, read);
            }
            return read;
        }
        throw new IOException("Attempt to read closed CacheDataSource.");
    }

    public final Uri getUri() {
        return this.uri;
    }
}
