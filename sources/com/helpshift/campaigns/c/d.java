package com.helpshift.campaigns.c;

import android.text.TextUtils;
import com.helpshift.b.a;
import com.helpshift.campaigns.i.c;
import com.helpshift.campaigns.l.f;
import com.helpshift.campaigns.models.AnalyticsEvent;
import com.helpshift.campaigns.models.CampaignSyncModel;
import com.helpshift.campaigns.models.b;
import com.helpshift.network.b.e;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.network.i;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d implements a, c, i {
    f a;
    com.helpshift.q.d b;
    private com.helpshift.campaigns.l.d c;
    private g d;
    private com.helpshift.campaigns.e.a e = new com.helpshift.campaigns.e.a(this);

    public void a(Integer num) {
    }

    public void b() {
    }

    public com.helpshift.network.a.a e() {
        return null;
    }

    public d(com.helpshift.campaigns.l.d dVar, f fVar, g gVar, com.helpshift.q.d dVar2) {
        this.c = dVar;
        this.a = fVar;
        this.d = gVar;
        this.b = dVar2;
        this.a.a(this.e);
        this.c.a(this.e);
        this.a.b(gVar.a().a);
        o.a().a((a) this);
    }

    public void a(String str) {
        this.a.b(str, this.d.a().a);
    }

    public void a(CampaignSyncModel campaignSyncModel, String str) {
        try {
            b bVar = new b(campaignSyncModel.a, new JSONObject(str), campaignSyncModel.c, campaignSyncModel.d);
            this.a.a(campaignSyncModel.a, this.d.a().a);
            this.c.a(bVar);
            b.a().e.a(AnalyticsEvent.a.b, campaignSyncModel.a, Boolean.valueOf(false));
        } catch (JSONException e) {
            l.a("Helpshift_ISControl", "Exception while parsing json string of campaign detail object", e);
        }
    }

    public void b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Campaign download failed : ");
        stringBuilder.append(str);
        l.a("Helpshift_ISControl", stringBuilder.toString());
        this.a.c(str, this.d.a().a);
    }

    public void a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Campaign icon image download complete : ");
        stringBuilder.append(str);
        l.a("Helpshift_ISControl", stringBuilder.toString());
        this.c.a(str, str2);
    }

    public void c(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Campaign icon download failed : ");
        stringBuilder.append(str);
        l.a("Helpshift_ISControl", stringBuilder.toString());
    }

    public void b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Campaign cover image download complete : ");
        stringBuilder.append(str);
        stringBuilder.append(", File path : ");
        stringBuilder.append(str2);
        l.a("Helpshift_ISControl", stringBuilder.toString());
        this.c.b(str, str2);
    }

    public void d(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Campaign cover image download failed : ");
        stringBuilder.append(str);
        l.a("Helpshift_ISControl", stringBuilder.toString());
    }

    public void e(String str) {
        this.e.i(str);
    }

    public void a() {
        for (CampaignSyncModel campaignSyncModel : this.a.a(this.d.a().a)) {
            l.a("Helpshift_ISControl", "Starting unsynced campaign download");
            this.e.a(campaignSyncModel);
        }
    }

    public void c(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Campaign icon image download start : ");
        stringBuilder.append(str2);
        stringBuilder.append(", URL : ");
        stringBuilder.append(str);
        l.a("Helpshift_ISControl", stringBuilder.toString());
        this.e.a(str, str2);
    }

    public void d(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Campaign cover image download start : ");
        stringBuilder.append(str2);
        stringBuilder.append(", URL : ");
        stringBuilder.append(str);
        l.a("Helpshift_ISControl", stringBuilder.toString());
        this.e.b(str, str2);
    }

    public com.helpshift.network.a.a d() {
        HashMap hashMap = new HashMap();
        hashMap.put("did", b.a().a.b.a());
        final String str = b.a().d.a().a;
        hashMap.put("uid", str);
        com.helpshift.q.d dVar = this.b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hs__campaigns_inbox_cursor");
        stringBuilder.append(str);
        String str2 = (String) dVar.a(stringBuilder.toString());
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("cursor", str2);
        }
        return new com.helpshift.network.a.a(0, "/ma/inbox/", hashMap, new e.b<JSONObject>() {
            public void a(JSONObject jSONObject, Integer num) {
                com.helpshift.k.b.a().b.b(Boolean.valueOf(true));
                String optString = jSONObject.optString("cursor", "");
                if (!TextUtils.isEmpty(optString)) {
                    com.helpshift.q.d dVar = d.this.b;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("hs__campaigns_inbox_cursor");
                    stringBuilder.append(str);
                    dVar.a(stringBuilder.toString(), optString);
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("campaigns");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            try {
                                String optString2 = optJSONObject.optString("cid", "");
                                String a = com.helpshift.campaigns.m.b.a(optString2);
                                com.helpshift.k.b.a().b.b(optString2, a);
                                optJSONObject.put("cid", a);
                                d.this.a.a(new CampaignSyncModel(optJSONObject), str);
                            } catch (JSONException unused) {
                                l.a("Helpshift_ISControl", "Error while parsing creative");
                            }
                        }
                    }
                }
            }
        }, new e.a() {
            public void a(NetworkError networkError, Integer num) {
            }
        }, new com.helpshift.network.b.c());
    }
}
