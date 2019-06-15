package com.helpshift.campaigns.models;

import com.helpshift.campaigns.m.a.b;
import java.util.ArrayList;

public class g {
    private final String a;
    private final String b;
    private final String c;
    private final long d;
    private ArrayList<Long> e;
    private long f = 0;
    private Integer g = b.a;

    public g(String str, String str2, String str3, long j) {
        this.a = str;
        this.c = str2;
        this.b = str3;
        this.d = j;
    }

    public g a(long j) {
        this.f = j;
        return this;
    }

    public g a(Integer num) {
        this.g = num;
        return this;
    }

    public g a(ArrayList<Long> arrayList) {
        this.e = arrayList;
        return this;
    }

    public f a() {
        return new f(this.a, this.c, this.b, this.d, this.f, this.e, this.g);
    }
}
