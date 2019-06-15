package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import java.util.List;

public abstract /* synthetic */ class TrackSelection$$CC {
    @Deprecated
    public static void updateSelectedTrack(TrackSelection trackSelection, long j, long j2, long j3) {
        throw new UnsupportedOperationException();
    }

    public static void updateSelectedTrack(TrackSelection trackSelection, long j, long j2, long j3, List list, MediaChunkIterator[] mediaChunkIteratorArr) {
        trackSelection.updateSelectedTrack(j, j2, j3);
    }
}
