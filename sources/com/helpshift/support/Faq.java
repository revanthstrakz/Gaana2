package com.helpshift.support;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class Faq implements Parcelable {
    public static final Creator<Faq> CREATOR = new Creator<Faq>() {
        /* renamed from: a */
        public Faq createFromParcel(Parcel parcel) {
            return new Faq(parcel);
        }

        /* renamed from: a */
        public Faq[] newArray(int i) {
            return new Faq[i];
        }
    };
    public long a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final int f;
    public final Boolean g;
    public ArrayList<String> h;
    private String i;
    private String j;
    private List<String> k;
    private List<String> l;

    public int describeContents() {
        return 0;
    }

    public Faq() {
        this.b = "";
        this.c = "";
        this.j = "";
        this.e = "";
        this.d = "";
        this.f = 0;
        this.g = Boolean.valueOf(false);
        this.k = new ArrayList();
        this.l = new ArrayList();
    }

    public Faq(long j, String str, String str2, String str3, String str4, String str5, int i, Boolean bool, List<String> list, List<String> list2) {
        this.a = j;
        this.i = str;
        this.b = str4;
        this.c = str2;
        this.j = "faq";
        this.d = str3;
        this.e = str5;
        this.f = i;
        this.g = bool;
        this.k = list;
        this.l = list2;
    }

    Faq(Parcel parcel) {
        this.i = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.j = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readInt();
        this.g = Boolean.valueOf(parcel.readByte() != (byte) 0);
        if (this.h == null) {
            this.h = new ArrayList();
        }
        if (this.k == null) {
            this.k = new ArrayList();
        }
        if (this.l == null) {
            this.l = new ArrayList();
        }
        parcel.readStringList(this.h);
        parcel.readStringList(this.k);
        parcel.readStringList(this.l);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.i);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.j);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
        parcel.writeByte((byte) this.g.booleanValue());
        parcel.writeStringList(this.h);
        parcel.writeStringList(this.k);
        parcel.writeStringList(this.l);
    }

    public String toString() {
        return this.b;
    }

    public String a() {
        return this.i;
    }

    public List<String> b() {
        if (this.k == null) {
            return new ArrayList();
        }
        return this.k;
    }

    public List<String> c() {
        if (this.l == null) {
            return new ArrayList();
        }
        return this.l;
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        this.h = null;
    }

    /* Access modifiers changed, original: protected */
    public void a(ArrayList<String> arrayList) {
        this.h = a(this.h, arrayList);
    }

    private static ArrayList<String> a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        HashSet hashSet = new HashSet();
        if (arrayList != null) {
            hashSet.addAll(arrayList);
        }
        if (arrayList2 != null) {
            hashSet.addAll(arrayList2);
        }
        return new ArrayList(hashSet);
    }

    public boolean equals(Object obj) {
        Faq faq = (Faq) obj;
        return this.i.equals(faq.i) && this.b.equals(faq.b) && this.e.equals(faq.e) && this.c.equals(faq.c) && this.d.equals(faq.d) && this.g == faq.g && this.f == faq.f && this.k.equals(faq.k) && this.l.equals(faq.l);
    }
}
