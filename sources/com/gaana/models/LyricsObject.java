package com.gaana.models;

import com.constants.Constants;
import com.gaana.models.Tracks.Track.Artist;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class LyricsObject implements Serializable {
    private static final long serialVersionUID = 1;
    private String id;
    private String language = null;
    private int lyricsType;
    @SerializedName("lyrics_type")
    private String lyricsTypeString;
    @SerializedName("lyrics_url")
    private String lyricsUrl;
    private String trackAlbumName;
    private ArrayList<Artist> trackArtistList;
    private String trackName;

    public void setId(String str) {
        this.id = str;
    }

    public void setLyricsUrl(String str) {
        this.lyricsUrl = str;
    }

    public void setLyricsType(int i) {
        this.lyricsType = i;
    }

    public void setTrackName(String str) {
        this.trackName = str;
    }

    public void setTrackAlbumName(String str) {
        this.trackAlbumName = str;
    }

    public void setTrackArtistName(ArrayList<Artist> arrayList) {
        this.trackArtistList = arrayList;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getId() {
        return this.id;
    }

    public String getLyricsUrl() {
        return this.lyricsUrl;
    }

    public String getLyricsTypeString() {
        return this.lyricsTypeString;
    }

    public int getLyricsType() {
        return this.lyricsType;
    }

    public String getTrackAlbumName() {
        return Constants.a(this.trackAlbumName, this.language);
    }

    public String getArtistNames() {
        String str = "";
        if (this.trackArtistList == null) {
            return str;
        }
        for (int i = 0; i < this.trackArtistList.size(); i++) {
            StringBuilder stringBuilder;
            if (i != 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(", ");
                str = stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(Constants.a(((Artist) this.trackArtistList.get(i)).name, this.language));
            str = stringBuilder.toString();
        }
        return str;
    }

    public String getTrackName() {
        return Constants.a(this.trackName, this.language);
    }

    public String getLanguage() {
        return this.language;
    }
}
