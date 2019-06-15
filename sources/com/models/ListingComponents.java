package com.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.gaana.models.BusinessObject;
import com.managers.GaanaSearchManager.SearchType;
import java.util.ArrayList;

public class ListingComponents implements Parcelable {
    public static final Creator<ListingComponents> CREATOR = new Creator<ListingComponents>() {
        /* renamed from: a */
        public ListingComponents createFromParcel(Parcel parcel) {
            return new ListingComponents(parcel);
        }

        /* renamed from: a */
        public ListingComponents[] newArray(int i) {
            return new ListingComponents[i];
        }
    };
    private String a;
    private String b;
    private ArrayList<ListingButton> c;
    private Boolean d;
    private Boolean e;
    private BusinessObject f;
    private boolean g;
    private String h;
    private SearchType i;

    public int describeContents() {
        return 0;
    }

    protected ListingComponents(Parcel parcel) {
        Boolean bool;
        this.a = "";
        boolean z = false;
        this.d = Boolean.valueOf(false);
        this.e = Boolean.valueOf(false);
        this.g = false;
        this.h = "";
        this.i = SearchType.Generic;
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.createTypedArrayList(ListingButton.CREATOR);
        byte readByte = parcel.readByte();
        Boolean bool2 = null;
        if (readByte == (byte) 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(readByte == (byte) 1);
        }
        this.d = bool;
        readByte = parcel.readByte();
        if (readByte != (byte) 0) {
            bool2 = Boolean.valueOf(readByte == (byte) 1);
        }
        this.e = bool2;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        }
        this.g = z;
        this.h = parcel.readString();
        this.f = (BusinessObject) parcel.readSerializable();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeTypedList(this.c);
        int i2 = 2;
        i = this.d == null ? 0 : this.d.booleanValue() ? 1 : 2;
        parcel.writeByte((byte) i);
        if (this.e == null) {
            i2 = 0;
        } else if (this.e.booleanValue()) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
        parcel.writeByte((byte) this.g);
        parcel.writeString(this.h);
        if (this.f != null) {
            this.f.setArrListBusinessObj(null);
        }
        parcel.writeSerializable(this.f);
    }

    public ListingComponents() {
        this.a = "";
        this.d = Boolean.valueOf(false);
        this.e = Boolean.valueOf(false);
        this.g = false;
        this.h = "";
        this.i = SearchType.Generic;
    }

    public BusinessObject a() {
        return this.f;
    }

    public void a(BusinessObject businessObject) {
        this.f = businessObject;
    }

    public Boolean b() {
        return this.d;
    }

    public void a(Boolean bool) {
        this.d = bool;
    }

    public void a(String str) {
        this.b = str;
    }

    public ArrayList<ListingButton> c() {
        return this.c;
    }

    public void a(ArrayList<ListingButton> arrayList) {
        this.c = arrayList;
    }

    public String d() {
        return this.a;
    }

    public void b(String str) {
        this.a = str;
    }

    public void a(boolean z, String str) {
        this.g = z;
        this.h = str;
    }

    public String e() {
        return this.h;
    }

    public SearchType f() {
        return this.i;
    }

    public void a(SearchType searchType) {
        this.i = searchType;
    }
}
