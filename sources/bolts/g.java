package bolts;

import java.io.Closeable;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;

public class g implements Closeable {
    private final Object a;
    private final List<f> b;
    private ScheduledFuture<?> c;
    private boolean d;
    private boolean e;

    public boolean a() {
        boolean z;
        synchronized (this.a) {
            b();
            z = this.d;
        }
        return z;
    }

    public void close() {
        synchronized (this.a) {
            if (this.e) {
                return;
            }
            c();
            for (f close : this.b) {
                close.close();
            }
            this.b.clear();
            this.e = true;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(f fVar) {
        synchronized (this.a) {
            b();
            this.b.remove(fVar);
        }
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(a())});
    }

    private void b() {
        if (this.e) {
            throw new IllegalStateException("Object already closed");
        }
    }

    private void c() {
        if (this.c != null) {
            this.c.cancel(true);
            this.c = null;
        }
    }
}
