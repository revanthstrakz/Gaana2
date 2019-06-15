package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.LinkedList;

@zzark
final class zzaho {
    private final String zzboa;
    private final LinkedList<zzahp> zzdhh = new LinkedList();
    private zzwb zzdhi;
    private final int zzdhj;
    private boolean zzdhk;

    zzaho(zzwb zzwb, String str, int i) {
        Preconditions.checkNotNull(zzwb);
        Preconditions.checkNotNull(str);
        this.zzdhi = zzwb;
        this.zzboa = str;
        this.zzdhj = i;
    }

    /* Access modifiers changed, original: final */
    public final zzwb zztj() {
        return this.zzdhi;
    }

    /* Access modifiers changed, original: final */
    public final int getNetworkType() {
        return this.zzdhj;
    }

    /* Access modifiers changed, original: final */
    public final String getAdUnitId() {
        return this.zzboa;
    }

    /* Access modifiers changed, original: final */
    public final zzahp zzl(@Nullable zzwb zzwb) {
        if (zzwb != null) {
            this.zzdhi = zzwb;
        }
        return (zzahp) this.zzdhh.remove();
    }

    /* Access modifiers changed, original: final */
    public final int size() {
        return this.zzdhh.size();
    }

    /* Access modifiers changed, original: final */
    public final int zztk() {
        Iterator it = this.zzdhh.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((zzahp) it.next()).zzblw) {
                i++;
            }
        }
        return i;
    }

    /* Access modifiers changed, original: final */
    public final boolean zzb(zzagi zzagi) {
        zzahp zzahp = new zzahp(this, zzagi);
        this.zzdhh.add(zzahp);
        return zzahp.load();
    }

    /* Access modifiers changed, original: final */
    public final int zztl() {
        Iterator it = this.zzdhh.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((zzahp) it.next()).load()) {
                i++;
            }
        }
        return i;
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzagi zzagi, zzwb zzwb) {
        this.zzdhh.add(new zzahp(this, zzagi, zzwb));
    }

    /* Access modifiers changed, original: final */
    public final void zztm() {
        this.zzdhk = true;
    }

    /* Access modifiers changed, original: final */
    public final boolean zztn() {
        return this.zzdhk;
    }
}
