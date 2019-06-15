package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Emi implements Parcelable {
    public static final Creator<Emi> CREATOR = new Creator<Emi>() {
        /* renamed from: a */
        public Emi createFromParcel(Parcel parcel) {
            return new Emi(parcel, null);
        }

        /* renamed from: a */
        public Emi[] newArray(int i) {
            return new Emi[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;

    public int describeContents() {
        return 0;
    }

    /* synthetic */ Emi(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    private Emi(Parcel parcel) {
        this.b = parcel.readString();
        this.a = parcel.readString();
        this.c = parcel.readString();
        this.e = parcel.readString();
        this.d = parcel.readString();
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public void d(String str) {
        this.d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.a);
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeString(this.d);
    }
}
