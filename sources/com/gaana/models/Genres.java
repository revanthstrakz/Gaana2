package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Genres extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("genre")
    private ArrayList<Genre> arrListGenre;

    public static class Genre extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("albumcount")
        private String albumCount;
        private String artwork;
        private String description;
        @SerializedName("genre_id")
        private String genreId;
        private String name;
        private String seokey;

        public String getBusinessObjId() {
            return this.genreId;
        }

        public String getSeokey() {
            return this.seokey;
        }

        public String getName() {
            return Constants.b(this.name);
        }

        public String getRawName() {
            return this.name;
        }

        public String getAlbumCount() {
            return this.albumCount;
        }

        public String getDescription() {
            return this.description;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.artwork;
            }
            return getAtw();
        }
    }

    public ArrayList<Genre> getArrListBusinessObj() {
        return this.arrListGenre;
    }
}
