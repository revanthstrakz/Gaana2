package com.gaana.models;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class HomeAction extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("hr_list")
    private ArrayList<HrList> hrListArrayList;
    @SerializedName("status")
    private int status;
    @SerializedName("tags")
    private ArrayList<TagList> tagLists;

    public static class HrList extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("hour")
        private int hour;
        @SerializedName("taglist")
        private ArrayList<Integer> tagList;

        public int getHour() {
            return this.hour;
        }

        public void setHour(int i) {
            this.hour = i;
        }

        public ArrayList<Integer> getTagList() {
            return this.tagList;
        }

        public void setTagList(ArrayList<Integer> arrayList) {
            this.tagList = arrayList;
        }
    }

    public static class TagList extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("artwork")
        private String artwork;
        @SerializedName("entity_id")
        private String entity_id;
        @SerializedName("entity_type")
        private String entity_ype;
        @SerializedName("tag_name")
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getEntity_ype() {
            return this.entity_ype;
        }

        public void setEntity_ype(String str) {
            this.entity_ype = str;
        }

        public String getEntity_id() {
            return this.entity_id;
        }

        public void setEntity_id(String str) {
            this.entity_id = str;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.artwork;
            }
            return getAtw();
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }
    }

    public ArrayList<HrList> getHrListArrayList() {
        return this.hrListArrayList;
    }

    public void setHrListArrayList(ArrayList<HrList> arrayList) {
        this.hrListArrayList = arrayList;
    }

    public ArrayList<TagList> getTagLists() {
        return this.tagLists;
    }

    public void setTagLists(ArrayList<TagList> arrayList) {
        this.tagLists = arrayList;
    }
}
