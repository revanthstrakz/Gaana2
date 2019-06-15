package com.helpshift.account.dao;

import java.io.Serializable;

public class ProfileDTO implements Serializable {
    public final Long a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final boolean i;

    public ProfileDTO(Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        this.a = l;
        this.b = str2;
        this.c = str;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.i = z;
    }
}
