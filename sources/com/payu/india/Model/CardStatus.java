package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CardStatus implements Parcelable {
    public static final Creator<CardStatus> CREATOR = new Creator<CardStatus>() {
        /* renamed from: a */
        public CardStatus createFromParcel(Parcel parcel) {
            return new CardStatus(parcel);
        }

        /* renamed from: a */
        public CardStatus[] newArray(int i) {
            return new CardStatus[i];
        }
    };
    private String a;
    private int b;

    public int describeContents() {
        return 0;
    }

    protected CardStatus(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
    }
}
