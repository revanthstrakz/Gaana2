package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Artists extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("artist")
    private ArrayList<Artist> arrListArtist;
    @SerializedName("unfavourite")
    private String unfavorite;

    public static class Artist extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("albums")
        private String albumsCount;
        @SerializedName("gener")
        private ArrayList<Genres> arrListGener;
        @SerializedName("artist_id")
        private String artistId;
        private String artwork;
        @SerializedName("bottom_banner_off")
        private int bottom_banner_off;
        private String description;
        @SerializedName("favorite_count")
        private String favoriteCount;
        @SerializedName("is_sponsored")
        private String is_sponsored;
        private String name;
        private String rating;
        private String seokey;
        @SerializedName("songs")
        private String songsCount;

        public static class Genre implements Serializable {
            @SerializedName("genre_id")
            private String genreId;
            private String name;

            public String getGenreId() {
                return this.genreId;
            }

            public String getName() {
                return Constants.b(this.name);
            }

            public String getRawName() {
                return this.name;
            }
        }

        public boolean isBottomBannerOff() {
            return this.bottom_banner_off == 1;
        }

        public String getBusinessObjId() {
            return this.artistId;
        }

        public void setBusinessObjId(String str) {
            this.artistId = str;
        }

        public String getSeokey() {
            return this.seokey;
        }

        public void setSeokey(String str) {
            this.seokey = str;
        }

        public String getName() {
            return Constants.b(this.name);
        }

        public String getEnglishName() {
            return Constants.a(this.name);
        }

        public String getRawName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getRating() {
            return this.rating;
        }

        public String getSongsCount() {
            return this.songsCount;
        }

        public String getAlbumsCount() {
            return this.albumsCount;
        }

        public String getDescription() {
            return this.description;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.artwork;
            }
            return getAtw();
        }

        public void setSongsCount(String str) {
            this.songsCount = str;
        }

        public void setAlbumsCount(String str) {
            this.albumsCount = str;
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }

        public String getIsSponsored() {
            return this.is_sponsored;
        }

        public void setIs_sponsored(String str) {
            this.is_sponsored = str;
        }

        public String getFavoriteCount() {
            return this.favoriteCount;
        }

        public void setFavoriteCount(String str) {
            this.favoriteCount = str;
        }

        public ArrayList<Genres> getArrListGener() {
            return this.arrListGener;
        }
    }

    public ArrayList<Artist> getArrListBusinessObj() {
        return this.arrListArtist;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListArtist = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof Artist) {
                this.arrListArtist.add((Artist) businessObject);
            }
        }
    }

    public String getUnfavorite() {
        return this.unfavorite;
    }
}
