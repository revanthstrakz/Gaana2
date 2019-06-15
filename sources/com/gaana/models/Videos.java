package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class Videos extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("videoplaylist")
    private ArrayList<Video> arrListTracks;

    public static class Video extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("album_id")
        private String albumId = "";
        @SerializedName("album_title")
        private String albumTitle = "";
        @SerializedName("albumseokey")
        private String albumseokey = "";
        private ArrayList<Artist> artist;
        private String artwork = "";
        private String artwork_large = "";
        @SerializedName("display_global")
        private String display_global = "0";
        private String duration = "0";
        private ArrayList<Gener> gener;
        @SerializedName("http")
        private String http = "0";
        @SerializedName("https")
        private String https = "0";
        @SerializedName("is_most_popular")
        private String is_most_popular = "0";
        private String isrc = "";
        private String language = "";
        @SerializedName("lyrics_url")
        private String lyricsUrl = null;
        private String popularity = "";
        private String rating = "";
        @SerializedName("rtmp")
        private String rtmp = "0";
        @SerializedName("rtsp")
        private String rtsp = "0";
        @SerializedName("seokey")
        private String seokey = "";
        @SerializedName("stream_type")
        private String streamType = "";
        @SerializedName("track_id")
        private String trackId = "0";
        @SerializedName("track_title")
        private String trackTitle = "";
        @SerializedName("video_url")
        private String videoUrl = null;

        public static class Artist implements Serializable {
            private static final long serialVersionUID = 1;
            public String artist_id = "";
            public String name = "";
        }

        public static class Gener implements Serializable {
            private static final long serialVersionUID = 1;
            public String genre_id = "";
            public String name = "";
        }

        public static long getSerialversionuid() {
            return 1;
        }

        public String getBusinessObjId() {
            return this.trackId;
        }

        public String getAlbumseokey() {
            return this.albumseokey;
        }

        public String getTrackTitle() {
            return Constants.b(this.trackTitle);
        }

        public String getRawName() {
            return this.trackTitle;
        }

        public String getName() {
            return Constants.b(this.trackTitle);
        }

        public void setTracktitle(String str) {
            this.trackTitle = str;
        }

        public String getAlbumId() {
            return this.albumId;
        }

        public String getAlbumTitle() {
            return Constants.b(this.albumTitle);
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

        public String getArtworkLarge() {
            if (this.artwork_large == null || this.artwork_large.length() == 0) {
                return this.artwork;
            }
            return this.artwork_large;
        }

        public String getPopularity() {
            return this.popularity;
        }

        public String getRating() {
            return this.rating;
        }

        public String getDuration() {
            return this.duration;
        }

        public String getIsrc() {
            return this.isrc;
        }

        public ArrayList<Artist> getArtists() {
            return this.artist;
        }

        public ArrayList<Gener> getGeners() {
            return this.gener;
        }

        public boolean isMostPopular() {
            return this.is_most_popular != null && this.is_most_popular.equals("1");
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
                stringBuilder.append(((Artist) this.artist.get(i)).name);
                str = stringBuilder.toString();
            }
            return str;
        }

        public String getStreamType() {
            return this.streamType;
        }

        public String getSeokey() {
            return this.seokey;
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

        public String getTrackId() {
            return this.trackId;
        }

        public String getArtwork_large() {
            return this.artwork_large;
        }

        public String getLanguage() {
            return this.language;
        }

        public ArrayList<Artist> getArtist() {
            return this.artist;
        }

        public ArrayList<Gener> getGener() {
            return this.gener;
        }

        public String getIs_most_popular() {
            return this.is_most_popular;
        }

        public String getRtmp() {
            return this.rtmp;
        }

        public String getHttp() {
            return this.http;
        }

        public String getHttps() {
            return this.https;
        }

        public String getRtsp() {
            return this.rtsp;
        }

        public String getDisplay_global() {
            return this.display_global;
        }
    }

    public ArrayList<Video> getArrListBusinessObj() {
        return this.arrListTracks;
    }
}
