package net.jpountz.a;

import java.util.zip.Checksum;

public abstract class a {
    final int a;

    interface a {
        a a(int i);
    }

    public abstract int a();

    public abstract void a(byte[] bArr, int i, int i2);

    public abstract void b();

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append("(seed=");
        stringBuilder.append(this.a);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public final Checksum c() {
        return new Checksum() {
            public long getValue() {
                return ((long) a.this.a()) & 268435455;
            }

            public void reset() {
                a.this.b();
            }

            public void update(int i) {
                a.this.a(new byte[]{(byte) i}, 0, 1);
            }

            public void update(byte[] bArr, int i, int i2) {
                a.this.a(bArr, i, i2);
            }

            public String toString() {
                return a.this.toString();
            }
        };
    }
}
