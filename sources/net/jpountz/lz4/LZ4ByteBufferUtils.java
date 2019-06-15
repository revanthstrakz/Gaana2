package net.jpountz.lz4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import net.jpountz.util.ByteBufferUtils;

enum LZ4ByteBufferUtils {
    ;

    static class Match {
        int len;
        int ref;
        int start;

        Match() {
        }

        /* Access modifiers changed, original: 0000 */
        public void fix(int i) {
            this.start += i;
            this.ref += i;
            this.len -= i;
        }

        /* Access modifiers changed, original: 0000 */
        public int end() {
            return this.start + this.len;
        }
    }

    static int hash(ByteBuffer byteBuffer, int i) {
        return LZ4Utils.hash(ByteBufferUtils.readInt(byteBuffer, i));
    }

    static int hash64k(ByteBuffer byteBuffer, int i) {
        return LZ4Utils.hash64k(ByteBufferUtils.readInt(byteBuffer, i));
    }

    static boolean readIntEquals(ByteBuffer byteBuffer, int i, int i2) {
        return byteBuffer.getInt(i) == byteBuffer.getInt(i2);
    }

    static void safeIncrementalCopy(ByteBuffer byteBuffer, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            byteBuffer.put(i2 + i4, byteBuffer.get(i + i4));
        }
    }

    static void wildIncrementalCopy(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int i4 = i2 - i;
        if (i4 < 4) {
            i4 = 0;
            for (int i5 = 0; i5 < 4; i5++) {
                ByteBufferUtils.writeByte(byteBuffer, i2 + i5, ByteBufferUtils.readByte(byteBuffer, i + i5));
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
            ByteBufferUtils.writeInt(byteBuffer, i2, ByteBufferUtils.readInt(byteBuffer, i));
            i2 += 4;
            i -= i4;
        } else if (i4 < 8) {
            ByteBufferUtils.writeLong(byteBuffer, i2, ByteBufferUtils.readLong(byteBuffer, i));
            i2 += i4;
        }
        while (i2 < i3) {
            ByteBufferUtils.writeLong(byteBuffer, i2, ByteBufferUtils.readLong(byteBuffer, i));
            i2 += 8;
            i += 8;
        }
    }

    static int commonBytes(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int i4 = 0;
        while (i2 <= i3 - 8) {
            if (ByteBufferUtils.readLong(byteBuffer, i2) == ByteBufferUtils.readLong(byteBuffer, i)) {
                i4 += 8;
                i += 8;
                i2 += 8;
            } else {
                int numberOfLeadingZeros;
                if (byteBuffer.order() == ByteOrder.BIG_ENDIAN) {
                    numberOfLeadingZeros = Long.numberOfLeadingZeros(ByteBufferUtils.readLong(byteBuffer, i2) ^ ByteBufferUtils.readLong(byteBuffer, i));
                } else {
                    numberOfLeadingZeros = Long.numberOfTrailingZeros(ByteBufferUtils.readLong(byteBuffer, i2) ^ ByteBufferUtils.readLong(byteBuffer, i));
                }
                return i4 + (numberOfLeadingZeros >>> 3);
            }
        }
        while (i2 < i3) {
            int i5 = i + 1;
            int i6 = i2 + 1;
            if (ByteBufferUtils.readByte(byteBuffer, i) != ByteBufferUtils.readByte(byteBuffer, i2)) {
                break;
            }
            i4++;
            i = i5;
            i2 = i6;
        }
        return i4;
    }

    static int commonBytesBackward(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        int i5 = 0;
        while (i > i3 && i2 > i4) {
            i--;
            i2--;
            if (byteBuffer.get(i) != byteBuffer.get(i2)) {
                break;
            }
            i5++;
        }
        return i5;
    }

    static void safeArraycopy(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            byteBuffer2.put(i2 + i4, byteBuffer.get(i + i4));
        }
    }

    static void wildArraycopy(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            try {
                byteBuffer2.putLong(i2 + i4, byteBuffer.getLong(i + i4));
                i4 += 8;
            } catch (IndexOutOfBoundsException unused) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Malformed input at offset ");
                stringBuilder.append(i);
                throw new LZ4Exception(stringBuilder.toString());
            }
        }
    }

    static int encodeSequence(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, ByteBuffer byteBuffer2, int i5, int i6) {
        int i7 = i2 - i;
        int i8 = i5 + 1;
        if (((i8 + i7) + 8) + (i7 >>> 8) > i6) {
            throw new LZ4Exception("maxDestLen is too small");
        }
        int i9;
        if (i7 >= 15) {
            i9 = -16;
            i8 = writeLen(i7 - 15, byteBuffer2, i8);
        } else {
            i9 = i7 << 4;
        }
        wildArraycopy(byteBuffer, i, byteBuffer2, i8, i7);
        i8 += i7;
        i2 -= i3;
        int i10 = i8 + 1;
        byteBuffer2.put(i8, (byte) i2);
        i = i10 + 1;
        byteBuffer2.put(i10, (byte) (i2 >>> 8));
        i4 -= 4;
        if ((i + 6) + (i4 >>> 8) > i6) {
            throw new LZ4Exception("maxDestLen is too small");
        }
        if (i4 >= 15) {
            i10 = i9 | 15;
            i = writeLen(i4 - 15, byteBuffer2, i);
        } else {
            i10 = i9 | i4;
        }
        byteBuffer2.put(i5, (byte) i10);
        return i;
    }

    static int lastLiterals(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3, int i4) {
        if (((i3 + i2) + 1) + (((i2 + 255) - 15) / 255) > i4) {
            throw new LZ4Exception();
        }
        if (i2 >= 15) {
            i4 = i3 + 1;
            byteBuffer2.put(i3, (byte) -16);
            i3 = writeLen(i2 - 15, byteBuffer2, i4);
        } else {
            i4 = i3 + 1;
            byteBuffer2.put(i3, (byte) (i2 << 4));
            i3 = i4;
        }
        safeArraycopy(byteBuffer, i, byteBuffer2, i3, i2);
        return i3 + i2;
    }

    static int writeLen(int i, ByteBuffer byteBuffer, int i2) {
        int i3;
        while (i >= 255) {
            i3 = i2 + 1;
            byteBuffer.put(i2, (byte) -1);
            i -= 255;
            i2 = i3;
        }
        i3 = i2 + 1;
        byteBuffer.put(i2, (byte) i);
        return i3;
    }

    static void copyTo(Match match, Match match2) {
        match2.len = match.len;
        match2.start = match.start;
        match2.ref = match.ref;
    }
}
