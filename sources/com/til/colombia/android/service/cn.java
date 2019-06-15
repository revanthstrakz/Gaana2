package com.til.colombia.android.service;

import java.util.Collection;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class cn {
    private Collection<AdRequestResponse> a;
    private bl b;

    public cn(bl blVar, m mVar) throws JSONException {
        this.b = blVar;
        a(blVar, mVar);
    }

    private void a(bl blVar, m mVar) throws JSONException {
        HashMap hashMap = new HashMap();
        this.a = blVar.getAdRequests();
        JSONArray jSONArray = mVar.d;
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                ItemResponse itemResponse = new ItemResponse(new AdRequestParams().setVideoAutoPlay(blVar.isVideoAutoPlay()).setAdManager(blVar.getAdManager()));
                itemResponse.parseJSONResponse((JSONObject) jSONArray.get(i), false, false);
                if (mVar.a || !mVar.c || mVar.b != null) {
                    itemResponse.setException(mVar.b);
                }
                hashMap.put(itemResponse.getAdSlot(), itemResponse);
            }
        }
        for (AdRequestResponse adRequestResponse : this.a) {
            ItemResponse itemResponse2 = (ItemResponse) hashMap.get(adRequestResponse.getAdSlot());
            if (itemResponse2 != null) {
                adRequestResponse.setResponse(itemResponse2);
            } else {
                ItemResponse itemResponse3 = new ItemResponse(new AdRequestParams().setVideoAutoPlay(blVar.isVideoAutoPlay()).setAdManager(blVar.getAdManager()));
                itemResponse3.setException(mVar.b);
                adRequestResponse.setResponse(itemResponse3);
            }
        }
    }

    public final void a() {
        for (AdRequestResponse dispatchResponse : this.a) {
            dispatchResponse.dispatchResponse(this.b);
        }
    }
}
