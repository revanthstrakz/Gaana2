package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.JsonUtils;
import org.json.JSONObject;

public final class zzcr implements PlayerInfo {
    private final int zzep;
    private final String zzwt;
    private final JSONObject zzxq;
    private final boolean zzxr;

    public zzcr(String str, int i, JSONObject jSONObject, boolean z) {
        this.zzwt = str;
        this.zzep = i;
        this.zzxq = jSONObject;
        this.zzxr = z;
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

    public final boolean isConnected() {
        switch (this.zzep) {
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                return false;
        }
    }

    public final boolean isControllable() {
        return this.zzxr;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PlayerInfo)) {
            return false;
        }
        PlayerInfo playerInfo = (PlayerInfo) obj;
        if (this.zzxr == playerInfo.isControllable() && this.zzep == playerInfo.getPlayerState() && zzdk.zza(this.zzwt, playerInfo.getPlayerId()) && JsonUtils.areJsonValuesEquivalent(this.zzxq, playerInfo.getPlayerData())) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzwt, Integer.valueOf(this.zzep), this.zzxq, Boolean.valueOf(this.zzxr));
    }
}
