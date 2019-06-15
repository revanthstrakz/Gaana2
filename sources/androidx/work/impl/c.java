package androidx.work.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.f;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class c implements a {
    private static final String a = f.a("Processor");
    private Context b;
    private androidx.work.a c;
    private androidx.work.impl.utils.a.a d;
    private WorkDatabase e;
    private Map<String, j> f = new HashMap();
    private List<d> g;
    private Set<String> h;
    private final List<a> i;
    private final Object j;

    private static class a implements Runnable {
        @NonNull
        private a a;
        @NonNull
        private String b;
        @NonNull
        private ListenableFuture<Boolean> c;

        a(@NonNull a aVar, @NonNull String str, @NonNull ListenableFuture<Boolean> listenableFuture) {
            this.a = aVar;
            this.b = str;
            this.c = listenableFuture;
        }

        public void run() {
            boolean booleanValue;
            try {
                booleanValue = ((Boolean) this.c.get()).booleanValue();
            } catch (InterruptedException | ExecutionException unused) {
                booleanValue = true;
            }
            this.a.a(this.b, booleanValue);
        }
    }

    public c(Context context, androidx.work.a aVar, androidx.work.impl.utils.a.a aVar2, WorkDatabase workDatabase, List<d> list) {
        this.b = context;
        this.c = aVar;
        this.d = aVar2;
        this.e = workDatabase;
        this.g = list;
        this.h = new HashSet();
        this.i = new ArrayList();
        this.j = new Object();
    }

    public boolean a(String str) {
        return a(str, null);
    }

    public boolean a(String str, androidx.work.WorkerParameters.a aVar) {
        synchronized (this.j) {
            if (this.f.containsKey(str)) {
                f.a().b(a, String.format("Work %s is already enqueued for processing", new Object[]{str}), new Throwable[0]);
                return false;
            }
            j a = new androidx.work.impl.j.a(this.b, this.c, this.d, this.e, str).a(this.g).a(aVar).a();
            ListenableFuture a2 = a.a();
            a2.addListener(new a(this, str, a2), this.d.a());
            this.f.put(str, a);
            this.d.c().execute(a);
            f.a().b(a, String.format("%s: processing %s", new Object[]{getClass().getSimpleName(), str}), new Throwable[0]);
            return true;
        }
    }

    public boolean b(String str) {
        synchronized (this.j) {
            f.a().b(a, String.format("Processor stopping %s", new Object[]{str}), new Throwable[0]);
            j jVar = (j) this.f.remove(str);
            if (jVar != null) {
                jVar.a(false);
                f.a().b(a, String.format("WorkerWrapper stopped for %s", new Object[]{str}), new Throwable[0]);
                return true;
            }
            f.a().b(a, String.format("WorkerWrapper could not be found for %s", new Object[]{str}), new Throwable[0]);
            return false;
        }
    }

    public boolean c(String str) {
        synchronized (this.j) {
            f.a().b(a, String.format("Processor cancelling %s", new Object[]{str}), new Throwable[0]);
            this.h.add(str);
            j jVar = (j) this.f.remove(str);
            if (jVar != null) {
                jVar.a(true);
                f.a().b(a, String.format("WorkerWrapper cancelled for %s", new Object[]{str}), new Throwable[0]);
                return true;
            }
            f.a().b(a, String.format("WorkerWrapper could not be found for %s", new Object[]{str}), new Throwable[0]);
            return false;
        }
    }

    public boolean d(String str) {
        boolean contains;
        synchronized (this.j) {
            contains = this.h.contains(str);
        }
        return contains;
    }

    public boolean e(@NonNull String str) {
        boolean containsKey;
        synchronized (this.j) {
            containsKey = this.f.containsKey(str);
        }
        return containsKey;
    }

    public void a(a aVar) {
        synchronized (this.j) {
            this.i.add(aVar);
        }
    }

    public void b(a aVar) {
        synchronized (this.j) {
            this.i.remove(aVar);
        }
    }

    public void a(@NonNull String str, boolean z) {
        synchronized (this.j) {
            this.f.remove(str);
            f.a().b(a, String.format("%s %s executed; reschedule = %s", new Object[]{getClass().getSimpleName(), str, Boolean.valueOf(z)}), new Throwable[0]);
            for (a a : this.i) {
                a.a(str, z);
            }
        }
    }
}
