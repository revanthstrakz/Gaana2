package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzark
public final class zzala implements zzakp {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final zzalg zzbma;
    private final boolean zzbum;
    private final zzakr zzdmn;
    private final boolean zzdms;
    private final boolean zzdmt;
    private final zzasi zzdnh;
    private final long zzdni;
    private final int zzdnj;
    private boolean zzdnk = false;
    private final Map<zzbcb<zzakx>, zzaku> zzdnl = new HashMap();
    private final String zzdnm;
    private List<zzakx> zzdnn = new ArrayList();

    public zzala(Context context, zzasi zzasi, zzalg zzalg, zzakr zzakr, boolean z, boolean z2, String str, long j, long j2, int i, boolean z3) {
        this.mContext = context;
        this.zzdnh = zzasi;
        this.zzbma = zzalg;
        this.zzdmn = zzakr;
        this.zzbum = z;
        this.zzdms = z2;
        this.zzdnm = str;
        this.mStartTime = j;
        this.zzdni = j2;
        this.zzdnj = 2;
        this.zzdmt = z3;
    }

    public final zzakx zzh(List<zzakq> list) {
        ArrayList arrayList;
        zzbbd.zzdn("Starting mediation.");
        ArrayList arrayList2 = new ArrayList();
        zzwf zzwf = this.zzdnh.zzbst;
        int[] iArr = new int[2];
        if (zzwf.zzckm != null) {
            zzbv.zzlz();
            if (zzakz.zza(this.zzdnm, iArr)) {
                int i = 0;
                int i2 = iArr[0];
                int i3 = iArr[1];
                zzwf[] zzwfArr = zzwf.zzckm;
                int length = zzwfArr.length;
                while (i < length) {
                    zzwf zzwf2 = zzwfArr[i];
                    if (i2 == zzwf2.width && i3 == zzwf2.height) {
                        zzwf = zzwf2;
                        break;
                    }
                    i++;
                }
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzakq zzakq = (zzakq) it.next();
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(zzakq.zzdkv);
            zzbbd.zzen(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            Iterator it2 = zzakq.zzdkw.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                Context context = this.mContext;
                zzalg zzalg = this.zzbma;
                zzakr zzakr = this.zzdmn;
                zzwb zzwb = this.zzdnh.zzdwg;
                zzbbi zzbbi = this.zzdnh.zzbsp;
                boolean z = this.zzbum;
                boolean z2 = this.zzdms;
                zzacp zzacp = this.zzdnh.zzbti;
                Iterator it3 = it;
                List list2 = this.zzdnh.zzbtt;
                Iterator it4 = it2;
                ArrayList arrayList3 = arrayList2;
                boolean z3 = z;
                zzakq zzakq2 = zzakq;
                zzwf zzwf3 = zzwf;
                zzbbi zzbbi2 = zzbbi;
                zzaku zzaku = new zzaku(context, str2, zzalg, zzakr, zzakq2, zzwb, zzwf3, zzbbi2, z3, z2, zzacp, list2, this.zzdnh.zzdwu, this.zzdnh.zzdxp, this.zzdmt);
                zzbcb zza = zzayf.zza(new zzalb(this, zzaku));
                this.zzdnl.put(zza, zzaku);
                arrayList = arrayList3;
                arrayList.add(zza);
                arrayList2 = arrayList;
                it = it3;
                it2 = it4;
            }
        }
        arrayList = arrayList2;
        if (this.zzdnj != 2) {
            return zzi(arrayList);
        }
        return zzj(arrayList);
    }

    /* JADX WARNING: Missing block: B:9:0x0010, code skipped:
            r4 = r4.iterator();
     */
    /* JADX WARNING: Missing block: B:11:0x0018, code skipped:
            if (r4.hasNext() == false) goto L_0x003c;
     */
    /* JADX WARNING: Missing block: B:12:0x001a, code skipped:
            r0 = (com.google.android.gms.internal.ads.zzbcb) r4.next();
     */
    /* JADX WARNING: Missing block: B:14:?, code skipped:
            r1 = (com.google.android.gms.internal.ads.zzakx) r0.get();
            r3.zzdnn.add(r1);
     */
    /* JADX WARNING: Missing block: B:15:0x002b, code skipped:
            if (r1 == null) goto L_0x0014;
     */
    /* JADX WARNING: Missing block: B:17:0x002f, code skipped:
            if (r1.zzdna != 0) goto L_0x0014;
     */
    /* JADX WARNING: Missing block: B:18:0x0031, code skipped:
            zza(r0);
     */
    /* JADX WARNING: Missing block: B:19:0x0034, code skipped:
            return r1;
     */
    /* JADX WARNING: Missing block: B:20:0x0035, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:21:0x0036, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzc("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Missing block: B:22:0x003c, code skipped:
            zza(null);
     */
    /* JADX WARNING: Missing block: B:23:0x0046, code skipped:
            return new com.google.android.gms.internal.ads.zzakx(1);
     */
    private final com.google.android.gms.internal.ads.zzakx zzi(java.util.List<com.google.android.gms.internal.ads.zzbcb<com.google.android.gms.internal.ads.zzakx>> r4) {
        /*
        r3 = this;
        r0 = r3.mLock;
        monitor-enter(r0);
        r1 = r3.zzdnk;	 Catch:{ all -> 0x0047 }
        if (r1 == 0) goto L_0x000f;
    L_0x0007:
        r4 = new com.google.android.gms.internal.ads.zzakx;	 Catch:{ all -> 0x0047 }
        r1 = -1;
        r4.<init>(r1);	 Catch:{ all -> 0x0047 }
        monitor-exit(r0);	 Catch:{ all -> 0x0047 }
        return r4;
    L_0x000f:
        monitor-exit(r0);	 Catch:{ all -> 0x0047 }
        r4 = r4.iterator();
    L_0x0014:
        r0 = r4.hasNext();
        if (r0 == 0) goto L_0x003c;
    L_0x001a:
        r0 = r4.next();
        r0 = (com.google.android.gms.internal.ads.zzbcb) r0;
        r1 = r0.get();	 Catch:{ InterruptedException | ExecutionException -> 0x0035, InterruptedException | ExecutionException -> 0x0035 }
        r1 = (com.google.android.gms.internal.ads.zzakx) r1;	 Catch:{ InterruptedException | ExecutionException -> 0x0035, InterruptedException | ExecutionException -> 0x0035 }
        r2 = r3.zzdnn;	 Catch:{ InterruptedException | ExecutionException -> 0x0035, InterruptedException | ExecutionException -> 0x0035 }
        r2.add(r1);	 Catch:{ InterruptedException | ExecutionException -> 0x0035, InterruptedException | ExecutionException -> 0x0035 }
        if (r1 == 0) goto L_0x0014;
    L_0x002d:
        r2 = r1.zzdna;	 Catch:{ InterruptedException | ExecutionException -> 0x0035, InterruptedException | ExecutionException -> 0x0035 }
        if (r2 != 0) goto L_0x0014;
    L_0x0031:
        r3.zza(r0);	 Catch:{ InterruptedException | ExecutionException -> 0x0035, InterruptedException | ExecutionException -> 0x0035 }
        return r1;
    L_0x0035:
        r0 = move-exception;
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.internal.ads.zzbbd.zzc(r1, r0);
        goto L_0x0014;
    L_0x003c:
        r4 = 0;
        r3.zza(r4);
        r4 = new com.google.android.gms.internal.ads.zzakx;
        r0 = 1;
        r4.<init>(r0);
        return r4;
    L_0x0047:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0047 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzala.zzi(java.util.List):com.google.android.gms.internal.ads.zzakx");
    }

    /* JADX WARNING: Missing block: B:10:0x0018, code skipped:
            if (r14.zzdmn.zzdmb == -1) goto L_0x001f;
     */
    /* JADX WARNING: Missing block: B:11:0x001a, code skipped:
            r0 = r14.zzdmn.zzdmb;
     */
    /* JADX WARNING: Missing block: B:12:0x001f, code skipped:
            r0 = com.comscore.streaming.Constants.HEARTBEAT_STAGE_ONE_INTERVAL;
     */
    /* JADX WARNING: Missing block: B:13:0x0021, code skipped:
            r15 = r15.iterator();
            r3 = null;
            r4 = -1;
            r1 = r0;
            r0 = null;
     */
    /* JADX WARNING: Missing block: B:15:0x002d, code skipped:
            if (r15.hasNext() == false) goto L_0x00af;
     */
    /* JADX WARNING: Missing block: B:16:0x002f, code skipped:
            r5 = (com.google.android.gms.internal.ads.zzbcb) r15.next();
            r6 = com.google.android.gms.ads.internal.zzbv.zzlm().currentTimeMillis();
     */
    /* JADX WARNING: Missing block: B:17:0x0041, code skipped:
            if (r1 != 0) goto L_0x0054;
     */
    /* JADX WARNING: Missing block: B:20:0x0047, code skipped:
            if (r5.isDone() == false) goto L_0x0054;
     */
    /* JADX WARNING: Missing block: B:21:0x0049, code skipped:
            r10 = (com.google.android.gms.internal.ads.zzakx) r5.get();
     */
    /* JADX WARNING: Missing block: B:24:0x0052, code skipped:
            r5 = move-exception;
     */
    /* JADX WARNING: Missing block: B:26:0x0054, code skipped:
            r10 = (com.google.android.gms.internal.ads.zzakx) r5.get(r1, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Missing block: B:27:0x005c, code skipped:
            r14.zzdnn.add(r10);
     */
    /* JADX WARNING: Missing block: B:28:0x0061, code skipped:
            if (r10 == null) goto L_0x0078;
     */
    /* JADX WARNING: Missing block: B:30:0x0065, code skipped:
            if (r10.zzdna != 0) goto L_0x0078;
     */
    /* JADX WARNING: Missing block: B:31:0x0067, code skipped:
            r11 = r10.zzdnf;
     */
    /* JADX WARNING: Missing block: B:32:0x0069, code skipped:
            if (r11 == null) goto L_0x0078;
     */
    /* JADX WARNING: Missing block: B:34:0x006f, code skipped:
            if (r11.zzur() <= r4) goto L_0x0078;
     */
    /* JADX WARNING: Missing block: B:36:0x0075, code skipped:
            r3 = r5;
            r0 = r10;
            r4 = r11.zzur();
     */
    /* JADX WARNING: Missing block: B:37:0x0078, code skipped:
            r1 = java.lang.Math.max(r1 - (com.google.android.gms.ads.internal.zzbv.zzlm().currentTimeMillis() - r6), 0);
     */
    /* JADX WARNING: Missing block: B:39:?, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzc("Exception while processing an adapter; continuing with other adapters", r5);
     */
    /* JADX WARNING: Missing block: B:40:0x008e, code skipped:
            r1 = java.lang.Math.max(r1 - (com.google.android.gms.ads.internal.zzbv.zzlm().currentTimeMillis() - r6), 0);
     */
    /* JADX WARNING: Missing block: B:41:0x009f, code skipped:
            java.lang.Math.max(r1 - (com.google.android.gms.ads.internal.zzbv.zzlm().currentTimeMillis() - r6), 0);
     */
    /* JADX WARNING: Missing block: B:43:0x00af, code skipped:
            zza(r3);
     */
    /* JADX WARNING: Missing block: B:44:0x00b2, code skipped:
            if (r0 != null) goto L_0x00bb;
     */
    /* JADX WARNING: Missing block: B:46:0x00ba, code skipped:
            return new com.google.android.gms.internal.ads.zzakx(1);
     */
    /* JADX WARNING: Missing block: B:47:0x00bb, code skipped:
            return r0;
     */
    private final com.google.android.gms.internal.ads.zzakx zzj(java.util.List<com.google.android.gms.internal.ads.zzbcb<com.google.android.gms.internal.ads.zzakx>> r15) {
        /*
        r14 = this;
        r0 = r14.mLock;
        monitor-enter(r0);
        r1 = r14.zzdnk;	 Catch:{ all -> 0x00bc }
        r2 = -1;
        if (r1 == 0) goto L_0x000f;
    L_0x0008:
        r15 = new com.google.android.gms.internal.ads.zzakx;	 Catch:{ all -> 0x00bc }
        r15.<init>(r2);	 Catch:{ all -> 0x00bc }
        monitor-exit(r0);	 Catch:{ all -> 0x00bc }
        return r15;
    L_0x000f:
        monitor-exit(r0);	 Catch:{ all -> 0x00bc }
        r0 = r14.zzdmn;
        r0 = r0.zzdmb;
        r3 = -1;
        r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1));
        if (r5 == 0) goto L_0x001f;
    L_0x001a:
        r0 = r14.zzdmn;
        r0 = r0.zzdmb;
        goto L_0x0021;
    L_0x001f:
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
    L_0x0021:
        r15 = r15.iterator();
        r3 = 0;
        r4 = r2;
        r1 = r0;
        r0 = r3;
    L_0x0029:
        r5 = r15.hasNext();
        if (r5 == 0) goto L_0x00af;
    L_0x002f:
        r5 = r15.next();
        r5 = (com.google.android.gms.internal.ads.zzbcb) r5;
        r6 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r6 = r6.currentTimeMillis();
        r8 = 0;
        r10 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1));
        if (r10 != 0) goto L_0x0054;
    L_0x0043:
        r10 = r5.isDone();	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        if (r10 == 0) goto L_0x0054;
    L_0x0049:
        r10 = r5.get();	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        r10 = (com.google.android.gms.internal.ads.zzakx) r10;	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        goto L_0x005c;
    L_0x0050:
        r15 = move-exception;
        goto L_0x009f;
    L_0x0052:
        r5 = move-exception;
        goto L_0x0089;
    L_0x0054:
        r10 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        r10 = r5.get(r1, r10);	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        r10 = (com.google.android.gms.internal.ads.zzakx) r10;	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
    L_0x005c:
        r11 = r14.zzdnn;	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        r11.add(r10);	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        if (r10 == 0) goto L_0x0078;
    L_0x0063:
        r11 = r10.zzdna;	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        if (r11 != 0) goto L_0x0078;
    L_0x0067:
        r11 = r10.zzdnf;	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        if (r11 == 0) goto L_0x0078;
    L_0x006b:
        r12 = r11.zzur();	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        if (r12 <= r4) goto L_0x0078;
    L_0x0071:
        r11 = r11.zzur();	 Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052, RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        r3 = r5;
        r0 = r10;
        r4 = r11;
    L_0x0078:
        r5 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r10 = r5.currentTimeMillis();
        r12 = r10 - r6;
        r5 = r1 - r12;
        r1 = java.lang.Math.max(r5, r8);
        goto L_0x0029;
    L_0x0089:
        r10 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.internal.ads.zzbbd.zzc(r10, r5);	 Catch:{ all -> 0x0050 }
        r5 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r10 = r5.currentTimeMillis();
        r12 = r10 - r6;
        r5 = r1 - r12;
        r1 = java.lang.Math.max(r5, r8);
        goto L_0x0029;
    L_0x009f:
        r0 = com.google.android.gms.ads.internal.zzbv.zzlm();
        r3 = r0.currentTimeMillis();
        r10 = r3 - r6;
        r3 = r1 - r10;
        java.lang.Math.max(r3, r8);
        throw r15;
    L_0x00af:
        r14.zza(r3);
        if (r0 != 0) goto L_0x00bb;
    L_0x00b4:
        r15 = new com.google.android.gms.internal.ads.zzakx;
        r0 = 1;
        r15.<init>(r0);
        return r15;
    L_0x00bb:
        return r0;
    L_0x00bc:
        r15 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x00bc }
        throw r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzala.zzj(java.util.List):com.google.android.gms.internal.ads.zzakx");
    }

    private final void zza(zzbcb<zzakx> zzbcb) {
        zzayh.zzelc.post(new zzalc(this, zzbcb));
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzdnk = true;
            for (zzaku cancel : this.zzdnl.values()) {
                cancel.cancel();
            }
        }
    }

    public final List<zzakx> zzui() {
        return this.zzdnn;
    }
}
