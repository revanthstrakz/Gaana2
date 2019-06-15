package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.models.MoreInfo.Composer;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.annotations.SerializedName;
import com.managers.DownloadManager.DownloadStatus;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class Tracks extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("tracks")
    private ArrayList<Track> arrListTracks;
    @SerializedName("composers")
    private ArrayList<Composer> composers;
    @SerializedName("created_on")
    private String createdOn;
    @SerializedName("favorite_count")
    private String favoriteCount;
    @SerializedName("msg")
    private String headerMessage;
    @SerializedName("is_sponsored")
    public int is_sponsored;
    @SerializedName("modified_on")
    private String modifiedOn;
    @SerializedName("count")
    private int songsCount;
    @SerializedName("tags")
    private ArrayList<Tags> tags;
    @SerializedName("top_artists")
    private ArrayList<TopArtists> topArtists;
    @SerializedName("top_languages")
    private ArrayList<TopLanguage> topLanguages;
    @SerializedName("unfavourite")
    private String unfavorite;

    public static class Track extends BusinessObject implements Downloadable {
        private static final long serialVersionUID = 1;
        @SerializedName("album_id")
        private String albumId = "";
        @SerializedName("album_title")
        private String albumTitle = "";
        @SerializedName("artist")
        private ArrayList<Artist> artist;
        @SerializedName("artwork")
        private String artwork = "";
        @SerializedName("artwork_large")
        private String artwork_large = "";
        @SerializedName("av_ad")
        private int avAd;
        private int cachingBehaviour;
        @SerializedName("clip_vd")
        private String clipVideoUrl;
        @SerializedName("content_source")
        private double contentSource;
        @SerializedName("mobile")
        private String deviceAvailability = "1";
        @SerializedName("download_enabled")
        private int downloadEnabled = 0;
        protected DownloadStatus downloadStatus;
        @SerializedName("duration")
        private String duration = "0";
        @SerializedName("horz_vd")
        private String horizontalUrl;
        private boolean isAddedToPlaylist;
        private boolean isMarkedForDeletionFromPlaylist = false;
        protected Boolean isOffline = Boolean.valueOf(false);
        protected Boolean isPlaying = Boolean.valueOf(false);
        protected Boolean isSelected = Boolean.valueOf(true);
        @SerializedName("is_local")
        private String is_local = null;
        @SerializedName("is_most_popular")
        private String is_most_popular = "0";
        @SerializedName("isrc")
        private String isrc = "";
        @SerializedName("language")
        private String language = null;
        @SerializedName("country")
        private String locationAvailability = "1";
        @SerializedName("lyrics_type")
        private String lyricsType;
        @SerializedName("lyrics_url")
        private String lyricsUrl = null;
        @SerializedName("parental_warning")
        private int parentalWarning = 0;
        @SerializedName("play_ct")
        private String playCount = null;
        @SerializedName("popularity")
        private String popularity;
        @SerializedName("premium_content")
        private String premiumContent = null;
        @SerializedName("seed_track_id")
        private String seed_track_id;
        @SerializedName("seokey")
        private String seokey = "";
        private int smartDownload = 0;
        @SerializedName("stream_type")
        private String streamType = "";
        @SerializedName("tags")
        private ArrayList<Tags> tags;
        @SerializedName("track_id")
        private String trackId = "0";
        @SerializedName("track_title")
        private String trackTitle = "";
        @SerializedName("urls")
        private StreamUrls urls;
        @SerializedName("vert_vd")
        private String verticalUrl;
        @SerializedName("vgid")
        private String vgid;
        @SerializedName("vd_expiry")
        private String videoExpiryTime;
        @SerializedName("video_id")
        private String videoId = null;
        @SerializedName("video_url")
        private String videoUrl = null;
        @SerializedName("youtube_id")
        private String youtubeId = null;

        public static class Artist implements Serializable {
            private static final long serialVersionUID = 1;
            @SerializedName("artist_id")
            public String artist_id;
            @SerializedName("cached")
            public int cached;
            @SerializedName("name")
            public String name;
            @SerializedName("seokey")
            public String seokey;

            public String getArtist_id() {
                return this.artist_id;
            }

            public void setId(String str) {
                this.artist_id = str;
            }

            public String getArtistid() {
                return this.artist_id;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getName(String str) {
                return Constants.a(this.name, str);
            }

            public String getEnglishName() {
                return Constants.a(this.name);
            }

            public int getCached() {
                return this.cached;
            }

            public void setCached(int i) {
                this.cached = i;
            }

            public String getSeokey() {
                return this.seokey;
            }

            public void setSeokey(String str) {
                this.seokey = str;
            }
        }

        public static class Gener implements Serializable {
            private static final long serialVersionUID = 1;
            @SerializedName("genre_id")
            public String genre_id = "";
            @SerializedName("name")
            public String name = "";
        }

        public static class Tags extends BusinessObject {
            private static final long serialVersionUID = 1;
            private boolean isSelected = false;
            @SerializedName("tag_id")
            public String tag_id = "";
            @SerializedName("tag_name")
            public String tag_name = "";

            public String getTag_id() {
                return this.tag_id;
            }

            public void setTag_id(String str) {
                this.tag_id = str;
            }

            public String getTag_name(String str) {
                return Constants.a(this.tag_name, str);
            }

            public String getEnglishName() {
                return Constants.a(this.tag_name);
            }

            public void setTag_name(String str) {
                this.tag_name = str;
            }

            public boolean isSelected() {
                return this.isSelected;
            }

            public void setSelected(boolean z) {
                this.isSelected = z;
            }
        }

        public static class TopArtists extends BusinessObject {
            private static final long serialVersionUID = 1;
            @SerializedName("name")
            public String artist_name = "";
            private boolean isSelected = false;
            @SerializedName("artist_id")
            public String top_artistId = "";

            public String getTop_artistId() {
                return this.top_artistId;
            }

            public void setTop_artistId(String str) {
                this.top_artistId = str;
            }

            public String getArtist_name(String str) {
                return Constants.a(this.artist_name, str);
            }

            public String getEnglishName() {
                return Constants.a(this.artist_name);
            }

            public void setArtist_name(String str) {
                this.artist_name = str;
            }

            public boolean isSelected() {
                return this.isSelected;
            }

            public void setSelected(boolean z) {
                this.isSelected = z;
            }
        }

        public static class TopLanguage extends BusinessObject {
            private static final long serialVersionUID = 1;
            private boolean isSelected = false;
            @SerializedName("lang_name")
            public String lang_name = "";

            public String getLang_name() {
                return this.lang_name;
            }

            public String getEnglishName() {
                return Constants.a(this.lang_name);
            }

            public String getLang_name(String str) {
                return Constants.a(this.lang_name, str);
            }

            public void setLang_name(String str) {
                this.lang_name = str;
            }

            public boolean isSelected() {
                return this.isSelected;
            }

            public void setSelected(boolean z) {
                this.isSelected = z;
            }
        }

        public void setCachingBehaviour(int i) {
            this.cachingBehaviour = i;
        }

        public int getCachingBehaviour() {
            return this.cachingBehaviour;
        }

        public double getContentSource() {
            return this.contentSource;
        }

        public void setContentSource(double d) {
            this.contentSource = d;
        }

        public long getVideoExpiryTime() {
            return !TextUtils.isEmpty(this.videoExpiryTime) ? Long.parseLong(this.videoExpiryTime) : -1;
        }

        public boolean isFreeDownloadEnabled() {
            return this.downloadEnabled == 1;
        }

        public void setFreeDownloadEnabled(int i) {
            this.downloadEnabled = i;
        }

        public int getSmartDownload() {
            return this.smartDownload;
        }

        public void setSmartDownload(int i) {
            this.smartDownload = i;
        }

        public void setVideoExpiryTime(String str) {
            this.videoExpiryTime = str;
        }

        public String getVgid() {
            return this.vgid;
        }

        public void setVgid(String str) {
            this.vgid = str;
        }

        public String getLanguage() {
            return this.language;
        }

        public void setLanguage(String str) {
            this.language = str;
        }

        public long getPopularity() {
            if (this.popularity == null || !this.popularity.contains("~")) {
                return 0;
            }
            return Long.valueOf(this.popularity.substring(this.popularity.indexOf(126) + 1)).longValue();
        }

        public String getSeedTrackId() {
            return this.seed_track_id;
        }

        public void setSeedTrackId(String str) {
            this.seed_track_id = str;
        }

        public String getIslocal() {
            return this.is_local;
        }

        public void setIsLocal(String str) {
            this.is_local = str;
        }

        public void setIsSelected(Boolean bool) {
            this.isSelected = bool;
        }

        public Boolean isSelected() {
            if (this.isSelected == null) {
                this.isSelected = Boolean.valueOf(true);
            }
            return this.isSelected;
        }

        public Boolean isPlaying() {
            return this.isPlaying;
        }

        public void setIsPlaying(Boolean bool) {
            this.isPlaying = bool;
        }

        public String getBusinessObjId() {
            return this.trackId;
        }

        public void setBusinessObjId(String str) {
            this.trackId = str;
        }

        public void setPremiumContent(String str) {
            this.premiumContent = str;
        }

        public String getPremiumContent() {
            return this.premiumContent;
        }

        public String getTrackTitle() {
            return Constants.a(this.trackTitle, this.language);
        }

        public String getRawName() {
            return this.trackTitle;
        }

        public String getName() {
            return Constants.a(this.trackTitle, this.language);
        }

        public String getEnglishName() {
            return Constants.a(this.trackTitle);
        }

        public void setName(String str) {
            this.trackTitle = str;
        }

        public void setTracktitle(String str) {
            this.trackTitle = str;
        }

        public String getAlbumId() {
            return this.albumId;
        }

        public void setAlbumId(String str) {
            this.albumId = str;
        }

        public String getAlbumTitle() {
            return Constants.a(this.albumTitle, this.language);
        }

        public String getEnglishAlbumTitle() {
            return Constants.a(this.albumTitle);
        }

        public String getRawAlbumTitle() {
            return this.albumTitle;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.artwork;
            }
            return getAtw();
        }

        public String getArtworkSpecific() {
            if (TextUtils.isEmpty(this.artwork)) {
                return getAtw();
            }
            return this.artwork;
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }

        public void setArtworkLarge(String str) {
            if (TextUtils.isEmpty(str)) {
                this.artwork_large = this.artwork;
            } else {
                this.artwork_large = str;
            }
        }

        public String getArtworkLarge() {
            if (!TextUtils.isEmpty(getAtw())) {
                return getAtw();
            }
            if (this.artwork_large == null || this.artwork_large.length() == 0) {
                return this.artwork;
            }
            return this.artwork_large;
        }

        public String getDuration() {
            return this.duration;
        }

        public void setDuration(String str) {
            this.duration = str;
        }

        public String getIsrc() {
            return this.isrc;
        }

        public void setIsrc(String str) {
            this.isrc = str;
        }

        public ArrayList<Artist> getArtists() {
            return this.artist;
        }

        public String getArtistIds() {
            String str = "";
            if (this.artist == null) {
                return str;
            }
            for (int i = 0; i < this.artist.size(); i++) {
                StringBuilder stringBuilder;
                if (i != 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(",");
                    str = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(((Artist) this.artist.get(i)).artist_id);
                str = stringBuilder.toString();
            }
            return str;
        }

        public String getTargetingArtistIds() {
            String str = "";
            if (this.artist == null) {
                return str;
            }
            for (int i = 0; i < this.artist.size(); i++) {
                StringBuilder stringBuilder;
                if (i != 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(",");
                    str = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(((Artist) this.artist.get(i)).artist_id);
                str = stringBuilder.toString();
            }
            return str;
        }

        public boolean isMostPopular() {
            return this.is_most_popular != null && this.is_most_popular.equals("1");
        }

        public void setIsMostPopular(String str) {
            this.is_most_popular = str;
        }

        public String getDeviceAvailability() {
            if (this.deviceAvailability == null) {
                this.deviceAvailability = "1";
            }
            return this.deviceAvailability;
        }

        public void setDeviceAvailability(String str) {
            this.deviceAvailability = str;
        }

        public String getLocationAvailability() {
            if (this.locationAvailability == null) {
                this.locationAvailability = "1";
            }
            return this.locationAvailability;
        }

        public void setLocationAvailability(String str) {
            this.locationAvailability = str;
        }

        public String getArtistNames() {
            String str = "";
            if (this.artist == null) {
                return str;
            }
            for (int i = 0; i < this.artist.size(); i++) {
                StringBuilder stringBuilder;
                if (i != 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(", ");
                    str = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(Constants.a(((Artist) this.artist.get(i)).name, this.language));
                str = stringBuilder.toString();
            }
            return str;
        }

        public String getEnglishArtistNames() {
            String str = "";
            if (this.artist == null) {
                return str;
            }
            for (int i = 0; i < this.artist.size(); i++) {
                StringBuilder stringBuilder;
                if (i != 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(", ");
                    str = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(Constants.a(((Artist) this.artist.get(i)).name));
                str = stringBuilder.toString();
            }
            return str;
        }

        public String getArtistRawNames() {
            String str = "";
            if (this.artist == null) {
                return str;
            }
            for (int i = 0; i < this.artist.size(); i++) {
                StringBuilder stringBuilder;
                if (i != 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(", ");
                    str = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(((Artist) this.artist.get(i)).name);
                str = stringBuilder.toString();
            }
            return str;
        }

        public void setArtist(ArrayList<Artist> arrayList) {
            this.artist = arrayList;
        }

        public boolean isMarkedForDeletionFromPlaylist() {
            return this.isMarkedForDeletionFromPlaylist;
        }

        public void setMarkedForDeletionFromPlaylist(boolean z) {
            this.isMarkedForDeletionFromPlaylist = z;
        }

        public String getStreamType() {
            return this.streamType;
        }

        public void setStreamType(String str) {
            this.streamType = str;
        }

        public String getSeokey() {
            return this.seokey;
        }

        public void setSeokey(String str) {
            this.seokey = str;
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

        public String getHorizontalUrl() {
            return this.horizontalUrl;
        }

        public String getVerticalUrl() {
            return this.verticalUrl;
        }

        public String getClipVideoUrl() {
            return this.clipVideoUrl;
        }

        public void setClipVideoUrl(String str) {
            this.clipVideoUrl = str;
        }

        public void setVerticalUrl(String str) {
            this.verticalUrl = str;
        }

        public void setHorizontalUrl(String str) {
            this.horizontalUrl = str;
        }

        public String getVideoUrl() {
            return this.videoUrl;
        }

        public void setVideoUrl(String str) {
            this.videoUrl = str;
        }

        public String getLyricsUrl() {
            return this.lyricsUrl;
        }

        public void setLyricsUrl(String str) {
            this.lyricsUrl = str;
        }

        public String getLyricsType() {
            return this.lyricsType;
        }

        public void setLyricsType(String str) {
            this.lyricsType = str;
        }

        public void setAlbumName(String str) {
            this.albumTitle = str;
        }

        public String getYoutubeId() {
            return this.youtubeId;
        }

        public void setYoutubeId(String str) {
            this.youtubeId = str;
        }

        public String getVideoId() {
            return this.videoId;
        }

        public void setVideoId(String str) {
            this.videoId = str;
        }

        public boolean isParentalWarningEnabled() {
            return this.parentalWarning == 1;
        }

        public void setParentalWarning(int i) {
            this.parentalWarning = i;
        }

        public String getPlayCount() {
            return this.playCount;
        }

        public void setPlayCount(String str) {
            this.playCount = str;
        }

        public int getAvAd() {
            return this.avAd;
        }

        public void setAvAd(int i) {
            this.avAd = i;
        }

        public StreamUrls getUrls() {
            return this.urls;
        }

        public void setUrls(StreamUrls streamUrls) {
            this.urls = streamUrls;
        }

        public int hashCode() {
            if (this.trackId == null) {
                this.trackId = "0";
            }
            return Integer.valueOf(this.trackId).intValue();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Track track = (Track) obj;
            if (this.trackId == null) {
                this.trackId = "0";
            }
            int intValue = Integer.valueOf(this.trackId).intValue();
            String businessObjId = track.getBusinessObjId();
            if (businessObjId == null) {
                businessObjId = "0";
            }
            return intValue == Integer.valueOf(businessObjId).intValue();
        }

        public String getTagID() {
            String str = "";
            if (this.tags == null) {
                return str;
            }
            for (int i = 0; i < this.tags.size(); i++) {
                StringBuilder stringBuilder;
                if (i != 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(",");
                    str = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(((Tags) this.tags.get(i)).tag_id);
                str = stringBuilder.toString();
            }
            return str;
        }

        public ArrayList<Tags> getTags() {
            return this.tags;
        }

        public void setTags(ArrayList<Tags> arrayList) {
            this.tags = arrayList;
        }

        public boolean isAddedToPlaylist() {
            return this.isAddedToPlaylist;
        }

        public void setAddedToPlaylist(boolean z) {
            this.isAddedToPlaylist = z;
        }

        public String getNameAndId() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Constants.a(this.trackTitle, this.language));
            stringBuilder.append("#");
            stringBuilder.append(this.trackId);
            return stringBuilder.toString();
        }

        public StreamUrls getStreamUrls() {
            return this.urls;
        }

        public void setStreamUrls(StreamUrls streamUrls) {
            this.urls = streamUrls;
        }
    }

    public int getSongsCount() {
        return this.songsCount;
    }

    public String getHeaderMessage() {
        return this.headerMessage;
    }

    public boolean getIsSponsored() {
        return this.is_sponsored == 1;
    }

    public ArrayList<Composer> getComposers() {
        return this.composers;
    }

    public ArrayList<Track> getArrListBusinessObj() {
        return this.arrListTracks;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListTracks = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof Track) {
                this.arrListTracks.add((Track) businessObject);
            }
        }
    }

    public String getFavoriteCount() {
        return this.favoriteCount;
    }

    public void setFavoriteCount(String str) {
        this.favoriteCount = str;
    }

    public Date getModifiedOn() {
        try {
            if (this.modifiedOn != null) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(this.modifiedOn);
            }
            return null;
        } catch (ParseException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public Date getCreatedOn() {
        Date date = new Date();
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(this.createdOn);
        } catch (ParseException e) {
            ThrowableExtension.printStackTrace(e);
            return date;
        }
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

    public String getUnfavorite() {
        return this.unfavorite;
    }
}
