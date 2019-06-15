package com.moe.pushlibrary.exceptions;

public class SDKNotInitializedException extends Exception {
    private static final long serialVersionUID = -5429313798902234160L;

    public SDKNotInitializedException() {
        super("SDK is not initialized");
    }

    public SDKNotInitializedException(String str) {
        super(str);
    }

    public SDKNotInitializedException(Throwable th) {
        super(th);
    }

    public SDKNotInitializedException(String str, Throwable th) {
        super(str, th);
    }
}
