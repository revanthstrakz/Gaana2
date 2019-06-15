package com.google.android.gms.internal.measurement;

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

class zzwo<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzbpy;
    private final int zzcbr;
    private List<zzwv> zzcbs;
    private Map<K, V> zzcbt;
    private volatile zzwx zzcbu;
    private Map<K, V> zzcbv;
    private volatile zzwr zzcbw;

    static <FieldDescriptorType extends zzuh<FieldDescriptorType>> zzwo<FieldDescriptorType, Object> zzbw(int i) {
        return new zzwp(i);
    }

    private zzwo(int i) {
        this.zzcbr = i;
        this.zzcbs = Collections.emptyList();
        this.zzcbt = Collections.emptyMap();
        this.zzcbv = Collections.emptyMap();
    }

    public void zzsw() {
        if (!this.zzbpy) {
            Map emptyMap;
            if (this.zzcbt.isEmpty()) {
                emptyMap = Collections.emptyMap();
            } else {
                emptyMap = Collections.unmodifiableMap(this.zzcbt);
            }
            this.zzcbt = emptyMap;
            if (this.zzcbv.isEmpty()) {
                emptyMap = Collections.emptyMap();
            } else {
                emptyMap = Collections.unmodifiableMap(this.zzcbv);
            }
            this.zzcbv = emptyMap;
            this.zzbpy = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzbpy;
    }

    public final int zzyc() {
        return this.zzcbs.size();
    }

    public final Entry<K, V> zzbx(int i) {
        return (Entry) this.zzcbs.get(i);
    }

    public final Iterable<Entry<K, V>> zzyd() {
        if (this.zzcbt.isEmpty()) {
            return zzws.zzyi();
        }
        return this.zzcbt.entrySet();
    }

    public int size() {
        return this.zzcbs.size() + this.zzcbt.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzcbt.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return ((zzwv) this.zzcbs.get(zza)).getValue();
        }
        return this.zzcbt.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzyf();
        int zza = zza((Comparable) k);
        if (zza >= 0) {
            return ((zzwv) this.zzcbs.get(zza)).setValue(v);
        }
        zzyf();
        if (this.zzcbs.isEmpty() && !(this.zzcbs instanceof ArrayList)) {
            this.zzcbs = new ArrayList(this.zzcbr);
        }
        zza = -(zza + 1);
        if (zza >= this.zzcbr) {
            return zzyg().put(k, v);
        }
        if (this.zzcbs.size() == this.zzcbr) {
            zzwv zzwv = (zzwv) this.zzcbs.remove(this.zzcbr - 1);
            zzyg().put((Comparable) zzwv.getKey(), zzwv.getValue());
        }
        this.zzcbs.add(zza, new zzwv(this, k, v));
        return null;
    }

    public void clear() {
        zzyf();
        if (!this.zzcbs.isEmpty()) {
            this.zzcbs.clear();
        }
        if (!this.zzcbt.isEmpty()) {
            this.zzcbt.clear();
        }
    }

    public V remove(Object obj) {
        zzyf();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return zzby(zza);
        }
        if (this.zzcbt.isEmpty()) {
            return null;
        }
        return this.zzcbt.remove(comparable);
    }

    private final V zzby(int i) {
        zzyf();
        Object value = ((zzwv) this.zzcbs.remove(i)).getValue();
        if (!this.zzcbt.isEmpty()) {
            Iterator it = zzyg().entrySet().iterator();
            this.zzcbs.add(new zzwv(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int compareTo;
        int size = this.zzcbs.size() - 1;
        if (size >= 0) {
            compareTo = k.compareTo((Comparable) ((zzwv) this.zzcbs.get(size)).getKey());
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
            int compareTo2 = k.compareTo((Comparable) ((zzwv) this.zzcbs.get(i)).getKey());
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
        if (this.zzcbu == null) {
            this.zzcbu = new zzwx(this, null);
        }
        return this.zzcbu;
    }

    /* Access modifiers changed, original: final */
    public final Set<Entry<K, V>> zzye() {
        if (this.zzcbw == null) {
            this.zzcbw = new zzwr(this, null);
        }
        return this.zzcbw;
    }

    private final void zzyf() {
        if (this.zzbpy) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzyg() {
        zzyf();
        if (this.zzcbt.isEmpty() && !(this.zzcbt instanceof TreeMap)) {
            this.zzcbt = new TreeMap();
            this.zzcbv = ((TreeMap) this.zzcbt).descendingMap();
        }
        return (SortedMap) this.zzcbt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzwo)) {
            return super.equals(obj);
        }
        zzwo zzwo = (zzwo) obj;
        int size = size();
        if (size != zzwo.size()) {
            return false;
        }
        int zzyc = zzyc();
        if (zzyc != zzwo.zzyc()) {
            return entrySet().equals(zzwo.entrySet());
        }
        for (int i = 0; i < zzyc; i++) {
            if (!zzbx(i).equals(zzwo.zzbx(i))) {
                return false;
            }
        }
        if (zzyc != size) {
            return this.zzcbt.equals(zzwo.zzcbt);
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (i < zzyc()) {
            i2 += ((zzwv) this.zzcbs.get(i)).hashCode();
            i++;
        }
        return this.zzcbt.size() > 0 ? i2 + this.zzcbt.hashCode() : i2;
    }

    /* synthetic */ zzwo(int i, zzwp zzwp) {
        this(i);
    }
}
