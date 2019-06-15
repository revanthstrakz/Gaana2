package com.google.android.gms.internal.icing;

import java.util.Map.Entry;

final class zzfp implements Comparable<zzfp>, Entry<K, V> {
    private V value;
    private final /* synthetic */ zzfi zznn;
    private final K zznq;

    zzfp(zzfi zzfi, Entry<K, V> entry) {
        this(zzfi, (Comparable) entry.getKey(), entry.getValue());
    }

    zzfp(zzfi zzfi, K k, V v) {
        this.zznn = zzfi;
        this.zznq = k;
        this.value = v;
    }

    public final V getValue() {
        return this.value;
    }

    public final V setValue(V v) {
        this.zznn.zzdk();
        Object obj = this.value;
        this.value = v;
        return obj;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return equals(this.zznq, entry.getKey()) && equals(this.value, entry.getValue());
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.zznq == null ? 0 : this.zznq.hashCode();
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return hashCode ^ i;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zznq);
        String valueOf2 = String.valueOf(this.value);
        StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length());
        stringBuilder.append(valueOf);
        stringBuilder.append("=");
        stringBuilder.append(valueOf2);
        return stringBuilder.toString();
    }

    private static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public final /* synthetic */ Object getKey() {
        return this.zznq;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzfp) obj).getKey());
    }
}
