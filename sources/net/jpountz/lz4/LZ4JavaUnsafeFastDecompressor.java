package net.jpountz.lz4;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.UnsafeUtils;

final class LZ4JavaUnsafeFastDecompressor extends LZ4FastDecompressor {
    public static final LZ4FastDecompressor INSTANCE = new LZ4JavaUnsafeFastDecompressor();

    LZ4JavaUnsafeFastDecompressor() {
    }

    public int decompress(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        UnsafeUtils.checkRange(bArr, i);
        UnsafeUtils.checkRange(bArr2, i2, i3);
        if (i3 != 0) {
            i3 += i2;
            int i4 = i;
            int i5 = i2;
            while (true) {
                int i6;
                byte readByte;
                int readByte2 = UnsafeUtils.readByte(bArr, i4) & 255;
                i4++;
                int i7 = readByte2 >>> 4;
                if (i7 == 15) {
                    while (true) {
                        i6 = i4 + 1;
                        readByte = UnsafeUtils.readByte(bArr, i4);
                        if (readByte != (byte) -1) {
                            break;
                        }
                        i7 += 255;
                        i4 = i6;
                    }
                    i7 += readByte & 255;
                    i4 = i6;
                }
                i6 = i5 + i7;
                int i8 = i3 - 8;
                StringBuilder stringBuilder;
                if (i6 <= i8) {
                    LZ4UnsafeUtils.wildArraycopy(bArr, i4, bArr2, i5, i7);
                    i4 += i7;
                    i5 = UnsafeUtils.readShortLE(bArr, i4);
                    i4 += 2;
                    i5 = i6 - i5;
                    if (i5 < i2) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Malformed input at ");
                        stringBuilder.append(i4);
                        throw new LZ4Exception(stringBuilder.toString());
                    }
                    readByte2 &= 15;
                    if (readByte2 == 15) {
                        while (true) {
                            i7 = i4 + 1;
                            readByte = UnsafeUtils.readByte(bArr, i4);
                            if (readByte != (byte) -1) {
                                break;
                            }
                            readByte2 += 255;
                            i4 = i7;
                        }
                        readByte2 += readByte & 255;
                        i4 = i7;
                    }
                    readByte2 += 4;
                    i7 = i6 + readByte2;
                    if (i7 <= i8) {
                        LZ4UnsafeUtils.wildIncrementalCopy(bArr2, i5, i6, i7);
                    } else if (i7 > i3) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Malformed input at ");
                        stringBuilder.append(i4);
                        throw new LZ4Exception(stringBuilder.toString());
                    } else {
                        LZ4UnsafeUtils.safeIncrementalCopy(bArr2, i5, i6, readByte2);
                    }
                    i5 = i7;
                } else if (i6 != i3) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Malformed input at ");
                    stringBuilder.append(i4);
                    throw new LZ4Exception(stringBuilder.toString());
                } else {
                    LZ4UnsafeUtils.safeArraycopy(bArr, i4, bArr2, i5, i7);
                    return (i4 + i7) - i;
                }
            }
        } else if (UnsafeUtils.readByte(bArr, i) == (byte) 0) {
            return 1;
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Malformed input at ");
            stringBuilder2.append(i);
            throw new LZ4Exception(stringBuilder2.toString());
        }
    }

    public int decompress(ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2, int i3) {
        if (byteBuffer.hasArray() && byteBuffer2.hasArray()) {
            return decompress(byteBuffer.array(), i + byteBuffer.arrayOffset(), byteBuffer2.array(), i2 + byteBuffer2.arrayOffset(), i3);
        }
        byteBuffer = ByteBufferUtils.inNativeByteOrder(byteBuffer);
        byteBuffer2 = ByteBufferUtils.inNativeByteOrder(byteBuffer2);
        ByteBufferUtils.checkRange(byteBuffer, i);
        ByteBufferUtils.checkRange(byteBuffer2, i2, i3);
        if (i3 != 0) {
            i3 += i2;
            int i4 = i;
            int i5 = i2;
            while (true) {
                int i6;
                byte readByte;
                int readByte2 = ByteBufferUtils.readByte(byteBuffer, i4) & 255;
                i4++;
                int i7 = readByte2 >>> 4;
                if (i7 == 15) {
                    while (true) {
                        i6 = i4 + 1;
                        readByte = ByteBufferUtils.readByte(byteBuffer, i4);
                        if (readByte != (byte) -1) {
                            break;
                        }
                        i7 += 255;
                        i4 = i6;
                    }
                    i7 += readByte & 255;
                    i4 = i6;
                }
                i6 = i5 + i7;
                int i8 = i3 - 8;
                StringBuilder stringBuilder;
                if (i6 <= i8) {
                    LZ4ByteBufferUtils.wildArraycopy(byteBuffer, i4, byteBuffer2, i5, i7);
                    i4 += i7;
                    i5 = ByteBufferUtils.readShortLE(byteBuffer, i4);
                    i4 += 2;
                    i5 = i6 - i5;
                    if (i5 < i2) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Malformed input at ");
                        stringBuilder.append(i4);
                        throw new LZ4Exception(stringBuilder.toString());
                    }
                    readByte2 &= 15;
                    if (readByte2 == 15) {
                        while (true) {
                            i7 = i4 + 1;
                            readByte = ByteBufferUtils.readByte(byteBuffer, i4);
                            if (readByte != (byte) -1) {
                                break;
                            }
                            readByte2 += 255;
                            i4 = i7;
                        }
                        readByte2 += readByte & 255;
                        i4 = i7;
                    }
                    readByte2 += 4;
                    i7 = i6 + readByte2;
                    if (i7 <= i8) {
                        LZ4ByteBufferUtils.wildIncrementalCopy(byteBuffer2, i5, i6, i7);
                    } else if (i7 > i3) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Malformed input at ");
                        stringBuilder.append(i4);
                        throw new LZ4Exception(stringBuilder.toString());
                    } else {
                        LZ4ByteBufferUtils.safeIncrementalCopy(byteBuffer2, i5, i6, readByte2);
                    }
                    i5 = i7;
                } else if (i6 != i3) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Malformed input at ");
                    stringBuilder.append(i4);
                    throw new LZ4Exception(stringBuilder.toString());
                } else {
                    LZ4ByteBufferUtils.safeArraycopy(byteBuffer, i4, byteBuffer2, i5, i7);
                    return (i4 + i7) - i;
                }
            }
        } else if (ByteBufferUtils.readByte(byteBuffer, i) == (byte) 0) {
            return 1;
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Malformed input at ");
            stringBuilder2.append(i);
            throw new LZ4Exception(stringBuilder2.toString());
        }
    }
}
