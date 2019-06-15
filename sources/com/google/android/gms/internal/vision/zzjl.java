package com.google.android.gms.internal.vision;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzjl {
    private zzfe zzade;
    private int zzadf;
    private final ByteBuffer zzsw;

    private zzjl(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zzbd(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (i & -268435456) == 0 ? 4 : 5;
    }

    private zzjl(ByteBuffer byteBuffer) {
        this.zzsw = byteBuffer;
        this.zzsw.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static zzjl zzk(byte[] bArr) {
        return zzl(bArr, 0, bArr.length);
    }

    public static zzjl zzl(byte[] bArr, int i, int i2) {
        return new zzjl(bArr, 0, i2);
    }

    public final void zza(int i, float f) throws IOException {
        zzd(i, 5);
        i = Float.floatToIntBits(f);
        if (this.zzsw.remaining() < 4) {
            throw new zzjm(this.zzsw.position(), this.zzsw.limit());
        }
        this.zzsw.putInt(i);
    }

    public final void zzi(int i, long j) throws IOException {
        zzd(i, 0);
        zzq(j);
    }

    public final void zze(int i, int i2) throws IOException {
        zzd(i, 0);
        if (i2 >= 0) {
            zzbv(i2);
        } else {
            zzq((long) i2);
        }
    }

    public final void zzb(int i, boolean z) throws IOException {
        zzd(i, 0);
        byte b = (byte) z;
        if (this.zzsw.hasRemaining()) {
            this.zzsw.put(b);
            return;
        }
        throw new zzjm(this.zzsw.position(), this.zzsw.limit());
    }

    public final void zza(int i, String str) throws IOException {
        zzd(i, 2);
        try {
            i = zzbd(str.length());
            if (i == zzbd(str.length() * 3)) {
                int position = this.zzsw.position();
                if (this.zzsw.remaining() < i) {
                    throw new zzjm(position + i, this.zzsw.limit());
                }
                this.zzsw.position(position + i);
                zzd((CharSequence) str, this.zzsw);
                int position2 = this.zzsw.position();
                this.zzsw.position(position);
                zzbv((position2 - position) - i);
                this.zzsw.position(position2);
                return;
            }
            zzbv(zza(str));
            zzd((CharSequence) str, this.zzsw);
        } catch (BufferOverflowException e) {
            zzjm zzjm = new zzjm(this.zzsw.position(), this.zzsw.limit());
            zzjm.initCause(e);
            throw zzjm;
        }
    }

    public final void zza(int i, zzjt zzjt) throws IOException {
        zzd(i, 2);
        if (zzjt.zzadp < 0) {
            zzjt.zzeq();
        }
        zzbv(zzjt.zzadp);
        zzjt.zza(this);
    }

    public final void zze(int i, zzhf zzhf) throws IOException {
        if (this.zzade == null) {
            this.zzade = zzfe.zza(this.zzsw);
            this.zzadf = this.zzsw.position();
        } else if (this.zzadf != this.zzsw.position()) {
            this.zzade.write(this.zzsw.array(), this.zzadf, this.zzsw.position() - this.zzadf);
            this.zzadf = this.zzsw.position();
        }
        zzfe zzfe = this.zzade;
        zzfe.zza(2, zzhf);
        zzfe.flush();
        this.zzadf = this.zzsw.position();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008e A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006d  */
    private static int zza(java.lang.CharSequence r8) {
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
        if (r2 >= r0) goto L_0x006b;
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
        if (r2 >= r4) goto L_0x006a;
    L_0x002c:
        r6 = r8.charAt(r2);
        if (r6 >= r5) goto L_0x0038;
    L_0x0032:
        r6 = 127 - r6;
        r6 = r6 >>> 31;
        r1 = r1 + r6;
        goto L_0x0067;
    L_0x0038:
        r1 = r1 + 2;
        r7 = 55296; // 0xd800 float:7.7486E-41 double:2.732E-319;
        if (r7 > r6) goto L_0x0067;
    L_0x003f:
        r7 = 57343; // 0xdfff float:8.0355E-41 double:2.8331E-319;
        if (r6 > r7) goto L_0x0067;
    L_0x0044:
        r6 = java.lang.Character.codePointAt(r8, r2);
        r7 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        if (r6 >= r7) goto L_0x0065;
    L_0x004c:
        r8 = new java.lang.IllegalArgumentException;
        r0 = 39;
        r1 = new java.lang.StringBuilder;
        r1.<init>(r0);
        r0 = "Unpaired surrogate at index ";
        r1.append(r0);
        r1.append(r2);
        r0 = r1.toString();
        r8.<init>(r0);
        throw r8;
    L_0x0065:
        r2 = r2 + 1;
    L_0x0067:
        r2 = r2 + 1;
        goto L_0x002a;
    L_0x006a:
        r3 = r3 + r1;
    L_0x006b:
        if (r3 >= r0) goto L_0x008e;
    L_0x006d:
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
    L_0x008e:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjl.zza(java.lang.CharSequence):int");
    }

    private static void zzd(CharSequence charSequence, ByteBuffer byteBuffer) {
        CharSequence charSequence2 = charSequence;
        ByteBuffer byteBuffer2 = byteBuffer;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        char c = 2048;
        int i;
        char charAt;
        int i2;
        char charAt2;
        StringBuilder stringBuilder;
        if (byteBuffer.hasArray()) {
            try {
                int i3;
                byte[] array = byteBuffer.array();
                int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                int remaining = byteBuffer.remaining();
                int length = charSequence.length();
                remaining += arrayOffset;
                i = 0;
                while (i < length) {
                    i3 = i + arrayOffset;
                    if (i3 >= remaining) {
                        break;
                    }
                    char charAt3 = charSequence2.charAt(i);
                    if (charAt3 >= 128) {
                        break;
                    }
                    array[i3] = (byte) charAt3;
                    i++;
                }
                if (i == length) {
                    arrayOffset += length;
                } else {
                    arrayOffset += i;
                    while (i < length) {
                        int i4;
                        charAt = charSequence2.charAt(i);
                        if (charAt >= 128 || arrayOffset >= remaining) {
                            if (charAt < c && arrayOffset <= remaining - 2) {
                                i4 = arrayOffset + 1;
                                array[arrayOffset] = (byte) (960 | (charAt >>> 6));
                                i2 = i4 + 1;
                                array[i4] = (byte) ((charAt & 63) | 128);
                            } else if ((charAt < 55296 || 57343 < charAt) && arrayOffset <= remaining - 3) {
                                i4 = arrayOffset + 1;
                                array[arrayOffset] = (byte) (480 | (charAt >>> 12));
                                i2 = i4 + 1;
                                array[i4] = (byte) (((charAt >>> 6) & 63) | 128);
                                i4 = i2 + 1;
                                array[i2] = (byte) ((charAt & 63) | 128);
                            } else if (arrayOffset <= remaining - 4) {
                                i4 = i + 1;
                                if (i4 != charSequence.length()) {
                                    charAt2 = charSequence2.charAt(i4);
                                    if (Character.isSurrogatePair(charAt, charAt2)) {
                                        i = Character.toCodePoint(charAt, charAt2);
                                        i3 = arrayOffset + 1;
                                        array[arrayOffset] = (byte) (PsExtractor.VIDEO_STREAM_MASK | (i >>> 18));
                                        i2 = i3 + 1;
                                        array[i3] = (byte) (((i >>> 12) & 63) | 128);
                                        i3 = i2 + 1;
                                        array[i2] = (byte) (((i >>> 6) & 63) | 128);
                                        i2 = i3 + 1;
                                        array[i3] = (byte) ((i & 63) | 128);
                                        i = i4;
                                    } else {
                                        i = i4;
                                    }
                                }
                                i--;
                                stringBuilder = new StringBuilder(39);
                                stringBuilder.append("Unpaired surrogate at index ");
                                stringBuilder.append(i);
                                throw new IllegalArgumentException(stringBuilder.toString());
                            } else {
                                StringBuilder stringBuilder2 = new StringBuilder(37);
                                stringBuilder2.append("Failed writing ");
                                stringBuilder2.append(charAt);
                                stringBuilder2.append(" at index ");
                                stringBuilder2.append(arrayOffset);
                                throw new ArrayIndexOutOfBoundsException(stringBuilder2.toString());
                            }
                            arrayOffset = i2;
                            i++;
                            c = 2048;
                        } else {
                            i4 = arrayOffset + 1;
                            array[arrayOffset] = (byte) charAt;
                        }
                        arrayOffset = i4;
                        i++;
                        c = 2048;
                    }
                }
                byteBuffer2.position(arrayOffset - byteBuffer.arrayOffset());
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                Throwable th = e;
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(th);
                throw bufferOverflowException;
            }
        }
        int length2 = charSequence.length();
        i = 0;
        while (i < length2) {
            charAt = charSequence2.charAt(i);
            if (charAt < 128) {
                byteBuffer2.put((byte) charAt);
            } else if (charAt < 2048) {
                byteBuffer2.put((byte) ((charAt >>> 6) | 960));
                byteBuffer2.put((byte) ((charAt & 63) | 128));
            } else if (charAt < 55296 || 57343 < charAt) {
                byteBuffer2.put((byte) ((charAt >>> 12) | 480));
                byteBuffer2.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer2.put((byte) ((charAt & 63) | 128));
                i++;
            } else {
                i2 = i + 1;
                if (i2 != charSequence.length()) {
                    charAt2 = charSequence2.charAt(i2);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        i = Character.toCodePoint(charAt, charAt2);
                        byteBuffer2.put((byte) ((i >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                        byteBuffer2.put((byte) (((i >>> 12) & 63) | 128));
                        byteBuffer2.put((byte) (((i >>> 6) & 63) | 128));
                        byteBuffer2.put((byte) ((i & 63) | 128));
                        i = i2;
                        i++;
                    } else {
                        i = i2;
                    }
                }
                i--;
                stringBuilder = new StringBuilder(39);
                stringBuilder.append("Unpaired surrogate at index ");
                stringBuilder.append(i);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            i++;
        }
    }

    public static int zzd(int i, long j) {
        i = zzav(i);
        int i2 = (j & -128) == 0 ? 1 : (j & -16384) == 0 ? 2 : (j & -2097152) == 0 ? 3 : (j & -268435456) == 0 ? 4 : (j & -34359738368L) == 0 ? 5 : (j & -4398046511104L) == 0 ? 6 : (j & -562949953421312L) == 0 ? 7 : (j & -72057594037927936L) == 0 ? 8 : (j & Long.MIN_VALUE) == 0 ? 9 : 10;
        return i + i2;
    }

    public static int zzi(int i, int i2) {
        return zzav(i) + zzaw(i2);
    }

    public static int zzb(int i, String str) {
        return zzav(i) + zzn(str);
    }

    public static int zzb(int i, zzjt zzjt) {
        i = zzav(i);
        int zzeq = zzjt.zzeq();
        return i + (zzbd(zzeq) + zzeq);
    }

    public static int zzaw(int i) {
        return i >= 0 ? zzbd(i) : 10;
    }

    public static int zzn(String str) {
        int zza = zza(str);
        return zzbd(zza) + zza;
    }

    public final void zzea() {
        if (this.zzsw.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzsw.remaining())}));
        }
    }

    private final void zzbu(int i) throws IOException {
        byte b = (byte) i;
        if (this.zzsw.hasRemaining()) {
            this.zzsw.put(b);
            return;
        }
        throw new zzjm(this.zzsw.position(), this.zzsw.limit());
    }

    public final void zzl(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzsw.remaining() >= length) {
            this.zzsw.put(bArr, 0, length);
            return;
        }
        throw new zzjm(this.zzsw.position(), this.zzsw.limit());
    }

    private final void zzd(int i, int i2) throws IOException {
        zzbv((i << 3) | i2);
    }

    public static int zzav(int i) {
        return zzbd(i << 3);
    }

    public final void zzbv(int i) throws IOException {
        while ((i & -128) != 0) {
            zzbu((i & 127) | 128);
            i >>>= 7;
        }
        zzbu(i);
    }

    private final void zzq(long j) throws IOException {
        while ((j & -128) != 0) {
            zzbu((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzbu((int) j);
    }
}
