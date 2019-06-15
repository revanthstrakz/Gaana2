package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PostData implements Parcelable {
    public static final Creator<PostData> CREATOR = new Creator<PostData>() {
        /* renamed from: a */
        public PostData createFromParcel(Parcel parcel) {
            return new PostData(parcel);
        }

        /* renamed from: a */
        public PostData[] newArray(int i) {
            return new PostData[i];
        }
    };
    private String a;
    private String b;
    private int c;

    public int describeContents() {
        return 0;
    }

    protected PostData(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c);
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }
}
