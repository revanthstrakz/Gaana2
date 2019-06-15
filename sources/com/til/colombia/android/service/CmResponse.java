package com.til.colombia.android.service;

import com.google.gson.GsonBuilder;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CmResponse {
    private List<CmEntity> cmEntities;
    private long feedSlotId;
    private int pageNo;
    private String reqItemId;
    private int status;

    public CmResponse(byte[] bArr) {
        this(bArr, false);
    }

    public CmResponse(JSONObject jSONObject) {
        this(jSONObject, false);
    }

    public CmResponse(byte[] bArr, boolean z) {
        this.status = -1;
        if (bArr != null) {
            try {
                create(new JSONObject(new String(bArr)), z);
            } catch (Exception unused) {
            }
        }
    }

    public CmResponse(JSONObject jSONObject, boolean z) {
        this.status = -1;
        create(jSONObject, z);
    }

    private void create(JSONObject jSONObject, boolean z) {
        if (jSONObject != null) {
            this.status = jSONObject.optInt("status");
            this.feedSlotId = jSONObject.optLong("id");
            this.pageNo = jSONObject.optInt("pn");
            int i = 0;
            boolean z2 = true;
            if (this.pageNo != 1) {
                z2 = false;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("resArr");
            this.reqItemId = jSONObject.optString(e.Q);
            if (optJSONArray != null) {
                this.cmEntities = new ArrayList();
                while (i < optJSONArray.length()) {
                    CmEntity cmEntity = new CmEntity();
                    try {
                        cmEntity.parseJSONResponse((JSONObject) optJSONArray.get(i), z, z2);
                        if (cmEntity.getCmItems().size() > 0) {
                            this.cmEntities.add(cmEntity);
                            if (CmManager.getInstance().getCmFeedUtil() != null) {
                                CmManager.getInstance().getCmFeedUtil().putCmEntity(cmEntity);
                            }
                        }
                    } catch (JSONException unused) {
                    }
                    i++;
                }
            }
            return;
        }
        this.status = 103;
    }

    public List<CmEntity> getCmEntities() {
        return this.cmEntities;
    }

    public long getFeedSlotId() {
        return this.feedSlotId;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public String getReqItemId() {
        if (h.a(this.reqItemId)) {
            return null;
        }
        return this.reqItemId;
    }

    private JSONArray getFeedResponseJson() {
        if (this.cmEntities.size() <= 0) {
            return null;
        }
        try {
            return new JSONArray(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(this.cmEntities));
        } catch (JSONException unused) {
            return null;
        }
    }

    public boolean isEmpty() {
        return this.cmEntities == null || this.cmEntities.size() <= 0;
    }

    public int status() {
        return this.status;
    }
}
