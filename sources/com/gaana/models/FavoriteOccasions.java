package com.gaana.models;

import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;

public class FavoriteOccasions extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("entities")
    private ArrayList<FavoriteOccasion> arrListOccasions;
    @SerializedName("unfavourite")
    private String unfavorite;

    public static class FavoriteOccasion extends BusinessObject {
        @SerializedName("artwork")
        String artwork;
        @SerializedName("entity_id")
        String entityId;
        @SerializedName("entity_type")
        String entityType;
        @SerializedName("language")
        String language;
        @SerializedName("name")
        String name;
        @SerializedName("seokey")
        String seoKey;

        public String getEntityId() {
            return this.entityId;
        }

        public void setEntityId(String str) {
            this.entityId = str;
        }

        public String getEntityType() {
            return this.entityType;
        }

        public void setEntityType(String str) {
            this.entityType = str;
        }

        public String getName() {
            return Constants.a(this.name, this.language);
        }

        public String getRawName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getSeoKey() {
            return this.seoKey;
        }

        public void setSeoKey(String str) {
            this.seoKey = str;
        }

        public String getLanguage() {
            return this.language;
        }

        public void setLanguage(String str) {
            this.language = str;
        }

        public String getArtwork() {
            return this.artwork;
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }
    }

    public ArrayList<FavoriteOccasion> getArrListBusinessObj() {
        return this.arrListOccasions;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListOccasions = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof FavoriteOccasion) {
                this.arrListOccasions.add((FavoriteOccasion) businessObject);
            }
        }
    }

    public String getUnfavorite() {
        return this.unfavorite;
    }
}
