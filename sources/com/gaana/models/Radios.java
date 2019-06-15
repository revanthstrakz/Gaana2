package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;

public class Radios extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("radios")
    private ArrayList<Radio> arrListRadio;
    @SerializedName("unfavourite")
    private String unfavorite;

    public static class Radio extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("ad_code")
        private String ad_code;
        @SerializedName("ads_position")
        private int ad_compaign_position;
        @SerializedName("ads_slugs")
        private CustomAdParams ads_slugs;
        @SerializedName("image")
        private String artWork;
        @SerializedName("bottom_banner_off")
        private int bottom_banner_off;
        @SerializedName("favorite_count")
        private String favorite_count;
        @SerializedName("gaana_ad")
        private int gaana_ad;
        @SerializedName("is_sponsored")
        private String is_sponsored;
        private String language;
        @SerializedName("poll_api")
        private String poll_api;
        @SerializedName("poll_time")
        private String poll_time;
        @SerializedName("id")
        private String radioId;
        @SerializedName("radio_theme")
        private String radioTheme;
        @SerializedName("seokey")
        private String seokey;
        @SerializedName("stream_url")
        private String stream_url;
        @SerializedName("type")
        private String type;

        public int getAdCompaignPosition() {
            return this.ad_compaign_position;
        }

        public int setAdCompaignPosition(int i) {
            this.ad_compaign_position = i;
            return i;
        }

        public boolean isBottomBannerOff() {
            return this.bottom_banner_off == 1;
        }

        public String getLanguage() {
            return this.language;
        }

        public void setLanguage(String str) {
            this.language = str;
        }

        public String getStreamUrl() {
            return this.stream_url;
        }

        public void setStreamUrl(String str) {
            this.stream_url = str;
        }

        public String getFavorite_count() {
            return this.favorite_count;
        }

        public void setFavoriteCount(String str) {
            this.favorite_count = str;
        }

        public String getChannelPageAdCode() {
            return this.ad_code;
        }

        public void setChannelPageAdCode(String str) {
            this.ad_code = str;
        }

        public int getGaana_ad() {
            return this.gaana_ad;
        }

        public void setGaana_ad(int i) {
            this.gaana_ad = i;
        }

        public String getPoll_api() {
            return this.poll_api;
        }

        public void setPoll_api(String str) {
            this.poll_api = str;
        }

        public String getPoll_time() {
            return this.poll_time;
        }

        public void setPoll_time(String str) {
            this.poll_time = str;
        }

        public String getSeokey() {
            return this.seokey;
        }

        public void setSeokey(String str) {
            this.seokey = str;
        }

        public String getBusinessObjId() {
            return this.radioId;
        }

        public void setBusinessObjId(String str) {
            this.radioId = str;
        }

        public String getName() {
            return Constants.a(this.radioTheme, this.language);
        }

        public String getRawName() {
            return this.radioTheme;
        }

        public String getEnglishName() {
            return Constants.a(this.radioTheme);
        }

        public void setName(String str) {
            this.radioTheme = str;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.artWork;
            }
            return getAtw();
        }

        public String getAtw() {
            String atw = super.getAtw();
            if (TextUtils.isEmpty(atw)) {
                return this.artWork;
            }
            return atw;
        }

        public void setArtwork(String str) {
            this.artWork = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getIsSponsored() {
            return this.is_sponsored;
        }

        public void setIs_sponsored(String str) {
            this.is_sponsored = str;
        }
    }

    public ArrayList<Radio> getArrListBusinessObj() {
        return this.arrListRadio;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListRadio = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof Radio) {
                this.arrListRadio.add((Radio) businessObject);
            }
        }
    }

    public String getUnfavorite() {
        return this.unfavorite;
    }
}
