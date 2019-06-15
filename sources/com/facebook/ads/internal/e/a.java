package com.facebook.ads.internal.e;

import com.moengage.inapp.InAppMessage;

public enum a {
    TEST(InAppMessage.INAPP_TYPE_TEST),
    BROWSER_SESSION("browser_session"),
    CLOSE("close"),
    IMPRESSION("impression"),
    INVALIDATION("invalidation"),
    STORE("store"),
    OFF_TARGET_CLICK("off_target_click"),
    OPEN_LINK("open_link"),
    NATIVE_VIEW("native_view"),
    VIDEO("video"),
    USER_RETURN("user_return"),
    AD_REPORTING("x_out");
    
    public final String m;

    private a(String str) {
        this.m = str;
    }

    public static String[] a() {
        a[] values = values();
        String[] strArr = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            strArr[i] = values[i].m;
        }
        return strArr;
    }
}
