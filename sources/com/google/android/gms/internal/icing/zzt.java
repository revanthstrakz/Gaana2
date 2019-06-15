package com.google.android.gms.internal.icing;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

@ShowFirstParty
@VisibleForTesting
public final class zzt {
    private final String name;
    private int weight = 1;
    private String zzaa;
    private boolean zzab;
    private boolean zzac;
    private String zzaf;
    private final List<zzn> zzah = new ArrayList();

    public zzt(String str) {
        this.name = str;
    }

    public final zzt zzc(String str) {
        this.zzaa = str;
        return this;
    }

    public final zzt zzb(boolean z) {
        this.zzab = true;
        return this;
    }

    public final zzt zzc(boolean z) {
        this.zzac = true;
        return this;
    }

    public final zzt zzd(String str) {
        this.zzaf = str;
        return this;
    }

    public final zzs zzc() {
        return new zzs(this.name, this.zzaa, this.zzab, this.weight, this.zzac, null, (zzn[]) this.zzah.toArray(new zzn[this.zzah.size()]), this.zzaf, null);
    }
}
