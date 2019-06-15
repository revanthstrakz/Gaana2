package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.engine.a.b;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecyclableBufferedInputStream extends FilterInputStream {
    private volatile byte[] a;
    private int b;
    private int c;
    private int d;
    private int e;
    private final b f;

    public static class InvalidMarkException extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        public InvalidMarkException(String str) {
            super(str);
        }
    }

    public boolean markSupported() {
        return true;
    }

    public RecyclableBufferedInputStream(InputStream inputStream, b bVar) {
        this(inputStream, bVar, 65536);
    }

    RecyclableBufferedInputStream(InputStream inputStream, b bVar, int i) {
        super(inputStream);
        this.d = -1;
        this.f = bVar;
        this.a = (byte[]) bVar.a(i, byte[].class);
    }

    public synchronized int available() throws IOException {
        InputStream inputStream;
        inputStream = this.in;
        if (this.a != null) {
            if (inputStream != null) {
            }
        }
        throw c();
        return (this.b - this.e) + inputStream.available();
    }

    private static IOException c() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public synchronized void a() {
        this.c = this.a.length;
    }

    public synchronized void b() {
        if (this.a != null) {
            this.f.a(this.a, byte[].class);
            this.a = null;
        }
    }

    public void close() throws IOException {
        if (this.a != null) {
            this.f.a(this.a, byte[].class);
            this.a = null;
        }
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private int a(InputStream inputStream, byte[] bArr) throws IOException {
        int read;
        if (this.d == -1 || this.e - this.d >= this.c) {
            read = inputStream.read(bArr);
            if (read > 0) {
                this.d = -1;
                this.e = 0;
                this.b = read;
            }
            return read;
        }
        int i;
        if (this.d == 0 && this.c > bArr.length && this.b == bArr.length) {
            int length = bArr.length * 2;
            if (length > this.c) {
                length = this.c;
            }
            byte[] bArr2 = (byte[]) this.f.a(length, byte[].class);
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.a = bArr2;
            this.f.a((Object) bArr, byte[].class);
            bArr = bArr2;
        } else if (this.d > 0) {
            System.arraycopy(bArr, this.d, bArr, 0, bArr.length - this.d);
        }
        this.e -= this.d;
        this.d = 0;
        this.b = 0;
        read = inputStream.read(bArr, this.e, bArr.length - this.e);
        if (read <= 0) {
            i = this.e;
        } else {
            i = this.e + read;
        }
        this.b = i;
        return read;
    }

    public synchronized void mark(int i) {
        this.c = Math.max(this.c, i);
        this.d = this.e;
    }

    public synchronized int read() throws IOException {
        byte[] bArr = this.a;
        InputStream inputStream = this.in;
        if (bArr != null) {
            if (inputStream != null) {
                if (this.e >= this.b && a(inputStream, bArr) == -1) {
                    return -1;
                }
                if (bArr != this.a) {
                    bArr = this.a;
                    if (bArr == null) {
                        throw c();
                    }
                }
                if (this.b - this.e <= 0) {
                    return -1;
                }
                int i = this.e;
                this.e = i + 1;
                return bArr[i] & 255;
            }
        }
        throw c();
    }

    /* JADX WARNING: Missing block: B:27:0x0044, code skipped:
            return r2;
     */
    /* JADX WARNING: Missing block: B:39:0x005a, code skipped:
            return r4;
     */
    /* JADX WARNING: Missing block: B:46:0x0067, code skipped:
            return r4;
     */
    public synchronized int read(byte[] r6, int r7, int r8) throws java.io.IOException {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.a;	 Catch:{ all -> 0x009d }
        if (r0 != 0) goto L_0x000a;
    L_0x0005:
        r6 = c();	 Catch:{ all -> 0x009d }
        throw r6;	 Catch:{ all -> 0x009d }
    L_0x000a:
        if (r8 != 0) goto L_0x000f;
    L_0x000c:
        r6 = 0;
        monitor-exit(r5);
        return r6;
    L_0x000f:
        r1 = r5.in;	 Catch:{ all -> 0x009d }
        if (r1 != 0) goto L_0x0018;
    L_0x0013:
        r6 = c();	 Catch:{ all -> 0x009d }
        throw r6;	 Catch:{ all -> 0x009d }
    L_0x0018:
        r2 = r5.e;	 Catch:{ all -> 0x009d }
        r3 = r5.b;	 Catch:{ all -> 0x009d }
        if (r2 >= r3) goto L_0x0045;
    L_0x001e:
        r2 = r5.b;	 Catch:{ all -> 0x009d }
        r3 = r5.e;	 Catch:{ all -> 0x009d }
        r2 = r2 - r3;
        if (r2 < r8) goto L_0x0027;
    L_0x0025:
        r2 = r8;
        goto L_0x002c;
    L_0x0027:
        r2 = r5.b;	 Catch:{ all -> 0x009d }
        r3 = r5.e;	 Catch:{ all -> 0x009d }
        r2 = r2 - r3;
    L_0x002c:
        r3 = r5.e;	 Catch:{ all -> 0x009d }
        java.lang.System.arraycopy(r0, r3, r6, r7, r2);	 Catch:{ all -> 0x009d }
        r3 = r5.e;	 Catch:{ all -> 0x009d }
        r3 = r3 + r2;
        r5.e = r3;	 Catch:{ all -> 0x009d }
        if (r2 == r8) goto L_0x0043;
    L_0x0038:
        r3 = r1.available();	 Catch:{ all -> 0x009d }
        if (r3 != 0) goto L_0x003f;
    L_0x003e:
        goto L_0x0043;
    L_0x003f:
        r7 = r7 + r2;
        r2 = r8 - r2;
        goto L_0x0046;
    L_0x0043:
        monitor-exit(r5);
        return r2;
    L_0x0045:
        r2 = r8;
    L_0x0046:
        r3 = r5.d;	 Catch:{ all -> 0x009d }
        r4 = -1;
        if (r3 != r4) goto L_0x005b;
    L_0x004b:
        r3 = r0.length;	 Catch:{ all -> 0x009d }
        if (r2 < r3) goto L_0x005b;
    L_0x004e:
        r3 = r1.read(r6, r7, r2);	 Catch:{ all -> 0x009d }
        if (r3 != r4) goto L_0x008d;
    L_0x0054:
        if (r2 != r8) goto L_0x0057;
    L_0x0056:
        goto L_0x0059;
    L_0x0057:
        r4 = r8 - r2;
    L_0x0059:
        monitor-exit(r5);
        return r4;
    L_0x005b:
        r3 = r5.a(r1, r0);	 Catch:{ all -> 0x009d }
        if (r3 != r4) goto L_0x0068;
    L_0x0061:
        if (r2 != r8) goto L_0x0064;
    L_0x0063:
        goto L_0x0066;
    L_0x0064:
        r4 = r8 - r2;
    L_0x0066:
        monitor-exit(r5);
        return r4;
    L_0x0068:
        r3 = r5.a;	 Catch:{ all -> 0x009d }
        if (r0 == r3) goto L_0x0075;
    L_0x006c:
        r0 = r5.a;	 Catch:{ all -> 0x009d }
        if (r0 != 0) goto L_0x0075;
    L_0x0070:
        r6 = c();	 Catch:{ all -> 0x009d }
        throw r6;	 Catch:{ all -> 0x009d }
    L_0x0075:
        r3 = r5.b;	 Catch:{ all -> 0x009d }
        r4 = r5.e;	 Catch:{ all -> 0x009d }
        r3 = r3 - r4;
        if (r3 < r2) goto L_0x007e;
    L_0x007c:
        r3 = r2;
        goto L_0x0083;
    L_0x007e:
        r3 = r5.b;	 Catch:{ all -> 0x009d }
        r4 = r5.e;	 Catch:{ all -> 0x009d }
        r3 = r3 - r4;
    L_0x0083:
        r4 = r5.e;	 Catch:{ all -> 0x009d }
        java.lang.System.arraycopy(r0, r4, r6, r7, r3);	 Catch:{ all -> 0x009d }
        r4 = r5.e;	 Catch:{ all -> 0x009d }
        r4 = r4 + r3;
        r5.e = r4;	 Catch:{ all -> 0x009d }
    L_0x008d:
        r2 = r2 - r3;
        if (r2 != 0) goto L_0x0092;
    L_0x0090:
        monitor-exit(r5);
        return r8;
    L_0x0092:
        r4 = r1.available();	 Catch:{ all -> 0x009d }
        if (r4 != 0) goto L_0x009b;
    L_0x0098:
        r8 = r8 - r2;
        monitor-exit(r5);
        return r8;
    L_0x009b:
        r7 = r7 + r3;
        goto L_0x0046;
    L_0x009d:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read(byte[], int, int):int");
    }

    public synchronized void reset() throws IOException {
        if (this.a == null) {
            throw new IOException("Stream is closed");
        } else if (-1 == this.d) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Mark has been invalidated, pos: ");
            stringBuilder.append(this.e);
            stringBuilder.append(" markLimit: ");
            stringBuilder.append(this.c);
            throw new InvalidMarkException(stringBuilder.toString());
        } else {
            this.e = this.d;
        }
    }

    public synchronized long skip(long j) throws IOException {
        byte[] bArr = this.a;
        InputStream inputStream = this.in;
        if (bArr == null) {
            throw c();
        } else if (j < 1) {
            return 0;
        } else {
            if (inputStream == null) {
                throw c();
            } else if (((long) (this.b - this.e)) >= j) {
                this.e = (int) (((long) this.e) + j);
                return j;
            } else {
                long j2 = (long) (this.b - this.e);
                this.e = this.b;
                if (this.d == -1 || j > ((long) this.c)) {
                    return j2 + inputStream.skip(j - j2);
                } else if (a(inputStream, bArr) == -1) {
                    return j2;
                } else {
                    long j3 = j - j2;
                    if (((long) (this.b - this.e)) >= j3) {
                        this.e = (int) (((long) this.e) + j3);
                        return j;
                    }
                    j2 = (j2 + ((long) this.b)) - ((long) this.e);
                    this.e = this.b;
                    return j2;
                }
            }
        }
    }
}
