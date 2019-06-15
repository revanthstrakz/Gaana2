package com.google.android.gms.internal.icing;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzfi<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzgz;
    private final int zzng;
    private List<zzfp> zznh;
    private Map<K, V> zzni;
    private volatile zzfr zznj;
    private Map<K, V> zznk;
    private volatile zzfl zznl;

    static <FieldDescriptorType extends zzde<FieldDescriptorType>> zzfi<FieldDescriptorType, Object> zzai(int i) {
        return new zzfj(i);
    }

    private zzfi(int i) {
        this.zzng = i;
        this.zznh = Collections.emptyList();
        this.zzni = Collections.emptyMap();
        this.zznk = Collections.emptyMap();
    }

    public void zzaj() {
        if (!this.zzgz) {
            Map emptyMap;
            if (this.zzni.isEmpty()) {
                emptyMap = Collections.emptyMap();
            } else {
                emptyMap = Collections.unmodifiableMap(this.zzni);
            }
            this.zzni = emptyMap;
            if (this.zznk.isEmpty()) {
                emptyMap = Collections.emptyMap();
            } else {
                emptyMap = Collections.unmodifiableMap(this.zznk);
            }
            this.zznk = emptyMap;
            this.zzgz = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzgz;
    }

    public final int zzdh() {
        return this.zznh.size();
    }

    public final Entry<K, V> zzaj(int i) {
        return (Entry) this.zznh.get(i);
    }

    public final Iterable<Entry<K, V>> zzdi() {
        if (this.zzni.isEmpty()) {
            return zzfm.zzdn();
        }
        return this.zzni.entrySet();
    }

    public int size() {
        return this.zznh.size() + this.zzni.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzni.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return ((zzfp) this.zznh.get(zza)).getValue();
        }
        return this.zzni.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzdk();
        int zza = zza((Comparable) k);
        if (zza >= 0) {
            return ((zzfp) this.zznh.get(zza)).setValue(v);
        }
        zzdk();
        if (this.zznh.isEmpty() && !(this.zznh instanceof ArrayList)) {
            this.zznh = new ArrayList(this.zzng);
        }
        zza = -(zza + 1);
        if (zza >= this.zzng) {
            return zzdl().put(k, v);
        }
        if (this.zznh.size() == this.zzng) {
            zzfp zzfp = (zzfp) this.zznh.remove(this.zzng - 1);
            zzdl().put((Comparable) zzfp.getKey(), zzfp.getValue());
        }
        this.zznh.add(zza, new zzfp(this, k, v));
        return null;
    }

    public void clear() {
        zzdk();
        if (!this.zznh.isEmpty()) {
            this.zznh.clear();
        }
        if (!this.zzni.isEmpty()) {
            this.zzni.clear();
        }
    }

    public V remove(Object obj) {
        zzdk();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return zzak(zza);
        }
        if (this.zzni.isEmpty()) {
            return null;
        }
        return this.zzni.remove(comparable);
    }

    private final V zzak(int i) {
        zzdk();
        Object value = ((zzfp) this.zznh.remove(i)).getValue();
        if (!this.zzni.isEmpty()) {
            Iterator it = zzdl().entrySet().iterator();
            this.zznh.add(new zzfp(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int compareTo;
        int size = this.zznh.size() - 1;
        if (size >= 0) {
            compareTo = k.compareTo((Comparable) ((zzfp) this.zznh.get(size)).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        compareTo = 0;
        while (compareTo <= size) {
            int i = (compareTo + size) / 2;
            int compareTo2 = k.compareTo((Comparable) ((zzfp) this.zznh.get(i)).getKey());
            if (compareTo2 < 0) {
                size = i - 1;
            } else if (compareTo2 <= 0) {
                return i;
            } else {
                compareTo = i + 1;
            }
        }
        return -(compareTo + 1);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.zznj == null) {
            this.zznj = new zzfr(this, null);
        }
        return this.zznj;
    }

    /* Access modifiers changed, original: final */
    public final Set<Entry<K, V>> zzdj() {
        if (this.zznl == null) {
            this.zznl = new zzfl(this, null);
        }
        return this.zznl;
    }

    private final void zzdk() {
        if (this.zzgz) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzdl() {
        zzdk();
        if (this.zzni.isEmpty() && !(this.zzni instanceof TreeMap)) {
            this.zzni = new TreeMap();
            this.zznk = ((TreeMap) this.zzni).descendingMap();
        }
        return (SortedMap) this.zzni;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfi)) {
            return super.equals(obj);
        }
        zzfi zzfi = (zzfi) obj;
        int size = size();
        if (size != zzfi.size()) {
            return false;
        }
        int zzdh = zzdh();
        if (zzdh != zzfi.zzdh()) {
            return entrySet().equals(zzfi.entrySet());
        }
        for (int i = 0; i < zzdh; i++) {
            if (!zzaj(i).equals(zzfi.zzaj(i))) {
                return false;
            }
        }
        if (zzdh != size) {
            return this.zzni.equals(zzfi.zzni);
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (i < zzdh()) {
            i2 += ((zzfp) this.zznh.get(i)).hashCode();
            i++;
        }
        return this.zzni.size() > 0 ? i2 + this.zzni.hashCode() : i2;
    }

    /* synthetic */ zzfi(int i, zzfj zzfj) {
        this(i);
    }
}
