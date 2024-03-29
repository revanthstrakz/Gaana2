package com.google.android.exoplayer2.audio;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.PlaybackParameters;
import java.nio.ByteBuffer;

public interface AudioSink {
    public static final long CURRENT_POSITION_NOT_SET = Long.MIN_VALUE;

    public static final class ConfigurationException extends Exception {
        public ConfigurationException(Throwable th) {
            super(th);
        }

        public ConfigurationException(String str) {
            super(str);
        }
    }

    public static final class InitializationException extends Exception {
        public final int audioTrackState;

        public InitializationException(int i, int i2, int i3, int i4) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AudioTrack init failed: ");
            stringBuilder.append(i);
            stringBuilder.append(", Config(");
            stringBuilder.append(i2);
            stringBuilder.append(", ");
            stringBuilder.append(i3);
            stringBuilder.append(", ");
            stringBuilder.append(i4);
            stringBuilder.append(")");
            super(stringBuilder.toString());
            this.audioTrackState = i;
        }
    }

    public interface Listener {
        void onAudioSessionId(int i);

        void onPositionDiscontinuity();

        void onUnderrun(int i, long j, long j2);
    }

    public static final class WriteException extends Exception {
        public final int errorCode;

        public WriteException(int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AudioTrack write failed: ");
            stringBuilder.append(i);
            super(stringBuilder.toString());
            this.errorCode = i;
        }
    }

    void configure(int i, int i2, int i3, int i4, @Nullable int[] iArr, int i5, int i6) throws ConfigurationException;

    void disableTunneling();

    void enableTunnelingV21(int i);

    long getCurrentPositionUs(boolean z);

    PlaybackParameters getPlaybackParameters();

    boolean handleBuffer(ByteBuffer byteBuffer, long j) throws InitializationException, WriteException;

    void handleDiscontinuity();

    boolean hasPendingData();

    boolean isEnded();

    void pause();

    void play();

    void playToEndOfStream() throws WriteException;

    void release();

    void reset();

    void setAudioAttributes(AudioAttributes audioAttributes);

    void setAudioSessionId(int i);

    void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

    void setListener(Listener listener);

    PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters);

    void setVolume(float f);

    boolean supportsOutput(int i, int i2);
}
