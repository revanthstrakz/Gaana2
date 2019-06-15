package com.helpshift.websockets;

import android.support.v4.internal.view.SupportMenu;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class ak extends FilterOutputStream {
    public ak(OutputStream outputStream) {
        super(outputStream);
    }

    public void a(String str) throws IOException {
        write(p.a(str));
    }

    public void a(ah ahVar) throws IOException {
        b(ahVar);
        c(ahVar);
        d(ahVar);
        byte[] a = p.a(4);
        write(a);
        a(ahVar, a);
    }

    private void b(ah ahVar) throws IOException {
        int i = 0;
        int i2 = ((ahVar.a() ? 128 : 0) | (ahVar.b() ? 64 : 0)) | (ahVar.c() ? 32 : 0);
        if (ahVar.d()) {
            i = 16;
        }
        write((ahVar.e() & 15) | (i2 | i));
    }

    private void c(ah ahVar) throws IOException {
        int n = ahVar.n();
        n = n <= 125 ? n | 128 : n <= SupportMenu.USER_MASK ? 254 : 255;
        write(n);
    }

    private void d(ah ahVar) throws IOException {
        int n = ahVar.n();
        if (n > 125) {
            if (n <= SupportMenu.USER_MASK) {
                write((n >> 8) & 255);
                write(n & 255);
                return;
            }
            write(0);
            write(0);
            write(0);
            write(0);
            write((n >> 24) & 255);
            write((n >> 16) & 255);
            write((n >> 8) & 255);
            write(n & 255);
        }
    }

    private void a(ah ahVar, byte[] bArr) throws IOException {
        byte[] o = ahVar.o();
        if (o != null) {
            for (int i = 0; i < o.length; i++) {
                write((o[i] ^ bArr[i % 4]) & 255);
            }
        }
    }
}
