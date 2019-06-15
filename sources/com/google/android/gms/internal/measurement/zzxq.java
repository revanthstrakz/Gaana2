package com.google.android.gms.internal.measurement;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.nio.ByteBuffer;

final class zzxq extends zzxn {
    zzxq() {
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Missing block: B:47:0x00ab, code skipped:
            return -1;
     */
    /* JADX WARNING: Missing block: B:59:0x00d6, code skipped:
            return -1;
     */
    public final int zzb(int r12, byte[] r13, int r14, int r15) {
        /*
        r11 = this;
        r12 = r14 | r15;
        r0 = 2;
        r1 = 3;
        r2 = 0;
        r3 = r13.length;
        r3 = r3 - r15;
        r12 = r12 | r3;
        if (r12 >= 0) goto L_0x002c;
    L_0x000a:
        r12 = new java.lang.ArrayIndexOutOfBoundsException;
        r3 = "Array length=%d, index=%d, limit=%d";
        r1 = new java.lang.Object[r1];
        r4 = 1;
        r13 = r13.length;
        r13 = java.lang.Integer.valueOf(r13);
        r1[r2] = r13;
        r13 = java.lang.Integer.valueOf(r14);
        r1[r4] = r13;
        r13 = java.lang.Integer.valueOf(r15);
        r1[r0] = r13;
        r13 = java.lang.String.format(r3, r1);
        r12.<init>(r13);
        throw r12;
    L_0x002c:
        r3 = (long) r14;
        r14 = (long) r15;
        r5 = r14 - r3;
        r12 = (int) r5;
        r14 = 16;
        r5 = 1;
        if (r12 >= r14) goto L_0x0039;
    L_0x0037:
        r14 = r2;
        goto L_0x004b;
    L_0x0039:
        r14 = r2;
        r7 = r3;
    L_0x003b:
        if (r14 >= r12) goto L_0x004a;
    L_0x003d:
        r9 = r7 + r5;
        r15 = com.google.android.gms.internal.measurement.zzxj.zza(r13, r7);
        if (r15 >= 0) goto L_0x0046;
    L_0x0045:
        goto L_0x004b;
    L_0x0046:
        r14 = r14 + 1;
        r7 = r9;
        goto L_0x003b;
    L_0x004a:
        r14 = r12;
    L_0x004b:
        r12 = r12 - r14;
        r14 = (long) r14;
        r7 = r3 + r14;
    L_0x004f:
        r14 = r2;
    L_0x0050:
        if (r12 <= 0) goto L_0x0061;
    L_0x0052:
        r14 = r7 + r5;
        r3 = com.google.android.gms.internal.measurement.zzxj.zza(r13, r7);
        if (r3 < 0) goto L_0x005f;
    L_0x005a:
        r12 = r12 + -1;
        r7 = r14;
        r14 = r3;
        goto L_0x0050;
    L_0x005f:
        r7 = r14;
        r14 = r3;
    L_0x0061:
        if (r12 != 0) goto L_0x0064;
    L_0x0063:
        return r2;
    L_0x0064:
        r12 = r12 + -1;
        r15 = -32;
        r3 = -65;
        r4 = -1;
        if (r14 >= r15) goto L_0x0082;
    L_0x006d:
        if (r12 != 0) goto L_0x0070;
    L_0x006f:
        return r14;
    L_0x0070:
        r12 = r12 + -1;
        r15 = -62;
        if (r14 < r15) goto L_0x0081;
    L_0x0076:
        r14 = r7 + r5;
        r7 = com.google.android.gms.internal.measurement.zzxj.zza(r13, r7);
        if (r7 <= r3) goto L_0x007f;
    L_0x007e:
        goto L_0x0081;
    L_0x007f:
        r7 = r14;
        goto L_0x004f;
    L_0x0081:
        return r4;
    L_0x0082:
        r9 = -16;
        if (r14 >= r9) goto L_0x00ac;
    L_0x0086:
        if (r12 >= r0) goto L_0x008d;
    L_0x0088:
        r12 = zza(r13, r14, r7, r12);
        return r12;
    L_0x008d:
        r12 = r12 + -2;
        r9 = r7 + r5;
        r7 = com.google.android.gms.internal.measurement.zzxj.zza(r13, r7);
        if (r7 > r3) goto L_0x00ab;
    L_0x0097:
        r8 = -96;
        if (r14 != r15) goto L_0x009d;
    L_0x009b:
        if (r7 < r8) goto L_0x00ab;
    L_0x009d:
        r15 = -19;
        if (r14 != r15) goto L_0x00a3;
    L_0x00a1:
        if (r7 >= r8) goto L_0x00ab;
    L_0x00a3:
        r7 = r9 + r5;
        r14 = com.google.android.gms.internal.measurement.zzxj.zza(r13, r9);
        if (r14 <= r3) goto L_0x004f;
    L_0x00ab:
        return r4;
    L_0x00ac:
        if (r12 >= r1) goto L_0x00b3;
    L_0x00ae:
        r12 = zza(r13, r14, r7, r12);
        return r12;
    L_0x00b3:
        r12 = r12 + -3;
        r9 = r7 + r5;
        r15 = com.google.android.gms.internal.measurement.zzxj.zza(r13, r7);
        if (r15 > r3) goto L_0x00d6;
    L_0x00bd:
        r14 = r14 << 28;
        r15 = r15 + 112;
        r14 = r14 + r15;
        r14 = r14 >> 30;
        if (r14 != 0) goto L_0x00d6;
    L_0x00c6:
        r14 = r9 + r5;
        r7 = com.google.android.gms.internal.measurement.zzxj.zza(r13, r9);
        if (r7 > r3) goto L_0x00d6;
    L_0x00ce:
        r7 = r14 + r5;
        r14 = com.google.android.gms.internal.measurement.zzxj.zza(r13, r14);
        if (r14 <= r3) goto L_0x004f;
    L_0x00d6:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzxq.zzb(int, byte[], int, int):int");
    }

    /* Access modifiers changed, original: final */
    public final String zzh(byte[] bArr, int i, int i2) throws zzuv {
        if (((i | i2) | ((bArr.length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        }
        int i3;
        int i4 = i + i2;
        char[] cArr = new char[i2];
        int i5 = 0;
        while (i < i4) {
            byte zza = zzxj.zza(bArr, (long) i);
            if (!zzxm.zzd(zza)) {
                break;
            }
            i++;
            i3 = i5 + 1;
            zzxm.zza(zza, cArr, i5);
            i5 = i3;
        }
        int i6 = i5;
        while (i < i4) {
            i5 = i + 1;
            byte zza2 = zzxj.zza(bArr, (long) i);
            int i7;
            if (zzxm.zzd(zza2)) {
                i7 = i6 + 1;
                zzxm.zza(zza2, cArr, i6);
                while (i5 < i4) {
                    zza2 = zzxj.zza(bArr, (long) i5);
                    if (!zzxm.zzd(zza2)) {
                        break;
                    }
                    i5++;
                    i3 = i7 + 1;
                    zzxm.zza(zza2, cArr, i7);
                    i7 = i3;
                }
                i = i5;
                i6 = i7;
            } else if (zzxm.zze(zza2)) {
                if (i5 >= i4) {
                    throw zzuv.zzwx();
                }
                i7 = i5 + 1;
                i3 = i6 + 1;
                zzxm.zza(zza2, zzxj.zza(bArr, (long) i5), cArr, i6);
                i = i7;
                i6 = i3;
            } else if (zzxm.zzf(zza2)) {
                if (i5 >= i4 - 1) {
                    throw zzuv.zzwx();
                }
                i7 = i5 + 1;
                i3 = i7 + 1;
                int i8 = i6 + 1;
                zzxm.zza(zza2, zzxj.zza(bArr, (long) i5), zzxj.zza(bArr, (long) i7), cArr, i6);
                i = i3;
                i6 = i8;
            } else if (i5 >= i4 - 2) {
                throw zzuv.zzwx();
            } else {
                i7 = i5 + 1;
                byte zza3 = zzxj.zza(bArr, (long) i5);
                i5 = i7 + 1;
                int i9 = i5 + 1;
                int i10 = i6 + 1;
                zzxm.zza(zza2, zza3, zzxj.zza(bArr, (long) i7), zzxj.zza(bArr, (long) i5), cArr, i6);
                i = i9;
                i6 = i10 + 1;
            }
        }
        return new String(cArr, 0, i6);
    }

    /* Access modifiers changed, original: final */
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        CharSequence charSequence2 = charSequence;
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        long j = (long) i3;
        long j2 = j + ((long) i4);
        int length = charSequence.length();
        if (length > i4 || bArr2.length - i4 < i3) {
            char charAt = charSequence2.charAt(length - 1);
            i3 += i4;
            StringBuilder stringBuilder = new StringBuilder(37);
            stringBuilder.append("Failed writing ");
            stringBuilder.append(charAt);
            stringBuilder.append(" at index ");
            stringBuilder.append(i3);
            throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
        }
        char charAt2;
        long j3;
        i3 = 0;
        while (i3 < length) {
            charAt2 = charSequence2.charAt(i3);
            if (charAt2 >= 128) {
                break;
            }
            j3 = j + 1;
            zzxj.zza(bArr2, j, (byte) charAt2);
            i3++;
            j = j3;
        }
        if (i3 == length) {
            return (int) j;
        }
        while (i3 < length) {
            charAt2 = charSequence2.charAt(i3);
            long j4;
            if (charAt2 < 128 && j < j2) {
                j3 = j + 1;
                zzxj.zza(bArr2, j, (byte) charAt2);
                j = j3;
            } else if (charAt2 < 2048 && j <= j2 - 2) {
                j4 = j + 1;
                zzxj.zza(bArr2, j, (byte) (960 | (charAt2 >>> 6)));
                j = j4 + 1;
                zzxj.zza(bArr2, j4, (byte) ((charAt2 & 63) | 128));
            } else if ((charAt2 < 55296 || 57343 < charAt2) && j <= j2 - 3) {
                j4 = j + 1;
                zzxj.zza(bArr2, j, (byte) (480 | (charAt2 >>> 12)));
                j = j4 + 1;
                zzxj.zza(bArr2, j4, (byte) (((charAt2 >>> 6) & 63) | 128));
                j4 = j + 1;
                zzxj.zza(bArr2, j, (byte) ((charAt2 & 63) | 128));
                j = j4;
            } else if (j <= j2 - 4) {
                int i5 = i3 + 1;
                if (i5 != length) {
                    char charAt3 = charSequence2.charAt(i5);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        i3 = Character.toCodePoint(charAt2, charAt3);
                        j4 = j + 1;
                        zzxj.zza(bArr2, j, (byte) (PsExtractor.VIDEO_STREAM_MASK | (i3 >>> 18)));
                        j = j4 + 1;
                        zzxj.zza(bArr2, j4, (byte) (((i3 >>> 12) & 63) | 128));
                        j4 = j + 1;
                        zzxj.zza(bArr2, j, (byte) (((i3 >>> 6) & 63) | 128));
                        j = j4 + 1;
                        zzxj.zza(bArr2, j4, (byte) ((i3 & 63) | 128));
                        i3 = i5;
                    }
                } else {
                    i5 = i3;
                }
                throw new zzxp(i5 - 1, length);
            } else {
                if (55296 <= charAt2 && charAt2 <= 57343) {
                    int i6 = i3 + 1;
                    if (i6 == length || !Character.isSurrogatePair(charAt2, charSequence2.charAt(i6))) {
                        throw new zzxp(i3, length);
                    }
                }
                StringBuilder stringBuilder2 = new StringBuilder(46);
                stringBuilder2.append("Failed writing ");
                stringBuilder2.append(charAt2);
                stringBuilder2.append(" at index ");
                stringBuilder2.append(j);
                throw new ArrayIndexOutOfBoundsException(stringBuilder2.toString());
            }
            i3++;
        }
        return (int) j;
    }

    /* Access modifiers changed, original: final */
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        CharSequence charSequence2 = charSequence;
        ByteBuffer byteBuffer2 = byteBuffer;
        long zzb = zzxj.zzb(byteBuffer);
        long position = zzb + ((long) byteBuffer.position());
        long limit = zzb + ((long) byteBuffer.limit());
        int length = charSequence.length();
        int limit2;
        if (((long) length) > limit - position) {
            char charAt = charSequence2.charAt(length - 1);
            limit2 = byteBuffer.limit();
            StringBuilder stringBuilder = new StringBuilder(37);
            stringBuilder.append("Failed writing ");
            stringBuilder.append(charAt);
            stringBuilder.append(" at index ");
            stringBuilder.append(limit2);
            throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
        }
        char charAt2;
        long j;
        int i = 0;
        while (i < length) {
            charAt2 = charSequence2.charAt(i);
            if (charAt2 >= 128) {
                break;
            }
            j = position + 1;
            zzxj.zza(position, (byte) charAt2);
            i++;
            position = j;
        }
        if (i == length) {
            byteBuffer2.position((int) (position - zzb));
            return;
        }
        while (i < length) {
            long j2;
            charAt2 = charSequence2.charAt(i);
            long j3;
            if (charAt2 < 128 && position < limit) {
                j = position + 1;
                zzxj.zza(position, (byte) charAt2);
                j2 = zzb;
                position = j;
            } else if (charAt2 >= 2048 || position > limit - 2) {
                j2 = zzb;
                if ((charAt2 < 55296 || 57343 < charAt2) && position <= limit - 3) {
                    j3 = position + 1;
                    zzxj.zza(position, (byte) (480 | (charAt2 >>> 12)));
                    position = j3 + 1;
                    zzxj.zza(j3, (byte) (((charAt2 >>> 6) & 63) | 128));
                    j3 = position + 1;
                    zzxj.zza(position, (byte) ((63 & charAt2) | 128));
                    position = j3;
                } else if (position <= limit - 4) {
                    limit2 = i + 1;
                    if (limit2 != length) {
                        char charAt3 = charSequence2.charAt(limit2);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int toCodePoint = Character.toCodePoint(charAt2, charAt3);
                            long j4 = position + 1;
                            zzxj.zza(position, (byte) (PsExtractor.VIDEO_STREAM_MASK | (toCodePoint >>> 18)));
                            long j5 = j4 + 1;
                            zzxj.zza(j4, (byte) (((toCodePoint >>> 12) & 63) | 128));
                            j4 = j5 + 1;
                            zzxj.zza(j5, (byte) (((toCodePoint >>> 6) & 63) | 128));
                            j5 = j4 + 1;
                            zzxj.zza(j4, (byte) ((toCodePoint & 63) | 128));
                            position = j5;
                            i = limit2;
                        }
                    } else {
                        limit2 = i;
                    }
                    throw new zzxp(limit2 - 1, length);
                } else {
                    if (55296 <= charAt2 && charAt2 <= 57343) {
                        limit2 = i + 1;
                        if (limit2 == length || !Character.isSurrogatePair(charAt2, charSequence2.charAt(limit2))) {
                            throw new zzxp(i, length);
                        }
                    }
                    StringBuilder stringBuilder2 = new StringBuilder(46);
                    stringBuilder2.append("Failed writing ");
                    stringBuilder2.append(charAt2);
                    stringBuilder2.append(" at index ");
                    stringBuilder2.append(position);
                    throw new ArrayIndexOutOfBoundsException(stringBuilder2.toString());
                }
            } else {
                j2 = zzb;
                j3 = position + 1;
                zzxj.zza(position, (byte) (960 | (charAt2 >>> 6)));
                position = j3 + 1;
                zzxj.zza(j3, (byte) ((63 & charAt2) | 128));
            }
            i++;
            zzb = j2;
            byteBuffer2 = byteBuffer;
        }
        byteBuffer.position((int) (position - zzb));
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzxl.zzbz(i);
            case 1:
                return zzxl.zzq(i, zzxj.zza(bArr, j));
            case 2:
                return zzxl.zzc(i, zzxj.zza(bArr, j), zzxj.zza(bArr, j + 1));
            default:
                throw new AssertionError();
        }
    }
}
