package com.moe.pushlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UserAttribute implements Parcelable {
    public static final Creator<UserAttribute> CREATOR = new Creator<UserAttribute>() {
        public UserAttribute createFromParcel(Parcel parcel) {
            return new UserAttribute(parcel);
        }

        public UserAttribute[] newArray(int i) {
            return new UserAttribute[i];
        }
    };
    public String userAttributeName;
    public String userAttributeValue;

    public int describeContents() {
        return 0;
    }

    public UserAttribute(String str, String str2) {
        this.userAttributeName = str;
        this.userAttributeValue = str2;
    }

    protected UserAttribute(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.userAttributeName);
        parcel.writeString(this.userAttributeValue);
    }

    public void readFromParcel(Parcel parcel) {
        this.userAttributeName = parcel.readString();
        this.userAttributeValue = parcel.readString();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null || !(obj instanceof UserAttribute)) {
            return false;
        }
        UserAttribute userAttribute = (UserAttribute) obj;
        if (this.userAttributeName.equals(userAttribute.userAttributeName) && this.userAttributeValue.equals(userAttribute.userAttributeValue)) {
            z = true;
        }
        return z;
    }
}
