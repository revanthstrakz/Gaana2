package com.facebook.ads.internal.g;

public class b {
    public final int a;
    public final String b;
    public final String c;

    public b(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.b);
        stringBuilder.append(" ");
        stringBuilder.append(this.c);
        return stringBuilder.toString();
    }
}
