package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Friends extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("friends")
    private ArrayList<Friend> arrListFriend;

    public static class Friend extends BusinessObject {
        private static final long serialVersionUID = 1;
        private String artwork;
        private String name;
        private String seokey;
        private String track_id;
        private String track_title;
        private String user_id;

        public String getBusinessObjId() {
            return this.user_id;
        }

        public String getName() {
            return this.name;
        }

        public String getFriendSeoKey() {
            return this.seokey;
        }

        public String getTrack_title() {
            return this.track_title;
        }

        public String getTrack_id() {
            return this.track_id;
        }

        public String getArtwork() {
            return this.artwork;
        }
    }

    public ArrayList<Friend> getArrListBusinessObj() {
        return this.arrListFriend;
    }
}
