package com.til.colombia.android.internal;

public class ColombiaException extends Exception {
    public ColombiaException(String str) {
        super(str);
    }

    public ColombiaException(Exception exception) {
        super(exception);
    }
}
