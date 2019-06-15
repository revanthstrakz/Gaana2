package android.databinding;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.List;

public class c<C, T, A> implements Cloneable {
    private List<C> a;
    private long b;
    private long[] c;
    private int d;
    private final a<C, T, A> e;

    public static abstract class a<C, T, A> {
        public abstract void a(C c, T t, int i, A a);
    }

    public synchronized void a(T t, int i, A a) {
        this.d++;
        c(t, i, a);
        this.d--;
        if (this.d == 0) {
            if (this.c != null) {
                for (int length = this.c.length - 1; length >= 0; length--) {
                    long j = this.c[length];
                    if (j != 0) {
                        a((length + 1) * 64, j);
                        this.c[length] = 0;
                    }
                }
            }
            if (this.b != 0) {
                a(0, this.b);
                this.b = 0;
            }
        }
    }

    private void b(T t, int i, A a) {
        a(t, i, a, 0, Math.min(64, this.a.size()), this.b);
    }

    private void c(T t, int i, A a) {
        int size = this.a.size();
        int length = this.c == null ? -1 : this.c.length - 1;
        a(t, i, a, length);
        a(t, i, a, (length + 2) * 64, size, 0);
    }

    private void a(T t, int i, A a, int i2) {
        if (i2 < 0) {
            b(t, i, a);
            return;
        }
        long j = this.c[i2];
        int i3 = (i2 + 1) * 64;
        int min = Math.min(this.a.size(), i3 + 64);
        a(t, i, a, i2 - 1);
        a(t, i, a, i3, min, j);
    }

    private void a(T t, int i, A a, int i2, int i3, long j) {
        long j2 = 1;
        while (i2 < i3) {
            if ((j & j2) == 0) {
                this.e.a(this.a.get(i2), t, i, a);
            }
            j2 <<= 1;
            i2++;
        }
    }

    private boolean a(int i) {
        boolean z = true;
        if (i < 64) {
            if ((this.b & (1 << i)) == 0) {
                z = false;
            }
            return z;
        } else if (this.c == null) {
            return false;
        } else {
            int i2 = (i / 64) - 1;
            if (i2 >= this.c.length) {
                return false;
            }
            if ((this.c[i2] & (1 << (i % 64))) == 0) {
                z = false;
            }
            return z;
        }
    }

    private void a(int i, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i2 = (i + 64) - 1; i2 >= i; i2--) {
            if ((j & j2) != 0) {
                this.a.remove(i2);
            }
            j2 >>>= 1;
        }
    }

    /* renamed from: a */
    public synchronized c<C, T, A> clone() {
        c<C, T, A> cVar;
        Throwable e;
        try {
            cVar = (c) super.clone();
            try {
                cVar.b = 0;
                cVar.c = null;
                int i = 0;
                cVar.d = 0;
                cVar.a = new ArrayList();
                int size = this.a.size();
                while (i < size) {
                    if (!a(i)) {
                        cVar.a.add(this.a.get(i));
                    }
                    i++;
                }
            } catch (CloneNotSupportedException e2) {
                e = e2;
                ThrowableExtension.printStackTrace(e);
                return cVar;
            }
        } catch (CloneNotSupportedException e3) {
            Throwable th = e3;
            cVar = null;
            e = th;
            ThrowableExtension.printStackTrace(e);
            return cVar;
        }
        return cVar;
    }
}
