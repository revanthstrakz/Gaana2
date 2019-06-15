package com.google.android.gms.cast;

import org.json.JSONObject;

public final class zzar {
    private int repeatMode;
    private long zzdq = -1;
    private int zzej;
    private JSONObject zzp;

    public final zzar zzb(int i) {
        this.zzej = i;
        return this;
    }

    public final zzar zzb(long j) {
        this.zzdq = j;
        return this;
    }

    public final zzar zzc(int i) {
        this.repeatMode = i;
        return this;
    }

    public final zzar zzg(JSONObject jSONObject) {
        this.zzp = jSONObject;
        return this;
    }

    public final zzap zzl() {
        return new zzap(this.zzej, this.zzdq, this.repeatMode, this.zzp, null);
    }
}
