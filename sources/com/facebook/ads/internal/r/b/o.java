package com.facebook.ads.internal.r.b;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;

final class o {
    public static File a(Context context) {
        return new File(a(context, true), "video-cache");
    }

    private static File a(Context context, boolean z) {
        Object externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            externalStorageState = "";
        }
        File b = (z && "mounted".equals(externalStorageState)) ? b(context) : null;
        if (b == null) {
            b = context.getCacheDir();
        }
        if (b != null) {
            return b;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/data/data/");
        stringBuilder.append(context.getPackageName());
        stringBuilder.append("/cache/");
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("Can't define system cache directory! '");
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append("%s' will be used.");
        Log.w("ProxyCache", stringBuilder3.toString());
        return new File(stringBuilder2);
    }

    private static File b(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), InternalLogger.EVENT_PARAM_SDK_ANDROID), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        Log.w("ProxyCache", "Unable to create external cache directory");
        return null;
    }
}
