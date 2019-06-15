package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.nio.ByteBuffer;

final class zzbuf extends zzbue {
    zzbuf() {
    }

    /* Access modifiers changed, original: final */
    public final int zzb(int i, byte[] bArr, int i2, int i3) {
        while (i2 < i3 && bArr[i2] >= (byte) 0) {
            i2++;
        }
        if (i2 >= i3) {
            return 0;
        }
        while (i2 < i3) {
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b < (byte) 0) {
                byte b2;
                if (b < (byte) -32) {
                    if (i4 >= i3) {
                        return b;
                    }
                    if (b >= (byte) -62) {
                        i2 = i4 + 1;
                        if (bArr[i4] > (byte) -65) {
                        }
                    }
                    return -1;
                } else if (b < (byte) -16) {
                    if (i4 >= i3 - 1) {
                        return zzbuc.zzn(bArr, i4, i3);
                    }
                    int i5 = i4 + 1;
                    b2 = bArr[i4];
                    if (b2 <= (byte) -65 && ((b != (byte) -32 || b2 >= (byte) -96) && (b != (byte) -19 || b2 < (byte) -96))) {
                        i2 = i5 + 1;
                        if (bArr[i5] > (byte) -65) {
                        }
                    }
                    return -1;
                } else if (i4 >= i3 - 2) {
                    return zzbuc.zzn(bArr, i4, i3);
                } else {
                    int i6 = i4 + 1;
                    b2 = bArr[i4];
                    if (b2 <= (byte) -65 && (((b << 28) + (b2 + 112)) >> 30) == 0) {
                        i2 = i6 + 1;
                        if (bArr[i6] <= (byte) -65) {
                            i4 = i2 + 1;
                            if (bArr[i2] > (byte) -65) {
                            }
                        }
                    }
                    return -1;
                }
            }
            i2 = i4;
        }
        return 0;
    }

    /* Access modifiers changed, original: final */
    public final String zzo(byte[] bArr, int i, int i2) throws zzbrl {
        if (((i | i2) | ((bArr.length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        }
        int i3;
        int i4 = i + i2;
        char[] cArr = new char[i2];
        int i5 = 0;
        while (i < i4) {
            byte b = bArr[i];
            if (!zzbud.zze(b)) {
                break;
            }
            i++;
            i3 = i5 + 1;
            zzbud.zza(b, cArr, i5);
            i5 = i3;
        }
        int i6 = i5;
        while (i < i4) {
            i5 = i + 1;
            byte b2 = bArr[i];
            int i7;
            if (zzbud.zze(b2)) {
                i7 = i6 + 1;
                zzbud.zza(b2, cArr, i6);
                while (i5 < i4) {
                    b2 = bArr[i5];
                    if (!zzbud.zze(b2)) {
                        break;
                    }
                    i5++;
                    i3 = i7 + 1;
                    zzbud.zza(b2, cArr, i7);
                    i7 = i3;
                }
                i = i5;
                i6 = i7;
            } else if (zzbud.zzf(b2)) {
                if (i5 >= i4) {
                    throw zzbrl.zzank();
                }
                i7 = i5 + 1;
                i3 = i6 + 1;
                zzbud.zza(b2, bArr[i5], cArr, i6);
                i = i7;
                i6 = i3;
            } else if (zzbud.zzg(b2)) {
                if (i5 >= i4 - 1) {
                    throw zzbrl.zzank();
                }
                i7 = i5 + 1;
                i3 = i7 + 1;
                int i8 = i6 + 1;
                zzbud.zza(b2, bArr[i5], bArr[i7], cArr, i6);
                i = i3;
                i6 = i8;
            } else if (i5 >= i4 - 2) {
                throw zzbrl.zzank();
            } else {
                i7 = i5 + 1;
                byte b3 = bArr[i5];
                i5 = i7 + 1;
                int i9 = i5 + 1;
                int i10 = i6 + 1;
                zzbud.zza(b2, b3, bArr[i7], bArr[i5], cArr, i6);
                i = i9;
                i6 = i10 + 1;
            }
        }
        return new String(cArr, 0, i6);
    }

    /* Access modifiers changed, original: final */
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int length = charSequence.length();
        i2 += i;
        int i4 = 0;
        while (i4 < length) {
            i3 = i4 + i;
            if (i3 >= i2) {
                break;
            }
            char charAt = charSequence.charAt(i4);
            if (charAt >= 128) {
                break;
            }
            bArr[i3] = (byte) charAt;
            i4++;
        }
        if (i4 == length) {
            return i + length;
        }
        i += i4;
        while (i4 < length) {
            int i5;
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 < 128 && i < i2) {
                i5 = i + 1;
                bArr[i] = (byte) charAt2;
            } else if (charAt2 < 2048 && i <= i2 - 2) {
                i5 = i + 1;
                bArr[i] = (byte) (960 | (charAt2 >>> 6));
                i = i5 + 1;
                bArr[i5] = (byte) ((charAt2 & 63) | 128);
                i4++;
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i <= i2 - 3) {
                i5 = i + 1;
                bArr[i] = (byte) (480 | (charAt2 >>> 12));
                i = i5 + 1;
                bArr[i5] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i5 = i + 1;
                bArr[i] = (byte) ((charAt2 & 63) | 128);
            } else if (i <= i2 - 4) {
                i5 = i4 + 1;
                if (i5 != charSequence.length()) {
                    char charAt3 = charSequence.charAt(i5);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        i4 = Character.toCodePoint(charAt2, charAt3);
                        i3 = i + 1;
                        bArr[i] = (byte) (PsExtractor.VIDEO_STREAM_MASK | (i4 >>> 18));
                        i = i3 + 1;
                        bArr[i3] = (byte) (((i4 >>> 12) & 63) | 128);
                        i3 = i + 1;
                        bArr[i] = (byte) (((i4 >>> 6) & 63) | 128);
                        i = i3 + 1;
                        bArr[i3] = (byte) ((i4 & 63) | 128);
                        i4 = i5;
                        i4++;
                    } else {
                        i4 = i5;
                    }
                }
                throw new zzbug(i4 - 1, length);
            } else {
                if (55296 <= charAt2 && charAt2 <= 57343) {
                    int i6 = i4 + 1;
                    if (i6 == charSequence.length() || !Character.isSurrogatePair(charAt2, charSequence.charAt(i6))) {
                        throw new zzbug(i4, length);
                    }
                }
                StringBuilder stringBuilder = new StringBuilder(37);
                stringBuilder.append("Failed writing ");
                stringBuilder.append(charAt2);
                stringBuilder.append(" at index ");
                stringBuilder.append(i);
                throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
            }
            i = i5;
            i4++;
        }
        return i;
    }

    /* Access modifiers changed, original: final */
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        zzbue.zzc(charSequence, byteBuffer);
    }
}
