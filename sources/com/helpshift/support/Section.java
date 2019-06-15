package com.helpshift.support;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Section implements Parcelable {
    public static final Creator<Section> CREATOR = new Creator<Section>() {
        /* renamed from: a */
        public Section createFromParcel(Parcel parcel) {
            return new Section(parcel);
        }

        /* renamed from: a */
        public Section[] newArray(int i) {
            return new Section[i];
        }
    };
    private long a;
    private String b;
    private String c;
    private String d;

    public int describeContents() {
        return 0;
    }

    public Section() {
        this.a = -1;
        this.b = "";
        this.d = "";
        this.c = "";
    }

    public Section(long j, String str, String str2, String str3) {
        this.a = j;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    Section(Parcel parcel) {
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }

    public String toString() {
        return this.c;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    public boolean equals(Object obj) {
        Section section = (Section) obj;
        return this.c.equals(section.c) && this.d.equals(section.d) && this.b.equals(section.b);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }
}
