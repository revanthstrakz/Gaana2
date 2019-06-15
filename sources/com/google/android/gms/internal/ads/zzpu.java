package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class zzpu {
    public static final byte[] zzbhi = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
    private static final float[] zzbhm = new float[]{1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object zzbhn = new Object();
    private static int[] zzbho = new int[10];

    public static int zzb(byte[] bArr, int i) {
        synchronized (zzbhn) {
            int i2;
            int i3 = 0;
            int i4 = i3;
            while (i3 < i) {
                while (i3 < i - 2) {
                    try {
                        if (bArr[i3] == (byte) 0 && bArr[i3 + 1] == (byte) 0 && bArr[i3 + 2] == (byte) 3) {
                            break;
                        }
                        i3++;
                    } finally {
                    }
                }
                i3 = i;
                if (i3 < i) {
                    if (zzbho.length <= i4) {
                        zzbho = Arrays.copyOf(zzbho, zzbho.length << 1);
                    }
                    i2 = i4 + 1;
                    zzbho[i4] = i3;
                    i3 += 3;
                    i4 = i2;
                }
            }
            i -= i4;
            i3 = 0;
            int i5 = i3;
            i2 = i5;
            while (i3 < i4) {
                int i6 = zzbho[i3] - i2;
                System.arraycopy(bArr, i2, bArr, i5, i6);
                i5 += i6;
                int i7 = i5 + 1;
                bArr[i5] = (byte) 0;
                i5 = i7 + 1;
                bArr[i7] = (byte) 0;
                i2 += i6 + 3;
                i3++;
            }
            System.arraycopy(bArr, i2, bArr, i5, i - i5);
        }
        return i;
    }

    public static void zzk(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int i = 0;
        int i2 = i;
        while (true) {
            int i3 = i + 1;
            if (i3 < position) {
                int i4 = byteBuffer.get(i) & 255;
                if (i2 == 3) {
                    if (i4 == 1 && (byteBuffer.get(i3) & 31) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(i - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (i4 == 0) {
                    i2++;
                }
                if (i4 != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static boolean zza(String str, byte b) {
        if ((MimeTypes.VIDEO_H264.equals(str) && (b & 31) == 6) || (MimeTypes.VIDEO_H265.equals(str) && ((b & 126) >> 1) == 39)) {
            return true;
        }
        return false;
    }

    public static zzpv zzd(byte[] bArr, int i, int i2) {
        boolean zzhj;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z;
        boolean z2;
        int i7;
        float f;
        zzpy zzpy = new zzpy(bArr, i, i2);
        zzpy.zzbn(8);
        int zzbj = zzpy.zzbj(8);
        zzpy.zzbn(16);
        int zzhk = zzpy.zzhk();
        int i8 = 1;
        if (zzbj == 100 || zzbj == 110 || zzbj == 122 || zzbj == 244 || zzbj == 44 || zzbj == 83 || zzbj == 86 || zzbj == 118 || zzbj == 128 || zzbj == TsExtractor.TS_STREAM_TYPE_DTS) {
            zzbj = zzpy.zzhk();
            zzhj = zzbj == 3 ? zzpy.zzhj() : false;
            zzpy.zzhk();
            zzpy.zzhk();
            zzpy.zzbn(1);
            if (zzpy.zzhj()) {
                int i9 = zzbj != 3 ? 8 : 12;
                i3 = 0;
                while (i3 < i9) {
                    if (zzpy.zzhj()) {
                        i4 = i3 < 6 ? 16 : 64;
                        i5 = 8;
                        int i10 = i5;
                        for (i6 = 0; i6 < i4; i6++) {
                            if (i5 != 0) {
                                i5 = ((zzpy.zzhl() + i10) + 256) % 256;
                            }
                            if (i5 != 0) {
                                i10 = i5;
                            }
                        }
                    }
                    i3++;
                }
            }
            z = zzhj;
        } else {
            z = false;
            zzbj = 1;
        }
        i4 = zzpy.zzhk() + 4;
        i6 = zzpy.zzhk();
        if (i6 == 0) {
            z2 = false;
            i5 = zzpy.zzhk() + 4;
        } else if (i6 == 1) {
            zzhj = zzpy.zzhj();
            zzpy.zzhl();
            zzpy.zzhl();
            long zzhk2 = (long) zzpy.zzhk();
            for (i3 = 0; ((long) i3) < zzhk2; i3++) {
                zzpy.zzhk();
            }
            i5 = 0;
            z2 = zzhj;
        } else {
            i5 = 0;
            z2 = i5;
        }
        zzpy.zzhk();
        zzpy.zzbn(1);
        int zzhk3 = zzpy.zzhk() + 1;
        int zzhk4 = zzpy.zzhk() + 1;
        boolean zzhj2 = zzpy.zzhj();
        int i11 = (2 - zzhj2) * zzhk4;
        if (!zzhj2) {
            zzpy.zzbn(1);
        }
        zzpy.zzbn(1);
        zzhk3 <<= 4;
        zzhk4 = i11 << 4;
        if (zzpy.zzhj()) {
            int i12;
            i11 = zzpy.zzhk();
            int zzhk5 = zzpy.zzhk();
            int zzhk6 = zzpy.zzhk();
            int zzhk7 = zzpy.zzhk();
            if (zzbj == 0) {
                i7 = 2 - zzhj2;
                i12 = 1;
            } else {
                i12 = zzbj == 3 ? 1 : 2;
                if (zzbj == 1) {
                    i8 = 2;
                }
                i7 = (2 - zzhj2) * i8;
            }
            zzhk3 -= (i11 + zzhk5) * i12;
            zzhk4 -= (zzhk6 + zzhk7) * i7;
        }
        i7 = zzhk3;
        i8 = zzhk4;
        float f2 = 1.0f;
        if (zzpy.zzhj() && zzpy.zzhj()) {
            int zzbj2 = zzpy.zzbj(8);
            if (zzbj2 == 255) {
                zzbj2 = zzpy.zzbj(16);
                int zzbj3 = zzpy.zzbj(16);
                if (!(zzbj2 == 0 || zzbj3 == 0)) {
                    f2 = ((float) zzbj2) / ((float) zzbj3);
                }
            } else if (zzbj2 < zzbhm.length) {
                f = zzbhm[zzbj2];
                return new zzpv(zzhk, i7, i8, f, z, zzhj2, i4, i6, i5, z2);
            } else {
                StringBuilder stringBuilder = new StringBuilder(46);
                stringBuilder.append("Unexpected aspect_ratio_idc value: ");
                stringBuilder.append(zzbj2);
                Log.w("NalUnitUtil", stringBuilder.toString());
            }
        }
        f = f2;
        return new zzpv(zzhk, i7, i8, f, z, zzhj2, i4, i6, i5, z2);
    }
}
