package com.til.colombia.android.network;

import java.io.Serializable;
import org.json.JSONObject;

public class MediationNetworkItem implements Serializable {
    private boolean ics;
    private String networkId;

    public MediationNetworkItem(JSONObject jSONObject) {
        this.ics = jSONObject.optBoolean("ics");
        this.networkId = jSONObject.optString("cid");
    }

    public MediationNetworkItem(boolean z, String str) {
        this.ics = z;
        this.networkId = str;
    }

    public boolean isClientSide() {
        return this.ics;
    }

    public void setIcs(boolean z) {
        this.ics = z;
    }

    public String getNetworkId() {
        return this.networkId;
    }

    public void setNetworkId(String str) {
        this.networkId = str;
    }
}
