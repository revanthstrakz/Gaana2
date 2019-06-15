package androidx.work;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

public final class b {
    public static final b a = new a().a();
    private NetworkType b = NetworkType.NOT_REQUIRED;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private long g = -1;
    private long h = -1;
    private c i = new c();

    public static final class a {
        boolean a = false;
        boolean b = false;
        NetworkType c = NetworkType.NOT_REQUIRED;
        boolean d = false;
        boolean e = false;
        long f = -1;
        long g = -1;
        c h = new c();

        @NonNull
        public a a(@NonNull NetworkType networkType) {
            this.c = networkType;
            return this;
        }

        @NonNull
        public b a() {
            return new b(this);
        }
    }

    b(a aVar) {
        this.c = aVar.a;
        boolean z = VERSION.SDK_INT >= 23 && aVar.b;
        this.d = z;
        this.b = aVar.c;
        this.e = aVar.d;
        this.f = aVar.e;
        if (VERSION.SDK_INT >= 24) {
            this.i = aVar.h;
            this.g = aVar.f;
            this.h = aVar.g;
        }
    }

    public b(@NonNull b bVar) {
        this.c = bVar.c;
        this.d = bVar.d;
        this.b = bVar.b;
        this.e = bVar.e;
        this.f = bVar.f;
        this.i = bVar.i;
    }

    @NonNull
    public NetworkType a() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(@NonNull NetworkType networkType) {
        this.b = networkType;
    }

    public boolean b() {
        return this.c;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(boolean z) {
        this.c = z;
    }

    @RequiresApi(23)
    public boolean c() {
        return this.d;
    }

    @RequiresApi(23)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public void b(boolean z) {
        this.d = z;
    }

    public boolean d() {
        return this.e;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void c(boolean z) {
        this.e = z;
    }

    public boolean e() {
        return this.f;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void d(boolean z) {
        this.f = z;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public long f() {
        return this.g;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(long j) {
        this.g = j;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public long g() {
        return this.h;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void b(long j) {
        this.h = j;
    }

    @RequiresApi(24)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public void a(@Nullable c cVar) {
        this.i = cVar;
    }

    @RequiresApi(24)
    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public c h() {
        return this.i;
    }

    @RequiresApi(24)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean i() {
        return this.i.b() > 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.c == bVar.c && this.d == bVar.d && this.e == bVar.e && this.f == bVar.f && this.g == bVar.g && this.h == bVar.h && this.b == bVar.b) {
            return this.i.equals(bVar.i);
        }
        return false;
    }

    public int hashCode() {
        return (31 * ((((((((((((this.b.hashCode() * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31) + ((int) (this.h ^ (this.h >>> 32))))) + this.i.hashCode();
    }
}
