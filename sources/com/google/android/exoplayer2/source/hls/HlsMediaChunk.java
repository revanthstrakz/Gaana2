package com.google.android.exoplayer2.source.hls;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class HlsMediaChunk extends MediaChunk {
    public static final String PRIV_TIMESTAMP_FRAME_OWNER = "com.apple.streaming.transportStreamTimestamp";
    private static final AtomicInteger uidSource = new AtomicInteger();
    public final int discontinuitySequenceNumber;
    private final DrmInitData drmInitData;
    private Extractor extractor;
    private final HlsExtractorFactory extractorFactory;
    private final boolean hasGapTag;
    public final HlsUrl hlsUrl;
    private final ParsableByteArray id3Data;
    private final Id3Decoder id3Decoder;
    private final DataSource initDataSource;
    private final DataSpec initDataSpec;
    private boolean initLoadCompleted;
    private int initSegmentBytesLoaded;
    private final boolean isEncrypted;
    private final boolean isMasterTimestampSource;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private final List<Format> muxedCaptionFormats;
    private int nextLoadPosition;
    private HlsSampleStreamWrapper output;
    private final Extractor previousExtractor;
    private final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;

    public HlsMediaChunk(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, DataSpec dataSpec, DataSpec dataSpec2, HlsUrl hlsUrl, List<Format> list, int i, Object obj, long j, long j2, long j3, int i2, boolean z, boolean z2, TimestampAdjuster timestampAdjuster, HlsMediaChunk hlsMediaChunk, DrmInitData drmInitData, byte[] bArr, byte[] bArr2) {
        HlsUrl hlsUrl2 = hlsUrl;
        int i3 = i2;
        byte[] bArr3 = bArr;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        byte[] bArr4 = bArr3;
        super(buildDataSource(dataSource, bArr3, bArr2), dataSpec, hlsUrl2.format, i, obj, j, j2, j3);
        this.discontinuitySequenceNumber = i3;
        this.initDataSpec = dataSpec2;
        this.hlsUrl = hlsUrl2;
        this.isMasterTimestampSource = z2;
        this.timestampAdjuster = timestampAdjuster;
        boolean z3 = true;
        this.isEncrypted = bArr4 != null;
        this.hasGapTag = z;
        this.extractorFactory = hlsExtractorFactory;
        this.muxedCaptionFormats = list;
        this.drmInitData = drmInitData;
        Extractor extractor = null;
        if (hlsMediaChunk2 != null) {
            this.id3Decoder = hlsMediaChunk2.id3Decoder;
            this.id3Data = hlsMediaChunk2.id3Data;
            if (hlsMediaChunk2.hlsUrl == hlsUrl2 && hlsMediaChunk2.loadCompleted) {
                z3 = false;
            }
            this.shouldSpliceIn = z3;
            if (hlsMediaChunk2.discontinuitySequenceNumber == i3 && !this.shouldSpliceIn) {
                extractor = hlsMediaChunk2.extractor;
            }
        } else {
            this.id3Decoder = new Id3Decoder();
            this.id3Data = new ParsableByteArray(10);
            this.shouldSpliceIn = false;
        }
        this.previousExtractor = extractor;
        this.initDataSource = dataSource;
        this.uid = uidSource.getAndIncrement();
    }

    public void init(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.output = hlsSampleStreamWrapper;
    }

    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void load() throws IOException, InterruptedException {
        maybeLoadInitData();
        if (!this.loadCanceled) {
            if (!this.hasGapTag) {
                loadMedia();
            }
            this.loadCompleted = true;
        }
    }

    private void maybeLoadInitData() throws IOException, InterruptedException {
        if (!this.initLoadCompleted && this.initDataSpec != null) {
            DefaultExtractorInput prepareExtraction;
            try {
                prepareExtraction = prepareExtraction(this.initDataSource, this.initDataSpec.subrange((long) this.initSegmentBytesLoaded));
                int i = 0;
                while (i == 0) {
                    if (this.loadCanceled) {
                        break;
                    }
                    i = this.extractor.read(prepareExtraction, null);
                }
                this.initSegmentBytesLoaded = (int) (prepareExtraction.getPosition() - this.initDataSpec.absoluteStreamPosition);
                Util.closeQuietly(this.initDataSource);
                this.initLoadCompleted = true;
            } catch (Throwable th) {
                Util.closeQuietly(this.initDataSource);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f A:{Catch:{ all -> 0x0052, all -> 0x0074 }} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046 A:{SYNTHETIC, Splitter:B:18:0x0046} */
    private void loadMedia() throws java.io.IOException, java.lang.InterruptedException {
        /*
        r8 = this;
        r0 = r8.isEncrypted;
        r1 = 0;
        if (r0 == 0) goto L_0x000d;
    L_0x0005:
        r0 = r8.dataSpec;
        r2 = r8.nextLoadPosition;
        if (r2 == 0) goto L_0x0016;
    L_0x000b:
        r2 = 1;
        goto L_0x0017;
    L_0x000d:
        r0 = r8.dataSpec;
        r2 = r8.nextLoadPosition;
        r2 = (long) r2;
        r0 = r0.subrange(r2);
    L_0x0016:
        r2 = r1;
    L_0x0017:
        r3 = r8.isMasterTimestampSource;
        if (r3 != 0) goto L_0x0021;
    L_0x001b:
        r3 = r8.timestampAdjuster;
        r3.waitUntilInitialized();
        goto L_0x0037;
    L_0x0021:
        r3 = r8.timestampAdjuster;
        r3 = r3.getFirstSampleTimestampUs();
        r5 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 != 0) goto L_0x0037;
    L_0x0030:
        r3 = r8.timestampAdjuster;
        r4 = r8.startTimeUs;
        r3.setFirstSampleTimestampUs(r4);
    L_0x0037:
        r3 = r8.dataSource;	 Catch:{ all -> 0x0074 }
        r0 = r8.prepareExtraction(r3, r0);	 Catch:{ all -> 0x0074 }
        if (r2 == 0) goto L_0x0044;
    L_0x003f:
        r2 = r8.nextLoadPosition;	 Catch:{ all -> 0x0074 }
        r0.skipFully(r2);	 Catch:{ all -> 0x0074 }
    L_0x0044:
        if (r1 != 0) goto L_0x0061;
    L_0x0046:
        r1 = r8.loadCanceled;	 Catch:{ all -> 0x0052 }
        if (r1 != 0) goto L_0x0061;
    L_0x004a:
        r1 = r8.extractor;	 Catch:{ all -> 0x0052 }
        r2 = 0;
        r1 = r1.read(r0, r2);	 Catch:{ all -> 0x0052 }
        goto L_0x0044;
    L_0x0052:
        r1 = move-exception;
        r2 = r0.getPosition();	 Catch:{ all -> 0x0074 }
        r0 = r8.dataSpec;	 Catch:{ all -> 0x0074 }
        r4 = r0.absoluteStreamPosition;	 Catch:{ all -> 0x0074 }
        r6 = r2 - r4;
        r0 = (int) r6;	 Catch:{ all -> 0x0074 }
        r8.nextLoadPosition = r0;	 Catch:{ all -> 0x0074 }
        throw r1;	 Catch:{ all -> 0x0074 }
    L_0x0061:
        r0 = r0.getPosition();	 Catch:{ all -> 0x0074 }
        r2 = r8.dataSpec;	 Catch:{ all -> 0x0074 }
        r2 = r2.absoluteStreamPosition;	 Catch:{ all -> 0x0074 }
        r4 = r0 - r2;
        r0 = (int) r4;	 Catch:{ all -> 0x0074 }
        r8.nextLoadPosition = r0;	 Catch:{ all -> 0x0074 }
        r0 = r8.dataSource;
        com.google.android.exoplayer2.util.Util.closeQuietly(r0);
        return;
    L_0x0074:
        r0 = move-exception;
        r1 = r8.dataSource;
        com.google.android.exoplayer2.util.Util.closeQuietly(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsMediaChunk.loadMedia():void");
    }

    private DefaultExtractorInput prepareExtraction(DataSource dataSource, DataSpec dataSpec) throws IOException, InterruptedException {
        DataSpec dataSpec2 = dataSpec;
        DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(dataSource, dataSpec2.absoluteStreamPosition, dataSource.open(dataSpec));
        if (this.extractor != null) {
            return defaultExtractorInput;
        }
        long peekId3PrivTimestamp = peekId3PrivTimestamp(defaultExtractorInput);
        defaultExtractorInput.resetPeekPosition();
        DefaultExtractorInput defaultExtractorInput2 = defaultExtractorInput;
        Pair createExtractor = this.extractorFactory.createExtractor(this.previousExtractor, dataSpec2.uri, this.trackFormat, this.muxedCaptionFormats, this.drmInitData, this.timestampAdjuster, dataSource.getResponseHeaders(), defaultExtractorInput2);
        this.extractor = (Extractor) createExtractor.first;
        boolean z = false;
        boolean z2 = this.extractor == this.previousExtractor;
        if (((Boolean) createExtractor.second).booleanValue()) {
            this.output.setSampleOffsetUs(peekId3PrivTimestamp != C.TIME_UNSET ? this.timestampAdjuster.adjustTsTimestamp(peekId3PrivTimestamp) : this.startTimeUs);
        }
        if (z2 && this.initDataSpec != null) {
            z = true;
        }
        this.initLoadCompleted = z;
        this.output.init(this.uid, this.shouldSpliceIn, z2);
        if (z2) {
            return defaultExtractorInput2;
        }
        this.extractor.init(this.output);
        return defaultExtractorInput2;
    }

    private long peekId3PrivTimestamp(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.resetPeekPosition();
        try {
            extractorInput.peekFully(this.id3Data.data, 0, 10);
            this.id3Data.reset(10);
            if (this.id3Data.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
                return C.TIME_UNSET;
            }
            this.id3Data.skipBytes(3);
            int readSynchSafeInt = this.id3Data.readSynchSafeInt();
            int i = readSynchSafeInt + 10;
            if (i > this.id3Data.capacity()) {
                byte[] bArr = this.id3Data.data;
                this.id3Data.reset(i);
                System.arraycopy(bArr, 0, this.id3Data.data, 0, 10);
            }
            extractorInput.peekFully(this.id3Data.data, 10, readSynchSafeInt);
            Metadata decode = this.id3Decoder.decode(this.id3Data.data, readSynchSafeInt);
            if (decode == null) {
                return C.TIME_UNSET;
            }
            readSynchSafeInt = decode.length();
            for (int i2 = 0; i2 < readSynchSafeInt; i2++) {
                Entry entry = decode.get(i2);
                if (entry instanceof PrivFrame) {
                    PrivFrame privFrame = (PrivFrame) entry;
                    if (PRIV_TIMESTAMP_FRAME_OWNER.equals(privFrame.owner)) {
                        System.arraycopy(privFrame.privateData, 0, this.id3Data.data, 0, 8);
                        this.id3Data.reset(8);
                        return this.id3Data.readLong() & 8589934591L;
                    }
                }
            }
            return C.TIME_UNSET;
        } catch (EOFException unused) {
            return C.TIME_UNSET;
        }
    }

    private static DataSource buildDataSource(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        return bArr != null ? new Aes128DataSource(dataSource, bArr, bArr2) : dataSource;
    }
}
