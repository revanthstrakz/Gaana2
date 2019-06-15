package com.inmobi.ads;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.comscore.android.id.IdHelperAndroid;
import java.util.Locale;

public class al {
    public Point a;
    Point b;
    Point c;
    Point d;
    protected String e;
    protected String f;
    protected String g;
    protected float h;
    protected String i;
    protected String j;
    protected ba k;

    al() {
        this.a = new Point(0, 0);
        this.c = new Point(0, 0);
        this.b = new Point(0, 0);
        this.d = new Point(0, 0);
        this.e = IdHelperAndroid.NO_ID_AVAILABLE;
        this.f = "straight";
        this.h = 10.0f;
        this.i = "#ff000000";
        this.j = "#00000000";
        this.g = "fill";
        this.k = null;
    }

    public al(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, @NonNull String str3, @NonNull String str4, @Nullable ba baVar) {
        this(i, i2, i3, i4, i5, i6, i7, i8, "fill", str, str2, str3, str4, baVar);
    }

    public al(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, String str3, @NonNull String str4, @NonNull String str5, @Nullable ba baVar) {
        this.a = new Point(i3, i4);
        this.b = new Point(i7, i8);
        this.c = new Point(i, i2);
        this.d = new Point(i5, i6);
        this.e = str2;
        this.f = str3;
        this.h = 10.0f;
        this.g = str;
        if (str4.length() == 0) {
            str4 = "#ff000000";
        }
        this.i = str4;
        if (str5.length() == 0) {
            str5 = "#00000000";
        }
        this.j = str5;
        this.k = baVar;
    }

    public final String a() {
        return this.e;
    }

    public final String b() {
        return this.f;
    }

    public final float c() {
        return this.h;
    }

    public final String d() {
        return this.i.toLowerCase(Locale.US);
    }

    public String e() {
        return this.j.toLowerCase(Locale.US);
    }

    public final String f() {
        return this.g;
    }

    public final ba g() {
        return this.k;
    }
}
