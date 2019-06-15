package com.google.android.gms.internal.ads;

public final class zzha extends Exception {
    private final int errorCode;

    public zzha(int i) {
        StringBuilder stringBuilder = new StringBuilder(36);
        stringBuilder.append("AudioTrack write failed: ");
        stringBuilder.append(i);
        super(stringBuilder.toString());
        this.errorCode = i;
    }
}
