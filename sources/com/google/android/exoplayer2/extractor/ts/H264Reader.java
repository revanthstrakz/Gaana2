package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.TrackIdGenerator;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.NalUnitUtil.PpsData;
import com.google.android.exoplayer2.util.NalUnitUtil.SpsData;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.ParsableNalUnitBitArray;
import java.util.ArrayList;
import java.util.Arrays;

public final class H264Reader implements ElementaryStreamReader {
    private static final int NAL_UNIT_TYPE_PPS = 8;
    private static final int NAL_UNIT_TYPE_SEI = 6;
    private static final int NAL_UNIT_TYPE_SPS = 7;
    private final boolean allowNonIdrKeyframes;
    private final boolean detectAccessUnits;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(8, 128);
    private final boolean[] prefixFlags = new boolean[3];
    private boolean randomAccessIndicator;
    private SampleReader sampleReader;
    private final NalUnitTargetBuffer sei = new NalUnitTargetBuffer(6, 128);
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper = new ParsableByteArray();
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(7, 128);
    private long totalBytesWritten;

    private static final class SampleReader {
        private static final int DEFAULT_BUFFER_SIZE = 128;
        private static final int NAL_UNIT_TYPE_AUD = 9;
        private static final int NAL_UNIT_TYPE_IDR = 5;
        private static final int NAL_UNIT_TYPE_NON_IDR = 1;
        private static final int NAL_UNIT_TYPE_PARTITION_A = 2;
        private final boolean allowNonIdrKeyframes;
        private final ParsableNalUnitBitArray bitArray = new ParsableNalUnitBitArray(this.buffer, 0, 0);
        private byte[] buffer = new byte[128];
        private int bufferLength;
        private final boolean detectAccessUnits;
        private boolean isFilling;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private int nalUnitType;
        private final TrackOutput output;
        private final SparseArray<PpsData> pps = new SparseArray();
        private SliceHeaderData previousSliceHeader = new SliceHeaderData();
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;
        private SliceHeaderData sliceHeader = new SliceHeaderData();
        private final SparseArray<SpsData> sps = new SparseArray();

        private static final class SliceHeaderData {
            private static final int SLICE_TYPE_ALL_I = 7;
            private static final int SLICE_TYPE_I = 2;
            private boolean bottomFieldFlag;
            private boolean bottomFieldFlagPresent;
            private int deltaPicOrderCnt0;
            private int deltaPicOrderCnt1;
            private int deltaPicOrderCntBottom;
            private boolean fieldPicFlag;
            private int frameNum;
            private boolean hasSliceType;
            private boolean idrPicFlag;
            private int idrPicId;
            private boolean isComplete;
            private int nalRefIdc;
            private int picOrderCntLsb;
            private int picParameterSetId;
            private int sliceType;
            private SpsData spsData;

            private SliceHeaderData() {
            }

            public void clear() {
                this.hasSliceType = false;
                this.isComplete = false;
            }

            public void setSliceType(int i) {
                this.sliceType = i;
                this.hasSliceType = true;
            }

            public void setAll(SpsData spsData, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5, int i6, int i7, int i8, int i9) {
                this.spsData = spsData;
                this.nalRefIdc = i;
                this.sliceType = i2;
                this.frameNum = i3;
                this.picParameterSetId = i4;
                this.fieldPicFlag = z;
                this.bottomFieldFlagPresent = z2;
                this.bottomFieldFlag = z3;
                this.idrPicFlag = z4;
                this.idrPicId = i5;
                this.picOrderCntLsb = i6;
                this.deltaPicOrderCntBottom = i7;
                this.deltaPicOrderCnt0 = i8;
                this.deltaPicOrderCnt1 = i9;
                this.isComplete = true;
                this.hasSliceType = true;
            }

            public boolean isISlice() {
                return this.hasSliceType && (this.sliceType == 7 || this.sliceType == 2);
            }

            private boolean isFirstVclNalUnitOfPicture(SliceHeaderData sliceHeaderData) {
                if (this.isComplete) {
                    if (!sliceHeaderData.isComplete || this.frameNum != sliceHeaderData.frameNum || this.picParameterSetId != sliceHeaderData.picParameterSetId || this.fieldPicFlag != sliceHeaderData.fieldPicFlag) {
                        return true;
                    }
                    if (this.bottomFieldFlagPresent && sliceHeaderData.bottomFieldFlagPresent && this.bottomFieldFlag != sliceHeaderData.bottomFieldFlag) {
                        return true;
                    }
                    if (this.nalRefIdc != sliceHeaderData.nalRefIdc && (this.nalRefIdc == 0 || sliceHeaderData.nalRefIdc == 0)) {
                        return true;
                    }
                    if (this.spsData.picOrderCountType == 0 && sliceHeaderData.spsData.picOrderCountType == 0 && (this.picOrderCntLsb != sliceHeaderData.picOrderCntLsb || this.deltaPicOrderCntBottom != sliceHeaderData.deltaPicOrderCntBottom)) {
                        return true;
                    }
                    if ((this.spsData.picOrderCountType == 1 && sliceHeaderData.spsData.picOrderCountType == 1 && (this.deltaPicOrderCnt0 != sliceHeaderData.deltaPicOrderCnt0 || this.deltaPicOrderCnt1 != sliceHeaderData.deltaPicOrderCnt1)) || this.idrPicFlag != sliceHeaderData.idrPicFlag) {
                        return true;
                    }
                    if (this.idrPicFlag && sliceHeaderData.idrPicFlag && this.idrPicId != sliceHeaderData.idrPicId) {
                        return true;
                    }
                }
                return false;
            }
        }

        public SampleReader(TrackOutput trackOutput, boolean z, boolean z2) {
            this.output = trackOutput;
            this.allowNonIdrKeyframes = z;
            this.detectAccessUnits = z2;
            reset();
        }

        public boolean needsSpsPps() {
            return this.detectAccessUnits;
        }

        public void putSps(SpsData spsData) {
            this.sps.append(spsData.seqParameterSetId, spsData);
        }

        public void putPps(PpsData ppsData) {
            this.pps.append(ppsData.picParameterSetId, ppsData);
        }

        public void reset() {
            this.isFilling = false;
            this.readingSample = false;
            this.sliceHeader.clear();
        }

        public void startNalUnit(long j, int i, long j2) {
            this.nalUnitType = i;
            this.nalUnitTimeUs = j2;
            this.nalUnitStartPosition = j;
            if (!(this.allowNonIdrKeyframes && this.nalUnitType == 1)) {
                if (!this.detectAccessUnits) {
                    return;
                }
                if (!(this.nalUnitType == 5 || this.nalUnitType == 1 || this.nalUnitType == 2)) {
                    return;
                }
            }
            SliceHeaderData sliceHeaderData = this.previousSliceHeader;
            this.previousSliceHeader = this.sliceHeader;
            this.sliceHeader = sliceHeaderData;
            this.sliceHeader.clear();
            this.bufferLength = 0;
            this.isFilling = true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:52:0x0104  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x0102  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0119  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x0153  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x011f  */
        public void appendToNalUnit(byte[] r22, int r23, int r24) {
            /*
            r21 = this;
            r0 = r21;
            r1 = r23;
            r2 = r0.isFilling;
            if (r2 != 0) goto L_0x0009;
        L_0x0008:
            return;
        L_0x0009:
            r2 = r24 - r1;
            r3 = r0.buffer;
            r4 = 2;
            r3 = r3.length;
            r5 = r0.bufferLength;
            r5 = r5 + r2;
            if (r3 >= r5) goto L_0x0020;
        L_0x0014:
            r3 = r0.buffer;
            r5 = r0.bufferLength;
            r5 = r5 + r2;
            r5 = r5 * r4;
            r3 = java.util.Arrays.copyOf(r3, r5);
            r0.buffer = r3;
        L_0x0020:
            r3 = r0.buffer;
            r5 = r0.bufferLength;
            r6 = r22;
            java.lang.System.arraycopy(r6, r1, r3, r5, r2);
            r1 = r0.bufferLength;
            r1 = r1 + r2;
            r0.bufferLength = r1;
            r1 = r0.bitArray;
            r2 = r0.buffer;
            r3 = r0.bufferLength;
            r5 = 0;
            r1.reset(r2, r5, r3);
            r1 = r0.bitArray;
            r2 = 8;
            r1 = r1.canReadBits(r2);
            if (r1 != 0) goto L_0x0043;
        L_0x0042:
            return;
        L_0x0043:
            r1 = r0.bitArray;
            r1.skipBit();
            r1 = r0.bitArray;
            r8 = r1.readBits(r4);
            r1 = r0.bitArray;
            r2 = 5;
            r1.skipBits(r2);
            r1 = r0.bitArray;
            r1 = r1.canReadExpGolombCodedNum();
            if (r1 != 0) goto L_0x005d;
        L_0x005c:
            return;
        L_0x005d:
            r1 = r0.bitArray;
            r1.readUnsignedExpGolombCodedInt();
            r1 = r0.bitArray;
            r1 = r1.canReadExpGolombCodedNum();
            if (r1 != 0) goto L_0x006b;
        L_0x006a:
            return;
        L_0x006b:
            r1 = r0.bitArray;
            r9 = r1.readUnsignedExpGolombCodedInt();
            r1 = r0.detectAccessUnits;
            if (r1 != 0) goto L_0x007d;
        L_0x0075:
            r0.isFilling = r5;
            r1 = r0.sliceHeader;
            r1.setSliceType(r9);
            return;
        L_0x007d:
            r1 = r0.bitArray;
            r1 = r1.canReadExpGolombCodedNum();
            if (r1 != 0) goto L_0x0086;
        L_0x0085:
            return;
        L_0x0086:
            r1 = r0.bitArray;
            r11 = r1.readUnsignedExpGolombCodedInt();
            r1 = r0.pps;
            r1 = r1.indexOfKey(r11);
            if (r1 >= 0) goto L_0x0097;
        L_0x0094:
            r0.isFilling = r5;
            return;
        L_0x0097:
            r1 = r0.pps;
            r1 = r1.get(r11);
            r1 = (com.google.android.exoplayer2.util.NalUnitUtil.PpsData) r1;
            r3 = r0.sps;
            r6 = r1.seqParameterSetId;
            r3 = r3.get(r6);
            r7 = r3;
            r7 = (com.google.android.exoplayer2.util.NalUnitUtil.SpsData) r7;
            r3 = r7.separateColorPlaneFlag;
            if (r3 == 0) goto L_0x00bc;
        L_0x00ae:
            r3 = r0.bitArray;
            r3 = r3.canReadBits(r4);
            if (r3 != 0) goto L_0x00b7;
        L_0x00b6:
            return;
        L_0x00b7:
            r3 = r0.bitArray;
            r3.skipBits(r4);
        L_0x00bc:
            r3 = r0.bitArray;
            r4 = r7.frameNumLength;
            r3 = r3.canReadBits(r4);
            if (r3 != 0) goto L_0x00c7;
        L_0x00c6:
            return;
        L_0x00c7:
            r3 = r0.bitArray;
            r4 = r7.frameNumLength;
            r10 = r3.readBits(r4);
            r3 = r7.frameMbsOnlyFlag;
            r4 = 1;
            if (r3 != 0) goto L_0x00fb;
        L_0x00d4:
            r3 = r0.bitArray;
            r3 = r3.canReadBits(r4);
            if (r3 != 0) goto L_0x00dd;
        L_0x00dc:
            return;
        L_0x00dd:
            r3 = r0.bitArray;
            r3 = r3.readBit();
            if (r3 == 0) goto L_0x00f8;
        L_0x00e5:
            r6 = r0.bitArray;
            r6 = r6.canReadBits(r4);
            if (r6 != 0) goto L_0x00ee;
        L_0x00ed:
            return;
        L_0x00ee:
            r6 = r0.bitArray;
            r6 = r6.readBit();
            r12 = r3;
            r13 = r4;
            r14 = r6;
            goto L_0x00fe;
        L_0x00f8:
            r12 = r3;
            r13 = r5;
            goto L_0x00fd;
        L_0x00fb:
            r12 = r5;
            r13 = r12;
        L_0x00fd:
            r14 = r13;
        L_0x00fe:
            r3 = r0.nalUnitType;
            if (r3 != r2) goto L_0x0104;
        L_0x0102:
            r15 = r4;
            goto L_0x0105;
        L_0x0104:
            r15 = r5;
        L_0x0105:
            if (r15 == 0) goto L_0x0119;
        L_0x0107:
            r2 = r0.bitArray;
            r2 = r2.canReadExpGolombCodedNum();
            if (r2 != 0) goto L_0x0110;
        L_0x010f:
            return;
        L_0x0110:
            r2 = r0.bitArray;
            r2 = r2.readUnsignedExpGolombCodedInt();
            r16 = r2;
            goto L_0x011b;
        L_0x0119:
            r16 = r5;
        L_0x011b:
            r2 = r7.picOrderCountType;
            if (r2 != 0) goto L_0x0153;
        L_0x011f:
            r2 = r0.bitArray;
            r3 = r7.picOrderCntLsbLength;
            r2 = r2.canReadBits(r3);
            if (r2 != 0) goto L_0x012a;
        L_0x0129:
            return;
        L_0x012a:
            r2 = r0.bitArray;
            r3 = r7.picOrderCntLsbLength;
            r2 = r2.readBits(r3);
            r1 = r1.bottomFieldPicOrderInFramePresentFlag;
            if (r1 == 0) goto L_0x014e;
        L_0x0136:
            if (r12 != 0) goto L_0x014e;
        L_0x0138:
            r1 = r0.bitArray;
            r1 = r1.canReadExpGolombCodedNum();
            if (r1 != 0) goto L_0x0141;
        L_0x0140:
            return;
        L_0x0141:
            r1 = r0.bitArray;
            r1 = r1.readSignedExpGolombCodedInt();
            r18 = r1;
            r17 = r2;
            r19 = r5;
            goto L_0x0197;
        L_0x014e:
            r17 = r2;
            r18 = r5;
            goto L_0x0195;
        L_0x0153:
            r2 = r7.picOrderCountType;
            if (r2 != r4) goto L_0x0191;
        L_0x0157:
            r2 = r7.deltaPicOrderAlwaysZeroFlag;
            if (r2 != 0) goto L_0x0191;
        L_0x015b:
            r2 = r0.bitArray;
            r2 = r2.canReadExpGolombCodedNum();
            if (r2 != 0) goto L_0x0164;
        L_0x0163:
            return;
        L_0x0164:
            r2 = r0.bitArray;
            r2 = r2.readSignedExpGolombCodedInt();
            r1 = r1.bottomFieldPicOrderInFramePresentFlag;
            if (r1 == 0) goto L_0x0188;
        L_0x016e:
            if (r12 != 0) goto L_0x0188;
        L_0x0170:
            r1 = r0.bitArray;
            r1 = r1.canReadExpGolombCodedNum();
            if (r1 != 0) goto L_0x0179;
        L_0x0178:
            return;
        L_0x0179:
            r1 = r0.bitArray;
            r1 = r1.readSignedExpGolombCodedInt();
            r20 = r1;
            r19 = r2;
            r17 = r5;
            r18 = r17;
            goto L_0x0199;
        L_0x0188:
            r19 = r2;
            r17 = r5;
            r18 = r17;
            r20 = r18;
            goto L_0x0199;
        L_0x0191:
            r17 = r5;
            r18 = r17;
        L_0x0195:
            r19 = r18;
        L_0x0197:
            r20 = r19;
        L_0x0199:
            r6 = r0.sliceHeader;
            r6.setAll(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
            r0.isFilling = r5;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader.appendToNalUnit(byte[], int, int):void");
        }

        public boolean endNalUnit(long j, int i, boolean z, boolean z2) {
            int i2 = 0;
            if (this.nalUnitType == 9 || (this.detectAccessUnits && this.sliceHeader.isFirstVclNalUnitOfPicture(this.previousSliceHeader))) {
                if (z && this.readingSample) {
                    outputSample(i + ((int) (j - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = false;
                this.readingSample = true;
            }
            if (this.allowNonIdrKeyframes) {
                z2 = this.sliceHeader.isISlice();
            }
            boolean z3 = this.sampleIsKeyframe;
            if (this.nalUnitType == 5 || (z2 && this.nalUnitType == 1)) {
                i2 = 1;
            }
            this.sampleIsKeyframe = z3 | i2;
            return this.sampleIsKeyframe;
        }

        private void outputSample(int i) {
            this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe, (int) (this.nalUnitStartPosition - this.samplePosition), i, null);
        }
    }

    public void packetFinished() {
    }

    public H264Reader(SeiReader seiReader, boolean z, boolean z2) {
        this.seiReader = seiReader;
        this.allowNonIdrKeyframes = z;
        this.detectAccessUnits = z2;
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.sps.reset();
        this.pps.reset();
        this.sei.reset();
        this.sampleReader.reset();
        this.totalBytesWritten = 0;
        this.randomAccessIndicator = false;
    }

    public void createTracks(ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.sampleReader = new SampleReader(this.output, this.allowNonIdrKeyframes, this.detectAccessUnits);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    public void packetStarted(long j, int i) {
        this.pesTimeUs = j;
        this.randomAccessIndicator |= (i & 2) != 0 ? 1 : 0;
    }

    public void consume(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] bArr = parsableByteArray.data;
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int findNalUnit = NalUnitUtil.findNalUnit(bArr, position, limit, this.prefixFlags);
            if (findNalUnit == limit) {
                nalUnitData(bArr, position, limit);
                return;
            }
            int nalUnitType = NalUnitUtil.getNalUnitType(bArr, findNalUnit);
            int i = findNalUnit - position;
            if (i > 0) {
                nalUnitData(bArr, position, findNalUnit);
            }
            int i2 = limit - findNalUnit;
            long j = this.totalBytesWritten - ((long) i2);
            int i3 = i < 0 ? -i : 0;
            long j2 = j;
            endNalUnit(j2, i2, i3, this.pesTimeUs);
            startNalUnit(j2, nalUnitType, this.pesTimeUs);
            position = findNalUnit + 3;
        }
    }

    private void startNalUnit(long j, int i, long j2) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.startNalUnit(i);
            this.pps.startNalUnit(i);
        }
        this.sei.startNalUnit(i);
        this.sampleReader.startNalUnit(j, i, j2);
    }

    private void nalUnitData(byte[] bArr, int i, int i2) {
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.appendToNalUnit(bArr, i, i2);
            this.pps.appendToNalUnit(bArr, i, i2);
        }
        this.sei.appendToNalUnit(bArr, i, i2);
        this.sampleReader.appendToNalUnit(bArr, i, i2);
    }

    private void endNalUnit(long j, int i, int i2, long j2) {
        int i3 = i2;
        if (!this.hasOutputFormat || this.sampleReader.needsSpsPps()) {
            this.sps.endNalUnit(i3);
            this.pps.endNalUnit(i3);
            if (this.hasOutputFormat) {
                if (this.sps.isCompleted()) {
                    this.sampleReader.putSps(NalUnitUtil.parseSpsNalUnit(this.sps.nalData, 3, this.sps.nalLength));
                    this.sps.reset();
                } else if (this.pps.isCompleted()) {
                    this.sampleReader.putPps(NalUnitUtil.parsePpsNalUnit(this.pps.nalData, 3, this.pps.nalLength));
                    this.pps.reset();
                }
            } else if (this.sps.isCompleted() && this.pps.isCompleted()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(Arrays.copyOf(this.sps.nalData, this.sps.nalLength));
                arrayList.add(Arrays.copyOf(this.pps.nalData, this.pps.nalLength));
                SpsData parseSpsNalUnit = NalUnitUtil.parseSpsNalUnit(this.sps.nalData, 3, this.sps.nalLength);
                PpsData parsePpsNalUnit = NalUnitUtil.parsePpsNalUnit(this.pps.nalData, 3, this.pps.nalLength);
                this.output.format(Format.createVideoSampleFormat(this.formatId, MimeTypes.VIDEO_H264, CodecSpecificDataUtil.buildAvcCodecString(parseSpsNalUnit.profileIdc, parseSpsNalUnit.constraintsFlagsAndReservedZero2Bits, parseSpsNalUnit.levelIdc), -1, -1, parseSpsNalUnit.width, parseSpsNalUnit.height, -1.0f, arrayList, -1, parseSpsNalUnit.pixelWidthAspectRatio, null));
                this.hasOutputFormat = true;
                this.sampleReader.putSps(parseSpsNalUnit);
                this.sampleReader.putPps(parsePpsNalUnit);
                this.sps.reset();
                this.pps.reset();
            }
        }
        if (this.sei.endNalUnit(i2)) {
            this.seiWrapper.reset(this.sei.nalData, NalUnitUtil.unescapeStream(this.sei.nalData, this.sei.nalLength));
            this.seiWrapper.setPosition(4);
            this.seiReader.consume(j2, this.seiWrapper);
        }
        if (this.sampleReader.endNalUnit(j, i, this.hasOutputFormat, this.randomAccessIndicator)) {
            this.randomAccessIndicator = false;
        }
    }
}
