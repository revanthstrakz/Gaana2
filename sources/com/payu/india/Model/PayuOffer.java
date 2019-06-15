package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class PayuOffer implements Parcelable {
    public static final Creator<PayuOffer> CREATOR = new Creator<PayuOffer>() {
        /* renamed from: a */
        public PayuOffer createFromParcel(Parcel parcel) {
            return new PayuOffer(parcel);
        }

        /* renamed from: a */
        public PayuOffer[] newArray(int i) {
            return new PayuOffer[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private ArrayList<String> j;

    public int describeContents() {
        return 0;
    }

    protected PayuOffer(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.createStringArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeStringList(this.j);
    }
}
