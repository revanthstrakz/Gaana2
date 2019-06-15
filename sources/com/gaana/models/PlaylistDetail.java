package com.gaana.models;

import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.annotations.SerializedName;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PlaylistDetail extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("tracks")
    private ArrayList<Track> arrListTracks;
    @SerializedName("created_on")
    private String createdOn;
    @SerializedName("favorite_count")
    private String favoriteCount;
    @SerializedName("modified_on")
    private String modifiedOn;
    @SerializedName("playlist")
    private Playlist playlist;

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public ArrayList<Track> getArrListBusinessObj() {
        return this.arrListTracks;
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
}
