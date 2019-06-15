package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.Map;
import org.json.JSONObject;

final class zzaqz implements zzu<zzbgg> {
    private final /* synthetic */ zzbgg zzduz;
    private final /* synthetic */ zzbcl zzdva;
    private final /* synthetic */ zzaqt zzdvb;

    zzaqz(zzaqt zzaqt, zzbgg zzbgg, zzbcl zzbcl) {
        this.zzdvb = zzaqt;
        this.zzduz = zzbgg;
        this.zzdva = zzbcl;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        try {
            boolean z;
            JSONObject jSONObject;
            String str = (String) map.get("success");
            String str2 = (String) map.get("failure");
            if (TextUtils.isEmpty(str2)) {
                JSONObject jSONObject2 = new JSONObject(str);
                z = true;
                jSONObject = jSONObject2;
            } else {
                jSONObject = new JSONObject(str2);
                z = false;
            }
            if (this.zzdvb.zzbqb.equals(jSONObject.optString("ads_id", ""))) {
                this.zzduz.zzb("/nativeAdPreProcess", this);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("success", z);
                jSONObject3.put("json", jSONObject);
                this.zzdva.set(jSONObject3);
            }
        } catch (Throwable th) {
            zzbbd.zzb("Error while preprocessing json.", th);
            this.zzdva.setException(th);
        }
    }
}
