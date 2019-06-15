package com.helpshift.campaigns.l;

import android.text.TextUtils;
import com.helpshift.campaigns.i.g;
import com.helpshift.campaigns.models.CampaignSyncModel;
import com.helpshift.q.d;
import com.helpshift.util.a.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class e implements f {
    d a;
    ConcurrentLinkedQueue<g> b = new ConcurrentLinkedQueue();
    private c c;

    public e(d dVar, c cVar) {
        this.a = dVar;
        this.c = cVar;
    }

    public void a(final CampaignSyncModel campaignSyncModel, final String str) {
        if (campaignSyncModel != null && !TextUtils.isEmpty(campaignSyncModel.a) && !TextUtils.isEmpty(campaignSyncModel.b) && !TextUtils.isEmpty(str)) {
            this.c.b(new Runnable() {
                public void run() {
                    d dVar = e.this.a;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("kCampaignSyncModels");
                    stringBuilder.append(str);
                    HashMap hashMap = (HashMap) dVar.a(stringBuilder.toString());
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    hashMap.put(campaignSyncModel.a, campaignSyncModel);
                    d dVar2 = e.this.a;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("kCampaignSyncModels");
                    stringBuilder2.append(str);
                    dVar2.a(stringBuilder2.toString(), hashMap);
                    Iterator it = e.this.b.iterator();
                    while (it.hasNext()) {
                        ((g) it.next()).b(campaignSyncModel);
                    }
                }
            });
        }
    }

    public void a(final String str, final String str2) {
        this.c.b(new Runnable() {
            public void run() {
                d dVar = e.this.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("kCampaignSyncModels");
                stringBuilder.append(str2);
                HashMap hashMap = (HashMap) dVar.a(stringBuilder.toString());
                if (hashMap != null) {
                    hashMap.remove(str);
                    d dVar2 = e.this.a;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("kCampaignSyncModels");
                    stringBuilder2.append(str2);
                    dVar2.a(stringBuilder2.toString(), hashMap);
                    Iterator it = e.this.b.iterator();
                    while (it.hasNext()) {
                        ((g) it.next()).f(str);
                    }
                }
            }
        });
    }

    public void b(final String str, final String str2) {
        this.c.b(new Runnable() {
            public void run() {
                d dVar = e.this.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("kCampaignSyncModels");
                stringBuilder.append(str2);
                HashMap hashMap = (HashMap) dVar.a(stringBuilder.toString());
                if (hashMap != null) {
                    CampaignSyncModel campaignSyncModel = (CampaignSyncModel) hashMap.get(str);
                    if (campaignSyncModel != null) {
                        campaignSyncModel.a(true);
                    }
                    hashMap.put(str, campaignSyncModel);
                    d dVar2 = e.this.a;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("kCampaignSyncModels");
                    stringBuilder2.append(str2);
                    dVar2.a(stringBuilder2.toString(), hashMap);
                }
            }
        });
    }

    public void c(final String str, final String str2) {
        this.c.b(new Runnable() {
            public void run() {
                d dVar = e.this.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("kCampaignSyncModels");
                stringBuilder.append(str2);
                HashMap hashMap = (HashMap) dVar.a(stringBuilder.toString());
                if (hashMap != null) {
                    CampaignSyncModel campaignSyncModel = (CampaignSyncModel) hashMap.get(str);
                    if (campaignSyncModel != null) {
                        campaignSyncModel.a(false);
                    }
                    hashMap.put(str, campaignSyncModel);
                    d dVar2 = e.this.a;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("kCampaignSyncModels");
                    stringBuilder2.append(str2);
                    dVar2.a(stringBuilder2.toString(), hashMap);
                }
            }
        });
    }

    public List<CampaignSyncModel> a(String str) {
        d dVar = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("kCampaignSyncModels");
        stringBuilder.append(str);
        HashMap hashMap = (HashMap) dVar.a(stringBuilder.toString());
        ArrayList arrayList = new ArrayList();
        if (hashMap != null) {
            for (String str2 : hashMap.keySet()) {
                CampaignSyncModel campaignSyncModel = (CampaignSyncModel) hashMap.get(str2);
                if (!(campaignSyncModel == null || campaignSyncModel.a())) {
                    arrayList.add(campaignSyncModel);
                }
            }
        }
        return arrayList;
    }

    public CampaignSyncModel d(String str, String str2) {
        d dVar = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("kCampaignSyncModels");
        stringBuilder.append(str2);
        HashMap hashMap = (HashMap) dVar.a(stringBuilder.toString());
        return hashMap != null ? (CampaignSyncModel) hashMap.get(str) : null;
    }

    public void a(g gVar) {
        if (gVar != null) {
            this.b.add(gVar);
        }
    }

    public void b(final String str) {
        this.c.b(new Runnable() {
            public void run() {
                d dVar = e.this.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("kCampaignSyncModels");
                stringBuilder.append(str);
                HashMap hashMap = (HashMap) dVar.a(stringBuilder.toString());
                HashMap hashMap2 = new HashMap();
                if (hashMap != null) {
                    for (String str : hashMap.keySet()) {
                        CampaignSyncModel campaignSyncModel = (CampaignSyncModel) hashMap.get(str);
                        if (campaignSyncModel.a()) {
                            campaignSyncModel.a(false);
                        }
                        hashMap2.put(str, campaignSyncModel);
                    }
                }
                dVar = e.this.a;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("kCampaignSyncModels");
                stringBuilder2.append(str);
                dVar.a(stringBuilder2.toString(), hashMap2);
            }
        });
    }
}
