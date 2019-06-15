package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import com.managers.URLManager.BusinessObjectType;
import java.io.Serializable;
import java.util.ArrayList;

public class MoreInfo extends BusinessObject {
    @SerializedName("album_artwork")
    private String album_artwork;
    @SerializedName("album_favorite")
    private int album_favorite;
    @SerializedName("album_id")
    private String album_id;
    @SerializedName("album_title")
    private String album_title;
    @SerializedName("albumseokey")
    private String albumseokey;
    @SerializedName("artwork")
    private String artwork;
    @SerializedName("artwork_large")
    private String artwork_large;
    @SerializedName("artwork_web")
    private String artwork_web;
    @SerializedName("cast")
    private ArrayList<Cast> cast;
    @SerializedName("composers")
    private ArrayList<Composer> composers;
    @SerializedName("content_source")
    private String content_source;
    @SerializedName("created_on")
    private String created_on;
    @SerializedName("duration")
    private String duration;
    @SerializedName("language")
    private String language;
    @SerializedName("lyricist")
    private ArrayList<Lyricist> lyricist;
    @SerializedName("modified_on")
    private String modified_on;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("seokey")
    private String seokey;
    @SerializedName("singers")
    private ArrayList<Singer> singers;
    @SerializedName("status")
    private int status;
    @SerializedName("title")
    private String title;
    @SerializedName("track_favorite")
    private int track_favorite;
    @SerializedName("track_id")
    private String track_id;
    @SerializedName("trackcount")
    private int trackcount;
    @SerializedName("vendor")
    private String vendor;

    public class Cast extends BusinessObject implements Serializable {
        @SerializedName("artwork")
        private String artwork;
        @SerializedName("e_id")
        private String e_id;
        @SerializedName("e_type")
        private String e_type;
        @SerializedName("favorite")
        private int favorite;
        @SerializedName("name")
        private String name;
        private String seokey;

        public String getEId() {
            return this.e_id;
        }

        public void setEId(String str) {
            this.e_id = str;
        }

        public String getName() {
            return Constants.b(this.name);
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getRawName() {
            return this.name;
        }

        public String getEnglishName() {
            return Constants.a(this.name);
        }

        public String getSeokey() {
            return this.seokey;
        }

        public void setSeokey(String str) {
            this.seokey = str;
        }

        public String getArtwork() {
            return this.artwork;
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }

        public int getFavorite() {
            return this.favorite;
        }

        public void setFavorite(int i) {
            this.favorite = i;
        }

        public String getEType() {
            return this.e_type;
        }

        public void setEType(String str) {
            this.e_type = str;
        }
    }

    public class Composer extends BusinessObject implements Serializable {
        @SerializedName("artwork")
        private String artwork;
        @SerializedName("e_id")
        private String e_id;
        @SerializedName("e_type")
        private String e_type;
        @SerializedName("favorite")
        private int favorite;
        @SerializedName("name")
        private String name;
        @SerializedName("seokey")
        private String seokey;

        public String getEId() {
            return this.e_id;
        }

        public void setEId(String str) {
            this.e_id = str;
        }

        public String getBusinessObjId() {
            return this.e_id;
        }

        public BusinessObjectType getBusinessObjType() {
            return BusinessObjectType.Artists;
        }

        public Boolean isFavorite() {
            if (this.favorite == 1) {
                return Boolean.valueOf(true);
            }
            return Boolean.valueOf(false);
        }

        public String getName() {
            return Constants.a(this.name, this.language);
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getRawName() {
            return this.name;
        }

        public String getEnglishName() {
            return Constants.a(this.name);
        }

        public String getSeokey() {
            return this.seokey;
        }

        public void setSeokey(String str) {
            this.seokey = str;
        }

        public String getArtwork() {
            return this.artwork;
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }

        public int getFavorite() {
            return this.favorite;
        }

        public void setFavorite(int i) {
            this.favorite = i;
        }

        public String getEType() {
            return this.e_type;
        }

        public void setEType(String str) {
            this.e_type = str;
        }
    }

    public class Lyricist extends BusinessObject implements Serializable {
        @SerializedName("artwork")
        private String artwork;
        @SerializedName("e_id")
        private String e_id;
        @SerializedName("e_type")
        private String e_type;
        @SerializedName("favorite")
        private int favorite;
        @SerializedName("name")
        private String name;

        public String getEId() {
            return this.e_id;
        }

        public void setEId(String str) {
            this.e_id = str;
        }

        public String getBusinessObjId() {
            return this.e_id;
        }

        public BusinessObjectType getBusinessObjType() {
            return BusinessObjectType.Artists;
        }

        public String getName() {
            return Constants.b(this.name);
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getRawName() {
            return this.name;
        }

        public String getEnglishName() {
            return Constants.a(this.name);
        }

        public Boolean isFavorite() {
            if (this.favorite == 1) {
                return Boolean.valueOf(true);
            }
            return Boolean.valueOf(false);
        }

        public String getArtwork() {
            return this.artwork;
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }

        public int getFavorite() {
            return this.favorite;
        }

        public void setFavorite(int i) {
            this.favorite = i;
        }

        public String getEType() {
            return this.e_type;
        }

        public void setEType(String str) {
            this.e_type = str;
        }
    }

    public class Singer extends BusinessObject implements Serializable {
        @SerializedName("artwork")
        private String artwork;
        @SerializedName("e_id")
        private String e_id;
        @SerializedName("e_type")
        private String e_type;
        @SerializedName("favorite")
        private int favorite;
        private String index;
        @SerializedName("name")
        private String name;
        @SerializedName("seokey")
        private String seokey;

        public String getEId() {
            return this.e_id;
        }

        public void setEId(String str) {
            this.e_id = str;
        }

        public String getIndex() {
            return this.index;
        }

        public void setIndex(String str) {
            this.index = str;
        }

        public String getBusinessObjId() {
            return this.e_id;
        }

        public BusinessObjectType getBusinessObjType() {
            return BusinessObjectType.Artists;
        }

        public Boolean isFavorite() {
            if (this.favorite == 1) {
                return Boolean.valueOf(true);
            }
            return Boolean.valueOf(false);
        }

        public String getName() {
            return Constants.a(this.name, this.language);
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getRawName() {
            return this.name;
        }

        public String getEnglishName() {
            return Constants.a(this.name);
        }

        public String getSeokey() {
            return this.seokey;
        }

        public void setSeokey(String str) {
            this.seokey = str;
        }

        public String getArtwork() {
            return this.artwork;
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }

        public int getFavorite() {
            return this.favorite;
        }

        public void setFavorite(int i) {
            this.favorite = i;
        }

        public String getEType() {
            return this.e_type;
        }

        public void setEType(String str) {
            this.e_type = str;
        }
    }

    public int getTrackcount() {
        return this.trackcount;
    }

    public String getCreatedOn() {
        return this.created_on;
    }

    public String getModifiedOn() {
        return this.modified_on;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getTrackId() {
        return this.track_id;
    }

    public void setTrackId(String str) {
        this.track_id = str;
    }

    public String getSeokey() {
        return this.seokey;
    }

    public void setSeokey(String str) {
        this.seokey = str;
    }

    public String getAlbumseokey() {
        return this.albumseokey;
    }

    public void setAlbumseokey(String str) {
        this.albumseokey = str;
    }

    public String getAlbumId() {
        return this.album_id;
    }

    public void setAlbumId(String str) {
        this.album_id = str;
    }

    public String getTitle() {
        return Constants.a(this.title, this.language);
    }

    public String getRawTitle() {
        return this.title;
    }

    public String getRawAlbumTitle() {
        return this.album_title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAlbumTitle() {
        return Constants.a(this.album_title, this.language);
    }

    public void setAlbumTitle(String str) {
        this.album_title = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
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

    public String getArtworkWeb() {
        return this.artwork_web;
    }

    public void setArtworkWeb(String str) {
        this.artwork_web = str;
    }

    public String getArtworkLarge() {
        return this.artwork_large;
    }

    public void setArtworkLarge(String str) {
        this.artwork_large = str;
    }

    public String getPopularity() {
        return this.popularity;
    }

    public void setPopularity(String str) {
        this.popularity = str;
    }

    public String getContentSource() {
        return this.content_source;
    }

    public void setContentSource(String str) {
        this.content_source = str;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public ArrayList<Cast> getCast() {
        return this.cast;
    }

    public void setCast(ArrayList<Cast> arrayList) {
        this.cast = arrayList;
    }

    public ArrayList<Composer> getComposers() {
        return this.composers;
    }

    public void setComposers(ArrayList<Composer> arrayList) {
        this.composers = arrayList;
    }

    public ArrayList<Singer> getSingers() {
        return this.singers;
    }

    public void setSingers(ArrayList<Singer> arrayList) {
        this.singers = arrayList;
    }

    public ArrayList<Lyricist> getLyricist() {
        return this.lyricist;
    }

    public void setLyricist(ArrayList<Lyricist> arrayList) {
        this.lyricist = arrayList;
    }

    public String getReleaseDate() {
        return this.release_date;
    }

    public void setReleaseDate(String str) {
        this.release_date = str;
    }

    public int getTrackFavorite() {
        return this.track_favorite;
    }

    public void setTrackFavorite(int i) {
        this.track_favorite = i;
    }

    public int getAlbumFavorite() {
        return this.album_favorite;
    }

    public void setAlbumFavorite(int i) {
        this.album_favorite = i;
    }

    public String getVendor() {
        return Constants.a(this.vendor, this.language);
    }

    public String getRawVendorName() {
        return this.vendor;
    }

    public void setVendor(String str) {
        this.vendor = str;
    }

    public String getAlbumArtwork() {
        return this.album_artwork;
    }

    public void setAlbumArtwork(String str) {
        this.album_artwork = str;
    }
}
