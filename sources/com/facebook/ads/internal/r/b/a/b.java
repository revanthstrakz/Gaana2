package com.facebook.ads.internal.r.b.a;

import com.facebook.ads.internal.r.b.a;
import com.facebook.ads.internal.r.b.l;
import com.til.colombia.android.internal.e;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;

public class b implements a {
    public File a;
    private final a b;
    private RandomAccessFile c;

    public b(File file, a aVar) {
        if (aVar == null) {
            try {
                throw new NullPointerException();
            } catch (IOException e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Error using file ");
                stringBuilder.append(file);
                stringBuilder.append(" as disc cache");
                throw new l(stringBuilder.toString(), e);
            }
        }
        File file2;
        this.b = aVar;
        d.a(file.getParentFile());
        boolean exists = file.exists();
        if (exists) {
            file2 = file;
        } else {
            File parentFile = file.getParentFile();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(file.getName());
            stringBuilder2.append(".download");
            file2 = new File(parentFile, stringBuilder2.toString());
        }
        this.a = file2;
        this.c = new RandomAccessFile(this.a, exists ? e.o : "rw");
    }

    private boolean a(File file) {
        return file.getName().endsWith(".download");
    }

    public synchronized int a() {
        try {
        } catch (IOException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error reading length of file ");
            stringBuilder.append(this.a);
            throw new l(stringBuilder.toString(), e);
        }
        return (int) this.c.length();
    }

    public synchronized int a(byte[] bArr, long j, int i) {
        try {
            this.c.seek(j);
        } catch (IOException e) {
            throw new l(String.format(Locale.US, "Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", new Object[]{Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(a()), Integer.valueOf(bArr.length)}), e);
        }
        return this.c.read(bArr, 0, i);
    }

    public synchronized void a(byte[] bArr, int i) {
        try {
            if (d()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Error append cache: cache file ");
                stringBuilder.append(this.a);
                stringBuilder.append(" is completed!");
                throw new l(stringBuilder.toString());
            }
            this.c.seek((long) a());
            this.c.write(bArr, 0, i);
        } catch (IOException e) {
            throw new l(String.format(Locale.US, "Error writing %d bytes to %s from buffer with size %d", new Object[]{Integer.valueOf(i), this.c, Integer.valueOf(bArr.length)}), e);
        }
    }

    public synchronized void b() {
        try {
            this.c.close();
            this.b.a(this.a);
        } catch (IOException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error closing file ");
            stringBuilder.append(this.a);
            throw new l(stringBuilder.toString(), e);
        }
    }

    public synchronized void c() {
        StringBuilder stringBuilder;
        if (!d()) {
            b();
            File file = new File(this.a.getParentFile(), this.a.getName().substring(0, this.a.getName().length() - ".download".length()));
            if (this.a.renameTo(file)) {
                this.a = file;
                try {
                    this.c = new RandomAccessFile(this.a, e.o);
                    return;
                } catch (IOException e) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Error opening ");
                    stringBuilder.append(this.a);
                    stringBuilder.append(" as disc cache");
                    throw new l(stringBuilder.toString(), e);
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Error renaming file ");
            stringBuilder.append(this.a);
            stringBuilder.append(" to ");
            stringBuilder.append(file);
            stringBuilder.append(" for completion!");
            throw new l(stringBuilder.toString());
        }
    }

    public synchronized boolean d() {
        return a(this.a) ^ 1;
    }
}
