package com.google.android.exoplayer2.extractor.ts;

import android.support.v4.view.InputDeviceCompat;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap.Unseekable;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.TrackIdGenerator;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.io.IOException;

public final class PsExtractor implements Extractor {
    public static final int AUDIO_STREAM = 192;
    public static final int AUDIO_STREAM_MASK = 224;
    public static final ExtractorsFactory FACTORY = PsExtractor$$Lambda$0.$instance;
    private static final long MAX_SEARCH_LENGTH = 1048576;
    private static final long MAX_SEARCH_LENGTH_AFTER_AUDIO_AND_VIDEO_FOUND = 8192;
    private static final int MAX_STREAM_ID_PLUS_ONE = 256;
    static final int MPEG_PROGRAM_END_CODE = 441;
    static final int PACKET_START_CODE_PREFIX = 1;
    static final int PACK_START_CODE = 442;
    public static final int PRIVATE_STREAM_1 = 189;
    static final int SYSTEM_HEADER_START_CODE = 443;
    public static final int VIDEO_STREAM = 224;
    public static final int VIDEO_STREAM_MASK = 240;
    private final PsDurationReader durationReader;
    private boolean foundAllTracks;
    private boolean foundAudioTrack;
    private boolean foundVideoTrack;
    private boolean hasOutputSeekMap;
    private long lastTrackPosition;
    private ExtractorOutput output;
    private PsBinarySearchSeeker psBinarySearchSeeker;
    private final ParsableByteArray psPacketBuffer;
    private final SparseArray<PesReader> psPayloadReaders;
    private final TimestampAdjuster timestampAdjuster;

    private static final class PesReader {
        private static final int PES_SCRATCH_SIZE = 64;
        private boolean dtsFlag;
        private int extendedHeaderLength;
        private final ElementaryStreamReader pesPayloadReader;
        private final ParsableBitArray pesScratch = new ParsableBitArray(new byte[64]);
        private boolean ptsFlag;
        private boolean seenFirstDts;
        private long timeUs;
        private final TimestampAdjuster timestampAdjuster;

        public PesReader(ElementaryStreamReader elementaryStreamReader, TimestampAdjuster timestampAdjuster) {
            this.pesPayloadReader = elementaryStreamReader;
            this.timestampAdjuster = timestampAdjuster;
        }

        public void seek() {
            this.seenFirstDts = false;
            this.pesPayloadReader.seek();
        }

        public void consume(ParsableByteArray parsableByteArray) throws ParserException {
            parsableByteArray.readBytes(this.pesScratch.data, 0, 3);
            this.pesScratch.setPosition(0);
            parseHeader();
            parsableByteArray.readBytes(this.pesScratch.data, 0, this.extendedHeaderLength);
            this.pesScratch.setPosition(0);
            parseHeaderExtension();
            this.pesPayloadReader.packetStarted(this.timeUs, 4);
            this.pesPayloadReader.consume(parsableByteArray);
            this.pesPayloadReader.packetFinished();
        }

        private void parseHeader() {
            this.pesScratch.skipBits(8);
            this.ptsFlag = this.pesScratch.readBit();
            this.dtsFlag = this.pesScratch.readBit();
            this.pesScratch.skipBits(6);
            this.extendedHeaderLength = this.pesScratch.readBits(8);
        }

        private void parseHeaderExtension() {
            this.timeUs = 0;
            if (this.ptsFlag) {
                this.pesScratch.skipBits(4);
                long readBits = ((long) this.pesScratch.readBits(3)) << 30;
                this.pesScratch.skipBits(1);
                long readBits2 = readBits | ((long) (this.pesScratch.readBits(15) << 15));
                this.pesScratch.skipBits(1);
                long readBits3 = readBits2 | ((long) this.pesScratch.readBits(15));
                this.pesScratch.skipBits(1);
                if (!this.seenFirstDts && this.dtsFlag) {
                    this.pesScratch.skipBits(4);
                    long readBits4 = ((long) this.pesScratch.readBits(3)) << 30;
                    this.pesScratch.skipBits(1);
                    long readBits5 = readBits4 | ((long) (this.pesScratch.readBits(15) << 15));
                    this.pesScratch.skipBits(1);
                    long readBits6 = readBits5 | ((long) this.pesScratch.readBits(15));
                    this.pesScratch.skipBits(1);
                    this.timestampAdjuster.adjustTsTimestamp(readBits6);
                    this.seenFirstDts = true;
                }
                this.timeUs = this.timestampAdjuster.adjustTsTimestamp(readBits3);
            }
        }
    }

    public void release() {
    }

    public PsExtractor() {
        this(new TimestampAdjuster(0));
    }

    public PsExtractor(TimestampAdjuster timestampAdjuster) {
        this.timestampAdjuster = timestampAdjuster;
        this.psPacketBuffer = new ParsableByteArray(4096);
        this.psPayloadReaders = new SparseArray();
        this.durationReader = new PsDurationReader();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        byte[] bArr = new byte[14];
        boolean z = false;
        extractorInput.peekFully(bArr, 0, 14);
        if (PACK_START_CODE != (((((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16)) | ((bArr[2] & 255) << 8)) | (bArr[3] & 255)) || (bArr[4] & 196) != 68 || (bArr[6] & 4) != 4 || (bArr[8] & 4) != 4 || (bArr[9] & 1) != 1 || (bArr[12] & 3) != 3) {
            return false;
        }
        extractorInput.advancePeekPosition(bArr[13] & 7);
        extractorInput.peekFully(bArr, 0, 3);
        if (1 == ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8)) | (bArr[2] & 255))) {
            z = true;
        }
        return z;
    }

    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
    }

    public void seek(long j, long j2) {
        int i = 0;
        if (!((this.timestampAdjuster.getTimestampOffsetUs() == C.TIME_UNSET ? 1 : 0) == 0 && (this.timestampAdjuster.getFirstSampleTimestampUs() == 0 || this.timestampAdjuster.getFirstSampleTimestampUs() == j2))) {
            this.timestampAdjuster.reset();
            this.timestampAdjuster.setFirstSampleTimestampUs(j2);
        }
        if (this.psBinarySearchSeeker != null) {
            this.psBinarySearchSeeker.setSeekTargetUs(j2);
        }
        while (i < this.psPayloadReaders.size()) {
            ((PesReader) this.psPayloadReaders.valueAt(i)).seek();
            i++;
        }
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        long length = extractorInput.getLength();
        if ((length != -1) && !this.durationReader.isDurationReadFinished()) {
            return this.durationReader.readDuration(extractorInput, positionHolder);
        }
        maybeOutputSeekMap(length);
        ElementaryStreamReader elementaryStreamReader = null;
        if (this.psBinarySearchSeeker != null && this.psBinarySearchSeeker.isSeeking()) {
            return this.psBinarySearchSeeker.handlePendingSeek(extractorInput, positionHolder, null);
        }
        extractorInput.resetPeekPosition();
        long peekPosition = length != -1 ? length - extractorInput.getPeekPosition() : -1;
        if ((peekPosition != -1 && peekPosition < 4) || !extractorInput.peekFully(this.psPacketBuffer.data, 0, 4, true)) {
            return -1;
        }
        this.psPacketBuffer.setPosition(0);
        int readInt = this.psPacketBuffer.readInt();
        if (readInt == MPEG_PROGRAM_END_CODE) {
            return -1;
        }
        if (readInt == PACK_START_CODE) {
            extractorInput.peekFully(this.psPacketBuffer.data, 0, 10);
            this.psPacketBuffer.setPosition(9);
            extractorInput.skipFully((this.psPacketBuffer.readUnsignedByte() & 7) + 14);
            return 0;
        } else if (readInt == SYSTEM_HEADER_START_CODE) {
            extractorInput.peekFully(this.psPacketBuffer.data, 0, 2);
            this.psPacketBuffer.setPosition(0);
            extractorInput.skipFully(this.psPacketBuffer.readUnsignedShort() + 6);
            return 0;
        } else if (((readInt & InputDeviceCompat.SOURCE_ANY) >> 8) != 1) {
            extractorInput.skipFully(1);
            return 0;
        } else {
            readInt &= 255;
            PesReader pesReader = (PesReader) this.psPayloadReaders.get(readInt);
            if (!this.foundAllTracks) {
                if (pesReader == null) {
                    if (readInt == PRIVATE_STREAM_1) {
                        elementaryStreamReader = new Ac3Reader();
                        this.foundAudioTrack = true;
                        this.lastTrackPosition = extractorInput.getPosition();
                    } else if ((readInt & 224) == AUDIO_STREAM) {
                        elementaryStreamReader = new MpegAudioReader();
                        this.foundAudioTrack = true;
                        this.lastTrackPosition = extractorInput.getPosition();
                    } else if ((readInt & VIDEO_STREAM_MASK) == 224) {
                        elementaryStreamReader = new H262Reader();
                        this.foundVideoTrack = true;
                        this.lastTrackPosition = extractorInput.getPosition();
                    }
                    if (elementaryStreamReader != null) {
                        elementaryStreamReader.createTracks(this.output, new TrackIdGenerator(readInt, 256));
                        pesReader = new PesReader(elementaryStreamReader, this.timestampAdjuster);
                        this.psPayloadReaders.put(readInt, pesReader);
                    }
                }
                long j = (this.foundAudioTrack && this.foundVideoTrack) ? this.lastTrackPosition + 8192 : 1048576;
                if (extractorInput.getPosition() > j) {
                    this.foundAllTracks = true;
                    this.output.endTracks();
                }
            }
            extractorInput.peekFully(this.psPacketBuffer.data, 0, 2);
            this.psPacketBuffer.setPosition(0);
            readInt = this.psPacketBuffer.readUnsignedShort() + 6;
            if (pesReader == null) {
                extractorInput.skipFully(readInt);
            } else {
                this.psPacketBuffer.reset(readInt);
                extractorInput.readFully(this.psPacketBuffer.data, 0, readInt);
                this.psPacketBuffer.setPosition(6);
                pesReader.consume(this.psPacketBuffer);
                this.psPacketBuffer.setLimit(this.psPacketBuffer.capacity());
            }
            return 0;
        }
    }

    private void maybeOutputSeekMap(long j) {
        if (!this.hasOutputSeekMap) {
            this.hasOutputSeekMap = true;
            if (this.durationReader.getDurationUs() != C.TIME_UNSET) {
                this.psBinarySearchSeeker = new PsBinarySearchSeeker(this.durationReader.getScrTimestampAdjuster(), this.durationReader.getDurationUs(), j);
                this.output.seekMap(this.psBinarySearchSeeker.getSeekMap());
                return;
            }
            this.output.seekMap(new Unseekable(this.durationReader.getDurationUs()));
        }
    }
}
