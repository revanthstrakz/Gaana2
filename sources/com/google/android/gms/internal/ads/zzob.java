package com.google.android.gms.internal.ads;

public final class zzob {
    public final String value;
    public final String zzbdi;

    public zzob(String str, String str2) {
        this.zzbdi = str;
        this.value = str2;
    }

    public final String toString() {
        String str = this.zzbdi;
        String str2 = this.value;
        StringBuilder stringBuilder = new StringBuilder((2 + String.valueOf(str).length()) + String.valueOf(str2).length());
        stringBuilder.append(str);
        stringBuilder.append(", ");
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }
}
