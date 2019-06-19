package com.til.colombia.android.vast;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import com.google.android.exoplayer2.util.MimeTypes;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.til.colombia.android.commons.b;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VASTXmlParser implements Serializable {
    private static final String AD_TAG = "Ad";
    private static final String DAAST_ADTAGURI_TAG = "DAASTAdTagURI";
    private static final String DAAST_AUDIOINTERACTIONS_TAG = "AudioInteractions";
    private static final String DAAST_START_TAG = "DAAST";
    private static final String ICONS_TAG = "Icons";
    public static final List<String> MEDIA_MIME_TYPES = Arrays.asList(new String[]{MimeTypes.VIDEO_MP4, "audio/mp3", MimeTypes.AUDIO_MP4, MimeTypes.AUDIO_MPEG});
    private static final String TAG = "VASTXmlParser";
    private static final String VAST_ADTAGURI_TAG = "VASTAdTagURI";
    private static final String VAST_BANDWIDTHINKBPS_TAG = "BandwidthKbps";
    private static final String VAST_CLICKTHROUGH_TAG = "ClickThrough";
    private static final String VAST_CLICKTRACKING_TAG = "ClickTracking";
    private static final String VAST_CREATIVES_TAG = "Creatives";
    private static final String VAST_CREATIVE_TAG = "Creative";
    private static final String VAST_DURATION_TAG = "Duration";
    public static final String VAST_EXTNS_TAG = "Extensions";
    public static final String VAST_EXTN_TAG = "Extension";
    private static final String VAST_IMPRESSION_TAG = "Impression";
    private static final String VAST_INLINE_TAG = "InLine";
    private static final String VAST_LINEAR_TAG = "Linear";
    private static final String VAST_MEDIAFILES_TAG = "MediaFiles";
    private static final String VAST_MEDIAFILE_TAG = "MediaFile";
    private static final String VAST_START_TAG = "VAST";
    private static final String VAST_TRACKINGEVENTS_TAG = "TrackingEvents";
    private static final String VAST_TRACKING_TAG = "Tracking";
    private static final String VAST_VIDEOCLICKS_TAG = "VideoClicks";
    private static final String VAST_WRAPPER_TAG = "Wrapper";
    private int bandwidthInKbps;
    private String clickThroughUrl;
    private String clickTrackingUrl;
    private List<VastCompanionAdConfig> companionVideoAdConfigs;
    private Context context;
    private String duration;
    private volatile boolean hasWrapper;
    private List<VastCompanionAdConfig> iconConfigs;
    private List<String> impressionTrackerUrls;
    private boolean isImpressionTracked = false;
    private String mSkipOffset;
    private String mediaFileUrl;
    private List<MediaFile> mediaFiles;
    private Map<Integer, List<Tracking>> progressTrackings;
    private boolean ready = false;
    private VastSponsoredAdConfig sponsoredAdConfig;
    private List<Tracking> trackings = new ArrayList();
    private a vastListener;
    private volatile VASTXmlParser wrappedVASTXml;

    public interface a {
        void onVASTReady(VASTXmlParser vASTXmlParser);

        void onVASTWrapperFound(String str);
    }

    public class MediaFile implements Serializable {
        public Integer bitrate;
        public Integer h;
        public String type;
        public String url;
        public Integer w;

        public MediaFile(String str, String str2, int i, int i2, int i3) {
            this.w = Integer.valueOf(i2);
            this.h = Integer.valueOf(i3);
            this.bitrate = Integer.valueOf(i);
            this.url = str;
            this.type = str2;
        }
    }

    public class Tracking implements Serializable {
        private final String[] EVENT_MAPPING;
        private int event;
        private String url;
        private VastTrackingEvent vtEvent;

        /* synthetic */ Tracking(VASTXmlParser vASTXmlParser, String str, String str2, a aVar) {
            this(str, str2);
        }

        private Tracking(String str, String str2) {
            this.EVENT_MAPPING = new String[]{"finalReturn", "creativeView", "start", "firstQuartile", "midpoint", "thirdQuartile", "complete", NotificationCompat.CATEGORY_PROGRESS, "skip", "mute", "unmute", "pause", "resume", "fullscreen"};
            this.event = findEvent(str);
            this.url = str2;
            if (this.event <= 0 || this.event > 7) {
                this.vtEvent = new VastTrackingEvent(str2, true, false);
            } else {
                this.vtEvent = new VastTrackingEvent(str2, false, false);
            }
            String str3 = VASTXmlParser.TAG;
            StringBuilder stringBuilder = new StringBuilder("VAST tracking url [");
            stringBuilder.append(str);
            stringBuilder.append(", ");
            stringBuilder.append(this.event);
            stringBuilder.append("]: ");
            stringBuilder.append(this.url);
            Log.a(str3, stringBuilder.toString());
        }

        private int findEvent(String str) {
            for (int i = 0; i < this.EVENT_MAPPING.length; i++) {
                if (this.EVENT_MAPPING[i].equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        public int getEvent() {
            return this.event;
        }

        public String getUrl() {
            return this.url;
        }

        public VastTrackingEvent getVtEvent() {
            return this.vtEvent;
        }

        public void resetVtEvent() {
            this.vtEvent.setIsTracked(false);
        }
    }

    public VASTXmlParser(Context context, a aVar) {
        this.context = context;
        this.vastListener = aVar;
    }

    public boolean process(String str) {
        try {
            readVAST(str);
            this.ready = true;
            return true;
        } catch (Exception e) {
            Log.a(TAG, "Error parsing VAST XML", e);
            return false;
        }
    }

    public void addTrackingEvent(String str, String str2, String str3) {
        Tracking tracking = new Tracking(this, str, str2, null);
        this.trackings.add(tracking);
        if (!h.a(str3)) {
            List list;
            if (this.progressTrackings == null) {
                this.progressTrackings = new HashMap();
            }
            Integer num = getpOffset(str3);
            if (this.progressTrackings.containsKey(num)) {
                list = (List) this.progressTrackings.get(num);
            } else {
                list = new ArrayList();
            }
            list.add(tracking);
            this.progressTrackings.put(num, list);
        }
    }

    public List<VastCompanionAdConfig> getCompanionVideoAdConfigs() {
        waitForWrapper();
        if (this.companionVideoAdConfigs != null || this.wrappedVASTXml == null) {
            return this.companionVideoAdConfigs;
        }
        return this.wrappedVASTXml.getCompanionVideoAdConfigs();
    }

    public VastSponsoredAdConfig getSpnsoredAdConfig() {
        waitForWrapper();
        if (this.sponsoredAdConfig != null || this.wrappedVASTXml == null) {
            return this.sponsoredAdConfig;
        }
        return this.wrappedVASTXml.getSpnsoredAdConfig();
    }

    public String getPreCompanionAudioSrc() {
        VastSponsoredAdConfig spnsoredAdConfig = getSpnsoredAdConfig();
        return (spnsoredAdConfig == null || spnsoredAdConfig.getPreAudioCompanion() == null) ? null : spnsoredAdConfig.getPreAudioCompanion().getAudioSrc();
    }

    public String getPostCompanionAudioSrc() {
        VastSponsoredAdConfig spnsoredAdConfig = getSpnsoredAdConfig();
        return (spnsoredAdConfig == null || spnsoredAdConfig.getPostAudioCompanion() == null) ? null : spnsoredAdConfig.getPostAudioCompanion().getAudioSrc();
    }

    public String getPreCompanionImgSrc() {
        VastSponsoredAdConfig spnsoredAdConfig = getSpnsoredAdConfig();
        return (spnsoredAdConfig == null || spnsoredAdConfig.getPreAudioCompanion() == null) ? null : spnsoredAdConfig.getPreAudioCompanion().getStaticCompanionRes();
    }

    public String getPostCompanionImgSrc() {
        VastSponsoredAdConfig spnsoredAdConfig = getSpnsoredAdConfig();
        return (spnsoredAdConfig == null || spnsoredAdConfig.getPostAudioCompanion() == null) ? null : spnsoredAdConfig.getPostAudioCompanion().getStaticCompanionRes();
    }

    public String getClickThroughUrl() {
        waitForWrapper();
        String str = this.clickThroughUrl;
        return (str != null || this.wrappedVASTXml == null) ? str : this.wrappedVASTXml.getClickThroughUrl();
    }

    public List<String> getClickTrackingUrl() {
        waitForWrapper();
        ArrayList arrayList = new ArrayList();
        if (this.clickTrackingUrl != null) {
            arrayList.add(this.clickTrackingUrl);
        }
        if (this.wrappedVASTXml != null) {
            arrayList.addAll(this.wrappedVASTXml.getClickTrackingUrl());
        }
        return arrayList;
    }

    public String getDuration() {
        waitForWrapper();
        if (this.duration != null || this.wrappedVASTXml == null) {
            return this.duration;
        }
        return this.wrappedVASTXml.getDuration();
    }

    public List<String> getImpressionTrackerUrl() {
        ArrayList arrayList = new ArrayList();
        if (this.isImpressionTracked) {
            return arrayList;
        }
        waitForWrapper();
        if (this.impressionTrackerUrls != null) {
            arrayList.addAll(this.impressionTrackerUrls);
        }
        if (this.wrappedVASTXml != null) {
            arrayList.addAll(this.wrappedVASTXml.getImpressionTrackerUrl());
        }
        this.isImpressionTracked = true;
        return arrayList;
    }

    public String getMediaFileUrl() {
        waitForWrapper();
        if (this.mediaFileUrl != null || this.wrappedVASTXml == null) {
            return this.mediaFileUrl;
        }
        return this.wrappedVASTXml.getMediaFileUrl();
    }

    public List<MediaFile> getMediaFiles() {
        return this.mediaFiles;
    }

    public List<String> getTrackingByType(int i) {
        waitForWrapper();
        ArrayList arrayList = new ArrayList();
        for (Tracking tracking : this.trackings) {
            if (tracking.getEvent() == i) {
                arrayList.add(tracking.getUrl());
            }
        }
        if (this.wrappedVASTXml != null) {
            arrayList.addAll(this.wrappedVASTXml.getTrackingByType(i));
        }
        return arrayList;
    }

    public List<VastTrackingEvent> getProgressTrackingBean(int i) {
        waitForWrapper();
        if (this.progressTrackings == null) {
            return null;
        }
        List<Tracking> list = (List) this.progressTrackings.get(Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Tracking vtEvent : list) {
                arrayList.add(vtEvent.getVtEvent());
            }
        }
        return arrayList;
    }

    public List<VastTrackingEvent> getTrackingBeanByType(int i) {
        waitForWrapper();
        ArrayList arrayList = new ArrayList();
        for (Tracking tracking : this.trackings) {
            if (tracking.getEvent() == i) {
                arrayList.add(tracking.getVtEvent());
            }
        }
        if (this.wrappedVASTXml != null) {
            arrayList.addAll(this.wrappedVASTXml.getTrackingBeanByType(i));
        }
        return arrayList;
    }

    public void resetVtEvents() {
        for (Tracking resetVtEvent : this.trackings) {
            resetVtEvent.resetVtEvent();
        }
        if (this.wrappedVASTXml != null) {
            this.wrappedVASTXml.resetVtEvents();
        }
        this.isImpressionTracked = false;
    }

    public List<Tracking> getTrackings() {
        waitForWrapper();
        List list = this.trackings;
        if (this.wrappedVASTXml != null) {
            list.addAll(this.wrappedVASTXml.getTrackings());
        }
        return list;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
    private void getWrappedVastOrDaast(org.xmlpull.v1.XmlPullParser r6, java.lang.String r7) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
        r5 = this;
        r0 = "";
        r1 = r7.hashCode();
        r2 = -1774725798; // 0xffffffff9637d55a float:-1.4849944E-25 double:NaN;
        r3 = 1;
        if (r1 == r2) goto L_0x001c;
    L_0x000c:
        r2 = -587420703; // 0xffffffffdcfcabe1 float:-5.68965416E17 double:NaN;
        if (r1 == r2) goto L_0x0012;
    L_0x0011:
        goto L_0x0026;
    L_0x0012:
        r1 = "VASTAdTagURI";
        r7 = r7.equals(r1);
        if (r7 == 0) goto L_0x0026;
    L_0x001a:
        r7 = 0;
        goto L_0x0027;
    L_0x001c:
        r1 = "DAASTAdTagURI";
        r7 = r7.equals(r1);
        if (r7 == 0) goto L_0x0026;
    L_0x0024:
        r7 = r3;
        goto L_0x0027;
    L_0x0026:
        r7 = -1;
    L_0x0027:
        r1 = 3;
        r2 = 2;
        r4 = 0;
        switch(r7) {
            case 0: goto L_0x0041;
            case 1: goto L_0x002e;
            default: goto L_0x002d;
        };
    L_0x002d:
        goto L_0x0053;
    L_0x002e:
        r7 = "DAASTAdTagURI";
        r6.require(r2, r4, r7);
        r7 = readText(r6);
        r0 = com.til.colombia.android.internal.a.d.a(r7);
        r7 = "DAASTAdTagURI";
        r6.require(r1, r4, r7);
        goto L_0x0053;
    L_0x0041:
        r7 = "VASTAdTagURI";
        r6.require(r2, r4, r7);
        r7 = readText(r6);
        r0 = com.til.colombia.android.internal.a.d.a(r7);
        r7 = "VASTAdTagURI";
        r6.require(r1, r4, r7);
    L_0x0053:
        r6 = r5.vastListener;
        if (r6 == 0) goto L_0x0070;
    L_0x0057:
        r6 = "VASTXmlParser";
        r7 = new java.lang.StringBuilder;
        r1 = "Notifying VAST listener of new location ";
        r7.<init>(r1);
        r7.append(r0);
        r7 = r7.toString();
        com.til.colombia.android.internal.Log.a(r6, r7);
        r6 = r5.vastListener;
        r6.onVASTWrapperFound(r0);
        goto L_0x0077;
    L_0x0070:
        r6 = "VASTXmlParser";
        r7 = "No listener set for wrapped VAST xml.";
        com.til.colombia.android.internal.Log.a(r6, r7);
    L_0x0077:
        r6 = new java.net.URL;	 Catch:{ Exception -> 0x009a }
        r6.<init>(r0);	 Catch:{ Exception -> 0x009a }
        r6 = r6.openConnection();	 Catch:{ Exception -> 0x009a }
        r6 = (java.net.HttpURLConnection) r6;	 Catch:{ Exception -> 0x009a }
        r6.connect();	 Catch:{ Exception -> 0x009a }
        r7 = new com.til.colombia.android.vast.VASTXmlParser;	 Catch:{ Exception -> 0x009a }
        r0 = r5.context;	 Catch:{ Exception -> 0x009a }
        r7.<init>(r0, r4);	 Catch:{ Exception -> 0x009a }
        r5.wrappedVASTXml = r7;	 Catch:{ Exception -> 0x009a }
        r5.hasWrapper = r3;	 Catch:{ Exception -> 0x009a }
        r7 = r5.wrappedVASTXml;	 Catch:{ Exception -> 0x009a }
        r6 = com.til.colombia.android.commons.CommonUtil.a(r6);	 Catch:{ Exception -> 0x009a }
        r7.process(r6);	 Catch:{ Exception -> 0x009a }
        return;
    L_0x009a:
        r6 = move-exception;
        r7 = "Col:aos:4.0.0";
        r0 = "";
        com.til.colombia.android.internal.Log.b(r7, r0, r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.vast.VASTXmlParser.getWrappedVastOrDaast(org.xmlpull.v1.XmlPullParser, java.lang.String):void");
    }

    public VASTXmlParser getWrappedVASTXml() {
        return this.wrappedVASTXml;
    }

    public boolean hasWrapper() {
        return this.hasWrapper;
    }

    /* JADX WARNING: Missing block: B:10:0x0017, code skipped:
            if (r1.hasWrapper == false) goto L_0x0019;
     */
    public synchronized boolean isReady() {
        /*
        r1 = this;
        monitor-enter(r1);
        r1.waitForWrapper();	 Catch:{ all -> 0x001f }
        r0 = r1.ready;	 Catch:{ all -> 0x001f }
        if (r0 == 0) goto L_0x001c;
    L_0x0008:
        r0 = r1.wrappedVASTXml;	 Catch:{ all -> 0x001f }
        if (r0 == 0) goto L_0x0015;
    L_0x000c:
        r0 = r1.wrappedVASTXml;	 Catch:{ all -> 0x001f }
        r0 = r0.isReady();	 Catch:{ all -> 0x001f }
        if (r0 == 0) goto L_0x001c;
    L_0x0014:
        goto L_0x0019;
    L_0x0015:
        r0 = r1.hasWrapper;	 Catch:{ all -> 0x001f }
        if (r0 != 0) goto L_0x001c;
    L_0x0019:
        r0 = 1;
        monitor-exit(r1);
        return r0;
    L_0x001c:
        r0 = 0;
        monitor-exit(r1);
        return r0;
    L_0x001f:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.vast.VASTXmlParser.isReady():boolean");
    }

    private void readAd(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, AD_TAG);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equals(VAST_INLINE_TAG)) {
                    Log.a(TAG, "VAST file contains inline ad information.");
                    readInLine(xmlPullParser);
                }
                if (name.equals(VAST_WRAPPER_TAG)) {
                    name = TAG;
                    StringBuilder stringBuilder = new StringBuilder("VAST file contains wrapped ad information. [");
                    stringBuilder.append(this);
                    stringBuilder.append("]");
                    Log.a(name, stringBuilder.toString());
                    this.hasWrapper = true;
                    readWrapper(xmlPullParser);
                }
            }
        }
    }

    private void readCreative(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, VAST_CREATIVE_TAG);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name != null && name.equals(VAST_LINEAR_TAG)) {
                    this.mSkipOffset = xmlPullParser.getAttributeValue(null, "skipoffset");
                    readLinear(xmlPullParser);
                } else if (name == null || !name.equals(g.e)) {
                    skip(xmlPullParser);
                } else {
                    this.companionVideoAdConfigs = g.a(xmlPullParser, g.e);
                }
            }
        }
    }

    public String getSkipOffsetString() {
        return this.mSkipOffset;
    }

    public int getDurationInMillis() {
        if (this.duration != null) {
            try {
                if (b.b(this.duration)) {
                    return b.c(this.duration).intValue();
                }
            } catch (NumberFormatException unused) {
                Log.a("VastXmlParser", String.format("Failed to parse skipoffset %s", new Object[]{this.mSkipOffset}));
            }
        }
        return -1;
    }

    public int getSkipOffset(int i) {
        if (this.mSkipOffset != null) {
            try {
                if (b.b(this.mSkipOffset)) {
                    Integer c = b.c(this.mSkipOffset);
                    if (c != null && c.intValue() < i) {
                        return c.intValue() / 1000;
                    }
                } else if (b.a(this.mSkipOffset)) {
                    int round = Math.round(((float) i) * (Float.parseFloat(this.mSkipOffset.replace("%", "")) / 100.0f));
                    if (round < i) {
                        return round / 1000;
                    }
                } else {
                    Log.a("VastXmlParser", String.format("Invalid VAST skipoffset format: %s", new Object[]{this.mSkipOffset}));
                }
            } catch (NumberFormatException unused) {
                Log.a("VastXmlParser", String.format("Failed to parse skipoffset %s", new Object[]{this.mSkipOffset}));
            }
        }
        return -1;
    }

    public Integer getpOffset(String str) {
        if (str != null) {
            try {
                if (b.b(str)) {
                    Integer c = b.c(str);
                    if (c != null) {
                        return Integer.valueOf(c.intValue() / 1000);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return Integer.valueOf(-1);
    }

    private void readCreatives(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, VAST_CREATIVES_TAG);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null || !name.equals(VAST_CREATIVE_TAG)) {
                    skip(xmlPullParser);
                } else {
                    readCreative(xmlPullParser);
                }
            }
        }
    }

    private void readInLine(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, VAST_INLINE_TAG);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name != null && name.equals(VAST_IMPRESSION_TAG)) {
                    xmlPullParser.require(2, null, VAST_IMPRESSION_TAG);
                    name = readText(xmlPullParser);
                    if (this.impressionTrackerUrls == null) {
                        this.impressionTrackerUrls = new ArrayList();
                    }
                    this.impressionTrackerUrls.add(name);
                    xmlPullParser.require(3, null, VAST_IMPRESSION_TAG);
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder("Impression tracker url: ");
                    stringBuilder.append(name);
                    Log.a(str, stringBuilder.toString());
                } else if (name != null && name.equals(VAST_CREATIVES_TAG)) {
                    readCreatives(xmlPullParser);
                } else if (name == null || !name.equals(VAST_EXTNS_TAG)) {
                    skip(xmlPullParser);
                } else {
                    readExtensions(xmlPullParser);
                }
            }
        }
    }

    private void readLinear(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, VAST_LINEAR_TAG);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (xmlPullParser.getEventType() == 2) {
                if (name != null && name.equals(VAST_DURATION_TAG)) {
                    xmlPullParser.require(2, null, VAST_DURATION_TAG);
                    this.duration = readText(xmlPullParser);
                    xmlPullParser.require(3, null, VAST_DURATION_TAG);
                    name = TAG;
                    StringBuilder stringBuilder = new StringBuilder("Video duration: ");
                    stringBuilder.append(this.duration);
                    Log.a(name, stringBuilder.toString());
                } else if (name != null && name.equals("TrackingEvents")) {
                    readTrackingEvents(xmlPullParser);
                } else if (name != null && name.equals(VAST_MEDIAFILES_TAG)) {
                    readMediaFiles(xmlPullParser);
                } else if (name != null && (name.equals(VAST_VIDEOCLICKS_TAG) || name.equals(DAAST_AUDIOINTERACTIONS_TAG))) {
                    readVideoOrAudioClicks(xmlPullParser, name);
                } else if (name == null || !name.equals("Icons")) {
                    skip(xmlPullParser);
                } else {
                    this.iconConfigs = g.a(xmlPullParser, "Icons");
                }
            }
        }
    }

    private void readMediaFiles(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        int i;
        String name;
        xmlPullParser.require(2, null, VAST_MEDIAFILES_TAG);
        this.mediaFiles = new ArrayList();
        while (true) {
            i = 0;
            if (xmlPullParser.next() == 3) {
                break;
            } else if (xmlPullParser.getEventType() == 2) {
                name = xmlPullParser.getName();
                if (name == null || !name.equals(VAST_MEDIAFILE_TAG)) {
                    skip(xmlPullParser);
                } else {
                    xmlPullParser.require(2, null, VAST_MEDIAFILE_TAG);
                    String attributeValue = xmlPullParser.getAttributeValue(null, "type");
                    name = xmlPullParser.getAttributeValue(null, "bitrate");
                    String attributeValue2 = xmlPullParser.getAttributeValue(null, "width");
                    String attributeValue3 = xmlPullParser.getAttributeValue(null, "height");
                    String replaceAll = readText(xmlPullParser).replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
                    if (attributeValue != null && MEDIA_MIME_TYPES.contains(attributeValue)) {
                        List list = this.mediaFiles;
                        int intValue = name != null ? Integer.valueOf(name).intValue() : 0;
                        int intValue2 = attributeValue2 != null ? Integer.valueOf(attributeValue2).intValue() : 0;
                        if (attributeValue3 != null) {
                            i = Integer.valueOf(attributeValue3).intValue();
                        }
                        list.add(new MediaFile(replaceAll, attributeValue, intValue, intValue2, i));
                    }
                    xmlPullParser.require(3, null, VAST_MEDIAFILE_TAG);
                }
            }
        }
        if (this.mediaFiles.size() == 1) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder("Found 1 mediafile: ");
            stringBuilder.append(((MediaFile) this.mediaFiles.get(0)).url);
            stringBuilder.append(" ");
            stringBuilder.append(((MediaFile) this.mediaFiles.get(0)).w);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(((MediaFile) this.mediaFiles.get(0)).h);
            stringBuilder.append("@");
            stringBuilder.append(((MediaFile) this.mediaFiles.get(0)).bitrate);
            Log.a(str, stringBuilder.toString());
            this.mediaFileUrl = ((MediaFile) this.mediaFiles.get(0)).url;
        } else if (this.mediaFiles.size() > 1) {
            StringBuilder stringBuilder2;
            int i2 = 0;
            while (i < this.mediaFiles.size()) {
                name = TAG;
                stringBuilder2 = new StringBuilder("Found ");
                stringBuilder2.append(((MediaFile) this.mediaFiles.get(i)).url);
                stringBuilder2.append(" ");
                stringBuilder2.append(((MediaFile) this.mediaFiles.get(i)).w);
                stringBuilder2.append(AvidJSONUtil.KEY_X);
                stringBuilder2.append(((MediaFile) this.mediaFiles.get(i)).h);
                stringBuilder2.append("@");
                stringBuilder2.append(((MediaFile) this.mediaFiles.get(i)).bitrate);
                Log.a(name, stringBuilder2.toString());
                if (((MediaFile) this.mediaFiles.get(i)).bitrate.intValue() == 0 || ((MediaFile) this.mediaFiles.get(i2)).bitrate.intValue() < 150 || ((MediaFile) this.mediaFiles.get(i)).bitrate.intValue() < 150) {
                    if (((MediaFile) this.mediaFiles.get(i)).bitrate.intValue() != 0) {
                        if (((MediaFile) this.mediaFiles.get(i2)).bitrate.intValue() >= ((MediaFile) this.mediaFiles.get(i)).bitrate.intValue()) {
                        }
                    }
                    i++;
                } else if (((MediaFile) this.mediaFiles.get(i2)).bitrate.intValue() <= ((MediaFile) this.mediaFiles.get(i)).bitrate.intValue()) {
                    i++;
                }
                i2 = i;
                i++;
            }
            name = TAG;
            stringBuilder2 = new StringBuilder("Selected ");
            stringBuilder2.append(((MediaFile) this.mediaFiles.get(i2)).url);
            stringBuilder2.append(" ");
            stringBuilder2.append(((MediaFile) this.mediaFiles.get(i2)).w);
            stringBuilder2.append(AvidJSONUtil.KEY_X);
            stringBuilder2.append(((MediaFile) this.mediaFiles.get(i2)).h);
            stringBuilder2.append("@");
            stringBuilder2.append(((MediaFile) this.mediaFiles.get(i2)).bitrate);
            Log.a(name, stringBuilder2.toString());
            this.mediaFileUrl = ((MediaFile) this.mediaFiles.get(i2)).url;
        } else {
            Log.a(TAG, "No compatible mediafile found.");
        }
    }

    public static String readText(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str = "";
        if (xmlPullParser.next() == 4) {
            str = xmlPullParser.getText();
            xmlPullParser.nextTag();
        } else {
            String str2 = TAG;
            StringBuilder stringBuilder = new StringBuilder("No text: ");
            stringBuilder.append(xmlPullParser.getName());
            Log.a(str2, stringBuilder.toString());
        }
        return str.trim();
    }

    private void readTrackingEvents(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, "TrackingEvents");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name == null || !name.equals("Tracking")) {
                    skip(xmlPullParser);
                } else {
                    name = xmlPullParser.getAttributeValue(null, "event");
                    String attributeValue = name.equalsIgnoreCase(NotificationCompat.CATEGORY_PROGRESS) ? xmlPullParser.getAttributeValue(null, "offset") : null;
                    xmlPullParser.require(2, null, "Tracking");
                    addTrackingEvent(name, readText(xmlPullParser), attributeValue);
                    attributeValue = TAG;
                    StringBuilder stringBuilder = new StringBuilder("Added VAST tracking \"");
                    stringBuilder.append(name);
                    stringBuilder.append("\"");
                    Log.a(attributeValue, stringBuilder.toString());
                    xmlPullParser.require(3, null, "Tracking");
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054  */
    /* JADX WARNING: Missing block: B:8:0x0038, code skipped:
            if (r5.equals(VAST_START_TAG) != false) goto L_0x003c;
     */
    private void readVAST(java.lang.String r5) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        r4 = this;
        r0 = android.util.Xml.newPullParser();
        r1 = "http://xmlpull.org/v1/doc/features.html#process-namespaces";
        r2 = 0;
        r0.setFeature(r1, r2);
        r1 = new java.io.StringReader;
        r1.<init>(r5);
        r0.setInput(r1);
        r0.nextTag();
        r5 = r0.getName();
        r1 = r5.hashCode();
        r3 = 2627148; // 0x28164c float:3.681418E-39 double:1.2979836E-317;
        if (r1 == r3) goto L_0x0032;
    L_0x0022:
        r2 = 64800965; // 0x3dcc8c5 float:1.2976532E-36 double:3.20159306E-316;
        if (r1 == r2) goto L_0x0028;
    L_0x0027:
        goto L_0x003b;
    L_0x0028:
        r1 = "DAAST";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x003b;
    L_0x0030:
        r2 = 1;
        goto L_0x003c;
    L_0x0032:
        r1 = "VAST";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x003b;
    L_0x003a:
        goto L_0x003c;
    L_0x003b:
        r2 = -1;
    L_0x003c:
        r5 = 0;
        r1 = 2;
        switch(r2) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0042;
            default: goto L_0x0041;
        };
    L_0x0041:
        goto L_0x004d;
    L_0x0042:
        r2 = "DAAST";
        r0.require(r1, r5, r2);
        goto L_0x004d;
    L_0x0048:
        r2 = "VAST";
        r0.require(r1, r5, r2);
    L_0x004d:
        r5 = r0.next();
        r2 = 3;
        if (r5 == r2) goto L_0x006a;
    L_0x0054:
        r5 = r0.getEventType();
        if (r5 != r1) goto L_0x004d;
    L_0x005a:
        r5 = r0.getName();
        r2 = "Ad";
        r5 = r5.equals(r2);
        if (r5 == 0) goto L_0x004d;
    L_0x0066:
        r4.readAd(r0);
        goto L_0x004d;
    L_0x006a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.vast.VASTXmlParser.readVAST(java.lang.String):void");
    }

    private void readVideoOrAudioClicks(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, str);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                str = xmlPullParser.getName();
                StringBuilder stringBuilder;
                if (str != null && str.equals(VAST_CLICKTHROUGH_TAG)) {
                    xmlPullParser.require(2, null, VAST_CLICKTHROUGH_TAG);
                    this.clickThroughUrl = readText(xmlPullParser);
                    str = TAG;
                    stringBuilder = new StringBuilder("Video clickthrough url: ");
                    stringBuilder.append(this.clickThroughUrl);
                    Log.a(str, stringBuilder.toString());
                    xmlPullParser.require(3, null, VAST_CLICKTHROUGH_TAG);
                } else if (str == null || !str.equals(VAST_CLICKTRACKING_TAG)) {
                    skip(xmlPullParser);
                } else {
                    xmlPullParser.require(2, null, VAST_CLICKTRACKING_TAG);
                    this.clickTrackingUrl = readText(xmlPullParser);
                    str = TAG;
                    stringBuilder = new StringBuilder("Video clicktracking url: ");
                    stringBuilder.append(this.clickThroughUrl);
                    Log.a(str, stringBuilder.toString());
                    xmlPullParser.require(3, null, VAST_CLICKTRACKING_TAG);
                }
            }
        }
    }

    private void readExtensions(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, VAST_EXTNS_TAG);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name != null && name.equals(VAST_EXTN_TAG)) {
                    xmlPullParser.require(2, null, VAST_EXTN_TAG);
                    name = xmlPullParser.getAttributeValue(null, "type");
                    if ((name == null || !name.equalsIgnoreCase("geo")) && name != null && name.equalsIgnoreCase("sponsored")) {
                        this.sponsoredAdConfig = h.a(xmlPullParser);
                    } else {
                        skip(xmlPullParser);
                    }
                }
            }
        }
    }

    private void readWrapper(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, null, VAST_WRAPPER_TAG);
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name != null && name.equals(VAST_IMPRESSION_TAG)) {
                    xmlPullParser.require(2, null, VAST_IMPRESSION_TAG);
                    name = readText(xmlPullParser);
                    if (this.impressionTrackerUrls == null) {
                        this.impressionTrackerUrls = new ArrayList();
                    }
                    this.impressionTrackerUrls.add(name);
                    xmlPullParser.require(3, null, VAST_IMPRESSION_TAG);
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder("Impression tracker url: ");
                    stringBuilder.append(name);
                    Log.a(str, stringBuilder.toString());
                } else if (name != null && name.equals(VAST_CREATIVES_TAG)) {
                    readCreatives(xmlPullParser);
                } else if (name != null && name.equals(VAST_EXTNS_TAG)) {
                    readExtensions(xmlPullParser);
                } else if (name != null && name.equals(VAST_ADTAGURI_TAG)) {
                    getWrappedVastOrDaast(xmlPullParser, VAST_ADTAGURI_TAG);
                } else if (name == null || !name.equals(DAAST_ADTAGURI_TAG)) {
                    skip(xmlPullParser);
                } else {
                    getWrappedVastOrDaast(xmlPullParser, DAAST_ADTAGURI_TAG);
                }
            }
        }
    }

    public void setWrapper(VASTXmlParser vASTXmlParser) {
        this.hasWrapper = true;
        this.wrappedVASTXml = vASTXmlParser;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder("Setting wrapper for ");
        stringBuilder.append(this);
        stringBuilder.append(" to ");
        stringBuilder.append(vASTXmlParser);
        Log.a(str, stringBuilder.toString());
    }

    public static void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0) {
            switch (xmlPullParser.next()) {
                case 2:
                    i++;
                    break;
                case 3:
                    i--;
                    break;
                default:
                    break;
            }
        }
    }

    private void waitForWrapper() {
        if (this.hasWrapper) {
            for (int i = 0; i < 2 && this.hasWrapper && (this.wrappedVASTXml == null || !this.wrappedVASTXml.isReady()); i++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    Log.a(TAG, "Error wraiting for wrapper", e);
                }
                Thread.yield();
            }
        }
    }
}
