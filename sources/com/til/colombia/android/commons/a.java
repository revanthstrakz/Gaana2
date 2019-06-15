package com.til.colombia.android.commons;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class a {
    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream == null) {
            throw new IOException("Unable to copy from or to a null stream.");
        }
        byte[] bArr = new byte[16384];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static void a(InputStream inputStream, OutputStream outputStream, long j) throws IOException {
        if (inputStream == null || outputStream == null) {
            throw new IOException("Unable to copy from or to a null stream.");
        }
        byte[] bArr = new byte[16384];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                long j3 = j2 + ((long) read);
                if (j3 >= j) {
                    StringBuilder stringBuilder = new StringBuilder("Error copying content: attempted to copy ");
                    stringBuilder.append(j3);
                    stringBuilder.append(" bytes, with ");
                    stringBuilder.append(j);
                    stringBuilder.append(" maximum.");
                    throw new IOException(stringBuilder.toString());
                }
                outputStream.write(bArr, 0, read);
                j2 = j3;
            } else {
                return;
            }
        }
    }

    public static void a(InputStream inputStream, byte[] bArr) throws IOException {
        int i = 0;
        int length = bArr.length;
        do {
            int read = inputStream.read(bArr, i, length);
            if (read != -1) {
                i += read;
                length -= read;
            } else {
                return;
            }
        } while (length > 0);
    }

    public static void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }
}
