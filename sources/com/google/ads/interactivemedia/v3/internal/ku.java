package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;

final class ku<F, T> extends lh<F> implements Serializable {
    final kn<F, ? extends T> a;
    final lh<T> b;

    ku(kn<F, ? extends T> knVar, lh<T> lhVar) {
        this.a = (kn) kr.a(knVar);
        this.b = (lh) kr.a(lhVar);
    }

    public int compare(F f, F f2) {
        return this.b.compare(this.a.a(f), this.a.a(f2));
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ku)) {
            return false;
        }
        ku kuVar = (ku) obj;
        if (!(this.a.equals(kuVar.a) && this.b.equals(kuVar.b))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ko.a(this.a, this.b);
    }

    public String toString() {
        String valueOf = String.valueOf(this.b);
        String valueOf2 = String.valueOf(this.a);
        StringBuilder stringBuilder = new StringBuilder((13 + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length());
        stringBuilder.append(valueOf);
        stringBuilder.append(".onResultOf(");
        stringBuilder.append(valueOf2);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
