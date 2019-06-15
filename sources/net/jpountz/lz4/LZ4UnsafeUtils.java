package net.jpountz.lz4;

import android.support.v4.internal.view.SupportMenu;
import java.nio.ByteOrder;
import net.jpountz.util.UnsafeUtils;
import net.jpountz.util.Utils;

enum LZ4UnsafeUtils {
    ;

    static void safeArraycopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4 = i3 & -8;
        wildArraycopy(bArr, i, bArr2, i2, i4);
        i3 &= 7;
        for (int i5 = 0; i5 < i3; i5++) {
            UnsafeUtils.writeByte(bArr2, (i2 + i4) + i5, UnsafeUtils.readByte(bArr, (i + i4) + i5));
        }
    }

    static void wildArraycopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4 += 8) {
            UnsafeUtils.writeLong(bArr2, i2 + i4, UnsafeUtils.readLong(bArr, i + i4));
        }
    }

    static void wildIncrementalCopy(byte[] bArr, int i, int i2, int i3) {
        int i4 = i2 - i;
        if (i4 < 4) {
            i4 = 0;
            for (int i5 = 0; i5 < 4; i5++) {
                UnsafeUtils.writeByte(bArr, i2 + i5, UnsafeUtils.readByte(bArr, i + i5));
            }
            i2 += 4;
            i += 4;
            switch (i2 - i) {
                case 1:
                    i -= 3;
                    break;
                case 2:
                    i -= 2;
                    break;
                case 3:
                    i -= 3;
                    i4 = -1;
                    break;
                case 5:
                    i4 = 1;
                    break;
                case 6:
                    i4 = 2;
                    break;
                case 7:
                    i4 = 3;
                    break;
            }
            UnsafeUtils.writeInt(bArr, i2, UnsafeUtils.readInt(bArr, i));
            i2 += 4;
            i -= i4;
        } else if (i4 < 8) {
            UnsafeUtils.writeLong(bArr, i2, UnsafeUtils.readLong(bArr, i));
            i2 += i4;
        }
        while (i2 < i3) {
            UnsafeUtils.writeLong(bArr, i2, UnsafeUtils.readLong(bArr, i));
            i2 += 8;
            i += 8;
        }
    }

    static void safeIncrementalCopy(byte[] bArr, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i2 + i4;
            int i6 = i + i4;
            bArr[i5] = bArr[i6];
            UnsafeUtils.writeByte(bArr, i5, UnsafeUtils.readByte(bArr, i6));
        }
    }

    static int readShortLittleEndian(byte[] bArr, int i) {
        int readShort = UnsafeUtils.readShort(bArr, i);
        if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
            readShort = Short.reverseBytes(readShort);
        }
        return readShort & SupportMenu.USER_MASK;
    }

    static void writeShortLittleEndian(byte[] bArr, int i, int i2) {
        short s = (short) i2;
        if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
            s = Short.reverseBytes(s);
        }
        UnsafeUtils.writeShort(bArr, i, s);
    }

    static boolean readIntEquals(byte[] bArr, int i, int i2) {
        return UnsafeUtils.readInt(bArr, i) == UnsafeUtils.readInt(bArr, i2);
    }

    static int commonBytes(byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i2 <= i3 - 8) {
            if (UnsafeUtils.readLong(bArr, i2) == UnsafeUtils.readLong(bArr, i)) {
                i4 += 8;
                i += 8;
                i2 += 8;
            } else {
                int numberOfLeadingZeros;
                if (Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN) {
                    numberOfLeadingZeros = Long.numberOfLeadingZeros(UnsafeUtils.readLong(bArr, i2) ^ UnsafeUtils.readLong(bArr, i));
                } else {
                    numberOfLeadingZeros = Long.numberOfTrailingZeros(UnsafeUtils.readLong(bArr, i2) ^ UnsafeUtils.readLong(bArr, i));
                }
                return i4 + (numberOfLeadingZeros >>> 3);
            }
        }
        while (i2 < i3) {
            int i5 = i + 1;
            int i6 = i2 + 1;
            if (UnsafeUtils.readByte(bArr, i) != UnsafeUtils.readByte(bArr, i2)) {
                break;
            }
            i4++;
            i = i5;
            i2 = i6;
        }
        return i4;
    }

    static int writeLen(int i, byte[] bArr, int i2) {
        while (i >= 255) {
            int i3 = i2 + 1;
            UnsafeUtils.writeByte(bArr, i2, 255);
            i -= 255;
            i2 = i3;
        }
        int i4 = i2 + 1;
        UnsafeUtils.writeByte(bArr, i2, i);
        return i4;
    }

    static int encodeSequence(byte[] bArr, int i, int i2, int i3, int i4, byte[] bArr2, int i5, int i6) {
        int i7;
        int i8 = i2 - i;
        int i9 = i5 + 1;
        if (i8 >= 15) {
            i7 = -16;
            i9 = writeLen(i8 - 15, bArr2, i9);
        } else {
            i7 = i8 << 4;
        }
        wildArraycopy(bArr, i, bArr2, i9, i8);
        i9 += i8;
        i2 -= i3;
        int i10 = i9 + 1;
        bArr2[i9] = (byte) i2;
        i = i10 + 1;
        bArr2[i10] = (byte) (i2 >>> 8);
        i4 -= 4;
        if ((i + 6) + (i4 >>> 8) > i6) {
            throw new LZ4Exception("maxDestLen is too small");
        }
        if (i4 >= 15) {
            i10 = i7 | 15;
            i = writeLen(i4 - 15, bArr2, i);
        } else {
            i10 = i7 | i4;
        }
        bArr2[i5] = (byte) i10;
        return i;
    }

    static int commonBytesBackward(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = 0;
        while (i > i3 && i2 > i4) {
            i--;
            i2--;
            if (UnsafeUtils.readByte(bArr, i) != UnsafeUtils.readByte(bArr, i2)) {
                break;
            }
            i5++;
        }
        return i5;
    }

    static int lastLiterals(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        return LZ4SafeUtils.lastLiterals(bArr, i, i2, bArr2, i3, i4);
    }
}
