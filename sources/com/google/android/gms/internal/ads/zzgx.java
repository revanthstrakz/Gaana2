package com.google.android.gms.internal.ads;

public final class zzgx extends Exception {
    private final int zzaej;

    public zzgx(int i, int i2, int i3, int i4) {
        StringBuilder stringBuilder = new StringBuilder(82);
        stringBuilder.append("AudioTrack init failed: ");
        stringBuilder.append(i);
        stringBuilder.append(", Config(");
        stringBuilder.append(i2);
        stringBuilder.append(", ");
        stringBuilder.append(i3);
        stringBuilder.append(", ");
        stringBuilder.append(i4);
        stringBuilder.append(")");
        super(stringBuilder.toString());
        this.zzaej = i;
    }
}
