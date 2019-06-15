package com.gaana.models;

import com.e.a.e.c;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class DownloadSyncArrays extends BusinessObject {
    @SerializedName("added_items")
    private DownloadSyncArray addedArray = new DownloadSyncArray();
    @SerializedName("deleted_items")
    private DownloadSyncArray deletedArray = new DownloadSyncArray();
    @SerializedName("last_sync_time")
    private String last_sync_time;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private String result;
    @SerializedName("smart_download")
    private ArrayList<String> smart_download;
    @SerializedName("status")
    private String status;
    @SerializedName("sync_required")
    private String sync_required;
    @SerializedName("total_downloads")
    private String total_downloads;

    public static class DownloadSyncArray {
        @SerializedName("downloaded_albums")
        private ArrayList<String> arrListAlbum = new ArrayList();
        @SerializedName("downloaded_playlists")
        private ArrayList<String> arrListPlaylist = new ArrayList();
        @SerializedName("downloaded_tracks")
        private ArrayList<String> arrListTracks = new ArrayList();

        public ArrayList<String> getArrListAlbums() {
            return this.arrListAlbum;
        }

        public ArrayList<String> getArrListPlaylists() {
            return this.arrListPlaylist;
        }

        public ArrayList<String> getArrListTracks() {
            return this.arrListTracks;
        }

        /* Access modifiers changed, original: 0000 */
        public void addToSync(String str, int i) {
            if (i == c.c) {
                this.arrListTracks.add(str);
            } else if (i == c.a) {
                this.arrListAlbum.add(str);
            } else if (i == c.b) {
                this.arrListPlaylist.add(str);
            }
        }
    }

    public ArrayList<String> getSmart_download() {
        return this.smart_download;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public String getResult() {
        return this.result;
    }

    public DownloadSyncArray getAddedArray() {
        return this.addedArray;
    }

    public void setAddedArray(DownloadSyncArray downloadSyncArray) {
        this.addedArray = downloadSyncArray;
    }

    public DownloadSyncArray getDeletedArray() {
        return this.deletedArray;
    }

    public void setDeletedArray(DownloadSyncArray downloadSyncArray) {
        this.deletedArray = downloadSyncArray;
    }

    public String getLastSyncTime() {
        return this.last_sync_time;
    }

    public void setLastSyncTime(String str) {
        this.last_sync_time = str;
    }

    public boolean isSyncRequired() {
        return (this.sync_required == null || this.sync_required.equalsIgnoreCase("NO")) ? false : true;
    }

    public void setSyncRequired(String str) {
        this.sync_required = str;
    }

    public String getTotalDownloads() {
        return this.total_downloads;
    }

    public void setTotalDownloads(String str) {
        this.total_downloads = str;
    }

    public void addToSync(int i, int i2, int i3) {
        if (i2 == 0) {
            this.deletedArray.addToSync(String.valueOf(i), i3);
        } else {
            this.addedArray.addToSync(String.valueOf(i), i3);
        }
    }
}
