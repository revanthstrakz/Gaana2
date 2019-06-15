package com.moe.pushlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class BatchData implements Parcelable {
    public static final Creator<BatchData> CREATOR = new Creator<BatchData>() {
        public BatchData createFromParcel(Parcel parcel) {
            return new BatchData(parcel);
        }

        public BatchData[] newArray(int i) {
            return new BatchData[i];
        }
    };
    public long _id;
    public String batchData;

    public int describeContents() {
        return 0;
    }

    public BatchData(long j, String str) {
        this._id = j;
        this.batchData = str;
    }

    public BatchData(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this._id);
        parcel.writeString(this.batchData);
    }

    public void readFromParcel(Parcel parcel) {
        this._id = parcel.readLong();
        this.batchData = parcel.readString();
    }
}
