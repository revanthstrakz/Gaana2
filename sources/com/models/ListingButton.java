package com.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.managers.URLManager;
import com.managers.x;
import java.io.Serializable;
import java.util.ArrayList;

public class ListingButton implements Parcelable, Serializable {
    public static final Creator<ListingButton> CREATOR = new Creator<ListingButton>() {
        /* renamed from: a */
        public ListingButton createFromParcel(Parcel parcel) {
            return new ListingButton(parcel);
        }

        /* renamed from: a */
        public ListingButton[] newArray(int i) {
            return new ListingButton[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private URLManager d;
    private ArrayList<?> e;
    private x f;
    private ArrayList<Track> g;
    private a h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;

    public interface a {
        void a(BusinessObject businessObject);
    }

    public int describeContents() {
        return 0;
    }

    protected ListingButton(Parcel parcel) {
        this.a = "Gaana";
        this.b = this.a;
        this.h = null;
        boolean z = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = (URLManager) parcel.readParcelable(URLManager.class.getClassLoader());
        this.i = parcel.readByte() != (byte) 0;
        this.j = parcel.readByte() != (byte) 0;
        this.k = parcel.readByte() != (byte) 0;
        this.l = parcel.readByte() != (byte) 0;
        this.m = parcel.readByte() != (byte) 0;
        this.n = parcel.readByte() != (byte) 0;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        }
        this.o = z;
    }

    public ListingButton() {
        this.a = "Gaana";
        this.b = this.a;
        this.h = null;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
    }

    public boolean a() {
        return this.n;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public boolean b() {
        return this.l;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public URLManager c() {
        return this.d;
    }

    public void a(URLManager uRLManager) {
        this.d = uRLManager;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public String f() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public ArrayList<?> g() {
        return this.e;
    }

    public void a(ArrayList<?> arrayList) {
        this.e = arrayList;
    }

    public void b(ArrayList<Track> arrayList) {
        this.g = arrayList;
    }

    public ArrayList<Track> h() {
        return this.g;
    }

    public boolean i() {
        return this.i;
    }

    public void c(boolean z) {
        this.i = z;
    }

    public void d(boolean z) {
        this.k = z;
    }

    public boolean j() {
        return this.k;
    }

    public a k() {
        return this.h;
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public boolean l() {
        return this.j;
    }

    public void e(boolean z) {
        this.j = z;
    }

    public boolean m() {
        return this.o;
    }

    public void f(boolean z) {
        this.o = z;
    }

    public void a(x xVar) {
        this.f = xVar;
    }

    public x n() {
        return this.f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeParcelable(this.d, i);
        parcel.writeByte((byte) this.i);
        parcel.writeByte((byte) this.j);
        parcel.writeByte((byte) this.k);
        parcel.writeByte((byte) this.l);
        parcel.writeByte((byte) this.m);
        parcel.writeByte((byte) this.n);
        parcel.writeByte((byte) this.o);
    }
}
