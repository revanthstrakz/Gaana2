package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class UserAppSettings extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("autoDownloadOverWifi")
    private String autoDownloadOverWifi;
    @SerializedName("auto_queue")
    private String autoQueue;
    @SerializedName("auto_sync")
    private String autoSync;
    @SerializedName("can_follow")
    private String canFollow;
    @SerializedName("cross_fade")
    private String crossFade;
    @SerializedName("data_save_mode")
    private String dataSaveMode;
    @SerializedName("display_language")
    private String displayLanguage;
    @SerializedName("download_over")
    private String downloadOver;
    @SerializedName("download_over_2G3G")
    private String downloadOver2G3G;
    @SerializedName("download_quality")
    private String downloadQuality;
    @SerializedName("gap_less_playback")
    private String gapLessPlayback;
    @SerializedName("is_private")
    private String isPrivate;
    @SerializedName("mark_fav_playlist")
    private String markFavPlaylist;
    @SerializedName("music_recommendations")
    private String musicRecommendations;
    @SerializedName("parental_warning")
    private String parentalWarning;
    @SerializedName("recommend_song_queue")
    private String recommendSongQueue;
    @SerializedName("schedule_downloads")
    private String scheduleDownloads;
    @SerializedName("sync_over_2G3G")
    private String syncOver2G3G;
    @SerializedName("user_id")
    private String userId;

    public int getIsPrivate() {
        if (this.isPrivate == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.isPrivate) != 1) {
            i = 0;
        }
        return i;
    }

    public void setIsPrivate(String str) {
        this.isPrivate = str;
    }

    public int getGapLessPlayback() {
        if (this.gapLessPlayback == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.gapLessPlayback) != 1) {
            i = 0;
        }
        return i;
    }

    public void setGapLessPlayback(String str) {
        this.gapLessPlayback = str;
    }

    public int getCrossFade() {
        if (this.crossFade == null) {
            return -1;
        }
        int parseInt = Integer.parseInt(this.crossFade);
        if (parseInt > 15 || parseInt < 0) {
            parseInt = -1;
        }
        return parseInt;
    }

    public void setCrossFade(String str) {
        this.crossFade = str;
    }

    public int getDataSaveMode() {
        if (this.dataSaveMode == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.dataSaveMode) != 1) {
            i = 0;
        }
        return i;
    }

    public void setDataSaveMode(String str) {
        this.dataSaveMode = str;
    }

    public int getRecommendSongQueue() {
        if (this.recommendSongQueue == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.recommendSongQueue) != 1) {
            i = 0;
        }
        return i;
    }

    public void setRecommendSongQueue(String str) {
        this.recommendSongQueue = str;
    }

    public int getDownloadOver() {
        if (this.downloadOver == null) {
            return -1;
        }
        int parseInt = Integer.parseInt(this.downloadOver);
        if (parseInt > 2 || parseInt < 0) {
            parseInt = -1;
        }
        return parseInt;
    }

    public void setDownloadOver(String str) {
        this.downloadOver = str;
    }

    public int getDownloadOver2G3G() {
        if (this.downloadOver2G3G == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.downloadOver2G3G) != 1) {
            i = 0;
        }
        return i;
    }

    public int getAutoDownloadOverWifi() {
        if (this.autoDownloadOverWifi == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.autoDownloadOverWifi) != 1) {
            i = 0;
        }
        return i;
    }

    public void setDownloadOver2G3G(String str) {
        this.downloadOver2G3G = str;
    }

    public int getScheduleDownloads() {
        if (this.scheduleDownloads == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.scheduleDownloads) != 1) {
            i = 0;
        }
        return i;
    }

    public void setScheduleDownloads(String str) {
        this.scheduleDownloads = str;
    }

    public int getDownloadQuality() {
        if (this.downloadQuality == null) {
            return -1;
        }
        return Integer.parseInt(this.downloadQuality);
    }

    public void setDownloadQuality(String str) {
        this.downloadQuality = str;
    }

    public int getSyncOver2G3G() {
        if (this.syncOver2G3G == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.syncOver2G3G) != 1) {
            i = 0;
        }
        return i;
    }

    public void setSyncOver2G3G(String str) {
        this.syncOver2G3G = str;
    }

    public int getAutoSync() {
        if (this.autoSync == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.autoSync) != 1) {
            i = 0;
        }
        return i;
    }

    public void setAutoSync(String str) {
        this.autoSync = str;
    }

    public int getMusicRecommendations() {
        if (this.musicRecommendations == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.musicRecommendations) != 1) {
            i = 0;
        }
        return i;
    }

    public void setMusicRecommendations(String str) {
        this.musicRecommendations = str;
    }

    public int getMarkFavPlaylist() {
        if (this.markFavPlaylist == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.markFavPlaylist) != 1) {
            i = 0;
        }
        return i;
    }

    public void setMarkFavPlaylist(String str) {
        this.markFavPlaylist = str;
    }

    public int getCanFollow() {
        if (this.canFollow == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.canFollow) != 1) {
            i = 0;
        }
        return i;
    }

    public void setCanFollow(String str) {
        this.canFollow = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getDisplayLanguage() {
        return this.displayLanguage;
    }

    public void setDisplayLanguage(String str) {
        this.displayLanguage = str;
    }

    public int getParentalWarning() {
        if (this.parentalWarning == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.parentalWarning) != 1) {
            i = 0;
        }
        return i;
    }

    public void setParentalWarning(String str) {
        this.parentalWarning = str;
    }

    public int getAutoQueue() {
        if (this.autoQueue == null) {
            return -1;
        }
        int i = 1;
        if (Integer.parseInt(this.autoQueue) != 1) {
            i = 0;
        }
        return i;
    }

    public void setAutoQueue(String str) {
        this.autoQueue = str;
    }
}
