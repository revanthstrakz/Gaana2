package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class PayuOfferDetails implements Parcelable {
    public static final Creator<PayuOfferDetails> CREATOR = new Creator<PayuOfferDetails>() {
        /* renamed from: a */
        public PayuOfferDetails createFromParcel(Parcel parcel) {
            return new PayuOfferDetails(parcel);
        }

        /* renamed from: a */
        public PayuOfferDetails[] newArray(int i) {
            return new PayuOfferDetails[i];
        }
    };
    private ArrayList<PayuUserOffer> a;
    private ArrayList<PayuOffer> b;

    public int describeContents() {
        return 0;
    }

    protected PayuOfferDetails(Parcel parcel) {
        this.a = parcel.createTypedArrayList(PayuUserOffer.CREATOR);
        this.b = parcel.createTypedArrayList(PayuOffer.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.a);
        parcel.writeTypedList(this.b);
    }
}
