package com.gaana.login;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class GaanaMiniSubDetails implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("artwork")
    private String artwork;
    @SerializedName("count")
    private String count;
    @SerializedName("desc")
    private String desc;
    @SerializedName("entity_id")
    private String entity_id;
    @SerializedName("entity_type")
    private String entity_type;
    @SerializedName("playlist_id")
    private String playlistId;
    @SerializedName("title")
    private String title;
    @SerializedName("valid_upto")
    private String valid_upto;

    public String getEntityType() {
        return this.entity_type;
    }

    public String getEntityId() {
        return this.entity_id;
    }

    public String getValidUpto() {
        return this.valid_upto;
    }

    public String getArtwork() {
        return this.artwork;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public String getDownloadLimitCount() {
        return this.count;
    }
}
