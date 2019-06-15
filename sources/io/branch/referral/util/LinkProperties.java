package io.branch.referral.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class LinkProperties implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        /* renamed from: a */
        public LinkProperties createFromParcel(Parcel parcel) {
            return new LinkProperties(parcel, null);
        }

        /* renamed from: a */
        public LinkProperties[] newArray(int i) {
            return new LinkProperties[i];
        }
    };
    private final ArrayList<String> a;
    private String b;
    private String c;
    private String d;
    private int e;
    private final HashMap<String, String> f;
    private String g;

    public int describeContents() {
        return 0;
    }

    /* synthetic */ LinkProperties(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    public LinkProperties() {
        this.a = new ArrayList();
        this.b = "Share";
        this.f = new HashMap();
        this.c = "";
        this.d = "";
        this.e = 0;
        this.g = "";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.g);
        parcel.writeInt(this.e);
        parcel.writeSerializable(this.a);
        parcel.writeInt(this.f.size());
        for (Entry entry : this.f.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    private LinkProperties(Parcel parcel) {
        this();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.g = parcel.readString();
        this.e = parcel.readInt();
        this.a.addAll((ArrayList) parcel.readSerializable());
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f.put(parcel.readString(), parcel.readString());
        }
    }
}
