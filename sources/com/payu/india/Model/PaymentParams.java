package com.payu.india.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PaymentParams implements Parcelable {
    public static final Creator<PaymentParams> CREATOR = new Creator<PaymentParams>() {
        /* renamed from: a */
        public PaymentParams createFromParcel(Parcel parcel) {
            return new PaymentParams(parcel);
        }

        /* renamed from: a */
        public PaymentParams[] newArray(int i) {
            return new PaymentParams[i];
        }
    };
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private String K;
    private String L;
    private int M;
    private String N;
    private String O;
    private String P;
    private String Q;
    private String R;
    private String S;
    private String T;
    private String U;
    private String V;
    private String W;
    private int X;
    private String Y;
    private String Z;
    private String a;
    private String aa;
    private String ab;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.aa;
    }

    public String b() {
        return this.ab;
    }

    protected PaymentParams(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.n = parcel.readString();
        this.m = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readString();
        this.s = parcel.readString();
        this.t = parcel.readString();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.x = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.y = parcel.readString();
        this.z = parcel.readString();
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.C = parcel.readString();
        this.D = parcel.readString();
        this.E = parcel.readString();
        this.F = parcel.readString();
        this.G = parcel.readString();
        this.H = parcel.readString();
        this.I = parcel.readString();
        this.J = parcel.readString();
        this.K = parcel.readString();
        this.L = parcel.readString();
        this.o = parcel.readString();
        this.M = parcel.readInt();
        this.N = parcel.readString();
        this.O = parcel.readString();
        this.P = parcel.readString();
        this.Q = parcel.readString();
        this.R = parcel.readString();
        this.S = parcel.readString();
        this.T = parcel.readString();
        this.U = parcel.readString();
        this.V = parcel.readString();
        this.W = parcel.readString();
        this.X = parcel.readInt();
        this.Y = parcel.readString();
        this.Z = parcel.readString();
        this.ab = parcel.readString();
        this.aa = parcel.readString();
    }

    public String c() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String d() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String e() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String f() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String g() {
        return this.n;
    }

    public void e(String str) {
        this.n = str;
    }

    public String h() {
        return this.m;
    }

    public void f(String str) {
        this.m = str;
    }

    public String i() {
        return this.e;
    }

    public void g(String str) {
        this.e = str;
    }

    public String j() {
        return this.f;
    }

    public void h(String str) {
        this.f = str;
    }

    public String k() {
        return this.g;
    }

    public void i(String str) {
        this.g = str;
    }

    public String l() {
        return this.p;
    }

    public void j(String str) {
        this.p = str;
    }

    public String m() {
        return this.q;
    }

    public String n() {
        return this.r;
    }

    public String o() {
        return this.s;
    }

    public String p() {
        return this.t;
    }

    public String q() {
        return this.u;
    }

    public String r() {
        return this.v;
    }

    public String s() {
        return this.w;
    }

    public String t() {
        return this.x;
    }

    public String u() {
        return this.h;
    }

    public void k(String str) {
        this.h = str;
    }

    public String v() {
        return this.i;
    }

    public void l(String str) {
        this.i = str;
    }

    public String w() {
        return this.j;
    }

    public void m(String str) {
        this.j = str;
    }

    public String x() {
        return this.k;
    }

    public void n(String str) {
        this.k = str;
    }

    public String y() {
        return this.l;
    }

    public void o(String str) {
        this.l = str;
    }

    public String z() {
        return this.y;
    }

    public String A() {
        return this.z;
    }

    public String B() {
        return this.A;
    }

    public String C() {
        return this.B;
    }

    public String D() {
        return this.C;
    }

    public String E() {
        return this.D;
    }

    public String F() {
        return this.E;
    }

    public String G() {
        return this.F;
    }

    public String H() {
        return this.G;
    }

    public String I() {
        return this.H;
    }

    public String J() {
        return this.I;
    }

    public String K() {
        return this.J;
    }

    public String L() {
        return this.K;
    }

    public String M() {
        return this.L;
    }

    public String N() {
        return this.o;
    }

    public void p(String str) {
        this.o = str;
    }

    public int O() {
        return this.M;
    }

    public void a(int i) {
        this.M = i;
    }

    public String P() {
        return this.N;
    }

    public void q(String str) {
        this.N = str;
    }

    public String Q() {
        return this.O;
    }

    public void r(String str) {
        this.O = str;
    }

    public String R() {
        return this.P;
    }

    public void s(String str) {
        this.P = str;
    }

    public String S() {
        return this.Q;
    }

    public void t(String str) {
        this.Q = str;
    }

    public String T() {
        return this.R;
    }

    public void u(String str) {
        this.R = str;
    }

    public String U() {
        return this.S;
    }

    public void v(String str) {
        this.S = str;
    }

    public String V() {
        return this.T;
    }

    public void w(String str) {
        this.T = str;
    }

    public String W() {
        return this.U;
    }

    public String X() {
        return this.V;
    }

    public void x(String str) {
        this.V = str;
    }

    public void y(String str) {
        this.W = str;
    }

    public int Y() {
        return this.X;
    }

    public String Z() {
        return this.Y;
    }

    public String aa() {
        return this.Z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.n);
        parcel.writeString(this.m);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.t);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeString(this.x);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.y);
        parcel.writeString(this.z);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
        parcel.writeString(this.C);
        parcel.writeString(this.D);
        parcel.writeString(this.E);
        parcel.writeString(this.F);
        parcel.writeString(this.G);
        parcel.writeString(this.H);
        parcel.writeString(this.I);
        parcel.writeString(this.J);
        parcel.writeString(this.K);
        parcel.writeString(this.L);
        parcel.writeString(this.o);
        parcel.writeInt(this.M);
        parcel.writeString(this.N);
        parcel.writeString(this.O);
        parcel.writeString(this.P);
        parcel.writeString(this.Q);
        parcel.writeString(this.R);
        parcel.writeString(this.S);
        parcel.writeString(this.T);
        parcel.writeString(this.U);
        parcel.writeString(this.V);
        parcel.writeString(this.W);
        parcel.writeInt(this.X);
        parcel.writeString(this.Y);
        parcel.writeString(this.Z);
        parcel.writeString(this.ab);
        parcel.writeString(this.aa);
    }
}
