package com.player_framework;

import android.content.Context;
import android.content.SharedPreferences;

public class PlayerStatus {
    private static SharedPreferences b;
    private PlayerStates a = PlayerStates.QUEUE_EMPTY;

    public enum PlayerStates {
        INVALID(-1),
        QUEUE_EMPTY(0),
        LOADING(1),
        STOPPED(2),
        PAUSED(3),
        PLAYING(4);
        
        private final int _code;

        private PlayerStates(int i) {
            this._code = i;
        }

        public static PlayerStates fromInt(int i) {
            for (PlayerStates playerStates : values()) {
                if (playerStates.toInt() == i) {
                    return playerStates;
                }
            }
            return QUEUE_EMPTY;
        }

        public int toInt() {
            return this._code;
        }
    }

    private PlayerStatus() {
    }

    public static PlayerStatus a(Context context) {
        PlayerStatus playerStatus = new PlayerStatus();
        if (b == null) {
            b = context.getSharedPreferences("PREFERENCE_FILE_NAME_PLAYER", 0);
        }
        playerStatus.a = PlayerStates.fromInt(b.getInt("PREFERENCE_KEY_PLAYER_STATE", PlayerStates.STOPPED.toInt()));
        return playerStatus;
    }

    public static void a(Context context, PlayerStates playerStates) {
        if (b == null) {
            b = context.getSharedPreferences("PREFERENCE_FILE_NAME_PLAYER", 0);
        }
        b.edit().putInt("PREFERENCE_KEY_PLAYER_STATE", playerStates.toInt()).apply();
    }

    public static PlayerStates b(Context context) {
        if (b == null) {
            b = context.getSharedPreferences("PREFERENCE_FILE_NAME_PLAYER", 0);
        }
        return PlayerStates.fromInt(b.getInt("PREFERENCE_KEY_PLAYER_STATE", -1));
    }

    public PlayerStates a() {
        return this.a;
    }

    public boolean b() {
        return this.a == PlayerStates.LOADING;
    }

    public boolean c() {
        return this.a == PlayerStates.PLAYING;
    }

    public boolean d() {
        return this.a == PlayerStates.PAUSED;
    }

    public boolean e() {
        return this.a == PlayerStates.STOPPED;
    }
}
