package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public class NumberUtils {
    @KeepForSdk
    public static long parseHexLong(String str) {
        if (str.length() > 16) {
            StringBuilder stringBuilder = new StringBuilder(37 + String.valueOf(str).length());
            stringBuilder.append("Invalid input: ");
            stringBuilder.append(str);
            stringBuilder.append(" exceeds 16 characters");
            throw new NumberFormatException(stringBuilder.toString());
        } else if (str.length() == 16) {
            return Long.parseLong(str.substring(8), 16) | (Long.parseLong(str.substring(0, 8), 16) << 32);
        } else {
            return Long.parseLong(str, 16);
        }
    }

    private NumberUtils() {
    }
}
