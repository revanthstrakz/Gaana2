package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzeq implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzor;
    private final /* synthetic */ zzei zzos;
    private boolean zzow;

    private zzeq(zzei zzei) {
        this.zzos = zzei;
        this.pos = -1;
    }

    /* synthetic */ zzeq(zzei zzei, zzej zzej) {
        this(zzei);
    }

    private final Iterator<Entry<K, V>> zzdw() {
        if (this.zzor == null) {
            this.zzor = this.zzos.zzon.entrySet().iterator();
        }
        return this.zzor;
    }

    public final boolean hasNext() {
        return this.pos + 1 >= this.zzos.zzom.size() ? !this.zzos.zzon.isEmpty() && zzdw().hasNext() : true;
    }

    public final /* synthetic */ Object next() {
        this.zzow = true;
        int i = this.pos + 1;
        this.pos = i;
        return (Entry) (i < this.zzos.zzom.size() ? this.zzos.zzom.get(this.pos) : zzdw().next());
    }

    public final void remove() {
        if (this.zzow) {
            this.zzow = false;
            this.zzos.zzdu();
            if (this.pos < this.zzos.zzom.size()) {
                zzei zzei = this.zzos;
                int i = this.pos;
                this.pos = i - 1;
                zzei.zzal(i);
                return;
            }
            zzdw().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
