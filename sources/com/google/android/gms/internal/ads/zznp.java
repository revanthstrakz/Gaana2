package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

public abstract class zznp {
    public final zzfs zzaad;
    private final String zzbdc;
    public final String zzbde;
    private final long zzbdm;
    public final long zzbdn;
    public final List<zznm> zzbdo;
    private final zzno zzbdp;

    private zznp(String str, long j, zzfs zzfs, String str2, zznt zznt, List<zznm> list) {
        List emptyList;
        this.zzbdc = str;
        this.zzbdm = j;
        this.zzaad = zzfs;
        this.zzbde = str2;
        if (list == null) {
            emptyList = Collections.emptyList();
        } else {
            emptyList = Collections.unmodifiableList(list);
        }
        this.zzbdo = emptyList;
        this.zzbdp = zznt.zza(this);
        this.zzbdn = zzqe.zza(zznt.zzbdw, 1000000, zznt.zzcr);
    }

    public abstract String zzf();

    public abstract zzno zzgi();

    public abstract zznd zzgj();

    public final zzno zzgh() {
        return this.zzbdp;
    }

    /* synthetic */ zznp(String str, long j, zzfs zzfs, String str2, zznt zznt, List list, zznq zznq) {
        this(str, j, zzfs, str2, zznt, list);
    }
}
