package com.til.colombia.android.service;

import android.location.Location;
import com.til.colombia.android.commons.MEDIA_CACHE_FLAG;
import com.til.colombia.android.service.ColombiaAdManager.GENDER;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;

public interface bl {
    boolean downloadIcon();

    boolean downloadImage();

    ColombiaAdManager getAdManager();

    Set<AdRequestResponse> getAdRequests();

    AdSize getAdSize();

    Date getBirthDay();

    String getCId();

    HashMap<String, String> getCustomAudience();

    GENDER getGender();

    Location getLocation();

    ArrayList<AdSize> getMediaAdSize();

    EnumSet<MEDIA_CACHE_FLAG> getMediaCacheFlags();

    String getPageNo();

    String getReferer();

    Integer getResponseFormat();

    boolean getSavers();

    boolean isRecordImpressionEnabled();

    boolean isVideoAutoPlay();

    boolean isWebViewEnabled();
}
