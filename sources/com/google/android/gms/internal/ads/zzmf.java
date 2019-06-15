package com.google.android.gms.internal.ads;

import android.util.SparseArray;

public final class zzmf implements zzib {
    private zzig zzaxa;
    public final zzhz zzaxu;
    private final zzfs zzazu;
    private final SparseArray<zzmg> zzazv = new SparseArray();
    private boolean zzazw;
    private zzmh zzazx;
    private zzfs[] zzazy;

    public zzmf(zzhz zzhz, zzfs zzfs) {
        this.zzaxu = zzhz;
        this.zzazu = zzfs;
    }

    public final zzig zzfw() {
        return this.zzaxa;
    }

    public final zzfs[] zzfx() {
        return this.zzazy;
    }

    public final void zza(zzmh zzmh) {
        this.zzazx = zzmh;
        if (this.zzazw) {
            this.zzaxu.zzc(0, 0);
            for (int i = 0; i < this.zzazv.size(); i++) {
                ((zzmg) this.zzazv.valueAt(i)).zzb(zzmh);
            }
            return;
        }
        this.zzaxu.zza((zzib) this);
        this.zzazw = true;
    }

    public final zzii zzb(int i, int i2) {
        zzmg zzmg = (zzmg) this.zzazv.get(i);
        if (zzmg != null) {
            return zzmg;
        }
        zzpo.checkState(this.zzazy == null);
        zzmg = new zzmg(i, i2, this.zzazu);
        zzmg.zzb(this.zzazx);
        this.zzazv.put(i, zzmg);
        return zzmg;
    }

    public final void zzdy() {
        zzfs[] zzfsArr = new zzfs[this.zzazv.size()];
        for (int i = 0; i < this.zzazv.size(); i++) {
            zzfsArr[i] = ((zzmg) this.zzazv.valueAt(i)).zzazz;
        }
        this.zzazy = zzfsArr;
    }

    public final void zza(zzig zzig) {
        this.zzaxa = zzig;
    }
}
