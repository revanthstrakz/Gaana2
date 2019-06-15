package com.google.ads.interactivemedia.v3.internal;

final class ip extends a {
    private final String TXXX;

    ip(String str) {
        if (str == null) {
            throw new NullPointerException("Null TXXX");
        }
        this.TXXX = str;
    }

    /* Access modifiers changed, original: 0000 */
    public String TXXX() {
        return this.TXXX;
    }

    public String toString() {
        String str = this.TXXX;
        StringBuilder stringBuilder = new StringBuilder(28 + String.valueOf(str).length());
        stringBuilder.append("TimedMetadataWithKeys{TXXX=");
        stringBuilder.append(str);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        return this.TXXX.equals(((a) obj).TXXX());
    }

    public int hashCode() {
        return this.TXXX.hashCode() ^ 1000003;
    }
}
