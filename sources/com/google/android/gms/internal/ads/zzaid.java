package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final /* synthetic */ class zzaid {
    public static void zza(zzaic zzaic, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzaic.zzh(str, jSONObject.toString());
    }

    public static void zza(zzaic zzaic, String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder((3 + String.valueOf(str).length()) + String.valueOf(str2).length());
        stringBuilder.append(str);
        stringBuilder.append("(");
        stringBuilder.append(str2);
        stringBuilder.append(");");
        zzaic.zzcg(stringBuilder.toString());
    }

    public static void zzb(zzaic zzaic, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(window.AFMA_ReceiveMessage || function() {})('");
        stringBuilder.append(str);
        stringBuilder.append("'");
        stringBuilder.append(",");
        stringBuilder.append(jSONObject2);
        stringBuilder.append(");");
        str = "Dispatching AFMA event: ";
        jSONObject2 = String.valueOf(stringBuilder.toString());
        zzbbd.zzdn(jSONObject2.length() != 0 ? str.concat(jSONObject2) : new String(str));
        zzaic.zzcg(stringBuilder.toString());
    }

    public static void zza(zzaic zzaic, String str, Map map) {
        try {
            zzaic.zza(str, zzbv.zzlf().zzn(map));
        } catch (JSONException unused) {
            zzbbd.zzeo("Could not convert parameters to JSON.");
        }
    }
}
