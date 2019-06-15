package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class FavoriteData extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("favourites")
    private FavData favData;

    public static class FavData extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("albums")
        private String albums = "";
        @SerializedName("artists")
        private String artists = "";
        @SerializedName("playlists")
        private String playlists = "";
        @SerializedName("radios")
        private Radio radios;
        @SerializedName("tracks")
        private String tracks = "";

        public void setTracks(String str) {
            this.tracks = str;
        }

        public void setAlbums(String str) {
            this.albums = str;
        }

        public void setArtists(String str) {
            this.artists = str;
        }

        public void setPlaylists(String str) {
            this.playlists = str;
        }

        public void setRadios(Radio radio) {
            this.radios = radio;
        }

        public String getTracks() {
            return this.tracks;
        }

        public String getAlbums() {
            return this.albums;
        }

        public String getArtists() {
            return this.artists;
        }

        public String getPlaylists() {
            return this.playlists;
        }

        public Radio getRadios() {
            return this.radios;
        }
    }

    public static class Radio extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("rl")
        private String gaanaRadio = "";
        @SerializedName("rm")
        private String radioMirchi = "";

        public void setRadioMirchi(String str) {
            this.radioMirchi = str;
        }

        public void setGaanaRadio(String str) {
            this.gaanaRadio = str;
        }

        public String getGaanaRadio() {
            return this.gaanaRadio;
        }

        public String getRadioMirchi() {
            return this.radioMirchi;
        }
    }

    public FavData getFavData() {
        return this.favData;
    }

    public void setFavData(FavData favData) {
        this.favData = favData;
    }
}
