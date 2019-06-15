package com.google.android.exoplayer2.metadata.id3;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class Id3Decoder implements MetadataDecoder {
    private static final int FRAME_FLAG_V3_HAS_GROUP_IDENTIFIER = 32;
    private static final int FRAME_FLAG_V3_IS_COMPRESSED = 128;
    private static final int FRAME_FLAG_V3_IS_ENCRYPTED = 64;
    private static final int FRAME_FLAG_V4_HAS_DATA_LENGTH = 1;
    private static final int FRAME_FLAG_V4_HAS_GROUP_IDENTIFIER = 64;
    private static final int FRAME_FLAG_V4_IS_COMPRESSED = 8;
    private static final int FRAME_FLAG_V4_IS_ENCRYPTED = 4;
    private static final int FRAME_FLAG_V4_IS_UNSYNCHRONIZED = 2;
    public static final int ID3_HEADER_LENGTH = 10;
    public static final int ID3_TAG = Util.getIntegerCodeForString("ID3");
    private static final int ID3_TEXT_ENCODING_ISO_8859_1 = 0;
    private static final int ID3_TEXT_ENCODING_UTF_16 = 1;
    private static final int ID3_TEXT_ENCODING_UTF_16BE = 2;
    private static final int ID3_TEXT_ENCODING_UTF_8 = 3;
    public static final FramePredicate NO_FRAMES_PREDICATE = Id3Decoder$$Lambda$0.$instance;
    private static final String TAG = "Id3Decoder";
    @Nullable
    private final FramePredicate framePredicate;

    public interface FramePredicate {
        boolean evaluate(int i, int i2, int i3, int i4, int i5);
    }

    private static final class Id3Header {
        private final int framesSize;
        private final boolean isUnsynchronized;
        private final int majorVersion;

        public Id3Header(int i, boolean z, int i2) {
            this.majorVersion = i;
            this.isUnsynchronized = z;
            this.framesSize = i2;
        }
    }

    private static int delimiterLength(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    private static String getCharsetName(int i) {
        switch (i) {
            case 1:
                return C.UTF16_NAME;
            case 2:
                return "UTF-16BE";
            case 3:
                return "UTF-8";
            default:
                return "ISO-8859-1";
        }
    }

    public Id3Decoder() {
        this(null);
    }

    public Id3Decoder(@Nullable FramePredicate framePredicate) {
        this.framePredicate = framePredicate;
    }

    @Nullable
    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        return decode(byteBuffer.array(), byteBuffer.limit());
    }

    @Nullable
    public Metadata decode(byte[] bArr, int i) {
        List arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        Id3Header decodeHeader = decodeHeader(parsableByteArray);
        if (decodeHeader == null) {
            return null;
        }
        int position = parsableByteArray.getPosition();
        int i2 = decodeHeader.majorVersion == 2 ? 6 : 10;
        int access$100 = decodeHeader.framesSize;
        if (decodeHeader.isUnsynchronized) {
            access$100 = removeUnsynchronization(parsableByteArray, decodeHeader.framesSize);
        }
        parsableByteArray.setLimit(position + access$100);
        boolean z = false;
        if (!validateFrames(parsableByteArray, decodeHeader.majorVersion, i2, false)) {
            if (decodeHeader.majorVersion == 4 && validateFrames(parsableByteArray, 4, i2, true)) {
                z = true;
            } else {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to validate ID3 tag with majorVersion=");
                stringBuilder.append(decodeHeader.majorVersion);
                Log.w(str, stringBuilder.toString());
                return null;
            }
        }
        while (parsableByteArray.bytesLeft() >= i2) {
            Id3Frame decodeFrame = decodeFrame(decodeHeader.majorVersion, parsableByteArray, z, i2, this.framePredicate);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new Metadata(arrayList);
    }

    @Nullable
    private static Id3Header decodeHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 10) {
            Log.w(TAG, "Data too short to be an ID3 tag");
            return null;
        }
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        String str;
        StringBuilder stringBuilder;
        if (readUnsignedInt24 != ID3_TAG) {
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected first three bytes of ID3 tag header: ");
            stringBuilder.append(readUnsignedInt24);
            Log.w(str, stringBuilder.toString());
            return null;
        }
        readUnsignedInt24 = parsableByteArray.readUnsignedByte();
        boolean z = true;
        parsableByteArray.skipBytes(1);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readSynchSafeInt = parsableByteArray.readSynchSafeInt();
        int readInt;
        if (readUnsignedInt24 == 2) {
            if ((readUnsignedByte & 64) != 0) {
                Log.w(TAG, "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (readUnsignedInt24 == 3) {
            if ((readUnsignedByte & 64) != 0) {
                readInt = parsableByteArray.readInt();
                parsableByteArray.skipBytes(readInt);
                readSynchSafeInt -= readInt + 4;
            }
        } else if (readUnsignedInt24 == 4) {
            if ((readUnsignedByte & 64) != 0) {
                readInt = parsableByteArray.readSynchSafeInt();
                parsableByteArray.skipBytes(readInt - 4);
                readSynchSafeInt -= readInt;
            }
            if ((readUnsignedByte & 16) != 0) {
                readSynchSafeInt -= 10;
            }
        } else {
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Skipped ID3 tag with unsupported majorVersion=");
            stringBuilder.append(readUnsignedInt24);
            Log.w(str, stringBuilder.toString());
            return null;
        }
        if (readUnsignedInt24 >= 4 || (readUnsignedByte & 128) == 0) {
            z = false;
        }
        return new Id3Header(readUnsignedInt24, z, readSynchSafeInt);
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a7 A:{SYNTHETIC, Splitter:B:50:0x00a7} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00a3 A:{SYNTHETIC} */
    /* JADX WARNING: Missing block: B:31:0x0080, code skipped:
            if ((r11 & 1) != 0) goto L_0x0082;
     */
    /* JADX WARNING: Missing block: B:33:0x0084, code skipped:
            r8 = false;
     */
    /* JADX WARNING: Missing block: B:40:0x0091, code skipped:
            if ((r11 & 128) != 0) goto L_0x0082;
     */
    private static boolean validateFrames(com.google.android.exoplayer2.util.ParsableByteArray r20, int r21, int r22, boolean r23) {
        /*
        r1 = r20;
        r2 = r21;
        r3 = r20.getPosition();
    L_0x0008:
        r4 = r20.bytesLeft();	 Catch:{ all -> 0x00be }
        r5 = 1;
        r6 = r22;
        if (r4 < r6) goto L_0x00ba;
    L_0x0011:
        r4 = 3;
        r7 = 0;
        if (r2 < r4) goto L_0x0022;
    L_0x0015:
        r8 = r20.readInt();	 Catch:{ all -> 0x00be }
        r9 = r20.readUnsignedInt();	 Catch:{ all -> 0x00be }
        r11 = r20.readUnsignedShort();	 Catch:{ all -> 0x00be }
        goto L_0x002c;
    L_0x0022:
        r8 = r20.readUnsignedInt24();	 Catch:{ all -> 0x00be }
        r9 = r20.readUnsignedInt24();	 Catch:{ all -> 0x00be }
        r9 = (long) r9;
        r11 = r7;
    L_0x002c:
        r12 = 0;
        if (r8 != 0) goto L_0x003a;
    L_0x0030:
        r8 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1));
        if (r8 != 0) goto L_0x003a;
    L_0x0034:
        if (r11 != 0) goto L_0x003a;
    L_0x0036:
        r1.setPosition(r3);
        return r5;
    L_0x003a:
        r8 = 4;
        if (r2 != r8) goto L_0x0074;
    L_0x003d:
        if (r23 != 0) goto L_0x0074;
    L_0x003f:
        r14 = 8421504; // 0x808080 float:1.180104E-38 double:4.160776E-317;
        r16 = r9 & r14;
        r14 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1));
        if (r14 == 0) goto L_0x004c;
    L_0x0048:
        r1.setPosition(r3);
        return r7;
    L_0x004c:
        r12 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r14 = r9 & r12;
        r16 = 8;
        r16 = r9 >> r16;
        r18 = r16 & r12;
        r16 = 7;
        r16 = r18 << r16;
        r18 = r14 | r16;
        r14 = 16;
        r14 = r9 >> r14;
        r16 = r14 & r12;
        r14 = 14;
        r14 = r16 << r14;
        r16 = r18 | r14;
        r14 = 24;
        r9 = r9 >> r14;
        r14 = r9 & r12;
        r9 = 21;
        r9 = r14 << r9;
        r12 = r16 | r9;
        goto L_0x0075;
    L_0x0074:
        r12 = r9;
    L_0x0075:
        if (r2 != r8) goto L_0x0086;
    L_0x0077:
        r4 = r11 & 64;
        if (r4 == 0) goto L_0x007d;
    L_0x007b:
        r4 = r5;
        goto L_0x007e;
    L_0x007d:
        r4 = r7;
    L_0x007e:
        r8 = r11 & 1;
        if (r8 == 0) goto L_0x0084;
    L_0x0082:
        r8 = r5;
        goto L_0x0096;
    L_0x0084:
        r8 = r7;
        goto L_0x0096;
    L_0x0086:
        if (r2 != r4) goto L_0x0094;
    L_0x0088:
        r4 = r11 & 32;
        if (r4 == 0) goto L_0x008e;
    L_0x008c:
        r4 = r5;
        goto L_0x008f;
    L_0x008e:
        r4 = r7;
    L_0x008f:
        r8 = r11 & 128;
        if (r8 == 0) goto L_0x0084;
    L_0x0093:
        goto L_0x0082;
    L_0x0094:
        r4 = r7;
        r8 = r4;
    L_0x0096:
        if (r4 == 0) goto L_0x0099;
    L_0x0098:
        goto L_0x009a;
    L_0x0099:
        r5 = r7;
    L_0x009a:
        if (r8 == 0) goto L_0x009e;
    L_0x009c:
        r5 = r5 + 4;
    L_0x009e:
        r4 = (long) r5;
        r8 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1));
        if (r8 >= 0) goto L_0x00a7;
    L_0x00a3:
        r1.setPosition(r3);
        return r7;
    L_0x00a7:
        r4 = r20.bytesLeft();	 Catch:{ all -> 0x00be }
        r4 = (long) r4;
        r8 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));
        if (r8 >= 0) goto L_0x00b4;
    L_0x00b0:
        r1.setPosition(r3);
        return r7;
    L_0x00b4:
        r4 = (int) r12;
        r1.skipBytes(r4);	 Catch:{ all -> 0x00be }
        goto L_0x0008;
    L_0x00ba:
        r1.setPosition(r3);
        return r5;
    L_0x00be:
        r0 = move-exception;
        r2 = r0;
        r1.setPosition(r3);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.id3.Id3Decoder.validateFrames(com.google.android.exoplayer2.util.ParsableByteArray, int, int, boolean):boolean");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:165:0x021c */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:93:0x012d, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:94:0x012e, code skipped:
            r1 = r0;
     */
    /* JADX WARNING: Missing block: B:132:0x0192, code skipped:
            if (r14 == 67) goto L_0x0194;
     */
    /* JADX WARNING: Missing block: B:166:?, code skipped:
            com.google.android.exoplayer2.util.Log.w(TAG, "Unsupported character encoding");
     */
    /* JADX WARNING: Missing block: B:167:0x0223, code skipped:
            r8.setPosition(r13);
     */
    /* JADX WARNING: Missing block: B:168:0x0226, code skipped:
            return null;
     */
    /* JADX WARNING: Missing block: B:169:0x0227, code skipped:
            r8.setPosition(r13);
     */
    @android.support.annotation.Nullable
    private static com.google.android.exoplayer2.metadata.id3.Id3Frame decodeFrame(int r20, com.google.android.exoplayer2.util.ParsableByteArray r21, boolean r22, int r23, @android.support.annotation.Nullable com.google.android.exoplayer2.metadata.id3.Id3Decoder.FramePredicate r24) {
        /*
        r7 = r20;
        r8 = r21;
        r9 = r21.readUnsignedByte();
        r10 = r21.readUnsignedByte();
        r11 = r21.readUnsignedByte();
        r12 = 3;
        if (r7 < r12) goto L_0x0019;
    L_0x0013:
        r1 = r21.readUnsignedByte();
        r14 = r1;
        goto L_0x001a;
    L_0x0019:
        r14 = 0;
    L_0x001a:
        r15 = 4;
        if (r7 != r15) goto L_0x003d;
    L_0x001d:
        r1 = r21.readUnsignedIntToInt();
        if (r22 != 0) goto L_0x003a;
    L_0x0023:
        r2 = r1 & 255;
        r3 = r1 >> 8;
        r3 = r3 & 255;
        r3 = r3 << 7;
        r2 = r2 | r3;
        r3 = r1 >> 16;
        r3 = r3 & 255;
        r3 = r3 << 14;
        r2 = r2 | r3;
        r1 = r1 >> 24;
        r1 = r1 & 255;
        r1 = r1 << 21;
        r1 = r1 | r2;
    L_0x003a:
        r16 = r1;
        goto L_0x0049;
    L_0x003d:
        if (r7 != r12) goto L_0x0044;
    L_0x003f:
        r1 = r21.readUnsignedIntToInt();
        goto L_0x003a;
    L_0x0044:
        r1 = r21.readUnsignedInt24();
        goto L_0x003a;
    L_0x0049:
        if (r7 < r12) goto L_0x0051;
    L_0x004b:
        r1 = r21.readUnsignedShort();
        r6 = r1;
        goto L_0x0052;
    L_0x0051:
        r6 = 0;
    L_0x0052:
        r17 = 0;
        if (r9 != 0) goto L_0x0068;
    L_0x0056:
        if (r10 != 0) goto L_0x0068;
    L_0x0058:
        if (r11 != 0) goto L_0x0068;
    L_0x005a:
        if (r14 != 0) goto L_0x0068;
    L_0x005c:
        if (r16 != 0) goto L_0x0068;
    L_0x005e:
        if (r6 != 0) goto L_0x0068;
    L_0x0060:
        r1 = r21.limit();
        r8.setPosition(r1);
        return r17;
    L_0x0068:
        r1 = r21.getPosition();
        r5 = r1 + r16;
        r1 = r21.limit();
        if (r5 <= r1) goto L_0x0083;
    L_0x0074:
        r1 = "Id3Decoder";
        r2 = "Frame size exceeds remaining tag data";
        com.google.android.exoplayer2.util.Log.w(r1, r2);
        r1 = r21.limit();
        r8.setPosition(r1);
        return r17;
    L_0x0083:
        if (r24 == 0) goto L_0x0098;
    L_0x0085:
        r1 = r24;
        r2 = r7;
        r3 = r9;
        r4 = r10;
        r13 = r5;
        r5 = r11;
        r15 = r6;
        r6 = r14;
        r1 = r1.evaluate(r2, r3, r4, r5, r6);
        if (r1 != 0) goto L_0x009a;
    L_0x0094:
        r8.setPosition(r13);
        return r17;
    L_0x0098:
        r13 = r5;
        r15 = r6;
    L_0x009a:
        r1 = 1;
        if (r7 != r12) goto L_0x00b7;
    L_0x009d:
        r2 = r15 & 128;
        if (r2 == 0) goto L_0x00a3;
    L_0x00a1:
        r2 = r1;
        goto L_0x00a4;
    L_0x00a3:
        r2 = 0;
    L_0x00a4:
        r3 = r15 & 64;
        if (r3 == 0) goto L_0x00aa;
    L_0x00a8:
        r3 = r1;
        goto L_0x00ab;
    L_0x00aa:
        r3 = 0;
    L_0x00ab:
        r4 = r15 & 32;
        if (r4 == 0) goto L_0x00b1;
    L_0x00af:
        r4 = r1;
        goto L_0x00b2;
    L_0x00b1:
        r4 = 0;
    L_0x00b2:
        r18 = r4;
        r5 = 0;
        r4 = r2;
        goto L_0x00ee;
    L_0x00b7:
        r2 = 4;
        if (r7 != r2) goto L_0x00e8;
    L_0x00ba:
        r2 = r15 & 64;
        if (r2 == 0) goto L_0x00c0;
    L_0x00be:
        r2 = r1;
        goto L_0x00c1;
    L_0x00c0:
        r2 = 0;
    L_0x00c1:
        r3 = r15 & 8;
        if (r3 == 0) goto L_0x00c7;
    L_0x00c5:
        r3 = r1;
        goto L_0x00c8;
    L_0x00c7:
        r3 = 0;
    L_0x00c8:
        r4 = r15 & 4;
        if (r4 == 0) goto L_0x00ce;
    L_0x00cc:
        r4 = r1;
        goto L_0x00cf;
    L_0x00ce:
        r4 = 0;
    L_0x00cf:
        r5 = r15 & 2;
        if (r5 == 0) goto L_0x00d5;
    L_0x00d3:
        r5 = r1;
        goto L_0x00d6;
    L_0x00d5:
        r5 = 0;
    L_0x00d6:
        r6 = r15 & 1;
        if (r6 == 0) goto L_0x00dd;
    L_0x00da:
        r18 = r1;
        goto L_0x00df;
    L_0x00dd:
        r18 = 0;
    L_0x00df:
        r19 = r18;
        r18 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r19;
        goto L_0x00ee;
    L_0x00e8:
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r18 = 0;
    L_0x00ee:
        if (r2 != 0) goto L_0x022b;
    L_0x00f0:
        if (r3 == 0) goto L_0x00f4;
    L_0x00f2:
        goto L_0x022b;
    L_0x00f4:
        if (r18 == 0) goto L_0x00fb;
    L_0x00f6:
        r16 = r16 + -1;
        r8.skipBytes(r1);
    L_0x00fb:
        if (r4 == 0) goto L_0x0103;
    L_0x00fd:
        r16 = r16 + -4;
        r1 = 4;
        r8.skipBytes(r1);
    L_0x0103:
        r1 = r16;
        if (r5 == 0) goto L_0x010b;
    L_0x0107:
        r1 = removeUnsynchronization(r8, r1);
    L_0x010b:
        r12 = r1;
        r1 = 84;
        r2 = 88;
        r3 = 2;
        if (r9 != r1) goto L_0x0121;
    L_0x0113:
        if (r10 != r2) goto L_0x0121;
    L_0x0115:
        if (r11 != r2) goto L_0x0121;
    L_0x0117:
        if (r7 == r3) goto L_0x011b;
    L_0x0119:
        if (r14 != r2) goto L_0x0121;
    L_0x011b:
        r1 = decodeTxxxFrame(r8, r12);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x0121:
        if (r9 != r1) goto L_0x0131;
    L_0x0123:
        r1 = getFrameId(r7, r9, r10, r11, r14);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r1 = decodeTextInformationFrame(r8, r12, r1);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x012d:
        r0 = move-exception;
        r1 = r0;
        goto L_0x0227;
    L_0x0131:
        r4 = 87;
        if (r9 != r4) goto L_0x0143;
    L_0x0135:
        if (r10 != r2) goto L_0x0143;
    L_0x0137:
        if (r11 != r2) goto L_0x0143;
    L_0x0139:
        if (r7 == r3) goto L_0x013d;
    L_0x013b:
        if (r14 != r2) goto L_0x0143;
    L_0x013d:
        r1 = decodeWxxxFrame(r8, r12);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x0143:
        r2 = 87;
        if (r9 != r2) goto L_0x0151;
    L_0x0147:
        r1 = getFrameId(r7, r9, r10, r11, r14);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r1 = decodeUrlLinkFrame(r8, r12, r1);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x0151:
        r2 = 73;
        r4 = 80;
        if (r9 != r4) goto L_0x0167;
    L_0x0157:
        r5 = 82;
        if (r10 != r5) goto L_0x0167;
    L_0x015b:
        if (r11 != r2) goto L_0x0167;
    L_0x015d:
        r5 = 86;
        if (r14 != r5) goto L_0x0167;
    L_0x0161:
        r1 = decodePrivFrame(r8, r12);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x0167:
        r5 = 71;
        r6 = 79;
        if (r9 != r5) goto L_0x017f;
    L_0x016d:
        r5 = 69;
        if (r10 != r5) goto L_0x017f;
    L_0x0171:
        if (r11 != r6) goto L_0x017f;
    L_0x0173:
        r5 = 66;
        if (r14 == r5) goto L_0x0179;
    L_0x0177:
        if (r7 != r3) goto L_0x017f;
    L_0x0179:
        r1 = decodeGeobFrame(r8, r12);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x017f:
        r5 = 67;
        if (r7 != r3) goto L_0x018a;
    L_0x0183:
        if (r9 != r4) goto L_0x019a;
    L_0x0185:
        if (r10 != r2) goto L_0x019a;
    L_0x0187:
        if (r11 != r5) goto L_0x019a;
    L_0x0189:
        goto L_0x0194;
    L_0x018a:
        r15 = 65;
        if (r9 != r15) goto L_0x019a;
    L_0x018e:
        if (r10 != r4) goto L_0x019a;
    L_0x0190:
        if (r11 != r2) goto L_0x019a;
    L_0x0192:
        if (r14 != r5) goto L_0x019a;
    L_0x0194:
        r1 = decodeApicFrame(r8, r12, r7);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x019a:
        r2 = 77;
        if (r9 != r5) goto L_0x01ab;
    L_0x019e:
        if (r10 != r6) goto L_0x01ab;
    L_0x01a0:
        if (r11 != r2) goto L_0x01ab;
    L_0x01a2:
        if (r14 == r2) goto L_0x01a6;
    L_0x01a4:
        if (r7 != r3) goto L_0x01ab;
    L_0x01a6:
        r1 = decodeCommentFrame(r8, r12);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x01ab:
        if (r9 != r5) goto L_0x01c5;
    L_0x01ad:
        r3 = 72;
        if (r10 != r3) goto L_0x01c5;
    L_0x01b1:
        r3 = 65;
        if (r11 != r3) goto L_0x01c5;
    L_0x01b5:
        if (r14 != r4) goto L_0x01c5;
    L_0x01b7:
        r1 = r8;
        r2 = r12;
        r3 = r7;
        r4 = r22;
        r5 = r23;
        r6 = r24;
        r1 = decodeChapterFrame(r1, r2, r3, r4, r5, r6);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x01c5:
        if (r9 != r5) goto L_0x01db;
    L_0x01c7:
        if (r10 != r1) goto L_0x01db;
    L_0x01c9:
        if (r11 != r6) goto L_0x01db;
    L_0x01cb:
        if (r14 != r5) goto L_0x01db;
    L_0x01cd:
        r1 = r8;
        r2 = r12;
        r3 = r7;
        r4 = r22;
        r5 = r23;
        r6 = r24;
        r1 = decodeChapterTOCFrame(r1, r2, r3, r4, r5, r6);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x01db:
        if (r9 != r2) goto L_0x01ec;
    L_0x01dd:
        r2 = 76;
        if (r10 != r2) goto L_0x01ec;
    L_0x01e1:
        r2 = 76;
        if (r11 != r2) goto L_0x01ec;
    L_0x01e5:
        if (r14 != r1) goto L_0x01ec;
    L_0x01e7:
        r1 = decodeMlltFrame(r8, r12);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        goto L_0x01f4;
    L_0x01ec:
        r1 = getFrameId(r7, r9, r10, r11, r14);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r1 = decodeBinaryFrame(r8, r12, r1);	 Catch:{ UnsupportedEncodingException -> 0x021c }
    L_0x01f4:
        if (r1 != 0) goto L_0x0218;
    L_0x01f6:
        r2 = "Id3Decoder";
        r3 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r3.<init>();	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r4 = "Failed to decode frame: id=";
        r3.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r4 = getFrameId(r7, r9, r10, r11, r14);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r3.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r4 = ", frameSize=";
        r3.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r3.append(r12);	 Catch:{ UnsupportedEncodingException -> 0x021c }
        r3 = r3.toString();	 Catch:{ UnsupportedEncodingException -> 0x021c }
        com.google.android.exoplayer2.util.Log.w(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x021c }
    L_0x0218:
        r8.setPosition(r13);
        return r1;
    L_0x021c:
        r1 = "Id3Decoder";
        r2 = "Unsupported character encoding";
        com.google.android.exoplayer2.util.Log.w(r1, r2);	 Catch:{ all -> 0x012d }
        r8.setPosition(r13);
        return r17;
    L_0x0227:
        r8.setPosition(r13);
        throw r1;
    L_0x022b:
        r1 = "Id3Decoder";
        r2 = "Skipping unsupported compressed or encrypted frame";
        com.google.android.exoplayer2.util.Log.w(r1, r2);
        r8.setPosition(r13);
        return r17;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.id3.Id3Decoder.decodeFrame(int, com.google.android.exoplayer2.util.ParsableByteArray, boolean, int, com.google.android.exoplayer2.metadata.id3.Id3Decoder$FramePredicate):com.google.android.exoplayer2.metadata.id3.Id3Frame");
    }

    @Nullable
    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        i--;
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        int indexOfEos = indexOfEos(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfEos, charsetName);
        indexOfEos += delimiterLength(readUnsignedByte);
        return new TextInformationFrame("TXXX", str, decodeStringIfValid(bArr, indexOfEos, indexOfEos(bArr, indexOfEos, readUnsignedByte), charsetName));
    }

    @Nullable
    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray parsableByteArray, int i, String str) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        i--;
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new TextInformationFrame(str, null, new String(bArr, 0, indexOfEos(bArr, 0, readUnsignedByte), charsetName));
    }

    @Nullable
    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        i--;
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        int indexOfEos = indexOfEos(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfEos, charsetName);
        indexOfEos += delimiterLength(readUnsignedByte);
        return new UrlLinkFrame("WXXX", str, decodeStringIfValid(bArr, indexOfEos, indexOfZeroByte(bArr, indexOfEos), "ISO-8859-1"));
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray parsableByteArray, int i, String str) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new UrlLinkFrame(str, null, new String(bArr, 0, indexOfZeroByte(bArr, 0), "ISO-8859-1"));
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        return new PrivFrame(new String(bArr, 0, indexOfZeroByte, "ISO-8859-1"), copyOfRangeIfValid(bArr, indexOfZeroByte + 1, bArr.length));
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        i--;
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        String str = new String(bArr, 0, indexOfZeroByte, "ISO-8859-1");
        indexOfZeroByte++;
        int indexOfEos = indexOfEos(bArr, indexOfZeroByte, readUnsignedByte);
        String decodeStringIfValid = decodeStringIfValid(bArr, indexOfZeroByte, indexOfEos, charsetName);
        indexOfEos += delimiterLength(readUnsignedByte);
        int indexOfEos2 = indexOfEos(bArr, indexOfEos, readUnsignedByte);
        return new GeobFrame(str, decodeStringIfValid, decodeStringIfValid(bArr, indexOfEos, indexOfEos2, charsetName), copyOfRangeIfValid(bArr, indexOfEos2 + delimiterLength(readUnsignedByte), bArr.length));
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray parsableByteArray, int i, int i2) throws UnsupportedEncodingException {
        String str;
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        i--;
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        if (i2 == 2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("image/");
            stringBuilder.append(Util.toLowerInvariant(new String(bArr, 0, 3, "ISO-8859-1")));
            String stringBuilder2 = stringBuilder.toString();
            if ("image/jpg".equals(stringBuilder2)) {
                stringBuilder2 = "image/jpeg";
            }
            str = stringBuilder2;
            i = 2;
        } else {
            i = indexOfZeroByte(bArr, 0);
            str = Util.toLowerInvariant(new String(bArr, 0, i, "ISO-8859-1"));
            if (str.indexOf(47) == -1) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("image/");
                stringBuilder3.append(str);
                str = stringBuilder3.toString();
            }
        }
        int i3 = bArr[i + 1] & 255;
        i += 2;
        int indexOfEos = indexOfEos(bArr, i, readUnsignedByte);
        return new ApicFrame(str, new String(bArr, i, indexOfEos - i, charsetName), i3, copyOfRangeIfValid(bArr, indexOfEos + delimiterLength(readUnsignedByte), bArr.length));
    }

    @Nullable
    private static CommentFrame decodeCommentFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        if (i < 4) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        byte[] bArr = new byte[3];
        parsableByteArray.readBytes(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        i -= 4;
        byte[] bArr2 = new byte[i];
        parsableByteArray.readBytes(bArr2, 0, i);
        int indexOfEos = indexOfEos(bArr2, 0, readUnsignedByte);
        String str2 = new String(bArr2, 0, indexOfEos, charsetName);
        indexOfEos += delimiterLength(readUnsignedByte);
        return new CommentFrame(str, str2, decodeStringIfValid(bArr2, indexOfEos, indexOfEos(bArr2, indexOfEos, readUnsignedByte), charsetName));
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, @Nullable FramePredicate framePredicate) throws UnsupportedEncodingException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray2.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray2.data, position);
        String str = new String(parsableByteArray2.data, position, indexOfZeroByte - position, "ISO-8859-1");
        parsableByteArray2.setPosition(indexOfZeroByte + 1);
        int readInt = parsableByteArray2.readInt();
        int readInt2 = parsableByteArray2.readInt();
        long readUnsignedInt = parsableByteArray2.readUnsignedInt();
        long j = readUnsignedInt == 4294967295L ? -1 : readUnsignedInt;
        readUnsignedInt = parsableByteArray2.readUnsignedInt();
        long j2 = readUnsignedInt == 4294967295L ? -1 : readUnsignedInt;
        ArrayList arrayList = new ArrayList();
        position += i;
        while (parsableByteArray2.getPosition() < position) {
            Id3Frame decodeFrame = decodeFrame(i2, parsableByteArray2, z, i3, framePredicate);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        Id3Frame[] id3FrameArr = new Id3Frame[arrayList.size()];
        arrayList.toArray(id3FrameArr);
        return new ChapterFrame(str, readInt, readInt2, j, j2, id3FrameArr);
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, @Nullable FramePredicate framePredicate) throws UnsupportedEncodingException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray2.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray2.data, position);
        String str = new String(parsableByteArray2.data, position, indexOfZeroByte - position, "ISO-8859-1");
        parsableByteArray2.setPosition(indexOfZeroByte + 1);
        indexOfZeroByte = parsableByteArray2.readUnsignedByte();
        int i4 = 0;
        boolean z2 = (indexOfZeroByte & 2) != 0;
        boolean z3 = (indexOfZeroByte & 1) != 0;
        int readUnsignedByte = parsableByteArray2.readUnsignedByte();
        String[] strArr = new String[readUnsignedByte];
        while (i4 < readUnsignedByte) {
            int position2 = parsableByteArray2.getPosition();
            int indexOfZeroByte2 = indexOfZeroByte(parsableByteArray2.data, position2);
            strArr[i4] = new String(parsableByteArray2.data, position2, indexOfZeroByte2 - position2, "ISO-8859-1");
            parsableByteArray2.setPosition(indexOfZeroByte2 + 1);
            i4++;
        }
        ArrayList arrayList = new ArrayList();
        position += i;
        while (parsableByteArray2.getPosition() < position) {
            Id3Frame decodeFrame = decodeFrame(i2, parsableByteArray2, z, i3, framePredicate);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        Id3Frame[] id3FrameArr = new Id3Frame[arrayList.size()];
        arrayList.toArray(id3FrameArr);
        return new ChapterTocFrame(str, z2, z3, strArr, id3FrameArr);
    }

    private static MlltFrame decodeMlltFrame(ParsableByteArray parsableByteArray, int i) {
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        int readUnsignedInt242 = parsableByteArray.readUnsignedInt24();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int i2 = (8 * (i - 10)) / (readUnsignedByte + readUnsignedByte2);
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int readBits = parsableBitArray.readBits(readUnsignedByte);
            int readBits2 = parsableBitArray.readBits(readUnsignedByte2);
            iArr[i3] = readBits;
            iArr2[i3] = readBits2;
        }
        return new MlltFrame(readUnsignedShort, readUnsignedInt24, readUnsignedInt242, iArr, iArr2);
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray parsableByteArray, int i, String str) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new BinaryFrame(str, bArr);
    }

    private static int removeUnsynchronization(ParsableByteArray parsableByteArray, int i) {
        byte[] bArr = parsableByteArray.data;
        int position = parsableByteArray.getPosition();
        while (true) {
            int i2 = position + 1;
            if (i2 >= i) {
                return i;
            }
            if ((bArr[position] & 255) == 255 && bArr[i2] == (byte) 0) {
                System.arraycopy(bArr, position + 2, bArr, i2, (i - position) - 2);
                i--;
            }
            position = i2;
        }
    }

    private static String getFrameId(int i, int i2, int i3, int i4, int i5) {
        if (i == 2) {
            return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
        }
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    private static int indexOfEos(byte[] bArr, int i, int i2) {
        i = indexOfZeroByte(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return i;
        }
        while (i < bArr.length - 1) {
            if (i % 2 == 0 && bArr[i + 1] == (byte) 0) {
                return i;
            }
            i = indexOfZeroByte(bArr, i + 1);
        }
        return bArr.length;
    }

    private static int indexOfZeroByte(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == (byte) 0) {
                return i;
            }
            i++;
        }
        return bArr.length;
    }

    private static byte[] copyOfRangeIfValid(byte[] bArr, int i, int i2) {
        if (i2 <= i) {
            return Util.EMPTY_BYTE_ARRAY;
        }
        return Arrays.copyOfRange(bArr, i, i2);
    }

    private static String decodeStringIfValid(byte[] bArr, int i, int i2, String str) throws UnsupportedEncodingException {
        return (i2 <= i || i2 > bArr.length) ? "" : new String(bArr, i, i2 - i, str);
    }
}
