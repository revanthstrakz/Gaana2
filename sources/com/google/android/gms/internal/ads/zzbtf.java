package com.google.android.gms.internal.ads;

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

class zzbtf<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzfmy;
    private final int zzftj;
    private List<zzbtm> zzftk;
    private Map<K, V> zzftl;
    private volatile zzbto zzftm;
    private Map<K, V> zzftn;
    private volatile zzbti zzfto;

    static <FieldDescriptorType extends zzbqw<FieldDescriptorType>> zzbtf<FieldDescriptorType, Object> zzfx(int i) {
        return new zzbtg(i);
    }

    private zzbtf(int i) {
        this.zzftj = i;
        this.zzftk = Collections.emptyList();
        this.zzftl = Collections.emptyMap();
        this.zzftn = Collections.emptyMap();
    }

    public void zzakj() {
        if (!this.zzfmy) {
            Map emptyMap;
            if (this.zzftl.isEmpty()) {
                emptyMap = Collections.emptyMap();
            } else {
                emptyMap = Collections.unmodifiableMap(this.zzftl);
            }
            this.zzftl = emptyMap;
            if (this.zzftn.isEmpty()) {
                emptyMap = Collections.emptyMap();
            } else {
                emptyMap = Collections.unmodifiableMap(this.zzftn);
            }
            this.zzftn = emptyMap;
            this.zzfmy = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzfmy;
    }

    public final int zzaop() {
        return this.zzftk.size();
    }

    public final Entry<K, V> zzfy(int i) {
        return (Entry) this.zzftk.get(i);
    }

    public final Iterable<Entry<K, V>> zzaoq() {
        if (this.zzftl.isEmpty()) {
            return zzbtj.zzaov();
        }
        return this.zzftl.entrySet();
    }

    public int size() {
        return this.zzftk.size() + this.zzftl.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzftl.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return ((zzbtm) this.zzftk.get(zza)).getValue();
        }
        return this.zzftl.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzaos();
        int zza = zza((Comparable) k);
        if (zza >= 0) {
            return ((zzbtm) this.zzftk.get(zza)).setValue(v);
        }
        zzaos();
        if (this.zzftk.isEmpty() && !(this.zzftk instanceof ArrayList)) {
            this.zzftk = new ArrayList(this.zzftj);
        }
        zza = -(zza + 1);
        if (zza >= this.zzftj) {
            return zzaot().put(k, v);
        }
        if (this.zzftk.size() == this.zzftj) {
            zzbtm zzbtm = (zzbtm) this.zzftk.remove(this.zzftj - 1);
            zzaot().put((Comparable) zzbtm.getKey(), zzbtm.getValue());
        }
        this.zzftk.add(zza, new zzbtm(this, k, v));
        return null;
    }

    public void clear() {
        zzaos();
        if (!this.zzftk.isEmpty()) {
            this.zzftk.clear();
        }
        if (!this.zzftl.isEmpty()) {
            this.zzftl.clear();
        }
    }

    public V remove(Object obj) {
        zzaos();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return zzfz(zza);
        }
        if (this.zzftl.isEmpty()) {
            return null;
        }
        return this.zzftl.remove(comparable);
    }

    private final V zzfz(int i) {
        zzaos();
        Object value = ((zzbtm) this.zzftk.remove(i)).getValue();
        if (!this.zzftl.isEmpty()) {
            Iterator it = zzaot().entrySet().iterator();
            this.zzftk.add(new zzbtm(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int compareTo;
        int size = this.zzftk.size() - 1;
        if (size >= 0) {
            compareTo = k.compareTo((Comparable) ((zzbtm) this.zzftk.get(size)).getKey());
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
            int compareTo2 = k.compareTo((Comparable) ((zzbtm) this.zzftk.get(i)).getKey());
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
        if (this.zzftm == null) {
            this.zzftm = new zzbto(this, null);
        }
        return this.zzftm;
    }

    /* Access modifiers changed, original: final */
    public final Set<Entry<K, V>> zzaor() {
        if (this.zzfto == null) {
            this.zzfto = new zzbti(this, null);
        }
        return this.zzfto;
    }

    private final void zzaos() {
        if (this.zzfmy) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzaot() {
        zzaos();
        if (this.zzftl.isEmpty() && !(this.zzftl instanceof TreeMap)) {
            this.zzftl = new TreeMap();
            this.zzftn = ((TreeMap) this.zzftl).descendingMap();
        }
        return (SortedMap) this.zzftl;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbtf)) {
            return super.equals(obj);
        }
        zzbtf zzbtf = (zzbtf) obj;
        int size = size();
        if (size != zzbtf.size()) {
            return false;
        }
        int zzaop = zzaop();
        if (zzaop != zzbtf.zzaop()) {
            return entrySet().equals(zzbtf.entrySet());
        }
        for (int i = 0; i < zzaop; i++) {
            if (!zzfy(i).equals(zzbtf.zzfy(i))) {
                return false;
            }
        }
        if (zzaop != size) {
            return this.zzftl.equals(zzbtf.zzftl);
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (i < zzaop()) {
            i2 += ((zzbtm) this.zzftk.get(i)).hashCode();
            i++;
        }
        return this.zzftl.size() > 0 ? i2 + this.zzftl.hashCode() : i2;
    }

    /* synthetic */ zzbtf(int i, zzbtg zzbtg) {
        this(i);
    }
}
