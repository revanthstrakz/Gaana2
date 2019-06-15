package com.helpshift.websockets;

import java.nio.ByteBuffer;

class c {
    private ByteBuffer a;
    private int b = 0;

    public c(int i) {
        this.a = ByteBuffer.allocate(i);
    }

    public int a() {
        return this.b;
    }

    public byte a(int i) throws IndexOutOfBoundsException {
        if (i >= 0 && this.b > i) {
            return this.a.get(i);
        }
        throw new IndexOutOfBoundsException(String.format("Bad index: index=%d, length=%d", new Object[]{Integer.valueOf(i), Integer.valueOf(this.b)}));
    }

    private void g(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        int position = this.a.position();
        this.a.position(0);
        allocate.put(this.a);
        allocate.position(position);
        this.a = allocate;
    }

    public void b(int i) {
        if (this.a.capacity() < this.b + 1) {
            g(this.b + 1024);
        }
        this.a.put((byte) i);
        this.b++;
    }

    public void a(byte[] bArr) {
        if (this.a.capacity() < this.b + bArr.length) {
            g((this.b + bArr.length) + 1024);
        }
        this.a.put(bArr);
        this.b += bArr.length;
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.a.capacity() < this.b + i2) {
            g((this.b + i2) + 1024);
        }
        this.a.put(bArr, i, i2);
        this.b += i2;
    }

    public void a(c cVar, int i, int i2) {
        a(cVar.a.array(), i, i2);
    }

    public byte[] c(int i) {
        return a(i, a());
    }

    public byte[] a(int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 0 || i < 0 || this.b < i2) {
            throw new IllegalArgumentException(String.format("Bad range: beginIndex=%d, endIndex=%d, length=%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.b)}));
        }
        byte[] bArr = new byte[i3];
        if (i3 != 0) {
            System.arraycopy(this.a.array(), i, bArr, 0, i3);
        }
        return bArr;
    }

    public void b() {
        this.a.clear();
        this.a.position(0);
        this.b = 0;
    }

    public void d(int i) {
        if (this.a.capacity() > i) {
            byte[] a = a(this.b - i, this.b);
            this.a = ByteBuffer.wrap(a);
            this.a.position(a.length);
            this.b = a.length;
        }
    }

    public boolean e(int i) {
        return ((1 << (i % 8)) & a(i / 8)) != 0;
    }

    public int b(int i, int i2) {
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        while (i3 < i2) {
            if (e(i + i3)) {
                i5 += i4;
            }
            i3++;
            i4 *= 2;
        }
        return i5;
    }

    public int c(int i, int i2) {
        int i3 = 1;
        i2--;
        int i4 = 0;
        while (i2 >= 0) {
            if (e(i + i2)) {
                i4 += i3;
            }
            i2--;
            i3 *= 2;
        }
        return i4;
    }

    public boolean a(int[] iArr) {
        boolean e = e(iArr[0]);
        iArr[0] = iArr[0] + 1;
        return e;
    }

    public int a(int[] iArr, int i) {
        int b = b(iArr[0], i);
        iArr[0] = iArr[0] + i;
        return b;
    }

    public void a(int i, boolean z) {
        int i2 = i / 8;
        i %= 8;
        byte a = a(i2);
        this.a.put(i2, (byte) (z ? (1 << i) | a : ((1 << i) ^ -1) & a));
    }

    public void f(int i) {
        a(i, false);
    }
}
