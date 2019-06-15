package com.google.android.gms.internal.ads;

final class zzlq {
    private int length;
    private int[] zzagt = new int[this.zzaxz];
    private long[] zzagu = new long[this.zzaxz];
    private long[] zzagw = new long[this.zzaxz];
    private int[] zzapr = new int[this.zzaxz];
    private int zzaxz = 1000;
    private int[] zzaya = new int[this.zzaxz];
    private zzij[] zzayb = new zzij[this.zzaxz];
    private zzfs[] zzayc = new zzfs[this.zzaxz];
    private int zzayd;
    private int zzaye;
    private int zzayf;
    private long zzayg = Long.MIN_VALUE;
    private long zzayh = Long.MIN_VALUE;
    private boolean zzayi = true;
    private boolean zzayj = true;
    private zzfs zzayk;

    public final void zzfi() {
        this.zzayd = 0;
        this.zzaye = 0;
        this.zzayf = 0;
        this.length = 0;
        this.zzayi = true;
    }

    public final void zzfj() {
        this.zzayg = Long.MIN_VALUE;
        this.zzayh = Long.MIN_VALUE;
    }

    public final int zzfk() {
        return this.zzayd + this.length;
    }

    public final long zzaq(int i) {
        int zzfk = zzfk() - i;
        boolean z = zzfk >= 0 && zzfk <= this.length;
        zzpo.checkArgument(z);
        if (zzfk != 0) {
            this.length -= zzfk;
            this.zzayf = ((this.zzayf + this.zzaxz) - zzfk) % this.zzaxz;
            this.zzayh = Long.MIN_VALUE;
            for (zzfk = this.length - 1; zzfk >= 0; zzfk--) {
                int i2 = (this.zzaye + zzfk) % this.zzaxz;
                this.zzayh = Math.max(this.zzayh, this.zzagw[i2]);
                if ((this.zzapr[i2] & 1) != 0) {
                    break;
                }
            }
            return this.zzagu[this.zzayf];
        } else if (this.zzayd == 0 && this.length == 0) {
            return 0;
        } else {
            zzfk = (this.zzayf == 0 ? this.zzaxz : this.zzayf) - 1;
            return this.zzagu[zzfk] + ((long) this.zzagt[zzfk]);
        }
    }

    public final int zzfl() {
        return this.zzayd;
    }

    public final synchronized boolean zzfm() {
        return this.length != 0;
    }

    public final synchronized zzfs zzfn() {
        if (this.zzayj) {
            return null;
        }
        return this.zzayk;
    }

    public final synchronized long zzfc() {
        return Math.max(this.zzayg, this.zzayh);
    }

    /* JADX WARNING: Missing block: B:18:0x0023, code skipped:
            return -3;
     */
    public final synchronized int zza(com.google.android.gms.internal.ads.zzfu r5, com.google.android.gms.internal.ads.zzho r6, boolean r7, boolean r8, com.google.android.gms.internal.ads.zzfs r9, com.google.android.gms.internal.ads.zzlr r10) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.zzfm();	 Catch:{ all -> 0x00a7 }
        r1 = -3;
        r2 = -5;
        r3 = -4;
        if (r0 != 0) goto L_0x0024;
    L_0x000a:
        if (r8 == 0) goto L_0x0012;
    L_0x000c:
        r5 = 4;
        r6.setFlags(r5);	 Catch:{ all -> 0x00a7 }
        monitor-exit(r4);
        return r3;
    L_0x0012:
        r6 = r4.zzayk;	 Catch:{ all -> 0x00a7 }
        if (r6 == 0) goto L_0x0022;
    L_0x0016:
        if (r7 != 0) goto L_0x001c;
    L_0x0018:
        r6 = r4.zzayk;	 Catch:{ all -> 0x00a7 }
        if (r6 == r9) goto L_0x0022;
    L_0x001c:
        r6 = r4.zzayk;	 Catch:{ all -> 0x00a7 }
        r5.zzaad = r6;	 Catch:{ all -> 0x00a7 }
        monitor-exit(r4);
        return r2;
    L_0x0022:
        monitor-exit(r4);
        return r1;
    L_0x0024:
        if (r7 != 0) goto L_0x009d;
    L_0x0026:
        r7 = r4.zzayc;	 Catch:{ all -> 0x00a7 }
        r8 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r7 = r7[r8];	 Catch:{ all -> 0x00a7 }
        if (r7 == r9) goto L_0x002f;
    L_0x002e:
        goto L_0x009d;
    L_0x002f:
        r5 = r6.zzdd;	 Catch:{ all -> 0x00a7 }
        r7 = 0;
        r8 = 1;
        if (r5 != 0) goto L_0x0037;
    L_0x0035:
        r5 = r8;
        goto L_0x0038;
    L_0x0037:
        r5 = r7;
    L_0x0038:
        if (r5 == 0) goto L_0x003c;
    L_0x003a:
        monitor-exit(r4);
        return r1;
    L_0x003c:
        r5 = r4.zzagw;	 Catch:{ all -> 0x00a7 }
        r9 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r0 = r5[r9];	 Catch:{ all -> 0x00a7 }
        r6.zzago = r0;	 Catch:{ all -> 0x00a7 }
        r5 = r4.zzapr;	 Catch:{ all -> 0x00a7 }
        r9 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r5 = r5[r9];	 Catch:{ all -> 0x00a7 }
        r6.setFlags(r5);	 Catch:{ all -> 0x00a7 }
        r5 = r4.zzagt;	 Catch:{ all -> 0x00a7 }
        r9 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r5 = r5[r9];	 Catch:{ all -> 0x00a7 }
        r10.size = r5;	 Catch:{ all -> 0x00a7 }
        r5 = r4.zzagu;	 Catch:{ all -> 0x00a7 }
        r9 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r0 = r5[r9];	 Catch:{ all -> 0x00a7 }
        r10.zzapb = r0;	 Catch:{ all -> 0x00a7 }
        r5 = r4.zzayb;	 Catch:{ all -> 0x00a7 }
        r9 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r5 = r5[r9];	 Catch:{ all -> 0x00a7 }
        r10.zzajw = r5;	 Catch:{ all -> 0x00a7 }
        r0 = r4.zzayg;	 Catch:{ all -> 0x00a7 }
        r5 = r6.zzago;	 Catch:{ all -> 0x00a7 }
        r5 = java.lang.Math.max(r0, r5);	 Catch:{ all -> 0x00a7 }
        r4.zzayg = r5;	 Catch:{ all -> 0x00a7 }
        r5 = r4.length;	 Catch:{ all -> 0x00a7 }
        r5 = r5 - r8;
        r4.length = r5;	 Catch:{ all -> 0x00a7 }
        r5 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r5 = r5 + r8;
        r4.zzaye = r5;	 Catch:{ all -> 0x00a7 }
        r5 = r4.zzayd;	 Catch:{ all -> 0x00a7 }
        r5 = r5 + r8;
        r4.zzayd = r5;	 Catch:{ all -> 0x00a7 }
        r5 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r6 = r4.zzaxz;	 Catch:{ all -> 0x00a7 }
        if (r5 != r6) goto L_0x0086;
    L_0x0084:
        r4.zzaye = r7;	 Catch:{ all -> 0x00a7 }
    L_0x0086:
        r5 = r4.length;	 Catch:{ all -> 0x00a7 }
        if (r5 <= 0) goto L_0x0091;
    L_0x008a:
        r5 = r4.zzagu;	 Catch:{ all -> 0x00a7 }
        r6 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r6 = r5[r6];	 Catch:{ all -> 0x00a7 }
        goto L_0x0099;
    L_0x0091:
        r5 = r10.zzapb;	 Catch:{ all -> 0x00a7 }
        r7 = r10.size;	 Catch:{ all -> 0x00a7 }
        r7 = (long) r7;	 Catch:{ all -> 0x00a7 }
        r0 = r5 + r7;
        r6 = r0;
    L_0x0099:
        r10.zzayl = r6;	 Catch:{ all -> 0x00a7 }
        monitor-exit(r4);
        return r3;
    L_0x009d:
        r6 = r4.zzayc;	 Catch:{ all -> 0x00a7 }
        r7 = r4.zzaye;	 Catch:{ all -> 0x00a7 }
        r6 = r6[r7];	 Catch:{ all -> 0x00a7 }
        r5.zzaad = r6;	 Catch:{ all -> 0x00a7 }
        monitor-exit(r4);
        return r2;
    L_0x00a7:
        r5 = move-exception;
        monitor-exit(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlq.zza(com.google.android.gms.internal.ads.zzfu, com.google.android.gms.internal.ads.zzho, boolean, boolean, com.google.android.gms.internal.ads.zzfs, com.google.android.gms.internal.ads.zzlr):int");
    }

    public final synchronized long zzfo() {
        if (!zzfm()) {
            return -1;
        }
        int i = ((this.zzaye + this.length) - 1) % this.zzaxz;
        this.zzaye = (this.zzaye + this.length) % this.zzaxz;
        this.zzayd += this.length;
        this.length = 0;
        return this.zzagu[i] + ((long) this.zzagt[i]);
    }

    /* JADX WARNING: Missing block: B:33:0x0060, code skipped:
            return -1;
     */
    public final synchronized long zzd(long r9, boolean r11) {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = r8.zzfm();	 Catch:{ all -> 0x0061 }
        r1 = -1;
        if (r0 == 0) goto L_0x005f;
    L_0x0009:
        r0 = r8.zzagw;	 Catch:{ all -> 0x0061 }
        r3 = r8.zzaye;	 Catch:{ all -> 0x0061 }
        r3 = r0[r3];	 Catch:{ all -> 0x0061 }
        r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1));
        if (r0 >= 0) goto L_0x0014;
    L_0x0013:
        goto L_0x005f;
    L_0x0014:
        r3 = r8.zzayh;	 Catch:{ all -> 0x0061 }
        r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1));
        if (r0 <= 0) goto L_0x001e;
    L_0x001a:
        if (r11 != 0) goto L_0x001e;
    L_0x001c:
        monitor-exit(r8);
        return r1;
    L_0x001e:
        r11 = 0;
        r0 = r8.zzaye;	 Catch:{ all -> 0x0061 }
        r3 = -1;
        r4 = r11;
        r11 = r3;
    L_0x0024:
        r5 = r8.zzayf;	 Catch:{ all -> 0x0061 }
        if (r0 == r5) goto L_0x0041;
    L_0x0028:
        r5 = r8.zzagw;	 Catch:{ all -> 0x0061 }
        r6 = r5[r0];	 Catch:{ all -> 0x0061 }
        r5 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1));
        if (r5 > 0) goto L_0x0041;
    L_0x0030:
        r5 = r8.zzapr;	 Catch:{ all -> 0x0061 }
        r5 = r5[r0];	 Catch:{ all -> 0x0061 }
        r5 = r5 & 1;
        if (r5 == 0) goto L_0x0039;
    L_0x0038:
        r11 = r4;
    L_0x0039:
        r0 = r0 + 1;
        r5 = r8.zzaxz;	 Catch:{ all -> 0x0061 }
        r0 = r0 % r5;
        r4 = r4 + 1;
        goto L_0x0024;
    L_0x0041:
        if (r11 != r3) goto L_0x0045;
    L_0x0043:
        monitor-exit(r8);
        return r1;
    L_0x0045:
        r9 = r8.zzaye;	 Catch:{ all -> 0x0061 }
        r9 = r9 + r11;
        r10 = r8.zzaxz;	 Catch:{ all -> 0x0061 }
        r9 = r9 % r10;
        r8.zzaye = r9;	 Catch:{ all -> 0x0061 }
        r9 = r8.zzayd;	 Catch:{ all -> 0x0061 }
        r9 = r9 + r11;
        r8.zzayd = r9;	 Catch:{ all -> 0x0061 }
        r9 = r8.length;	 Catch:{ all -> 0x0061 }
        r9 = r9 - r11;
        r8.length = r9;	 Catch:{ all -> 0x0061 }
        r9 = r8.zzagu;	 Catch:{ all -> 0x0061 }
        r10 = r8.zzaye;	 Catch:{ all -> 0x0061 }
        r10 = r9[r10];	 Catch:{ all -> 0x0061 }
        monitor-exit(r8);
        return r10;
    L_0x005f:
        monitor-exit(r8);
        return r1;
    L_0x0061:
        r9 = move-exception;
        monitor-exit(r8);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlq.zzd(long, boolean):long");
    }

    public final synchronized boolean zzh(zzfs zzfs) {
        if (zzfs == null) {
            this.zzayj = true;
            return false;
        }
        this.zzayj = false;
        if (zzqe.zza((Object) zzfs, this.zzayk)) {
            return false;
        }
        this.zzayk = zzfs;
        return true;
    }

    /* JADX WARNING: Missing block: B:21:0x00e8, code skipped:
            return;
     */
    public final synchronized void zza(long r6, int r8, long r9, int r11, com.google.android.gms.internal.ads.zzij r12) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.zzayi;	 Catch:{ all -> 0x00e9 }
        r1 = 0;
        if (r0 == 0) goto L_0x000e;
    L_0x0006:
        r0 = r8 & 1;
        if (r0 != 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r5);
        return;
    L_0x000c:
        r5.zzayi = r1;	 Catch:{ all -> 0x00e9 }
    L_0x000e:
        r0 = r5.zzayj;	 Catch:{ all -> 0x00e9 }
        r0 = r0 ^ 1;
        com.google.android.gms.internal.ads.zzpo.checkState(r0);	 Catch:{ all -> 0x00e9 }
        r5.zzac(r6);	 Catch:{ all -> 0x00e9 }
        r0 = r5.zzagw;	 Catch:{ all -> 0x00e9 }
        r2 = r5.zzayf;	 Catch:{ all -> 0x00e9 }
        r0[r2] = r6;	 Catch:{ all -> 0x00e9 }
        r6 = r5.zzagu;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzayf;	 Catch:{ all -> 0x00e9 }
        r6[r7] = r9;	 Catch:{ all -> 0x00e9 }
        r6 = r5.zzagt;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzayf;	 Catch:{ all -> 0x00e9 }
        r6[r7] = r11;	 Catch:{ all -> 0x00e9 }
        r6 = r5.zzapr;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzayf;	 Catch:{ all -> 0x00e9 }
        r6[r7] = r8;	 Catch:{ all -> 0x00e9 }
        r6 = r5.zzayb;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzayf;	 Catch:{ all -> 0x00e9 }
        r6[r7] = r12;	 Catch:{ all -> 0x00e9 }
        r6 = r5.zzayc;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzayf;	 Catch:{ all -> 0x00e9 }
        r8 = r5.zzayk;	 Catch:{ all -> 0x00e9 }
        r6[r7] = r8;	 Catch:{ all -> 0x00e9 }
        r6 = r5.zzaya;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzayf;	 Catch:{ all -> 0x00e9 }
        r6[r7] = r1;	 Catch:{ all -> 0x00e9 }
        r6 = r5.length;	 Catch:{ all -> 0x00e9 }
        r6 = r6 + 1;
        r5.length = r6;	 Catch:{ all -> 0x00e9 }
        r6 = r5.length;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzaxz;	 Catch:{ all -> 0x00e9 }
        if (r6 != r7) goto L_0x00d9;
    L_0x0050:
        r6 = r5.zzaxz;	 Catch:{ all -> 0x00e9 }
        r6 = r6 + 1000;
        r7 = new int[r6];	 Catch:{ all -> 0x00e9 }
        r8 = new long[r6];	 Catch:{ all -> 0x00e9 }
        r9 = new long[r6];	 Catch:{ all -> 0x00e9 }
        r10 = new int[r6];	 Catch:{ all -> 0x00e9 }
        r11 = new int[r6];	 Catch:{ all -> 0x00e9 }
        r12 = new com.google.android.gms.internal.ads.zzij[r6];	 Catch:{ all -> 0x00e9 }
        r0 = new com.google.android.gms.internal.ads.zzfs[r6];	 Catch:{ all -> 0x00e9 }
        r2 = r5.zzaxz;	 Catch:{ all -> 0x00e9 }
        r3 = r5.zzaye;	 Catch:{ all -> 0x00e9 }
        r2 = r2 - r3;
        r3 = r5.zzagu;	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzaye;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r3, r4, r8, r1, r2);	 Catch:{ all -> 0x00e9 }
        r3 = r5.zzagw;	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzaye;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r3, r4, r9, r1, r2);	 Catch:{ all -> 0x00e9 }
        r3 = r5.zzapr;	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzaye;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r3, r4, r10, r1, r2);	 Catch:{ all -> 0x00e9 }
        r3 = r5.zzagt;	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzaye;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r3, r4, r11, r1, r2);	 Catch:{ all -> 0x00e9 }
        r3 = r5.zzayb;	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzaye;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r3, r4, r12, r1, r2);	 Catch:{ all -> 0x00e9 }
        r3 = r5.zzayc;	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzaye;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r3, r4, r0, r1, r2);	 Catch:{ all -> 0x00e9 }
        r3 = r5.zzaya;	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzaye;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r3, r4, r7, r1, r2);	 Catch:{ all -> 0x00e9 }
        r3 = r5.zzaye;	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzagu;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r4, r1, r8, r2, r3);	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzagw;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r4, r1, r9, r2, r3);	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzapr;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r4, r1, r10, r2, r3);	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzagt;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r4, r1, r11, r2, r3);	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzayb;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r4, r1, r12, r2, r3);	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzayc;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r4, r1, r0, r2, r3);	 Catch:{ all -> 0x00e9 }
        r4 = r5.zzaya;	 Catch:{ all -> 0x00e9 }
        java.lang.System.arraycopy(r4, r1, r7, r2, r3);	 Catch:{ all -> 0x00e9 }
        r5.zzagu = r8;	 Catch:{ all -> 0x00e9 }
        r5.zzagw = r9;	 Catch:{ all -> 0x00e9 }
        r5.zzapr = r10;	 Catch:{ all -> 0x00e9 }
        r5.zzagt = r11;	 Catch:{ all -> 0x00e9 }
        r5.zzayb = r12;	 Catch:{ all -> 0x00e9 }
        r5.zzayc = r0;	 Catch:{ all -> 0x00e9 }
        r5.zzaya = r7;	 Catch:{ all -> 0x00e9 }
        r5.zzaye = r1;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzaxz;	 Catch:{ all -> 0x00e9 }
        r5.zzayf = r7;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzaxz;	 Catch:{ all -> 0x00e9 }
        r5.length = r7;	 Catch:{ all -> 0x00e9 }
        r5.zzaxz = r6;	 Catch:{ all -> 0x00e9 }
        monitor-exit(r5);
        return;
    L_0x00d9:
        r6 = r5.zzayf;	 Catch:{ all -> 0x00e9 }
        r6 = r6 + 1;
        r5.zzayf = r6;	 Catch:{ all -> 0x00e9 }
        r6 = r5.zzayf;	 Catch:{ all -> 0x00e9 }
        r7 = r5.zzaxz;	 Catch:{ all -> 0x00e9 }
        if (r6 != r7) goto L_0x00e7;
    L_0x00e5:
        r5.zzayf = r1;	 Catch:{ all -> 0x00e9 }
    L_0x00e7:
        monitor-exit(r5);
        return;
    L_0x00e9:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlq.zza(long, int, long, int, com.google.android.gms.internal.ads.zzij):void");
    }

    public final synchronized void zzac(long j) {
        this.zzayh = Math.max(this.zzayh, j);
    }
}
