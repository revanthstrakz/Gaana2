package com.google.android.gms.internal.ads;

import com.google.api.client.http.HttpMethods;
import java.util.Map;

public final class zzbaa extends zzr<zzp> {
    private final zzbcl<zzp> zzene;
    private final Map<String, String> zzenf;
    private final zzbax zzeng;

    public zzbaa(String str, zzbcl<zzp> zzbcl) {
        this(str, null, zzbcl);
    }

    private zzbaa(String str, Map<String, String> map, zzbcl<zzp> zzbcl) {
        super(0, str, new zzbab(zzbcl));
        this.zzenf = null;
        this.zzene = zzbcl;
        this.zzeng = new zzbax();
        this.zzeng.zza(str, HttpMethods.GET, null, null);
    }

    /* Access modifiers changed, original: protected|final */
    public final zzx<zzp> zza(zzp zzp) {
        return zzx.zza(zzp, zzap.zzb(zzp));
    }
}
