package com.google.android.gms.internal.cast;

public final class zzef {
    public static String zza(Integer num) {
        if (num == null) {
            return null;
        }
        switch (num.intValue()) {
            case 0:
                return "REPEAT_OFF";
            case 1:
                return "REPEAT_ALL";
            case 2:
                return "REPEAT_SINGLE";
            case 3:
                return "REPEAT_ALL_AND_SHUFFLE";
            default:
                return null;
        }
    }
}
