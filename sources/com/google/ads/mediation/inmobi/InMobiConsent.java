package com.google.ads.mediation.inmobi;

import com.inmobi.sdk.InMobiSdk;
import org.json.JSONObject;

public class InMobiConsent {
    private static JSONObject consentObj = new JSONObject();

    public static void updateGDPRConsent(JSONObject jSONObject) {
        if (InMobiAdapter.IsAppInitialized().booleanValue()) {
            InMobiSdk.updateGDPRConsent(jSONObject);
        }
        consentObj = jSONObject;
    }

    static JSONObject getConsentObj() {
        return consentObj;
    }
}
