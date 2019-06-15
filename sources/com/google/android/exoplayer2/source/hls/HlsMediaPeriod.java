package com.google.android.exoplayer2.source.hls;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.Callback;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistEventListener;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;

public final class HlsMediaPeriod implements MediaPeriod, Callback, PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    @Nullable
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private final EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    @Nullable
    private final TransferListener mediaTransferListener;
    private boolean notifiedReadingStarted;
    private int pendingPrepareCount;
    private final HlsPlaylistTracker playlistTracker;
    private HlsSampleStreamWrapper[] sampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices = new IdentityHashMap();
    private final TimestampAdjusterProvider timestampAdjusterProvider = new TimestampAdjusterProvider();
    private TrackGroupArray trackGroups;

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return j;
    }

    public HlsMediaPeriod(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, @Nullable TransferListener transferListener, LoadErrorHandlingPolicy loadErrorHandlingPolicy, EventDispatcher eventDispatcher, Allocator allocator, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, boolean z) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.mediaTransferListener = transferListener;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.eventDispatcher = eventDispatcher;
        this.allocator = allocator;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.allowChunklessPreparation = z;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(new SequenceableLoader[0]);
        eventDispatcher.mediaPeriodCreated();
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        for (HlsSampleStreamWrapper release : this.sampleStreamWrappers) {
            release.release();
        }
        this.callback = null;
        this.eventDispatcher.mediaPeriodReleased();
    }

    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(j);
    }

    public void maybeThrowPrepareError() throws IOException {
        for (HlsSampleStreamWrapper maybeThrowPrepareError : this.sampleStreamWrappers) {
            maybeThrowPrepareError.maybeThrowPrepareError();
        }
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        int i;
        int i2;
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr;
        TrackSelection[] trackSelectionArr2 = trackSelectionArr;
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        int[] iArr = new int[trackSelectionArr2.length];
        int[] iArr2 = new int[trackSelectionArr2.length];
        for (int i3 = 0; i3 < trackSelectionArr2.length; i3++) {
            int i4;
            if (sampleStreamArr2[i3] == null) {
                i4 = -1;
            } else {
                i4 = ((Integer) this.streamWrapperIndices.get(sampleStreamArr2[i3])).intValue();
            }
            iArr[i3] = i4;
            iArr2[i3] = -1;
            if (trackSelectionArr2[i3] != null) {
                TrackGroup trackGroup = trackSelectionArr2[i3].getTrackGroup();
                for (i = 0; i < this.sampleStreamWrappers.length; i++) {
                    if (this.sampleStreamWrappers[i].getTrackGroups().indexOf(trackGroup) != -1) {
                        iArr2[i3] = i;
                        break;
                    }
                }
            }
        }
        this.streamWrapperIndices.clear();
        SampleStream[] sampleStreamArr3 = new SampleStream[trackSelectionArr2.length];
        SampleStream[] sampleStreamArr4 = new SampleStream[trackSelectionArr2.length];
        TrackSelection[] trackSelectionArr3 = new TrackSelection[trackSelectionArr2.length];
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = new HlsSampleStreamWrapper[this.sampleStreamWrappers.length];
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        while (i6 < this.sampleStreamWrappers.length) {
            int i7 = 0;
            while (i7 < trackSelectionArr2.length) {
                TrackSelection trackSelection = null;
                sampleStreamArr4[i7] = iArr[i7] == i6 ? sampleStreamArr2[i7] : null;
                if (iArr2[i7] == i6) {
                    trackSelection = trackSelectionArr2[i7];
                }
                trackSelectionArr3[i7] = trackSelection;
                i7++;
            }
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.sampleStreamWrappers[i6];
            HlsSampleStreamWrapper hlsSampleStreamWrapper2 = hlsSampleStreamWrapper;
            i2 = i5;
            hlsSampleStreamWrapperArr = hlsSampleStreamWrapperArr2;
            int i8 = i6;
            TrackSelection[] trackSelectionArr4 = trackSelectionArr3;
            boolean selectTracks = hlsSampleStreamWrapper.selectTracks(trackSelectionArr3, zArr, sampleStreamArr4, zArr2, j, z);
            i = 0;
            boolean z2 = false;
            while (true) {
                boolean z3 = true;
                if (i >= trackSelectionArr2.length) {
                    break;
                }
                if (iArr2[i] == i8) {
                    Assertions.checkState(sampleStreamArr4[i] != null);
                    sampleStreamArr3[i] = sampleStreamArr4[i];
                    this.streamWrapperIndices.put(sampleStreamArr4[i], Integer.valueOf(i8));
                    z2 = true;
                } else if (iArr[i] == i8) {
                    if (sampleStreamArr4[i] != null) {
                        z3 = false;
                    }
                    Assertions.checkState(z3);
                }
                i++;
            }
            if (z2) {
                hlsSampleStreamWrapperArr[i2] = hlsSampleStreamWrapper2;
                i5 = i2 + 1;
                if (i2 == 0) {
                    hlsSampleStreamWrapper2.setIsTimestampMaster(true);
                    if (!selectTracks && this.enabledSampleStreamWrappers.length != 0) {
                        if (hlsSampleStreamWrapper2 == this.enabledSampleStreamWrappers[0]) {
                        }
                    }
                    this.timestampAdjusterProvider.reset();
                    z = true;
                } else {
                    hlsSampleStreamWrapper2.setIsTimestampMaster(false);
                }
            } else {
                i5 = i2;
            }
            i6 = i8 + 1;
            hlsSampleStreamWrapperArr2 = hlsSampleStreamWrapperArr;
            trackSelectionArr3 = trackSelectionArr4;
            sampleStreamArr2 = sampleStreamArr;
        }
        i2 = i5;
        hlsSampleStreamWrapperArr = hlsSampleStreamWrapperArr2;
        System.arraycopy(sampleStreamArr3, 0, sampleStreamArr, 0, sampleStreamArr3.length);
        this.enabledSampleStreamWrappers = (HlsSampleStreamWrapper[]) Arrays.copyOf(hlsSampleStreamWrapperArr, i5);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.enabledSampleStreamWrappers);
        return j;
    }

    public void discardBuffer(long j, boolean z) {
        for (HlsSampleStreamWrapper discardBuffer : this.enabledSampleStreamWrappers) {
            discardBuffer.discardBuffer(j, z);
        }
    }

    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    public boolean continueLoading(long j) {
        if (this.trackGroups != null) {
            return this.compositeSequenceableLoader.continueLoading(j);
        }
        for (HlsSampleStreamWrapper continuePreparing : this.sampleStreamWrappers) {
            continuePreparing.continuePreparing();
        }
        return false;
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public long readDiscontinuity() {
        if (!this.notifiedReadingStarted) {
            this.eventDispatcher.readingStarted();
            this.notifiedReadingStarted = true;
        }
        return C.TIME_UNSET;
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public long seekToUs(long j) {
        if (this.enabledSampleStreamWrappers.length > 0) {
            boolean seekToUs = this.enabledSampleStreamWrappers[0].seekToUs(j, false);
            for (int i = 1; i < this.enabledSampleStreamWrappers.length; i++) {
                this.enabledSampleStreamWrappers[i].seekToUs(j, seekToUs);
            }
            if (seekToUs) {
                this.timestampAdjusterProvider.reset();
            }
        }
        return j;
    }

    public void onPrepared() {
        int i = this.pendingPrepareCount - 1;
        this.pendingPrepareCount = i;
        if (i <= 0) {
            HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
            int i2 = 0;
            int i3 = i2;
            while (i2 < hlsSampleStreamWrapperArr.length) {
                i3 += hlsSampleStreamWrapperArr[i2].getTrackGroups().length;
                i2++;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i3];
            HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.sampleStreamWrappers;
            i2 = hlsSampleStreamWrapperArr2.length;
            i3 = 0;
            int i4 = i3;
            while (i3 < i2) {
                HlsSampleStreamWrapper hlsSampleStreamWrapper = hlsSampleStreamWrapperArr2[i3];
                int i5 = hlsSampleStreamWrapper.getTrackGroups().length;
                int i6 = i4;
                i4 = 0;
                while (i4 < i5) {
                    int i7 = i6 + 1;
                    trackGroupArr[i6] = hlsSampleStreamWrapper.getTrackGroups().get(i4);
                    i4++;
                    i6 = i7;
                }
                i3++;
                i4 = i6;
            }
            this.trackGroups = new TrackGroupArray(trackGroupArr);
            this.callback.onPrepared(this);
        }
    }

    public void onPlaylistRefreshRequired(HlsUrl hlsUrl) {
        this.playlistTracker.refreshPlaylist(hlsUrl);
    }

    public void onContinueLoadingRequested(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.callback.onContinueLoadingRequested(this);
    }

    public void onPlaylistChanged() {
        this.callback.onContinueLoadingRequested(this);
    }

    public boolean onPlaylistError(HlsUrl hlsUrl, long j) {
        int i = 1;
        for (HlsSampleStreamWrapper onPlaylistError : this.sampleStreamWrappers) {
            i &= onPlaylistError.onPlaylistError(hlsUrl, j);
        }
        this.callback.onContinueLoadingRequested(this);
        return i;
    }

    private void buildAndPrepareSampleStreamWrappers(long j) {
        HlsUrl hlsUrl;
        HlsSampleStreamWrapper buildSampleStreamWrapper;
        int i;
        TrackGroup[] trackGroupArr;
        HlsMasterPlaylist masterPlaylist = this.playlistTracker.getMasterPlaylist();
        List list = masterPlaylist.audios;
        List list2 = masterPlaylist.subtitles;
        int size = (list.size() + 1) + list2.size();
        this.sampleStreamWrappers = new HlsSampleStreamWrapper[size];
        this.pendingPrepareCount = size;
        long j2 = j;
        buildAndPrepareMainSampleStreamWrapper(masterPlaylist, j2);
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i4 < list.size()) {
            hlsUrl = (HlsUrl) list.get(i4);
            buildSampleStreamWrapper = buildSampleStreamWrapper(1, new HlsUrl[]{(HlsUrl) list.get(i4)}, null, Collections.emptyList(), j2);
            i = i3 + 1;
            this.sampleStreamWrappers[i3] = buildSampleStreamWrapper;
            Format format = hlsUrl.format;
            if (!this.allowChunklessPreparation || format.codecs == null) {
                buildSampleStreamWrapper.continuePreparing();
            } else {
                trackGroupArr = new TrackGroup[1];
                trackGroupArr[0] = new TrackGroup(hlsUrl.format);
                buildSampleStreamWrapper.prepareWithMasterPlaylistInfo(new TrackGroupArray(trackGroupArr), 0, TrackGroupArray.EMPTY);
            }
            i4++;
            i3 = i;
            i2 = 0;
        }
        int i5 = 0;
        while (i5 < list2.size()) {
            hlsUrl = (HlsUrl) list2.get(i5);
            buildSampleStreamWrapper = buildSampleStreamWrapper(3, new HlsUrl[]{hlsUrl}, null, Collections.emptyList(), j2);
            i = i3 + 1;
            this.sampleStreamWrappers[i3] = buildSampleStreamWrapper;
            trackGroupArr = new TrackGroup[1];
            trackGroupArr[0] = new TrackGroup(hlsUrl.format);
            buildSampleStreamWrapper.prepareWithMasterPlaylistInfo(new TrackGroupArray(trackGroupArr), 0, TrackGroupArray.EMPTY);
            i5++;
            i3 = i;
        }
        this.enabledSampleStreamWrappers = this.sampleStreamWrappers;
    }

    private void buildAndPrepareMainSampleStreamWrapper(HlsMasterPlaylist hlsMasterPlaylist, long j) {
        List list;
        HlsMasterPlaylist hlsMasterPlaylist2 = hlsMasterPlaylist;
        ArrayList arrayList = new ArrayList(hlsMasterPlaylist2.variants);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            HlsUrl hlsUrl = (HlsUrl) arrayList.get(i);
            Format format = hlsUrl.format;
            if (format.height > 0 || Util.getCodecsOfType(format.codecs, 2) != null) {
                arrayList2.add(hlsUrl);
            } else if (Util.getCodecsOfType(format.codecs, 1) != null) {
                arrayList3.add(hlsUrl);
            }
        }
        if (arrayList2.isEmpty()) {
            if (arrayList3.size() < arrayList.size()) {
                arrayList.removeAll(arrayList3);
            }
            list = arrayList;
        } else {
            list = arrayList2;
        }
        Assertions.checkArgument(list.isEmpty() ^ 1);
        HlsUrl[] hlsUrlArr = (HlsUrl[]) list.toArray(new HlsUrl[0]);
        String str = hlsUrlArr[0].format.codecs;
        HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(0, hlsUrlArr, hlsMasterPlaylist2.muxedAudioFormat, hlsMasterPlaylist2.muxedCaptionFormats, j);
        this.sampleStreamWrappers[0] = buildSampleStreamWrapper;
        if (!this.allowChunklessPreparation || str == null) {
            buildSampleStreamWrapper.setIsTimestampMaster(true);
            buildSampleStreamWrapper.continuePreparing();
            return;
        }
        int i2 = Util.getCodecsOfType(str, 2) != null ? 1 : 0;
        int i3 = Util.getCodecsOfType(str, 1) != null ? 1 : 0;
        ArrayList arrayList4 = new ArrayList();
        Format[] formatArr;
        if (i2 != 0) {
            formatArr = new Format[list.size()];
            for (int i4 = 0; i4 < formatArr.length; i4++) {
                formatArr[i4] = deriveVideoFormat(hlsUrlArr[i4].format);
            }
            arrayList4.add(new TrackGroup(formatArr));
            if (i3 != 0 && (hlsMasterPlaylist2.muxedAudioFormat != null || hlsMasterPlaylist2.audios.isEmpty())) {
                arrayList4.add(new TrackGroup(deriveAudioFormat(hlsUrlArr[0].format, hlsMasterPlaylist2.muxedAudioFormat, false)));
            }
            List list2 = hlsMasterPlaylist2.muxedCaptionFormats;
            if (list2 != null) {
                for (i3 = 0; i3 < list2.size(); i3++) {
                    arrayList4.add(new TrackGroup((Format) list2.get(i3)));
                }
            }
        } else if (i3 != 0) {
            formatArr = new Format[list.size()];
            for (i3 = 0; i3 < formatArr.length; i3++) {
                formatArr[i3] = deriveAudioFormat(hlsUrlArr[i3].format, hlsMasterPlaylist2.muxedAudioFormat, true);
            }
            arrayList4.add(new TrackGroup(formatArr));
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected codecs attribute: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        arrayList4.add(new TrackGroup(Format.createSampleFormat("ID3", MimeTypes.APPLICATION_ID3, null, -1, null)));
        buildSampleStreamWrapper.prepareWithMasterPlaylistInfo(new TrackGroupArray((TrackGroup[]) arrayList4.toArray(new TrackGroup[0])), 0, new TrackGroupArray(r1));
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(int i, HlsUrl[] hlsUrlArr, Format format, List<Format> list, long j) {
        return new HlsSampleStreamWrapper(i, this, new HlsChunkSource(this.extractorFactory, this.playlistTracker, hlsUrlArr, this.dataSourceFactory, this.mediaTransferListener, this.timestampAdjusterProvider, list), this.allocator, j, format, this.loadErrorHandlingPolicy, this.eventDispatcher);
    }

    private static Format deriveVideoFormat(Format format) {
        String codecsOfType = Util.getCodecsOfType(format.codecs, 2);
        return Format.createVideoContainerFormat(format.id, format.label, format.containerMimeType, MimeTypes.getMediaMimeType(codecsOfType), codecsOfType, format.bitrate, format.width, format.height, format.frameRate, null, format.selectionFlags);
    }

    private static Format deriveAudioFormat(Format format, Format format2, boolean z) {
        String str;
        String str2;
        int i;
        int i2;
        String str3;
        Format format3 = format;
        Format format4 = format2;
        int i3 = -1;
        int i4;
        if (format4 != null) {
            String str4 = format4.codecs;
            int i5 = format4.channelCount;
            i4 = format4.selectionFlags;
            String str5 = format4.language;
            str = format4.label;
            str2 = str4;
            i = i5;
            i2 = i4;
            str3 = str5;
        } else {
            String codecsOfType = Util.getCodecsOfType(format3.codecs, 1);
            if (z) {
                int i6 = format3.channelCount;
                i4 = format3.selectionFlags;
                str2 = codecsOfType;
                i = i6;
                str3 = format3.label;
                i2 = i4;
                str = format3.label;
            } else {
                str2 = codecsOfType;
                str = null;
                str3 = str;
                i = -1;
                i2 = 0;
            }
        }
        String mediaMimeType = MimeTypes.getMediaMimeType(str2);
        if (z) {
            i3 = format3.bitrate;
        }
        return Format.createAudioContainerFormat(format3.id, str, format3.containerMimeType, mediaMimeType, str2, i3, i, -1, null, i2, str3);
    }
}
