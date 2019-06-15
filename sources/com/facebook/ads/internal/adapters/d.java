package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;

public class d {
    public static AdAdapter a(AdPlacementType adPlacementType) {
        switch (adPlacementType) {
            case BANNER:
                return new f();
            case INTERSTITIAL:
                return new h();
            case NATIVE:
                return new j();
            case NATIVE_BANNER:
                return new k();
            case INSTREAM:
                return new g();
            case REWARDED_VIDEO:
                return new l();
            default:
                return null;
        }
    }
}
