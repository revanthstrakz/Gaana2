package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Iterator;

public class ReferralUserActivities extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("activity")
    private ArrayList<ReferralUserActivity> arrListActivity;

    public static class ReferralUserActivity extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("created_on")
        private String createdOn;
        @SerializedName("invitee_artwork")
        private String inviteeArtwork;
        @SerializedName("invitee_id")
        private String inviteeId;
        @SerializedName("id")
        private String itemId;
        @SerializedName("message")
        private String message;

        public String getItemId() {
            return this.itemId;
        }

        public String getInviteeId() {
            return this.inviteeId;
        }

        public String getMessage() {
            return Constants.b(this.message);
        }

        public String getInviteeArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.inviteeArtwork;
            }
            return getAtw();
        }

        public String getCreatedOn() {
            return this.createdOn;
        }
    }

    public ArrayList<ReferralUserActivity> getArrListBusinessObj() {
        return this.arrListActivity;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListActivity = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof ReferralUserActivity) {
                this.arrListActivity.add((ReferralUserActivity) businessObject);
            }
        }
    }
}
