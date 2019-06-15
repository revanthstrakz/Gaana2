package androidx.work.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.h;
import androidx.work.impl.utils.b;
import androidx.work.j;
import androidx.work.l;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestrictTo({Scope.LIBRARY_GROUP})
public class f extends j {
    private static final String a = androidx.work.f.a("WorkContinuationImpl");
    private final h b;
    private final String c;
    private final ExistingWorkPolicy d;
    private final List<? extends l> e;
    private final List<String> f;
    private final List<String> g;
    private final List<f> h;
    private boolean i;
    private h j;

    @NonNull
    public h a() {
        return this.b;
    }

    @Nullable
    public String b() {
        return this.c;
    }

    public ExistingWorkPolicy c() {
        return this.d;
    }

    @NonNull
    public List<? extends l> d() {
        return this.e;
    }

    @NonNull
    public List<String> e() {
        return this.f;
    }

    public boolean f() {
        return this.i;
    }

    public void g() {
        this.i = true;
    }

    public List<f> h() {
        return this.h;
    }

    f(@NonNull h hVar, String str, ExistingWorkPolicy existingWorkPolicy, @NonNull List<? extends l> list) {
        this(hVar, str, existingWorkPolicy, list, null);
    }

    f(@NonNull h hVar, String str, ExistingWorkPolicy existingWorkPolicy, @NonNull List<? extends l> list, @Nullable List<f> list2) {
        this.b = hVar;
        this.c = str;
        this.d = existingWorkPolicy;
        this.e = list;
        this.h = list2;
        this.f = new ArrayList(this.e.size());
        this.g = new ArrayList();
        if (list2 != null) {
            for (f fVar : list2) {
                this.g.addAll(fVar.g);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            str = ((l) list.get(i)).a();
            this.f.add(str);
            this.g.add(str);
        }
    }

    @NonNull
    public h i() {
        if (this.i) {
            androidx.work.f.a().d(a, String.format("Already enqueued work ids (%s)", new Object[]{TextUtils.join(", ", this.f)}), new Throwable[0]);
        } else {
            b bVar = new b(this);
            this.b.h().a(bVar);
            this.j = bVar.a();
        }
        return this.j;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean j() {
        return a(this, new HashSet());
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    private static boolean a(@NonNull f fVar, @NonNull Set<String> set) {
        set.addAll(fVar.e());
        Set a = a(fVar);
        for (String contains : set) {
            if (a.contains(contains)) {
                return true;
            }
        }
        List<f> h = fVar.h();
        if (!(h == null || h.isEmpty())) {
            for (f a2 : h) {
                if (a(a2, set)) {
                    return true;
                }
            }
        }
        set.removeAll(fVar.e());
        return false;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Set<String> a(f fVar) {
        HashSet hashSet = new HashSet();
        List<f> h = fVar.h();
        if (!(h == null || h.isEmpty())) {
            for (f e : h) {
                hashSet.addAll(e.e());
            }
        }
        return hashSet;
    }
}
