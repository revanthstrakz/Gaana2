package com.payu.custombrowser.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ReviewOrderData implements Parcelable {
    public static final Creator<ReviewOrderData> CREATOR = new Creator<ReviewOrderData>() {
        /* renamed from: a */
        public ReviewOrderData createFromParcel(Parcel parcel) {
            return new ReviewOrderData(parcel);
        }

        /* renamed from: a */
        public ReviewOrderData[] newArray(int i) {
            return new ReviewOrderData[i];
        }
    };
    private String a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public String getKey() {
        return this.a;
    }

    public String getValue() {
        return this.b;
    }

    public ReviewOrderData(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    protected ReviewOrderData(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
