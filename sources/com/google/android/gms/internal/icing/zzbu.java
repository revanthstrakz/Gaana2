package com.google.android.gms.internal.icing;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzbu<T> implements zzbs<T>, Serializable {
    @NullableDecl
    private transient T value;
    private final zzbs<T> zzdx;
    private volatile transient boolean zzdy;

    zzbu(zzbs<T> zzbs) {
        this.zzdx = (zzbs) zzbr.checkNotNull(zzbs);
    }

    public final T get() {
        if (!this.zzdy) {
            synchronized (this) {
                if (!this.zzdy) {
                    Object obj = this.zzdx.get();
                    this.value = obj;
                    this.zzdy = true;
                    return obj;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        String valueOf;
        StringBuilder stringBuilder;
        Object stringBuilder2;
        if (this.zzdy) {
            valueOf = String.valueOf(this.value);
            stringBuilder = new StringBuilder(25 + String.valueOf(valueOf).length());
            stringBuilder.append("<supplier that returned ");
            stringBuilder.append(valueOf);
            stringBuilder.append(">");
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = this.zzdx;
        }
        valueOf = String.valueOf(stringBuilder2);
        stringBuilder = new StringBuilder(19 + String.valueOf(valueOf).length());
        stringBuilder.append("Suppliers.memoize(");
        stringBuilder.append(valueOf);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
