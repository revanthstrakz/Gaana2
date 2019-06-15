package com.facebook.ads.internal.s.a;

import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import java.util.Map;

public class aa {
    private static final String a = "aa";

    public static boolean a(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(a, "Invalid Window info in window interactive check, assuming is not a Lockscreen.");
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        String str3 = (String) map.get("kgr");
        return str != null && str.equals("1") && str2 != null && str2.equals("1") && str3 != null && str3.equals("true");
    }

    public static boolean b(Map<String, String> map) {
        boolean z = false;
        if (map == null || map.isEmpty()) {
            Log.v(a, "Invalid Window info in window interactive check, assuming not obstructed by Keyguard.");
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        if ((str != null && str.equals("1")) || (str2 != null && str2.equals("1"))) {
            return false;
        }
        String str3 = (String) map.get("kgr");
        if (str3 != null && str3.equals("true")) {
            z = true;
        }
        return z;
    }
}
