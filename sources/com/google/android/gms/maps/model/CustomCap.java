package com.google.android.gms.maps.model;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;

public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    public CustomCap(@NonNull BitmapDescriptor bitmapDescriptor) {
        this(bitmapDescriptor, 10.0f);
    }

    public CustomCap(@NonNull BitmapDescriptor bitmapDescriptor, float f) {
        BitmapDescriptor bitmapDescriptor2 = (BitmapDescriptor) Preconditions.checkNotNull(bitmapDescriptor, "bitmapDescriptor must not be null");
        String str = "refWidth must be positive";
        if (f <= 0.0f) {
            throw new IllegalArgumentException(str);
        }
        super(bitmapDescriptor2, f);
        this.bitmapDescriptor = bitmapDescriptor;
        this.refWidth = f;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.bitmapDescriptor);
        float f = this.refWidth;
        StringBuilder stringBuilder = new StringBuilder(55 + String.valueOf(valueOf).length());
        stringBuilder.append("[CustomCap: bitmapDescriptor=");
        stringBuilder.append(valueOf);
        stringBuilder.append(" refWidth=");
        stringBuilder.append(f);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
