package com.google.android.exoplayer2.audio;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioFocusRequest.Builder;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class AudioFocusManager {
    private static final int AUDIO_FOCUS_STATE_HAVE_FOCUS = 1;
    private static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT = 2;
    private static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT_DUCK = 3;
    private static final int AUDIO_FOCUS_STATE_LOST_FOCUS = -1;
    private static final int AUDIO_FOCUS_STATE_NO_FOCUS = 0;
    public static final int PLAYER_COMMAND_DO_NOT_PLAY = -1;
    public static final int PLAYER_COMMAND_PLAY_WHEN_READY = 1;
    public static final int PLAYER_COMMAND_WAIT_FOR_CALLBACK = 0;
    private static final String TAG = "AudioFocusManager";
    private static final float VOLUME_MULTIPLIER_DEFAULT = 1.0f;
    private static final float VOLUME_MULTIPLIER_DUCK = 0.2f;
    @Nullable
    private AudioAttributes audioAttributes;
    private AudioFocusRequest audioFocusRequest;
    private int audioFocusState;
    @Nullable
    private final AudioManager audioManager;
    private int focusGain;
    private final AudioFocusListener focusListener;
    private final PlayerControl playerControl;
    private boolean rebuildAudioFocusRequest;
    private float volumeMultiplier = 1.0f;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayerCommand {
    }

    public interface PlayerControl {
        void executePlayerCommand(int i);

        void setVolumeMultiplier(float f);
    }

    private class AudioFocusListener implements OnAudioFocusChangeListener {
        private AudioFocusListener() {
        }

        public void onAudioFocusChange(int i) {
            if (i != 1) {
                switch (i) {
                    case -3:
                        if (!AudioFocusManager.this.willPauseWhenDucked()) {
                            AudioFocusManager.this.audioFocusState = 3;
                            break;
                        } else {
                            AudioFocusManager.this.audioFocusState = 2;
                            break;
                        }
                    case -2:
                        AudioFocusManager.this.audioFocusState = 2;
                        break;
                    case -1:
                        AudioFocusManager.this.audioFocusState = -1;
                        break;
                    default:
                        String str = AudioFocusManager.TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Unknown focus change type: ");
                        stringBuilder.append(i);
                        Log.w(str, stringBuilder.toString());
                        return;
                }
            }
            AudioFocusManager.this.audioFocusState = 1;
            switch (AudioFocusManager.this.audioFocusState) {
                case -1:
                    AudioFocusManager.this.playerControl.executePlayerCommand(-1);
                    AudioFocusManager.this.abandonAudioFocus(true);
                    break;
                case 0:
                case 3:
                    break;
                case 1:
                    AudioFocusManager.this.playerControl.executePlayerCommand(1);
                    break;
                case 2:
                    AudioFocusManager.this.playerControl.executePlayerCommand(0);
                    break;
                default:
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Unknown audio focus state: ");
                    stringBuilder2.append(AudioFocusManager.this.audioFocusState);
                    throw new IllegalStateException(stringBuilder2.toString());
            }
            float f = AudioFocusManager.this.audioFocusState == 3 ? AudioFocusManager.VOLUME_MULTIPLIER_DUCK : 1.0f;
            if (AudioFocusManager.this.volumeMultiplier != f) {
                AudioFocusManager.this.volumeMultiplier = f;
                AudioFocusManager.this.playerControl.setVolumeMultiplier(f);
            }
        }
    }

    private int handleIdle(boolean z) {
        return z ? 1 : -1;
    }

    public AudioFocusManager(@Nullable Context context, PlayerControl playerControl) {
        this.audioManager = context == null ? null : (AudioManager) context.getApplicationContext().getSystemService("audio");
        this.playerControl = playerControl;
        this.focusListener = new AudioFocusListener();
        this.audioFocusState = 0;
    }

    public float getVolumeMultiplier() {
        return this.volumeMultiplier;
    }

    public int setAudioAttributes(@Nullable AudioAttributes audioAttributes, boolean z, int i) {
        int i2 = 1;
        if (this.audioAttributes == null && audioAttributes == null) {
            if (!z) {
                i2 = -1;
            }
            return i2;
        }
        int handleIdle;
        Assertions.checkNotNull(this.audioManager, "SimpleExoPlayer must be created with a context to handle audio focus.");
        if (!Util.areEqual(this.audioAttributes, audioAttributes)) {
            this.audioAttributes = audioAttributes;
            this.focusGain = convertAudioAttributesToFocusGain(audioAttributes);
            boolean z2 = this.focusGain == 1 || this.focusGain == 0;
            Assertions.checkArgument(z2, "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
            if (z && (i == 2 || i == 3)) {
                return requestAudioFocus();
            }
        }
        if (i == 1) {
            handleIdle = handleIdle(z);
        } else {
            handleIdle = handlePrepare(z);
        }
        return handleIdle;
    }

    public int handlePrepare(boolean z) {
        if (this.audioManager == null) {
            return 1;
        }
        return z ? requestAudioFocus() : -1;
    }

    public int handleSetPlayWhenReady(boolean z, int i) {
        if (this.audioManager == null) {
            return 1;
        }
        if (z) {
            return i == 1 ? handleIdle(z) : requestAudioFocus();
        }
        abandonAudioFocus();
        return -1;
    }

    public void handleStop() {
        if (this.audioManager != null) {
            abandonAudioFocus(true);
        }
    }

    private int requestAudioFocus() {
        int i = 1;
        if (this.focusGain == 0) {
            if (this.audioFocusState != 0) {
                abandonAudioFocus(true);
            }
            return 1;
        }
        if (this.audioFocusState == 0) {
            int requestAudioFocusV26;
            if (Util.SDK_INT >= 26) {
                requestAudioFocusV26 = requestAudioFocusV26();
            } else {
                requestAudioFocusV26 = requestAudioFocusDefault();
            }
            this.audioFocusState = requestAudioFocusV26 == 1 ? 1 : 0;
        }
        if (this.audioFocusState == 0) {
            return -1;
        }
        if (this.audioFocusState == 2) {
            i = 0;
        }
        return i;
    }

    private void abandonAudioFocus() {
        abandonAudioFocus(false);
    }

    private void abandonAudioFocus(boolean z) {
        if (this.focusGain != 0 || this.audioFocusState != 0) {
            if (this.focusGain != 1 || this.audioFocusState == -1 || z) {
                if (Util.SDK_INT >= 26) {
                    abandonAudioFocusV26();
                } else {
                    abandonAudioFocusDefault();
                }
                this.audioFocusState = 0;
            }
        }
    }

    private int requestAudioFocusDefault() {
        return ((AudioManager) Assertions.checkNotNull(this.audioManager)).requestAudioFocus(this.focusListener, Util.getStreamTypeForAudioUsage(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).usage), this.focusGain);
    }

    @RequiresApi(26)
    private int requestAudioFocusV26() {
        if (this.audioFocusRequest == null || this.rebuildAudioFocusRequest) {
            this.audioFocusRequest = (this.audioFocusRequest == null ? new Builder(this.focusGain) : new Builder(this.audioFocusRequest)).setAudioAttributes(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).getAudioAttributesV21()).setWillPauseWhenDucked(willPauseWhenDucked()).setOnAudioFocusChangeListener(this.focusListener).build();
            this.rebuildAudioFocusRequest = false;
        }
        return ((AudioManager) Assertions.checkNotNull(this.audioManager)).requestAudioFocus(this.audioFocusRequest);
    }

    private void abandonAudioFocusDefault() {
        ((AudioManager) Assertions.checkNotNull(this.audioManager)).abandonAudioFocus(this.focusListener);
    }

    @RequiresApi(26)
    private void abandonAudioFocusV26() {
        if (this.audioFocusRequest != null) {
            ((AudioManager) Assertions.checkNotNull(this.audioManager)).abandonAudioFocusRequest(this.audioFocusRequest);
        }
    }

    private boolean willPauseWhenDucked() {
        return this.audioAttributes != null && this.audioAttributes.contentType == 1;
    }

    private static int convertAudioAttributesToFocusGain(@Nullable AudioAttributes audioAttributes) {
        if (audioAttributes == null) {
            return 0;
        }
        switch (audioAttributes.usage) {
            case 0:
                Log.w(TAG, "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                return 1;
            case 1:
            case 14:
                return 1;
            case 2:
            case 4:
                return 2;
            case 3:
                return 0;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
                return 3;
            case 11:
                return audioAttributes.contentType == 1 ? 2 : 3;
            case 16:
                if (Util.SDK_INT >= 19) {
                    return 4;
                }
                return 2;
            default:
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unidentified audio usage: ");
                stringBuilder.append(audioAttributes.usage);
                Log.w(str, stringBuilder.toString());
                return 0;
        }
    }
}
