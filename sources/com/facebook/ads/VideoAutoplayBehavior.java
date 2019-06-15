package com.facebook.ads;

import com.facebook.ads.internal.p.l;

public enum VideoAutoplayBehavior {
    DEFAULT,
    ON,
    OFF;

    public static VideoAutoplayBehavior fromInternalAutoplayBehavior(l lVar) {
        if (lVar == null) {
            return DEFAULT;
        }
        switch (lVar) {
            case DEFAULT:
                return DEFAULT;
            case ON:
                return ON;
            case OFF:
                return OFF;
            default:
                return DEFAULT;
        }
    }
}
