package com.til.colombia.android.service;

import com.til.colombia.android.internal.ColombiaException;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.network.MediationNetworkItem;
import com.til.colombia.android.network.a;
import com.til.colombia.android.network.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ItemResponse implements Serializable {
    private static final String LOG_TAG = "Col:aos:4.0.0ItemResponse";
    private List<CmItem> adCmItem;
    private int adPageScrollPosition = 0;
    private String adUnitId;
    private ColombiaCarouselAdView carouselView;
    private List<CmItem> contentCmItem;
    private Exception exception;
    private String imprUrl;
    private boolean impressed = false;
    private boolean isCarousel;
    private boolean isValid = false;
    private MediationNetworkItem mediationItem;
    private String nmeta;
    private List<Item> organicItems;
    private List<Item> paidItems;
    private String position;
    private AdRequestParams reqParam;
    private String requestCode;
    private String responseImgUrl;
    private String responseTitle;
    private String sectionId;
    private String snippet;
    private Integer success;
    private int widgetId;

    public int getAdPageScrollPosition() {
        return this.adPageScrollPosition;
    }

    public void setAdPageScrollPosition(int i) {
        this.adPageScrollPosition = i;
    }

    public ItemResponse(AdRequestParams adRequestParams) {
        this.reqParam = adRequestParams;
    }

    public ItemResponse(AdRequestParams adRequestParams, String str) {
        this.reqParam = adRequestParams;
        this.imprUrl = str;
    }

    /* Access modifiers changed, original: protected */
    public AdRequestParams getAdRequestParams() {
        return this.reqParam;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public MediationNetworkItem getMediationNetworkItem() {
        return this.mediationItem;
    }

    public List<Item> getOrganicItems() {
        return this.organicItems;
    }

    public List<Item> getPaidItems() {
        return this.paidItems;
    }

    /* Access modifiers changed, original: protected */
    public List<CmItem> getContentCmItem() {
        return this.contentCmItem;
    }

    /* Access modifiers changed, original: protected */
    public List<CmItem> getAdCmItem() {
        return this.adCmItem;
    }

    public Long getAdUnitId() {
        return Long.valueOf(Long.parseLong(this.adUnitId));
    }

    public String getSection() {
        return this.sectionId;
    }

    public Integer getPosition() {
        return Integer.valueOf(Integer.parseInt(this.position));
    }

    public String getAdSlot() {
        return AdRequestResponse.getAdSlot(getAdUnitId(), getPosition(), getSection());
    }

    public boolean isSuccessful() {
        return this.success != null && this.success.intValue() == 1;
    }

    public boolean isCarousel() {
        return this.isCarousel;
    }

    public String getResponseTitle() {
        return this.responseTitle;
    }

    public String getResponseImgUrl() {
        return this.responseImgUrl;
    }

    public void parseJSONResponse(JSONObject jSONObject, boolean z, boolean z2) {
        if (jSONObject != null) {
            Log.b(LOG_TAG, jSONObject.toString());
            this.snippet = jSONObject.optString(bm.a);
            this.adUnitId = jSONObject.optString("adSlot");
            this.position = jSONObject.optString("position");
            this.sectionId = jSONObject.optString("section");
            this.imprUrl = jSONObject.optString("imprUrl");
            this.nmeta = jSONObject.optString("nmeta");
            this.widgetId = jSONObject.optInt("innId");
            z2 = true;
            if (!z) {
                this.paidItems = parseAds(jSONObject, "items", true);
                if (this.paidItems != null) {
                    this.adCmItem = new ArrayList();
                    for (Item add : this.paidItems) {
                        this.adCmItem.add(add);
                    }
                }
            }
            this.organicItems = parseAds(jSONObject, "oItems", false);
            if (this.organicItems != null) {
                this.contentCmItem = new ArrayList();
                for (Item add2 : this.organicItems) {
                    this.contentCmItem.add(add2);
                }
            }
            this.success = Integer.valueOf(jSONObject.optInt("success"));
            try {
                this.responseTitle = jSONObject.getJSONObject("rtFields").optString("adunitTitle");
            } catch (Exception unused) {
                this.responseTitle = "";
            }
            try {
                this.responseImgUrl = jSONObject.getJSONObject("rtFields").optString("adunitIcon");
            } catch (Exception unused2) {
                this.responseImgUrl = "";
            }
            if (!(jSONObject.optInt("icr") == 1 && jSONObject.optInt("isc") == 1)) {
                z2 = false;
            }
            this.isCarousel = z2;
        }
    }

    private List<Item> parseAds(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            if (optJSONArray == null) {
                return arrayList;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                arrayList.add(new NativeItem(this, jSONObject2, this.reqParam, this.snippet, z));
                this.mediationItem = new MediationNetworkItem(jSONObject2.optBoolean("ics"), jSONObject2.optString("cid"));
            }
            return arrayList;
        } catch (JSONException e) {
            Log.a(LOG_TAG, "Error in parsing items", new ColombiaException(e));
            return null;
        }
    }

    public void setPaidItems(Item item) {
        if (this.paidItems == null) {
            this.paidItems = new LinkedList();
            this.paidItems.add(item);
        }
    }

    public boolean isImpressed() {
        return this.impressed;
    }

    /* Access modifiers changed, original: protected */
    public void trackItemImpression(List<Item> list) {
        if (list != null) {
            for (Item item : list) {
                ((NativeItem) item).recordImpression(null);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void trackItemImpression(Item item) {
        if (item != null) {
            ((NativeItem) item).recordImpression(null);
        }
    }

    /* JADX WARNING: Missing block: B:16:0x005b, code skipped:
            return;
     */
    public synchronized void recordItemResponseImpression(android.view.View r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r3 = r2.isImpressed();	 Catch:{ all -> 0x005c }
        if (r3 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r2);
        return;
    L_0x0009:
        r3 = r2.imprUrl;	 Catch:{ all -> 0x005c }
        r3 = com.til.colombia.android.internal.a.h.a(r3);	 Catch:{ all -> 0x005c }
        if (r3 != 0) goto L_0x005a;
    L_0x0011:
        r3 = r2.getAdRequestParams();	 Catch:{ all -> 0x005c }
        r3 = r3.getAdManager();	 Catch:{ all -> 0x005c }
        if (r3 == 0) goto L_0x004a;
    L_0x001b:
        r3 = r2.getAdRequestParams();	 Catch:{ all -> 0x005c }
        r3 = r3.getAdManager();	 Catch:{ all -> 0x005c }
        r3 = r3.isFirstImpression();	 Catch:{ all -> 0x005c }
        if (r3 == 0) goto L_0x004a;
    L_0x0029:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005c }
        r3.<init>();	 Catch:{ all -> 0x005c }
        r0 = r2.imprUrl;	 Catch:{ all -> 0x005c }
        r3.append(r0);	 Catch:{ all -> 0x005c }
        r0 = "&pv=1";
        r3.append(r0);	 Catch:{ all -> 0x005c }
        r3 = r3.toString();	 Catch:{ all -> 0x005c }
        r2.imprUrl = r3;	 Catch:{ all -> 0x005c }
        r3 = r2.getAdRequestParams();	 Catch:{ all -> 0x005c }
        r3 = r3.getAdManager();	 Catch:{ all -> 0x005c }
        r0 = 0;
        r3.setFirstImpression(r0);	 Catch:{ all -> 0x005c }
    L_0x004a:
        r3 = 1;
        r2.impressed = r3;	 Catch:{ all -> 0x005c }
        r3 = new com.til.colombia.android.service.cd;	 Catch:{ all -> 0x005c }
        r3.<init>(r2);	 Catch:{ all -> 0x005c }
        r0 = r2.getAdImpressionUrl();	 Catch:{ all -> 0x005c }
        r1 = 5;
        com.til.colombia.android.network.l.a(r0, r1, r3);	 Catch:{ all -> 0x005c }
    L_0x005a:
        monitor-exit(r2);
        return;
    L_0x005c:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.ItemResponse.recordItemResponseImpression(android.view.View):void");
    }

    /* Access modifiers changed, original: protected */
    public String getAdImpressionUrl() {
        return this.imprUrl;
    }

    public ColombiaAdManager getAdManager() {
        return this.reqParam != null ? this.reqParam.getAdManager() : null;
    }

    public void setRequestCode(String str) {
        this.requestCode = str;
    }

    public String getRequestCode() {
        return this.requestCode;
    }

    public void destroy() {
        this.organicItems = null;
        this.paidItems = null;
        this.adUnitId = null;
        this.sectionId = null;
        this.position = null;
        this.success = null;
        this.reqParam = null;
        this.mediationItem = null;
    }

    public int getWidgetId() {
        return this.widgetId;
    }

    /* Access modifiers changed, original: protected */
    public void putCarouselView(ColombiaCarouselAdView colombiaCarouselAdView) {
        this.carouselView = colombiaCarouselAdView;
    }

    public ColombiaCarouselAdView getCarouselAdview() {
        return this.carouselView;
    }

    /* Access modifiers changed, original: protected */
    public void recordItemResponseImpression() {
        this.impressed = true;
        a cmImpressionNotifier = CmManager.getInstance().getCmImpressionNotifier();
        String str = this.nmeta;
        if (!h.a(str)) {
            if (cmImpressionNotifier.b == null) {
                cmImpressionNotifier.b = new ArrayList();
            }
            if (cmImpressionNotifier.c == null) {
                cmImpressionNotifier.c = new ArrayList();
            }
            new Thread(new c(cmImpressionNotifier, str)).start();
        }
        trackItemImpression(this.organicItems);
        trackItemImpression(this.paidItems);
    }
}
