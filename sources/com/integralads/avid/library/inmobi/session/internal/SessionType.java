package com.integralads.avid.library.inmobi.session.internal;

import com.facebook.internal.ServerProtocol;

public enum SessionType {
    DISPLAY(ServerProtocol.DIALOG_PARAM_DISPLAY),
    VIDEO("video"),
    MANAGED_DISPLAY("managedDisplay"),
    MANAGED_VIDEO("managedVideo");
    
    private final String value;

    private SessionType(String str) {
        this.value = str;
    }

    public final String toString() {
        return this.value;
    }
}
