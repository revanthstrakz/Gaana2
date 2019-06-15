package com.comscore.utils;

import android.os.Build;
import java.io.File;

public class RootDetector {
    private static boolean a() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean b() {
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static boolean isDeviceRooted() {
        return a() || b();
    }
}
