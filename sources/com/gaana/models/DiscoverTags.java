package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class DiscoverTags extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("tags")
    private ArrayList<DiscoverTag> arrListDiscover;

    public static class DiscoverTag extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("favorite_count")
        private String favorite_count;
        private String formatedName = "";
        private int position = -1;
        @SerializedName("image")
        private String tagArtwork;
        @SerializedName("entity_type")
        private String tagEntityType;
        @SerializedName("id")
        private String tagId;
        @SerializedName("name")
        private String tagName;

        public int getPosition() {
            return this.position;
        }

        public void setPosition(int i) {
            this.position = i;
        }

        public String getBusinessObjId() {
            return this.tagId;
        }

        public void setBusinessObjId(String str) {
            this.tagId = str;
        }

        public String getName() {
            return Constants.b(this.tagName);
        }

        public String getRawName() {
            return this.tagName;
        }

        public String getEnglishName() {
            return Constants.a(this.tagName);
        }

        public void setName(String str) {
            this.tagName = str;
        }

        public String getTagEntityType() {
            return this.tagEntityType;
        }

        public void setTagEntityType(String str) {
            this.tagEntityType = str;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.tagArtwork;
            }
            return getAtw();
        }

        public void setArtwork(String str) {
            this.tagArtwork = str;
        }

        public String getFavorite_count() {
            return this.favorite_count;
        }

        public void setFavoriteCount(String str) {
            this.favorite_count = str;
        }

        public String getFormatedName() {
            if (TextUtils.isEmpty(this.formatedName)) {
                this.formatedName = Util.p(this.tagName);
            }
            return this.formatedName;
        }
    }

    public ArrayList<DiscoverTag> getArrListBusinessObj() {
        return this.arrListDiscover;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListDiscover = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof DiscoverTag) {
                this.arrListDiscover.add((DiscoverTag) businessObject);
            }
        }
    }
}
