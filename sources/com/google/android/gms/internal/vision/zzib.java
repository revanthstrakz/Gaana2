package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzib implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzaak;
    private final /* synthetic */ zzhz zzaal;

    private zzib(zzhz zzhz) {
        this.zzaal = zzhz;
        this.pos = this.zzaal.zzaaf.size();
    }

    public final boolean hasNext() {
        return (this.pos > 0 && this.pos <= this.zzaal.zzaaf.size()) || zzgz().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Entry<K, V>> zzgz() {
        if (this.zzaak == null) {
            this.zzaak = this.zzaal.zzaai.entrySet().iterator();
        }
        return this.zzaak;
    }

    public final /* synthetic */ Object next() {
        if (zzgz().hasNext()) {
            return (Entry) zzgz().next();
        }
        List zzb = this.zzaal.zzaaf;
        int i = this.pos - 1;
        this.pos = i;
        return (Entry) zzb.get(i);
    }

    /* synthetic */ zzib(zzhz zzhz, zzia zzia) {
        this(zzhz);
    }
}
