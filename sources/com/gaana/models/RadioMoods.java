package com.gaana.models;

import com.constants.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class RadioMoods extends BusinessObject {
    @SerializedName("entities")
    @Expose
    private ArrayList<RadioMood> arrListItem;

    public static class RadioMood {
        @SerializedName("entity_id")
        @Expose
        private String entityId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("user_favorite")
        @Expose
        private int userFavorite;

        public String getEntityId() {
            return this.entityId;
        }

        public String getName() {
            return Constants.b(this.name);
        }

        public String getRawName() {
            return this.name;
        }

        public String getEnglishName() {
            return Constants.a(this.name);
        }

        public int getUserFavorite() {
            return this.userFavorite;
        }
    }

    public ArrayList<RadioMood> getArrListItem() {
        return this.arrListItem;
    }
}
