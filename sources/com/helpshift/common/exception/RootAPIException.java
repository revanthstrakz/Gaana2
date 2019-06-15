package com.helpshift.common.exception;

public class RootAPIException extends RuntimeException {
    public final String a;
    public final Exception b;
    public final a c;

    private RootAPIException(Exception exception, a aVar, String str) {
        this.b = exception;
        this.c = aVar;
        this.a = str;
    }

    public static RootAPIException a(Exception exception) {
        return a(exception, null);
    }

    public static RootAPIException a(Exception exception, a aVar) {
        return a(exception, aVar, null);
    }

    public static RootAPIException a(Exception exception, a aVar, String str) {
        if (exception instanceof RootAPIException) {
            RootAPIException rootAPIException = (RootAPIException) exception;
            Exception exception2 = rootAPIException.b;
            if (aVar == null) {
                aVar = rootAPIException.c;
            }
            if (str == null) {
                str = rootAPIException.a;
            }
            exception = exception2;
        } else if (aVar == null) {
            aVar = UnexpectedException.GENERIC;
        }
        return new RootAPIException(exception, aVar, str);
    }

    public int a() {
        return this.c instanceof NetworkException ? ((NetworkException) this.c).serverStatusCode : 0;
    }

    public boolean b() {
        return this.b != null;
    }
}
