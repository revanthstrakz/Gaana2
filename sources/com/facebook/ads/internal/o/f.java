package com.facebook.ads.internal.o;

import com.moengage.inapp.InAppMessage;

public enum f {
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
    
    private String m;

    private f(String str) {
        this.m = str;
    }

    public static f a(String str) {
        for (f fVar : values()) {
            if (fVar.m.equalsIgnoreCase(str)) {
                return fVar;
            }
        }
        return null;
    }

    public String toString() {
        return this.m;
    }
}
