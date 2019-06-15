package androidx.work;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

public interface h {
    @SuppressLint({"SyntheticAccessor"})
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final c a = new c();
    @SuppressLint({"SyntheticAccessor"})
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final b b = new b();

    public static abstract class a {

        public static final class a extends a {
            private final Throwable a;

            public a(@NonNull Throwable th) {
                this.a = th;
            }

            @NonNull
            public Throwable a() {
                return this.a;
            }

            @NonNull
            public String toString() {
                return String.format("FAILURE (%s)", new Object[]{this.a.getMessage()});
            }
        }

        public static final class b extends a {
            @NonNull
            public String toString() {
                return "IN_PROGRESS";
            }

            private b() {
            }
        }

        public static final class c extends a {
            @NonNull
            public String toString() {
                return "SUCCESS";
            }

            private c() {
            }
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        a() {
        }
    }
}
