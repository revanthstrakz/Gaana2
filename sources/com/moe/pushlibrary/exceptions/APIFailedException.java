package com.moe.pushlibrary.exceptions;

public class APIFailedException extends Exception {
    private static final long serialVersionUID = 8590521244276955545L;

    public APIFailedException(String str) {
        super(str);
    }

    public APIFailedException(Throwable th) {
        super(th);
    }

    public APIFailedException(String str, Throwable th) {
        super(str, th);
    }
}
