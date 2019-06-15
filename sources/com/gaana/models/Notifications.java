package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Notifications extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("notifications")
    private ArrayList<Notification> arrListNotification;
    @SerializedName("freshNoficationsCount")
    private int freshNoficationsCount = 0;
    private Date lastRetrievedDate = new Date();

    public static class Notification extends BusinessObject {
        private static final long serialVersionUID = -3473097501363228303L;
        @SerializedName("action_url")
        private String action;
        @SerializedName("action_url_mobile")
        private String action_url_mobile;
        @SerializedName("follow_crowned")
        private int follow_crowned = 0;
        private boolean isQuestionAnswered = false;
        private boolean isViewed = false;
        @SerializedName("itemartwork")
        private String itemartwork;
        @SerializedName("itemid")
        private String itemid;
        @SerializedName("message")
        private String message;
        private String messageDetails;
        @SerializedName("nid")
        private String notification_id;
        @SerializedName("notification_src")
        private String notification_src;
        @SerializedName("ntype")
        private String notification_type;
        @SerializedName("seen")
        private int seen = 0;
        long timeStampInMilliSeconds = 0;
        @SerializedName("timestamp")
        private String timestamp;
        @SerializedName("type")
        private String type;

        public Notification(String str, String str2, String str3, String str4, String str5) {
            this.message = str;
            this.type = str2;
            this.action = str3;
            this.itemid = str4;
            this.name = str5;
        }

        public void setNotificationId(String str) {
            this.notification_id = str;
        }

        public void setNotificationType(String str) {
            this.notification_type = str;
        }

        public void setType(String str) {
            this.type = str;
        }

        public void setItemid(String str) {
            this.itemid = str;
        }

        public void setAction(String str) {
            this.action = str;
        }

        public void setAction_url_mobile(String str) {
            this.action_url_mobile = str;
        }

        public int getFollow_crowned() {
            return this.follow_crowned;
        }

        public void setFollow_crowned(int i) {
            this.follow_crowned = i;
        }

        public void setItemartwork(String str) {
            this.itemartwork = str;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public void setMessageDetails(String str) {
            this.messageDetails = str;
        }

        public void setTimestamp(long j) {
            this.timeStampInMilliSeconds = j;
            this.timestamp = Long.toString(j / 1000);
        }

        public void setTimestamp(String str) {
            this.timestamp = str;
        }

        public void setNotificationSrc(String str) {
            this.notification_src = str;
        }

        public void setSeen(int i) {
            this.seen = i;
        }

        public void setViewed(boolean z) {
            this.isViewed = z;
        }

        public String getNotificationId() {
            return this.notification_id;
        }

        public String getNotificationType() {
            return this.notification_type;
        }

        public String getType() {
            return this.type;
        }

        public String getItemid() {
            return this.itemid;
        }

        public String getAction() {
            return this.action;
        }

        public String getActionUrlMobile() {
            return this.action_url_mobile;
        }

        public String getItemartwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.itemartwork;
            }
            return getAtw();
        }

        public String getMessage() {
            return Constants.b(this.message);
        }

        public String getMessageDetails() {
            return this.messageDetails;
        }

        public String getTimeStamp() {
            return this.timestamp;
        }

        public long getTimeStampInMilliSeconds() {
            if (this.timeStampInMilliSeconds > 0) {
                return this.timeStampInMilliSeconds;
            }
            return Long.parseLong(this.timestamp) * 1000;
        }

        public String getNotificationSrc() {
            return this.notification_src;
        }

        public String getName() {
            return Constants.b(this.name);
        }

        public boolean hasSeen() {
            if (this.seen != 1) {
                return false;
            }
            this.isViewed = true;
            return true;
        }

        public boolean isViewed() {
            return this.isViewed;
        }

        public String getBusinessObjId() {
            return getItemid();
        }
    }

    public void setLatestNotificationIsViewed() {
        if (this.arrListNotification != null && this.arrListNotification.get(0) != null) {
            ((Notification) this.arrListNotification.get(0)).setViewed(true);
        }
    }

    public boolean isLatestNotificationViewed() {
        if (this.arrListNotification == null || this.arrListNotification.get(0) == null) {
            return false;
        }
        return ((Notification) this.arrListNotification.get(0)).isViewed();
    }

    public int getFreshNotificationsCount() {
        return this.freshNoficationsCount;
    }

    public void setFreshNoficationsCount(int i) {
        this.freshNoficationsCount = i;
    }

    public ArrayList<Notification> getArrListBusinessObj() {
        return this.arrListNotification;
    }

    public void setArrListBusinessObj(ArrayList<BusinessObject> arrayList) {
        this.arrListNotification = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            addNotificationToList((Notification) ((BusinessObject) it.next()));
        }
    }

    public void addNotificationToList(Notification notification) {
        if (this.arrListNotification == null) {
            this.arrListNotification = new ArrayList();
        }
        if (notification != null) {
            int i = 0;
            if (this.arrListNotification.size() == 0) {
                this.arrListNotification.add(0, notification);
            } else if (this.arrListNotification.size() >= 1) {
                int i2 = 0;
                while (i2 < this.arrListNotification.size()) {
                    if (notification.getNotificationId() != null && notification.getNotificationId().equalsIgnoreCase(String.valueOf(1003)) && !TextUtils.isEmpty(notification.getMessageDetails()) && ((Notification) this.arrListNotification.get(i2)).getType() != null && ((Notification) this.arrListNotification.get(i2)).getType().equalsIgnoreCase(notification.getType()) && notification.getTimeStampInMilliSeconds() > ((Notification) this.arrListNotification.get(i2)).getTimeStampInMilliSeconds()) {
                        this.arrListNotification.set(i2, notification);
                        return;
                    } else if ((notification.getNotificationId() != null && notification.getNotificationId().equalsIgnoreCase(((Notification) this.arrListNotification.get(i2)).getNotificationId())) || (notification.getNotificationId() == null && notification.getMessage() != null && notification.getActionUrlMobile() != null && ((Notification) this.arrListNotification.get(i2)).getMessage() != null && ((Notification) this.arrListNotification.get(i2)).getMessage().equalsIgnoreCase(notification.getMessage()) && ((Notification) this.arrListNotification.get(i2)).getActionUrlMobile().equalsIgnoreCase(notification.getActionUrlMobile()))) {
                        return;
                    } else {
                        if (notification.getTimeStampInMilliSeconds() > ((Notification) this.arrListNotification.get(i2)).getTimeStampInMilliSeconds()) {
                            this.arrListNotification.add(i2, notification);
                            i = 1;
                            break;
                        }
                        i2++;
                    }
                }
                if (i == 0) {
                    this.arrListNotification.add(notification);
                }
            }
            if (!notification.hasSeen()) {
                this.freshNoficationsCount++;
            }
        }
    }

    public void setAllNotificationsSeen() {
        Iterator it = getArrListBusinessObj().iterator();
        int i = 0;
        while (it.hasNext()) {
            Notification notification = (Notification) it.next();
            if (!notification.getType().equalsIgnoreCase("FOLLOW_REQUEST") || notification.hasSeen()) {
                notification.setSeen(1);
            } else {
                i++;
            }
        }
        setFreshNoficationsCount(i);
    }

    public void notifSeenAtPosition(int i) {
        ((Notification) getArrListBusinessObj().get(i)).setSeen(1);
        i = this.freshNoficationsCount - 1;
        this.freshNoficationsCount = i;
        if (i <= 0) {
            setFreshNoficationsCount(0);
        }
    }

    public Date getRetrievedDate() {
        return this.lastRetrievedDate;
    }

    public void setRetrievedDate(Date date) {
        this.lastRetrievedDate = date;
    }
}
