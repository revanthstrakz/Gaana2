package com.google.android.gms.internal.ads;

final class zzbug extends IllegalArgumentException {
    zzbug(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder(54);
        stringBuilder.append("Unpaired surrogate at index ");
        stringBuilder.append(i);
        stringBuilder.append(" of ");
        stringBuilder.append(i2);
        super(stringBuilder.toString());
    }
}
