package com.facebook.accountkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.accountkit.internal.Utility;

public final class Account implements Parcelable {
    public static final Creator<Account> CREATOR = new Creator<Account>() {
        public Account createFromParcel(Parcel parcel) {
            return new Account(parcel, null);
        }

        public Account[] newArray(int i) {
            return new Account[i];
        }
    };
    private final String email;
    private final String id;
    private final PhoneNumber phoneNumber;

    public int describeContents() {
        return 0;
    }

    /* synthetic */ Account(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    public Account(@NonNull String str, @Nullable PhoneNumber phoneNumber, @Nullable String str2) {
        this.id = str;
        this.phoneNumber = phoneNumber;
        this.email = str2;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() {
        return this.id;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account account = (Account) obj;
        if (!(Utility.areObjectsEqual(this.email, account.email) && Utility.areObjectsEqual(this.id, account.id) && Utility.areObjectsEqual(this.phoneNumber, account.phoneNumber))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((527 + Utility.getHashCode(this.email)) * 31) + Utility.getHashCode(this.id)) * 31) + Utility.getHashCode(this.phoneNumber);
    }

    private Account(Parcel parcel) {
        this.id = parcel.readString();
        this.phoneNumber = (PhoneNumber) parcel.readParcelable(PhoneNumber.class.getClassLoader());
        this.email = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeParcelable(this.phoneNumber, i);
        parcel.writeString(this.email);
    }
}
