package com.google.android.gms.tagmanager;

import java.util.Arrays;

final class zzay {
    final byte[] zzbbs;
    final String zzoj;

    zzay(String str, byte[] bArr) {
        this.zzoj = str;
        this.zzbbs = bArr;
    }

    public final String toString() {
        String str = this.zzoj;
        int hashCode = Arrays.hashCode(this.zzbbs);
        StringBuilder stringBuilder = new StringBuilder(54 + String.valueOf(str).length());
        stringBuilder.append("KeyAndSerialized: key = ");
        stringBuilder.append(str);
        stringBuilder.append(" serialized hash = ");
        stringBuilder.append(hashCode);
        return stringBuilder.toString();
    }
}
