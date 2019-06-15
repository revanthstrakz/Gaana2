package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.IOException;

public final class zzjw implements zzhz {
    private static final int zzatm = zzqe.zzam("RCC\u0001");
    private int version;
    private final zzfs zzaad;
    private int zzajn;
    private int zzaqe = 0;
    private zzii zzasj;
    private final zzpx zzatn = new zzpx(9);
    private long zzato;
    private int zzatp;

    public zzjw(zzfs zzfs) {
        this.zzaad = zzfs;
    }

    public final void release() {
    }

    public final void zza(zzib zzib) {
        zzib.zza(new zzih(C.TIME_UNSET));
        this.zzasj = zzib.zzb(0, 3);
        zzib.zzdy();
        this.zzasj.zzf(this.zzaad);
    }

    public final boolean zza(zzia zzia) throws IOException, InterruptedException {
        this.zzatn.reset();
        zzia.zza(this.zzatn.data, 0, 8);
        if (this.zzatn.readInt() == zzatm) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0093 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0093 A:{SYNTHETIC} */
    public final int zza(com.google.android.gms.internal.ads.zzia r11, com.google.android.gms.internal.ads.zzif r12) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r10 = this;
    L_0x0000:
        r12 = r10.zzaqe;
        r0 = -1;
        r1 = 1;
        r2 = 0;
        switch(r12) {
            case 0: goto L_0x00b1;
            case 1: goto L_0x0044;
            case 2: goto L_0x000e;
            default: goto L_0x0008;
        };
    L_0x0008:
        r11 = new java.lang.IllegalStateException;
        r11.<init>();
        throw r11;
    L_0x000e:
        r12 = r10.zzatp;
        if (r12 <= 0) goto L_0x0031;
    L_0x0012:
        r12 = r10.zzatn;
        r12.reset();
        r12 = r10.zzatn;
        r12 = r12.data;
        r0 = 3;
        r11.readFully(r12, r2, r0);
        r12 = r10.zzasj;
        r3 = r10.zzatn;
        r12.zza(r3, r0);
        r12 = r10.zzajn;
        r12 = r12 + r0;
        r10.zzajn = r12;
        r12 = r10.zzatp;
        r12 = r12 - r1;
        r10.zzatp = r12;
        goto L_0x000e;
    L_0x0031:
        r11 = r10.zzajn;
        if (r11 <= 0) goto L_0x0041;
    L_0x0035:
        r3 = r10.zzasj;
        r4 = r10.zzato;
        r6 = 1;
        r7 = r10.zzajn;
        r8 = 0;
        r9 = 0;
        r3.zza(r4, r6, r7, r8, r9);
    L_0x0041:
        r10.zzaqe = r1;
        return r2;
    L_0x0044:
        r12 = r10.zzatn;
        r12.reset();
        r12 = r10.version;
        if (r12 != 0) goto L_0x0069;
    L_0x004d:
        r12 = r10.zzatn;
        r12 = r12.data;
        r3 = 5;
        r12 = r11.zza(r12, r2, r3, r1);
        if (r12 != 0) goto L_0x005a;
    L_0x0058:
        r1 = r2;
        goto L_0x008c;
    L_0x005a:
        r12 = r10.zzatn;
        r3 = r12.zzhd();
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r3 = r3 * r5;
        r5 = 45;
        r3 = r3 / r5;
        r10.zzato = r3;
        goto L_0x0082;
    L_0x0069:
        r12 = r10.version;
        if (r12 != r1) goto L_0x0096;
    L_0x006d:
        r12 = r10.zzatn;
        r12 = r12.data;
        r3 = 9;
        r12 = r11.zza(r12, r2, r3, r1);
        if (r12 != 0) goto L_0x007a;
    L_0x0079:
        goto L_0x0058;
    L_0x007a:
        r12 = r10.zzatn;
        r3 = r12.readLong();
        r10.zzato = r3;
    L_0x0082:
        r12 = r10.zzatn;
        r12 = r12.readUnsignedByte();
        r10.zzatp = r12;
        r10.zzajn = r2;
    L_0x008c:
        if (r1 == 0) goto L_0x0093;
    L_0x008e:
        r12 = 2;
        r10.zzaqe = r12;
        goto L_0x0000;
    L_0x0093:
        r10.zzaqe = r2;
        return r0;
    L_0x0096:
        r11 = new com.google.android.gms.internal.ads.zzfx;
        r12 = r10.version;
        r0 = 39;
        r1 = new java.lang.StringBuilder;
        r1.<init>(r0);
        r0 = "Unsupported version number: ";
        r1.append(r0);
        r1.append(r12);
        r12 = r1.toString();
        r11.<init>(r12);
        throw r11;
    L_0x00b1:
        r12 = r10.zzatn;
        r12.reset();
        r12 = r10.zzatn;
        r12 = r12.data;
        r3 = 8;
        r12 = r11.zza(r12, r2, r3, r1);
        if (r12 == 0) goto L_0x00dd;
    L_0x00c2:
        r12 = r10.zzatn;
        r12 = r12.readInt();
        r2 = zzatm;
        if (r12 == r2) goto L_0x00d4;
    L_0x00cc:
        r11 = new java.io.IOException;
        r12 = "Input not RawCC";
        r11.<init>(r12);
        throw r11;
    L_0x00d4:
        r12 = r10.zzatn;
        r12 = r12.readUnsignedByte();
        r10.version = r12;
        r2 = r1;
    L_0x00dd:
        if (r2 == 0) goto L_0x00e3;
    L_0x00df:
        r10.zzaqe = r1;
        goto L_0x0000;
    L_0x00e3:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjw.zza(com.google.android.gms.internal.ads.zzia, com.google.android.gms.internal.ads.zzif):int");
    }

    public final void zzc(long j, long j2) {
        this.zzaqe = 0;
    }
}
