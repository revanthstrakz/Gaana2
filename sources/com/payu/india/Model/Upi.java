package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Upi implements Parcelable {
    public static final Creator<Upi> CREATOR = new Creator<Upi>() {
        /* renamed from: a */
        public Upi createFromParcel(Parcel parcel) {
            return new Upi(parcel);
        }

        /* renamed from: a */
        public Upi[] newArray(int i) {
            return new Upi[i];
        }
    };
    String a;
    String b;
    String c;
    String d;

    public int describeContents() {
        return 0;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.a = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.c = str;
    }

    protected Upi(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }
}
