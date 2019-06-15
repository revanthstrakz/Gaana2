package com.gaana.models;

import com.gaana.models.Tracks.Track;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class SongResultModel extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("tracks")
    private ArrayList<Track> trackArrayList;

    public ArrayList<Track> getTrackArrayList() {
        return this.trackArrayList;
    }
}
