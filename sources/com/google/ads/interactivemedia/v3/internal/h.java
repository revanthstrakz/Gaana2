package com.google.ads.interactivemedia.v3.internal;

import com.comscore.android.id.IdHelperAndroid;
import com.facebook.internal.AnalyticsEvents;

public enum h {
    NATIVE(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE),
    JAVASCRIPT("javascript"),
    NONE(IdHelperAndroid.NO_ID_AVAILABLE);
    
    private final String d;

    private h(String str) {
        this.d = str;
    }

    public String toString() {
        return this.d;
    }
}
