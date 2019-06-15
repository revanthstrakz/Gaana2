package androidx.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
import java.util.concurrent.Executor;

public abstract class ListenableWorker {
    @NonNull
    private Context a;
    @NonNull
    private WorkerParameters b;
    private volatile boolean c;
    private boolean d;

    public static abstract class a {

        @RestrictTo({Scope.LIBRARY_GROUP})
        public static final class a extends a {
            private final d a;

            public a() {
                this(d.a);
            }

            public a(@NonNull d dVar) {
                this.a = dVar;
            }

            @RestrictTo({Scope.LIBRARY_GROUP})
            public d d() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                return this.a.equals(((a) obj).a);
            }

            public int hashCode() {
                return (31 * a.class.getName().hashCode()) + this.a.hashCode();
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failure {mOutputData=");
                stringBuilder.append(this.a);
                stringBuilder.append('}');
                return stringBuilder.toString();
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public static final class b extends a {
            public String toString() {
                return "Retry";
            }

            public boolean equals(Object obj) {
                boolean z = true;
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    z = false;
                }
                return z;
            }

            public int hashCode() {
                return b.class.getName().hashCode();
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public static final class c extends a {
            private final d a;

            public c() {
                this(d.a);
            }

            public c(@NonNull d dVar) {
                this.a = dVar;
            }

            @RestrictTo({Scope.LIBRARY_GROUP})
            public d d() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                return this.a.equals(((c) obj).a);
            }

            public int hashCode() {
                return (31 * c.class.getName().hashCode()) + this.a.hashCode();
            }

            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Success {mOutputData=");
                stringBuilder.append(this.a);
                stringBuilder.append('}');
                return stringBuilder.toString();
            }
        }

        @NonNull
        public static a a() {
            return new c();
        }

        @NonNull
        public static a a(@NonNull d dVar) {
            return new c(dVar);
        }

        @NonNull
        public static a b() {
            return new b();
        }

        @NonNull
        public static a c() {
            return new a();
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        a() {
        }
    }

    @MainThread
    @NonNull
    public abstract ListenableFuture<a> d();

    public void f() {
    }

    @Keep
    @SuppressLint({"BanKeepAnnotation"})
    public ListenableWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        if (context == null) {
            throw new IllegalArgumentException("Application Context is null");
        } else if (workerParameters == null) {
            throw new IllegalArgumentException("WorkerParameters is null");
        } else {
            this.a = context;
            this.b = workerParameters;
        }
    }

    @NonNull
    public final Context a() {
        return this.a;
    }

    @NonNull
    public final UUID b() {
        return this.b.a();
    }

    @NonNull
    public final d c() {
        return this.b.b();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public final void e() {
        this.c = true;
        f();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public final boolean g() {
        return this.d;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public final void h() {
        this.d = true;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public Executor i() {
        return this.b.c();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public m j() {
        return this.b.d();
    }
}
