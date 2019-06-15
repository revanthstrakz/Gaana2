package com.payu.india.Extras;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.payu.custombrowser.util.CBConstant;

public class PayUChecksum implements Parcelable {
    public static final Creator<PayUChecksum> CREATOR = new Creator<PayUChecksum>() {
        /* renamed from: a */
        public PayUChecksum createFromParcel(Parcel parcel) {
            return new PayUChecksum(parcel, null);
        }

        /* renamed from: a */
        public PayUChecksum[] newArray(int i) {
            return new PayUChecksum[i];
        }
    };
    String a;
    String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String[] p;
    private String[] q;

    public int describeContents() {
        return 0;
    }

    /* synthetic */ PayUChecksum(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    public PayUChecksum() {
        this.p = new String[]{CBConstant.KEY, CBConstant.COMMAND, CBConstant.VAR1, "salt"};
        this.q = new String[]{CBConstant.KEY, CBConstant.TXN_ID, "amount", "productinfo", "firstname", "email", "udf1", "udf2", "udf3", "udf4", "udf5", "salt", "subvention_amount"};
    }

    private PayUChecksum(Parcel parcel) {
        this.p = new String[]{CBConstant.KEY, CBConstant.COMMAND, CBConstant.VAR1, "salt"};
        this.q = new String[]{CBConstant.KEY, CBConstant.TXN_ID, "amount", "productinfo", "firstname", "email", "udf1", "udf2", "udf3", "udf4", "udf5", "salt", "subvention_amount"};
        this.c = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.d = parcel.readString();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.o = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.d);
        parcel.writeString(this.b);
        parcel.writeString(this.o);
    }
}
