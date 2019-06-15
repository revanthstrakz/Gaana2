package com.google.ads.interactivemedia.v3.internal;

public class jf {
    private final long a;

    jf(long j) {
        this.a = j;
    }

    public long a() {
        return this.a;
    }

    public int hashCode() {
        return (int) this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof jf)) {
            return false;
        }
        return this.a == ((jf) obj).a;
    }

    public String toString() {
        long j = this.a;
        StringBuilder stringBuilder = new StringBuilder(56);
        stringBuilder.append("NativeBridgeConfig [adTimeUpdateMs=");
        stringBuilder.append(j);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
