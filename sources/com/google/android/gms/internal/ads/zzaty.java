package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

@zzark
public final class zzaty {
    private final int errorCode;
    private final String type;
    private String url;
    private final String zzdws;
    private final String zzdze;
    private final boolean zzdzf;
    private final List<String> zzeci;
    private final String zzecj;
    private final String zzeck;
    private final boolean zzecl;
    private final String zzecm;
    private final boolean zzecn;
    private final JSONObject zzeco;

    public zzaty(JSONObject jSONObject) {
        List list;
        this.url = jSONObject.optString("url");
        this.zzecj = jSONObject.optString("base_uri");
        this.zzeck = jSONObject.optString("post_parameters");
        String optString = jSONObject.optString("drt_include");
        int i = 1;
        boolean z = optString != null && (optString.equals("1") || optString.equals("true"));
        this.zzecl = z;
        this.zzdws = jSONObject.optString("request_id");
        this.type = jSONObject.optString("type");
        optString = jSONObject.optString("errors");
        if (optString == null) {
            list = null;
        } else {
            list = Arrays.asList(optString.split(","));
        }
        this.zzeci = list;
        if (jSONObject.optInt("valid", 0) == 1) {
            i = -2;
        }
        this.errorCode = i;
        this.zzecm = jSONObject.optString("fetched_ad");
        this.zzecn = jSONObject.optBoolean("render_test_ad_label");
        JSONObject optJSONObject = jSONObject.optJSONObject("preprocessor_flags");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        this.zzeco = optJSONObject;
        this.zzdze = jSONObject.optString("analytics_query_ad_event_id");
        this.zzdzf = jSONObject.optBoolean("is_analytics_logging_enabled");
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final String zzwq() {
        return this.zzecj;
    }

    public final String zzwr() {
        return this.zzeck;
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean zzws() {
        return this.zzecl;
    }

    public final String zzwt() {
        return this.zzecm;
    }

    public final boolean zzwu() {
        return this.zzecn;
    }

    public final String zzwv() {
        return this.zzdze;
    }

    public final boolean zzww() {
        return this.zzdzf;
    }
}
