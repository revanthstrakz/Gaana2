package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StoredCard implements Parcelable {
    public static final Creator<StoredCard> CREATOR = new Creator<StoredCard>() {
        /* renamed from: a */
        public StoredCard createFromParcel(Parcel parcel) {
            return new StoredCard(parcel);
        }

        /* renamed from: a */
        public StoredCard[] newArray(int i) {
            return new StoredCard[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private Boolean g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private int o;
    private int p;
    private String q;
    private int r;

    public int describeContents() {
        return 0;
    }

    protected StoredCard(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readInt();
        this.p = parcel.readInt();
        this.q = parcel.readString();
        this.r = parcel.readInt();
    }

    public void a(String str) {
        this.a = str;
    }

    public String a() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String b() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String c() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public String d() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }

    public void a(Boolean bool) {
        this.g = bool;
    }

    public String e() {
        return this.h;
    }

    public void g(String str) {
        this.h = str;
    }

    public String f() {
        return this.i;
    }

    public void h(String str) {
        this.i = str;
    }

    public String g() {
        return this.j;
    }

    public void i(String str) {
        this.j = str;
    }

    public void j(String str) {
        this.k = str;
    }

    public void k(String str) {
        this.l = str;
    }

    public String h() {
        return this.n;
    }

    public void l(String str) {
        this.n = str;
    }

    public void a(int i) {
        this.o = i;
    }

    public void b(int i) {
        this.p = i;
    }

    public void m(String str) {
        this.q = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.p);
        parcel.writeString(this.q);
        parcel.writeInt(this.r);
    }
}
