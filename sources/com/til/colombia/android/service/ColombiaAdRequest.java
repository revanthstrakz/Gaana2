package com.til.colombia.android.service;

import android.location.Location;
import com.til.colombia.android.commons.MEDIA_CACHE_FLAG;
import com.til.colombia.android.internal.h;
import com.til.colombia.android.service.ColombiaAdManager.DFP_ITEM_TYPE;
import com.til.colombia.android.service.ColombiaAdManager.GENDER;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public final class ColombiaAdRequest implements bl {
    private Builder builder;

    public static final class Builder {
        private Date birthday;
        private String cId;
        private LinkedHashMap<String, String> customAudience;
        private boolean downloadIcon;
        private boolean downloadImage;
        private GENDER gender = GENDER.UNKNOWN;
        private AdSize imageDimension;
        private boolean isVideoAutoPlay = true;
        private Location location;
        private ColombiaAdManager manager;
        private EnumSet<MEDIA_CACHE_FLAG> mediaCacheFlags;
        private ArrayList<AdSize> mediaImageDimen;
        private String pageNo;
        private boolean recordImpression;
        private String referer;
        private Set<AdRequestResponse> requestResponseSet;
        private boolean savers;
        private boolean webViewEnabled;

        @Deprecated
        public final Builder enableRecordManualImpression(boolean z) {
            return this;
        }

        @Deprecated
        public final Builder enabledGoogleAdFormats(DFP_ITEM_TYPE[] dfp_item_typeArr) {
            return this;
        }

        @Deprecated
        public final Builder returnItemUrl(boolean z) {
            return this;
        }

        public final Builder addSavers(boolean z) {
            this.savers = z;
            return this;
        }

        public final Builder addPageNo(int i) {
            this.pageNo = Integer.toString(i);
            return this;
        }

        public final Builder addCategoryId(String str) {
            this.cId = str;
            return this;
        }

        /* Access modifiers changed, original: protected|final */
        public final boolean getSavers() {
            return this.savers;
        }

        /* Access modifiers changed, original: protected|final */
        public final String getPageNo() {
            return this.pageNo;
        }

        /* Access modifiers changed, original: protected|final */
        public final String getCId() {
            return this.cId;
        }

        public Builder(ColombiaAdManager colombiaAdManager) {
            if (colombiaAdManager == null) {
                throw new IllegalArgumentException("ColombiaAdManager can not be null");
            }
            this.manager = colombiaAdManager;
        }

        /* Access modifiers changed, original: protected|final */
        public final ColombiaAdManager getAdManager() {
            return this.manager;
        }

        public final ColombiaAdRequest build() {
            return new ColombiaAdRequest(this);
        }

        /* Access modifiers changed, original: protected|final */
        public final Set<AdRequestResponse> getAdRequests() {
            if (this.requestResponseSet == null) {
                return null;
            }
            for (AdRequestResponse adRequestResponse : this.requestResponseSet) {
                adRequestResponse.setLoadIcon(this.downloadIcon);
                adRequestResponse.setLoadImage(this.downloadImage);
            }
            return Collections.unmodifiableSet(this.requestResponseSet);
        }

        @Deprecated
        public final Builder addRequest(Long l, int i, String str, ItemListener itemListener) {
            AdRequestResponse validateAndGetAdRequest = validateAndGetAdRequest(l, Integer.valueOf(i), str, new af(itemListener));
            if (validateAndGetAdRequest != null) {
                if (this.requestResponseSet == null) {
                    this.requestResponseSet = new LinkedHashSet();
                }
                this.requestResponseSet.add(validateAndGetAdRequest);
            }
            return this;
        }

        @Deprecated
        public final Builder addRequest(Long l, int i, String str, AdListener adListener) {
            AdRequestResponse validateAndGetAdRequest = validateAndGetAdRequest(l, Integer.valueOf(i), str, adListener);
            if (validateAndGetAdRequest != null) {
                if (this.requestResponseSet == null) {
                    this.requestResponseSet = new LinkedHashSet();
                }
                this.requestResponseSet.add(validateAndGetAdRequest);
            }
            return this;
        }

        public final Builder addRequest(PublisherAdRequest publisherAdRequest) {
            AdRequestResponse validateAndGetAdRequest = validateAndGetAdRequest(publisherAdRequest);
            if (validateAndGetAdRequest != null) {
                if (this.requestResponseSet == null) {
                    this.requestResponseSet = new LinkedHashSet();
                }
                this.requestResponseSet.add(validateAndGetAdRequest);
            }
            return this;
        }

        /* Access modifiers changed, original: protected|final */
        public final String getReferer() {
            return this.referer;
        }

        public final Builder addReferer(String str) {
            this.referer = str;
            return this;
        }

        public final Builder addBirthDay(Date date) {
            this.birthday = date;
            return this;
        }

        /* Access modifiers changed, original: protected|final */
        public final Date getBirthDay() {
            return this.birthday;
        }

        public final Builder addCustomAudience(String str, String str2) {
            Object str22;
            if (this.customAudience == null) {
                this.customAudience = new LinkedHashMap();
            }
            if (str != null && this.customAudience.containsKey(str)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append((String) this.customAudience.get(str));
                stringBuilder.append(",");
                stringBuilder.append(str22);
                str22 = stringBuilder.toString();
            }
            this.customAudience.put(str, str22);
            return this;
        }

        public final Builder addVideoAutoPlay(boolean z) {
            this.isVideoAutoPlay = z;
            return this;
        }

        public final Builder addGender(GENDER gender) {
            this.gender = gender;
            return this;
        }

        /* Access modifiers changed, original: protected|final */
        public final GENDER getGender() {
            return this.gender;
        }

        /* Access modifiers changed, original: protected|final */
        public final Integer getResponseFormat() {
            return h.J;
        }

        public final Builder addLocation(Location location) {
            this.location = location;
            return this;
        }

        public final Builder addAdSize(int i, int i2) {
            if (i <= 0 || i2 <= 0) {
                return this;
            }
            this.imageDimension = new AdSize(i, i2);
            return this;
        }

        public final Builder addMediaAdSize(int i, int i2) {
            if (this.mediaImageDimen == null) {
                this.mediaImageDimen = new ArrayList();
            }
            this.mediaImageDimen.add(new AdSize(i, i2));
            return this;
        }

        public final AdSize getAdSize() {
            return this.imageDimension;
        }

        @Deprecated
        public final Builder downloadImageBitmap(boolean z) {
            this.downloadImage = z;
            return this;
        }

        @Deprecated
        public final Builder downloadIconBitmap(boolean z) {
            this.downloadIcon = z;
            return this;
        }

        public final Builder addMediaCacheFlags(MEDIA_CACHE_FLAG[] media_cache_flagArr) {
            if (media_cache_flagArr != null && this.mediaCacheFlags == null) {
                this.mediaCacheFlags = EnumSet.copyOf(Arrays.asList(media_cache_flagArr));
            }
            return this;
        }

        public final EnumSet<MEDIA_CACHE_FLAG> getMediaCacheFlags() {
            return this.mediaCacheFlags;
        }

        @Deprecated
        public final boolean isRecordImpressionEnabled() {
            return this.recordImpression;
        }

        /* Access modifiers changed, original: protected|final */
        public final Location getLocation() {
            return this.location;
        }

        private AdRequestResponse validateAndGetAdRequest(Long l, Integer num, String str, AdListener adListener) {
            if (l == null || num == null || str == null || adListener == null) {
                return null;
            }
            AdRequestResponse adRequestResponse = new AdRequestResponse();
            adRequestResponse.setAdUnitId(l);
            adRequestResponse.setPosition(num);
            adRequestResponse.setSectionId(str);
            adRequestResponse.setAdListener(adListener);
            return adRequestResponse;
        }

        private AdRequestResponse validateAndGetAdRequest(PublisherAdRequest publisherAdRequest) {
            if (publisherAdRequest.getAdUnitId() == null || publisherAdRequest.getPositionId() <= 0 || publisherAdRequest.getSectionId() == null || publisherAdRequest.getAdListener() == null) {
                return null;
            }
            AdRequestResponse adRequestResponse = new AdRequestResponse();
            adRequestResponse.setAdUnitId(publisherAdRequest.getAdUnitId());
            adRequestResponse.setPosition(Integer.valueOf(publisherAdRequest.getPositionId()));
            adRequestResponse.setSectionId(publisherAdRequest.getSectionId());
            adRequestResponse.setAdListener(publisherAdRequest.getAdListener());
            adRequestResponse.setRequestCode(publisherAdRequest.getRequestCode());
            return adRequestResponse;
        }
    }

    @Deprecated
    public final boolean isWebViewEnabled() {
        return false;
    }

    private ColombiaAdRequest(Builder builder) {
        this.builder = builder;
    }

    public final Set<AdRequestResponse> getAdRequests() {
        return this.builder.getAdRequests();
    }

    public final String getReferer() {
        return this.builder.getReferer();
    }

    public final Integer getResponseFormat() {
        return this.builder.getResponseFormat();
    }

    public final Date getBirthDay() {
        return this.builder.getBirthDay();
    }

    public final ColombiaAdManager getAdManager() {
        return this.builder.getAdManager();
    }

    public final GENDER getGender() {
        return this.builder.getGender();
    }

    public final Location getLocation() {
        return this.builder.getLocation();
    }

    public final boolean downloadImage() {
        return this.builder.downloadImage;
    }

    public final boolean downloadIcon() {
        return this.builder.downloadIcon;
    }

    @Deprecated
    public final boolean isRecordImpressionEnabled() {
        return this.builder.isRecordImpressionEnabled();
    }

    public final boolean isVideoAutoPlay() {
        return this.builder.isVideoAutoPlay;
    }

    public final EnumSet<MEDIA_CACHE_FLAG> getMediaCacheFlags() {
        return this.builder.getMediaCacheFlags();
    }

    public final AdSize getAdSize() {
        return this.builder.imageDimension;
    }

    public final ArrayList<AdSize> getMediaAdSize() {
        return this.builder.mediaImageDimen;
    }

    public final boolean getSavers() {
        return this.builder.savers;
    }

    public final String getPageNo() {
        return this.builder.pageNo;
    }

    public final String getCId() {
        return this.builder.cId;
    }

    public final HashMap<String, String> getCustomAudience() {
        return this.builder.customAudience;
    }
}
