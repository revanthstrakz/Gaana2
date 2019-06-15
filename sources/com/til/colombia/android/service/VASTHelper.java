package com.til.colombia.android.service;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.d;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.vast.VASTXmlParser;
import com.til.colombia.android.vast.VASTXmlParser.MediaFile;
import com.til.colombia.android.vast.VastCompanionAdConfig;
import com.til.colombia.android.vast.VastCompanionResource;
import com.til.colombia.android.vast.VastCompanionResource.Type;
import com.til.colombia.android.vast.VastSponsoredAdConfig;
import com.til.colombia.android.vast.VastTrackingEvent;
import com.til.colombia.android.vast.g;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class VASTHelper implements com.til.colombia.android.vast.VASTXmlParser.a, Serializable {
    private static final int DEFAULT_START_NOTIFY_TIME = 3;
    public static final int EVENT_COMPLETE = 6;
    public static final int EVENT_CREATIVE_VIEW = 1;
    public static final int EVENT_FINAL_RETURN = 0;
    public static final int EVENT_FIRSTQ = 3;
    public static final int EVENT_FULLSCREEN = 13;
    public static final int EVENT_MID = 4;
    public static final int EVENT_MUTE = 9;
    public static final int EVENT_PAUSE = 11;
    public static final int EVENT_PROGRESS = 7;
    public static final int EVENT_RESUME = 12;
    public static final int EVENT_SKIP = 8;
    public static final int EVENT_START = 2;
    public static final int EVENT_THIRDQ = 5;
    public static final int EVENT_UNMUTE = 10;
    private static final String TAG = "VASTHelper";
    private static final long serialVersionUID = 10;
    VastCompanionAdConfig bestCompanionAdConfig;
    private boolean disablePreCache = false;
    private boolean hasFollowUp = false;
    private boolean isClickable = false;
    private CmItem item;
    private List<String> jsonClickTrackingUrl;
    private Map<Integer, List<String>> jsonCustomEvents;
    private List<String> jsonImpressionTrackingUrl;
    private Context mContext;
    private int startNotifyTime = 0;
    private String url;
    private VASTXmlParser vParser;
    private transient XmlCallbacks xCb;

    public interface XmlCallbacks extends Serializable {
        void onParsingComplete();

        void onParsingError();

        void onParsingStatus();
    }

    private class a extends AsyncTask<Void, Integer, VASTXmlParser> {
        String a;
        VASTHelper b;

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            VASTXmlParser vASTXmlParser = (VASTXmlParser) obj;
            String str = VASTHelper.TAG;
            StringBuilder stringBuilder = new StringBuilder("result");
            stringBuilder.append(vASTXmlParser);
            Log.a(str, stringBuilder.toString());
            VASTHelper.this.onVASTReady(vASTXmlParser);
        }

        a(VASTHelper vASTHelper, String str) {
            this.b = vASTHelper;
            this.a = str;
        }

        /* Access modifiers changed, original: protected|final */
        public final void onPreExecute() {
            Log.a(VASTHelper.TAG, "onPreExecute");
        }

        private VASTXmlParser a() {
            try {
                if (h.a(this.a)) {
                    VASTHelper.this.url = d.a(VASTHelper.this.url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(VASTHelper.this.url).openConnection();
                    httpURLConnection.connect();
                    this.a = CommonUtil.a(httpURLConnection);
                }
                VASTHelper.this.vParser = new VASTXmlParser(VASTHelper.this.mContext, this.b);
                if (!VASTHelper.this.vParser.process(this.a)) {
                    return null;
                }
                VASTHelper.this.processJsonData();
                return VASTHelper.this.vParser;
            } catch (Exception e) {
                Log.b(i.f, "", e);
                return null;
            }
        }

        private void a(VASTXmlParser vASTXmlParser) {
            String str = VASTHelper.TAG;
            StringBuilder stringBuilder = new StringBuilder("result");
            stringBuilder.append(vASTXmlParser);
            Log.a(str, stringBuilder.toString());
            VASTHelper.this.onVASTReady(vASTXmlParser);
        }
    }

    public void onVASTWrapperFound(String str) {
    }

    protected VASTHelper(CmItem cmItem) {
        CommonUtil.c();
        this.jsonClickTrackingUrl = new ArrayList();
        this.jsonImpressionTrackingUrl = new ArrayList();
        this.jsonCustomEvents = new HashMap();
        this.item = cmItem;
        ((NativeItem) cmItem).setVastHelper(this);
    }

    /* Access modifiers changed, original: protected */
    public CmItem getItem() {
        return this.item;
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0047 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:2|(2:3|4)|5|7|8|(1:12)|13|14|(1:18)|19|20|(1:24)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:2|(2:3|4)|5|7|8|(1:12)|13|14|(1:18)|19|20|(1:24)) */
    public void getXML(android.content.Context r3, java.lang.String r4) {
        /*
        r2 = this;
        r2.mContext = r3;
        r2.url = r4;
        r3 = r2.url;
        r3 = com.til.colombia.android.internal.a.h.a(r3);
        if (r3 != 0) goto L_0x005b;
    L_0x000c:
        r3 = r2.url;
        r3 = android.net.Uri.parse(r3);
        r4 = "startpixel";
        r4 = r3.getQueryParameter(r4);	 Catch:{ Exception -> 0x001e }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x001e }
        r2.startNotifyTime = r4;	 Catch:{ Exception -> 0x001e }
    L_0x001e:
        r4 = 1;
        r0 = "precaching";
        r0 = r3.getQueryParameter(r0);	 Catch:{ Exception -> 0x0033 }
        r1 = com.til.colombia.android.internal.a.h.a(r0);	 Catch:{ Exception -> 0x0033 }
        if (r1 != 0) goto L_0x0033;
    L_0x002b:
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0033 }
        if (r0 != 0) goto L_0x0033;
    L_0x0031:
        r2.disablePreCache = r4;	 Catch:{ Exception -> 0x0033 }
    L_0x0033:
        r0 = "followup";
        r0 = r3.getQueryParameter(r0);	 Catch:{ Exception -> 0x0047 }
        r1 = com.til.colombia.android.internal.a.h.a(r0);	 Catch:{ Exception -> 0x0047 }
        if (r1 != 0) goto L_0x0047;
    L_0x003f:
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0047 }
        if (r0 != r4) goto L_0x0047;
    L_0x0045:
        r2.hasFollowUp = r4;	 Catch:{ Exception -> 0x0047 }
    L_0x0047:
        r0 = "clickable";
        r3 = r3.getQueryParameter(r0);	 Catch:{ Exception -> 0x005b }
        r0 = com.til.colombia.android.internal.a.h.a(r3);	 Catch:{ Exception -> 0x005b }
        if (r0 != 0) goto L_0x005b;
    L_0x0053:
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x005b }
        if (r3 != r4) goto L_0x005b;
    L_0x0059:
        r2.isClickable = r4;	 Catch:{ Exception -> 0x005b }
    L_0x005b:
        r3 = android.os.Build.VERSION.SDK_INT;
        r4 = 11;
        r0 = 0;
        r1 = 0;
        if (r3 < r4) goto L_0x0070;
    L_0x0063:
        r3 = new com.til.colombia.android.service.VASTHelper$a;
        r3.<init>(r2, r1);
        r4 = android.os.AsyncTask.THREAD_POOL_EXECUTOR;
        r0 = new java.lang.Void[r0];
        r3.executeOnExecutor(r4, r0);
        return;
    L_0x0070:
        r3 = new com.til.colombia.android.service.VASTHelper$a;
        r3.<init>(r2, r1);
        r4 = new java.lang.Void[r0];
        r3.execute(r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.VASTHelper.getXML(android.content.Context, java.lang.String):void");
    }

    /* Access modifiers changed, original: protected */
    public void getVastContent(Context context, String str) {
        this.mContext = context;
        if (VERSION.SDK_INT >= 11) {
            new a(this, str).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            new a(this, str).execute(new Void[0]);
        }
    }

    public void setCallbackView(XmlCallbacks xmlCallbacks) {
        this.xCb = xmlCallbacks;
    }

    public void onVASTReady(VASTXmlParser vASTXmlParser) {
        this.vParser = vASTXmlParser;
        if (this.xCb != null) {
            if (this.vParser != null) {
                ((NativeItem) this.item).setCtaVideoUrl(this.vParser.getClickThroughUrl());
                ((NativeItem) this.item).setDuration(this.vParser.getDurationInMillis());
                this.xCb.onParsingComplete();
                return;
            }
            this.xCb.onParsingError();
        }
    }

    /* Access modifiers changed, original: protected */
    public String getMediaFileUrl() {
        if (this.vParser == null) {
            return null;
        }
        return this.vParser.getMediaFileUrl();
    }

    /* Access modifiers changed, original: protected */
    public int getStartNotifyTime() {
        if (this.startNotifyTime == 0 && this.item.getItemType() == ITEM_TYPE.VIDEO) {
            this.startNotifyTime = 3;
        }
        return this.startNotifyTime;
    }

    /* Access modifiers changed, original: protected */
    public boolean isDisablePreCache() {
        return this.disablePreCache;
    }

    /* Access modifiers changed, original: protected */
    public boolean hasFollowUp() {
        return this.hasFollowUp;
    }

    /* Access modifiers changed, original: protected */
    public boolean isClickable() {
        return this.isClickable;
    }

    /* Access modifiers changed, original: protected */
    public String getBestMediaFileUrl() {
        String str = null;
        List mediaFiles = this.vParser != null ? this.vParser.getMediaFiles() : null;
        if (mediaFiles == null || mediaFiles.size() == 0) {
            return null;
        }
        double d = Double.POSITIVE_INFINITY;
        Iterator it = mediaFiles.iterator();
        while (it.hasNext()) {
            MediaFile mediaFile = (MediaFile) it.next();
            String str2 = mediaFile.url;
            if (!VASTXmlParser.MEDIA_MIME_TYPES.contains(mediaFile.type) || str2 == null) {
                it.remove();
            } else {
                Integer num = mediaFile.w;
                Integer num2 = mediaFile.h;
                if (num != null && num.intValue() > 0 && num2 != null && num2.intValue() > 0) {
                    double b = CommonUtil.b(num.intValue(), num2.intValue());
                    if (b < d) {
                        str = str2;
                        d = b;
                    }
                }
            }
        }
        return str;
    }

    /* Access modifiers changed, original: protected */
    public List<VastCompanionAdConfig> getCompanionAdConfigs() {
        return this.vParser != null ? this.vParser.getCompanionVideoAdConfigs() : null;
    }

    /* Access modifiers changed, original: protected */
    public VastCompanionAdConfig getBestCompanionAdConfig(ArrayList<AdSize> arrayList) {
        ArrayList<AdSize> arrayList2 = arrayList;
        if (this.bestCompanionAdConfig == null) {
            VastCompanionAdConfig a;
            List<VastCompanionAdConfig> companionAdConfigs = getCompanionAdConfigs();
            if (arrayList2 == null || arrayList.size() == 0) {
                a = g.a(companionAdConfigs);
            } else {
                loop0:
                for (Type type : Type.values()) {
                    for (VastCompanionAdConfig vastCompanionAdConfig : companionAdConfigs) {
                        VastCompanionResource vastResource = vastCompanionAdConfig.getVastResource();
                        if (vastResource != null && type == vastResource.getType()) {
                            int width = vastCompanionAdConfig.getWidth();
                            int height = vastCompanionAdConfig.getHeight();
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                AdSize adSize = (AdSize) it.next();
                                int width2 = adSize.getWidth();
                                int height2 = adSize.getHeight();
                                if (CommonUtil.c(width, height, width2, height2)) {
                                    Point a2 = CommonUtil.a(width, height, width2, height2);
                                    VastCompanionResource vastCompanionResource = new VastCompanionResource(vastResource.getResource(), vastResource.getType(), vastResource.getCreativeType(), a2.x, a2.y);
                                    VastCompanionAdConfig vastCompanionAdConfig2 = new VastCompanionAdConfig(a2.x, a2.y, vastCompanionResource, vastCompanionAdConfig.getClickThroughUrl(), vastCompanionAdConfig.getClickTrackers(), vastCompanionAdConfig.getCreativeViewTrackers(), adSize);
                                    break loop0;
                                }
                            }
                            continue;
                        }
                    }
                }
                a = g.a(companionAdConfigs, ((AdSize) arrayList2.get(0)).getWidth(), ((AdSize) arrayList2.get(0)).getHeight());
            }
            this.bestCompanionAdConfig = a;
        }
        return this.bestCompanionAdConfig;
    }

    /* Access modifiers changed, original: protected */
    public VastCompanionAdConfig getBestCompanionAdConfig() {
        if (this.bestCompanionAdConfig == null) {
            this.bestCompanionAdConfig = g.a(getCompanionAdConfigs());
        }
        return this.bestCompanionAdConfig;
    }

    /* Access modifiers changed, original: protected */
    public VastCompanionAdConfig getBestCompanionAdConfig(int i, int i2) {
        if (this.bestCompanionAdConfig == null) {
            this.bestCompanionAdConfig = g.a(getCompanionAdConfigs(), i, i2);
        }
        return this.bestCompanionAdConfig;
    }

    /* Access modifiers changed, original: protected */
    public VastSponsoredAdConfig getSponsoredAdConfig() {
        return this.vParser.getSpnsoredAdConfig();
    }

    /* Access modifiers changed, original: protected */
    public List<String> getImpressionTrackerUrl() {
        ArrayList arrayList = new ArrayList();
        if (this.vParser != null) {
            List impressionTrackerUrl = this.vParser.getImpressionTrackerUrl();
            if (impressionTrackerUrl != null) {
                arrayList.addAll(impressionTrackerUrl);
            }
        }
        if (this.jsonImpressionTrackingUrl != null) {
            arrayList.addAll(this.jsonImpressionTrackingUrl);
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public List<String> getTrackingByType(int i) {
        return this.vParser != null ? this.vParser.getTrackingByType(i) : null;
    }

    /* Access modifiers changed, original: protected */
    public List<String> getVastProgressEvent(int i) {
        if (this.vParser != null) {
            List progressTrackingBean = this.vParser.getProgressTrackingBean(i);
            if (progressTrackingBean != null && progressTrackingBean.size() > 0) {
                return getTrackingEventUris(progressTrackingBean);
            }
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    public List<String> getVastTrackingByType(int i) {
        if (this.vParser != null) {
            List trackingBeanByType = this.vParser.getTrackingBeanByType(i);
            if (trackingBeanByType != null && trackingBeanByType.size() > 0) {
                return getTrackingEventUris(trackingBeanByType);
            }
        }
        return null;
    }

    public static List<String> getTrackingEventUris(List<VastTrackingEvent> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (VastTrackingEvent vastTrackingEvent : list) {
            if (!vastTrackingEvent.isTracked() || vastTrackingEvent.isRepeatable()) {
                arrayList.add(vastTrackingEvent.getUrl());
                vastTrackingEvent.setIsTracked(true);
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public void resetVtEvents() {
        if (this.vParser != null) {
            this.vParser.resetVtEvents();
        }
    }

    /* Access modifiers changed, original: protected */
    public String getClickThroughUrl() {
        return this.vParser != null ? this.vParser.getClickThroughUrl() : null;
    }

    /* Access modifiers changed, original: protected */
    public List<String> getClickTrackingUrl() {
        ArrayList arrayList = new ArrayList();
        if (this.vParser != null) {
            List clickTrackingUrl = this.vParser.getClickTrackingUrl();
            if (clickTrackingUrl != null) {
                arrayList.addAll(clickTrackingUrl);
            }
        }
        if (this.jsonClickTrackingUrl != null) {
            arrayList.addAll(this.jsonClickTrackingUrl);
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public String getPreCompanionAudioSrc() {
        return this.vParser != null ? this.vParser.getPreCompanionAudioSrc() : null;
    }

    /* Access modifiers changed, original: protected */
    public String getPostCompanionAudioSrc() {
        return this.vParser != null ? this.vParser.getPostCompanionAudioSrc() : null;
    }

    /* Access modifiers changed, original: protected */
    public String getPreCompanionImageSrc() {
        return this.vParser != null ? this.vParser.getPreCompanionImgSrc() : null;
    }

    /* Access modifiers changed, original: protected */
    public String getPostCompanionImageSrc() {
        return this.vParser != null ? this.vParser.getPostCompanionImgSrc() : null;
    }

    /* Access modifiers changed, original: protected */
    public int getDuration() {
        return this.vParser != null ? this.vParser.getDurationInMillis() : -1;
    }

    /* Access modifiers changed, original: protected */
    public int getSkipOffset(int i) {
        return this.vParser != null ? this.vParser.getSkipOffset(i) : -1;
    }

    private void processJsonData() {
        try {
            JSONObject jSONObject = new JSONObject(((NativeItem) this.item).getMediaJson());
            addTrackingEvent(jSONObject, "start");
            addImpressionTracker(jSONObject, "impression");
            addClickTracker(jSONObject, "click");
            addCustomEvents(jSONObject, "custom");
            addTrackingEvent(jSONObject, "firstQuartile");
            addTrackingEvent(jSONObject, "midpoint");
            addTrackingEvent(jSONObject, "thirdQuartile");
            addTrackingEvent(jSONObject, "complete");
        } catch (Exception unused) {
        }
    }

    private void addTrackingEvent(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (i == 0 && str.equalsIgnoreCase("start")) {
                    this.startNotifyTime = optJSONObject.optInt("time");
                }
                if (this.vParser != null) {
                    this.vParser.addTrackingEvent(str, optJSONObject.optString("url"), null);
                }
            }
        }
    }

    private void addImpressionTracker(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.jsonImpressionTrackingUrl.add(optJSONArray.optJSONObject(i).optString("url"));
            }
        }
    }

    private void addClickTracker(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.jsonClickTrackingUrl.add(optJSONArray.optJSONObject(i).optString("url"));
            }
        }
    }

    private void addCustomEvents(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                int optInt = optJSONObject.optInt("time");
                List list = (List) this.jsonCustomEvents.get(Integer.valueOf(optInt));
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(optJSONObject.optString("url"));
                this.jsonCustomEvents.put(Integer.valueOf(optInt), list);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public List<String> getCustomEvents(int i) {
        return (List) this.jsonCustomEvents.get(Integer.valueOf(i));
    }
}
