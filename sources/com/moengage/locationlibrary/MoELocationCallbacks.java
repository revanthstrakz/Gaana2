package com.moengage.locationlibrary;

import android.content.Intent;

public class MoELocationCallbacks {
    private static MoELocationCallbacks _INSTANCE;
    private OnGeoFenceHit onGeoFenceHit;

    public interface OnGeoFenceHit {
        boolean geoHit(Intent intent);
    }

    private MoELocationCallbacks() {
    }

    public static MoELocationCallbacks getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new MoELocationCallbacks();
        }
        return _INSTANCE;
    }

    public void setGeoFenceHitListener(OnGeoFenceHit onGeoFenceHit) {
        this.onGeoFenceHit = onGeoFenceHit;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean onGeoFenceHit(Intent intent) {
        return this.onGeoFenceHit != null && this.onGeoFenceHit.geoHit(intent);
    }
}
