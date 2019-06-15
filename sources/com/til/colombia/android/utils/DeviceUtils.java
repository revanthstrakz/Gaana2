package com.til.colombia.android.utils;

import android.app.Activity;
import android.os.StatFs;
import com.til.colombia.android.internal.Log;
import java.io.File;

public final class DeviceUtils {
    private static final int a = 31457280;
    private static final int b = 31457280;
    private static final int c = 104857600;

    public enum ForceOrientation {
        FORCE_PORTRAIT("portrait"),
        FORCE_LANDSCAPE("landscape"),
        DEVICE_ORIENTATION("device"),
        UNDEFINED("");
        
        private final String mKey;

        private ForceOrientation(String str) {
            this.mKey = str;
        }

        public static ForceOrientation getForceOrientation(String str) {
            for (ForceOrientation forceOrientation : values()) {
                if (forceOrientation.mKey.equalsIgnoreCase(str)) {
                    return forceOrientation;
                }
            }
            return UNDEFINED;
        }
    }

    private DeviceUtils() {
    }

    public static long a(File file, long j) {
        long blockCount;
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 50;
        } catch (IllegalArgumentException unused) {
            Log.a("DeviceUtils", "Unable to calculate 2% of available disk space, defaulting to minimum");
            blockCount = 31457280;
        }
        return Math.max(Math.min(blockCount, 104857600), 31457280);
    }

    public static long a(File file) {
        return a(file, 31457280);
    }

    private static int a(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int i = activity.getResources().getConfiguration().orientation;
        if (1 == i) {
            switch (rotation) {
                case 1:
                case 2:
                    return 9;
                default:
                    return 1;
            }
        } else if (2 == i) {
            switch (rotation) {
                case 2:
                case 3:
                    return 8;
                default:
                    return 0;
            }
        } else {
            Log.a("DeviceUtils", "Unknown screen orientation. Defaulting to portrait.");
            return 9;
        }
    }

    private static int a(int i, int i2) {
        if (1 == i2) {
            switch (i) {
                case 1:
                case 2:
                    return 9;
                default:
                    return 1;
            }
        } else if (2 == i2) {
            switch (i) {
                case 2:
                case 3:
                    return 8;
                default:
                    return 0;
            }
        } else {
            Log.a("DeviceUtils", "Unknown screen orientation. Defaulting to portrait.");
            return 9;
        }
    }
}
