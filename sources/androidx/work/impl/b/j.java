package androidx.work.impl.b;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.BackoffPolicy;
import androidx.work.OverwritingInputMerger;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import androidx.work.b;
import androidx.work.d;
import androidx.work.f;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class j {
    public static final android.arch.a.c.a<List<Object>, List<WorkInfo>> q = new android.arch.a.c.a<List<Object>, List<WorkInfo>>() {
    };
    private static final String r = f.a("WorkSpec");
    @NonNull
    public String a;
    @NonNull
    public State b = State.ENQUEUED;
    @NonNull
    public String c;
    public String d = OverwritingInputMerger.class.getName();
    @NonNull
    public d e = d.a;
    @NonNull
    public d f = d.a;
    public long g;
    public long h;
    public long i;
    @NonNull
    public b j = b.a;
    public int k;
    @NonNull
    public BackoffPolicy l = BackoffPolicy.EXPONENTIAL;
    public long m = 30000;
    public long n;
    public long o;
    public long p = -1;

    public static class a {
        public String a;
        public State b;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (this.b != aVar.b) {
                return false;
            }
            return this.a.equals(aVar.a);
        }

        public int hashCode() {
            return (31 * this.a.hashCode()) + this.b.hashCode();
        }
    }

    public j(@NonNull String str, @NonNull String str2) {
        this.a = str;
        this.c = str2;
    }

    public j(@NonNull j jVar) {
        this.a = jVar.a;
        this.c = jVar.c;
        this.b = jVar.b;
        this.d = jVar.d;
        this.e = new d(jVar.e);
        this.f = new d(jVar.f);
        this.g = jVar.g;
        this.h = jVar.h;
        this.i = jVar.i;
        this.j = new b(jVar.j);
        this.k = jVar.k;
        this.l = jVar.l;
        this.m = jVar.m;
        this.n = jVar.n;
        this.o = jVar.o;
        this.p = jVar.p;
    }

    public boolean a() {
        return this.h != 0;
    }

    public boolean b() {
        return this.b == State.ENQUEUED && this.k > 0;
    }

    public long c() {
        int i = 0;
        if (b()) {
            long j;
            if (this.l == BackoffPolicy.LINEAR) {
                i = 1;
            }
            if (i != 0) {
                j = this.m * ((long) this.k);
            } else {
                j = (long) Math.scalb((float) this.m, this.k - 1);
            }
            return this.n + Math.min(18000000, j);
        } else if (!a()) {
            return this.n + this.g;
        } else {
            if (VERSION.SDK_INT > 22) {
                return (this.n + this.h) - this.i;
            }
            if (this.i != this.h) {
                i = 1;
            }
            if (i == 0) {
                return this.n + this.h;
            }
            return ((this.n == 0 ? System.currentTimeMillis() : this.n) + this.h) + (this.n == 0 ? -1 * this.i : 0);
        }
    }

    public boolean d() {
        return b.a.equals(this.j) ^ 1;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        j jVar = (j) obj;
        if (this.g != jVar.g || this.h != jVar.h || this.i != jVar.i || this.k != jVar.k || this.m != jVar.m || this.n != jVar.n || this.o != jVar.o || this.p != jVar.p || !this.a.equals(jVar.a) || this.b != jVar.b || !this.c.equals(jVar.c)) {
            return false;
        }
        if (!this.d == null ? this.d.equals(jVar.d) : jVar.d == null) {
            return false;
        }
        if (!this.e.equals(jVar.e) || !this.f.equals(jVar.f) || !this.j.equals(jVar.j)) {
            return false;
        }
        if (this.l != jVar.l) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (31 * ((((((((((((((((((((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31) + ((int) (this.h ^ (this.h >>> 32)))) * 31) + ((int) (this.i ^ (this.i >>> 32)))) * 31) + this.j.hashCode()) * 31) + this.k) * 31) + this.l.hashCode()) * 31) + ((int) (this.m ^ (this.m >>> 32)))) * 31) + ((int) (this.n ^ (this.n >>> 32)))) * 31) + ((int) (this.o ^ (this.o >>> 32))))) + ((int) (this.p ^ (this.p >>> 32)));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{WorkSpec: ");
        stringBuilder.append(this.a);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
