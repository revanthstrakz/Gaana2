package com.google.android.exoplayer2.extractor.mp4;

import android.support.v4.internal.view.SupportMenu;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class AtomParsers {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 3;
    private static final String TAG = "AtomParsers";
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_text = Util.getIntegerCodeForString(MimeTypes.BASE_TYPE_TEXT);
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            boolean z2 = true;
            if (parsableByteArray.readInt() != 1) {
                z2 = false;
            }
            Assertions.checkState(z2, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            long readUnsignedLongToLong;
            if (this.chunkOffsetsAreLongs) {
                readUnsignedLongToLong = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                readUnsignedLongToLong = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = readUnsignedLongToLong;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                i = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i;
                this.nextSamplesPerChunkChangeIndex = i > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    private static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize = this.data.readUnsignedIntToInt();
        private final int sampleCount = this.data.readUnsignedIntToInt();

        public StszSampleSizeBox(LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            return this.fixedSampleSize == 0 ? this.data.readUnsignedIntToInt() : this.fixedSampleSize;
        }

        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize = (this.data.readUnsignedIntToInt() & 255);
        private final int sampleCount = this.data.readUnsignedIntToInt();
        private int sampleIndex;

        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            if (this.fieldSize == 8) {
                return this.data.readUnsignedByte();
            }
            if (this.fieldSize == 16) {
                return this.data.readUnsignedShort();
            }
            int i = this.sampleIndex;
            this.sampleIndex = i + 1;
            if (i % 2 != 0) {
                return this.currentByte & 15;
            }
            this.currentByte = this.data.readUnsignedByte();
            return (this.currentByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
    }

    private static final class TkhdData {
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    public static Track parseTrak(ContainerAtom containerAtom, LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        ContainerAtom containerAtom2 = containerAtom;
        ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia);
        int parseHdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (parseHdlr == -1) {
            return null;
        }
        long access$000;
        LeafAtom leafAtom2;
        long[] jArr;
        long[] jArr2;
        Track track;
        TkhdData parseTkhd = parseTkhd(containerAtom2.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long j2 = C.TIME_UNSET;
        if (j == C.TIME_UNSET) {
            access$000 = parseTkhd.duration;
            leafAtom2 = leafAtom;
        } else {
            leafAtom2 = leafAtom;
            access$000 = j;
        }
        long parseMvhd = parseMvhd(leafAtom2.data);
        if (access$000 != C.TIME_UNSET) {
            j2 = Util.scaleLargeTimestamp(access$000, 1000000, parseMvhd);
        }
        long j3 = j2;
        ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair parseMdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData parseStsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z2);
        if (z) {
            jArr = null;
            jArr2 = jArr;
        } else {
            Pair parseEdts = parseEdts(containerAtom2.getContainerAtomOfType(Atom.TYPE_edts));
            jArr2 = (long[]) parseEdts.second;
            jArr = (long[]) parseEdts.first;
        }
        if (parseStsd.format == null) {
            track = null;
        } else {
            int access$100 = parseTkhd.id;
            j2 = ((Long) parseMdhd.first).longValue();
            Format format = parseStsd.format;
            int i = parseStsd.requiredSampleTransformation;
            TrackEncryptionBox[] trackEncryptionBoxArr = parseStsd.trackEncryptionBoxes;
            int i2 = parseStsd.nalUnitLengthFieldLength;
            Track track2 = new Track(access$100, parseHdlr, j2, parseMvhd, j3, format, i, trackEncryptionBoxArr, i2, jArr, jArr2);
        }
        return track;
    }

    /* JADX WARNING: Removed duplicated region for block: B:143:0x036e  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x036c  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0382  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x03f8  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x0400  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x03fd  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0406  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0403  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x040a  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0411  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x040e  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x041a  */
    public static com.google.android.exoplayer2.extractor.mp4.TrackSampleTable parseStbl(com.google.android.exoplayer2.extractor.mp4.Track r52, com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom r53, com.google.android.exoplayer2.extractor.GaplessInfoHolder r54) throws com.google.android.exoplayer2.ParserException {
        /*
        r1 = r52;
        r0 = r53;
        r2 = r54;
        r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stsz;
        r3 = r0.getLeafAtomOfType(r3);
        if (r3 == 0) goto L_0x0014;
    L_0x000e:
        r4 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$StszSampleSizeBox;
        r4.<init>(r3);
        goto L_0x0029;
    L_0x0014:
        r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stz2;
        r3 = r0.getLeafAtomOfType(r3);
        if (r3 != 0) goto L_0x0024;
    L_0x001c:
        r0 = new com.google.android.exoplayer2.ParserException;
        r1 = "Track has no sample table size information";
        r0.<init>(r1);
        throw r0;
    L_0x0024:
        r4 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$Stz2SampleSizeBox;
        r4.<init>(r3);
    L_0x0029:
        r3 = r4.getSampleCount();
        r5 = 0;
        if (r3 != 0) goto L_0x0048;
    L_0x0030:
        r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable;
        r2 = new long[r5];
        r3 = new int[r5];
        r4 = 0;
        r6 = new long[r5];
        r7 = new int[r5];
        r10 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r0 = r9;
        r5 = r6;
        r6 = r7;
        r7 = r10;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        return r9;
    L_0x0048:
        r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stco;
        r6 = r0.getLeafAtomOfType(r6);
        r7 = 1;
        if (r6 != 0) goto L_0x0059;
    L_0x0051:
        r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_co64;
        r6 = r0.getLeafAtomOfType(r6);
        r8 = r7;
        goto L_0x005a;
    L_0x0059:
        r8 = r5;
    L_0x005a:
        r6 = r6.data;
        r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stsc;
        r9 = r0.getLeafAtomOfType(r9);
        r9 = r9.data;
        r10 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stts;
        r10 = r0.getLeafAtomOfType(r10);
        r10 = r10.data;
        r11 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_stss;
        r11 = r0.getLeafAtomOfType(r11);
        r12 = 0;
        if (r11 == 0) goto L_0x0078;
    L_0x0075:
        r11 = r11.data;
        goto L_0x0079;
    L_0x0078:
        r11 = r12;
    L_0x0079:
        r13 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ctts;
        r0 = r0.getLeafAtomOfType(r13);
        if (r0 == 0) goto L_0x0084;
    L_0x0081:
        r0 = r0.data;
        goto L_0x0085;
    L_0x0084:
        r0 = r12;
    L_0x0085:
        r13 = new com.google.android.exoplayer2.extractor.mp4.AtomParsers$ChunkIterator;
        r13.<init>(r9, r6, r8);
        r6 = 12;
        r10.setPosition(r6);
        r8 = r10.readUnsignedIntToInt();
        r8 = r8 - r7;
        r9 = r10.readUnsignedIntToInt();
        r14 = r10.readUnsignedIntToInt();
        if (r0 == 0) goto L_0x00a6;
    L_0x009e:
        r0.setPosition(r6);
        r15 = r0.readUnsignedIntToInt();
        goto L_0x00a7;
    L_0x00a6:
        r15 = r5;
    L_0x00a7:
        r16 = -1;
        if (r11 == 0) goto L_0x00bd;
    L_0x00ab:
        r11.setPosition(r6);
        r6 = r11.readUnsignedIntToInt();
        if (r6 <= 0) goto L_0x00bb;
    L_0x00b4:
        r12 = r11.readUnsignedIntToInt();
        r16 = r12 + -1;
        goto L_0x00be;
    L_0x00bb:
        r11 = r12;
        goto L_0x00be;
    L_0x00bd:
        r6 = r5;
    L_0x00be:
        r12 = r4.isFixedSampleSize();
        if (r12 == 0) goto L_0x00d8;
    L_0x00c4:
        r12 = "audio/raw";
        r5 = r1.format;
        r5 = r5.sampleMimeType;
        r5 = r12.equals(r5);
        if (r5 == 0) goto L_0x00d8;
    L_0x00d0:
        if (r8 != 0) goto L_0x00d8;
    L_0x00d2:
        if (r15 != 0) goto L_0x00d8;
    L_0x00d4:
        if (r6 != 0) goto L_0x00d8;
    L_0x00d6:
        r5 = r7;
        goto L_0x00d9;
    L_0x00d8:
        r5 = 0;
    L_0x00d9:
        r18 = 0;
        if (r5 != 0) goto L_0x0244;
    L_0x00dd:
        r5 = new long[r3];
        r12 = new int[r3];
        r7 = new long[r3];
        r21 = r6;
        r6 = new int[r3];
        r30 = r8;
        r2 = r9;
        r29 = r10;
        r10 = r16;
        r25 = r18;
        r9 = r21;
        r1 = 0;
        r8 = 0;
        r22 = 0;
        r27 = 0;
        r28 = 0;
        r21 = r15;
        r15 = r25;
    L_0x00fe:
        if (r8 >= r3) goto L_0x01b8;
    L_0x0100:
        r31 = r25;
        r25 = 1;
    L_0x0104:
        if (r22 != 0) goto L_0x011f;
    L_0x0106:
        r25 = r13.moveNext();
        if (r25 == 0) goto L_0x011f;
    L_0x010c:
        r34 = r2;
        r33 = r3;
        r2 = r13.offset;
        r35 = r2;
        r2 = r13.numSamples;
        r22 = r2;
        r3 = r33;
        r2 = r34;
        r31 = r35;
        goto L_0x0104;
    L_0x011f:
        r34 = r2;
        r33 = r3;
        if (r25 != 0) goto L_0x0140;
    L_0x0125:
        r2 = "AtomParsers";
        r3 = "Unexpected end of chunk data";
        com.google.android.exoplayer2.util.Log.w(r2, r3);
        r5 = java.util.Arrays.copyOf(r5, r8);
        r12 = java.util.Arrays.copyOf(r12, r8);
        r7 = java.util.Arrays.copyOf(r7, r8);
        r6 = java.util.Arrays.copyOf(r6, r8);
        r33 = r8;
        goto L_0x01bc;
    L_0x0140:
        if (r0 == 0) goto L_0x0153;
    L_0x0142:
        if (r28 != 0) goto L_0x0151;
    L_0x0144:
        if (r21 <= 0) goto L_0x0151;
    L_0x0146:
        r28 = r0.readUnsignedIntToInt();
        r27 = r0.readInt();
        r21 = r21 + -1;
        goto L_0x0142;
    L_0x0151:
        r28 = r28 + -1;
    L_0x0153:
        r2 = r27;
        r5[r8] = r31;
        r3 = r4.readNextSampleSize();
        r12[r8] = r3;
        r3 = r12[r8];
        if (r3 <= r1) goto L_0x0163;
    L_0x0161:
        r1 = r12[r8];
    L_0x0163:
        r37 = r4;
        r3 = (long) r2;
        r25 = r15 + r3;
        r7[r8] = r25;
        if (r11 != 0) goto L_0x016e;
    L_0x016c:
        r3 = 1;
        goto L_0x016f;
    L_0x016e:
        r3 = 0;
    L_0x016f:
        r6[r8] = r3;
        if (r8 != r10) goto L_0x0180;
    L_0x0173:
        r3 = 1;
        r6[r8] = r3;
        r9 = r9 + -1;
        if (r9 <= 0) goto L_0x0180;
    L_0x017a:
        r4 = r11.readUnsignedIntToInt();
        r4 = r4 - r3;
        r10 = r4;
    L_0x0180:
        r3 = (long) r14;
        r25 = r15 + r3;
        r3 = r34 + -1;
        if (r3 != 0) goto L_0x0196;
    L_0x0187:
        if (r30 <= 0) goto L_0x0196;
    L_0x0189:
        r4 = r29;
        r3 = r4.readUnsignedIntToInt();
        r14 = r4.readInt();
        r30 = r30 + -1;
        goto L_0x0198;
    L_0x0196:
        r4 = r29;
    L_0x0198:
        r15 = r12[r8];
        r39 = r1;
        r38 = r2;
        r1 = (long) r15;
        r15 = r31 + r1;
        r22 = r22 + -1;
        r8 = r8 + 1;
        r2 = r3;
        r29 = r4;
        r3 = r33;
        r4 = r37;
        r27 = r38;
        r1 = r39;
        r50 = r15;
        r15 = r25;
        r25 = r50;
        goto L_0x00fe;
    L_0x01b8:
        r34 = r2;
        r33 = r3;
    L_0x01bc:
        r3 = r22;
        r2 = r27;
        r10 = (long) r2;
        r13 = r15 + r10;
    L_0x01c3:
        if (r21 <= 0) goto L_0x01d3;
    L_0x01c5:
        r2 = r0.readUnsignedIntToInt();
        if (r2 == 0) goto L_0x01cd;
    L_0x01cb:
        r0 = 0;
        goto L_0x01d4;
    L_0x01cd:
        r0.readInt();
        r21 = r21 + -1;
        goto L_0x01c3;
    L_0x01d3:
        r0 = 1;
    L_0x01d4:
        if (r9 != 0) goto L_0x01e8;
    L_0x01d6:
        if (r34 != 0) goto L_0x01e8;
    L_0x01d8:
        if (r3 != 0) goto L_0x01e8;
    L_0x01da:
        if (r30 != 0) goto L_0x01e8;
    L_0x01dc:
        r2 = r28;
        if (r2 != 0) goto L_0x01ea;
    L_0x01e0:
        if (r0 != 0) goto L_0x01e3;
    L_0x01e2:
        goto L_0x01ea;
    L_0x01e3:
        r39 = r1;
        r1 = r52;
        goto L_0x023c;
    L_0x01e8:
        r2 = r28;
    L_0x01ea:
        r4 = "AtomParsers";
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r10 = "Inconsistent stbl box for track ";
        r8.append(r10);
        r39 = r1;
        r1 = r52;
        r10 = r1.id;
        r8.append(r10);
        r10 = ": remainingSynchronizationSamples ";
        r8.append(r10);
        r8.append(r9);
        r9 = ", remainingSamplesAtTimestampDelta ";
        r8.append(r9);
        r9 = r34;
        r8.append(r9);
        r9 = ", remainingSamplesInChunk ";
        r8.append(r9);
        r8.append(r3);
        r3 = ", remainingTimestampDeltaChanges ";
        r8.append(r3);
        r3 = r30;
        r8.append(r3);
        r3 = ", remainingSamplesAtTimestampOffset ";
        r8.append(r3);
        r8.append(r2);
        if (r0 != 0) goto L_0x0230;
    L_0x022d:
        r0 = ", ctts invalid";
        goto L_0x0232;
    L_0x0230:
        r0 = "";
    L_0x0232:
        r8.append(r0);
        r0 = r8.toString();
        com.google.android.exoplayer2.util.Log.w(r4, r0);
    L_0x023c:
        r2 = r5;
        r5 = r7;
        r3 = r12;
        r0 = r33;
        r4 = r39;
        goto L_0x0280;
    L_0x0244:
        r33 = r3;
        r0 = r13.length;
        r0 = new long[r0];
        r2 = r13.length;
        r2 = new int[r2];
    L_0x024e:
        r3 = r13.moveNext();
        if (r3 == 0) goto L_0x0261;
    L_0x0254:
        r3 = r13.index;
        r4 = r13.offset;
        r0[r3] = r4;
        r3 = r13.index;
        r4 = r13.numSamples;
        r2[r3] = r4;
        goto L_0x024e;
    L_0x0261:
        r3 = r1.format;
        r3 = r3.pcmEncoding;
        r4 = r1.format;
        r4 = r4.channelCount;
        r3 = com.google.android.exoplayer2.util.Util.getPcmFrameSize(r3, r4);
        r4 = (long) r14;
        r0 = com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker.rechunk(r3, r0, r2, r4);
        r2 = r0.offsets;
        r3 = r0.sizes;
        r4 = r0.maximumSize;
        r5 = r0.timestamps;
        r6 = r0.flags;
        r13 = r0.duration;
        r0 = r33;
    L_0x0280:
        r9 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r11 = r1.timescale;
        r7 = r13;
        r7 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r7, r9, r11);
        r9 = r1.editListDurations;
        if (r9 == 0) goto L_0x04bf;
    L_0x028e:
        r9 = r54;
        r12 = r54.hasGaplessInfo();
        if (r12 == 0) goto L_0x0298;
    L_0x0296:
        goto L_0x04bf;
    L_0x0298:
        r7 = r1.editListDurations;
        r7 = r7.length;
        r8 = 1;
        if (r7 != r8) goto L_0x0328;
    L_0x029e:
        r7 = r1.type;
        if (r7 != r8) goto L_0x0328;
    L_0x02a2:
        r7 = r5.length;
        r8 = 2;
        if (r7 < r8) goto L_0x0328;
    L_0x02a6:
        r7 = r1.editListMediaTimes;
        r8 = 0;
        r15 = r7[r8];
        r7 = r1.editListDurations;
        r21 = r7[r8];
        r7 = r1.timescale;
        r10 = r1.movieTimescale;
        r23 = r7;
        r25 = r10;
        r7 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r21, r23, r25);
        r10 = r15 + r7;
        r21 = r5;
        r22 = r13;
        r24 = r15;
        r26 = r10;
        r7 = canApplyEditWithGaplessInfo(r21, r22, r24, r26);
        if (r7 == 0) goto L_0x0328;
    L_0x02cb:
        r21 = r13 - r10;
        r7 = 0;
        r10 = r5[r7];
        r23 = r15 - r10;
        r7 = r1.format;
        r7 = r7.sampleRate;
        r7 = (long) r7;
        r10 = r1.timescale;
        r25 = r7;
        r27 = r10;
        r7 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r23, r25, r27);
        r10 = r1.format;
        r10 = r10.sampleRate;
        r10 = (long) r10;
        r40 = r13;
        r12 = r1.timescale;
        r23 = r10;
        r25 = r12;
        r10 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r21, r23, r25);
        r12 = (r7 > r18 ? 1 : (r7 == r18 ? 0 : -1));
        if (r12 != 0) goto L_0x02fa;
    L_0x02f6:
        r12 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1));
        if (r12 == 0) goto L_0x032a;
    L_0x02fa:
        r12 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r14 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1));
        if (r14 > 0) goto L_0x032a;
    L_0x0301:
        r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r14 > 0) goto L_0x032a;
    L_0x0305:
        r0 = (int) r7;
        r9.encoderDelay = r0;
        r0 = (int) r10;
        r9.encoderPadding = r0;
        r7 = r1.timescale;
        r9 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        com.google.android.exoplayer2.util.Util.scaleLargeTimestampsInPlace(r5, r9, r7);
        r0 = r1.editListDurations;
        r7 = 0;
        r8 = r0[r7];
        r10 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r12 = r1.movieTimescale;
        r7 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r8, r10, r12);
        r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable;
        r0 = r9;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        return r9;
    L_0x0328:
        r40 = r13;
    L_0x032a:
        r7 = r1.editListDurations;
        r7 = r7.length;
        r8 = 1;
        if (r7 != r8) goto L_0x0367;
    L_0x0330:
        r7 = r1.editListDurations;
        r8 = 0;
        r9 = r7[r8];
        r7 = (r9 > r18 ? 1 : (r9 == r18 ? 0 : -1));
        if (r7 != 0) goto L_0x0367;
    L_0x0339:
        r0 = r1.editListMediaTimes;
        r9 = r0[r8];
        r0 = 0;
    L_0x033e:
        r7 = r5.length;
        if (r0 >= r7) goto L_0x0354;
    L_0x0341:
        r7 = r5[r0];
        r11 = r7 - r9;
        r13 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r7 = r1.timescale;
        r15 = r7;
        r7 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r11, r13, r15);
        r5[r0] = r7;
        r0 = r0 + 1;
        goto L_0x033e;
    L_0x0354:
        r11 = r40 - r9;
        r13 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r7 = r1.timescale;
        r15 = r7;
        r7 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r11, r13, r15);
        r9 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable;
        r0 = r9;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        return r9;
    L_0x0367:
        r7 = r1.type;
        r8 = 1;
        if (r7 != r8) goto L_0x036e;
    L_0x036c:
        r7 = 1;
        goto L_0x036f;
    L_0x036e:
        r7 = 0;
    L_0x036f:
        r8 = r1.editListDurations;
        r8 = r8.length;
        r8 = new int[r8];
        r9 = r1.editListDurations;
        r9 = r9.length;
        r9 = new int[r9];
        r10 = 0;
        r11 = 0;
        r12 = 0;
        r13 = 0;
    L_0x037d:
        r14 = r1.editListDurations;
        r14 = r14.length;
        if (r10 >= r14) goto L_0x03ed;
    L_0x0382:
        r14 = r1.editListMediaTimes;
        r42 = r3;
        r43 = r4;
        r3 = r14[r10];
        r14 = -1;
        r16 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1));
        if (r16 == 0) goto L_0x03de;
    L_0x0390:
        r14 = r1.editListDurations;
        r21 = r14[r10];
        r14 = r1.timescale;
        r44 = r11;
        r45 = r12;
        r11 = r1.movieTimescale;
        r23 = r14;
        r25 = r11;
        r11 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r21, r23, r25);
        r14 = 1;
        r15 = com.google.android.exoplayer2.util.Util.binarySearchCeil(r5, r3, r14, r14);
        r8[r10] = r15;
        r14 = r3 + r11;
        r3 = 0;
        r4 = com.google.android.exoplayer2.util.Util.binarySearchCeil(r5, r14, r7, r3);
        r9[r10] = r4;
    L_0x03b4:
        r4 = r8[r10];
        r11 = r9[r10];
        if (r4 >= r11) goto L_0x03c8;
    L_0x03ba:
        r4 = r8[r10];
        r4 = r6[r4];
        r11 = 1;
        r4 = r4 & r11;
        if (r4 != 0) goto L_0x03c9;
    L_0x03c2:
        r4 = r8[r10];
        r4 = r4 + r11;
        r8[r10] = r4;
        goto L_0x03b4;
    L_0x03c8:
        r11 = 1;
    L_0x03c9:
        r4 = r9[r10];
        r12 = r8[r10];
        r4 = r4 - r12;
        r12 = r45 + r4;
        r4 = r8[r10];
        if (r13 == r4) goto L_0x03d6;
    L_0x03d4:
        r4 = r11;
        goto L_0x03d7;
    L_0x03d6:
        r4 = r3;
    L_0x03d7:
        r4 = r44 | r4;
        r13 = r9[r10];
        r44 = r4;
        goto L_0x03e4;
    L_0x03de:
        r44 = r11;
        r45 = r12;
        r3 = 0;
        r11 = 1;
    L_0x03e4:
        r10 = r10 + 1;
        r3 = r42;
        r4 = r43;
        r11 = r44;
        goto L_0x037d;
    L_0x03ed:
        r42 = r3;
        r43 = r4;
        r44 = r11;
        r3 = 0;
        r11 = 1;
        if (r12 == r0) goto L_0x03f8;
    L_0x03f7:
        goto L_0x03f9;
    L_0x03f8:
        r11 = r3;
    L_0x03f9:
        r0 = r44 | r11;
        if (r0 == 0) goto L_0x0400;
    L_0x03fd:
        r4 = new long[r12];
        goto L_0x0401;
    L_0x0400:
        r4 = r2;
    L_0x0401:
        if (r0 == 0) goto L_0x0406;
    L_0x0403:
        r7 = new int[r12];
        goto L_0x0408;
    L_0x0406:
        r7 = r42;
    L_0x0408:
        if (r0 == 0) goto L_0x040c;
    L_0x040a:
        r43 = r3;
    L_0x040c:
        if (r0 == 0) goto L_0x0411;
    L_0x040e:
        r10 = new int[r12];
        goto L_0x0412;
    L_0x0411:
        r10 = r6;
    L_0x0412:
        r11 = new long[r12];
        r12 = r3;
    L_0x0415:
        r13 = r1.editListDurations;
        r13 = r13.length;
        if (r3 >= r13) goto L_0x04a4;
    L_0x041a:
        r13 = r1.editListMediaTimes;
        r20 = r13[r3];
        r13 = r8[r3];
        r14 = r9[r3];
        if (r0 == 0) goto L_0x0434;
    L_0x0424:
        r15 = r14 - r13;
        java.lang.System.arraycopy(r2, r13, r4, r12, r15);
        r46 = r9;
        r9 = r42;
        java.lang.System.arraycopy(r9, r13, r7, r12, r15);
        java.lang.System.arraycopy(r6, r13, r10, r12, r15);
        goto L_0x0438;
    L_0x0434:
        r46 = r9;
        r9 = r42;
    L_0x0438:
        r22 = r12;
        r15 = r13;
        r12 = r43;
    L_0x043d:
        if (r15 >= r14) goto L_0x0484;
    L_0x043f:
        r16 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r47 = r2;
        r48 = r3;
        r2 = r1.movieTimescale;
        r49 = r8;
        r8 = r12;
        r12 = r18;
        r23 = r14;
        r24 = r15;
        r14 = r16;
        r16 = r2;
        r2 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r12, r14, r16);
        r12 = r5[r24];
        r25 = r12 - r20;
        r27 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r12 = r1.timescale;
        r29 = r12;
        r12 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r25, r27, r29);
        r14 = r2 + r12;
        r11[r22] = r14;
        if (r0 == 0) goto L_0x0476;
    L_0x046e:
        r2 = r7[r22];
        if (r2 <= r8) goto L_0x0476;
    L_0x0472:
        r2 = r9[r24];
        r12 = r2;
        goto L_0x0477;
    L_0x0476:
        r12 = r8;
    L_0x0477:
        r22 = r22 + 1;
        r15 = r24 + 1;
        r14 = r23;
        r2 = r47;
        r3 = r48;
        r8 = r49;
        goto L_0x043d;
    L_0x0484:
        r47 = r2;
        r48 = r3;
        r49 = r8;
        r8 = r12;
        r2 = r1.editListDurations;
        r12 = r2[r48];
        r2 = r18 + r12;
        r12 = r48 + 1;
        r18 = r2;
        r43 = r8;
        r42 = r9;
        r3 = r12;
        r12 = r22;
        r9 = r46;
        r2 = r47;
        r8 = r49;
        goto L_0x0415;
    L_0x04a4:
        r14 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r2 = r1.movieTimescale;
        r12 = r18;
        r16 = r2;
        r8 = com.google.android.exoplayer2.util.Util.scaleLargeTimestamp(r12, r14, r16);
        r12 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable;
        r0 = r12;
        r2 = r4;
        r3 = r7;
        r4 = r43;
        r5 = r11;
        r6 = r10;
        r7 = r8;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        return r12;
    L_0x04bf:
        r47 = r2;
        r9 = r3;
        r43 = r4;
        r2 = r1.timescale;
        r10 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        com.google.android.exoplayer2.util.Util.scaleLargeTimestampsInPlace(r5, r10, r2);
        r10 = new com.google.android.exoplayer2.extractor.mp4.TrackSampleTable;
        r0 = r10;
        r2 = r47;
        r3 = r9;
        r4 = r43;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        return r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseStbl(com.google.android.exoplayer2.extractor.mp4.Track, com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom, com.google.android.exoplayer2.extractor.GaplessInfoHolder):com.google.android.exoplayer2.extractor.mp4.TrackSampleTable");
    }

    public static Metadata parseUdta(LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseMetaAtom(parsableByteArray, position + readInt);
            }
            parsableByteArray.skipBytes(readInt - 8);
        }
        return null;
    }

    private static Metadata parseMetaAtom(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.skipBytes(readInt - 8);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        List arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        return arrayList.isEmpty() ? null : new Metadata(arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (parsableByteArray.data[position + i3] != (byte) -1) {
                position = 0;
                break;
            }
        }
        position = 1;
        long j = C.TIME_UNSET;
        if (position != 0) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt != 0) {
                j = readUnsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        i = parsableByteArray.readInt();
        parseFullAtomVersion = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        if (i == 0 && parseFullAtomVersion == 65536 && readInt2 == SupportMenu.CATEGORY_MASK && readInt3 == 0) {
            i2 = 90;
        } else if (i == 0 && parseFullAtomVersion == SupportMenu.CATEGORY_MASK && readInt2 == 65536 && readInt3 == 0) {
            i2 = 270;
        } else if (i == SupportMenu.CATEGORY_MASK && parseFullAtomVersion == 0 && readInt2 == 0 && readInt3 == SupportMenu.CATEGORY_MASK) {
            i2 = 180;
        }
        return new TkhdData(readInt, j, i2);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        int readInt = parsableByteArray.readInt();
        if (readInt == TYPE_soun) {
            return 1;
        }
        if (readInt == TYPE_vide) {
            return 2;
        }
        if (readInt == TYPE_text || readInt == TYPE_sbtl || readInt == TYPE_subt || readInt == TYPE_clcp) {
            return 3;
        }
        return readInt == TYPE_meta ? 4 : -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        parsableByteArray.skipBytes(i);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append((char) (((readUnsignedShort >> 10) & 31) + 96));
        stringBuilder.append((char) (((readUnsignedShort >> 5) & 31) + 96));
        stringBuilder.append((char) ((readUnsignedShort & 31) + 96));
        return Pair.create(Long.valueOf(readUnsignedInt), stringBuilder.toString());
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            Assertions.checkArgument(readInt2 > 0, "childAtomSize should be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == Atom.TYPE_avc1 || readInt3 == Atom.TYPE_avc3 || readInt3 == Atom.TYPE_encv || readInt3 == Atom.TYPE_mp4v || readInt3 == Atom.TYPE_hvc1 || readInt3 == Atom.TYPE_hev1 || readInt3 == Atom.TYPE_s263 || readInt3 == Atom.TYPE_vp08 || readInt3 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray2, readInt3, position, readInt2, i, i2, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_mp4a || readInt3 == Atom.TYPE_enca || readInt3 == Atom.TYPE_ac_3 || readInt3 == Atom.TYPE_ec_3 || readInt3 == Atom.TYPE_dtsc || readInt3 == Atom.TYPE_dtse || readInt3 == Atom.TYPE_dtsh || readInt3 == Atom.TYPE_dtsl || readInt3 == Atom.TYPE_samr || readInt3 == Atom.TYPE_sawb || readInt3 == Atom.TYPE_lpcm || readInt3 == Atom.TYPE_sowt || readInt3 == Atom.TYPE__mp3 || readInt3 == Atom.TYPE_alac || readInt3 == Atom.TYPE_alaw || readInt3 == Atom.TYPE_ulaw || readInt3 == Atom.TYPE_Opus || readInt3 == Atom.TYPE_fLaC) {
                parseAudioSampleEntry(parsableByteArray2, readInt3, position, readInt2, i, str, z, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_TTML || readInt3 == Atom.TYPE_tx3g || readInt3 == Atom.TYPE_wvtt || readInt3 == Atom.TYPE_stpp || readInt3 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray2, readInt3, position, readInt2, i, str, stsdData);
            } else if (readInt3 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), MimeTypes.APPLICATION_CAMERA_MOTION, null, -1, null);
            }
            parsableByteArray2.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        String str2;
        String str3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i5 = i;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition((i2 + 8) + 8);
        List list = null;
        long j = Long.MAX_VALUE;
        if (i5 == Atom.TYPE_TTML) {
            str2 = MimeTypes.APPLICATION_TTML;
        } else if (i5 == Atom.TYPE_tx3g) {
            String str4 = MimeTypes.APPLICATION_TX3G;
            int i6 = (i3 - 8) - 8;
            byte[] bArr = new byte[i6];
            parsableByteArray2.readBytes(bArr, 0, i6);
            list = Collections.singletonList(bArr);
            str3 = str4;
            stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str3, null, -1, 0, str, -1, null, j, list);
        } else if (i5 == Atom.TYPE_wvtt) {
            str2 = MimeTypes.APPLICATION_MP4VTT;
        } else if (i5 == Atom.TYPE_stpp) {
            str2 = MimeTypes.APPLICATION_TTML;
            j = 0;
        } else if (i5 == Atom.TYPE_c608) {
            str2 = MimeTypes.APPLICATION_MP4CEA608;
            stsdData2.requiredSampleTransformation = 1;
        } else {
            throw new IllegalStateException();
        }
        str3 = str2;
        stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str3, null, -1, 0, str, -1, null, j, list);
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x014a A:{RETURN} */
    private static void parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray r22, int r23, int r24, int r25, int r26, int r27, com.google.android.exoplayer2.drm.DrmInitData r28, com.google.android.exoplayer2.extractor.mp4.AtomParsers.StsdData r29, int r30) throws com.google.android.exoplayer2.ParserException {
        /*
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r3 = r28;
        r4 = r29;
        r5 = r1 + 8;
        r5 = r5 + 8;
        r0.setPosition(r5);
        r5 = 16;
        r0.skipBytes(r5);
        r11 = r22.readUnsignedShort();
        r12 = r22.readUnsignedShort();
        r5 = 50;
        r0.skipBytes(r5);
        r5 = r22.getPosition();
        r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_encv;
        r7 = 0;
        r8 = r23;
        if (r8 != r6) goto L_0x0055;
    L_0x002e:
        r6 = parseSampleEntryEncryptionData(r0, r1, r2);
        if (r6 == 0) goto L_0x0052;
    L_0x0034:
        r8 = r6.first;
        r8 = (java.lang.Integer) r8;
        r8 = r8.intValue();
        if (r3 != 0) goto L_0x0040;
    L_0x003e:
        r3 = r7;
        goto L_0x004a;
    L_0x0040:
        r9 = r6.second;
        r9 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r9;
        r9 = r9.schemeType;
        r3 = r3.copyWithSchemeType(r9);
    L_0x004a:
        r9 = r4.trackEncryptionBoxes;
        r6 = r6.second;
        r6 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r6;
        r9[r30] = r6;
    L_0x0052:
        r0.setPosition(r5);
    L_0x0055:
        r20 = r3;
        r3 = -1;
        r9 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r18 = r3;
        r14 = r7;
        r17 = r14;
        r16 = r9;
        r3 = 0;
    L_0x0062:
        r9 = r5 - r1;
        if (r9 >= r2) goto L_0x0148;
    L_0x0066:
        r0.setPosition(r5);
        r9 = r22.getPosition();
        r10 = r22.readInt();
        if (r10 != 0) goto L_0x007c;
    L_0x0073:
        r13 = r22.getPosition();
        r13 = r13 - r1;
        if (r13 != r2) goto L_0x007c;
    L_0x007a:
        goto L_0x0148;
    L_0x007c:
        if (r10 <= 0) goto L_0x0080;
    L_0x007e:
        r15 = 1;
        goto L_0x0081;
    L_0x0080:
        r15 = 0;
    L_0x0081:
        r6 = "childAtomSize should be positive";
        com.google.android.exoplayer2.util.Assertions.checkArgument(r15, r6);
        r6 = r22.readInt();
        r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_avcC;
        r13 = 3;
        if (r6 != r15) goto L_0x00b0;
    L_0x008f:
        if (r7 != 0) goto L_0x0093;
    L_0x0091:
        r6 = 1;
        goto L_0x0094;
    L_0x0093:
        r6 = 0;
    L_0x0094:
        com.google.android.exoplayer2.util.Assertions.checkState(r6);
        r7 = "video/avc";
        r9 = r9 + 8;
        r0.setPosition(r9);
        r6 = com.google.android.exoplayer2.video.AvcConfig.parse(r22);
        r14 = r6.initializationData;
        r9 = r6.nalUnitLengthFieldLength;
        r4.nalUnitLengthFieldLength = r9;
        if (r3 != 0) goto L_0x0145;
    L_0x00aa:
        r6 = r6.pixelWidthAspectRatio;
        r16 = r6;
        goto L_0x0145;
    L_0x00b0:
        r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_hvcC;
        if (r6 != r15) goto L_0x00cf;
    L_0x00b4:
        if (r7 != 0) goto L_0x00b8;
    L_0x00b6:
        r6 = 1;
        goto L_0x00b9;
    L_0x00b8:
        r6 = 0;
    L_0x00b9:
        com.google.android.exoplayer2.util.Assertions.checkState(r6);
        r7 = "video/hevc";
        r9 = r9 + 8;
        r0.setPosition(r9);
        r6 = com.google.android.exoplayer2.video.HevcConfig.parse(r22);
        r14 = r6.initializationData;
        r6 = r6.nalUnitLengthFieldLength;
        r4.nalUnitLengthFieldLength = r6;
        goto L_0x0145;
    L_0x00cf:
        r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vpcC;
        if (r6 != r15) goto L_0x00e7;
    L_0x00d3:
        if (r7 != 0) goto L_0x00d7;
    L_0x00d5:
        r6 = 1;
        goto L_0x00d8;
    L_0x00d7:
        r6 = 0;
    L_0x00d8:
        com.google.android.exoplayer2.util.Assertions.checkState(r6);
        r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vp08;
        if (r8 != r6) goto L_0x00e4;
    L_0x00df:
        r6 = "video/x-vnd.on2.vp8";
    L_0x00e1:
        r7 = r6;
        goto L_0x0145;
    L_0x00e4:
        r6 = "video/x-vnd.on2.vp9";
        goto L_0x00e1;
    L_0x00e7:
        r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_d263;
        if (r6 != r15) goto L_0x00f6;
    L_0x00eb:
        if (r7 != 0) goto L_0x00ef;
    L_0x00ed:
        r6 = 1;
        goto L_0x00f0;
    L_0x00ef:
        r6 = 0;
    L_0x00f0:
        com.google.android.exoplayer2.util.Assertions.checkState(r6);
        r7 = "video/3gpp";
        goto L_0x0145;
    L_0x00f6:
        r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_esds;
        if (r6 != r15) goto L_0x0111;
    L_0x00fa:
        if (r7 != 0) goto L_0x00fe;
    L_0x00fc:
        r6 = 1;
        goto L_0x00ff;
    L_0x00fe:
        r6 = 0;
    L_0x00ff:
        com.google.android.exoplayer2.util.Assertions.checkState(r6);
        r6 = parseEsdsFromParent(r0, r9);
        r7 = r6.first;
        r7 = (java.lang.String) r7;
        r6 = r6.second;
        r14 = java.util.Collections.singletonList(r6);
        goto L_0x0145;
    L_0x0111:
        r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_pasp;
        if (r6 != r15) goto L_0x011b;
    L_0x0115:
        r16 = parsePaspFromParent(r0, r9);
        r3 = 1;
        goto L_0x0145;
    L_0x011b:
        r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_sv3d;
        if (r6 != r15) goto L_0x0124;
    L_0x011f:
        r17 = parseProjFromParent(r0, r9, r10);
        goto L_0x0145;
    L_0x0124:
        r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_st3d;
        if (r6 != r9) goto L_0x0145;
    L_0x0128:
        r6 = r22.readUnsignedByte();
        r0.skipBytes(r13);
        if (r6 != 0) goto L_0x0145;
    L_0x0131:
        r6 = r22.readUnsignedByte();
        switch(r6) {
            case 0: goto L_0x0143;
            case 1: goto L_0x0140;
            case 2: goto L_0x013c;
            case 3: goto L_0x0139;
            default: goto L_0x0138;
        };
    L_0x0138:
        goto L_0x0145;
    L_0x0139:
        r18 = r13;
        goto L_0x0145;
    L_0x013c:
        r6 = 2;
        r18 = r6;
        goto L_0x0145;
    L_0x0140:
        r18 = 1;
        goto L_0x0145;
    L_0x0143:
        r18 = 0;
    L_0x0145:
        r5 = r5 + r10;
        goto L_0x0062;
    L_0x0148:
        if (r7 != 0) goto L_0x014b;
    L_0x014a:
        return;
    L_0x014b:
        r6 = java.lang.Integer.toString(r26);
        r8 = 0;
        r9 = -1;
        r10 = -1;
        r13 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r19 = 0;
        r15 = r27;
        r0 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
        r4.format = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray, int, int, int, int, int, com.google.android.exoplayer2.drm.DrmInitData, com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    private static Pair<long[], long[]> parseEdts(ContainerAtom containerAtom) {
        if (containerAtom != null) {
            LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst);
            if (leafAtomOfType != null) {
                ParsableByteArray parsableByteArray = leafAtomOfType.data;
                parsableByteArray.setPosition(8);
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                long[] jArr = new long[readUnsignedIntToInt];
                long[] jArr2 = new long[readUnsignedIntToInt];
                for (int i = 0; i < readUnsignedIntToInt; i++) {
                    jArr[i] = parseFullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
                    jArr2[i] = parseFullAtomVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
                    if (parsableByteArray.readShort() != (short) 1) {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                    parsableByteArray.skipBytes(2);
                }
                return Pair.create(jArr, jArr2);
            }
        }
        return Pair.create(null, null);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) throws ParserException {
        int readUnsignedShort;
        int readUnsignedShort2;
        int readUnsignedFixedPoint1616;
        Object obj;
        String str2;
        DrmInitData drmInitData2;
        StsdData stsdData2;
        int i6;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i7 = i2;
        int i8 = i3;
        String str3 = str;
        DrmInitData drmInitData3 = drmInitData;
        StsdData stsdData3 = stsdData;
        int i9 = 8;
        parsableByteArray2.setPosition((i7 + 8) + 8);
        boolean z2 = false;
        if (z) {
            readUnsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray2.skipBytes(6);
        } else {
            parsableByteArray2.skipBytes(8);
            readUnsignedShort = 0;
        }
        int i10 = 2;
        if (readUnsignedShort == 0 || readUnsignedShort == 1) {
            readUnsignedShort2 = parsableByteArray.readUnsignedShort();
            parsableByteArray2.skipBytes(6);
            readUnsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            if (readUnsignedShort == 1) {
                parsableByteArray2.skipBytes(16);
            }
            readUnsignedShort = readUnsignedShort2;
        } else if (readUnsignedShort == 2) {
            parsableByteArray2.skipBytes(16);
            readUnsignedFixedPoint1616 = (int) Math.round(parsableByteArray.readDouble());
            readUnsignedShort = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray2.skipBytes(20);
        } else {
            return;
        }
        int position = parsableByteArray.getPosition();
        int i11 = i;
        if (i11 == Atom.TYPE_enca) {
            Pair parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray2, i7, i8);
            if (parseSampleEntryEncryptionData != null) {
                i11 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData3 == null) {
                    drmInitData3 = null;
                } else {
                    drmInitData3 = drmInitData3.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData3.trackEncryptionBoxes[i5] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray2.setPosition(position);
        }
        DrmInitData drmInitData4 = drmInitData3;
        String str4 = i11 == Atom.TYPE_ac_3 ? MimeTypes.AUDIO_AC3 : i11 == Atom.TYPE_ec_3 ? MimeTypes.AUDIO_E_AC3 : i11 == Atom.TYPE_dtsc ? MimeTypes.AUDIO_DTS : (i11 == Atom.TYPE_dtsh || i11 == Atom.TYPE_dtsl) ? MimeTypes.AUDIO_DTS_HD : i11 == Atom.TYPE_dtse ? MimeTypes.AUDIO_DTS_EXPRESS : i11 == Atom.TYPE_samr ? MimeTypes.AUDIO_AMR_NB : i11 == Atom.TYPE_sawb ? MimeTypes.AUDIO_AMR_WB : (i11 == Atom.TYPE_lpcm || i11 == Atom.TYPE_sowt) ? MimeTypes.AUDIO_RAW : i11 == Atom.TYPE__mp3 ? MimeTypes.AUDIO_MPEG : i11 == Atom.TYPE_alac ? MimeTypes.AUDIO_ALAC : i11 == Atom.TYPE_alaw ? MimeTypes.AUDIO_ALAW : i11 == Atom.TYPE_ulaw ? MimeTypes.AUDIO_MLAW : i11 == Atom.TYPE_Opus ? MimeTypes.AUDIO_OPUS : i11 == Atom.TYPE_fLaC ? MimeTypes.AUDIO_FLAC : null;
        String str5 = str4;
        int i12 = readUnsignedFixedPoint1616;
        int i13 = readUnsignedShort;
        i11 = position;
        Object obj2 = null;
        while (i11 - i7 < i8) {
            boolean z3;
            int i14;
            Object obj3;
            Object obj4;
            parsableByteArray2.setPosition(i11);
            readUnsignedShort = parsableByteArray.readInt();
            Assertions.checkArgument(readUnsignedShort > 0 ? true : z2, "childAtomSize should be positive");
            int readInt = parsableByteArray.readInt();
            if (readInt == Atom.TYPE_esds || (z && readInt == Atom.TYPE_wave)) {
                String str6;
                obj = obj2;
                str2 = str5;
                position = i11;
                drmInitData2 = drmInitData4;
                z3 = z2;
                i14 = i9;
                stsdData2 = stsdData3;
                i6 = 2;
                if (readInt == Atom.TYPE_esds) {
                    i11 = position;
                } else {
                    i11 = findEsdsPosition(parsableByteArray2, position, readUnsignedShort);
                }
                if (i11 != -1) {
                    Pair parseEsdsFromParent = parseEsdsFromParent(parsableByteArray2, i11);
                    str6 = (String) parseEsdsFromParent.first;
                    obj3 = (byte[]) parseEsdsFromParent.second;
                    if (MimeTypes.AUDIO_AAC.equals(str6)) {
                        Pair parseAacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(obj3);
                        i12 = ((Integer) parseAacAudioSpecificConfig.first).intValue();
                        i13 = ((Integer) parseAacAudioSpecificConfig.second).intValue();
                    }
                } else {
                    str6 = str2;
                    obj3 = obj;
                }
                str2 = str6;
            } else {
                if (readInt == Atom.TYPE_dac3) {
                    parsableByteArray2.setPosition(i9 + i11);
                    stsdData3.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray2, Integer.toString(i4), str3, drmInitData4);
                } else if (readInt == Atom.TYPE_dec3) {
                    parsableByteArray2.setPosition(i9 + i11);
                    stsdData3.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray2, Integer.toString(i4), str3, drmInitData4);
                } else {
                    int i15;
                    int i16;
                    if (readInt == Atom.TYPE_ddts) {
                        i15 = readUnsignedShort;
                        str2 = str5;
                        i16 = i11;
                        drmInitData2 = drmInitData4;
                        obj = obj2;
                        i6 = 2;
                        i14 = i9;
                        stsdData2 = stsdData3;
                        stsdData2.format = Format.createAudioSampleFormat(Integer.toString(i4), str5, null, -1, -1, i13, i12, null, drmInitData2, 0, str3);
                        readUnsignedShort = i15;
                        position = i16;
                        z3 = false;
                    } else {
                        i15 = readUnsignedShort;
                        obj = obj2;
                        str2 = str5;
                        i16 = i11;
                        drmInitData2 = drmInitData4;
                        i14 = i9;
                        stsdData2 = stsdData3;
                        i6 = 2;
                        if (readInt == Atom.TYPE_alac) {
                            readUnsignedShort = i15;
                            obj3 = new byte[readUnsignedShort];
                            position = i16;
                            parsableByteArray2.setPosition(position);
                            z3 = false;
                            parsableByteArray2.readBytes(obj3, 0, readUnsignedShort);
                        } else {
                            readUnsignedShort = i15;
                            position = i16;
                            z3 = false;
                            if (readInt == Atom.TYPE_dOps) {
                                readInt = readUnsignedShort - 8;
                                obj4 = new byte[(opusMagic.length + readInt)];
                                System.arraycopy(opusMagic, 0, obj4, 0, opusMagic.length);
                                parsableByteArray2.setPosition(position + 8);
                                parsableByteArray2.readBytes(obj4, opusMagic.length, readInt);
                            } else if (readUnsignedShort == Atom.TYPE_dfLa) {
                                readInt = readUnsignedShort - 12;
                                obj4 = new byte[readInt];
                                parsableByteArray2.setPosition(position + 12);
                                parsableByteArray2.readBytes(obj4, 0, readInt);
                            }
                            i11 = position + readUnsignedShort;
                            stsdData3 = stsdData2;
                            obj2 = obj4;
                            z2 = z3;
                            drmInitData4 = drmInitData2;
                            i10 = i6;
                            i9 = i14;
                            str5 = str2;
                            i8 = i3;
                        }
                    }
                    obj4 = obj;
                    i11 = position + readUnsignedShort;
                    stsdData3 = stsdData2;
                    obj2 = obj4;
                    z2 = z3;
                    drmInitData4 = drmInitData2;
                    i10 = i6;
                    i9 = i14;
                    str5 = str2;
                    i8 = i3;
                }
                obj = obj2;
                str2 = str5;
                position = i11;
                drmInitData2 = drmInitData4;
                z3 = z2;
                i14 = i9;
                stsdData2 = stsdData3;
                i6 = 2;
                obj4 = obj;
                i11 = position + readUnsignedShort;
                stsdData3 = stsdData2;
                obj2 = obj4;
                z2 = z3;
                drmInitData4 = drmInitData2;
                i10 = i6;
                i9 = i14;
                str5 = str2;
                i8 = i3;
            }
            obj4 = obj3;
            i11 = position + readUnsignedShort;
            stsdData3 = stsdData2;
            obj2 = obj4;
            z2 = z3;
            drmInitData4 = drmInitData2;
            i10 = i6;
            i9 = i14;
            str5 = str2;
            i8 = i3;
        }
        obj = obj2;
        str2 = str5;
        drmInitData2 = drmInitData4;
        i6 = i10;
        stsdData2 = stsdData3;
        if (stsdData2.format == null) {
            str4 = str2;
            if (str4 != null) {
                List list;
                readUnsignedShort2 = MimeTypes.AUDIO_RAW.equals(str4) ? i6 : -1;
                String num = Integer.toString(i4);
                Object obj5 = obj;
                if (obj5 == null) {
                    list = null;
                } else {
                    list = Collections.singletonList(obj5);
                }
                stsdData2.format = Format.createAudioSampleFormat(num, str4, null, -1, -1, i13, i12, readUnsignedShort2, list, drmInitData2, 0, str3);
            }
        }
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition((i + 8) + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        i = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf) {
                Pair parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt);
                if (parseCommonEncryptionSinfFromParent != null) {
                    return parseCommonEncryptionSinfFromParent;
                }
            }
            position += readInt;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        int i4 = -1;
        Object obj = null;
        Object obj2 = obj;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_frma) {
                obj2 = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                obj = parsableByteArray.readString(4);
            } else if (readInt2 == Atom.TYPE_schi) {
                i4 = i3;
                i5 = readInt;
            }
            i3 += readInt;
        }
        if (!C.CENC_TYPE_cenc.equals(obj) && !C.CENC_TYPE_cbc1.equals(obj) && !C.CENC_TYPE_cens.equals(obj) && !C.CENC_TYPE_cbcs.equals(obj)) {
            return null;
        }
        boolean z = true;
        Assertions.checkArgument(obj2 != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, obj);
        if (parseSchiFromParent == null) {
            z = false;
        }
        Assertions.checkArgument(z, "tenc atom is mandatory");
        return Pair.create(obj2, parseSchiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3 = i + 8;
        while (true) {
            TrackEncryptionBox trackEncryptionBox = null;
            if (i3 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                int i4;
                int i5;
                i = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (i == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i5 = i4;
                } else {
                    i = parsableByteArray.readUnsignedByte();
                    i5 = i & 15;
                    i4 = (i & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                byte[] bArr = new byte[16];
                parsableByteArray.readBytes(bArr, 0, bArr.length);
                if (z && readUnsignedByte == 0) {
                    i = parsableByteArray.readUnsignedByte();
                    trackEncryptionBox = new byte[i];
                    parsableByteArray.readBytes(trackEncryptionBox, 0, i);
                }
                return new TrackEncryptionBox(z, str, readUnsignedByte, bArr, i4, i5, trackEncryptionBox);
            }
            i3 += readInt;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        int constrainValue = Util.constrainValue(3, 0, length);
        int constrainValue2 = Util.constrainValue(jArr.length - 3, 0, length);
        if (jArr[0] > j2 || j2 >= jArr[constrainValue] || jArr[constrainValue2] >= j3 || j3 > j) {
            return false;
        }
        return true;
    }

    private AtomParsers() {
    }
}
