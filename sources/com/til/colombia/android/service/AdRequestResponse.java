package com.til.colombia.android.service;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.til.colombia.android.commons.CommonUtil.MediaSource;
import com.til.colombia.android.commons.MEDIA_CACHE_FLAG;
import com.til.colombia.android.internal.a.g;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.ErrorCode;
import com.til.colombia.android.network.MediationNetworkItem;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.service.VASTHelper.XmlCallbacks;
import com.til.colombia.android.utils.a;
import com.til.colombia.android.vast.VastCompanionAdConfig;
import com.til.colombia.android.vast.VastCompanionResource.CreativeType;
import com.til.colombia.android.vast.VastCompanionResource.Type;
import java.util.ArrayList;

public class AdRequestResponse implements XmlCallbacks {
    private static final String ADSLOT_JOINT = "~";
    private AdListener adListener;
    private Long adUnitId;
    private ColombiaAdRequest colombiaAdRequest;
    private boolean isLoading = true;
    private boolean loadIcon = false;
    private boolean loadImage = false;
    private Integer position;
    private String requestCode;
    private ItemResponse response;
    private String sectionId;
    private VASTHelper vastHelper;
    private boolean webViewEnabled = false;

    public static boolean isValid(Long l, Integer num, String str) {
        return (l == null || num == null || str == null) ? false : true;
    }

    public void onParsingStatus() {
    }

    public static String getAdSlot(Long l, Integer num, String str) {
        if (!isValid(l, num, str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(l);
        stringBuilder.append(ADSLOT_JOINT);
        stringBuilder.append(num);
        stringBuilder.append(ADSLOT_JOINT);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public String getAdSlot() {
        return getAdSlot(this.adUnitId, this.position, this.sectionId);
    }

    public boolean shouldLoadIcon() {
        return this.loadIcon;
    }

    public void setLoadIcon(boolean z) {
        this.loadIcon = z;
    }

    public boolean shouldLoadImage() {
        return this.loadImage;
    }

    public void setLoadImage(boolean z) {
        this.loadImage = z;
    }

    public Long getAdUnitId() {
        return this.adUnitId;
    }

    public void setAdUnitId(Long l) {
        this.adUnitId = l;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer num) {
        this.position = num;
    }

    public String getSectionId() {
        return this.sectionId;
    }

    public void setSectionId(String str) {
        this.sectionId = str;
    }

    public AdListener getAdListener() {
        return this.adListener;
    }

    public void setAdListener(AdListener adListener) {
        this.adListener = adListener;
    }

    public void setWebViewEnabled(boolean z) {
        this.webViewEnabled = z;
    }

    public boolean getWebViewEnabled() {
        return this.webViewEnabled;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdRequestResponse adRequestResponse = (AdRequestResponse) obj;
        if (!this.adUnitId == null ? this.adUnitId.equals(adRequestResponse.adUnitId) : adRequestResponse.adUnitId == null) {
            return false;
        }
        if (!this.adListener == null ? this.adListener.equals(adRequestResponse.adListener) : adRequestResponse.adListener == null) {
            return false;
        }
        if (this.position == null ? adRequestResponse.position == null : this.position.equals(adRequestResponse.position)) {
            return this.sectionId == null ? adRequestResponse.sectionId == null : this.sectionId.equals(adRequestResponse.sectionId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        int i = 0;
        int hashCode = 31 * (((((this.adUnitId != null ? this.adUnitId.hashCode() : 0) * 31) + (this.position != null ? this.position.hashCode() : 0)) * 31) + (this.sectionId != null ? this.sectionId.hashCode() : 0));
        if (this.adListener != null) {
            i = this.adListener.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("AdRequestBean{  adUnitId=");
        stringBuilder.append(this.adUnitId);
        stringBuilder.append(", position=");
        stringBuilder.append(this.position);
        stringBuilder.append(", sectionId=");
        stringBuilder.append(this.sectionId);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void dispatchResponse(bl blVar) {
        if (this.adListener != null) {
            this.colombiaAdRequest = (ColombiaAdRequest) blVar;
            if (this.response == null || this.response.getException() != null) {
                b.a(blVar, this.adListener, this.response.getException());
            } else if (!this.response.isSuccessful()) {
                b.a(this.colombiaAdRequest, this.adListener, new Exception(ErrorCode.ADE_ERROR.toString()));
                Log.e(i.f, "request failed to load Ads.");
            } else if (this.response.getMediationNetworkItem().isClientSide()) {
                getNetworkAds(this.colombiaAdRequest, this.requestCode, this.response.getMediationNetworkItem(), this.response.getAdImpressionUrl(), this.adListener);
            } else {
                NativeItem nativeItem;
                if (this.response.getPaidItems() != null && this.response.getPaidItems().size() > 0) {
                    for (Item item : this.response.getPaidItems()) {
                        nativeItem = (NativeItem) item;
                        nativeItem.setAdListener(this.adListener);
                        nativeItem.setItemResponse(this.response);
                    }
                }
                if (this.response.getOrganicItems() != null && this.response.getOrganicItems().size() > 0) {
                    for (Item item2 : this.response.getOrganicItems()) {
                        nativeItem = (NativeItem) item2;
                        nativeItem.setAdListener(this.adListener);
                        nativeItem.setItemResponse(this.response);
                    }
                }
                Item item3 = null;
                if (this.response.getPaidItems() != null && this.response.getPaidItems().size() > 0) {
                    item3 = (Item) this.response.getPaidItems().get(0);
                } else if (this.response.getOrganicItems() != null && this.response.getOrganicItems().size() > 0) {
                    item3 = (Item) this.response.getOrganicItems().get(0);
                }
                if (item3.getItemType() == ITEM_TYPE.VIDEO || item3.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE || item3.getItemType() == ITEM_TYPE.AUDIO || item3.getItemType() == ITEM_TYPE.AUDIO_BANNER || item3.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
                    checkForValidMediaAds(item3);
                } else if (item3.getItemType() == ITEM_TYPE.BANNER) {
                    NativeItem nativeItem2 = (NativeItem) item3;
                    a aVar = new a();
                    aVar.a(new h(this, nativeItem2), nativeItem2.getImageUrl());
                    aVar.b = new i(this);
                    try {
                        aVar.a();
                    } catch (Exception unused) {
                        onItemRequestFailedOnMainThread(new Exception("Error : failed to download media files"));
                    }
                } else {
                    b.a(this.colombiaAdRequest, this.adListener, this.response);
                }
            }
            this.isLoading = false;
        }
    }

    private void onItemRequestFailedOnMainThread(Exception exception) {
        new Handler(Looper.getMainLooper()).post(new j(this, exception));
    }

    private void checkForValidMediaAds(Item item) {
        NativeItem nativeItem = (NativeItem) item;
        MediaSource mediaSrcMode = nativeItem.getMediaSrcMode();
        String mediaSrc = nativeItem.getMediaSrc();
        this.vastHelper = new VASTHelper(item);
        this.vastHelper.setCallbackView(this);
        if (item.getItemType() == ITEM_TYPE.VIDEO && VERSION.SDK_INT < 14) {
            bl blVar = this.colombiaAdRequest;
            AdListener adListener = this.adListener;
            StringBuilder stringBuilder = new StringBuilder("OS version:");
            stringBuilder.append(VERSION.SDK_INT);
            stringBuilder.append(" not supported for video ads.");
            b.a(blVar, adListener, new Exception(stringBuilder.toString()));
        } else if (mediaSrcMode == MediaSource.VAST_URL || mediaSrcMode == MediaSource.VPAID_URL) {
            this.vastHelper.getXML(null, mediaSrc);
        } else if (mediaSrcMode == MediaSource.VAST_XML) {
            this.vastHelper.getVastContent(null, mediaSrc);
        } else if (item.getItemType() == ITEM_TYPE.VIDEO) {
            b.a(this.colombiaAdRequest, this.adListener, this.response);
        } else {
            b.a(this.colombiaAdRequest, this.adListener, new Exception("Invalid ad or wrong source type for Media Ad"));
        }
    }

    public void setResponse(ItemResponse itemResponse) {
        this.response = itemResponse;
        if (itemResponse != null) {
            this.response.setRequestCode(getRequestCode());
        }
    }

    public ItemResponse getResponse() {
        return this.response;
    }

    public void setRequestCode(String str) {
        this.requestCode = str;
    }

    public String getRequestCode() {
        return this.requestCode;
    }

    private void getNetworkAds(bl blVar, String str, MediationNetworkItem mediationNetworkItem, String str2, AdListener adListener) {
        new Handler(Looper.getMainLooper()).post(new k(this, mediationNetworkItem, blVar, str, str2, adListener));
    }

    public void onParsingComplete() {
        if (this.vastHelper == null || this.vastHelper.getMediaFileUrl() == null) {
            onItemRequestFailedOnMainThread(new Exception(ErrorCode.VAST_PARSE_ERROR.toString()));
        } else if (this.vastHelper.getItem().getItemType() == ITEM_TYPE.VIDEO || !(isPreloadImage() || isPreloadAudio() || isPreloadVideo())) {
            b.a(this.colombiaAdRequest, this.adListener, this.response);
        } else {
            ArrayList arrayList = new ArrayList();
            com.til.colombia.android.commons.a.a.a(com.til.colombia.android.internal.a.a());
            ITEM_TYPE itemType = this.vastHelper.getItem().getItemType();
            if (isPreloadVideo() && (itemType == ITEM_TYPE.VIDEO_INCENTIVE || itemType == ITEM_TYPE.INTERSTITIAL_VIDEO)) {
                arrayList.add(this.vastHelper.getMediaFileUrl());
            }
            if (isPreloadAudio()) {
                if (itemType == ITEM_TYPE.AUDIO || itemType == ITEM_TYPE.AUDIO_BANNER) {
                    arrayList.add(this.vastHelper.getMediaFileUrl());
                } else if (itemType == ITEM_TYPE.VIDEO_INCENTIVE) {
                    arrayList.add(this.vastHelper.getPreCompanionAudioSrc());
                    arrayList.add(this.vastHelper.getPostCompanionAudioSrc());
                }
            }
            if (isPreloadImage()) {
                if (itemType == ITEM_TYPE.VIDEO_INCENTIVE) {
                    arrayList.add(this.vastHelper.getPreCompanionImageSrc());
                    arrayList.add(this.vastHelper.getPostCompanionImageSrc());
                }
                VastCompanionAdConfig bestCompanionAdConfig = this.vastHelper.getBestCompanionAdConfig(this.colombiaAdRequest.getMediaAdSize());
                if (itemType == ITEM_TYPE.AUDIO_BANNER && (bestCompanionAdConfig == null || bestCompanionAdConfig.getVastResource() == null)) {
                    onItemRequestFailedOnMainThread(new Exception("Error : no companion found for audio-banner ad"));
                    return;
                } else if (bestCompanionAdConfig == null || bestCompanionAdConfig.getVastResource() == null) {
                    if (itemType == ITEM_TYPE.AUDIO) {
                        arrayList.add(this.vastHelper.getItem().getImageUrl());
                    }
                } else if (bestCompanionAdConfig.getVastResource().getType() == Type.STATIC_RESOURCE && bestCompanionAdConfig.getVastResource().getCreativeType() == CreativeType.IMAGE) {
                    arrayList.add(bestCompanionAdConfig.getVastResource().getResource());
                }
            }
            String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            if (strArr == null && strArr.length == 0) {
                b.a(this.colombiaAdRequest, this.adListener, this.response);
            } else {
                new g(new l(this)).execute(strArr);
            }
        }
    }

    public void onParsingError() {
        b.a(this.colombiaAdRequest, this.adListener, new Exception(ErrorCode.VAST_PARSE_ERROR.toString()));
    }

    private boolean isPreloadImage() {
        if ((this.vastHelper == null || !this.vastHelper.isDisablePreCache()) && this.colombiaAdRequest.getMediaCacheFlags() != null && (this.colombiaAdRequest.getMediaCacheFlags().contains(MEDIA_CACHE_FLAG.ALL) || this.colombiaAdRequest.getMediaCacheFlags().contains(MEDIA_CACHE_FLAG.IMAGE))) {
            return true;
        }
        return false;
    }

    private boolean isPreloadAudio() {
        if ((this.vastHelper == null || !this.vastHelper.isDisablePreCache()) && this.colombiaAdRequest.getMediaCacheFlags() != null && (this.colombiaAdRequest.getMediaCacheFlags().contains(MEDIA_CACHE_FLAG.ALL) || this.colombiaAdRequest.getMediaCacheFlags().contains(MEDIA_CACHE_FLAG.AUDIO))) {
            return true;
        }
        return false;
    }

    private boolean isPreloadVideo() {
        if ((this.vastHelper == null || !this.vastHelper.isDisablePreCache()) && this.colombiaAdRequest.getMediaCacheFlags() != null && (this.colombiaAdRequest.getMediaCacheFlags().contains(MEDIA_CACHE_FLAG.ALL) || this.colombiaAdRequest.getMediaCacheFlags().contains(MEDIA_CACHE_FLAG.VIDEO))) {
            return true;
        }
        return false;
    }
}
