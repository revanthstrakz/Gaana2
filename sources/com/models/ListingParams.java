package com.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.constants.Constants.SortOrder;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.GaanaSearchManager.SearchType;
import java.io.Serializable;

public class ListingParams implements Parcelable, Serializable {
    public static final Creator<ListingParams> CREATOR = new Creator<ListingParams>() {
        /* renamed from: a */
        public ListingParams createFromParcel(Parcel parcel) {
            return new ListingParams(parcel);
        }

        /* renamed from: a */
        public ListingParams[] newArray(int i) {
            return new ListingParams[i];
        }
    };
    private int a;
    private String b;
    private boolean c;
    private boolean d;
    private int e = 0;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private ListingButton j = null;
    private boolean k = false;
    private boolean l;
    private String m = PLAYOUT_SECTION_TYPE.OTHERS.name();
    private SearchType n;
    private boolean o = true;
    private boolean p;
    private int q = -1;
    private String r;
    private boolean s = false;
    private boolean t = false;
    private int u = -1;
    private SortOrder v = SortOrder.Default;
    private boolean w;
    private boolean x = true;
    private boolean y = true;
    private boolean z = false;

    public int describeContents() {
        return 0;
    }

    protected ListingParams(Parcel parcel) {
        boolean z = false;
        this.a = parcel.readInt();
        this.b = parcel.readString();
        this.c = parcel.readByte() != (byte) 0;
        this.d = parcel.readByte() != (byte) 0;
        this.f = parcel.readByte() != (byte) 0;
        this.g = parcel.readByte() != (byte) 0;
        this.h = parcel.readByte() != (byte) 0;
        this.i = parcel.readByte() != (byte) 0;
        this.j = (ListingButton) parcel.readParcelable(ListingButton.class.getClassLoader());
        this.k = parcel.readByte() != (byte) 0;
        this.l = parcel.readByte() != (byte) 0;
        this.m = parcel.readString();
        this.o = parcel.readByte() != (byte) 0;
        this.p = parcel.readByte() != (byte) 0;
        this.q = parcel.readInt();
        this.r = parcel.readString();
        this.t = parcel.readByte() != (byte) 0;
        String readString = parcel.readString();
        this.v = !readString.equals("null") ? SortOrder.valueOf(readString) : SortOrder.Default;
        this.u = parcel.readInt();
        this.x = parcel.readByte() != (byte) 0;
        this.y = parcel.readByte() != (byte) 0;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        }
        this.z = z;
    }

    public void a(int i) {
        this.e = i;
    }

    public int a() {
        return this.e;
    }

    public boolean b() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public boolean c() {
        return this.l;
    }

    public boolean d() {
        return this.z;
    }

    public void c(boolean z) {
        this.z = z;
    }

    public int e() {
        return this.a;
    }

    public void b(int i) {
        this.a = i;
    }

    public boolean f() {
        return this.c;
    }

    public void d(boolean z) {
        this.c = z;
    }

    public boolean g() {
        return this.d;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public void f(boolean z) {
        this.f = z;
    }

    public void c(int i) {
        this.u = i;
    }

    public int h() {
        return this.u;
    }

    public void g(boolean z) {
        this.t = z;
    }

    public boolean i() {
        return this.g;
    }

    public void h(boolean z) {
        this.g = z;
    }

    public void a(ListingButton listingButton) {
        this.j = listingButton;
    }

    public void a(SearchType searchType) {
        this.n = searchType;
    }

    public ListingButton j() {
        return this.j;
    }

    public boolean k() {
        return this.h;
    }

    public void i(boolean z) {
        this.h = z;
    }

    public void j(boolean z) {
        this.i = z;
    }

    public void k(boolean z) {
        this.o = z;
    }

    public boolean l() {
        return this.o;
    }

    public boolean m() {
        return this.i;
    }

    public String n() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public boolean o() {
        return this.t;
    }

    public void b(String str) {
        this.m = str;
    }

    public String p() {
        return this.m;
    }

    public void l(boolean z) {
        this.p = z;
    }

    public boolean q() {
        return this.p;
    }

    public void d(int i) {
        this.q = i;
    }

    public int r() {
        return this.q;
    }

    public void c(String str) {
        this.r = str;
    }

    public String s() {
        return this.r;
    }

    public void m(boolean z) {
        this.s = z;
    }

    public boolean t() {
        return this.s;
    }

    public void a(SortOrder sortOrder) {
        this.v = sortOrder;
    }

    public boolean u() {
        return this.x;
    }

    public void n(boolean z) {
        this.x = z;
    }

    public boolean v() {
        return this.y;
    }

    public void o(boolean z) {
        this.y = z;
    }

    public SortOrder w() {
        return this.v;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeByte((byte) this.c);
        parcel.writeByte((byte) this.d);
        parcel.writeByte((byte) this.f);
        parcel.writeByte((byte) this.g);
        parcel.writeByte((byte) this.h);
        parcel.writeByte((byte) this.i);
        parcel.writeParcelable(this.j, i);
        parcel.writeByte((byte) this.k);
        parcel.writeByte((byte) this.l);
        parcel.writeString(this.m);
        parcel.writeByte((byte) this.o);
        parcel.writeByte((byte) this.p);
        parcel.writeInt(this.q);
        parcel.writeString(this.r);
        parcel.writeByte((byte) this.t);
        parcel.writeString(this.v != null ? this.v.name() : "null");
        parcel.writeInt(this.u);
        parcel.writeByte((byte) this.x);
        parcel.writeByte((byte) this.y);
        parcel.writeByte((byte) this.z);
    }

    public void p(boolean z) {
        this.w = z;
    }

    public boolean x() {
        return this.w;
    }
}
