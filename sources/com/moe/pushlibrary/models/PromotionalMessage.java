package com.moe.pushlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.moengage.core.Logger;
import org.json.JSONObject;

public class PromotionalMessage implements Parcelable {
    public static final Creator<PromotionalMessage> CREATOR = new Creator<PromotionalMessage>() {
        public PromotionalMessage createFromParcel(Parcel parcel) {
            return new PromotionalMessage(parcel);
        }

        public PromotionalMessage[] newArray(int i) {
            return new PromotionalMessage[i];
        }
    };
    public long _id;
    public long gtime;
    public boolean isClicked;
    public JSONObject msg_details;
    public long ttl;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this._id);
        if (this.msg_details != null) {
            parcel.writeString(this.msg_details.toString());
        } else {
            parcel.writeString("");
        }
        parcel.writeInt(this.isClicked);
        parcel.writeLong(this.ttl);
        parcel.writeLong(this.gtime);
    }

    public PromotionalMessage(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this._id = parcel.readLong();
        String readString = parcel.readString();
        if (!TextUtils.isEmpty(readString)) {
            try {
                this.msg_details = new JSONObject(readString);
            } catch (Exception e) {
                Logger.f("PromotionalMessage : readFromParcel", e);
            }
        }
        this.isClicked = parcel.readInt() != 0;
        this.ttl = parcel.readLong();
        this.gtime = parcel.readLong();
    }
}
