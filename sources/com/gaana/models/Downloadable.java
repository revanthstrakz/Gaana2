package com.gaana.models;

import com.managers.DownloadManager.DownloadStatus;

public interface Downloadable {
    DownloadStatus getDownloadStatus();

    Boolean isOffline();

    void setDownloadStatus(DownloadStatus downloadStatus);

    void setOfflineStatus(Boolean bool);
}
