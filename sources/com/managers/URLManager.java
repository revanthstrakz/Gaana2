package com.managers;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.android.volley.Request.Priority;
import com.utilities.Util;
import java.io.Serializable;
import java.util.HashMap;

public class URLManager implements Parcelable, Serializable {
    public static final Creator<URLManager> CREATOR = new Creator<URLManager>() {
        /* renamed from: a */
        public URLManager createFromParcel(Parcel parcel) {
            return new URLManager(parcel);
        }

        /* renamed from: a */
        public URLManager[] newArray(int i) {
            return new URLManager[i];
        }
    };
    private static final long serialVersionUID = 1;
    private boolean A;
    private boolean B;
    private boolean C;
    private Priority D;
    private int E;
    private BusinessObjectType a;
    private Class<?> b;
    private BusinessObjectType c;
    private String d;
    private HashMap<String, String> e;
    private int f;
    private Boolean g;
    private Boolean h;
    private int i;
    private boolean j;
    private Boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private String q;
    private int r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private String w;
    private String x;
    private boolean y;
    private int z;

    public enum BusinessObjectType {
        Tracks,
        Artists,
        Albums,
        Composers,
        Singers,
        Lyricists,
        Geners,
        Playlists,
        Charts,
        User,
        Friends,
        History,
        Activities,
        Discover,
        Radios,
        TopCharts,
        ProfileUsers,
        Notifications,
        Products,
        CampaignPromo,
        BasicResponse,
        AppDetails,
        TrendingSearches,
        GenericItems,
        RadioMoods,
        YouTubeVideos,
        DynamicViews,
        UberResponse,
        FavoriteData,
        PlaylistDetails,
        PersonaDedications,
        SocialFeed,
        Dedicate,
        ALL,
        SubscriptionTrialCard,
        SubscriptionCard,
        HomeAction,
        TrialProductFeature,
        CountryData,
        DeviceList,
        Occasion,
        FavoriteOccasions,
        PayUHashes,
        BankCodes,
        CouponProducts,
        DeleteHash,
        IssueBankHash,
        DynamicViewSections,
        DynamicViewCategories,
        JukePlaylist,
        JukePlayLists,
        PreScreens
    }

    public int describeContents() {
        return 0;
    }

    protected URLManager(Parcel parcel) {
        Boolean bool;
        Boolean bool2 = null;
        this.b = null;
        this.d = null;
        boolean z = true;
        this.g = Boolean.valueOf(true);
        this.h = Boolean.valueOf(false);
        this.i = -1;
        this.j = false;
        this.k = Boolean.valueOf(false);
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = null;
        this.r = 0;
        this.s = true;
        this.t = false;
        this.u = false;
        this.y = false;
        this.z = -1;
        this.A = true;
        this.B = true;
        this.C = false;
        this.D = Priority.NORMAL;
        this.E = 0;
        this.d = parcel.readString();
        this.f = parcel.readInt();
        byte readByte = parcel.readByte();
        if (readByte == (byte) 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(readByte == (byte) 1);
        }
        this.g = bool;
        readByte = parcel.readByte();
        if (readByte == (byte) 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(readByte == (byte) 1);
        }
        this.h = bool;
        this.i = parcel.readInt();
        this.j = parcel.readByte() != (byte) 0;
        readByte = parcel.readByte();
        if (readByte != (byte) 0) {
            bool2 = Boolean.valueOf(readByte == (byte) 1);
        }
        this.k = bool2;
        this.l = parcel.readByte() != (byte) 0;
        this.m = parcel.readByte() != (byte) 0;
        this.n = parcel.readByte() != (byte) 0;
        this.o = parcel.readByte() != (byte) 0;
        this.p = parcel.readByte() != (byte) 0;
        this.q = parcel.readString();
        this.r = parcel.readInt();
        this.s = parcel.readByte() != (byte) 0;
        this.t = parcel.readByte() != (byte) 0;
        this.v = parcel.readByte() != (byte) 0;
        this.C = parcel.readByte() != (byte) 0;
        this.E = parcel.readInt();
        this.w = parcel.readString();
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        if (!"null".equals(readString)) {
            this.a = BusinessObjectType.valueOf(readString);
        }
        if (!"null".equals(readString2)) {
            this.c = BusinessObjectType.valueOf(readString2);
        }
        this.x = parcel.readString();
        this.z = parcel.readInt();
        this.y = parcel.readByte() != (byte) 0;
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.B = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.d = Util.a(!TextUtils.isEmpty(this.d) ? this.d : "", 0, 20);
        parcel.writeString(this.d);
        parcel.writeInt(this.f);
        int i2 = 2;
        i = this.g == null ? 0 : this.g.booleanValue() ? 1 : 2;
        parcel.writeByte((byte) i);
        i = this.h == null ? 0 : this.h.booleanValue() ? 1 : 2;
        parcel.writeByte((byte) i);
        parcel.writeInt(this.i);
        parcel.writeByte((byte) this.j);
        if (this.k == null) {
            i2 = 0;
        } else if (this.k.booleanValue()) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
        parcel.writeByte((byte) this.l);
        parcel.writeByte((byte) this.m);
        parcel.writeByte((byte) this.n);
        parcel.writeByte((byte) this.o);
        parcel.writeByte((byte) this.p);
        parcel.writeString(this.q);
        parcel.writeInt(this.r);
        parcel.writeByte((byte) this.s);
        parcel.writeByte((byte) this.t);
        parcel.writeByte((byte) this.v);
        parcel.writeByte((byte) this.C);
        parcel.writeInt(this.E);
        parcel.writeString(this.w);
        if (this.a != null) {
            parcel.writeString(this.a.name());
        } else {
            parcel.writeString("null");
        }
        if (this.c != null) {
            parcel.writeString(this.c.name());
        } else {
            parcel.writeString("null");
        }
        parcel.writeString(this.x);
        parcel.writeInt(this.z);
        parcel.writeByte((byte) this.y);
        parcel.writeByte((byte) this.B);
    }

    public boolean a() {
        return this.C;
    }

    public void a(boolean z) {
        this.C = z;
    }

    public void b(boolean z) {
        this.B = z;
    }

    public boolean b() {
        return this.B;
    }

    public URLManager() {
        this.b = null;
        this.d = null;
        this.g = Boolean.valueOf(true);
        this.h = Boolean.valueOf(false);
        this.i = -1;
        this.j = false;
        this.k = Boolean.valueOf(false);
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = null;
        this.r = 0;
        this.s = true;
        this.t = false;
        this.u = false;
        this.y = false;
        this.z = -1;
        this.A = true;
        this.B = true;
        this.C = false;
        this.D = Priority.NORMAL;
        this.E = 0;
    }

    public void c(boolean z) {
        this.j = z;
    }

    public boolean c() {
        return this.j;
    }

    public int d() {
        return this.i;
    }

    public void a(int i) {
        this.i = i;
    }

    public Boolean e() {
        return this.h;
    }

    public void a(Boolean bool) {
        this.h = bool;
    }

    public void b(Boolean bool) {
        this.g = bool;
    }

    public Boolean f() {
        return this.g;
    }

    public int g() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }

    public HashMap<String, String> h() {
        return this.e;
    }

    public void a(HashMap<String, String> hashMap) {
        this.e = hashMap;
    }

    public BusinessObjectType i() {
        return this.a;
    }

    public void a(BusinessObjectType businessObjectType) {
        this.a = businessObjectType;
    }

    public void a(Class<?> cls) {
        this.b = cls;
        if (cls != null) {
            this.w = cls.getName();
        }
    }

    public Class<?> j() {
        if (this.b != null) {
            return this.b;
        }
        if (!TextUtils.isEmpty(this.w)) {
            try {
                return Class.forName(this.w);
            } catch (ClassNotFoundException unused) {
            }
        }
        return null;
    }

    public String k() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public BusinessObjectType l() {
        return this.c;
    }

    public void b(BusinessObjectType businessObjectType) {
        this.c = businessObjectType;
    }

    public Boolean m() {
        return this.k;
    }

    public void c(Boolean bool) {
        this.k = bool;
    }

    public boolean n() {
        return this.l;
    }

    public void d(boolean z) {
        this.l = z;
    }

    public boolean o() {
        return this.m;
    }

    public void e(boolean z) {
        this.m = z;
    }

    public boolean p() {
        return this.p;
    }

    public void f(boolean z) {
        this.p = z;
    }

    public String q() {
        return this.q;
    }

    public void b(String str) {
        this.q = str;
    }

    public boolean r() {
        return this.o;
    }

    public void g(boolean z) {
        this.o = z;
    }

    public boolean s() {
        return this.n;
    }

    public void h(boolean z) {
        this.n = z;
    }

    public int t() {
        return this.r;
    }

    public void c(int i) {
        this.r = i;
    }

    public Priority u() {
        return this.D;
    }

    public void a(Priority priority) {
        this.D = priority;
    }

    public int v() {
        return this.E;
    }

    public void d(int i) {
        this.E = i;
    }

    public boolean w() {
        return this.s;
    }

    public void i(boolean z) {
        this.s = z;
    }

    public boolean x() {
        return this.t;
    }

    public void j(boolean z) {
        this.v = z;
    }

    public void k(boolean z) {
        this.u = z;
    }

    public boolean y() {
        return this.u;
    }

    public boolean z() {
        return this.v;
    }

    public void c(String str) {
        this.x = str;
    }

    public String A() {
        return this.x;
    }

    public void l(boolean z) {
        this.y = z;
    }

    public boolean B() {
        return this.y;
    }

    public int C() {
        return this.z;
    }

    public void e(int i) {
        this.z = i;
    }

    public boolean D() {
        return this.A;
    }

    public void m(boolean z) {
        this.A = z;
    }
}
