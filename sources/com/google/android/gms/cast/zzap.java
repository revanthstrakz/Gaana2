package com.google.android.gms.cast;

import org.json.JSONObject;

public final class zzap {
    private final int repeatMode;
    private final long zzdq;
    private final int zzej;
    private final JSONObject zzp;

    private zzap(int i, long j, int i2, JSONObject jSONObject) {
        this.zzej = i;
        this.zzdq = j;
        this.repeatMode = i2;
        this.zzp = jSONObject;
    }

    public final int getStartIndex() {
        return this.zzej;
    }

    public final long getPlayPosition() {
        return this.zzdq;
    }

    public final int getRepeatMode() {
        return this.repeatMode;
    }

    public final JSONObject getCustomData() {
        return this.zzp;
    }

    /* synthetic */ zzap(int i, long j, int i2, JSONObject jSONObject, zzaq zzaq) {
        this(i, j, i2, jSONObject);
    }
}
