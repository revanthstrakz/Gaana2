package com.gaana.revampeddetail.model;

import com.constants.Constants;
import com.constants.Constants.REVAMPED_DETAIL_TYPE;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class RevampedDetailObject extends BusinessObject {
    @SerializedName("album")
    private Album album;
    @SerializedName("artist")
    private Artist artist;
    @SerializedName("entityDescription")
    private String entityDescription;
    @SerializedName("playlist")
    private Playlist playlist;
    @SerializedName("radios")
    private Radio radio;
    @SerializedName("section_data")
    private ArrayList<RevampedSectionData> section_data;
    @SerializedName("specials")
    private Object specials;
    @SerializedName("status")
    @Expose
    private String status;

    public class CustomArtworks implements Serializable {
        @SerializedName("110x110")
        private String artwork110;
        @SerializedName("175x175")
        private String artwork175;
        @SerializedName("40x40")
        private String artwork40;
        @SerializedName("480x480")
        private String artwork480;
        @SerializedName("80x80")
        private String artwork80;

        public String getArtwork110() {
            return this.artwork110;
        }

        public void setArtwork110(String str) {
            this.artwork110 = str;
        }

        public String getArtwork40() {
            return this.artwork40;
        }

        public void setArtwork40(String str) {
            this.artwork40 = str;
        }

        public String getArtwork80() {
            return this.artwork80;
        }

        public void setArtwork80(String str) {
            this.artwork80 = str;
        }

        public String getArtwork175() {
            return this.artwork175;
        }

        public void setArtwork175(String str) {
            this.artwork175 = str;
        }

        public String getArtwork480() {
            return this.artwork480;
        }

        public void setArtwork480(String str) {
            this.artwork480 = str;
        }
    }

    public static class DetailArtistSection implements Serializable {
        @SerializedName("section_data_url")
        private String section_data_url;
        @SerializedName("section_title")
        private String section_title;

        public String getSection_data_url() {
            return this.section_data_url;
        }

        public void setSection_data_url(String str) {
            this.section_data_url = str;
        }

        public String getSection_title() {
            return this.section_title;
        }

        public void setSection_title(String str) {
            this.section_title = str;
        }
    }

    public static class RevampedSectionData implements Serializable {
        @SerializedName("detail_artist_sections")
        private ArrayList<DetailArtistSection> detail_artist_sections;
        @SerializedName("section_data_url")
        private String section_data_url;
        @SerializedName("section_title")
        private String section_title;
        @SerializedName("section_type")
        private int section_type;
        @SerializedName("tracks")
        private ArrayList<Track> tracks;
        @SerializedName("view_type")
        private int view_type;

        public int getSection_type() {
            return this.section_type;
        }

        public void setSection_type(int i) {
            this.section_type = i;
        }

        public int getView_type() {
            return this.view_type;
        }

        public void setView_type(int i) {
            this.view_type = i;
        }

        public String getSection_data_url() {
            return this.section_data_url;
        }

        public void setSection_data_url(String str) {
            this.section_data_url = str;
        }

        public ArrayList<Track> getTracks() {
            return this.tracks;
        }

        public void setTracks(ArrayList arrayList) {
            this.tracks = arrayList;
        }

        public String getSection_title() {
            return Constants.b(this.section_title);
        }

        public void setSection_title(String str) {
            this.section_title = str;
        }

        public ArrayList<DetailArtistSection> getDetail_artist_sections() {
            return this.detail_artist_sections;
        }

        public void setDetail_artist_sections(ArrayList<DetailArtistSection> arrayList) {
            this.detail_artist_sections = arrayList;
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public ArrayList<RevampedSectionData> getSection_data() {
        return this.section_data;
    }

    public void setSection_data(ArrayList<RevampedSectionData> arrayList) {
        this.section_data = arrayList;
    }

    public String getEntityDescription() {
        return this.entityDescription;
    }

    public void setEntityDescription(String str) {
        this.entityDescription = str;
    }

    public Album getAlbum() {
        return this.album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Object getSpecials() {
        return this.specials;
    }

    public void setSpecials(Object obj) {
        this.specials = obj;
    }

    public Radio getRadio() {
        return this.radio;
    }

    public void setRadio(Radio radio) {
        this.radio = radio;
    }

    public int getDetailType() {
        if (this.album != null) {
            return REVAMPED_DETAIL_TYPE.ALBUM.getNumVal();
        }
        if (this.playlist != null) {
            return REVAMPED_DETAIL_TYPE.PLAYLIST.getNumVal();
        }
        if (this.radio != null) {
            return REVAMPED_DETAIL_TYPE.RADIO.getNumVal();
        }
        if (this.artist != null) {
            return REVAMPED_DETAIL_TYPE.ARTIST.getNumVal();
        }
        return REVAMPED_DETAIL_TYPE.SPECIALs.getNumVal();
    }

    public String getBusinessObjId() {
        if (this.album != null) {
            return this.album.getBusinessObjId();
        }
        if (this.playlist != null) {
            return this.playlist.getBusinessObjId();
        }
        if (this.radio != null) {
            return this.radio.getBusinessObjId();
        }
        if (this.artist != null) {
            return this.artist.getBusinessObjId();
        }
        return this.album.getBusinessObjId();
    }

    public BusinessObject getBusinessObject() {
        if (this.album != null) {
            return this.album;
        }
        if (this.playlist != null) {
            return this.playlist;
        }
        if (this.artist != null) {
            return this.artist;
        }
        if (this.radio != null) {
            return this.radio;
        }
        return this.album;
    }

    public String getBusinessObjectString() {
        if (this.album != null) {
            return "Revamped Album";
        }
        if (this.playlist != null) {
            return "Revamped Playlist";
        }
        if (this.artist != null) {
            return "Revamped Artist";
        }
        return this.radio != null ? "Revamped Radio" : "Revamped Specials";
    }

    public String getAtw() {
        if (this.album != null) {
            return this.album.getArtwork();
        }
        if (this.playlist != null) {
            return this.playlist.getArtwork();
        }
        if (this.radio != null) {
            return this.radio.getArtwork();
        }
        return this.artist != null ? this.artist.getAtw() : "";
    }

    public ArrayList<BusinessObject> getTrackListifAvailable() {
        ArrayList arrayList = new ArrayList();
        if ((getDetailType() == REVAMPED_DETAIL_TYPE.ALBUM.getNumVal() || getDetailType() == REVAMPED_DETAIL_TYPE.PLAYLIST.getNumVal()) && this.section_data != null && this.section_data.size() > 0) {
            for (int i = 0; i < this.section_data.size(); i++) {
                RevampedSectionData revampedSectionData = (RevampedSectionData) this.section_data.get(i);
                if (revampedSectionData.getTracks() != null && revampedSectionData.getTracks().size() > 0) {
                    arrayList.clear();
                    arrayList.addAll(revampedSectionData.getTracks());
                }
            }
        }
        return arrayList;
    }
}
