package com.google.android.gms.internal.cast;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzcp {
    private static final zzdw zzbf = new zzdw("GameManagerMessage");
    protected final int zzwy;
    protected final int zzwz;
    protected final String zzxa;
    protected final JSONObject zzxb;
    protected final int zzxc;
    protected final int zzxd;
    protected final List<zzcs> zzxe;
    protected final JSONObject zzxf;
    protected final String zzxg;
    protected final String zzxh;
    protected final long zzxi;
    protected final String zzxj;
    protected final zzco zzxk;

    private zzcp(int i, int i2, String str, JSONObject jSONObject, int i3, int i4, List<zzcs> list, JSONObject jSONObject2, String str2, String str3, long j, String str4, zzco zzco) {
        this.zzwy = i;
        this.zzwz = i2;
        this.zzxa = str;
        this.zzxb = jSONObject;
        this.zzxc = i3;
        this.zzxd = i4;
        this.zzxe = list;
        this.zzxf = jSONObject2;
        this.zzxg = str2;
        this.zzxh = str3;
        this.zzxi = j;
        this.zzxj = str4;
        this.zzxk = zzco;
    }

    protected static zzcp zzj(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        int optInt = jSONObject2.optInt("type", -1);
        switch (optInt) {
            case 1:
                JSONObject optJSONObject = jSONObject2.optJSONObject("gameManagerConfig");
                return new zzcp(optInt, jSONObject2.optInt("statusCode"), jSONObject2.optString("errorDescription"), jSONObject2.optJSONObject("extraMessageData"), jSONObject2.optInt("gameplayState"), jSONObject2.optInt("lobbyState"), zza(jSONObject2.optJSONArray("players")), jSONObject2.optJSONObject("gameData"), jSONObject2.optString("gameStatusText"), jSONObject2.optString("playerId"), jSONObject2.optLong("requestId"), jSONObject2.optString("playerToken"), optJSONObject != null ? new zzco(optJSONObject) : null);
            case 2:
                return new zzcp(optInt, jSONObject2.optInt("statusCode"), jSONObject2.optString("errorDescription"), jSONObject2.optJSONObject("extraMessageData"), jSONObject2.optInt("gameplayState"), jSONObject2.optInt("lobbyState"), zza(jSONObject2.optJSONArray("players")), jSONObject2.optJSONObject("gameData"), jSONObject2.optString("gameStatusText"), jSONObject2.optString("playerId"), -1, null, null);
            default:
                try {
                    zzbf.w("Unrecognized Game Message type %d", Integer.valueOf(optInt));
                } catch (JSONException e) {
                    zzbf.zzb(e, "Exception while parsing GameManagerMessage from json", new Object[0]);
                }
                return null;
        }
    }

    private static List<zzcs> zza(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                Object obj = null;
                try {
                    obj = new zzcs(optJSONObject);
                } catch (JSONException e) {
                    zzbf.zzb(e, "Exception when attempting to parse PlayerInfoMessageComponent at index %d", Integer.valueOf(i));
                }
                if (obj != null) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }
}
