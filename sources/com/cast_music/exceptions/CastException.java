package com.cast_music.exceptions;

public class CastException extends Exception {
    private static final long serialVersionUID = 1;

    public CastException(String str, Throwable th) {
        super(str, th);
    }
}
