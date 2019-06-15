package com.google.android.gms.cast;

import org.json.JSONObject;

public final class zzas {
    private final long zzek;
    private final int zzel;
    private final boolean zzem;
    private final JSONObject zzp;

    public final long getPosition() {
        return this.zzek;
    }

    public final int zzm() {
        return this.zzel;
    }

    public final boolean zzn() {
        return this.zzem;
    }

    public final JSONObject getCustomData() {
        return this.zzp;
    }

    private zzas(long j, int i, boolean z, JSONObject jSONObject) {
        this.zzek = j;
        this.zzel = i;
        this.zzem = z;
        this.zzp = jSONObject;
    }

    /* synthetic */ zzas(long j, int i, boolean z, JSONObject jSONObject, zzat zzat) {
        this(j, i, false, jSONObject);
    }
}
