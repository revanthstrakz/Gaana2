package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.models.flatbuffer.GaanaEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.managers.DownloadManager.DownloadStatus;
import java.util.ArrayList;
import java.util.List;

public class Item extends BusinessObject implements Downloadable {
    private static final long serialVersionUID = 1;
    @SerializedName("artwork")
    @Expose
    private String artwork;
    @SerializedName("artwork_medium")
    private String artwork_medium;
    @SerializedName("entity_id")
    @Expose
    private String entityId;
    @SerializedName("entity_info")
    @Expose
    private List<EntityInfo> entityInfo;
    @SerializedName("entity_type")
    @Expose
    private String entityType;
    @SerializedName("favorite_count")
    @Expose
    private long favoriteCount;
    private boolean isFbResponse;
    private boolean isRequested;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("lpid")
    private String localPlaylistId;
    @SerializedName("artworks")
    private ArrayList<String> mArtworks;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("notify_status")
    private int notify_status;
    private Object objMap;
    private long offlinePlaylistId;
    private PlaylistSourceType playlistSourceType;
    private String playlist_type;
    @SerializedName("premium_content")
    @Expose
    private String premiumContent;
    @SerializedName("sapid")
    private String sapID;
    @SerializedName("seokey")
    @Expose
    private String seokey;
    private int syncStatus;

    public enum PlaylistSourceType {
        NORMAL,
        HOURLY_PLAYLIST
    }

    public DownloadStatus getDownloadStatus() {
        return null;
    }

    public Boolean isOffline() {
        return null;
    }

    public void setDownloadStatus(DownloadStatus downloadStatus) {
    }

    public void setOfflineStatus(Boolean bool) {
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getSeokey() {
        return this.seokey;
    }

    public void setSeokey(String str) {
        this.seokey = str;
    }

    public String getName() {
        return Constants.a(this.name, this.language);
    }

    public String getEnglishName() {
        return Constants.a(this.name);
    }

    public String getRawName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getArtwork() {
        if (TextUtils.isEmpty(getAtw())) {
            return this.artwork;
        }
        return getAtw();
    }

    public String getArtworkSpecific() {
        if (TextUtils.isEmpty(this.artwork)) {
            return getAtw();
        }
        return this.artwork;
    }

    public String getArtworkMedium() {
        if (TextUtils.isEmpty(getAtw())) {
            return this.artwork_medium;
        }
        return getAtw();
    }

    public void setArtwork(String str) {
        this.artwork = str;
    }

    public void setEntityId(String str) {
        this.entityId = str;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public String getBusinessObjId() {
        return this.entityId;
    }

    public void setEntityType(String str) {
        this.entityType = str;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public long getFavoriteCount() {
        return this.favoriteCount;
    }

    public void setFavoriteCount(long j) {
        this.favoriteCount = j;
    }

    public void setPremiumContent(String str) {
        this.premiumContent = str;
    }

    public String getPremiumContent() {
        return this.premiumContent;
    }

    public List<EntityInfo> getEntityInfo() {
        return this.entityInfo;
    }

    public PlaylistSourceType getPlaylistSourceType() {
        return this.playlistSourceType;
    }

    public void setPlaylistSource(PlaylistSourceType playlistSourceType) {
        this.playlistSourceType = playlistSourceType;
    }

    public void setEntityInfo(ArrayList<EntityInfo> arrayList) {
        this.entityInfo = arrayList;
    }

    public long getOfflinePlaylistId() {
        return this.offlinePlaylistId;
    }

    public void setOfflinePlaylistId(long j) {
        this.offlinePlaylistId = j;
    }

    public String getLocalPlaylistId() {
        return this.localPlaylistId;
    }

    public void setLocalPlaylistId(String str) {
        this.localPlaylistId = str;
    }

    public String getPlaylistType() {
        if (this.playlist_type == null || this.playlist_type.length() == 0) {
            this.playlist_type = "playlist";
        }
        return this.playlist_type;
    }

    public int getSyncStatus() {
        return this.syncStatus;
    }

    public void setSyncStatus(int i) {
        this.syncStatus = i;
    }

    public String getSapID() {
        return this.sapID;
    }

    public int getNotifyStatus() {
        return this.notify_status;
    }

    public void setIsRequested(boolean z) {
        this.isRequested = z;
    }

    public boolean getIsRequested() {
        return this.isRequested;
    }

    public ArrayList<String> getArtworks() {
        return this.mArtworks;
    }

    public Item() {
        this.entityInfo = new ArrayList();
        this.playlistSourceType = PlaylistSourceType.NORMAL;
        this.syncStatus = -3;
        this.isRequested = false;
        this.isFbResponse = false;
    }

    public Item(GaanaEntity gaanaEntity) {
        this.entityInfo = new ArrayList();
        this.playlistSourceType = PlaylistSourceType.NORMAL;
        this.syncStatus = -3;
        int i = 0;
        this.isRequested = false;
        this.isFbResponse = false;
        this.entityId = gaanaEntity.entityId();
        this.entityType = gaanaEntity.entityType();
        this.seokey = gaanaEntity.seokey();
        this.name = gaanaEntity.name();
        this.artwork = gaanaEntity.artwork();
        this.artwork_medium = gaanaEntity.artworkMedium();
        this.favoriteCount = (long) gaanaEntity.favoriteCount();
        this.premiumContent = gaanaEntity.premiumContent();
        setUserFavorite(gaanaEntity.userFavorite() == 1);
        this.sapID = gaanaEntity.sapid();
        this.atw = gaanaEntity.atw();
        this.language = gaanaEntity.language();
        this.entityInfo = new ArrayList();
        for (int i2 = 0; i2 < gaanaEntity.entityInfoLength(); i2++) {
            this.entityInfo.add(new EntityInfo(gaanaEntity.entityInfo(i2)));
        }
        this.mArtworks = new ArrayList();
        while (i < gaanaEntity.artworksLength()) {
            this.mArtworks.add(gaanaEntity.artworks(i));
            i++;
        }
        this.isFbResponse = true;
    }

    public boolean isFlatBufferResponse() {
        return this.isFbResponse;
    }
}
