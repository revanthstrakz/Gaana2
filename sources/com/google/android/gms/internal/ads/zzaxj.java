package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzaxj {
    private final long zzein;
    private final List<String> zzeio = new ArrayList();
    private final List<String> zzeip = new ArrayList();
    private final Map<String, zzakr> zzeiq = new HashMap();
    private String zzeir;
    private String zzeis;
    private JSONObject zzeit;
    private boolean zzeiu;

    public zzaxj(String str, long j) {
        int i = 0;
        this.zzeiu = false;
        this.zzeis = str;
        this.zzein = j;
        if (!TextUtils.isEmpty(str)) {
            try {
                this.zzeit = new JSONObject(str);
                if (this.zzeit.optInt("status", -1) != 1) {
                    this.zzeiu = false;
                    zzbbd.zzeo("App settings could not be fetched successfully.");
                    return;
                }
                this.zzeiu = true;
                this.zzeir = this.zzeit.optString("app_id");
                JSONArray optJSONArray = this.zzeit.optJSONArray("ad_unit_id_settings");
                if (optJSONArray != null) {
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject jSONObject = optJSONArray.getJSONObject(i2);
                        String optString = jSONObject.optString("format");
                        String optString2 = jSONObject.optString("ad_unit_id");
                        if (!TextUtils.isEmpty(optString)) {
                            if (!TextUtils.isEmpty(optString2)) {
                                if ("interstitial".equalsIgnoreCase(optString)) {
                                    this.zzeip.add(optString2);
                                } else if ("rewarded".equalsIgnoreCase(optString)) {
                                    jSONObject = jSONObject.optJSONObject("mediation_config");
                                    if (jSONObject != null) {
                                        this.zzeiq.put(optString2, new zzakr(jSONObject));
                                    }
                                }
                            }
                        }
                    }
                }
                optJSONArray = this.zzeit.optJSONArray("persistable_banner_ad_unit_ids");
                if (optJSONArray != null) {
                    while (i < optJSONArray.length()) {
                        this.zzeio.add(optJSONArray.optString(i));
                        i++;
                    }
                }
            } catch (JSONException e) {
                zzbbd.zzc("Exception occurred while processing app setting json", e);
                zzbv.zzlj().zza(e, "AppSettings.parseAppSettingsJson");
            }
        }
    }

    public final long zzyc() {
        return this.zzein;
    }

    public final boolean zzyd() {
        return this.zzeiu;
    }

    public final String zzye() {
        return this.zzeis;
    }

    public final String zzyf() {
        return this.zzeir;
    }

    public final Map<String, zzakr> zzyg() {
        return this.zzeiq;
    }
}
