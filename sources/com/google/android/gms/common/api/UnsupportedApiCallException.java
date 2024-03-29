package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;

public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature zzas;

    @KeepForSdk
    public UnsupportedApiCallException(Feature feature) {
        this.zzas = feature;
    }

    public final String getMessage() {
        String valueOf = String.valueOf(this.zzas);
        StringBuilder stringBuilder = new StringBuilder(8 + String.valueOf(valueOf).length());
        stringBuilder.append("Missing ");
        stringBuilder.append(valueOf);
        return stringBuilder.toString();
    }
}
