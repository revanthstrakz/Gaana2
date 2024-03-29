package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Collection;
import java.util.Locale;

public final class CastMediaControlIntent {
    public static final String ACTION_SYNC_STATUS = "com.google.android.gms.cast.ACTION_SYNC_STATUS";
    public static final String DEFAULT_MEDIA_RECEIVER_APPLICATION_ID = "CC1AD845";
    public static final int ERROR_CODE_REQUEST_FAILED = 1;
    public static final int ERROR_CODE_SESSION_START_FAILED = 2;
    public static final int ERROR_CODE_TEMPORARILY_DISCONNECTED = 3;
    public static final String EXTRA_CAST_APPLICATION_ID = "com.google.android.gms.cast.EXTRA_CAST_APPLICATION_ID";
    public static final String EXTRA_CAST_LANGUAGE_CODE = "com.google.android.gms.cast.EXTRA_CAST_LANGUAGE_CODE";
    public static final String EXTRA_CAST_RELAUNCH_APPLICATION = "com.google.android.gms.cast.EXTRA_CAST_RELAUNCH_APPLICATION";
    public static final String EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS = "com.google.android.gms.cast.EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS";
    public static final String EXTRA_CUSTOM_DATA = "com.google.android.gms.cast.EXTRA_CUSTOM_DATA";
    public static final String EXTRA_DEBUG_LOGGING_ENABLED = "com.google.android.gms.cast.EXTRA_DEBUG_LOGGING_ENABLED";
    public static final String EXTRA_ERROR_CODE = "com.google.android.gms.cast.EXTRA_ERROR_CODE";

    public static String categoryForRemotePlayback(String str) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            return zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", str, null, false, true);
        }
        throw new IllegalArgumentException("applicationId cannot be null or empty");
    }

    public static String categoryForRemotePlayback() {
        return zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", null, null, false, true);
    }

    public static String categoryForCast(String str) throws IllegalArgumentException {
        if (str != null) {
            return zza("com.google.android.gms.cast.CATEGORY_CAST", str, null, false, true);
        }
        throw new IllegalArgumentException("applicationId cannot be null");
    }

    public static String categoryForCast(Collection<String> collection) throws IllegalArgumentException {
        if (collection != null) {
            return zza("com.google.android.gms.cast.CATEGORY_CAST", null, collection, false, true);
        }
        throw new IllegalArgumentException("namespaces cannot be null");
    }

    public static String categoryForCast(String str, Collection<String> collection) {
        if (str == null) {
            throw new IllegalArgumentException("applicationId cannot be null");
        } else if (collection != null) {
            return zza("com.google.android.gms.cast.CATEGORY_CAST", str, collection, false, true);
        } else {
            throw new IllegalArgumentException("namespaces cannot be null");
        }
    }

    private static String zza(String str, String str2, Collection<String> collection, boolean z, boolean z2) throws IllegalArgumentException {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (str2 != null) {
            str = str2.toUpperCase();
            if (str.matches("[A-F0-9]+")) {
                stringBuilder.append("/");
                stringBuilder.append(str);
            } else {
                String str3 = "Invalid application ID: ";
                str2 = String.valueOf(str2);
                throw new IllegalArgumentException(str2.length() != 0 ? str3.concat(str2) : new String(str3));
            }
        }
        if (collection != null) {
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Must specify at least one namespace");
            }
            if (str2 == null) {
                stringBuilder.append("/");
            }
            stringBuilder.append("/");
            Object obj = 1;
            for (String str4 : collection) {
                zzdk.zzp(str4);
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(zzdk.zzr(str4));
            }
        }
        if (str2 == null && collection == null) {
            stringBuilder.append("/");
        }
        if (collection == null) {
            stringBuilder.append("/");
        }
        stringBuilder.append("/");
        stringBuilder.append("/");
        stringBuilder.append("ALLOW_IPV6");
        return stringBuilder.toString();
    }

    public static String languageTagForLocale(Locale locale) {
        return zzdk.zza(locale);
    }

    private CastMediaControlIntent() {
    }
}
