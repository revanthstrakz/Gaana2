package com.gaana.models;

import com.gaana.models.Tracks.Track;
import com.utilities.Util;

public class LocalTrack extends Track {
    private String localHashValue = null;

    public String getLocalHashValue() {
        return Util.a(getName(), getDuration(), getArtistNames());
    }

    public void setLocalHashValue(String str) {
        this.localHashValue = str;
    }
}
