package com.google.android.gms.internal.ads;

public final class zzoc {
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053  */
    public static void zza(long r16, com.google.android.gms.internal.ads.zzpx r18, com.google.android.gms.internal.ads.zzii[] r19) {
        /*
        r0 = r18;
        r1 = r19;
    L_0x0004:
        r2 = r18.zzhb();
        r3 = 1;
        if (r2 <= r3) goto L_0x0098;
    L_0x000b:
        r2 = zzf(r18);
        r4 = zzf(r18);
        r5 = -1;
        if (r4 == r5) goto L_0x0088;
    L_0x0016:
        r5 = r18.zzhb();
        if (r4 <= r5) goto L_0x001e;
    L_0x001c:
        goto L_0x0088;
    L_0x001e:
        r5 = 4;
        r6 = 3;
        r7 = 8;
        r8 = 0;
        if (r2 != r5) goto L_0x0050;
    L_0x0025:
        if (r4 >= r7) goto L_0x0028;
    L_0x0027:
        goto L_0x0050;
    L_0x0028:
        r2 = r18.getPosition();
        r5 = r18.readUnsignedByte();
        r9 = r18.readUnsignedShort();
        r10 = r18.readInt();
        r11 = r18.readUnsignedByte();
        r0.setPosition(r2);
        r2 = 181; // 0xb5 float:2.54E-43 double:8.94E-322;
        if (r5 != r2) goto L_0x0050;
    L_0x0043:
        r2 = 49;
        if (r9 != r2) goto L_0x0050;
    L_0x0047:
        r2 = 1195456820; // 0x47413934 float:49465.203 double:5.90634146E-315;
        if (r10 != r2) goto L_0x0050;
    L_0x004c:
        if (r11 != r6) goto L_0x0050;
    L_0x004e:
        r2 = r3;
        goto L_0x0051;
    L_0x0050:
        r2 = r8;
    L_0x0051:
        if (r2 == 0) goto L_0x0083;
    L_0x0053:
        r0.zzbl(r7);
        r2 = r18.readUnsignedByte();
        r2 = r2 & 31;
        r0.zzbl(r3);
        r2 = r2 * r6;
        r3 = r18.getPosition();
        r5 = r1.length;
    L_0x0065:
        if (r8 >= r5) goto L_0x007b;
    L_0x0067:
        r9 = r1[r8];
        r0.setPosition(r3);
        r9.zza(r0, r2);
        r12 = 1;
        r14 = 0;
        r15 = 0;
        r10 = r16;
        r13 = r2;
        r9.zza(r10, r12, r13, r14, r15);
        r8 = r8 + 1;
        goto L_0x0065;
    L_0x007b:
        r3 = 10;
        r3 = r3 + r2;
        r4 = r4 - r3;
        r0.zzbl(r4);
        goto L_0x0004;
    L_0x0083:
        r0.zzbl(r4);
        goto L_0x0004;
    L_0x0088:
        r2 = "CeaUtil";
        r3 = "Skipping remainder of malformed SEI NAL unit.";
        android.util.Log.w(r2, r3);
        r2 = r18.limit();
        r0.setPosition(r2);
        goto L_0x0004;
    L_0x0098:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzoc.zza(long, com.google.android.gms.internal.ads.zzpx, com.google.android.gms.internal.ads.zzii[]):void");
    }

    private static int zzf(zzpx zzpx) {
        int i = 0;
        while (zzpx.zzhb() != 0) {
            int readUnsignedByte = zzpx.readUnsignedByte();
            i += readUnsignedByte;
            if (readUnsignedByte != 255) {
                return i;
            }
        }
        return -1;
    }
}
