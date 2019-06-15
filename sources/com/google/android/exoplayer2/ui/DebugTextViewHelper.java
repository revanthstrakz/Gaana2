package com.google.android.exoplayer2.ui;

import android.annotation.SuppressLint;
import android.os.Looper;
import android.widget.TextView;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player$EventListener$$CC;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.Assertions;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.Locale;

public class DebugTextViewHelper implements EventListener, Runnable {
    private static final int REFRESH_INTERVAL_MS = 1000;
    private final SimpleExoPlayer player;
    private boolean started;
    private final TextView textView;

    public void onLoadingChanged(boolean z) {
        Player$EventListener$$CC.onLoadingChanged(this, z);
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Player$EventListener$$CC.onPlaybackParametersChanged(this, playbackParameters);
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        Player$EventListener$$CC.onPlayerError(this, exoPlaybackException);
    }

    public void onRepeatModeChanged(int i) {
        Player$EventListener$$CC.onRepeatModeChanged(this, i);
    }

    public void onSeekProcessed() {
        Player$EventListener$$CC.onSeekProcessed(this);
    }

    public void onShuffleModeEnabledChanged(boolean z) {
        Player$EventListener$$CC.onShuffleModeEnabledChanged(this, z);
    }

    public void onTimelineChanged(Timeline timeline, Object obj, int i) {
        Player$EventListener$$CC.onTimelineChanged(this, timeline, obj, i);
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        Player$EventListener$$CC.onTracksChanged(this, trackGroupArray, trackSelectionArray);
    }

    public DebugTextViewHelper(SimpleExoPlayer simpleExoPlayer, TextView textView) {
        Assertions.checkArgument(simpleExoPlayer.getApplicationLooper() == Looper.getMainLooper());
        this.player = simpleExoPlayer;
        this.textView = textView;
    }

    public final void start() {
        if (!this.started) {
            this.started = true;
            this.player.addListener(this);
            updateAndPost();
        }
    }

    public final void stop() {
        if (this.started) {
            this.started = false;
            this.player.removeListener(this);
            this.textView.removeCallbacks(this);
        }
    }

    public final void onPlayerStateChanged(boolean z, int i) {
        updateAndPost();
    }

    public final void onPositionDiscontinuity(int i) {
        updateAndPost();
    }

    public final void run() {
        updateAndPost();
    }

    /* Access modifiers changed, original: protected|final */
    @SuppressLint({"SetTextI18n"})
    public final void updateAndPost() {
        this.textView.setText(getDebugString());
        this.textView.removeCallbacks(this);
        this.textView.postDelayed(this, 1000);
    }

    /* Access modifiers changed, original: protected */
    public String getDebugString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getPlayerStateString());
        stringBuilder.append(getVideoString());
        stringBuilder.append(getAudioString());
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public String getPlayerStateString() {
        String str;
        switch (this.player.getPlaybackState()) {
            case 1:
                str = "idle";
                break;
            case 2:
                str = "buffering";
                break;
            case 3:
                str = "ready";
                break;
            case 4:
                str = "ended";
                break;
            default:
                str = "unknown";
                break;
        }
        return String.format("playWhenReady:%s playbackState:%s window:%s", new Object[]{Boolean.valueOf(this.player.getPlayWhenReady()), str, Integer.valueOf(this.player.getCurrentWindowIndex())});
    }

    /* Access modifiers changed, original: protected */
    public String getVideoString() {
        Format videoFormat = this.player.getVideoFormat();
        if (videoFormat == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(videoFormat.sampleMimeType);
        stringBuilder.append("(id:");
        stringBuilder.append(videoFormat.id);
        stringBuilder.append(" r:");
        stringBuilder.append(videoFormat.width);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(videoFormat.height);
        stringBuilder.append(getPixelAspectRatioString(videoFormat.pixelWidthHeightRatio));
        stringBuilder.append(getDecoderCountersBufferCountString(this.player.getVideoDecoderCounters()));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public String getAudioString() {
        Format audioFormat = this.player.getAudioFormat();
        if (audioFormat == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(audioFormat.sampleMimeType);
        stringBuilder.append("(id:");
        stringBuilder.append(audioFormat.id);
        stringBuilder.append(" hz:");
        stringBuilder.append(audioFormat.sampleRate);
        stringBuilder.append(" ch:");
        stringBuilder.append(audioFormat.channelCount);
        stringBuilder.append(getDecoderCountersBufferCountString(this.player.getAudioDecoderCounters()));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private static String getDecoderCountersBufferCountString(DecoderCounters decoderCounters) {
        if (decoderCounters == null) {
            return "";
        }
        decoderCounters.ensureUpdated();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" sib:");
        stringBuilder.append(decoderCounters.skippedInputBufferCount);
        stringBuilder.append(" sb:");
        stringBuilder.append(decoderCounters.skippedOutputBufferCount);
        stringBuilder.append(" rb:");
        stringBuilder.append(decoderCounters.renderedOutputBufferCount);
        stringBuilder.append(" db:");
        stringBuilder.append(decoderCounters.droppedBufferCount);
        stringBuilder.append(" mcdb:");
        stringBuilder.append(decoderCounters.maxConsecutiveDroppedBufferCount);
        stringBuilder.append(" dk:");
        stringBuilder.append(decoderCounters.droppedToKeyframeCount);
        return stringBuilder.toString();
    }

    private static String getPixelAspectRatioString(float f) {
        if (f == -1.0f || f == 1.0f) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" par:");
        stringBuilder.append(String.format(Locale.US, "%.02f", new Object[]{Float.valueOf(f)}));
        return stringBuilder.toString();
    }
}
