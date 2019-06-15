package com.google.ads.interactivemedia.v3.internal;

import android.util.Base64;

class jv implements kd {
    jv() {
    }

    public String a(byte[] bArr, boolean z) {
        return Base64.encodeToString(bArr, z ? 11 : 2);
    }

    public byte[] a(String str, boolean z) throws IllegalArgumentException {
        return Base64.decode(str, z ? 11 : 2);
    }
}
