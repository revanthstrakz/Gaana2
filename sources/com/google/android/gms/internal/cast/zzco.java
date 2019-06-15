package com.google.android.gms.internal.cast;

import com.facebook.internal.ServerProtocol;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzco {
    private final String version;
    private final String zzww;
    private final int zzwx;

    public zzco(JSONObject jSONObject) throws JSONException {
        this(jSONObject.optString("applicationName"), jSONObject.optInt("maxPlayers"), jSONObject.optString(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION));
    }

    private zzco(String str, int i, String str2) {
        this.zzww = str;
        this.zzwx = i;
        this.version = str2;
    }

    public final String zzeo() {
        return this.zzww;
    }

    public final int getMaxPlayers() {
        return this.zzwx;
    }

    public final String getVersion() {
        return this.version;
    }
}
