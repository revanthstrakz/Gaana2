package com.til.colombia.android.commons.a;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

final class f extends ByteArrayOutputStream {
    final /* synthetic */ e a;

    f(e eVar, int i) {
        this.a = eVar;
        super(i);
    }

    public final String toString() {
        int i = (this.count <= 0 || this.buf[this.count - 1] != (byte) 13) ? this.count : this.count - 1;
        try {
            return new String(this.buf, 0, i, this.a.a.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
