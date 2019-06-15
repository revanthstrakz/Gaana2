package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class PayuUserOffer implements Parcelable {
    public static final Creator<PayuUserOffer> CREATOR = new Creator<PayuUserOffer>() {
        /* renamed from: a */
        public PayuUserOffer createFromParcel(Parcel parcel) {
            return new PayuUserOffer(parcel);
        }

        /* renamed from: a */
        public PayuUserOffer[] newArray(int i) {
            return new PayuUserOffer[i];
        }
    };
    private StoredCard a;
    private ArrayList<PayuOffer> b;

    public int describeContents() {
        return 0;
    }

    protected PayuUserOffer(Parcel parcel) {
        this.a = (StoredCard) parcel.readParcelable(StoredCard.class.getClassLoader());
        this.b = parcel.createTypedArrayList(PayuOffer.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeTypedList(this.b);
    }
}
