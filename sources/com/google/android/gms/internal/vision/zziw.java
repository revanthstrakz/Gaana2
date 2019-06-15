package com.google.android.gms.internal.vision;

import java.nio.ByteBuffer;

final class zziw {
    private static final zziy zzabt;

    private static int zzbr(int i) {
        return i > -12 ? -1 : i;
    }

    private static int zzc(int i, int i2, int i3) {
        return (i > -12 || i2 > -65 || i3 > -65) ? -1 : (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean zzi(byte[] bArr) {
        return zzabt.zzg(bArr, 0, bArr.length);
    }

    private static int zzt(int i, int i2) {
        return (i > -12 || i2 > -65) ? -1 : i ^ (i2 << 8);
    }

    public static boolean zzg(byte[] bArr, int i, int i2) {
        return zzabt.zzg(bArr, i, i2);
    }

    private static int zzh(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return zzbr(b);
            case 1:
                return zzt(b, bArr[i]);
            case 2:
                return zzc(b, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x007b A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005a  */
    static int zza(java.lang.CharSequence r8) {
        /*
        r0 = r8.length();
        r1 = 0;
        r2 = r1;
    L_0x0006:
        if (r2 >= r0) goto L_0x0013;
    L_0x0008:
        r3 = r8.charAt(r2);
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r3 >= r4) goto L_0x0013;
    L_0x0010:
        r2 = r2 + 1;
        goto L_0x0006;
    L_0x0013:
        r3 = r0;
    L_0x0014:
        if (r2 >= r0) goto L_0x0058;
    L_0x0016:
        r4 = r8.charAt(r2);
        r5 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        if (r4 >= r5) goto L_0x0026;
    L_0x001e:
        r4 = 127 - r4;
        r4 = r4 >>> 31;
        r3 = r3 + r4;
        r2 = r2 + 1;
        goto L_0x0014;
    L_0x0026:
        r4 = r8.length();
    L_0x002a:
        if (r2 >= r4) goto L_0x0057;
    L_0x002c:
        r6 = r8.charAt(r2);
        if (r6 >= r5) goto L_0x0038;
    L_0x0032:
        r6 = 127 - r6;
        r6 = r6 >>> 31;
        r1 = r1 + r6;
        goto L_0x0054;
    L_0x0038:
        r1 = r1 + 2;
        r7 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r7 > r6) goto L_0x0054;
    L_0x003f:
        r7 = 57343; // 0xdfff float:8.0355E-41 double:2.8331E-319;
        if (r6 > r7) goto L_0x0054;
    L_0x0044:
        r6 = java.lang.Character.codePointAt(r8, r2);
        r7 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r6 >= r7) goto L_0x0052;
    L_0x004c:
        r8 = new com.google.android.gms.internal.vision.zzja;
        r8.<init>(r2, r4);
        throw r8;
    L_0x0052:
        r2 = r2 + 1;
    L_0x0054:
        r2 = r2 + 1;
        goto L_0x002a;
    L_0x0057:
        r3 = r3 + r1;
    L_0x0058:
        if (r3 >= r0) goto L_0x007b;
    L_0x005a:
        r8 = new java.lang.IllegalArgumentException;
        r0 = (long) r3;
        r2 = 4294967296; // 0x100000000 float:0.0 double:2.121995791E-314;
        r4 = r0 + r2;
        r0 = 54;
        r1 = new java.lang.StringBuilder;
        r1.<init>(r0);
        r0 = "UTF-8 length does not fit in int: ";
        r1.append(r0);
        r1.append(r4);
        r0 = r1.toString();
        r8.<init>(r0);
        throw r8;
    L_0x007b:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zziw.zza(java.lang.CharSequence):int");
    }

    static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return zzabt.zzb(charSequence, bArr, i, i2);
    }

    static String zzi(byte[] bArr, int i, int i2) throws zzgf {
        return zzabt.zzi(bArr, i, i2);
    }

    static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        zziy zziy = zzabt;
        if (byteBuffer.hasArray()) {
            int arrayOffset = byteBuffer.arrayOffset();
            byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
        } else if (byteBuffer.isDirect()) {
            zziy.zzb(charSequence, byteBuffer);
        } else {
            zziy.zzc(charSequence, byteBuffer);
        }
    }

    static {
        zziy zziz;
        Object obj = (zziu.zzhh() && zziu.zzhi()) ? 1 : null;
        if (obj == null || zzeg.zzck()) {
            zziz = new zziz();
        } else {
            zziz = new zzjb();
        }
        zzabt = zziz;
    }
}
