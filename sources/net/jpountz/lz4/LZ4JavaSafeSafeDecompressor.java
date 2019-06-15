package net.jpountz.lz4;

import java.nio.ByteBuffer;
import net.jpountz.util.ByteBufferUtils;
import net.jpountz.util.SafeUtils;

final class LZ4JavaSafeSafeDecompressor extends LZ4SafeDecompressor {
    public static final LZ4SafeDecompressor INSTANCE = new LZ4JavaSafeSafeDecompressor();

    LZ4JavaSafeSafeDecompressor() {
    }

    public int decompress(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] bArr3 = bArr;
        int i5 = i2;
        byte[] bArr4 = bArr2;
        int i6 = i3;
        SafeUtils.checkRange(bArr, i, i2);
        SafeUtils.checkRange(bArr2, i3, i4);
        if (i4 != 0) {
            int i7;
            int i8;
            StringBuilder stringBuilder;
            i5 = i + i5;
            int i9 = i6 + i4;
            int i10 = i;
            int i11 = i6;
            while (true) {
                int readByte = SafeUtils.readByte(bArr3, i10) & 255;
                i10++;
                i7 = readByte >>> 4;
                if (i7 == 15) {
                    i8 = -1;
                    while (i10 < i5) {
                        i8 = i10 + 1;
                        byte readByte2 = SafeUtils.readByte(bArr3, i10);
                        int i12;
                        if (readByte2 != (byte) -1) {
                            i12 = i8;
                            i8 = readByte2;
                            i10 = i12;
                            break;
                        }
                        i7 += 255;
                        i12 = i8;
                        byte i82 = readByte2;
                        i10 = i12;
                    }
                    i7 += i82 & 255;
                }
                i82 = i11 + i7;
                int i13 = i9 - 8;
                if (i82 > i13) {
                    break;
                }
                int i14 = i10 + i7;
                if (i14 > i5 - 8) {
                    break;
                }
                LZ4SafeUtils.wildArraycopy(bArr3, i10, bArr4, i11, i7);
                i10 = SafeUtils.readShortLE(bArr3, i14);
                i14 += 2;
                i10 = i82 - i10;
                if (i10 < i6) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Malformed input at ");
                    stringBuilder.append(i14);
                    throw new LZ4Exception(stringBuilder.toString());
                }
                i11 = readByte & 15;
                if (i11 == 15) {
                    readByte = -1;
                    while (i14 < i5) {
                        readByte = i14 + 1;
                        byte readByte3 = SafeUtils.readByte(bArr3, i14);
                        if (readByte3 != (byte) -1) {
                            i14 = readByte;
                            readByte = readByte3;
                            break;
                        }
                        i11 += 255;
                        i14 = readByte;
                        byte b = readByte3;
                    }
                    i11 += readByte & 255;
                }
                i11 += 4;
                readByte = i82 + i11;
                if (readByte <= i13) {
                    LZ4SafeUtils.wildIncrementalCopy(bArr4, i10, i82, readByte);
                } else if (readByte > i9) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Malformed input at ");
                    stringBuilder.append(i14);
                    throw new LZ4Exception(stringBuilder.toString());
                } else {
                    LZ4SafeUtils.safeIncrementalCopy(bArr4, i10, i82, i11);
                }
                i11 = readByte;
                i10 = i14;
            }
            if (i82 > i9) {
                throw new LZ4Exception();
            } else if (i10 + i7 != i5) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Malformed input at ");
                stringBuilder.append(i10);
                throw new LZ4Exception(stringBuilder.toString());
            } else {
                LZ4SafeUtils.safeArraycopy(bArr3, i10, bArr4, i11, i7);
                return i82 - i6;
            }
        } else if (i5 == 1 && SafeUtils.readByte(bArr, i) == (byte) 0) {
            return 0;
        } else {
            throw new LZ4Exception("Output buffer too small");
        }
    }

    public int decompress(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3, int i4) {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        if (byteBuffer.hasArray() && byteBuffer2.hasArray()) {
            return decompress(byteBuffer.array(), byteBuffer.arrayOffset() + i5, i6, byteBuffer2.array(), i7 + byteBuffer2.arrayOffset(), i8);
        }
        ByteBuffer inNativeByteOrder = ByteBufferUtils.inNativeByteOrder(byteBuffer);
        ByteBuffer inNativeByteOrder2 = ByteBufferUtils.inNativeByteOrder(byteBuffer2);
        ByteBufferUtils.checkRange(inNativeByteOrder, i5, i6);
        ByteBufferUtils.checkRange(inNativeByteOrder2, i7, i8);
        if (i8 != 0) {
            int i9;
            int i10;
            i6 += i5;
            i8 += i7;
            int i11 = i7;
            while (true) {
                int readByte = ByteBufferUtils.readByte(inNativeByteOrder, i5) & 255;
                i5++;
                i9 = readByte >>> 4;
                if (i9 == 15) {
                    i10 = -1;
                    while (i5 < i6) {
                        i10 = i5 + 1;
                        byte readByte2 = ByteBufferUtils.readByte(inNativeByteOrder, i5);
                        int i12;
                        if (readByte2 != (byte) -1) {
                            i12 = i10;
                            i10 = readByte2;
                            i5 = i12;
                            break;
                        }
                        i9 += 255;
                        i12 = i10;
                        byte i102 = readByte2;
                        i5 = i12;
                    }
                    i9 += i102 & 255;
                }
                i102 = i11 + i9;
                int i13 = i8 - 8;
                if (i102 > i13) {
                    break;
                }
                int i14 = i5 + i9;
                if (i14 > i6 - 8) {
                    break;
                }
                LZ4ByteBufferUtils.wildArraycopy(inNativeByteOrder, i5, inNativeByteOrder2, i11, i9);
                i5 = ByteBufferUtils.readShortLE(inNativeByteOrder, i14);
                i14 += 2;
                i5 = i102 - i5;
                StringBuilder stringBuilder;
                if (i5 < i7) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Malformed input at ");
                    stringBuilder.append(i14);
                    throw new LZ4Exception(stringBuilder.toString());
                }
                i11 = readByte & 15;
                if (i11 == 15) {
                    readByte = -1;
                    while (i14 < i6) {
                        readByte = i14 + 1;
                        byte readByte3 = ByteBufferUtils.readByte(inNativeByteOrder, i14);
                        if (readByte3 != (byte) -1) {
                            i14 = readByte;
                            readByte = readByte3;
                            break;
                        }
                        i11 += 255;
                        i14 = readByte;
                        byte b = readByte3;
                    }
                    i11 += readByte & 255;
                }
                i11 += 4;
                readByte = i102 + i11;
                if (readByte <= i13) {
                    LZ4ByteBufferUtils.wildIncrementalCopy(inNativeByteOrder2, i5, i102, readByte);
                } else if (readByte > i8) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Malformed input at ");
                    stringBuilder.append(i14);
                    throw new LZ4Exception(stringBuilder.toString());
                } else {
                    LZ4ByteBufferUtils.safeIncrementalCopy(inNativeByteOrder2, i5, i102, i11);
                }
                i11 = readByte;
                i5 = i14;
            }
            if (i102 > i8) {
                throw new LZ4Exception();
            } else if (i5 + i9 != i6) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Malformed input at ");
                stringBuilder2.append(i5);
                throw new LZ4Exception(stringBuilder2.toString());
            } else {
                LZ4ByteBufferUtils.safeArraycopy(inNativeByteOrder, i5, inNativeByteOrder2, i11, i9);
                return i102 - i7;
            }
        } else if (i6 == 1 && ByteBufferUtils.readByte(inNativeByteOrder, i5) == (byte) 0) {
            return 0;
        } else {
            throw new LZ4Exception("Output buffer too small");
        }
    }
}
