package com.google.ads.interactivemedia.v3.internal;

public final class az extends Exception {
    public final boolean a;

    public az(String str) {
        super(str);
        this.a = false;
    }

    public az(Throwable th) {
        super(th);
        this.a = false;
    }

    az(Throwable th, boolean z) {
        super(th);
        this.a = z;
    }
}
