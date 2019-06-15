package com.logging;

import java.io.Serializable;

public class VideoTrackLog implements Serializable {
    private static final long serialVersionUID = 1;
    public String a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public long e = 0;
    public long f = 0;
    public long g = 0;
    public String h;

    public void a(long j) {
        this.e = j;
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

    public void c(String str) {
        this.c = str;
    }

    public String c() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public long d() {
        return this.f;
    }

    public void b(long j) {
        this.f = j;
    }

    public long e() {
        return this.g;
    }

    public void c(long j) {
        this.g = j;
    }

    public String f() {
        return this.h;
    }

    public void e(String str) {
        this.h = str;
    }
}
