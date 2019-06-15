package com.gaana.models;

import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class SearchAutoSuggests implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("autocomplete")
    private ArrayList<AutoComplete> autocomplete;
    @SerializedName("top_facets")
    private String top_facets;

    public static class AutoComplete implements Serializable {
        private static final long serialVersionUID = 1;
        @SerializedName("iid")
        private int iid;
        private boolean isLocalMedia = false;
        private boolean isRecentSearch = false;
        @SerializedName("ti")
        private String ti;
        @SerializedName("ty")
        private String ty;

        public boolean isLocalMedia() {
            return this.isLocalMedia;
        }

        public void setLocalMedia(boolean z) {
            this.isLocalMedia = z;
        }

        public AutoComplete(String str, String str2, int i) {
            this.ti = str;
            this.ty = str2;
            this.iid = i;
        }

        public String getTitle() {
            return Constants.b(this.ti);
        }

        public String getType() {
            return this.ty;
        }

        public String getBusinessObjectId() {
            return String.valueOf(this.iid);
        }

        public void setBusinessObjectId(int i) {
            this.iid = i;
        }

        public String toString() {
            return Constants.b(this.ti);
        }

        public boolean isRecentSearch() {
            return this.isRecentSearch;
        }

        public void setRecentSearch(boolean z) {
            this.isRecentSearch = z;
        }
    }

    public String getTopFacets() {
        return this.top_facets;
    }

    public ArrayList<AutoComplete> getAutocomplete() {
        return this.autocomplete;
    }
}
