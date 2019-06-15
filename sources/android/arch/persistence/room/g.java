package android.arch.persistence.room;

import android.arch.persistence.a.d;
import android.arch.persistence.a.e;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

@RestrictTo({Scope.LIBRARY_GROUP})
public class g implements d, e {
    @VisibleForTesting
    static final TreeMap<Integer, g> g = new TreeMap();
    @VisibleForTesting
    final long[] a;
    @VisibleForTesting
    final double[] b;
    @VisibleForTesting
    final String[] c;
    @VisibleForTesting
    final byte[][] d;
    @VisibleForTesting
    final int e;
    @VisibleForTesting
    int f;
    private volatile String h;
    private final int[] i;

    public void close() {
    }

    public static g a(String str, int i) {
        synchronized (g) {
            Entry ceilingEntry = g.ceilingEntry(Integer.valueOf(i));
            if (ceilingEntry != null) {
                g.remove(ceilingEntry.getKey());
                g gVar = (g) ceilingEntry.getValue();
                gVar.b(str, i);
                return gVar;
            }
            g gVar2 = new g(i);
            gVar2.b(str, i);
            return gVar2;
        }
    }

    private g(int i) {
        this.e = i;
        i++;
        this.i = new int[i];
        this.a = new long[i];
        this.b = new double[i];
        this.c = new String[i];
        this.d = new byte[i][];
    }

    /* Access modifiers changed, original: 0000 */
    public void b(String str, int i) {
        this.h = str;
        this.f = i;
    }

    public void b() {
        synchronized (g) {
            g.put(Integer.valueOf(this.e), this);
            c();
        }
    }

    private static void c() {
        if (g.size() > 15) {
            int size = g.size() - 10;
            Iterator it = g.descendingKeySet().iterator();
            while (true) {
                int i = size - 1;
                if (size > 0) {
                    it.next();
                    it.remove();
                    size = i;
                } else {
                    return;
                }
            }
        }
    }

    public String a() {
        return this.h;
    }

    public void a(d dVar) {
        for (int i = 1; i <= this.f; i++) {
            switch (this.i[i]) {
                case 1:
                    dVar.a(i);
                    break;
                case 2:
                    dVar.a(i, this.a[i]);
                    break;
                case 3:
                    dVar.a(i, this.b[i]);
                    break;
                case 4:
                    dVar.a(i, this.c[i]);
                    break;
                case 5:
                    dVar.a(i, this.d[i]);
                    break;
                default:
                    break;
            }
        }
    }

    public void a(int i) {
        this.i[i] = 1;
    }

    public void a(int i, long j) {
        this.i[i] = 2;
        this.a[i] = j;
    }

    public void a(int i, double d) {
        this.i[i] = 3;
        this.b[i] = d;
    }

    public void a(int i, String str) {
        this.i[i] = 4;
        this.c[i] = str;
    }

    public void a(int i, byte[] bArr) {
        this.i[i] = 5;
        this.d[i] = bArr;
    }
}
