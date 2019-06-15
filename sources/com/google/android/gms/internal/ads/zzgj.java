package com.google.android.gms.internal.ads;

public final class zzgj extends Exception {
    public zzgj(int i, int i2, int i3) {
        StringBuilder stringBuilder = new StringBuilder(78);
        stringBuilder.append("Unhandled format: ");
        stringBuilder.append(i);
        stringBuilder.append(" Hz, ");
        stringBuilder.append(i2);
        stringBuilder.append(" channels in encoding ");
        stringBuilder.append(i3);
        super(stringBuilder.toString());
    }
}
