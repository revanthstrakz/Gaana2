package com.google.firebase.appindexing.internal;

import android.util.Log;
import com.google.firebase.appindexing.FirebaseAppIndex;

public final class zzu {
    public static int zzr(String str) {
        return isLoggable(3) ? Log.d(FirebaseAppIndex.APP_INDEXING_API_TAG, str) : 0;
    }

    public static boolean isLoggable(int i) {
        if (Log.isLoggable(FirebaseAppIndex.APP_INDEXING_API_TAG, i)) {
            return true;
        }
        return Log.isLoggable(FirebaseAppIndex.APP_INDEXING_API_TAG, i);
    }
}
