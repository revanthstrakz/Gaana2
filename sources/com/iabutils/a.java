package com.iabutils;

public class a {
    int a;
    String b;

    public a(int i, String str) {
        this.a = i;
        if (str == null || str.trim().length() == 0) {
            this.b = IabHelper.a(i);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" (response: ");
        stringBuilder.append(IabHelper.a(i));
        stringBuilder.append(")");
        this.b = stringBuilder.toString();
    }

    public String a() {
        return this.b;
    }

    public boolean b() {
        return this.a == 0;
    }

    public boolean c() {
        return b() ^ 1;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IabResult: ");
        stringBuilder.append(a());
        return stringBuilder.toString();
    }
}
