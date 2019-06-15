package com.google.ads.interactivemedia.v3.impl.data;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.api.StreamRequest.StreamFormat;
import com.google.ads.interactivemedia.v3.internal.ic;
import com.google.ads.interactivemedia.v3.internal.ii.b;
import com.google.ads.interactivemedia.v3.internal.in;
import com.google.ads.interactivemedia.v3.internal.iq;
import com.google.ads.interactivemedia.v3.internal.jk;
import com.google.ads.interactivemedia.v3.internal.js;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.List;
import java.util.Map;

public abstract class o {

    public interface a {
        a adTagParameters(Map<String, String> map);

        a adTagUrl(String str);

        a adsResponse(String str);

        a apiKey(String str);

        a assetKey(String str);

        a authToken(String str);

        o build();

        a companionSlots(Map<String, String> map);

        a contentDuration(Float f);

        a contentKeywords(List<String> list);

        a contentSourceId(String str);

        a contentTitle(String str);

        a env(String str);

        a extraParameters(Map<String, String> map);

        a format(String str);

        a identifierInfo(js jsVar);

        a isTv(Boolean bool);

        a linearAdSlotHeight(Integer num);

        a linearAdSlotWidth(Integer num);

        a liveStreamPrefetchSeconds(Float f);

        a marketAppInfo(b bVar);

        a msParameter(String str);

        a network(String str);

        a settings(ImaSdkSettings imaSdkSettings);

        a streamActivityMonitorId(String str);

        a useQAStreamBaseUrl(Boolean bool);

        a vastLoadTimeout(Float f);

        a videoId(String str);

        a videoPlayActivation(com.google.ads.interactivemedia.v3.internal.in.a aVar);

        a videoPlayMuted(in.b bVar);
    }

    public abstract Map<String, String> adTagParameters();

    public abstract String adTagUrl();

    public abstract String adsResponse();

    public abstract String apiKey();

    public abstract String assetKey();

    public abstract String authToken();

    public abstract Map<String, String> companionSlots();

    public abstract Float contentDuration();

    public abstract List<String> contentKeywords();

    public abstract String contentSourceId();

    public abstract String contentTitle();

    public abstract String env();

    public abstract Map<String, String> extraParameters();

    public abstract String format();

    public abstract js identifierInfo();

    public abstract Boolean isTv();

    public abstract Integer linearAdSlotHeight();

    public abstract Integer linearAdSlotWidth();

    public abstract Float liveStreamPrefetchSeconds();

    public abstract b marketAppInfo();

    public abstract String msParameter();

    public abstract String network();

    public abstract ImaSdkSettings settings();

    public abstract String streamActivityMonitorId();

    public abstract Boolean useQAStreamBaseUrl();

    public abstract Float vastLoadTimeout();

    public abstract String videoId();

    public abstract com.google.ads.interactivemedia.v3.internal.in.a videoPlayActivation();

    public abstract in.b videoPlayMuted();

    public static o create(AdsRequest adsRequest, String str, String str2, ImaSdkSettings imaSdkSettings, b bVar, boolean z, js jsVar) {
        String adTagUrl = adsRequest.getAdTagUrl();
        String adsResponse = adsRequest.getAdsResponse();
        Map extraParameters = adsRequest.getExtraParameters();
        in inVar = (in) adsRequest;
        com.google.ads.interactivemedia.v3.internal.in.a a = inVar.a();
        in.b b = inVar.b();
        Float c = inVar.c();
        List d = inVar.d();
        String e = inVar.e();
        Float f = inVar.f();
        Float g = inVar.g();
        Map companionSlots = getCompanionSlots((ic) adsRequest.getAdDisplayContainer());
        ViewGroup adContainer = adsRequest.getAdDisplayContainer().getAdContainer();
        return builder().adTagUrl(adTagUrl).adsResponse(adsResponse).env(str).network(str2).extraParameters(extraParameters).settings(imaSdkSettings).videoPlayActivation(a).videoPlayMuted(b).contentDuration(c).contentKeywords(d).contentTitle(e).vastLoadTimeout(f).liveStreamPrefetchSeconds(g).companionSlots(companionSlots).marketAppInfo(bVar).isTv(Boolean.valueOf(z)).identifierInfo(jsVar).linearAdSlotWidth(Integer.valueOf(adContainer.getWidth())).linearAdSlotHeight(Integer.valueOf(adContainer.getHeight())).build();
    }

    public static o createFromStreamRequest(StreamRequest streamRequest, String str, String str2, ImaSdkSettings imaSdkSettings, b bVar, boolean z, String str3, js jsVar) {
        Map companionSlots = getCompanionSlots((jk) streamRequest.getStreamDisplayContainer());
        ViewGroup adContainer = streamRequest.getStreamDisplayContainer().getAdContainer();
        String str4 = "hls";
        if (streamRequest.getFormat() == StreamFormat.DASH) {
            str4 = "dash";
        }
        return builder().assetKey(streamRequest.getAssetKey()).authToken(streamRequest.getAuthToken()).contentSourceId(streamRequest.getContentSourceId()).videoId(streamRequest.getVideoId()).apiKey(streamRequest.getApiKey()).adTagParameters(streamRequest.getAdTagParameters()).env(str).network(str2).settings(imaSdkSettings).companionSlots(companionSlots).marketAppInfo(bVar).isTv(Boolean.valueOf(z)).msParameter(str3).linearAdSlotWidth(Integer.valueOf(adContainer.getWidth())).linearAdSlotHeight(Integer.valueOf(adContainer.getHeight())).streamActivityMonitorId(streamRequest.getStreamActivityMonitorId()).format(str4).identifierInfo(jsVar).useQAStreamBaseUrl(streamRequest.getUseQAStreamBaseUrl()).build();
    }

    public static a builder() {
        return new a();
    }

    private static Map<String, String> getCompanionSlots(iq iqVar) {
        Map a = iqVar.a();
        if (a == null || a.isEmpty()) {
            return null;
        }
        com.google.ads.interactivemedia.v3.internal.lb.a aVar = new com.google.ads.interactivemedia.v3.internal.lb.a();
        for (String str : a.keySet()) {
            CompanionAdSlot companionAdSlot = (CompanionAdSlot) a.get(str);
            int width = companionAdSlot.getWidth();
            int height = companionAdSlot.getHeight();
            StringBuilder stringBuilder = new StringBuilder(23);
            stringBuilder.append(width);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(height);
            aVar.a(str, stringBuilder.toString());
        }
        return aVar.a();
    }
}
