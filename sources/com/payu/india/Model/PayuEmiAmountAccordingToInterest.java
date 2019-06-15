package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PayuEmiAmountAccordingToInterest implements Parcelable {
    public static final Creator<PayuEmiAmountAccordingToInterest> CREATOR = new Creator<PayuEmiAmountAccordingToInterest>() {
        /* renamed from: a */
        public PayuEmiAmountAccordingToInterest createFromParcel(Parcel parcel) {
            return new PayuEmiAmountAccordingToInterest(parcel);
        }

        /* renamed from: a */
        public PayuEmiAmountAccordingToInterest[] newArray(int i) {
            return new PayuEmiAmountAccordingToInterest[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public int describeContents() {
        return 0;
    }

    protected PayuEmiAmountAccordingToInterest(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }
}
