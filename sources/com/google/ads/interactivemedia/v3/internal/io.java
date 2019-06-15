package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.ii.b;

final class io extends b {
    private final int appVersion;
    private final String packageName;

    io(int i, String str) {
        this.appVersion = i;
        if (str == null) {
            throw new NullPointerException("Null packageName");
        }
        this.packageName = str;
    }

    public int appVersion() {
        return this.appVersion;
    }

    public String packageName() {
        return this.packageName;
    }

    public String toString() {
        int i = this.appVersion;
        String str = this.packageName;
        StringBuilder stringBuilder = new StringBuilder(51 + String.valueOf(str).length());
        stringBuilder.append("MarketAppInfo{appVersion=");
        stringBuilder.append(i);
        stringBuilder.append(", packageName=");
        stringBuilder.append(str);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (!(this.appVersion == bVar.appVersion() && this.packageName.equals(bVar.packageName()))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((this.appVersion ^ 1000003) * 1000003) ^ this.packageName.hashCode();
    }
}
