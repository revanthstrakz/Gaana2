package com.integralads.avid.library.inmobi.session.internal;

import com.facebook.internal.ServerProtocol;

public enum MediaType {
    DISPLAY(ServerProtocol.DIALOG_PARAM_DISPLAY),
    VIDEO("video");
    
    private final String value;

    private MediaType(String str) {
        this.value = str;
    }

    public final String toString() {
        return this.value;
    }
}
