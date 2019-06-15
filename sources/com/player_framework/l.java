package com.player_framework;

import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.Tracks.Track;
import com.managers.DownloadManager;

public class l {
    public String a(Track track, boolean z) {
        if (track.isLocalMedia()) {
            return LocalMediaManager.getInstance(GaanaApplication.getContext()).getLocalTrackPath(track.getBusinessObjId());
        }
        return DownloadManager.c().b(Integer.parseInt(track.getBusinessObjId()), z);
    }
}
