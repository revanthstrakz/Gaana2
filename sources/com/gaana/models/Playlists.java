package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Tracks.Track.Tags;
import com.gaana.models.Tracks.Track.TopArtists;
import com.gaana.models.Tracks.Track.TopLanguage;
import com.google.gson.annotations.SerializedName;
import com.managers.DownloadManager.DownloadStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class Playlists extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("playlist")
    private ArrayList<Playlist> arrListPlaylist;
    @SerializedName("hp_title")
    private String title;
    @SerializedName("unfavourite")
    private String unfavorite;

    public static class Playlist extends BusinessObject implements Downloadable {
        private static final long serialVersionUID = 1;
        @SerializedName("ad_code")
        private String ad_code;
        @SerializedName("ads_slugs")
        private CustomAdParams ads_slugs;
        private String artwork;
        @SerializedName("autoDisplayHome")
        private boolean autoDisplayHome = false;
        @SerializedName("automated")
        private String automated = "0";
        @SerializedName("bottom_banner_off")
        private int bottom_banner_off;
        private String createdby;
        @SerializedName("user_id")
        private String creatorUserId;
        @SerializedName("delete_mode")
        private String deleteMode;
        protected DownloadStatus downloadStatus;
        private long downloadTime;
        private String favorite_count = "0";
        private boolean hasChanged;
        @SerializedName("is_mini_playlist")
        private String isMiniPlaylist;
        protected Boolean isOffline = Boolean.valueOf(false);
        protected Boolean isSelected = Boolean.valueOf(false);
        @SerializedName("is_sponsored")
        private String is_sponsored;
        @SerializedName("language")
        private String language;
        private Date last_modifed_date = null;
        @SerializedName("lpid")
        private String localPlaylistId;
        private int notify_status;
        private long offlinePlaylistId;
        @SerializedName("parental_warning")
        private int parentalWarning;
        @SerializedName("party_source")
        private String partySource;
        @SerializedName("playlist_id")
        private String playlistId;
        private PlaylistSourceType playlistSourceType = PlaylistSourceType.NORMAL;
        private String playlist_detailed_description;
        private String playlist_type;
        @SerializedName("popularity")
        private String popularity;
        private String premiumContent = null;
        @SerializedName("rating")
        private String rating;
        @SerializedName("sapid")
        private String sapID;
        private String seokey;
        private int syncStatus = -3;
        private long syncTime;
        private ArrayList<Tags> tags;
        private String title;
        private ArrayList<TopArtists> topArtists;
        @SerializedName("top_languages")
        private ArrayList<TopLanguage> topLanguages;
        private String trackids;
        @SerializedName("vid_list")
        private String[] videoList;

        public enum PlaylistSourceType {
            NORMAL,
            HOURLY_PLAYLIST
        }

        public String getAutomated() {
            return this.automated;
        }

        public void setAutomated(String str) {
            this.automated = str;
        }

        public String getPartySource() {
            return this.partySource;
        }

        public void setPartySource(String str) {
            this.partySource = str;
        }

        public boolean isParentalWarningEnabled() {
            return this.parentalWarning == 1;
        }

        public HashMap getVideoListMap() {
            if (this.videoList == null || this.videoList.length <= 0) {
                return null;
            }
            HashMap hashMap = new HashMap();
            for (Object put : this.videoList) {
                hashMap.put(put, Boolean.valueOf(true));
            }
            return hashMap;
        }

        public void setAutoDisplayHome(boolean z) {
            this.autoDisplayHome = z;
        }

        public boolean isBottomBannerOff() {
            return this.bottom_banner_off == 1;
        }

        public boolean getAutoDisplayHome() {
            return this.autoDisplayHome;
        }

        public void setSyncTime(long j) {
            this.syncTime = j;
        }

        public long getSyncTime() {
            return this.syncTime;
        }

        public void setDownloadTime(long j) {
            this.downloadTime = j;
        }

        public long getDownloadTime() {
            return this.downloadTime;
        }

        public void setIsSelected(Boolean bool) {
            this.isSelected = bool;
        }

        public void setIs_sponsored(String str) {
            this.is_sponsored = str;
        }

        public String getIsSponsored() {
            return this.is_sponsored;
        }

        public PlaylistSourceType getPlaylistSourceType() {
            return this.playlistSourceType;
        }

        public void setPlaylistSource(PlaylistSourceType playlistSourceType) {
            this.playlistSourceType = playlistSourceType;
        }

        public Boolean isSelected() {
            if (this.isSelected == null) {
                this.isSelected = Boolean.valueOf(false);
            }
            return this.isSelected;
        }

        public void setRating(String str) {
            this.rating = str;
        }

        public String getRating() {
            return this.rating;
        }

        public boolean isPlaylistChanged() {
            return this.hasChanged;
        }

        public void setChanged(boolean z) {
            this.hasChanged = z;
        }

        public String getBusinessObjId() {
            return this.playlistId;
        }

        public void setBusinessObjId(String str) {
            this.playlistId = str;
            super.setBusinessObjId(str);
        }

        public String getSeokey() {
            return this.seokey;
        }

        public void setSeokey(String str) {
            this.seokey = str;
        }

        public String getLanguage() {
            return this.language;
        }

        public void setLanguage(String str) {
            this.language = str;
        }

        public String getName() {
            return Constants.a(this.title, this.language);
        }

        public String getRawName() {
            return this.title;
        }

        public String getEnglishName() {
            return Constants.a(this.title);
        }

        public void setName(String str) {
            this.name = str;
            this.title = str;
        }

        public String getCreatorUserId() {
            return this.creatorUserId;
        }

        public String getPlaylistDetailedDescription() {
            return Constants.a(this.playlist_detailed_description, this.language);
        }

        public String getTrackids() {
            return this.trackids;
        }

        public void setTrackIds(String str) {
            this.trackids = str;
        }

        public String getCreatedby() {
            return Constants.b(this.createdby);
        }

        public void setCreatedby(String str) {
            this.createdby = str;
        }

        public void setCreatedbyUserId(String str) {
            this.creatorUserId = str;
        }

        public void setPlaylistDetailedDescription(String str) {
            this.playlist_detailed_description = str;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.artwork;
            }
            return getAtw();
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }

        public String getPlaylistType() {
            if (this.playlist_type == null || this.playlist_type.length() == 0) {
                this.playlist_type = "playlist";
            }
            return this.playlist_type;
        }

        public Boolean isOffline() {
            return this.isOffline;
        }

        public void setOfflineStatus(Boolean bool) {
            this.isOffline = bool;
        }

        public DownloadStatus getDownloadStatus() {
            return this.downloadStatus;
        }

        public void setDownloadStatus(DownloadStatus downloadStatus) {
            this.downloadStatus = downloadStatus;
        }

        public void setPlaylistId(String str) {
            this.playlistId = str;
        }

        public String getFavoriteCount() {
            return this.favorite_count;
        }

        public void setFavoriteCount(String str) {
            this.favorite_count = str;
        }

        public void setPremiumContent(String str) {
            this.premiumContent = str;
        }

        public String getPremiumContent() {
            return this.premiumContent;
        }

        public Date getLastModifiedDate() {
            return this.last_modifed_date;
        }

        public void setLastModifiedDate(Date date) {
            this.last_modifed_date = date;
        }

        public void setParentalWarning(int i) {
            this.parentalWarning = i;
        }

        public ArrayList<Tags> getTags() {
            return this.tags;
        }

        public void setTags(ArrayList<Tags> arrayList) {
            this.tags = arrayList;
        }

        public ArrayList<TopArtists> getTopArtists() {
            return this.topArtists;
        }

        public void setTopArtists(ArrayList<TopArtists> arrayList) {
            this.topArtists = arrayList;
        }

        public ArrayList<TopLanguage> getTopLanguages() {
            return this.topLanguages;
        }

        public void setTopLanguages(ArrayList<TopLanguage> arrayList) {
            this.topLanguages = arrayList;
        }

        public long getOfflinePlaylistId() {
            return this.offlinePlaylistId;
        }

        public void setOfflinePlaylistId(long j) {
            this.offlinePlaylistId = j;
        }

        public String getLocalPlaylistId() {
            return this.localPlaylistId;
        }

        public void setLocalPlaylistId(String str) {
            this.localPlaylistId = str;
        }

        public int getSyncStatus() {
            return this.syncStatus;
        }

        public void setSyncStatus(int i) {
            this.syncStatus = i;
        }

        public String getChannelPageAdCode() {
            return this.ad_code;
        }

        public void setChannelPageAdCode(String str) {
            this.ad_code = str;
        }

        public void setSapID(String str) {
            this.sapID = str;
        }

        public boolean isGaanaSpecial() {
            return !TextUtils.isEmpty(this.sapID) && this.sapID.equalsIgnoreCase("appcast");
        }

        public void setNotifyStatus(int i) {
            this.notify_status = i;
        }

        public boolean isNotifyStatus() {
            return this.notify_status == 1;
        }

        public long getPopularity() {
            if (this.popularity == null || !this.popularity.contains("~")) {
                return 0;
            }
            return Long.valueOf(this.popularity.substring(this.popularity.indexOf(126) + 1)).longValue();
        }

        public String getIsMiniPlaylist() {
            return this.isMiniPlaylist;
        }

        public String getDeleteMode() {
            return this.deleteMode;
        }

        public boolean isUserCreatedPlaylist() {
            if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                String str = "";
                if (GaanaApplication.getInstance().getCurrentUser().getUserProfile() != null) {
                    str = GaanaApplication.getInstance().getCurrentUser().getUserProfile().getUserId();
                }
                if (this.creatorUserId != null && this.creatorUserId.equalsIgnoreCase(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public ArrayList<Playlist> getArrListBusinessObj() {
        return this.arrListPlaylist;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListPlaylist = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof Playlist) {
                this.arrListPlaylist.add((Playlist) businessObject);
            }
        }
    }

    public String getTitle() {
        return Constants.b(this.title);
    }

    public String getEnglishName() {
        return Constants.a(this.title);
    }

    public String getRawName() {
        return this.title;
    }

    public String getUnfavorite() {
        return this.unfavorite;
    }
}
