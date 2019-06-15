package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@zzark
public final class zzaaz {
    @Nullable
    private final zzaba zzbln;
    private final Map<String, zzaay> zzczi = new HashMap();

    public zzaaz(@Nullable zzaba zzaba) {
        this.zzbln = zzaba;
    }

    public final void zza(String str, zzaay zzaay) {
        this.zzczi.put(str, zzaay);
    }

    public final void zzb(String str, String str2, long j) {
        Object obj;
        zzaba zzaba = this.zzbln;
        zzaay zzaay = (zzaay) this.zzczi.get(str2);
        String[] strArr = new String[]{str};
        if (!(zzaba == null || zzaay == null)) {
            zzaba.zza(zzaay, j, strArr);
        }
        Map map = this.zzczi;
        zzaba = this.zzbln;
        if (zzaba == null) {
            obj = null;
        } else {
            obj = zzaba.zzao(j);
        }
        map.put(str, obj);
    }

    @Nullable
    public final zzaba zzrf() {
        return this.zzbln;
    }
}
