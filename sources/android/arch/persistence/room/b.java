package android.arch.persistence.room;

import android.arch.persistence.a.f;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class b<T> extends h {
    public abstract void a(f fVar, T t);

    public b(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    public final void a(T t) {
        f c = c();
        try {
            a(c, t);
            c.b();
        } finally {
            a(c);
        }
    }
}
