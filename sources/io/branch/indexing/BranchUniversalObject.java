package io.branch.indexing;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class BranchUniversalObject implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        /* renamed from: a */
        public BranchUniversalObject createFromParcel(Parcel parcel) {
            return new BranchUniversalObject(parcel, null);
        }

        /* renamed from: a */
        public BranchUniversalObject[] newArray(int i) {
            return new BranchUniversalObject[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private final HashMap<String, String> f;
    private String g;
    private CONTENT_INDEX_MODE h;
    private final ArrayList<String> i;
    private long j;

    public enum CONTENT_INDEX_MODE {
        PUBLIC,
        PRIVATE
    }

    public int describeContents() {
        return 0;
    }

    /* synthetic */ BranchUniversalObject(Parcel parcel, AnonymousClass1 anonymousClass1) {
        this(parcel);
    }

    public BranchUniversalObject() {
        this.f = new HashMap();
        this.i = new ArrayList();
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.g = "";
        this.h = CONTENT_INDEX_MODE.PUBLIC;
        this.j = 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.g);
        parcel.writeLong(this.j);
        parcel.writeInt(this.h.ordinal());
        parcel.writeSerializable(this.i);
        parcel.writeInt(this.f.size());
        for (Entry entry : this.f.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    private BranchUniversalObject(Parcel parcel) {
        this();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.g = parcel.readString();
        this.j = parcel.readLong();
        this.h = CONTENT_INDEX_MODE.values()[parcel.readInt()];
        this.i.addAll((ArrayList) parcel.readSerializable());
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f.put(parcel.readString(), parcel.readString());
        }
    }
}
