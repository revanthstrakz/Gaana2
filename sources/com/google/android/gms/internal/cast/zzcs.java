package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcs {
    private final int zzep;
    private final String zzwt;
    private final JSONObject zzxq;

    public zzcs(JSONObject jSONObject) throws JSONException {
        this(jSONObject.optString("playerId"), jSONObject.optInt("playerState"), jSONObject.optJSONObject("playerData"));
    }

    private zzcs(String str, int i, JSONObject jSONObject) {
        this.zzwt = str;
        this.zzep = i;
        this.zzxq = jSONObject;
    }

    public final int getPlayerState() {
        return this.zzep;
    }

    public final JSONObject getPlayerData() {
        return this.zzxq;
    }

    public final String getPlayerId() {
        return this.zzwt;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzcs)) {
            return false;
        }
        zzcs zzcs = (zzcs) obj;
        if (this.zzep == zzcs.zzep && zzdk.zza(this.zzwt, zzcs.zzwt) && JsonUtils.areJsonValuesEquivalent(this.zzxq, zzcs.zzxq)) {
            return true;
        }
        return false;
    }
}
