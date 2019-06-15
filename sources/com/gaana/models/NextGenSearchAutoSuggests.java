package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class NextGenSearchAutoSuggests extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("action")
    private int action;
    @SerializedName("algo")
    private String algo;
    @SerializedName("gr")
    private ArrayList<GroupItem> groupItems;
    @SerializedName("dmtxt")
    private String interventionText;
    @SerializedName("si")
    private int searchIntervention;
    @SerializedName("srId")
    private String searchReqId;
    @SerializedName("speechType")
    private String speechType;
    @SerializedName("speechtxt")
    private String speechtxt;
    @SerializedName("ltxt")
    private String subText;
    @SerializedName("topFacet")
    private String topFacet;
    @SerializedName("q")
    private String transliteratedHeader;
    @SerializedName("voicetxt")
    private String voicetxt;

    public static class AutoComplete extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("aw")
        private String artwork;
        @SerializedName("ty")
        private String autoType;
        @SerializedName("et")
        private String expiryTime;
        @SerializedName("hf")
        private String isHighlighted;
        private boolean isRecentSearch = false;
        private boolean isRecommendedSearch = false;
        private Boolean isSelected = Boolean.valueOf(false);
        @SerializedName("iid")
        private int itemId;
        @SerializedName("language")
        private String language;
        @SerializedName("radio_type")
        private String my_radio_type;
        @SerializedName("seo")
        private String occasionUrl;
        @SerializedName("pw")
        private String parentalWarning;
        @SerializedName("play_ct")
        @Expose
        private String playCount;
        private int position;
        @SerializedName("sty")
        private String radioType;
        @SerializedName("scoreF")
        @Expose
        private Double scoreF;
        private String sectionType = "";
        @SerializedName("sti")
        private String subtitle;
        @SerializedName("ti")
        private String title;
        @SerializedName("tr")
        private String topResult;
        private String type;
        @SerializedName("urls")
        private StreamUrls urls;
        @SerializedName("oty")
        @Expose
        private String videotype;
        @SerializedName("vty")
        @Expose
        private Boolean vty;
        @SerializedName("vurl")
        @Expose
        private String vurl;

        public String getLanguage() {
            if (!TextUtils.isEmpty(this.language) && !this.language.contains(",")) {
                return this.language;
            }
            String[] strArr = new String[0];
            if (!TextUtils.isEmpty(this.language)) {
                strArr = this.language.split(",");
            }
            String str = "";
            if (strArr.length > 1) {
                str = strArr[0];
            }
            return str;
        }

        public StreamUrls getStreamUrls() {
            return this.urls;
        }

        public void setStreamUrls(StreamUrls streamUrls) {
            this.urls = streamUrls;
        }

        public String getisHighlighted() {
            return this.isHighlighted;
        }

        public boolean isHighlighted() {
            if (this.isHighlighted == null) {
                return false;
            }
            return this.isHighlighted.equalsIgnoreCase("1");
        }

        public void setisHighlighted(String str) {
            this.isHighlighted = this.isHighlighted;
        }

        public void setIsSelected(Boolean bool) {
            this.isSelected = bool;
        }

        public Boolean isSelected() {
            if (this.isSelected == null) {
                this.isSelected = Boolean.valueOf(false);
            }
            return this.isSelected;
        }

        public String getType() {
            if (TextUtils.isEmpty(this.autoType)) {
                return this.type;
            }
            return this.autoType;
        }

        public void setType(String str) {
            this.type = str;
        }

        public void setSectionType(String str) {
            this.sectionType = str;
        }

        public String getSectionType() {
            return this.sectionType;
        }

        public boolean isLocalMedia() {
            return this.isLocalMedia;
        }

        public void setLocalMedia(boolean z) {
            this.isLocalMedia = z;
        }

        public AutoComplete(String str, String str2, int i, String str3) {
            this.title = str;
            this.itemId = i;
            this.artwork = str3;
            this.subtitle = str2;
        }

        public String getTitle() {
            return Constants.a(this.title, getLanguage());
        }

        public String getEnglishTitle() {
            return Constants.a(this.title);
        }

        public String getRawTitle() {
            return this.title;
        }

        public String getSubtitle() {
            return Constants.a(this.subtitle, getLanguage());
        }

        public String getRawSubtitle() {
            return this.subtitle;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.artwork;
            }
            return getAtw();
        }

        public String getRadioType() {
            return this.radioType;
        }

        public String getBusinessObjectId() {
            return String.valueOf(this.itemId);
        }

        public void setBusinessObjectId(int i) {
            this.itemId = i;
        }

        public String toString() {
            return Constants.a(this.title, getLanguage());
        }

        public boolean isRecentSearch() {
            return this.isRecentSearch;
        }

        public void setRecentSearch(boolean z) {
            this.isRecentSearch = z;
        }

        public void setPosition(int i) {
            this.position = i;
        }

        public int getPosition() {
            return this.position;
        }

        public String getAutoType() {
            return this.autoType;
        }

        public void setAutoType(String str) {
            this.autoType = str;
        }

        public void setIsRecommendedSearch(boolean z) {
            this.isRecommendedSearch = z;
        }

        public boolean isRecommendedSearch() {
            return this.isRecommendedSearch;
        }

        public boolean isParentalWarningEnabled() {
            return this.parentalWarning != null && this.parentalWarning.equals("1");
        }

        public String getOccasionUrl() {
            return this.occasionUrl;
        }

        public boolean isTopResult() {
            return !TextUtils.isEmpty(this.topResult) && this.topResult.equalsIgnoreCase("1");
        }

        public String getVurl() {
            return this.vurl;
        }

        public void setVurl(String str) {
            this.vurl = str;
        }

        public Boolean getVty() {
            return this.vty;
        }

        public void setVty(Boolean bool) {
            this.vty = bool;
        }

        public Double getScoreF() {
            return this.scoreF;
        }

        public void setScoreF(Double d) {
            this.scoreF = d;
        }

        public String getVideoType() {
            return this.videotype;
        }

        public String getPlayCount() {
            return this.playCount;
        }

        public long getExpiryTime() {
            return !TextUtils.isEmpty(this.expiryTime) ? Long.parseLong(this.expiryTime) : -1;
        }

        public void setExpiryTime(String str) {
            this.expiryTime = str;
        }
    }

    public static class GroupItem extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("gd")
        private ArrayList<AutoComplete> autoComplete;
        @SerializedName("stxt")
        private String displayTitle;
        @SerializedName("ty")
        private String type;
        @SerializedName("va")
        private int viewAll;

        public ArrayList<AutoComplete> getAutocomplete() {
            return this.autoComplete;
        }

        public ArrayList<?> getArrListBusinessObj() {
            return this.autoComplete;
        }

        public void setAutocomplete(ArrayList<AutoComplete> arrayList) {
            this.autoComplete = arrayList;
        }

        public boolean isViewAllEnabled() {
            return this.viewAll == 1;
        }

        public void setViewAll(int i) {
            this.viewAll = i;
        }

        public String getdisplayTitle() {
            return Constants.b(this.displayTitle);
        }

        public void setDisplayTitle(String str) {
            this.displayTitle = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public boolean isLocalMedia() {
            return this.isLocalMedia;
        }

        public void setLocalMedia(boolean z) {
            this.isLocalMedia = z;
        }
    }

    public String getTransliteratedHeader() {
        return "";
    }

    public ArrayList<GroupItem> getGroupItems() {
        return this.groupItems;
    }

    public ArrayList<?> getArrListBusinessObj() {
        return this.groupItems;
    }

    public void setGroupItems(ArrayList<GroupItem> arrayList) {
        this.groupItems = arrayList;
    }

    public String getTopFacets() {
        return this.topFacet;
    }

    public int getSearchIntervention() {
        return this.searchIntervention;
    }

    public String getInterventionText() {
        return Constants.b(this.interventionText);
    }

    public String getSearchReqId() {
        return this.searchReqId;
    }

    public String getSubText() {
        return Constants.b(this.subText);
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int i) {
        this.action = i;
    }

    public String getAlgo() {
        return this.algo;
    }

    public String getVoiceTxt() {
        return this.voicetxt;
    }

    public String getSpeechText() {
        return this.speechtxt;
    }

    public String getSpeechType() {
        return this.speechType;
    }
}
