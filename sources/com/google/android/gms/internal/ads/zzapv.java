package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzapv implements zzaqe<zzabq> {
    public final /* synthetic */ zzacf zza(zzapw zzapw, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        zzbcb zzc;
        JSONObject zza = zzbac.zza(jSONObject, "html_containers", "instream");
        if (zza == null) {
            zzc = zzapw.zzc(jSONObject, "video");
        } else {
            zzc = zzapw.zza(zza.optString("base_url"), zza.optString("html"), true);
        }
        zzbgg zzc2 = zzapw.zzc(zzc);
        if (zzc2 != null) {
            return new zzabq(zzc2);
        }
        zzbbd.zzeo("Can not get video view for instream ad.");
        return null;
    }
}
