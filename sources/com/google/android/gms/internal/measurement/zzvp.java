package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzvp<K, V> extends LinkedHashMap<K, V> {
    private static final zzvp zzcal;
    private boolean zzbtl = true;

    private zzvp() {
    }

    private zzvp(Map<K, V> map) {
        super(map);
    }

    public static <K, V> zzvp<K, V> zzxg() {
        return zzcal;
    }

    public final void zza(zzvp<K, V> zzvp) {
        zzxi();
        if (!zzvp.isEmpty()) {
            putAll(zzvp);
        }
    }

    public final Set<Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final void clear() {
        zzxi();
        super.clear();
    }

    public final V put(K k, V v) {
        zzxi();
        zzuq.checkNotNull(k);
        zzuq.checkNotNull(v);
        return super.put(k, v);
    }

    public final void putAll(Map<? extends K, ? extends V> map) {
        zzxi();
        for (Object next : map.keySet()) {
            zzuq.checkNotNull(next);
            zzuq.checkNotNull(map.get(next));
        }
        super.putAll(map);
    }

    public final V remove(Object obj) {
        zzxi();
        return super.remove(obj);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005c A:{RETURN} */
    public final boolean equals(java.lang.Object r7) {
        /*
        r6 = this;
        r0 = r7 instanceof java.util.Map;
        r1 = 0;
        if (r0 == 0) goto L_0x005d;
    L_0x0005:
        r7 = (java.util.Map) r7;
        r0 = 1;
        if (r6 == r7) goto L_0x0059;
    L_0x000a:
        r2 = r6.size();
        r3 = r7.size();
        if (r2 == r3) goto L_0x0016;
    L_0x0014:
        r7 = r1;
        goto L_0x005a;
    L_0x0016:
        r2 = r6.entrySet();
        r2 = r2.iterator();
    L_0x001e:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x0059;
    L_0x0024:
        r3 = r2.next();
        r3 = (java.util.Map.Entry) r3;
        r4 = r3.getKey();
        r4 = r7.containsKey(r4);
        if (r4 != 0) goto L_0x0035;
    L_0x0034:
        goto L_0x0014;
    L_0x0035:
        r4 = r3.getValue();
        r3 = r3.getKey();
        r3 = r7.get(r3);
        r5 = r4 instanceof byte[];
        if (r5 == 0) goto L_0x0052;
    L_0x0045:
        r5 = r3 instanceof byte[];
        if (r5 == 0) goto L_0x0052;
    L_0x0049:
        r4 = (byte[]) r4;
        r3 = (byte[]) r3;
        r3 = java.util.Arrays.equals(r4, r3);
        goto L_0x0056;
    L_0x0052:
        r3 = r4.equals(r3);
    L_0x0056:
        if (r3 != 0) goto L_0x001e;
    L_0x0058:
        goto L_0x0014;
    L_0x0059:
        r7 = r0;
    L_0x005a:
        if (r7 == 0) goto L_0x005d;
    L_0x005c:
        return r0;
    L_0x005d:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzvp.equals(java.lang.Object):boolean");
    }

    private static int zzab(Object obj) {
        if (obj instanceof byte[]) {
            return zzuq.hashCode((byte[]) obj);
        }
        if (!(obj instanceof zzur)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    public final int hashCode() {
        int i = 0;
        for (Entry entry : entrySet()) {
            i += zzab(entry.getValue()) ^ zzab(entry.getKey());
        }
        return i;
    }

    public final zzvp<K, V> zzxh() {
        return isEmpty() ? new zzvp() : new zzvp(this);
    }

    public final void zzsw() {
        this.zzbtl = false;
    }

    public final boolean isMutable() {
        return this.zzbtl;
    }

    private final void zzxi() {
        if (!this.zzbtl) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        zzvp zzvp = new zzvp();
        zzcal = zzvp;
        zzvp.zzbtl = false;
    }
}
