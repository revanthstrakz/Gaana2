package com.google.android.exoplayer2;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

public abstract /* synthetic */ class Player$EventListener$$CC {
    public static void onLoadingChanged(EventListener eventListener, boolean z) {
    }

    public static void onPlaybackParametersChanged(EventListener eventListener, PlaybackParameters playbackParameters) {
    }

    public static void onPlayerError(EventListener eventListener, ExoPlaybackException exoPlaybackException) {
    }

    public static void onPlayerStateChanged(EventListener eventListener, boolean z, int i) {
    }

    public static void onPositionDiscontinuity(EventListener eventListener, int i) {
    }

    public static void onRepeatModeChanged(EventListener eventListener, int i) {
    }

    public static void onSeekProcessed(EventListener eventListener) {
    }

    public static void onShuffleModeEnabledChanged(EventListener eventListener, boolean z) {
    }

    public static void onTimelineChanged(EventListener eventListener, @Nullable Timeline timeline, Object obj, int i) {
    }

    public static void onTracksChanged(EventListener eventListener, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }
}
