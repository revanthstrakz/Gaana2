package com.facebook.ads.internal.k;

import android.content.Context;
import android.os.Build;
import com.facebook.ads.internal.h.e;
import java.io.File;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

class b {
    private static final String a = "b";

    b() {
    }

    static String a(Context context) {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "is_emu", String.valueOf(a()));
        a(jSONObject, "apk_size", String.valueOf(b(context)));
        return jSONObject.toString();
    }

    private static void a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException unused) {
        }
    }

    private static boolean a() {
        return Build.FINGERPRINT.contains("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT));
    }

    private static long b(Context context) {
        try {
            return new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).publicSourceDir).length();
        } catch (Exception e) {
            e.a(e, context, Collections.EMPTY_MAP);
            return -1;
        }
    }
}
