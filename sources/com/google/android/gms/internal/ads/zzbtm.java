package com.google.android.gms.internal.ads;

import java.util.Map.Entry;

final class zzbtm implements Comparable<zzbtm>, Entry<K, V> {
    private V value;
    private final /* synthetic */ zzbtf zzftq;
    private final K zzftt;

    zzbtm(zzbtf zzbtf, Entry<K, V> entry) {
        this(zzbtf, (Comparable) entry.getKey(), entry.getValue());
    }

    zzbtm(zzbtf zzbtf, K k, V v) {
        this.zzftq = zzbtf;
        this.zzftt = k;
        this.value = v;
    }

    public final V getValue() {
        return this.value;
    }

    public final V setValue(V v) {
        this.zzftq.zzaos();
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
        return equals(this.zzftt, entry.getKey()) && equals(this.value, entry.getValue());
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.zzftt == null ? 0 : this.zzftt.hashCode();
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return hashCode ^ i;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzftt);
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
        return this.zzftt;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzbtm) obj).getKey());
    }
}
