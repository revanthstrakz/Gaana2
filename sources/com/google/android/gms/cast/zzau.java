package com.google.android.gms.cast;

import org.json.JSONObject;

public final class zzau {
    private long zzek;
    private int zzel = 0;
    private JSONObject zzp;

    public final zzau zzc(long j) {
        this.zzek = j;
        return this;
    }

    public final zzau zzd(int i) {
        this.zzel = i;
        return this;
    }

    public final zzau zzh(JSONObject jSONObject) {
        this.zzp = jSONObject;
        return this;
    }

    public final zzas zzo() {
        return new zzas(this.zzek, this.zzel, false, this.zzp, null);
    }
}
