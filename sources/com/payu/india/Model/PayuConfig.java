package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PayuConfig implements Parcelable {
    public static final Creator<PayuConfig> CREATOR = new Creator<PayuConfig>() {
        /* renamed from: a */
        public PayuConfig createFromParcel(Parcel parcel) {
            return new PayuConfig(parcel);
        }

        /* renamed from: a */
        public PayuConfig[] newArray(int i) {
            return new PayuConfig[i];
        }
    };
    private String a;
    private int b;

    public int describeContents() {
        return 0;
    }

    protected PayuConfig(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
    }
}
