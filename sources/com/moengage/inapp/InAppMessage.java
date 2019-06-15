package com.moengage.inapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.view.View;
import com.moengage.core.Logger;

public class InAppMessage implements Parcelable {
    public static final Creator<InAppMessage> CREATOR = new Creator<InAppMessage>() {
        public InAppMessage createFromParcel(Parcel parcel) {
            return new InAppMessage(parcel);
        }

        public InAppMessage[] newArray(int i) {
            return new InAppMessage[i];
        }
    };
    public static final String INAPP_ALIGN_BOTTOM = "bottom";
    public static final String INAPP_ALIGN_CENTER = "center";
    public static final String INAPP_ALIGN_EMBED = "embedded";
    public static final String INAPP_ALIGN_FULL = "full";
    public static final String INAPP_ALIGN_SELF = "self_handled";
    public static final String INAPP_ALIGN_TOP = "top";
    public static final String INAPP_TYPE_ADVANCED = "advanced";
    public static final String INAPP_TYPE_GENERAL = "general";
    public static final String INAPP_TYPE_LINKED = "linked";
    public static final String INAPP_TYPE_SELF_HANDLED = "self_handled";
    public static final String INAPP_TYPE_SMART = "smart";
    public static final String INAPP_TYPE_TEST = "test";
    public String content;
    public String dimStyle;
    public Rules rules;
    public String status;
    public View theComposedView;

    public enum ALIGN_TYPE {
        EMBED,
        CENTER,
        TOP,
        BOTTOM,
        FULL,
        SELF;

        public String toString() {
            return name();
        }
    }

    public static class Rules {
        public long _id;
        public ALIGN_TYPE alignType;
        public long autoDismiss;
        public String campaignId;
        public boolean cancelable;
        public String context;
        public int entryAnimation;
        public int exitAnimation;
        public boolean isActive;
        public boolean isClicked;
        public boolean isShowing;
        public long lastShown;
        public long lastUpdatedTime;
        public int maxTimes;
        public long minmumDelay;
        public boolean persistent;
        public int priority;
        public String showOnlyIn;
        public int shownCount;
        public long ttl;
        public TYPE type;
    }

    public enum TYPE {
        GENERAL,
        ADVANCED,
        SELF_HANDLED,
        LINKED,
        SMART,
        TEST;

        public String toString() {
            return name();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.content);
        parcel.writeString(this.status);
        parcel.writeString(this.rules.alignType.toString());
        parcel.writeString(this.rules.type.toString());
        parcel.writeLong(this.rules._id);
        parcel.writeString(this.rules.campaignId);
        parcel.writeLong(this.rules.ttl);
        parcel.writeLong(this.rules.minmumDelay);
        parcel.writeInt(this.rules.maxTimes);
        parcel.writeInt(this.rules.shownCount);
        parcel.writeInt(this.rules.persistent);
        parcel.writeInt(this.rules.priority);
        parcel.writeInt(this.rules.isActive);
        parcel.writeString(this.rules.context);
        parcel.writeLong(this.rules.lastShown);
        parcel.writeInt(this.rules.isClicked);
        parcel.writeLong(this.rules.autoDismiss);
        parcel.writeInt(this.rules.cancelable);
        parcel.writeInt(this.rules.isShowing);
        parcel.writeString(this.rules.showOnlyIn);
        parcel.writeString(this.dimStyle);
    }

    public InAppMessage(Parcel parcel) {
        if (this.rules == null) {
            this.rules = new Rules();
            this.rules.cancelable = true;
        }
        readFromParcel(parcel);
        this.rules = new Rules();
        this.rules.cancelable = true;
    }

    public InAppMessage() {
        this.rules = new Rules();
        this.rules.cancelable = true;
    }

    public void readFromParcel(Parcel parcel) {
        this.content = parcel.readString();
        this.status = parcel.readString();
        if (this.rules != null) {
            this.rules.alignType = ALIGN_TYPE.valueOf(parcel.readString());
            this.rules.type = TYPE.valueOf(parcel.readString());
            this.rules._id = parcel.readLong();
            this.rules.campaignId = parcel.readString();
            this.rules.ttl = parcel.readLong();
            this.rules.minmumDelay = parcel.readLong();
            this.rules.maxTimes = parcel.readInt();
            this.rules.shownCount = parcel.readInt();
            boolean z = false;
            this.rules.persistent = parcel.readInt() == 1;
            this.rules.priority = parcel.readInt();
            this.rules.isActive = parcel.readInt() == 1;
            this.rules.context = parcel.readString();
            this.rules.lastShown = parcel.readLong();
            this.rules.isClicked = parcel.readInt() == 1;
            this.rules.autoDismiss = parcel.readLong();
            this.rules.cancelable = parcel.readInt() == 1;
            Rules rules = this.rules;
            if (parcel.readInt() == 1) {
                z = true;
            }
            rules.isShowing = z;
            this.rules.showOnlyIn = parcel.readString();
            this.dimStyle = parcel.readString();
        }
    }

    public void dump() {
        try {
            StringBuilder stringBuilder = new StringBuilder("InAppMessage: Details -> {");
            if (this.rules != null) {
                stringBuilder.append("\n| rules.type: ");
                stringBuilder.append(this.rules.type);
                stringBuilder.append(" | rules._id: ");
                stringBuilder.append(this.rules._id);
                stringBuilder.append(" | rules.campaignId: ");
                stringBuilder.append(this.rules.campaignId);
                stringBuilder.append(" | rules.ttl: ");
                stringBuilder.append(this.rules.ttl);
                stringBuilder.append(" | rules.minmumDelay: ");
                stringBuilder.append(this.rules.minmumDelay);
                stringBuilder.append(" | rules.maxTimes: ");
                stringBuilder.append(this.rules.maxTimes);
                stringBuilder.append(" | rules.shownCount: ");
                stringBuilder.append(this.rules.shownCount);
                stringBuilder.append(" | rules.persistent: ");
                stringBuilder.append(this.rules.persistent);
                stringBuilder.append(" | rules.priority: ");
                stringBuilder.append(this.rules.priority);
                stringBuilder.append(" | rules.isActive: ");
                stringBuilder.append(this.rules.isActive);
                stringBuilder.append(" | rules.context: ");
                stringBuilder.append(this.rules.context);
                stringBuilder.append(" | rules.lastShown: ");
                stringBuilder.append(this.rules.lastShown);
                stringBuilder.append(" | rules.isClicked: ");
                stringBuilder.append(this.rules.isClicked);
                stringBuilder.append(" | rules.autoDismiss: ");
                stringBuilder.append(this.rules.autoDismiss);
                stringBuilder.append(" | rules.cancelable: ");
                stringBuilder.append(this.rules.cancelable);
                stringBuilder.append(" | rules.isShowing: ");
                stringBuilder.append(this.rules.isShowing);
                stringBuilder.append(" | rules.showOnlyIn: ");
                stringBuilder.append(this.rules.showOnlyIn);
            } else {
                stringBuilder.append(" No Rules found for the InApp Message");
            }
            stringBuilder.append("\n| content: ");
            stringBuilder.append(this.content);
            stringBuilder.append("\n| status: ");
            stringBuilder.append(this.status);
            if (this.rules.alignType != null) {
                stringBuilder.append("\n| alignType: : ");
                stringBuilder.append(this.rules.alignType.toString());
            }
            stringBuilder.append("\n| dimStyle: : ");
            stringBuilder.append(this.dimStyle);
            stringBuilder.append("}");
            Logger.v(stringBuilder.toString());
        } catch (Exception e) {
            Logger.e("InAppMessage", e);
        }
    }

    public void setInAppType(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.e("InAppMessage: INAPP type");
            return;
        }
        if (INAPP_TYPE_GENERAL.equals(str)) {
            this.rules.type = TYPE.GENERAL;
        } else if (INAPP_TYPE_LINKED.equals(str)) {
            this.rules.type = TYPE.LINKED;
        } else if (INAPP_TYPE_ADVANCED.equals(str)) {
            this.rules.type = TYPE.ADVANCED;
        } else if ("self_handled".equals(str)) {
            this.rules.type = TYPE.SELF_HANDLED;
        } else if (INAPP_TYPE_SMART.equals(str)) {
            this.rules.type = TYPE.SMART;
        } else if (INAPP_TYPE_TEST.equals(str)) {
            this.rules.type = TYPE.TEST;
        }
    }

    public String getInAppType() {
        if (this.rules.type == TYPE.LINKED) {
            return INAPP_TYPE_LINKED;
        }
        if (this.rules.type == TYPE.ADVANCED) {
            return INAPP_TYPE_ADVANCED;
        }
        if (this.rules.type == TYPE.SELF_HANDLED) {
            return "self_handled";
        }
        return this.rules.type == TYPE.SMART ? INAPP_TYPE_GENERAL : INAPP_TYPE_GENERAL;
    }

    public void setAlignType(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.e("InAppMessage: no align type");
            return;
        }
        if ("center".equals(str)) {
            this.rules.alignType = ALIGN_TYPE.CENTER;
        } else if (INAPP_ALIGN_TOP.equals(str)) {
            this.rules.alignType = ALIGN_TYPE.TOP;
        } else if (INAPP_ALIGN_BOTTOM.equals(str)) {
            this.rules.alignType = ALIGN_TYPE.BOTTOM;
        } else if (INAPP_ALIGN_FULL.equals(str)) {
            this.rules.alignType = ALIGN_TYPE.FULL;
        } else if (INAPP_ALIGN_EMBED.equals(str)) {
            this.rules.alignType = ALIGN_TYPE.EMBED;
        } else if ("self_handled".equals(str)) {
            this.rules.alignType = ALIGN_TYPE.SELF;
        }
    }

    public String getAlignType() {
        if (this.rules.alignType == ALIGN_TYPE.TOP) {
            return INAPP_ALIGN_TOP;
        }
        if (this.rules.alignType == ALIGN_TYPE.BOTTOM) {
            return INAPP_ALIGN_BOTTOM;
        }
        if (this.rules.alignType == ALIGN_TYPE.FULL) {
            return INAPP_ALIGN_FULL;
        }
        if (this.rules.alignType == ALIGN_TYPE.EMBED) {
            return INAPP_ALIGN_EMBED;
        }
        return this.rules.alignType == ALIGN_TYPE.SELF ? "self_handled" : "center";
    }
}
