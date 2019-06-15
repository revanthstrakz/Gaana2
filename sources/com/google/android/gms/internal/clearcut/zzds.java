package com.google.android.gms.internal.clearcut;

import com.google.android.exoplayer2.C;
import com.google.android.gms.internal.clearcut.zzcg.zzg;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

final class zzds<T> implements zzef<T> {
    private static final Unsafe zzmh = zzfd.zzef();
    private final int[] zzmi;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final zzdo zzmn;
    private final boolean zzmo;
    private final boolean zzmp;
    private final boolean zzmq;
    private final boolean zzmr;
    private final int[] zzms;
    private final int[] zzmt;
    private final int[] zzmu;
    private final zzdw zzmv;
    private final zzcy zzmw;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;
    private final zzdj zzmz;

    private zzds(int[] iArr, Object[] objArr, int i, int i2, int i3, zzdo zzdo, boolean z, boolean z2, int[] iArr2, int[] iArr3, int[] iArr4, zzdw zzdw, zzcy zzcy, zzex<?, ?> zzex, zzbu<?> zzbu, zzdj zzdj) {
        zzdo zzdo2 = zzdo;
        zzbu<?> zzbu2 = zzbu;
        this.zzmi = iArr;
        this.zzmj = objArr;
        this.zzmk = i;
        this.zzml = i2;
        this.zzmm = i3;
        this.zzmp = zzdo2 instanceof zzcg;
        this.zzmq = z;
        boolean z3 = zzbu2 != null && zzbu2.zze(zzdo2);
        this.zzmo = z3;
        this.zzmr = false;
        this.zzms = iArr2;
        this.zzmt = iArr3;
        this.zzmu = iArr4;
        this.zzmv = zzdw;
        this.zzmw = zzcy;
        this.zzmx = zzex;
        this.zzmy = zzbu2;
        this.zzmn = zzdo2;
        this.zzmz = zzdj;
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzay zzay) throws IOException {
        return zzax.zza(i, bArr, i2, i3, zzn(obj), zzay);
    }

    private static int zza(zzef<?> zzef, int i, byte[] bArr, int i2, int i3, zzcn<?> zzcn, zzay zzay) throws IOException {
        i2 = zza((zzef) zzef, bArr, i2, i3, zzay);
        while (true) {
            zzcn.add(zzay.zzff);
            if (i2 >= i3) {
                break;
            }
            int zza = zzax.zza(bArr, i2, zzay);
            if (i != zzay.zzfd) {
                break;
            }
            i2 = zza((zzef) zzef, bArr, zza, i3, zzay);
        }
        return i2;
    }

    private static int zza(zzef zzef, byte[] bArr, int i, int i2, int i3, zzay zzay) throws IOException {
        zzds zzds = (zzds) zzef;
        Object newInstance = zzds.newInstance();
        int zza = zzds.zza(newInstance, bArr, i, i2, i3, zzay);
        zzds.zzc(newInstance);
        zzay.zzff = newInstance;
        return zza;
    }

    private static int zza(zzef zzef, byte[] bArr, int i, int i2, zzay zzay) throws IOException {
        int i3 = i + 1;
        i = bArr[i];
        if (i < 0) {
            i3 = zzax.zza(i, bArr, i3, zzay);
            i = zzay.zzfd;
        }
        int i4 = i3;
        if (i < 0 || i > i2 - i4) {
            throw zzco.zzbl();
        }
        Object newInstance = zzef.newInstance();
        i += i4;
        zzef.zza(newInstance, bArr, i4, i, zzay);
        zzef.zzc(newInstance);
        zzay.zzff = newInstance;
        return i;
    }

    private static <UT, UB> int zza(zzex<UT, UB> zzex, T t) {
        return zzex.zzm(zzex.zzq(t));
    }

    /* JADX WARNING: Missing block: B:28:0x00b3, code skipped:
            r2 = r2 + r4;
     */
    /* JADX WARNING: Missing block: B:61:0x013c, code skipped:
            r3 = java.lang.Integer.valueOf(r3);
     */
    /* JADX WARNING: Missing block: B:64:0x0149, code skipped:
            r3 = java.lang.Long.valueOf(r3);
     */
    /* JADX WARNING: Missing block: B:65:0x014d, code skipped:
            r12.putObject(r1, r9, r3);
     */
    /* JADX WARNING: Missing block: B:68:0x015b, code skipped:
            r12.putObject(r1, r9, r2);
            r2 = r4 + 4;
     */
    /* JADX WARNING: Missing block: B:72:0x016c, code skipped:
            r12.putObject(r1, r9, r2);
            r2 = r4 + 8;
     */
    /* JADX WARNING: Missing block: B:73:0x0171, code skipped:
            r12.putInt(r1, r13, r8);
     */
    /* JADX WARNING: Missing block: B:74:0x0174, code skipped:
            return r2;
     */
    /* JADX WARNING: Missing block: B:76:0x0176, code skipped:
            return r4;
     */
    private final int zza(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, long r27, int r29, com.google.android.gms.internal.clearcut.zzay r30) throws java.io.IOException {
        /*
        r17 = this;
        r0 = r17;
        r1 = r18;
        r3 = r19;
        r4 = r20;
        r2 = r22;
        r8 = r23;
        r5 = r24;
        r9 = r27;
        r6 = r29;
        r11 = r30;
        r12 = zzmh;
        r7 = r0.zzmi;
        r13 = r6 + 2;
        r7 = r7[r13];
        r13 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r7 = r7 & r13;
        r13 = (long) r7;
        r7 = 5;
        r15 = 2;
        switch(r26) {
            case 51: goto L_0x0161;
            case 52: goto L_0x0151;
            case 53: goto L_0x0141;
            case 54: goto L_0x0141;
            case 55: goto L_0x0134;
            case 56: goto L_0x0128;
            case 57: goto L_0x011d;
            case 58: goto L_0x0107;
            case 59: goto L_0x00dc;
            case 60: goto L_0x00b6;
            case 61: goto L_0x009e;
            case 62: goto L_0x0134;
            case 63: goto L_0x0071;
            case 64: goto L_0x011d;
            case 65: goto L_0x0128;
            case 66: goto L_0x0063;
            case 67: goto L_0x0055;
            case 68: goto L_0x0028;
            default: goto L_0x0026;
        };
    L_0x0026:
        goto L_0x0175;
    L_0x0028:
        r7 = 3;
        if (r5 != r7) goto L_0x0175;
    L_0x002b:
        r2 = r2 & -8;
        r7 = r2 | 4;
        r2 = r0.zzad(r6);
        r5 = r21;
        r6 = r7;
        r7 = r11;
        r2 = zza(r2, r3, r4, r5, r6, r7);
        r3 = r12.getInt(r1, r13);
        if (r3 != r8) goto L_0x0046;
    L_0x0041:
        r15 = r12.getObject(r1, r9);
        goto L_0x0047;
    L_0x0046:
        r15 = 0;
    L_0x0047:
        if (r15 != 0) goto L_0x004d;
    L_0x0049:
        r3 = r11.zzff;
        goto L_0x014d;
    L_0x004d:
        r3 = r11.zzff;
        r3 = com.google.android.gms.internal.clearcut.zzci.zza(r15, r3);
        goto L_0x014d;
    L_0x0055:
        if (r5 != 0) goto L_0x0175;
    L_0x0057:
        r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r3, r4, r11);
        r3 = r11.zzfe;
        r3 = com.google.android.gms.internal.clearcut.zzbk.zza(r3);
        goto L_0x0149;
    L_0x0063:
        if (r5 != 0) goto L_0x0175;
    L_0x0065:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11);
        r3 = r11.zzfd;
        r3 = com.google.android.gms.internal.clearcut.zzbk.zzm(r3);
        goto L_0x013c;
    L_0x0071:
        if (r5 != 0) goto L_0x0175;
    L_0x0073:
        r3 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11);
        r4 = r11.zzfd;
        r5 = r0.zzaf(r6);
        if (r5 == 0) goto L_0x0094;
    L_0x007f:
        r5 = r5.zzb(r4);
        if (r5 == 0) goto L_0x0086;
    L_0x0085:
        goto L_0x0094;
    L_0x0086:
        r1 = zzn(r18);
        r4 = (long) r4;
        r4 = java.lang.Long.valueOf(r4);
        r1.zzb(r2, r4);
        r2 = r3;
        return r2;
    L_0x0094:
        r2 = java.lang.Integer.valueOf(r4);
        r12.putObject(r1, r9, r2);
        r2 = r3;
        goto L_0x0171;
    L_0x009e:
        if (r5 != r15) goto L_0x0175;
    L_0x00a0:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11);
        r4 = r11.zzfd;
        if (r4 != 0) goto L_0x00ac;
    L_0x00a8:
        r3 = com.google.android.gms.internal.clearcut.zzbb.zzfi;
        goto L_0x014d;
    L_0x00ac:
        r3 = com.google.android.gms.internal.clearcut.zzbb.zzb(r3, r2, r4);
        r12.putObject(r1, r9, r3);
    L_0x00b3:
        r2 = r2 + r4;
        goto L_0x0171;
    L_0x00b6:
        if (r5 != r15) goto L_0x0175;
    L_0x00b8:
        r2 = r0.zzad(r6);
        r5 = r21;
        r2 = zza(r2, r3, r4, r5, r11);
        r3 = r12.getInt(r1, r13);
        if (r3 != r8) goto L_0x00cd;
    L_0x00c8:
        r15 = r12.getObject(r1, r9);
        goto L_0x00ce;
    L_0x00cd:
        r15 = 0;
    L_0x00ce:
        if (r15 != 0) goto L_0x00d4;
    L_0x00d0:
        r3 = r11.zzff;
        goto L_0x014d;
    L_0x00d4:
        r3 = r11.zzff;
        r3 = com.google.android.gms.internal.clearcut.zzci.zza(r15, r3);
        goto L_0x014d;
    L_0x00dc:
        if (r5 != r15) goto L_0x0175;
    L_0x00de:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11);
        r4 = r11.zzfd;
        if (r4 != 0) goto L_0x00e9;
    L_0x00e6:
        r3 = "";
        goto L_0x014d;
    L_0x00e9:
        r5 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r5 = r25 & r5;
        if (r5 == 0) goto L_0x00fc;
    L_0x00ef:
        r5 = r2 + r4;
        r5 = com.google.android.gms.internal.clearcut.zzff.zze(r3, r2, r5);
        if (r5 != 0) goto L_0x00fc;
    L_0x00f7:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbp();
        throw r1;
    L_0x00fc:
        r5 = new java.lang.String;
        r6 = com.google.android.gms.internal.clearcut.zzci.UTF_8;
        r5.<init>(r3, r2, r4, r6);
        r12.putObject(r1, r9, r5);
        goto L_0x00b3;
    L_0x0107:
        if (r5 != 0) goto L_0x0175;
    L_0x0109:
        r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r3, r4, r11);
        r3 = r11.zzfe;
        r5 = 0;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 == 0) goto L_0x0117;
    L_0x0115:
        r15 = 1;
        goto L_0x0118;
    L_0x0117:
        r15 = 0;
    L_0x0118:
        r3 = java.lang.Boolean.valueOf(r15);
        goto L_0x014d;
    L_0x011d:
        if (r5 != r7) goto L_0x0175;
    L_0x011f:
        r2 = com.google.android.gms.internal.clearcut.zzax.zzc(r19, r20);
        r2 = java.lang.Integer.valueOf(r2);
        goto L_0x015b;
    L_0x0128:
        r2 = 1;
        if (r5 != r2) goto L_0x0175;
    L_0x012b:
        r2 = com.google.android.gms.internal.clearcut.zzax.zzd(r19, r20);
        r2 = java.lang.Long.valueOf(r2);
        goto L_0x016c;
    L_0x0134:
        if (r5 != 0) goto L_0x0175;
    L_0x0136:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11);
        r3 = r11.zzfd;
    L_0x013c:
        r3 = java.lang.Integer.valueOf(r3);
        goto L_0x014d;
    L_0x0141:
        if (r5 != 0) goto L_0x0175;
    L_0x0143:
        r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r3, r4, r11);
        r3 = r11.zzfe;
    L_0x0149:
        r3 = java.lang.Long.valueOf(r3);
    L_0x014d:
        r12.putObject(r1, r9, r3);
        goto L_0x0171;
    L_0x0151:
        if (r5 != r7) goto L_0x0175;
    L_0x0153:
        r2 = com.google.android.gms.internal.clearcut.zzax.zzf(r19, r20);
        r2 = java.lang.Float.valueOf(r2);
    L_0x015b:
        r12.putObject(r1, r9, r2);
        r2 = r4 + 4;
        goto L_0x0171;
    L_0x0161:
        r2 = 1;
        if (r5 != r2) goto L_0x0175;
    L_0x0164:
        r2 = com.google.android.gms.internal.clearcut.zzax.zze(r19, r20);
        r2 = java.lang.Double.valueOf(r2);
    L_0x016c:
        r12.putObject(r1, r9, r2);
        r2 = r4 + 8;
    L_0x0171:
        r12.putInt(r1, r13, r8);
        return r2;
    L_0x0175:
        r2 = r4;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:79:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01be  */
    /* JADX WARNING: Missing block: B:56:0x011b, code skipped:
            return r2;
     */
    /* JADX WARNING: Missing block: B:59:0x0124, code skipped:
            if (r2 == 0) goto L_0x0126;
     */
    /* JADX WARNING: Missing block: B:60:0x0126, code skipped:
            r12.add(com.google.android.gms.internal.clearcut.zzbb.zzfi);
     */
    /* JADX WARNING: Missing block: B:61:0x012c, code skipped:
            r12.add(com.google.android.gms.internal.clearcut.zzbb.zzb(r7, r1, r2));
            r1 = r1 + r2;
     */
    /* JADX WARNING: Missing block: B:62:0x0134, code skipped:
            if (r1 >= r8) goto L_0x037e;
     */
    /* JADX WARNING: Missing block: B:63:0x0136, code skipped:
            r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
     */
    /* JADX WARNING: Missing block: B:64:0x013c, code skipped:
            if (r9 != r11.zzfd) goto L_0x037e;
     */
    /* JADX WARNING: Missing block: B:65:0x013e, code skipped:
            r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11);
            r2 = r11.zzfd;
     */
    /* JADX WARNING: Missing block: B:66:0x0144, code skipped:
            if (r2 != 0) goto L_0x012c;
     */
    /* JADX WARNING: Missing block: B:117:0x0216, code skipped:
            if (r11.zzfe != 0) goto L_0x0218;
     */
    /* JADX WARNING: Missing block: B:118:0x0218, code skipped:
            r3 = true;
     */
    /* JADX WARNING: Missing block: B:119:0x021a, code skipped:
            r3 = false;
     */
    /* JADX WARNING: Missing block: B:120:0x021b, code skipped:
            r12.addBoolean(r3);
     */
    /* JADX WARNING: Missing block: B:121:0x021e, code skipped:
            if (r2 >= r8) goto L_0x011a;
     */
    /* JADX WARNING: Missing block: B:122:0x0220, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11);
     */
    /* JADX WARNING: Missing block: B:123:0x0226, code skipped:
            if (r9 != r11.zzfd) goto L_0x011a;
     */
    /* JADX WARNING: Missing block: B:124:0x0228, code skipped:
            r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r3, r11);
     */
    /* JADX WARNING: Missing block: B:125:0x0230, code skipped:
            if (r11.zzfe == 0) goto L_0x021a;
     */
    /* JADX WARNING: Missing block: B:199:0x037e, code skipped:
            return r1;
     */
    private final int zza(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, long r29, com.google.android.gms.internal.clearcut.zzay r31) throws java.io.IOException {
        /*
        r17 = this;
        r0 = r17;
        r1 = r18;
        r7 = r19;
        r4 = r20;
        r8 = r21;
        r9 = r22;
        r2 = r24;
        r10 = r25;
        r5 = r29;
        r11 = r31;
        r3 = zzmh;
        r3 = r3.getObject(r1, r5);
        r3 = (com.google.android.gms.internal.clearcut.zzcn) r3;
        r12 = r3.zzu();
        r13 = 1;
        if (r12 != 0) goto L_0x0036;
    L_0x0023:
        r12 = r3.size();
        if (r12 != 0) goto L_0x002c;
    L_0x0029:
        r12 = 10;
        goto L_0x002d;
    L_0x002c:
        r12 = r12 << r13;
    L_0x002d:
        r3 = r3.zzi(r12);
        r12 = zzmh;
        r12.putObject(r1, r5, r3);
    L_0x0036:
        r12 = r3;
        r3 = 5;
        r5 = 0;
        r14 = 2;
        switch(r28) {
            case 18: goto L_0x033e;
            case 19: goto L_0x02ff;
            case 20: goto L_0x02c5;
            case 21: goto L_0x02c5;
            case 22: goto L_0x02b1;
            case 23: goto L_0x0272;
            case 24: goto L_0x0233;
            case 25: goto L_0x01e4;
            case 26: goto L_0x0157;
            case 27: goto L_0x0147;
            case 28: goto L_0x011c;
            case 29: goto L_0x02b1;
            case 30: goto L_0x00eb;
            case 31: goto L_0x0233;
            case 32: goto L_0x0272;
            case 33: goto L_0x00a9;
            case 34: goto L_0x0067;
            case 35: goto L_0x033e;
            case 36: goto L_0x02ff;
            case 37: goto L_0x02c5;
            case 38: goto L_0x02c5;
            case 39: goto L_0x02b1;
            case 40: goto L_0x0272;
            case 41: goto L_0x0233;
            case 42: goto L_0x01e4;
            case 43: goto L_0x02b1;
            case 44: goto L_0x00eb;
            case 45: goto L_0x0233;
            case 46: goto L_0x0272;
            case 47: goto L_0x00a9;
            case 48: goto L_0x0067;
            case 49: goto L_0x0040;
            default: goto L_0x003e;
        };
    L_0x003e:
        goto L_0x037d;
    L_0x0040:
        r1 = 3;
        if (r2 != r1) goto L_0x037d;
    L_0x0043:
        r10 = r0.zzad(r10);
        r1 = r9 & -8;
        r13 = r1 | 4;
        r1 = r10;
        r2 = r7;
        r3 = r4;
    L_0x004e:
        r4 = r8;
        r5 = r13;
        r6 = r11;
        r1 = zza(r1, r2, r3, r4, r5, r6);
        r2 = r11.zzff;
        r12.add(r2);
        if (r1 >= r8) goto L_0x037e;
    L_0x005c:
        r3 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r2 = r11.zzfd;
        if (r9 != r2) goto L_0x037e;
    L_0x0064:
        r1 = r10;
        r2 = r7;
        goto L_0x004e;
    L_0x0067:
        if (r2 != r14) goto L_0x0089;
    L_0x0069:
        r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        r2 = r2 + r1;
    L_0x0072:
        if (r1 >= r2) goto L_0x0082;
    L_0x0074:
        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r1, r11);
        r3 = r11.zzfe;
        r3 = com.google.android.gms.internal.clearcut.zzbk.zza(r3);
        r12.zzm(r3);
        goto L_0x0072;
    L_0x0082:
        if (r1 == r2) goto L_0x037e;
    L_0x0084:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbl();
        throw r1;
    L_0x0089:
        if (r2 != 0) goto L_0x037d;
    L_0x008b:
        r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r4, r11);
    L_0x0091:
        r2 = r11.zzfe;
        r2 = com.google.android.gms.internal.clearcut.zzbk.zza(r2);
        r12.zzm(r2);
        if (r1 >= r8) goto L_0x037e;
    L_0x009c:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x00a4:
        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r2, r11);
        goto L_0x0091;
    L_0x00a9:
        if (r2 != r14) goto L_0x00cb;
    L_0x00ab:
        r12 = (com.google.android.gms.internal.clearcut.zzch) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        r2 = r2 + r1;
    L_0x00b4:
        if (r1 >= r2) goto L_0x00c4;
    L_0x00b6:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        r3 = com.google.android.gms.internal.clearcut.zzbk.zzm(r3);
        r12.zzac(r3);
        goto L_0x00b4;
    L_0x00c4:
        if (r1 == r2) goto L_0x037e;
    L_0x00c6:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbl();
        throw r1;
    L_0x00cb:
        if (r2 != 0) goto L_0x037d;
    L_0x00cd:
        r12 = (com.google.android.gms.internal.clearcut.zzch) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
    L_0x00d3:
        r2 = r11.zzfd;
        r2 = com.google.android.gms.internal.clearcut.zzbk.zzm(r2);
        r12.zzac(r2);
        if (r1 >= r8) goto L_0x037e;
    L_0x00de:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x00e6:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11);
        goto L_0x00d3;
    L_0x00eb:
        if (r2 != r14) goto L_0x00f2;
    L_0x00ed:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r12, r11);
        goto L_0x00fd;
    L_0x00f2:
        if (r2 != 0) goto L_0x037d;
    L_0x00f4:
        r2 = r9;
        r3 = r7;
        r5 = r8;
        r6 = r12;
        r7 = r11;
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r2, r3, r4, r5, r6, r7);
    L_0x00fd:
        r1 = (com.google.android.gms.internal.clearcut.zzcg) r1;
        r3 = r1.zzjp;
        r4 = com.google.android.gms.internal.clearcut.zzey.zzea();
        if (r3 != r4) goto L_0x0108;
    L_0x0107:
        r3 = 0;
    L_0x0108:
        r4 = r0.zzaf(r10);
        r5 = r0.zzmx;
        r6 = r23;
        r3 = com.google.android.gms.internal.clearcut.zzeh.zza(r6, r12, r4, r3, r5);
        r3 = (com.google.android.gms.internal.clearcut.zzey) r3;
        if (r3 == 0) goto L_0x011a;
    L_0x0118:
        r1.zzjp = r3;
    L_0x011a:
        r1 = r2;
        return r1;
    L_0x011c:
        if (r2 != r14) goto L_0x037d;
    L_0x011e:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        if (r2 != 0) goto L_0x012c;
    L_0x0126:
        r2 = com.google.android.gms.internal.clearcut.zzbb.zzfi;
        r12.add(r2);
        goto L_0x0134;
    L_0x012c:
        r3 = com.google.android.gms.internal.clearcut.zzbb.zzb(r7, r1, r2);
        r12.add(r3);
        r1 = r1 + r2;
    L_0x0134:
        if (r1 >= r8) goto L_0x037e;
    L_0x0136:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x013e:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11);
        r2 = r11.zzfd;
        if (r2 != 0) goto L_0x012c;
    L_0x0146:
        goto L_0x0126;
    L_0x0147:
        if (r2 != r14) goto L_0x037d;
    L_0x0149:
        r1 = r0.zzad(r10);
        r2 = r9;
        r3 = r7;
        r5 = r8;
        r6 = r12;
        r7 = r11;
        r1 = zza(r1, r2, r3, r4, r5, r6, r7);
        return r1;
    L_0x0157:
        if (r2 != r14) goto L_0x037d;
    L_0x0159:
        r1 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r15 = r26 & r1;
        r1 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1));
        if (r1 != 0) goto L_0x0196;
    L_0x0162:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        if (r2 != 0) goto L_0x0170;
    L_0x016a:
        r2 = "";
        r12.add(r2);
        goto L_0x017b;
    L_0x0170:
        r3 = new java.lang.String;
        r4 = com.google.android.gms.internal.clearcut.zzci.UTF_8;
        r3.<init>(r7, r1, r2, r4);
    L_0x0177:
        r12.add(r3);
        r1 = r1 + r2;
    L_0x017b:
        if (r1 >= r8) goto L_0x037e;
    L_0x017d:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x0185:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11);
        r2 = r11.zzfd;
        if (r2 != 0) goto L_0x018e;
    L_0x018d:
        goto L_0x016a;
    L_0x018e:
        r3 = new java.lang.String;
        r4 = com.google.android.gms.internal.clearcut.zzci.UTF_8;
        r3.<init>(r7, r1, r2, r4);
        goto L_0x0177;
    L_0x0196:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        if (r2 != 0) goto L_0x01a4;
    L_0x019e:
        r2 = "";
        r12.add(r2);
        goto L_0x01bc;
    L_0x01a4:
        r3 = r1 + r2;
        r4 = com.google.android.gms.internal.clearcut.zzff.zze(r7, r1, r3);
        if (r4 != 0) goto L_0x01b1;
    L_0x01ac:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbp();
        throw r1;
    L_0x01b1:
        r4 = new java.lang.String;
        r5 = com.google.android.gms.internal.clearcut.zzci.UTF_8;
        r4.<init>(r7, r1, r2, r5);
    L_0x01b8:
        r12.add(r4);
        r1 = r3;
    L_0x01bc:
        if (r1 >= r8) goto L_0x037e;
    L_0x01be:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x01c6:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11);
        r2 = r11.zzfd;
        if (r2 != 0) goto L_0x01cf;
    L_0x01ce:
        goto L_0x019e;
    L_0x01cf:
        r3 = r1 + r2;
        r4 = com.google.android.gms.internal.clearcut.zzff.zze(r7, r1, r3);
        if (r4 != 0) goto L_0x01dc;
    L_0x01d7:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbp();
        throw r1;
    L_0x01dc:
        r4 = new java.lang.String;
        r5 = com.google.android.gms.internal.clearcut.zzci.UTF_8;
        r4.<init>(r7, r1, r2, r5);
        goto L_0x01b8;
    L_0x01e4:
        r1 = 0;
        if (r2 != r14) goto L_0x020a;
    L_0x01e7:
        r12 = (com.google.android.gms.internal.clearcut.zzaz) r12;
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r3 = r11.zzfd;
        r3 = r3 + r2;
    L_0x01f0:
        if (r2 >= r3) goto L_0x0203;
    L_0x01f2:
        r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r2, r11);
        r8 = r11.zzfe;
        r4 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1));
        if (r4 == 0) goto L_0x01fe;
    L_0x01fc:
        r4 = r13;
        goto L_0x01ff;
    L_0x01fe:
        r4 = r1;
    L_0x01ff:
        r12.addBoolean(r4);
        goto L_0x01f0;
    L_0x0203:
        if (r2 == r3) goto L_0x011a;
    L_0x0205:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbl();
        throw r1;
    L_0x020a:
        if (r2 != 0) goto L_0x037d;
    L_0x020c:
        r12 = (com.google.android.gms.internal.clearcut.zzaz) r12;
        r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r4, r11);
        r3 = r11.zzfe;
        r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r10 == 0) goto L_0x021a;
    L_0x0218:
        r3 = r13;
        goto L_0x021b;
    L_0x021a:
        r3 = r1;
    L_0x021b:
        r12.addBoolean(r3);
        if (r2 >= r8) goto L_0x011a;
    L_0x0220:
        r3 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11);
        r4 = r11.zzfd;
        if (r9 != r4) goto L_0x011a;
    L_0x0228:
        r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r3, r11);
        r3 = r11.zzfe;
        r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r10 == 0) goto L_0x021a;
    L_0x0232:
        goto L_0x0218;
    L_0x0233:
        if (r2 != r14) goto L_0x0251;
    L_0x0235:
        r12 = (com.google.android.gms.internal.clearcut.zzch) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        r2 = r2 + r1;
    L_0x023e:
        if (r1 >= r2) goto L_0x024a;
    L_0x0240:
        r3 = com.google.android.gms.internal.clearcut.zzax.zzc(r7, r1);
        r12.zzac(r3);
        r1 = r1 + 4;
        goto L_0x023e;
    L_0x024a:
        if (r1 == r2) goto L_0x037e;
    L_0x024c:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbl();
        throw r1;
    L_0x0251:
        if (r2 != r3) goto L_0x037d;
    L_0x0253:
        r12 = (com.google.android.gms.internal.clearcut.zzch) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzc(r19, r20);
        r12.zzac(r1);
        r1 = r4 + 4;
    L_0x025e:
        if (r1 >= r8) goto L_0x037e;
    L_0x0260:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x0268:
        r1 = com.google.android.gms.internal.clearcut.zzax.zzc(r7, r2);
        r12.zzac(r1);
        r1 = r2 + 4;
        goto L_0x025e;
    L_0x0272:
        if (r2 != r14) goto L_0x0290;
    L_0x0274:
        r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        r2 = r2 + r1;
    L_0x027d:
        if (r1 >= r2) goto L_0x0289;
    L_0x027f:
        r3 = com.google.android.gms.internal.clearcut.zzax.zzd(r7, r1);
        r12.zzm(r3);
        r1 = r1 + 8;
        goto L_0x027d;
    L_0x0289:
        if (r1 == r2) goto L_0x037e;
    L_0x028b:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbl();
        throw r1;
    L_0x0290:
        if (r2 != r13) goto L_0x037d;
    L_0x0292:
        r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzd(r19, r20);
        r12.zzm(r1);
        r1 = r4 + 8;
    L_0x029d:
        if (r1 >= r8) goto L_0x037e;
    L_0x029f:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x02a7:
        r3 = com.google.android.gms.internal.clearcut.zzax.zzd(r7, r2);
        r12.zzm(r3);
        r1 = r2 + 8;
        goto L_0x029d;
    L_0x02b1:
        if (r2 != r14) goto L_0x02b8;
    L_0x02b3:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r12, r11);
        return r1;
    L_0x02b8:
        if (r2 != 0) goto L_0x037d;
    L_0x02ba:
        r1 = r9;
        r2 = r7;
        r3 = r4;
        r4 = r8;
        r5 = r12;
        r6 = r11;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r1, r2, r3, r4, r5, r6);
        return r1;
    L_0x02c5:
        if (r2 != r14) goto L_0x02e3;
    L_0x02c7:
        r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        r2 = r2 + r1;
    L_0x02d0:
        if (r1 >= r2) goto L_0x02dc;
    L_0x02d2:
        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r1, r11);
        r3 = r11.zzfe;
        r12.zzm(r3);
        goto L_0x02d0;
    L_0x02dc:
        if (r1 == r2) goto L_0x037e;
    L_0x02de:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbl();
        throw r1;
    L_0x02e3:
        if (r2 != 0) goto L_0x037d;
    L_0x02e5:
        r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r4, r11);
    L_0x02eb:
        r2 = r11.zzfe;
        r12.zzm(r2);
        if (r1 >= r8) goto L_0x037e;
    L_0x02f2:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x02fa:
        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r2, r11);
        goto L_0x02eb;
    L_0x02ff:
        if (r2 != r14) goto L_0x031d;
    L_0x0301:
        r12 = (com.google.android.gms.internal.clearcut.zzce) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        r2 = r2 + r1;
    L_0x030a:
        if (r1 >= r2) goto L_0x0316;
    L_0x030c:
        r3 = com.google.android.gms.internal.clearcut.zzax.zzf(r7, r1);
        r12.zzc(r3);
        r1 = r1 + 4;
        goto L_0x030a;
    L_0x0316:
        if (r1 == r2) goto L_0x037e;
    L_0x0318:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbl();
        throw r1;
    L_0x031d:
        if (r2 != r3) goto L_0x037d;
    L_0x031f:
        r12 = (com.google.android.gms.internal.clearcut.zzce) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzf(r19, r20);
        r12.zzc(r1);
        r1 = r4 + 4;
    L_0x032a:
        if (r1 >= r8) goto L_0x037e;
    L_0x032c:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x0334:
        r1 = com.google.android.gms.internal.clearcut.zzax.zzf(r7, r2);
        r12.zzc(r1);
        r1 = r2 + 4;
        goto L_0x032a;
    L_0x033e:
        if (r2 != r14) goto L_0x035c;
    L_0x0340:
        r12 = (com.google.android.gms.internal.clearcut.zzbq) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11);
        r2 = r11.zzfd;
        r2 = r2 + r1;
    L_0x0349:
        if (r1 >= r2) goto L_0x0355;
    L_0x034b:
        r3 = com.google.android.gms.internal.clearcut.zzax.zze(r7, r1);
        r12.zzc(r3);
        r1 = r1 + 8;
        goto L_0x0349;
    L_0x0355:
        if (r1 == r2) goto L_0x037e;
    L_0x0357:
        r1 = com.google.android.gms.internal.clearcut.zzco.zzbl();
        throw r1;
    L_0x035c:
        if (r2 != r13) goto L_0x037d;
    L_0x035e:
        r12 = (com.google.android.gms.internal.clearcut.zzbq) r12;
        r1 = com.google.android.gms.internal.clearcut.zzax.zze(r19, r20);
        r12.zzc(r1);
        r1 = r4 + 8;
    L_0x0369:
        if (r1 >= r8) goto L_0x037e;
    L_0x036b:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
        r3 = r11.zzfd;
        if (r9 != r3) goto L_0x037e;
    L_0x0373:
        r3 = com.google.android.gms.internal.clearcut.zzax.zze(r7, r2);
        r12.zzc(r3);
        r1 = r2 + 8;
        goto L_0x0369;
    L_0x037d:
        r1 = r4;
    L_0x037e:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.clearcut.zzay):int");
    }

    private final <K, V> int zza(T r7, byte[] r8, int r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.clearcut.zzay r15) throws java.io.IOException {
        /*
        r6 = this;
        r12 = zzmh;
        r11 = r6.zzae(r11);
        r0 = r12.getObject(r7, r13);
        r1 = r6.zzmz;
        r1 = r1.zzi(r0);
        if (r1 == 0) goto L_0x0021;
    L_0x0012:
        r1 = r6.zzmz;
        r1 = r1.zzk(r11);
        r2 = r6.zzmz;
        r2.zzb(r1, r0);
        r12.putObject(r7, r13, r1);
        r0 = r1;
    L_0x0021:
        r7 = r6.zzmz;
        r7 = r7.zzl(r11);
        r11 = r6.zzmz;
        r11 = r11.zzg(r0);
        r9 = com.google.android.gms.internal.clearcut.zzax.zza(r8, r9, r15);
        r12 = r15.zzfd;
        if (r12 < 0) goto L_0x0095;
    L_0x0035:
        r13 = r10 - r9;
        if (r12 <= r13) goto L_0x003a;
    L_0x0039:
        goto L_0x0095;
    L_0x003a:
        r12 = r12 + r9;
        r13 = r7.zzmc;
        r14 = r7.zzdu;
    L_0x003f:
        if (r9 >= r12) goto L_0x008a;
    L_0x0041:
        r0 = r9 + 1;
        r9 = r8[r9];
        if (r9 >= 0) goto L_0x004d;
    L_0x0047:
        r0 = com.google.android.gms.internal.clearcut.zzax.zza(r9, r8, r0, r15);
        r9 = r15.zzfd;
    L_0x004d:
        r1 = r0;
        r0 = r9 >>> 3;
        r2 = r9 & 7;
        switch(r0) {
            case 1: goto L_0x0070;
            case 2: goto L_0x0056;
            default: goto L_0x0055;
        };
    L_0x0055:
        goto L_0x0085;
    L_0x0056:
        r0 = r7.zzmd;
        r0 = r0.zzel();
        if (r2 != r0) goto L_0x0085;
    L_0x005e:
        r3 = r7.zzmd;
        r9 = r7.zzdu;
        r4 = r9.getClass();
        r0 = r8;
        r2 = r10;
        r5 = r15;
        r9 = zza(r0, r1, r2, r3, r4, r5);
        r14 = r15.zzff;
        goto L_0x003f;
    L_0x0070:
        r0 = r7.zzmb;
        r0 = r0.zzel();
        if (r2 != r0) goto L_0x0085;
    L_0x0078:
        r3 = r7.zzmb;
        r4 = 0;
        r0 = r8;
        r2 = r10;
        r5 = r15;
        r9 = zza(r0, r1, r2, r3, r4, r5);
        r13 = r15.zzff;
        goto L_0x003f;
    L_0x0085:
        r9 = com.google.android.gms.internal.clearcut.zzax.zza(r9, r8, r1, r10, r15);
        goto L_0x003f;
    L_0x008a:
        if (r9 == r12) goto L_0x0091;
    L_0x008c:
        r7 = com.google.android.gms.internal.clearcut.zzco.zzbo();
        throw r7;
    L_0x0091:
        r11.put(r13, r14);
        return r12;
    L_0x0095:
        r7 = com.google.android.gms.internal.clearcut.zzco.zzbl();
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, int, long, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:126:0x0353 A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0353 A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0399  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0390  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x039f  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x03e7  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x03de  */
    /* JADX WARNING: Missing block: B:30:0x00c9, code skipped:
            r0 = r21;
     */
    /* JADX WARNING: Missing block: B:45:0x0128, code skipped:
            r1 = r9.zzff;
     */
    /* JADX WARNING: Missing block: B:46:0x012a, code skipped:
            r10.putObject(r14, r7, r1);
     */
    /* JADX WARNING: Missing block: B:71:0x01c7, code skipped:
            r0 = r3;
     */
    /* JADX WARNING: Missing block: B:75:0x01dc, code skipped:
            r10.putInt(r14, r7, r1);
     */
    /* JADX WARNING: Missing block: B:79:0x01f2, code skipped:
            r10.putLong(r14, r7, r4);
            r6 = r6 | r18;
            r1 = r11;
            r0 = r17;
     */
    /* JADX WARNING: Missing block: B:86:0x0229, code skipped:
            r6 = r6 | r18;
     */
    /* JADX WARNING: Missing block: B:87:0x022b, code skipped:
            r1 = r11;
     */
    /* JADX WARNING: Missing block: B:89:0x0233, code skipped:
            r2 = r0;
            r18 = r6;
            r29 = r10;
            r6 = r11;
            r14 = r15;
     */
    /* JADX WARNING: Missing block: B:114:0x030a, code skipped:
            if (r0 == r15) goto L_0x032d;
     */
    /* JADX WARNING: Missing block: B:117:0x032b, code skipped:
            if (r0 == r15) goto L_0x032d;
     */
    /* JADX WARNING: Missing block: B:119:0x032f, code skipped:
            r12 = r33;
            r13 = r35;
            r9 = r37;
            r15 = r14;
            r6 = r18;
            r7 = r24;
            r10 = r29;
            r1 = r30;
            r8 = -1;
            r11 = r36;
     */
    private final int zza(T r32, byte[] r33, int r34, int r35, int r36, com.google.android.gms.internal.clearcut.zzay r37) throws java.io.IOException {
        /*
        r31 = this;
        r15 = r31;
        r14 = r32;
        r12 = r33;
        r13 = r35;
        r11 = r36;
        r9 = r37;
        r10 = zzmh;
        r16 = 0;
        r8 = -1;
        r0 = r34;
        r7 = r8;
        r1 = r16;
        r6 = r1;
    L_0x0017:
        r17 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        if (r0 >= r13) goto L_0x037f;
    L_0x001c:
        r1 = r0 + 1;
        r0 = r12[r0];
        if (r0 >= 0) goto L_0x002b;
    L_0x0022:
        r0 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r12, r1, r9);
        r1 = r9.zzfd;
        r4 = r0;
        r5 = r1;
        goto L_0x002d;
    L_0x002b:
        r5 = r0;
        r4 = r1;
    L_0x002d:
        r3 = r5 >>> 3;
        r2 = r5 & 7;
        r1 = r15.zzai(r3);
        if (r1 == r8) goto L_0x0342;
    L_0x0037:
        r0 = r15.zzmi;
        r18 = r1 + 1;
        r0 = r0[r18];
        r18 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r18 = r0 & r18;
        r8 = r18 >>> 20;
        r20 = r5;
        r5 = r0 & r17;
        r21 = r4;
        r4 = (long) r5;
        r22 = r4;
        r4 = 17;
        if (r8 > r4) goto L_0x023c;
    L_0x0050:
        r4 = r15.zzmi;
        r18 = r1 + 2;
        r4 = r4[r18];
        r18 = r4 >>> 20;
        r5 = 1;
        r18 = r5 << r18;
        r4 = r4 & r17;
        if (r4 == r7) goto L_0x006c;
    L_0x005f:
        r11 = -1;
        if (r7 == r11) goto L_0x0066;
    L_0x0062:
        r11 = (long) r7;
        r10.putInt(r14, r11, r6);
    L_0x0066:
        r6 = (long) r4;
        r6 = r10.getInt(r14, r6);
        r7 = r4;
    L_0x006c:
        r4 = 5;
        switch(r8) {
            case 0: goto L_0x0214;
            case 1: goto L_0x01fe;
            case 2: goto L_0x01e0;
            case 3: goto L_0x01e0;
            case 4: goto L_0x01ca;
            case 5: goto L_0x01a9;
            case 6: goto L_0x0192;
            case 7: goto L_0x0172;
            case 8: goto L_0x0156;
            case 9: goto L_0x012f;
            case 10: goto L_0x0117;
            case 11: goto L_0x01ca;
            case 12: goto L_0x00e5;
            case 13: goto L_0x0192;
            case 14: goto L_0x01a9;
            case 15: goto L_0x00cd;
            case 16: goto L_0x00b1;
            case 17: goto L_0x007a;
            default: goto L_0x0070;
        };
    L_0x0070:
        r24 = r7;
        r11 = r20;
        r0 = r21;
        r12 = r33;
        goto L_0x0233;
    L_0x007a:
        r0 = 3;
        if (r2 != r0) goto L_0x00aa;
    L_0x007d:
        r0 = r3 << 3;
        r4 = r0 | 4;
        r0 = r15.zzad(r1);
        r12 = r33;
        r1 = r12;
        r2 = r21;
        r3 = r13;
        r24 = r7;
        r7 = r22;
        r11 = r20;
        r5 = r9;
        r0 = zza(r0, r1, r2, r3, r4, r5);
        r1 = r6 & r18;
        if (r1 != 0) goto L_0x009e;
    L_0x009a:
        r1 = r9.zzff;
        goto L_0x012a;
    L_0x009e:
        r1 = r10.getObject(r14, r7);
        r2 = r9.zzff;
        r1 = com.google.android.gms.internal.clearcut.zzci.zza(r1, r2);
        goto L_0x012a;
    L_0x00aa:
        r24 = r7;
        r11 = r20;
        r12 = r33;
        goto L_0x00c9;
    L_0x00b1:
        r24 = r7;
        r11 = r20;
        r7 = r22;
        r12 = r33;
        if (r2 != 0) goto L_0x00c9;
    L_0x00bb:
        r3 = r21;
        r17 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r3, r9);
        r0 = r9.zzfe;
        r4 = com.google.android.gms.internal.clearcut.zzbk.zza(r0);
        goto L_0x01f2;
    L_0x00c9:
        r0 = r21;
        goto L_0x0233;
    L_0x00cd:
        r24 = r7;
        r11 = r20;
        r3 = r21;
        r7 = r22;
        r12 = r33;
        if (r2 != 0) goto L_0x01c7;
    L_0x00d9:
        r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r3, r9);
        r1 = r9.zzfd;
        r1 = com.google.android.gms.internal.clearcut.zzbk.zzm(r1);
        goto L_0x01dc;
    L_0x00e5:
        r24 = r7;
        r11 = r20;
        r3 = r21;
        r7 = r22;
        r12 = r33;
        if (r2 != 0) goto L_0x01c7;
    L_0x00f1:
        r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r3, r9);
        r2 = r9.zzfd;
        r1 = r15.zzaf(r1);
        if (r1 == 0) goto L_0x0112;
    L_0x00fd:
        r1 = r1.zzb(r2);
        if (r1 == 0) goto L_0x0104;
    L_0x0103:
        goto L_0x0112;
    L_0x0104:
        r1 = zzn(r32);
        r2 = (long) r2;
        r2 = java.lang.Long.valueOf(r2);
        r1.zzb(r11, r2);
        goto L_0x022b;
    L_0x0112:
        r10.putInt(r14, r7, r2);
        goto L_0x0229;
    L_0x0117:
        r24 = r7;
        r11 = r20;
        r3 = r21;
        r7 = r22;
        r0 = 2;
        r12 = r33;
        if (r2 != r0) goto L_0x01c7;
    L_0x0124:
        r0 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r3, r9);
    L_0x0128:
        r1 = r9.zzff;
    L_0x012a:
        r10.putObject(r14, r7, r1);
        goto L_0x0229;
    L_0x012f:
        r24 = r7;
        r11 = r20;
        r3 = r21;
        r7 = r22;
        r0 = 2;
        r12 = r33;
        if (r2 != r0) goto L_0x01c7;
    L_0x013c:
        r0 = r15.zzad(r1);
        r0 = zza(r0, r12, r3, r13, r9);
        r1 = r6 & r18;
        if (r1 != 0) goto L_0x014b;
    L_0x0148:
        r1 = r9.zzff;
        goto L_0x012a;
    L_0x014b:
        r1 = r10.getObject(r14, r7);
        r2 = r9.zzff;
        r1 = com.google.android.gms.internal.clearcut.zzci.zza(r1, r2);
        goto L_0x012a;
    L_0x0156:
        r24 = r7;
        r11 = r20;
        r3 = r21;
        r7 = r22;
        r1 = 2;
        r12 = r33;
        if (r2 != r1) goto L_0x01c7;
    L_0x0163:
        r1 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r0 = r0 & r1;
        if (r0 != 0) goto L_0x016d;
    L_0x0168:
        r0 = com.google.android.gms.internal.clearcut.zzax.zzc(r12, r3, r9);
        goto L_0x0128;
    L_0x016d:
        r0 = com.google.android.gms.internal.clearcut.zzax.zzd(r12, r3, r9);
        goto L_0x0128;
    L_0x0172:
        r24 = r7;
        r11 = r20;
        r3 = r21;
        r7 = r22;
        r12 = r33;
        if (r2 != 0) goto L_0x01c7;
    L_0x017e:
        r0 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r3, r9);
        r1 = r9.zzfe;
        r3 = 0;
        r17 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r17 == 0) goto L_0x018b;
    L_0x018a:
        goto L_0x018d;
    L_0x018b:
        r5 = r16;
    L_0x018d:
        com.google.android.gms.internal.clearcut.zzfd.zza(r14, r7, r5);
        goto L_0x0229;
    L_0x0192:
        r24 = r7;
        r11 = r20;
        r3 = r21;
        r7 = r22;
        r12 = r33;
        if (r2 != r4) goto L_0x01c7;
    L_0x019e:
        r0 = com.google.android.gms.internal.clearcut.zzax.zzc(r12, r3);
        r10.putInt(r14, r7, r0);
        r0 = r3 + 4;
        goto L_0x0229;
    L_0x01a9:
        r24 = r7;
        r11 = r20;
        r3 = r21;
        r7 = r22;
        r12 = r33;
        if (r2 != r5) goto L_0x01c7;
    L_0x01b5:
        r4 = com.google.android.gms.internal.clearcut.zzax.zzd(r12, r3);
        r0 = r10;
        r1 = r14;
        r17 = r3;
        r2 = r7;
        r7 = r17;
        r0.putLong(r1, r2, r4);
        r0 = r7 + 8;
        goto L_0x0229;
    L_0x01c7:
        r0 = r3;
        goto L_0x0233;
    L_0x01ca:
        r24 = r7;
        r11 = r20;
        r0 = r21;
        r7 = r22;
        r12 = r33;
        if (r2 != 0) goto L_0x0233;
    L_0x01d6:
        r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r0, r9);
        r1 = r9.zzfd;
    L_0x01dc:
        r10.putInt(r14, r7, r1);
        goto L_0x0229;
    L_0x01e0:
        r24 = r7;
        r11 = r20;
        r0 = r21;
        r7 = r22;
        r12 = r33;
        if (r2 != 0) goto L_0x0233;
    L_0x01ec:
        r17 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r0, r9);
        r4 = r9.zzfe;
    L_0x01f2:
        r0 = r10;
        r1 = r14;
        r2 = r7;
        r0.putLong(r1, r2, r4);
        r6 = r6 | r18;
        r1 = r11;
        r0 = r17;
        goto L_0x022c;
    L_0x01fe:
        r24 = r7;
        r11 = r20;
        r0 = r21;
        r7 = r22;
        r12 = r33;
        if (r2 != r4) goto L_0x0233;
    L_0x020a:
        r1 = com.google.android.gms.internal.clearcut.zzax.zzf(r12, r0);
        com.google.android.gms.internal.clearcut.zzfd.zza(r14, r7, r1);
        r0 = r0 + 4;
        goto L_0x0229;
    L_0x0214:
        r24 = r7;
        r11 = r20;
        r0 = r21;
        r7 = r22;
        r12 = r33;
        if (r2 != r5) goto L_0x0233;
    L_0x0220:
        r1 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r0);
        com.google.android.gms.internal.clearcut.zzfd.zza(r14, r7, r1);
        r0 = r0 + 8;
    L_0x0229:
        r6 = r6 | r18;
    L_0x022b:
        r1 = r11;
    L_0x022c:
        r7 = r24;
        r8 = -1;
        r11 = r36;
        goto L_0x0017;
    L_0x0233:
        r2 = r0;
        r18 = r6;
        r29 = r10;
        r6 = r11;
        r14 = r15;
        goto L_0x034f;
    L_0x023c:
        r25 = r3;
        r24 = r7;
        r5 = r8;
        r11 = r20;
        r4 = r21;
        r7 = r22;
        r3 = 27;
        if (r5 != r3) goto L_0x0287;
    L_0x024b:
        r3 = 2;
        if (r2 != r3) goto L_0x027f;
    L_0x024e:
        r0 = r10.getObject(r14, r7);
        r0 = (com.google.android.gms.internal.clearcut.zzcn) r0;
        r2 = r0.zzu();
        if (r2 != 0) goto L_0x026c;
    L_0x025a:
        r2 = r0.size();
        if (r2 != 0) goto L_0x0263;
    L_0x0260:
        r2 = 10;
        goto L_0x0265;
    L_0x0263:
        r2 = r2 << 1;
    L_0x0265:
        r0 = r0.zzi(r2);
        r10.putObject(r14, r7, r0);
    L_0x026c:
        r5 = r0;
        r0 = r15.zzad(r1);
        r1 = r11;
        r2 = r12;
        r3 = r4;
        r4 = r13;
        r18 = r6;
        r6 = r9;
        r0 = zza(r0, r1, r2, r3, r4, r5, r6);
        r6 = r18;
        goto L_0x022c;
    L_0x027f:
        r18 = r6;
        r29 = r10;
        r30 = r11;
        goto L_0x034a;
    L_0x0287:
        r18 = r6;
        r3 = 49;
        if (r5 > r3) goto L_0x02da;
    L_0x028d:
        r26 = r10;
        r9 = (long) r0;
        r0 = r15;
        r19 = r1;
        r1 = r14;
        r6 = r2;
        r2 = r12;
        r20 = r25;
        r3 = r4;
        r15 = r4;
        r4 = r13;
        r21 = r5;
        r5 = r11;
        r27 = r6;
        r6 = r20;
        r22 = r7;
        r7 = r27;
        r28 = r21;
        r8 = r19;
        r29 = r26;
        r30 = r11;
        r11 = r28;
        r12 = r22;
        r14 = r37;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14);
        if (r0 != r15) goto L_0x02c3;
    L_0x02ba:
        r2 = r0;
        r6 = r30;
        r7 = r36;
        r14 = r31;
        goto L_0x0351;
    L_0x02c3:
        r14 = r32;
        r12 = r33;
        r13 = r35;
        r9 = r37;
        r6 = r18;
        r7 = r24;
        r10 = r29;
        r1 = r30;
        r8 = -1;
        r11 = r36;
        r15 = r31;
        goto L_0x0017;
    L_0x02da:
        r19 = r1;
        r27 = r2;
        r15 = r4;
        r28 = r5;
        r22 = r7;
        r29 = r10;
        r30 = r11;
        r20 = r25;
        r1 = 50;
        r9 = r28;
        if (r9 != r1) goto L_0x0310;
    L_0x02ef:
        r7 = r27;
        r1 = 2;
        if (r7 != r1) goto L_0x030d;
    L_0x02f4:
        r14 = r31;
        r0 = r14;
        r1 = r32;
        r2 = r33;
        r3 = r15;
        r4 = r35;
        r5 = r19;
        r6 = r20;
        r7 = r22;
        r9 = r37;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r9);
        if (r0 != r15) goto L_0x032f;
    L_0x030c:
        goto L_0x032d;
    L_0x030d:
        r14 = r31;
        goto L_0x034c;
    L_0x0310:
        r8 = r0;
        r7 = r27;
        r14 = r31;
        r0 = r14;
        r1 = r32;
        r2 = r33;
        r3 = r15;
        r4 = r35;
        r5 = r30;
        r6 = r20;
        r10 = r22;
        r12 = r19;
        r13 = r37;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13);
        if (r0 != r15) goto L_0x032f;
    L_0x032d:
        r2 = r0;
        goto L_0x034d;
    L_0x032f:
        r12 = r33;
        r13 = r35;
        r9 = r37;
        r15 = r14;
        r6 = r18;
        r7 = r24;
        r10 = r29;
        r1 = r30;
        r8 = -1;
        r11 = r36;
        goto L_0x037b;
    L_0x0342:
        r30 = r5;
        r18 = r6;
        r24 = r7;
        r29 = r10;
    L_0x034a:
        r14 = r15;
        r15 = r4;
    L_0x034c:
        r2 = r15;
    L_0x034d:
        r6 = r30;
    L_0x034f:
        r7 = r36;
    L_0x0351:
        if (r6 != r7) goto L_0x035e;
    L_0x0353:
        if (r7 != 0) goto L_0x0356;
    L_0x0355:
        goto L_0x035e;
    L_0x0356:
        r8 = r2;
        r9 = r6;
        r1 = r18;
        r0 = r24;
        r2 = -1;
        goto L_0x038e;
    L_0x035e:
        r0 = r6;
        r1 = r33;
        r3 = r35;
        r4 = r32;
        r5 = r37;
        r0 = zza(r0, r1, r2, r3, r4, r5);
        r12 = r33;
        r13 = r35;
        r9 = r37;
        r1 = r6;
        r11 = r7;
        r15 = r14;
        r6 = r18;
        r7 = r24;
        r10 = r29;
        r8 = -1;
    L_0x037b:
        r14 = r32;
        goto L_0x0017;
    L_0x037f:
        r18 = r6;
        r24 = r7;
        r29 = r10;
        r7 = r11;
        r14 = r15;
        r9 = r1;
        r2 = r8;
        r1 = r18;
        r8 = r0;
        r0 = r24;
    L_0x038e:
        if (r0 == r2) goto L_0x0399;
    L_0x0390:
        r2 = (long) r0;
        r10 = r32;
        r0 = r29;
        r0.putInt(r10, r2, r1);
        goto L_0x039b;
    L_0x0399:
        r10 = r32;
    L_0x039b:
        r0 = r14.zzmt;
        if (r0 == 0) goto L_0x03dc;
    L_0x039f:
        r0 = 0;
        r11 = r14.zzmt;
        r12 = r11.length;
        r5 = r0;
        r13 = r16;
    L_0x03a6:
        if (r13 >= r12) goto L_0x03d5;
    L_0x03a8:
        r1 = r11[r13];
        r6 = r14.zzmx;
        r0 = r14.zzmi;
        r2 = r0[r1];
        r0 = r14.zzag(r1);
        r0 = r0 & r17;
        r3 = (long) r0;
        r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r3);
        if (r0 != 0) goto L_0x03be;
    L_0x03bd:
        goto L_0x03d0;
    L_0x03be:
        r4 = r14.zzaf(r1);
        if (r4 != 0) goto L_0x03c5;
    L_0x03c4:
        goto L_0x03d0;
    L_0x03c5:
        r3 = r14.zzmz;
        r3 = r3.zzg(r0);
        r0 = r14;
        r5 = r0.zza(r1, r2, r3, r4, r5, r6);
    L_0x03d0:
        r5 = (com.google.android.gms.internal.clearcut.zzey) r5;
        r13 = r13 + 1;
        goto L_0x03a6;
    L_0x03d5:
        if (r5 == 0) goto L_0x03dc;
    L_0x03d7:
        r0 = r14.zzmx;
        r0.zzf(r10, r5);
    L_0x03dc:
        if (r7 != 0) goto L_0x03e7;
    L_0x03de:
        r0 = r35;
        if (r8 == r0) goto L_0x03ee;
    L_0x03e2:
        r0 = com.google.android.gms.internal.clearcut.zzco.zzbo();
        throw r0;
    L_0x03e7:
        r0 = r35;
        if (r8 > r0) goto L_0x03ef;
    L_0x03eb:
        if (r9 == r7) goto L_0x03ee;
    L_0x03ed:
        goto L_0x03ef;
    L_0x03ee:
        return r8;
    L_0x03ef:
        r0 = com.google.android.gms.internal.clearcut.zzco.zzbo();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:31:0x0098 in {3, 5, 6, 7, 9, 10, 11, 12, 13, 15, 16, 17, 18, 20, 21, 23, 25, 28, 29, 30} preds:[]
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:242)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:42)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Unknown Source)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    private static int zza(byte[] r1, int r2, int r3, com.google.android.gms.internal.clearcut.zzfl r4, java.lang.Class<?> r5, com.google.android.gms.internal.clearcut.zzay r6) throws java.io.IOException {
        /*
        r0 = com.google.android.gms.internal.clearcut.zzdt.zzgq;
        r4 = r4.ordinal();
        r4 = r0[r4];
        switch(r4) {
            case 1: goto L_0x0084;
            case 2: goto L_0x007f;
            case 3: goto L_0x0072;
            case 4: goto L_0x0065;
            case 5: goto L_0x0065;
            case 6: goto L_0x005c;
            case 7: goto L_0x005c;
            case 8: goto L_0x0053;
            case 9: goto L_0x0046;
            case 10: goto L_0x0046;
            case 11: goto L_0x0046;
            case 12: goto L_0x003b;
            case 13: goto L_0x003b;
            case 14: goto L_0x002e;
            case 15: goto L_0x0023;
            case 16: goto L_0x0018;
            case 17: goto L_0x0013;
            default: goto L_0x000b;
        };
        r1 = new java.lang.RuntimeException;
        r2 = "unsupported field type.";
        r1.<init>(r2);
        throw r1;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzd(r1, r2, r6);
        return r1;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r1, r2, r6);
        r2 = r6.zzfe;
        r2 = com.google.android.gms.internal.clearcut.zzbk.zza(r2);
        goto L_0x0041;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r1, r2, r6);
        r2 = r6.zzfd;
        r2 = com.google.android.gms.internal.clearcut.zzbk.zzm(r2);
        goto L_0x004c;
        r4 = com.google.android.gms.internal.clearcut.zzea.zzcm();
        r4 = r4.zze(r5);
        r1 = zza(r4, r1, r2, r3, r6);
        return r1;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r1, r2, r6);
        r2 = r6.zzfe;
        r2 = java.lang.Long.valueOf(r2);
        goto L_0x0050;
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r1, r2, r6);
        r2 = r6.zzfd;
        r2 = java.lang.Integer.valueOf(r2);
        r6.zzff = r2;
        return r1;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzf(r1, r2);
        r1 = java.lang.Float.valueOf(r1);
        goto L_0x006d;
        r3 = com.google.android.gms.internal.clearcut.zzax.zzd(r1, r2);
        r1 = java.lang.Long.valueOf(r3);
        goto L_0x007a;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzc(r1, r2);
        r1 = java.lang.Integer.valueOf(r1);
        r6.zzff = r1;
        r1 = r2 + 4;
        return r1;
        r3 = com.google.android.gms.internal.clearcut.zzax.zze(r1, r2);
        r1 = java.lang.Double.valueOf(r3);
        r6.zzff = r1;
        r1 = r2 + 8;
        return r1;
        r1 = com.google.android.gms.internal.clearcut.zzax.zze(r1, r2, r6);
        return r1;
        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r1, r2, r6);
        r2 = r6.zzfe;
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x0092;
        r2 = 1;
        goto L_0x0093;
        r2 = 0;
        r2 = java.lang.Boolean.valueOf(r2);
        goto L_0x0050;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(byte[], int, int, com.google.android.gms.internal.clearcut.zzfl, java.lang.Class, com.google.android.gms.internal.clearcut.zzay):int");
    }

    static <T> zzds<T> zza(Class<T> cls, zzdm zzdm, zzdw zzdw, zzcy zzcy, zzex<?, ?> zzex, zzbu<?> zzbu, zzdj zzdj) {
        zzdm zzdm2 = zzdm;
        if (zzdm2 instanceof zzec) {
            int i;
            int i2;
            int i3;
            int zzcq;
            zzec zzec = (zzec) zzdm2;
            boolean z = zzec.zzcf() == zzg.zzkm;
            if (zzec.getFieldCount() == 0) {
                i = 0;
                i2 = i;
                i3 = i2;
            } else {
                int zzcp = zzec.zzcp();
                zzcq = zzec.zzcq();
                i = zzec.zzcu();
                i2 = zzcp;
                i3 = zzcq;
            }
            int[] iArr = new int[(i << 2)];
            Object[] objArr = new Object[(i << 1)];
            int[] iArr2 = zzec.zzcr() > 0 ? new int[zzec.zzcr()] : null;
            int[] iArr3 = zzec.zzcs() > 0 ? new int[zzec.zzcs()] : null;
            zzed zzco = zzec.zzco();
            if (zzco.next()) {
                zzcq = zzco.zzcx();
                i = 0;
                int i4 = i;
                int i5 = i4;
                while (true) {
                    int zza;
                    if (zzcq >= zzec.zzcv() || i >= ((zzcq - i2) << 2)) {
                        int i6;
                        if (zzco.zzda()) {
                            zzcq = (int) zzfd.zza(zzco.zzdb());
                            zza = (int) zzfd.zza(zzco.zzdc());
                            i6 = 0;
                        } else {
                            zzcq = (int) zzfd.zza(zzco.zzdd());
                            if (zzco.zzde()) {
                                zza = (int) zzfd.zza(zzco.zzdf());
                                i6 = zzco.zzdg();
                            } else {
                                zza = 0;
                                i6 = zza;
                            }
                        }
                        iArr[i] = zzco.zzcx();
                        int i7 = i + 1;
                        iArr[i7] = (((zzco.zzdi() ? C.ENCODING_PCM_A_LAW : 0) | (zzco.zzdh() ? C.ENCODING_PCM_MU_LAW : 0)) | (zzco.zzcy() << 20)) | zzcq;
                        iArr[i + 2] = zza | (i6 << 20);
                        if (zzco.zzdl() != null) {
                            zzcq = (i / 4) << 1;
                            objArr[zzcq] = zzco.zzdl();
                            if (zzco.zzdj() != null) {
                                objArr[zzcq + 1] = zzco.zzdj();
                            } else if (zzco.zzdk() != null) {
                                objArr[zzcq + 1] = zzco.zzdk();
                            }
                        } else if (zzco.zzdj() != null) {
                            objArr[((i / 4) << 1) + 1] = zzco.zzdj();
                        } else if (zzco.zzdk() != null) {
                            objArr[((i / 4) << 1) + 1] = zzco.zzdk();
                        }
                        zzcq = zzco.zzcy();
                        if (zzcq == zzcb.MAP.ordinal()) {
                            zzcq = i4 + 1;
                            iArr2[i4] = i;
                            i4 = zzcq;
                        } else if (zzcq >= 18 && zzcq <= 49) {
                            zzcq = i5 + 1;
                            iArr3[i5] = iArr[i7] & 1048575;
                            i5 = zzcq;
                        }
                        if (!zzco.next()) {
                            break;
                        }
                        zzcq = zzco.zzcx();
                    } else {
                        for (zza = 0; zza < 4; zza++) {
                            iArr[i + zza] = -1;
                        }
                    }
                    i += 4;
                }
            }
            return new zzds(iArr, objArr, i2, i3, zzec.zzcv(), zzec.zzch(), z, false, zzec.zzct(), iArr2, iArr3, zzdw, zzcy, zzex, zzbu, zzdj);
        }
        ((zzes) zzdm2).zzcf();
        throw new NoSuchMethodError();
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzck<?> zzck, UB ub, zzex<UT, UB> zzex) {
        zzdh zzl = this.zzmz.zzl(zzae(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (zzck.zzb(((Integer) entry.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = zzex.zzdz();
                }
                zzbg zzk = zzbb.zzk(zzdg.zza(zzl, entry.getKey(), entry.getValue()));
                try {
                    zzdg.zza(zzk.zzae(), zzl, entry.getKey(), entry.getValue());
                    zzex.zza((Object) ub, i2, zzk.zzad());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private static void zza(int i, Object obj, zzfr zzfr) throws IOException {
        if (obj instanceof String) {
            zzfr.zza(i, (String) obj);
        } else {
            zzfr.zza(i, (zzbb) obj);
        }
    }

    private static <UT, UB> void zza(zzex<UT, UB> zzex, T t, zzfr zzfr) throws IOException {
        zzex.zza(zzex.zzq(t), zzfr);
    }

    private final <K, V> void zza(zzfr zzfr, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzfr.zza(i, this.zzmz.zzl(zzae(i2)), this.zzmz.zzh(obj));
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzag = (long) (zzag(i) & 1048575);
        if (zza((Object) t2, i)) {
            Object zzo = zzfd.zzo(t, zzag);
            Object zzo2 = zzfd.zzo(t2, zzag);
            if (zzo == null || zzo2 == null) {
                if (zzo2 != null) {
                    zzfd.zza((Object) t, zzag, zzo2);
                    zzb((Object) t, i);
                }
                return;
            }
            zzfd.zza((Object) t, zzag, zzci.zza(zzo, zzo2));
            zzb((Object) t, i);
        }
    }

    private final boolean zza(T t, int i) {
        if (this.zzmq) {
            i = zzag(i);
            long j = (long) (i & 1048575);
            switch ((i & 267386880) >>> 20) {
                case 0:
                    return zzfd.zzn(t, j) != 0.0d;
                case 1:
                    return zzfd.zzm(t, j) != 0.0f;
                case 2:
                    return zzfd.zzk(t, j) != 0;
                case 3:
                    return zzfd.zzk(t, j) != 0;
                case 4:
                    return zzfd.zzj(t, j) != 0;
                case 5:
                    return zzfd.zzk(t, j) != 0;
                case 6:
                    return zzfd.zzj(t, j) != 0;
                case 7:
                    return zzfd.zzl(t, j);
                case 8:
                    Object zzo = zzfd.zzo(t, j);
                    if (zzo instanceof String) {
                        return !((String) zzo).isEmpty();
                    } else {
                        if (zzo instanceof zzbb) {
                            return !zzbb.zzfi.equals(zzo);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                case 9:
                    return zzfd.zzo(t, j) != null;
                case 10:
                    return !zzbb.zzfi.equals(zzfd.zzo(t, j));
                case 11:
                    return zzfd.zzj(t, j) != 0;
                case 12:
                    return zzfd.zzj(t, j) != 0;
                case 13:
                    return zzfd.zzj(t, j) != 0;
                case 14:
                    return zzfd.zzk(t, j) != 0;
                case 15:
                    return zzfd.zzj(t, j) != 0;
                case 16:
                    return zzfd.zzk(t, j) != 0;
                case 17:
                    return zzfd.zzo(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        i = zzah(i);
        return (zzfd.zzj(t, (long) (i & 1048575)) & (1 << (i >>> 20))) != 0;
    }

    private final boolean zza(T t, int i, int i2) {
        return zzfd.zzj(t, (long) (zzah(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzmq ? zza((Object) t, i) : (i2 & i3) != 0;
    }

    private static boolean zza(Object obj, int i, zzef zzef) {
        return zzef.zzo(zzfd.zzo(obj, (long) (i & 1048575)));
    }

    private final zzef zzad(int i) {
        i = (i / 4) << 1;
        zzef zzef = (zzef) this.zzmj[i];
        if (zzef != null) {
            return zzef;
        }
        zzef = zzea.zzcm().zze((Class) this.zzmj[i + 1]);
        this.zzmj[i] = zzef;
        return zzef;
    }

    private final Object zzae(int i) {
        return this.zzmj[(i / 4) << 1];
    }

    private final zzck<?> zzaf(int i) {
        return (zzck) this.zzmj[((i / 4) << 1) + 1];
    }

    private final int zzag(int i) {
        return this.zzmi[i + 1];
    }

    private final int zzah(int i) {
        return this.zzmi[i + 2];
    }

    private final int zzai(int i) {
        if (i >= this.zzmk) {
            int i2;
            if (i < this.zzmm) {
                i2 = (i - this.zzmk) << 2;
                return this.zzmi[i2] == i ? i2 : -1;
            } else if (i <= this.zzml) {
                i2 = this.zzmm - this.zzmk;
                int length = (this.zzmi.length / 4) - 1;
                while (i2 <= length) {
                    int i3 = (length + i2) >>> 1;
                    int i4 = i3 << 2;
                    int i5 = this.zzmi[i4];
                    if (i == i5) {
                        return i4;
                    }
                    if (i < i5) {
                        length = i3 - 1;
                    } else {
                        i2 = i3 + 1;
                    }
                }
            }
        }
        return -1;
    }

    private final void zzb(T t, int i) {
        if (!this.zzmq) {
            i = zzah(i);
            long j = (long) (i & 1048575);
            zzfd.zza((Object) t, j, zzfd.zzj(t, j) | (1 << (i >>> 20)));
        }
    }

    private final void zzb(T t, int i, int i2) {
        zzfd.zza((Object) t, (long) (zzah(i2) & 1048575), i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0523  */
    /* JADX WARNING: Missing block: B:105:0x0344, code skipped:
            r14 = r13;
     */
    /* JADX WARNING: Missing block: B:171:0x051d, code skipped:
            r5 = r12 + 4;
     */
    private final void zzb(T r21, com.google.android.gms.internal.clearcut.zzfr r22) throws java.io.IOException {
        /*
        r20 = this;
        r0 = r20;
        r1 = r21;
        r2 = r22;
        r3 = r0.zzmo;
        if (r3 == 0) goto L_0x0021;
    L_0x000a:
        r3 = r0.zzmy;
        r3 = r3.zza(r1);
        r5 = r3.isEmpty();
        if (r5 != 0) goto L_0x0021;
    L_0x0016:
        r3 = r3.iterator();
        r5 = r3.next();
        r5 = (java.util.Map.Entry) r5;
        goto L_0x0023;
    L_0x0021:
        r3 = 0;
        r5 = 0;
    L_0x0023:
        r6 = -1;
        r7 = r0.zzmi;
        r7 = r7.length;
        r9 = zzmh;
        r10 = r5;
        r5 = 0;
        r11 = 0;
    L_0x002c:
        if (r5 >= r7) goto L_0x0521;
    L_0x002e:
        r12 = r0.zzag(r5);
        r13 = r0.zzmi;
        r13 = r13[r5];
        r14 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r14 = r14 & r12;
        r14 = r14 >>> 20;
        r15 = r0.zzmq;
        r16 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        if (r15 != 0) goto L_0x0061;
    L_0x0042:
        r15 = 17;
        if (r14 > r15) goto L_0x0061;
    L_0x0046:
        r15 = r0.zzmi;
        r17 = r5 + 2;
        r15 = r15[r17];
        r8 = r15 & r16;
        if (r8 == r6) goto L_0x0059;
    L_0x0050:
        r18 = r5;
        r4 = (long) r8;
        r11 = r9.getInt(r1, r4);
        r6 = r8;
        goto L_0x005b;
    L_0x0059:
        r18 = r5;
    L_0x005b:
        r4 = r15 >>> 20;
        r5 = 1;
        r8 = r5 << r4;
        goto L_0x0064;
    L_0x0061:
        r18 = r5;
        r8 = 0;
    L_0x0064:
        if (r10 == 0) goto L_0x0083;
    L_0x0066:
        r4 = r0.zzmy;
        r4 = r4.zza(r10);
        if (r4 > r13) goto L_0x0083;
    L_0x006e:
        r4 = r0.zzmy;
        r4.zza(r2, r10);
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0081;
    L_0x0079:
        r4 = r3.next();
        r4 = (java.util.Map.Entry) r4;
        r10 = r4;
        goto L_0x0064;
    L_0x0081:
        r10 = 0;
        goto L_0x0064;
    L_0x0083:
        r4 = r12 & r16;
        r4 = (long) r4;
        switch(r14) {
            case 0: goto L_0x0510;
            case 1: goto L_0x0502;
            case 2: goto L_0x04f4;
            case 3: goto L_0x04e6;
            case 4: goto L_0x04d8;
            case 5: goto L_0x04ca;
            case 6: goto L_0x04bc;
            case 7: goto L_0x04ae;
            case 8: goto L_0x049f;
            case 9: goto L_0x048c;
            case 10: goto L_0x047b;
            case 11: goto L_0x046c;
            case 12: goto L_0x045d;
            case 13: goto L_0x044e;
            case 14: goto L_0x043f;
            case 15: goto L_0x0430;
            case 16: goto L_0x0421;
            case 17: goto L_0x040e;
            case 18: goto L_0x03fc;
            case 19: goto L_0x03ea;
            case 20: goto L_0x03d8;
            case 21: goto L_0x03c6;
            case 22: goto L_0x03b4;
            case 23: goto L_0x03a2;
            case 24: goto L_0x0390;
            case 25: goto L_0x037e;
            case 26: goto L_0x036d;
            case 27: goto L_0x0358;
            case 28: goto L_0x0347;
            case 29: goto L_0x0334;
            case 30: goto L_0x0323;
            case 31: goto L_0x0312;
            case 32: goto L_0x0301;
            case 33: goto L_0x02f0;
            case 34: goto L_0x02df;
            case 35: goto L_0x02cd;
            case 36: goto L_0x02bb;
            case 37: goto L_0x02a9;
            case 38: goto L_0x0297;
            case 39: goto L_0x0285;
            case 40: goto L_0x0273;
            case 41: goto L_0x0261;
            case 42: goto L_0x024f;
            case 43: goto L_0x023d;
            case 44: goto L_0x022b;
            case 45: goto L_0x0219;
            case 46: goto L_0x0207;
            case 47: goto L_0x01f5;
            case 48: goto L_0x01e3;
            case 49: goto L_0x01ce;
            case 50: goto L_0x01c3;
            case 51: goto L_0x01b2;
            case 52: goto L_0x01a1;
            case 53: goto L_0x0190;
            case 54: goto L_0x017f;
            case 55: goto L_0x016e;
            case 56: goto L_0x015d;
            case 57: goto L_0x014c;
            case 58: goto L_0x013b;
            case 59: goto L_0x012a;
            case 60: goto L_0x0115;
            case 61: goto L_0x0102;
            case 62: goto L_0x00f2;
            case 63: goto L_0x00e2;
            case 64: goto L_0x00d2;
            case 65: goto L_0x00c2;
            case 66: goto L_0x00b2;
            case 67: goto L_0x00a2;
            case 68: goto L_0x008e;
            default: goto L_0x0089;
        };
    L_0x0089:
        r12 = r18;
    L_0x008b:
        r14 = 0;
        goto L_0x051d;
    L_0x008e:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0096:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzad(r12);
        r2.zzb(r13, r4, r5);
        goto L_0x008b;
    L_0x00a2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00aa:
        r4 = zzh(r1, r4);
        r2.zzb(r13, r4);
        goto L_0x008b;
    L_0x00b2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00ba:
        r4 = zzg(r1, r4);
        r2.zze(r13, r4);
        goto L_0x008b;
    L_0x00c2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00ca:
        r4 = zzh(r1, r4);
        r2.zzj(r13, r4);
        goto L_0x008b;
    L_0x00d2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00da:
        r4 = zzg(r1, r4);
        r2.zzm(r13, r4);
        goto L_0x008b;
    L_0x00e2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00ea:
        r4 = zzg(r1, r4);
        r2.zzn(r13, r4);
        goto L_0x008b;
    L_0x00f2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x00fa:
        r4 = zzg(r1, r4);
        r2.zzd(r13, r4);
        goto L_0x008b;
    L_0x0102:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x010a:
        r4 = r9.getObject(r1, r4);
        r4 = (com.google.android.gms.internal.clearcut.zzbb) r4;
        r2.zza(r13, r4);
        goto L_0x008b;
    L_0x0115:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x011d:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzad(r12);
        r2.zza(r13, r4, r5);
        goto L_0x008b;
    L_0x012a:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0132:
        r4 = r9.getObject(r1, r4);
        zza(r13, r4, r2);
        goto L_0x008b;
    L_0x013b:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0143:
        r4 = zzi(r1, r4);
        r2.zzb(r13, r4);
        goto L_0x008b;
    L_0x014c:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0154:
        r4 = zzg(r1, r4);
        r2.zzf(r13, r4);
        goto L_0x008b;
    L_0x015d:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0165:
        r4 = zzh(r1, r4);
        r2.zzc(r13, r4);
        goto L_0x008b;
    L_0x016e:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0176:
        r4 = zzg(r1, r4);
        r2.zzc(r13, r4);
        goto L_0x008b;
    L_0x017f:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0187:
        r4 = zzh(r1, r4);
        r2.zza(r13, r4);
        goto L_0x008b;
    L_0x0190:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x0198:
        r4 = zzh(r1, r4);
        r2.zzi(r13, r4);
        goto L_0x008b;
    L_0x01a1:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x01a9:
        r4 = zzf(r1, r4);
        r2.zza(r13, r4);
        goto L_0x008b;
    L_0x01b2:
        r12 = r18;
        r8 = r0.zza(r1, r13, r12);
        if (r8 == 0) goto L_0x008b;
    L_0x01ba:
        r4 = zze(r1, r4);
        r2.zza(r13, r4);
        goto L_0x008b;
    L_0x01c3:
        r12 = r18;
        r4 = r9.getObject(r1, r4);
        r0.zza(r2, r13, r4, r12);
        goto L_0x008b;
    L_0x01ce:
        r12 = r18;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r5 = r0.zzad(r12);
        com.google.android.gms.internal.clearcut.zzeh.zzb(r8, r4, r2, r5);
        goto L_0x008b;
    L_0x01e3:
        r12 = r18;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r13 = 1;
        com.google.android.gms.internal.clearcut.zzeh.zze(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x01f5:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzj(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0207:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzg(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0219:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzl(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x022b:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzm(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x023d:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzi(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x024f:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzn(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0261:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzk(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0273:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzf(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0285:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzh(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x0297:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzd(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02a9:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzc(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02bb:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzb(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02cd:
        r12 = r18;
        r13 = 1;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zza(r8, r4, r2, r13);
        goto L_0x008b;
    L_0x02df:
        r12 = r18;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r13 = 0;
        com.google.android.gms.internal.clearcut.zzeh.zze(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x02f0:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzj(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0301:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzg(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0312:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzl(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0323:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzm(r8, r4, r2, r13);
        goto L_0x0344;
    L_0x0334:
        r12 = r18;
        r13 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzi(r8, r4, r2, r13);
    L_0x0344:
        r14 = r13;
        goto L_0x051d;
    L_0x0347:
        r12 = r18;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzb(r8, r4, r2);
        goto L_0x008b;
    L_0x0358:
        r12 = r18;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r5 = r0.zzad(r12);
        com.google.android.gms.internal.clearcut.zzeh.zza(r8, r4, r2, r5);
        goto L_0x008b;
    L_0x036d:
        r12 = r18;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zza(r8, r4, r2);
        goto L_0x008b;
    L_0x037e:
        r12 = r18;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r14 = 0;
        com.google.android.gms.internal.clearcut.zzeh.zzn(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x0390:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzk(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03a2:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzf(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03b4:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzh(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03c6:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzd(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03d8:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzc(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03ea:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zzb(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x03fc:
        r12 = r18;
        r14 = 0;
        r8 = r0.zzmi;
        r8 = r8[r12];
        r4 = r9.getObject(r1, r4);
        r4 = (java.util.List) r4;
        com.google.android.gms.internal.clearcut.zzeh.zza(r8, r4, r2, r14);
        goto L_0x051d;
    L_0x040e:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0414:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzad(r12);
        r2.zzb(r13, r4, r5);
        goto L_0x051d;
    L_0x0421:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0427:
        r4 = r9.getLong(r1, r4);
        r2.zzb(r13, r4);
        goto L_0x051d;
    L_0x0430:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0436:
        r4 = r9.getInt(r1, r4);
        r2.zze(r13, r4);
        goto L_0x051d;
    L_0x043f:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0445:
        r4 = r9.getLong(r1, r4);
        r2.zzj(r13, r4);
        goto L_0x051d;
    L_0x044e:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0454:
        r4 = r9.getInt(r1, r4);
        r2.zzm(r13, r4);
        goto L_0x051d;
    L_0x045d:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0463:
        r4 = r9.getInt(r1, r4);
        r2.zzn(r13, r4);
        goto L_0x051d;
    L_0x046c:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0472:
        r4 = r9.getInt(r1, r4);
        r2.zzd(r13, r4);
        goto L_0x051d;
    L_0x047b:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0481:
        r4 = r9.getObject(r1, r4);
        r4 = (com.google.android.gms.internal.clearcut.zzbb) r4;
        r2.zza(r13, r4);
        goto L_0x051d;
    L_0x048c:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0492:
        r4 = r9.getObject(r1, r4);
        r5 = r0.zzad(r12);
        r2.zza(r13, r4, r5);
        goto L_0x051d;
    L_0x049f:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04a5:
        r4 = r9.getObject(r1, r4);
        zza(r13, r4, r2);
        goto L_0x051d;
    L_0x04ae:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04b4:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzl(r1, r4);
        r2.zzb(r13, r4);
        goto L_0x051d;
    L_0x04bc:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04c2:
        r4 = r9.getInt(r1, r4);
        r2.zzf(r13, r4);
        goto L_0x051d;
    L_0x04ca:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04d0:
        r4 = r9.getLong(r1, r4);
        r2.zzc(r13, r4);
        goto L_0x051d;
    L_0x04d8:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04de:
        r4 = r9.getInt(r1, r4);
        r2.zzc(r13, r4);
        goto L_0x051d;
    L_0x04e6:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04ec:
        r4 = r9.getLong(r1, r4);
        r2.zza(r13, r4);
        goto L_0x051d;
    L_0x04f4:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x04fa:
        r4 = r9.getLong(r1, r4);
        r2.zzi(r13, r4);
        goto L_0x051d;
    L_0x0502:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0508:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzm(r1, r4);
        r2.zza(r13, r4);
        goto L_0x051d;
    L_0x0510:
        r12 = r18;
        r14 = 0;
        r8 = r8 & r11;
        if (r8 == 0) goto L_0x051d;
    L_0x0516:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzn(r1, r4);
        r2.zza(r13, r4);
    L_0x051d:
        r5 = r12 + 4;
        goto L_0x002c;
    L_0x0521:
        if (r10 == 0) goto L_0x0538;
    L_0x0523:
        r4 = r0.zzmy;
        r4.zza(r2, r10);
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0536;
    L_0x052e:
        r4 = r3.next();
        r4 = (java.util.Map.Entry) r4;
        r10 = r4;
        goto L_0x0521;
    L_0x0536:
        r10 = 0;
        goto L_0x0521;
    L_0x0538:
        r3 = r0.zzmx;
        zza(r3, r1, r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzb(java.lang.Object, com.google.android.gms.internal.clearcut.zzfr):void");
    }

    private final void zzb(T t, T t2, int i) {
        int zzag = zzag(i);
        int i2 = this.zzmi[i];
        long j = (long) (zzag & 1048575);
        if (zza((Object) t2, i2, i)) {
            Object zzo = zzfd.zzo(t, j);
            Object zzo2 = zzfd.zzo(t2, j);
            if (zzo == null || zzo2 == null) {
                if (zzo2 != null) {
                    zzfd.zza((Object) t, j, zzo2);
                    zzb((Object) t, i2, i);
                }
                return;
            }
            zzfd.zza((Object) t, j, zzci.zza(zzo, zzo2));
            zzb((Object) t, i2, i);
        }
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((Object) t, i) == zza((Object) t2, i);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzfd.zzo(obj, j);
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zzfd.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zzfd.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zzfd.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zzfd.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zzfd.zzo(t, j)).booleanValue();
    }

    private static zzey zzn(Object obj) {
        zzcg zzcg = (zzcg) obj;
        zzey zzey = zzcg.zzjp;
        if (zzey != zzey.zzea()) {
            return zzey;
        }
        zzey = zzey.zzeb();
        zzcg.zzjp = zzey;
        return zzey;
    }

    /* JADX WARNING: Missing block: B:8:0x0038, code skipped:
            if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:13:0x005c, code skipped:
            if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:17:0x0070, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:21:0x0082, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:25:0x0096, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:29:0x00a8, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:33:0x00ba, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:37:0x00cc, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:41:0x00e2, code skipped:
            if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:45:0x00f8, code skipped:
            if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:49:0x010e, code skipped:
            if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:53:0x0120, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzl(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzl(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:57:0x0132, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:61:0x0145, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:65:0x0156, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:69:0x0169, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:73:0x017c, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:77:0x018d, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:81:0x01a0, code skipped:
            if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Missing block: B:82:0x01a2, code skipped:
            r3 = false;
     */
    public final boolean equals(T r10, T r11) {
        /*
        r9 = this;
        r0 = r9.zzmi;
        r1 = 0;
        r0 = r0.length;
        r2 = r1;
    L_0x0005:
        r3 = 1;
        if (r2 >= r0) goto L_0x01aa;
    L_0x0008:
        r4 = r9.zzag(r2);
        r5 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r6 = r4 & r5;
        r6 = (long) r6;
        r8 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r4 = r4 & r8;
        r4 = r4 >>> 20;
        switch(r4) {
            case 0: goto L_0x0190;
            case 1: goto L_0x017f;
            case 2: goto L_0x016c;
            case 3: goto L_0x0159;
            case 4: goto L_0x0148;
            case 5: goto L_0x0135;
            case 6: goto L_0x0124;
            case 7: goto L_0x0112;
            case 8: goto L_0x00fc;
            case 9: goto L_0x00e6;
            case 10: goto L_0x00d0;
            case 11: goto L_0x00be;
            case 12: goto L_0x00ac;
            case 13: goto L_0x009a;
            case 14: goto L_0x0086;
            case 15: goto L_0x0074;
            case 16: goto L_0x0060;
            case 17: goto L_0x004a;
            case 18: goto L_0x003c;
            case 19: goto L_0x003c;
            case 20: goto L_0x003c;
            case 21: goto L_0x003c;
            case 22: goto L_0x003c;
            case 23: goto L_0x003c;
            case 24: goto L_0x003c;
            case 25: goto L_0x003c;
            case 26: goto L_0x003c;
            case 27: goto L_0x003c;
            case 28: goto L_0x003c;
            case 29: goto L_0x003c;
            case 30: goto L_0x003c;
            case 31: goto L_0x003c;
            case 32: goto L_0x003c;
            case 33: goto L_0x003c;
            case 34: goto L_0x003c;
            case 35: goto L_0x003c;
            case 36: goto L_0x003c;
            case 37: goto L_0x003c;
            case 38: goto L_0x003c;
            case 39: goto L_0x003c;
            case 40: goto L_0x003c;
            case 41: goto L_0x003c;
            case 42: goto L_0x003c;
            case 43: goto L_0x003c;
            case 44: goto L_0x003c;
            case 45: goto L_0x003c;
            case 46: goto L_0x003c;
            case 47: goto L_0x003c;
            case 48: goto L_0x003c;
            case 49: goto L_0x003c;
            case 50: goto L_0x003c;
            case 51: goto L_0x001c;
            case 52: goto L_0x001c;
            case 53: goto L_0x001c;
            case 54: goto L_0x001c;
            case 55: goto L_0x001c;
            case 56: goto L_0x001c;
            case 57: goto L_0x001c;
            case 58: goto L_0x001c;
            case 59: goto L_0x001c;
            case 60: goto L_0x001c;
            case 61: goto L_0x001c;
            case 62: goto L_0x001c;
            case 63: goto L_0x001c;
            case 64: goto L_0x001c;
            case 65: goto L_0x001c;
            case 66: goto L_0x001c;
            case 67: goto L_0x001c;
            case 68: goto L_0x001c;
            default: goto L_0x001a;
        };
    L_0x001a:
        goto L_0x01a3;
    L_0x001c:
        r4 = r9.zzah(r2);
        r4 = r4 & r5;
        r4 = (long) r4;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r4);
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r4);
        if (r8 != r4) goto L_0x01a2;
    L_0x002c:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6);
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5);
        if (r4 != 0) goto L_0x01a3;
    L_0x003a:
        goto L_0x018f;
    L_0x003c:
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6);
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzd(r3, r4);
        goto L_0x01a3;
    L_0x004a:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x0050:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6);
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5);
        if (r4 != 0) goto L_0x01a3;
    L_0x005e:
        goto L_0x01a2;
    L_0x0060:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x0066:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6);
        r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01a3;
    L_0x0072:
        goto L_0x018f;
    L_0x0074:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x007a:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6);
        if (r4 == r5) goto L_0x01a3;
    L_0x0084:
        goto L_0x01a2;
    L_0x0086:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x008c:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6);
        r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01a3;
    L_0x0098:
        goto L_0x018f;
    L_0x009a:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x00a0:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6);
        if (r4 == r5) goto L_0x01a3;
    L_0x00aa:
        goto L_0x01a2;
    L_0x00ac:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x00b2:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6);
        if (r4 == r5) goto L_0x01a3;
    L_0x00bc:
        goto L_0x018f;
    L_0x00be:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x00c4:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6);
        if (r4 == r5) goto L_0x01a3;
    L_0x00ce:
        goto L_0x01a2;
    L_0x00d0:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x00d6:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6);
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5);
        if (r4 != 0) goto L_0x01a3;
    L_0x00e4:
        goto L_0x018f;
    L_0x00e6:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x00ec:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6);
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5);
        if (r4 != 0) goto L_0x01a3;
    L_0x00fa:
        goto L_0x01a2;
    L_0x00fc:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x0102:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6);
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5);
        if (r4 != 0) goto L_0x01a3;
    L_0x0110:
        goto L_0x018f;
    L_0x0112:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x0118:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzl(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzl(r11, r6);
        if (r4 == r5) goto L_0x01a3;
    L_0x0122:
        goto L_0x01a2;
    L_0x0124:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x012a:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6);
        if (r4 == r5) goto L_0x01a3;
    L_0x0134:
        goto L_0x018f;
    L_0x0135:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x013b:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6);
        r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01a3;
    L_0x0147:
        goto L_0x01a2;
    L_0x0148:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x014e:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6);
        if (r4 == r5) goto L_0x01a3;
    L_0x0158:
        goto L_0x018f;
    L_0x0159:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x015f:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6);
        r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01a3;
    L_0x016b:
        goto L_0x01a2;
    L_0x016c:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x0172:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6);
        r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01a3;
    L_0x017e:
        goto L_0x018f;
    L_0x017f:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x0185:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6);
        r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6);
        if (r4 == r5) goto L_0x01a3;
    L_0x018f:
        goto L_0x01a2;
    L_0x0190:
        r4 = r9.zzc(r10, r11, r2);
        if (r4 == 0) goto L_0x01a2;
    L_0x0196:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6);
        r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6);
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x01a3;
    L_0x01a2:
        r3 = r1;
    L_0x01a3:
        if (r3 != 0) goto L_0x01a6;
    L_0x01a5:
        return r1;
    L_0x01a6:
        r2 = r2 + 4;
        goto L_0x0005;
    L_0x01aa:
        r0 = r9.zzmx;
        r0 = r0.zzq(r10);
        r2 = r9.zzmx;
        r2 = r2.zzq(r11);
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x01bd;
    L_0x01bc:
        return r1;
    L_0x01bd:
        r0 = r9.zzmo;
        if (r0 == 0) goto L_0x01d2;
    L_0x01c1:
        r0 = r9.zzmy;
        r10 = r0.zza(r10);
        r0 = r9.zzmy;
        r11 = r0.zza(r11);
        r10 = r10.equals(r11);
        return r10;
    L_0x01d2:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Missing block: B:22:0x0061, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
            r2 = r2 * 53;
     */
    /* JADX WARNING: Missing block: B:34:0x0093, code skipped:
            r2 = r2 * 53;
            r3 = zzg(r9, r5);
     */
    /* JADX WARNING: Missing block: B:39:0x00a8, code skipped:
            r2 = r2 * 53;
            r3 = zzh(r9, r5);
     */
    /* JADX WARNING: Missing block: B:47:0x00ce, code skipped:
            if (r3 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Missing block: B:48:0x00d1, code skipped:
            r2 = r2 * 53;
            r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
     */
    /* JADX WARNING: Missing block: B:49:0x00d7, code skipped:
            r3 = r3.hashCode();
     */
    /* JADX WARNING: Missing block: B:51:0x00e0, code skipped:
            if (r3 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Missing block: B:52:0x00e2, code skipped:
            r7 = r3.hashCode();
     */
    /* JADX WARNING: Missing block: B:53:0x00e6, code skipped:
            r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Missing block: B:54:0x00ea, code skipped:
            r2 = r2 * 53;
            r3 = ((java.lang.String) com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5)).hashCode();
     */
    /* JADX WARNING: Missing block: B:56:0x00fd, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzci.zzc(r3);
     */
    /* JADX WARNING: Missing block: B:60:0x0116, code skipped:
            r3 = java.lang.Float.floatToIntBits(r3);
     */
    /* JADX WARNING: Missing block: B:62:0x0121, code skipped:
            r3 = java.lang.Double.doubleToLongBits(r3);
     */
    /* JADX WARNING: Missing block: B:63:0x0125, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
     */
    /* JADX WARNING: Missing block: B:64:0x0129, code skipped:
            r2 = r2 + r3;
     */
    /* JADX WARNING: Missing block: B:65:0x012a, code skipped:
            r1 = r1 + 4;
     */
    public final int hashCode(T r9) {
        /*
        r8 = this;
        r0 = r8.zzmi;
        r1 = 0;
        r0 = r0.length;
        r2 = r1;
    L_0x0005:
        if (r1 >= r0) goto L_0x012e;
    L_0x0007:
        r3 = r8.zzag(r1);
        r4 = r8.zzmi;
        r4 = r4[r1];
        r5 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r5 = r5 & r3;
        r5 = (long) r5;
        r7 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r3 = r3 & r7;
        r3 = r3 >>> 20;
        r7 = 37;
        switch(r3) {
            case 0: goto L_0x011b;
            case 1: goto L_0x0110;
            case 2: goto L_0x0109;
            case 3: goto L_0x0109;
            case 4: goto L_0x0102;
            case 5: goto L_0x0109;
            case 6: goto L_0x0102;
            case 7: goto L_0x00f7;
            case 8: goto L_0x00ea;
            case 9: goto L_0x00dc;
            case 10: goto L_0x00d1;
            case 11: goto L_0x0102;
            case 12: goto L_0x0102;
            case 13: goto L_0x0102;
            case 14: goto L_0x0109;
            case 15: goto L_0x0102;
            case 16: goto L_0x0109;
            case 17: goto L_0x00ca;
            case 18: goto L_0x00d1;
            case 19: goto L_0x00d1;
            case 20: goto L_0x00d1;
            case 21: goto L_0x00d1;
            case 22: goto L_0x00d1;
            case 23: goto L_0x00d1;
            case 24: goto L_0x00d1;
            case 25: goto L_0x00d1;
            case 26: goto L_0x00d1;
            case 27: goto L_0x00d1;
            case 28: goto L_0x00d1;
            case 29: goto L_0x00d1;
            case 30: goto L_0x00d1;
            case 31: goto L_0x00d1;
            case 32: goto L_0x00d1;
            case 33: goto L_0x00d1;
            case 34: goto L_0x00d1;
            case 35: goto L_0x00d1;
            case 36: goto L_0x00d1;
            case 37: goto L_0x00d1;
            case 38: goto L_0x00d1;
            case 39: goto L_0x00d1;
            case 40: goto L_0x00d1;
            case 41: goto L_0x00d1;
            case 42: goto L_0x00d1;
            case 43: goto L_0x00d1;
            case 44: goto L_0x00d1;
            case 45: goto L_0x00d1;
            case 46: goto L_0x00d1;
            case 47: goto L_0x00d1;
            case 48: goto L_0x00d1;
            case 49: goto L_0x00d1;
            case 50: goto L_0x00d1;
            case 51: goto L_0x00bd;
            case 52: goto L_0x00b0;
            case 53: goto L_0x00a2;
            case 54: goto L_0x009b;
            case 55: goto L_0x008d;
            case 56: goto L_0x0086;
            case 57: goto L_0x007f;
            case 58: goto L_0x0071;
            case 59: goto L_0x0069;
            case 60: goto L_0x005b;
            case 61: goto L_0x0053;
            case 62: goto L_0x004c;
            case 63: goto L_0x0045;
            case 64: goto L_0x003e;
            case 65: goto L_0x0036;
            case 66: goto L_0x002f;
            case 67: goto L_0x0027;
            case 68: goto L_0x0020;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x012a;
    L_0x0020:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x0026:
        goto L_0x0061;
    L_0x0027:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x002d:
        goto L_0x00a8;
    L_0x002f:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x0035:
        goto L_0x004b;
    L_0x0036:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x003c:
        goto L_0x00a8;
    L_0x003e:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x0044:
        goto L_0x004b;
    L_0x0045:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x004b:
        goto L_0x0093;
    L_0x004c:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x0052:
        goto L_0x0093;
    L_0x0053:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x0059:
        goto L_0x00d1;
    L_0x005b:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x0061:
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
        r2 = r2 * 53;
        goto L_0x00d7;
    L_0x0069:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x006f:
        goto L_0x00ea;
    L_0x0071:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x0077:
        r2 = r2 * 53;
        r3 = zzi(r9, r5);
        goto L_0x00fd;
    L_0x007f:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x0085:
        goto L_0x0093;
    L_0x0086:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x008c:
        goto L_0x00a8;
    L_0x008d:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x0093:
        r2 = r2 * 53;
        r3 = zzg(r9, r5);
        goto L_0x0129;
    L_0x009b:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x00a1:
        goto L_0x00a8;
    L_0x00a2:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x00a8:
        r2 = r2 * 53;
        r3 = zzh(r9, r5);
        goto L_0x0125;
    L_0x00b0:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x00b6:
        r2 = r2 * 53;
        r3 = zzf(r9, r5);
        goto L_0x0116;
    L_0x00bd:
        r3 = r8.zza(r9, r4, r1);
        if (r3 == 0) goto L_0x012a;
    L_0x00c3:
        r2 = r2 * 53;
        r3 = zze(r9, r5);
        goto L_0x0121;
    L_0x00ca:
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
        if (r3 == 0) goto L_0x00e6;
    L_0x00d0:
        goto L_0x00e2;
    L_0x00d1:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
    L_0x00d7:
        r3 = r3.hashCode();
        goto L_0x0129;
    L_0x00dc:
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
        if (r3 == 0) goto L_0x00e6;
    L_0x00e2:
        r7 = r3.hashCode();
    L_0x00e6:
        r2 = r2 * 53;
        r2 = r2 + r7;
        goto L_0x012a;
    L_0x00ea:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
        r3 = (java.lang.String) r3;
        r3 = r3.hashCode();
        goto L_0x0129;
    L_0x00f7:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzl(r9, r5);
    L_0x00fd:
        r3 = com.google.android.gms.internal.clearcut.zzci.zzc(r3);
        goto L_0x0129;
    L_0x0102:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzj(r9, r5);
        goto L_0x0129;
    L_0x0109:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzk(r9, r5);
        goto L_0x0125;
    L_0x0110:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzm(r9, r5);
    L_0x0116:
        r3 = java.lang.Float.floatToIntBits(r3);
        goto L_0x0129;
    L_0x011b:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzn(r9, r5);
    L_0x0121:
        r3 = java.lang.Double.doubleToLongBits(r3);
    L_0x0125:
        r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
    L_0x0129:
        r2 = r2 + r3;
    L_0x012a:
        r1 = r1 + 4;
        goto L_0x0005;
    L_0x012e:
        r2 = r2 * 53;
        r0 = r8.zzmx;
        r0 = r0.zzq(r9);
        r0 = r0.hashCode();
        r2 = r2 + r0;
        r0 = r8.zzmo;
        if (r0 == 0) goto L_0x014c;
    L_0x013f:
        r2 = r2 * 53;
        r0 = r8.zzmy;
        r9 = r0.zza(r9);
        r9 = r9.hashCode();
        r2 = r2 + r9;
    L_0x014c:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.hashCode(java.lang.Object):int");
    }

    public final T newInstance() {
        return this.zzmv.newInstance(this.zzmn);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04b9  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x04f7  */
    /* JADX WARNING: Removed duplicated region for block: B:351:0x0977  */
    /* JADX WARNING: Missing block: B:105:0x0385, code skipped:
            r15.zzb(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r8 & 1048575)), zzad(r7));
     */
    /* JADX WARNING: Missing block: B:109:0x03a0, code skipped:
            r15.zzb(r9, r10);
     */
    /* JADX WARNING: Missing block: B:113:0x03b1, code skipped:
            r15.zze(r9, r8);
     */
    /* JADX WARNING: Missing block: B:117:0x03c2, code skipped:
            r15.zzj(r9, r10);
     */
    /* JADX WARNING: Missing block: B:121:0x03d3, code skipped:
            r15.zzm(r9, r8);
     */
    /* JADX WARNING: Missing block: B:125:0x03e4, code skipped:
            r15.zzn(r9, r8);
     */
    /* JADX WARNING: Missing block: B:129:0x03f5, code skipped:
            r15.zzd(r9, r8);
     */
    /* JADX WARNING: Missing block: B:132:0x0400, code skipped:
            r15.zza(r9, (com.google.android.gms.internal.clearcut.zzbb) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r8 & 1048575)));
     */
    /* JADX WARNING: Missing block: B:135:0x0413, code skipped:
            r15.zza(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r8 & 1048575)), zzad(r7));
     */
    /* JADX WARNING: Missing block: B:138:0x0428, code skipped:
            zza(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r8 & 1048575)), r15);
     */
    /* JADX WARNING: Missing block: B:142:0x043f, code skipped:
            r15.zzb(r9, r8);
     */
    /* JADX WARNING: Missing block: B:146:0x0450, code skipped:
            r15.zzf(r9, r8);
     */
    /* JADX WARNING: Missing block: B:150:0x0460, code skipped:
            r15.zzc(r9, r10);
     */
    /* JADX WARNING: Missing block: B:154:0x0470, code skipped:
            r15.zzc(r9, r8);
     */
    /* JADX WARNING: Missing block: B:158:0x0480, code skipped:
            r15.zza(r9, r10);
     */
    /* JADX WARNING: Missing block: B:162:0x0490, code skipped:
            r15.zzi(r9, r10);
     */
    /* JADX WARNING: Missing block: B:166:0x04a0, code skipped:
            r15.zza(r9, r8);
     */
    /* JADX WARNING: Missing block: B:170:0x04b0, code skipped:
            r15.zza(r9, r10);
     */
    /* JADX WARNING: Missing block: B:283:0x0843, code skipped:
            r15.zzb(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r9 & 1048575)), zzad(r1));
     */
    /* JADX WARNING: Missing block: B:287:0x085e, code skipped:
            r15.zzb(r10, r11);
     */
    /* JADX WARNING: Missing block: B:291:0x086f, code skipped:
            r15.zze(r10, r9);
     */
    /* JADX WARNING: Missing block: B:295:0x0880, code skipped:
            r15.zzj(r10, r11);
     */
    /* JADX WARNING: Missing block: B:299:0x0891, code skipped:
            r15.zzm(r10, r9);
     */
    /* JADX WARNING: Missing block: B:303:0x08a2, code skipped:
            r15.zzn(r10, r9);
     */
    /* JADX WARNING: Missing block: B:307:0x08b3, code skipped:
            r15.zzd(r10, r9);
     */
    /* JADX WARNING: Missing block: B:310:0x08be, code skipped:
            r15.zza(r10, (com.google.android.gms.internal.clearcut.zzbb) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r9 & 1048575)));
     */
    /* JADX WARNING: Missing block: B:313:0x08d1, code skipped:
            r15.zza(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r9 & 1048575)), zzad(r1));
     */
    /* JADX WARNING: Missing block: B:316:0x08e6, code skipped:
            zza(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r9 & 1048575)), r15);
     */
    /* JADX WARNING: Missing block: B:320:0x08fd, code skipped:
            r15.zzb(r10, r9);
     */
    /* JADX WARNING: Missing block: B:324:0x090e, code skipped:
            r15.zzf(r10, r9);
     */
    /* JADX WARNING: Missing block: B:328:0x091e, code skipped:
            r15.zzc(r10, r11);
     */
    /* JADX WARNING: Missing block: B:332:0x092e, code skipped:
            r15.zzc(r10, r9);
     */
    /* JADX WARNING: Missing block: B:336:0x093e, code skipped:
            r15.zza(r10, r11);
     */
    /* JADX WARNING: Missing block: B:340:0x094e, code skipped:
            r15.zzi(r10, r11);
     */
    /* JADX WARNING: Missing block: B:344:0x095e, code skipped:
            r15.zza(r10, r9);
     */
    /* JADX WARNING: Missing block: B:348:0x096e, code skipped:
            r15.zza(r10, r11);
     */
    public final void zza(T r14, com.google.android.gms.internal.clearcut.zzfr r15) throws java.io.IOException {
        /*
        r13 = this;
        r0 = r15.zzaj();
        r1 = com.google.android.gms.internal.clearcut.zzcg.zzg.zzkp;
        r2 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r3 = 0;
        r4 = 1;
        r5 = 0;
        r6 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        if (r0 != r1) goto L_0x04cf;
    L_0x0010:
        r0 = r13.zzmx;
        zza(r0, r14, r15);
        r0 = r13.zzmo;
        if (r0 == 0) goto L_0x0030;
    L_0x0019:
        r0 = r13.zzmy;
        r0 = r0.zza(r14);
        r1 = r0.isEmpty();
        if (r1 != 0) goto L_0x0030;
    L_0x0025:
        r0 = r0.descendingIterator();
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        goto L_0x0032;
    L_0x0030:
        r0 = r3;
        r1 = r0;
    L_0x0032:
        r7 = r13.zzmi;
        r7 = r7.length;
        r7 = r7 + -4;
    L_0x0037:
        if (r7 < 0) goto L_0x04b7;
    L_0x0039:
        r8 = r13.zzag(r7);
        r9 = r13.zzmi;
        r9 = r9[r7];
    L_0x0041:
        if (r1 == 0) goto L_0x005f;
    L_0x0043:
        r10 = r13.zzmy;
        r10 = r10.zza(r1);
        if (r10 <= r9) goto L_0x005f;
    L_0x004b:
        r10 = r13.zzmy;
        r10.zza(r15, r1);
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x005d;
    L_0x0056:
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        goto L_0x0041;
    L_0x005d:
        r1 = r3;
        goto L_0x0041;
    L_0x005f:
        r10 = r8 & r2;
        r10 = r10 >>> 20;
        switch(r10) {
            case 0: goto L_0x04a4;
            case 1: goto L_0x0494;
            case 2: goto L_0x0484;
            case 3: goto L_0x0474;
            case 4: goto L_0x0464;
            case 5: goto L_0x0454;
            case 6: goto L_0x0444;
            case 7: goto L_0x0433;
            case 8: goto L_0x0422;
            case 9: goto L_0x040d;
            case 10: goto L_0x03fa;
            case 11: goto L_0x03e9;
            case 12: goto L_0x03d8;
            case 13: goto L_0x03c7;
            case 14: goto L_0x03b6;
            case 15: goto L_0x03a5;
            case 16: goto L_0x0394;
            case 17: goto L_0x037f;
            case 18: goto L_0x036e;
            case 19: goto L_0x035d;
            case 20: goto L_0x034c;
            case 21: goto L_0x033b;
            case 22: goto L_0x032a;
            case 23: goto L_0x0319;
            case 24: goto L_0x0308;
            case 25: goto L_0x02f7;
            case 26: goto L_0x02e6;
            case 27: goto L_0x02d1;
            case 28: goto L_0x02c0;
            case 29: goto L_0x02af;
            case 30: goto L_0x029e;
            case 31: goto L_0x028d;
            case 32: goto L_0x027c;
            case 33: goto L_0x026b;
            case 34: goto L_0x025a;
            case 35: goto L_0x0249;
            case 36: goto L_0x0238;
            case 37: goto L_0x0227;
            case 38: goto L_0x0216;
            case 39: goto L_0x0205;
            case 40: goto L_0x01f4;
            case 41: goto L_0x01e3;
            case 42: goto L_0x01d2;
            case 43: goto L_0x01c1;
            case 44: goto L_0x01b0;
            case 45: goto L_0x019f;
            case 46: goto L_0x018e;
            case 47: goto L_0x017d;
            case 48: goto L_0x016c;
            case 49: goto L_0x0157;
            case 50: goto L_0x014c;
            case 51: goto L_0x013e;
            case 52: goto L_0x0130;
            case 53: goto L_0x0122;
            case 54: goto L_0x0114;
            case 55: goto L_0x0106;
            case 56: goto L_0x00f8;
            case 57: goto L_0x00ea;
            case 58: goto L_0x00dc;
            case 59: goto L_0x00d4;
            case 60: goto L_0x00cc;
            case 61: goto L_0x00c4;
            case 62: goto L_0x00b6;
            case 63: goto L_0x00a8;
            case 64: goto L_0x009a;
            case 65: goto L_0x008c;
            case 66: goto L_0x007e;
            case 67: goto L_0x0070;
            case 68: goto L_0x0068;
            default: goto L_0x0066;
        };
    L_0x0066:
        goto L_0x04b3;
    L_0x0068:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x006e:
        goto L_0x0385;
    L_0x0070:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0076:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzh(r14, r10);
        goto L_0x03a0;
    L_0x007e:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0084:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzg(r14, r10);
        goto L_0x03b1;
    L_0x008c:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0092:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzh(r14, r10);
        goto L_0x03c2;
    L_0x009a:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x00a0:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzg(r14, r10);
        goto L_0x03d3;
    L_0x00a8:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x00ae:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzg(r14, r10);
        goto L_0x03e4;
    L_0x00b6:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x00bc:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzg(r14, r10);
        goto L_0x03f5;
    L_0x00c4:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x00ca:
        goto L_0x0400;
    L_0x00cc:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x00d2:
        goto L_0x0413;
    L_0x00d4:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x00da:
        goto L_0x0428;
    L_0x00dc:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x00e2:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzi(r14, r10);
        goto L_0x043f;
    L_0x00ea:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x00f0:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzg(r14, r10);
        goto L_0x0450;
    L_0x00f8:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x00fe:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzh(r14, r10);
        goto L_0x0460;
    L_0x0106:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x010c:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzg(r14, r10);
        goto L_0x0470;
    L_0x0114:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x011a:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzh(r14, r10);
        goto L_0x0480;
    L_0x0122:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0128:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zzh(r14, r10);
        goto L_0x0490;
    L_0x0130:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0136:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = zzf(r14, r10);
        goto L_0x04a0;
    L_0x013e:
        r10 = r13.zza(r14, r9, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0144:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = zze(r14, r10);
        goto L_0x04b0;
    L_0x014c:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r13.zza(r15, r9, r8, r7);
        goto L_0x04b3;
    L_0x0157:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        r10 = r13.zzad(r7);
        com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r8, r15, r10);
        goto L_0x04b3;
    L_0x016c:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zze(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x017d:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzj(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x018e:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzg(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x019f:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzl(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x01b0:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzm(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x01c1:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzi(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x01d2:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzn(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x01e3:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzk(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x01f4:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzf(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x0205:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzh(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x0216:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzd(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x0227:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzc(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x0238:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x0249:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zza(r9, r8, r15, r4);
        goto L_0x04b3;
    L_0x025a:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zze(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x026b:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzj(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x027c:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzg(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x028d:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzl(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x029e:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzm(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x02af:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzi(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x02c0:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r8, r15);
        goto L_0x04b3;
    L_0x02d1:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        r10 = r13.zzad(r7);
        com.google.android.gms.internal.clearcut.zzeh.zza(r9, r8, r15, r10);
        goto L_0x04b3;
    L_0x02e6:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zza(r9, r8, r15);
        goto L_0x04b3;
    L_0x02f7:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzn(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x0308:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzk(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x0319:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzf(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x032a:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzh(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x033b:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzd(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x034c:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzc(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x035d:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x036e:
        r9 = r13.zzmi;
        r9 = r9[r7];
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (java.util.List) r8;
        com.google.android.gms.internal.clearcut.zzeh.zza(r9, r8, r15, r5);
        goto L_0x04b3;
    L_0x037f:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0385:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r10 = r13.zzad(r7);
        r15.zzb(r9, r8, r10);
        goto L_0x04b3;
    L_0x0394:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x039a:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10);
    L_0x03a0:
        r15.zzb(r9, r10);
        goto L_0x04b3;
    L_0x03a5:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x03ab:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10);
    L_0x03b1:
        r15.zze(r9, r8);
        goto L_0x04b3;
    L_0x03b6:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x03bc:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10);
    L_0x03c2:
        r15.zzj(r9, r10);
        goto L_0x04b3;
    L_0x03c7:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x03cd:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10);
    L_0x03d3:
        r15.zzm(r9, r8);
        goto L_0x04b3;
    L_0x03d8:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x03de:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10);
    L_0x03e4:
        r15.zzn(r9, r8);
        goto L_0x04b3;
    L_0x03e9:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x03ef:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10);
    L_0x03f5:
        r15.zzd(r9, r8);
        goto L_0x04b3;
    L_0x03fa:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0400:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r8 = (com.google.android.gms.internal.clearcut.zzbb) r8;
        r15.zza(r9, r8);
        goto L_0x04b3;
    L_0x040d:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0413:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        r10 = r13.zzad(r7);
        r15.zza(r9, r8, r10);
        goto L_0x04b3;
    L_0x0422:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0428:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10);
        zza(r9, r8, r15);
        goto L_0x04b3;
    L_0x0433:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x0439:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzl(r14, r10);
    L_0x043f:
        r15.zzb(r9, r8);
        goto L_0x04b3;
    L_0x0444:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x044a:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10);
    L_0x0450:
        r15.zzf(r9, r8);
        goto L_0x04b3;
    L_0x0454:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x045a:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10);
    L_0x0460:
        r15.zzc(r9, r10);
        goto L_0x04b3;
    L_0x0464:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x046a:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10);
    L_0x0470:
        r15.zzc(r9, r8);
        goto L_0x04b3;
    L_0x0474:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x047a:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10);
    L_0x0480:
        r15.zza(r9, r10);
        goto L_0x04b3;
    L_0x0484:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x048a:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10);
    L_0x0490:
        r15.zzi(r9, r10);
        goto L_0x04b3;
    L_0x0494:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x049a:
        r8 = r8 & r6;
        r10 = (long) r8;
        r8 = com.google.android.gms.internal.clearcut.zzfd.zzm(r14, r10);
    L_0x04a0:
        r15.zza(r9, r8);
        goto L_0x04b3;
    L_0x04a4:
        r10 = r13.zza(r14, r7);
        if (r10 == 0) goto L_0x04b3;
    L_0x04aa:
        r8 = r8 & r6;
        r10 = (long) r8;
        r10 = com.google.android.gms.internal.clearcut.zzfd.zzn(r14, r10);
    L_0x04b0:
        r15.zza(r9, r10);
    L_0x04b3:
        r7 = r7 + -4;
        goto L_0x0037;
    L_0x04b7:
        if (r1 == 0) goto L_0x04ce;
    L_0x04b9:
        r14 = r13.zzmy;
        r14.zza(r15, r1);
        r14 = r0.hasNext();
        if (r14 == 0) goto L_0x04cc;
    L_0x04c4:
        r14 = r0.next();
        r14 = (java.util.Map.Entry) r14;
        r1 = r14;
        goto L_0x04b7;
    L_0x04cc:
        r1 = r3;
        goto L_0x04b7;
    L_0x04ce:
        return;
    L_0x04cf:
        r0 = r13.zzmq;
        if (r0 == 0) goto L_0x0992;
    L_0x04d3:
        r0 = r13.zzmo;
        if (r0 == 0) goto L_0x04ee;
    L_0x04d7:
        r0 = r13.zzmy;
        r0 = r0.zza(r14);
        r1 = r0.isEmpty();
        if (r1 != 0) goto L_0x04ee;
    L_0x04e3:
        r0 = r0.iterator();
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        goto L_0x04f0;
    L_0x04ee:
        r0 = r3;
        r1 = r0;
    L_0x04f0:
        r7 = r13.zzmi;
        r7 = r7.length;
        r8 = r1;
        r1 = r5;
    L_0x04f5:
        if (r1 >= r7) goto L_0x0975;
    L_0x04f7:
        r9 = r13.zzag(r1);
        r10 = r13.zzmi;
        r10 = r10[r1];
    L_0x04ff:
        if (r8 == 0) goto L_0x051d;
    L_0x0501:
        r11 = r13.zzmy;
        r11 = r11.zza(r8);
        if (r11 > r10) goto L_0x051d;
    L_0x0509:
        r11 = r13.zzmy;
        r11.zza(r15, r8);
        r8 = r0.hasNext();
        if (r8 == 0) goto L_0x051b;
    L_0x0514:
        r8 = r0.next();
        r8 = (java.util.Map.Entry) r8;
        goto L_0x04ff;
    L_0x051b:
        r8 = r3;
        goto L_0x04ff;
    L_0x051d:
        r11 = r9 & r2;
        r11 = r11 >>> 20;
        switch(r11) {
            case 0: goto L_0x0962;
            case 1: goto L_0x0952;
            case 2: goto L_0x0942;
            case 3: goto L_0x0932;
            case 4: goto L_0x0922;
            case 5: goto L_0x0912;
            case 6: goto L_0x0902;
            case 7: goto L_0x08f1;
            case 8: goto L_0x08e0;
            case 9: goto L_0x08cb;
            case 10: goto L_0x08b8;
            case 11: goto L_0x08a7;
            case 12: goto L_0x0896;
            case 13: goto L_0x0885;
            case 14: goto L_0x0874;
            case 15: goto L_0x0863;
            case 16: goto L_0x0852;
            case 17: goto L_0x083d;
            case 18: goto L_0x082c;
            case 19: goto L_0x081b;
            case 20: goto L_0x080a;
            case 21: goto L_0x07f9;
            case 22: goto L_0x07e8;
            case 23: goto L_0x07d7;
            case 24: goto L_0x07c6;
            case 25: goto L_0x07b5;
            case 26: goto L_0x07a4;
            case 27: goto L_0x078f;
            case 28: goto L_0x077e;
            case 29: goto L_0x076d;
            case 30: goto L_0x075c;
            case 31: goto L_0x074b;
            case 32: goto L_0x073a;
            case 33: goto L_0x0729;
            case 34: goto L_0x0718;
            case 35: goto L_0x0707;
            case 36: goto L_0x06f6;
            case 37: goto L_0x06e5;
            case 38: goto L_0x06d4;
            case 39: goto L_0x06c3;
            case 40: goto L_0x06b2;
            case 41: goto L_0x06a1;
            case 42: goto L_0x0690;
            case 43: goto L_0x067f;
            case 44: goto L_0x066e;
            case 45: goto L_0x065d;
            case 46: goto L_0x064c;
            case 47: goto L_0x063b;
            case 48: goto L_0x062a;
            case 49: goto L_0x0615;
            case 50: goto L_0x060a;
            case 51: goto L_0x05fc;
            case 52: goto L_0x05ee;
            case 53: goto L_0x05e0;
            case 54: goto L_0x05d2;
            case 55: goto L_0x05c4;
            case 56: goto L_0x05b6;
            case 57: goto L_0x05a8;
            case 58: goto L_0x059a;
            case 59: goto L_0x0592;
            case 60: goto L_0x058a;
            case 61: goto L_0x0582;
            case 62: goto L_0x0574;
            case 63: goto L_0x0566;
            case 64: goto L_0x0558;
            case 65: goto L_0x054a;
            case 66: goto L_0x053c;
            case 67: goto L_0x052e;
            case 68: goto L_0x0526;
            default: goto L_0x0524;
        };
    L_0x0524:
        goto L_0x0971;
    L_0x0526:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x052c:
        goto L_0x0843;
    L_0x052e:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0534:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzh(r14, r11);
        goto L_0x085e;
    L_0x053c:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0542:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzg(r14, r11);
        goto L_0x086f;
    L_0x054a:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0550:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzh(r14, r11);
        goto L_0x0880;
    L_0x0558:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x055e:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzg(r14, r11);
        goto L_0x0891;
    L_0x0566:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x056c:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzg(r14, r11);
        goto L_0x08a2;
    L_0x0574:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x057a:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzg(r14, r11);
        goto L_0x08b3;
    L_0x0582:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0588:
        goto L_0x08be;
    L_0x058a:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0590:
        goto L_0x08d1;
    L_0x0592:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0598:
        goto L_0x08e6;
    L_0x059a:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x05a0:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzi(r14, r11);
        goto L_0x08fd;
    L_0x05a8:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x05ae:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzg(r14, r11);
        goto L_0x090e;
    L_0x05b6:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x05bc:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzh(r14, r11);
        goto L_0x091e;
    L_0x05c4:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x05ca:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzg(r14, r11);
        goto L_0x092e;
    L_0x05d2:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x05d8:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzh(r14, r11);
        goto L_0x093e;
    L_0x05e0:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x05e6:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zzh(r14, r11);
        goto L_0x094e;
    L_0x05ee:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x05f4:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = zzf(r14, r11);
        goto L_0x095e;
    L_0x05fc:
        r11 = r13.zza(r14, r10, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0602:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = zze(r14, r11);
        goto L_0x096e;
    L_0x060a:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r13.zza(r15, r10, r9, r1);
        goto L_0x0971;
    L_0x0615:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        r11 = r13.zzad(r1);
        com.google.android.gms.internal.clearcut.zzeh.zzb(r10, r9, r15, r11);
        goto L_0x0971;
    L_0x062a:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zze(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x063b:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzj(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x064c:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzg(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x065d:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzl(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x066e:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzm(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x067f:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzi(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x0690:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzn(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x06a1:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzk(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x06b2:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzf(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x06c3:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzh(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x06d4:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzd(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x06e5:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzc(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x06f6:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzb(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x0707:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zza(r10, r9, r15, r4);
        goto L_0x0971;
    L_0x0718:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zze(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x0729:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzj(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x073a:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzg(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x074b:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzl(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x075c:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzm(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x076d:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzi(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x077e:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzb(r10, r9, r15);
        goto L_0x0971;
    L_0x078f:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        r11 = r13.zzad(r1);
        com.google.android.gms.internal.clearcut.zzeh.zza(r10, r9, r15, r11);
        goto L_0x0971;
    L_0x07a4:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zza(r10, r9, r15);
        goto L_0x0971;
    L_0x07b5:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzn(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x07c6:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzk(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x07d7:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzf(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x07e8:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzh(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x07f9:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzd(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x080a:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzc(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x081b:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zzb(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x082c:
        r10 = r13.zzmi;
        r10 = r10[r1];
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (java.util.List) r9;
        com.google.android.gms.internal.clearcut.zzeh.zza(r10, r9, r15, r5);
        goto L_0x0971;
    L_0x083d:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0843:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r11 = r13.zzad(r1);
        r15.zzb(r10, r9, r11);
        goto L_0x0971;
    L_0x0852:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0858:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11);
    L_0x085e:
        r15.zzb(r10, r11);
        goto L_0x0971;
    L_0x0863:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0869:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11);
    L_0x086f:
        r15.zze(r10, r9);
        goto L_0x0971;
    L_0x0874:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x087a:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11);
    L_0x0880:
        r15.zzj(r10, r11);
        goto L_0x0971;
    L_0x0885:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x088b:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11);
    L_0x0891:
        r15.zzm(r10, r9);
        goto L_0x0971;
    L_0x0896:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x089c:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11);
    L_0x08a2:
        r15.zzn(r10, r9);
        goto L_0x0971;
    L_0x08a7:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x08ad:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11);
    L_0x08b3:
        r15.zzd(r10, r9);
        goto L_0x0971;
    L_0x08b8:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x08be:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r9 = (com.google.android.gms.internal.clearcut.zzbb) r9;
        r15.zza(r10, r9);
        goto L_0x0971;
    L_0x08cb:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x08d1:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        r11 = r13.zzad(r1);
        r15.zza(r10, r9, r11);
        goto L_0x0971;
    L_0x08e0:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x08e6:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11);
        zza(r10, r9, r15);
        goto L_0x0971;
    L_0x08f1:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x08f7:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzl(r14, r11);
    L_0x08fd:
        r15.zzb(r10, r9);
        goto L_0x0971;
    L_0x0902:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0908:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11);
    L_0x090e:
        r15.zzf(r10, r9);
        goto L_0x0971;
    L_0x0912:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0918:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11);
    L_0x091e:
        r15.zzc(r10, r11);
        goto L_0x0971;
    L_0x0922:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0928:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11);
    L_0x092e:
        r15.zzc(r10, r9);
        goto L_0x0971;
    L_0x0932:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0938:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11);
    L_0x093e:
        r15.zza(r10, r11);
        goto L_0x0971;
    L_0x0942:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0948:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11);
    L_0x094e:
        r15.zzi(r10, r11);
        goto L_0x0971;
    L_0x0952:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0958:
        r9 = r9 & r6;
        r11 = (long) r9;
        r9 = com.google.android.gms.internal.clearcut.zzfd.zzm(r14, r11);
    L_0x095e:
        r15.zza(r10, r9);
        goto L_0x0971;
    L_0x0962:
        r11 = r13.zza(r14, r1);
        if (r11 == 0) goto L_0x0971;
    L_0x0968:
        r9 = r9 & r6;
        r11 = (long) r9;
        r11 = com.google.android.gms.internal.clearcut.zzfd.zzn(r14, r11);
    L_0x096e:
        r15.zza(r10, r11);
    L_0x0971:
        r1 = r1 + 4;
        goto L_0x04f5;
    L_0x0975:
        if (r8 == 0) goto L_0x098c;
    L_0x0977:
        r1 = r13.zzmy;
        r1.zza(r15, r8);
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x098a;
    L_0x0982:
        r1 = r0.next();
        r1 = (java.util.Map.Entry) r1;
        r8 = r1;
        goto L_0x0975;
    L_0x098a:
        r8 = r3;
        goto L_0x0975;
    L_0x098c:
        r0 = r13.zzmx;
        zza(r0, r14, r15);
        return;
    L_0x0992:
        r13.zzb(r14, r15);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, com.google.android.gms.internal.clearcut.zzfr):void");
    }

    /* JADX WARNING: Missing block: B:18:0x0069, code skipped:
            if (r7 == 0) goto L_0x00cf;
     */
    /* JADX WARNING: Missing block: B:21:0x0073, code skipped:
            r1 = r11.zzff;
     */
    /* JADX WARNING: Missing block: B:22:0x0075, code skipped:
            r9.putObject(r14, r2, r1);
     */
    /* JADX WARNING: Missing block: B:41:0x00cd, code skipped:
            if (r7 == 0) goto L_0x00cf;
     */
    /* JADX WARNING: Missing block: B:42:0x00cf, code skipped:
            r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r10, r11);
            r1 = r11.zzfd;
     */
    /* JADX WARNING: Missing block: B:43:0x00d5, code skipped:
            r9.putInt(r14, r2, r1);
     */
    /* JADX WARNING: Missing block: B:46:0x00e2, code skipped:
            r9.putLong(r14, r2, r4);
            r0 = r6;
     */
    /* JADX WARNING: Missing block: B:49:0x00f3, code skipped:
            r0 = r10 + 4;
     */
    /* JADX WARNING: Missing block: B:52:0x0100, code skipped:
            r0 = r10 + 8;
     */
    /* JADX WARNING: Missing block: B:74:0x0190, code skipped:
            if (r0 == r14) goto L_0x015d;
     */
    /* JADX WARNING: Missing block: B:77:0x01ac, code skipped:
            if (r0 == r14) goto L_0x015d;
     */
    public final void zza(T r25, byte[] r26, int r27, int r28, com.google.android.gms.internal.clearcut.zzay r29) throws java.io.IOException {
        /*
        r24 = this;
        r15 = r24;
        r14 = r25;
        r12 = r26;
        r13 = r28;
        r11 = r29;
        r0 = r15.zzmq;
        if (r0 == 0) goto L_0x01d8;
    L_0x000e:
        r9 = zzmh;
        r0 = r27;
    L_0x0012:
        if (r0 >= r13) goto L_0x01cf;
    L_0x0014:
        r1 = r0 + 1;
        r0 = r12[r0];
        if (r0 >= 0) goto L_0x0024;
    L_0x001a:
        r0 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r12, r1, r11);
        r1 = r11.zzfd;
        r10 = r0;
        r16 = r1;
        goto L_0x0027;
    L_0x0024:
        r16 = r0;
        r10 = r1;
    L_0x0027:
        r6 = r16 >>> 3;
        r7 = r16 & 7;
        r8 = r15.zzai(r6);
        if (r8 < 0) goto L_0x01af;
    L_0x0031:
        r0 = r15.zzmi;
        r1 = r8 + 1;
        r5 = r0[r1];
        r0 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r0 = r0 & r5;
        r4 = r0 >>> 20;
        r0 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r0 = r0 & r5;
        r2 = (long) r0;
        r0 = 17;
        r1 = 2;
        if (r4 > r0) goto L_0x0104;
    L_0x0046:
        r0 = 5;
        r6 = 1;
        switch(r4) {
            case 0: goto L_0x00f7;
            case 1: goto L_0x00ea;
            case 2: goto L_0x00da;
            case 3: goto L_0x00da;
            case 4: goto L_0x00cd;
            case 5: goto L_0x00c1;
            case 6: goto L_0x00b7;
            case 7: goto L_0x00a2;
            case 8: goto L_0x0091;
            case 9: goto L_0x0079;
            case 10: goto L_0x006d;
            case 11: goto L_0x00cd;
            case 12: goto L_0x0069;
            case 13: goto L_0x00b7;
            case 14: goto L_0x00c1;
            case 15: goto L_0x005b;
            case 16: goto L_0x004d;
            default: goto L_0x004b;
        };
    L_0x004b:
        goto L_0x01af;
    L_0x004d:
        if (r7 != 0) goto L_0x01af;
    L_0x004f:
        r6 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r10, r11);
        r0 = r11.zzfe;
        r4 = com.google.android.gms.internal.clearcut.zzbk.zza(r0);
        goto L_0x00e2;
    L_0x005b:
        if (r7 != 0) goto L_0x01af;
    L_0x005d:
        r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r10, r11);
        r1 = r11.zzfd;
        r1 = com.google.android.gms.internal.clearcut.zzbk.zzm(r1);
        goto L_0x00d5;
    L_0x0069:
        if (r7 != 0) goto L_0x01af;
    L_0x006b:
        goto L_0x00cf;
    L_0x006d:
        if (r7 != r1) goto L_0x01af;
    L_0x006f:
        r0 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r10, r11);
    L_0x0073:
        r1 = r11.zzff;
    L_0x0075:
        r9.putObject(r14, r2, r1);
        goto L_0x0012;
    L_0x0079:
        if (r7 != r1) goto L_0x01af;
    L_0x007b:
        r0 = r15.zzad(r8);
        r0 = zza(r0, r12, r10, r13, r11);
        r1 = r9.getObject(r14, r2);
        if (r1 != 0) goto L_0x008a;
    L_0x0089:
        goto L_0x0073;
    L_0x008a:
        r4 = r11.zzff;
        r1 = com.google.android.gms.internal.clearcut.zzci.zza(r1, r4);
        goto L_0x0075;
    L_0x0091:
        if (r7 != r1) goto L_0x01af;
    L_0x0093:
        r0 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r0 = r0 & r5;
        if (r0 != 0) goto L_0x009d;
    L_0x0098:
        r0 = com.google.android.gms.internal.clearcut.zzax.zzc(r12, r10, r11);
        goto L_0x0073;
    L_0x009d:
        r0 = com.google.android.gms.internal.clearcut.zzax.zzd(r12, r10, r11);
        goto L_0x0073;
    L_0x00a2:
        if (r7 != 0) goto L_0x01af;
    L_0x00a4:
        r0 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r10, r11);
        r4 = r11.zzfe;
        r7 = 0;
        r1 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
        if (r1 == 0) goto L_0x00b1;
    L_0x00b0:
        goto L_0x00b2;
    L_0x00b1:
        r6 = 0;
    L_0x00b2:
        com.google.android.gms.internal.clearcut.zzfd.zza(r14, r2, r6);
        goto L_0x0012;
    L_0x00b7:
        if (r7 != r0) goto L_0x01af;
    L_0x00b9:
        r0 = com.google.android.gms.internal.clearcut.zzax.zzc(r12, r10);
        r9.putInt(r14, r2, r0);
        goto L_0x00f3;
    L_0x00c1:
        if (r7 != r6) goto L_0x01af;
    L_0x00c3:
        r4 = com.google.android.gms.internal.clearcut.zzax.zzd(r12, r10);
        r0 = r9;
        r1 = r14;
        r0.putLong(r1, r2, r4);
        goto L_0x0100;
    L_0x00cd:
        if (r7 != 0) goto L_0x01af;
    L_0x00cf:
        r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r10, r11);
        r1 = r11.zzfd;
    L_0x00d5:
        r9.putInt(r14, r2, r1);
        goto L_0x0012;
    L_0x00da:
        if (r7 != 0) goto L_0x01af;
    L_0x00dc:
        r6 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r10, r11);
        r4 = r11.zzfe;
    L_0x00e2:
        r0 = r9;
        r1 = r14;
        r0.putLong(r1, r2, r4);
        r0 = r6;
        goto L_0x0012;
    L_0x00ea:
        if (r7 != r0) goto L_0x01af;
    L_0x00ec:
        r0 = com.google.android.gms.internal.clearcut.zzax.zzf(r12, r10);
        com.google.android.gms.internal.clearcut.zzfd.zza(r14, r2, r0);
    L_0x00f3:
        r0 = r10 + 4;
        goto L_0x0012;
    L_0x00f7:
        if (r7 != r6) goto L_0x01af;
    L_0x00f9:
        r0 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r10);
        com.google.android.gms.internal.clearcut.zzfd.zza(r14, r2, r0);
    L_0x0100:
        r0 = r10 + 8;
        goto L_0x0012;
    L_0x0104:
        r0 = 27;
        if (r4 != r0) goto L_0x0139;
    L_0x0108:
        if (r7 != r1) goto L_0x01af;
    L_0x010a:
        r0 = r9.getObject(r14, r2);
        r0 = (com.google.android.gms.internal.clearcut.zzcn) r0;
        r1 = r0.zzu();
        if (r1 != 0) goto L_0x0128;
    L_0x0116:
        r1 = r0.size();
        if (r1 != 0) goto L_0x011f;
    L_0x011c:
        r1 = 10;
        goto L_0x0121;
    L_0x011f:
        r1 = r1 << 1;
    L_0x0121:
        r0 = r0.zzi(r1);
        r9.putObject(r14, r2, r0);
    L_0x0128:
        r5 = r0;
        r0 = r15.zzad(r8);
        r1 = r16;
        r2 = r12;
        r3 = r10;
        r4 = r13;
        r6 = r11;
        r0 = zza(r0, r1, r2, r3, r4, r5, r6);
        goto L_0x0012;
    L_0x0139:
        r0 = 49;
        if (r4 > r0) goto L_0x016e;
    L_0x013d:
        r0 = (long) r5;
        r17 = r0;
        r0 = r15;
        r1 = r14;
        r19 = r2;
        r2 = r12;
        r3 = r10;
        r5 = r4;
        r4 = r13;
        r21 = r5;
        r5 = r16;
        r22 = r9;
        r15 = r10;
        r9 = r17;
        r11 = r21;
        r12 = r19;
        r14 = r29;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14);
        if (r0 != r15) goto L_0x0160;
    L_0x015d:
        r2 = r0;
        goto L_0x01b3;
    L_0x0160:
        r14 = r25;
        r12 = r26;
        r13 = r28;
        r11 = r29;
        r9 = r22;
        r15 = r24;
        goto L_0x0012;
    L_0x016e:
        r19 = r2;
        r21 = r4;
        r22 = r9;
        r15 = r10;
        r0 = 50;
        r9 = r21;
        if (r9 != r0) goto L_0x0195;
    L_0x017b:
        if (r7 != r1) goto L_0x0193;
    L_0x017d:
        r14 = r15;
        r0 = r24;
        r1 = r25;
        r2 = r26;
        r3 = r14;
        r4 = r28;
        r5 = r8;
        r7 = r19;
        r9 = r29;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r9);
        if (r0 != r14) goto L_0x01c1;
    L_0x0192:
        goto L_0x015d;
    L_0x0193:
        r14 = r15;
        goto L_0x01b2;
    L_0x0195:
        r14 = r15;
        r0 = r24;
        r1 = r25;
        r2 = r26;
        r3 = r14;
        r4 = r28;
        r10 = r5;
        r5 = r16;
        r12 = r8;
        r8 = r10;
        r10 = r19;
        r13 = r29;
        r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13);
        if (r0 != r14) goto L_0x01c1;
    L_0x01ae:
        goto L_0x015d;
    L_0x01af:
        r22 = r9;
        r14 = r10;
    L_0x01b2:
        r2 = r14;
    L_0x01b3:
        r0 = r16;
        r1 = r26;
        r3 = r28;
        r4 = r25;
        r5 = r29;
        r0 = zza(r0, r1, r2, r3, r4, r5);
    L_0x01c1:
        r15 = r24;
        r14 = r25;
        r12 = r26;
        r13 = r28;
        r11 = r29;
        r9 = r22;
        goto L_0x0012;
    L_0x01cf:
        r4 = r13;
        if (r0 == r4) goto L_0x01d7;
    L_0x01d2:
        r0 = com.google.android.gms.internal.clearcut.zzco.zzbo();
        throw r0;
    L_0x01d7:
        return;
    L_0x01d8:
        r4 = r13;
        r5 = 0;
        r0 = r24;
        r1 = r25;
        r2 = r26;
        r3 = r27;
        r6 = r29;
        r0.zza(r1, r2, r3, r4, r5, r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.clearcut.zzay):void");
    }

    public final void zzc(T t) {
        int length;
        int i = 0;
        if (this.zzmt != null) {
            for (int zzag : this.zzmt) {
                long zzag2 = (long) (zzag(zzag) & 1048575);
                Object zzo = zzfd.zzo(t, zzag2);
                if (zzo != null) {
                    zzfd.zza((Object) t, zzag2, this.zzmz.zzj(zzo));
                }
            }
        }
        if (this.zzmu != null) {
            int[] iArr = this.zzmu;
            length = iArr.length;
            while (i < length) {
                this.zzmw.zza(t, (long) iArr[i]);
                i++;
            }
        }
        this.zzmx.zzc(t);
        if (this.zzmo) {
            this.zzmy.zzc(t);
        }
    }

    /* JADX WARNING: Missing block: B:13:0x0037, code skipped:
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.clearcut.zzfd.zzo(r8, r2));
            zzb((java.lang.Object) r7, r4, r0);
     */
    /* JADX WARNING: Missing block: B:33:0x008f, code skipped:
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.clearcut.zzfd.zzo(r8, r2));
     */
    /* JADX WARNING: Missing block: B:43:0x00b9, code skipped:
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.clearcut.zzfd.zzj(r8, r2));
     */
    /* JADX WARNING: Missing block: B:48:0x00ce, code skipped:
            com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.clearcut.zzfd.zzk(r8, r2));
     */
    /* JADX WARNING: Missing block: B:55:0x00f1, code skipped:
            zzb((java.lang.Object) r7, r0);
     */
    /* JADX WARNING: Missing block: B:56:0x00f4, code skipped:
            r0 = r0 + 4;
     */
    public final void zzc(T r7, T r8) {
        /*
        r6 = this;
        if (r8 != 0) goto L_0x0008;
    L_0x0002:
        r7 = new java.lang.NullPointerException;
        r7.<init>();
        throw r7;
    L_0x0008:
        r0 = 0;
    L_0x0009:
        r1 = r6.zzmi;
        r1 = r1.length;
        if (r0 >= r1) goto L_0x00f8;
    L_0x000e:
        r1 = r6.zzag(r0);
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r1;
        r2 = (long) r2;
        r4 = r6.zzmi;
        r4 = r4[r0];
        r5 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r1 = r1 & r5;
        r1 = r1 >>> 20;
        switch(r1) {
            case 0: goto L_0x00e4;
            case 1: goto L_0x00d6;
            case 2: goto L_0x00c8;
            case 3: goto L_0x00c1;
            case 4: goto L_0x00b3;
            case 5: goto L_0x00ac;
            case 6: goto L_0x00a5;
            case 7: goto L_0x0097;
            case 8: goto L_0x0089;
            case 9: goto L_0x0084;
            case 10: goto L_0x007d;
            case 11: goto L_0x0076;
            case 12: goto L_0x006f;
            case 13: goto L_0x0068;
            case 14: goto L_0x0060;
            case 15: goto L_0x0059;
            case 16: goto L_0x0051;
            case 17: goto L_0x0084;
            case 18: goto L_0x004a;
            case 19: goto L_0x004a;
            case 20: goto L_0x004a;
            case 21: goto L_0x004a;
            case 22: goto L_0x004a;
            case 23: goto L_0x004a;
            case 24: goto L_0x004a;
            case 25: goto L_0x004a;
            case 26: goto L_0x004a;
            case 27: goto L_0x004a;
            case 28: goto L_0x004a;
            case 29: goto L_0x004a;
            case 30: goto L_0x004a;
            case 31: goto L_0x004a;
            case 32: goto L_0x004a;
            case 33: goto L_0x004a;
            case 34: goto L_0x004a;
            case 35: goto L_0x004a;
            case 36: goto L_0x004a;
            case 37: goto L_0x004a;
            case 38: goto L_0x004a;
            case 39: goto L_0x004a;
            case 40: goto L_0x004a;
            case 41: goto L_0x004a;
            case 42: goto L_0x004a;
            case 43: goto L_0x004a;
            case 44: goto L_0x004a;
            case 45: goto L_0x004a;
            case 46: goto L_0x004a;
            case 47: goto L_0x004a;
            case 48: goto L_0x004a;
            case 49: goto L_0x004a;
            case 50: goto L_0x0043;
            case 51: goto L_0x0031;
            case 52: goto L_0x0031;
            case 53: goto L_0x0031;
            case 54: goto L_0x0031;
            case 55: goto L_0x0031;
            case 56: goto L_0x0031;
            case 57: goto L_0x0031;
            case 58: goto L_0x0031;
            case 59: goto L_0x0031;
            case 60: goto L_0x002c;
            case 61: goto L_0x0025;
            case 62: goto L_0x0025;
            case 63: goto L_0x0025;
            case 64: goto L_0x0025;
            case 65: goto L_0x0025;
            case 66: goto L_0x0025;
            case 67: goto L_0x0025;
            case 68: goto L_0x002c;
            default: goto L_0x0023;
        };
    L_0x0023:
        goto L_0x00f4;
    L_0x0025:
        r1 = r6.zza(r8, r4, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x002b:
        goto L_0x0037;
    L_0x002c:
        r6.zzb(r7, r8, r0);
        goto L_0x00f4;
    L_0x0031:
        r1 = r6.zza(r8, r4, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x0037:
        r1 = com.google.android.gms.internal.clearcut.zzfd.zzo(r8, r2);
        com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1);
        r6.zzb(r7, r4, r0);
        goto L_0x00f4;
    L_0x0043:
        r1 = r6.zzmz;
        com.google.android.gms.internal.clearcut.zzeh.zza(r1, r7, r8, r2);
        goto L_0x00f4;
    L_0x004a:
        r1 = r6.zzmw;
        r1.zza(r7, r8, r2);
        goto L_0x00f4;
    L_0x0051:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x0057:
        goto L_0x00ce;
    L_0x0059:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x005f:
        goto L_0x0075;
    L_0x0060:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x0066:
        goto L_0x00ce;
    L_0x0068:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x006e:
        goto L_0x0075;
    L_0x006f:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x0075:
        goto L_0x00b9;
    L_0x0076:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x007c:
        goto L_0x00b9;
    L_0x007d:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x0083:
        goto L_0x008f;
    L_0x0084:
        r6.zza(r7, r8, r0);
        goto L_0x00f4;
    L_0x0089:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x008f:
        r1 = com.google.android.gms.internal.clearcut.zzfd.zzo(r8, r2);
        com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1);
        goto L_0x00f1;
    L_0x0097:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x009d:
        r1 = com.google.android.gms.internal.clearcut.zzfd.zzl(r8, r2);
        com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1);
        goto L_0x00f1;
    L_0x00a5:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x00ab:
        goto L_0x00b9;
    L_0x00ac:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x00b2:
        goto L_0x00ce;
    L_0x00b3:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x00b9:
        r1 = com.google.android.gms.internal.clearcut.zzfd.zzj(r8, r2);
        com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1);
        goto L_0x00f1;
    L_0x00c1:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x00c7:
        goto L_0x00ce;
    L_0x00c8:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x00ce:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r8, r2);
        com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r4);
        goto L_0x00f1;
    L_0x00d6:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x00dc:
        r1 = com.google.android.gms.internal.clearcut.zzfd.zzm(r8, r2);
        com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1);
        goto L_0x00f1;
    L_0x00e4:
        r1 = r6.zza(r8, r0);
        if (r1 == 0) goto L_0x00f4;
    L_0x00ea:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzn(r8, r2);
        com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r4);
    L_0x00f1:
        r6.zzb(r7, r0);
    L_0x00f4:
        r0 = r0 + 4;
        goto L_0x0009;
    L_0x00f8:
        r0 = r6.zzmq;
        if (r0 != 0) goto L_0x010a;
    L_0x00fc:
        r0 = r6.zzmx;
        com.google.android.gms.internal.clearcut.zzeh.zza(r0, r7, r8);
        r0 = r6.zzmo;
        if (r0 == 0) goto L_0x010a;
    L_0x0105:
        r0 = r6.zzmy;
        com.google.android.gms.internal.clearcut.zzeh.zza(r0, r7, r8);
    L_0x010a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzc(java.lang.Object, java.lang.Object):void");
    }

    /* JADX WARNING: Missing block: B:37:0x00ab, code skipped:
            if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L_0x030a;
     */
    /* JADX WARNING: Missing block: B:62:0x0127, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:66:0x0139, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:70:0x014b, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:74:0x015d, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:78:0x016f, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:82:0x0181, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:86:0x0193, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:90:0x01a5, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:94:0x01b6, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:98:0x01c7, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:102:0x01d8, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:106:0x01e9, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:110:0x01fa, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:114:0x020b, code skipped:
            if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Missing block: B:115:0x020d, code skipped:
            r2.putInt(r1, (long) r14, r4);
     */
    /* JADX WARNING: Missing block: B:116:0x0211, code skipped:
            r3 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r3) + com.google.android.gms.internal.clearcut.zzbn.zzt(r4)) + r4;
     */
    /* JADX WARNING: Missing block: B:130:0x0296, code skipped:
            r13 = r13 + r3;
     */
    /* JADX WARNING: Missing block: B:133:0x029f, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, (com.google.android.gms.internal.clearcut.zzdo) com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4), zzad(r12));
     */
    /* JADX WARNING: Missing block: B:137:0x02b8, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzf(r3, r4);
     */
    /* JADX WARNING: Missing block: B:141:0x02c7, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzi(r3, r4);
     */
    /* JADX WARNING: Missing block: B:144:0x02d2, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, 0);
     */
    /* JADX WARNING: Missing block: B:147:0x02dd, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzk(r3, 0);
     */
    /* JADX WARNING: Missing block: B:151:0x02ec, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzl(r3, r4);
     */
    /* JADX WARNING: Missing block: B:155:0x02fb, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, r4);
     */
    /* JADX WARNING: Missing block: B:158:0x0306, code skipped:
            r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4);
     */
    /* JADX WARNING: Missing block: B:159:0x030a, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, (com.google.android.gms.internal.clearcut.zzbb) r4);
     */
    /* JADX WARNING: Missing block: B:162:0x0317, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4), zzad(r12));
     */
    /* JADX WARNING: Missing block: B:166:0x0331, code skipped:
            if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L_0x030a;
     */
    /* JADX WARNING: Missing block: B:167:0x0334, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, (java.lang.String) r4);
     */
    /* JADX WARNING: Missing block: B:170:0x0342, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, true);
     */
    /* JADX WARNING: Missing block: B:173:0x034e, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzj(r3, 0);
     */
    /* JADX WARNING: Missing block: B:176:0x035a, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, 0);
     */
    /* JADX WARNING: Missing block: B:180:0x036a, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, r4);
     */
    /* JADX WARNING: Missing block: B:184:0x037a, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zze(r3, r4);
     */
    /* JADX WARNING: Missing block: B:188:0x038a, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzd(r3, r4);
     */
    /* JADX WARNING: Missing block: B:191:0x0396, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, 0.0f);
     */
    /* JADX WARNING: Missing block: B:194:0x03a2, code skipped:
            r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, 0.0d);
     */
    /* JADX WARNING: Missing block: B:195:0x03aa, code skipped:
            r12 = r12 + 4;
            r3 = 267386880;
     */
    /* JADX WARNING: Missing block: B:218:0x0418, code skipped:
            if (zza(r1, r14, r3) != false) goto L_0x06ca;
     */
    /* JADX WARNING: Missing block: B:226:0x0438, code skipped:
            if (zza(r1, r14, r3) != false) goto L_0x06f7;
     */
    /* JADX WARNING: Missing block: B:228:0x0440, code skipped:
            if (zza(r1, r14, r3) != false) goto L_0x0702;
     */
    /* JADX WARNING: Missing block: B:236:0x0460, code skipped:
            if (zza(r1, r14, r3) != false) goto L_0x0727;
     */
    /* JADX WARNING: Missing block: B:238:0x0468, code skipped:
            if (zza(r1, r14, r3) != false) goto L_0x0736;
     */
    /* JADX WARNING: Missing block: B:242:0x0478, code skipped:
            if ((r6 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L_0x072b;
     */
    /* JADX WARNING: Missing block: B:244:0x0480, code skipped:
            if (zza(r1, r14, r3) != false) goto L_0x075d;
     */
    /* JADX WARNING: Missing block: B:271:0x0518, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:275:0x052a, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:279:0x053c, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:283:0x054e, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:287:0x0560, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:291:0x0572, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:295:0x0584, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:299:0x0596, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:303:0x05a7, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:307:0x05b8, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:311:0x05c9, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:315:0x05da, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:319:0x05eb, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:323:0x05fc, code skipped:
            if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Missing block: B:324:0x05fe, code skipped:
            r2.putInt(r1, (long) r6, r9);
     */
    /* JADX WARNING: Missing block: B:325:0x0602, code skipped:
            r6 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r14) + com.google.android.gms.internal.clearcut.zzbn.zzt(r9)) + r9;
     */
    /* JADX WARNING: Missing block: B:339:0x06af, code skipped:
            r4 = r4 + r6;
            r6 = r11;
     */
    /* JADX WARNING: Missing block: B:341:0x06bd, code skipped:
            r4 = r4 + r6;
     */
    /* JADX WARNING: Missing block: B:343:0x06bf, code skipped:
            r9 = 0.0f;
            r18 = 0;
     */
    /* JADX WARNING: Missing block: B:345:0x06c8, code skipped:
            if ((r12 & r16) != 0) goto L_0x06ca;
     */
    /* JADX WARNING: Missing block: B:346:0x06ca, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, (com.google.android.gms.internal.clearcut.zzdo) r2.getObject(r1, r9), zzad(r3));
     */
    /* JADX WARNING: Missing block: B:350:0x06e1, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzbn.zzf(r14, r9);
     */
    /* JADX WARNING: Missing block: B:354:0x06ee, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzbn.zzi(r14, r6);
     */
    /* JADX WARNING: Missing block: B:356:0x06f5, code skipped:
            if ((r12 & r16) != 0) goto L_0x06f7;
     */
    /* JADX WARNING: Missing block: B:357:0x06f7, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzbn.zzh(r14, 0);
     */
    /* JADX WARNING: Missing block: B:359:0x0700, code skipped:
            if ((r12 & r16) != 0) goto L_0x0702;
     */
    /* JADX WARNING: Missing block: B:360:0x0702, code skipped:
            r9 = com.google.android.gms.internal.clearcut.zzbn.zzk(r14, 0);
     */
    /* JADX WARNING: Missing block: B:361:0x0707, code skipped:
            r4 = r4 + r9;
     */
    /* JADX WARNING: Missing block: B:365:0x0711, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzbn.zzl(r14, r6);
     */
    /* JADX WARNING: Missing block: B:369:0x071e, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzbn.zzh(r14, r6);
     */
    /* JADX WARNING: Missing block: B:371:0x0725, code skipped:
            if ((r12 & r16) != 0) goto L_0x0727;
     */
    /* JADX WARNING: Missing block: B:372:0x0727, code skipped:
            r6 = r2.getObject(r1, r9);
     */
    /* JADX WARNING: Missing block: B:373:0x072b, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, (com.google.android.gms.internal.clearcut.zzbb) r6);
     */
    /* JADX WARNING: Missing block: B:375:0x0734, code skipped:
            if ((r12 & r16) != 0) goto L_0x0736;
     */
    /* JADX WARNING: Missing block: B:376:0x0736, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzeh.zzc(r14, r2.getObject(r1, r9), zzad(r3));
     */
    /* JADX WARNING: Missing block: B:380:0x074e, code skipped:
            if ((r6 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L_0x072b;
     */
    /* JADX WARNING: Missing block: B:381:0x0751, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, (java.lang.String) r6);
     */
    /* JADX WARNING: Missing block: B:383:0x075b, code skipped:
            if ((r12 & r16) != 0) goto L_0x075d;
     */
    /* JADX WARNING: Missing block: B:384:0x075d, code skipped:
            r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, true);
     */
    /* JADX WARNING: Missing block: B:400:0x07ad, code skipped:
            r4 = r4 + r9;
     */
    /* JADX WARNING: Missing block: B:409:0x07cf, code skipped:
            r3 = r3 + 4;
            r11 = r6;
            r6 = r9;
            r9 = r18;
     */
    public final int zzm(T r22) {
        /*
        r21 = this;
        r0 = r21;
        r1 = r22;
        r2 = r0.zzmq;
        r3 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r6 = 0;
        r7 = 1;
        r8 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r9 = 0;
        r11 = 0;
        if (r2 == 0) goto L_0x03b8;
    L_0x0012:
        r2 = zzmh;
        r12 = r11;
        r13 = r12;
    L_0x0016:
        r14 = r0.zzmi;
        r14 = r14.length;
        if (r12 >= r14) goto L_0x03b0;
    L_0x001b:
        r14 = r0.zzag(r12);
        r15 = r14 & r3;
        r15 = r15 >>> 20;
        r3 = r0.zzmi;
        r3 = r3[r12];
        r14 = r14 & r8;
        r4 = (long) r14;
        r14 = com.google.android.gms.internal.clearcut.zzcb.DOUBLE_LIST_PACKED;
        r14 = r14.id();
        if (r15 < r14) goto L_0x0041;
    L_0x0031:
        r14 = com.google.android.gms.internal.clearcut.zzcb.SINT64_LIST_PACKED;
        r14 = r14.id();
        if (r15 > r14) goto L_0x0041;
    L_0x0039:
        r14 = r0.zzmi;
        r17 = r12 + 2;
        r14 = r14[r17];
        r14 = r14 & r8;
        goto L_0x0042;
    L_0x0041:
        r14 = r11;
    L_0x0042:
        switch(r15) {
            case 0: goto L_0x039c;
            case 1: goto L_0x0390;
            case 2: goto L_0x0380;
            case 3: goto L_0x0370;
            case 4: goto L_0x0360;
            case 5: goto L_0x0354;
            case 6: goto L_0x0348;
            case 7: goto L_0x033c;
            case 8: goto L_0x0325;
            case 9: goto L_0x0311;
            case 10: goto L_0x0300;
            case 11: goto L_0x02f1;
            case 12: goto L_0x02e2;
            case 13: goto L_0x02d7;
            case 14: goto L_0x02cc;
            case 15: goto L_0x02bd;
            case 16: goto L_0x02ae;
            case 17: goto L_0x0299;
            case 18: goto L_0x028e;
            case 19: goto L_0x0285;
            case 20: goto L_0x027c;
            case 21: goto L_0x0273;
            case 22: goto L_0x026a;
            case 23: goto L_0x028e;
            case 24: goto L_0x0285;
            case 25: goto L_0x0261;
            case 26: goto L_0x0258;
            case 27: goto L_0x024b;
            case 28: goto L_0x0242;
            case 29: goto L_0x0239;
            case 30: goto L_0x0230;
            case 31: goto L_0x0285;
            case 32: goto L_0x028e;
            case 33: goto L_0x0227;
            case 34: goto L_0x021d;
            case 35: goto L_0x01fd;
            case 36: goto L_0x01ec;
            case 37: goto L_0x01db;
            case 38: goto L_0x01ca;
            case 39: goto L_0x01b9;
            case 40: goto L_0x01a8;
            case 41: goto L_0x0197;
            case 42: goto L_0x0185;
            case 43: goto L_0x0173;
            case 44: goto L_0x0161;
            case 45: goto L_0x014f;
            case 46: goto L_0x013d;
            case 47: goto L_0x012b;
            case 48: goto L_0x0119;
            case 49: goto L_0x010b;
            case 50: goto L_0x00fb;
            case 51: goto L_0x00f3;
            case 52: goto L_0x00eb;
            case 53: goto L_0x00df;
            case 54: goto L_0x00d3;
            case 55: goto L_0x00c7;
            case 56: goto L_0x00bf;
            case 57: goto L_0x00b7;
            case 58: goto L_0x00af;
            case 59: goto L_0x009f;
            case 60: goto L_0x0097;
            case 61: goto L_0x008f;
            case 62: goto L_0x0083;
            case 63: goto L_0x0077;
            case 64: goto L_0x006f;
            case 65: goto L_0x0067;
            case 66: goto L_0x005b;
            case 67: goto L_0x004f;
            case 68: goto L_0x0047;
            default: goto L_0x0045;
        };
    L_0x0045:
        goto L_0x03aa;
    L_0x0047:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x004d:
        goto L_0x029f;
    L_0x004f:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x0055:
        r4 = zzh(r1, r4);
        goto L_0x02b8;
    L_0x005b:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x0061:
        r4 = zzg(r1, r4);
        goto L_0x02c7;
    L_0x0067:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x006d:
        goto L_0x02d2;
    L_0x006f:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x0075:
        goto L_0x02dd;
    L_0x0077:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x007d:
        r4 = zzg(r1, r4);
        goto L_0x02ec;
    L_0x0083:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x0089:
        r4 = zzg(r1, r4);
        goto L_0x02fb;
    L_0x008f:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x0095:
        goto L_0x0306;
    L_0x0097:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x009d:
        goto L_0x0317;
    L_0x009f:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x00a5:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4);
        r5 = r4 instanceof com.google.android.gms.internal.clearcut.zzbb;
        if (r5 == 0) goto L_0x0334;
    L_0x00ad:
        goto L_0x0333;
    L_0x00af:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x00b5:
        goto L_0x0342;
    L_0x00b7:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x00bd:
        goto L_0x034e;
    L_0x00bf:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x00c5:
        goto L_0x035a;
    L_0x00c7:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x00cd:
        r4 = zzg(r1, r4);
        goto L_0x036a;
    L_0x00d3:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x00d9:
        r4 = zzh(r1, r4);
        goto L_0x037a;
    L_0x00df:
        r14 = r0.zza(r1, r3, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x00e5:
        r4 = zzh(r1, r4);
        goto L_0x038a;
    L_0x00eb:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x00f1:
        goto L_0x0396;
    L_0x00f3:
        r4 = r0.zza(r1, r3, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x00f9:
        goto L_0x03a2;
    L_0x00fb:
        r14 = r0.zzmz;
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4);
        r5 = r0.zzae(r12);
        r3 = r14.zzb(r3, r4, r5);
        goto L_0x0296;
    L_0x010b:
        r4 = zzd(r1, r4);
        r5 = r0.zzad(r12);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzd(r3, r4, r5);
        goto L_0x0296;
    L_0x0119:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzc(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x0125:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x0129:
        goto L_0x020d;
    L_0x012b:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzg(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x0137:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x013b:
        goto L_0x020d;
    L_0x013d:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzi(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x0149:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x014d:
        goto L_0x020d;
    L_0x014f:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzh(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x015b:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x015f:
        goto L_0x020d;
    L_0x0161:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x016d:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x0171:
        goto L_0x020d;
    L_0x0173:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzf(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x017f:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x0183:
        goto L_0x020d;
    L_0x0185:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzj(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x0191:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x0195:
        goto L_0x020d;
    L_0x0197:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzh(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x01a3:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x01a7:
        goto L_0x020d;
    L_0x01a8:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzi(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x01b4:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x01b8:
        goto L_0x020d;
    L_0x01b9:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zze(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x01c5:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x01c9:
        goto L_0x020d;
    L_0x01ca:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzb(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x01d6:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x01da:
        goto L_0x020d;
    L_0x01db:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zza(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x01e7:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x01eb:
        goto L_0x020d;
    L_0x01ec:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzh(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x01f8:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x01fc:
        goto L_0x020d;
    L_0x01fd:
        r4 = r2.getObject(r1, r4);
        r4 = (java.util.List) r4;
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzi(r4);
        if (r4 <= 0) goto L_0x03aa;
    L_0x0209:
        r5 = r0.zzmr;
        if (r5 == 0) goto L_0x0211;
    L_0x020d:
        r14 = (long) r14;
        r2.putInt(r1, r14, r4);
    L_0x0211:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzr(r3);
        r5 = com.google.android.gms.internal.clearcut.zzbn.zzt(r4);
        r3 = r3 + r5;
        r3 = r3 + r4;
        goto L_0x0296;
    L_0x021d:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzq(r3, r4, r11);
        goto L_0x0296;
    L_0x0227:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzu(r3, r4, r11);
        goto L_0x0296;
    L_0x0230:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzr(r3, r4, r11);
        goto L_0x0296;
    L_0x0239:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzt(r3, r4, r11);
        goto L_0x0296;
    L_0x0242:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzd(r3, r4);
        goto L_0x0296;
    L_0x024b:
        r4 = zzd(r1, r4);
        r5 = r0.zzad(r12);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, r4, r5);
        goto L_0x0296;
    L_0x0258:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, r4);
        goto L_0x0296;
    L_0x0261:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzx(r3, r4, r11);
        goto L_0x0296;
    L_0x026a:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzs(r3, r4, r11);
        goto L_0x0296;
    L_0x0273:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzp(r3, r4, r11);
        goto L_0x0296;
    L_0x027c:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzo(r3, r4, r11);
        goto L_0x0296;
    L_0x0285:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzv(r3, r4, r11);
        goto L_0x0296;
    L_0x028e:
        r4 = zzd(r1, r4);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzw(r3, r4, r11);
    L_0x0296:
        r13 = r13 + r3;
        goto L_0x03aa;
    L_0x0299:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x029f:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4);
        r4 = (com.google.android.gms.internal.clearcut.zzdo) r4;
        r5 = r0.zzad(r12);
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, r4, r5);
        goto L_0x0296;
    L_0x02ae:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x02b4:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r1, r4);
    L_0x02b8:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzf(r3, r4);
        goto L_0x0296;
    L_0x02bd:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x02c3:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r1, r4);
    L_0x02c7:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzi(r3, r4);
        goto L_0x0296;
    L_0x02cc:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x02d2:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, r9);
        goto L_0x0296;
    L_0x02d7:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x02dd:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzk(r3, r11);
        goto L_0x0296;
    L_0x02e2:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x02e8:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r1, r4);
    L_0x02ec:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzl(r3, r4);
        goto L_0x0296;
    L_0x02f1:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x02f7:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r1, r4);
    L_0x02fb:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, r4);
        goto L_0x0296;
    L_0x0300:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x0306:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4);
    L_0x030a:
        r4 = (com.google.android.gms.internal.clearcut.zzbb) r4;
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, r4);
        goto L_0x0296;
    L_0x0311:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x0317:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4);
        r5 = r0.zzad(r12);
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, r4, r5);
        goto L_0x0296;
    L_0x0325:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x032b:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4);
        r5 = r4 instanceof com.google.android.gms.internal.clearcut.zzbb;
        if (r5 == 0) goto L_0x0334;
    L_0x0333:
        goto L_0x030a;
    L_0x0334:
        r4 = (java.lang.String) r4;
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, r4);
        goto L_0x0296;
    L_0x033c:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x0342:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, r7);
        goto L_0x0296;
    L_0x0348:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x034e:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzj(r3, r11);
        goto L_0x0296;
    L_0x0354:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x035a:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, r9);
        goto L_0x0296;
    L_0x0360:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x0366:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r1, r4);
    L_0x036a:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, r4);
        goto L_0x0296;
    L_0x0370:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x0376:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r1, r4);
    L_0x037a:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zze(r3, r4);
        goto L_0x0296;
    L_0x0380:
        r14 = r0.zza(r1, r12);
        if (r14 == 0) goto L_0x03aa;
    L_0x0386:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r1, r4);
    L_0x038a:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzd(r3, r4);
        goto L_0x0296;
    L_0x0390:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x0396:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, r6);
        goto L_0x0296;
    L_0x039c:
        r4 = r0.zza(r1, r12);
        if (r4 == 0) goto L_0x03aa;
    L_0x03a2:
        r4 = 0;
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, r4);
        goto L_0x0296;
    L_0x03aa:
        r12 = r12 + 4;
        r3 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        goto L_0x0016;
    L_0x03b0:
        r2 = r0.zzmx;
        r1 = zza(r2, r1);
        r13 = r13 + r1;
        return r13;
    L_0x03b8:
        r2 = zzmh;
        r3 = -1;
        r5 = r3;
        r3 = r11;
        r4 = r3;
        r12 = r4;
    L_0x03bf:
        r13 = r0.zzmi;
        r13 = r13.length;
        if (r3 >= r13) goto L_0x07d7;
    L_0x03c4:
        r13 = r0.zzag(r3);
        r14 = r0.zzmi;
        r14 = r14[r3];
        r15 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r16 = r13 & r15;
        r15 = r16 >>> 20;
        r6 = 17;
        if (r15 > r6) goto L_0x03eb;
    L_0x03d6:
        r6 = r0.zzmi;
        r16 = r3 + 2;
        r6 = r6[r16];
        r11 = r6 & r8;
        r16 = r6 >>> 20;
        r16 = r7 << r16;
        if (r11 == r5) goto L_0x040c;
    L_0x03e4:
        r9 = (long) r11;
        r12 = r2.getInt(r1, r9);
        r5 = r11;
        goto L_0x040c;
    L_0x03eb:
        r6 = r0.zzmr;
        if (r6 == 0) goto L_0x0409;
    L_0x03ef:
        r6 = com.google.android.gms.internal.clearcut.zzcb.DOUBLE_LIST_PACKED;
        r6 = r6.id();
        if (r15 < r6) goto L_0x0409;
    L_0x03f7:
        r6 = com.google.android.gms.internal.clearcut.zzcb.SINT64_LIST_PACKED;
        r6 = r6.id();
        if (r15 > r6) goto L_0x0409;
    L_0x03ff:
        r6 = r0.zzmi;
        r9 = r3 + 2;
        r6 = r6[r9];
        r11 = r6 & r8;
        r6 = r11;
        goto L_0x040a;
    L_0x0409:
        r6 = 0;
    L_0x040a:
        r16 = 0;
    L_0x040c:
        r9 = r13 & r8;
        r9 = (long) r9;
        switch(r15) {
            case 0: goto L_0x07c0;
            case 1: goto L_0x07b0;
            case 2: goto L_0x079e;
            case 3: goto L_0x078e;
            case 4: goto L_0x077e;
            case 5: goto L_0x076f;
            case 6: goto L_0x0763;
            case 7: goto L_0x0759;
            case 8: goto L_0x0744;
            case 9: goto L_0x0732;
            case 10: goto L_0x0723;
            case 11: goto L_0x0716;
            case 12: goto L_0x0709;
            case 13: goto L_0x06fe;
            case 14: goto L_0x06f3;
            case 15: goto L_0x06e6;
            case 16: goto L_0x06d9;
            case 17: goto L_0x06c6;
            case 18: goto L_0x06b2;
            case 19: goto L_0x06a4;
            case 20: goto L_0x0698;
            case 21: goto L_0x068c;
            case 22: goto L_0x0680;
            case 23: goto L_0x0674;
            case 24: goto L_0x06a4;
            case 25: goto L_0x0668;
            case 26: goto L_0x065d;
            case 27: goto L_0x064e;
            case 28: goto L_0x0642;
            case 29: goto L_0x0635;
            case 30: goto L_0x0628;
            case 31: goto L_0x06a4;
            case 32: goto L_0x0674;
            case 33: goto L_0x061b;
            case 34: goto L_0x060e;
            case 35: goto L_0x05ee;
            case 36: goto L_0x05dd;
            case 37: goto L_0x05cc;
            case 38: goto L_0x05bb;
            case 39: goto L_0x05aa;
            case 40: goto L_0x0599;
            case 41: goto L_0x0588;
            case 42: goto L_0x0576;
            case 43: goto L_0x0564;
            case 44: goto L_0x0552;
            case 45: goto L_0x0540;
            case 46: goto L_0x052e;
            case 47: goto L_0x051c;
            case 48: goto L_0x050a;
            case 49: goto L_0x04fa;
            case 50: goto L_0x04ea;
            case 51: goto L_0x04dc;
            case 52: goto L_0x04cf;
            case 53: goto L_0x04bf;
            case 54: goto L_0x04af;
            case 55: goto L_0x049f;
            case 56: goto L_0x0491;
            case 57: goto L_0x0484;
            case 58: goto L_0x047c;
            case 59: goto L_0x046c;
            case 60: goto L_0x0464;
            case 61: goto L_0x045c;
            case 62: goto L_0x0450;
            case 63: goto L_0x0444;
            case 64: goto L_0x043c;
            case 65: goto L_0x0434;
            case 66: goto L_0x0428;
            case 67: goto L_0x041c;
            case 68: goto L_0x0414;
            default: goto L_0x0412;
        };
    L_0x0412:
        goto L_0x06be;
    L_0x0414:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x041a:
        goto L_0x06ca;
    L_0x041c:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x0422:
        r9 = zzh(r1, r9);
        goto L_0x06e1;
    L_0x0428:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x042e:
        r6 = zzg(r1, r9);
        goto L_0x06ee;
    L_0x0434:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x043a:
        goto L_0x06f7;
    L_0x043c:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x0442:
        goto L_0x0702;
    L_0x0444:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x044a:
        r6 = zzg(r1, r9);
        goto L_0x0711;
    L_0x0450:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x0456:
        r6 = zzg(r1, r9);
        goto L_0x071e;
    L_0x045c:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x0462:
        goto L_0x0727;
    L_0x0464:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x046a:
        goto L_0x0736;
    L_0x046c:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x0472:
        r6 = r2.getObject(r1, r9);
        r9 = r6 instanceof com.google.android.gms.internal.clearcut.zzbb;
        if (r9 == 0) goto L_0x0751;
    L_0x047a:
        goto L_0x0750;
    L_0x047c:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x0482:
        goto L_0x075d;
    L_0x0484:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x048a:
        r6 = 0;
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzj(r14, r6);
        goto L_0x0707;
    L_0x0491:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x0497:
        r9 = 0;
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzg(r14, r9);
        goto L_0x06bd;
    L_0x049f:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x04a5:
        r6 = zzg(r1, r9);
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzg(r14, r6);
        goto L_0x06bd;
    L_0x04af:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x04b5:
        r9 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.clearcut.zzbn.zze(r14, r9);
        goto L_0x06bd;
    L_0x04bf:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x04c5:
        r9 = zzh(r1, r9);
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzd(r14, r9);
        goto L_0x06bd;
    L_0x04cf:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x04d5:
        r6 = 0;
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r6);
        goto L_0x0707;
    L_0x04dc:
        r6 = r0.zza(r1, r14, r3);
        if (r6 == 0) goto L_0x06be;
    L_0x04e2:
        r9 = 0;
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r9);
        goto L_0x06bd;
    L_0x04ea:
        r6 = r0.zzmz;
        r9 = r2.getObject(r1, r9);
        r10 = r0.zzae(r3);
        r6 = r6.zzb(r14, r9, r10);
        goto L_0x06bd;
    L_0x04fa:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r9 = r0.zzad(r3);
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzd(r14, r6, r9);
        goto L_0x06bd;
    L_0x050a:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzc(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x0516:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x051a:
        goto L_0x05fe;
    L_0x051c:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzg(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x0528:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x052c:
        goto L_0x05fe;
    L_0x052e:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzi(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x053a:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x053e:
        goto L_0x05fe;
    L_0x0540:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzh(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x054c:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x0550:
        goto L_0x05fe;
    L_0x0552:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzd(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x055e:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x0562:
        goto L_0x05fe;
    L_0x0564:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzf(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x0570:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x0574:
        goto L_0x05fe;
    L_0x0576:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzj(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x0582:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x0586:
        goto L_0x05fe;
    L_0x0588:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzh(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x0594:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x0598:
        goto L_0x05fe;
    L_0x0599:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzi(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x05a5:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x05a9:
        goto L_0x05fe;
    L_0x05aa:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zze(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x05b6:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x05ba:
        goto L_0x05fe;
    L_0x05bb:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzb(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x05c7:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x05cb:
        goto L_0x05fe;
    L_0x05cc:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zza(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x05d8:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x05dc:
        goto L_0x05fe;
    L_0x05dd:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzh(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x05e9:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x05ed:
        goto L_0x05fe;
    L_0x05ee:
        r9 = r2.getObject(r1, r9);
        r9 = (java.util.List) r9;
        r9 = com.google.android.gms.internal.clearcut.zzeh.zzi(r9);
        if (r9 <= 0) goto L_0x06be;
    L_0x05fa:
        r10 = r0.zzmr;
        if (r10 == 0) goto L_0x0602;
    L_0x05fe:
        r10 = (long) r6;
        r2.putInt(r1, r10, r9);
    L_0x0602:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzr(r14);
        r10 = com.google.android.gms.internal.clearcut.zzbn.zzt(r9);
        r6 = r6 + r10;
        r6 = r6 + r9;
        goto L_0x06bd;
    L_0x060e:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r11 = 0;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzq(r14, r6, r11);
        goto L_0x06af;
    L_0x061b:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzu(r14, r6, r11);
        goto L_0x06af;
    L_0x0628:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzr(r14, r6, r11);
        goto L_0x06af;
    L_0x0635:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzt(r14, r6, r11);
        goto L_0x06bd;
    L_0x0642:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzd(r14, r6);
        goto L_0x06bd;
    L_0x064e:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r9 = r0.zzad(r3);
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzc(r14, r6, r9);
        goto L_0x06bd;
    L_0x065d:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzc(r14, r6);
        goto L_0x06bd;
    L_0x0668:
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r11 = 0;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzx(r14, r6, r11);
        goto L_0x06af;
    L_0x0674:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzw(r14, r6, r11);
        goto L_0x06af;
    L_0x0680:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzs(r14, r6, r11);
        goto L_0x06af;
    L_0x068c:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzp(r14, r6, r11);
        goto L_0x06af;
    L_0x0698:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzo(r14, r6, r11);
        goto L_0x06af;
    L_0x06a4:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzv(r14, r6, r11);
    L_0x06af:
        r4 = r4 + r6;
        r6 = r11;
        goto L_0x06bf;
    L_0x06b2:
        r11 = 0;
        r6 = r2.getObject(r1, r9);
        r6 = (java.util.List) r6;
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzw(r14, r6, r11);
    L_0x06bd:
        r4 = r4 + r6;
    L_0x06be:
        r6 = 0;
    L_0x06bf:
        r9 = 0;
        r10 = 0;
        r18 = 0;
        goto L_0x07cf;
    L_0x06c6:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x06ca:
        r6 = r2.getObject(r1, r9);
        r6 = (com.google.android.gms.internal.clearcut.zzdo) r6;
        r9 = r0.zzad(r3);
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, r6, r9);
        goto L_0x06bd;
    L_0x06d9:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x06dd:
        r9 = r2.getLong(r1, r9);
    L_0x06e1:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzf(r14, r9);
        goto L_0x06bd;
    L_0x06e6:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x06ea:
        r6 = r2.getInt(r1, r9);
    L_0x06ee:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzi(r14, r6);
        goto L_0x06bd;
    L_0x06f3:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x06f7:
        r9 = 0;
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzh(r14, r9);
        goto L_0x06bd;
    L_0x06fe:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x0702:
        r6 = 0;
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzk(r14, r6);
    L_0x0707:
        r4 = r4 + r9;
        goto L_0x06be;
    L_0x0709:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x070d:
        r6 = r2.getInt(r1, r9);
    L_0x0711:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzl(r14, r6);
        goto L_0x06bd;
    L_0x0716:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x071a:
        r6 = r2.getInt(r1, r9);
    L_0x071e:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzh(r14, r6);
        goto L_0x06bd;
    L_0x0723:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x0727:
        r6 = r2.getObject(r1, r9);
    L_0x072b:
        r6 = (com.google.android.gms.internal.clearcut.zzbb) r6;
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, r6);
        goto L_0x06bd;
    L_0x0732:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x0736:
        r6 = r2.getObject(r1, r9);
        r9 = r0.zzad(r3);
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzc(r14, r6, r9);
        goto L_0x06bd;
    L_0x0744:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x0748:
        r6 = r2.getObject(r1, r9);
        r9 = r6 instanceof com.google.android.gms.internal.clearcut.zzbb;
        if (r9 == 0) goto L_0x0751;
    L_0x0750:
        goto L_0x072b;
    L_0x0751:
        r6 = (java.lang.String) r6;
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r6);
        goto L_0x06bd;
    L_0x0759:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x075d:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, r7);
        goto L_0x06bd;
    L_0x0763:
        r6 = r12 & r16;
        if (r6 == 0) goto L_0x06be;
    L_0x0767:
        r6 = 0;
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzj(r14, r6);
        r4 = r4 + r9;
        goto L_0x06bf;
    L_0x076f:
        r6 = 0;
        r9 = r12 & r16;
        if (r9 == 0) goto L_0x06bf;
    L_0x0774:
        r9 = 0;
        r11 = com.google.android.gms.internal.clearcut.zzbn.zzg(r14, r9);
        r4 = r4 + r11;
        r18 = r9;
        goto L_0x07ae;
    L_0x077e:
        r6 = 0;
        r18 = 0;
        r11 = r12 & r16;
        if (r11 == 0) goto L_0x07ae;
    L_0x0785:
        r9 = r2.getInt(r1, r9);
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzg(r14, r9);
        goto L_0x07ad;
    L_0x078e:
        r6 = 0;
        r18 = 0;
        r11 = r12 & r16;
        if (r11 == 0) goto L_0x07ae;
    L_0x0795:
        r9 = r2.getLong(r1, r9);
        r9 = com.google.android.gms.internal.clearcut.zzbn.zze(r14, r9);
        goto L_0x07ad;
    L_0x079e:
        r6 = 0;
        r18 = 0;
        r11 = r12 & r16;
        if (r11 == 0) goto L_0x07ae;
    L_0x07a5:
        r9 = r2.getLong(r1, r9);
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzd(r14, r9);
    L_0x07ad:
        r4 = r4 + r9;
    L_0x07ae:
        r9 = 0;
        goto L_0x07bd;
    L_0x07b0:
        r6 = 0;
        r18 = 0;
        r9 = r12 & r16;
        if (r9 == 0) goto L_0x07ae;
    L_0x07b7:
        r9 = 0;
        r10 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r9);
        r4 = r4 + r10;
    L_0x07bd:
        r10 = 0;
        goto L_0x07cf;
    L_0x07c0:
        r6 = 0;
        r9 = 0;
        r18 = 0;
        r10 = r12 & r16;
        if (r10 == 0) goto L_0x07bd;
    L_0x07c8:
        r10 = 0;
        r13 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r10);
        r4 = r4 + r13;
    L_0x07cf:
        r3 = r3 + 4;
        r11 = r6;
        r6 = r9;
        r9 = r18;
        goto L_0x03bf;
    L_0x07d7:
        r2 = r0.zzmx;
        r2 = zza(r2, r1);
        r4 = r4 + r2;
        r2 = r0.zzmo;
        if (r2 == 0) goto L_0x07ed;
    L_0x07e2:
        r2 = r0.zzmy;
        r1 = r2.zza(r1);
        r1 = r1.zzas();
        r4 = r4 + r1;
    L_0x07ed:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzm(java.lang.Object):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:86:0x011a A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00ca A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x011a A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0108 A:{SYNTHETIC} */
    public final boolean zzo(T r17) {
        /*
        r16 = this;
        r0 = r16;
        r1 = r17;
        r2 = r0.zzms;
        r3 = 1;
        if (r2 == 0) goto L_0x0133;
    L_0x0009:
        r2 = r0.zzms;
        r2 = r2.length;
        if (r2 != 0) goto L_0x0010;
    L_0x000e:
        goto L_0x0133;
    L_0x0010:
        r2 = -1;
        r4 = r0.zzms;
        r5 = 0;
        r6 = r4.length;
        r7 = r2;
        r2 = r5;
        r8 = r2;
    L_0x0018:
        if (r2 >= r6) goto L_0x0120;
    L_0x001a:
        r9 = r4[r2];
        r10 = r0.zzai(r9);
        r11 = r0.zzag(r10);
        r12 = r0.zzmq;
        r13 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        if (r12 != 0) goto L_0x0046;
    L_0x002b:
        r12 = r0.zzmi;
        r14 = r10 + 2;
        r12 = r12[r14];
        r14 = r12 & r13;
        r12 = r12 >>> 20;
        r12 = r3 << r12;
        if (r14 == r7) goto L_0x0044;
    L_0x0039:
        r7 = zzmh;
        r15 = r4;
        r3 = (long) r14;
        r3 = r7.getInt(r1, r3);
        r8 = r3;
        r7 = r14;
        goto L_0x0048;
    L_0x0044:
        r15 = r4;
        goto L_0x0048;
    L_0x0046:
        r15 = r4;
        r12 = r5;
    L_0x0048:
        r3 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r3 = r3 & r11;
        if (r3 == 0) goto L_0x004f;
    L_0x004d:
        r3 = 1;
        goto L_0x0050;
    L_0x004f:
        r3 = r5;
    L_0x0050:
        if (r3 == 0) goto L_0x0059;
    L_0x0052:
        r3 = r0.zza(r1, r10, r8, r12);
        if (r3 != 0) goto L_0x0059;
    L_0x0058:
        return r5;
    L_0x0059:
        r3 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r3 = r3 & r11;
        r3 = r3 >>> 20;
        r4 = 9;
        if (r3 == r4) goto L_0x0109;
    L_0x0062:
        r4 = 17;
        if (r3 == r4) goto L_0x0109;
    L_0x0066:
        r4 = 27;
        if (r3 == r4) goto L_0x00dc;
    L_0x006a:
        r4 = 60;
        if (r3 == r4) goto L_0x00cb;
    L_0x006e:
        r4 = 68;
        if (r3 == r4) goto L_0x00cb;
    L_0x0072:
        switch(r3) {
            case 49: goto L_0x00dc;
            case 50: goto L_0x0077;
            default: goto L_0x0075;
        };
    L_0x0075:
        goto L_0x011a;
    L_0x0077:
        r3 = r0.zzmz;
        r4 = r11 & r13;
        r11 = (long) r4;
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r11);
        r3 = r3.zzh(r4);
        r4 = r3.isEmpty();
        if (r4 != 0) goto L_0x00c7;
    L_0x008a:
        r4 = r0.zzae(r10);
        r9 = r0.zzmz;
        r4 = r9.zzl(r4);
        r4 = r4.zzmd;
        r4 = r4.zzek();
        r9 = com.google.android.gms.internal.clearcut.zzfq.MESSAGE;
        if (r4 != r9) goto L_0x00c7;
    L_0x009e:
        r4 = 0;
        r3 = r3.values();
        r3 = r3.iterator();
    L_0x00a7:
        r9 = r3.hasNext();
        if (r9 == 0) goto L_0x00c7;
    L_0x00ad:
        r9 = r3.next();
        if (r4 != 0) goto L_0x00bf;
    L_0x00b3:
        r4 = com.google.android.gms.internal.clearcut.zzea.zzcm();
        r10 = r9.getClass();
        r4 = r4.zze(r10);
    L_0x00bf:
        r9 = r4.zzo(r9);
        if (r9 != 0) goto L_0x00a7;
    L_0x00c5:
        r3 = r5;
        goto L_0x00c8;
    L_0x00c7:
        r3 = 1;
    L_0x00c8:
        if (r3 != 0) goto L_0x011a;
    L_0x00ca:
        return r5;
    L_0x00cb:
        r3 = r0.zza(r1, r9, r10);
        if (r3 == 0) goto L_0x011a;
    L_0x00d1:
        r3 = r0.zzad(r10);
        r3 = zza(r1, r11, r3);
        if (r3 != 0) goto L_0x011a;
    L_0x00db:
        return r5;
    L_0x00dc:
        r3 = r11 & r13;
        r3 = (long) r3;
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r3);
        r3 = (java.util.List) r3;
        r4 = r3.isEmpty();
        if (r4 != 0) goto L_0x0105;
    L_0x00eb:
        r4 = r0.zzad(r10);
        r9 = r5;
    L_0x00f0:
        r10 = r3.size();
        if (r9 >= r10) goto L_0x0105;
    L_0x00f6:
        r10 = r3.get(r9);
        r10 = r4.zzo(r10);
        if (r10 != 0) goto L_0x0102;
    L_0x0100:
        r3 = r5;
        goto L_0x0106;
    L_0x0102:
        r9 = r9 + 1;
        goto L_0x00f0;
    L_0x0105:
        r3 = 1;
    L_0x0106:
        if (r3 != 0) goto L_0x011a;
    L_0x0108:
        return r5;
    L_0x0109:
        r3 = r0.zza(r1, r10, r8, r12);
        if (r3 == 0) goto L_0x011a;
    L_0x010f:
        r3 = r0.zzad(r10);
        r3 = zza(r1, r11, r3);
        if (r3 != 0) goto L_0x011a;
    L_0x0119:
        return r5;
    L_0x011a:
        r2 = r2 + 1;
        r4 = r15;
        r3 = 1;
        goto L_0x0018;
    L_0x0120:
        r2 = r0.zzmo;
        if (r2 == 0) goto L_0x0131;
    L_0x0124:
        r2 = r0.zzmy;
        r1 = r2.zza(r1);
        r1 = r1.isInitialized();
        if (r1 != 0) goto L_0x0131;
    L_0x0130:
        return r5;
    L_0x0131:
        r1 = 1;
        return r1;
    L_0x0133:
        r1 = r3;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzo(java.lang.Object):boolean");
    }
}
