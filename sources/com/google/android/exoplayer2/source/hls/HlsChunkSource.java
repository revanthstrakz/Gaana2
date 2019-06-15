package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.DataChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

class HlsChunkSource {
    private final DataSource encryptionDataSource;
    private byte[] encryptionIv;
    private String encryptionIvString;
    private byte[] encryptionKey;
    private Uri encryptionKeyUri;
    private HlsUrl expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private long liveEdgeInPeriodTimeUs = C.TIME_UNSET;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private final HlsPlaylistTracker playlistTracker;
    private byte[] scratchSpace;
    private boolean seenExpectedPlaylistError;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private TrackSelection trackSelection;
    private final HlsUrl[] variants;

    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public HlsUrl playlist;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlist = null;
        }
    }

    private static final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
        private final HlsMediaPlaylist playlist;
        private final long startOfPlaylistInPeriodUs;

        public HlsMediaPlaylistSegmentIterator(HlsMediaPlaylist hlsMediaPlaylist, long j, int i) {
            super((long) i, (long) (hlsMediaPlaylist.segments.size() - 1));
            this.playlist = hlsMediaPlaylist;
            this.startOfPlaylistInPeriodUs = j;
        }

        public DataSpec getDataSpec() {
            checkInBounds();
            Segment segment = (Segment) this.playlist.segments.get((int) getCurrentIndex());
            return new DataSpec(UriUtil.resolveToUri(this.playlist.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, null);
        }

        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.startOfPlaylistInPeriodUs + ((Segment) this.playlist.segments.get((int) getCurrentIndex())).relativeStartTimeUs;
        }

        public long getChunkEndTimeUs() {
            checkInBounds();
            Segment segment = (Segment) this.playlist.segments.get((int) getCurrentIndex());
            return (this.startOfPlaylistInPeriodUs + segment.relativeStartTimeUs) + segment.durationUs;
        }
    }

    private static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public Object getSelectionData() {
            return null;
        }

        public int getSelectionReason() {
            return 0;
        }

        public InitializationTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.selectedIndex = indexOf(trackGroup.getFormat(0));
        }

        public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
            j = SystemClock.elapsedRealtime();
            if (isBlacklisted(this.selectedIndex, j)) {
                int i = this.length - 1;
                while (i >= 0) {
                    if (isBlacklisted(i, j)) {
                        i--;
                    } else {
                        this.selectedIndex = i;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        public int getSelectedIndex() {
            return this.selectedIndex;
        }
    }

    private static final class EncryptionKeyChunk extends DataChunk {
        public final String iv;
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, byte[] bArr, String str) {
            super(dataSource, dataSpec, 3, format, i, obj, bArr);
            this.iv = str;
        }

        /* Access modifiers changed, original: protected */
        public void consume(byte[] bArr, int i) throws IOException {
            this.result = Arrays.copyOf(bArr, i);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsUrl[] hlsUrlArr, HlsDataSourceFactory hlsDataSourceFactory, @Nullable TransferListener transferListener, TimestampAdjusterProvider timestampAdjusterProvider, List<Format> list) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.variants = hlsUrlArr;
        this.timestampAdjusterProvider = timestampAdjusterProvider;
        this.muxedCaptionFormats = list;
        Format[] formatArr = new Format[hlsUrlArr.length];
        int[] iArr = new int[hlsUrlArr.length];
        for (int i = 0; i < hlsUrlArr.length; i++) {
            formatArr[i] = hlsUrlArr[i].format;
            iArr[i] = i;
        }
        this.mediaDataSource = hlsDataSourceFactory.createDataSource(1);
        if (transferListener != null) {
            this.mediaDataSource.addTransferListener(transferListener);
        }
        this.encryptionDataSource = hlsDataSourceFactory.createDataSource(3);
        this.trackGroup = new TrackGroup(formatArr);
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, iArr);
    }

    public void maybeThrowError() throws IOException {
        if (this.fatalError != null) {
            throw this.fatalError;
        } else if (this.expectedPlaylistUrl != null && this.seenExpectedPlaylistError) {
            this.playlistTracker.maybeThrowPlaylistRefreshError(this.expectedPlaylistUrl);
        }
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public void selectTracks(TrackSelection trackSelection) {
        this.trackSelection = trackSelection;
    }

    public TrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public void reset() {
        this.fatalError = null;
    }

    public void setIsTimestampMaster(boolean z) {
        this.isTimestampMaster = z;
    }

    public void getNextChunk(long j, long j2, List<HlsMediaChunk> list, HlsChunkHolder hlsChunkHolder) {
        List list2;
        HlsMediaChunk hlsMediaChunk;
        int i;
        long j3;
        long j4;
        long j5 = j2;
        HlsChunkHolder hlsChunkHolder2 = hlsChunkHolder;
        if (list.isEmpty()) {
            list2 = list;
            hlsMediaChunk = null;
        } else {
            list2 = list;
            hlsMediaChunk = (HlsMediaChunk) list2.get(list.size() - 1);
        }
        if (hlsMediaChunk == null) {
            i = -1;
        } else {
            i = this.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        }
        int i2 = i;
        long j6 = j5 - j;
        long resolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j);
        if (hlsMediaChunk == null || this.independentSegments) {
            j3 = resolveTimeToLiveEdgeUs;
            j4 = j6;
        } else {
            long j7;
            long durationUs = hlsMediaChunk.getDurationUs();
            long max = Math.max(0, j6 - durationUs);
            if (resolveTimeToLiveEdgeUs != C.TIME_UNSET) {
                j7 = max;
                j3 = Math.max(0, resolveTimeToLiveEdgeUs - durationUs);
            } else {
                j7 = max;
                j3 = resolveTimeToLiveEdgeUs;
            }
            j4 = j7;
        }
        this.trackSelection.updateSelectedTrack(j, j4, j3, list2, createMediaChunkIterators(hlsMediaChunk, j5));
        int selectedIndexInTrackGroup = this.trackSelection.getSelectedIndexInTrackGroup();
        boolean z = i2 != selectedIndexInTrackGroup;
        HlsUrl hlsUrl = this.variants[selectedIndexInTrackGroup];
        if (this.playlistTracker.isSnapshotValid(hlsUrl)) {
            long j8;
            int i3;
            HlsUrl hlsUrl2;
            HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl, true);
            this.independentSegments = playlistSnapshot.hasIndependentSegments;
            updateLiveEdgeTimeUs(playlistSnapshot);
            j4 = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
            HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
            int i4 = i2;
            long chunkMediaSequence = getChunkMediaSequence(hlsMediaChunk, z, playlistSnapshot, j4, j5);
            if (chunkMediaSequence >= playlistSnapshot.mediaSequence) {
                j8 = chunkMediaSequence;
                i3 = selectedIndexInTrackGroup;
                hlsUrl2 = hlsUrl;
            } else if (hlsMediaChunk2 == null || !z) {
                this.fatalError = new BehindLiveWindowException();
                return;
            } else {
                hlsUrl2 = this.variants[i4];
                playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl2, true);
                j4 = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
                j8 = hlsMediaChunk2.getNextChunkIndex();
                i3 = i4;
            }
            int i5 = (int) (j8 - playlistSnapshot.mediaSequence);
            if (i5 >= playlistSnapshot.segments.size()) {
                if (playlistSnapshot.hasEndTag) {
                    hlsChunkHolder2.endOfStream = true;
                } else {
                    hlsChunkHolder2.playlist = hlsUrl2;
                    this.seenExpectedPlaylistError &= this.expectedPlaylistUrl == hlsUrl2 ? 1 : 0;
                    this.expectedPlaylistUrl = hlsUrl2;
                }
                return;
            }
            this.seenExpectedPlaylistError = false;
            HlsUrl hlsUrl3 = null;
            this.expectedPlaylistUrl = null;
            Segment segment = (Segment) playlistSnapshot.segments.get(i5);
            if (segment.fullSegmentEncryptionKeyUri != null) {
                Uri resolveToUri = UriUtil.resolveToUri(playlistSnapshot.baseUri, segment.fullSegmentEncryptionKeyUri);
                if (!resolveToUri.equals(this.encryptionKeyUri)) {
                    hlsChunkHolder2.chunk = newEncryptionKeyChunk(resolveToUri, segment.encryptionIV, i3, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData());
                    return;
                } else if (!Util.areEqual(segment.encryptionIV, this.encryptionIvString)) {
                    setEncryptionData(resolveToUri, segment.encryptionIV, this.encryptionKey);
                }
            } else {
                clearEncryptionData();
            }
            Segment segment2 = segment.initializationSegment;
            if (segment2 != null) {
                HlsUrl dataSpec = new DataSpec(UriUtil.resolveToUri(playlistSnapshot.baseUri, segment2.url), segment2.byterangeOffset, segment2.byterangeLength, null);
            }
            long j9 = j4 + segment.relativeStartTimeUs;
            i3 = playlistSnapshot.discontinuitySequence + segment.relativeDiscontinuitySequence;
            hlsChunkHolder.chunk = new HlsMediaChunk(this.extractorFactory, this.mediaDataSource, new DataSpec(UriUtil.resolveToUri(playlistSnapshot.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, null), hlsUrl3, hlsUrl2, this.muxedCaptionFormats, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), j9, j9 + segment.durationUs, j8, i3, segment.hasGapTag, this.isTimestampMaster, this.timestampAdjusterProvider.getAdjuster(i3), hlsMediaChunk2, segment.drmInitData, this.encryptionKey, this.encryptionIv);
            return;
        }
        hlsChunkHolder2.playlist = hlsUrl;
        this.seenExpectedPlaylistError &= this.expectedPlaylistUrl == hlsUrl ? 1 : 0;
        this.expectedPlaylistUrl = hlsUrl;
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            setEncryptionData(encryptionKeyChunk.dataSpec.uri, encryptionKeyChunk.iv, encryptionKeyChunk.getResult());
        }
    }

    public boolean maybeBlacklistTrack(Chunk chunk, long j) {
        return this.trackSelection.blacklist(this.trackSelection.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), j);
    }

    public boolean onPlaylistError(HlsUrl hlsUrl, long j) {
        int indexOf = this.trackGroup.indexOf(hlsUrl.format);
        boolean z = true;
        if (indexOf == -1) {
            return true;
        }
        indexOf = this.trackSelection.indexOf(indexOf);
        if (indexOf == -1) {
            return true;
        }
        this.seenExpectedPlaylistError = (this.expectedPlaylistUrl == hlsUrl ? 1 : 0) | this.seenExpectedPlaylistError;
        if (!(j == C.TIME_UNSET || this.trackSelection.blacklist(indexOf, j))) {
            z = false;
        }
        return z;
    }

    public MediaChunkIterator[] createMediaChunkIterators(@Nullable HlsMediaChunk hlsMediaChunk, long j) {
        int i;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        if (hlsMediaChunk2 == null) {
            i = -1;
        } else {
            i = this.trackGroup.indexOf(hlsMediaChunk2.trackFormat);
        }
        int i2 = i;
        MediaChunkIterator[] mediaChunkIteratorArr = new MediaChunkIterator[this.trackSelection.length()];
        for (int i3 = 0; i3 < mediaChunkIteratorArr.length; i3++) {
            i = this.trackSelection.getIndexInTrackGroup(i3);
            HlsUrl hlsUrl = this.variants[i];
            if (this.playlistTracker.isSnapshotValid(hlsUrl)) {
                HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl, false);
                long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
                long j2 = initialStartTimeUs;
                long chunkMediaSequence = getChunkMediaSequence(hlsMediaChunk2, i != i2, playlistSnapshot, initialStartTimeUs, j);
                if (chunkMediaSequence < playlistSnapshot.mediaSequence) {
                    mediaChunkIteratorArr[i3] = MediaChunkIterator.EMPTY;
                } else {
                    mediaChunkIteratorArr[i3] = new HlsMediaPlaylistSegmentIterator(playlistSnapshot, j2, (int) (chunkMediaSequence - playlistSnapshot.mediaSequence));
                }
            } else {
                mediaChunkIteratorArr[i3] = MediaChunkIterator.EMPTY;
            }
        }
        return mediaChunkIteratorArr;
    }

    private long getChunkMediaSequence(@Nullable HlsMediaChunk hlsMediaChunk, boolean z, HlsMediaPlaylist hlsMediaPlaylist, long j, long j2) {
        if (hlsMediaChunk != null && !z) {
            return hlsMediaChunk.getNextChunkIndex();
        }
        long j3 = j + hlsMediaPlaylist.durationUs;
        if (!(hlsMediaChunk == null || this.independentSegments)) {
            j2 = hlsMediaChunk.startTimeUs;
        }
        if (!hlsMediaPlaylist.hasEndTag && j2 >= j3) {
            return hlsMediaPlaylist.mediaSequence + ((long) hlsMediaPlaylist.segments.size());
        }
        long j4 = j2 - j;
        List list = hlsMediaPlaylist.segments;
        Comparable valueOf = Long.valueOf(j4);
        boolean z2 = !this.playlistTracker.isLive() || hlsMediaChunk == null;
        return ((long) Util.binarySearchFloor(list, valueOf, true, z2)) + hlsMediaPlaylist.mediaSequence;
    }

    private long resolveTimeToLiveEdgeUs(long j) {
        if ((this.liveEdgeInPeriodTimeUs != C.TIME_UNSET ? 1 : null) != null) {
            return this.liveEdgeInPeriodTimeUs - j;
        }
        return C.TIME_UNSET;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist hlsMediaPlaylist) {
        long j;
        if (hlsMediaPlaylist.hasEndTag) {
            j = C.TIME_UNSET;
        } else {
            j = hlsMediaPlaylist.getEndTimeUs() - this.playlistTracker.getInitialStartTimeUs();
        }
        this.liveEdgeInPeriodTimeUs = j;
    }

    private EncryptionKeyChunk newEncryptionKeyChunk(Uri uri, String str, int i, int i2, Object obj) {
        return new EncryptionKeyChunk(this.encryptionDataSource, new DataSpec(uri, 0, -1, null, 1), this.variants[i].format, i2, obj, this.scratchSpace, str);
    }

    private void setEncryptionData(Uri uri, String str, byte[] bArr) {
        byte[] toByteArray = new BigInteger(Util.toLowerInvariant(str).startsWith("0x") ? str.substring(2) : str, 16).toByteArray();
        byte[] bArr2 = new byte[16];
        int length = toByteArray.length > 16 ? toByteArray.length - 16 : 0;
        System.arraycopy(toByteArray, length, bArr2, (bArr2.length - toByteArray.length) + length, toByteArray.length - length);
        this.encryptionKeyUri = uri;
        this.encryptionKey = bArr;
        this.encryptionIvString = str;
        this.encryptionIv = bArr2;
    }

    private void clearEncryptionData() {
        this.encryptionKeyUri = null;
        this.encryptionKey = null;
        this.encryptionIvString = null;
        this.encryptionIv = null;
    }
}
