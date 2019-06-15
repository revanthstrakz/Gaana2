package com.fasterxml.jackson.core;

import com.gaana.cardoption.AssetsHelper.CARD;
import java.io.Serializable;

public class JsonLocation implements Serializable {
    public static final JsonLocation a = new JsonLocation("N/A", -1, -1, -1, -1);
    private static final long serialVersionUID = 1;
    final long b;
    final long c;
    final int d;
    final int e;
    final Object f;

    public JsonLocation(Object obj, long j, int i, int i2) {
        this(obj, -1, j, i, i2);
    }

    public JsonLocation(Object obj, long j, long j2, int i, int i2) {
        this.f = obj;
        this.b = j;
        this.c = j2;
        this.d = i;
        this.e = i2;
    }

    public long a() {
        return this.b;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(80);
        stringBuilder.append("[Source: ");
        if (this.f == null) {
            stringBuilder.append(CARD.UNKNOWN);
        } else {
            stringBuilder.append(this.f.toString());
        }
        stringBuilder.append("; line: ");
        stringBuilder.append(this.d);
        stringBuilder.append(", column: ");
        stringBuilder.append(this.e);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public int hashCode() {
        return ((((this.f == null ? 1 : this.f.hashCode()) ^ this.d) + this.e) ^ ((int) this.c)) + ((int) this.b);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof JsonLocation)) {
            return false;
        }
        JsonLocation jsonLocation = (JsonLocation) obj;
        if (this.f == null) {
            if (jsonLocation.f != null) {
                return false;
            }
        } else if (!this.f.equals(jsonLocation.f)) {
            return false;
        }
        if (!(this.d == jsonLocation.d && this.e == jsonLocation.e && this.c == jsonLocation.c && a() == jsonLocation.a())) {
            z = false;
        }
        return z;
    }
}
