package com.gaana.models;

import com.constants.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class PreferedArtists extends BusinessObject {
    @SerializedName("entities")
    private ArrayList<PreferedArtist> arrListArtists;
    @SerializedName("count")
    private int count;
    @SerializedName("entityDescription")
    private String entityDescription;

    public class PreferedArtist extends BusinessObject {
        @SerializedName("artwork")
        private String artwork;
        @SerializedName("artwork_medium")
        private String artwork_medium;
        @SerializedName("entity_info")
        @Expose
        private List<EntityInfo> entityInfo = new ArrayList();
        @SerializedName("entity_id")
        private String entity_id;
        @SerializedName("name")
        private String name;
        @SerializedName("seokey")
        private String seokey;

        public String getName() {
            return Constants.a(this.name, this.language);
        }

        public List<EntityInfo> getEntityInfo() {
            return this.entityInfo;
        }

        public boolean isPrefered() {
            return isUserFavorited();
        }

        public void setIsPrefered(boolean z) {
            setUserFavorite(z);
        }

        public String getArtistId() {
            return this.entity_id;
        }
    }

    public ArrayList<PreferedArtist> getPreferedArtists() {
        return this.arrListArtists;
    }

    public String getEntityDescription() {
        return Constants.a(this.entityDescription, this.language);
    }
}
