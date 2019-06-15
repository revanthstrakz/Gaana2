package androidx.work.impl.a;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import androidx.work.f;
import androidx.work.impl.a.a.b;
import androidx.work.impl.a.a.c;
import androidx.work.impl.a.a.c.a;
import androidx.work.impl.a.a.e;
import androidx.work.impl.a.a.g;
import androidx.work.impl.a.a.h;
import androidx.work.impl.b.j;
import java.util.ArrayList;
import java.util.List;

public class d implements a {
    private static final String a = f.a("WorkConstraintsTracker");
    @Nullable
    private final c b;
    private final c[] c;
    private final Object d = new Object();

    public d(Context context, @Nullable c cVar) {
        context = context.getApplicationContext();
        this.b = cVar;
        this.c = new c[]{new androidx.work.impl.a.a.a(context), new b(context), new h(context), new androidx.work.impl.a.a.d(context), new g(context), new androidx.work.impl.a.a.f(context), new e(context)};
    }

    public void a(@NonNull List<j> list) {
        synchronized (this.d) {
            int i = 0;
            for (c a : this.c) {
                a.a(null);
            }
            for (c a2 : this.c) {
                a2.a((List) list);
            }
            c[] cVarArr = this.c;
            int length = cVarArr.length;
            while (i < length) {
                cVarArr[i].a((a) this);
                i++;
            }
        }
    }

    public void a() {
        synchronized (this.d) {
            for (c a : this.c) {
                a.a();
            }
        }
    }

    public boolean a(@NonNull String str) {
        synchronized (this.d) {
            for (c a : this.c) {
                if (a.a(str)) {
                    f.a().b(a, String.format("Work %s constrained by %s", new Object[]{str, r1[r4].getClass().getSimpleName()}), new Throwable[0]);
                    return false;
                }
            }
            return true;
        }
    }

    public void b(@NonNull List<String> list) {
        synchronized (this.d) {
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                if (a(str)) {
                    f.a().b(a, String.format("Constraints met for %s", new Object[]{str}), new Throwable[0]);
                    arrayList.add(str);
                }
            }
            if (this.b != null) {
                this.b.a(arrayList);
            }
        }
    }

    public void c(@NonNull List<String> list) {
        synchronized (this.d) {
            if (this.b != null) {
                this.b.b(list);
            }
        }
    }
}
