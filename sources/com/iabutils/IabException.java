package com.iabutils;

public class IabException extends Exception {
    a a;

    public IabException(a aVar) {
        this(aVar, null);
    }

    public IabException(int i, String str) {
        this(new a(i, str));
    }

    public IabException(a aVar, Exception exception) {
        super(aVar.a(), exception);
        this.a = aVar;
    }

    public IabException(int i, String str, Exception exception) {
        this(new a(i, str), exception);
    }

    public a a() {
        return this.a;
    }
}
