package com.gaana.revampeddetail.model;

import com.gaana.models.Albums.Album.Artist;
import com.gaana.models.Item;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class RevampedSimilarAlbumEntityInfo {
    @SerializedName("count")
    private String count;
    @SerializedName("generic_entities")
    ArrayList<GenericEntity> generic_entities;
    @SerializedName("hv")
    private String hv;
    @SerializedName("status")
    private int status;
    @SerializedName("user_token_status")
    private String user_token_status;

    public static class GenericEntity extends Item {
        @SerializedName("generic_entity_info")
        private GenericEntityInfo generic_entity_info;

        public GenericEntityInfo getGeneric_entity_info() {
            return this.generic_entity_info;
        }

        public void setGeneric_entity_info(GenericEntityInfo genericEntityInfo) {
            this.generic_entity_info = genericEntityInfo;
        }
    }

    public static class GenericEntityInfo {
        @SerializedName("parental_warning")
        private int parental_warning;
        @SerializedName("primary_artist_count")
        private int primary_artist_count;
        @SerializedName("primaryartist")
        private ArrayList<Artist> primaryartist;

        public ArrayList<Artist> getPrimaryartist() {
            return this.primaryartist;
        }

        public void setPrimaryartist(ArrayList<Artist> arrayList) {
            this.primaryartist = arrayList;
        }

        public int getParental_warning() {
            return this.parental_warning;
        }

        public void setParental_warning(int i) {
            this.parental_warning = i;
        }

        public int getPrimary_artist_count() {
            return this.primary_artist_count;
        }

        public void setPrimary_artist_count(int i) {
            this.primary_artist_count = i;
        }
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getUser_token_status() {
        return this.user_token_status;
    }

    public void setUser_token_status(String str) {
        this.user_token_status = str;
    }

    public String getHv() {
        return this.hv;
    }

    public void setHv(String str) {
        this.hv = str;
    }

    public ArrayList<GenericEntity> getGeneric_entities() {
        return this.generic_entities;
    }

    public void setGeneric_entities(ArrayList<GenericEntity> arrayList) {
        this.generic_entities = arrayList;
    }
}
