package com.google.ads.interactivemedia.v3.internal;

import com.facebook.internal.AnalyticsEvents;

public enum f {
    HTML("html"),
    NATIVE(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
    
    private final String c;

    private f(String str) {
        this.c = str;
    }

    public String toString() {
        return this.c;
    }
}
