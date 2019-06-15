package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient.GameManagerResult;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzcn implements GameManagerResult {
    private final Status zzgq;
    private final String zzwt;
    private final long zzwu;
    private final JSONObject zzwv;

    zzcn(Status status, String str, long j, JSONObject jSONObject) {
        this.zzgq = status;
        this.zzwt = str;
        this.zzwu = j;
        this.zzwv = jSONObject;
    }

    public final Status getStatus() {
        return this.zzgq;
    }

    public final String getPlayerId() {
        return this.zzwt;
    }

    public final long getRequestId() {
        return this.zzwu;
    }

    public final JSONObject getExtraMessageData() {
        return this.zzwv;
    }
}
