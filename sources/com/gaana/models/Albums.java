package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.MoreInfo.Composer;
import com.gaana.models.Tracks.Track.Tags;
import com.gaana.models.Tracks.Track.TopArtists;
import com.gaana.models.Tracks.Track.TopLanguage;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Albums extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("album")
    private ArrayList<Album> arrListAlbum;
    @SerializedName("unfavourite")
    private String unfavorite;

    public static class Album extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("ad_code")
        private String ad_code;
        @SerializedName("ads_slugs")
        private CustomAdParams ads_slugs;
        @SerializedName("album_id")
        private String albumId;
        @SerializedName("artist")
        private ArrayList<Artist> artist;
        private String artwork;
        @SerializedName("bottom_banner_off")
        private int bottom_banner_off;
        private ArrayList<Composer> composers;
        @SerializedName("mobile")
        private String deviceAvailability = "1";
        private long downloadTime;
        private String favorite_count = "0";
        @SerializedName("is_sponsored")
        private String is_sponsored;
        private String language;
        @SerializedName("country")
        private String locationAvailability = "1";
        @SerializedName("parental_warning")
        private int parentalWarning;
        private String premiumContent = null;
        @SerializedName("primaryartist")
        private ArrayList<Artist> primaryartist;
        private String seokey;
        private ArrayList<Tags> tags;
        private String title;
        private ArrayList<TopArtists> topArtists;
        @SerializedName("top_languages")
        private ArrayList<TopLanguage> topLanguages;

        public static class Artist implements Serializable {
            private static final long serialVersionUID = 1;
            @SerializedName("artist_id")
            private String artistId;
            private String name;
            private String seoKey;

            public String getArtistId() {
                return this.artistId;
            }

            public String getName(String str) {
                return Constants.a(this.name, str);
            }

            public String getRawName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public void setId(String str) {
                this.artistId = str;
            }

            public void setSeoKey(String str) {
                this.seoKey = str;
            }

            public String getSeoKey() {
                return this.seoKey;
            }
        }

        public static class Genre implements Serializable {
            private static final long serialVersionUID = 1;
            @SerializedName("gener_id")
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

        public ArrayList<Composer> getComposers() {
            return this.composers;
        }

        public void setComposers(ArrayList<Composer> arrayList) {
            this.composers = arrayList;
        }

        public boolean isBottomBannerOff() {
            return this.bottom_banner_off == 1;
        }

        public boolean isParentalWarningEnabled() {
            return this.parentalWarning == 1;
        }

        public void setDownloadTime(long j) {
            this.downloadTime = j;
        }

        public long getDownloadTime() {
            return this.downloadTime;
        }

        public String getChannelPageAdCode() {
            return this.ad_code;
        }

        public void setIs_sponsored(String str) {
            this.is_sponsored = str;
        }

        public String getIsSponsored() {
            return this.is_sponsored;
        }

        public void setChannelPageAdCode(String str) {
            this.ad_code = str;
        }

        public String getBusinessObjId() {
            return this.albumId;
        }

        public void setBusinessObjId(String str) {
            this.albumId = str;
        }

        public String getSeokey() {
            return this.seokey;
        }

        public void setSeokey(String str) {
            this.seokey = str;
        }

        public String getEnglishName() {
            return Constants.a(this.title);
        }

        public String getName() {
            return Constants.a(this.title, this.language);
        }

        public String getRawName() {
            return this.title;
        }

        public void setName(String str) {
            super.setName(str);
            this.title = str;
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

        public ArrayList<Artist> getPrimaryartist() {
            return this.primaryartist;
        }

        public void setPrimaryartist(ArrayList<Artist> arrayList) {
            this.primaryartist = arrayList;
        }

        public ArrayList<Artist> getArtist() {
            return this.artist;
        }

        public void setArtist(Artist artist) {
            this.artist = new ArrayList();
            this.artist.add(artist);
        }

        public void setArtistArray(ArrayList<Artist> arrayList) {
            this.artist = arrayList;
        }

        public String getDeviceAvailability() {
            if (this.deviceAvailability == null) {
                this.deviceAvailability = "1";
            }
            return this.deviceAvailability;
        }

        public String getLocationAvailability() {
            if (this.locationAvailability == null) {
                this.locationAvailability = "1";
            }
            return this.locationAvailability;
        }

        public String getArtistNames() {
            String str = "";
            ArrayList primaryartist = getPrimaryartist();
            if (!(primaryartist == null || primaryartist.size() <= 0 || primaryartist.get(0) == null)) {
                str = ((Artist) primaryartist.get(0)).getName(this.language);
            }
            if (TextUtils.isEmpty(str)) {
                primaryartist = getArtist();
                if (primaryartist != null && primaryartist.size() > 0) {
                    str = ((Artist) primaryartist.get(0)).getName(this.language);
                }
            }
            return TextUtils.isEmpty(str) ? GaanaApplication.getInstance().getString(R.string.various_artists) : str;
        }

        public String getRawArtistNames() {
            String str = "";
            ArrayList primaryartist = getPrimaryartist();
            if (!(primaryartist == null || primaryartist.size() <= 0 || primaryartist.get(0) == null)) {
                str = ((Artist) primaryartist.get(0)).getRawName();
            }
            if (TextUtils.isEmpty(str)) {
                primaryartist = getArtist();
                if (primaryartist != null && primaryartist.size() > 0) {
                    str = ((Artist) primaryartist.get(0)).getRawName();
                }
            }
            return TextUtils.isEmpty(str) ? GaanaApplication.getInstance().getString(R.string.various_artists) : str;
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
    }

    public ArrayList<Album> getArrListBusinessObj() {
        return this.arrListAlbum;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListAlbum = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof Album) {
                this.arrListAlbum.add((Album) businessObject);
            }
        }
    }

    public String getUnfavorite() {
        return this.unfavorite;
    }
}
