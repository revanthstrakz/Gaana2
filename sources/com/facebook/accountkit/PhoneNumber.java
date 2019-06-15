package com.facebook.accountkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.accountkit.internal.Utility;

public final class PhoneNumber implements Parcelable {
    public static final Creator<PhoneNumber> CREATOR = new Creator<PhoneNumber>() {
        public PhoneNumber createFromParcel(Parcel parcel) {
            return new PhoneNumber(parcel, null);
        }

        public PhoneNumber[] newArray(int i) {
            return new PhoneNumber[i];
        }
    };
    private final String countryCode;
    private final String countryCodeIso;
    private final String phoneNumber;

    public int describeContents() {
        return 0;
    }

    public PhoneNumber(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        this.phoneNumber = Utility.cleanPhoneNumberString(str2);
        this.countryCode = Utility.cleanPhoneNumberString(str);
        this.countryCodeIso = str3;
    }

    @Deprecated
    public PhoneNumber(String str, String str2) {
        this(str, str2, null);
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getCountryCodeIso() {
        return this.countryCodeIso;
    }

    public String getRawPhoneNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.countryCode);
        stringBuilder.append(this.phoneNumber);
        return stringBuilder.toString();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("+");
        stringBuilder.append(this.countryCode);
        stringBuilder.append(this.phoneNumber);
        return stringBuilder.toString();
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof PhoneNumber) && hashCode() == obj.hashCode();
    }

    private PhoneNumber(Parcel parcel) {
        this.countryCode = parcel.readString();
        this.phoneNumber = parcel.readString();
        this.countryCodeIso = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.countryCode);
        parcel.writeString(this.phoneNumber);
        parcel.writeString(this.countryCodeIso);
    }
}
