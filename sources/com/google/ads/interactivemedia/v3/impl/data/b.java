package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.api.CompanionAd;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.internal.lx;
import com.google.ads.interactivemedia.v3.internal.ly;
import com.google.ads.interactivemedia.v3.internal.lz;
import com.google.ads.interactivemedia.v3.internal.ma;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class b implements Ad {
    private String adId;
    @ly
    @ma
    private c adPodInfo = new c();
    private String adSystem;
    @ly
    @ma
    private String[] adWrapperCreativeIds;
    @ly
    @ma
    private String[] adWrapperIds;
    @ly
    @ma
    private String[] adWrapperSystems;
    private String advertiserName;
    private String clickThroughUrl;
    @ly
    @ma
    private List<Object> companions;
    private String contentType;
    private String creativeAdId;
    private String creativeId;
    private String dealId;
    private String description;
    private boolean disableUi;
    private double duration;
    private int height;
    private boolean isUiDisabled_ = false;
    private boolean linear;
    private double skipTimeOffset = -1.0d;
    private boolean skippable;
    private String surveyUrl;
    private String title;
    private String traffickingParameters;
    @ly
    @ma
    private Set<UiElement> uiElements;
    private String universalAdIdRegistry;
    private String universalAdIdValue;
    private int vastMediaBitrate;
    private int vastMediaHeight;
    private int vastMediaWidth;
    private int width;

    public String getAdId() {
        return this.adId;
    }

    public void setAdId(String str) {
        this.adId = str;
    }

    public String getCreativeId() {
        return this.creativeId;
    }

    public void setCreativeId(String str) {
        this.creativeId = str;
    }

    public String getCreativeAdId() {
        return this.creativeAdId;
    }

    public void setCreativeAdId(String str) {
        this.creativeAdId = str;
    }

    public String getUniversalAdIdValue() {
        return this.universalAdIdValue;
    }

    public void setUniversalAdIdValue(String str) {
        this.universalAdIdValue = str;
    }

    public String getUniversalAdIdRegistry() {
        return this.universalAdIdRegistry;
    }

    public void setUniversalAdIdRegistry(String str) {
        this.universalAdIdRegistry = str;
    }

    public String getAdSystem() {
        return this.adSystem;
    }

    public void setAdSystem(String str) {
        this.adSystem = str;
    }

    public String[] getAdWrapperIds() {
        return this.adWrapperIds;
    }

    public void setAdWrapperIds(String[] strArr) {
        this.adWrapperIds = strArr;
    }

    public String[] getAdWrapperSystems() {
        return this.adWrapperSystems;
    }

    public void setAdWrapperSystems(String[] strArr) {
        this.adWrapperSystems = strArr;
    }

    public String[] getAdWrapperCreativeIds() {
        return this.adWrapperCreativeIds;
    }

    public void setAdWrapperCreativeIds(String[] strArr) {
        this.adWrapperCreativeIds = strArr;
    }

    public boolean isLinear() {
        return this.linear;
    }

    public void setLinear(boolean z) {
        this.linear = z;
    }

    public boolean isSkippable() {
        return this.skippable;
    }

    public void setSkippable(boolean z) {
        this.skippable = z;
    }

    public double getSkipTimeOffset() {
        return this.skipTimeOffset;
    }

    public void setSkipTimeOffset(double d) {
        this.skipTimeOffset = d;
    }

    public boolean isUiDisabled() {
        return this.isUiDisabled_;
    }

    public void setUiDisabled(boolean z) {
        this.isUiDisabled_ = z;
    }

    public boolean canDisableUi() {
        return this.disableUi;
    }

    public void setCanDisableUi(boolean z) {
        this.disableUi = z;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public String getAdvertiserName() {
        return this.advertiserName;
    }

    public void setAdvertiserName(String str) {
        this.advertiserName = str;
    }

    public String getSurveyUrl() {
        return this.surveyUrl;
    }

    public void setSurveyUrl(String str) {
        this.surveyUrl = str;
    }

    public String getDealId() {
        return this.dealId;
    }

    public void setDealId(String str) {
        this.dealId = str;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getVastMediaWidth() {
        return this.vastMediaWidth;
    }

    public void setVastMediaWidth(int i) {
        this.vastMediaWidth = i;
    }

    public int getVastMediaHeight() {
        return this.vastMediaHeight;
    }

    public void setVastMediaHeight(int i) {
        this.vastMediaHeight = i;
    }

    public int getVastMediaBitrate() {
        return this.vastMediaBitrate;
    }

    public void setVastMediaBitrate(int i) {
        this.vastMediaBitrate = i;
    }

    public String getTraffickingParameters() {
        return this.traffickingParameters;
    }

    public void setTraffickingParameters(String str) {
        this.traffickingParameters = str;
    }

    public String getClickThruUrl() {
        return this.clickThroughUrl;
    }

    public void setClickThruUrl(String str) {
        this.clickThroughUrl = str;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double d) {
        this.duration = d;
    }

    public AdPodInfo getAdPodInfo() {
        return this.adPodInfo;
    }

    public void setAdPodInfo(c cVar) {
        this.adPodInfo = cVar;
    }

    public Set<UiElement> getUiElements() {
        return this.uiElements;
    }

    public void setUiElements(Set<UiElement> set) {
        this.uiElements = set;
    }

    public List<CompanionAd> getCompanionAds() {
        ArrayList arrayList = new ArrayList();
        for (CompanionAd add : this.companions) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public void setCompanionAds(List<Object> list) {
        this.companions = list;
    }

    public int hashCode() {
        return lz.a(this, new String[0]);
    }

    public boolean equals(Object obj) {
        return lx.a((Object) this, obj, new String[0]);
    }

    public String toString() {
        String str = this.adId;
        String str2 = this.creativeId;
        String str3 = this.creativeAdId;
        String str4 = this.universalAdIdValue;
        String str5 = this.universalAdIdRegistry;
        String str6 = this.title;
        String str7 = this.description;
        String str8 = this.contentType;
        String arrays = Arrays.toString(this.adWrapperIds);
        String arrays2 = Arrays.toString(this.adWrapperSystems);
        String arrays3 = Arrays.toString(this.adWrapperCreativeIds);
        String str9 = this.adSystem;
        String str10 = this.advertiserName;
        String str11 = this.surveyUrl;
        String str12 = this.dealId;
        boolean z = this.linear;
        boolean z2 = this.skippable;
        int i = this.width;
        int i2 = this.height;
        String str13 = this.traffickingParameters;
        String str14 = str11;
        String str15 = this.clickThroughUrl;
        double d = this.duration;
        str11 = String.valueOf(this.adPodInfo);
        String valueOf = String.valueOf(this.uiElements);
        String str16 = str11;
        boolean z3 = this.disableUi;
        double d2 = this.skipTimeOffset;
        String str17 = str14;
        str11 = str12;
        String str18 = str11;
        String str19 = str13;
        String str20 = str19;
        str11 = str15;
        String str21 = str11;
        str19 = str16;
        String str22 = str19;
        str11 = valueOf;
        String str23 = str11;
        StringBuilder stringBuilder = new StringBuilder(((((((((((((((((((455 + String.valueOf(str).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()) + String.valueOf(str4).length()) + String.valueOf(str5).length()) + String.valueOf(str6).length()) + String.valueOf(str7).length()) + String.valueOf(str8).length()) + String.valueOf(arrays).length()) + String.valueOf(arrays2).length()) + String.valueOf(arrays3).length()) + String.valueOf(str9).length()) + String.valueOf(str10).length()) + String.valueOf(str17).length()) + String.valueOf(str11).length()) + String.valueOf(str19).length()) + String.valueOf(str11).length()) + String.valueOf(str19).length()) + String.valueOf(str11).length());
        stringBuilder.append("Ad [adId=");
        stringBuilder.append(str);
        stringBuilder.append(", creativeId=");
        stringBuilder.append(str2);
        stringBuilder.append(", creativeAdId=");
        stringBuilder.append(str3);
        stringBuilder.append(", universalAdIdValue=");
        stringBuilder.append(str4);
        stringBuilder.append(", universalAdIdRegistry=");
        stringBuilder.append(str5);
        stringBuilder.append(", title=");
        stringBuilder.append(str6);
        stringBuilder.append(", description=");
        stringBuilder.append(str7);
        stringBuilder.append(", contentType=");
        stringBuilder.append(str8);
        stringBuilder.append(", adWrapperIds=");
        stringBuilder.append(arrays);
        stringBuilder.append(", adWrapperSystems=");
        stringBuilder.append(arrays2);
        stringBuilder.append(", adWrapperCreativeIds=");
        stringBuilder.append(arrays3);
        stringBuilder.append(", adSystem=");
        stringBuilder.append(str9);
        stringBuilder.append(", advertiserName=");
        stringBuilder.append(str10);
        stringBuilder.append(", surveyUrl=");
        stringBuilder.append(str17);
        stringBuilder.append(", dealId=");
        stringBuilder.append(str18);
        stringBuilder.append(", linear=");
        stringBuilder.append(z);
        stringBuilder.append(", skippable=");
        stringBuilder.append(z2);
        stringBuilder.append(", width=");
        stringBuilder.append(i);
        stringBuilder.append(", height=");
        stringBuilder.append(i2);
        stringBuilder.append(", traffickingParameters=");
        stringBuilder.append(str20);
        stringBuilder.append(", clickThroughUrl=");
        stringBuilder.append(str21);
        stringBuilder.append(", duration=");
        stringBuilder.append(d);
        stringBuilder.append(", adPodInfo=");
        stringBuilder.append(str22);
        stringBuilder.append(", uiElements=");
        stringBuilder.append(str23);
        stringBuilder.append(", disableUi=");
        stringBuilder.append(z3);
        stringBuilder.append(", skipTimeOffset=");
        stringBuilder.append(d2);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
