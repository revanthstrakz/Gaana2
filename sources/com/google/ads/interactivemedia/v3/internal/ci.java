package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;

final class ci {
    private final eq a;
    private final int b;
    private final a c = new a();
    private final LinkedBlockingDeque<ep> d = new LinkedBlockingDeque();
    private final b e = new b();
    private final fp f = new fp(32);
    private long g;
    private long h;
    private ep i;
    private int j = this.b;

    private static final class a {
        private int a = 1000;
        private long[] b = new long[this.a];
        private int[] c = new int[this.a];
        private int[] d = new int[this.a];
        private long[] e = new long[this.a];
        private byte[][] f = new byte[this.a][];
        private int g;
        private int h;
        private int i;
        private int j;

        public void a() {
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.g = 0;
        }

        public synchronized boolean a(bm bmVar, b bVar) {
            if (this.g == 0) {
                return false;
            }
            bmVar.e = this.e[this.i];
            bmVar.c = this.c[this.i];
            bmVar.d = this.d[this.i];
            bVar.a = this.b[this.i];
            bVar.b = this.f[this.i];
            return true;
        }

        public synchronized long b() {
            long j;
            this.g--;
            int i = this.i;
            this.i = i + 1;
            this.h++;
            if (this.i == this.a) {
                this.i = 0;
            }
            if (this.g > 0) {
                j = this.b[this.i];
            } else {
                j = ((long) this.c[i]) + this.b[i];
            }
            return j;
        }

        /* JADX WARNING: Missing block: B:37:0x006a, code skipped:
            return -1;
     */
        public synchronized long a(long r10) {
            /*
            r9 = this;
            monitor-enter(r9);
            r0 = r9.g;	 Catch:{ all -> 0x006b }
            r1 = -1;
            if (r0 == 0) goto L_0x0069;
        L_0x0007:
            r0 = r9.e;	 Catch:{ all -> 0x006b }
            r3 = r9.i;	 Catch:{ all -> 0x006b }
            r3 = r0[r3];	 Catch:{ all -> 0x006b }
            r0 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1));
            if (r0 >= 0) goto L_0x0012;
        L_0x0011:
            goto L_0x0069;
        L_0x0012:
            r0 = r9.j;	 Catch:{ all -> 0x006b }
            if (r0 != 0) goto L_0x0019;
        L_0x0016:
            r0 = r9.a;	 Catch:{ all -> 0x006b }
            goto L_0x001b;
        L_0x0019:
            r0 = r9.j;	 Catch:{ all -> 0x006b }
        L_0x001b:
            r0 = r0 + -1;
            r3 = r9.e;	 Catch:{ all -> 0x006b }
            r4 = r3[r0];	 Catch:{ all -> 0x006b }
            r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x0027;
        L_0x0025:
            monitor-exit(r9);
            return r1;
        L_0x0027:
            r0 = 0;
            r3 = r9.i;	 Catch:{ all -> 0x006b }
            r4 = -1;
            r5 = r0;
            r0 = r4;
        L_0x002d:
            r6 = r9.j;	 Catch:{ all -> 0x006b }
            if (r3 == r6) goto L_0x004b;
        L_0x0031:
            r6 = r9.e;	 Catch:{ all -> 0x006b }
            r7 = r6[r3];	 Catch:{ all -> 0x006b }
            r6 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1));
            if (r6 <= 0) goto L_0x003a;
        L_0x0039:
            goto L_0x004b;
        L_0x003a:
            r6 = r9.d;	 Catch:{ all -> 0x006b }
            r6 = r6[r3];	 Catch:{ all -> 0x006b }
            r6 = r6 & 1;
            if (r6 == 0) goto L_0x0043;
        L_0x0042:
            r0 = r5;
        L_0x0043:
            r3 = r3 + 1;
            r6 = r9.a;	 Catch:{ all -> 0x006b }
            r3 = r3 % r6;
            r5 = r5 + 1;
            goto L_0x002d;
        L_0x004b:
            if (r0 != r4) goto L_0x004f;
        L_0x004d:
            monitor-exit(r9);
            return r1;
        L_0x004f:
            r10 = r9.g;	 Catch:{ all -> 0x006b }
            r10 = r10 - r0;
            r9.g = r10;	 Catch:{ all -> 0x006b }
            r10 = r9.i;	 Catch:{ all -> 0x006b }
            r10 = r10 + r0;
            r11 = r9.a;	 Catch:{ all -> 0x006b }
            r10 = r10 % r11;
            r9.i = r10;	 Catch:{ all -> 0x006b }
            r10 = r9.h;	 Catch:{ all -> 0x006b }
            r10 = r10 + r0;
            r9.h = r10;	 Catch:{ all -> 0x006b }
            r10 = r9.b;	 Catch:{ all -> 0x006b }
            r11 = r9.i;	 Catch:{ all -> 0x006b }
            r0 = r10[r11];	 Catch:{ all -> 0x006b }
            monitor-exit(r9);
            return r0;
        L_0x0069:
            monitor-exit(r9);
            return r1;
        L_0x006b:
            r10 = move-exception;
            monitor-exit(r9);
            throw r10;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ci$a.a(long):long");
        }

        public synchronized void a(long j, int i, long j2, int i2, byte[] bArr) {
            this.e[this.j] = j;
            this.b[this.j] = j2;
            this.c[this.j] = i2;
            this.d[this.j] = i;
            this.f[this.j] = bArr;
            this.g++;
            if (this.g == this.a) {
                int i3 = this.a + 1000;
                long[] jArr = new long[i3];
                long[] jArr2 = new long[i3];
                int[] iArr = new int[i3];
                int[] iArr2 = new int[i3];
                byte[][] bArr2 = new byte[i3][];
                int i4 = this.a - this.i;
                System.arraycopy(this.b, this.i, jArr, 0, i4);
                System.arraycopy(this.e, this.i, jArr2, 0, i4);
                System.arraycopy(this.d, this.i, iArr, 0, i4);
                System.arraycopy(this.c, this.i, iArr2, 0, i4);
                System.arraycopy(this.f, this.i, bArr2, 0, i4);
                int i5 = this.i;
                System.arraycopy(this.b, 0, jArr, i4, i5);
                System.arraycopy(this.e, 0, jArr2, i4, i5);
                System.arraycopy(this.d, 0, iArr, i4, i5);
                System.arraycopy(this.c, 0, iArr2, i4, i5);
                System.arraycopy(this.f, 0, bArr2, i4, i5);
                this.b = jArr;
                this.e = jArr2;
                this.d = iArr;
                this.c = iArr2;
                this.f = bArr2;
                this.i = 0;
                this.j = this.a;
                this.g = this.a;
                this.a = i3;
            } else {
                this.j++;
                if (this.j == this.a) {
                    this.j = 0;
                }
            }
        }
    }

    private static final class b {
        public long a;
        public byte[] b;

        private b() {
        }
    }

    public ci(eq eqVar) {
        this.a = eqVar;
        this.b = eqVar.b();
    }

    public void a() {
        this.c.a();
        this.a.a((ep[]) this.d.toArray(new ep[this.d.size()]));
        this.d.clear();
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = this.b;
    }

    public boolean a(bm bmVar) {
        return this.c.a(bmVar, this.e);
    }

    public void b() {
        b(this.c.b());
    }

    public boolean a(long j) {
        j = this.c.a(j);
        if (j == -1) {
            return false;
        }
        b(j);
        return true;
    }

    public boolean b(bm bmVar) {
        if (!this.c.a(bmVar, this.e)) {
            return false;
        }
        if (bmVar.a()) {
            a(bmVar, this.e);
        }
        bmVar.a(bmVar.c);
        a(this.e.a, bmVar.b, bmVar.c);
        b(this.c.b());
        return true;
    }

    private void a(bm bmVar, b bVar) {
        long j;
        bm bmVar2 = bmVar;
        b bVar2 = bVar;
        long j2 = bVar2.a;
        int i = 1;
        a(j2, this.f.a, 1);
        long j3 = j2 + 1;
        int i2 = 0;
        byte b = this.f.a[0];
        int i3 = (b & 128) != 0 ? 1 : 0;
        int i4 = b & 127;
        if (bmVar2.a.a == null) {
            bmVar2.a.a = new byte[16];
        }
        a(j3, bmVar2.a.a, i4);
        long j4 = j3 + ((long) i4);
        if (i3 != 0) {
            a(j4, this.f.a, 2);
            j = j4 + 2;
            this.f.c(0);
            i = this.f.g();
        } else {
            j = j4;
        }
        int i5 = i;
        int[] iArr = bmVar2.a.d;
        if (iArr == null || iArr.length < i5) {
            iArr = new int[i5];
        }
        int[] iArr2 = iArr;
        iArr = bmVar2.a.e;
        if (iArr == null || iArr.length < i5) {
            iArr = new int[i5];
        }
        int[] iArr3 = iArr;
        if (i3 != 0) {
            i4 = 6 * i5;
            b(this.f, i4);
            a(j, this.f.a, i4);
            long j5 = j + ((long) i4);
            this.f.c(0);
            while (i2 < i5) {
                iArr2[i2] = this.f.g();
                iArr3[i2] = this.f.s();
                i2++;
            }
            j = j5;
        } else {
            iArr2[0] = 0;
            iArr3[0] = bmVar2.c - ((int) (j - bVar2.a));
        }
        bmVar2.a.a(i5, iArr2, iArr3, bVar2.b, bmVar2.a.a, 1);
        i4 = (int) (j - bVar2.a);
        bVar2.a += (long) i4;
        bmVar2.c -= i4;
    }

    private void a(long j, ByteBuffer byteBuffer, int i) {
        while (i > 0) {
            b(j);
            int i2 = (int) (j - this.g);
            int min = Math.min(i, this.b - i2);
            ep epVar = (ep) this.d.peek();
            byteBuffer.put(epVar.a, epVar.a(i2), min);
            i -= min;
            j += (long) min;
        }
    }

    private void a(long j, byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            b(j);
            int i3 = (int) (j - this.g);
            int min = Math.min(i - i2, this.b - i3);
            ep epVar = (ep) this.d.peek();
            System.arraycopy(epVar.a, epVar.a(i3), bArr, i2, min);
            i2 += min;
            j += (long) min;
        }
    }

    private void b(long j) {
        int i = ((int) (j - this.g)) / this.b;
        for (int i2 = 0; i2 < i; i2++) {
            this.a.a((ep) this.d.remove());
            this.g += (long) this.b;
        }
    }

    private static void b(fp fpVar, int i) {
        if (fpVar.c() < i) {
            fpVar.a(new byte[i], i);
        }
    }

    public long c() {
        return this.h;
    }

    public int a(cd cdVar, int i, boolean z) throws IOException, InterruptedException {
        int a = cdVar.a(this.i.a, this.i.a(this.j), a(i));
        if (a != -1) {
            this.j += a;
            this.h += (long) a;
            return a;
        } else if (z) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    public void a(fp fpVar, int i) {
        while (i > 0) {
            int a = a(i);
            fpVar.a(this.i.a, this.i.a(this.j), a);
            this.j += a;
            this.h += (long) a;
            i -= a;
        }
    }

    public void a(long j, int i, long j2, int i2, byte[] bArr) {
        this.c.a(j, i, j2, i2, bArr);
    }

    private int a(int i) {
        if (this.j == this.b) {
            this.j = 0;
            this.i = this.a.a();
            this.d.add(this.i);
        }
        return Math.min(i, this.b - this.j);
    }
}
