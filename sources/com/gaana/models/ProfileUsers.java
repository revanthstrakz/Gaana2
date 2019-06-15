package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ProfileUsers extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("users")
    private ArrayList<ProfileUser> arrListUsers;

    public static class ProfileUser extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("artwork")
        private String artwork;
        @SerializedName("download_count")
        private String downloadCount;
        @SerializedName("email")
        private String email;
        @SerializedName("fullname")
        private String fullname;
        private boolean isFollowing = false;
        @SerializedName("user_id")
        private String user_id;

        public String getDownloadCount() {
            return this.downloadCount;
        }

        public String getEmail() {
            return this.email;
        }

        public boolean isFollowing() {
            return this.isFollowing;
        }

        public void setFollowing(boolean z) {
            this.isFollowing = z;
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

        public String getBusinessObjId() {
            return this.user_id;
        }

        public void setBusinessObjId(String str) {
            this.user_id = str;
        }

        public String getName() {
            return Constants.b(this.fullname);
        }

        public void setName(String str) {
            this.fullname = str;
        }
    }

    public ArrayList<ProfileUser> getArrListBusinessObj() {
        return this.arrListUsers;
    }

    public void setUsers(ArrayList<ProfileUser> arrayList) {
        this.arrListUsers = arrayList;
    }
}
