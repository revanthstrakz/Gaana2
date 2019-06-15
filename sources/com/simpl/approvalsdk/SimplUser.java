package com.simpl.approvalsdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SimplUser implements Parcelable {
    public static final Creator<SimplUser> CREATOR = new Creator<SimplUser>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new SimplUser(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new SimplUser[i];
        }
    };
    private String mEmailAddress;
    private String mPhoneNumber;

    protected SimplUser(Parcel parcel) {
        this.mEmailAddress = parcel.readString();
        this.mPhoneNumber = parcel.readString();
    }

    public SimplUser(String str, String str2) {
        this.mEmailAddress = str;
        this.mPhoneNumber = str2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SimplUser simplUser = (SimplUser) obj;
        return this.mEmailAddress.equals(simplUser.mEmailAddress) && this.mPhoneNumber.equals(simplUser.mPhoneNumber);
    }

    public String getEmailAddress() {
        return this.mEmailAddress;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public int hashCode() {
        return (31 * this.mEmailAddress.hashCode()) + this.mPhoneNumber.hashCode();
    }

    public void setEmailAddress(String str) {
        this.mEmailAddress = str;
    }

    public void setPhoneNumber(String str) {
        this.mPhoneNumber = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("SimplUser{mPhoneNumber='");
        stringBuilder.append(this.mPhoneNumber);
        stringBuilder.append('\'');
        stringBuilder.append(", mEmailAddress='");
        stringBuilder.append(this.mEmailAddress);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mEmailAddress);
        parcel.writeString(this.mPhoneNumber);
    }
}
