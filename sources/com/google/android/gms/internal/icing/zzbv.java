package com.google.android.gms.internal.icing;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzbv<T> implements zzbs<T> {
    @NullableDecl
    private T value;
    private volatile zzbs<T> zzdx;
    private volatile boolean zzdy;

    zzbv(zzbs<T> zzbs) {
        this.zzdx = (zzbs) zzbr.checkNotNull(zzbs);
    }

    public final T get() {
        if (!this.zzdy) {
            synchronized (this) {
                if (!this.zzdy) {
                    Object obj = this.zzdx.get();
                    this.value = obj;
                    this.zzdy = true;
                    this.zzdx = null;
                    return obj;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        String valueOf;
        StringBuilder stringBuilder;
        Object obj = this.zzdx;
        if (obj == null) {
            valueOf = String.valueOf(this.value);
            stringBuilder = new StringBuilder(25 + String.valueOf(valueOf).length());
            stringBuilder.append("<supplier that returned ");
            stringBuilder.append(valueOf);
            stringBuilder.append(">");
            obj = stringBuilder.toString();
        }
        valueOf = String.valueOf(obj);
        stringBuilder = new StringBuilder(19 + String.valueOf(valueOf).length());
        stringBuilder.append("Suppliers.memoize(");
        stringBuilder.append(valueOf);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
