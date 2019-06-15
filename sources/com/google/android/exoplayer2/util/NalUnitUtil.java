package com.google.android.exoplayer2.util;

import java.nio.ByteBuffer;
import java.util.Arrays;

public final class NalUnitUtil {
    public static final float[] ASPECT_RATIO_IDC_VALUES = new float[]{1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    public static final int EXTENDED_SAR = 255;
    private static final int H264_NAL_UNIT_TYPE_SEI = 6;
    private static final int H264_NAL_UNIT_TYPE_SPS = 7;
    private static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final byte[] NAL_START_CODE = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
    private static final String TAG = "NalUnitUtil";
    private static int[] scratchEscapePositions = new int[10];
    private static final Object scratchEscapePositionsLock = new Object();

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i, int i2, boolean z) {
            this.picParameterSetId = i;
            this.seqParameterSetId = i2;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    public static final class SpsData {
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthAspectRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i, int i2, int i3, int i4, int i5, int i6, float f, boolean z, boolean z2, int i7, int i8, int i9, boolean z3) {
            this.profileIdc = i;
            this.constraintsFlagsAndReservedZero2Bits = i2;
            this.levelIdc = i3;
            this.seqParameterSetId = i4;
            this.width = i5;
            this.height = i6;
            this.pixelWidthAspectRatio = f;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = i7;
            this.picOrderCountType = i8;
            this.picOrderCntLsbLength = i9;
            this.deltaPicOrderAlwaysZeroFlag = z3;
        }
    }

    public static int unescapeStream(byte[] bArr, int i) {
        synchronized (scratchEscapePositionsLock) {
            int i2;
            int i3 = 0;
            int i4 = i3;
            while (i3 < i) {
                try {
                    i3 = findNextUnescapeIndex(bArr, i3, i);
                    if (i3 < i) {
                        if (scratchEscapePositions.length <= i4) {
                            scratchEscapePositions = Arrays.copyOf(scratchEscapePositions, scratchEscapePositions.length * 2);
                        }
                        i2 = i4 + 1;
                        scratchEscapePositions[i4] = i3;
                        i3 += 3;
                        i4 = i2;
                    }
                } catch (Throwable th) {
                }
            }
            i -= i4;
            i3 = 0;
            int i5 = i3;
            i2 = i5;
            while (i3 < i4) {
                int i6 = scratchEscapePositions[i3] - i2;
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

    public static void discardToSps(ByteBuffer byteBuffer) {
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

    public static boolean isNalUnitSei(String str, byte b) {
        if (MimeTypes.VIDEO_H264.equals(str) && (b & 31) == 6) {
            return true;
        }
        if (MimeTypes.VIDEO_H265.equals(str) && ((b & 126) >> 1) == 39) {
            return true;
        }
        return false;
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & 31;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0130  */
    public static com.google.android.exoplayer2.util.NalUnitUtil.SpsData parseSpsNalUnit(byte[] r21, int r22, int r23) {
        /*
        r0 = new com.google.android.exoplayer2.util.ParsableNalUnitBitArray;
        r1 = r21;
        r2 = r22;
        r3 = r23;
        r0.<init>(r1, r2, r3);
        r1 = 8;
        r0.skipBits(r1);
        r3 = r0.readBits(r1);
        r4 = r0.readBits(r1);
        r5 = r0.readBits(r1);
        r6 = r0.readUnsignedExpGolombCodedInt();
        r2 = 3;
        r9 = 1;
        r10 = 100;
        if (r3 == r10) goto L_0x004e;
    L_0x0026:
        r10 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        if (r3 == r10) goto L_0x004e;
    L_0x002a:
        r10 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        if (r3 == r10) goto L_0x004e;
    L_0x002e:
        r10 = 244; // 0xf4 float:3.42E-43 double:1.206E-321;
        if (r3 == r10) goto L_0x004e;
    L_0x0032:
        r10 = 44;
        if (r3 == r10) goto L_0x004e;
    L_0x0036:
        r10 = 83;
        if (r3 == r10) goto L_0x004e;
    L_0x003a:
        r10 = 86;
        if (r3 == r10) goto L_0x004e;
    L_0x003e:
        r10 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        if (r3 == r10) goto L_0x004e;
    L_0x0042:
        r10 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        if (r3 == r10) goto L_0x004e;
    L_0x0046:
        r10 = 138; // 0x8a float:1.93E-43 double:6.8E-322;
        if (r3 != r10) goto L_0x004b;
    L_0x004a:
        goto L_0x004e;
    L_0x004b:
        r10 = r9;
        r11 = 0;
        goto L_0x0086;
    L_0x004e:
        r10 = r0.readUnsignedExpGolombCodedInt();
        if (r10 != r2) goto L_0x0059;
    L_0x0054:
        r11 = r0.readBit();
        goto L_0x005a;
    L_0x0059:
        r11 = 0;
    L_0x005a:
        r0.readUnsignedExpGolombCodedInt();
        r0.readUnsignedExpGolombCodedInt();
        r0.skipBit();
        r12 = r0.readBit();
        if (r12 == 0) goto L_0x0086;
    L_0x0069:
        if (r10 == r2) goto L_0x006d;
    L_0x006b:
        r12 = r1;
        goto L_0x006f;
    L_0x006d:
        r12 = 12;
    L_0x006f:
        r13 = 0;
    L_0x0070:
        if (r13 >= r12) goto L_0x0086;
    L_0x0072:
        r14 = r0.readBit();
        if (r14 == 0) goto L_0x0083;
    L_0x0078:
        r14 = 6;
        if (r13 >= r14) goto L_0x007e;
    L_0x007b:
        r14 = 16;
        goto L_0x0080;
    L_0x007e:
        r14 = 64;
    L_0x0080:
        skipScalingList(r0, r14);
    L_0x0083:
        r13 = r13 + 1;
        goto L_0x0070;
    L_0x0086:
        r12 = r0.readUnsignedExpGolombCodedInt();
        r12 = r12 + 4;
        r13 = r0.readUnsignedExpGolombCodedInt();
        if (r13 != 0) goto L_0x009a;
    L_0x0092:
        r14 = r0.readUnsignedExpGolombCodedInt();
        r14 = r14 + 4;
    L_0x0098:
        r15 = 0;
        goto L_0x00bc;
    L_0x009a:
        if (r13 != r9) goto L_0x00ba;
    L_0x009c:
        r14 = r0.readBit();
        r0.readSignedExpGolombCodedInt();
        r0.readSignedExpGolombCodedInt();
        r15 = r0.readUnsignedExpGolombCodedInt();
        r1 = (long) r15;
        r15 = 0;
    L_0x00ac:
        r7 = (long) r15;
        r17 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1));
        if (r17 >= 0) goto L_0x00b7;
    L_0x00b1:
        r0.readUnsignedExpGolombCodedInt();
        r15 = r15 + 1;
        goto L_0x00ac;
    L_0x00b7:
        r15 = r14;
        r14 = 0;
        goto L_0x00bc;
    L_0x00ba:
        r14 = 0;
        goto L_0x0098;
    L_0x00bc:
        r0.readUnsignedExpGolombCodedInt();
        r0.skipBit();
        r1 = r0.readUnsignedExpGolombCodedInt();
        r1 = r1 + r9;
        r2 = r0.readUnsignedExpGolombCodedInt();
        r2 = r2 + r9;
        r16 = r0.readBit();
        r7 = 2;
        r8 = 2 - r16;
        r8 = r8 * r2;
        if (r16 != 0) goto L_0x00d9;
    L_0x00d6:
        r0.skipBit();
    L_0x00d9:
        r0.skipBit();
        r2 = 16;
        r1 = r1 * r2;
        r8 = r8 * r2;
        r2 = r0.readBit();
        if (r2 == 0) goto L_0x0117;
    L_0x00e6:
        r2 = r0.readUnsignedExpGolombCodedInt();
        r17 = r0.readUnsignedExpGolombCodedInt();
        r18 = r0.readUnsignedExpGolombCodedInt();
        r19 = r0.readUnsignedExpGolombCodedInt();
        if (r10 != 0) goto L_0x0100;
    L_0x00f8:
        r7 = 2 - r16;
        r20 = r9;
        r9 = r7;
        r7 = r20;
        goto L_0x010d;
    L_0x0100:
        r7 = 3;
        if (r10 != r7) goto L_0x0105;
    L_0x0103:
        r7 = r9;
        goto L_0x0106;
    L_0x0105:
        r7 = 2;
    L_0x0106:
        if (r10 != r9) goto L_0x0109;
    L_0x0108:
        r9 = 2;
    L_0x0109:
        r10 = 2;
        r10 = 2 - r16;
        r9 = r9 * r10;
    L_0x010d:
        r2 = r2 + r17;
        r2 = r2 * r7;
        r1 = r1 - r2;
        r18 = r18 + r19;
        r18 = r18 * r9;
        r8 = r8 - r18;
    L_0x0117:
        r7 = r1;
        r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r2 = r0.readBit();
        if (r2 == 0) goto L_0x0163;
    L_0x0120:
        r2 = r0.readBit();
        if (r2 == 0) goto L_0x0163;
    L_0x0126:
        r2 = 8;
        r2 = r0.readBits(r2);
        r9 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r2 != r9) goto L_0x0142;
    L_0x0130:
        r9 = 16;
        r2 = r0.readBits(r9);
        r0 = r0.readBits(r9);
        if (r2 == 0) goto L_0x0163;
    L_0x013c:
        if (r0 == 0) goto L_0x0163;
    L_0x013e:
        r1 = (float) r2;
        r0 = (float) r0;
        r1 = r1 / r0;
        goto L_0x0163;
    L_0x0142:
        r0 = ASPECT_RATIO_IDC_VALUES;
        r0 = r0.length;
        if (r2 >= r0) goto L_0x014d;
    L_0x0147:
        r0 = ASPECT_RATIO_IDC_VALUES;
        r0 = r0[r2];
        r9 = r0;
        goto L_0x0164;
    L_0x014d:
        r0 = "NalUnitUtil";
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "Unexpected aspect_ratio_idc value: ";
        r9.append(r10);
        r9.append(r2);
        r2 = r9.toString();
        com.google.android.exoplayer2.util.Log.w(r0, r2);
    L_0x0163:
        r9 = r1;
    L_0x0164:
        r0 = new com.google.android.exoplayer2.util.NalUnitUtil$SpsData;
        r2 = r0;
        r10 = r11;
        r11 = r16;
        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.NalUnitUtil.parseSpsNalUnit(byte[], int, int):com.google.android.exoplayer2.util.NalUnitUtil$SpsData");
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i, int i2) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        parsableNalUnitBitArray.skipBits(8);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        i = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(readUnsignedExpGolombCodedInt, i, parsableNalUnitBitArray.readBit());
    }

    /* JADX WARNING: Removed duplicated region for block: B:79:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00ba  */
    public static int findNalUnit(byte[] r7, int r8, int r9, boolean[] r10) {
        /*
        r0 = r9 - r8;
        r1 = 0;
        r2 = 1;
        if (r0 < 0) goto L_0x0008;
    L_0x0006:
        r3 = r2;
        goto L_0x0009;
    L_0x0008:
        r3 = r1;
    L_0x0009:
        com.google.android.exoplayer2.util.Assertions.checkState(r3);
        if (r0 != 0) goto L_0x000f;
    L_0x000e:
        return r9;
    L_0x000f:
        r3 = 2;
        if (r10 == 0) goto L_0x0040;
    L_0x0012:
        r4 = r10[r1];
        if (r4 == 0) goto L_0x001c;
    L_0x0016:
        clearPrefixFlags(r10);
        r8 = r8 + -3;
        return r8;
    L_0x001c:
        if (r0 <= r2) goto L_0x002b;
    L_0x001e:
        r4 = r10[r2];
        if (r4 == 0) goto L_0x002b;
    L_0x0022:
        r4 = r7[r8];
        if (r4 != r2) goto L_0x002b;
    L_0x0026:
        clearPrefixFlags(r10);
        r8 = r8 - r3;
        return r8;
    L_0x002b:
        if (r0 <= r3) goto L_0x0040;
    L_0x002d:
        r4 = r10[r3];
        if (r4 == 0) goto L_0x0040;
    L_0x0031:
        r4 = r7[r8];
        if (r4 != 0) goto L_0x0040;
    L_0x0035:
        r4 = r8 + 1;
        r4 = r7[r4];
        if (r4 != r2) goto L_0x0040;
    L_0x003b:
        clearPrefixFlags(r10);
        r8 = r8 - r2;
        return r8;
    L_0x0040:
        r4 = r9 + -1;
        r8 = r8 + r3;
    L_0x0043:
        if (r8 >= r4) goto L_0x0067;
    L_0x0045:
        r5 = r7[r8];
        r5 = r5 & 254;
        if (r5 == 0) goto L_0x004c;
    L_0x004b:
        goto L_0x0064;
    L_0x004c:
        r5 = r8 + -2;
        r6 = r7[r5];
        if (r6 != 0) goto L_0x0062;
    L_0x0052:
        r6 = r8 + -1;
        r6 = r7[r6];
        if (r6 != 0) goto L_0x0062;
    L_0x0058:
        r6 = r7[r8];
        if (r6 != r2) goto L_0x0062;
    L_0x005c:
        if (r10 == 0) goto L_0x0061;
    L_0x005e:
        clearPrefixFlags(r10);
    L_0x0061:
        return r5;
    L_0x0062:
        r8 = r8 + -2;
    L_0x0064:
        r8 = r8 + 3;
        goto L_0x0043;
    L_0x0067:
        if (r10 == 0) goto L_0x00bd;
    L_0x0069:
        if (r0 <= r3) goto L_0x007f;
    L_0x006b:
        r8 = r9 + -3;
        r8 = r7[r8];
        if (r8 != 0) goto L_0x007d;
    L_0x0071:
        r8 = r9 + -2;
        r8 = r7[r8];
        if (r8 != 0) goto L_0x007d;
    L_0x0077:
        r8 = r7[r4];
        if (r8 != r2) goto L_0x007d;
    L_0x007b:
        r8 = r2;
        goto L_0x0099;
    L_0x007d:
        r8 = r1;
        goto L_0x0099;
    L_0x007f:
        if (r0 != r3) goto L_0x0090;
    L_0x0081:
        r8 = r10[r3];
        if (r8 == 0) goto L_0x007d;
    L_0x0085:
        r8 = r9 + -2;
        r8 = r7[r8];
        if (r8 != 0) goto L_0x007d;
    L_0x008b:
        r8 = r7[r4];
        if (r8 != r2) goto L_0x007d;
    L_0x008f:
        goto L_0x007b;
    L_0x0090:
        r8 = r10[r2];
        if (r8 == 0) goto L_0x007d;
    L_0x0094:
        r8 = r7[r4];
        if (r8 != r2) goto L_0x007d;
    L_0x0098:
        goto L_0x007b;
    L_0x0099:
        r10[r1] = r8;
        if (r0 <= r2) goto L_0x00ab;
    L_0x009d:
        r8 = r9 + -2;
        r8 = r7[r8];
        if (r8 != 0) goto L_0x00a9;
    L_0x00a3:
        r8 = r7[r4];
        if (r8 != 0) goto L_0x00a9;
    L_0x00a7:
        r8 = r2;
        goto L_0x00b4;
    L_0x00a9:
        r8 = r1;
        goto L_0x00b4;
    L_0x00ab:
        r8 = r10[r3];
        if (r8 == 0) goto L_0x00a9;
    L_0x00af:
        r8 = r7[r4];
        if (r8 != 0) goto L_0x00a9;
    L_0x00b3:
        goto L_0x00a7;
    L_0x00b4:
        r10[r2] = r8;
        r7 = r7[r4];
        if (r7 != 0) goto L_0x00bb;
    L_0x00ba:
        r1 = r2;
    L_0x00bb:
        r10[r3] = r1;
    L_0x00bd:
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.NalUnitUtil.findNalUnit(byte[], int, int, boolean[]):int");
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == (byte) 0 && bArr[i + 1] == (byte) 0 && bArr[i + 2] == (byte) 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int i2 = 8;
        int i3 = 8;
        for (int i4 = 0; i4 < i; i4++) {
            if (i2 != 0) {
                i2 = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i3) + 256) % 256;
            }
            if (i2 != 0) {
                i3 = i2;
            }
        }
    }

    private NalUnitUtil() {
    }
}
