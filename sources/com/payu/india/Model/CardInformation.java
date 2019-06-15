package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CardInformation implements Parcelable {
    public static final Creator<CardInformation> CREATOR = new Creator<CardInformation>() {
        /* renamed from: a */
        public CardInformation createFromParcel(Parcel parcel) {
            return new CardInformation(parcel);
        }

        /* renamed from: a */
        public CardInformation[] newArray(int i) {
            return new CardInformation[i];
        }
    };
    private Boolean a;
    private String b;
    private String c;
    private String d;

    public int describeContents() {
        return 0;
    }

    protected CardInformation(Parcel parcel) {
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(Boolean bool) {
        this.a = bool;
    }

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }
}
