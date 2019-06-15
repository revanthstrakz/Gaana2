package com.google.ads.interactivemedia.v3.internal;

final class kv {
    static void a(Object obj, Object obj2) {
        StringBuilder stringBuilder;
        if (obj == null) {
            String valueOf = String.valueOf(obj2);
            stringBuilder = new StringBuilder(24 + String.valueOf(valueOf).length());
            stringBuilder.append("null key in entry: null=");
            stringBuilder.append(valueOf);
            throw new NullPointerException(stringBuilder.toString());
        } else if (obj2 == null) {
            String valueOf2 = String.valueOf(obj);
            stringBuilder = new StringBuilder(26 + String.valueOf(valueOf2).length());
            stringBuilder.append("null value in entry: ");
            stringBuilder.append(valueOf2);
            stringBuilder.append("=null");
            throw new NullPointerException(stringBuilder.toString());
        }
    }

    static int a(int i, String str) {
        if (i >= 0) {
            return i;
        }
        StringBuilder stringBuilder = new StringBuilder(40 + String.valueOf(str).length());
        stringBuilder.append(str);
        stringBuilder.append(" cannot be negative but was: ");
        stringBuilder.append(i);
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
