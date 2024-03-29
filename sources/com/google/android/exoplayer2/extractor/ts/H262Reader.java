package com.google.android.exoplayer2.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.TrackIdGenerator;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;
import java.util.Collections;

public final class H262Reader implements ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES = new double[]{23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private static final int START_EXTENSION = 181;
    private static final int START_GROUP = 184;
    private static final int START_PICTURE = 0;
    private static final int START_SEQUENCE_HEADER = 179;
    private static final int START_USER_DATA = 178;
    private final CsdBuffer csdBuffer;
    private String formatId;
    private long frameDurationUs;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private boolean sampleHasPicture;
    private boolean sampleIsKeyframe;
    private long samplePosition;
    private long sampleTimeUs;
    private boolean startedFirstSample;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer userData;
    private final ParsableByteArray userDataParsable;
    private final UserDataReader userDataReader;

    private static final class CsdBuffer {
        private static final byte[] START_CODE = new byte[]{(byte) 0, (byte) 0, (byte) 1};
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        public CsdBuffer(int i) {
            this.data = new byte[i];
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }

        public boolean onStartCode(int i, int i2) {
            if (this.isFilling) {
                this.length -= i2;
                if (this.sequenceExtensionPosition == 0 && i == H262Reader.START_EXTENSION) {
                    this.sequenceExtensionPosition = this.length;
                } else {
                    this.isFilling = false;
                    return true;
                }
            } else if (i == H262Reader.START_SEQUENCE_HEADER) {
                this.isFilling = true;
            }
            onData(START_CODE, 0, START_CODE.length);
            return false;
        }

        public void onData(byte[] bArr, int i, int i2) {
            if (this.isFilling) {
                i2 -= i;
                if (this.data.length < this.length + i2) {
                    this.data = Arrays.copyOf(this.data, (this.length + i2) * 2);
                }
                System.arraycopy(bArr, i, this.data, this.length, i2);
                this.length += i2;
            }
        }
    }

    public void packetFinished() {
    }

    public H262Reader() {
        this(null);
    }

    public H262Reader(UserDataReader userDataReader) {
        this.userDataReader = userDataReader;
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(128);
        if (userDataReader != null) {
            this.userData = new NalUnitTargetBuffer(START_USER_DATA, 128);
            this.userDataParsable = new ParsableByteArray();
            return;
        }
        this.userData = null;
        this.userDataParsable = null;
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        if (this.userDataReader != null) {
            this.userData.reset();
        }
        this.totalBytesWritten = 0;
        this.startedFirstSample = false;
    }

    public void createTracks(ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        if (this.userDataReader != null) {
            this.userDataReader.createTracks(extractorOutput, trackIdGenerator);
        }
    }

    public void packetStarted(long j, int i) {
        this.pesTimeUs = j;
    }

    public void consume(ParsableByteArray parsableByteArray) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] bArr = parsableByteArray2.data;
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray2, parsableByteArray.bytesLeft());
        while (true) {
            int findNalUnit = NalUnitUtil.findNalUnit(bArr, position, limit, this.prefixFlags);
            if (findNalUnit == limit) {
                break;
            }
            int i;
            byte[] bArr2;
            int i2 = findNalUnit + 3;
            int i3 = parsableByteArray2.data[i2] & 255;
            int i4 = findNalUnit - position;
            boolean z = false;
            if (!this.hasOutputFormat) {
                if (i4 > 0) {
                    this.csdBuffer.onData(bArr, position, findNalUnit);
                }
                if (this.csdBuffer.onStartCode(i3, i4 < 0 ? -i4 : 0)) {
                    Pair parseCsdBuffer = parseCsdBuffer(this.csdBuffer, this.formatId);
                    this.output.format((Format) parseCsdBuffer.first);
                    this.frameDurationUs = ((Long) parseCsdBuffer.second).longValue();
                    this.hasOutputFormat = true;
                }
            }
            if (this.userDataReader != null) {
                if (i4 > 0) {
                    this.userData.appendToNalUnit(bArr, position, findNalUnit);
                    position = 0;
                } else {
                    position = -i4;
                }
                if (this.userData.endNalUnit(position)) {
                    this.userDataParsable.reset(this.userData.nalData, NalUnitUtil.unescapeStream(this.userData.nalData, this.userData.nalLength));
                    this.userDataReader.consume(this.sampleTimeUs, this.userDataParsable);
                }
                if (i3 == START_USER_DATA && parsableByteArray2.data[findNalUnit + 2] == (byte) 1) {
                    this.userData.startNalUnit(i3);
                }
            }
            if (i3 == 0 || i3 == START_SEQUENCE_HEADER) {
                position = limit - findNalUnit;
                if (this.startedFirstSample && this.sampleHasPicture && this.hasOutputFormat) {
                    i = limit;
                    bArr2 = bArr;
                    this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe, ((int) (this.totalBytesWritten - this.samplePosition)) - position, position, null);
                } else {
                    i = limit;
                    bArr2 = bArr;
                }
                if (!this.startedFirstSample || this.sampleHasPicture) {
                    this.samplePosition = this.totalBytesWritten - ((long) position);
                    long j = this.pesTimeUs != C.TIME_UNSET ? this.pesTimeUs : this.startedFirstSample ? this.sampleTimeUs + this.frameDurationUs : 0;
                    this.sampleTimeUs = j;
                    this.sampleIsKeyframe = false;
                    this.pesTimeUs = C.TIME_UNSET;
                    this.startedFirstSample = true;
                }
                if (i3 == 0) {
                    z = true;
                }
                this.sampleHasPicture = z;
            } else {
                if (i3 == START_GROUP) {
                    this.sampleIsKeyframe = true;
                }
                i = limit;
                bArr2 = bArr;
            }
            position = i2;
            limit = i;
            bArr = bArr2;
        }
        if (!this.hasOutputFormat) {
            this.csdBuffer.onData(bArr, position, limit);
        }
        if (this.userDataReader != null) {
            this.userData.appendToNalUnit(bArr, position, limit);
        }
    }

    private static Pair<Format, Long> parseCsdBuffer(CsdBuffer csdBuffer, String str) {
        float f;
        CsdBuffer csdBuffer2 = csdBuffer;
        byte[] copyOf = Arrays.copyOf(csdBuffer2.data, csdBuffer2.length);
        int i = copyOf[5] & 255;
        int i2 = ((copyOf[4] & 255) << 4) | (i >> 4);
        int i3 = ((i & 15) << 8) | (copyOf[6] & 255);
        switch ((copyOf[7] & PsExtractor.VIDEO_STREAM_MASK) >> 4) {
            case 2:
                f = ((float) (4 * i3)) / ((float) (3 * i2));
                break;
            case 3:
                f = ((float) (16 * i3)) / ((float) (9 * i2));
                break;
            case 4:
                f = ((float) (121 * i3)) / ((float) (100 * i2));
                break;
            default:
                f = 1.0f;
                break;
        }
        Format createVideoSampleFormat = Format.createVideoSampleFormat(str, MimeTypes.VIDEO_MPEG2, null, -1, -1, i2, i3, -1.0f, Collections.singletonList(copyOf), -1, f, null);
        long j = 0;
        int i4 = (copyOf[7] & 15) - 1;
        if (i4 >= 0 && i4 < FRAME_RATE_VALUES.length) {
            double d = FRAME_RATE_VALUES[i4];
            int i5 = csdBuffer2.sequenceExtensionPosition + 9;
            i4 = (copyOf[i5] & 96) >> 5;
            i5 = copyOf[i5] & 31;
            if (i4 != i5) {
                d *= (((double) i4) + 1.0d) / ((double) (i5 + 1));
            }
            j = (long) (1000000.0d / d);
        }
        return Pair.create(createVideoSampleFormat, Long.valueOf(j));
    }
}
