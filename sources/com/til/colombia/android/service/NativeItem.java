package com.til.colombia.android.service;

import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.android.volley.Request;
import com.android.volley.toolbox.k;
import com.android.volley.toolbox.n;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.gson.annotations.Expose;
import com.til.colombia.android.commons.CommonUtil.AutoPlay;
import com.til.colombia.android.commons.CommonUtil.MediaSource;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.a.d;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.g;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.l;
import com.til.colombia.android.service.ColombiaAdManager.AD_NTWK;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NativeItem extends Item {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    @Expose
    private String adAttributionText;
    @Expose
    private String adAttributionUrl;
    @Expose
    private String adChoiceClickUrl;
    @Expose
    private String adChoiceImageUrl;
    @Nullable
    private transient AdListener adListener;
    @Nullable
    private transient ColombiaAdManager adManager;
    private String adUrl = null;
    @Nullable
    q audioBannerView;
    @Nullable
    private transient ah audioPlayer;
    private Integer autoPlayMode;
    @Expose
    private String brand;
    @Expose
    private String cTag;
    private List<String> clickTrackers;
    @Expose
    private String ctaText;
    private String ctaVideoUrl;
    private String currency;
    @Expose
    private transient JSONObject dataTags;
    private Integer dataType;
    @Expose
    private String deepLink;
    @Expose
    private String desc;
    @Expose
    private String displayUrl;
    @Expose
    private Long downloads;
    private Integer duration;
    @Expose
    private Date expiry;
    private transient Bitmap icon;
    private transient Bitmap image;
    private String imageDataUrl;
    private int imageHeight;
    private int imageWidth;
    @Expose
    private List<String> img;
    private List<String> impTrackers;
    private boolean impressed = false;
    private boolean isAd;
    private boolean isDisplayed = false;
    private boolean isSOVItem = false;
    private String itemId;
    @Nullable
    private transient ItemResponse itemResponse;
    @Expose
    private Integer itemType;
    private Integer lineItemId;
    private String logo;
    private VASTHelper mVastHelper;
    private String mediaJson;
    private String mediaSrc;
    private MediaSource mediaSrcMode;
    private Double mrp;
    private String networkId;
    private String offerCode;
    private String offerText;
    private Double price;
    @Expose
    private Double rating;
    @Nullable
    private transient ck receiver;
    @Expose
    private String redirectUrl;
    @Expose
    private String sTag;
    private String snippet;
    @Expose
    private String title;
    @Expose
    private String uid;
    private String url;
    @Expose
    private Long views;

    private void parseUnusedFields() throws JSONException {
    }

    /* Access modifiers changed, original: protected */
    public void setItemResponse(@Nullable ItemResponse itemResponse) {
    }

    public Object thirdPartyAd() {
        return null;
    }

    public NativeItem(ItemResponse itemResponse, JSONObject jSONObject, AdRequestParams adRequestParams, String str, boolean z) throws JSONException {
        if (jSONObject != null) {
            this.itemResponse = itemResponse;
            this.isAd = z;
            parseExposedFields(jSONObject);
            parseOtherFields(jSONObject, str);
            parseUnusedFields();
            this.adManager = adRequestParams.getAdManager();
            this.adListener = adRequestParams.getAdListener();
            if (!adRequestParams.isVideoAutoPlay()) {
                this.autoPlayMode = Integer.valueOf(0);
            }
        }
    }

    private String getString(JSONObject jSONObject, String str) throws JSONException {
        return jSONObject.has(str) ? jSONObject.optString(str) : null;
    }

    private Double getDouble(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.has(str)) {
            return null;
        }
        Double valueOf = Double.valueOf(jSONObject.optDouble(str));
        if (valueOf.equals(Double.valueOf(Double.NaN))) {
            return null;
        }
        return valueOf;
    }

    private Long getLong(JSONObject jSONObject, String str) throws JSONException {
        return jSONObject.has(str) ? Long.valueOf(jSONObject.optLong(str)) : null;
    }

    private Integer getInt(JSONObject jSONObject, String str) throws JSONException {
        return jSONObject.has(str) ? Integer.valueOf(jSONObject.optInt(str)) : null;
    }

    private void parseExposedFields(JSONObject jSONObject) throws JSONException {
        this.uid = UUID.randomUUID().toString();
        this.title = getString(jSONObject, "name");
        this.desc = getString(jSONObject, "desc");
        this.brand = getString(jSONObject, "brand");
        this.ctaText = getString(jSONObject, "ctaText");
        this.rating = getDouble(jSONObject, "rating");
        this.downloads = getLong(jSONObject, "downloads");
        this.views = getLong(jSONObject, "views");
        this.expiry = h.b(getString(jSONObject, "expiryDate"));
        this.adAttributionText = getString(jSONObject, "adAttributionText");
        this.adAttributionUrl = getString(jSONObject, "adAttributionUrl");
        this.img = cb.a(jSONObject.optJSONArray("sImg"));
        this.itemType = getInt(jSONObject, "itemType");
        if (this.itemType == null) {
            this.itemType = Integer.valueOf(0);
        }
        this.displayUrl = getString(jSONObject, "displayUrl");
        this.deepLink = getString(jSONObject, "dlAndroid");
        this.redirectUrl = d.a(getString(jSONObject, "rurl"));
        this.dataTags = jSONObject.optJSONObject("dt");
        jSONObject = jSONObject.optJSONObject("ITN");
        if (jSONObject != null) {
            this.sTag = getString(jSONObject, "stg");
            this.cTag = getString(jSONObject, "ctg");
        }
    }

    private void parseOtherFields(JSONObject jSONObject, String str) throws JSONException {
        this.snippet = getString(jSONObject, bm.a);
        if (h.a(this.snippet)) {
            this.snippet = str;
        }
        this.url = d.a(getString(jSONObject, "url"));
        this.itemId = getString(jSONObject, bm.b);
        this.impTrackers = cb.a(jSONObject.optJSONArray("imprTrackers"));
        List list = this.impTrackers;
        String optString = jSONObject.optString("imprUrl");
        if (!h.a(optString)) {
            if (list == null) {
                list = new ArrayList();
            }
            list.add(optString);
        }
        this.impTrackers = list;
        this.clickTrackers = cb.a(jSONObject.optJSONArray("clickTrackers"));
        this.lineItemId = getInt(jSONObject, bm.c);
        this.networkId = getString(jSONObject, "cid");
        this.logo = getString(jSONObject, "logo");
        this.adChoiceClickUrl = getString(jSONObject, "adchoicesClickURL");
        this.adChoiceImageUrl = getString(jSONObject, "adchoicesImageURL");
        setMediaSrc(jSONObject);
        this.autoPlayMode = getInt(jSONObject, "playStrategy");
        this.duration = getInt(jSONObject, "duration");
        this.dataType = getInt(jSONObject, "dataType");
        this.offerCode = getString(jSONObject, "oc");
        this.offerText = getString(jSONObject, "offerText");
        this.currency = getString(jSONObject, "currency");
        this.price = getDouble(jSONObject, InMobiNetworkValues.PRICE);
        this.mrp = getDouble(jSONObject, "mrp");
        if (!(this.price == null || this.mrp == null || Double.compare(this.price.doubleValue(), this.mrp.doubleValue()) < 0)) {
            this.mrp = null;
        }
        if (!(this.itemType.intValue() != ITEM_TYPE.GENERAL.ordinal() || getDataType() == 1 || getDataType() == 2)) {
            this.itemType = Integer.valueOf(ITEM_TYPE.CONTENT.ordinal());
        }
        if (this.itemType.intValue() == ITEM_TYPE.VIDEO_INCENTIVE.ordinal() && getDataType() == 1) {
            this.itemType = Integer.valueOf(ITEM_TYPE.INTERSTITIAL_VIDEO.ordinal());
            if (!(this.itemResponse == null || this.itemResponse.getAdImpressionUrl() == null)) {
                Object adImpressionUrl = this.itemResponse.getAdImpressionUrl();
                if (getAdManager() != null && getAdManager().isFirstImpression()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(adImpressionUrl);
                    stringBuilder.append("&pv=1");
                    adImpressionUrl = stringBuilder.toString();
                    getAdManager().setFirstImpression(false);
                }
                this.impTrackers.add(adImpressionUrl);
            }
        }
        Integer num = getInt(jSONObject, "sov_itemtype");
        if (getItemType() == ITEM_TYPE.SOV) {
            if (num == null) {
                this.itemType = Integer.valueOf(a.n());
            } else {
                this.itemType = num;
            }
            this.isSOVItem = true;
        }
    }

    private void setMediaSrc(JSONObject jSONObject) {
        if (jSONObject.optString("vastUrl") != null && !jSONObject.optString("vastUrl").isEmpty()) {
            this.mediaSrc = jSONObject.optString("vastUrl");
            this.mediaSrcMode = MediaSource.VAST_URL;
            this.mediaJson = jSONObject.optString("vastXml");
        } else if (jSONObject.optString("vpaidvastUrl") != null && !jSONObject.optString("vpaidvastUrl").isEmpty()) {
            this.mediaSrc = jSONObject.optString("vpaidvastUrl");
            this.mediaSrcMode = MediaSource.VPAID_URL;
            this.mediaJson = jSONObject.optString("vastXml");
        } else if (jSONObject.optString("vastXml") == null || jSONObject.optString("vastXml").isEmpty()) {
            this.mediaSrcMode = MediaSource.NONE;
        } else {
            this.mediaSrc = jSONObject.optString("vastXml");
            this.mediaSrcMode = MediaSource.VAST_XML;
            this.mediaJson = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public ColombiaAdManager getAdManager() {
        return this.adManager;
    }

    /* Access modifiers changed, original: protected */
    public void setAdListener(@Nullable AdListener adListener) {
        this.adListener = adListener;
    }

    /* Access modifiers changed, original: protected */
    public AdListener getAdListener() {
        return this.adListener;
    }

    /* Access modifiers changed, original: protected */
    public ItemResponse getItemResponse() {
        return this.itemResponse;
    }

    public String getItemId() {
        return this.itemId;
    }

    public AD_NTWK getAdNetwork() {
        if (getNetworkId().equalsIgnoreCase(g.g)) {
            return AD_NTWK.CRITEO;
        }
        return AD_NTWK.COLOMBIA;
    }

    /* Access modifiers changed, original: protected */
    public String getNetworkId() {
        return this.networkId;
    }

    public Integer getLineItemId() {
        return this.lineItemId;
    }

    public boolean isAd() {
        return this.isAd;
    }

    public String getUID() {
        return this.uid;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return getBodyText();
    }

    public String getBrand() {
        return getBrandText();
    }

    public String getCtaText() {
        return this.ctaText;
    }

    public Double getStarRating() {
        if (this.rating == null) {
            return Double.valueOf(0.0d);
        }
        return this.rating;
    }

    public Long getDownloadsCount() {
        if (this.downloads == null) {
            return Long.valueOf(0);
        }
        return this.downloads;
    }

    public Long getViewsCount() {
        if (this.views == null) {
            return Long.valueOf(0);
        }
        return this.views;
    }

    public Date getExpiry() {
        return this.expiry;
    }

    public String getAdAttrText() {
        return getAdAttributionText();
    }

    public String getAdAttrUrl() {
        return getAdAttributionUrl();
    }

    public String getAdChoiceClickUrl() {
        return this.adChoiceClickUrl;
    }

    public String getAdChoiceImageUrl() {
        return this.adChoiceImageUrl;
    }

    public String getImageUrl() {
        if (this.itemType.intValue() != ITEM_TYPE.VIDEO_INCENTIVE.ordinal() || getVastHelper() == null || getVastHelper().getSponsoredAdConfig() == null || !getVastHelper().getSponsoredAdConfig().isPreConfigPresent()) {
            return (this.img == null || this.img.size() <= 0 || this.img.get(0) == null || ((String) this.img.get(0)).length() <= 0) ? null : (String) this.img.get(0);
        } else {
            return getVastHelper().getPreCompanionImageSrc();
        }
    }

    public String getIconUrl() {
        if (h.a(this.logo)) {
            return getImageUrl();
        }
        return this.logo;
    }

    public ITEM_TYPE getItemType() {
        if (this.itemType == null) {
            return ITEM_TYPE.UNDECLARED;
        }
        if (this.itemType.intValue() == ITEM_TYPE.PRODUCT.ordinal()) {
            return ITEM_TYPE.PRODUCT;
        }
        if (this.itemType.intValue() == ITEM_TYPE.CONTENT.ordinal()) {
            return ITEM_TYPE.CONTENT;
        }
        if (this.itemType.intValue() == ITEM_TYPE.APP.ordinal()) {
            return ITEM_TYPE.APP;
        }
        if (this.itemType.intValue() == ITEM_TYPE.GENERAL.ordinal()) {
            return ITEM_TYPE.GENERAL;
        }
        if (this.itemType.intValue() == ITEM_TYPE.VIDEO.ordinal()) {
            return ITEM_TYPE.VIDEO;
        }
        if (this.itemType.intValue() == ITEM_TYPE.LEADGEN.ordinal()) {
            return ITEM_TYPE.LEADGEN;
        }
        if (this.itemType.intValue() == ITEM_TYPE.AUDIO.ordinal()) {
            return ITEM_TYPE.AUDIO;
        }
        if (this.itemType.intValue() == ITEM_TYPE.VIDEO_INCENTIVE.ordinal()) {
            return ITEM_TYPE.VIDEO_INCENTIVE;
        }
        if (this.itemType.intValue() == ITEM_TYPE.AUDIO_BANNER.ordinal()) {
            return ITEM_TYPE.AUDIO_BANNER;
        }
        if (this.itemType.intValue() == ITEM_TYPE.BANNER.ordinal()) {
            return ITEM_TYPE.BANNER;
        }
        if (this.itemType.intValue() == ITEM_TYPE.INTERSTITIAL_VIDEO.ordinal()) {
            return ITEM_TYPE.INTERSTITIAL_VIDEO;
        }
        if (this.itemType.intValue() == ITEM_TYPE.SOV.ordinal()) {
            return ITEM_TYPE.SOV;
        }
        return ITEM_TYPE.GENERAL;
    }

    public String getDisplayUrl() {
        return this.displayUrl;
    }

    public String getDeepLink() {
        return this.deepLink;
    }

    public String getRedirectionUrl() {
        return this.redirectUrl;
    }

    public String getTags() {
        return this.sTag;
    }

    public JSONObject getDataTags() {
        return this.dataTags;
    }

    /* Access modifiers changed, original: protected */
    public String getUrl() {
        if (this.itemType.intValue() == ITEM_TYPE.VIDEO.ordinal() || this.itemType.intValue() == ITEM_TYPE.INTERSTITIAL_VIDEO.ordinal()) {
            return getCtaVideoUrl();
        }
        return this.url;
    }

    /* Access modifiers changed, original: protected */
    public Set<String> getClickTrackers() {
        return new HashSet(this.clickTrackers);
    }

    /* Access modifiers changed, original: protected */
    public boolean onClick() {
        return this.adListener != null ? this.adListener.onItemClick(this) : false;
    }

    /* Access modifiers changed, original: protected */
    public void registerItemClick() {
        Request nVar = new n(0, this.url, new co(this), new cp(this));
        nVar.setTag("click");
        if (com.til.colombia.android.network.d.b == null) {
            com.android.volley.h hVar = new com.android.volley.h(new k(), new com.android.volley.toolbox.a(new com.til.colombia.android.network.g()), 1);
            com.til.colombia.android.network.d.b = hVar;
            hVar.a();
        }
        com.til.colombia.android.network.d.b.a(nVar);
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized void performClick() {
        synchronized (this) {
            if (this.clickTrackers != null) {
                for (String replaceAll : this.clickTrackers) {
                    StringBuilder stringBuilder = new StringBuilder("=");
                    stringBuilder.append(System.currentTimeMillis());
                    l.a(replaceAll.replaceAll("=\\$TS", stringBuilder.toString()), 5, new cq(this));
                }
            }
        }
        String str = i.f;
        StringBuilder stringBuilder2 = new StringBuilder("\"");
        stringBuilder2.append(getItemId());
        stringBuilder2.append("\" item click-trackers submitted");
        Log.a(str, stringBuilder2.toString());
    }

    /* Access modifiers changed, original: protected */
    public Set<String> getImpressionTrackers() {
        return new HashSet(this.impTrackers);
    }

    public boolean isImpressed() {
        return this.impressed;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:19:0x005a, code skipped:
            r6 = com.til.colombia.android.internal.i.f;
            r0 = new java.lang.StringBuilder("\"");
            r0.append(getItemId());
            r0.append("\" item impression-trackers submitted.");
            com.til.colombia.android.internal.Log.a(r6, r0.toString());
     */
    /* JADX WARNING: Missing block: B:20:0x0076, code skipped:
            return;
     */
    public void recordImpression(android.view.View r6) {
        /*
        r5 = this;
        r6 = r5.isImpressed();
        if (r6 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        monitor-enter(r5);
        r6 = r5.isImpressed();	 Catch:{ all -> 0x0077 }
        if (r6 == 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r5);	 Catch:{ all -> 0x0077 }
        return;
    L_0x0010:
        r6 = 1;
        r5.impressed = r6;	 Catch:{ all -> 0x0077 }
        r6 = r5.getItemResponse();	 Catch:{ all -> 0x0077 }
        if (r6 == 0) goto L_0x0021;
    L_0x0019:
        r6 = r5.getItemResponse();	 Catch:{ all -> 0x0077 }
        r0 = 0;
        r6.recordItemResponseImpression(r0);	 Catch:{ all -> 0x0077 }
    L_0x0021:
        r6 = r5.impTrackers;	 Catch:{ all -> 0x0077 }
        if (r6 == 0) goto L_0x0059;
    L_0x0025:
        r6 = r5.impTrackers;	 Catch:{ all -> 0x0077 }
        r6 = r6.iterator();	 Catch:{ all -> 0x0077 }
    L_0x002b:
        r0 = r6.hasNext();	 Catch:{ all -> 0x0077 }
        if (r0 == 0) goto L_0x0059;
    L_0x0031:
        r0 = r6.next();	 Catch:{ all -> 0x0077 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0077 }
        r1 = "=\\$TS";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0077 }
        r3 = "=";
        r2.<init>(r3);	 Catch:{ all -> 0x0077 }
        r3 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0077 }
        r2.append(r3);	 Catch:{ all -> 0x0077 }
        r2 = r2.toString();	 Catch:{ all -> 0x0077 }
        r0 = r0.replaceAll(r1, r2);	 Catch:{ all -> 0x0077 }
        r1 = new com.til.colombia.android.service.cr;	 Catch:{ all -> 0x0077 }
        r1.<init>(r5);	 Catch:{ all -> 0x0077 }
        r2 = 5;
        com.til.colombia.android.network.l.a(r0, r2, r1);	 Catch:{ all -> 0x0077 }
        goto L_0x002b;
    L_0x0059:
        monitor-exit(r5);	 Catch:{ all -> 0x0077 }
        r6 = "Col:aos:4.0.0";
        r0 = new java.lang.StringBuilder;
        r1 = "\"";
        r0.<init>(r1);
        r1 = r5.getItemId();
        r0.append(r1);
        r1 = "\" item impression-trackers submitted.";
        r0.append(r1);
        r0 = r0.toString();
        com.til.colombia.android.internal.Log.a(r6, r0);
        return;
    L_0x0077:
        r6 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0077 }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.NativeItem.recordImpression(android.view.View):void");
    }

    /* Access modifiers changed, original: protected */
    public void setCtaVideoUrl(String str) {
        this.ctaVideoUrl = str;
    }

    /* Access modifiers changed, original: protected */
    public String getCtaVideoUrl() {
        return this.ctaVideoUrl;
    }

    public void setVastHelper(VASTHelper vASTHelper) {
        this.mVastHelper = vASTHelper;
    }

    public VASTHelper getVastHelper() {
        return this.mVastHelper;
    }

    public List<String> getVastClickTrackers() {
        return this.mVastHelper != null ? this.mVastHelper.getClickTrackingUrl() : null;
    }

    /* Access modifiers changed, original: protected */
    public String getMediaJson() {
        return this.mediaJson;
    }

    public String getMediaSrc() {
        return this.mediaSrc;
    }

    public MediaSource getMediaSrcMode() {
        return this.mediaSrcMode;
    }

    public AutoPlay getPlayMode() {
        if (this.autoPlayMode == null || this.autoPlayMode.intValue() != 1) {
            return AutoPlay.OFF;
        }
        return AutoPlay.ON;
    }

    /* Access modifiers changed, original: protected */
    public boolean isOnCall() {
        return getAdManager().isOnCall();
    }

    public String getPrice() {
        if (this.price == null || this.price.equals(Double.valueOf(Double.NaN))) {
            return "";
        }
        try {
            String[] split = this.price.toString().split("\\.");
            if (Integer.parseInt(split[1]) == 0) {
                return split[0];
            }
            return this.price.toString();
        } catch (Exception unused) {
            return this.price.toString();
        }
    }

    public String getCurrency() {
        if (h.a(this.currency)) {
            this.currency = "Rs.";
        }
        return this.currency;
    }

    public String getMRP() {
        if (this.mrp == null || this.mrp.equals(Double.valueOf(Double.NaN))) {
            return "";
        }
        try {
            String[] split = this.mrp.toString().split("\\.");
            if (Integer.parseInt(split[1]) == 0) {
                return split[0];
            }
            return this.mrp.toString();
        } catch (Exception unused) {
            return this.mrp.toString();
        }
    }

    public String getOfferCode() {
        return this.offerCode;
    }

    public String getOfferText() {
        return this.offerText;
    }

    /* Access modifiers changed, original: protected */
    public long getDiscount() {
        return (this.mrp == null || this.price == null) ? 0 : Math.round(((this.mrp.doubleValue() - this.price.doubleValue()) * 100.0d) / this.mrp.doubleValue());
    }

    public int getAdImgWidth() {
        return this.imageWidth;
    }

    public int getAdImgHeight() {
        return this.imageHeight;
    }

    public void setAdImgWidth(int i) {
        this.imageWidth = i;
    }

    public void setAdImgHeight(int i) {
        this.imageHeight = i;
    }

    /* Access modifiers changed, original: protected */
    public void setImageDataUrl(String str) {
        this.imageDataUrl = str;
    }

    /* Access modifiers changed, original: protected */
    public String getSnippet() {
        return this.snippet;
    }

    /* Access modifiers changed, original: protected */
    public int getDataType() {
        if (this.dataType == null) {
            return -1;
        }
        return this.dataType.intValue();
    }

    /* Access modifiers changed, original: protected */
    public String toJSONObjectString() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray;
            jSONObject.put("adAttributionText", this.adAttributionText);
            jSONObject.put("adAttributionUrl", this.adAttributionUrl);
            jSONObject.put("autoPlayMode", this.autoPlayMode);
            jSONObject.put("brand", this.brand);
            if (this.clickTrackers != null && this.clickTrackers.size() > 0) {
                jSONArray = new JSONArray();
                for (String put : this.clickTrackers) {
                    jSONArray.put(put);
                }
                jSONObject.put("clickTrackers", jSONArray);
            }
            jSONObject.put("ctaText", this.ctaText);
            jSONObject.put("ctaVideoUrl", this.ctaVideoUrl);
            jSONObject.put("dataType", this.dataType);
            jSONObject.put("deepLink", this.deepLink);
            jSONObject.put("desc", this.desc);
            jSONObject.put("displayUrl", this.displayUrl);
            jSONObject.put("downloads", this.downloads);
            jSONObject.put("duration", this.duration);
            jSONObject.put("imageDataUrl", this.imageDataUrl);
            if (this.impTrackers != null && this.impTrackers.size() > 0) {
                jSONArray = new JSONArray();
                for (String put2 : this.impTrackers) {
                    jSONArray.put(put2);
                }
                jSONObject.put("impTrackers", jSONArray);
            }
            jSONObject.put(bm.b, this.itemId);
            jSONObject.put("itemType", this.itemType);
            jSONObject.put("lineItemId", this.lineItemId);
            jSONObject.put("oc", this.offerCode);
            jSONObject.put("logo", this.logo);
            jSONObject.put("mediaJson", this.mediaJson);
            jSONObject.put("mediaSrc", this.mediaSrc);
            jSONObject.put("mediaSrcMode", this.mediaSrcMode);
            jSONObject.put("name", this.title);
            jSONObject.put("offerText", this.offerText);
            jSONObject.put(InMobiNetworkValues.PRICE, this.price);
            jSONObject.put("mrp", this.mrp);
            jSONObject.put("rating", this.rating);
            if (this.img != null && this.img.size() > 0) {
                jSONArray = new JSONArray();
                for (String put22 : this.img) {
                    jSONArray.put(put22);
                }
                jSONObject.put("sImg", jSONArray);
            }
            jSONObject.put(bm.a, this.snippet);
            jSONObject.put("uid", this.uid);
            jSONObject.put("url", this.url);
            jSONObject.put("views", this.views);
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    /* Access modifiers changed, original: protected */
    public void setDuration(int i) {
        this.duration = Integer.valueOf(i);
    }

    public Integer getDuration() {
        return this.duration;
    }

    public boolean isSOVItem() {
        return this.isSOVItem;
    }

    public int getMediaAdWidth() {
        try {
            return getVastHelper().getBestCompanionAdConfig().getWidth();
        } catch (Exception unused) {
            return 0;
        }
    }

    public int getMediaAdHeight() {
        try {
            return getVastHelper().getBestCompanionAdConfig().getHeight();
        } catch (Exception unused) {
            return 0;
        }
    }

    private boolean isItemValid() {
        if (getAdManager() != null && getAdManager().getActivityContext() != null) {
            return true;
        }
        android.util.Log.e(i.f, "Item expired : invalid activity context.");
        return false;
    }

    public boolean hasFollowUp() {
        try {
            return getVastHelper().hasFollowUp();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARNING: Missing block: B:28:0x0068, code skipped:
            return;
     */
    public synchronized void show() {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.getItemType();	 Catch:{ all -> 0x0069 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.AUDIO;	 Catch:{ all -> 0x0069 }
        if (r0 == r1) goto L_0x0011;
    L_0x0009:
        r0 = r3.isItemValid();	 Catch:{ all -> 0x0069 }
        if (r0 != 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r3);
        return;
    L_0x0011:
        r0 = r3.isDisplayed;	 Catch:{ all -> 0x0069 }
        if (r0 == 0) goto L_0x0017;
    L_0x0015:
        monitor-exit(r3);
        return;
    L_0x0017:
        r0 = 1;
        r3.isDisplayed = r0;	 Catch:{ all -> 0x0069 }
        r0 = r3.getItemType();	 Catch:{ all -> 0x0069 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.VIDEO_INCENTIVE;	 Catch:{ all -> 0x0069 }
        if (r0 != r1) goto L_0x0028;
    L_0x0022:
        r3.registerReceiver();	 Catch:{ all -> 0x0069 }
        com.til.colombia.android.service.bm.b(r3);	 Catch:{ all -> 0x0069 }
    L_0x0028:
        r0 = r3.getItemType();	 Catch:{ all -> 0x0069 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.INTERSTITIAL_VIDEO;	 Catch:{ all -> 0x0069 }
        if (r0 != r1) goto L_0x0036;
    L_0x0030:
        r3.registerReceiver();	 Catch:{ all -> 0x0069 }
        com.til.colombia.android.service.bm.c(r3);	 Catch:{ all -> 0x0069 }
    L_0x0036:
        r0 = r3.getItemType();	 Catch:{ all -> 0x0069 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.AUDIO;	 Catch:{ all -> 0x0069 }
        if (r0 != r1) goto L_0x004b;
    L_0x003e:
        r0 = new com.til.colombia.android.service.ah;	 Catch:{ all -> 0x0069 }
        r1 = com.til.colombia.android.internal.a.a();	 Catch:{ all -> 0x0069 }
        r2 = r3.adListener;	 Catch:{ all -> 0x0069 }
        r0.<init>(r1, r3, r2);	 Catch:{ all -> 0x0069 }
        r3.audioPlayer = r0;	 Catch:{ all -> 0x0069 }
    L_0x004b:
        r0 = r3.getItemType();	 Catch:{ all -> 0x0069 }
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.AUDIO_BANNER;	 Catch:{ all -> 0x0069 }
        if (r0 != r1) goto L_0x0067;
    L_0x0053:
        r0 = new com.til.colombia.android.service.q;	 Catch:{ all -> 0x0069 }
        r1 = r3.adManager;	 Catch:{ all -> 0x0069 }
        r1 = r1.getActivityContext();	 Catch:{ all -> 0x0069 }
        r2 = r3.adListener;	 Catch:{ all -> 0x0069 }
        r0.<init>(r1, r3, r2);	 Catch:{ all -> 0x0069 }
        r3.audioBannerView = r0;	 Catch:{ all -> 0x0069 }
        r0 = r3.audioBannerView;	 Catch:{ all -> 0x0069 }
        r0.a();	 Catch:{ all -> 0x0069 }
    L_0x0067:
        monitor-exit(r3);
        return;
    L_0x0069:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.NativeItem.show():void");
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    public synchronized void registerReceiver() {
        if (this.adListener != null) {
            this.receiver = new ck(a.a(), getUID(), this, this.adListener);
            ck ckVar = this.receiver;
            IntentFilter intentFilter = new IntentFilter();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ck.a);
            stringBuilder.append(":");
            stringBuilder.append(ckVar.h);
            intentFilter.addAction(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append(ck.b);
            stringBuilder.append(":");
            stringBuilder.append(ckVar.h);
            intentFilter.addAction(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append(ck.c);
            stringBuilder.append(":");
            stringBuilder.append(ckVar.h);
            intentFilter.addAction(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append(ck.d);
            stringBuilder.append(":");
            stringBuilder.append(ckVar.h);
            intentFilter.addAction(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append(ck.e);
            stringBuilder.append(":");
            stringBuilder.append(ckVar.h);
            intentFilter.addAction(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append(ck.f);
            stringBuilder.append(":");
            stringBuilder.append(ckVar.h);
            intentFilter.addAction(stringBuilder.toString());
            LocalBroadcastManager.getInstance(ckVar.g).registerReceiver(ckVar, intentFilter);
        }
    }

    public synchronized void destroy() {
        if (this.receiver != null) {
            ck ckVar = this.receiver;
            try {
                LocalBroadcastManager.getInstance(ckVar.g).unregisterReceiver(ckVar);
            } catch (Exception e) {
                Log.a(i.f, "", e);
            }
        }
        if (getItemType() == ITEM_TYPE.AUDIO && this.audioPlayer != null) {
            this.audioPlayer.d();
        }
        if (getItemType() == ITEM_TYPE.AUDIO_BANNER && this.audioBannerView != null) {
            this.audioBannerView.c();
        }
    }

    public String getBodyText() {
        return this.desc;
    }

    public String getBrandText() {
        return this.brand;
    }

    public String getAdAttributionText() {
        if (h.a(this.adAttributionText)) {
            return "Ad";
        }
        return this.adAttributionText;
    }

    public String getAdAttributionUrl() {
        return this.adAttributionUrl;
    }

    public synchronized String getAdUrl() {
        if (this.itemType.intValue() == ITEM_TYPE.AUDIO.ordinal()) {
            com.til.colombia.android.network.n.a(getVastClickTrackers(), 5, " audio vast clicktrackers tracked.");
            return this.ctaVideoUrl;
        }
        return getRedirectionUrl();
    }

    public synchronized String getAdDeepLink() {
        return this.deepLink;
    }

    public Bitmap getIcon() {
        if (this.icon != null) {
            return this.icon;
        }
        return getImage();
    }

    public void setIcon(Bitmap bitmap) {
        this.icon = bitmap;
    }

    public Bitmap getImage() {
        return this.image;
    }

    public void setImage(Bitmap bitmap) {
        this.image = bitmap;
    }
}
