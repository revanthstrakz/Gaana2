package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.internal.ii.b;
import com.google.ads.interactivemedia.v3.internal.in;
import com.google.ads.interactivemedia.v3.internal.js;
import java.util.List;
import java.util.Map;

final class h extends o {
    private final Map<String, String> adTagParameters;
    private final String adTagUrl;
    private final String adsResponse;
    private final String apiKey;
    private final String assetKey;
    private final String authToken;
    private final Map<String, String> companionSlots;
    private final Float contentDuration;
    private final List<String> contentKeywords;
    private final String contentSourceId;
    private final String contentTitle;
    private final String env;
    private final Map<String, String> extraParameters;
    private final String format;
    private final js identifierInfo;
    private final Boolean isTv;
    private final Integer linearAdSlotHeight;
    private final Integer linearAdSlotWidth;
    private final Float liveStreamPrefetchSeconds;
    private final b marketAppInfo;
    private final String msParameter;
    private final String network;
    private final ImaSdkSettings settings;
    private final String streamActivityMonitorId;
    private final Boolean useQAStreamBaseUrl;
    private final Float vastLoadTimeout;
    private final String videoId;
    private final com.google.ads.interactivemedia.v3.internal.in.a videoPlayActivation;
    private final in.b videoPlayMuted;

    static final class a implements com.google.ads.interactivemedia.v3.impl.data.o.a {
        private Map<String, String> adTagParameters;
        private String adTagUrl;
        private String adsResponse;
        private String apiKey;
        private String assetKey;
        private String authToken;
        private Map<String, String> companionSlots;
        private Float contentDuration;
        private List<String> contentKeywords;
        private String contentSourceId;
        private String contentTitle;
        private String env;
        private Map<String, String> extraParameters;
        private String format;
        private js identifierInfo;
        private Boolean isTv;
        private Integer linearAdSlotHeight;
        private Integer linearAdSlotWidth;
        private Float liveStreamPrefetchSeconds;
        private b marketAppInfo;
        private String msParameter;
        private String network;
        private ImaSdkSettings settings;
        private String streamActivityMonitorId;
        private Boolean useQAStreamBaseUrl;
        private Float vastLoadTimeout;
        private String videoId;
        private com.google.ads.interactivemedia.v3.internal.in.a videoPlayActivation;
        private in.b videoPlayMuted;

        a() {
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a adsResponse(String str) {
            this.adsResponse = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a adTagUrl(String str) {
            this.adTagUrl = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a assetKey(String str) {
            this.assetKey = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a authToken(String str) {
            this.authToken = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a contentSourceId(String str) {
            this.contentSourceId = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a videoId(String str) {
            this.videoId = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a apiKey(String str) {
            this.apiKey = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a format(String str) {
            this.format = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a adTagParameters(Map<String, String> map) {
            this.adTagParameters = map;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a env(String str) {
            this.env = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a network(String str) {
            this.network = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a contentDuration(Float f) {
            this.contentDuration = f;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a contentKeywords(List<String> list) {
            this.contentKeywords = list;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a contentTitle(String str) {
            this.contentTitle = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a vastLoadTimeout(Float f) {
            this.vastLoadTimeout = f;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a liveStreamPrefetchSeconds(Float f) {
            this.liveStreamPrefetchSeconds = f;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a companionSlots(Map<String, String> map) {
            this.companionSlots = map;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a extraParameters(Map<String, String> map) {
            this.extraParameters = map;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a isTv(Boolean bool) {
            this.isTv = bool;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a msParameter(String str) {
            this.msParameter = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a linearAdSlotWidth(Integer num) {
            this.linearAdSlotWidth = num;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a linearAdSlotHeight(Integer num) {
            this.linearAdSlotHeight = num;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a streamActivityMonitorId(String str) {
            this.streamActivityMonitorId = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a identifierInfo(js jsVar) {
            this.identifierInfo = jsVar;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a useQAStreamBaseUrl(Boolean bool) {
            this.useQAStreamBaseUrl = bool;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a videoPlayActivation(com.google.ads.interactivemedia.v3.internal.in.a aVar) {
            this.videoPlayActivation = aVar;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a videoPlayMuted(in.b bVar) {
            this.videoPlayMuted = bVar;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a settings(ImaSdkSettings imaSdkSettings) {
            this.settings = imaSdkSettings;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o.a marketAppInfo(b bVar) {
            this.marketAppInfo = bVar;
            return this;
        }

        public o build() {
            String str = this.adsResponse;
            String str2 = this.adTagUrl;
            String str3 = this.assetKey;
            String str4 = this.authToken;
            String str5 = this.contentSourceId;
            String str6 = this.videoId;
            String str7 = this.apiKey;
            String str8 = this.format;
            Map map = this.adTagParameters;
            String str9 = this.env;
            String str10 = this.network;
            Float f = this.contentDuration;
            List list = this.contentKeywords;
            String str11 = this.contentTitle;
            String str12 = str11;
            return new h(str, str2, str3, str4, str5, str6, str7, str8, map, str9, str10, f, list, str12, this.vastLoadTimeout, this.liveStreamPrefetchSeconds, this.companionSlots, this.extraParameters, this.isTv, this.msParameter, this.linearAdSlotWidth, this.linearAdSlotHeight, this.streamActivityMonitorId, this.identifierInfo, this.useQAStreamBaseUrl, this.videoPlayActivation, this.videoPlayMuted, this.settings, this.marketAppInfo);
        }
    }

    private h(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Map<String, String> map, String str9, String str10, Float f, List<String> list, String str11, Float f2, Float f3, Map<String, String> map2, Map<String, String> map3, Boolean bool, String str12, Integer num, Integer num2, String str13, js jsVar, Boolean bool2, com.google.ads.interactivemedia.v3.internal.in.a aVar, in.b bVar, ImaSdkSettings imaSdkSettings, b bVar2) {
        this.adsResponse = str;
        this.adTagUrl = str2;
        this.assetKey = str3;
        this.authToken = str4;
        this.contentSourceId = str5;
        this.videoId = str6;
        this.apiKey = str7;
        this.format = str8;
        this.adTagParameters = map;
        this.env = str9;
        this.network = str10;
        this.contentDuration = f;
        this.contentKeywords = list;
        this.contentTitle = str11;
        this.vastLoadTimeout = f2;
        this.liveStreamPrefetchSeconds = f3;
        this.companionSlots = map2;
        this.extraParameters = map3;
        this.isTv = bool;
        this.msParameter = str12;
        this.linearAdSlotWidth = num;
        this.linearAdSlotHeight = num2;
        this.streamActivityMonitorId = str13;
        this.identifierInfo = jsVar;
        this.useQAStreamBaseUrl = bool2;
        this.videoPlayActivation = aVar;
        this.videoPlayMuted = bVar;
        this.settings = imaSdkSettings;
        this.marketAppInfo = bVar2;
    }

    public String adsResponse() {
        return this.adsResponse;
    }

    public String adTagUrl() {
        return this.adTagUrl;
    }

    public String assetKey() {
        return this.assetKey;
    }

    public String authToken() {
        return this.authToken;
    }

    public String contentSourceId() {
        return this.contentSourceId;
    }

    public String videoId() {
        return this.videoId;
    }

    public String apiKey() {
        return this.apiKey;
    }

    public String format() {
        return this.format;
    }

    public Map<String, String> adTagParameters() {
        return this.adTagParameters;
    }

    public String env() {
        return this.env;
    }

    public String network() {
        return this.network;
    }

    public Float contentDuration() {
        return this.contentDuration;
    }

    public List<String> contentKeywords() {
        return this.contentKeywords;
    }

    public String contentTitle() {
        return this.contentTitle;
    }

    public Float vastLoadTimeout() {
        return this.vastLoadTimeout;
    }

    public Float liveStreamPrefetchSeconds() {
        return this.liveStreamPrefetchSeconds;
    }

    public Map<String, String> companionSlots() {
        return this.companionSlots;
    }

    public Map<String, String> extraParameters() {
        return this.extraParameters;
    }

    public Boolean isTv() {
        return this.isTv;
    }

    public String msParameter() {
        return this.msParameter;
    }

    public Integer linearAdSlotWidth() {
        return this.linearAdSlotWidth;
    }

    public Integer linearAdSlotHeight() {
        return this.linearAdSlotHeight;
    }

    public String streamActivityMonitorId() {
        return this.streamActivityMonitorId;
    }

    public js identifierInfo() {
        return this.identifierInfo;
    }

    public Boolean useQAStreamBaseUrl() {
        return this.useQAStreamBaseUrl;
    }

    public com.google.ads.interactivemedia.v3.internal.in.a videoPlayActivation() {
        return this.videoPlayActivation;
    }

    public in.b videoPlayMuted() {
        return this.videoPlayMuted;
    }

    public ImaSdkSettings settings() {
        return this.settings;
    }

    public b marketAppInfo() {
        return this.marketAppInfo;
    }

    public String toString() {
        String str = this.adsResponse;
        String str2 = this.adTagUrl;
        String str3 = this.assetKey;
        String str4 = this.authToken;
        String str5 = this.contentSourceId;
        String str6 = this.videoId;
        String str7 = this.apiKey;
        String str8 = this.format;
        String valueOf = String.valueOf(this.adTagParameters);
        String str9 = this.env;
        String str10 = this.network;
        String valueOf2 = String.valueOf(this.contentDuration);
        String valueOf3 = String.valueOf(this.contentKeywords);
        String str11 = this.contentTitle;
        String valueOf4 = String.valueOf(this.vastLoadTimeout);
        String valueOf5 = String.valueOf(this.liveStreamPrefetchSeconds);
        String valueOf6 = String.valueOf(this.companionSlots);
        String valueOf7 = String.valueOf(this.extraParameters);
        String valueOf8 = String.valueOf(this.isTv);
        String str12 = this.msParameter;
        String valueOf9 = String.valueOf(this.linearAdSlotWidth);
        String valueOf10 = String.valueOf(this.linearAdSlotHeight);
        String str13 = this.streamActivityMonitorId;
        String valueOf11 = String.valueOf(this.identifierInfo);
        String valueOf12 = String.valueOf(this.useQAStreamBaseUrl);
        String valueOf13 = String.valueOf(this.videoPlayActivation);
        String valueOf14 = String.valueOf(this.videoPlayMuted);
        String valueOf15 = String.valueOf(this.settings);
        String valueOf16 = String.valueOf(this.marketAppInfo);
        String str14 = str11;
        String str15 = valueOf4;
        String str16 = str15;
        str11 = valueOf5;
        String str17 = str11;
        str15 = valueOf6;
        String str18 = str15;
        str11 = valueOf7;
        String str19 = str11;
        str15 = valueOf8;
        String str20 = str15;
        str11 = str12;
        String str21 = str11;
        str15 = valueOf9;
        String str22 = str15;
        str11 = valueOf10;
        String str23 = str11;
        str15 = str13;
        String str24 = str15;
        str11 = valueOf11;
        String str25 = str11;
        str15 = valueOf12;
        String str26 = str15;
        str11 = valueOf13;
        String str27 = str11;
        str15 = valueOf14;
        String str28 = str15;
        str11 = valueOf15;
        String str29 = valueOf16;
        StringBuilder stringBuilder = new StringBuilder(((((((((((((((((((((((((((((466 + String.valueOf(str).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()) + String.valueOf(str4).length()) + String.valueOf(str5).length()) + String.valueOf(str6).length()) + String.valueOf(str7).length()) + String.valueOf(str8).length()) + String.valueOf(valueOf).length()) + String.valueOf(str9).length()) + String.valueOf(str10).length()) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()) + String.valueOf(str11).length()) + String.valueOf(str15).length()) + String.valueOf(str11).length()) + String.valueOf(str15).length()) + String.valueOf(str11).length()) + String.valueOf(str15).length()) + String.valueOf(str11).length()) + String.valueOf(str15).length()) + String.valueOf(str11).length()) + String.valueOf(str15).length()) + String.valueOf(str11).length()) + String.valueOf(str15).length()) + String.valueOf(str11).length()) + String.valueOf(str15).length()) + String.valueOf(str11).length()) + String.valueOf(valueOf16).length());
        stringBuilder.append("GsonAdsRequest{adsResponse=");
        stringBuilder.append(str);
        stringBuilder.append(", adTagUrl=");
        stringBuilder.append(str2);
        stringBuilder.append(", assetKey=");
        stringBuilder.append(str3);
        stringBuilder.append(", authToken=");
        stringBuilder.append(str4);
        stringBuilder.append(", contentSourceId=");
        stringBuilder.append(str5);
        stringBuilder.append(", videoId=");
        stringBuilder.append(str6);
        stringBuilder.append(", apiKey=");
        stringBuilder.append(str7);
        stringBuilder.append(", format=");
        stringBuilder.append(str8);
        stringBuilder.append(", adTagParameters=");
        stringBuilder.append(valueOf);
        stringBuilder.append(", env=");
        stringBuilder.append(str9);
        stringBuilder.append(", network=");
        stringBuilder.append(str10);
        stringBuilder.append(", contentDuration=");
        stringBuilder.append(valueOf2);
        stringBuilder.append(", contentKeywords=");
        stringBuilder.append(valueOf3);
        stringBuilder.append(", contentTitle=");
        stringBuilder.append(str14);
        stringBuilder.append(", vastLoadTimeout=");
        stringBuilder.append(str16);
        stringBuilder.append(", liveStreamPrefetchSeconds=");
        stringBuilder.append(str17);
        stringBuilder.append(", companionSlots=");
        stringBuilder.append(str18);
        stringBuilder.append(", extraParameters=");
        stringBuilder.append(str19);
        stringBuilder.append(", isTv=");
        stringBuilder.append(str20);
        stringBuilder.append(", msParameter=");
        stringBuilder.append(str21);
        stringBuilder.append(", linearAdSlotWidth=");
        stringBuilder.append(str22);
        stringBuilder.append(", linearAdSlotHeight=");
        stringBuilder.append(str23);
        stringBuilder.append(", streamActivityMonitorId=");
        stringBuilder.append(str24);
        stringBuilder.append(", identifierInfo=");
        stringBuilder.append(str25);
        stringBuilder.append(", useQAStreamBaseUrl=");
        stringBuilder.append(str26);
        stringBuilder.append(", videoPlayActivation=");
        stringBuilder.append(str27);
        stringBuilder.append(", videoPlayMuted=");
        stringBuilder.append(str28);
        stringBuilder.append(", settings=");
        stringBuilder.append(str11);
        stringBuilder.append(", marketAppInfo=");
        stringBuilder.append(str29);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        if (this.adsResponse != null ? !this.adsResponse.equals(oVar.adsResponse()) : oVar.adsResponse() != null) {
            if (this.adTagUrl != null ? !this.adTagUrl.equals(oVar.adTagUrl()) : oVar.adTagUrl() != null) {
                if (this.assetKey != null ? !this.assetKey.equals(oVar.assetKey()) : oVar.assetKey() != null) {
                    if (this.authToken != null ? !this.authToken.equals(oVar.authToken()) : oVar.authToken() != null) {
                        if (this.contentSourceId != null ? !this.contentSourceId.equals(oVar.contentSourceId()) : oVar.contentSourceId() != null) {
                            if (this.videoId != null ? !this.videoId.equals(oVar.videoId()) : oVar.videoId() != null) {
                                if (this.apiKey != null ? !this.apiKey.equals(oVar.apiKey()) : oVar.apiKey() != null) {
                                    if (this.format != null ? !this.format.equals(oVar.format()) : oVar.format() != null) {
                                        if (this.adTagParameters != null ? !this.adTagParameters.equals(oVar.adTagParameters()) : oVar.adTagParameters() != null) {
                                            if (this.env != null ? !this.env.equals(oVar.env()) : oVar.env() != null) {
                                                if (this.network != null ? !this.network.equals(oVar.network()) : oVar.network() != null) {
                                                    if (this.contentDuration != null ? !this.contentDuration.equals(oVar.contentDuration()) : oVar.contentDuration() != null) {
                                                        if (this.contentKeywords != null ? !this.contentKeywords.equals(oVar.contentKeywords()) : oVar.contentKeywords() != null) {
                                                            if (this.contentTitle != null ? !this.contentTitle.equals(oVar.contentTitle()) : oVar.contentTitle() != null) {
                                                                if (this.vastLoadTimeout != null ? !this.vastLoadTimeout.equals(oVar.vastLoadTimeout()) : oVar.vastLoadTimeout() != null) {
                                                                    if (this.liveStreamPrefetchSeconds != null ? !this.liveStreamPrefetchSeconds.equals(oVar.liveStreamPrefetchSeconds()) : oVar.liveStreamPrefetchSeconds() != null) {
                                                                        if (this.companionSlots != null ? !this.companionSlots.equals(oVar.companionSlots()) : oVar.companionSlots() != null) {
                                                                            if (this.extraParameters != null ? !this.extraParameters.equals(oVar.extraParameters()) : oVar.extraParameters() != null) {
                                                                                if (this.isTv != null ? !this.isTv.equals(oVar.isTv()) : oVar.isTv() != null) {
                                                                                    if (this.msParameter != null ? !this.msParameter.equals(oVar.msParameter()) : oVar.msParameter() != null) {
                                                                                        if (this.linearAdSlotWidth != null ? !this.linearAdSlotWidth.equals(oVar.linearAdSlotWidth()) : oVar.linearAdSlotWidth() != null) {
                                                                                            if (this.linearAdSlotHeight != null ? !this.linearAdSlotHeight.equals(oVar.linearAdSlotHeight()) : oVar.linearAdSlotHeight() != null) {
                                                                                                if (this.streamActivityMonitorId != null ? !this.streamActivityMonitorId.equals(oVar.streamActivityMonitorId()) : oVar.streamActivityMonitorId() != null) {
                                                                                                    if (this.identifierInfo != null ? !this.identifierInfo.equals(oVar.identifierInfo()) : oVar.identifierInfo() != null) {
                                                                                                        if (this.useQAStreamBaseUrl != null ? !this.useQAStreamBaseUrl.equals(oVar.useQAStreamBaseUrl()) : oVar.useQAStreamBaseUrl() != null) {
                                                                                                            if (this.videoPlayActivation != null ? !this.videoPlayActivation.equals(oVar.videoPlayActivation()) : oVar.videoPlayActivation() != null) {
                                                                                                                if (this.videoPlayMuted != null ? !this.videoPlayMuted.equals(oVar.videoPlayMuted()) : oVar.videoPlayMuted() != null) {
                                                                                                                    if (this.settings != null ? !this.settings.equals(oVar.settings()) : oVar.settings() != null) {
                                                                                                                        if (this.marketAppInfo != null) {
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.adsResponse == null ? 0 : this.adsResponse.hashCode()) ^ 1000003) * 1000003) ^ (this.adTagUrl == null ? 0 : this.adTagUrl.hashCode())) * 1000003) ^ (this.assetKey == null ? 0 : this.assetKey.hashCode())) * 1000003) ^ (this.authToken == null ? 0 : this.authToken.hashCode())) * 1000003) ^ (this.contentSourceId == null ? 0 : this.contentSourceId.hashCode())) * 1000003) ^ (this.videoId == null ? 0 : this.videoId.hashCode())) * 1000003) ^ (this.apiKey == null ? 0 : this.apiKey.hashCode())) * 1000003) ^ (this.format == null ? 0 : this.format.hashCode())) * 1000003) ^ (this.adTagParameters == null ? 0 : this.adTagParameters.hashCode())) * 1000003) ^ (this.env == null ? 0 : this.env.hashCode())) * 1000003) ^ (this.network == null ? 0 : this.network.hashCode())) * 1000003) ^ (this.contentDuration == null ? 0 : this.contentDuration.hashCode())) * 1000003) ^ (this.contentKeywords == null ? 0 : this.contentKeywords.hashCode())) * 1000003) ^ (this.contentTitle == null ? 0 : this.contentTitle.hashCode())) * 1000003) ^ (this.vastLoadTimeout == null ? 0 : this.vastLoadTimeout.hashCode())) * 1000003) ^ (this.liveStreamPrefetchSeconds == null ? 0 : this.liveStreamPrefetchSeconds.hashCode())) * 1000003) ^ (this.companionSlots == null ? 0 : this.companionSlots.hashCode())) * 1000003) ^ (this.extraParameters == null ? 0 : this.extraParameters.hashCode())) * 1000003) ^ (this.isTv == null ? 0 : this.isTv.hashCode())) * 1000003) ^ (this.msParameter == null ? 0 : this.msParameter.hashCode())) * 1000003) ^ (this.linearAdSlotWidth == null ? 0 : this.linearAdSlotWidth.hashCode())) * 1000003) ^ (this.linearAdSlotHeight == null ? 0 : this.linearAdSlotHeight.hashCode())) * 1000003) ^ (this.streamActivityMonitorId == null ? 0 : this.streamActivityMonitorId.hashCode())) * 1000003) ^ (this.identifierInfo == null ? 0 : this.identifierInfo.hashCode())) * 1000003) ^ (this.useQAStreamBaseUrl == null ? 0 : this.useQAStreamBaseUrl.hashCode())) * 1000003) ^ (this.videoPlayActivation == null ? 0 : this.videoPlayActivation.hashCode())) * 1000003) ^ (this.videoPlayMuted == null ? 0 : this.videoPlayMuted.hashCode())) * 1000003) ^ (this.settings == null ? 0 : this.settings.hashCode())) * 1000003;
        if (this.marketAppInfo != null) {
            i = this.marketAppInfo.hashCode();
        }
        return hashCode ^ i;
    }
}
