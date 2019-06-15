package com.google.ads.interactivemedia.v3.internal;

import android.os.Build;
import android.os.Build.VERSION;
import com.til.colombia.android.internal.e;
import org.json.JSONObject;

public final class ab {
    public static String a() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        StringBuilder stringBuilder = new StringBuilder((2 + String.valueOf(str).length()) + String.valueOf(str2).length());
        stringBuilder.append(str);
        stringBuilder.append("; ");
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    public static String c() {
        return InternalLogger.EVENT_PARAM_SDK_ANDROID;
    }

    public static String b() {
        return Integer.toString(VERSION.SDK_INT);
    }

    public static JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        ac.a(jSONObject, "deviceType", a());
        ac.a(jSONObject, "osVersion", b());
        ac.a(jSONObject, e.C, c());
        return jSONObject;
    }
}
