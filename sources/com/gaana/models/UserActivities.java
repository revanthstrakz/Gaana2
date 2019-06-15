package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.models.ProfileUsers.ProfileUser;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;

public class UserActivities extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("activities")
    private ArrayList<UserActivity> arrListActivity;
    @SerializedName("Fbstatus")
    private String fbStatus;

    public static class UserActivity extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("activity_id")
        private String activityId;
        @SerializedName("activity_tag")
        private String activityTag;
        @SerializedName("activity_time")
        private String activity_time;
        @SerializedName("item_artwork")
        private String itemArtwork;
        @SerializedName("item_id")
        private String itemId;
        @SerializedName("item_name")
        private String itemName;
        @SerializedName("item_type")
        private String itemType;
        @SerializedName("radio_type")
        private String radioType;
        @SerializedName("users")
        private ArrayList<ProfileUser> users;

        public String getRadioType() {
            return this.radioType;
        }

        public ArrayList<ProfileUser> getUsers() {
            return this.users != null ? this.users : null;
        }

        public String getActivityTag() {
            return this.activityTag;
        }

        public String getItemType() {
            return this.itemType;
        }

        public String getItemId() {
            return this.itemId;
        }

        public String getItemName() {
            return Constants.b(this.itemName);
        }

        public String getItemArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.itemArtwork;
            }
            return getAtw();
        }

        public String getActivityId() {
            return this.activityId;
        }

        public String getActivityTimeStamp() {
            return this.activity_time;
        }

        public void setActivity_time(String str) {
            this.activity_time = str;
        }

        public void setItemArtwork(String str) {
            this.itemArtwork = str;
        }

        public void setItemName(String str) {
            this.itemName = str;
        }

        public void setItemId(String str) {
            this.itemId = str;
        }

        public void setItemType(String str) {
            this.itemType = str;
        }

        public void setActivityTag(String str) {
            this.activityTag = str;
        }

        public void setActivityId(String str) {
            this.activityId = str;
        }
    }

    public ArrayList<UserActivity> getArrListBusinessObj() {
        return this.arrListActivity;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListActivity = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof UserActivity) {
                this.arrListActivity.add((UserActivity) businessObject);
            }
        }
    }

    public String getFbStatus() {
        return this.fbStatus;
    }
}
