package com.facebook.ads;

import com.facebook.ads.internal.protocol.d;
import java.io.Serializable;

public class AdSize implements Serializable {
    @Deprecated
    public static final AdSize BANNER_320_50 = new AdSize(d.BANNER_320_50);
    public static final AdSize BANNER_HEIGHT_50 = new AdSize(d.BANNER_HEIGHT_50);
    public static final AdSize BANNER_HEIGHT_90 = new AdSize(d.BANNER_HEIGHT_90);
    public static final AdSize INTERSTITIAL = new AdSize(d.INTERSTITIAL);
    public static final AdSize RECTANGLE_HEIGHT_250 = new AdSize(d.RECTANGLE_HEIGHT_250);
    private final int a;
    private final int b;

    public AdSize(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    private AdSize(d dVar) {
        this.a = dVar.a();
        this.b = dVar.b();
    }

    public static AdSize fromWidthAndHeight(int i, int i2) {
        return (INTERSTITIAL.b == i2 && INTERSTITIAL.a == i) ? INTERSTITIAL : (BANNER_320_50.b == i2 && BANNER_320_50.a == i) ? BANNER_320_50 : (BANNER_HEIGHT_50.b == i2 && BANNER_HEIGHT_50.a == i) ? BANNER_HEIGHT_50 : (BANNER_HEIGHT_90.b == i2 && BANNER_HEIGHT_90.a == i) ? BANNER_HEIGHT_90 : (RECTANGLE_HEIGHT_250.b == i2 && RECTANGLE_HEIGHT_250.a == i) ? RECTANGLE_HEIGHT_250 : null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.a != adSize.a ? false : this.b == adSize.b;
    }

    public int getHeight() {
        return this.b;
    }

    public int getWidth() {
        return this.a;
    }

    public int hashCode() {
        return (31 * this.a) + this.b;
    }

    public d toInternalAdSize() {
        return d.a(this.a, this.b);
    }
}
