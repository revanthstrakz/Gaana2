package com.google.android.gms.internal.icing;

import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzbw<T> implements zzbs<T>, Serializable {
    @NullableDecl
    private final T zzdz;

    zzbw(@NullableDecl T t) {
        this.zzdz = t;
    }

    public final T get() {
        return this.zzdz;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof zzbw)) {
            return false;
        }
        zzbw zzbw = (zzbw) obj;
        Object obj2 = this.zzdz;
        obj = zzbw.zzdz;
        if (obj2 == obj || (obj2 != null && obj2.equals(obj))) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzdz});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzdz);
        StringBuilder stringBuilder = new StringBuilder(22 + String.valueOf(valueOf).length());
        stringBuilder.append("Suppliers.ofInstance(");
        stringBuilder.append(valueOf);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
