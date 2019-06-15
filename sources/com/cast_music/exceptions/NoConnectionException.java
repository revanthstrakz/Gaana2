package com.cast_music.exceptions;

import java.io.IOException;

public class NoConnectionException extends IOException {
    public NoConnectionException(String str, Throwable th) {
        super(str, th);
    }
}
