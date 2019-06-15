package com.moe.pushlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import org.json.JSONObject;

public class Event implements Parcelable {
    public static final Creator<Event> CREATOR = new Creator<Event>() {
        public Event createFromParcel(Parcel parcel) {
            return new Event(parcel);
        }

        public Event[] newArray(int i) {
            return new Event[i];
        }
    };
    public long _id;
    public String details;
    public long gtime;

    public int describeContents() {
        return 0;
    }

    public Event() {
        this(-1, null);
    }

    public Event(int i, String str) {
        this(i, -1, str);
    }

    public Event(int i, long j, String str) {
        if (i != -1) {
            this._id = (long) i;
        }
        if (-1 != j) {
            this.gtime = j;
        } else {
            this.gtime = System.currentTimeMillis();
        }
        this.details = str;
    }

    public Event(String str) {
        this(-1, str);
    }

    public Event(JSONObject jSONObject) {
        this(-1, jSONObject.toString());
    }

    public Event(Parcel parcel) {
        readFromParcel(parcel);
    }

    public Event(String str, JSONObject jSONObject) {
        this(MoEHelperUtils.getDatapointJSON(str, jSONObject));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this._id);
        parcel.writeString(this.details);
    }

    public void readFromParcel(Parcel parcel) {
        this._id = parcel.readLong();
        this.details = parcel.readString();
    }
}
