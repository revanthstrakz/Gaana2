package com.player_framework;

public class PlayerConstants {

    public enum PauseReasons {
        INVALID(0),
        AUDIO_FOCUS_LOSS(1),
        MEDIA_BUTTON_TAP(2),
        AUDIO_FOCUS_LOSS_TRANSIENT(3);
        
        private final int _reasonCode;

        private PauseReasons(int i) {
            this._reasonCode = i;
        }

        public static PauseReasons fromInt(int i) {
            for (PauseReasons pauseReasons : values()) {
                if (pauseReasons.toInt() == i) {
                    return pauseReasons;
                }
            }
            return INVALID;
        }

        public int toInt() {
            return this._reasonCode;
        }
    }

    public enum PlayerCommands {
        NONE(0),
        PLAY(1),
        PLAY_TRACK(2),
        PAUSE(3),
        PLAY_PAUSE(4),
        RESUME(5),
        STOP(6),
        PLAY_STOP(7),
        PLAY_PREVIOUS(8),
        PLAY_NEXT(9),
        SEEK_TO(10),
        HANDLE_ERROR(11),
        UPDATE_NOTIFICATION(12),
        REMOVE_NOTIFICATION(13),
        CHANGE_STREAMING_QUALITY(14),
        FETCH_CF_TRACKS(15),
        CANCEL_CF_SCHEDULER(16),
        FAVORITE_TRACK(17),
        CHANGE_SONG_MODE(18);
        
        private final int _commandCode;

        private PlayerCommands(int i) {
            this._commandCode = i;
        }

        public static PlayerCommands fromInt(int i) {
            for (PlayerCommands playerCommands : values()) {
                if (playerCommands.toInt() == i) {
                    return playerCommands;
                }
            }
            return NONE;
        }

        public int toInt() {
            return this._commandCode;
        }
    }

    public enum RepeatModes {
        NO_REPEAT,
        SINGLE,
        ALL
    }
}
