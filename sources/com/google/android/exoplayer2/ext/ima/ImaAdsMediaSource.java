package com.google.android.exoplayer2.ext.ima;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource.SourceInfoRefreshListener;
import com.google.android.exoplayer2.source.ads.AdsLoader;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.source.ads.AdsMediaSource.EventListener;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;

@Deprecated
public final class ImaAdsMediaSource extends BaseMediaSource implements SourceInfoRefreshListener {
    private final AdsMediaSource adsMediaSource;

    public ImaAdsMediaSource(MediaSource mediaSource, Factory factory, ImaAdsLoader imaAdsLoader, ViewGroup viewGroup) {
        this(mediaSource, factory, imaAdsLoader, viewGroup, null, null);
    }

    public ImaAdsMediaSource(MediaSource mediaSource, Factory factory, ImaAdsLoader imaAdsLoader, ViewGroup viewGroup, @Nullable Handler handler, @Nullable EventListener eventListener) {
        this.adsMediaSource = new AdsMediaSource(mediaSource, factory, (AdsLoader) imaAdsLoader, viewGroup, handler, eventListener);
    }

    @Nullable
    public Object getTag() {
        return this.adsMediaSource.getTag();
    }

    public void prepareSourceInternal(ExoPlayer exoPlayer, boolean z, @Nullable TransferListener transferListener) {
        this.adsMediaSource.prepareSource(exoPlayer, z, this, transferListener);
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.adsMediaSource.maybeThrowSourceInfoRefreshError();
    }

    public MediaPeriod createPeriod(MediaPeriodId mediaPeriodId, Allocator allocator) {
        return this.adsMediaSource.createPeriod(mediaPeriodId, allocator);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.adsMediaSource.releasePeriod(mediaPeriod);
    }

    public void releaseSourceInternal() {
        this.adsMediaSource.releaseSource(this);
    }

    public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        refreshSourceInfo(timeline, obj);
    }
}
