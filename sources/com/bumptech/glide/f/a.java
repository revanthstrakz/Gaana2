package com.bumptech.glide.f;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicReference;

public final class a {
    private static final AtomicReference<byte[]> a = new AtomicReference();

    private static class a extends InputStream {
        private final ByteBuffer a;
        private int b = -1;

        public boolean markSupported() {
            return true;
        }

        public a(ByteBuffer byteBuffer) {
            this.a = byteBuffer;
        }

        public int available() throws IOException {
            return this.a.remaining();
        }

        public int read() throws IOException {
            if (this.a.hasRemaining()) {
                return this.a.get();
            }
            return -1;
        }

        public synchronized void mark(int i) {
            this.b = this.a.position();
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (!this.a.hasRemaining()) {
                return -1;
            }
            i2 = Math.min(i2, available());
            this.a.get(bArr, i, i2);
            return i2;
        }

        public synchronized void reset() throws IOException {
            if (this.b == -1) {
                throw new IOException("Cannot reset to unset mark position");
            }
            this.a.position(this.b);
        }

        public long skip(long j) throws IOException {
            if (!this.a.hasRemaining()) {
                return -1;
            }
            j = Math.min(j, (long) available());
            this.a.position((int) (((long) this.a.position()) + j));
            return j;
        }
    }

    static final class b {
        final int a;
        final int b;
        final byte[] c;

        public b(byte[] bArr, int i, int i2) {
            this.c = bArr;
            this.a = i;
            this.b = i2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0050 A:{SYNTHETIC, Splitter:B:30:0x0050} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0055 A:{SYNTHETIC, Splitter:B:34:0x0055} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0050 A:{SYNTHETIC, Splitter:B:30:0x0050} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0055 A:{SYNTHETIC, Splitter:B:34:0x0055} */
    public static java.nio.ByteBuffer a(java.io.File r9) throws java.io.IOException {
        /*
        r0 = 0;
        r5 = r9.length();	 Catch:{ all -> 0x004c }
        r1 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r3 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1));
        if (r3 <= 0) goto L_0x0014;
    L_0x000c:
        r9 = new java.io.IOException;	 Catch:{ all -> 0x004c }
        r1 = "File too large to map into memory";
        r9.<init>(r1);	 Catch:{ all -> 0x004c }
        throw r9;	 Catch:{ all -> 0x004c }
    L_0x0014:
        r1 = 0;
        r3 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1));
        if (r3 != 0) goto L_0x0022;
    L_0x001a:
        r9 = new java.io.IOException;	 Catch:{ all -> 0x004c }
        r1 = "File unsuitable for memory mapping";
        r9.<init>(r1);	 Catch:{ all -> 0x004c }
        throw r9;	 Catch:{ all -> 0x004c }
    L_0x0022:
        r7 = new java.io.RandomAccessFile;	 Catch:{ all -> 0x004c }
        r1 = "r";
        r7.<init>(r9, r1);	 Catch:{ all -> 0x004c }
        r9 = r7.getChannel();	 Catch:{ all -> 0x004a }
        r2 = java.nio.channels.FileChannel.MapMode.READ_ONLY;	 Catch:{ all -> 0x0045 }
        r3 = 0;
        r1 = r9;
        r0 = r1.map(r2, r3, r5);	 Catch:{ all -> 0x0045 }
        r0 = r0.load();	 Catch:{ all -> 0x0045 }
        if (r9 == 0) goto L_0x003f;
    L_0x003c:
        r9.close();	 Catch:{ IOException -> 0x003f }
    L_0x003f:
        if (r7 == 0) goto L_0x0044;
    L_0x0041:
        r7.close();	 Catch:{ IOException -> 0x0044 }
    L_0x0044:
        return r0;
    L_0x0045:
        r0 = move-exception;
        r8 = r0;
        r0 = r9;
        r9 = r8;
        goto L_0x004e;
    L_0x004a:
        r9 = move-exception;
        goto L_0x004e;
    L_0x004c:
        r9 = move-exception;
        r7 = r0;
    L_0x004e:
        if (r0 == 0) goto L_0x0053;
    L_0x0050:
        r0.close();	 Catch:{ IOException -> 0x0053 }
    L_0x0053:
        if (r7 == 0) goto L_0x0058;
    L_0x0055:
        r7.close();	 Catch:{ IOException -> 0x0058 }
    L_0x0058:
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.f.a.a(java.io.File):java.nio.ByteBuffer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031 A:{SYNTHETIC, Splitter:B:21:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0036 A:{SYNTHETIC, Splitter:B:25:0x0036} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031 A:{SYNTHETIC, Splitter:B:21:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0036 A:{SYNTHETIC, Splitter:B:25:0x0036} */
    public static void a(java.nio.ByteBuffer r4, java.io.File r5) throws java.io.IOException {
        /*
        r0 = 0;
        r4.position(r0);
        r1 = 0;
        r2 = new java.io.RandomAccessFile;	 Catch:{ all -> 0x002c }
        r3 = "rw";
        r2.<init>(r5, r3);	 Catch:{ all -> 0x002c }
        r5 = r2.getChannel();	 Catch:{ all -> 0x0029 }
        r5.write(r4);	 Catch:{ all -> 0x0027 }
        r5.force(r0);	 Catch:{ all -> 0x0027 }
        r5.close();	 Catch:{ all -> 0x0027 }
        r2.close();	 Catch:{ all -> 0x0027 }
        if (r5 == 0) goto L_0x0021;
    L_0x001e:
        r5.close();	 Catch:{ IOException -> 0x0021 }
    L_0x0021:
        if (r2 == 0) goto L_0x0026;
    L_0x0023:
        r2.close();	 Catch:{ IOException -> 0x0026 }
    L_0x0026:
        return;
    L_0x0027:
        r4 = move-exception;
        goto L_0x002f;
    L_0x0029:
        r4 = move-exception;
        r5 = r1;
        goto L_0x002f;
    L_0x002c:
        r4 = move-exception;
        r5 = r1;
        r2 = r5;
    L_0x002f:
        if (r5 == 0) goto L_0x0034;
    L_0x0031:
        r5.close();	 Catch:{ IOException -> 0x0034 }
    L_0x0034:
        if (r2 == 0) goto L_0x0039;
    L_0x0036:
        r2.close();	 Catch:{ IOException -> 0x0039 }
    L_0x0039:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.f.a.a(java.nio.ByteBuffer, java.io.File):void");
    }

    public static byte[] a(ByteBuffer byteBuffer) {
        b c = c(byteBuffer);
        if (c != null && c.a == 0 && c.b == c.c.length) {
            return byteBuffer.array();
        }
        byteBuffer = byteBuffer.asReadOnlyBuffer();
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.position(0);
        byteBuffer.get(bArr);
        return bArr;
    }

    public static InputStream b(ByteBuffer byteBuffer) {
        return new a(byteBuffer);
    }

    private static b c(ByteBuffer byteBuffer) {
        return (byteBuffer.isReadOnly() || !byteBuffer.hasArray()) ? null : new b(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit());
    }
}
