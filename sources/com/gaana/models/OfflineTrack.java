package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;

public class OfflineTrack extends BusinessObject {
    private String albumId;
    private String albumName;
    private String artistId;
    private String artistName;
    private long downloadTime;
    private String duration = "0";
    private String imageUrl;
    private int isFreeDownload = 0;
    private boolean isSelected = false;
    private int parentalWarning = 0;
    private int position = 0;
    private int smartDownload = 0;
    private String videoUrl;

    public int isFreeDownload() {
        return this.isFreeDownload;
    }

    public void setFreeDownload(int i) {
        this.isFreeDownload = i;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public OfflineTrack(String str, String str2, String str3, String str4, long j) {
        setBusinessObjId(str);
        setName(str2);
        this.artistName = str3;
        this.videoUrl = str4;
        this.downloadTime = j;
    }

    public OfflineTrack(String str, String str2, String str3) {
        setBusinessObjId(str);
        setName(str2);
        this.artistName = str3;
    }

    public void setSmartDownload(int i) {
        this.smartDownload = i;
    }

    public int getSmartDownload() {
        return this.smartDownload;
    }

    public long getDownloadTime() {
        return this.downloadTime;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public String getArtistName() {
        return Constants.a(this.artistName, this.language);
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String str) {
        this.videoUrl = str;
    }

    public String getAlbumName() {
        return Constants.a(this.albumName, this.language);
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String str) {
        this.albumId = str;
    }

    public String getArtistId() {
        return this.artistId;
    }

    public void setArtistId(String str) {
        this.artistId = str;
    }

    public String getImageUrl() {
        if (TextUtils.isEmpty(this.imageUrl)) {
            this.imageUrl = this.albumId;
        }
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public String getRawName() {
        return this.name;
    }

    public String getArtistRawName() {
        return this.artistName;
    }

    public String getAlbumRawName() {
        return this.albumName;
    }

    public void setParentalWarning(int i) {
        this.parentalWarning = i;
    }

    public boolean isParentalWarningEnabled() {
        return this.parentalWarning == 1;
    }
}
