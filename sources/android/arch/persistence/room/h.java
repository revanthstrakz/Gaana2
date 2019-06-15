package android.arch.persistence.room;

import android.arch.persistence.a.f;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class h {
    private final AtomicBoolean a = new AtomicBoolean(false);
    private final RoomDatabase b;
    private volatile f c;

    public abstract String a();

    public h(RoomDatabase roomDatabase) {
        this.b = roomDatabase;
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        this.b.e();
    }

    private f d() {
        return this.b.a(a());
    }

    private f a(boolean z) {
        if (!z) {
            return d();
        }
        if (this.c == null) {
            this.c = d();
        }
        return this.c;
    }

    public f c() {
        b();
        return a(this.a.compareAndSet(false, true));
    }

    public void a(f fVar) {
        if (fVar == this.c) {
            this.a.set(false);
        }
    }
}
