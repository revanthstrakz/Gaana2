package com.facebook.ads.internal.p;

import com.google.api.client.http.HttpStatusCodes;

public enum k {
    HEIGHT_100(-1, 100),
    HEIGHT_120(-1, 120),
    HEIGHT_300(-1, HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES),
    HEIGHT_400(-1, 400);
    
    private final int e;
    private final int f;

    private k(int i, int i2) {
        this.e = i;
        this.f = i2;
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public int c() {
        int i = this.f;
        return i != 100 ? i != 120 ? i != HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES ? i != 400 ? -1 : 4 : 3 : 2 : 1;
    }
}
