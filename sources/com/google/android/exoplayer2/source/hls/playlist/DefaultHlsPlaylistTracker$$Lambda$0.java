package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.Factory;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;

final /* synthetic */ class DefaultHlsPlaylistTracker$$Lambda$0 implements Factory {
    static final Factory $instance = new DefaultHlsPlaylistTracker$$Lambda$0();

    private DefaultHlsPlaylistTracker$$Lambda$0() {
    }

    public HlsPlaylistTracker createTracker(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistParserFactory hlsPlaylistParserFactory) {
        return new DefaultHlsPlaylistTracker(hlsDataSourceFactory, loadErrorHandlingPolicy, hlsPlaylistParserFactory);
    }
}
