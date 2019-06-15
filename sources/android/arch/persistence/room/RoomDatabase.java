package android.arch.persistence.room;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.arch.persistence.a.e;
import android.arch.persistence.a.f;
import android.content.Context;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RoomDatabase {
    protected volatile android.arch.persistence.a.b a;
    boolean b;
    @Nullable
    protected List<b> c;
    private android.arch.persistence.a.c d;
    private final c e = c();
    private boolean f;
    private final ReentrantLock g = new ReentrantLock();

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        /* Access modifiers changed, original: 0000 */
        @SuppressLint({"NewApi"})
        public JournalMode resolve(Context context) {
            if (this != AUTOMATIC) {
                return this;
            }
            if (VERSION.SDK_INT >= 16) {
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (!(activityManager == null || ActivityManagerCompat.isLowRamDevice(activityManager))) {
                    return WRITE_AHEAD_LOGGING;
                }
            }
            return TRUNCATE;
        }
    }

    public static class a<T extends RoomDatabase> {
        private final Class<T> a;
        private final String b;
        private final Context c;
        private ArrayList<b> d;
        private android.arch.persistence.a.c.c e;
        private boolean f;
        private JournalMode g = JournalMode.AUTOMATIC;
        private boolean h = true;
        private final c i = new c();
        private Set<Integer> j;
        private Set<Integer> k;

        a(@NonNull Context context, @NonNull Class<T> cls, @Nullable String str) {
            this.c = context;
            this.a = cls;
            this.b = str;
        }

        @NonNull
        public a<T> a(@NonNull android.arch.persistence.room.a.a... aVarArr) {
            if (this.k == null) {
                this.k = new HashSet();
            }
            for (android.arch.persistence.room.a.a aVar : aVarArr) {
                this.k.add(Integer.valueOf(aVar.a));
                this.k.add(Integer.valueOf(aVar.b));
            }
            this.i.a(aVarArr);
            return this;
        }

        @NonNull
        public a<T> a() {
            this.f = true;
            return this;
        }

        @NonNull
        public a<T> b() {
            this.h = false;
            return this;
        }

        @NonNull
        public a<T> a(@NonNull b bVar) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(bVar);
            return this;
        }

        @NonNull
        public T c() {
            if (this.c == null) {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            } else if (this.a == null) {
                throw new IllegalArgumentException("Must provide an abstract class that extends RoomDatabase");
            } else {
                if (!(this.k == null || this.j == null)) {
                    for (Integer num : this.k) {
                        if (this.j.contains(num)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: ");
                            stringBuilder.append(num);
                            throw new IllegalArgumentException(stringBuilder.toString());
                        }
                    }
                }
                if (this.e == null) {
                    this.e = new android.arch.persistence.a.a.c();
                }
                a aVar = new a(this.c, this.b, this.e, this.i, this.d, this.f, this.g.resolve(this.c), this.h, this.j);
                RoomDatabase roomDatabase = (RoomDatabase) d.a(this.a, "_Impl");
                roomDatabase.a(aVar);
                return roomDatabase;
            }
        }
    }

    public static abstract class b {
        public void a(@NonNull android.arch.persistence.a.b bVar) {
        }

        public void b(@NonNull android.arch.persistence.a.b bVar) {
        }
    }

    public static class c {
        private SparseArrayCompat<SparseArrayCompat<android.arch.persistence.room.a.a>> a = new SparseArrayCompat();

        public void a(@NonNull android.arch.persistence.room.a.a... aVarArr) {
            for (android.arch.persistence.room.a.a a : aVarArr) {
                a(a);
            }
        }

        private void a(android.arch.persistence.room.a.a aVar) {
            int i = aVar.a;
            int i2 = aVar.b;
            SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) this.a.get(i);
            if (sparseArrayCompat == null) {
                sparseArrayCompat = new SparseArrayCompat();
                this.a.put(i, sparseArrayCompat);
            }
            android.arch.persistence.room.a.a aVar2 = (android.arch.persistence.room.a.a) sparseArrayCompat.get(i2);
            if (aVar2 != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Overriding migration ");
                stringBuilder.append(aVar2);
                stringBuilder.append(" with ");
                stringBuilder.append(aVar);
                Log.w("ROOM", stringBuilder.toString());
            }
            sparseArrayCompat.append(i2, aVar);
        }

        @Nullable
        public List<android.arch.persistence.room.a.a> a(int i, int i2) {
            if (i == i2) {
                return Collections.emptyList();
            }
            return a(new ArrayList(), i2 > i, i, i2);
        }

        private List<android.arch.persistence.room.a.a> a(List<android.arch.persistence.room.a.a> list, boolean z, int i, int i2) {
            int i3 = z ? -1 : 1;
            Object obj;
            do {
                if (z) {
                    if (i >= i2) {
                        return list;
                    }
                } else if (i <= i2) {
                    return list;
                }
                SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) this.a.get(i);
                if (sparseArrayCompat == null) {
                    return null;
                }
                int i4;
                int size = sparseArrayCompat.size();
                obj = null;
                if (z) {
                    size--;
                    i4 = -1;
                } else {
                    i4 = size;
                    size = 0;
                }
                while (size != i4) {
                    int keyAt = sparseArrayCompat.keyAt(size);
                    Object obj2 = (z ? keyAt > i2 || keyAt <= i : keyAt < i2 || keyAt >= i) ? null : 1;
                    if (obj2 != null) {
                        list.add(sparseArrayCompat.valueAt(size));
                        obj = 1;
                        i = keyAt;
                        continue;
                        break;
                    }
                    size += i3;
                }
            } while (obj != null);
            return null;
        }
    }

    @NonNull
    public abstract android.arch.persistence.a.c b(a aVar);

    @NonNull
    public abstract c c();

    /* Access modifiers changed, original: 0000 */
    public Lock a() {
        return this.g;
    }

    @CallSuper
    public void a(@NonNull a aVar) {
        this.d = b(aVar);
        boolean z = false;
        if (VERSION.SDK_INT >= 16) {
            if (aVar.g == JournalMode.WRITE_AHEAD_LOGGING) {
                z = true;
            }
            this.d.a(z);
        }
        this.c = aVar.e;
        this.f = aVar.f;
        this.b = z;
    }

    @NonNull
    public android.arch.persistence.a.c b() {
        return this.d;
    }

    public boolean d() {
        android.arch.persistence.a.b bVar = this.a;
        return bVar != null && bVar.e();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void e() {
        if (!this.f && android.arch.a.a.a.a().b()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public Cursor a(String str, @Nullable Object[] objArr) {
        return this.d.a().a(new android.arch.persistence.a.a(str, objArr));
    }

    public Cursor a(e eVar) {
        e();
        return this.d.a().a(eVar);
    }

    public f a(@NonNull String str) {
        e();
        return this.d.a().a(str);
    }

    public void f() {
        e();
        android.arch.persistence.a.b a = this.d.a();
        this.e.b(a);
        a.a();
    }

    public void g() {
        this.d.a().b();
        if (!i()) {
            this.e.a();
        }
    }

    public void h() {
        this.d.a().c();
    }

    /* Access modifiers changed, original: protected */
    public void a(@NonNull android.arch.persistence.a.b bVar) {
        this.e.a(bVar);
    }

    public boolean i() {
        return this.d.a().d();
    }
}
