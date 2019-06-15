package com.facebook.ads.internal.protocol;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.io.Serializable;

public enum d implements Serializable {
    BANNER_320_50(ModuleDescriptor.MODULE_VERSION, 50),
    INTERSTITIAL(0, 0),
    BANNER_HEIGHT_50(-1, 50),
    BANNER_HEIGHT_90(-1, 90),
    RECTANGLE_HEIGHT_250(-1, Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    
    private final int f;
    private final int g;

    private d(int i, int i2) {
        this.f = i;
        this.g = i2;
    }

    public static d a(int i, int i2) {
        return (INTERSTITIAL.g == i2 && INTERSTITIAL.f == i) ? INTERSTITIAL : (BANNER_320_50.g == i2 && BANNER_320_50.f == i) ? BANNER_320_50 : (BANNER_HEIGHT_50.g == i2 && BANNER_HEIGHT_50.f == i) ? BANNER_HEIGHT_50 : (BANNER_HEIGHT_90.g == i2 && BANNER_HEIGHT_90.f == i) ? BANNER_HEIGHT_90 : (RECTANGLE_HEIGHT_250.g == i2 && RECTANGLE_HEIGHT_250.f == i) ? RECTANGLE_HEIGHT_250 : null;
    }

    public int a() {
        return this.f;
    }

    public int b() {
        return this.g;
    }
}
